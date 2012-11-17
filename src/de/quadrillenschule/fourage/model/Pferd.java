/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.quadrillenschule.fourage.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author andi
 */
public class Pferd {

    private String name = "Standardpferd";
    private float gewicht = 550f;
    private Date geburtstag = new Date(2000, 1, 1);
    private float futtrigkeit = 1.0f;
    private String barcode = "";
    private Bedarf bedarf;
    private ArrayList<Futtermittel> futtermittel;
    private Rationsplan rationsplan;

    public Pferd() {
    }

    public String bedarfToString() {
        return bedarf.calcBedarfFuerPferd(this).toString();
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
     * @return the gewicht
     */
    public float getGewicht() {
        return gewicht;
    }

    /**
     * @param gewicht the gewicht to set
     */
    public void setGewicht(float gewicht) {
        this.gewicht = gewicht;
    }

    /**
     * @return the geburtstag
     */
    public Date getGeburtstag() {
        return geburtstag;
    }

    /**
     * @param geburtstag the geburtstag to set
     */
    public void setGeburtstag(Date geburtstag) {
        this.geburtstag = geburtstag;
    }

    /**
     * @return the futtrigkeit
     */
    public float getFuttrigkeit() {
        return futtrigkeit;
    }

    /**
     * @param futtrigkeit the futtrigkeit to set
     */
    public void setFuttrigkeit(float futtrigkeit) {
        this.futtrigkeit = futtrigkeit;
    }

    /**
     * @return the barcode
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * @param barcode the barcode to set
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    /**
     * @return the bedarf
     */
    public Bedarf getBedarf() {
        return bedarf;
    }

    /**
     * @param bedarf the bedarf to set
     */
    public void setBedarf(Bedarf bedarf) {
        this.bedarf = bedarf;
    }

}
