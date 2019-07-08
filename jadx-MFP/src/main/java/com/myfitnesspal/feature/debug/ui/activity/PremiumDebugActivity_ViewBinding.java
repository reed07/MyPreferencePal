package com.myfitnesspal.feature.debug.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class PremiumDebugActivity_ViewBinding implements Unbinder {
    private PremiumDebugActivity target;

    @UiThread
    public PremiumDebugActivity_ViewBinding(PremiumDebugActivity premiumDebugActivity) {
        this(premiumDebugActivity, premiumDebugActivity.getWindow().getDecorView());
    }

    @UiThread
    public PremiumDebugActivity_ViewBinding(PremiumDebugActivity premiumDebugActivity, View view) {
        this.target = premiumDebugActivity;
        premiumDebugActivity.featureOverrides = (Switch) Utils.findRequiredViewAsType(view, R.id.devMenu, "field 'featureOverrides'", Switch.class);
        premiumDebugActivity.disableReceiptRetrieval = (Switch) Utils.findRequiredViewAsType(view, R.id.disableReceiptRetrieval, "field 'disableReceiptRetrieval'", Switch.class);
        premiumDebugActivity.disableReceiptPostToServer = (Switch) Utils.findRequiredViewAsType(view, R.id.disableReceiptPostToServer, "field 'disableReceiptPostToServer'", Switch.class);
        premiumDebugActivity.qaAvailable = (Switch) Utils.findRequiredViewAsType(view, R.id.qaAvailable, "field 'qaAvailable'", Switch.class);
        premiumDebugActivity.qaEnabled = (Switch) Utils.findRequiredViewAsType(view, R.id.qaEnabled, "field 'qaEnabled'", Switch.class);
        premiumDebugActivity.qaSubscribed = (Switch) Utils.findRequiredViewAsType(view, R.id.qaSubscribed, "field 'qaSubscribed'", Switch.class);
        premiumDebugActivity.tmAvailable = (Switch) Utils.findRequiredViewAsType(view, R.id.tmAvailable, "field 'tmAvailable'", Switch.class);
        premiumDebugActivity.tmEnabled = (Switch) Utils.findRequiredViewAsType(view, R.id.tmEnabled, "field 'tmEnabled'", Switch.class);
        premiumDebugActivity.tmSubscribed = (Switch) Utils.findRequiredViewAsType(view, R.id.tmSubscribed, "field 'tmSubscribed'", Switch.class);
        premiumDebugActivity.ecAvailable = (Switch) Utils.findRequiredViewAsType(view, R.id.ecAvailable, "field 'ecAvailable'", Switch.class);
        premiumDebugActivity.ecEnabled = (Switch) Utils.findRequiredViewAsType(view, R.id.ecEnabled, "field 'ecEnabled'", Switch.class);
        premiumDebugActivity.ecSubscribed = (Switch) Utils.findRequiredViewAsType(view, R.id.ecSubscribed, "field 'ecSubscribed'", Switch.class);
        premiumDebugActivity.dmAvailable = (Switch) Utils.findRequiredViewAsType(view, R.id.dmAvailable, "field 'dmAvailable'", Switch.class);
        premiumDebugActivity.dmEnabled = (Switch) Utils.findRequiredViewAsType(view, R.id.dmEnabled, "field 'dmEnabled'", Switch.class);
        premiumDebugActivity.dmSubscribed = (Switch) Utils.findRequiredViewAsType(view, R.id.dmSubscribed, "field 'dmSubscribed'", Switch.class);
        premiumDebugActivity.mbdAvailable = (Switch) Utils.findRequiredViewAsType(view, R.id.mbdAvailable, "field 'mbdAvailable'", Switch.class);
        premiumDebugActivity.mbdEnabled = (Switch) Utils.findRequiredViewAsType(view, R.id.mbdEnabled, "field 'mbdEnabled'", Switch.class);
        premiumDebugActivity.mbdSubscribed = (Switch) Utils.findRequiredViewAsType(view, R.id.mbdSubscribed, "field 'mbdSubscribed'", Switch.class);
        premiumDebugActivity.afAvailable = (Switch) Utils.findRequiredViewAsType(view, R.id.afAvailable, "field 'afAvailable'", Switch.class);
        premiumDebugActivity.afEnabled = (Switch) Utils.findRequiredViewAsType(view, R.id.afEnabled, "field 'afEnabled'", Switch.class);
        premiumDebugActivity.afSubscribed = (Switch) Utils.findRequiredViewAsType(view, R.id.afSubscribed, "field 'afSubscribed'", Switch.class);
        premiumDebugActivity.grAvailable = (Switch) Utils.findRequiredViewAsType(view, R.id.grAvailable, "field 'grAvailable'", Switch.class);
        premiumDebugActivity.grEnabled = (Switch) Utils.findRequiredViewAsType(view, R.id.grEnabled, "field 'grEnabled'", Switch.class);
        premiumDebugActivity.grSubscribed = (Switch) Utils.findRequiredViewAsType(view, R.id.grSubscribed, "field 'grSubscribed'", Switch.class);
        premiumDebugActivity.flAvailable = (Switch) Utils.findRequiredViewAsType(view, R.id.flAvailable, "field 'flAvailable'", Switch.class);
        premiumDebugActivity.flEnabled = (Switch) Utils.findRequiredViewAsType(view, R.id.flEnabled, "field 'flEnabled'", Switch.class);
        premiumDebugActivity.flSubscribed = (Switch) Utils.findRequiredViewAsType(view, R.id.flSubscribed, "field 'flSubscribed'", Switch.class);
        premiumDebugActivity.coAvailable = (Switch) Utils.findRequiredViewAsType(view, R.id.coAvailable, "field 'coAvailable'", Switch.class);
        premiumDebugActivity.coEnabled = (Switch) Utils.findRequiredViewAsType(view, R.id.coEnabled, "field 'coEnabled'", Switch.class);
        premiumDebugActivity.coSubscribed = (Switch) Utils.findRequiredViewAsType(view, R.id.coSubscribed, "field 'coSubscribed'", Switch.class);
        premiumDebugActivity.vfAvailable = (Switch) Utils.findRequiredViewAsType(view, R.id.vfAvailable, "field 'vfAvailable'", Switch.class);
        premiumDebugActivity.vfEnabled = (Switch) Utils.findRequiredViewAsType(view, R.id.vfEnabled, "field 'vfEnabled'", Switch.class);
        premiumDebugActivity.vfSubscribed = (Switch) Utils.findRequiredViewAsType(view, R.id.vfSubscribed, "field 'vfSubscribed'", Switch.class);
        premiumDebugActivity.upAvailable = (Switch) Utils.findRequiredViewAsType(view, R.id.upAvailable, "field 'upAvailable'", Switch.class);
        premiumDebugActivity.upEnabled = (Switch) Utils.findRequiredViewAsType(view, R.id.upEnabled, "field 'upEnabled'", Switch.class);
        premiumDebugActivity.upSubscribed = (Switch) Utils.findRequiredViewAsType(view, R.id.upSubscribed, "field 'upSubscribed'", Switch.class);
        premiumDebugActivity.mealMacroAvailable = (Switch) Utils.findRequiredViewAsType(view, R.id.mealMacroAvailable, "field 'mealMacroAvailable'", Switch.class);
        premiumDebugActivity.mealMacroEnabled = (Switch) Utils.findRequiredViewAsType(view, R.id.mealMacroEnabled, "field 'mealMacroEnabled'", Switch.class);
        premiumDebugActivity.mealMacroSubscribed = (Switch) Utils.findRequiredViewAsType(view, R.id.mealMacroSubscribed, "field 'mealMacroSubscribed'", Switch.class);
        premiumDebugActivity.fileExportAvailable = (Switch) Utils.findRequiredViewAsType(view, R.id.fileExportAvailable, "field 'fileExportAvailable'", Switch.class);
        premiumDebugActivity.fileExportEnabled = (Switch) Utils.findRequiredViewAsType(view, R.id.fileExportEnabled, "field 'fileExportEnabled'", Switch.class);
        premiumDebugActivity.fileExportSubscribed = (Switch) Utils.findRequiredViewAsType(view, R.id.fileExportSubscribed, "field 'fileExportSubscribed'", Switch.class);
        premiumDebugActivity.mealGoalsAvailable = (Switch) Utils.findRequiredViewAsType(view, R.id.mealGoalsAvailable, "field 'mealGoalsAvailable'", Switch.class);
        premiumDebugActivity.mealGoalsEnabled = (Switch) Utils.findRequiredViewAsType(view, R.id.mealGoalsEnabled, "field 'mealGoalsEnabled'", Switch.class);
        premiumDebugActivity.mealGoalsSubscribed = (Switch) Utils.findRequiredViewAsType(view, R.id.mealGoalsSubscribed, "field 'mealGoalsSubscribed'", Switch.class);
        premiumDebugActivity.foodTimestampsAvailable = (Switch) Utils.findRequiredViewAsType(view, R.id.foodTimestampsAvailable, "field 'foodTimestampsAvailable'", Switch.class);
        premiumDebugActivity.foodTimestampsEnabled = (Switch) Utils.findRequiredViewAsType(view, R.id.foodTimestampsEnabled, "field 'foodTimestampsEnabled'", Switch.class);
        premiumDebugActivity.foodTimestampsSubscribed = (Switch) Utils.findRequiredViewAsType(view, R.id.foodTimestampsSubscribed, "field 'foodTimestampsSubscribed'", Switch.class);
        premiumDebugActivity.available = (TextView) Utils.findRequiredViewAsType(view, R.id.available, "field 'available'", TextView.class);
        premiumDebugActivity.localeInfo = (TextView) Utils.findRequiredViewAsType(view, R.id.localeInfo, "field 'localeInfo'", TextView.class);
        premiumDebugActivity.hasSubscription = (TextView) Utils.findRequiredViewAsType(view, R.id.hasSubscription, "field 'hasSubscription'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        PremiumDebugActivity premiumDebugActivity = this.target;
        if (premiumDebugActivity != null) {
            this.target = null;
            premiumDebugActivity.featureOverrides = null;
            premiumDebugActivity.disableReceiptRetrieval = null;
            premiumDebugActivity.disableReceiptPostToServer = null;
            premiumDebugActivity.qaAvailable = null;
            premiumDebugActivity.qaEnabled = null;
            premiumDebugActivity.qaSubscribed = null;
            premiumDebugActivity.tmAvailable = null;
            premiumDebugActivity.tmEnabled = null;
            premiumDebugActivity.tmSubscribed = null;
            premiumDebugActivity.ecAvailable = null;
            premiumDebugActivity.ecEnabled = null;
            premiumDebugActivity.ecSubscribed = null;
            premiumDebugActivity.dmAvailable = null;
            premiumDebugActivity.dmEnabled = null;
            premiumDebugActivity.dmSubscribed = null;
            premiumDebugActivity.mbdAvailable = null;
            premiumDebugActivity.mbdEnabled = null;
            premiumDebugActivity.mbdSubscribed = null;
            premiumDebugActivity.afAvailable = null;
            premiumDebugActivity.afEnabled = null;
            premiumDebugActivity.afSubscribed = null;
            premiumDebugActivity.grAvailable = null;
            premiumDebugActivity.grEnabled = null;
            premiumDebugActivity.grSubscribed = null;
            premiumDebugActivity.flAvailable = null;
            premiumDebugActivity.flEnabled = null;
            premiumDebugActivity.flSubscribed = null;
            premiumDebugActivity.coAvailable = null;
            premiumDebugActivity.coEnabled = null;
            premiumDebugActivity.coSubscribed = null;
            premiumDebugActivity.vfAvailable = null;
            premiumDebugActivity.vfEnabled = null;
            premiumDebugActivity.vfSubscribed = null;
            premiumDebugActivity.upAvailable = null;
            premiumDebugActivity.upEnabled = null;
            premiumDebugActivity.upSubscribed = null;
            premiumDebugActivity.mealMacroAvailable = null;
            premiumDebugActivity.mealMacroEnabled = null;
            premiumDebugActivity.mealMacroSubscribed = null;
            premiumDebugActivity.fileExportAvailable = null;
            premiumDebugActivity.fileExportEnabled = null;
            premiumDebugActivity.fileExportSubscribed = null;
            premiumDebugActivity.mealGoalsAvailable = null;
            premiumDebugActivity.mealGoalsEnabled = null;
            premiumDebugActivity.mealGoalsSubscribed = null;
            premiumDebugActivity.foodTimestampsAvailable = null;
            premiumDebugActivity.foodTimestampsEnabled = null;
            premiumDebugActivity.foodTimestampsSubscribed = null;
            premiumDebugActivity.available = null;
            premiumDebugActivity.localeInfo = null;
            premiumDebugActivity.hasSubscription = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
