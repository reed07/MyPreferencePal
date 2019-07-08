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
import java.util.List;

public abstract class SingleChoiceListDialogWithCustomAdapterAndNeutralButton<T> extends CustomLayoutBaseDialogFragment {
    /* access modifiers changed from: private */
    public int selectedIndex;

    /* access modifiers changed from: protected */
    public abstract int getInitialSelectedItemIndex();

    /* access modifiers changed from: protected */
    public abstract List<T> getItems();

    /* access modifiers changed from: protected */
    public abstract int getNegativeButtonString();

    /* access modifiers changed from: protected */
    public abstract OnClickListener getNeutralButtonOnClickListener();

    /* access modifiers changed from: protected */
    public abstract int getNeutralButtonString();

    /* access modifiers changed from: protected */
    public abstract int getPositiveButtonString();

    /* access modifiers changed from: protected */
    public abstract String getTextFromItem(T t);

    /* access modifiers changed from: protected */
    public abstract String getTitle();

    /* access modifiers changed from: protected */
    public abstract void onOkClicked(T t);

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        List items = getItems();
        this.selectedIndex = getInitialSelectedItemIndex();
        MfpAlertDialogBuilder dismissOnItemClick = new MfpAlertDialogBuilder(getActivity()).setTitle((CharSequence) getTitle()).setDismissOnItemClick(false);
        AnonymousClass1 r0 = new ArrayAdapter<T>(getActivity(), R.layout.checkable_item, R.id.text, items) {
            public View getView(int i, View view, ViewGroup viewGroup) {
                View view2 = super.getView(i, view, viewGroup);
                CompoundButton compoundButton = (CompoundButton) view2.findViewById(R.id.radio_button);
                ((TextView) view2.findViewById(R.id.text)).setText(SingleChoiceListDialogWithCustomAdapterAndNeutralButton.this.getTextFromItem(getItem(i)));
                compoundButton.setChecked(i == SingleChoiceListDialogWithCustomAdapterAndNeutralButton.this.selectedIndex);
                compoundButton.setOnClickListener(ViewExtKt.createAlertDialogRadioButtonClickListener((AdapterView) viewGroup, view2, i, getItemId(i)));
                return view2;
            }
        };
        return dismissOnItemClick.setSingleChoiceItems(r0, new OnItemClickListener() {
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                SingleChoiceListDialogWithCustomAdapterAndNeutralButton.this.selectedIndex = i;
            }
        }, this.selectedIndex).setPositiveButton(getPositiveButtonString(), (OnClickListener) new OnClickListener(items) {
            private final /* synthetic */ List f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                SingleChoiceListDialogWithCustomAdapterAndNeutralButton.this.onOkClicked(this.f$1.get(SingleChoiceListDialogWithCustomAdapterAndNeutralButton.this.selectedIndex));
            }
        }).setNegativeButton(getNegativeButtonString(), (OnClickListener) null).setNeutralButton(getNeutralButtonString(), getNeutralButtonOnClickListener()).create();
    }

    /* access modifiers changed from: protected */
    public final int getCurrentSelectedItemIndex() {
        return this.selectedIndex;
    }
}
