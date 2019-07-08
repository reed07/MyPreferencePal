package com.myfitnesspal.feature.settings.ui.dialog;

import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.settings.event.DialogTimezoneEvent;
import com.myfitnesspal.shared.model.v1.Timezone;
import com.myfitnesspal.shared.ui.dialog.SingleChoiceListDialogWithCustomAdapterAndButton;
import com.myfitnesspal.shared.util.TimeZoneHelper;
import java.util.List;

public class TimezoneDialogFragment extends SingleChoiceListDialogWithCustomAdapterAndButton<Timezone> {
    public static TimezoneDialogFragment newInstance() {
        return new TimezoneDialogFragment();
    }

    /* access modifiers changed from: protected */
    public String getTitle() {
        return getString(R.string.timeZoneTxt);
    }

    /* access modifiers changed from: protected */
    public List<Timezone> getItems() {
        return TimeZoneHelper.allTimezones();
    }

    /* access modifiers changed from: protected */
    public int getInitialSelectedItemIndex() {
        return TimeZoneHelper.allTimezones().indexOf(getSession().getUser().getProfile().getCurrentTimezone());
    }

    /* access modifiers changed from: protected */
    public String getTextFromItem(Timezone timezone) {
        return timezone.getTimezone_name();
    }

    /* access modifiers changed from: protected */
    public void onOkClicked(Timezone timezone) {
        this.messageBus.post(new DialogTimezoneEvent(timezone));
    }
}
