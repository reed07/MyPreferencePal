package com.myfitnesspal.feature.settings.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.dashboard.service.NutrientDashboardAnalyticsHelper;
import com.myfitnesspal.feature.dashboard.ui.activity.NutrientDashboardSettingsActivity;
import com.myfitnesspal.feature.goals.ui.activity.Goals;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.ui.activity.PremiumUpsellActivity;
import com.myfitnesspal.feature.search.ui.constants.FoodSearchTab;
import com.myfitnesspal.feature.settings.event.DiarySharingSettingChangeEvent;
import com.myfitnesspal.feature.settings.model.DiarySetting;
import com.myfitnesspal.feature.settings.model.InsightSettings;
import com.myfitnesspal.feature.settings.ui.activity.CustomMealNames;
import com.myfitnesspal.feature.settings.util.DiarySharingSettingsManager;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper;
import com.myfitnesspal.shared.constants.Constants.Analytics.Settings;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Basic;
import com.myfitnesspal.shared.model.CheckableListItem;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.premium.PremiumAnalyticsHelper;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

public class DiarySettingsFragment extends MfpFragment {
    private static final String ALWAYS_SHOW_WATER_DIARY_TOGGLED = "always_show_water_diary_toggled";
    private static final String HIDE_CARD = "hide_card";
    private static final String SHOW_CARD = "show_card";
    private static final String VALUE = "value";
    @Inject
    Lazy<AnalyticsService> analyticsService;
    @Inject
    Lazy<CountryService> countryService;
    @Inject
    Lazy<DiarySharingSettingsManager> diarySharingSettingsManager;
    @Inject
    Lazy<GlobalSettingsService> globalSettingsService;
    @Inject
    Lazy<InsightSettings> insightSettings;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    @Inject
    Lazy<NutrientDashboardAnalyticsHelper> nutrientDashboardAnalyticsHelper;
    @Inject
    Lazy<PremiumAnalyticsHelper> premiumAnalyticsHelper;
    @Inject
    Lazy<PremiumService> premiumService;
    private List<FoodSearchTab> searchTabs;
    @Inject
    Lazy<TimestampAnalyticsHelper> timestampAnalyticsHelper;
    private List<ViewHolder> viewHolders = new ArrayList();

    class ViewHolder implements OnClickListener {
        @BindView(2131362471)
        CheckBox box;
        private int id;
        @BindView(2131363297)
        View premiumLock;
        @BindView(2131363595)
        TextView selectedSetting;
        private DiarySetting setting;
        @BindView(2131363620)
        TextView settingName;
        @BindView(2131363750)
        TextView subTitle;

        public ViewHolder(DiarySetting diarySetting, View view) {
            ButterKnife.bind((Object) this, view);
            view.setOnClickListener(this);
            this.box.setSaveEnabled(false);
            this.setting = diarySetting;
            this.id = view.getId();
            checkVisibility(diarySetting, view);
        }

        public void onClick(View view) {
            boolean z = !this.box.isChecked();
            this.box.setChecked(z);
            switch (this.id) {
                case R.id.settings_all_foods /*2131363622*/:
                    ((LocalSettingsService) DiarySettingsFragment.this.localSettingsService.get()).setShouldShowAllMeals(z);
                    return;
                case R.id.settings_dashboard /*2131363623*/:
                    ((NutrientDashboardAnalyticsHelper) DiarySettingsFragment.this.nutrientDashboardAnalyticsHelper.get()).reportCustomNutrientDashboardScreenViewed("settings");
                    DiarySettingsFragment.this.getNavigationHelper().withExtra(Extras.SETTINGS_PARENT, Extras.DIARY_SETTINGS_PARENT).withIntent(NutrientDashboardSettingsActivity.newStartIntent(DiarySettingsFragment.this.getActivity())).startActivity();
                    return;
                case R.id.settings_diary_sharing /*2131363624*/:
                    ((DiarySharingSettingsManager) DiarySettingsFragment.this.diarySharingSettingsManager.get()).showChooser(DiarySettingsFragment.this.getActivity());
                    return;
                case R.id.settings_food_timestamps /*2131363625*/:
                    if (((PremiumService) DiarySettingsFragment.this.premiumService.get()).isFeatureSubscribed(PremiumFeature.FoodTimestamps)) {
                        ((LocalSettingsService) DiarySettingsFragment.this.localSettingsService.get()).setShouldShowFoodTimestamps(z);
                        ((TimestampAnalyticsHelper) DiarySettingsFragment.this.timestampAnalyticsHelper.get()).reportTimestampDiarySettingToggled(z);
                    } else {
                        DiarySettingsFragment.this.getNavigationHelper().withIntent(PremiumUpsellActivity.newStartIntent((Context) DiarySettingsFragment.this.getActivity(), TimestampAnalyticsHelper.FEATURE_TIMESTAMP_DIARY_SETTINGS)).startActivity();
                    }
                    if (z && !((LocalSettingsService) DiarySettingsFragment.this.localSettingsService.get()).didUserSeeTimestampFeature()) {
                        ((LocalSettingsService) DiarySettingsFragment.this.localSettingsService.get()).setUserSawTimestampFeature(true);
                        return;
                    }
                    return;
                case R.id.settings_goals /*2131363626*/:
                    DiarySettingsFragment.this.getNavigationHelper().withIntent(Goals.newStartIntent(DiarySettingsFragment.this.getActivity())).startActivity();
                    return;
                case R.id.settings_insights /*2131363627*/:
                    ((InsightSettings) DiarySettingsFragment.this.insightSettings.get()).setInsightsEnabled(z);
                    ((AnalyticsService) DiarySettingsFragment.this.analyticsService.get()).reportEvent(z ? Settings.INSIGHTS_OPT_IN : Settings.INSIGHTS_OPT_OUT);
                    return;
                case R.id.settings_macros /*2131363629*/:
                    if (((PremiumService) DiarySettingsFragment.this.premiumService.get()).isFeatureSubscribed(PremiumFeature.MealMacros)) {
                        User user = DiarySettingsFragment.this.getSession().getUser();
                        user.setDisplayDiaryMealMacros(z);
                        user.updatePropertyNamed(Basic.DISPLAY_DIARY_MEAL_MACROS);
                        ((PremiumAnalyticsHelper) DiarySettingsFragment.this.premiumAnalyticsHelper.get()).reportMealMacroSettingToggledFromDiarySettings(z);
                        return;
                    }
                    DiarySettingsFragment.this.getNavigationHelper().withIntent(PremiumUpsellActivity.newStartIntent((Context) DiarySettingsFragment.this.getActivity(), PremiumFeature.MealMacros)).startActivity();
                    return;
                case R.id.settings_meal_name /*2131363630*/:
                    DiarySettingsFragment.this.getNavigationHelper().withIntent(CustomMealNames.createNewIntent(DiarySettingsFragment.this.getActivity())).startActivity();
                    return;
                case R.id.settings_multiadd /*2131363631*/:
                    ((LocalSettingsService) DiarySettingsFragment.this.localSettingsService.get()).setMultiAddToggleOnByDefault(z);
                    return;
                case R.id.settings_search_tab /*2131363632*/:
                    DiarySettingsFragment.this.showSearchTabSettingsDialog((TextView) view.findViewById(R.id.selected_setting));
                    return;
                case R.id.settings_water /*2131363633*/:
                    ((LocalSettingsService) DiarySettingsFragment.this.localSettingsService.get()).setShouldShowWaterCard(z);
                    if (ConfigUtils.isSponsoredWaterAnalyticsEnabled(DiarySettingsFragment.this.getConfigService())) {
                        AnalyticsService analyticsService = (AnalyticsService) DiarySettingsFragment.this.analyticsService.get();
                        String str = DiarySettingsFragment.ALWAYS_SHOW_WATER_DIARY_TOGGLED;
                        String[] strArr = new String[2];
                        strArr[0] = "value";
                        strArr[1] = z ? DiarySettingsFragment.SHOW_CARD : DiarySettingsFragment.HIDE_CARD;
                        analyticsService.reportEvent(str, MapUtil.createMap(strArr));
                        return;
                    }
                    return;
                default:
                    return;
            }
        }

        /* access modifiers changed from: 0000 */
        public void rebind() {
            this.box.setClickable(false);
            ViewUtils.setVisible(this.box);
            ViewUtils.setGone(this.selectedSetting);
            ViewUtils.setGone(this.premiumLock);
            ViewUtils.setGone(this.subTitle);
            this.settingName.setText(this.setting.getStringResId());
            switch (this.id) {
                case R.id.settings_all_foods /*2131363622*/:
                    this.box.setChecked(((LocalSettingsService) DiarySettingsFragment.this.localSettingsService.get()).shouldShowAllMeals());
                    ViewUtils.setVisible(true, this.subTitle);
                    this.subTitle.setText(R.string.diary_settings_show_all_foods_subtext);
                    return;
                case R.id.settings_dashboard /*2131363623*/:
                    ViewUtils.setGone(this.box);
                    return;
                case R.id.settings_diary_sharing /*2131363624*/:
                    this.box.setVisibility(8);
                    this.selectedSetting.setVisibility(0);
                    this.selectedSetting.setText(((DiarySharingSettingsManager) DiarySettingsFragment.this.diarySharingSettingsManager.get()).getLocalizedStringForCurrentSetting());
                    return;
                case R.id.settings_food_timestamps /*2131363625*/:
                    this.subTitle.setText(R.string.diary_settings_description_show_food_timestamps);
                    if (!((PremiumService) DiarySettingsFragment.this.premiumService.get()).isFeatureSubscribed(PremiumFeature.FoodTimestamps)) {
                        ViewUtils.setVisible(this.premiumLock);
                        ViewUtils.setGone(this.box);
                    }
                    this.subTitle.setVisibility(0);
                    this.box.setChecked(((LocalSettingsService) DiarySettingsFragment.this.localSettingsService.get()).shouldShowFoodTimestamps());
                    return;
                case R.id.settings_goals /*2131363626*/:
                    ViewUtils.setGone(this.box);
                    return;
                case R.id.settings_insights /*2131363627*/:
                    this.box.setChecked(((InsightSettings) DiarySettingsFragment.this.insightSettings.get()).areInsightsEnabled());
                    return;
                case R.id.settings_macros /*2131363629*/:
                    boolean showUpdatedMacrosByMealGoalStrings = ConfigUtils.showUpdatedMacrosByMealGoalStrings(DiarySettingsFragment.this.getConfigService());
                    this.box.setChecked(DiarySettingsFragment.this.getSession().getUser().shouldDisplayDiaryMealMacros());
                    if (!((PremiumService) DiarySettingsFragment.this.premiumService.get()).isFeatureSubscribed(PremiumFeature.MealMacros)) {
                        ViewUtils.setVisible(this.premiumLock);
                        ViewUtils.setGone(this.box);
                    }
                    this.settingName.setText(showUpdatedMacrosByMealGoalStrings ? R.string.show_meal_macros_variant_b : R.string.show_meal_macros);
                    ViewUtils.setVisible(this.subTitle);
                    this.subTitle.setText(showUpdatedMacrosByMealGoalStrings ? R.string.show_carbs_protein_fat_by_gram_percent : R.string.show_meal_macros_subtext);
                    return;
                case R.id.settings_meal_name /*2131363630*/:
                    ViewUtils.setGone(this.box);
                    return;
                case R.id.settings_multiadd /*2131363631*/:
                    this.box.setChecked(((LocalSettingsService) DiarySettingsFragment.this.localSettingsService.get()).getMultiAddToggleOnByDefault());
                    return;
                case R.id.settings_search_tab /*2131363632*/:
                    this.box.setVisibility(8);
                    this.selectedSetting.setVisibility(0);
                    DiarySettingsFragment.this.updateDefaultSearchTab(this.selectedSetting);
                    return;
                case R.id.settings_water /*2131363633*/:
                    this.box.setChecked(((LocalSettingsService) DiarySettingsFragment.this.localSettingsService.get()).shouldShowWaterCard());
                    return;
                default:
                    return;
            }
        }

        private void checkVisibility(DiarySetting diarySetting, View view) {
            if (diarySetting == DiarySetting.FoodTimestamps && !((PremiumService) DiarySettingsFragment.this.premiumService.get()).isFeatureAvailable(PremiumFeature.FoodTimestamps)) {
                ViewUtils.setGone(view);
            }
        }
    }

    public class ViewHolder_ViewBinding implements Unbinder {
        private ViewHolder target;

        @UiThread
        public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
            this.target = viewHolder;
            viewHolder.settingName = (TextView) Utils.findRequiredViewAsType(view, R.id.setting_name, "field 'settingName'", TextView.class);
            viewHolder.subTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.subtitle, "field 'subTitle'", TextView.class);
            viewHolder.box = (CheckBox) Utils.findRequiredViewAsType(view, R.id.enabled, "field 'box'", CheckBox.class);
            viewHolder.selectedSetting = (TextView) Utils.findRequiredViewAsType(view, R.id.selected_setting, "field 'selectedSetting'", TextView.class);
            viewHolder.premiumLock = Utils.findRequiredView(view, R.id.premium_lock, "field 'premiumLock'");
        }

        @CallSuper
        public void unbind() {
            ViewHolder viewHolder = this.target;
            if (viewHolder != null) {
                this.target = null;
                viewHolder.settingName = null;
                viewHolder.subTitle = null;
                viewHolder.box = null;
                viewHolder.selectedSetting = null;
                viewHolder.premiumLock = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public static DiarySettingsFragment newInstance() {
        return new DiarySettingsFragment();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.diary_settings_fragment, viewGroup, false);
        setupSearchTabs();
        createViewHolders(inflate);
        rebindViewHolders();
        return inflate;
    }

    private void setupSearchTabs() {
        this.searchTabs = new ArrayList();
        if (ConfigUtils.isFoodSearchPhase1Enabled(getConfigService())) {
            this.searchTabs.add(FoodSearchTab.ALL);
            this.searchTabs.add(FoodSearchTab.RECIPES);
            this.searchTabs.add(FoodSearchTab.MEALS);
            this.searchTabs.add(FoodSearchTab.MY_FOODS);
            return;
        }
        this.searchTabs.add(FoodSearchTab.RECENT);
        if (((CountryService) this.countryService.get()).isEnglishCurrentDialect()) {
            this.searchTabs.add(FoodSearchTab.FREQUENT);
            this.searchTabs.add(FoodSearchTab.MY_FOODS);
        }
        this.searchTabs.add(FoodSearchTab.MEALS);
        this.searchTabs.add(FoodSearchTab.RECIPES);
    }

    /* access modifiers changed from: private */
    public void showSearchTabSettingsDialog(TextView textView) {
        FoodSearchTab fromTabId = FoodSearchTab.fromTabId(((LocalSettingsService) this.localSettingsService.get()).getDefaultSearchTab());
        if (!this.searchTabs.contains(fromTabId)) {
            fromTabId = getDefaultSearchTabIfAbsent();
        }
        ArrayList arrayList = new ArrayList(CollectionUtils.size((Collection<?>) this.searchTabs));
        for (FoodSearchTab foodSearchTab : this.searchTabs) {
            arrayList.add(new CheckableListItem(getString(foodSearchTab.getLabelResId()), foodSearchTab == fromTabId));
        }
        new MfpAlertDialogBuilder(getActivity()).setSingleChoiceItems((List<? extends CheckableListItem>) arrayList, (OnItemClickListener) new OnItemClickListener(textView) {
            private final /* synthetic */ TextView f$1;

            {
                this.f$1 = r2;
            }

            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                DiarySettingsFragment.lambda$showSearchTabSettingsDialog$0(DiarySettingsFragment.this, this.f$1, adapterView, view, i, j);
            }
        }).setTitle((int) R.string.default_search_tab_settings).show();
    }

    public static /* synthetic */ void lambda$showSearchTabSettingsDialog$0(DiarySettingsFragment diarySettingsFragment, TextView textView, AdapterView adapterView, View view, int i, long j) {
        ((LocalSettingsService) diarySettingsFragment.localSettingsService.get()).setDefaultSearchTab(((FoodSearchTab) diarySettingsFragment.searchTabs.get(i)).getTabId());
        diarySettingsFragment.updateDefaultSearchTab(textView);
    }

    /* access modifiers changed from: private */
    public void updateDefaultSearchTab(TextView textView) {
        FoodSearchTab fromTabId = FoodSearchTab.fromTabId(((LocalSettingsService) this.localSettingsService.get()).getDefaultSearchTab());
        if (!this.searchTabs.contains(fromTabId)) {
            fromTabId = getDefaultSearchTabIfAbsent();
        }
        if (fromTabId != null) {
            textView.setText(fromTabId.getLabelResId());
        }
    }

    private void createViewHolders(View view) {
        DiarySetting[] values;
        this.viewHolders.clear();
        for (DiarySetting diarySetting : DiarySetting.values()) {
            this.viewHolders.add(new ViewHolder(diarySetting, view.findViewById(diarySetting.getLayoutResId())));
        }
    }

    private void rebindViewHolders() {
        for (ViewHolder rebind : this.viewHolders) {
            rebind.rebind();
        }
    }

    private FoodSearchTab getDefaultSearchTabIfAbsent() {
        return ConfigUtils.isFoodSearchPhase1Enabled(getConfigService()) ? FoodSearchTab.ALL : FoodSearchTab.RECENT;
    }

    @Subscribe
    public void onDiarySharingSettingChange(DiarySharingSettingChangeEvent diarySharingSettingChangeEvent) {
        rebindViewHolders();
        ((MfpActivity) getActivity()).scheduleSync();
    }
}
