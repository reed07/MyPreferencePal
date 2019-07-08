package com.myfitnesspal.shared.ui.dialog.impl;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.diary.event.ShowServingErrorDialogEvent;
import com.myfitnesspal.shared.constants.Constants.Dialogs;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.LocalizedStrings;
import com.myfitnesspal.shared.event.QuickAddCalorieAddedEvent;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.activity.DialogsFragmentActivity;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.squareup.otto.Bus;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import javax.inject.Inject;

public class QuickAddCaloriesDialogFragment extends CustomLayoutBaseDialogFragment {
    @Inject
    Lazy<Bus> bus;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    @Inject
    Lazy<UserEnergyService> userEnergyService;

    public static QuickAddCaloriesDialogFragment newInstance(String str, String str2) {
        QuickAddCaloriesDialogFragment quickAddCaloriesDialogFragment = new QuickAddCaloriesDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Extras.INITIAL_VALUE_TO_EDIT, str);
        bundle.putString(Extras.MEAL_NAME, Strings.toString(str2));
        quickAddCaloriesDialogFragment.setArguments(bundle);
        return quickAddCaloriesDialogFragment;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        LayoutInflater from = LayoutInflater.from(getDialogContextThemeWrapper());
        component().inject(this);
        View inflate = from.inflate(R.layout.quick_add_calories, null);
        final Bundle arguments = getArguments();
        final EditText editText = (EditText) inflate.findViewById(R.id.numOfCalories);
        ((TextView) inflate.findViewById(R.id.calories)).setText(((UserEnergyService) this.userEnergyService.get()).getCurrentEnergyUnitString());
        String displayableEnergy = ((UserEnergyService) this.userEnergyService.get()).getDisplayableEnergy(BundleUtils.getString(arguments, Extras.INITIAL_VALUE_TO_EDIT, ""));
        editText.setText(displayableEnergy);
        editText.setSelection(0, Strings.length(displayableEnergy));
        editText.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    QuickAddCaloriesDialogFragment.this.showSoftInput();
                }
            }
        });
        return new MfpAlertDialogBuilder(getDialogContextThemeWrapper()).setTitle((CharSequence) ((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.TO_ADD, this.userEnergyService.get())).setView(inflate).setPositiveButton((int) R.string.add, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                float f;
                FragmentActivity activity = QuickAddCaloriesDialogFragment.this.getActivity();
                try {
                    f = ((UserEnergyService) QuickAddCaloriesDialogFragment.this.userEnergyService.get()).getCalories(Strings.toString(editText.getText()));
                } catch (NumberFormatException e) {
                    dialogInterface.cancel();
                    if (activity instanceof DialogsFragmentActivity) {
                        ((DialogsFragmentActivity) activity).showDialogWithId(Dialogs.SERVING_ERROR_DIALOG);
                    }
                    ((Bus) QuickAddCaloriesDialogFragment.this.bus.get()).post(new ShowServingErrorDialogEvent());
                    Ln.e(e);
                    f = BitmapDescriptorFactory.HUE_RED;
                }
                ((Bus) QuickAddCaloriesDialogFragment.this.bus.get()).post(new QuickAddCalorieAddedEvent(f, BundleUtils.getString(arguments, Extras.MEAL_NAME, "")));
                QuickAddCaloriesDialogFragment.this.hideSoftInputFor(editText);
                dialogInterface.cancel();
            }
        }).setNegativeButton((int) R.string.cancel, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    FragmentActivity activity = QuickAddCaloriesDialogFragment.this.getActivity();
                    if (activity instanceof MfpActivity) {
                        ((MfpActivity) activity).getImmHelper().hideSoftInput();
                    }
                    QuickAddCaloriesDialogFragment.this.hideSoftInputFor(editText);
                    dialogInterface.cancel();
                } catch (Exception e) {
                    Ln.e(e);
                }
            }
        }).create();
    }
}
