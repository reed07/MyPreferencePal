package com.myfitnesspal.feature.walkthrough.ui.fragment;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.addentry.event.EditServingsDialogCloseEvent;
import com.myfitnesspal.feature.addentry.ui.dialog.EditV2SearchServingsDialogFragment;
import com.myfitnesspal.feature.addentry.util.EditableServingV2;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.diary.ui.activity.Diary;
import com.myfitnesspal.feature.walkthrough.event.SkipLoggingWalkthroughEvent;
import com.myfitnesspal.feature.walkthrough.util.WalkthroughUtil;
import com.myfitnesspal.feature.walkthrough.util.WalkthroughUtilImpl.WalkthroughType;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Analytics.Flows;
import com.myfitnesspal.shared.constants.Constants.Dialogs;
import com.myfitnesspal.shared.constants.Constants.Dialogs.Fragments;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.Settings.App.URLs;
import com.myfitnesspal.shared.model.FoodV2Logging;
import com.myfitnesspal.shared.model.FoodV2Logging.LIST_MAPPER;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.model.mapper.impl.FoodMapper;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v1.FoodPortion;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpServingSize;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.foods.FoodService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.ui.navigation.SharedIntents;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Function2;
import com.uacf.core.util.Ln;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.ReturningFunction0;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

public class WalkthroughServingSizeV2Fragment extends MfpFragment implements EditableServingV2 {
    private static final int VARIABLE_ACTION_ITEM = 999;
    private static float servings;
    @Inject
    Lazy<ActionTrackingService> actionTrackingService;
    @Inject
    Lazy<DiaryService> diaryService;
    /* access modifiers changed from: private */
    public MfpFood food;
    @Inject
    FoodMapper foodMapper;
    @Inject
    Lazy<FoodService> foodService;
    private boolean isPerformingAction;
    private boolean isWalkThrough;
    private String mealName;
    @BindView(2131363151)
    View noOfServingsTableRow;
    private MfpServingSize selectedServingSize;
    /* access modifiers changed from: private */
    public int selectedServingSizeIndex;
    private MfpServingSize servingSize = null;
    @BindView(2131363605)
    View servingSizeTableRow;
    ReturningFunction0<String> skipEventNameFunc = new ReturningFunction0<String>() {
        public String execute() throws RuntimeException {
            return Events.SPOTLIGHT_SERVING_SIZE_SKIP;
        }
    };
    @BindView(2131364008)
    TextView txtFoodName;
    @BindView(2131364022)
    TextView txtNoOfServings;
    @BindView(2131364036)
    TextView txtServingSize;
    @BindView(2131364138)
    View verifiedBadge;
    @BindView(2131364194)
    View walkThroughContainer;
    @Inject
    WalkthroughUtil walkthroughUtil;

    public static WalkthroughServingSizeV2Fragment newInstance(MfpFood mfpFood, String str) {
        Bundle bundle = new Bundle();
        bundle.putString(Extras.MEAL_NAME, str);
        bundle.putParcelable(Extras.MFP_FOOD, mfpFood);
        WalkthroughServingSizeV2Fragment walkthroughServingSizeV2Fragment = new WalkthroughServingSizeV2Fragment();
        walkthroughServingSizeV2Fragment.setArguments(bundle);
        return walkthroughServingSizeV2Fragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.new_walkthrough_edit_serving_size, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mealName = BundleUtils.getString(getArguments(), Extras.MEAL_NAME);
        this.food = (MfpFood) BundleUtils.getParcelable(getArguments(), Extras.MFP_FOOD, MfpFood.class.getClassLoader());
        MfpFood mfpFood = this.food;
        if (mfpFood != null) {
            this.servingSize = (MfpServingSize) mfpFood.getServingSizes().get(0);
        }
        setTitle(R.string.addFood, new Object[0]);
        setHasOptionsMenu(true);
        setListeners();
        setFoodName();
        populateFoodData(1.0f);
        showWalkThrough();
        ViewUtils.setVisible(this.food.getVerified() && !this.isWalkThrough, this.verifiedBadge);
    }

    public void hideSoftInput() {
        getImmHelper().hideSoftInput();
    }

    private void showWalkThrough() {
        this.walkthroughUtil.showWalkthrough(this.walkThroughContainer, WalkthroughType.PickServingSize, new Function2<View, WalkthroughType>() {
            public void execute(View view, WalkthroughType walkthroughType) throws RuntimeException {
                WalkthroughServingSizeV2Fragment walkthroughServingSizeV2Fragment = WalkthroughServingSizeV2Fragment.this;
                walkthroughServingSizeV2Fragment.postEvent(new SkipLoggingWalkthroughEvent((String) walkthroughServingSizeV2Fragment.skipEventNameFunc.execute()));
            }
        });
        this.isWalkThrough = true;
        getAnalyticsService().reportEvent(Events.SPOTLIGHT_SERVING_SIZE_SEE);
    }

    /* access modifiers changed from: protected */
    public void setFoodName() {
        this.txtFoodName.setText(this.food.brandAndDescription());
    }

    private void setListeners() {
        this.noOfServingsTableRow.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WalkthroughServingSizeV2Fragment.this.getAnalyticsService().reportEvent(Events.SPOTLIGHT_SERVING_SIZE_NUMBER_OF_SERVINGS);
                WalkthroughServingSizeV2Fragment.this.showDialogFragment(Dialogs.EDIT_SERVINGS_DIALOG);
            }
        });
        this.servingSizeTableRow.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WalkthroughServingSizeV2Fragment.this.getAnalyticsService().reportEvent(Events.SPOTLIGHT_SERVING_SIZE_SERVING_SIZE);
                WalkthroughServingSizeV2Fragment.this.showDialogFragment(Dialogs.EDIT_SERVINGS_DIALOG_NO_KEYBOARD);
            }
        });
        this.verifiedBadge.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WalkthroughServingSizeV2Fragment.this.getNavigationHelper().withIntent(SharedIntents.newUriIntent(URLs.GOLD_FOOD_LEARN_MORE)).startActivity();
            }
        });
    }

    public void showDialogFragment(int i) {
        if (i == 7605) {
            showEditServingsDialogFragment(true);
        } else if (i == 7623) {
            showEditServingsDialogFragment(false);
        }
    }

    private void showEditServingsDialogFragment(boolean z) {
        EditV2SearchServingsDialogFragment newInstance = EditV2SearchServingsDialogFragment.newInstance(z);
        newInstance.setTargetFragment(this, 0);
        newInstance.show(getFragmentManager(), Fragments.EDIT_SERVINGS_DIALOG);
    }

    public MfpFood getFood() {
        return this.food;
    }

    public MfpServingSize getServingSize() {
        return this.selectedServingSize;
    }

    public void setServingSize(MfpServingSize mfpServingSize) {
        this.servingSize = mfpServingSize;
    }

    public void setServingSizeIndex(int i) {
        this.selectedServingSizeIndex = i;
    }

    public float getServings() {
        return servings;
    }

    public void populateFoodData(float f) {
        servings = f;
        try {
            String initStringWithFormattedFloat = Strings.initStringWithFormattedFloat(f, 3);
            if (Strings.notEmpty(initStringWithFormattedFloat)) {
                NumberFormat instance = NumberFormat.getInstance();
                DecimalFormat decimalFormat = (DecimalFormat) instance;
                decimalFormat.applyPattern("##0.###");
                this.txtNoOfServings.setText(decimalFormat.format(instance.parse(initStringWithFormattedFloat).doubleValue()));
                this.txtServingSize.setText(this.servingSize.descriptionWithAmount());
            }
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem icon = menu.add(0, 999, 0, R.string.save).setIcon(R.drawable.ic_check_white_24dp);
        icon.setEnabled(!this.isPerformingAction);
        MenuItemCompat.setShowAsAction(icon, 2);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 999) {
            return super.onOptionsItemSelected(menuItem);
        }
        handleActionItemClick();
        return true;
    }

    private void handleActionItemClick() {
        try {
            this.isPerformingAction = true;
            invalidateOptionsMenu();
            actionItemClick();
        } finally {
            this.isPerformingAction = false;
            invalidateOptionsMenu();
        }
    }

    private void actionItemClick() {
        boolean z;
        getAnalyticsService().reportEvent(Events.SPOTLIGHT_SERVING_SIZE_TAP_CHECKMARK);
        getAnalyticsService().reportEvent(Events.ADDFOOD_ADDBTN_CLICK);
        setBusy(1, true);
        if (this.selectedServingSize != null) {
            Iterator it = this.food.getServingSizes().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                MfpServingSize mfpServingSize = (MfpServingSize) it.next();
                if (Strings.equals(mfpServingSize.getUnit(), this.selectedServingSize.getUnit()) && NumberUtils.areEffectivelyEqual(mfpServingSize.getValue().doubleValue(), this.selectedServingSize.getValue().doubleValue())) {
                    z = true;
                    break;
                }
            }
        }
        z = false;
        if (this.selectedServingSize == null || z) {
            getAnalyticsService().reportEvent(Events.ADDFOOD_ADDBTN_CLICK);
            addFoodEntryAndMoveOn(this.foodMapper.mapFromMfpFood(this.food, getSession().getUser()));
            setBusy(1, false);
            return;
        }
        ArrayList arrayList = new ArrayList(this.food.getServingSizes());
        arrayList.add(this.selectedServingSize);
        ((FoodService) this.foodService.get()).patchFoodServingsAsync(arrayList, Strings.toString(this.food.getId()), Strings.toString(this.food.getVersion()), new Function1<ApiResponse<MfpFood>>() {
            public void execute(ApiResponse<MfpFood> apiResponse) {
                if (apiResponse != null) {
                    WalkthroughServingSizeV2Fragment.this.food = (MfpFood) apiResponse.getItem();
                    Food mapFromMfpFood = WalkthroughServingSizeV2Fragment.this.foodMapper.mapFromMfpFood(WalkthroughServingSizeV2Fragment.this.food, WalkthroughServingSizeV2Fragment.this.getSession().getUser());
                    WalkthroughServingSizeV2Fragment.this.selectedServingSizeIndex = mapFromMfpFood.getFoodPortions().length - 1;
                    WalkthroughServingSizeV2Fragment.this.addFoodEntryAndMoveOn(mapFromMfpFood);
                    WalkthroughServingSizeV2Fragment.this.setBusy(1, false);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void addFoodEntryAndMoveOn(Food food2) {
        FoodEntry buildFoodEntry = buildFoodEntry(food2);
        DiaryDay diaryDayForActiveDateSync = ((DiaryService) this.diaryService.get()).getDiaryDayForActiveDateSync();
        diaryDayForActiveDateSync.setJustAddedPrimaryText(buildFoodEntry.getFood().getDescription());
        diaryDayForActiveDateSync.addFoodEntry(buildFoodEntry);
        Map trackingEvents = ((ActionTrackingService) this.actionTrackingService.get()).getTrackingEvents(Flows.LOGGING);
        if (trackingEvents != null) {
            List list = (List) new ApiJsonMapper().withType(LIST_MAPPER.class).tryMapFrom((String) trackingEvents.get("foods"));
            if (CollectionUtils.size((Collection<?>) list) > 0) {
                ((FoodV2Logging) list.get(0)).setServingSizeIndex(this.selectedServingSizeIndex);
                ((ActionTrackingService) this.actionTrackingService.get()).appendToEvent(Flows.LOGGING, "foods", new ApiJsonMapper().reverseMap((Object) Arrays.asList(new List[]{list})));
            }
        }
        ((DiaryService) this.diaryService.get()).endFoodLoggingFlow(null);
        Ln.i("Food added", new Object[0]);
        getNavigationHelper().withIntent(Diary.newStartIntentWithReferrer(getActivity(), Extras.REFERRER_DIARY_JUST_LOGGED)).withExtra(Extras.IS_WALK_THROUGH, this.isWalkThrough).setResult(-1).finishActivityAfterNavigation().startActivity();
    }

    private FoodEntry buildFoodEntry(Food food2) {
        FoodEntry foodEntry = new FoodEntry();
        foodEntry.setFood(food2);
        FoodPortion foodPortion = food2.getFoodPortions()[this.selectedServingSizeIndex];
        foodEntry.setFoodPortion(foodPortion);
        foodEntry.setWeightIndex(foodPortion.getWeightIndex());
        foodEntry.setQuantity(servings);
        foodEntry.setMealName(this.mealName);
        foodEntry.setDate(getSession().getUser().getActiveDate());
        foodEntry.setIsFraction(foodPortion.getIsFraction());
        foodEntry.clearCachedData();
        return foodEntry;
    }

    @Subscribe
    public void onEditServingsDialogCloseEvent(EditServingsDialogCloseEvent editServingsDialogCloseEvent) {
        this.selectedServingSize = editServingsDialogCloseEvent.getServingSize();
        this.txtNoOfServings.setText(Strings.toString(Float.valueOf(editServingsDialogCloseEvent.getNumOfServings())));
        this.txtServingSize.setText(this.selectedServingSize.descriptionWithAmount());
    }
}
