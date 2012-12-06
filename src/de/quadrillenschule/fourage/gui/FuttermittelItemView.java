/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.quadrillenschule.fourage.gui;

import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import de.quadrillenschule.fourage.RationsplanActivity;
import de.quadrillenschule.fourage.helper.Helper;
import de.quadrillenschule.fourage.model.Futtermittel;

/**
 *
 * @author andi
 */
public class FuttermittelItemView extends LinearLayout implements SeekBar.OnSeekBarChangeListener {

    TextView tv;
    SeekBar seekBar;
    Futtermittel futtermittel;
    RationsplanActivity rationsplanActivity;

    public FuttermittelItemView(RationsplanActivity rationsplanActivity, Futtermittel futtermittel) {
        super(rationsplanActivity);
        this.rationsplanActivity = rationsplanActivity;
        setOrientation(VERTICAL);
        setLeft(3);
        setRight(3);
        this.futtermittel = futtermittel;
        tv = new TextView(rationsplanActivity);
        tv.setText(futtermittel.getName() + ": " + Helper.withUnit(futtermittel.getMenge()));
        tv.setTextColor(Color.BLACK);
        LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, 0.1f);
        this.addView(tv, lp);
        seekBar = new SeekBar(rationsplanActivity);
        seekBar.setMax(40);
        this.addView(seekBar, lp);

        seekBar.setOnSeekBarChangeListener(this);
        update();
    }

    public void update() {
        seekBar.setProgress((int) (futtermittel.getMenge() * (40.0/futtermittel.getMaximalwert() )));


    }

    public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
        futtermittel.setMenge(seekBar.getProgress() * (futtermittel.getMaximalwert() / 40.0));
        tv.setText(futtermittel.getName() + ": " + Helper.withUnit(futtermittel.getMenge()));
        rationsplanActivity.updateRationsPlan();
    }

    public void onStartTrackingTouch(SeekBar arg0) {
        //   throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onStopTrackingTouch(SeekBar arg0) {
        //   throw new UnsupportedOperationException("Not supported yet.");
    }
}
