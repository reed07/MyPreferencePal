package com.myfitnesspal.feature.nutrition.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.fileexport.service.FileExportAnalyticsHelper;
import com.myfitnesspal.feature.fileexport.ui.activity.FileExport;
import com.myfitnesspal.feature.fileexport.ui.activity.FileExport.ExportMode;
import com.myfitnesspal.feature.fileexport.ui.activity.FileExportPreview;
import com.myfitnesspal.feature.nutrition.event.GraphSubTypeChanged;
import com.myfitnesspal.feature.nutrition.service.NutritionGraphPreferenceService;
import com.myfitnesspal.feature.nutrition.ui.fragment.GraphViewFragment;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.ui.activity.PremiumUpsellActivity;
import com.myfitnesspal.feature.settings.ui.activity.WeeklyNutritionSettings;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.Graphs.Types;
import com.myfitnesspal.shared.model.AdUnit;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import javax.inject.Inject;

public class Nutrition extends NutritionPremiumActivityBase {
    private static final int MENU_FILE_EXPORT = 1001;
    private static final int MENU_SETTINGS = 1002;
    private int currentGraphSubtype;
    @Inject
    Lazy<FileExportAnalyticsHelper> fileExportAnalyticsHelper;
    private String graphType;
    private boolean hasUserClickedFileExportOnce;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    @Inject
    Lazy<NutritionGraphPreferenceService> nutritionGraphService;
    @Inject
    Lazy<PremiumService> premiumService;
    @BindView(2131363774)
    TabLayout tabLayout;

    public String getAnalyticsScreenTag() {
        return Screens.NUTRITION;
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, Nutrition.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.nutrition_premium);
        component().inject(this);
        showCorrespondingGraph();
        setupTabs();
        MaterialUtils.removeDefaultToolbarElevation(this);
        MaterialUtils.enableAppBarScrollIfLollipop(this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.hasUserClickedFileExportOnce = ((LocalSettingsService) this.localSettingsService.get()).hasUserClickedFileExportOnce();
        invalidateOptionsMenu();
        selectCurrentTab();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(Extras.CURRENT_GRAPH, this.currentGraphViewFragment.getGraphSubType());
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.currentGraphViewFragment.setGraphSubType(bundle.getInt(Extras.CURRENT_GRAPH, 1));
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        NutritionGraphPreferenceService nutritionGraphService2 = getNutritionGraphService();
        nutritionGraphService2.setGraphType(this.graphType);
        nutritionGraphService2.setGraphSubType(this.currentGraphSubtype);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (((PremiumService) this.premiumService.get()).isFeatureAvailable(PremiumFeature.FileExport)) {
            menu.add(0, 1001, 0, R.string.file_export).setIcon(this.hasUserClickedFileExportOnce ? R.drawable.ic_export_data : R.drawable.ic_export_data_alert).setShowAsAction(2);
        }
        if (ConfigUtils.isAppNavUpdateDiaryEnabled(getConfigService())) {
            menu.add(0, 1002, 0, R.string.settings).setIcon(R.drawable.ic_act_bar_settings).setShowAsAction(2);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 1001) {
            boolean showFileExportFeaturePreview = ConfigUtils.showFileExportFeaturePreview(getConfigService());
            boolean isFeatureSubscribed = ((PremiumService) this.premiumService.get()).isFeatureSubscribed(PremiumFeature.FileExport);
            boolean showFileExportNewUpdatePreview = ConfigUtils.showFileExportNewUpdatePreview(getConfigService());
            if (!this.hasUserClickedFileExportOnce) {
                ((LocalSettingsService) this.localSettingsService.get()).setUserHasClickedFileExportOnce();
            }
            if (showFileExportFeaturePreview || showFileExportNewUpdatePreview) {
                ((FileExportAnalyticsHelper) this.fileExportAnalyticsHelper.get()).reportFileExportIconClicked("nutrition", isFeatureSubscribed);
                if (!isFeatureSubscribed) {
                    ((FileExportAnalyticsHelper) this.fileExportAnalyticsHelper.get()).reportFileExportCtaViewed("nutrition");
                }
            }
            if (isFeatureSubscribed || showFileExportFeaturePreview) {
                startActivity(FileExport.createIntentForFileExport(this, ExportMode.Normal));
            } else if (showFileExportNewUpdatePreview) {
                startActivity(FileExportPreview.newStartIntent(this));
            } else {
                getNavigationHelper().withIntent(PremiumUpsellActivity.newStartIntent((Context) this, PremiumFeature.FileExport)).startActivity();
            }
            return true;
        } else if (menuItem.getItemId() != 1002) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            getNavigationHelper().withIntent(WeeklyNutritionSettings.newStartIntent(this)).startActivity();
            return true;
        }
    }

    @Subscribe
    public void onGraphSubTypeChangedEvent(GraphSubTypeChanged graphSubTypeChanged) {
        this.currentGraphSubtype = graphSubTypeChanged.getGraphSubType();
    }

    private void setupTabs() {
        TabLayout tabLayout2 = this.tabLayout;
        tabLayout2.addTab(tabLayout2.newTab().setText((CharSequence) ((UserEnergyService) this.userEnergyService.get()).getCurrentEnergyUnitString()).setTag(Types.CALORIES));
        TabLayout tabLayout3 = this.tabLayout;
        tabLayout3.addTab(tabLayout3.newTab().setText((int) R.string.nutrients).setTag(Types.NUTRIENTS));
        TabLayout tabLayout4 = this.tabLayout;
        tabLayout4.addTab(tabLayout4.newTab().setText((int) R.string.macros).setTag(Types.MACROS));
        this.tabLayout.addOnTabSelectedListener(new OnTabSelectedListener() {
            public void onTabReselected(Tab tab) {
            }

            public void onTabUnselected(Tab tab) {
            }

            public void onTabSelected(Tab tab) {
                if (Nutrition.this.hasResumed()) {
                    Nutrition.this.showCorrespondingGraph((String) tab.getTag());
                }
            }
        });
    }

    private void selectCurrentTab() {
        for (int i = 0; i < this.tabLayout.getTabCount(); i++) {
            Tab tabAt = this.tabLayout.getTabAt(i);
            if (Strings.equals(tabAt.getTag(), (Object) this.graphType)) {
                tabAt.select();
                return;
            }
        }
    }

    private void showCorrespondingGraph() {
        NutritionGraphPreferenceService nutritionGraphService2 = getNutritionGraphService();
        String graphType2 = nutritionGraphService2.getGraphType();
        this.currentGraphSubtype = nutritionGraphService2.getGraphSubType();
        showCorrespondingGraph(graphType2, this.currentGraphSubtype);
    }

    /* access modifiers changed from: private */
    public void showCorrespondingGraph(String str) {
        showCorrespondingGraph(str, this.currentGraphSubtype);
    }

    private void showCorrespondingGraph(String str, int i) {
        this.graphType = str;
        this.currentGraphViewFragment = GraphViewFragment.newInstance(str, i);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fragment_placeholder, this.currentGraphViewFragment, str);
        beginTransaction.commit();
    }

    public AdUnit getAdUnit() {
        if (Strings.equals(this.graphType, Types.NUTRIENTS)) {
            return getAdUnitService().getNutritionScreenWeeklyAdUnitValue();
        }
        return getAdUnitService().getNutritionScreenDailyAdUnitValue();
    }

    private NutritionGraphPreferenceService getNutritionGraphService() {
        return (NutritionGraphPreferenceService) this.nutritionGraphService.get();
    }
}
