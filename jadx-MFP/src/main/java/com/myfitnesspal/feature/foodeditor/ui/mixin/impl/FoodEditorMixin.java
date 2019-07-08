package com.myfitnesspal.feature.foodeditor.ui.mixin.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;
import com.myfitnesspal.feature.addentry.util.PairedFoodsHelper;
import com.myfitnesspal.feature.foodeditor.task.PatchFoodServingsTask;
import com.myfitnesspal.feature.foodeditor.task.PatchFoodServingsTask.CompletedEvent;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorAnalyticsExtras;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorExtras;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorExtras.ActionType;
import com.myfitnesspal.feature.foodeditor.ui.dialog.EditFoodServingsDialogFragment;
import com.myfitnesspal.feature.foodeditor.ui.dialog.EditFoodServingsDialogFragment.OnServingSizeSelectedListener;
import com.myfitnesspal.feature.foodeditor.ui.dialog.EditServingsDialogFragmentBase;
import com.myfitnesspal.feature.foodeditor.ui.mixin.EditorMixinBase.OnItemSavedListener;
import com.myfitnesspal.feature.foodfeedback.mixin.FoodFeedbackOptionsMixin;
import com.myfitnesspal.feature.foodfeedback.service.FoodFeedbackAnalyticsHelper;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.meals.util.MealUtil;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper.FoodScreenType;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper.TimeValue;
import com.myfitnesspal.shared.api.request.FoodAnalyzerResponseData;
import com.myfitnesspal.shared.constants.Constants.ABTest.PairedFoodsAndroid201508;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.model.mapper.impl.FoodMapper;
import com.myfitnesspal.shared.model.mapper.impl.MfpFoodMapper;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v1.FoodPortion;
import com.myfitnesspal.shared.model.v15.FoodObject;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpNutritionalContents;
import com.myfitnesspal.shared.model.v2.MfpServingSize;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.appindexer.AppIndexerClient;
import com.myfitnesspal.shared.service.appindexer.AppIndexerUriUtil;
import com.myfitnesspal.shared.service.appindexer.AppIndexerUriUtil.Source;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.foods.FoodService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.view.FoodInsightViewBinder;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.myfitnesspal.shared.util.MultiAddFoodHelper;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;

public class FoodEditorMixin extends FoodEditorMixinBase<MfpFood> {
    private static String SERVING_SIZE_DIALOG_TAG = EditServingsDialogFragmentBase.class.getName();
    @Inject
    Lazy<AnalyticsService> analyticsService;
    private AppIndexerClient appIndexerClient;
    private final BusEvents busEvents;
    @Inject
    Lazy<ConfigService> configService;
    /* access modifiers changed from: private */
    public MfpFood food;
    FoodEditorExtras foodEditorExtras;
    @Inject
    Lazy<FoodMapper> foodMapperV1;
    @Inject
    Lazy<MfpFoodMapper> foodMapperV2;
    @Inject
    Lazy<FoodService> foodService;
    @Inject
    Lazy<ImageService> imageService;
    @Inject
    Lazy<MealUtil> mealUtil;
    @Inject
    Lazy<MultiAddFoodHelper> multiAddFoodHelper;
    private MfpNutritionalContents nutritionalContents;
    private OnServingSizeSelectedListener onServingSizeSelectedListener;
    private PairedFoodsHelper pairedFoodsHelper;
    private Bundle savedState;
    float selectedServingAmount;
    private MfpServingSize selectedServingSize;
    int selectedServingSizeIndex;

    private class BusEvents {
        private BusEvents() {
        }

        @Subscribe
        public void onPatchFoodServingsTaskCompleted(CompletedEvent completedEvent) {
            if (completedEvent.successful()) {
                FoodEditorMixin.this.food = (MfpFood) completedEvent.getResult();
                FoodEditorMixin.this.postServingSizeSelection(completedEvent.getServingSize(), completedEvent.getNumServings(), completedEvent.getServingSizeIndex());
            }
        }
    }

    public FoodEditorMixin(MfpActivity mfpActivity, OnItemSavedListener onItemSavedListener, Intent intent, Bundle bundle, View view) {
        this(mfpActivity, onItemSavedListener, intent, bundle, view, (FoodEditorExtras) null);
    }

    public FoodEditorMixin(MfpActivity mfpActivity, OnItemSavedListener onItemSavedListener, Intent intent, Bundle bundle, View view, FoodEditorExtras foodEditorExtras2) {
        MfpActivity mfpActivity2 = mfpActivity;
        OnItemSavedListener onItemSavedListener2 = onItemSavedListener;
        Intent intent2 = intent;
        Bundle bundle2 = bundle;
        View view2 = view;
        this(mfpActivity2, onItemSavedListener2, intent2, bundle2, view2, (MfpFood) BundleUtils.getParcelable("extra_food", null, MfpFood.class.getClassLoader(), bundle, intent.getExtras()), foodEditorExtras2);
    }

    public FoodEditorMixin(MfpActivity mfpActivity, OnItemSavedListener onItemSavedListener, Intent intent, Bundle bundle, View view, MfpFood mfpFood) {
        this(mfpActivity, onItemSavedListener, intent, bundle, view, mfpFood, null);
    }

    private FoodEditorMixin(MfpActivity mfpActivity, OnItemSavedListener onItemSavedListener, Intent intent, Bundle bundle, View view, MfpFood mfpFood, FoodEditorExtras foodEditorExtras2) {
        super(mfpActivity, onItemSavedListener, intent, bundle, view);
        this.busEvents = new BusEvents();
        this.onServingSizeSelectedListener = new OnServingSizeSelectedListener() {
            public void onServingSizeSelected(MfpServingSize mfpServingSize, float f, int i) {
                int size = CollectionUtils.size((Collection<?>) FoodEditorMixin.this.food.getServingSizes());
                if (i > size - 1) {
                    List servingSizes = FoodEditorMixin.this.food.getServingSizes();
                    servingSizes.add(mfpServingSize);
                    PatchFoodServingsTask patchFoodServingsTask = new PatchFoodServingsTask(FoodEditorMixin.this.food, FoodEditorMixin.this.foodService, servingSizes, mfpServingSize, f, size);
                    patchFoodServingsTask.run(FoodEditorMixin.this.getRunner());
                    return;
                }
                FoodEditorMixin.this.postServingSizeSelection(mfpServingSize, f, i);
            }
        };
        if (foodEditorExtras2 == null) {
            foodEditorExtras2 = new FoodEditorExtras();
        }
        this.foodEditorExtras = foodEditorExtras2;
        init(mfpFood, bundle);
    }

    private void init(@Nullable MfpFood mfpFood, Bundle bundle) {
        component().inject(this);
        if (mfpFood == null) {
            showErrorDialog();
            return;
        }
        this.food = mfpFood;
        this.selectedServingSizeIndex = mfpFood.getSelectedServingSizeIndex();
        this.selectedServingSize = getServingSizeForIndex(mfpFood, this.selectedServingSizeIndex);
        this.selectedServingAmount = mfpFood.getSelectedServingAmount();
        this.savedState = bundle;
        this.timestampPickerMixin.setScreenType(this.foodEditorExtras.isForRecipe() ? FoodScreenType.RECIPE : FoodScreenType.FOOD);
    }

    private MfpServingSize getServingSizeForIndex(MfpFood mfpFood, int i) {
        List servingSizes = mfpFood.getServingSizes();
        if (CollectionUtils.isEmpty((Collection<?>) servingSizes)) {
            return null;
        }
        if (i >= CollectionUtils.size((Collection<?>) servingSizes)) {
            i = 0;
        }
        return (MfpServingSize) servingSizes.get(i);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("extra_food", this.food);
        this.appIndexerClient.saveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public void onNewFoodSelected(MfpFood mfpFood) {
        setFood(mfpFood);
        setName(mfpFood.brandAndDescription());
        setSelectedServingSizeIndex(0);
        setSelectedServingSize((MfpServingSize) mfpFood.getServingSizes().get(0));
        setSelectedServingAmount(1.0f);
    }

    public float getNutrientScale() {
        return this.selectedServingSize.getNutritionMultiplier().floatValue() * this.selectedServingAmount;
    }

    public String getFoodItemName() {
        return this.food.brandAndDescription();
    }

    public void renderView() {
        if (Strings.notEmpty(this.foodEditorExtras.getScreenTitle())) {
            this.activity.setTitle(this.foodEditorExtras.getScreenTitle());
        }
        initAppIndexing(this.savedState);
        MfpFood mfpFood = this.food;
        if (mfpFood == null) {
            showErrorDialog();
            return;
        }
        setName(mfpFood.brandAndDescription());
        renderFoodInfo();
        setupPairedFoods();
        initInsightsView();
        if (this.foodEditorExtras.isForRecipe()) {
            ViewUtils.setGone(this.servingSizeRowView);
        }
        if (isCurrentlyInMealCreationMode()) {
            ViewUtils.setGone(this.timestampRowView);
        } else if (this.foodEditorExtras.getActionType() == ActionType.Edit) {
            this.timestampPickerMixin.showCurrentTimestamp(this.foodEditorExtras.getFoodTimestamp());
        }
    }

    private void initInsightsView() {
        if (this.foodEditorExtras.showInsightsUi()) {
            ViewUtils.setVisible(false, this.insightsContainer);
            FoodAnalyzerResponseData foodAnalyzerResponseData = this.foodEditorExtras.getFoodAnalyzerResponseData();
            if (foodAnalyzerResponseData != null) {
                new FoodInsightViewBinder(this.insightsContainer, this.foodService, getMessageBus(), foodV2ToFakeFoodEntry(this.food)).setFoodAnalyzerData(foodAnalyzerResponseData, false);
            }
        }
    }

    public void onStop() {
        super.onStop();
        this.appIndexerClient.end();
    }

    public void onServingSizeClick() {
        showEditServingsDialog(false);
    }

    public void saveItemToTarget() {
        saveItemToTarget(this.food);
    }

    public void saveItemToTarget(@Nullable MfpFood mfpFood) {
        if (this.foodEditorExtras.getActionType() == ActionType.Edit) {
            saveEditedFoodEntry(mfpFood);
        } else {
            saveNewFoodEntry(mfpFood);
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable("food", mfpFood);
        onItemSaved(mfpFood, -1, bundle);
    }

    private void saveNewFoodEntry(MfpFood mfpFood) {
        saveFoodToTarget(mfpFood, this.selectedServingSizeIndex, this.selectedServingAmount, getPairedFoodEntries());
        FoodEditorAnalyticsExtras foodEditorAnalyticsExtras = null;
        TimeValue fromTimestampOption = this.timestampPickerMixin.isFeatureEnabled() ? TimeValue.Companion.fromTimestampOption(this.timestampPickerMixin.getSelectedOption()) : null;
        if (this.foodEditorExtras.isForRecipe()) {
            getAnalytics().reportRecipeAddedToDiary(getMealName(), getDate(), fromTimestampOption);
            return;
        }
        FoodEditorExtras foodEditorExtras2 = this.foodEditorExtras;
        if (foodEditorExtras2 != null) {
            foodEditorAnalyticsExtras = foodEditorExtras2.getFoodEditorAnalyticsExtras();
        }
        if (foodEditorAnalyticsExtras != null) {
            getAnalytics().reportFoodAddedToDiary(((FoodFeedbackAnalyticsHelper) this.foodFeedbackAnalyticsHelper.get()).getFlowId(), mfpFood, getMealName(), getBarcode(), getSource(), getDate(), foodEditorAnalyticsExtras.getSearchQuery(), this.selectedServingSizeIndex, foodEditorAnalyticsExtras.getResultsListPosition(), foodEditorAnalyticsExtras.getListType(), fromTimestampOption, ((FoodFeedbackAnalyticsHelper) this.foodFeedbackAnalyticsHelper.get()).getCorrectedBy(this.foodFeedbackOptionsMixin.getFoodFromFeedback()), this.searchVersion);
        } else {
            getAnalytics().reportFoodAddedToDiary(((FoodFeedbackAnalyticsHelper) this.foodFeedbackAnalyticsHelper.get()).getFlowId(), mfpFood, getMealName(), getBarcode(), getSource(), getDate(), fromTimestampOption, ((FoodFeedbackAnalyticsHelper) this.foodFeedbackAnalyticsHelper.get()).getCorrectedBy(this.foodFeedbackOptionsMixin.getFoodFromFeedback()), this.searchVersion);
        }
    }

    private void saveEditedFoodEntry(@Nullable MfpFood mfpFood) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.EDITFOOD_SAVEBTN_CLICK);
        updateFoodEntry(mfpFood, this.foodEditorExtras.getExistingFoodEntryMasterId(), this.foodEditorExtras.getExistingFoodEntryLocalId(), this.selectedServingSizeIndex, this.selectedServingAmount, this.timestampPickerMixin.getTimestamp());
    }

    public void onServingsCountClick() {
        showEditServingsDialog(true);
    }

    public void onResume() {
        super.onResume();
        this.activity.getMessageBus().register(this.busEvents);
    }

    public void onPause() {
        super.onPause();
        this.activity.getMessageBus().unregister(this.busEvents);
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (!SERVING_SIZE_DIALOG_TAG.equals(str)) {
            return super.onRebindDialogFragment(dialogFragment, str);
        }
        ((EditFoodServingsDialogFragment) dialogFragment).setOnServingSizeSelectedListener(this.onServingSizeSelectedListener);
        return true;
    }

    /* access modifiers changed from: protected */
    public void renderFoodInfo() {
        if (this.foodEditorExtras.isForRecipe()) {
            setNutritionDetailsMode(NutritionDetailsMode.Open);
        }
        renderNutritionDetails(getNutritionalContents());
        this.servingSizeTextView.setText(this.selectedServingSize.descriptionWithAmount());
        this.noOfServingsTextView.setText(NumberUtils.localeStringFromDouble((double) this.selectedServingAmount));
        boolean z = true;
        ViewUtils.setVisible(this.food.getVerified(), this.verifiedBadge);
        this.foodFeedbackOptionsMixin.setMfpFood(getFood());
        FoodFeedbackOptionsMixin foodFeedbackOptionsMixin = this.foodFeedbackOptionsMixin;
        if (this.foodEditorExtras.getActionType() == ActionType.Edit) {
            z = false;
        }
        foodFeedbackOptionsMixin.showReportFood(z);
    }

    /* access modifiers changed from: protected */
    public final void setFood(MfpFood mfpFood) {
        this.food = mfpFood;
        renderFoodInfo();
    }

    /* access modifiers changed from: protected */
    public final MfpFood getFood() {
        return this.food;
    }

    /* access modifiers changed from: protected */
    public final MfpNutritionalContents getNutritionalContents() {
        MfpNutritionalContents mfpNutritionalContents = this.nutritionalContents;
        return mfpNutritionalContents != null ? mfpNutritionalContents : this.food.getNutritionalContents();
    }

    /* access modifiers changed from: protected */
    public final void setNutritionalContents(MfpNutritionalContents mfpNutritionalContents) {
        this.nutritionalContents = mfpNutritionalContents;
    }

    /* access modifiers changed from: protected */
    public final MfpServingSize getSelectedServingSize() {
        return this.selectedServingSize;
    }

    /* access modifiers changed from: protected */
    public final float getSelectedServingsAmount() {
        return this.selectedServingAmount;
    }

    /* access modifiers changed from: protected */
    public final void setSelectedServingSize(MfpServingSize mfpServingSize) {
        this.selectedServingSize = mfpServingSize;
        renderFoodInfo();
    }

    /* access modifiers changed from: protected */
    public final void setSelectedServingAmount(float f) {
        this.selectedServingAmount = f;
        renderFoodInfo();
    }

    /* access modifiers changed from: protected */
    public final void setSelectedServing(MfpServingSize mfpServingSize, float f) {
        this.selectedServingSize = mfpServingSize;
        this.selectedServingAmount = f;
        renderFoodInfo();
    }

    /* access modifiers changed from: protected */
    public final int getSelectedServingSizeIndex() {
        return this.selectedServingSizeIndex;
    }

    /* access modifiers changed from: protected */
    public final void setSelectedServingSizeIndex(int i) {
        this.selectedServingSizeIndex = i;
    }

    private void showEditServingsDialog(boolean z) {
        MfpFood mfpFood = this.food;
        if (mfpFood == null) {
            showErrorSnackbar();
            return;
        }
        EditFoodServingsDialogFragment newInstance = EditFoodServingsDialogFragment.newInstance(mfpFood, this.selectedServingSize, this.selectedServingAmount, z, this.foodEditorExtras.getGetSuggestedServings());
        newInstance.setOnServingSizeSelectedListener(this.onServingSizeSelectedListener);
        this.activity.showDialogFragment(newInstance, SERVING_SIZE_DIALOG_TAG);
    }

    /* access modifiers changed from: private */
    public void postServingSizeSelection(MfpServingSize mfpServingSize, float f, int i) {
        this.selectedServingSize = mfpServingSize;
        this.selectedServingAmount = f;
        this.selectedServingSizeIndex = i;
        this.food.setSelectedServingAmount(f);
        this.food.setSelectedServingSizeIndex(i);
        renderFoodInfo();
    }

    /* access modifiers changed from: protected */
    public final FoodEntry foodV2ToFakeFoodEntry(MfpFood mfpFood) {
        Food food2 = (Food) ((FoodMapper) this.foodMapperV1.get()).reverseMap((FoodObject) ((MfpFoodMapper) this.foodMapperV2.get()).reverseMap(mfpFood));
        MfpServingSize selectedServingSize2 = getSelectedServingSize();
        FoodPortion foodPortion = new FoodPortion();
        foodPortion.setDescription(selectedServingSize2.getUnit());
        foodPortion.setWeightIndex(getSelectedServingSizeIndex());
        foodPortion.setGramWeight(selectedServingSize2.getNutritionMultiplier().floatValue());
        foodPortion.setAmount(selectedServingSize2.getValue().floatValue());
        foodPortion.setNutritionMultiplier(Double.valueOf(1.0d));
        FoodEntry foodEntry = new FoodEntry();
        foodEntry.setFood(food2);
        foodEntry.setQuantity(getSelectedServingsAmount());
        foodEntry.setFoodPortion(foodPortion);
        foodEntry.setDate(getDate());
        foodEntry.setWeightIndex(getSelectedServingSizeIndex());
        return foodEntry;
    }

    /* access modifiers changed from: protected */
    public ArrayList<FoodEntry> getPairedFoodEntries() {
        ArrayList<FoodEntry> arrayList = new ArrayList<>();
        PairedFoodsHelper pairedFoodsHelper2 = this.pairedFoodsHelper;
        if (pairedFoodsHelper2 != null) {
            Set<DiaryEntryCellModel> selectedPairedFoods = pairedFoodsHelper2.getSelectedPairedFoods();
            if (CollectionUtils.notEmpty((Collection<?>) selectedPairedFoods)) {
                for (DiaryEntryCellModel diaryEntryCellModel : selectedPairedFoods) {
                    if (diaryEntryCellModel instanceof FoodEntry) {
                        FoodEntry foodEntry = (FoodEntry) diaryEntryCellModel;
                        foodEntry.setMealName(getMealName());
                        foodEntry.setDate(((Session) this.session.get()).getUser().getActiveDate());
                        foodEntry.setEntryTimeAndUpdateLoggedAt(this.timestampPickerMixin.getTimestamp());
                        arrayList.add(foodEntry);
                    }
                }
            }
        }
        return arrayList;
    }

    private void setupPairedFoods() {
        if (showPairedFoods()) {
            MfpFood mfpFood = this.food;
            if (mfpFood != null) {
                PairedFoodsHelper pairedFoodsHelper2 = new PairedFoodsHelper(mfpFood.getOriginalLocalId(), ((Session) this.session.get()).getUser().getMealNames().mealIdForName(getMealName()), this.food.getId(), this.imageService, this.userEnergyService, this.mealUtil, this.localizedStringsUtil, this.multiAddFoodHelper, this.searchService, this.analyticsService);
                this.pairedFoodsHelper = pairedFoodsHelper2;
                this.pairedFoodsHelper.addPairedFoods(getActivity());
            }
        }
    }

    private boolean showPairedFoods() {
        return this.foodEditorExtras.getSupportPairedFoods() && ((ConfigService) this.configService.get()).isVariantEnabled(PairedFoodsAndroid201508.NAME);
    }

    private void initAppIndexing(Bundle bundle) {
        this.appIndexerClient = new AppIndexerClient(this.activity, (ConfigService) this.configService.get(), this.activity.getIntent(), bundle);
        MfpFood mfpFood = this.food;
        if (mfpFood != null && mfpFood.isPublic().booleanValue()) {
            this.appIndexerClient.start(AppIndexerUriUtil.getFoodTitle(this.food, (LocalizedStringsUtil) this.localizedStringsUtil.get(), (UserEnergyService) this.userEnergyService.get()), AppIndexerUriUtil.foodToUri(this.food, Source.AutoComplete));
        }
    }
}
