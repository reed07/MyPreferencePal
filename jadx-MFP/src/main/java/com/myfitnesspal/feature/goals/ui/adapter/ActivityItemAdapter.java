package com.myfitnesspal.feature.goals.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.extension.ViewExtKt;
import java.util.List;

public class ActivityItemAdapter extends ArrayAdapter<ActivityItem> {
    private int resource;

    public ActivityItemAdapter(Context context, int i, List<ActivityItem> list) {
        super(context, i, list);
        this.resource = i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        LinearLayout linearLayout;
        ActivityItem activityItem = (ActivityItem) getItem(i);
        if (view == null) {
            linearLayout = new LinearLayout(getContext());
            ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(this.resource, linearLayout, true);
        } else {
            linearLayout = (LinearLayout) view;
        }
        CompoundButton compoundButton = (CompoundButton) linearLayout.findViewById(R.id.radio1);
        TextView textView = (TextView) linearLayout.findViewById(R.id.text2);
        ((TextView) linearLayout.findViewById(R.id.text1)).setText(activityItem.getLabel());
        textView.setText(activityItem.getDescription());
        compoundButton.setChecked(activityItem.getState());
        compoundButton.setOnClickListener(ViewExtKt.createAlertDialogRadioButtonClickListener((AdapterView) viewGroup, linearLayout, i, getItemId(i)));
        return linearLayout;
    }
}
