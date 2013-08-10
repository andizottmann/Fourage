/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.quadrillenschule.fourage.model;

import android.util.JsonReader;
import android.util.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author andi
 */
public class Pferd {

    private String name = "Standardpferd";
    private double gewicht = 600.0;
    private Date geburtstag = new Date(2000, 1, 1);
    private double futtrigkeit = 1.0;
    private String barcode = "";
    private Bedarf bedarf;
    public ArrayList<FuttermittelProBedarf> futtermittelProBedarf;

    public Pferd() {
        futtermittelProBedarf = new ArrayList();

    }

    public Pferd(JsonReader reader, BedarfsKatalog bedarfsKatalog,
            FuttermittelKatalog futtermittelKatalog) throws IOException {
        futtermittelProBedarf = new ArrayList();
        reader.beginObject();
        while (reader.hasNext()) {
            String current = reader.nextName();
            if (current.equals("name")) {
                name = reader.nextString();
            } else if (current.equals("barcode")) {
                barcode = reader.nextString();
            } else if (current.equals("gewicht")) {
                gewicht = reader.nextDouble();
            } else if (current.equals("bedarf")) {
                String bedarfstr = reader.nextString();
                bedarf = bedarfsKatalog.getByName(bedarfstr);
            } else if (current.equals("futtrigkeit")) {
                futtrigkeit = reader.nextDouble();
            } else if (current.equals("futtermittelprobedarf")) {
                reader.beginArray();
                while (reader.hasNext()) {
                    FuttermittelProBedarf fpb = new FuttermittelProBedarf();
                    fpb.bedarf = bedarfsKatalog.getByName(reader.nextString());
                    reader.beginArray();
                    while (reader.hasNext()) {
                        Futtermittel fm = futtermittelKatalog.getFuttermittelByName(reader.nextString()).clone();
                        fm.setMenge(reader.nextDouble());
                        fpb.futtermittel.add(fm);

                    }
                    reader.endArray();
                    futtermittelProBedarf.add(fpb);
                }
                reader.endArray();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
    }

    public void writeJson(JsonWriter writer) throws IOException {
        writer.beginObject();
        writer.name("name").value(getName());
        writer.name("barcode").value(barcode);
        writer.name("gewicht").value(gewicht);
        writer.name("bedarf").value(bedarf.getName());
        writer.name("futtrigkeit").value(futtrigkeit);
        writer.name("futtermittelprobedarf");
        writer.beginArray();
        for (FuttermittelProBedarf fpb : futtermittelProBedarf) {
            writer.value(fpb.bedarf.getName());
            writer.beginArray();
            for (Futtermittel fm : fpb.futtermittel) {
                writer.value(fm.getName());
                writer.value(fm.getMenge());
            }
            writer.endArray();
        }
        writer.endArray();
        writer.endObject();
    }

    public String bedarfToString() {
        return bedarf.calcBedarfFuerPferd(this).toString();
    }

    public double getIstWertNaehrstoff(Naehrstoff naehrstoff) {
        double retval = 0.0;
        for (Futtermittel fm : futtermittel(bedarf)) {
            for (Naehrstoff ns : fm.getNaehrstoffe()) {
                if (ns.getName().equals(naehrstoff.getName())) {
                    retval += fm.getMenge() * ns.getMenge();
                }
            }
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
     * @return the gewicht
     */
    public double getGewicht() {
        return gewicht;
    }

    /**
     * @param gewicht the gewicht to set
     */
    public void setGewicht(double gewicht) {
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
    public double getFuttrigkeit() {
        return futtrigkeit;
    }

    /**
     * @param futtrigkeit the futtrigkeit to set
     */
    public void setFuttrigkeit(double futtrigkeit) {
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

    public String toString() {
        return getName();
    }

    /**
     * @return the futtermittel
     */
    public ArrayList<Futtermittel> getFuttermittel() {
        return futtermittel(bedarf);
    }

    /**
     * @param futtermittel the futtermittel to set
     */
    public void setFuttermittelProBedarf(ArrayList<FuttermittelProBedarf> futtermittelProBedarf) {
        this.futtermittelProBedarf = futtermittelProBedarf;
    }

    @Override
    public Pferd clone() {
        Pferd retval = new Pferd();
        retval.setName(name + "2");
        retval.setGewicht(gewicht);
        retval.setBedarf(bedarf);
        retval.setFuttrigkeit(futtrigkeit);
        for (FuttermittelProBedarf f : futtermittelProBedarf) {
            retval.futtermittelProBedarf.add(f.clone());
        }
        return retval;
    }

    public ArrayList<Futtermittel> futtermittel(Bedarf bedarf) {
        for (FuttermittelProBedarf fmp : futtermittelProBedarf) {
            if (fmp.bedarf.getName().equals(bedarf.getName())) {
                return fmp.futtermittel;
            }
        }
        return null;
    }
}
