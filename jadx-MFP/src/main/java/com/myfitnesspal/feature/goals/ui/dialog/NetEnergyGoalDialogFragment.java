package com.myfitnesspal.feature.goals.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.LocalizedStrings;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.sync.engine.UacfScheduler;
import dagger.Lazy;
import javax.inject.Inject;

public class NetEnergyGoalDialogFragment extends CustomLayoutBaseDialogFragment {
    @Inject
    Lazy<DbConnectionManager> dbConnectionManager;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    @Inject
    Lazy<UacfScheduler<SyncType>> syncScheduler;
    @Inject
    Lazy<UserEnergyService> userEnergyService;

    public interface NetCalorieGoalDialogFragmentListener {
        void onCalorieGoalUpdatedDialog(float f);
    }

    public static NetEnergyGoalDialogFragment newInstance(float f) {
        NetEnergyGoalDialogFragment netEnergyGoalDialogFragment = new NetEnergyGoalDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putFloat(Extras.LOCALIZED_ENERGY, f);
        netEnergyGoalDialogFragment.setArguments(bundle);
        return netEnergyGoalDialogFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        ContextThemeWrapper dialogContextThemeWrapper = getDialogContextThemeWrapper();
        View inflate = LayoutInflater.from(getDialogContextThemeWrapper()).inflate(R.layout.edit_text_dialog, null);
        final EditText editText = (EditText) inflate.findViewById(R.id.txtValue);
        TextView textView = (TextView) inflate.findViewById(R.id.txtUnit);
        editText.setText(Strings.toString(Integer.valueOf(Math.round(BundleUtils.getFloat(getArguments(), Extras.LOCALIZED_ENERGY)))));
        editText.setSelection(editText.getText().length());
        textView.setText(dialogContextThemeWrapper.getResources().getString(((UserEnergyService) this.userEnergyService.get()).isCalories() ? R.string.net_calories_per_day : R.string.net_kilojoules_per_day));
        final AnonymousClass1 r2 = new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    float tryParseFloat = NumberUtils.tryParseFloat(Strings.toString(editText.getText(), "0"));
                    if (((UserEnergyService) NetEnergyGoalDialogFragment.this.userEnergyService.get()).isGoalEnergyValid(tryParseFloat)) {
                        ((DbConnectionManager) NetEnergyGoalDialogFragment.this.dbConnectionManager.get()).usersDbAdapter().saveUser(NetEnergyGoalDialogFragment.this.getSession().getUser().getUserV1());
                        ((UacfScheduler) NetEnergyGoalDialogFragment.this.syncScheduler.get()).schedulePeriodicSyncIfNecessary();
                        NetCalorieGoalDialogFragmentListener netCalorieGoalDialogFragmentListener = (NetCalorieGoalDialogFragmentListener) NetEnergyGoalDialogFragment.this.getActivity();
                        if (netCalorieGoalDialogFragmentListener != null) {
                            netCalorieGoalDialogFragmentListener.onCalorieGoalUpdatedDialog(tryParseFloat);
                        }
                        dialogInterface.cancel();
                        return;
                    }
                    AlertDialog access$100 = NetEnergyGoalDialogFragment.this.getDismissDialog();
                    access$100.setCancelable(true);
                    access$100.show();
                } catch (NumberFormatException unused) {
                    AlertDialog access$1002 = NetEnergyGoalDialogFragment.this.getDismissDialog();
                    access$1002.setCancelable(true);
                    access$1002.show();
                }
            }
        };
        AlertDialog create = new MfpAlertDialogBuilder(dialogContextThemeWrapper).setTitle((CharSequence) ((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.NET_GOAL, this.userEnergyService.get())).setView(inflate).setPositiveButton((int) R.string.save, (OnClickListener) r2).setNegativeButton((int) R.string.cancel, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        }).setOnKeyListener(new OnKeyListener() {
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i == 4) {
                    dialogInterface.cancel();
                } else if (i == 66 && keyEvent.getRepeatCount() == 0) {
                    r2.onClick(dialogInterface, 0);
                    dialogInterface.cancel();
                    return true;
                }
                return false;
            }
        }).create();
        create.getWindow().setSoftInputMode(4);
        return create;
    }

    /* access modifiers changed from: private */
    public AlertDialog getDismissDialog() {
        return new MfpAlertDialogBuilder(getDialogContextThemeWrapper()).setTitle((int) R.string.alert).setMessage((int) R.string.enter_valid_goal).setPositiveButton((int) R.string.dismiss, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        }).create();
    }
}
