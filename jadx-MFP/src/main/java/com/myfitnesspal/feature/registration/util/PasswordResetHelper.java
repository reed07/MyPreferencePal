package com.myfitnesspal.feature.registration.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.google.gson.FieldNamingPolicy;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.registration.model.PasswordResetData;
import com.myfitnesspal.feature.registration.model.PasswordResetDataButton;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.ui.navigation.SharedIntents;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Inject;

public class PasswordResetHelper {
    private static final ApiJsonMapper MAPPER = new ApiJsonMapper(FieldNamingPolicy.IDENTITY);
    private final Context context;
    /* access modifiers changed from: private */
    public boolean isDialogShowing;
    /* access modifiers changed from: private */
    public final NavigationHelper navigationHelper;

    @Inject
    public PasswordResetHelper(Context context2, NavigationHelper navigationHelper2) {
        this.context = context2;
        this.navigationHelper = navigationHelper2;
    }

    public void showDialog(final Activity activity, final PasswordResetData passwordResetData) {
        if (!this.isDialogShowing) {
            AnonymousClass1 r0 = new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    String str;
                    switch (i) {
                        case -3:
                            str = ((PasswordResetDataButton) passwordResetData.getButtons().get(1)).getAction();
                            break;
                        case -2:
                            str = ((PasswordResetDataButton) passwordResetData.getButtons().get(2)).getAction();
                            break;
                        case -1:
                            str = ((PasswordResetDataButton) passwordResetData.getButtons().get(0)).getAction();
                            break;
                        default:
                            str = null;
                            break;
                    }
                    if (Strings.notEmpty(str)) {
                        PasswordResetHelper.this.navigationHelper.withContext(activity).withIntent(SharedIntents.newUriIntent(str)).startActivity();
                    }
                    dialogInterface.dismiss();
                }
            };
            View inflate = LayoutInflater.from(this.context).inflate(R.layout.password_reset_required, null);
            ((TextView) ViewUtils.findById(inflate, R.id.message)).setText(passwordResetData.getMsgBody());
            MfpAlertDialogBuilder mfpAlertDialogBuilder = new MfpAlertDialogBuilder(activity);
            mfpAlertDialogBuilder.setTitle((CharSequence) passwordResetData.getMsgSubject()).setCancelable(false).setView(inflate);
            int size = CollectionUtils.size((Collection<?>) passwordResetData.getButtons());
            if (size >= 1) {
                mfpAlertDialogBuilder.setPositiveButton((CharSequence) Strings.toString(((PasswordResetDataButton) passwordResetData.getButtons().get(0)).getText()), (OnClickListener) r0);
            }
            if (size >= 2) {
                mfpAlertDialogBuilder.setNeutralButton((CharSequence) Strings.toString(((PasswordResetDataButton) passwordResetData.getButtons().get(1)).getText()), (OnClickListener) r0);
            }
            if (size >= 3) {
                mfpAlertDialogBuilder.setNegativeButton((CharSequence) Strings.toString(((PasswordResetDataButton) passwordResetData.getButtons().get(2)).getText()), (OnClickListener) r0);
            }
            AlertDialog create = mfpAlertDialogBuilder.create();
            create.setOnDismissListener(new OnDismissListener() {
                public void onDismiss(DialogInterface dialogInterface) {
                    PasswordResetHelper.this.isDialogShowing = false;
                }
            });
            this.isDialogShowing = true;
            create.show();
        }
    }

    public PasswordResetData createDataFromJson(String str) {
        PasswordResetData passwordResetData = Strings.notEmpty(str) ? (PasswordResetData) MAPPER.tryMapFrom(str, PasswordResetData.class) : null;
        if (passwordResetData != null) {
            return passwordResetData;
        }
        PasswordResetData passwordResetData2 = new PasswordResetData();
        passwordResetData2.setMsgSubject(this.context.getString(R.string.default_password_reset_required_title));
        passwordResetData2.setMsgBody(this.context.getString(R.string.default_password_reset_required_message));
        ArrayList arrayList = new ArrayList();
        passwordResetData2.setButtons(arrayList);
        PasswordResetDataButton passwordResetDataButton = new PasswordResetDataButton();
        passwordResetDataButton.setText(this.context.getString(R.string.more_info));
        passwordResetDataButton.setAction(this.context.getString(R.string.default_password_reset_more_info_url));
        arrayList.add(passwordResetDataButton);
        PasswordResetDataButton passwordResetDataButton2 = new PasswordResetDataButton();
        passwordResetDataButton2.setText(this.context.getString(R.string.dismiss));
        arrayList.add(passwordResetDataButton2);
        return passwordResetData2;
    }
}
