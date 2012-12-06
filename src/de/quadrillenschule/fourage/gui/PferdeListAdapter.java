/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.quadrillenschule.fourage.gui;

import android.app.Activity;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import de.quadrillenschule.fourage.PferdeVerwaltenActivity;
import de.quadrillenschule.fourage.model.Pferd;
import java.util.ArrayList;


public class PferdeListAdapter extends ArrayAdapter<Pferd> {

    private ArrayList<Pferd> pferde;
    private int viewId;
     NoFilter noFilter;

    public PferdeListAdapter(Context pcontext, ArrayList<Pferd> values, int viewId, Activity activity) {
        super(pcontext,viewId, values);
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.pferde = values;
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
