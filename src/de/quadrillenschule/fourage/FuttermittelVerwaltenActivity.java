/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.quadrillenschule.fourage;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import de.quadrillenschule.fourage.gui.BedarfsplanListAdapter;
import de.quadrillenschule.fourage.gui.FuttermittelKatalogListAdapter;
import de.quadrillenschule.fourage.gui.PferdeListAdapter;
import de.quadrillenschule.fourage.model.Bedarf;
import de.quadrillenschule.fourage.model.Pferd;

/**
 *
 * @author andi
 */
public class FuttermittelVerwaltenActivity extends Activity {

    int lastPferdeIndex = 0;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.futtermittelverwalten);

        updateFields(0);
        setListeners();

    }

    @Override
    protected void onPause() {
        super.onPause();
        ((FourageApplication) this.getApplication()).savePferde();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    void updateFields(int pferdeindex) {
        final Spinner pferdeSpinner = (Spinner) findViewById(R.id.pferdeSelectorFM);
        SpinnerAdapter adapter = new PferdeListAdapter(this, ((FourageApplication) this.getApplication()).pferde, android.R.layout.simple_spinner_item, this);

        pferdeSpinner.setAdapter(adapter);
        pferdeSpinner.setSelection(pferdeindex);

        final ListView fmlv = (ListView) findViewById(R.id.futtermittelListFM);
        FuttermittelKatalogListAdapter adpater = new FuttermittelKatalogListAdapter(this, ((FourageApplication) this.getApplication()).futtermittelKatalog.futtermittel, ((FourageApplication) this.getApplication()).pferde.get(pferdeindex).getFuttermittel(), R.id.futtermittelListFM, this);
        fmlv.setAdapter(adpater);

    }

    void setListeners() {
        final Spinner pferdeSpinner = (Spinner) findViewById(R.id.pferdeSelectorFM);
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
}
