package com.myfitnesspal.feature.dashboard.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.dashboard.event.LaunchCustomSummaryEvent;
import com.myfitnesspal.feature.dashboard.event.PresetNutrientSelectedEvent;
import com.myfitnesspal.feature.dashboard.ui.view.NutrientDashboardUtil;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.goals.util.GoalPreferenceUtil;
import com.myfitnesspal.feature.nutrition.model.Nutrient;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.service.PremiumService.Availability;
import com.myfitnesspal.feature.premium.ui.activity.PremiumUpsellActivity;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.LocalizedStrings;
import com.myfitnesspal.shared.event.AlertEvent;
import com.myfitnesspal.shared.event.NextButtonEvent;
import com.myfitnesspal.shared.model.v2.MfpGoalPreferences;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Function1;
import com.uacf.core.util.FunctionUtils;
import com.uacf.core.util.ParcelableUtil;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

public class NutrientDashboardPresetSelectionFragment extends MfpFragment {
    private static final HashMap<String, String> DISPLAY_TYPE_TO_EVENT_SETTING = new HashMap<>();
    private static final String EXTRA_CURRENT_DISPLAY_TYPE = "current_display_type";
    /* access modifiers changed from: private */
    public static final HashMap<Integer, String> VIEW_ID_TO_DISPLAY_TYPE = new HashMap<>();
    @Inject
    Lazy<AnalyticsService> analyticsService;
    private String caller;
    /* access modifiers changed from: private */
    public String currentDisplayType;
    private List<Nutrient> customNutrients;
    @BindView(2131362338)
    TextView displayImportantInfo;
    @Inject
    Lazy<NutrientGoalsUtil> goalsUtil;
    private boolean isIconsEnabled;
    /* access modifiers changed from: private */
    public boolean isLocked = false;
    /* access modifiers changed from: private */
    public boolean isUnavailable = false;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    final OnClickListener onRadioButtonClickListener = new OnClickListener() {
        public void onClick(View view) {
            int id = view.getId();
            if (id != R.id.rbCaloriesRem) {
                if (NutrientDashboardPresetSelectionFragment.this.isLocked) {
                    NutrientDashboardPresetSelectionFragment.this.getNavigationHelper().withIntent(PremiumUpsellActivity.newStartIntent((Context) NutrientDashboardPresetSelectionFragment.this.getActivity(), PremiumFeature.NutrientDashboard)).startActivity();
                    return;
                } else if (NutrientDashboardPresetSelectionFragment.this.isUnavailable) {
                    NutrientDashboardPresetSelectionFragment nutrientDashboardPresetSelectionFragment = NutrientDashboardPresetSelectionFragment.this;
                    nutrientDashboardPresetSelectionFragment.postEvent(new AlertEvent(nutrientDashboardPresetSelectionFragment.getResources().getString(R.string.premium_feature_revoked)).asToast());
                    return;
                }
            }
            NutrientDashboardPresetSelectionFragment.this.setRadioButtonChecked(view, true);
            NutrientDashboardPresetSelectionFragment.this.currentDisplayType = (String) NutrientDashboardPresetSelectionFragment.VIEW_ID_TO_DISPLAY_TYPE.get(Integer.valueOf(id));
            if (id == R.id.rbCustomSummary) {
                view.setPressed(true);
                NutrientDashboardPresetSelectionFragment.this.postEvent(new LaunchCustomSummaryEvent());
            } else {
                NutrientDashboardPresetSelectionFragment nutrientDashboardPresetSelectionFragment2 = NutrientDashboardPresetSelectionFragment.this;
                nutrientDashboardPresetSelectionFragment2.postEvent(new PresetNutrientSelectedEvent(nutrientDashboardPresetSelectionFragment2.currentDisplayType));
            }
        }
    };
    @Inject
    Lazy<PremiumService> premiumService;
    @BindView(2131363413)
    View rbCaloriesRem;
    @BindView(2131363416)
    View rbCustomSummary;
    @BindView(2131363417)
    View rbHeartHealthy;
    @BindView(2131363418)
    View rbLowCarb;
    @BindView(2131363419)
    View rbMacrosRem;
    @Inject
    Lazy<UserEnergyService> userEnergyService;

    static {
        VIEW_ID_TO_DISPLAY_TYPE.put(Integer.valueOf(R.id.rbCaloriesRem), Extras.DEFAULT_GOAL_DISPLAY);
        VIEW_ID_TO_DISPLAY_TYPE.put(Integer.valueOf(R.id.rbMacrosRem), "macros_remaining");
        VIEW_ID_TO_DISPLAY_TYPE.put(Integer.valueOf(R.id.rbHeartHealthy), Extras.HEART_HEALTHY_GOAL_DISPLAY);
        VIEW_ID_TO_DISPLAY_TYPE.put(Integer.valueOf(R.id.rbLowCarb), Extras.LOW_CARB_GOAL_DISPLAY);
        VIEW_ID_TO_DISPLAY_TYPE.put(Integer.valueOf(R.id.rbCustomSummary), Extras.CUSTOM_GOAL_DISPLAY);
        DISPLAY_TYPE_TO_EVENT_SETTING.put(Extras.DEFAULT_GOAL_DISPLAY, "default");
        DISPLAY_TYPE_TO_EVENT_SETTING.put("macros_remaining", "macros_remaining");
        DISPLAY_TYPE_TO_EVENT_SETTING.put(Extras.HEART_HEALTHY_GOAL_DISPLAY, Attributes.DASH_SETTING_HEART_HEALTHY);
        DISPLAY_TYPE_TO_EVENT_SETTING.put(Extras.LOW_CARB_GOAL_DISPLAY, Attributes.DASH_SETTING_LOW_CARB);
        DISPLAY_TYPE_TO_EVENT_SETTING.put(Extras.CUSTOM_GOAL_DISPLAY, "custom");
    }

    public static NutrientDashboardPresetSelectionFragment newInstance(Bundle bundle) {
        NutrientDashboardPresetSelectionFragment nutrientDashboardPresetSelectionFragment = new NutrientDashboardPresetSelectionFragment();
        nutrientDashboardPresetSelectionFragment.setArguments(bundle);
        return nutrientDashboardPresetSelectionFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        extractDataFromArgs(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setHasOptionsMenu(true);
        View inflate = layoutInflater.inflate(R.layout.fragment_change_summary, viewGroup, false);
        getMessageBus().post(new NextButtonEvent().setEnabled(true));
        return inflate;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString(EXTRA_CURRENT_DISPLAY_TYPE, this.currentDisplayType);
    }

    public void onResume() {
        super.onResume();
        this.customNutrients = NutrientDashboardUtil.filterNutrientsForDisplay(getSession().getUser().getCustomDisplayGoal());
        updateUiWithPremiumAvailability();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.isIconsEnabled = ConfigUtils.isNutrientDashboardIconsEnabled(getConfigService());
        setTitle(this.isIconsEnabled ? R.string.custom_dashboard : R.string.fragment_change_summary_title, new Object[0]);
        ViewUtils.setVisible(this.isIconsEnabled, this.displayImportantInfo);
        clearAllRadioButtons();
    }

    public void save(Function1<String> function1, Function1<List<Exception>> function12) {
        writeToBackend(getSelectedDisplayType(), function1, function12);
    }

    private String getSelectedDisplayType() {
        if (this.isLocked || this.isUnavailable) {
            return Extras.DEFAULT_GOAL_DISPLAY;
        }
        if (isRadioButtonChecked(this.rbMacrosRem)) {
            return (String) VIEW_ID_TO_DISPLAY_TYPE.get(Integer.valueOf(R.id.rbMacrosRem));
        }
        if (isRadioButtonChecked(this.rbHeartHealthy)) {
            return (String) VIEW_ID_TO_DISPLAY_TYPE.get(Integer.valueOf(R.id.rbHeartHealthy));
        }
        if (isRadioButtonChecked(this.rbLowCarb)) {
            return (String) VIEW_ID_TO_DISPLAY_TYPE.get(Integer.valueOf(R.id.rbLowCarb));
        }
        return isRadioButtonChecked(this.rbCustomSummary) ? (String) VIEW_ID_TO_DISPLAY_TYPE.get(Integer.valueOf(R.id.rbCustomSummary)) : Extras.DEFAULT_GOAL_DISPLAY;
    }

    private void extractDataFromArgs(Bundle bundle) {
        String str;
        this.caller = BundleUtils.getString(getArguments(), Extras.CALLER_KEY);
        this.currentDisplayType = BundleUtils.getString(bundle, EXTRA_CURRENT_DISPLAY_TYPE);
        if (Strings.isEmpty(this.currentDisplayType)) {
            getSession().getUser().getGoalPreferences();
            if (Strings.equals(this.caller, "home")) {
                str = GoalPreferenceUtil.getHomeGoalDisplay(getSession());
            } else {
                str = GoalPreferenceUtil.getDiaryGoalDisplay(getSession());
            }
            this.currentDisplayType = str;
        }
    }

    private void setTextAndLockIcon(View view, int i, int i2, int i3, boolean z) {
        setTextAndLockIcon(view, i, i2, getString(i3), z);
    }

    private void setTextAndLockIcon(View view, int i, int i2, String str, boolean z) {
        ImageView imageView = (ImageView) ViewUtils.findById(view, R.id.icon);
        ViewUtils.setVisible(this.isIconsEnabled, imageView);
        if (this.isIconsEnabled) {
            imageView.setImageDrawable(getResources().getDrawable(i));
        }
        ((TextView) ViewUtils.findById(view, R.id.title)).setText(i2);
        ((TextView) ViewUtils.findById(view, R.id.radio)).setCompoundDrawablesWithIntrinsicBounds(0, 0, z ? R.drawable.ic_premium_lock : R.drawable.radio_button, 0);
        TextView textView = (TextView) ViewUtils.findById(view, R.id.summary1);
        boolean notEmpty = Strings.notEmpty(str);
        ViewUtils.setVisible(notEmpty, textView);
        if (notEmpty) {
            textView.setText(str);
        }
    }

    private void updateUiWithPremiumAvailability() {
        Availability featureAvailability = ((PremiumService) this.premiumService.get()).getFeatureAvailability(PremiumFeature.NutrientDashboard);
        this.isLocked = featureAvailability == Availability.Locked;
        this.isUnavailable = featureAvailability == Availability.Revoked || featureAvailability == Availability.Hidden;
        boolean z = this.isLocked || this.isUnavailable;
        setTextAndLockIcon(this.rbCaloriesRem, (int) R.drawable.ic_calories, this.isIconsEnabled ? getLocalizedEnergyStringId(LocalizedStrings.CALORIE_FOCUSED) : R.string.radio_calories_remaining, getLocalizedEnergyString(this.isIconsEnabled ? LocalizedStrings.CALORIE_FOCUSED_SUBTEXT : LocalizedStrings.RADIO_TIP_DEFAULT), false);
        setTextAndLockIcon(this.rbMacrosRem, (int) R.drawable.ic_macros, this.isIconsEnabled ? R.string.macro_focused : R.string.radio_macros_remaining, getLocalizedEnergyString(this.isIconsEnabled ? LocalizedStrings.MACRO_FOCUSED_SUBTEXT : LocalizedStrings.RADIO_TIP_MACROS), z);
        setTextAndLockIcon(this.rbHeartHealthy, (int) R.drawable.ic_heart_heathly, (int) R.string.radio_heart_healthy, getLocalizedEnergyString(this.isIconsEnabled ? LocalizedStrings.HEART_HEALTHY_SUBTEXT : LocalizedStrings.RADIO_TIP_HEART_HEALTHY), z);
        setTextAndLockIcon(this.rbLowCarb, (int) R.drawable.ic_low_carb, (int) R.string.radio_low_carb, getLocalizedEnergyString(this.isIconsEnabled ? LocalizedStrings.LOW_CARB_SUBTEXT : LocalizedStrings.RADIO_TIP_LOW_CARB), z);
        setTextAndLockIcon(this.rbCustomSummary, (int) R.drawable.ic_cog, this.isIconsEnabled ? R.string.custom : R.string.radio_custom_summary, this.isIconsEnabled ? R.string.custom_subtext : R.string.radio_custom_summary_tip, z);
        ensureCorrectRadioButtonSelected();
        String str = "";
        if (CollectionUtils.notEmpty((Collection<?>) this.customNutrients)) {
            FragmentActivity activity = getActivity();
            str = String.format("(%s, %s, %s)", new Object[]{((Nutrient) this.customNutrients.get(0)).getNutrientNameString(activity), ((Nutrient) this.customNutrients.get(1)).getNutrientNameString(activity), ((Nutrient) this.customNutrients.get(2)).getNutrientNameString(activity)});
        }
        TextView textView = (TextView) ViewUtils.findById(this.rbCustomSummary, R.id.summary2);
        ViewUtils.setVisible(Strings.notEmpty(str), textView);
        textView.setText(str);
    }

    private String getLocalizedEnergyString(String str) {
        return ((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(str, this.userEnergyService);
    }

    private int getLocalizedEnergyStringId(String str) {
        return ((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedStringId(str, this.userEnergyService);
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void ensureCorrectRadioButtonSelected() {
        /*
            r3 = this;
            java.lang.String r0 = r3.currentDisplayType
            int r1 = r0.hashCode()
            r2 = 1
            switch(r1) {
                case -1670936278: goto L_0x0033;
                case -1170652706: goto L_0x0029;
                case -834279909: goto L_0x001f;
                case 446259792: goto L_0x0015;
                case 716388772: goto L_0x000b;
                default: goto L_0x000a;
            }
        L_0x000a:
            goto L_0x003d
        L_0x000b:
            java.lang.String r1 = "custom_goal_display"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x003d
            r0 = 4
            goto L_0x003e
        L_0x0015:
            java.lang.String r1 = "low_carb_remaining"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x003d
            r0 = 3
            goto L_0x003e
        L_0x001f:
            java.lang.String r1 = "heart_healthy_remaining"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x003d
            r0 = 2
            goto L_0x003e
        L_0x0029:
            java.lang.String r1 = "macros_remaining"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x003d
            r0 = 1
            goto L_0x003e
        L_0x0033:
            java.lang.String r1 = "energy_breakdown"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x003d
            r0 = 0
            goto L_0x003e
        L_0x003d:
            r0 = -1
        L_0x003e:
            switch(r0) {
                case 0: goto L_0x005f;
                case 1: goto L_0x0059;
                case 2: goto L_0x0053;
                case 3: goto L_0x004d;
                case 4: goto L_0x0047;
                default: goto L_0x0041;
            }
        L_0x0041:
            android.view.View r0 = r3.rbCaloriesRem
            r3.setRadioButtonChecked(r0, r2)
            goto L_0x0064
        L_0x0047:
            android.view.View r0 = r3.rbCustomSummary
            r3.setRadioButtonChecked(r0, r2)
            goto L_0x0064
        L_0x004d:
            android.view.View r0 = r3.rbLowCarb
            r3.setRadioButtonChecked(r0, r2)
            goto L_0x0064
        L_0x0053:
            android.view.View r0 = r3.rbHeartHealthy
            r3.setRadioButtonChecked(r0, r2)
            goto L_0x0064
        L_0x0059:
            android.view.View r0 = r3.rbMacrosRem
            r3.setRadioButtonChecked(r0, r2)
            goto L_0x0064
        L_0x005f:
            android.view.View r0 = r3.rbCaloriesRem
            r3.setRadioButtonChecked(r0, r2)
        L_0x0064:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.dashboard.ui.fragment.NutrientDashboardPresetSelectionFragment.ensureCorrectRadioButtonSelected():void");
    }

    private boolean isRadioButtonChecked(View view) {
        return ((RadioButton) ViewUtils.findById(view, R.id.radio)).isChecked();
    }

    /* access modifiers changed from: private */
    public void setRadioButtonChecked(View view, boolean z) {
        view.setOnClickListener(null);
        if (z) {
            clearAllRadioButtons();
        }
        ((RadioButton) ViewUtils.findById(view, R.id.radio)).setChecked(z);
        view.setOnClickListener(this.onRadioButtonClickListener);
    }

    private void clearAllRadioButtons() {
        setRadioButtonChecked(this.rbCaloriesRem, false);
        setRadioButtonChecked(this.rbMacrosRem, false);
        setRadioButtonChecked(this.rbHeartHealthy, false);
        setRadioButtonChecked(this.rbLowCarb, false);
        setRadioButtonChecked(this.rbCustomSummary, false);
    }

    private void writeToBackend(final String str, final Function1<String> function1, Function1<List<Exception>> function12) {
        MfpGoalPreferences mfpGoalPreferences = (MfpGoalPreferences) ParcelableUtil.clone(getSession().getUser().getGoalPreferences(), MfpGoalPreferences.CREATOR);
        if (Strings.equals(this.caller, "home")) {
            mfpGoalPreferences.setHomeGoalDisplay(str);
        } else if (Strings.equals(this.caller, "diary") || Strings.equals(this.caller, Extras.DIARY_SETTINGS_PARENT)) {
            mfpGoalPreferences.setDiaryGoalDisplay(str);
        }
        getSession().getUser().updateGoalPreferences(new Function1<MfpGoalPreferences>() {
            public void execute(MfpGoalPreferences mfpGoalPreferences) {
                if (NutrientDashboardPresetSelectionFragment.this.isEnabled() || Strings.equals(str, Extras.CUSTOM_GOAL_DISPLAY)) {
                    NutrientDashboardPresetSelectionFragment.this.reportAnalyticsEvent(str);
                    FunctionUtils.invokeIfValid(function1, str);
                }
            }
        }, function12, mfpGoalPreferences);
    }

    /* access modifiers changed from: private */
    public void reportAnalyticsEvent(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("setting", DISPLAY_TYPE_TO_EVENT_SETTING.get(str));
        if (Strings.equals(str, Extras.CUSTOM_GOAL_DISPLAY)) {
            this.customNutrients = NutrientDashboardUtil.filterNutrientsForDisplay(getSession().getUser().getCustomDisplayGoal());
            hashMap.put(Attributes.SETTING_CUSTOM_1, ((Nutrient) this.customNutrients.get(0)).getApiKey());
            hashMap.put(Attributes.SETTING_CUSTOM_2, ((Nutrient) this.customNutrients.get(1)).getApiKey());
            hashMap.put(Attributes.SETTING_CUSTOM_3, ((Nutrient) this.customNutrients.get(2)).getApiKey());
        }
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Strings.equals(this.caller, "home") ? Events.HOME_DASH_ASSIGN : Events.DIARY_DASH_ASSIGN, (Map<String, String>) hashMap);
    }

    public void setEnabled(boolean z) {
        this.rbCaloriesRem.setEnabled(z);
        this.rbMacrosRem.setEnabled(z);
        this.rbHeartHealthy.setEnabled(z);
        this.rbLowCarb.setEnabled(z);
        this.rbCustomSummary.setEnabled(z);
    }
}
