package com.myfitnesspal.feature.meals.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;

public class PermissionRestrictionDialogFragment extends CustomLayoutBaseDialogFragment {
    /* access modifiers changed from: private */
    public OnChangePermissionClickListener onChangePermissionClickListener;

    public interface OnChangePermissionClickListener {
        void onCancelled();

        void onPermissionChangeClick();
    }

    public static PermissionRestrictionDialogFragment newInstance() {
        return new PermissionRestrictionDialogFragment();
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        return new MfpAlertDialogBuilder(getActivity()).setMessage((int) R.string.change_permission_private_public).setPositiveButton((int) R.string.share_update, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                PermissionRestrictionDialogFragment.this.onChangePermissionClickListener.onPermissionChangeClick();
            }
        }).setNegativeButton((int) R.string.cancel, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                PermissionRestrictionDialogFragment.this.onChangePermissionClickListener.onCancelled();
            }
        }).create();
    }

    public void setOnChangePermissionClickListener(OnChangePermissionClickListener onChangePermissionClickListener2) {
        this.onChangePermissionClickListener = onChangePermissionClickListener2;
    }
}
