/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.quadrillenschule.fourage.gui;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.TextView;
import de.quadrillenschule.fourage.RationsplanActivity;
import de.quadrillenschule.fourage.model.Futtermittel;
import java.util.ArrayList;

public class FuttermittelListAdapter extends ArrayAdapter<Futtermittel> {

    private ArrayList<Futtermittel> futtermittelkatalog;
    Context context;
    NoFilter noFilter;
    Activity activity;

    public FuttermittelListAdapter(Context pcontext, ArrayList<Futtermittel> values, int viewId, Activity activity) {
        super(pcontext, viewId, values);
        this.context = pcontext;
        this.futtermittelkatalog = values;
        this.activity = activity;

    }

     @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        FuttermittelItemView fmv = new FuttermittelItemView((RationsplanActivity) activity, futtermittelkatalog.get(position));
        fmv.update();
        return fmv;
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
