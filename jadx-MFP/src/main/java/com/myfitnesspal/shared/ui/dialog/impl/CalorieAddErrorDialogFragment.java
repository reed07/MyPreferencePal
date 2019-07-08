package com.myfitnesspal.shared.ui.dialog.impl;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.diary.event.ShowQuickAddCaloriesDialogEvent;
import com.myfitnesspal.shared.constants.Constants.Dialogs;
import com.myfitnesspal.shared.constants.Constants.LocalizedStrings;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.activity.DialogsFragmentActivity;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.squareup.otto.Bus;
import dagger.Lazy;
import javax.inject.Inject;

public class CalorieAddErrorDialogFragment extends CustomLayoutBaseDialogFragment {
    @Inject
    Lazy<Bus> bus;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    @Inject
    Lazy<UserEnergyService> userEnergyService;

    public static CalorieAddErrorDialogFragment newInstance(int i) {
        CalorieAddErrorDialogFragment calorieAddErrorDialogFragment = new CalorieAddErrorDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", i);
        calorieAddErrorDialogFragment.setArguments(bundle);
        return calorieAddErrorDialogFragment;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        String str;
        String str2;
        component().inject(this);
        switch (getArguments().getInt("id")) {
            case Dialogs.SERVING_ERROR_DIALOG /*7603*/:
                str = getString(R.string.alert_valid_serving);
                str2 = getString(R.string.invalid_input);
                break;
            case Dialogs.OUT_OF_RANGE_ERROR_DIALOG /*7604*/:
                str = ((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.TO_RECORD, this.userEnergyService.get());
                str2 = getString(R.string.alert);
                break;
            default:
                return null;
        }
        return new MfpAlertDialogBuilder(getDialogContextThemeWrapper()).setTitle((CharSequence) str2).setMessage((CharSequence) str).setPositiveButton((int) R.string.dismiss, (OnClickListener) new OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                CalorieAddErrorDialogFragment.lambda$onCreateDialog$0(CalorieAddErrorDialogFragment.this, dialogInterface, i);
            }
        }).create();
    }

    public static /* synthetic */ void lambda$onCreateDialog$0(CalorieAddErrorDialogFragment calorieAddErrorDialogFragment, DialogInterface dialogInterface, int i) {
        FragmentActivity activity = calorieAddErrorDialogFragment.getActivity();
        dialogInterface.dismiss();
        if (activity instanceof DialogsFragmentActivity) {
            ((DialogsFragmentActivity) activity).showDialogWithId(Dialogs.QUICK_ADD_CALORIES_DIALOG);
        }
        ((Bus) calorieAddErrorDialogFragment.bus.get()).post(new ShowQuickAddCaloriesDialogEvent());
    }
}
