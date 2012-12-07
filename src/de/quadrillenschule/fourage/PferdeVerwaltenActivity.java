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
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import de.quadrillenschule.fourage.gui.BedarfsplanListAdapter;
import de.quadrillenschule.fourage.gui.PferdeListAdapter;
import de.quadrillenschule.fourage.model.Bedarf;
import de.quadrillenschule.fourage.model.Pferd;

/**
 *
 * @author andi
 */
public class PferdeVerwaltenActivity extends Activity {

    int lastBedarfIndex = 0;
    int lastPferdeIndex = 0;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pferdeverwalten);

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
        final Spinner pferdeSpinner = (Spinner) findViewById(R.id.pferdeSelector);
        SpinnerAdapter adapter = new PferdeListAdapter(this, ((FourageApplication) this.getApplication()).pferde, android.R.layout.simple_spinner_item, this);

        pferdeSpinner.setAdapter(adapter);
        pferdeSpinner.setSelection(pferdeindex);

        final EditText pet = (EditText) findViewById(R.id.pferdenameedittext);
        pet.setText(((FourageApplication) this.getApplication()).pferde.get(pferdeindex).getName());

        final SeekBar gewichtSeekBar = (SeekBar) findViewById(R.id.pferdegewicht);
        gewichtSeekBar.setProgress((int) ((FourageApplication) this.getApplication()).pferde.get(pferdeindex).getGewicht() - 300);

        final TextView gewichtsAnzeige = (TextView) findViewById(R.id.pferdegewichtanzeige);
        gewichtsAnzeige.setText("Gewicht: " + ((FourageApplication) this.getApplication()).pferde.get(pferdeindex).getGewicht() + " kg");

        final SeekBar futterigkeitSeekBar = (SeekBar) findViewById(R.id.futterigkeit);
        futterigkeitSeekBar.setProgress((int) (((FourageApplication) this.getApplication()).pferde.get(pferdeindex).getFuttrigkeit() * 10.0) - 5);

        final TextView futterigkeitsAnzeige = (TextView) findViewById(R.id.futterigkeittanzeige);
        futterigkeitsAnzeige.setText("Futterigkeit: " + ((int) (((FourageApplication) this.getApplication()).pferde.get(pferdeindex).getFuttrigkeit() * 100.0)) + " %");

        final Spinner bedarfsSpinner = (Spinner) findViewById(R.id.bedarfsSelector);
        bedarfsSpinner.setAdapter(new BedarfsplanListAdapter(this, ((FourageApplication) this.getApplication()).bedarfsKatalog.bedarfsliste, android.R.layout.simple_spinner_item, this));
        Bedarf bedarf = ((FourageApplication) this.getApplication()).pferde.get(pferdeindex).getBedarf();
        bedarfsSpinner.setSelection(((FourageApplication) this.getApplication()).bedarfsKatalog.bedarfsliste.indexOf(bedarf));


    }

    void setListeners() {
        final Spinner pferdeSpinner = (Spinner) findViewById(R.id.pferdeSelector);
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


        final Button addpferd = (Button) findViewById(R.id.pferdhinzufuegenbutton);
        addpferd.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                final Spinner pferdeSpinner = (Spinner) findViewById(R.id.pferdeSelector);
                ((FourageApplication) getApplication()).pferde.add(((Pferd) pferdeSpinner.getSelectedItem()).clone());
                updateFields(((FourageApplication) getApplication()).pferde.size() - 1);
            }
        });

        final Button removepferd = (Button) findViewById(R.id.pferdloeschenbutton);
        removepferd.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                final Spinner pferdeSpinner = (Spinner) findViewById(R.id.pferdeSelector);
                ((FourageApplication) getApplication()).pferde.remove(((Pferd) pferdeSpinner.getSelectedItem()));
                updateFields(0);
            }
        });

        final EditText pet = (EditText) findViewById(R.id.pferdenameedittext);
        pet.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                final EditText pet = (EditText) findViewById(R.id.pferdenameedittext);
                final Spinner pferdeSpinner = (Spinner) findViewById(R.id.pferdeSelector);
                ((Pferd) pferdeSpinner.getSelectedItem()).setName(pet.getText() + "");
                //updateFields(lastPferdeIndex);
            }

            public void afterTextChanged(Editable arg0) {
            }
        });




        final SeekBar gewichtSeekBar = (SeekBar) findViewById(R.id.pferdegewicht);

        gewichtSeekBar.setMax(600);
        gewichtSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onSeekBarChange(NumberPicker arg0, int arg1, int arg2) {

                updateFields(lastPferdeIndex);

            }

            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
                final Spinner pferdeSpinner = (Spinner) findViewById(R.id.pferdeSelector);
                ((Pferd) pferdeSpinner.getSelectedItem()).setGewicht(arg1 + 300);
                final TextView gewichtsAnzeige = (TextView) findViewById(R.id.pferdegewichtanzeige);
                gewichtsAnzeige.setText("Gewicht: " + ((Pferd) pferdeSpinner.getSelectedItem()).getGewicht() + " kg");

            }

            public void onStartTrackingTouch(SeekBar arg0) {
            }

            public void onStopTrackingTouch(SeekBar arg0) {
            }
        });

        final SeekBar futtrigkeitSeekBar = (SeekBar) findViewById(R.id.futterigkeit);

        futtrigkeitSeekBar.setMax(10);
        futtrigkeitSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onSeekBarChange(NumberPicker arg0, int arg1, int arg2) {

                updateFields(lastPferdeIndex);

            }

            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
                final Spinner pferdeSpinner = (Spinner) findViewById(R.id.pferdeSelector);
                ((Pferd) pferdeSpinner.getSelectedItem()).setFuttrigkeit((arg1+5.0)/10.0);
                final TextView futterigkeitsAnzeige = (TextView) findViewById(R.id.futterigkeittanzeige);
                futterigkeitsAnzeige.setText("Futterigkeit: " + ((int)(((Pferd) pferdeSpinner.getSelectedItem()).getFuttrigkeit()*100.0)) + " %");

            }

            public void onStartTrackingTouch(SeekBar arg0) {
            }

            public void onStopTrackingTouch(SeekBar arg0) {
            }
        });


        final Spinner bedarfsSpinner = (Spinner) findViewById(R.id.bedarfsSelector);
        bedarfsSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if (lastBedarfIndex == arg2) {
                    return;
                }
                FourageApplication fa = ((FourageApplication) getApplication());
                final Spinner pferdeSpinner = (Spinner) findViewById(R.id.pferdeSelector);
                final Spinner bedarfsSpinner = (Spinner) findViewById(R.id.bedarfsSelector);

                ((Pferd) pferdeSpinner.getSelectedItem()).setBedarf(fa.bedarfsKatalog.bedarfsliste.get(bedarfsSpinner.getSelectedItemPosition()));
                lastBedarfIndex = arg2;
                updateFields(pferdeSpinner.getSelectedItemPosition());
            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });


    }
}
