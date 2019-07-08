package com.myfitnesspal.feature.foodeditor.ui.mixin.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.barcode.service.BarcodeService;
import com.myfitnesspal.feature.barcode.ui.activity.BarcodeMatchActivity;
import com.myfitnesspal.feature.barcode.ui.activity.BarcodeMatchActivity.StartMode;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorActivity;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorType;
import com.myfitnesspal.feature.foodeditor.ui.event.BasedOnViewClickEvent;
import com.myfitnesspal.feature.foodeditor.ui.mixin.EditorMixinBase;
import com.myfitnesspal.feature.foodeditor.ui.mixin.EditorMixinBase.OnItemSavedListener;
import com.myfitnesspal.feature.foodeditor.ui.service.FoodEditorAnalytics;
import com.myfitnesspal.feature.foodfeedback.mixin.FoodFeedbackOptionsMixin;
import com.myfitnesspal.feature.foodfeedback.service.FoodFeedbackAnalyticsHelper;
import com.myfitnesspal.feature.meals.ui.mixin.MealEditorMixin;
import com.myfitnesspal.feature.search.model.SearchSource;
import com.myfitnesspal.feature.search.service.SearchService;
import com.myfitnesspal.feature.timestamp.mixin.TimestampPickerMixin;
import com.myfitnesspal.feature.timestamp.view.TimestampRowView;
import com.myfitnesspal.framework.mixin.RunnerLifecycleMixin;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.constants.Constants.Dialogs.Fragments;
import com.myfitnesspal.shared.constants.Constants.MealTypeName;
import com.myfitnesspal.shared.injection.component.ApplicationComponent;
import com.myfitnesspal.shared.model.FoodEditorItem;
import com.myfitnesspal.shared.model.MealNames;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpNutritionalContents;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.activity.BusEventHandler;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogNegativeListener;
import com.myfitnesspal.shared.ui.dialog.impl.MealNamesDialogFragment;
import com.myfitnesspal.shared.ui.dialog.impl.MealNamesDialogFragment.OnMealSelectedListener;
import com.myfitnesspal.shared.ui.fragment.impl.DatePickerFragment;
import com.myfitnesspal.shared.ui.fragment.impl.DatePickerFragment.OnDateSelectedListener;
import com.myfitnesspal.shared.ui.fragment.impl.NewNutritionFactsFragment;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.FoodMapperUtil;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.myfitnesspal.shared.util.SnackbarBuilder;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class FoodEditorMixinBase<T extends FoodEditorItem> extends EditorMixinBase<T> implements BusEventHandler {
    private static final String DATE_PICKER_TAG = "date_picker";
    private static final int DEFAULT_VALUE_SEARCH_VERSION = 1;
    public static final String EXTRA_BARCODE = "extra_barcode";
    public static final String EXTRA_DATE = "extra_date";
    public static final String EXTRA_EDITOR_EXTRAS = "extra_editor_extras";
    public static final String EXTRA_FOOD = "extra_food";
    public static final String EXTRA_IS_EDITING_MEAL_INGREDIENT = "extra_is_editing_meal_ingredient";
    public static final String EXTRA_MEAL_INGREDIENT_INDEX = "extra_meal_ingredient_index";
    public static final String EXTRA_MEAL_NAME = "extra_meal_name";
    private static final String EXTRA_NEW_FOOD = "extra_new_food";
    public static final String EXTRA_REFERRER = "extra_referrer";
    public static final String EXTRA_REQUEST_ID = "request_id";
    public static final String EXTRA_SEARCH_VERSION = "search_version";
    public static final String EXTRA_SOURCE = "source";
    private static final String GENERAL_ERROR_DIALOG_TAG = "general_error_dialog";
    private static final String NUTRITION_FACTS_TAG = "nutrition_facts";
    /* access modifiers changed from: protected */
    public final MfpActivity activity;
    /* access modifiers changed from: private */
    public String barcode;
    Lazy<BarcodeService> barcodeService;
    @BindView(2131362633)
    protected TextView basedOnTextView;
    @BindView(2131362632)
    protected View basedOnView;
    @BindView(2131362244)
    protected TextView ctaButton;
    @BindView(2131362245)
    protected View ctaContainer;
    @BindView(2131362246)
    protected TextView ctaText;
    /* access modifiers changed from: private */
    public Date date;
    @BindView(2131362274)
    protected View dateRowView;
    @BindView(2131364002)
    protected TextView dateTextView;
    /* access modifiers changed from: protected */
    public Lazy<DiaryService> diaryService;
    @BindView(2131362548)
    protected FloatingActionButton fabActionPlus;
    @BindView(2131362634)
    protected View foodDescRowView;
    @BindView(2131364007)
    protected TextView foodDescriptionTextView;
    Lazy<FoodEditorAnalytics> foodEditorAnalytics;
    protected Lazy<FoodFeedbackAnalyticsHelper> foodFeedbackAnalyticsHelper;
    protected FoodFeedbackOptionsMixin foodFeedbackOptionsMixin;
    @BindView(2131364008)
    protected TextView foodNameTextView;
    @BindView(2131362620)
    protected ViewGroup headerContainer;
    /* access modifiers changed from: protected */
    @BindView(2131362816)
    public EditText inputMealName;
    @BindView(2131362817)
    protected View inputMealNameContainer;
    @BindView(2131362819)
    protected ViewGroup insightsContainer;
    protected final Intent intent;
    Lazy<LocalSettingsService> localSettingsService;
    protected Lazy<LocalizedStringsUtil> localizedStringsUtil;
    @BindView(2131363024)
    protected View mealItemsContainer;
    @BindView(2131363025)
    protected ViewGroup mealItemsList;
    /* access modifiers changed from: private */
    public String mealName;
    @BindView(2131364019)
    protected TextView mealNameTextView;
    private MealNames mealNames;
    @BindView(2131363037)
    protected ViewGroup mealNotesContainer;
    @BindView(2131363014)
    protected View mealRowView;
    @BindView(2131363151)
    protected View noOfServingsRowView;
    @BindView(2131364022)
    protected TextView noOfServingsTextView;
    /* access modifiers changed from: private */
    public NutritionDetailsMode nutritionDetailsMode = NutritionDetailsMode.Closed;
    private NewNutritionFactsFragment nutritionFactsFragment;
    private OnClickListener onBasedOnRowClickListener = new OnClickListener() {
        public void onClick(View view) {
            FoodEditorMixinBase.this.activity.postEvent(new BasedOnViewClickEvent());
        }
    };
    private OnClickListener onCtaButtonClickListener = new OnClickListener() {
        public void onClick(View view) {
            if (AnonymousClass5.$SwitchMap$com$myfitnesspal$feature$foodeditor$ui$mixin$impl$FoodEditorMixinBase$NutritionDetailsMode[FoodEditorMixinBase.this.nutritionDetailsMode.ordinal()] == 1) {
                FoodEditorMixinBase.this.activity.startActivityForResult(BarcodeMatchActivity.createStartIntent(FoodEditorMixinBase.this.activity, null, null, FoodEditorMixinBase.this.mealName, FoodEditorMixinBase.this.barcode, FoodEditorMixinBase.this.referrer, StartMode.BetterMatch), 55);
            }
        }
    };
    private OnClickListener onDateClickListener = new OnClickListener() {
        public void onClick(View view) {
            DatePickerFragment newInstance = DatePickerFragment.newInstance(FoodEditorMixinBase.this.date);
            newInstance.setListener(FoodEditorMixinBase.this.onDateSelectedListener);
            FoodEditorMixinBase.this.activity.showDialogFragment(newInstance, FoodEditorMixinBase.DATE_PICKER_TAG);
        }
    };
    /* access modifiers changed from: private */
    public OnDateSelectedListener onDateSelectedListener = new OnDateSelectedListener() {
        public final void onDateSelected(Calendar calendar) {
            FoodEditorMixinBase.lambda$new$3(FoodEditorMixinBase.this, calendar);
        }
    };
    /* access modifiers changed from: protected */
    public OnItemSavedListener onItemSavedListener;
    private OnClickListener onMealClickListener = new OnClickListener() {
        public void onClick(View view) {
            MealNamesDialogFragment newInstance = MealNamesDialogFragment.newInstance();
            newInstance.setListener(FoodEditorMixinBase.this.onMealSelectedListener);
            FoodEditorMixinBase.this.activity.showDialogFragment(newInstance, Fragments.MEAL_NAMES_DIALOG);
        }
    };
    /* access modifiers changed from: private */
    public OnMealSelectedListener onMealSelectedListener = new OnMealSelectedListener() {
        public final void onMealSelected(String str) {
            FoodEditorMixinBase.this.setMealName(str);
        }
    };
    private OnClickListener onNoOfServingsRowClickListener = new OnClickListener() {
        public final void onClick(View view) {
            FoodEditorMixinBase.this.onServingsCountClick();
        }
    };
    private OnClickListener onServingSizeRowClickListener = new OnClickListener() {
        public final void onClick(View view) {
            FoodEditorMixinBase.this.onServingSizeClick();
        }
    };
    protected final View parentView;
    /* access modifiers changed from: private */
    public String referrer;
    protected String requestId;
    Lazy<SearchService> searchService;
    protected int searchVersion;
    @BindView(2131363605)
    protected View servingSizeRowView;
    @BindView(2131364036)
    protected TextView servingSizeTextView;
    protected Lazy<Session> session;
    private SearchSource source;
    @BindView(2131363703)
    protected ViewGroup sponsoredFoodRow;
    protected TimestampPickerMixin timestampPickerMixin;
    @BindView(2131363830)
    protected TimestampRowView timestampRowView;
    protected Lazy<UserEnergyService> userEnergyService;
    @BindView(2131364138)
    protected ImageView verifiedBadge;

    /* renamed from: com.myfitnesspal.feature.foodeditor.ui.mixin.impl.FoodEditorMixinBase$5 reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$myfitnesspal$feature$foodeditor$ui$mixin$impl$FoodEditorMixinBase$NutritionDetailsMode = new int[NutritionDetailsMode.values().length];

        static {
            try {
                $SwitchMap$com$myfitnesspal$feature$foodeditor$ui$mixin$impl$FoodEditorMixinBase$NutritionDetailsMode[NutritionDetailsMode.BarcodeMatch.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    protected enum NutritionDetailsMode {
        Open,
        Closed,
        BarcodeMatch
    }

    public String getAnalyticsScreenTag() {
        return Screens.ADD_FOOD_ENTRY;
    }

    public abstract String getFoodItemName();

    public abstract float getNutrientScale();

    /* access modifiers changed from: protected */
    public void onNewFoodSelected(MfpFood mfpFood) {
    }

    /* access modifiers changed from: protected */
    public void onServingSizeClick() {
    }

    /* access modifiers changed from: protected */
    public void onServingsCountClick() {
    }

    public FoodEditorMixinBase(MfpActivity mfpActivity, OnItemSavedListener onItemSavedListener2, Intent intent2, Bundle bundle, View view) {
        RunnerLifecycleMixin[] runnerLifecycleMixinArr;
        super(mfpActivity);
        ButterKnife.bind((Object) this, view);
        injectDependencies();
        this.activity = mfpActivity;
        this.intent = intent2;
        this.parentView = view;
        this.onItemSavedListener = onItemSavedListener2;
        this.mealNames = ((Session) this.session.get()).getUser().getMealNames();
        Bundle extras = intent2.getExtras();
        String string = BundleUtils.getString(EXTRA_MEAL_NAME, (String) null, bundle, extras);
        ViewUtils.setVisible(extras.get(EXTRA_MEAL_NAME) == null, this.mealRowView);
        setMealName(string);
        Date date2 = (Date) BundleUtils.getSerializable("extra_date", null, Date.class.getClassLoader(), bundle, extras);
        ViewUtils.setVisible(extras.get("extra_date") == null, this.dateRowView);
        setDate(date2);
        this.barcode = BundleUtils.getString(EXTRA_BARCODE, (String) null, bundle, extras);
        this.referrer = BundleUtils.getString(EXTRA_REFERRER, (String) null, bundle, extras);
        this.source = (SearchSource) BundleUtils.getSerializable("source", null, SearchSource.class.getClassLoader(), bundle, extras);
        this.requestId = BundleUtils.getString("request_id", (String) null, bundle, extras);
        this.searchVersion = BundleUtils.getInt(EXTRA_SEARCH_VERSION, Integer.valueOf(1), bundle, extras).intValue();
        this.nutritionFactsFragment = (NewNutritionFactsFragment) mfpActivity.getSupportFragmentManager().findFragmentByTag(NUTRITION_FACTS_TAG);
        Date latestEntryTimeForMealName = ((DiaryService) this.diaryService.get()).getDiaryDayForActiveDateSync().getLatestEntryTimeForMealName(string);
        try {
            this.timestampPickerMixin = (TimestampPickerMixin) mfpActivity.mixin(TimestampPickerMixin.class);
            if (!this.timestampPickerMixin.equalTo(latestEntryTimeForMealName, this.timestampPickerMixin.getLatestEntryTimestamp())) {
                this.timestampPickerMixin.setLatestEntryTimestamp(latestEntryTimeForMealName);
            }
        } catch (IllegalArgumentException unused) {
            this.timestampPickerMixin = new TimestampPickerMixin(mfpActivity, ((DiaryService) this.diaryService.get()).getDiaryDayForActiveDateSync().getLatestEntryTimeForMealName(string), this.timestampRowView);
            mfpActivity.registerMixin(this.timestampPickerMixin);
        }
        try {
            this.foodFeedbackOptionsMixin = (FoodFeedbackOptionsMixin) mfpActivity.mixin(FoodFeedbackOptionsMixin.class);
            mfpActivity.unregisterMixin(this.foodFeedbackOptionsMixin.getClass());
            this.foodFeedbackOptionsMixin = new FoodFeedbackOptionsMixin(mfpActivity, view, (FoodEditorType) BundleUtils.getSerializable(extras, FoodEditorActivity.EXTRA_FOOD_EDITOR_TYPE, FoodEditorType.class.getClassLoader()));
            runnerLifecycleMixinArr = new RunnerLifecycleMixin[]{this.foodFeedbackOptionsMixin};
        } catch (IllegalArgumentException unused2) {
            Ln.e("Mixin not found. Creating a new mixin", new Object[0]);
            this.foodFeedbackOptionsMixin = new FoodFeedbackOptionsMixin(mfpActivity, view, (FoodEditorType) BundleUtils.getSerializable(extras, FoodEditorActivity.EXTRA_FOOD_EDITOR_TYPE, FoodEditorType.class.getClassLoader()));
            runnerLifecycleMixinArr = new RunnerLifecycleMixin[]{this.foodFeedbackOptionsMixin};
        } catch (Throwable th) {
            this.foodFeedbackOptionsMixin = new FoodFeedbackOptionsMixin(mfpActivity, view, (FoodEditorType) BundleUtils.getSerializable(extras, FoodEditorActivity.EXTRA_FOOD_EDITOR_TYPE, FoodEditorType.class.getClassLoader()));
            mfpActivity.registerMixin(this.foodFeedbackOptionsMixin);
            throw th;
        }
        mfpActivity.registerMixin(runnerLifecycleMixinArr);
        ((FoodFeedbackAnalyticsHelper) this.foodFeedbackAnalyticsHelper.get()).resetFlowId();
        initCtaArea(this.foodFeedbackOptionsMixin.getFoodFromFeedback());
        initListeners();
    }

    public void onActivityResult(int i, int i2, Intent intent2) {
        processActivityResult(i, i2, intent2);
    }

    public boolean processActivityResult(int i, int i2, Intent intent2) {
        if (i == 55) {
            if (i2 == -1) {
                MfpFood mfpFood = (MfpFood) BundleUtils.getParcelable(intent2.getExtras(), "food", MfpFood.class.getClassLoader());
                if (mfpFood != null) {
                    onNewFoodSelected(mfpFood);
                }
            }
            return true;
        } else if (i != 207) {
            return super.processActivityResult(i, i2, intent2);
        } else {
            if (i2 == -1) {
                if (intent2 != null) {
                    MfpFood mapV1FoodToMfpFood = FoodMapperUtil.mapV1FoodToMfpFood((Food) BundleUtils.getParcelable(intent2.getExtras(), EXTRA_NEW_FOOD, Food.class.getClassLoader()));
                    if (mapV1FoodToMfpFood != null) {
                        onNewFoodSelected(mapV1FoodToMfpFood);
                        this.foodFeedbackOptionsMixin.setFoodFromFeedback(true);
                        initCtaArea(this.foodFeedbackOptionsMixin.getFoodFromFeedback());
                        new SnackbarBuilder(this.parentView).setMessage((int) R.string.thanks_for_feedback_save).setDuration(0).show();
                    }
                } else {
                    new SnackbarBuilder(this.parentView).setMessage((int) R.string.thanks_for_feedback).setDuration(0).show();
                }
            }
            return true;
        }
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (Fragments.MEAL_NAMES_DIALOG.equals(str)) {
            ((MealNamesDialogFragment) dialogFragment).setListener(this.onMealSelectedListener);
            return true;
        } else if (DATE_PICKER_TAG.equals(str)) {
            ((DatePickerFragment) dialogFragment).setListener(this.onDateSelectedListener);
            return true;
        } else if (!GENERAL_ERROR_DIALOG_TAG.equals(str)) {
            return false;
        } else {
            ((AlertDialogFragment) dialogFragment).setNegativeText(R.string.cancel, new DialogNegativeListener() {
                public final void onClick() {
                    FoodEditorMixinBase.this.getMfpActivity().finish();
                }
            });
            return true;
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable("extra_date", this.date);
        bundle.putString(EXTRA_MEAL_NAME, this.mealName);
    }

    public Map<String, String> getAnalyticsScreenAttributes() {
        return MapUtil.createMap("flow_id", ((FoodFeedbackAnalyticsHelper) this.foodFeedbackAnalyticsHelper.get()).getFlowId(), Attributes.CORRECTED, ((FoodFeedbackAnalyticsHelper) this.foodFeedbackAnalyticsHelper.get()).getCorrectedBy(this.foodFeedbackOptionsMixin.getFoodFromFeedback()));
    }

    /* access modifiers changed from: protected */
    public final void onItemSaved(MfpFood mfpFood) {
        onItemSaved(mfpFood, -1, null);
    }

    /* access modifiers changed from: protected */
    public final void onItemSaved(@Nullable MfpFood mfpFood, int i, Bundle bundle) {
        associateBarcodeWithFood(mfpFood);
        this.onItemSavedListener.onItemSaved(i, bundle);
    }

    /* access modifiers changed from: protected */
    public final void onItemSaveFailed() {
        onItemSaveFailed(0, null);
    }

    /* access modifiers changed from: protected */
    public final void onItemSaveFailed(int i, Bundle bundle) {
        this.onItemSavedListener.onItemSaveFailed(i, bundle);
    }

    /* access modifiers changed from: protected */
    public void setNutritionDetailsMode(NutritionDetailsMode nutritionDetailsMode2) {
        this.nutritionDetailsMode = nutritionDetailsMode2;
    }

    /* access modifiers changed from: protected */
    public final void renderNutritionDetails(MfpNutritionalContents mfpNutritionalContents) {
        float nutrientScale = getNutrientScale();
        NewNutritionFactsFragment newNutritionFactsFragment = this.nutritionFactsFragment;
        if (newNutritionFactsFragment == null) {
            this.nutritionFactsFragment = getNutritionFactsFragment(mfpNutritionalContents, this.nutritionDetailsMode == NutritionDetailsMode.Open ? 1 : 0, nutrientScale);
            this.activity.getSupportFragmentManager().beginTransaction().add(R.id.nutrition_facts_container, this.nutritionFactsFragment, NUTRITION_FACTS_TAG).commit();
            return;
        }
        newNutritionFactsFragment.setMultiplier((double) nutrientScale);
        this.nutritionFactsFragment.setNutritionalContents(mfpNutritionalContents);
    }

    /* access modifiers changed from: protected */
    public NewNutritionFactsFragment getNutritionFactsFragment(MfpNutritionalContents mfpNutritionalContents, int i, float f) {
        return NewNutritionFactsFragment.newInstance(mfpNutritionalContents, 0, i, (double) f);
    }

    /* access modifiers changed from: protected */
    public void associateBarcodeWithFood(@Nullable MfpFood mfpFood) {
        if (mfpFood != null) {
            String version = mfpFood.getVersion();
            String id = mfpFood.getId();
            String barcode2 = getBarcode();
            if (Strings.notEmpty(barcode2) && Strings.notEmpty(version) && Strings.notEmpty(id)) {
                ((BarcodeService) this.barcodeService.get()).associateBarcodeWithFood(0, version, id, barcode2, null, null);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setName(String str) {
        this.foodNameTextView.setText(str);
    }

    /* access modifiers changed from: protected */
    public void setDescription(String str) {
        ViewUtils.setVisible(Strings.notEmpty(str), this.foodDescriptionTextView);
        this.foodDescriptionTextView.setText(str);
    }

    /* access modifiers changed from: protected */
    public void setBasedOnText(String str) {
        ViewUtils.setVisible(this.basedOnView);
        String string = this.activity.getString(R.string.not_accurate);
        String format = String.format("\"%1s\". %2s", new Object[]{str, string});
        int indexOf = format.indexOf(string);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(format);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(this.activity.getResources().getColor(R.color.blue)), indexOf, string.length() + indexOf, 33);
        this.basedOnTextView.setText(spannableStringBuilder);
    }

    /* access modifiers changed from: protected */
    public void setDate(Date date2) {
        if (date2 == null) {
            date2 = new Date();
        }
        this.date = date2;
        this.dateTextView.setText(DateTimeUtils.getNormalLocaleFormattedDate(this.date));
    }

    /* access modifiers changed from: protected */
    public void setMealName(String str) {
        String first = ((Session) this.session.get()).getUser().getMealNames().first();
        if (str == null) {
            str = first;
        }
        this.mealName = str;
        this.mealNameTextView.setText(getLocalizedMealName(this.mealName));
    }

    /* access modifiers changed from: protected */
    public final String getBarcode() {
        return this.barcode;
    }

    /* access modifiers changed from: protected */
    public final Date getDate() {
        return this.date;
    }

    /* access modifiers changed from: protected */
    public final String getMealName() {
        return this.mealName;
    }

    /* access modifiers changed from: protected */
    public final String getSource() {
        SearchSource searchSource = this.source;
        return searchSource != null ? searchSource.getTitle() : "";
    }

    /* access modifiers changed from: protected */
    public final FoodEditorAnalytics getAnalytics() {
        return (FoodEditorAnalytics) this.foodEditorAnalytics.get();
    }

    /* access modifiers changed from: protected */
    public void saveFoodToTarget(@Nullable MfpFood mfpFood, int i, float f, List<FoodEntry> list) {
        if (mfpFood == null) {
            showErrorDialog();
            return;
        }
        FoodEntry foodEntryFromMfpFood = getFoodEntryFromMfpFood(mfpFood, i, f);
        if (isCurrentlyInMealCreationMode()) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("food_entry", foodEntryFromMfpFood);
            this.onItemSavedListener.onItemSaved(-1, bundle);
            return;
        }
        foodEntryFromMfpFood.setEntryTimeAndUpdateLoggedAt(this.timestampPickerMixin.getTimestamp());
        ((Session) this.session.get()).getUser().setActiveDate(this.date);
        DiaryDay diaryDayForActiveDateSync = ((DiaryService) this.diaryService.get()).getDiaryDayForActiveDateSync();
        diaryDayForActiveDateSync.setJustAddedPrimaryText(foodEntryFromMfpFood.getFood().getDescription());
        diaryDayForActiveDateSync.addFoodEntry(foodEntryFromMfpFood);
        ((LocalSettingsService) this.localSettingsService.get()).removeRecentsDeletedFoodOriginalUid(foodEntryFromMfpFood.getFood().getOriginalUid());
        if (CollectionUtils.notEmpty((Collection<?>) list)) {
            addSelectPairedFoodEntries(list);
        }
        this.activity.setBusy(1, false);
    }

    /* access modifiers changed from: protected */
    public void updateFoodEntry(MfpFood mfpFood, long j, long j2, int i, float f, Date date2) {
        FoodEntry foodEntryFromMfpFood = getFoodEntryFromMfpFood(mfpFood, i, f);
        foodEntryFromMfpFood.setMasterDatabaseId(j);
        foodEntryFromMfpFood.setLocalId(j2);
        foodEntryFromMfpFood.setEntryTimeAndUpdateLoggedAt(date2);
        ((DiaryService) this.diaryService.get()).getDiaryDayForActiveDateSync().updateFoodEntry(foodEntryFromMfpFood);
    }

    private void addSelectPairedFoodEntries(List<FoodEntry> list) {
        int size = CollectionUtils.size((Collection<?>) list);
        if (size > 0) {
            DiaryDay diaryDayForActiveDateSync = ((DiaryService) this.diaryService.get()).getDiaryDayForActiveDateSync();
            for (FoodEntry foodEntry : list) {
                foodEntry.setMasterDatabaseId(0);
                diaryDayForActiveDateSync.addFoodEntry(foodEntry);
                ((LocalSettingsService) this.localSettingsService.get()).removeRecentsDeletedFoodOriginalUid(foodEntry.getFood().getOriginalUid());
            }
            getAnalytics().reportPairedFoodsLogged(size);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean isCurrentlyInMealCreationMode() {
        return ExtrasUtils.getBoolean(this.intent, MealEditorMixin.EXTRA_MEAL_FOOD_CREATION_FLOW);
    }

    private NutritionDetailsMode resolveCtaMode() {
        if (Strings.notEmpty(this.barcode)) {
            return NutritionDetailsMode.BarcodeMatch;
        }
        return NutritionDetailsMode.Closed;
    }

    private void initCtaArea(boolean z) {
        this.nutritionDetailsMode = resolveCtaMode();
        if (this.nutritionDetailsMode != NutritionDetailsMode.BarcodeMatch || z) {
            ViewUtils.setVisible(false, this.ctaContainer);
            return;
        }
        ViewUtils.setVisible(true, this.ctaContainer);
        this.ctaText.setText(R.string.barcode_wrong_food);
        this.ctaButton.setText(R.string.barcode_button_find_better_match);
    }

    private void initListeners() {
        this.noOfServingsRowView.setOnClickListener(this.onNoOfServingsRowClickListener);
        this.servingSizeRowView.setOnClickListener(this.onServingSizeRowClickListener);
        this.basedOnView.setOnClickListener(this.onBasedOnRowClickListener);
        this.mealRowView.setOnClickListener(this.onMealClickListener);
        this.dateRowView.setOnClickListener(this.onDateClickListener);
        this.ctaButton.setOnClickListener(this.onCtaButtonClickListener);
    }

    /* access modifiers changed from: protected */
    public FoodEntry getFoodEntryFromMfpFood(MfpFood mfpFood, int i, float f) {
        mfpFood.setSelectedServingSizeIndex(i);
        mfpFood.setSelectedServingAmount(f);
        return mfpFood.toFoodEntry(((Session) this.session.get()).getUser(), this.mealName, this.date);
    }

    private String getLocalizedMealName(String str) {
        if (Strings.isEmpty(str)) {
            str = this.mealNames.first();
            if (Strings.isEmpty(str)) {
                str = MealTypeName.BREAKFAST;
            }
        }
        return ((LocalizedStringsUtil) this.localizedStringsUtil.get()).getMealNameString(str, (UserEnergyService) this.userEnergyService.get());
    }

    public static /* synthetic */ void lambda$new$3(FoodEditorMixinBase foodEditorMixinBase, Calendar calendar) {
        if (calendar != null) {
            foodEditorMixinBase.setDate(calendar.getTime());
        }
    }

    private void injectDependencies() {
        ApplicationComponent component = component();
        this.userEnergyService = component.userEnergyService();
        this.localizedStringsUtil = component.localizedStringsUtil();
        this.diaryService = component.diaryService();
        this.session = component.session();
        this.searchService = component.searchService();
        this.barcodeService = component.barcodeService();
        this.foodEditorAnalytics = component.foodEditorAnalytics();
        this.localSettingsService = component.localSettingsService();
        this.foodFeedbackAnalyticsHelper = component.foodFeedbackAnalyticsHelper();
    }

    public void showErrorDialog() {
        MfpActivity mfpActivity = getMfpActivity();
        if (mfpActivity.getSupportFragmentManager().findFragmentByTag(GENERAL_ERROR_DIALOG_TAG) == null) {
            AlertDialogFragmentBase negativeText = ((AlertDialogFragment) ((AlertDialogFragment) AlertDialogFragment.newInstance().setTitle(R.string.error)).setMessage((int) R.string.generic_error_msg)).setNegativeText(R.string.cancel, new DialogNegativeListener() {
                public final void onClick() {
                    MfpActivity.this.finish();
                }
            });
            negativeText.setCancelable(false);
            mfpActivity.showDialogFragment(negativeText, GENERAL_ERROR_DIALOG_TAG);
        }
        String className = mfpActivity.getCallingActivity() != null ? mfpActivity.getCallingActivity().getClassName() : "";
        StringBuilder sb = new StringBuilder();
        sb.append("FOOD null from: ");
        sb.append(className);
        sb.append(" referrer: ");
        sb.append(Strings.toString(this.referrer));
        sb.append(" barcode: ");
        sb.append(Strings.toString(this.barcode));
        sb.append(" source: ");
        sb.append(Strings.toString(this.source));
        Ln.e(sb.toString(), new Object[0]);
    }

    /* access modifiers changed from: 0000 */
    public void showErrorSnackbar() {
        new SnackbarBuilder(this.parentView).setMessage((int) R.string.generic_error_msg).setDuration(0).show();
        String className = this.activity.getCallingActivity() != null ? this.activity.getCallingActivity().getClassName() : "";
        StringBuilder sb = new StringBuilder();
        sb.append("FOOD null from: ");
        sb.append(className);
        sb.append(" referrer: ");
        sb.append(Strings.toString(this.referrer));
        sb.append(" barcode: ");
        sb.append(Strings.toString(this.barcode));
        sb.append(" source: ");
        sb.append(Strings.toString(this.source));
        Ln.e(sb.toString(), new Object[0]);
    }
}
