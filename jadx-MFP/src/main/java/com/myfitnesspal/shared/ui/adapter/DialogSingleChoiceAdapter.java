package com.myfitnesspal.shared.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.extension.ViewExtKt;
import com.myfitnesspal.shared.model.CheckableListItem;
import java.util.List;

public class DialogSingleChoiceAdapter extends ArrayAdapter<CheckableListItem> {
    public DialogSingleChoiceAdapter(Context context, List list) {
        super(context, R.layout.checkable_item, R.id.text, list);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2 = super.getView(i, view, viewGroup);
        view2.setTag(getItem(i));
        CompoundButton compoundButton = (CompoundButton) view2.findViewById(R.id.radio_button);
        CheckableListItem checkableListItem = (CheckableListItem) getItem(i);
        ((TextView) view2.findViewById(R.id.text)).setText(checkableListItem.getDescription());
        compoundButton.setChecked(checkableListItem.getState());
        compoundButton.setOnClickListener(ViewExtKt.createAlertDialogRadioButtonClickListener((AdapterView) viewGroup, view2, i, getItemId(i)));
        return view2;
    }
}
