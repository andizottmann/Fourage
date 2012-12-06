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
import de.quadrillenschule.fourage.model.Naehrstoff;
import de.quadrillenschule.fourage.model.Pferd;

public class RationsplanActivity extends Activity {

    int lastPferdeIndex = 0;
    int lastBedarfIndex = 0;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rationsplan);

        int index = ((FourageApplication) this.getApplication()).selectedPferdeIndex;
        final Spinner pferdeSpinner = (Spinner) findViewById(R.id.pferdeSelectorRatio);
        pferdeSpinner.setAdapter(new PferdeListAdapter(this, ((FourageApplication) this.getApplication()).pferde, android.R.layout.simple_spinner_item, this));
        pferdeSpinner.setSelection(index);
        setListeners();
        updateFields(index);


    }

    @Override
    protected void onPause() {
        super.onPause();
        ((FourageApplication) this.getApplication()).savePferde();
    }

    void setListeners() {
        final Spinner pferdeSpinner = (Spinner) findViewById(R.id.pferdeSelectorRatio);
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

        final Spinner bedarfsSpinner = (Spinner) findViewById(R.id.bedarfsSelectorRP);
        bedarfsSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if (lastBedarfIndex == arg2) {
                    return;
                }
                FourageApplication fa = ((FourageApplication) getApplication());
                final Spinner pferdeSpinner = (Spinner) findViewById(R.id.pferdeSelectorRatio);
                final Spinner bedarfsSpinner = (Spinner) findViewById(R.id.bedarfsSelectorRP);

                ((Pferd) pferdeSpinner.getSelectedItem()).setBedarf(fa.bedarfsKatalog.bedarfsliste.get(bedarfsSpinner.getSelectedItemPosition()));
                lastBedarfIndex = arg2;
                updateFields(pferdeSpinner.getSelectedItemPosition());
            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

    }

    void updateFields(int pferdeindex) {
        Pferd pferd = ((FourageApplication) getApplication()).pferde.get(pferdeindex);
        Bedarf bedarf = pferd.getBedarf().calcBedarfFuerPferd(pferd);
        updateRationsPlan();
        ListView futtermittelList = (ListView) findViewById(R.id.futtermittelList);

        futtermittelList.setAdapter(new FuttermittelListAdapter(this, pferd.getFuttermittel(), R.id.futtermittelList, this));

        final Spinner bedarfsSpinner = (Spinner) findViewById(R.id.bedarfsSelectorRP);
        bedarfsSpinner.setAdapter(new BedarfsplanListAdapter(this, ((FourageApplication) this.getApplication()).bedarfsKatalog.bedarfsliste, android.R.layout.simple_spinner_item, this));
        Bedarf bedarf2 = pferd.getBedarf();
        bedarfsSpinner.setSelection(((FourageApplication) this.getApplication()).bedarfsKatalog.bedarfsliste.indexOf(bedarf2));

    }

    public void updateRationsPlan() {
        final Spinner pferdeSpinner = (Spinner) findViewById(R.id.pferdeSelectorRatio);
        int pferdeindex = pferdeSpinner.getSelectedItemPosition();
        Pferd pferd = ((FourageApplication) getApplication()).pferde.get(pferdeindex);
        Bedarf bedarf = pferd.getBedarf().calcBedarfFuerPferd(pferd);
        //   TextView tv = (TextView) findViewById(R.id.rationstext);
        TextView tvn = (TextView) findViewById(R.id.naehrstoffname);
        TextView tvs = (TextView) findViewById(R.id.naehrstoffsoll);
        TextView tvi = (TextView) findViewById(R.id.naehrstoffist);
        //   String tvtxt = bedarf.toString() + " \n";

        String tvns = "<b> </b><br>";
        String tvss = "<b>Bedarf</b><br>";
        String tvis = "<b>Gedeckt</b><br>";
        for (Naehrstoff n : bedarf.getNaehrstoffe()) {
            String color = "red";
            double a = n.getMenge();
            double b = pferd.getIstWertNaehrstoff(n);
            double c = ( b-a) / a;
            if (c > -0.2) {
                color = "yellow";
            }
            if (c > -0.1) {
                color = "green";
            }
            if (c > 0.2) {
                color="yellow";
            }
            tvns += "<font size=\"+2\" color=\""+color+"\">&#9679;</font> " + n.getName() + "<br>";
            if (n.getName().equals("Energie")) {
                tvss += Helper.round(n.getMenge()) + " MJ <br>";
                tvis += Helper.round(pferd.getIstWertNaehrstoff(n)) + " MJ <br>";
            } else {
                tvss += Helper.round(n.getMenge()) + " kg <br>";
                tvis += Helper.round(pferd.getIstWertNaehrstoff(n)) + " kg <br>";
            }
        }
        // tv.setText(tvtxt);
        tvn.setText(Html.fromHtml(tvns));
        tvs.setText(Html.fromHtml(tvss));
        tvi.setText(Html.fromHtml(tvis));

    }
}
