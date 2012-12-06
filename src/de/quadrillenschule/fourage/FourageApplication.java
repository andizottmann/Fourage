/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.quadrillenschule.fourage;

import android.app.Application;
import android.os.Message;
import android.util.JsonReader;
import android.util.JsonWriter;
import de.quadrillenschule.fourage.helper.CrashLog;
import de.quadrillenschule.fourage.model.BedarfsKatalog;
import de.quadrillenschule.fourage.model.Futtermittel;
import de.quadrillenschule.fourage.model.FuttermittelKatalog;
import de.quadrillenschule.fourage.model.Pferd;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andi
 */
public class FourageApplication extends Application {

    ArrayList<Pferd> pferde;
    BedarfsKatalog bedarfsKatalog;
    FuttermittelKatalog futtermittelKatalog;
    public int selectedPferdeIndex = 0;

    public FourageApplication() {
        super();

    }

    @Override
    public void onCreate() {
        Thread.setDefaultUncaughtExceptionHandler(new CrashLog(new File(getExternalFilesDir(null), "fourage.log")));
        futtermittelKatalog = new FuttermittelKatalog();
        futtermittelKatalog.lade(this);
        bedarfsKatalog = new BedarfsKatalog();
        bedarfsKatalog.lade(this);
        pferde = new ArrayList();
        loadPferde();

    }

    public void loadPferde() {
        try {
            FileInputStream fis = new FileInputStream(pferdeFile());
            JsonReader jr = new JsonReader(new InputStreamReader(fis));
            jr.beginArray();
            while (jr.hasNext()) {
                pferde.add(new Pferd(jr, bedarfsKatalog, futtermittelKatalog));
            }
            jr.endArray();

            fis.close();
        } catch (Exception e) {
            Pferd pferd = new Pferd();
            pferd.setBedarf(bedarfsKatalog.bedarfsliste.get(0));
            for (Futtermittel f : futtermittelKatalog.futtermittel) {
                pferd.getFuttermittel().add(f.clone());
            }
            pferde.add(pferd);
        }
    }

    public File pferdeFile() {
        File dir = getExternalFilesDir(null);
        return new File(dir, "Pferde.json");
    }

    public void savePferde() {

        try {
            FileOutputStream fos = new FileOutputStream(pferdeFile());

            writeJsonStream(fos);
            fos.close();
        } catch (Exception ex) {
            Logger.getLogger(FourageApplication.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void writeJsonStream(OutputStream out) throws IOException {
        JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
        writer.setIndent("  ");
        writer.beginArray();
        for (Pferd p : pferde) {
            p.writeJson(writer);
        }
        writer.endArray();
        writer.close();
    }
}
