package com.myfitnesspal.feature.addentry.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.addentry.event.EditServingsDialogCloseEvent;
import com.myfitnesspal.feature.addentry.ui.dialog.EditV2SearchServingsDialogFragment;
import com.myfitnesspal.feature.addentry.ui.extras.AddFoodExtrasBase;
import com.myfitnesspal.feature.addentry.util.EditableServingV2;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.diary.ui.activity.Diary;
import com.myfitnesspal.feature.foodfeedback.mixin.FoodFeedbackOptionsMixin;
import com.myfitnesspal.feature.foodfeedback.service.FoodFeedbackAnalyticsHelper;
import com.myfitnesspal.feature.home.ui.activity.HomeActivity;
import com.myfitnesspal.feature.nutrition.ui.view.CustomDatePicker;
import com.myfitnesspal.feature.search.model.SearchResultType;
import com.myfitnesspal.feature.timestamp.mixin.TimestampPickerMixin;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper.FoodScreenType;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper.TimeValue;
import com.myfitnesspal.feature.timestamp.view.TimestampRowView;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.api.request.FoodAnalyzerResponseData;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.constants.Constants.Dialogs;
import com.myfitnesspal.shared.constants.Constants.Dialogs.Fragments;
import com.myfitnesspal.shared.constants.Constants.MealTypeName;
import com.myfitnesspal.shared.constants.Constants.Settings.App.URLs;
import com.myfitnesspal.shared.event.DialogCalendarEvent;
import com.myfitnesspal.shared.event.MealNameEvent;
import com.myfitnesspal.shared.model.AdUnit;
import com.myfitnesspal.shared.model.FoodV2Logging;
import com.myfitnesspal.shared.model.FoodV2Logging.Builder;
import com.myfitnesspal.shared.model.MealNames;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.model.mapper.impl.FoodMapper;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpFoodSearchResult;
import com.myfitnesspal.shared.model.v2.MfpServingSize;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.appindexer.AppIndexerBot;
import com.myfitnesspal.shared.service.appindexer.AppIndexerClient;
import com.myfitnesspal.shared.service.appindexer.AppIndexerUriUtil;
import com.myfitnesspal.shared.service.appindexer.AppIndexerUriUtil.Source;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.foods.FoodService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.impl.InvalidInputDialogFragment;
import com.myfitnesspal.shared.ui.dialog.impl.MealNamesDialogFragment;
import com.myfitnesspal.shared.ui.fragment.impl.NewNutritionFactsFragment;
import com.myfitnesspal.shared.ui.fragment.impl.NewNutritionFactsFragment.OnNutritionFactsExpandedOrCollapsedListener;
import com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin;
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
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Ln;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

public class AddFoodSummaryViewV2 extends MfpActivity implements EditableServingV2, OnDateSetListener {
    private static final String DATE_PICKER = "date_picker";
    private static final String EXTRA_FROM_FEEDBACK = "extra_from_feedback";
    private static final String EXTRA_NEW_FOOD = "extra_new_food";
    private static final String NUTRITION_FACTS_TAG = "nutrition_facts_detailed";
    private static final int SELECT_SERVINGS = 1;
    private static final int SELECT_SERVING_SIZE = 2;
    private static final int VARIABLE_ACTION_ITEM = 999;
    @Inject
    Lazy<ActionTrackingService> actionTrackingService;
    @BindView(2131361867)
    View addFoodSummaryRoot;
    @Inject
    Lazy<AppIndexerBot> appIndexerBot;
    private AppIndexerClient appIndexerClient;
    private String barcode;
    @BindView(2131361934)
    View barcodeSection;
    @Inject
    Lazy<ConfigService> configService;
    @Inject
    Lazy<CountryService> countryService;
    private FoodEntry currentFoodEntry = null;
    private Date date;
    @BindView(2131362274)
    View dateTableRow;
    @Inject
    Lazy<DiaryService> diaryService;
    private MfpFood food = null;
    @Inject
    Lazy<FoodFeedbackAnalyticsHelper> foodFeedbackAnalyticsHelper;
    private FoodFeedbackOptionsMixin foodFeedbackOptionsMixin;
    @Inject
    FoodMapper foodMapper;
    @Inject
    Lazy<FoodService> foodService;
    @BindView(2131362819)
    View insightContainer;
    private boolean isMealFoodCreationFlow;
    private boolean isPerformingAction;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    private List<FoodV2Logging> loggedFood = new ArrayList();
    private String mealName;
    private MealNames mealNames;
    @BindView(2131363014)
    View mealTableRow;
    private int menuResourceId = R.string.add;
    @Inject
    Lazy<MultiAddFoodHelper> multiAddFoodHelper;
    private boolean needsPatch;
    @BindView(2131363151)
    View noOfServingsTableRow;
    private final OnNutritionFactsExpandedOrCollapsedListener nutritionFactsExpandedOrCollapsedListener = new OnNutritionFactsExpandedOrCollapsedListener() {
        public final void onNutritionFactsExpandedOrCollapsed(boolean z) {
            AddFoodSummaryViewV2.lambda$new$7(AddFoodSummaryViewV2.this, z);
        }
    };
    private NewNutritionFactsFragment nutritionFactsFragment;
    private int positionWithAd;
    private String requestId;
    private int resultsListPosition;
    private String searchQuery;
    private int searchVersion;
    private MfpServingSize selectedServingSize;
    private int selectedServingSizeIndex;
    private MfpServingSize servingSize = null;
    private int servingSizeIndexForLogging;
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
        private String barcode;
        private MfpFood food;
        private int positionWithAd;

        public int describeContents() {
            return 0;
        }

        public Extras() {
        }

        private Extras(Parcel parcel) {
            super(parcel);
            this.food = (MfpFood) parcel.readParcelable(MfpFoodSearchResult.class.getClassLoader());
            this.barcode = parcel.readString();
            this.positionWithAd = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(this.food, i);
            parcel.writeString(this.barcode);
            parcel.writeInt(this.positionWithAd);
        }

        public MfpFood getFood() {
            return this.food;
        }

        public Extras setFood(MfpFood mfpFood) {
            this.food = mfpFood;
            return this;
        }

        public String getBarcode() {
            return this.barcode;
        }

        public Extras setBarcode(String str) {
            this.barcode = str;
            return this;
        }

        public int getPositionWithAd() {
            return this.positionWithAd;
        }

        public Extras setPositionWithAd(int i) {
            this.positionWithAd = i;
            return this;
        }
    }

    public String getAnalyticsScreenTag() {
        return Screens.ADD_FOOD_ENTRY;
    }

    public static Intent newStartIntent(Context context, Extras extras) {
        return new Intent(context, AddFoodSummaryViewV2.class).putExtra("extras", extras);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        setContentView((int) R.layout.new_add_food_summary_view);
        if (bundle == null) {
            ((FoodFeedbackAnalyticsHelper) this.foodFeedbackAnalyticsHelper.get()).resetFlowId();
        }
        this.mealNames = getSession().getUser().getMealNames();
        this.menuResourceId = ((MultiAddFoodHelper) this.multiAddFoodHelper.get()).isMultiAddModeOn() ? R.string.add_to_checked : R.string.add;
        this.appIndexerClient = new AppIndexerClient(this, (ConfigService) this.configService.get(), getIntent(), bundle);
        this.timestampPickerMixin = new TimestampPickerMixin((MfpActivity) this, ((DiaryService) this.diaryService.get()).getDiaryDayForActiveDateSync().getLatestEntryTimeForMealName(this.mealName), this.timestampRowView, FoodScreenType.FOOD);
        registerMixin(this.timestampPickerMixin);
        this.foodFeedbackOptionsMixin = new FoodFeedbackOptionsMixin(this, this.addFoodSummaryRoot);
        registerMixin(this.foodFeedbackOptionsMixin);
        initSummaryView((Extras) getIntent().getParcelableExtra("extras"), bundle);
    }

    private void initSummaryView(Extras extras, Bundle bundle) {
        this.food = extras.getFood();
        this.servingSize = (MfpServingSize) this.food.getServingSizes().get(0);
        this.isMealFoodCreationFlow = extras.isMealFoodCreationFlow();
        this.barcode = extras.getBarcode();
        setMealName(extras.getMealName());
        this.source = extras.getSource();
        this.date = extras.getDate();
        this.searchQuery = extras.getQuery();
        this.resultsListPosition = extras.getPosition();
        this.positionWithAd = extras.getPositionWithAd();
        this.requestId = extras.getRequestId();
        this.searchVersion = extras.getSearchVersion();
        if (DateTimeUtils.isEmpty(this.date)) {
            ViewUtils.setVisible(true, this.dateTableRow);
            this.date = Calendar.getInstance().getTime();
        }
        this.txtDate.setText(DateTimeUtils.getNormalLocaleFormattedDate(this.date));
        TextView textView = this.txtFoodName;
        MfpFood mfpFood = this.food;
        textView.setText(mfpFood != null ? mfpFood.brandAndDescription() : "");
        ViewUtils.setVisible(this.food.getVerified(), this.verifiedBadge);
        if (this.isMealFoodCreationFlow) {
            ViewUtils.setGone(this.mealTableRow);
            ViewUtils.setGone(this.timestampRowView);
        }
        setTitle(extras.getTitle());
        populateFoodData(extras.getServings());
        hookupUIEventListeners();
        handleInsights();
        MfpFood mfpFood2 = this.food;
        if (mfpFood2 != null && mfpFood2.isPublic().booleanValue()) {
            this.appIndexerClient.start(AppIndexerUriUtil.getFoodTitle(this.food, (LocalizedStringsUtil) this.localizedStringsUtil.get(), (UserEnergyService) this.userEnergyService.get()), AppIndexerUriUtil.foodToUri(this.food, Source.AutoComplete));
        }
        try {
            addNutritionFacts(bundle);
        } catch (IOException e) {
            Ln.e(e);
        }
        MfpFood mfpFood3 = this.food;
        if (mfpFood3 != null) {
            this.foodFeedbackOptionsMixin.setMfpFood(mfpFood3);
            this.foodFeedbackOptionsMixin.showReportFood(true);
        }
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

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.appIndexerClient.saveInstanceState(bundle);
    }

    public void onBackPressed() {
        if (ExtrasUtils.getBoolean(getIntent(), com.myfitnesspal.shared.constants.Constants.Extras.STARTED_FROM_DEEP_LINK)) {
            getNavigationHelper().finishActivityAfterNavigation().withExtra(com.myfitnesspal.shared.constants.Constants.Extras.DISABLE_EXIT_TO_LAUNCHER, true).withIntent(HomeActivity.newStartIntent(this)).startActivity();
        } else {
            super.onBackPressed();
        }
    }

    public AdUnit getAdUnit() {
        return getAdUnitService().getAddOrEditFoodEntryScreenAdUnitValue();
    }

    public void hideSoftInput() {
        getImmHelper().hideSoftInput();
    }

    public void populateFoodData(float f) {
        try {
            this.servings = f;
            String initStringWithFormattedFloat = Strings.initStringWithFormattedFloat(f, 3);
            if (Strings.notEmpty(initStringWithFormattedFloat)) {
                NumberFormat instance = NumberFormat.getInstance();
                DecimalFormat decimalFormat = (DecimalFormat) instance;
                decimalFormat.applyPattern("##0.###");
                this.txtNoOfServings.setText(decimalFormat.format(instance.parse(initStringWithFormattedFloat).doubleValue()));
                this.txtServingSize.setText(this.servingSize.descriptionWithAmount());
            }
        } catch (Exception e) {
            ((LegacyAlertMixin) mixin(LegacyAlertMixin.class)).showGenericAlert(e);
        }
        NewNutritionFactsFragment newNutritionFactsFragment = this.nutritionFactsFragment;
        if (newNutritionFactsFragment != null) {
            newNutritionFactsFragment.setMultiplier((double) getNutrientScale());
        }
    }

    public void onDateSet(CustomDatePicker customDatePicker, int i, int i2, int i3) {
        onDateSet(i, i2, i3);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 207) {
            switch (i) {
                case 1:
                    if (i2 == -1) {
                        populateFoodData(NumberUtils.tryParseFloat(ExtrasUtils.getStringExtra(intent, com.myfitnesspal.shared.constants.Constants.Extras.SELECTED_SERVINGS)));
                        return;
                    }
                    return;
                case 2:
                    this.txtServingSize.setText(this.servingSize.descriptionWithAmount());
                    populateFoodData(this.servings);
                    return;
                default:
                    return;
            }
        } else if (i2 != -1) {
        } else {
            if (intent != null) {
                Extras extras = (Extras) getIntent().getParcelableExtra("extras");
                extras.setFood(FoodMapperUtil.mapV1FoodToMfpFood((Food) BundleUtils.getParcelable(intent.getExtras(), EXTRA_NEW_FOOD, Food.class.getClassLoader())));
                initSummaryView(extras, null);
                this.foodFeedbackOptionsMixin.setFoodFromFeedback(true);
                showSnackBar(R.string.thanks_for_feedback_save);
                return;
            }
            showSnackBar(R.string.thanks_for_feedback);
        }
    }

    public boolean shouldDisplayAds() {
        return !((AppIndexerBot) this.appIndexerBot.get()).isActive() && super.shouldDisplayAds();
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!super.onPrepareOptionsMenu(menu)) {
            return false;
        }
        menu.add(0, 999, 0, this.menuResourceId).setIcon(R.drawable.ic_check_white_24dp).setEnabled(!this.isPerformingAction).setShowAsAction(2);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 999) {
            return super.onOptionsItemSelected(menuItem);
        }
        handleActionItemClick();
        return true;
    }

    public MfpFood getFood() {
        return this.food;
    }

    public MfpServingSize getServingSize() {
        return this.servingSize;
    }

    public void setServingSize(MfpServingSize mfpServingSize) {
        this.servingSize = mfpServingSize;
    }

    public void setServingSizeIndex(int i) {
        this.selectedServingSizeIndex = i;
    }

    public float getServings() {
        return this.servings;
    }

    public Map<String, String> getAnalyticsScreenAttributes() {
        return MapUtil.createMap("flow_id", ((FoodFeedbackAnalyticsHelper) this.foodFeedbackAnalyticsHelper.get()).getFlowId(), Attributes.CORRECTED, ((FoodFeedbackAnalyticsHelper) this.foodFeedbackAnalyticsHelper.get()).getCorrectedBy(this.foodFeedbackOptionsMixin.getFoodFromFeedback()));
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
                onDateSet(calendar.get(1), calendar.get(2), calendar.get(5));
            }
        }
    }

    @Subscribe
    public void onEditServingsDialogCloseEvent(EditServingsDialogCloseEvent editServingsDialogCloseEvent) {
        this.selectedServingSize = editServingsDialogCloseEvent.getServingSize();
        this.txtNoOfServings.setText(Strings.toString(Float.valueOf(editServingsDialogCloseEvent.getNumOfServings())));
        this.txtServingSize.setText(this.selectedServingSize.descriptionWithAmount());
        this.servingSizeIndexForLogging = editServingsDialogCloseEvent.getServingSizeIndexForLogging();
        NewNutritionFactsFragment newNutritionFactsFragment = this.nutritionFactsFragment;
        if (newNutritionFactsFragment != null) {
            newNutritionFactsFragment.setMultiplier(this.selectedServingSize.getNutritionMultiplier().doubleValue() * ((double) editServingsDialogCloseEvent.getNumOfServings()));
        }
        this.needsPatch = editServingsDialogCloseEvent.isNeedsPatch();
    }

    private void addNutritionFacts(Bundle bundle) throws IOException {
        if (bundle == null) {
            double nutrientScale = (double) getNutrientScale();
            this.nutritionFactsFragment = NewNutritionFactsFragment.newInstance(this.food.getNutritionalContents(), 0, isBarcodeSectionVisible() ^ true ? 1 : 0, nutrientScale);
            getSupportFragmentManager().beginTransaction().replace(R.id.nutrition_facts_container, this.nutritionFactsFragment, NUTRITION_FACTS_TAG).commit();
            this.nutritionFactsFragment.setNutritionFactsExpandedOrCollapsedListener(this.nutritionFactsExpandedOrCollapsedListener);
        }
    }

    private boolean isBarcodeSectionVisible() {
        View view = this.barcodeSection;
        return view != null && view.getVisibility() == 0;
    }

    private void hookupUIEventListeners() {
        this.noOfServingsTableRow.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                AddFoodSummaryViewV2.this.showDialogFragment(Dialogs.EDIT_SERVINGS_DIALOG);
            }
        });
        this.servingSizeTableRow.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                AddFoodSummaryViewV2.this.showDialogFragment(Dialogs.EDIT_SERVINGS_DIALOG_NO_KEYBOARD);
            }
        });
        this.mealTableRow.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                AddFoodSummaryViewV2.this.showDialogFragment(Dialogs.MEAL_NAMES_DIALOG);
            }
        });
        this.dateTableRow.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: INVOKE  (wrap: com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryViewV2
                      0x0000: IGET  (r0v0 com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryViewV2) = (r1v0 'this' com.myfitnesspal.feature.addentry.ui.activity.-$$Lambda$AddFoodSummaryViewV2$UaDdr9s6GhO_yq7ABcvcqtTv0SA A[THIS]) com.myfitnesspal.feature.addentry.ui.activity.-$$Lambda$AddFoodSummaryViewV2$UaDdr9s6GhO_yq7ABcvcqtTv0SA.f$0 com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryViewV2), (r2v0 'view' android.view.View) com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryViewV2.lambda$hookupUIEventListeners$3(com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryViewV2, android.view.View):void type: STATIC in method: com.myfitnesspal.feature.addentry.ui.activity.-$$Lambda$AddFoodSummaryViewV2$UaDdr9s6GhO_yq7ABcvcqtTv0SA.onClick(android.view.View):void, dex: classes3.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:210)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:203)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:316)
                    	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:262)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:225)
                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:661)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:595)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:353)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:773)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:713)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:210)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:203)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:316)
                    	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:262)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:225)
                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
                    	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:76)
                    	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                    	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:32)
                    	at jadx.core.codegen.CodeGen.generate(CodeGen.java:20)
                    	at jadx.core.ProcessClass.process(ProcessClass.java:36)
                    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
                    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
                    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
                    Caused by: org.objenesis.ObjenesisException: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:57)
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.newConstructorForSerialization(SunReflectionFactoryHelper.java:37)
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.<init>(SunReflectionFactoryInstantiator.java:41)
                    	at org.objenesis.strategy.StdInstantiatorStrategy.newInstantiatorOf(StdInstantiatorStrategy.java:68)
                    	at org.objenesis.ObjenesisBase.getInstantiatorOf(ObjenesisBase.java:94)
                    	at org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
                    	at com.rits.cloning.ObjenesisInstantiationStrategy.newInstance(ObjenesisInstantiationStrategy.java:17)
                    	at com.rits.cloning.Cloner.newInstance(Cloner.java:300)
                    	at com.rits.cloning.Cloner.cloneObject(Cloner.java:461)
                    	at com.rits.cloning.Cloner.cloneInternal(Cloner.java:456)
                    	at com.rits.cloning.Cloner.deepClone(Cloner.java:326)
                    	at jadx.core.dex.nodes.InsnNode.copy(InsnNode.java:352)
                    	at jadx.core.dex.nodes.InsnNode.copyCommonParams(InsnNode.java:333)
                    	at jadx.core.dex.instructions.InvokeNode.copy(InvokeNode.java:56)
                    	at jadx.core.dex.nodes.InsnNode.copyCommonParams(InsnNode.java:333)
                    	at jadx.core.dex.instructions.InvokeNode.copy(InvokeNode.java:56)
                    	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:880)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                    	... 38 more
                    Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                    	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                    	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.Class.forName0(Native Method)
                    	at java.base/java.lang.Class.forName(Unknown Source)
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                    	... 57 more
                    */
                /*
                    this = this;
                    com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryViewV2 r0 = com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryViewV2.this
                    
                    // error: 0x0002: INVOKE  (r0 I:com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryViewV2), (r2 I:android.view.View) com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryViewV2.lambda$hookupUIEventListeners$3(com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryViewV2, android.view.View):void type: STATIC
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.addentry.ui.activity.$$Lambda$AddFoodSummaryViewV2$UaDdr9s6GhO_yq7ABcvcqtTv0SA.onClick(android.view.View):void");
            }
        });
        this.verifiedBadge.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                AddFoodSummaryViewV2.this.getNavigationHelper().withIntent(SharedIntents.newUriIntent(URLs.GOLD_FOOD_LEARN_MORE)).startActivity();
            }
        });
    }

    private MultiAddFoodHelper getMultiAddFoodHelper() {
        return (MultiAddFoodHelper) this.multiAddFoodHelper.get();
    }

    private void otherActionItemClick() {
        if (!Strings.isEmpty(this.barcode) || !getMultiAddFoodHelper().isMultiAddModeOn()) {
            if (this.needsPatch) {
                this.food.getServingSizes().add(this.selectedServingSize);
                ((FoodService) this.foodService.get()).patchFoodServingsAsync(this.food.getServingSizes(), Strings.toString(this.food.getId()), Strings.toString(this.food.getVersion()), new Function1() {
                    public final void execute(Object obj) {
                        AddFoodSummaryViewV2.lambda$otherActionItemClick$6(AddFoodSummaryViewV2.this, (ApiResponse) obj);
                    }
                });
                return;
            }
            getAnalyticsService().reportEvent(Events.ADDFOOD_ADDBTN_CLICK);
            addFoodEntry(this.foodMapper.mapFromMfpFood(this.food, getSession().getUser()));
        } else if (this.needsPatch) {
            this.food.getServingSizes().add(this.selectedServingSize);
            ((FoodService) this.foodService.get()).patchFoodServingsAsync(this.food.getServingSizes(), Strings.toString(this.food.getId()), Strings.toString(this.food.getVersion()), new Function1() {
                public final void execute(Object obj) {
                    AddFoodSummaryViewV2.lambda$otherActionItemClick$5(AddFoodSummaryViewV2.this, (ApiResponse) obj);
                }
            });
        } else {
            buildMultiAddEntryAndFinish(this.food);
        }
    }

    public static /* synthetic */ void lambda$otherActionItemClick$5(AddFoodSummaryViewV2 addFoodSummaryViewV2, ApiResponse apiResponse) throws RuntimeException {
        if (apiResponse != null) {
            MfpFood mfpFood = (MfpFood) apiResponse.getItem();
            if (!(mfpFood == null || mfpFood.getSelectedServingSize() == null)) {
                addFoodSummaryViewV2.selectedServingSizeIndex = ((MfpFood) apiResponse.getItem()).getServingSizes().size() - 1;
            }
            addFoodSummaryViewV2.buildMultiAddEntryAndFinish((MfpFood) apiResponse.getItem());
        }
    }

    public static /* synthetic */ void lambda$otherActionItemClick$6(AddFoodSummaryViewV2 addFoodSummaryViewV2, ApiResponse apiResponse) throws RuntimeException {
        if (apiResponse != null) {
            Food mapFromMfpFood = addFoodSummaryViewV2.foodMapper.mapFromMfpFood((MfpFood) apiResponse.getItem(), addFoodSummaryViewV2.getSession().getUser());
            if (!(mapFromMfpFood == null || mapFromMfpFood.getFoodPortions() == null)) {
                addFoodSummaryViewV2.selectedServingSizeIndex = mapFromMfpFood.getFoodPortions().length - 1;
            }
            addFoodSummaryViewV2.addFoodEntry(mapFromMfpFood);
        }
    }

    private void buildMultiAddEntryAndFinish(MfpFood mfpFood) {
        buildFoodEntry(this.foodMapper.mapFromMfpFood(mfpFood, getSession().getUser()));
        MultiAddFoodHelper multiAddFoodHelper2 = getMultiAddFoodHelper();
        if (multiAddFoodHelper2.isMultiAddModeOn()) {
            multiAddFoodHelper2.addAndLogItem(this.currentFoodEntry, Builder.fromMfpFood(mfpFood).searchTerm(ExtrasUtils.getString(getIntent(), "query")).index(this.resultsListPosition).indexWithAd(this.positionWithAd).servingSizeIndex(this.servingSizeIndexForLogging).source(this.source).type(SearchResultType.FOOD).requestId(this.requestId).build());
        }
        Intent intent = new Intent();
        intent.putExtra(com.myfitnesspal.shared.constants.Constants.Extras.MFP_FOOD, mfpFood);
        intent.putExtra("serving_size_index", this.servingSizeIndexForLogging);
        intent.putExtra("position", this.resultsListPosition);
        getNavigationHelper().setResult(-1, intent).done();
    }

    private void addFoodEntry(Food food2) {
        buildFoodEntry(food2);
        if (this.isMealFoodCreationFlow) {
            if (!((MultiAddFoodHelper) this.multiAddFoodHelper.get()).isMultiAddModeOn()) {
                Intent intent = new Intent();
                intent.putExtra("food_entry", this.currentFoodEntry);
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
        diaryDayForActiveDateSync.setJustAddedPrimaryText(this.currentFoodEntry.getFood().getDescription());
        diaryDayForActiveDateSync.addFoodEntry(this.currentFoodEntry);
        ((LocalSettingsService) this.localSettingsService.get()).removeRecentsDeletedFoodOriginalUid(food2.getOriginalUid());
        addFoodToLoggedFoodList(food2, this.resultsListPosition, this.selectedServingSizeIndex);
        Map createMap = MapUtil.createMap("flow_id", ((FoodFeedbackAnalyticsHelper) this.foodFeedbackAnalyticsHelper.get()).getFlowId(), "meal", this.mealName.toLowerCase(), "source", this.source, "locale", ((CountryService) this.countryService.get()).getCurrentLocaleIdentifierForV2(), "foods", new ApiJsonMapper().reverseMap((Object) this.loggedFood), Attributes.DIARY_DATE, DateTimeUtils.diaryDateAnalyticsFormat(diaryDayForActiveDateSync.getDate()), Attributes.CONTAINS_FOOD_AD, Strings.toString(Boolean.valueOf(FoodV2Logging.listContainsAdFood(this.loggedFood))), Attributes.CORRECTED, ((FoodFeedbackAnalyticsHelper) this.foodFeedbackAnalyticsHelper.get()).getCorrectedBy(this.foodFeedbackOptionsMixin.getFoodFromFeedback()), "version", Strings.toString(Integer.valueOf(this.searchVersion)));
        if (this.timestampPickerMixin.isFeatureEnabled()) {
            createMap.put(TimestampAnalyticsHelper.ATTR_TIME, TimeValue.Companion.fromTimestampOption(this.timestampPickerMixin.getSelectedOption()).getAnalyticsName());
        }
        ((DiaryService) this.diaryService.get()).endFoodLoggingFlow(createMap);
        reportAddFoodEntry();
        Ln.i("Food added", new Object[0]);
        getNavigationHelper().setResult(-1).finishActivityAfterNavigation().withClearTopAndSingleTop().withIntent(Diary.newStartIntentWithReferrerAndForceHomeOnBack(this, com.myfitnesspal.shared.constants.Constants.Extras.REFERRER_DIARY_JUST_LOGGED)).startActivity();
    }

    private void buildFoodEntry(Food food2) {
        this.currentFoodEntry = FoodEntry.Builder.fromFood(food2, this.selectedServingSizeIndex, this.servings).mealName(this.mealName).date(getCurrentDate()).entryTime(this.timestampPickerMixin.getTimestamp()).loggedAt(Calendar.getInstance().getTime()).build();
    }

    private void addFoodToLoggedFoodList(Food food2, int i, int i2) {
        this.loggedFood.clear();
        this.loggedFood.add(Builder.fromFood(food2).searchTerm(this.searchQuery).index(i).indexWithAd(this.positionWithAd).servingSizeIndex(i2).source(this.source).type(SearchResultType.FOOD).build());
    }

    private void handleActionItemClick() {
        try {
            this.isPerformingAction = true;
            supportInvalidateOptionsMenu();
            otherActionItemClick();
        } finally {
            this.isPerformingAction = false;
            supportInvalidateOptionsMenu();
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

    private void showEditServingsDialogFragment(boolean z) {
        showDialogFragment(EditV2SearchServingsDialogFragment.newInstance(z), Fragments.EDIT_SERVINGS_DIALOG);
    }

    private void handleInsights() {
        ViewUtils.setVisible(false, this.insightContainer);
        FoodAnalyzerResponseData foodAnalyzerResponseData = (FoodAnalyzerResponseData) ExtrasUtils.getParcelable(getIntent(), com.myfitnesspal.shared.constants.Constants.Extras.FOOD_ANALYZER_DATA, FoodAnalyzerResponseData.class.getClassLoader());
        if (foodAnalyzerResponseData != null) {
            new FoodInsightViewBinder(this.insightContainer, this.foodService, getMessageBus(), this.currentFoodEntry).setFoodAnalyzerData(foodAnalyzerResponseData, false);
        }
    }

    private void reportAddFoodEntry() {
        if (Strings.toBoolean(((ActionTrackingService) this.actionTrackingService.get()).getTrackingDataForEvent("is_last_pressed_search", "is_last_pressed_search"))) {
            ((ActionTrackingService) this.actionTrackingService.get()).appendToEvent(Events.ONLINE_SEARCH_SUMMARY, MapUtil.createMap(Attributes.LOGGED, "yes", "flow_id", ((FoodFeedbackAnalyticsHelper) this.foodFeedbackAnalyticsHelper.get()).getFlowId()));
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

    private void onDateSet(int i, int i2, int i3) {
        Calendar instance = Calendar.getInstance();
        instance.set(i, i2, i3);
        this.date = instance.getTime();
        this.txtDate.setText(DateTimeUtils.getNormalLocaleFormattedDate(instance.getTime()));
    }

    private void showSnackBar(int i) {
        new SnackbarBuilder(this.addFoodSummaryRoot).setMessage(i).setDuration(0).show();
    }

    private Date getCurrentDate() {
        return getSession().getUser().getActiveDate();
    }

    private float getNutrientScale() {
        return this.servingSize.getNutritionMultiplier().floatValue();
    }

    public static /* synthetic */ void lambda$new$7(AddFoodSummaryViewV2 addFoodSummaryViewV2, boolean z) {
        ScrollView scrollView = (ScrollView) addFoodSummaryViewV2.findViewById(R.id.scroll_parent);
        if (!z && scrollView != null) {
            scrollView.smoothScrollTo(0, 0);
        }
    }
}
