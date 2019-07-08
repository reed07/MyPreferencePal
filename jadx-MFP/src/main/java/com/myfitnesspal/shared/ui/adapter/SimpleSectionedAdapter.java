package com.myfitnesspal.shared.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleSectionedAdapter extends ArrayAdapter<ListItem> {
    private LayoutInflater mInflater;
    private Map<Integer, Integer> uniqueViewTypeToViewType = new HashMap();

    public SimpleSectionedAdapter(Context context, List<ListItem> list) {
        super(context, 0, list);
        this.mInflater = LayoutInflater.from(context);
        updateViewTypeMap();
    }

    public void notifyDataSetChanged() {
        updateViewTypeMap();
        super.notifyDataSetChanged();
    }

    public int getViewTypeCount() {
        return Math.max(this.uniqueViewTypeToViewType.size(), 1);
    }

    public int getItemViewType(int i) {
        return ((Integer) this.uniqueViewTypeToViewType.get(Integer.valueOf(((ListItem) getItem(i)).getViewType()))).intValue();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return ((ListItem) getItem(i)).getView(this.mInflater, view, viewGroup);
    }

    private void updateViewTypeMap() {
        this.uniqueViewTypeToViewType.clear();
        int i = 0;
        for (int i2 = 0; i2 < getCount(); i2++) {
            int viewType = ((ListItem) getItem(i2)).getViewType();
            if (!this.uniqueViewTypeToViewType.containsKey(Integer.valueOf(viewType))) {
                int i3 = i + 1;
                this.uniqueViewTypeToViewType.put(Integer.valueOf(viewType), Integer.valueOf(i));
                i = i3;
            }
        }
    }
}
