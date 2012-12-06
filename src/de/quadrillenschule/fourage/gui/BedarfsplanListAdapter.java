/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.quadrillenschule.fourage.gui;

import android.app.Activity;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import de.quadrillenschule.fourage.model.Bedarf;
import java.util.ArrayList;

public class BedarfsplanListAdapter extends ArrayAdapter<Bedarf> {

    private ArrayList<Bedarf> bedarfsplaene;
    private int viewId;
    NoFilter noFilter;

    public BedarfsplanListAdapter(Context pcontext, ArrayList<Bedarf> values, int viewId, Activity activity) {
        super(pcontext, viewId, values);
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //  this.context = pcontext;
        this.bedarfsplaene = values;


    }

    @Override
    public Filter getFilter() {
        if (noFilter == null) {
            noFilter = new NoFilter();
        }
        return noFilter;
    }

    private class NoFilter extends Filter {

        protected FilterResults performFiltering(CharSequence prefix) {
            return new FilterResults();
        }

        protected void publishResults(CharSequence constraint,
                FilterResults results) {
            // Do nothing
        }
    }
}
