package com.myfitnesspal.feature.addentry.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.app.DialogFragment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.addentry.event.EditServingsDialogCloseEvent;
import com.myfitnesspal.feature.addentry.ui.dialog.EditServingsDialogFragment;
import com.myfitnesspal.feature.addentry.ui.extras.AddFoodExtrasBase;
import com.myfitnesspal.feature.addentry.util.EditableServing;
import com.myfitnesspal.feature.addentry.util.PairedFoodsHelper;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.diary.ui.activity.Diary;
import com.myfitnesspal.feature.foodfeedback.mixin.FoodFeedbackOptionsMixin;
import com.myfitnesspal.feature.foodfeedback.service.FoodFeedbackAnalyticsHelper;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.meals.ui.mixin.MealEditorMixin;
import com.myfitnesspal.feature.meals.util.MealUtil;
import com.myfitnesspal.feature.nutrition.ui.view.CustomDatePicker;
import com.myfitnesspal.feature.search.service.SearchService;
import com.myfitnesspal.feature.timestamp.mixin.TimestampPickerMixin;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper.FoodScreenType;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper.TimeValue;
import com.myfitnesspal.feature.timestamp.view.TimestampRowView;
import com.myfitnesspal.shared.api.request.FoodAnalyzerResponseData;
import com.myfitnesspal.shared.constants.Constants.ABTest.PairedFoodsAndroid201508;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.constants.Constants.Dialogs;
import com.myfitnesspal.shared.constants.Constants.Dialogs.Fragments;
import com.myfitnesspal.shared.constants.Constants.MealTypeName;
import com.myfitnesspal.shared.constants.Constants.Settings.App.URLs;
import com.myfitnesspal.shared.event.AlertEvent;
import com.myfitnesspal.shared.event.DialogCalendarEvent;
import com.myfitnesspal.shared.event.MealNameEvent;
import com.myfitnesspal.shared.model.AdUnit;
import com.myfitnesspal.shared.model.FoodV2Logging;
import com.myfitnesspal.shared.model.FoodV2Logging.Builder;
import com.myfitnesspal.shared.model.MealNames;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v1.FoodPortion;
import com.myfitnesspal.shared.model.v1.RecipeFood;
import com.myfitnesspal.shared.model.v2.MfpNutritionalContents;
import com.myfitnesspal.shared.model.v2.MfpServingSize;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.appindexer.AppIndexerClient;
import com.myfitnesspal.shared.service.appindexer.AppIndexerUriUtil;
import com.myfitnesspal.shared.service.appindexer.AppIndexerUriUtil.Source;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.foods.FoodService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogPositiveListener;
import com.myfitnesspal.shared.ui.dialog.impl.InvalidInputDialogFragment;
import com.myfitnesspal.shared.ui.dialog.impl.MealNamesDialogFragment;
import com.myfitnesspal.shared.ui.fragment.impl.DatePickerFragment;
import com.myfitnesspal.shared.ui.fragment.impl.NewNutritionFactsFragment;
import com.myfitnesspal.shared.ui.fragment.impl.NewNutritionFactsFragment.OnNutritionFactsExpandedOrCollapsedListener;
import com.myfitnesspal.shared.ui.navigation.SharedIntents;
import com.myfitnesspal.shared.ui.view.CustomDateDialog.OnDateSetListener;
import com.myfitnesspal.shared.ui.view.FoodInsightViewBinder;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.FoodMapperUtil;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.myfitnesspal.shared.util.MultiAddFoodHelper;
import com.myfitnesspal.shared.util.SnackbarBuilder;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;

public class AddFoodSummaryView extends MfpActivity implements EditableServing, OnDateSetListener {
    private static final String ERROR_DIALOG_TAG = "error_dialog";
    private static final String EXTRA_FROM_FEEDBACK = "extra_from_feedback";
    private static final String EXTRA_NEW_FOOD = "extra_new_food";
    private static final String NUTRITION_FACTS_TAG = "nutrition_facts_detailed";
    private static final int VARIABLE_ACTION_ITEM = 999;
    @Inject
    Lazy<ActionTrackingService> actionTrackingService;
    @BindView(2131361867)
    View addFoodSummaryRoot;
    @Inject
    Lazy<AnalyticsService> analyticsServiceLazy;
    private AppIndexerClient appIndexerClient;
    @Inject
    Lazy<ConfigService> configService;
    @Inject
    Lazy<CountryService> countryService;
    private FoodEntry currentFoodEntry;
    private Date date;
    @BindView(2131362274)
    View dateTableRow;
    @Inject
    Lazy<DiaryService> diaryService;
    private Food food;
    @Inject
    Lazy<FoodFeedbackAnalyticsHelper> foodFeedbackAnalyticsHelper;
    private FoodFeedbackOptionsMixin foodFeedbackOptionsMixin;
    private FoodPortion foodPortion;
    @Inject
    Lazy<FoodService> foodService;
    @Inject
    Lazy<ImageService> imageService;
    @BindView(2131362819)
    View insightContainer;
    private boolean isForEdit;
    private boolean isMealFoodCreationFlow;
    private boolean isPerformingAction;
    private String listType;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    private List<FoodV2Logging> loggedFood;
    @Inject
    Lazy<MealUtil> mealHelperUtil;
    private String mealName;
    private MealNames mealNames;
    @BindView(2131363014)
    View mealTableRow;
    private int menuResourceId = R.string.add;
    @Inject
    Lazy<MultiAddFoodHelper> multiAddFoodHelperLazy;
    @BindView(2131363151)
    View noOfServingsTableRow;
    private final OnNutritionFactsExpandedOrCollapsedListener nutritionFactsExpandedOrCollapsedListener = new OnNutritionFactsExpandedOrCollapsedListener() {
        public final void onNutritionFactsExpandedOrCollapsed(boolean z) {
            AddFoodSummaryView.lambda$new$6(AddFoodSummaryView.this, z);
        }
    };
    private NewNutritionFactsFragment nutritionFactsFragment;
    private PairedFoodsHelper pairedFoodsHelper;
    private String requestId;
    private int resultsListPosition;
    private String searchQuery;
    @Inject
    Lazy<SearchService> searchService;
    private int searchVersion;
    private int servingSizeIndex = 0;
    @BindView(2131363605)
    View servingSizeTableRow;
    private float servings;
    private String source;
    private TimestampPickerMixin timestampPickerMixin;
    @BindView(2131361868)
    TimestampRowView timestampRowView;
    @BindView(2131364002)
    TextView txtDate;
    @BindView(2131364008)
    TextView txtFoodName;
    @BindView(2131364019)
    TextView txtMeal;
    @BindView(2131364022)
    TextView txtNoOfServings;
    @BindView(2131364036)
    TextView txtServingSize;
    @Inject
    Lazy<UserEnergyService> userEnergyService;
    @BindView(2131364138)
    View verifiedBadge;

    public static class Extras extends AddFoodExtrasBase<Extras> {
        public static final Creator<Extras> CREATOR = new Creator<Extras>() {
            public Extras createFromParcel(Parcel parcel) {
                return new Extras(parcel);
            }

            public Extras[] newArray(int i) {
                return new Extras[i];
            }
        };
        private Food food;
        private FoodEntry foodEntry;
        private FoodPortion foodPortion;
        private boolean isForEdit;
        private String listType;

        public int describeContents() {
            return 0;
        }

        public Extras() {
        }

        private Extras(Parcel parcel) {
            super(parcel);
            this.food = (Food) parcel.readParcelable(Food.class.getClassLoader());
            this.foodPortion = (FoodPortion) parcel.readParcelable(FoodPortion.class.getClassLoader());
            this.listType = parcel.readString();
            this.foodEntry = (FoodEntry) parcel.readParcelable(FoodEntry.class.getClassLoader());
            this.isForEdit = parcel.readByte() != 0;
        }

        public Food getFood() {
            return this.food;
        }

        public Extras setFood(Food food2) {
            this.food = food2;
            return this;
        }

        public FoodPortion getFoodPortion() {
            return this.foodPortion;
        }

        public Extras setFoodPortion(FoodPortion foodPortion2) {
            this.foodPortion = foodPortion2;
            return this;
        }

        public String getListType() {
            return this.listType;
        }

        public Extras setListType(String str) {
            this.listType = str;
            return this;
        }

        public FoodEntry getFoodEntry() {
            return this.foodEntry;
        }

        public Extras setFoodEntry(FoodEntry foodEntry2) {
            this.foodEntry = foodEntry2;
            setFood(foodEntry2.getFood());
            setFoodPortion(foodEntry2.getFoodPortion());
            setServings(foodEntry2.getQuantity());
            return this;
        }

        public boolean isForEdit() {
            return this.isForEdit;
        }

        public Extras setForEdit(boolean z) {
            this.isForEdit = z;
            return this;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(this.food, i);
            parcel.writeParcelable(this.foodPortion, i);
            parcel.writeString(this.listType);
            parcel.writeParcelable(this.foodEntry, i);
            parcel.writeByte(this.isForEdit ? (byte) 1 : 0);
        }
    }

    public static Intent newStartIntent(Context context, Extras extras) {
        logStackTraceIfFoodOrFoodPortionAreNull(extras.getFood(), extras.getFoodPortion());
        return new Intent(context, AddFoodSummaryView.class).putExtra("extras", extras);
    }

    private static void logStackTraceIfFoodOrFoodPortionAreNull(Food food2, FoodPortion foodPortion2) {
        if (food2 == null || foodPortion2 == null) {
            String str = "Food null:%s or food portion null:%s";
            Object[] objArr = new Object[2];
            boolean z = false;
            objArr[0] = Strings.toString(Boolean.valueOf(food2 == null));
            if (foodPortion2 == null) {
                z = true;
            }
            objArr[1] = Strings.toString(Boolean.valueOf(z));
            Ln.e(new Throwable(String.format(str, objArr)).fillInStackTrace());
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        if (bundle == null) {
            ((FoodFeedbackAnalyticsHelper) this.foodFeedbackAnalyticsHelper.get()).resetFlowId();
        }
        this.mealNames = getSession().getUser().getMealNames();
        this.loggedFood = new ArrayList();
        setContentView((int) R.layout.new_add_food_summary_view);
        this.timestampPickerMixin = new TimestampPickerMixin((MfpActivity) this, ((DiaryService) this.diaryService.get()).getDiaryDayForActiveDateSync().getLatestEntryTimeForMealName(this.mealName), this.timestampRowView, this.food instanceof RecipeFood ? FoodScreenType.RECIPE : FoodScreenType.FOOD);
        registerMixin(this.timestampPickerMixin);
        this.foodFeedbackOptionsMixin = new FoodFeedbackOptionsMixin(this, this.addFoodSummaryRoot);
        registerMixin(this.foodFeedbackOptionsMixin);
        initSummaryView((Extras) BundleUtils.getParcelable(getIntent().getExtras(), "extras", new Extras(), Extras.class.getClassLoader()), bundle);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.appIndexerClient.saveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        this.appIndexerClient.end();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        unregisterMixin(this.foodFeedbackOptionsMixin.getClass());
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        recreateDateDialog();
    }

    public void hideSoftInput() {
        getImmHelper().hideSoftInput();
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!super.onPrepareOptionsMenu(menu)) {
            Ln.d("AddFoodSummaryView#super#onPrepareOptionsMenu returned false, bail", new Object[0]);
            return false;
        }
        MenuItem icon = menu.add(0, 999, 0, this.menuResourceId).setIcon(R.drawable.ic_check_white_24dp);
        Ln.d("AddFoodSummaryView set save item enabled = %s", Boolean.valueOf(!this.isPerformingAction));
        icon.setEnabled(!this.isPerformingAction);
        icon.setShowAsAction(2);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 999) {
            return super.onOptionsItemSelected(menuItem);
        }
        handleActionItemClick();
        return true;
    }

    public AdUnit getAdUnit() {
        return getAdUnitService().getAddOrEditFoodEntryScreenAdUnitValue();
    }

    public void initFoodData(float f) {
        ViewUtils.setVisible(this.food.isVerified(), this.verifiedBadge);
        this.servings = f;
        this.txtNoOfServings.setText(NumberUtils.localeStringFromFloat(f));
        this.txtServingSize.setText(this.foodPortion.descriptionWithAmount());
        this.servingSizeIndex = this.foodPortion.getWeightIndex();
        NewNutritionFactsFragment newNutritionFactsFragment = this.nutritionFactsFragment;
        if (newNutritionFactsFragment != null) {
            newNutritionFactsFragment.setMultiplier((double) getNutrientScale());
        }
    }

    public void onDateSet(CustomDatePicker customDatePicker, int i, int i2, int i3) {
        Calendar instance = Calendar.getInstance();
        instance.set(i, i2, i3);
        this.date = instance.getTime();
        this.txtDate.setText(DateTimeUtils.getNormalLocaleFormattedDate(instance.getTime()));
    }

    public String getAnalyticsScreenTag() {
        return this.isForEdit ? Screens.EDIT_FOOD_ENTRY : Screens.ADD_FOOD_ENTRY;
    }

    public Map<String, String> getAnalyticsScreenAttributes() {
        if (this.isForEdit) {
            return super.getAnalyticsScreenAttributes();
        }
        return MapUtil.createMap("flow_id", ((FoodFeedbackAnalyticsHelper) this.foodFeedbackAnalyticsHelper.get()).getFlowId(), Attributes.CORRECTED, ((FoodFeedbackAnalyticsHelper) this.foodFeedbackAnalyticsHelper.get()).getCorrectedBy(this.foodFeedbackOptionsMixin.getFoodFromFeedback()));
    }

    public Food getFood() {
        return this.food;
    }

    public FoodPortion getFoodPortion() {
        return this.foodPortion;
    }

    public void setFoodPortion(FoodPortion foodPortion2) {
        this.foodPortion = foodPortion2;
    }

    public float getServings() {
        return this.servings;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 207 || i2 != -1) {
            return;
        }
        if (intent != null) {
            Extras extras = (Extras) getIntent().getParcelableExtra("extras");
            extras.setFood((Food) BundleUtils.getParcelable(intent.getExtras(), EXTRA_NEW_FOOD, Food.class.getClassLoader()));
            initSummaryView(extras, null);
            this.foodFeedbackOptionsMixin.setFoodFromFeedback(true);
            showSnackBar(R.string.thanks_for_feedback_save);
            return;
        }
        showSnackBar(R.string.thanks_for_feedback);
    }

    @Subscribe
    public void onMealNameEvent(MealNameEvent mealNameEvent) {
        setMealName(Strings.toString(mealNameEvent.getMealName()));
    }

    @Subscribe
    public void onDateSetFromDatePicker(DialogCalendarEvent dialogCalendarEvent) {
        if (dialogCalendarEvent != null) {
            Calendar calendar = dialogCalendarEvent.getCalendar();
            if (calendar != null) {
                onDateSet(null, calendar.get(1), calendar.get(2), calendar.get(5));
            }
        }
    }

    @Subscribe
    public void onEditServingsDialogCloseEvent(EditServingsDialogCloseEvent editServingsDialogCloseEvent) {
        MfpServingSize servingSize = editServingsDialogCloseEvent.getServingSize();
        this.txtNoOfServings.setText(NumberUtils.localeStringFromFloat(editServingsDialogCloseEvent.getNumOfServings()));
        this.txtServingSize.setText(servingSize.descriptionWithAmount());
        this.servingSizeIndex = editServingsDialogCloseEvent.getServingSizeIndexForLogging();
        NewNutritionFactsFragment newNutritionFactsFragment = this.nutritionFactsFragment;
        if (newNutritionFactsFragment != null) {
            newNutritionFactsFragment.setMultiplier(servingSize.getNutritionMultiplier().doubleValue() * ((double) editServingsDialogCloseEvent.getNumOfServings()));
        }
    }

    public void showDialogFragment(int i) {
        if (i != 7623) {
            switch (i) {
                case Dialogs.EDIT_SERVINGS_DIALOG /*7605*/:
                    showEditServingsDialogFragment(true);
                    return;
                case Dialogs.MEAL_NAMES_DIALOG /*7606*/:
                    showDialogFragment(MealNamesDialogFragment.newInstance(), Fragments.MEAL_NAMES_DIALOG);
                    return;
                case Dialogs.INVALID_INPUT /*7607*/:
                    showDialogFragment(InvalidInputDialogFragment.newInstance(), Fragments.INVALID_INPUT);
                    return;
                default:
                    return;
            }
        } else {
            showEditServingsDialogFragment(false);
        }
    }

    private void initSummaryView(Extras extras, Bundle bundle) {
        initSharedFields(extras);
        if (this.isForEdit) {
            initForEditMode(extras);
        } else {
            initForAddMode(extras);
        }
        Food food2 = this.food;
        if (food2 != null) {
            this.foodFeedbackOptionsMixin.setMfpFood(FoodMapperUtil.mapV1FoodToMfpFood(food2));
        }
        initAppIndexing(bundle);
        initUi(extras, bundle);
    }

    private void showEditServingsDialogFragment(boolean z) {
        showDialogFragment(EditServingsDialogFragment.newInstance(z), Fragments.EDIT_SERVINGS_DIALOG);
    }

    private void initSharedFields(Extras extras) {
        this.isForEdit = extras.isForEdit();
        this.isMealFoodCreationFlow = extras.isMealFoodCreationFlow();
        setMealName(extras.getMealName());
        this.date = extras.getDate();
        this.resultsListPosition = extras.getPosition();
        this.listType = extras.getListType();
        this.searchQuery = extras.getQuery();
        this.source = extras.getSource();
        this.requestId = extras.getRequestId();
        this.searchVersion = extras.getSearchVersion();
    }

    private void initForEditMode(Extras extras) {
        this.menuResourceId = R.string.save;
        this.food = extras.getFood();
        this.currentFoodEntry = extras.getFoodEntry();
        this.foodPortion = extras.getFoodPortion();
        this.timestampPickerMixin.showCurrentTimestamp(this.currentFoodEntry.getEntryTime());
    }

    private void initForAddMode(Extras extras) {
        this.food = extras.getFood();
        this.foodPortion = extras.getFoodPortion();
        this.menuResourceId = ((MultiAddFoodHelper) this.multiAddFoodHelperLazy.get()).isMultiAddModeOn() ? R.string.add_to_checked : R.string.add;
        if (shouldShowPairedFoods()) {
            Food food2 = this.food;
            if (food2 != null) {
                PairedFoodsHelper pairedFoodsHelper2 = new PairedFoodsHelper(food2.getOriginalId(), this.mealNames.mealIdForName(this.mealName), this.food.getOriginalUid(), this.imageService, this.userEnergyService, this.mealHelperUtil, this.localizedStringsUtil, this.multiAddFoodHelperLazy, this.searchService, this.analyticsServiceLazy);
                this.pairedFoodsHelper = pairedFoodsHelper2;
                this.pairedFoodsHelper.addPairedFoods(this);
            }
        }
        if (DateTimeUtils.isEmpty(this.date)) {
            ViewUtils.setVisible(true, this.dateTableRow);
            this.date = Calendar.getInstance().getTime();
        }
        if (this.isMealFoodCreationFlow) {
            ViewUtils.setGone(this.mealTableRow);
            ViewUtils.setGone(this.timestampRowView);
        }
        this.txtDate.setText(DateTimeUtils.getNormalLocaleFormattedDate(this.date));
    }

    private void initAppIndexing(Bundle bundle) {
        this.appIndexerClient = new AppIndexerClient(this, (ConfigService) this.configService.get(), getIntent(), bundle);
        Food food2 = this.food;
        if (food2 != null && food2.isNormalFood() && this.food.isPublic()) {
            this.appIndexerClient.start(AppIndexerUriUtil.getFoodTitle(this.food, (LocalizedStringsUtil) this.localizedStringsUtil.get(), (UserEnergyService) this.userEnergyService.get()), AppIndexerUriUtil.foodToUri(this.food, Source.AutoComplete));
        }
    }

    private void initAsError() {
        AlertDialogFragment alertDialogFragment = (AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) new AlertDialogFragment().setTitle(R.string.app_name)).setMessage((int) R.string.failed_to_load_app_data)).setPositiveText(R.string.ok, new DialogPositiveListener() {
            public final void onClick(Object obj) {
                AddFoodSummaryView.this.finish();
            }
        });
        alertDialogFragment.setCancelable(false);
        showDialogFragment(alertDialogFragment, ERROR_DIALOG_TAG);
    }

    private void initTitleAndFoodName(Extras extras) {
        setTitle(extras.getTitle());
        TextView textView = this.txtFoodName;
        Food food2 = this.food;
        textView.setText(food2 != null ? food2.brandAndDescription() : "");
    }

    private void initUi(Extras extras, Bundle bundle) {
        initTitleAndFoodName(extras);
        initInsightsUi();
        if (this.food == null || this.foodPortion == null) {
            initAsError();
            return;
        }
        this.foodFeedbackOptionsMixin.showReportFood(!this.isForEdit);
        initFoodData(extras.getServings());
        initNutritionFacts(bundle);
        initUiEventListeners();
    }

    private void initNutritionFacts(Bundle bundle) {
        if (bundle == null) {
            this.nutritionFactsFragment = NewNutritionFactsFragment.newInstance(MfpNutritionalContents.fromFood(this.food), 0, this.pairedFoodsHelper != null ? 0 : 1, (double) getNutrientScale());
            getSupportFragmentManager().beginTransaction().replace(R.id.nutrition_facts_container, this.nutritionFactsFragment, NUTRITION_FACTS_TAG).commit();
            this.nutritionFactsFragment.setNutritionFactsExpandedOrCollapsedListener(this.nutritionFactsExpandedOrCollapsedListener);
        }
    }

    private void initUiEventListeners() {
        this.noOfServingsTableRow.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                AddFoodSummaryView.this.showDialogFragment(Dialogs.EDIT_SERVINGS_DIALOG);
            }
        });
        this.servingSizeTableRow.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                AddFoodSummaryView.this.showDialogFragment(Dialogs.EDIT_SERVINGS_DIALOG_NO_KEYBOARD);
            }
        });
        this.mealTableRow.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                AddFoodSummaryView.this.showDialogFragment(Dialogs.MEAL_NAMES_DIALOG);
            }
        });
        this.dateTableRow.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                AddFoodSummaryView.this.showDateDialog(Calendar.getInstance());
            }
        });
        this.verifiedBadge.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                AddFoodSummaryView.this.getNavigationHelper().withIntent(SharedIntents.newUriIntent(URLs.GOLD_FOOD_LEARN_MORE)).startActivity();
            }
        });
    }

    /* access modifiers changed from: private */
    public void showDateDialog(Calendar calendar) {
        showDialogFragment(DatePickerFragment.newInstance(calendar), DatePickerFragment.class.getName());
    }

    private void recreateDateDialog() {
        DialogFragment dialogFragment = (DialogFragment) getSupportFragmentManager().findFragmentByTag(DatePickerFragment.class.getName());
        if (dialogFragment != null) {
            dialogFragment.dismiss();
            showDateDialog(((DatePickerFragment) dialogFragment).getSelectedDate());
        }
    }

    private void updateEntry() {
        buildFoodEntry(false);
        ((DiaryService) this.diaryService.get()).getDiaryDayForActiveDateSync().updateFoodEntry(this.currentFoodEntry);
        setResult(-1);
        finish();
    }

    private void addFoodEntry() {
        int i = 1;
        buildFoodEntry(true);
        if (this.isMealFoodCreationFlow) {
            if (!((MultiAddFoodHelper) this.multiAddFoodHelperLazy.get()).isMultiAddModeOn()) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(this.currentFoodEntry);
                if (shouldShowPairedFoods()) {
                    arrayList.addAll(getPairedFoodEntries());
                }
                Intent intent = new Intent();
                intent.putExtra(MealEditorMixin.EXTRA_FOOD_ENTRIES, arrayList);
                setResult(-1, intent);
                finish();
            } else {
                setResult(-1);
            }
            finish();
            return;
        }
        getSession().getUser().setActiveDate(this.date);
        DiaryDay diaryDayForActiveDateSync = ((DiaryService) this.diaryService.get()).getDiaryDayForActiveDateSync();
        Food food2 = this.currentFoodEntry.getFood();
        this.currentFoodEntry.setMasterDatabaseId(0);
        diaryDayForActiveDateSync.setJustAddedPrimaryText(food2.getDescription());
        diaryDayForActiveDateSync.addFoodEntry(this.currentFoodEntry);
        ((LocalSettingsService) this.localSettingsService.get()).removeRecentsDeletedFoodOriginalUid(food2.getOriginalUid());
        if (shouldShowPairedFoods()) {
            addSelectPairedFoodEntries();
        }
        getIntent().putExtra("source", this.source);
        addFoodToLoggedFoodList(food2, this.resultsListPosition);
        String[] strArr = new String[22];
        strArr[0] = "flow_id";
        strArr[1] = ((FoodFeedbackAnalyticsHelper) this.foodFeedbackAnalyticsHelper.get()).getFlowId();
        strArr[2] = "meal";
        strArr[3] = Strings.toString(this.mealName).toLowerCase();
        strArr[4] = "locale";
        strArr[5] = ((CountryService) this.countryService.get()).getCurrentLocaleIdentifierForV2();
        strArr[6] = "source";
        strArr[7] = this.source;
        strArr[8] = Attributes.DIARY_DATE;
        strArr[9] = DateTimeUtils.diaryDateAnalyticsFormat(diaryDayForActiveDateSync.getDate());
        strArr[10] = "foods";
        strArr[11] = new ApiJsonMapper().reverseMap((Object) this.loggedFood);
        strArr[12] = "list_type";
        strArr[13] = this.listType;
        strArr[14] = Attributes.RECIPE_COUNT;
        if (!(food2 instanceof RecipeFood)) {
            i = 0;
        }
        strArr[15] = Strings.toString(Integer.valueOf(i));
        strArr[16] = Attributes.CONTAINS_FOOD_AD;
        strArr[17] = Strings.toString(Boolean.valueOf(FoodV2Logging.listContainsAdFood(this.loggedFood)));
        strArr[18] = Attributes.CORRECTED;
        strArr[19] = ((FoodFeedbackAnalyticsHelper) this.foodFeedbackAnalyticsHelper.get()).getCorrectedBy(this.foodFeedbackOptionsMixin.getFoodFromFeedback());
        strArr[20] = "version";
        strArr[21] = Strings.toString(Integer.valueOf(this.searchVersion));
        Map createMap = MapUtil.createMap(strArr);
        if (this.timestampPickerMixin.isFeatureEnabled()) {
            createMap.put(TimestampAnalyticsHelper.ATTR_TIME, TimeValue.Companion.fromTimestampOption(this.timestampPickerMixin.getSelectedOption()).getAnalyticsName());
        }
        ((DiaryService) this.diaryService.get()).endFoodLoggingFlow(createMap);
        reportAddFoodEntry();
        Ln.i("Food added", new Object[0]);
        getNavigationHelper().setResult(-1).finishActivityAfterNavigation().withClearTopAndSingleTop().withIntent(Diary.newStartIntentWithReferrerAndForceHomeOnBack(this, com.myfitnesspal.shared.constants.Constants.Extras.REFERRER_DIARY_JUST_LOGGED)).startActivity();
    }

    private void reportAddFoodEntry() {
        if (Strings.toBoolean(((ActionTrackingService) this.actionTrackingService.get()).getTrackingDataForEvent("is_last_pressed_search", "is_last_pressed_search"))) {
            ((ActionTrackingService) this.actionTrackingService.get()).appendToEvent(Events.ONLINE_SEARCH_SUMMARY, MapUtil.createMap(Attributes.LOGGED, "yes", "flow_id", ((FoodFeedbackAnalyticsHelper) this.foodFeedbackAnalyticsHelper.get()).getFlowId()));
        }
    }

    private void addSelectPairedFoodEntries() {
        List<FoodEntry> pairedFoodEntries = getPairedFoodEntries();
        int size = CollectionUtils.size((Collection<?>) pairedFoodEntries);
        if (size > 0) {
            DiaryDay diaryDayForActiveDateSync = ((DiaryService) this.diaryService.get()).getDiaryDayForActiveDateSync();
            for (FoodEntry foodEntry : pairedFoodEntries) {
                foodEntry.setMasterDatabaseId(0);
                diaryDayForActiveDateSync.addFoodEntry(foodEntry);
            }
            reportPairedFoodsLogged(size);
        }
    }

    private List<FoodEntry> getPairedFoodEntries() {
        ArrayList arrayList = new ArrayList();
        PairedFoodsHelper pairedFoodsHelper2 = this.pairedFoodsHelper;
        if (pairedFoodsHelper2 == null) {
            return arrayList;
        }
        Set<DiaryEntryCellModel> selectedPairedFoods = pairedFoodsHelper2.getSelectedPairedFoods();
        if (CollectionUtils.notEmpty((Collection<?>) selectedPairedFoods)) {
            for (DiaryEntryCellModel diaryEntryCellModel : selectedPairedFoods) {
                if (diaryEntryCellModel instanceof FoodEntry) {
                    FoodEntry foodEntry = (FoodEntry) diaryEntryCellModel;
                    foodEntry.setMealName(this.mealName);
                    foodEntry.setDate(getCurrentDate());
                    foodEntry.setEntryTimeAndUpdateLoggedAt(this.timestampPickerMixin.getTimestamp());
                    arrayList.add(foodEntry);
                }
            }
        }
        return arrayList;
    }

    private void reportPairedFoodsLogged(int i) {
        getAnalyticsService().reportEvent(Events.PAIRED_FOOD_LOGGED, MapUtil.createMap(Attributes.NUMBER_OF_FOODS_LOGGED, Integer.toString(i)));
    }

    private void buildFoodEntry(boolean z) {
        FoodEntry foodEntry;
        if (z) {
            try {
                foodEntry = new FoodEntry();
            } catch (Exception e) {
                Ln.e(e);
                return;
            }
        } else {
            foodEntry = this.currentFoodEntry;
        }
        foodEntry.setFood(this.food);
        foodEntry.setFoodPortion(this.foodPortion);
        foodEntry.setWeightIndex(this.foodPortion.getWeightIndex());
        foodEntry.setQuantity(this.servings);
        foodEntry.setMealName(this.mealName);
        foodEntry.setDate(getCurrentDate());
        foodEntry.setIsFraction(this.foodPortion.getIsFraction());
        foodEntry.setEntryTimeAndUpdateLoggedAt(this.timestampPickerMixin.getTimestamp());
        foodEntry.clearCachedData();
        if (z) {
            this.currentFoodEntry = foodEntry;
        }
    }

    private void handleActionItemClick() {
        try {
            this.isPerformingAction = true;
            supportInvalidateOptionsMenu();
            actionItemClick();
        } finally {
            this.isPerformingAction = false;
            supportInvalidateOptionsMenu();
        }
    }

    private void actionItemClick() {
        if (NumberUtils.tryParseDouble(Strings.toString(this.txtNoOfServings.getText())) == 0.0d) {
            postEvent(new AlertEvent(getString(R.string.enter_valid_number_servings)));
            return;
        }
        if (this.isForEdit) {
            getAnalyticsService().reportEvent(Events.EDITFOOD_SAVEBTN_CLICK);
            updateEntry();
        } else {
            otherActionItemClick();
        }
    }

    private void otherActionItemClick() {
        MultiAddFoodHelper multiAddFoodHelper = (MultiAddFoodHelper) this.multiAddFoodHelperLazy.get();
        if (multiAddFoodHelper.isMultiAddModeOn()) {
            buildFoodEntry(true);
            this.currentFoodEntry.setMasterDatabaseId(0);
            int weightIndex = this.currentFoodEntry.getWeightIndex();
            if (multiAddFoodHelper.isMultiAddModeOn()) {
                multiAddFoodHelper.addAndLogItem(this.currentFoodEntry, Builder.fromFood(this.food).searchTerm(ExtrasUtils.getString(getIntent(), "query")).index(this.resultsListPosition).servingSizeIndex(weightIndex).source(this.source).requestId(this.requestId).listType(this.listType).build());
            }
            if (shouldShowPairedFoods()) {
                Set<DiaryEntryCellModel> selectedPairedFoods = this.pairedFoodsHelper.getSelectedPairedFoods();
                if (CollectionUtils.notEmpty((Collection<?>) selectedPairedFoods)) {
                    for (DiaryEntryCellModel addItem : selectedPairedFoods) {
                        multiAddFoodHelper.addItem(addItem);
                    }
                    reportPairedFoodsLogged(selectedPairedFoods.size());
                }
            }
            Intent intent = new Intent();
            intent.putExtra(com.myfitnesspal.shared.constants.Constants.Extras.FOOD_ORIGINAL_UID, this.food.getOriginalUid());
            intent.putExtra("food_uid", this.food.getUid());
            intent.putExtra("food_verified", this.food.isVerified());
            intent.putExtra("serving_size_index", weightIndex);
            intent.putExtra("position", this.resultsListPosition);
            setResult(-1, intent);
            finish();
            return;
        }
        getAnalyticsService().reportEvent(Events.ADDFOOD_ADDBTN_CLICK);
        addFoodEntry();
    }

    private void initInsightsUi() {
        ViewUtils.setVisible(false, this.insightContainer);
        FoodAnalyzerResponseData foodAnalyzerResponseData = (FoodAnalyzerResponseData) ExtrasUtils.getParcelable(getIntent(), com.myfitnesspal.shared.constants.Constants.Extras.FOOD_ANALYZER_DATA, FoodAnalyzerResponseData.class.getClassLoader());
        if (foodAnalyzerResponseData != null) {
            new FoodInsightViewBinder(this.insightContainer, this.foodService, getMessageBus(), this.currentFoodEntry).setFoodAnalyzerData(foodAnalyzerResponseData, false);
        }
    }

    private void setMealName(String str) {
        this.mealName = str;
        if (Strings.isEmpty(this.mealName)) {
            ViewUtils.setVisible(true, this.mealTableRow);
            if (this.mealNames.notEmpty()) {
                this.mealName = (String) this.mealNames.getNames().get(0);
            }
            if (Strings.isEmpty(this.mealName)) {
                this.mealName = MealTypeName.BREAKFAST;
            }
        }
        this.txtMeal.setText(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getMealNameString(this.mealName, (UserEnergyService) this.userEnergyService.get()));
    }

    private void addFoodToLoggedFoodList(Food food2, int i) {
        this.loggedFood.clear();
        this.loggedFood.add(Builder.fromFood(food2).searchTerm(this.searchQuery).index(i).servingSizeIndex(this.servingSizeIndex).source(this.source).listType(this.listType).build());
    }

    private void showSnackBar(int i) {
        new SnackbarBuilder(this.addFoodSummaryRoot).setMessage(i).setDuration(0).show();
    }

    private Date getCurrentDate() {
        return getSession().getUser().getActiveDate();
    }

    private float getNutrientScale() {
        return this.servings * this.food.nutrientMultiplierForFoodPortion(this.foodPortion);
    }

    private boolean shouldShowPairedFoods() {
        return ((ConfigService) this.configService.get()).isVariantEnabled(PairedFoodsAndroid201508.NAME);
    }

    public static /* synthetic */ void lambda$new$6(AddFoodSummaryView addFoodSummaryView, boolean z) {
        ScrollView scrollView = (ScrollView) addFoodSummaryView.findViewById(R.id.scroll_parent);
        if (!z && scrollView != null) {
            scrollView.smoothScrollTo(0, 0);
        }
    }
}
