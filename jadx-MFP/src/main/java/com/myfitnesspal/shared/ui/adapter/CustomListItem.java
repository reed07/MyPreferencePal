package com.myfitnesspal.shared.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CustomListItem implements ListItem {
    public static final int ITEM_VIEW_TYPE = CustomListItem.class.getCanonicalName().hashCode();
    private Context context;
    private View customView;
    private int layoutResourceId;

    public CustomListItem(Context context2, int i) {
        this.context = context2;
        this.layoutResourceId = i;
        initView();
    }

    private void initView() {
        this.customView = LayoutInflater.from(this.context).inflate(this.layoutResourceId, null);
    }

    public View getCustomView() {
        return this.customView;
    }

    public int getViewType() {
        return ITEM_VIEW_TYPE;
    }

    public View getView(LayoutInflater layoutInflater, View view, ViewGroup viewGroup) {
        return view == null ? this.customView : view;
    }
}
