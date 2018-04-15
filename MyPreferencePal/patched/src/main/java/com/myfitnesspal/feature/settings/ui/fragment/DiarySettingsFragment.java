package com.myfitnesspal.feature.settings.ui.fragment;

import com.myfitnesspal.feature.goals.ui.activity.Goals;
import com.myfitnesspal.shared.ui.fragment.*;
import dagger.*;
import com.myfitnesspal.shared.service.analytics.*;
import javax.inject.*;
import com.myfitnesspal.shared.service.install.*;
import com.myfitnesspal.feature.settings.util.*;
import com.myfitnesspal.shared.service.globalsettings.*;
import com.myfitnesspal.feature.settings.model.*;
import com.myfitnesspal.shared.service.localsettings.*;
import com.myfitnesspal.feature.dashboard.service.*;
import com.myfitnesspal.shared.service.premium.*;
import com.myfitnesspal.feature.premium.service.*;
import com.myfitnesspal.feature.premium.ui.activity.*;
import android.content.*;
import com.myfitnesspal.shared.util.*;
import android.app.*;
import com.myfitnesspal.feature.settings.ui.activity.*;
import com.myfitnesspal.feature.dashboard.ui.activity.*;
import java.util.*;
import com.myfitnesspal.shared.model.*;
import com.myfitnesspal.shared.ui.dialog.*;
import android.os.*;
import android.view.*;
import com.myfitnesspal.feature.settings.event.*;
import com.myfitnesspal.shared.ui.activity.*;
import com.squareup.otto.*;
import android.widget.*;
import com.myfitnesspal.shared.ui.view.*;
import com.uacf.core.util.*;
import lanchon.dexpatcher.annotation.*;

@DexEdit(defaultAction = DexAction.IGNORE)
public class DiarySettingsFragment extends MfpFragment
{
    private static final String ALWAYS_SHOW_WATER_DIARY_TOGGLED = "always_show_water_diary_toggled";
    private static final String HIDE_CARD = "hide_card";
    private static final String SHOW_CARD = "show_card";
    private static final String VALUE = "value";
    @Inject
    Lazy<AnalyticsService> analyticsService;
    @Inject
    Lazy<CountryService> countryService;
	@DexAdd
    public List<DiarySetting2> diarySetting2s;
    @Inject
    Lazy<DiarySharingSettingsManager> diarySharingSettingsManager;
    @Inject
    Lazy<GlobalSettingsService> globalSettingsService;
    @Inject
    Lazy<InsightSettings> insightSettings;
    private ListView listView;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    @Inject
    Lazy<NutrientDashboardAnalyticsHelper> nutrientDashboardAnalyticsHelper;
    private AdapterView.OnItemClickListener onListItemClick;
    @Inject
    Lazy<PremiumAnalyticsHelper> premiumAnalyticsHelper;
    @Inject
    Lazy<PremiumService> premiumService;
    private List<Integer> searchTabIds;
    private List<String> searchTabStrings;
    
	@DexAdd
	class BToTheFifth_ItemClickListener1 implements AdapterView.OnItemClickListener {
		public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
            final CheckBox checkBox = (CheckBox)view.findViewById(/*2131756829*/ 2131756833);
            final DiarySetting2 diarySetting = (DiarySetting2)DiarySettingsFragment.this.diarySetting2s.get(n);
            final boolean b = !checkBox.isChecked();
            checkBox.setChecked(b);
			if (diarySetting == DiarySetting2.MealMacros)
			{
                    final User user = DiarySettingsFragment.this.getSession().getUser();
                    user.setDisplayDiaryMealMacros(b);
                    user.updatePropertyNamed("display_diary_meal_macros");
                    ((PremiumAnalyticsHelper)DiarySettingsFragment.this.premiumAnalyticsHelper.get()).reportMealMacroSettingToggledFromDiarySettings(b);
                    DiarySettingsFragment.this.getNavigationHelper().withIntent(PremiumUpsellActivity.newStartIntent((Context)DiarySettingsFragment.this.getActivity(), PremiumFeature.MealMacros)).startActivity();
			}
			else if (diarySetting == DiarySetting2.NetCarbs)
			{
				((LocalSettingsService)DiarySettingsFragment.this.localSettingsService.get()).setShouldShowNetCarbs(b);
                ((CheckBox)DiarySettingsFragment.this.getView().findViewWithTag("SmartCarbTag")).setEnabled(b);

                if (!b)
                {
                    ((LocalSettingsService)DiarySettingsFragment.this.localSettingsService.get()).setShouldShowSmartCarbs(b);
                    ((CheckBox)DiarySettingsFragment.this.getView().findViewWithTag("SmartCarbTag")).setChecked(b);
                }
			}
			else if (diarySetting == DiarySetting2.SmartCarbs)
            {
                ((LocalSettingsService)DiarySettingsFragment.this.localSettingsService.get()).setShouldShowSmartCarbs(b);
            }
			else if (diarySetting == DiarySetting2.WaterCard)
			{
				((LocalSettingsService)DiarySettingsFragment.this.localSettingsService.get()).setShouldShowWaterCard(b);
                    if (ConfigUtils.isSponsoredWaterAnalyticsEnabled(DiarySettingsFragment.this.getConfigService())) {
                        final AnalyticsService analyticsService = (AnalyticsService)DiarySettingsFragment.this.analyticsService.get();
                        String s;
                        if (b) {
                            s = "show_card";
                        }
                        else {
                            s = "hide_card";
                        }
                        analyticsService.reportEvent("always_show_water_diary_toggled", MapUtil.createMap("value", s));
                    }
			}
			/*
			else if (diarySetting == DiarySetting2.MealsDiaryTab)
			{
				((LocalSettingsService)DiarySettingsFragment.this.localSettingsService.get()).setShouldShowAllMeals(b);
			}
			*/
			else if (diarySetting == DiarySetting2.MultiAdd)
			{
				((LocalSettingsService)DiarySettingsFragment.this.localSettingsService.get()).setMultiAddToggleOnByDefault(b);
			}
			else if (diarySetting == DiarySetting2.FoodInsights)
			{
				((InsightSettings)DiarySettingsFragment.this.insightSettings.get()).setInsightsEnabled(b);
                    final AnalyticsService analyticsService2 = (AnalyticsService)DiarySettingsFragment.this.analyticsService.get();
                    String s2;
                    if (b) {
                        s2 = "insights_setting_in";
                    }
                    else {
                        s2 = "insights_setting_out";
                    }
                    analyticsService2.reportEvent(s2);
			}
			else if (diarySetting == DiarySetting2.DefaultSearchTab)
			{
				DiarySettingsFragment.this.showSearchTabSettingsDialog((TextView)view.findViewById(/*2131756830*/ 2131756834));
			}
			else if (diarySetting == DiarySetting2.DiarySharing)
			{
				((DiarySharingSettingsManager)DiarySettingsFragment.this.diarySharingSettingsManager.get()).showChooser(DiarySettingsFragment.this.getActivity());
			}
			else if (diarySetting == DiarySetting2.CustomMealNames)
			{
				DiarySettingsFragment.this.getNavigationHelper().withIntent(CustomMealNames.createNewIntent((Context)DiarySettingsFragment.this.getActivity())).startActivity();
			}
			else if (diarySetting == DiarySetting2.CustomNutrientDashboard)
			{
				((NutrientDashboardAnalyticsHelper)DiarySettingsFragment.this.nutrientDashboardAnalyticsHelper.get()).reportCustomNutrientDashboardScreenViewed("settings");
                    DiarySettingsFragment.this.getNavigationHelper().withExtra("settings_parent", "diary_settings").withIntent(NutrientDashboardSettingsActivity.newStartIntent((Context)DiarySettingsFragment.this.getActivity())).startActivity();
			}
			else if (diarySetting == DiarySetting2.EditGoals)
            {
                DiarySettingsFragment.this.getNavigationHelper().withIntent(Goals.newStartIntent((Context)DiarySettingsFragment.this.getActivity())).startActivity();
            }
            else if (diarySetting == DiarySetting2.ShowAllFoods)
            {
                ((LocalSettingsService)DiarySettingsFragment.this.localSettingsService.get()).setShouldShowAllMeals(b);
            }
        }
	}
	
	
	@DexReplace
    public DiarySettingsFragment() {
        this.onListItemClick = (AdapterView.OnItemClickListener) new BToTheFifth_ItemClickListener1();
    }
    
    
    private void setupSearchTabs() {
        this.searchTabStrings = new ArrayList<String>();
        this.searchTabIds = new ArrayList<Integer>();
        this.searchTabStrings.add(this.getString(2131233248));
        this.searchTabIds.add(6001);
        if (((CountryService)this.countryService.get()).isEnglishCurrentDialect()) {
            this.searchTabStrings.add(this.getString(2131232322));
            this.searchTabIds.add(6000);
            this.searchTabStrings.add(this.getString(2131232792));
            this.searchTabIds.add(6002);
        }
        this.searchTabStrings.add(this.getString(2131232793));
        this.searchTabIds.add(6003);
        this.searchTabStrings.add(this.getString(2131233264));
        this.searchTabIds.add(6004);
    }
    
    @DexIgnore
    private void showSearchTabSettingsDialog(final TextView textView) {

    }

    @DexAdd
    public void updateDefaultSearchTab_public(final TextView textView) {
        textView.setText((CharSequence)(String)this.searchTabStrings.get(this.searchTabIds.indexOf((Object)((LocalSettingsService)this.localSettingsService.get()).getDefaultSearchTab())));
    }
    
    @Override
    public void onActivityCreated(final Bundle bundle) {
        super.onActivityCreated(bundle);
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.component().inject(this);
    }
    
	//This doesn't need to be replaced because of the final else statement
    @DexReplace
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        int i = 0;
        final View inflate = layoutInflater.inflate(/*2130903305*/ 2130903308, viewGroup, false);
        this.listView = (ListView)inflate.findViewById(/*2131756038*/ 2131756039);
        this.diarySetting2s = new ArrayList<DiarySetting2>();
        for (DiarySetting2[] values = DiarySetting2.values(); i < values.length; ++i) {
            final DiarySetting2 diarySetting = values[i];
            if (diarySetting == DiarySetting2.MealMacros) {
                if (((PremiumService)this.premiumService.get()).isFeatureAvailable(PremiumFeature.MealMacros)) {
                    this.diarySetting2s.add(diarySetting);
                }
            }
            else if (diarySetting == DiarySetting2.WaterCard) {
                if (ConfigUtils.isSponsoredWaterEnabled(this.getConfigService())) {
                    this.diarySetting2s.add(diarySetting);
                }
            }
            else if (diarySetting == DiarySetting2.CustomMealNames) {
                if (this.getConfigService().isVariantEnabled("custom-meal-names-android-2016-04")) {
                    this.diarySetting2s.add(diarySetting);
                }
            }
            else if (diarySetting == DiarySetting2.CustomNutrientDashboard) {
                if (this.getConfigService().isVariantEnabled("nutrient-dashboard-diary-setting-android-2017-03")) {
                    this.diarySetting2s.add(diarySetting);
                }
            }
            else if (diarySetting == DiarySetting2.EditGoals)
            {
                if (ConfigUtils.isAppNavUpdateDiaryEnabled(this.getConfigService())) {
                    this.diarySetting2s.add(diarySetting);
                }
            }
            else {
                this.diarySetting2s.add(diarySetting);
            }
        }
        this.setupSearchTabs();
        this.listView.setAdapter((ListAdapter)new DiarySettingsAdapter2(this.diarySetting2s));
        this.listView.setOnItemClickListener(this.onListItemClick);
        return inflate;
    }
    
    @Subscribe
    public void onDiarySharingSettingChange(final DiarySharingSettingChangeEvent diarySharingSettingChangeEvent) {
        ((DiarySettingsAdapter2)this.listView.getAdapter()).notifyDataSetChanged();
        ((MfpActivity)this.getActivity()).scheduleSync();
    }
    
	@DexAdd
    public class DiarySettingsAdapter2 extends BaseAdapter
    {
        List<DiarySetting2> settings2;
        
        public DiarySettingsAdapter2(final List<DiarySetting2> settings2) {
            this.settings2 = settings2;
        }
        
        public int getCount() {
            return this.settings2.size();
        }

        @Override
        public DiarySetting2 getItem(final int n) {
            return this.settings2.get(n);
        }
        
        public long getItemId(final int n) {
            return n;
        }
        
        public View getView(final int n, final View view, final ViewGroup viewGroup) {
            View inflate;
            DiarySettingViewHolder tag;
            if (view == null) {
                inflate = LayoutInflater.from((Context)DiarySettingsFragment.this.getActivity()).inflate(/*2130903614*/ 2130903617, viewGroup, false);
                tag = new DiarySettingViewHolder(inflate);
                inflate.setTag((Object)tag);
            }
            else {
                final DiarySettingViewHolder diarySettingViewHolder = (DiarySettingViewHolder)view.getTag();
                inflate = view;
                tag = diarySettingViewHolder;
            }
            tag.setData(this.getItem(n), n);
            return inflate;
        }
        
		@DexAdd
        private class DiarySettingViewHolder extends ViewHolder<DiarySetting2>
        {
            private final CheckBox box;
            private final View premiumLock;
            public final TextView selectedSetting;
            private final TextView settingName;
            private final TextView subTitle;
            
            protected DiarySettingViewHolder(final View view) {
                super(view);
                this.settingName = this.findById(/*2131756828*/2131756832);
                this.subTitle = this.findById(/*2131755579*/2131755579);
                this.box = this.findById(/*2131756829*/2131756833);
                this.selectedSetting = this.findById(/*2131756830*/2131756834);
                this.premiumLock = this.findById(/*2131756291*/2131756295);
            }

            public void setData(final DiarySetting2 diarySetting, int n) {
                if (diarySetting == DiarySetting2.NetCarbs)
                {
                    this.settingName.setText("Show Net Carbs");
                }
                else if (diarySetting == DiarySetting2.SmartCarbs)
                {
                    this.settingName.setText("Use Smart Net Carbs");
                }
                else
                {
                    this.settingName.setText(diarySetting.getStringResId());
                }

                this.box.setClickable(false);
                ViewUtils.setVisible(this.box);
                ViewUtils.setGone((View)this.selectedSetting);
                ViewUtils.setGone(this.premiumLock);
                ViewUtils.setGone((View)this.subTitle);
				if (diarySetting == DiarySetting2.MealMacros)
				{
					final boolean showUpdatedMacrosByMealGoalStrings = ConfigUtils.showUpdatedMacrosByMealGoalStrings(DiarySettingsFragment.this.getConfigService());
                        this.box.setChecked(DiarySettingsFragment.this.getSession().getUser().shouldDisplayDiaryMealMacros());
                        if (!((PremiumService)DiarySettingsFragment.this.premiumService.get()).isFeatureSubscribed(PremiumFeature.MealMacros)) {
                            ViewUtils.setVisible(this.premiumLock);
                            ViewUtils.setGone((View)this.box);
                        }
                        final TextView settingName = this.settingName;
                        if (showUpdatedMacrosByMealGoalStrings) {
                            //n = 2131233498;
                            n = 2131233504;
                        }
                        else {
                            //n = 2131233496;
                            n = 2131233502;
                        }
                        settingName.setText(n);
                        ViewUtils.setVisible(this.subTitle);
                        final TextView subTitle = this.subTitle;
                        if (showUpdatedMacrosByMealGoalStrings) {
                            //n = 2131233494;
                             n = 2131233500;
                        }
                        else {
                            //n = 2131233497;
                            n = 2131233503;
                        }
                        subTitle.setText(n);
				}
				else if (diarySetting == DiarySetting2.NetCarbs)
				{
					this.box.setChecked(((LocalSettingsService)DiarySettingsFragment.this.localSettingsService.get()).shouldShowNetCarbs());
				}
				else if (diarySetting == DiarySetting2.SmartCarbs)
                {
                    this.box.setChecked(((LocalSettingsService)DiarySettingsFragment.this.localSettingsService.get()).shouldShowSmartCarbs());
                    this.box.setEnabled(LocalSettingsServiceImpl.prefs2.getBoolean("show_net_carbs", false));
                    this.box.setTag("SmartCarbTag");
                    this.subTitle.setText("On:\nNet carbs = Min(total carbs, Max((total carbs) - fiber, (calories - 9*fat - 4*protein)/4))\nOff:\nNet carbs = carbs - fiber");
                    ViewUtils.setVisible(this.subTitle);
                }
				else if (diarySetting == DiarySetting2.WaterCard)
				{
					this.box.setChecked(((LocalSettingsService)DiarySettingsFragment.this.localSettingsService.get()).shouldShowWaterCard());
				}
				/*
				else if (diarySetting == DiarySetting2.MealsDiaryTab)
				{
					this.box.setChecked(((LocalSettingsService)DiarySettingsFragment.this.localSettingsService.get()).shouldShowAllMeals());
				}
				*/
				else if (diarySetting == DiarySetting2.MultiAdd)
				{
					this.box.setChecked(((LocalSettingsService)DiarySettingsFragment.this.localSettingsService.get()).getMultiAddToggleOnByDefault());
				}
				else if (diarySetting == DiarySetting2.FoodInsights)
				{
					this.box.setChecked(((InsightSettings)DiarySettingsFragment.this.insightSettings.get()).areInsightsEnabled());
				}
				else if (diarySetting == DiarySetting2.DefaultSearchTab)
				{
					this.box.setVisibility(8);
                    final TextView ss = this.selectedSetting;
                    ss.setVisibility(0);
					DiarySettingsFragment dsla = DiarySettingsFragment.this;
                    dsla.updateDefaultSearchTab_public(ss);
				}
				else if (diarySetting == DiarySetting2.DiarySharing)
				{
					this.box.setVisibility(8);
                    this.selectedSetting.setVisibility(0);
                    this.selectedSetting.setText((CharSequence)((DiarySharingSettingsManager)DiarySettingsFragment.this.diarySharingSettingsManager.get()).getLocalizedStringForCurrentSetting());
				}
				else if (diarySetting == DiarySetting2.CustomMealNames)
				{
					ViewUtils.setGone((View)this.box);
				}
				else if (diarySetting == DiarySetting2.CustomNutrientDashboard)
				{
					ViewUtils.setGone((View)this.box);
				}
				else if (diarySetting == DiarySetting2.EditGoals.EditGoals)
                {
                    ViewUtils.setGone((View)this.box);
                }
                else if (diarySetting == DiarySetting2.ShowAllFoods)
                {
                    this.box.setChecked(((LocalSettingsService)DiarySettingsFragment.this.localSettingsService.get()).shouldShowAllMeals());
                    ViewUtils.setVisible(true, this.subTitle);
                    this.subTitle.setText(2131231530);
                }
            }
        }
    }
}
