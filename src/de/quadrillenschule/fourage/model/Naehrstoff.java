/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.quadrillenschule.fourage.model;

/**
 *
 * @author andi
 */
public class Naehrstoff {

    private String name = "";
    private double menge = 0.0;

    public Naehrstoff(String name, double menge) {
        setName(name);
        setMenge(menge);
    }

    @Override
    public Naehrstoff clone(){
        return new Naehrstoff(name,menge);
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
}
