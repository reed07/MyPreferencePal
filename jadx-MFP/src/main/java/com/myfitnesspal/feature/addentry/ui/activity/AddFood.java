package com.myfitnesspal.feature.addentry.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import com.facebook.stetho.common.ListUtil;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.addentry.event.AddCustomFoodNextStepEvent;
import com.myfitnesspal.feature.addentry.ui.dialog.AddCustomFoodImprovementDialogFragment;
import com.myfitnesspal.feature.barcode.event.BarcodeScanSuccessEvent;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.diary.ui.activity.Diary;
import com.myfitnesspal.feature.search.service.FoodSearchAnalyticsHelper;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Dialogs.Fragments;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.event.MealNameEvent;
import com.myfitnesspal.shared.model.FoodV2Logging.Builder;
import com.myfitnesspal.shared.model.mapper.impl.FoodMapper;
import com.myfitnesspal.shared.model.mapper.impl.MfpFoodMapper;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v1.NutritionalValues;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.impl.MealNamesDialogFragment;
import com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin;
import com.myfitnesspal.shared.ui.view.CustomLocalizedNumberEditText;
import com.myfitnesspal.shared.util.InputMethodManagerHelper;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import com.uacf.sync.engine.UacfScheduler;
import dagger.Lazy;
import javax.inject.Inject;

public class AddFood extends MfpActivity {
    protected static final int ACTION_NEXT = 989;
    protected static final int ACTION_SAVE = 990;
    private static final int BRAND_NAME_MAX_LENGTH = 50;
    private static final int DESCRIPTION_MAX_LENGTH = 100;
    public static final String EXTRA_CREATE_DIARY_ENTRY = "extra_do_not_create_food_entry";
    public static final String EXTRA_FLOW_ID = "flow_id";
    protected static final int MINIMUM_EMPTY_FIELDS = 2;
    private static final int PAGE_BASIC = 1;
    private static final int PAGE_NUTRITION = 2;
    private static final String REFERRER_ID_ADD_ENTRY = "add_entry";
    private View _basicInfo = null;
    private View _nutritionalInfo = null;
    @Inject
    Lazy<ActionTrackingService> actionTrackingService;
    private CharSequence alertMsg;
    @Inject
    Lazy<AppSettings> appSettings;
    private String barcode;
    private TextView brandNameTxt;
    private TextView caloriesEditText;
    private TextView carbsEditText;
    @Inject
    Lazy<CountryService> countryService;
    private Food customFood;
    @Inject
    Lazy<DbConnectionManager> dbConnectionManager;
    private TextView descriptionTxt;
    @Inject
    Lazy<DiaryService> diaryService;
    private EditText editTxtBrandName;
    private EditText editTxtDescription;
    private EditText editTxtServingsPerContainer;
    private TextView fatEditText;
    @Inject
    MfpFoodMapper foodObjectToFoodV2;
    @Inject
    Lazy<FoodSearchAnalyticsHelper> foodSearchAnalyticsHelper;
    @Inject
    FoodMapper foodToFoodObject;
    private Boolean hasAskedForDoubleCheck;
    @Inject
    Lazy<InputMethodManagerHelper> inputMethodManagerHelper;
    private String mealName;
    private int numberOfEmptyFields;
    private NutritionalValues nutritionValues;
    int page;
    private TextView proteinEditText;
    private String referrer;
    private boolean requireMacros;
    private Resources resources;
    private boolean scannedEntry;
    private AutoCompleteTextView servingSizeAutoTxt;
    private EditText servingSizeQuantity;
    private boolean shouldShowCompleteDialog;
    @Inject
    Lazy<UacfScheduler<SyncType>> syncScheduler;
    @Inject
    Lazy<UserEnergyService> userEnergyService;

    public static Intent newStartIntent(Context context) {
        return newStartIntent(context, null, false);
    }

    public static Intent newStartIntent(Context context, boolean z) {
        return newStartIntent(context, null, z);
    }

    public static Intent newStartIntent(Context context, String str, boolean z) {
        return new Intent(context, AddFood.class).putExtra(Extras.GO_TO_DIARY, z).putExtra(Extras.MEAL_NAME, str);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        this.resources = getResources();
        setResult(0);
        Intent intent = getIntent();
        this.mealName = ExtrasUtils.getString(intent, Extras.MEAL_NAME);
        this.referrer = ExtrasUtils.getString(intent, "referrer", "unknown");
        this.barcode = ExtrasUtils.getString(intent, "barcode");
        this.scannedEntry = ExtrasUtils.getBoolean(intent, Extras.SCANNED_ENRTY);
        this.requireMacros = ExtrasUtils.getBoolean(intent, Extras.REQUIRE_MACROS);
        this._basicInfo = View.inflate(this, R.layout.add_food_basic_info_new, null);
        this._nutritionalInfo = View.inflate(this, R.layout.add_food_nutritional_info, null);
        setPage(1);
        setTitle(getString(R.string.createFood));
        this.editTxtBrandName = (EditText) ViewUtils.findById(this._basicInfo, R.id.editTxtBrandName);
        this.editTxtDescription = (EditText) ViewUtils.findById(this._basicInfo, R.id.editTxtDescription);
        this.brandNameTxt = (TextView) ViewUtils.findById(this._basicInfo, R.id.brandName);
        this.descriptionTxt = (TextView) ViewUtils.findById(this._basicInfo, R.id.description);
        this.editTxtServingsPerContainer = (EditText) ViewUtils.findById(this._basicInfo, R.id.editTxtServingsPerContainer);
        this.caloriesEditText = (TextView) ViewUtils.findById(this._nutritionalInfo, R.id.editTxtCalories);
        this.fatEditText = (TextView) ViewUtils.findById(this._nutritionalInfo, R.id.editTxtTotalFat);
        this.carbsEditText = (TextView) ViewUtils.findById(this._nutritionalInfo, R.id.editTxtTotalCarbohydrates);
        this.proteinEditText = (TextView) ViewUtils.findById(this._nutritionalInfo, R.id.editTxtProtein);
        TextView textView = (TextView) ViewUtils.findById(this._basicInfo, R.id.couldnt_match);
        TextView textView2 = (TextView) ViewUtils.findById(this._basicInfo, R.id.help_community);
        this.servingSizeAutoTxt = (AutoCompleteTextView) ViewUtils.findById(this._basicInfo, R.id.servingSizeAutoTxt);
        this.servingSizeQuantity = (EditText) ViewUtils.findById(this._basicInfo, R.id.editTxtServingSizeQuantity);
        this.nutritionValues = new NutritionalValues();
        this.nutritionValues.initAsBlank();
        ((ActionTrackingService) this.actionTrackingService.get()).registerEvent(Events.CREATE_FOOD_SUMMARY, MapUtil.createMap("country", ((CountryService) this.countryService.get()).getCurrentCountry().getLongName(), Attributes.LOGGED, "false", "completed", "false", Attributes.FOOD_BASIC_INFO, "false", "macros", "false", Attributes.MICROS, "false"));
        boolean z = !Strings.isEmpty(this.barcode);
        ViewUtils.setVisible(z, textView);
        ViewUtils.setVisible(z, textView2);
        if (z) {
            ((ActionTrackingService) this.actionTrackingService.get()).appendToEvent(Events.CREATE_FOOD_SUMMARY, "source", Attributes.BARCODE_MISS);
        }
        int i = this.requireMacros ? R.string.requiredLabel : R.string.optionalField;
        this.carbsEditText.setHint(i);
        this.proteinEditText.setHint(i);
        this.fatEditText.setHint(i);
        this.brandNameTxt.setText(!this.requireMacros ? R.string.brandNameOptional : R.string.brandNameRequired);
        ((ActionTrackingService) this.actionTrackingService.get()).appendToEvent(Events.CREATE_FOOD_SUMMARY, "channel", this.referrer);
        this.shouldShowCompleteDialog = true;
        this.hasAskedForDoubleCheck = Boolean.valueOf(false);
        getImmHelper().showSoftInput();
        this.servingSizeAutoTxt.setThreshold(1);
        this.servingSizeAutoTxt.setAdapter(new ArrayAdapter(this, 17367050, getResources().getStringArray(R.array.serving_sizes_measures)));
        this.servingSizeAutoTxt.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                AddFood.this.getAnalyticsService().reportEvent(Events.FOOD_CREATION_FLOW_SUGGESTION_SELECTED);
            }
        });
    }

    public void onUpPressed() {
        if (this.page == 2) {
            setPage(1);
            return;
        }
        ((ActionTrackingService) this.actionTrackingService.get()).reportEventToAnalytics(Events.CREATE_FOOD_SUMMARY, Events.CREATE_FOOD_SUMMARY, true);
        super.onUpPressed();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        getImmHelper().hideSoftInput();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        if (this.page == 2) {
            setPage(1);
            return true;
        }
        ((ActionTrackingService) this.actionTrackingService.get()).reportEventToAnalytics(Events.CREATE_FOOD_SUMMARY, Events.CREATE_FOOD_SUMMARY, true);
        return super.onKeyDown(i, keyEvent);
    }

    private void setPage(int i) {
        this.page = i;
        switch (i) {
            case 1:
                View view = this._basicInfo;
                removeParentAndSetContentView(view, view.findViewById(R.id.editTxtBrandName));
                sendCreateFoodScreenEvent(Events.CREATE_FOOD_SCREEN_ONE);
                return;
            case 2:
                View view2 = this._nutritionalInfo;
                removeParentAndSetContentView(view2, view2.findViewById(R.id.editTxtCalories));
                ((TextView) this._nutritionalInfo.findViewById(R.id.calories)).setText(getString(((UserEnergyService) this.userEnergyService.get()).getCurrentEnergyStringId()));
                sendCreateFoodScreenEvent(Events.CREATE_FOOD_SCREEN_TWO);
                return;
            default:
                return;
        }
    }

    private void removeParentAndSetContentView(View view, View view2) {
        ((InputMethodManagerHelper) this.inputMethodManagerHelper.get()).hideSoftInput((Activity) this);
        ViewUtils.removeViewFromParent(view);
        setContentView(view);
        view.post(new Runnable(view2) {
            private final /* synthetic */ View f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                AddFood.lambda$removeParentAndSetContentView$0(AddFood.this, this.f$1);
            }
        });
        supportInvalidateOptionsMenu();
    }

    public static /* synthetic */ void lambda$removeParentAndSetContentView$0(AddFood addFood, View view) {
        view.requestFocus();
        ((InputMethodManagerHelper) addFood.inputMethodManagerHelper.get()).showSoftInput();
    }

    public void createCustomFood() {
        try {
            float localeFloatFromString = NumberUtils.localeFloatFromString(Strings.toString(this.editTxtServingsPerContainer.getText()));
            String string = getString(R.string.two_strings_with_space_format, new Object[]{this.servingSizeQuantity.getText(), this.servingSizeAutoTxt.getText()});
            this.customFood = Food.createCustomFoodWithDescription(Strings.toString(this.editTxtDescription.getText()), Strings.toString(this.editTxtBrandName.getText()), string, localeFloatFromString, this.nutritionValues, null, this.barcode, getSession(), (DbConnectionManager) this.dbConnectionManager.get());
            this.editTxtBrandName.requestFocus();
        } catch (NumberFormatException e) {
            Ln.e(e);
        }
    }

    private void addCustomFood() {
        createCustomFood();
        if (getIntent().getExtras().getBoolean(EXTRA_CREATE_DIARY_ENTRY, true)) {
            addFoodEntryForCustomFood();
        }
        ((UacfScheduler) this.syncScheduler.get()).schedulePeriodicSyncIfNecessary();
        MfpFood mfpFood = (MfpFood) this.foodObjectToFoodV2.tryMapFrom(this.foodToFoodObject.tryMapFrom(this.customFood));
        Intent putExtra = new Intent().putExtra("food", mfpFood).putExtra("barcode", this.barcode);
        if (ExtrasUtils.getBoolean(getIntent(), Extras.GO_TO_DIARY)) {
            ((FoodSearchAnalyticsHelper) this.foodSearchAnalyticsHelper.get()).reportFoodLogged(this.mealName, ExtrasUtils.getString(getIntent(), "flow_id"), Extras.REFERRER_ADD_FOOD, ListUtil.newImmutableList(Builder.fromMfpFood(mfpFood).build()), 2, false);
            getNavigationHelper().setResult(-1, putExtra).finishActivityAfterNavigation().withClearTopAndSingleTop().withIntent(Diary.newStartIntentWithReferrerAndForceHomeOnBack(this, Extras.REFERRER_DIARY_JUST_LOGGED)).startActivity();
        } else {
            setResult(-1, putExtra);
            finish();
        }
        ((ActionTrackingService) this.actionTrackingService.get()).appendToEvent(Events.CREATE_FOOD_SUMMARY, "completed", "true");
        ((ActionTrackingService) this.actionTrackingService.get()).reportEventToAnalytics(Events.CREATE_FOOD_SUMMARY, Events.CREATE_FOOD_SUMMARY);
        reportMacroAndMicroCount();
    }

    private void addFoodEntryForCustomFood() {
        String nameForIndex = Strings.isEmpty(this.mealName) ? getSession().getUser().getMealNames().nameForIndex(0) : this.mealName;
        FoodEntry foodEntry = new FoodEntry();
        foodEntry.setFood(this.customFood);
        foodEntry.setDate(getSession().getUser().getActiveDate());
        foodEntry.setMealName(nameForIndex);
        foodEntry.setQuantity(1.0f);
        foodEntry.setFoodPortion(this.customFood.getFoodPortions()[0]);
        foodEntry.setFraction(false);
        ((DiaryService) this.diaryService.get()).getDiaryDayForActiveDateSync().addFoodEntry(foodEntry);
        ((ActionTrackingService) this.actionTrackingService.get()).appendToEvent(Events.CREATE_FOOD_SUMMARY, Attributes.LOGGED, "true");
    }

    private void populateNutritionalValues() {
        try {
            this.numberOfEmptyFields = 0;
            this.nutritionValues.setNutrientIndex(0, ((UserEnergyService) this.userEnergyService.get()).getCalories(Strings.toString(((CustomLocalizedNumberEditText) this._nutritionalInfo.findViewById(R.id.editTxtCalories)).getText())));
            readEditTextValue(1, R.id.editTxtTotalFat);
            readEditTextValue(2, R.id.editTxtSaturatedFat);
            readEditTextValue(3, R.id.editTxtPolyunsaturatedFat);
            readEditTextValue(4, R.id.editTxtMonounsaturatedFat);
            readEditTextValue(5, R.id.editTxtTransFat);
            readEditTextValue(6, R.id.editTxtCholesterol);
            readEditTextValue(7, R.id.editTxtSodium);
            readEditTextValue(8, R.id.editTxtPotassium);
            readEditTextValue(9, R.id.editTxtTotalCarbohydrates);
            readEditTextValue(10, R.id.editTxtDietaryFibers);
            readEditTextValue(11, R.id.editTxtSugars);
            readEditTextValue(12, R.id.editTxtProtein);
            readEditTextValue(13, R.id.editTxtVitaminA);
            readEditTextValue(14, R.id.editTxtVitaminC);
            readEditTextValue(15, R.id.editTxtCalcium);
            readEditTextValue(16, R.id.editTxtIron);
            if (this.numberOfEmptyFields <= 12) {
                ((ActionTrackingService) this.actionTrackingService.get()).appendToEvent(Events.CREATE_FOOD_SUMMARY, Attributes.MICROS, "true");
            }
            if (!this.shouldShowCompleteDialog || this.numberOfEmptyFields < 2) {
                buildAndAddCustomFood();
            } else {
                getAnalyticsService().reportEvent(this.scannedEntry ? Events.FOOD_CREATION_BARCODE_FLOW_ADD_NUTRIENTS_ALERT_SHOWN : Events.FOOD_CREATION_MANUAL_FLOW_ADD_NUTRIENTS_ALERT_SHOWN);
                AddCustomFoodImprovementDialogFragment.newInstance(this.scannedEntry).show(getSupportFragmentManager(), Fragments.ADD_CUSTOM_FOOD_IMPROVEMENT_DIALOG);
                this.shouldShowCompleteDialog = false;
            }
            getImmHelper().hideSoftInput();
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    private boolean validateBasicInfoFields() {
        String strings = Strings.toString(this.editTxtBrandName.getText());
        if ((Strings.length(strings) == 0 || Strings.length(strings) > 50) && this.requireMacros) {
            return showAlertAndReturnFalse(Events.CREATE_FOOD_SCREEN_BRAND_ALERT, this.resources.getText(R.string.enterBrandMsg));
        }
        if (!Strings.isEmpty(strings) && Strings.isEmpty(Strings.trimmed((Object) strings))) {
            return showAlertAndReturnFalse(Events.CREATE_FOOD_SCREEN_BRAND_ALERT, this.resources.getText(R.string.enter_valid_brand_msg));
        }
        String trimmed = Strings.trimmed((Object) this.editTxtDescription.getText());
        if (Strings.length(trimmed) == 0 || Strings.length(trimmed) > 100) {
            return showAlertAndReturnFalse(Events.CREATE_FOOD_SCREEN_DESCRIPTION_ALERT, this.resources.getText(R.string.enter_valid_description_msg));
        }
        if (((DbConnectionManager) this.dbConnectionManager.get()).foodDbAdapter().checkForExistingFoodWithDescription(trimmed, strings.trim(), Boolean.valueOf(true), Boolean.valueOf(true), 1, getSession().getUser().getLocalId(), this.appSettings)) {
            this.alertMsg = this.resources.getString(R.string.nameExistsMsg);
            return false;
        }
        String strings2 = Strings.toString(this.servingSizeQuantity.getText());
        String trimmed2 = Strings.trimmed((Object) this.servingSizeAutoTxt.getText());
        if (Strings.isEmpty(strings2) || NumberUtils.tryParseFloat(strings2) <= BitmapDescriptorFactory.HUE_RED || !isValidServingSizeName(trimmed2)) {
            return showAlertAndReturnFalse(Events.CREATE_FOOD_SCREEN_SERVING_SIZE_ALERT, getText(R.string.enterValidServingSizeFormat));
        }
        String strings3 = Strings.toString(this.editTxtServingsPerContainer.getText());
        if (Strings.isEmpty(strings3) || NumberUtils.tryParseFloat(strings3) <= BitmapDescriptorFactory.HUE_RED) {
            return showAlertAndReturnFalse(Events.CREATE_FOOD_SCREEN_SERVINGS_PER_CONTAINER_ALERT, getText(R.string.enterServingsPerContainerMsg));
        }
        try {
            float localeFloatFromString = NumberUtils.localeFloatFromString(strings3.trim());
            if (localeFloatFromString <= BitmapDescriptorFactory.HUE_RED || localeFloatFromString > 999.0f) {
                return showAlertAndReturnFalse(Events.CREATE_FOOD_SCREEN_SERVINGS_PER_CONTAINER_ALERT, getText(R.string.enterServingsPerContainerMsg));
            }
            return true;
        } catch (Exception unused) {
            return showAlertAndReturnFalse(Events.CREATE_FOOD_SCREEN_SERVINGS_PER_CONTAINER_ALERT, getText(R.string.enterServingsPerContainerMsg));
        }
    }

    private boolean showAlertAndReturnFalse(String str, CharSequence charSequence) {
        sendCreateFoodScreenEvent(str);
        this.alertMsg = charSequence;
        return false;
    }

    private boolean isValidServingSizeName(String str) {
        if (Strings.isEmpty(str)) {
            return false;
        }
        try {
            Double.valueOf(str);
            return false;
        } catch (NumberFormatException unused) {
            return true;
        }
    }

    private void sendCreateFoodScreenEvent(String str) {
        getAnalyticsService().reportEvent(str, new MapUtil.Builder().put("source", this.requireMacros ? "barcode" : "food_search").build());
    }

    private boolean validateNutritionalInfoFields(int i, String str) {
        String strings = Strings.toString(((CustomLocalizedNumberEditText) this._nutritionalInfo.findViewById(i)).getText());
        if (Strings.isEmpty(strings)) {
            this.alertMsg = getString(R.string.enter_value_in_all_fields);
            return false;
        }
        try {
            NumberUtils.localeFloatFromString(strings.trim());
            return true;
        } catch (Exception unused) {
            this.alertMsg = getString(R.string.enter_value_in_all_fields);
            return false;
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        if (this.page == 2) {
            MenuItemCompat.setShowAsAction(menu.add(0, ACTION_SAVE, 0, R.string.save), 2);
        } else {
            MenuItemCompat.setShowAsAction(menu.add(0, ACTION_NEXT, 0, R.string.next), 2);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case ACTION_NEXT /*989*/:
                if (validateBasicInfoFields()) {
                    ((ActionTrackingService) this.actionTrackingService.get()).appendToEvent(Events.CREATE_FOOD_SUMMARY, Attributes.FOOD_BASIC_INFO, "true");
                    setPage(2);
                } else {
                    ((LegacyAlertMixin) mixin(LegacyAlertMixin.class)).showAlertDialogWithTitle(getString(R.string.error), this.alertMsg, getString(R.string.dismiss));
                }
                return true;
            case ACTION_SAVE /*990*/:
                if (!validateNutritionalInfoFields(R.id.editTxtCalories, null) || (this.requireMacros && (!validateNutritionalInfoFields(R.id.editTxtTotalCarbohydrates, getString(R.string.total_carbs)) || !validateNutritionalInfoFields(R.id.editTxtProtein, getString(R.string.protein)) || !validateNutritionalInfoFields(R.id.editTxtTotalFat, getString(R.string.totalFatTxt))))) {
                    ((LegacyAlertMixin) mixin(LegacyAlertMixin.class)).showAlertDialogWithTitle(getString(R.string.error), this.alertMsg, getString(R.string.dismiss));
                } else if (!this.requireMacros || validateCalorieCount() || this.hasAskedForDoubleCheck.booleanValue()) {
                    ((ActionTrackingService) this.actionTrackingService.get()).appendToEvent(Events.CREATE_FOOD_SUMMARY, "macros", "true");
                    populateNutritionalValues();
                } else {
                    this.hasAskedForDoubleCheck = Boolean.valueOf(true);
                    ((LegacyAlertMixin) mixin(LegacyAlertMixin.class)).showAlertDialogWithTitle(getString(R.string.somethings_wrong), getString(R.string.inaccurate_food_info), getString(R.string.okay));
                }
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    private boolean validateCalorieCount() {
        Double valueOf = Double.valueOf(((NumberUtils.tryParseDouble(Strings.toString(this.carbsEditText.getText())) + NumberUtils.tryParseDouble(Strings.toString(this.proteinEditText.getText()))) * 4.0d) + (NumberUtils.tryParseDouble(Strings.toString(this.fatEditText.getText())) * 9.0d));
        if (NumberUtils.tryParseDouble(Strings.toString(this.caloriesEditText.getText())) <= valueOf.doubleValue() * 1.1d && NumberUtils.tryParseDouble(Strings.toString(this.caloriesEditText.getText())) >= valueOf.doubleValue() * 0.9d) {
            return true;
        }
        getAnalyticsService().reportEvent(Events.BARCODE_CREATE_FOOD_MACROS_CHECK_FAIL);
        return false;
    }

    private boolean showMealNamesDialog() {
        return Strings.equalsIgnoreCase(this.referrer, REFERRER_ID_ADD_ENTRY);
    }

    private void readEditTextValue(int i, int i2) {
        float f;
        String strings = Strings.toString(((CustomLocalizedNumberEditText) this._nutritionalInfo.findViewById(i2)).getText());
        try {
            f = Strings.isEmpty(strings) ? -1.0f : NumberUtils.localeFloatFromString(strings);
        } catch (NumberFormatException unused) {
            f = -1.0f;
        }
        if (f == -1.0f) {
            this.numberOfEmptyFields++;
        }
        this.nutritionValues.setNutrientIndex(i, f);
    }

    private void buildAndAddCustomFood() {
        if (this.scannedEntry) {
            getMessageBus().post(new BarcodeScanSuccessEvent(this.referrer));
        }
        if (showMealNamesDialog()) {
            MealNamesDialogFragment.newInstance().show(getSupportFragmentManager(), Fragments.MEAL_NAMES_DIALOG);
        } else {
            addCustomFood();
        }
    }

    private void reportMacroAndMicroCount() {
        getAnalyticsService().reportEvent(this.requireMacros ? Events.BARCODE_CREATE_FOOD_LOGGED : Events.MANUAL_CREATE_FOOD_LOGGED, new MapUtil.Builder().put(Attributes.NUMBER_OF_MACROS, Strings.toString(Integer.valueOf((Strings.notEmpty((Object) ((EditText) ViewUtils.findById(this._nutritionalInfo, R.id.editTxtTotalFat)).getText()) ? 1 : 0) + false + (Strings.notEmpty((Object) ((EditText) ViewUtils.findById(this._nutritionalInfo, R.id.editTxtCalories)).getText()) ? 1 : 0) + (Strings.notEmpty((Object) ((EditText) ViewUtils.findById(this._nutritionalInfo, R.id.editTxtTotalCarbohydrates)).getText()) ? 1 : 0) + (Strings.notEmpty((Object) ((EditText) ViewUtils.findById(this._nutritionalInfo, R.id.editTxtProtein)).getText()) ? 1 : 0)))).put(Attributes.NUMBER_OF_MICROS, Strings.toString(Integer.valueOf((Strings.notEmpty((Object) ((EditText) ViewUtils.findById(this._nutritionalInfo, R.id.editTxtSaturatedFat)).getText()) ? 1 : 0) + false + (Strings.notEmpty((Object) ((EditText) ViewUtils.findById(this._nutritionalInfo, R.id.editTxtPolyunsaturatedFat)).getText()) ? 1 : 0) + (Strings.notEmpty((Object) ((EditText) ViewUtils.findById(this._nutritionalInfo, R.id.editTxtMonounsaturatedFat)).getText()) ? 1 : 0) + (Strings.notEmpty((Object) ((EditText) ViewUtils.findById(this._nutritionalInfo, R.id.editTxtTransFat)).getText()) ? 1 : 0) + (Strings.notEmpty((Object) ((EditText) ViewUtils.findById(this._nutritionalInfo, R.id.editTxtCholesterol)).getText()) ? 1 : 0) + (Strings.notEmpty((Object) ((EditText) ViewUtils.findById(this._nutritionalInfo, R.id.editTxtSodium)).getText()) ? 1 : 0) + (Strings.notEmpty((Object) ((EditText) ViewUtils.findById(this._nutritionalInfo, R.id.editTxtPotassium)).getText()) ? 1 : 0) + (Strings.notEmpty((Object) ((EditText) ViewUtils.findById(this._nutritionalInfo, R.id.editTxtDietaryFibers)).getText()) ? 1 : 0) + (Strings.notEmpty((Object) ((EditText) ViewUtils.findById(this._nutritionalInfo, R.id.editTxtSugars)).getText()) ? 1 : 0) + (Strings.notEmpty((Object) ((EditText) ViewUtils.findById(this._nutritionalInfo, R.id.editTxtVitaminA)).getText()) ? 1 : 0) + (Strings.notEmpty((Object) ((EditText) ViewUtils.findById(this._nutritionalInfo, R.id.editTxtVitaminC)).getText()) ? 1 : 0) + (Strings.notEmpty((Object) ((EditText) ViewUtils.findById(this._nutritionalInfo, R.id.editTxtCalcium)).getText()) ? 1 : 0) + (Strings.notEmpty((Object) ((EditText) ViewUtils.findById(this._nutritionalInfo, R.id.editTxtIron)).getText()) ? 1 : 0)))).put(Attributes.SERVING_SIZE_NAME, Strings.toString(this.servingSizeAutoTxt.getText())).build());
    }

    @Subscribe
    public void onMealNameEvent(MealNameEvent mealNameEvent) {
        String mealName2 = mealNameEvent.getMealName();
        if (!Strings.notEmpty(mealName2)) {
            mealName2 = "";
        }
        this.mealName = mealName2;
        addCustomFood();
    }

    @Subscribe
    public void onAddCustomFoodNextStepEvent(AddCustomFoodNextStepEvent addCustomFoodNextStepEvent) {
        buildAndAddCustomFood();
    }
}
