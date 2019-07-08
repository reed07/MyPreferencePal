package com.myfitnesspal.feature.consents.ui.activity;

import android.view.View;
import android.widget.CheckBox;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogNegativeListener;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "onClick"}, k = 3, mv = {1, 1, 13})
/* compiled from: AdConsentsActivity.kt */
final class AdConsentsActivity$showAlertDialog$1 implements DialogNegativeListener {
    final /* synthetic */ AlertDialogFragment $alertDialog;
    final /* synthetic */ String $consentId;
    final /* synthetic */ View $itemView;
    final /* synthetic */ AdConsentsActivity this$0;

    AdConsentsActivity$showAlertDialog$1(AdConsentsActivity adConsentsActivity, AlertDialogFragment alertDialogFragment, View view, String str) {
        this.this$0 = adConsentsActivity;
        this.$alertDialog = alertDialogFragment;
        this.$itemView = view;
        this.$consentId = str;
    }

    public final void onClick() {
        this.$alertDialog.dismiss();
        View view = this.$itemView;
        if (view != null) {
            CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);
            checkBox.setOnCheckedChangeListener(null);
            checkBox.setChecked(true);
            checkBox.setOnCheckedChangeListener(new AdConsentsActivity$showAlertDialog$1$$special$$inlined$let$lambda$1(this));
        }
    }
}
