package com.myfitnesspal.feature.settings.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Basic;
import com.myfitnesspal.shared.event.AlertEvent;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.v1.Country;
import com.myfitnesspal.shared.model.v1.UserProfile;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.EditTextBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.squareup.otto.Bus;
import dagger.Lazy;
import javax.inject.Inject;

public class PinCodeDialogFragment extends EditTextBaseDialogFragment {
    @Inject
    Lazy<Bus> bus;
    @Inject
    Lazy<CountryService> countryService;

    /* access modifiers changed from: protected */
    public void setBuilderProperties(MfpAlertDialogBuilder mfpAlertDialogBuilder) {
    }

    /* access modifiers changed from: protected */
    public void setEditTextProperties() {
    }

    public static PinCodeDialogFragment newInstance() {
        return new PinCodeDialogFragment();
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        component().inject(this);
        return super.onCreateDialog(bundle);
    }

    /* access modifiers changed from: protected */
    public String getTitle() {
        return getString(R.string.zipCode);
    }

    /* access modifiers changed from: protected */
    public String getInitialText() {
        return getSession().getUser().getProfile().getPostalCode();
    }

    /* access modifiers changed from: protected */
    public void onOkClicked(String str) {
        User user = getSession().getUser();
        UserProfile profile = user.getProfile();
        Country countryFromLongName = ((CountryService) this.countryService.get()).getCountryFromLongName(profile.getCountryName());
        if (countryFromLongName.isUnitedStates() && !countryFromLongName.validateZipCode(str)) {
            ((Bus) this.bus.get()).post(new AlertEvent(getActivity().getString(R.string.enter_valid_zip)));
        }
        profile.setPostalCode(str);
        user.updatePropertyNamed(Basic.POSTAL_CODE);
        ((MfpActivity) getActivity()).scheduleSync();
        hideSoftInput();
    }

    /* access modifiers changed from: protected */
    public void onCancelClicked() {
        super.onCancelClicked();
        hideSoftInput();
    }
}
