package com.myfitnesspal.feature.addentry.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.addentry.event.AddCustomFoodNextStepEvent;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import dagger.Lazy;
import javax.inject.Inject;

public class AddCustomFoodImprovementDialogFragment extends CustomLayoutBaseDialogFragment {
    @Inject
    protected ActionTrackingService actionTrackingService;
    @Inject
    protected Lazy<AnalyticsService> analyticsService;
    @Inject
    protected Lazy<ConfigService> configService;
    private boolean isScan;

    public static AddCustomFoodImprovementDialogFragment newInstance(boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(Extras.SCANNED_ENRTY, z);
        AddCustomFoodImprovementDialogFragment addCustomFoodImprovementDialogFragment = new AddCustomFoodImprovementDialogFragment();
        addCustomFoodImprovementDialogFragment.setArguments(bundle);
        return addCustomFoodImprovementDialogFragment;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        component().inject(this);
        this.isScan = getArguments().getBoolean(Extras.SCANNED_ENRTY);
        return new MfpAlertDialogBuilder(getDialogContextThemeWrapper()).setTitle((CharSequence) getString(this.isScan ? R.string.add_additional_nutrients : R.string.add_nutrient_information)).setMessage((CharSequence) getString(this.isScan ? R.string.your_diary_is_more_accurate_micro : R.string.you_diary_is_more_accurate_nutrients)).setPositiveButton((int) R.string.add_details, (OnClickListener) new OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                AddCustomFoodImprovementDialogFragment.lambda$onCreateDialog$0(AddCustomFoodImprovementDialogFragment.this, dialogInterface, i);
            }
        }).setNegativeButton((int) R.string.no_thanks, (OnClickListener) new OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                AddCustomFoodImprovementDialogFragment.lambda$onCreateDialog$1(AddCustomFoodImprovementDialogFragment.this, dialogInterface, i);
            }
        }).create();
    }

    public static /* synthetic */ void lambda$onCreateDialog$0(AddCustomFoodImprovementDialogFragment addCustomFoodImprovementDialogFragment, DialogInterface dialogInterface, int i) {
        addCustomFoodImprovementDialogFragment.actionTrackingService.appendToEvent(Events.CREATE_FOOD_SUMMARY, Attributes.MORE_HELP, "true");
        addCustomFoodImprovementDialogFragment.sendButtonClickEvent();
        dialogInterface.cancel();
    }

    public static /* synthetic */ void lambda$onCreateDialog$1(AddCustomFoodImprovementDialogFragment addCustomFoodImprovementDialogFragment, DialogInterface dialogInterface, int i) {
        addCustomFoodImprovementDialogFragment.actionTrackingService.appendToEvent(Events.CREATE_FOOD_SUMMARY, Attributes.MORE_HELP, "false");
        addCustomFoodImprovementDialogFragment.sendButtonClickEvent();
        addCustomFoodImprovementDialogFragment.messageBus.post(new AddCustomFoodNextStepEvent());
        dialogInterface.cancel();
    }

    private void sendButtonClickEvent() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(this.isScan ? Events.FOOD_CREATION_BARCODE_FLOW_ADD_NUTRIENTS_ALERT_CLICK : Events.FOOD_CREATION_MANUAL_FLOW_ADD_NUTRIENTS_ALERT_CLICK);
    }
}
