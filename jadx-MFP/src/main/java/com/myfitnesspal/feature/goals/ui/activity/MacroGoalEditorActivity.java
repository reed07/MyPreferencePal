package com.myfitnesspal.feature.goals.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.BindView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.goals.event.MacroGoalsUpdatedEvent;
import com.myfitnesspal.feature.goals.event.PickerValuesUpdatedEvent;
import com.myfitnesspal.feature.goals.ui.adapter.EditMacroGoalsPagerAdapter;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.service.PremiumService.Availability;
import com.myfitnesspal.feature.premium.util.PremiumApiErrorUtil;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.model.v2.MfpGoalPreferences;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.session.UserV2Service;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Ln;
import com.uacf.core.util.ParcelableUtil;
import dagger.Lazy;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

public class MacroGoalEditorActivity extends MfpActivity {
    private static final int ACTION_BAR_ACCEPT = 100;
    public static final String DEFAULT_TAB = "default_tab_key";
    public static final int GRAMS_TAB_POSITION = 1;
    private static final String IS_TOTAL_VALID_BEFORE_ROTATION = "is_total_valid_before_rotation";
    public static final int PERCENT_TAB_POSITION = 0;
    public static final int TAB_DEFINED_BY_GOAL = -1;
    @Inject
    Lazy<AnalyticsService> analyticsService;
    private EditMacroGoalsPagerAdapter editMacroGoalsPagerAdapter;
    private boolean gramsLockedTabViewAdded = false;
    private boolean isTotalValid = true;
    private boolean isTotalValidBeforeRotation = false;
    @BindView(2131363233)
    ViewPager macrosViewPager;
    private OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {
        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            if (MacroGoalEditorActivity.this.hasResumed()) {
                MacroGoalEditorActivity.this.setSelectedTab(i);
                MacroGoalEditorActivity.this.checkForGramsEligibility(i);
            }
        }
    };
    @Inject
    Lazy<PremiumApiErrorUtil> premiumApiErrorUtil;
    @Inject
    Lazy<PremiumService> premiumService;
    @Inject
    Lazy<Session> session;
    @BindView(2131363774)
    TabLayout tabLayout;
    @Inject
    Lazy<UserV2Service> userService;

    public static Intent newStartIntent(Context context, float f, float f2, float f3, float f4) {
        return newStartIntent(context, f, f2, f3, f4, -1);
    }

    public static Intent newStartIntent(Context context, float f, float f2, float f3, float f4, int i) {
        return new Intent(context, MacroGoalEditorActivity.class).putExtra(Extras.LOCALIZED_ENERGY, f).putExtra("carbs", f2).putExtra("protein", f3).putExtra("fat", f4).putExtra(DEFAULT_TAB, i);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.goals_macros);
        component().inject(this);
        this.macrosViewPager.setCurrentItem(0);
        initAdapterAndViewPager();
        this.tabLayout.setupWithViewPager(this.macrosViewPager);
        if (getMacrosByGramAvailability() != Availability.Available) {
            this.tabLayout.removeTabAt(1);
            Tab newTab = this.tabLayout.newTab();
            newTab.setCustomView((int) R.layout.macros_tab_with_lock);
            this.tabLayout.addTab(newTab);
        }
        this.isTotalValidBeforeRotation = BundleUtils.getBoolean(bundle, IS_TOTAL_VALID_BEFORE_ROTATION);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        updateUiFromPremiumState();
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        MenuItemCompat.setShowAsAction(menu.add(0, 100, 0, R.string.done).setIcon(this.isTotalValid ? R.drawable.ic_check_white_24dp : R.drawable.ic_check_disabled_white_24dp), 2);
        menu.getItem(0).setEnabled(this.isTotalValid);
        return true;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(IS_TOTAL_VALID_BEFORE_ROTATION, isTotalValid());
    }

    public void onUpPressed() {
        finish();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 100) {
            return super.onOptionsItemSelected(menuItem);
        }
        onAcceptPressed();
        return true;
    }

    private boolean isGramsEligibile() {
        return getMacrosByGramAvailability() == Availability.Available;
    }

    /* access modifiers changed from: private */
    public void finishWithEvent(MacroGoalsUpdatedEvent macroGoalsUpdatedEvent) {
        reportAnalyticsEvents(macroGoalsUpdatedEvent.getAnalyticsEventsJSON());
        Intent intent = getIntent();
        intent.putExtra(Extras.EVENT_SOURCE, macroGoalsUpdatedEvent);
        setResult(-1, intent);
        finish();
    }

    private void reportAnalyticsEvents(String str) {
        Map map = (Map) new ApiJsonMapper().tryMapFrom(str, Map.class);
        if (CollectionUtils.notEmpty(map)) {
            ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.MACROS_CHANGE, map);
        } else {
            Ln.e("analyticsMap parse failed", new Object[0]);
        }
    }

    private void writeUpdatedGoalFormatToBackend(MfpGoalPreferences mfpGoalPreferences, final MacroGoalsUpdatedEvent macroGoalsUpdatedEvent) {
        MfpGoalPreferences mfpGoalPreferences2 = (MfpGoalPreferences) ParcelableUtil.clone(mfpGoalPreferences, MfpGoalPreferences.CREATOR);
        mfpGoalPreferences2.setMacroGoalFormatAsGrams(macroGoalsUpdatedEvent.isGrams());
        setBusy(true);
        ((UserV2Service) this.userService.get()).updateGoalPreferencesAsync(new Function1<MfpGoalPreferences>() {
            public void execute(MfpGoalPreferences mfpGoalPreferences) {
                MacroGoalEditorActivity.this.setBusy(false);
                MacroGoalEditorActivity.this.finishWithEvent(macroGoalsUpdatedEvent);
            }
        }, new Function1<List<Exception>>() {
            public void execute(List<Exception> list) {
                MacroGoalEditorActivity.this.setBusy(false);
                Ln.e(list, new Object[0]);
                ((PremiumApiErrorUtil) MacroGoalEditorActivity.this.premiumApiErrorUtil.get()).showAlertForApiError(list, MacroGoalEditorActivity.this.getResources().getString(R.string.error_could_not_set_goals));
            }
        }, mfpGoalPreferences2);
    }

    private void onAcceptPressed() {
        MacroGoalsUpdatedEvent executeDoneAction = this.editMacroGoalsPagerAdapter.executeDoneAction(this.macrosViewPager.getCurrentItem());
        if (executeDoneAction != null) {
            MfpGoalPreferences goalPreferences = ((Session) this.session.get()).getUser().getGoalPreferences();
            if (executeDoneAction.isGrams() != goalPreferences.isMacroGoalFormatGrams()) {
                writeUpdatedGoalFormatToBackend(goalPreferences, executeDoneAction);
            } else {
                finishWithEvent(executeDoneAction);
            }
        }
    }

    private void initAdapterAndViewPager() {
        EditMacroGoalsPagerAdapter editMacroGoalsPagerAdapter2 = new EditMacroGoalsPagerAdapter(this, getSupportFragmentManager(), ExtrasUtils.getFloat(getIntent(), Extras.LOCALIZED_ENERGY, BitmapDescriptorFactory.HUE_RED), ExtrasUtils.getFloat(getIntent(), "carbs", BitmapDescriptorFactory.HUE_RED), ExtrasUtils.getFloat(getIntent(), "protein", BitmapDescriptorFactory.HUE_RED), ExtrasUtils.getFloat(getIntent(), "fat", BitmapDescriptorFactory.HUE_RED), ((Session) this.session.get()).getUser().getGoalPreferences().isMacroGoalFormatGrams(), this.premiumService);
        this.editMacroGoalsPagerAdapter = editMacroGoalsPagerAdapter2;
        this.macrosViewPager.setAdapter(this.editMacroGoalsPagerAdapter);
        this.macrosViewPager.addOnPageChangeListener(this.onPageChangeListener);
        initTab();
    }

    private void initTab() {
        int i = 1;
        switch (ExtrasUtils.getInt(getIntent(), DEFAULT_TAB, -1)) {
            case 0:
                setSelectedTab(0);
                return;
            case 1:
                setSelectedTab(1);
                return;
            default:
                if (!((Session) this.session.get()).getUser().getGoalPreferences().isMacroGoalFormatGrams() || !isGramsEligibile()) {
                    i = 0;
                }
                setSelectedTab(i);
                return;
        }
    }

    /* access modifiers changed from: private */
    public void setSelectedTab(int i) {
        this.macrosViewPager.setCurrentItem(i);
        handleMenuItemState();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void checkForGramsEligibility(int r5) {
        /*
            r4 = this;
            r0 = 0
            r1 = 1
            if (r5 != r1) goto L_0x003a
            com.myfitnesspal.feature.premium.service.PremiumService$Availability r2 = r4.getMacrosByGramAvailability()
            com.myfitnesspal.feature.premium.service.PremiumService$Availability r3 = com.myfitnesspal.feature.premium.service.PremiumService.Availability.Locked
            if (r2 != r3) goto L_0x001e
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r1 = r4.getNavigationHelper()
            com.myfitnesspal.feature.premium.service.PremiumFeature r2 = com.myfitnesspal.feature.premium.service.PremiumFeature.TrackMacrosByGram
            android.content.Intent r2 = com.myfitnesspal.feature.premium.ui.activity.PremiumUpsellActivity.newStartIntent(r4, r2)
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r1 = r1.withIntent(r2)
            r1.startActivity()
            goto L_0x003b
        L_0x001e:
            com.myfitnesspal.feature.premium.service.PremiumService$Availability r3 = com.myfitnesspal.feature.premium.service.PremiumService.Availability.Revoked
            if (r2 != r3) goto L_0x003a
            com.squareup.otto.Bus r1 = r4.getMessageBus()
            com.myfitnesspal.shared.event.AlertEvent r2 = new com.myfitnesspal.shared.event.AlertEvent
            r3 = 2131888714(0x7f120a4a, float:1.9412071E38)
            java.lang.String r3 = r4.getString(r3)
            r2.<init>(r3)
            com.myfitnesspal.shared.event.AlertEvent r2 = r2.asToast()
            r1.post(r2)
            goto L_0x003b
        L_0x003a:
            r0 = 1
        L_0x003b:
            if (r0 == 0) goto L_0x0042
            android.support.v4.view.ViewPager r0 = r4.macrosViewPager
            r0.setCurrentItem(r5)
        L_0x0042:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.goals.ui.activity.MacroGoalEditorActivity.checkForGramsEligibility(int):void");
    }

    private void handleMenuItemState() {
        this.isTotalValid = isTotalValid() || this.isTotalValidBeforeRotation;
        invalidateOptionsMenu();
    }

    private boolean isTotalValid() {
        if (this.macrosViewPager.getCurrentItem() == 1 || this.editMacroGoalsPagerAdapter.isTotalValidOnPercentFragment()) {
            return true;
        }
        return false;
    }

    private void updateUiFromPremiumState() {
        Availability macrosByGramAvailability = getMacrosByGramAvailability();
        if (macrosByGramAvailability == Availability.Locked && !this.gramsLockedTabViewAdded) {
            this.gramsLockedTabViewAdded = true;
        }
        if (macrosByGramAvailability != Availability.Available && this.macrosViewPager.getCurrentItem() == 1) {
            setSelectedTab(0);
        }
    }

    private Availability getMacrosByGramAvailability() {
        return ((PremiumService) this.premiumService.get()).getFeatureAvailability(PremiumFeature.TrackMacrosByGram);
    }

    @Subscribe
    public void onPickerValueUpdated(PickerValuesUpdatedEvent pickerValuesUpdatedEvent) {
        this.isTotalValid = pickerValuesUpdatedEvent.isTotalValid();
        supportInvalidateOptionsMenu();
    }
}
