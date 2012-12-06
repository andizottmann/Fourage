/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.quadrillenschule.fourage.helper;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author andi
 */
public class Helper {

    public static String withUnit(double d) {
        String unit = "kg";
        if (d < 1.0) {
            unit = "g";
            d = d * 1000;
        }
        if (d < 1.0) {
            unit = "mg";
            d = d * 1000;
        }
        return round(d) + " " + unit;
    }

    public static String round(double d) {
        //   =new DecimalFormat("0.00000");
        NumberFormat df = DecimalFormat.getInstance(Locale.US);
        df.setMaximumFractionDigits(5);
        String dstr = "" + df.format(d);
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
        if (retval.endsWith(".")) {
            retval = retval.replace(".", "");
        }
        return retval;

    }
}
