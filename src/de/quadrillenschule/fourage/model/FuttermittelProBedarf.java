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
public class FuttermittelProBedarf {

    public ArrayList<Futtermittel> futtermittel;
    public Bedarf bedarf;

    public FuttermittelProBedarf() {
      futtermittel=new ArrayList();
    }
    
    public FuttermittelProBedarf clone() {
        FuttermittelProBedarf retval=new FuttermittelProBedarf();
        for (Futtermittel fm:this.futtermittel){
        retval.futtermittel.add(fm.clone());
        }
        retval.bedarf=this.bedarf;
        return retval;

    }
}
