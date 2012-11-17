/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.quadrillenschule.fourage;

import android.app.Application;
import de.quadrillenschule.fourage.model.BedarfsKatalog;
import de.quadrillenschule.fourage.model.FuttermittelKatalog;
import de.quadrillenschule.fourage.model.Pferd;
import java.util.ArrayList;

/**
 *
 * @author andi
 */
public class FourageApplication extends Application {

    ArrayList<Pferd> pferde;
    BedarfsKatalog bedarfsKatalog;
    FuttermittelKatalog futtermittelKatalog;

    public FourageApplication() {
        super();

    }

    @Override
    public void onCreate() {
        futtermittelKatalog = new FuttermittelKatalog();
        futtermittelKatalog.lade(this);
        bedarfsKatalog = new BedarfsKatalog();
        bedarfsKatalog.lade(this);
        pferde = new ArrayList();
        Pferd pferd = new Pferd();
        pferd.setBedarf(bedarfsKatalog.bedarfsliste.get(1));
        pferde.add(pferd);
    }
}
