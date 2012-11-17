package de.quadrillenschule.fourage;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import de.quadrillenschule.fourage.model.Pferd;

public class MainActivity extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView tv = (TextView) findViewById(R.id.mytext);
        Pferd pferd = ((FourageApplication) getApplication()).pferde.get(0);
        tv.setText(pferd.bedarfToString());

        ((Button) findViewById(R.id.pferdeverwaltenbutton)).setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent myIntent = new Intent().setClass(getApplicationContext(), PferdeVerwaltenActivity.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                startActivity(myIntent);
            }
        });
    }
}
