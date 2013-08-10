package de.quadrillenschule.fourage;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import de.quadrillenschule.fourage.gui.BedarfsplanListAdapter;
import de.quadrillenschule.fourage.gui.FuttermittelListAdapter;
import de.quadrillenschule.fourage.gui.PferdeListAdapter;
import de.quadrillenschule.fourage.helper.Helper;
import de.quadrillenschule.fourage.model.Bedarf;
import de.quadrillenschule.fourage.model.Futtermittel;
import de.quadrillenschule.fourage.model.Naehrstoff;
import de.quadrillenschule.fourage.model.Pferd;

public class RationsplanViewActivity extends Activity {

    int lastPferdeIndex = 0;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rationsplanview);

        int index = ((FourageApplication) this.getApplication()).selectedPferdeIndex;
        final Spinner pferdeSpinner = (Spinner) findViewById(R.id.pferdeSelectorRV);
        pferdeSpinner.setAdapter(new PferdeListAdapter(this, ((FourageApplication) this.getApplication()).pferde, android.R.layout.simple_spinner_item, this));
        pferdeSpinner.setSelection(index);
        setListeners();
        updateFields(index);


    }

    void setListeners() {
        final Spinner pferdeSpinner = (Spinner) findViewById(R.id.pferdeSelectorRV);
        pferdeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if (lastPferdeIndex == arg2) {
                    return;
                }
                lastPferdeIndex = arg2;
                updateFields(arg2);
            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });



    }

    void updateFields(int pferdeindex) {
        ((FourageApplication) this.getApplication()).selectedPferdeIndex=pferdeindex;
        Pferd pferd = ((FourageApplication) getApplication()).pferde.get(pferdeindex);
        updateRationsPlan();
    }

    public void updateRationsPlan() {
        final Spinner pferdeSpinner = (Spinner) findViewById(R.id.pferdeSelectorRV);
        int pferdeindex = pferdeSpinner.getSelectedItemPosition();
        Pferd pferd = ((FourageApplication) getApplication()).pferde.get(pferdeindex);
        Bedarf bedarf = pferd.getBedarf().calcBedarfFuerPferd(pferd);
        //   TextView tv = (TextView) findViewById(R.id.rationstext);
        TextView tvn = (TextView) findViewById(R.id.futtermittelnameview);
        TextView tvi = (TextView) findViewById(R.id.futtermittelmengeview);
        //   String tvtxt = bedarf.toString() + " \n";

        String tvns = "\n";
        String tvis = "\n";
        for (Futtermittel n : pferd.getFuttermittel()) {

            tvns += n.getName() + "\n\n";
            tvis += Helper.withUnit(n.getMenge())+"\n\n";
        }
        // tv.setText(tvtxt);
        tvn.setText(tvns);
        tvi.setText(tvis);

    }
}
