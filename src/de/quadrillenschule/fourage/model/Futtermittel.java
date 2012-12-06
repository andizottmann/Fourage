/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.quadrillenschule.fourage.model;

import java.util.ArrayList;

/**
 *
 * @author andi
 */
public class Futtermittel {

    private String name;
    private double menge = 0.0;
    private double maximalwert = 0.0;
    private ArrayList<Naehrstoff> naehrstoffe;

    public Futtermittel(String name, ArrayList<Naehrstoff> naehrstoffe) {
        this.name = name;
        this.naehrstoffe = naehrstoffe;
    }

    @Override
    public Futtermittel clone() {
        ArrayList<Naehrstoff> cn = new ArrayList();
        for (Naehrstoff n : naehrstoffe) {
            cn.add(n.clone());
        }
        Futtermittel retval = new Futtermittel(name, cn);
        retval.setMaximalwert(maximalwert);
        return retval;
    }

    public void extractAttributesFromNaehrstoffe() {
        ArrayList<Naehrstoff> removeables = new ArrayList();

        for (Naehrstoff n : naehrstoffe) {
            if (n.getName().equals("Maximalwert")) {
                maximalwert = n.getMenge();
                removeables.add(n);
            }
        }

        naehrstoffe.removeAll(removeables);

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

    @Override
    public String toString() {
        return getName();
    }

    /**
     * @return the menge
     */
    public double getMenge() {
        return menge;
    }

    /**
     * @param menge the menge to set
     */
    public void setMenge(double menge) {
        this.menge = menge;
    }

    /**
     * @return the maximalwert
     */
    public double getMaximalwert() {
        return maximalwert;
    }

    /**
     * @param maximalwert the maximalwert to set
     */
    public void setMaximalwert(double maximalwert) {
        this.maximalwert = maximalwert;
    }
}
