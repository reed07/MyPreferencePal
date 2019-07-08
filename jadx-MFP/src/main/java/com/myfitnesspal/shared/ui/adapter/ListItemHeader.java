package com.myfitnesspal.shared.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.myfitnesspal.android.R;

public class ListItemHeader implements ListItem {
    public static final int ITEM_VIEW_TYPE = ListItemHeader.class.getCanonicalName().hashCode();
    private String title;

    public ListItemHeader(String str) {
        this.title = str;
    }

    public int getViewType() {
        return ITEM_VIEW_TYPE;
    }

    public View getView(LayoutInflater layoutInflater, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = layoutInflater.inflate(R.layout.list_item_section_header, viewGroup, false);
        }
        ((TextView) view.findViewById(R.id.title)).setText(this.title);
        view.setClickable(false);
        view.setEnabled(false);
        return view;
    }
}
