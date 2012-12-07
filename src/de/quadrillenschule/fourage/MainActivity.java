package de.quadrillenschule.fourage;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import de.quadrillenschule.fourage.model.Pferd;
import java.util.ArrayList;

public class MainActivity extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ((Button) findViewById(R.id.pferdeverwaltenbutton)).setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent myIntent = new Intent().setClass(getApplicationContext(), PferdeVerwaltenActivity.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                startActivity(myIntent);
            }
        });

           ((Button) findViewById(R.id.futtermittelverwaltenbutton)).setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent myIntent = new Intent().setClass(getApplicationContext(), FuttermittelVerwaltenActivity.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                startActivity(myIntent);
            }
        });

        ((Button) findViewById(R.id.rationsplanbutton)).setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent myIntent = new Intent().setClass(getApplicationContext(), RationsplanActivity.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                startActivity(myIntent);
            }
        });
         ((Button) findViewById(R.id.rationsplanviewbutton)).setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                  Intent myIntent = new Intent().setClass(getApplicationContext(), RationsplanViewActivity.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                startActivity(myIntent);
            }
        });

        ((Button) findViewById(R.id.scanbutton)).setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                intent.setPackage("com.google.zxing.client.android");
                //  intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        final ArrayList<Pferd> pferde = ((FourageApplication) getApplication()).pferde;
        CharSequence[] pferdeItems = new CharSequence[pferde.size()];
        for (int i = 0; i < pferde.size(); i++) {
            pferdeItems[i] = pferde.get(i).getName();
        }

        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                final String barcode = intent.getStringExtra("SCAN_RESULT");
                Pferd retval = null;
                for (Pferd p : pferde) {

                    if (p.getBarcode().equals(barcode)) {

                        retval = p;
                    }
                }
                if (retval != null) {
                    ((FourageApplication) getApplication()).selectedPferdeIndex = ((FourageApplication) getApplication()).pferde.indexOf(retval);
                    Intent myIntent = new Intent().setClass(getApplicationContext(), RationsplanViewActivity.class);
                    myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                    startActivity(myIntent);

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle(R.string.pferdmitbarcode).setItems(pferdeItems, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            pferde.get(which).setBarcode(barcode);
                        }
                    });
                    builder.create().show();
                }
            } else if (resultCode == RESULT_CANCELED) {
            }
        }
    }
}
