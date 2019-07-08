package com.myfitnesspal.feature.addentry.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.addentry.event.UnitDialogDismissedEvent;
import com.myfitnesspal.feature.addentry.service.WaterLoggingAnalyticsHelper;
import com.myfitnesspal.feature.addentry.util.WaterSponsorshipUtil;
import com.myfitnesspal.feature.addentry.util.WaterSponsorshipUtil.WaterLoggingAsset;
import com.myfitnesspal.feature.alexainterstitial.ui.activity.AlexaInterstitialActivity;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.diary.ui.activity.Diary;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.settings.model.AdsSettings;
import com.myfitnesspal.feature.settings.ui.dialog.UnitsDialogFragment;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.shared.constants.Constants.Dialogs.Fragments;
import com.myfitnesspal.shared.model.AdNetworkType;
import com.myfitnesspal.shared.model.unitconv.LocalizedFluid;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.service.ads.AdsAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.location.LocationService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogPositiveListener;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.ui.view.CustomLocalizedNumberEditText;
import com.myfitnesspal.shared.util.AdsHelper;
import com.myfitnesspal.shared.util.AdsHelper.Builder;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.DecimalDigitsInputFilter;
import com.myfitnesspal.shared.util.GlideUtil;
import com.myfitnesspal.shared.util.UnitsUtils.Water;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import javax.inject.Inject;

public class WaterEntryActivity extends MfpActivity {
    private static final int ACTION_COMPLETE = 1001;
    private static final int CUPS_DECIMAL_PLACES = 2;
    private static final int CUPS_MAX = 50;
    private static final String DIALOG_SHOWN_REPORTED = "dialog_shown_reported";
    private static final int FLUID_OUNCES_DECIMAL_PLACES = 1;
    private static final int FLUID_OUNCES_MAX = 400;
    private static final String LIMIT_ERROR_TAG = "WaterEntryActivity.WaterLimitError";
    private static final int MILLILITERS_DECIMAL_PLACES = 0;
    private static final int MILLILITERS_MAX = 12000;
    private static final String WATER_DIALOG_ANALYTICS_TAG = "water_entry_dialog";
    private static final String WATER_ML = "water_ml";
    public static final String WATER_MODE_EXTRA = "water_mode";
    @Inject
    Lazy<AdsAnalyticsHelper> adsAnalytics;
    @Inject
    Lazy<AdsAnalyticsHelper> adsAnalyticsHelper;
    /* access modifiers changed from: private */
    public AdsHelper adsHelper;
    @Inject
    Lazy<AdsSettings> adsSettings;
    @Inject
    Lazy<AnalyticsService> analyticsService;
    @BindView(2131362126)
    View changeUnit;
    @Inject
    Lazy<ConfigService> configService;
    @Inject
    Lazy<CountryService> countryService;
    /* access modifiers changed from: private */
    public float currentValue;
    private ArrayList<LocalizedFluid> customQuickPicks;
    @Inject
    Lazy<DiaryService> diaryService;
    @BindView(2131362702)
    View genericBottles;
    private boolean isSponsoredWaterBrandingEnabled;
    @BindView(2131362868)
    ImageView ivBroughtToYouBuy;
    @BindView(2131362872)
    ImageView ivSponsoredWater;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    private LocalizedFluid localizedWater;
    @Inject
    Lazy<LocationService> locationService;
    @Inject
    Lazy<Bus> messageBus;
    private Mode mode;
    @Inject
    Lazy<NavigationHelper> navigationHelper;
    private LocalizedFluid preUnitChangeEntry;
    /* access modifiers changed from: private */
    public Water preUnitChangeUnit;
    @Inject
    Lazy<PremiumService> premiumService;
    @BindView(2131363361)
    Button quickPick1;
    @BindView(2131363362)
    Button quickPick2;
    @BindView(2131363363)
    Button quickPick3;
    @BindView(2131363364)
    View quickPicksContainer;
    private Session session;
    @BindView(2131363710)
    View sponsoredWaterBrandingContainer;
    @BindView(2131363878)
    View totalWaterToday;
    @BindView(2131364078)
    TextView unit;
    @Inject
    Lazy<UserApplicationSettingsService> userApplicationSettingsService;
    @BindView(2131364196)
    View waterCupsSpinner;
    @BindView(2131364197)
    TextView waterInsight;
    @Inject
    Lazy<WaterLoggingAnalyticsHelper> waterLoggingAnalyticsHelper;
    /* access modifiers changed from: private */
    public Water waterUnit;
    @BindView(2131364200)
    CustomLocalizedNumberEditText waterValue;
    private final TextWatcher waterValueWatcher = new TextWatcher() {
        public void afterTextChanged(Editable editable) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            WaterEntryActivity.this.currentValue = NumberUtils.localeFloatFromString(Strings.toString(charSequence));
        }
    };

    public enum Mode {
        Add,
        Edit
    }

    public static Intent newStartIntent(Context context, Mode mode2) {
        return new Intent(context, WaterEntryActivity.class).putExtra(WATER_MODE_EXTRA, mode2);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.water_entry);
        component().inject(this);
        ViewUtils.setGone(this.waterCupsSpinner);
        ViewUtils.setVisible(this.changeUnit);
        this.mode = (Mode) BundleUtils.getSerializable(WATER_MODE_EXTRA, Mode.Add, Mode.class.getClassLoader(), getIntent().getExtras());
        DiaryDay diaryDayForActiveDateSync = ((DiaryService) this.diaryService.get()).getDiaryDayForActiveDateSync();
        if (this.mode == Mode.Edit) {
            ((WaterLoggingAnalyticsHelper) this.waterLoggingAnalyticsHelper.get()).reportWaterScreenViewed("diary", "edit");
        }
        this.session = getSession();
        this.localizedWater = diaryDayForActiveDateSync.getLocalizedWater();
        this.waterValue.addTextChangedListener(this.waterValueWatcher);
        populateUnitDependants();
        setTitle(this.mode == Mode.Add ? R.string.add_water : R.string.edit_total);
        this.isSponsoredWaterBrandingEnabled = WaterSponsorshipUtil.shouldShowWaterSponsorship((ConfigService) this.configService.get(), this.session, (PremiumService) this.premiumService.get());
        this.sponsoredWaterBrandingContainer.setVisibility(this.isSponsoredWaterBrandingEnabled ? 4 : 8);
        ViewUtils.setVisible(!this.isSponsoredWaterBrandingEnabled, this.genericBottles);
        ViewUtils.setVisible(this.mode == Mode.Edit, this.totalWaterToday);
        if (this.isSponsoredWaterBrandingEnabled) {
            Builder builder = new Builder((ViewGroup) findById(R.id.ad_one), getAdUnitService().getSmartWaterEntryDialogAdUnitValue(), WATER_DIALOG_ANALYTICS_TAG, AdNetworkType.DFP, this.configService, this.localSettingsService, this.locationService, this.adsSettings, this.adsAnalytics, (NavigationHelper) this.navigationHelper.get(), this.session.getUser().isProfileCountryUS());
            this.adsHelper = builder.setWaterSponsor(WaterSponsorshipUtil.getSponsorName((ConfigService) this.configService.get())).build();
            GlideUtil.loadImageWithListener(this, WaterSponsorshipUtil.getAssetUrlForWaterEntry(this, (ConfigService) this.configService.get(), WaterLoggingAsset.WaterEntryBottles), this.ivSponsoredWater, new RequestListener<Drawable>() {
                public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                    return false;
                }

                public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                    ViewUtils.setVisible(WaterEntryActivity.this.sponsoredWaterBrandingContainer);
                    WaterEntryActivity.this.adsHelper.loadAds();
                    return false;
                }
            });
            String assetUrlForWaterEntry = WaterSponsorshipUtil.getAssetUrlForWaterEntry(this, (ConfigService) this.configService.get(), WaterLoggingAsset.BroughtToYouBy);
            ImageView imageView = this.ivBroughtToYouBuy;
            GlideUtil.loadImageWithFailureHandling(this, assetUrlForWaterEntry, imageView, imageView);
            String[] stringArray = getResources().getStringArray(R.array.water_insights);
            this.waterInsight.setText(stringArray[new Random().nextInt(stringArray.length)]);
        }
        this.localizedWater = diaryDayForActiveDateSync.getLocalizedWater();
        ViewUtils.setVisible(this.mode == Mode.Add, this.quickPicksContainer);
        this.changeUnit.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WaterEntryActivity waterEntryActivity = WaterEntryActivity.this;
                waterEntryActivity.preUnitChangeUnit = waterEntryActivity.waterUnit;
                WaterEntryActivity.this.getImmHelper().hideSoftInput();
                ((WaterLoggingAnalyticsHelper) WaterEntryActivity.this.waterLoggingAnalyticsHelper.get()).reportWaterUnitChangeClick(WaterLoggingAnalyticsHelper.ADD_WATER_SCREEN);
                UnitsDialogFragment.newInstance().show(WaterEntryActivity.this.getSupportFragmentManager(), Fragments.UNITS_DIALOG);
            }
        });
        if (!BundleUtils.getBoolean(bundle, DIALOG_SHOWN_REPORTED, false)) {
            ((WaterLoggingAnalyticsHelper) this.waterLoggingAnalyticsHelper.get()).reportDialogShown();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.waterValue.post(new Runnable() {
            public void run() {
                WaterEntryActivity.this.waterValue.requestFocus();
                WaterEntryActivity.this.getImmHelper().showSoftInput();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        getImmHelper().hideSoftInput();
    }

    private boolean waterLimitDialogShown() {
        boolean z;
        int i;
        if (this.mode != Mode.Add) {
            return false;
        }
        if (this.waterUnit == Water.CUPS) {
            i = 50;
            z = this.currentValue > 50.0f;
        } else if (this.waterUnit == Water.FL_OZ) {
            i = 400;
            z = this.currentValue > 400.0f;
        } else {
            i = MILLILITERS_MAX;
            z = this.currentValue > 12000.0f;
        }
        if (z) {
            showDialogFragment(((AlertDialogFragment) ((AlertDialogFragment) new AlertDialogFragment().setTitle(R.string.alert)).setMessage(getString(R.string.water_limit_error, new Object[]{NumberUtils.localeStringFromInt(i), LocalizedFluid.getCurrentWaterUnitPluralAbbrString(getBaseContext(), this.session)}))).setPositiveText(R.string.ok, new DialogPositiveListener() {
                public void onClick(Object obj) {
                }
            }), LIMIT_ERROR_TAG);
        }
        return z;
    }

    private void populateUnitDependants() {
        this.waterUnit = LocalizedFluid.getUserCurrentWaterUnit(this.session);
        this.unit.setText(LocalizedFluid.getCurrentWaterUnitAbbrString(this, this.session));
        int i = this.waterUnit == Water.CUPS ? 2 : this.waterUnit == Water.FL_OZ ? 1 : 0;
        this.waterValue.setAllowDecimal(this.waterUnit != Water.MILLILITERS);
        this.waterValue.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(5, i)});
        if (this.mode == Mode.Edit) {
            populateWaterValueEditTextFromLocalizedFluid(this.localizedWater);
        } else {
            setQuickPickDefaults();
        }
        LocalizedFluid localizedFluid = this.preUnitChangeEntry;
        if (localizedFluid != null) {
            populateWaterValueEditTextFromLocalizedFluid(localizedFluid);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(DIALOG_SHOWN_REPORTED, true);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItemCompat.setShowAsAction(menu.add(0, 1001, 1, R.string.ok).setIcon(R.drawable.ic_check_white_24dp), 2);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 1001) {
            return super.onOptionsItemSelected(menuItem);
        }
        if (!waterLimitDialogShown()) {
            saveWaterEntry();
            showAlexaInterstitialIfApplicable();
            finish();
        }
        return true;
    }

    private String[] getQuickPicksDefaultArray() {
        int i = this.waterUnit == Water.CUPS ? R.array.cups_quick_picks : this.waterUnit == Water.FL_OZ ? R.array.fl_oz_quick_picks : R.array.ml_quick_picks;
        return getResources().getStringArray(i);
    }

    private void setQuickPickDefaults() {
        String str;
        String str2;
        String str3;
        int i = this.waterUnit == Water.CUPS ? R.string.cups : this.waterUnit == Water.FL_OZ ? R.string.fl_oz_value : R.string.ml_value;
        this.customQuickPicks = ((LocalSettingsService) this.localSettingsService.get()).getCustomWaterQuickPicks(this.session);
        String[] quickPicksDefaultArray = getQuickPicksDefaultArray();
        String string = getString(i);
        switch (CollectionUtils.size((Collection<?>) this.customQuickPicks)) {
            case 1:
                str2 = getCustomQuickPickString(Double.valueOf(((LocalizedFluid) this.customQuickPicks.get(0)).getValue(this.waterUnit)));
                str = quickPicksDefaultArray[0];
                str3 = quickPicksDefaultArray[1];
                break;
            case 2:
                str2 = getCustomQuickPickString(Double.valueOf(((LocalizedFluid) this.customQuickPicks.get(0)).getValue(this.waterUnit)));
                str = getCustomQuickPickString(Double.valueOf(((LocalizedFluid) this.customQuickPicks.get(1)).getValue(this.waterUnit)));
                str3 = quickPicksDefaultArray[0];
                break;
            case 3:
                String customQuickPickString = getCustomQuickPickString(Double.valueOf(((LocalizedFluid) this.customQuickPicks.get(0)).getValue(this.waterUnit)));
                String customQuickPickString2 = getCustomQuickPickString(Double.valueOf(((LocalizedFluid) this.customQuickPicks.get(1)).getValue(this.waterUnit)));
                str2 = customQuickPickString;
                str3 = getCustomQuickPickString(Double.valueOf(((LocalizedFluid) this.customQuickPicks.get(2)).getValue(this.waterUnit)));
                str = customQuickPickString2;
                break;
            default:
                str2 = quickPicksDefaultArray[0];
                String str4 = quickPicksDefaultArray[1];
                str3 = quickPicksDefaultArray[2];
                str = str4;
                break;
        }
        setQuickPickButtonTextAndListener(this.quickPick1, string, str2);
        setQuickPickButtonTextAndListener(this.quickPick2, string, str);
        setQuickPickButtonTextAndListener(this.quickPick3, string, str3);
    }

    private String getCustomQuickPickString(Double d) {
        String str = this.waterUnit == Water.CUPS ? "#.##" : this.waterUnit == Water.FL_OZ ? "#.#" : "#";
        return new DecimalFormat(str).format(d);
    }

    private void saveCustomQuickPicks(LocalizedFluid localizedFluid) {
        boolean z;
        ArrayList<LocalizedFluid> arrayList = this.customQuickPicks;
        if (arrayList != null) {
            if (CollectionUtils.isEmpty((Collection<?>) arrayList)) {
                for (String tryParseFloat : getQuickPicksDefaultArray()) {
                    this.customQuickPicks.add(LocalizedFluid.from(this.waterUnit, Float.valueOf(NumberUtils.tryParseFloat(tryParseFloat))));
                }
            }
            if (localizedFluid.getValue(this.waterUnit) != 0.0d) {
                Iterator it = this.customQuickPicks.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                    if (Strings.equals(getCustomQuickPickString(Double.valueOf(localizedFluid.getValue(this.waterUnit))), getCustomQuickPickString(Double.valueOf(((LocalizedFluid) it.next()).getValue(this.waterUnit))))) {
                        z = true;
                        break;
                    }
                }
                if (!z) {
                    this.customQuickPicks.add(0, localizedFluid);
                    if (CollectionUtils.size((Collection<?>) this.customQuickPicks) > 3) {
                        this.customQuickPicks.remove(3);
                    }
                }
            }
            ((LocalSettingsService) this.localSettingsService.get()).setCustomWaterQuickPicks(this.session, this.customQuickPicks);
        }
    }

    private void setQuickPickButtonTextAndListener(Button button, String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append("+");
        sb.append(String.format(str, new Object[]{str2}));
        button.setText(sb.toString());
        setQuickPickButtonClickListener(button, str2);
    }

    private void setQuickPickButtonClickListener(Button button, final String str) {
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                float localeFloatFromString = NumberUtils.localeFloatFromString(str);
                WaterEntryActivity waterEntryActivity = WaterEntryActivity.this;
                waterEntryActivity.currentValue = waterEntryActivity.currentValue + localeFloatFromString;
                WaterEntryActivity waterEntryActivity2 = WaterEntryActivity.this;
                waterEntryActivity2.populateWaterValueEditText(NumberUtils.localeStringFromFloat(waterEntryActivity2.currentValue));
                ((WaterLoggingAnalyticsHelper) WaterEntryActivity.this.waterLoggingAnalyticsHelper.get()).reportWaterQuickOptionClick(WaterEntryActivity.this.waterUnit, Float.valueOf(WaterEntryActivity.this.currentValue));
            }
        });
    }

    private void populateWaterValueEditTextFromLocalizedFluid(LocalizedFluid localizedFluid) {
        String str;
        if (this.waterUnit == Water.CUPS) {
            str = NumberUtils.localeStringFromDouble(localizedFluid.getValue(Water.CUPS), 2);
        } else if (this.waterUnit == Water.FL_OZ) {
            str = NumberUtils.localeStringFromDouble(localizedFluid.getValue(Water.FL_OZ), 1);
        } else {
            str = NumberUtils.localeStringFromDouble(localizedFluid.getValue(Water.MILLILITERS), 0);
        }
        if (((double) NumberUtils.tryParseFloat(str)) > 0.0d) {
            populateWaterValueEditText(str);
        }
    }

    /* access modifiers changed from: private */
    public void populateWaterValueEditText(String str) {
        String customQuickPickString = getCustomQuickPickString(Double.valueOf(NumberUtils.tryParseDouble(str)));
        this.waterValue.setText("");
        this.waterValue.append(customQuickPickString);
    }

    private Map<String, String> getExtraAttributesMap() {
        Map<String, String> createMap = MapUtil.createMap(new String[0]);
        if (this.isSponsoredWaterBrandingEnabled) {
            createMap.put("sponsor", WaterSponsorshipUtil.getSponsorName((ConfigService) this.configService.get()));
        }
        return createMap;
    }

    private void saveWaterEntry() {
        if (this.mode == Mode.Add) {
            saveCustomQuickPicks(LocalizedFluid.from(this.waterUnit, Float.valueOf(NumberUtils.tryParseFloat(Strings.toString(this.waterValue.getText())))));
            this.currentValue = (float) (((double) this.currentValue) + this.localizedWater.getValue(this.waterUnit));
        }
        ((WaterLoggingAnalyticsHelper) this.waterLoggingAnalyticsHelper.get()).reportWaterLogged(this.waterUnit, Float.valueOf(this.currentValue), this.mode == Mode.Add ? "add" : "edit");
        ((DiaryService) this.diaryService.get()).getDiaryDayForActiveDateSync().setLocalizedWaterEntry(this.currentValue, this.waterUnit);
        getNavigationHelper().withClearTopAndSingleTop().withIntent(Diary.newStartIntent(this)).startActivity();
    }

    private void showAlexaInterstitialIfApplicable() {
        if (ConfigUtils.isAlexaInterstitialEnabled((ConfigService) this.configService.get()) && !((UserApplicationSettingsService) this.userApplicationSettingsService.get()).didSeeAlexaInterstitialForLogWater()) {
            getNavigationHelper().withIntent(AlexaInterstitialActivity.newInstance(this, com.myfitnesspal.feature.alexainterstitial.ui.activity.AlexaInterstitialActivity.Mode.LOG_WATER)).startActivity();
        }
    }

    @Subscribe
    public void onUnitDialogDismissedEvent(UnitDialogDismissedEvent unitDialogDismissedEvent) {
        if (!unitDialogDismissedEvent.isCancelled()) {
            this.preUnitChangeEntry = LocalizedFluid.from(this.preUnitChangeUnit, Float.valueOf(NumberUtils.localeFloatFromString(Strings.toString(this.waterValue.getText()))));
            populateUnitDependants();
            ((WaterLoggingAnalyticsHelper) this.waterLoggingAnalyticsHelper.get()).reportWaterUnitUpdated(WaterLoggingAnalyticsHelper.ADD_WATER_SCREEN, this.waterUnit);
        }
        getImmHelper().showSoftInput();
    }
}
