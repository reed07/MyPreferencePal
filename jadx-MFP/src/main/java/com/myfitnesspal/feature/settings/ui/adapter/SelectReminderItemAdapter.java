package com.myfitnesspal.feature.settings.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.model.MealNames;
import com.myfitnesspal.shared.model.v15.ReminderObject;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import dagger.Lazy;
import java.util.List;

public class SelectReminderItemAdapter extends ArrayAdapter<ReminderObject> {
    private final LayoutInflater inflater = ((LayoutInflater) getContext().getSystemService("layout_inflater"));
    private final Lazy<LocalizedStringsUtil> localizedStringsUtil;
    private final MealNames mealNames;
    private int resource;
    private int selectedItemIndex;

    public SelectReminderItemAdapter(Context context, int i, List<ReminderObject> list, int i2, Lazy<LocalizedStringsUtil> lazy, MealNames mealNames2) {
        super(context, i, list);
        this.resource = i;
        this.selectedItemIndex = i2;
        this.localizedStringsUtil = lazy;
        this.mealNames = mealNames2;
    }

    @NonNull
    public View getView(int i, View view, ViewGroup viewGroup) {
        ReminderObject reminderObject = (ReminderObject) getItem(i);
        boolean z = false;
        if (view == null) {
            view = this.inflater.inflate(this.resource, viewGroup, false);
        }
        RadioButton radioButton = (RadioButton) view.findViewById(R.id.chkReminderDescription);
        radioButton.setText(reminderObject.description(getContext(), this.localizedStringsUtil, this.mealNames));
        if (i == this.selectedItemIndex) {
            z = true;
        }
        radioButton.setChecked(z);
        return view;
    }
}
