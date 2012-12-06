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
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Filter;
import de.quadrillenschule.fourage.model.Futtermittel;
import de.quadrillenschule.fourage.model.FuttermittelKatalog;
import java.util.ArrayList;

public class FuttermittelKatalogListAdapter extends ArrayAdapter<Futtermittel> {

    private ArrayList<Futtermittel> futtermittelkatalog, futtermittelpropferd;
    Context context;
    NoFilter noFilter;
    Activity activity;

    public FuttermittelKatalogListAdapter(
            Context pcontext, ArrayList<Futtermittel> values, ArrayList<Futtermittel> pferdvalues, int viewId, Activity activity) {
        super(pcontext, viewId, values);
        this.context = pcontext;
        this.futtermittelkatalog = values;
        this.futtermittelpropferd = pferdvalues;
        this.activity = activity;

    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        CheckBox retval = new CheckBox(context);
        retval.setText(futtermittelkatalog.get(position).getName());
        retval.setChecked(itemContained(position));
        retval.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                String fm = "" + arg0.getText();
                if (arg1) {
                    if (!itemContained(fm)) {
                        futtermittelpropferd.add(getKatalogItem(fm));
                        FuttermittelKatalog.selfsort(futtermittelpropferd);
                    }

                } else {
                    if (itemContained(fm)) {
                        futtermittelpropferd.remove(getPositionPferdeFM(fm));
                          FuttermittelKatalog.selfsort(futtermittelpropferd);
                    }
                }
            }
        });

        return retval;
    }

    public boolean itemContained(int position) {
        Futtermittel fm = futtermittelkatalog.get(position);
        for (Futtermittel fmp : futtermittelpropferd) {
            if (fmp.getName().equals(fm.getName())) {
                return true;
            }
        }

        return false;
    }

    public boolean itemContained(String fm) {
        //  Futtermittel fm = futtermittelkatalog.get(position);
        for (Futtermittel fmp : futtermittelpropferd) {
            if (fmp.getName().equals(fm)) {
                return true;
            }
        }

        return false;
    }

    public Futtermittel getKatalogItem(String fm) {
        for (Futtermittel kfm : futtermittelkatalog) {
            if (kfm.getName().equals(fm)) {
                return kfm.clone();
            }
        }
        return null;
    }

    public int getPositionPferdeFM(String fm) {
        for (int i = 0; i < futtermittelpropferd.size(); i++) {
            if (futtermittelpropferd.get(i).getName().equals(fm)) {
                return i;
            }
        }
        return -1;
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
