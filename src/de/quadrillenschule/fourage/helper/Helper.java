/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.quadrillenschule.fourage.helper;

/**
 *
 * @author andi
 */
public class Helper {

    public static String round(double d) {
        String dstr = "" + d;
        String retval = "";
        int numbercounter = 0;
        for (int i = 0; i < dstr.length(); i++) {
            if (numbercounter < 3) {
            if (dstr.charAt(i) == ('0') || dstr.charAt(i) == ('.')) {
                retval += dstr.charAt(i);
                if (numbercounter > 0) {
                    numbercounter++;
                }
            } else {
                
                    retval += dstr.charAt(i);
                    numbercounter++;
                }
            }
        }
        return retval;

    }
}
