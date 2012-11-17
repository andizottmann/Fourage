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

    static final float KEINHOECHSTANTEIL = -1.0f;
    static final float KEINFESTWERT=-1.0f;
    private String name;
    private float hoechstanteil = KEINHOECHSTANTEIL;
    private float festwert = KEINFESTWERT;
    private ArrayList<Naehrstoff> naehrstoffe;

    public Futtermittel(String name,ArrayList<Naehrstoff> naehrstoffe){
      this.name=name;
      this.naehrstoffe=naehrstoffe;
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
     * @return the hoechstanteil
     */
    public float getHoechstanteil() {
        return hoechstanteil;
    }

    /**
     * @param hoechstanteil the hoechstanteil to set
     */
    public void setHoechstanteil(float hoechstanteil) {
        this.hoechstanteil = hoechstanteil;
    }

    /**
     * @return the festwert
     */
    public float getFestwert() {
        return festwert;
    }

    /**
     * @param festwert the festwert to set
     */
    public void setFestwert(float festwert) {
        this.festwert = festwert;
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
