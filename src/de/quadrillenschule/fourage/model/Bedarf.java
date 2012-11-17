/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.quadrillenschule.fourage.model;

import de.quadrillenschule.fourage.helper.Helper;
import java.util.ArrayList;

/**
 *
 * @author andi
 */
public class Bedarf {

    private String name;
    private ArrayList<Naehrstoff> naehrstoffe;

    public Bedarf(String name, ArrayList<Naehrstoff> naehrstoffe) {
        this.name = name;
        this.naehrstoffe = naehrstoffe;
    }

    public Bedarf calcBedarfFuerPferd(Pferd pferd) {
        ArrayList<Naehrstoff> retval = new ArrayList();
        for (Naehrstoff n : naehrstoffe) {
            double menge = n.getMenge() * pferd.getGewicht();
            if (n.getName().equals("Energie")) {
                menge = menge * pferd.getFuttrigkeit();
            }
            Naehrstoff rn = new Naehrstoff(n.getName(), menge);
            retval.add(rn);
        }
        return new Bedarf(getName() + "/" + pferd.getName(), retval);
    }

    public String toString() {
        String retval = getName() + "\n";
        for (Naehrstoff n : getNaehrstoffe()) {
            retval += n.getName() + ": " + Helper.round(n.getMenge())+"\n";
        }
        return retval;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the naehrstoffe
     */
    public ArrayList<Naehrstoff> getNaehrstoffe() {
        return naehrstoffe;
    }

    /**
     * @param naehrstoffe the naehrstoffe to set
     */
    public void setNaehrstoffe(ArrayList<Naehrstoff> naehrstoffe) {
        this.naehrstoffe = naehrstoffe;
    }
}
