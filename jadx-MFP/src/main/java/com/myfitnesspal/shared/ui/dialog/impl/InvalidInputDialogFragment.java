package com.myfitnesspal.shared.ui.dialog.impl;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.addentry.ui.dialog.EditServingsDialogFragment;
import com.myfitnesspal.feature.addentry.ui.dialog.EditV2SearchServingsDialogFragment;
import com.myfitnesspal.feature.addentry.util.EditableServingV2;
import com.myfitnesspal.shared.constants.Constants.Dialogs.Fragments;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;

public class InvalidInputDialogFragment extends CustomLayoutBaseDialogFragment {
    public static InvalidInputDialogFragment newInstance() {
        return new InvalidInputDialogFragment();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        MfpAlertDialogBuilder mfpAlertDialogBuilder = new MfpAlertDialogBuilder(getDialogContextThemeWrapper());
        mfpAlertDialogBuilder.setMessage((int) R.string.alert_valid_serving).setTitle((int) R.string.invalid_input).setNeutralButton((int) R.string.dismiss, (OnClickListener) new OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                InvalidInputDialogFragment.lambda$onCreateDialog$0(InvalidInputDialogFragment.this, dialogInterface, i);
            }
        });
        return mfpAlertDialogBuilder.create();
    }

    public static /* synthetic */ void lambda$onCreateDialog$0(InvalidInputDialogFragment invalidInputDialogFragment, DialogInterface dialogInterface, int i) {
        DialogFragment dialogFragment;
        FragmentActivity activity = invalidInputDialogFragment.getActivity();
        if (activity != null) {
            if (activity instanceof EditableServingV2) {
                dialogFragment = EditV2SearchServingsDialogFragment.newInstance();
            } else {
                dialogFragment = EditServingsDialogFragment.newInstance();
            }
            dialogFragment.show(activity.getSupportFragmentManager(), Fragments.EDIT_SERVINGS_DIALOG);
        }
    }
}
