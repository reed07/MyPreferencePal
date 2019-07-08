package com.myfitnesspal.shared.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.extension.ViewExtKt;
import com.uacf.core.util.BundleUtils;
import java.util.List;

public abstract class SingleChoiceListDialogWithCustomAdapterAndButton<T> extends CustomLayoutBaseDialogFragment {
    private static final String SELECTED_INDEX = "selected_index";
    /* access modifiers changed from: private */
    public int selectedIndex;

    /* access modifiers changed from: protected */
    public abstract int getInitialSelectedItemIndex();

    /* access modifiers changed from: protected */
    public abstract List<T> getItems();

    /* access modifiers changed from: protected */
    public abstract String getTextFromItem(T t);

    /* access modifiers changed from: protected */
    public abstract String getTitle();

    /* access modifiers changed from: protected */
    public abstract void onOkClicked(T t);

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        List items = getItems();
        this.selectedIndex = BundleUtils.getInt(bundle, "selected_index", getInitialSelectedItemIndex());
        MfpAlertDialogBuilder dismissOnItemClick = new MfpAlertDialogBuilder(getActivity()).setTitle((CharSequence) getTitle()).setDismissOnItemClick(false);
        AnonymousClass1 r0 = new ArrayAdapter<T>(getActivity(), R.layout.checkable_item, R.id.text, items) {
            public View getView(int i, View view, ViewGroup viewGroup) {
                View view2 = super.getView(i, view, viewGroup);
                CompoundButton compoundButton = (CompoundButton) view2.findViewById(R.id.radio_button);
                ((TextView) view2.findViewById(R.id.text)).setText(SingleChoiceListDialogWithCustomAdapterAndButton.this.getTextFromItem(getItem(i)));
                compoundButton.setChecked(i == SingleChoiceListDialogWithCustomAdapterAndButton.this.selectedIndex);
                compoundButton.setOnClickListener(ViewExtKt.createAlertDialogRadioButtonClickListener((AdapterView) viewGroup, view2, i, getItemId(i)));
                return view2;
            }
        };
        return dismissOnItemClick.setSingleChoiceItems(r0, new OnItemClickListener() {
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                SingleChoiceListDialogWithCustomAdapterAndButton.this.selectedIndex = i;
            }
        }, this.selectedIndex).setPositiveButton((int) R.string.ok, (OnClickListener) new OnClickListener(items) {
            private final /* synthetic */ List f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                SingleChoiceListDialogWithCustomAdapterAndButton.this.onOkClicked(this.f$1.get(SingleChoiceListDialogWithCustomAdapterAndButton.this.selectedIndex));
            }
        }).setNegativeButton((int) R.string.cancel, (OnClickListener) null).create();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("selected_index", this.selectedIndex);
    }

    /* access modifiers changed from: protected */
    public final int getCurrentSelectedItemIndex() {
        return this.selectedIndex;
    }
}
