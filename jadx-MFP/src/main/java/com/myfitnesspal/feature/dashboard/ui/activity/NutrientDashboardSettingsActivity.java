package com.myfitnesspal.feature.dashboard.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.dashboard.event.LaunchCustomSummaryEvent;
import com.myfitnesspal.feature.dashboard.event.PresetNutrientSelectedEvent;
import com.myfitnesspal.feature.dashboard.ui.fragment.CustomNutrientDashboardSelectionFragment;
import com.myfitnesspal.feature.dashboard.ui.fragment.NutrientDashboardPresetSelectionFragment;
import com.myfitnesspal.feature.dashboard.ui.view.NutrientDashboardUtil;
import com.myfitnesspal.feature.home.ui.activity.HomeActivity;
import com.myfitnesspal.feature.premium.util.PremiumApiErrorUtil;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.Fragments;
import com.myfitnesspal.shared.event.AlertEvent;
import com.myfitnesspal.shared.event.NextButtonEvent;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Function1;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class NutrientDashboardSettingsActivity extends MfpActivity {
    private static final int ACTION_BAR_DONE = 100;
    private static final String EXTRA_CURRENT_FRAGMENT = "current_fragment";
    private String caller = "";
    private boolean enableDoneButton = true;
    @Inject
    Lazy<PremiumApiErrorUtil> premiumApiErrorUtil;
    /* access modifiers changed from: private */
    public boolean shouldNavigateToHome;

    public static Intent newStartIntent(Context context) {
        return new Intent(context, NutrientDashboardSettingsActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        setContentView((int) R.layout.activity_macros_setting);
        this.caller = ExtrasUtils.getString(getIntent(), Extras.SETTINGS_PARENT);
        this.shouldNavigateToHome = ExtrasUtils.getBoolean(getIntent(), Extras.NAVIGATE_TO_HOME);
        if (bundle == null) {
            showPresetSelectionFragment();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString(EXTRA_CURRENT_FRAGMENT, getVisibleFragment().getTag());
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        if (!isBusy() && getSupportFragmentManager().getBackStackEntryCount() > 0) {
            MenuItemCompat.setShowAsAction(menu.add(0, 100, 0, R.string.done).setIcon(this.enableDoneButton ? R.drawable.ic_check_white_24dp : R.drawable.ic_check_disabled_white_24dp), 2);
            menu.getItem(0).setEnabled(this.enableDoneButton);
        }
        return true;
    }

    public void onUpPressed() {
        handleHomePressed();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 100) {
            return super.onOptionsItemSelected(menuItem);
        }
        saveChanges();
        return true;
    }

    public void setBusy(boolean z) {
        super.setBusy(z);
        supportInvalidateOptionsMenu();
    }

    private ArrayList<String> extractCustomNutrientsFromUser() {
        return NutrientDashboardUtil.filterNutrientStringsForDisplay(getSession().getUser().getCustomDisplayGoal().getNutrients());
    }

    private void showPresetSelectionFragment() {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(Extras.NUTRIENTS_KEY, extractCustomNutrientsFromUser());
        bundle.putString(Extras.CALLER_KEY, this.caller);
        getSupportFragmentManager().beginTransaction().replace(R.id.summaryContainer, NutrientDashboardPresetSelectionFragment.newInstance(bundle), Fragments.DASHBOARD_PRESET_FRAGMENT).commit();
        supportInvalidateOptionsMenu();
    }

    private void showCustomSelectionFragment() {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(Extras.NUTRIENTS_KEY, extractCustomNutrientsFromUser());
        setDoneButtonEnabled(false);
        getSupportFragmentManager().beginTransaction().replace(R.id.summaryContainer, CustomNutrientDashboardSelectionFragment.newInstance(bundle), Fragments.DASHBOARD_CUSTOM_NUTRIENTS_FRAGMENT).addToBackStack(Fragments.DASHBOARD_CUSTOM_NUTRIENTS_FRAGMENT).commit();
        supportInvalidateOptionsMenu();
    }

    private void handleHomePressed() {
        if (getVisibleFragment() instanceof CustomNutrientDashboardSelectionFragment) {
            showPresetSelectionFragment();
        } else {
            finish();
        }
    }

    @Subscribe
    public void onPresetNutrientSetSelectedEvent(PresetNutrientSelectedEvent presetNutrientSelectedEvent) {
        saveChanges();
    }

    /* access modifiers changed from: private */
    public void saveSelectedPreset(final NutrientDashboardPresetSelectionFragment nutrientDashboardPresetSelectionFragment) {
        setBusy(true);
        nutrientDashboardPresetSelectionFragment.setEnabled(false);
        nutrientDashboardPresetSelectionFragment.save(new Function1<String>() {
            public void execute(String str) {
                if (NutrientDashboardSettingsActivity.this.shouldNavigateToHome) {
                    NutrientDashboardSettingsActivity.this.getNavigationHelper().withIntent(HomeActivity.newStartIntent(this)).startActivity();
                } else {
                    NutrientDashboardSettingsActivity.this.getNavigationHelper().setResult(-1, new Intent().putExtra(Extras.SUMMARY_TYPE_KEY, str)).done();
                }
            }
        }, new Function1<List<Exception>>() {
            public void execute(List<Exception> list) {
                nutrientDashboardPresetSelectionFragment.setEnabled(true);
                NutrientDashboardSettingsActivity.this.setBusy(false);
                ((PremiumApiErrorUtil) NutrientDashboardSettingsActivity.this.premiumApiErrorUtil.get()).showAlertForApiError(list, NutrientDashboardSettingsActivity.this.getString(R.string.error_could_not_set_nutrient_dashboard));
            }
        });
    }

    private void saveCustomSelection(CustomNutrientDashboardSelectionFragment customNutrientDashboardSelectionFragment) {
        setBusy(true);
        customNutrientDashboardSelectionFragment.save(new Function0() {
            public void execute() {
                if (NutrientDashboardSettingsActivity.this.isEnabled()) {
                    Fragment findFragmentByTag = NutrientDashboardSettingsActivity.this.getSupportFragmentManager().findFragmentByTag(Fragments.DASHBOARD_PRESET_FRAGMENT);
                    if (findFragmentByTag instanceof NutrientDashboardPresetSelectionFragment) {
                        NutrientDashboardSettingsActivity.this.saveSelectedPreset((NutrientDashboardPresetSelectionFragment) findFragmentByTag);
                    }
                }
            }
        }, new Function0() {
            public void execute() {
                NutrientDashboardSettingsActivity.this.setBusy(false);
                NutrientDashboardSettingsActivity.this.getSupportFragmentManager().popBackStack();
                NutrientDashboardSettingsActivity.this.supportInvalidateOptionsMenu();
                NutrientDashboardSettingsActivity nutrientDashboardSettingsActivity = NutrientDashboardSettingsActivity.this;
                nutrientDashboardSettingsActivity.postEvent(new AlertEvent(nutrientDashboardSettingsActivity.getString(R.string.error_could_not_set_goals)));
            }
        });
    }

    private Fragment getVisibleFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.summaryContainer);
    }

    private void saveChanges() {
        Fragment visibleFragment = getVisibleFragment();
        if (visibleFragment instanceof CustomNutrientDashboardSelectionFragment) {
            saveCustomSelection((CustomNutrientDashboardSelectionFragment) visibleFragment);
        } else if (visibleFragment instanceof NutrientDashboardPresetSelectionFragment) {
            saveSelectedPreset((NutrientDashboardPresetSelectionFragment) visibleFragment);
        }
    }

    @Subscribe
    public void onLaunchCustomSummaryEvent(LaunchCustomSummaryEvent launchCustomSummaryEvent) {
        showCustomSelectionFragment();
    }

    @Subscribe
    public void onNextButtonEvent(NextButtonEvent nextButtonEvent) {
        setDoneButtonEnabled(nextButtonEvent.isEnabled());
    }

    private void setDoneButtonEnabled(boolean z) {
        this.enableDoneButton = z;
        supportInvalidateOptionsMenu();
    }
}
