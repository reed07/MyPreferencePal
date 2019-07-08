package com.myfitnesspal.shared.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public interface ListItem {
    View getView(LayoutInflater layoutInflater, View view, ViewGroup viewGroup);

    int getViewType();
}
