package com.myfitnesspal.shared.ui.dialog.impl;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import dagger.Lazy;
import javax.inject.Inject;

public class AccountRestrictedDialogFragment extends CustomLayoutBaseDialogFragment {
    @Inject
    NavigationHelper navigationHelper;
    @Inject
    Lazy<Session> session;

    public static AccountRestrictedDialogFragment newInstance() {
        return new AccountRestrictedDialogFragment();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        component().inject(this);
        return new MfpAlertDialogBuilder(getDialogContextThemeWrapper()).setTitle((int) R.string.account_restricted).setMessage((int) R.string.account_has_been_blocked).setPositiveButton((int) R.string.dismiss, (OnClickListener) new OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                ((Session) AccountRestrictedDialogFragment.this.session.get()).logoutAndNavigateToLoginActivity();
            }
        }).setCancelable(false).setCanceledOnTouchOutside(false).create();
    }
}
