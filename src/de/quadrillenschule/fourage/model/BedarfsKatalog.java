/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.quadrillenschule.fourage.model;

import android.app.Application;
import de.quadrillenschule.fourage.R;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author andi
 */
public class BedarfsKatalog {

    public ArrayList<Bedarf> bedarfsliste;

    public BedarfsKatalog() {
        bedarfsliste = new ArrayList();
    }

     public void lade(Application app) {
        try {
            InputStream is = app.getResources().openRawResource(R.raw.bedarf);
            Writer writer = new StringWriter();
            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } catch (Exception e) {
            }
            String jsonString = writer.toString();
            JSONObject jo = new JSONObject(jsonString);
            for (int i = 0; i < jo.names().length(); i++) {
                String bedarfsname = jo.names().getString(i);
                ArrayList<Naehrstoff> naehrstoffelist = new ArrayList();
                JSONObject jnaehrstoffliste = jo.getJSONObject(bedarfsname);
                for (int j = 0; j < jnaehrstoffliste.length(); j++) {
                    String naehrstoff = jnaehrstoffliste.names().getString(j);
                    naehrstoffelist.add(new Naehrstoff(naehrstoff, jnaehrstoffliste.getDouble(naehrstoff)));
                }
                Collections.sort(naehrstoffelist,new Comparator<Naehrstoff>() {

                    public int compare(Naehrstoff arg0, Naehrstoff arg1) {
                        return (int) Math.round((arg1.getMenge()-arg0.getMenge())*10000);
                    }
                });
                bedarfsliste.add(new Bedarf(bedarfsname, naehrstoffelist));

            }

        } catch (JSONException ex) {
            ex.printStackTrace();
            ;
        }
    }
}
