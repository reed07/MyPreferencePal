package com.myfitnesspal.feature.search.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.myfitnesspal.shared.model.CheckableListItem;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.uacf.core.util.BundleUtils;
import java.util.List;

public abstract class SortFilterDialogBase<T extends CheckableListItem> extends CustomLayoutBaseDialogFragment {
    private static final String ACTIVE_TAB_ID = "active_tab_id";
    private static final String MEAL_NAME = "meal_name";
    protected int activeTabId;
    protected List<T> checkableListItems;
    protected String mealName;

    /* access modifiers changed from: protected */
    public abstract List<T> getItemsList(int i);

    /* access modifiers changed from: protected */
    public abstract int getTitleResId();

    /* access modifiers changed from: protected */
    public abstract void onItemClick(AdapterView<?> adapterView, View view, int i, long j);

    protected static void setArgumentsToFragment(SortFilterDialogBase sortFilterDialogBase, int i, String str) {
        Bundle bundle = new Bundle();
        bundle.putInt(ACTIVE_TAB_ID, i);
        bundle.putString("meal_name", str);
        sortFilterDialogBase.setArguments(bundle);
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        Bundle arguments = getArguments();
        this.activeTabId = BundleUtils.getInt(arguments, ACTIVE_TAB_ID);
        this.mealName = BundleUtils.getString(arguments, "meal_name");
        this.checkableListItems = getItemsList(this.activeTabId);
        return new MfpAlertDialogBuilder(getActivity()).setTitle(getTitleResId()).setSingleChoiceItems(this.checkableListItems, (OnItemClickListener) new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                SortFilterDialogBase.this.onItemClick(adapterView, view, i, j);
            }
        }).create();
    }
}
