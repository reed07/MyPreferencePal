package com.myfitnesspal.feature.debug.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.util.PremiumFeatureOverrides;
import com.myfitnesspal.feature.premium.util.PremiumFeatureOverrides.OverrideState;
import com.myfitnesspal.feature.settings.model.ABTestSettings;
import com.myfitnesspal.shared.constants.Constants.ABTest;
import com.myfitnesspal.shared.constants.Constants.ABTest.Premium.DisableReceiptPostToServer;
import com.myfitnesspal.shared.constants.Constants.ABTest.Premium.DisableReceiptRetrieval;
import com.myfitnesspal.shared.geolocation.GeoLocationService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;

public class PremiumDebugActivity extends MfpActivity {
    private static final int NEXT = 100;
    private static final int TOGGLE = 101;
    @Inject
    Lazy<ABTestSettings> abTestSettings;
    @BindView(2131361889)
    Switch afAvailable;
    @BindView(2131361890)
    Switch afEnabled;
    @BindView(2131361891)
    Switch afSubscribed;
    @BindView(2131361925)
    TextView available;
    @BindView(2131362150)
    Switch coAvailable;
    @BindView(2131362151)
    Switch coEnabled;
    @BindView(2131362152)
    Switch coSubscribed;
    @BindView(2131362327)
    Switch disableReceiptPostToServer;
    @BindView(2131362328)
    Switch disableReceiptRetrieval;
    @BindView(2131362342)
    Switch dmAvailable;
    @BindView(2131362343)
    Switch dmEnabled;
    @BindView(2131362344)
    Switch dmSubscribed;
    @BindView(2131362362)
    Switch ecAvailable;
    @BindView(2131362363)
    Switch ecEnabled;
    @BindView(2131362364)
    Switch ecSubscribed;
    @BindView(2131362311)
    Switch featureOverrides;
    @BindView(2131362571)
    Switch fileExportAvailable;
    @BindView(2131362572)
    Switch fileExportEnabled;
    @BindView(2131362573)
    Switch fileExportSubscribed;
    @BindView(2131362599)
    Switch flAvailable;
    @BindView(2131362604)
    Switch flEnabled;
    @BindView(2131362611)
    Switch flSubscribed;
    @BindView(2131362629)
    Switch foodTimestampsAvailable;
    @BindView(2131362630)
    Switch foodTimestampsEnabled;
    @BindView(2131362631)
    Switch foodTimestampsSubscribed;
    @Inject
    Lazy<GeoLocationService> geoService;
    @BindView(2131362722)
    Switch grAvailable;
    @BindView(2131362723)
    Switch grEnabled;
    @BindView(2131362724)
    Switch grSubscribed;
    @BindView(2131362728)
    TextView hasSubscription;
    @BindView(2131362952)
    TextView localeInfo;
    @BindView(2131363004)
    Switch mbdAvailable;
    @BindView(2131363005)
    Switch mbdEnabled;
    @BindView(2131363006)
    Switch mbdSubscribed;
    @BindView(2131363008)
    Switch mealGoalsAvailable;
    @BindView(2131363009)
    Switch mealGoalsEnabled;
    @BindView(2131363010)
    Switch mealGoalsSubscribed;
    @BindView(2131363011)
    Switch mealMacroAvailable;
    @BindView(2131363012)
    Switch mealMacroEnabled;
    @BindView(2131363013)
    Switch mealMacroSubscribed;
    @Inject
    Lazy<PremiumFeatureOverrides> overrides;
    @Inject
    Lazy<PremiumService> premiumService;
    @BindView(2131363353)
    Switch qaAvailable;
    @BindView(2131363354)
    Switch qaEnabled;
    @BindView(2131363355)
    Switch qaSubscribed;
    private List<Switch> switches;
    @BindView(2131363841)
    Switch tmAvailable;
    @BindView(2131363842)
    Switch tmEnabled;
    @BindView(2131363843)
    Switch tmSubscribed;
    @BindView(2131364085)
    Switch upAvailable;
    @BindView(2131364086)
    Switch upEnabled;
    @BindView(2131364087)
    Switch upSubscribed;
    @BindView(2131364143)
    Switch vfAvailable;
    @BindView(2131364144)
    Switch vfEnabled;
    @BindView(2131364145)
    Switch vfSubscribed;

    public static Intent newStartIntent(Context context) {
        return new Intent(context, PremiumDebugActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.premium_debug_activity);
        component().inject(this);
        this.switches = ViewUtils.findByType(findViewById(R.id.mainContainer), Switch.class);
        this.featureOverrides.setChecked(((PremiumFeatureOverrides) this.overrides.get()).areOverridesEnabled());
        this.featureOverrides.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                PremiumDebugActivity.lambda$onCreate$0(PremiumDebugActivity.this, compoundButton, z);
            }
        });
        this.disableReceiptRetrieval.setChecked(isOverrideEnabled(DisableReceiptRetrieval.NAME));
        this.disableReceiptRetrieval.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                PremiumDebugActivity.this.setOverrideValue(DisableReceiptRetrieval.NAME, z);
            }
        });
        this.disableReceiptPostToServer.setChecked(isOverrideEnabled(DisableReceiptPostToServer.NAME));
        this.disableReceiptPostToServer.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                PremiumDebugActivity.this.setOverrideValue(DisableReceiptPostToServer.NAME, z);
            }
        });
    }

    public static /* synthetic */ void lambda$onCreate$0(PremiumDebugActivity premiumDebugActivity, CompoundButton compoundButton, boolean z) {
        premiumDebugActivity.save();
        ((PremiumFeatureOverrides) premiumDebugActivity.overrides.get()).setOverridesEnabled(z);
        premiumDebugActivity.refreshUi();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        refreshUi();
    }

    private void save() {
        HashMap hashMap = new HashMap();
        for (Switch switchR : this.switches) {
            if (switchR.getTag() != null) {
                hashMap.put((String) switchR.getTag(), Boolean.valueOf(switchR.isChecked()));
            }
        }
        ((PremiumFeatureOverrides) this.overrides.get()).save(hashMap);
    }

    private void refreshUi() {
        this.available.setText(String.format("%s", new Object[]{Boolean.valueOf(((PremiumService) this.premiumService.get()).isPremiumAvailable())}));
        this.localeInfo.setText(String.format("%s, %s", new Object[]{((GeoLocationService) this.geoService.get()).getLocaleCode(), ((GeoLocationService) this.geoService.get()).getCountryCode()}));
        this.hasSubscription.setText(String.format("%s", new Object[]{Boolean.valueOf(((PremiumService) this.premiumService.get()).isPremiumSubscribed())}));
        PremiumFeatureOverrides premiumFeatureOverrides = (PremiumFeatureOverrides) this.overrides.get();
        OverrideState overrideFor_CONFIG_DEBUG_ONLY = premiumFeatureOverrides.getOverrideFor_CONFIG_DEBUG_ONLY(PremiumFeature.QuickAddMacros);
        if (overrideFor_CONFIG_DEBUG_ONLY != null) {
            this.qaAvailable.setChecked(overrideFor_CONFIG_DEBUG_ONLY.isAvailable());
            this.qaEnabled.setChecked(overrideFor_CONFIG_DEBUG_ONLY.isEnabled());
            this.qaSubscribed.setChecked(overrideFor_CONFIG_DEBUG_ONLY.isSubscribed());
        }
        OverrideState overrideFor_CONFIG_DEBUG_ONLY2 = premiumFeatureOverrides.getOverrideFor_CONFIG_DEBUG_ONLY(PremiumFeature.TrackMacrosByGram);
        if (overrideFor_CONFIG_DEBUG_ONLY2 != null) {
            this.tmAvailable.setChecked(overrideFor_CONFIG_DEBUG_ONLY2.isAvailable());
            this.tmEnabled.setChecked(overrideFor_CONFIG_DEBUG_ONLY2.isEnabled());
            this.tmSubscribed.setChecked(overrideFor_CONFIG_DEBUG_ONLY2.isSubscribed());
        }
        OverrideState overrideFor_CONFIG_DEBUG_ONLY3 = premiumFeatureOverrides.getOverrideFor_CONFIG_DEBUG_ONLY(PremiumFeature.AssignExerciseCalories);
        if (overrideFor_CONFIG_DEBUG_ONLY3 != null) {
            this.ecAvailable.setChecked(overrideFor_CONFIG_DEBUG_ONLY3.isAvailable());
            this.ecEnabled.setChecked(overrideFor_CONFIG_DEBUG_ONLY3.isEnabled());
            this.ecSubscribed.setChecked(overrideFor_CONFIG_DEBUG_ONLY3.isSubscribed());
        }
        OverrideState overrideFor_CONFIG_DEBUG_ONLY4 = premiumFeatureOverrides.getOverrideFor_CONFIG_DEBUG_ONLY(PremiumFeature.NutrientDashboard);
        if (overrideFor_CONFIG_DEBUG_ONLY4 != null) {
            this.dmAvailable.setChecked(overrideFor_CONFIG_DEBUG_ONLY4.isAvailable());
            this.dmEnabled.setChecked(overrideFor_CONFIG_DEBUG_ONLY4.isEnabled());
            this.dmSubscribed.setChecked(overrideFor_CONFIG_DEBUG_ONLY4.isSubscribed());
        }
        OverrideState overrideFor_CONFIG_DEBUG_ONLY5 = premiumFeatureOverrides.getOverrideFor_CONFIG_DEBUG_ONLY(PremiumFeature.CustomDailyGoals);
        if (overrideFor_CONFIG_DEBUG_ONLY5 != null) {
            this.mbdAvailable.setChecked(overrideFor_CONFIG_DEBUG_ONLY5.isAvailable());
            this.mbdEnabled.setChecked(overrideFor_CONFIG_DEBUG_ONLY5.isEnabled());
            this.mbdSubscribed.setChecked(overrideFor_CONFIG_DEBUG_ONLY5.isSubscribed());
        }
        OverrideState overrideFor_CONFIG_DEBUG_ONLY6 = premiumFeatureOverrides.getOverrideFor_CONFIG_DEBUG_ONLY(PremiumFeature.AdFree);
        if (overrideFor_CONFIG_DEBUG_ONLY5 != null) {
            this.afAvailable.setChecked(overrideFor_CONFIG_DEBUG_ONLY6.isAvailable());
            this.afEnabled.setChecked(overrideFor_CONFIG_DEBUG_ONLY6.isEnabled());
            this.afSubscribed.setChecked(overrideFor_CONFIG_DEBUG_ONLY6.isSubscribed());
        }
        OverrideState overrideFor_CONFIG_DEBUG_ONLY7 = premiumFeatureOverrides.getOverrideFor_CONFIG_DEBUG_ONLY(PremiumFeature.Graphs);
        if (overrideFor_CONFIG_DEBUG_ONLY7 != null) {
            this.grAvailable.setChecked(overrideFor_CONFIG_DEBUG_ONLY7.isAvailable());
            this.grEnabled.setChecked(overrideFor_CONFIG_DEBUG_ONLY7.isEnabled());
            this.grSubscribed.setChecked(overrideFor_CONFIG_DEBUG_ONLY7.isSubscribed());
        }
        OverrideState overrideFor_CONFIG_DEBUG_ONLY8 = premiumFeatureOverrides.getOverrideFor_CONFIG_DEBUG_ONLY(PremiumFeature.FoodLists);
        if (overrideFor_CONFIG_DEBUG_ONLY8 != null) {
            this.flAvailable.setChecked(overrideFor_CONFIG_DEBUG_ONLY8.isAvailable());
            this.flEnabled.setChecked(overrideFor_CONFIG_DEBUG_ONLY8.isEnabled());
            this.flSubscribed.setChecked(overrideFor_CONFIG_DEBUG_ONLY8.isSubscribed());
        }
        OverrideState overrideFor_CONFIG_DEBUG_ONLY9 = premiumFeatureOverrides.getOverrideFor_CONFIG_DEBUG_ONLY(PremiumFeature.Content);
        if (overrideFor_CONFIG_DEBUG_ONLY9 != null) {
            this.coAvailable.setChecked(overrideFor_CONFIG_DEBUG_ONLY9.isAvailable());
            this.coEnabled.setChecked(overrideFor_CONFIG_DEBUG_ONLY9.isEnabled());
            this.coSubscribed.setChecked(overrideFor_CONFIG_DEBUG_ONLY9.isSubscribed());
        }
        OverrideState overrideFor_CONFIG_DEBUG_ONLY10 = premiumFeatureOverrides.getOverrideFor_CONFIG_DEBUG_ONLY(PremiumFeature.VerifiedFoods);
        if (overrideFor_CONFIG_DEBUG_ONLY10 != null) {
            this.vfAvailable.setChecked(overrideFor_CONFIG_DEBUG_ONLY10.isAvailable());
            this.vfEnabled.setChecked(overrideFor_CONFIG_DEBUG_ONLY10.isEnabled());
            this.vfSubscribed.setChecked(overrideFor_CONFIG_DEBUG_ONLY10.isSubscribed());
        }
        OverrideState overrideFor_CONFIG_DEBUG_ONLY11 = premiumFeatureOverrides.getOverrideFor_CONFIG_DEBUG_ONLY(PremiumFeature.Upsell);
        if (overrideFor_CONFIG_DEBUG_ONLY11 != null) {
            this.upAvailable.setChecked(overrideFor_CONFIG_DEBUG_ONLY11.isAvailable());
            this.upEnabled.setChecked(overrideFor_CONFIG_DEBUG_ONLY11.isEnabled());
            this.upSubscribed.setChecked(overrideFor_CONFIG_DEBUG_ONLY11.isSubscribed());
        }
        OverrideState overrideFor_CONFIG_DEBUG_ONLY12 = premiumFeatureOverrides.getOverrideFor_CONFIG_DEBUG_ONLY(PremiumFeature.MealMacros);
        if (overrideFor_CONFIG_DEBUG_ONLY12 != null) {
            this.mealMacroAvailable.setChecked(overrideFor_CONFIG_DEBUG_ONLY12.isAvailable());
            this.mealMacroEnabled.setChecked(overrideFor_CONFIG_DEBUG_ONLY12.isEnabled());
            this.mealMacroSubscribed.setChecked(overrideFor_CONFIG_DEBUG_ONLY12.isSubscribed());
        }
        OverrideState overrideFor_CONFIG_DEBUG_ONLY13 = premiumFeatureOverrides.getOverrideFor_CONFIG_DEBUG_ONLY(PremiumFeature.FileExport);
        if (overrideFor_CONFIG_DEBUG_ONLY13 != null) {
            this.fileExportAvailable.setChecked(overrideFor_CONFIG_DEBUG_ONLY13.isAvailable());
            this.fileExportEnabled.setChecked(overrideFor_CONFIG_DEBUG_ONLY13.isEnabled());
            this.fileExportSubscribed.setChecked(overrideFor_CONFIG_DEBUG_ONLY13.isSubscribed());
        }
        OverrideState overrideFor_CONFIG_DEBUG_ONLY14 = premiumFeatureOverrides.getOverrideFor_CONFIG_DEBUG_ONLY(PremiumFeature.MealGoals);
        if (overrideFor_CONFIG_DEBUG_ONLY14 != null) {
            this.mealGoalsAvailable.setChecked(overrideFor_CONFIG_DEBUG_ONLY14.isAvailable());
            this.mealGoalsEnabled.setChecked(overrideFor_CONFIG_DEBUG_ONLY14.isEnabled());
            this.mealGoalsSubscribed.setChecked(overrideFor_CONFIG_DEBUG_ONLY14.isSubscribed());
        }
        OverrideState overrideFor_CONFIG_DEBUG_ONLY15 = premiumFeatureOverrides.getOverrideFor_CONFIG_DEBUG_ONLY(PremiumFeature.FoodTimestamps);
        if (overrideFor_CONFIG_DEBUG_ONLY15 != null) {
            this.foodTimestampsAvailable.setChecked(overrideFor_CONFIG_DEBUG_ONLY15.isAvailable());
            this.foodTimestampsEnabled.setChecked(overrideFor_CONFIG_DEBUG_ONLY15.isEnabled());
            this.foodTimestampsSubscribed.setChecked(overrideFor_CONFIG_DEBUG_ONLY15.isSubscribed());
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        menu.add(0, 101, 0, R.string.select_all).setIcon(R.drawable.ic_act_bar_multiadd).setShowAsAction(2);
        menu.add(1, 100, 0, R.string.done).setIcon(R.drawable.ic_check_white_24dp).setShowAsAction(2);
        return true;
    }

    public void onUpPressed() {
        finish();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 100:
                save();
                finish();
                return true;
            case 101:
                toggleSwitches();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    private void toggleSwitches() {
        boolean any = Enumerable.any(this.switches, $$Lambda$PremiumDebugActivity$L5P8rv7lvPGNt8316ySVj5O6nI.INSTANCE);
        for (Switch checked : this.switches) {
            checked.setChecked(any);
        }
    }

    /* access modifiers changed from: private */
    public void setOverrideValue(String str, boolean z) {
        ((ABTestSettings) this.abTestSettings.get()).setVariantOverrideFor(str, z ? "on" : ABTest.VARIANT_NO_OVERRIDE);
    }

    private boolean isOverrideEnabled(String str) {
        return "on".equals(((ABTestSettings) this.abTestSettings.get()).getVariantOverrideFor(str, null));
    }
}
