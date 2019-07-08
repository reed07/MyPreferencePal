package com.myfitnesspal.shared.ui.dialog.impl;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class ProgressDialogFragment extends DialogFragment {
    public static final String MSG_ID = "msgId";
    public static final String TITLE_ID = "titleId";

    public static ProgressDialogFragment newInstance(int i) {
        return newInstance(-1, i);
    }

    public static ProgressDialogFragment newInstance(int i, int i2) {
        ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(MSG_ID, i2);
        bundle.putInt(TITLE_ID, i);
        progressDialogFragment.setArguments(bundle);
        return progressDialogFragment;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        int i = getArguments().getInt(MSG_ID);
        int i2 = getArguments().getInt(TITLE_ID, -1);
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        setCancelable(false);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getActivity().getResources().getString(i));
        if (i2 != -1) {
            progressDialog.setTitle(getActivity().getResources().getString(i2));
        }
        return progressDialog;
    }
}
