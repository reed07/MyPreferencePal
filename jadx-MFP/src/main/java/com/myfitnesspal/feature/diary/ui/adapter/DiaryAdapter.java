package com.myfitnesspal.feature.diary.ui.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnContextClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.addentry.util.WaterSponsorshipUtil;
import com.myfitnesspal.feature.addentry.util.WaterSponsorshipUtil.WaterLoggingAsset;
import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.diary.listener.DiaryAdapterItemActionListener;
import com.myfitnesspal.feature.diary.listener.MealHeaderTip;
import com.myfitnesspal.feature.diary.model.MealMacrosDisplayUnit;
import com.myfitnesspal.feature.diary.service.DiaryAnalyticsHelper;
import com.myfitnesspal.feature.diary.ui.fragment.DiaryFragmentBase;
import com.myfitnesspal.feature.diary.ui.item.DiaryAdapterItem;
import com.myfitnesspal.feature.diary.ui.item.DiaryButtonsFooterItem;
import com.myfitnesspal.feature.diary.ui.item.DiaryLandscapeFooterItem;
import com.myfitnesspal.feature.diary.ui.item.DiaryPromoItem;
import com.myfitnesspal.feature.diary.ui.item.DiaryRowActionItem;
import com.myfitnesspal.feature.diary.ui.item.SectionHeaderItem;
import com.myfitnesspal.feature.diary.util.DiaryDelegate;
import com.myfitnesspal.feature.diary.util.MealMacrosUtil;
import com.myfitnesspal.feature.externalsync.impl.googlefit.constants.GoogleFitConstants;
import com.myfitnesspal.feature.externalsync.impl.googlefit.util.GoogleFitStepsUtils;
import com.myfitnesspal.feature.home.service.NewsFeedAnalyticsHelper;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.settings.model.AdsSettings;
import com.myfitnesspal.feature.settings.ui.activity.StepsSettings;
import com.myfitnesspal.shared.api.request.FoodAnalyzerResponseData;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.LocalizedStrings;
import com.myfitnesspal.shared.constants.Constants.Report;
import com.myfitnesspal.shared.model.AdNetworkType;
import com.myfitnesspal.shared.model.unitconv.LocalizedFluid;
import com.myfitnesspal.shared.model.v1.DatabaseObject;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v1.DiaryEntryItem;
import com.myfitnesspal.shared.model.v1.DiaryNote;
import com.myfitnesspal.shared.model.v1.ExerciseEntry;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v1.NutritionalValues;
import com.myfitnesspal.shared.model.v1.WaterEntry;
import com.myfitnesspal.shared.model.v2.MfpExerciseMetadataForSteps;
import com.myfitnesspal.shared.model.v2.MfpPlatformApp;
import com.myfitnesspal.shared.model.v2.MfpStepSource;
import com.myfitnesspal.shared.service.ExerciseStringService;
import com.myfitnesspal.shared.service.ads.AdUnitService;
import com.myfitnesspal.shared.service.ads.AdsAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.foods.FoodService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.location.LocationService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.ui.view.InsightViewBinder;
import com.myfitnesspal.shared.ui.view.RecyclerViewHolder;
import com.myfitnesspal.shared.util.AdsHelper;
import com.myfitnesspal.shared.util.AdsHelper.Builder;
import com.myfitnesspal.shared.util.AdsHelper.DfpAdsListener;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.GlideUtil;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.squareup.otto.Bus;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.Tuple3;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Named;

public class DiaryAdapter extends Adapter<RecyclerViewHolder<DiaryAdapterItem>> implements DfpAdsListener {
    private static final int DEFAULT_MEAL_HEADER_TIP_INDEX = -1;
    private static final boolean DISABLE_UACF_ENTRY_IMAGES = true;
    private static final boolean ENABLE_EXERCISE_ENTRY_IMAGES = true;
    private static final String MAPMY_CLIENT_ID_PREFIX = "mmf-";
    private static final int MAX_NOTE_LENGTH = 40;
    private static final String RECORD_CLIENT_ID = "record";
    @Inject
    Lazy<AdUnitService> adUnitService;
    private final List<DiaryAdapterItem> adapterItems;
    @Inject
    Lazy<AdsAnalyticsHelper> adsAnalytics;
    @Inject
    Lazy<AdsSettings> adsSettings;
    @Inject
    Lazy<AnalyticsService> analyticsService;
    @Inject
    Lazy<AppGalleryService> appGalleryService;
    /* access modifiers changed from: private */
    public OnClickListener bottomRowOnClickListener = new OnClickListener() {
        public void onClick(View view) {
            if (DiaryAdapter.this.diaryFragmentBase.isEnabled()) {
                String headerName = ((DiaryRowActionItem) view.getTag()).getHeaderName();
                int id = view.getId();
                if (id == R.id.add_food) {
                    DiaryAdapter.this.diaryAdapterItemActionListener.onBottomRowAddFoodClick(headerName);
                } else if (id == R.id.more) {
                    DiaryAdapter.this.diaryAdapterItemActionListener.onBottomRowShowMoreActionsClick(headerName);
                } else if (id == R.id.save_meal) {
                    DiaryAdapter.this.diaryAdapterItemActionListener.onBottomRowSaveMealActionsClick(headerName);
                }
            }
        }
    };
    @Inject
    Lazy<Bus> bus;
    /* access modifiers changed from: private */
    public final List<Long> checkedItems;
    @Inject
    Lazy<ConfigService> configService;
    private final Context context;
    DiaryDay currentDiaryDay;
    /* access modifiers changed from: private */
    public final int currentPageIndex;
    /* access modifiers changed from: private */
    public AdsHelper dfpWaterAdsHelper;
    /* access modifiers changed from: private */
    public final DiaryAdapterItemActionListener diaryAdapterItemActionListener;
    @Inject
    Lazy<DiaryAnalyticsHelper> diaryAnalyticsHelper;
    /* access modifiers changed from: private */
    public final DiaryDelegate diaryDelegate;
    /* access modifiers changed from: private */
    public final DiaryFragmentBase diaryFragmentBase;
    /* access modifiers changed from: private */
    public OnCheckedChangeListener diaryItemOnCheckedChangeListener = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            DiaryAdapter.this.diaryAdapterItemActionListener.onEntryItemCheckChanged((DatabaseObject) compoundButton.getTag(), z);
        }
    };
    @Inject
    Lazy<ExerciseStringService> exerciseStringService;
    @Inject
    @Named("fit_client_store")
    Lazy<SharedPreferences> fitStepsSharedPreferences;
    /* access modifiers changed from: private */
    public List<FoodAnalyzerResponseData> foodInsights;
    @Inject
    Lazy<FoodService> foodService;
    private Map<SectionHeaderItem, List<DatabaseObject>> headerItemToObjectsMap;
    boolean inEditMode;
    final boolean isForRemoteUser;
    /* access modifiers changed from: private */
    public final boolean isSponsoredWaterEnabled;
    /* access modifiers changed from: private */
    public final boolean isWaterBrandingEnabled;
    private final LayoutInflater layoutInflater;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    @Inject
    Lazy<LocationService> locationService;
    private int mealHeaderTipIndex = -1;
    @Inject
    Lazy<NavigationHelper> navigationHelper;
    protected final Lazy<NewsFeedAnalyticsHelper> newsFeedAnalyticsHelper;
    private OnClickListener onCloseMealMacrosTipClickListener = new OnClickListener() {
        public void onClick(View view) {
            DiaryAdapter.this.diaryAdapterItemActionListener.onMealHeaderTipCloseClick((MealHeaderTip) view.getTag());
        }
    };
    /* access modifiers changed from: private */
    public OnClickListener onEntryClickListener = new OnClickListener() {
        public void onClick(View view) {
            DiaryAdapter.this.diaryAdapterItemActionListener.onEntryClick(DiaryAdapter.this.currentDiaryDay, (DatabaseObject) view.getTag(), view);
        }
    };
    /* access modifiers changed from: private */
    public OnLongClickListener onEntryLongClickListener = new OnLongClickListener() {
        public boolean onLongClick(View view) {
            return DiaryAdapter.this.diaryAdapterItemActionListener.onEntryLongClick((DatabaseObject) view.getTag());
        }
    };
    private OnClickListener onMealCaloriesClickListener = new OnClickListener() {
        public void onClick(View view) {
            DiaryAdapter.this.diaryAdapterItemActionListener.onMealCaloriesClick((SectionHeaderItem) view.getTag());
        }
    };
    @Inject
    Lazy<PremiumService> premiumService;
    /* access modifiers changed from: private */
    public OnCheckedChangeListener sectionHeaderOnCheckedChangeListener = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            SectionHeaderItem sectionHeaderItem = (SectionHeaderItem) compoundButton.getTag();
            sectionHeaderItem.setIsSelected(z);
            DiaryAdapter.this.diaryAdapterItemActionListener.onSectionHeaderCheckChanged(DiaryAdapter.this.currentDiaryDay, sectionHeaderItem.getHeaderName(), z);
        }
    };
    OnClickListener sectionHeaderOnClickListener = new OnClickListener() {
        public void onClick(View view) {
            if (DiaryAdapter.this.inEditMode) {
                ((CompoundButton) view.getTag()).toggle();
            } else if (DiaryAdapter.this.shouldDisplayDiaryMealMacros()) {
                DiaryAdapter.this.diaryAdapterItemActionListener.onToggleMealMacrosUnitClick();
            }
        }
    };
    @Inject
    Lazy<Session> session;
    @Inject
    Lazy<StepService> stepService;
    /* access modifiers changed from: private */
    public Set<Integer> timestampPositions;
    @Inject
    Lazy<UserEnergyService> userEnergyService;

    class ButtonsFooterViewHolder extends RecyclerViewHolder<DiaryAdapterItem> {
        @BindView(2131361990)
        View completeThisEntry;
        @BindView(2131362021)
        View notesBtn;
        @BindView(2131362023)
        View nutritionBtn;
        private OnClickListener onClickListener = new OnClickListener() {
            /* JADX WARNING: Removed duplicated region for block: B:11:0x0040  */
            /* JADX WARNING: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onClick(android.view.View r2) {
                /*
                    r1 = this;
                    int r2 = r2.getId()
                    r0 = 2131361990(0x7f0a00c6, float:1.8343748E38)
                    if (r2 == r0) goto L_0x0030
                    r0 = 2131362021(0x7f0a00e5, float:1.834381E38)
                    if (r2 == r0) goto L_0x0022
                    r0 = 2131362023(0x7f0a00e7, float:1.8343815E38)
                    if (r2 == r0) goto L_0x0014
                    goto L_0x003d
                L_0x0014:
                    com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter$ButtonsFooterViewHolder r2 = com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter.ButtonsFooterViewHolder.this
                    com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter r2 = com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter.this
                    com.myfitnesspal.feature.diary.listener.DiaryAdapterItemActionListener r2 = r2.diaryAdapterItemActionListener
                    java.lang.String r0 = "bottom"
                    r2.onNutritionClick(r0)
                    goto L_0x003d
                L_0x0022:
                    java.lang.String r2 = "diary_tap_notes"
                    com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter$ButtonsFooterViewHolder r0 = com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter.ButtonsFooterViewHolder.this
                    com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter r0 = com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter.this
                    com.myfitnesspal.feature.diary.listener.DiaryAdapterItemActionListener r0 = r0.diaryAdapterItemActionListener
                    r0.onNotesClick()
                    goto L_0x003e
                L_0x0030:
                    com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter$ButtonsFooterViewHolder r2 = com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter.ButtonsFooterViewHolder.this
                    com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter r2 = com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter.this
                    com.myfitnesspal.feature.diary.listener.DiaryAdapterItemActionListener r2 = r2.diaryAdapterItemActionListener
                    java.lang.String r0 = "bottom_button"
                    r2.onCompleteEntryClick(r0)
                L_0x003d:
                    r2 = 0
                L_0x003e:
                    if (r2 == 0) goto L_0x004f
                    com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter$ButtonsFooterViewHolder r0 = com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter.ButtonsFooterViewHolder.this
                    com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter r0 = com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter.this
                    dagger.Lazy<com.myfitnesspal.shared.service.analytics.AnalyticsService> r0 = r0.analyticsService
                    java.lang.Object r0 = r0.get()
                    com.myfitnesspal.shared.service.analytics.AnalyticsService r0 = (com.myfitnesspal.shared.service.analytics.AnalyticsService) r0
                    r0.reportEvent(r2)
                L_0x004f:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter.ButtonsFooterViewHolder.AnonymousClass1.onClick(android.view.View):void");
            }
        };

        public ButtonsFooterViewHolder(ViewGroup viewGroup) {
            super(R.layout.complete_this_entry, viewGroup);
        }

        public void setData(DiaryAdapterItem diaryAdapterItem, int i) {
            this.completeThisEntry.setOnClickListener(this.onClickListener);
            this.nutritionBtn.setOnClickListener(this.onClickListener);
            this.notesBtn.setOnClickListener(this.onClickListener);
        }
    }

    public class ButtonsFooterViewHolder_ViewBinding implements Unbinder {
        private ButtonsFooterViewHolder target;

        @UiThread
        public ButtonsFooterViewHolder_ViewBinding(ButtonsFooterViewHolder buttonsFooterViewHolder, View view) {
            this.target = buttonsFooterViewHolder;
            buttonsFooterViewHolder.completeThisEntry = Utils.findRequiredView(view, R.id.btnComplete, "field 'completeThisEntry'");
            buttonsFooterViewHolder.nutritionBtn = Utils.findRequiredView(view, R.id.btn_nutrition, "field 'nutritionBtn'");
            buttonsFooterViewHolder.notesBtn = Utils.findRequiredView(view, R.id.btn_notes, "field 'notesBtn'");
        }

        @CallSuper
        public void unbind() {
            ButtonsFooterViewHolder buttonsFooterViewHolder = this.target;
            if (buttonsFooterViewHolder != null) {
                this.target = null;
                buttonsFooterViewHolder.completeThisEntry = null;
                buttonsFooterViewHolder.nutritionBtn = null;
                buttonsFooterViewHolder.notesBtn = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    class EntryViewHolder extends RecyclerViewHolder<DiaryAdapterItem> {
        @Nullable
        @BindView(2131362339)
        View divider;
        private final LayoutParams dividerLayoutParams;
        @Nullable
        @BindView(2131362431)
        CompoundButton editCheckBox;
        @Nullable
        @BindView(2131362484)
        ImageView entryImage;
        InsightViewBinder insightViewHolder;
        @Nullable
        @BindView(2131362819)
        View insightsContainer;
        @Nullable
        @BindView(2131362306)
        View llDescriptionContainer;
        @Nullable
        @BindView(2131362628)
        View parentContainer;
        @BindView(2131362489)
        TextView timestampView;
        @Nullable
        @BindView(2131363999)
        TextView txtCalories;
        @Nullable
        @BindView(2131364015)
        TextView txtItemDescription;
        @Nullable
        @BindView(2131364016)
        TextView txtItemDetails;

        EntryViewHolder(int i, ViewGroup viewGroup) {
            super(i, viewGroup);
            View view = this.divider;
            this.dividerLayoutParams = view != null ? (LayoutParams) view.getLayoutParams() : null;
        }

        private EntryViewHolder(DiaryAdapter diaryAdapter, ViewGroup viewGroup) {
            this((int) R.layout.diary_entry, viewGroup);
        }

        public void setData(DiaryAdapterItem diaryAdapterItem, int i) {
            DiaryEntryItem diaryEntryItem = (DiaryEntryItem) diaryAdapterItem;
            DatabaseObject databaseObject = diaryEntryItem.getDatabaseObject();
            int sectionType = diaryEntryItem.getSectionType();
            ViewUtils.setVisible(this.txtItemDetails);
            ViewUtils.setVisible(DiaryAdapter.this.inEditMode, this.editCheckBox);
            ViewUtils.setVisible(!DiaryAdapter.this.inEditMode, this.txtCalories);
            stretchDividerToFullWidth();
            setEntryIconVisible(false);
            this.editCheckBox.setOnCheckedChangeListener(null);
            ViewUtils.setVisible(false, this.insightsContainer);
            View view = this.divider;
            if (view != null) {
                ViewUtils.setVisible(true, view);
            }
            this.txtItemDetails.setTag(null);
            if (DiaryAdapter.this.checkedItems != null) {
                this.editCheckBox.setChecked(DiaryAdapter.this.checkedItems.contains(Long.valueOf(databaseObject.getLocalId())));
            }
            this.editCheckBox.setTag(databaseObject);
            this.editCheckBox.setOnCheckedChangeListener(DiaryAdapter.this.diaryItemOnCheckedChangeListener);
            if (DiaryAdapter.this.inEditMode || ((sectionType == 4 && !DiaryAdapter.this.isSponsoredWaterEnabled) || sectionType == 5)) {
                drawEditMode(diaryEntryItem);
            }
            if (sectionType == 2) {
                bindAsFoodEntry(diaryEntryItem);
                updateInsights(diaryEntryItem);
            } else if (sectionType == 3 || sectionType == 6 || sectionType == 7) {
                bindAsExerciseEntry(diaryEntryItem);
            } else if (sectionType == 4) {
                bindAsWaterEntry();
            } else if (sectionType == 5) {
                bindAsNoteEntry(diaryEntryItem);
            }
            animateInIfNecessary(diaryEntryItem);
            View parent = getParent();
            parent.setTag(databaseObject);
            parent.setOnClickListener(DiaryAdapter.this.onEntryClickListener);
            parent.setOnLongClickListener(DiaryAdapter.this.onEntryLongClickListener);
            if (VERSION.SDK_INT >= 23) {
                parent.setOnContextClickListener(new OnContextClickListener() {
                    public final boolean onContextClick(View view) {
                        return DiaryAdapter.this.diaryAdapterItemActionListener.onEntryLongClick((DatabaseObject) view.getTag());
                    }
                });
            }
        }

        private void animateInIfNecessary(DiaryEntryItem diaryEntryItem) {
            DatabaseObject databaseObject = diaryEntryItem.getDatabaseObject();
            if (DiaryAdapter.this.diaryDelegate.isMostRecentlyAddedEntry(databaseObject)) {
                Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_left_100_medium);
                loadAnimation.setStartOffset(500);
                this.parentContainer.startAnimation(loadAnimation);
                DiaryAdapter.this.diaryDelegate.removeFromMostRecentlyAddedEntries(databaseObject);
            }
        }

        private void drawEditMode(DiaryEntryItem diaryEntryItem) {
            if (diaryEntryItem.isLastItemOfSection()) {
                View view = this.divider;
                if (view != null) {
                    ViewUtils.setVisible(false, view);
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void bindAsFoodEntry(DiaryEntryItem diaryEntryItem) {
            FoodEntry foodEntry = (FoodEntry) diaryEntryItem.getDatabaseObject();
            UserEnergyService userEnergyService = (UserEnergyService) DiaryAdapter.this.userEnergyService.get();
            this.txtItemDescription.setText(((LocalizedStringsUtil) DiaryAdapter.this.localizedStringsUtil.get()).getMealNameString(foodEntry.getFood().getDescription(), userEnergyService));
            String foodDetails = getFoodDetails(foodEntry, userEnergyService);
            ViewUtils.setVisible(Strings.notEmpty(foodDetails), this.txtItemDetails);
            this.txtItemDetails.setText(foodDetails);
            this.txtCalories.setText(userEnergyService.getRoundedCurrentEnergyAsString((double) foodEntry.getCaloriesValue(), true));
            showTimestamp(diaryEntryItem);
        }

        private void bindAsExerciseEntry(DiaryEntryItem diaryEntryItem) {
            ExerciseEntry exerciseEntry = (ExerciseEntry) diaryEntryItem.getDatabaseObject();
            double calories = (double) exerciseEntry.getCalories();
            String roundedCurrentEnergyAsString = ((UserEnergyService) DiaryAdapter.this.userEnergyService.get()).getRoundedCurrentEnergyAsString(calories, false);
            if (!exerciseEntry.getExercise().isCalorieAdjustmentExercise() && NumberUtils.isEffectivelyZero(calories)) {
                roundedCurrentEnergyAsString = "—";
            }
            String descriptionName = ((ExerciseStringService) DiaryAdapter.this.exerciseStringService.get()).getDescriptionName(exerciseEntry.getExercise());
            if (exerciseEntry.getExercise().isCalorieAdjustmentExercise()) {
                setupStepsDetails(exerciseEntry, descriptionName);
            } else {
                this.txtItemDescription.setText(descriptionName);
                this.txtItemDetails.setText(((ExerciseStringService) DiaryAdapter.this.exerciseStringService.get()).getSummaryDescription(exerciseEntry));
            }
            checkShowExerciseEntryImage(exerciseEntry);
            this.txtCalories.setText(Strings.toString(roundedCurrentEnergyAsString));
            this.timestampView.setVisibility(8);
        }

        private void setEntryIconVisible(boolean z) {
            if (this.entryImage != null) {
                RelativeLayout.LayoutParams layoutParams = null;
                if (this.parentContainer instanceof RelativeLayout) {
                    layoutParams = (RelativeLayout.LayoutParams) this.llDescriptionContainer.getLayoutParams();
                }
                if (z) {
                    ViewUtils.setVisible(this.entryImage);
                    if (layoutParams != null) {
                        layoutParams.addRule(1, R.id.entryImage);
                        layoutParams.addRule(9, 0);
                        this.llDescriptionContainer.setLayoutParams(layoutParams);
                        return;
                    }
                    return;
                }
                ViewUtils.setGone(this.entryImage);
                if (layoutParams != null) {
                    layoutParams.addRule(1, 0);
                    layoutParams.addRule(9, 1);
                    this.llDescriptionContainer.setLayoutParams(layoutParams);
                }
            }
        }

        private void showTimestamp(DiaryEntryItem diaryEntryItem) {
            if (DiaryAdapter.this.inEditMode || DiaryAdapter.this.isForRemoteUser || !((PremiumService) DiaryAdapter.this.premiumService.get()).isFeatureSubscribed(PremiumFeature.FoodTimestamps) || !((LocalSettingsService) DiaryAdapter.this.localSettingsService.get()).shouldShowFoodTimestamps()) {
                this.timestampView.setVisibility(8);
                return;
            }
            FoodEntry foodEntry = (FoodEntry) diaryEntryItem.getDatabaseObject();
            Date entryTime = foodEntry.getEntryTime();
            ViewUtils.increaseHitRectBy((int) this.context.getResources().getDimension(R.dimen.diary_timestamp_hit_rect_bounds), this.timestampView);
            if (DiaryAdapter.this.timestampPositions.contains(Integer.valueOf(getAdapterPosition()))) {
                this.timestampView.setVisibility(0);
                this.timestampView.setOnClickListener(new OnClickListener(foodEntry, entryTime) {
                    private final /* synthetic */ FoodEntry f$1;
                    private final /* synthetic */ Date f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void onClick(View view) {
                        DiaryAdapter.this.diaryAdapterItemActionListener.onTimestampClick(this.f$1.getMealName(), this.f$2);
                    }
                });
                if (entryTime != null) {
                    this.timestampView.setText(DateTimeUtils.localeFormattedTime(this.context, entryTime));
                } else {
                    this.timestampView.setText(R.string.timestamp_value_no_time);
                }
            } else {
                this.timestampView.setVisibility(8);
            }
            if (DiaryAdapter.this.timestampPositions.contains(Integer.valueOf(getAdapterPosition() + 1)) || diaryEntryItem.isLastItemOfSection()) {
                stretchDividerToFullWidth();
            } else {
                setDividerMargin((int) this.context.getResources().getDimension(R.dimen.item_horizontal_margin));
            }
        }

        private boolean isUacfExerciseEntry(ExerciseEntry exerciseEntry) {
            String extraPropertyNamed = exerciseEntry.extraPropertyNamed("source");
            return Strings.notEmpty(extraPropertyNamed) && (extraPropertyNamed.equals(DiaryAdapter.RECORD_CLIENT_ID) || extraPropertyNamed.startsWith(DiaryAdapter.MAPMY_CLIENT_ID_PREFIX));
        }

        private void checkShowExerciseEntryImage(ExerciseEntry exerciseEntry) {
            if (this.entryImage == null) {
                setEntryIconVisible(false);
            } else if (isUacfExerciseEntry(exerciseEntry)) {
                setEntryIconVisible(false);
            } else {
                String extraPropertyNamed = exerciseEntry.extraPropertyNamed("app_id");
                String extraPropertyNamed2 = exerciseEntry.extraPropertyNamed("source");
                MfpPlatformApp findByAppId = ((AppGalleryService) DiaryAdapter.this.appGalleryService.get()).findByAppId(extraPropertyNamed);
                if (findByAppId == null) {
                    findByAppId = ((AppGalleryService) DiaryAdapter.this.appGalleryService.get()).findByClientId(extraPropertyNamed2);
                }
                RequestBuilder requestBuilder = null;
                boolean z = true;
                if (findByAppId != null) {
                    requestBuilder = Glide.with(getContext()).load(findByAppId.getIconImage().getFilename());
                } else if (GoogleFitStepsUtils.isGoogleFitStepSource(extraPropertyNamed2, extraPropertyNamed)) {
                    requestBuilder = Glide.with(getContext()).load(Integer.valueOf(R.drawable.ic_google_fit_steps));
                } else {
                    z = false;
                }
                if (requestBuilder != null) {
                    requestBuilder.apply(new RequestOptions().placeholder((int) R.drawable.ic_activity_placeholder_icon).error(R.drawable.ic_activity_placeholder_icon)).into(this.entryImage);
                }
                setEntryIconVisible(z);
            }
        }

        private void bindAsWaterEntry() {
            LocalizedFluid localizedWater = DiaryAdapter.this.currentDiaryDay.getLocalizedWater();
            this.txtItemDescription.setText(getContext().getResources().getString(R.string.water));
            this.txtItemDetails.setText(LocalizedFluid.getDisplayString(getContext(), localizedWater, LocalizedFluid.getUserCurrentWaterUnit((Session) DiaryAdapter.this.session.get())));
            this.txtCalories.setText("-");
            ViewUtils.setGone(this.timestampView);
        }

        private void bindAsNoteEntry(DiaryEntryItem diaryEntryItem) {
            DiaryNote diaryNote = (DiaryNote) diaryEntryItem.getDatabaseObject();
            Resources resources = getContext().getResources();
            if (diaryNote != null) {
                this.txtItemDescription.setText(resources.getString(diaryNote.typeDescription()));
                this.txtItemDetails.setText(diaryNote.bodyTruncatedTo(40));
            } else {
                this.txtItemDescription.setText("???");
                this.txtItemDetails.setText("???");
            }
            this.txtCalories.setText("");
            this.timestampView.setVisibility(8);
        }

        /* JADX WARNING: Removed duplicated region for block: B:14:0x0054  */
        /* JADX WARNING: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void updateInsights(com.myfitnesspal.shared.model.v1.DiaryEntryItem r8) {
            /*
                r7 = this;
                com.myfitnesspal.shared.model.v1.DatabaseObject r8 = r8.getDatabaseObject()
                com.myfitnesspal.shared.model.v1.FoodEntry r8 = (com.myfitnesspal.shared.model.v1.FoodEntry) r8
                com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter r0 = com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter.this
                java.util.List r0 = r0.foodInsights
                boolean r0 = com.uacf.core.util.CollectionUtils.notEmpty(r0)
                r1 = 1
                r2 = 0
                if (r0 == 0) goto L_0x0051
                com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter r0 = com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter.this
                boolean r0 = r0.inEditMode
                if (r0 != 0) goto L_0x0051
                com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter r0 = com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter.this
                java.util.List r0 = r0.foodInsights
                com.myfitnesspal.feature.diary.ui.adapter.-$$Lambda$DiaryAdapter$EntryViewHolder$vX5fV2UGGPVj1If-5tmikC1Ti1c r3 = new com.myfitnesspal.feature.diary.ui.adapter.-$$Lambda$DiaryAdapter$EntryViewHolder$vX5fV2UGGPVj1If-5tmikC1Ti1c
                r3.<init>()
                java.lang.Object r0 = com.uacf.core.util.Enumerable.firstOrDefault(r0, r3)
                com.myfitnesspal.shared.api.request.FoodAnalyzerResponseData r0 = (com.myfitnesspal.shared.api.request.FoodAnalyzerResponseData) r0
                if (r0 == 0) goto L_0x0051
                com.myfitnesspal.shared.ui.view.InsightViewBinder r3 = r7.insightViewHolder
                if (r3 != 0) goto L_0x0032
                r2 = 1
            L_0x0032:
                if (r3 != 0) goto L_0x004d
                com.myfitnesspal.shared.ui.view.InsightViewHolderFAB r3 = new com.myfitnesspal.shared.ui.view.InsightViewHolderFAB
                android.view.View r4 = r7.getParent()
                com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter r5 = com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter.this
                dagger.Lazy<com.myfitnesspal.shared.service.foods.FoodService> r5 = r5.foodService
                com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter r6 = com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter.this
                dagger.Lazy<com.squareup.otto.Bus> r6 = r6.bus
                java.lang.Object r6 = r6.get()
                com.squareup.otto.Bus r6 = (com.squareup.otto.Bus) r6
                r3.<init>(r4, r5, r6, r8)
                r7.insightViewHolder = r3
            L_0x004d:
                r3.setFoodAnalyzerData(r0, r2)
                goto L_0x0052
            L_0x0051:
                r1 = 0
            L_0x0052:
                if (r1 != 0) goto L_0x005e
                com.myfitnesspal.shared.ui.view.InsightViewBinder r8 = r7.insightViewHolder
                if (r8 == 0) goto L_0x005e
                r8.hideInsightsContainer()
                r8 = 0
                r7.insightViewHolder = r8
            L_0x005e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter.EntryViewHolder.updateInsights(com.myfitnesspal.shared.model.v1.DiaryEntryItem):void");
        }

        static /* synthetic */ Boolean lambda$updateInsights$2(FoodEntry foodEntry, FoodAnalyzerResponseData foodAnalyzerResponseData) throws RuntimeException {
            return Boolean.valueOf(foodAnalyzerResponseData != null && Strings.notEmpty(foodAnalyzerResponseData.getFoodId()) && foodAnalyzerResponseData.getAssociatedFoodEntryLocalId() > 0 && foodAnalyzerResponseData.getAssociatedFoodEntryLocalId() == foodEntry.getLocalId());
        }

        /* access modifiers changed from: 0000 */
        public String getFoodDetails(FoodEntry foodEntry, UserEnergyService userEnergyService) {
            Food food = foodEntry.getFood();
            if (food.isQuickAddFood()) {
                return quickAddFoodDetails(food);
            }
            return food.isLegacyQuickAddedCalories() ? userEnergyService.getQuickAddedDescription(foodEntry) : userEnergyService.getDescription(foodEntry, false);
        }

        /* access modifiers changed from: 0000 */
        public String quickAddFoodDetails(Food food) {
            Context context = getContext();
            NutritionalValues nutritionalValues = food.getNutritionalValues();
            ArrayList arrayList = new ArrayList();
            String string = context.getString(R.string.gram_abbreviation);
            if (nutritionalValues.getCarbohydrate() > BitmapDescriptorFactory.HUE_RED) {
                arrayList.add(String.format("%s %s%s", new Object[]{context.getString(R.string.carbs), Float.valueOf(nutritionalValues.getCarbohydrate()), string}));
            }
            if (nutritionalValues.getFat() > BitmapDescriptorFactory.HUE_RED) {
                arrayList.add(String.format("%s %s%s", new Object[]{context.getString(R.string.fat), Float.valueOf(nutritionalValues.getFat()), string}));
            }
            if (nutritionalValues.getProtein() > BitmapDescriptorFactory.HUE_RED) {
                arrayList.add(String.format("%s %s%s", new Object[]{context.getString(R.string.protein), Float.valueOf(nutritionalValues.getProtein()), string}));
            }
            return Strings.join(", ", (Collection<T>) arrayList);
        }

        private void setupStepsDetails(ExerciseEntry exerciseEntry, String str) {
            MfpExerciseMetadataForSteps stepsData = exerciseEntry.getStepsData();
            String clientId = stepsData != null ? stepsData.getClientId() : null;
            if (Strings.notEmpty(clientId)) {
                setupStepsClient(clientId, exerciseEntry);
                return;
            }
            this.txtItemDetails.setText(getContext().getString(R.string.calorie_adjustment_exercise_entry_text));
            this.txtItemDescription.setText(((ExerciseStringService) DiaryAdapter.this.exerciseStringService.get()).getAdjustedExerciseName(str, ((UserEnergyService) DiaryAdapter.this.userEnergyService.get()).isCalories()));
        }

        private void setupStepsClient(String str, ExerciseEntry exerciseEntry) {
            Resources resources = getContext().getResources();
            MfpExerciseMetadataForSteps stepsData = exerciseEntry.getStepsData();
            boolean isGoogleFitStepSource = GoogleFitStepsUtils.isGoogleFitStepSource(exerciseEntry);
            if (Strings.equals(str, Extras.MFP_MOBILE_IOS) || isGoogleFitStepSource) {
                int steps = stepsData.getSteps();
                if (isGoogleFitStepSource && DateTimeUtils.isDateToday(DiaryAdapter.this.currentDiaryDay.getDate())) {
                    steps = Math.max(steps, ((SharedPreferences) DiaryAdapter.this.fitStepsSharedPreferences.get()).getInt(GoogleFitConstants.SharedPreferences.LAST_SYNC_TODAYS_STEP_COUNT, 0));
                }
                this.txtItemDetails.setText(String.format(resources.getString(R.string.steps_from_device), new Object[]{NumberUtils.localeStringFromInt(steps)}));
                TextView textView = this.txtItemDescription;
                ExerciseStringService exerciseStringService = (ExerciseStringService) DiaryAdapter.this.exerciseStringService.get();
                String localizedString = ((LocalizedStringsUtil) DiaryAdapter.this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.APP_ADJUSTMENT_LABEL, DiaryAdapter.this.userEnergyService.get());
                Object[] objArr = new Object[1];
                objArr[0] = resources.getString(isGoogleFitStepSource ? R.string.google_fit : R.string.iPhone);
                textView.setText(exerciseStringService.getAdjustedExerciseName(String.format(localizedString, objArr), ((UserEnergyService) DiaryAdapter.this.userEnergyService.get()).isCalories()));
                return;
            }
            MfpStepSource stepSource = ((StepService) DiaryAdapter.this.stepService.get()).getStepSource(str);
            MfpPlatformApp mfpPlatformApp = null;
            if (Strings.notEmpty((Object) stepSource)) {
                mfpPlatformApp = ((AppGalleryService) DiaryAdapter.this.appGalleryService.get()).findByAppId(stepSource.getAppId());
                if (mfpPlatformApp == null) {
                    mfpPlatformApp = ((AppGalleryService) DiaryAdapter.this.appGalleryService.get()).findByClientId(stepSource.getClientId());
                }
            }
            bindStepsCalorieAdjustment(stepsData, mfpPlatformApp, str);
        }

        private void bindStepsCalorieAdjustment(MfpExerciseMetadataForSteps mfpExerciseMetadataForSteps, MfpPlatformApp mfpPlatformApp, String str) {
            Resources resources = this.context.getResources();
            String name = mfpPlatformApp != null ? mfpPlatformApp.getName() : null;
            if (Strings.isEmpty(name)) {
                name = str.toUpperCase();
            }
            this.txtItemDetails.setText(String.format(resources.getString(R.string.steps_from_device), new Object[]{NumberUtils.localeStringFromInt(mfpExerciseMetadataForSteps.getSteps())}));
            this.txtItemDescription.setText(((ExerciseStringService) DiaryAdapter.this.exerciseStringService.get()).getAdjustedExerciseName(String.format(((LocalizedStringsUtil) DiaryAdapter.this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.APP_ADJUSTMENT_LABEL, DiaryAdapter.this.userEnergyService.get()), new Object[]{name}), ((UserEnergyService) DiaryAdapter.this.userEnergyService.get()).isCalories()));
        }

        private void stretchDividerToFullWidth() {
            LayoutParams layoutParams = this.dividerLayoutParams;
            if (layoutParams != null) {
                layoutParams.setMarginStart(0);
                this.dividerLayoutParams.setMarginEnd(0);
            }
        }

        private void setDividerMargin(int i) {
            this.dividerLayoutParams.setMarginStart(i);
            this.dividerLayoutParams.setMarginEnd(i);
        }
    }

    public class EntryViewHolder_ViewBinding implements Unbinder {
        private EntryViewHolder target;

        @UiThread
        public EntryViewHolder_ViewBinding(EntryViewHolder entryViewHolder, View view) {
            this.target = entryViewHolder;
            entryViewHolder.parentContainer = view.findViewById(R.id.foodSearchViewFoodItem);
            entryViewHolder.txtItemDescription = (TextView) Utils.findOptionalViewAsType(view, R.id.txtItemDescription, "field 'txtItemDescription'", TextView.class);
            entryViewHolder.txtItemDetails = (TextView) Utils.findOptionalViewAsType(view, R.id.txtItemDetails, "field 'txtItemDetails'", TextView.class);
            entryViewHolder.txtCalories = (TextView) Utils.findOptionalViewAsType(view, R.id.txtCalories, "field 'txtCalories'", TextView.class);
            entryViewHolder.editCheckBox = (CompoundButton) Utils.findOptionalViewAsType(view, R.id.edit_checkbox, "field 'editCheckBox'", CompoundButton.class);
            entryViewHolder.insightsContainer = view.findViewById(R.id.insight_container);
            entryViewHolder.divider = view.findViewById(R.id.divider);
            entryViewHolder.entryImage = (ImageView) Utils.findOptionalViewAsType(view, R.id.entryImage, "field 'entryImage'", ImageView.class);
            entryViewHolder.llDescriptionContainer = view.findViewById(R.id.detailsLinearLayout);
            entryViewHolder.timestampView = (TextView) Utils.findRequiredViewAsType(view, R.id.entry_timestamp, "field 'timestampView'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            EntryViewHolder entryViewHolder = this.target;
            if (entryViewHolder != null) {
                this.target = null;
                entryViewHolder.parentContainer = null;
                entryViewHolder.txtItemDescription = null;
                entryViewHolder.txtItemDetails = null;
                entryViewHolder.txtCalories = null;
                entryViewHolder.editCheckBox = null;
                entryViewHolder.insightsContainer = null;
                entryViewHolder.divider = null;
                entryViewHolder.entryImage = null;
                entryViewHolder.llDescriptionContainer = null;
                entryViewHolder.timestampView = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    class PromoViewHolder extends RecyclerViewHolder<DiaryAdapterItem> {
        private OnClickListener onPromoCallActionClickListener = new OnClickListener() {
            public void onClick(View view) {
                PremiumFeature premiumFeature = (PremiumFeature) view.getTag();
                if (premiumFeature == PremiumFeature.MealGoals) {
                    ((NewsFeedAnalyticsHelper) DiaryAdapter.this.newsFeedAnalyticsHelper.get()).reportHeroCardTapped(NewsFeedAnalyticsHelper.NON_PREMIUM_MEAL_GOAL_CARD_TYPE, -1);
                }
                DiaryAdapter.this.diaryAdapterItemActionListener.onPromoCallActionClick(premiumFeature);
            }
        };
        @BindView(2131363341)
        Button promoCallAction;
        @BindView(2131363342)
        ImageView promoDismissAction;
        @BindView(2131363343)
        ImageView promoImage;
        @BindView(2131363344)
        TextView promoText;
        @BindView(2131363345)
        TextView promoTitle;

        PromoViewHolder(ViewGroup viewGroup) {
            super(R.layout.diary_day_promo, viewGroup);
        }

        public void setData(DiaryAdapterItem diaryAdapterItem, int i) {
            DiaryPromoItem diaryPromoItem = (DiaryPromoItem) diaryAdapterItem;
            this.promoTitle.setText(diaryPromoItem.getTitle());
            this.promoText.setText(diaryPromoItem.getMessage());
            this.promoImage.setImageResource(diaryPromoItem.getImageId());
            this.promoDismissAction.setOnClickListener(diaryPromoItem.onDismissActionListener());
            ViewUtils.increaseHitRectBy(this.context.getResources().getDimensionPixelSize(R.dimen.material_padding_12), this.promoDismissAction);
            if (((PremiumService) DiaryAdapter.this.premiumService.get()).isFeatureSubscribed(diaryPromoItem.getPremiumFeature())) {
                this.promoCallAction.setText(diaryPromoItem.getAction());
                this.promoCallAction.setOnClickListener(diaryPromoItem.getOnPositiveActionListener());
            } else {
                this.promoCallAction.setText(R.string.go_premium);
                this.promoCallAction.setTag(diaryPromoItem.getPremiumFeature());
                this.promoCallAction.setOnClickListener(this.onPromoCallActionClickListener);
            }
            if (DiaryAdapter.this instanceof DiaryLandscapeAdapter) {
                View parent = getParent();
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) parent.getLayoutParams();
                layoutParams.bottomMargin = getContext().getResources().getDimensionPixelSize(R.dimen.diary_card_vertical_margin);
                parent.setLayoutParams(layoutParams);
            }
        }
    }

    public class PromoViewHolder_ViewBinding implements Unbinder {
        private PromoViewHolder target;

        @UiThread
        public PromoViewHolder_ViewBinding(PromoViewHolder promoViewHolder, View view) {
            this.target = promoViewHolder;
            promoViewHolder.promoTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.promo_title, "field 'promoTitle'", TextView.class);
            promoViewHolder.promoText = (TextView) Utils.findRequiredViewAsType(view, R.id.promo_text, "field 'promoText'", TextView.class);
            promoViewHolder.promoCallAction = (Button) Utils.findRequiredViewAsType(view, R.id.promo_call_action, "field 'promoCallAction'", Button.class);
            promoViewHolder.promoImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.promo_image, "field 'promoImage'", ImageView.class);
            promoViewHolder.promoDismissAction = (ImageView) Utils.findRequiredViewAsType(view, R.id.promo_dismiss, "field 'promoDismissAction'", ImageView.class);
        }

        @CallSuper
        public void unbind() {
            PromoViewHolder promoViewHolder = this.target;
            if (promoViewHolder != null) {
                this.target = null;
                promoViewHolder.promoTitle = null;
                promoViewHolder.promoText = null;
                promoViewHolder.promoCallAction = null;
                promoViewHolder.promoImage = null;
                promoViewHolder.promoDismissAction = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    class SectionFooterViewHolder extends RecyclerViewHolder<DiaryAdapterItem> {
        @BindView(2131361866)
        TextView addFood;
        @BindView(2131362649)
        View footerContainer;
        @BindView(2131363069)
        View more;
        @BindView(2131363526)
        View saveMeal;

        SectionFooterViewHolder(ViewGroup viewGroup) {
            super(R.layout.diary_row_actions, viewGroup);
        }

        public void setData(DiaryAdapterItem diaryAdapterItem, int i) {
            boolean z;
            int i2;
            DiaryRowActionItem diaryRowActionItem = (DiaryRowActionItem) diaryAdapterItem;
            int i3 = 0;
            this.addFood.setCompoundDrawablesWithIntrinsicBounds(DiaryAdapter.this.isForRemoteUser ? R.drawable.ic_card_action_copy_black_24dp : R.drawable.ic_card_action_add_black_24dp, 0, 0, 0);
            String headerName = diaryRowActionItem.getHeaderName();
            char c = 65535;
            int hashCode = headerName.hashCode();
            if (hashCode != 324362706) {
                if (hashCode == 2120967672 && headerName.equals("Exercise")) {
                    c = 0;
                }
            } else if (headerName.equals(Report.WATER_CONSUMPTION)) {
                c = 1;
            }
            switch (c) {
                case 0:
                    this.addFood.setText(DiaryAdapter.this.isForRemoteUser ? R.string.copy_exercises : R.string.add_exercise);
                    break;
                case 1:
                    this.addFood.setText(R.string.add_water);
                    break;
                default:
                    TextView textView = this.addFood;
                    if (DiaryAdapter.this.isForRemoteUser) {
                        i2 = R.string.copy_entries;
                    } else {
                        i2 = R.string.addFood;
                    }
                    textView.setText(i2);
                    z = true;
                    break;
            }
            z = false;
            ViewUtils.setVisible(DiaryAdapter.this.isForRemoteUser && z, this.saveMeal);
            ViewUtils.setVisible(!DiaryAdapter.this.isForRemoteUser, this.more);
            setListenerAndTag(this.addFood, diaryRowActionItem);
            setListenerAndTag(this.saveMeal, diaryRowActionItem);
            setListenerAndTag(this.more, diaryRowActionItem);
            if (DiaryAdapter.this.isForRemoteUser && this.footerContainer != null) {
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) getParent().getLayoutParams();
                if (i == DiaryAdapter.this.getItemCount() - 1) {
                    i3 = getContext().getResources().getDimensionPixelSize(R.dimen.diary_card_vertical_margin);
                }
                layoutParams.bottomMargin = i3;
            }
        }

        private void setListenerAndTag(View view, Object obj) {
            view.setOnClickListener(DiaryAdapter.this.bottomRowOnClickListener);
            view.setTag(obj);
        }
    }

    public class SectionFooterViewHolder_ViewBinding implements Unbinder {
        private SectionFooterViewHolder target;

        @UiThread
        public SectionFooterViewHolder_ViewBinding(SectionFooterViewHolder sectionFooterViewHolder, View view) {
            this.target = sectionFooterViewHolder;
            sectionFooterViewHolder.addFood = (TextView) Utils.findRequiredViewAsType(view, R.id.add_food, "field 'addFood'", TextView.class);
            sectionFooterViewHolder.saveMeal = Utils.findRequiredView(view, R.id.save_meal, "field 'saveMeal'");
            sectionFooterViewHolder.more = Utils.findRequiredView(view, R.id.more, "field 'more'");
            sectionFooterViewHolder.footerContainer = Utils.findRequiredView(view, R.id.footer_container, "field 'footerContainer'");
        }

        @CallSuper
        public void unbind() {
            SectionFooterViewHolder sectionFooterViewHolder = this.target;
            if (sectionFooterViewHolder != null) {
                this.target = null;
                sectionFooterViewHolder.addFood = null;
                sectionFooterViewHolder.saveMeal = null;
                sectionFooterViewHolder.more = null;
                sectionFooterViewHolder.footerContainer = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    class SectionHeaderViewHolder extends RecyclerViewHolder<DiaryAdapterItem> {
        @BindView(2131363041)
        CompoundButton checkBox;
        @BindView(2131362339)
        View divider;
        @BindView(2131363584)
        View headerContainer;
        @BindView(2131363029)
        TextView mealMacrosTextView;
        @BindView(2131363831)
        ViewGroup tipContainer;
        @BindView(2131364034)
        TextView txtSectionCalories;
        @BindView(2131364035)
        TextView txtSectionHeader;
        @BindView(2131364195)
        ViewGroup waterAdContainer;

        SectionHeaderViewHolder(ViewGroup viewGroup) {
            super(R.layout.section_header, viewGroup);
        }

        public void setData(DiaryAdapterItem diaryAdapterItem, int i) {
            SectionHeaderItem sectionHeaderItem = (SectionHeaderItem) diaryAdapterItem;
            Resources resources = getContext().getResources();
            if (this.txtSectionHeader != null) {
                adjustLayoutParams(false);
                this.txtSectionHeader.setText(sectionHeaderItem.getLocalizeddHeaderName());
            }
            String localizeddHeaderName = sectionHeaderItem.getLocalizeddHeaderName();
            if (this.txtSectionCalories != null) {
                int diarySection = sectionHeaderItem.getDiarySection();
                if (Strings.equalsIgnoreCase(localizeddHeaderName, resources.getString(R.string.noEntriesToday)) || Strings.equalsIgnoreCase(localizeddHeaderName, resources.getString(R.string.please_wait)) || diarySection == 4 || diarySection == 5 || diarySection == 6 || diarySection == 7) {
                    this.txtSectionCalories.setVisibility(8);
                } else {
                    ViewUtils.setVisible(!DiaryAdapter.this.inEditMode, this.txtSectionCalories);
                    DiaryAdapter.this.setAnnotationTextForSectionHeader(this.txtSectionCalories, sectionHeaderItem);
                    this.txtSectionCalories.setTextColor(resources.getColor(R.color.black_text));
                }
            }
            if (this.checkBox != null) {
                ViewUtils.setVisible(isSelectedSubHeaderHasItems(sectionHeaderItem.getHeaderName()) && DiaryAdapter.this.inEditMode && sectionHeaderItem.isSelectable(), this.checkBox);
                this.checkBox.setOnCheckedChangeListener(null);
                this.checkBox.setChecked(sectionHeaderItem.isSelected());
                this.checkBox.setTag(sectionHeaderItem);
                this.checkBox.setOnCheckedChangeListener(DiaryAdapter.this.sectionHeaderOnCheckedChangeListener);
            }
            this.headerContainer.setTag(this.checkBox);
            this.headerContainer.setOnClickListener(DiaryAdapter.this.sectionHeaderOnClickListener);
            TextView textView = this.mealMacrosTextView;
            if (textView != null) {
                ViewUtils.setGone(textView);
                if (DiaryAdapter.this.showMealMacrosForHeader(sectionHeaderItem)) {
                    Tuple3 macroValuesBasedOnUserPreference = MealMacrosUtil.getMacroValuesBasedOnUserPreference(DiaryAdapter.this.getMealMacrosDisplayUnit(), sectionHeaderItem.getMacroTotals().getMacroValues(), DiaryAdapter.this.shouldDisplayDiaryMealMacros());
                    int unitWithValuePlaceholderStringResId = DiaryAdapter.this.getMealMacrosDisplayUnit().getUnitWithValuePlaceholderStringResId();
                    this.mealMacrosTextView.setText(getContext().getString(R.string.meal_macros_values, new Object[]{getFormattedMacroValue(unitWithValuePlaceholderStringResId, ((Float) macroValuesBasedOnUserPreference.getItem1()).floatValue()), getFormattedMacroValue(unitWithValuePlaceholderStringResId, ((Float) macroValuesBasedOnUserPreference.getItem2()).floatValue()), getFormattedMacroValue(unitWithValuePlaceholderStringResId, ((Float) macroValuesBasedOnUserPreference.getItem3()).floatValue())}));
                    ViewUtils.setVisible(this.mealMacrosTextView);
                }
            }
            ViewGroup viewGroup = this.tipContainer;
            if (viewGroup != null) {
                DiaryAdapter.this.showMealHeaderTip(sectionHeaderItem, viewGroup, this.divider, i);
            }
            if (this.waterAdContainer != null) {
                if (sectionHeaderItem.getDiarySection() != 4 || !DiaryAdapter.this.isWaterBrandingEnabled || DiaryAdapter.this.isForRemoteUser) {
                    ViewUtils.setGone(this.waterAdContainer);
                } else {
                    adjustLayoutParams(true);
                    ViewUtils.setVisible(this.waterAdContainer);
                    if (DiaryAdapter.this.dfpWaterAdsHelper == null) {
                        DiaryAdapter diaryAdapter = DiaryAdapter.this;
                        Builder builder = new Builder(this.waterAdContainer, ((AdUnitService) diaryAdapter.adUnitService.get()).getSmartWaterSectionHeaderAdUnitValue(), "Diary", AdNetworkType.DFP, DiaryAdapter.this.configService, DiaryAdapter.this.localSettingsService, DiaryAdapter.this.locationService, DiaryAdapter.this.adsSettings, DiaryAdapter.this.adsAnalytics, (NavigationHelper) DiaryAdapter.this.navigationHelper.get(), ((Session) DiaryAdapter.this.session.get()).getUser().isProfileCountryUS());
                        diaryAdapter.dfpWaterAdsHelper = builder.setWaterSponsor(WaterSponsorshipUtil.getSponsorName((ConfigService) DiaryAdapter.this.configService.get())).build();
                        if (DiaryAdapter.this.diaryFragmentBase.getCurrentItemIndex() == DiaryAdapter.this.currentPageIndex) {
                            DiaryAdapter.this.dfpWaterAdsHelper.loadAds();
                        }
                    }
                }
            }
            adjustTopMarginForSelectAll(i);
        }

        private void adjustLayoutParams(boolean z) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.txtSectionHeader.getLayoutParams();
            if (z) {
                layoutParams.addRule(15);
            } else {
                layoutParams.removeRule(15);
            }
            this.txtSectionHeader.setLayoutParams(layoutParams);
        }

        private String getFormattedMacroValue(int i, float f) {
            return getContext().getString(i, new Object[]{NumberUtils.localeStringFromDoubleNoDecimal((double) f)});
        }

        private void adjustTopMarginForSelectAll(int i) {
            if (DiaryAdapter.this.inEditMode) {
                View view = this.headerContainer;
                if (view != null && (view.getLayoutParams() instanceof LayoutParams)) {
                    LayoutParams layoutParams = (LayoutParams) this.headerContainer.getLayoutParams();
                    layoutParams.setMargins(layoutParams.leftMargin, i == 0 ? 0 : getContext().getResources().getDimensionPixelSize(R.dimen.diary_card_vertical_margin), layoutParams.rightMargin, layoutParams.bottomMargin);
                    this.headerContainer.setLayoutParams(layoutParams);
                }
            }
        }

        private boolean isSelectedSubHeaderHasItems(String str) {
            return Strings.notEmpty(str) && DiaryAdapter.this.currentDiaryDay != null && ((Strings.equals(str, "Exercise") && CollectionUtils.notEmpty((Collection<?>) DiaryAdapter.this.currentDiaryDay.getExerciseEntries())) || ((Strings.equals(str, Report.CARDIO_EXERCISE) && CollectionUtils.notEmpty((Collection<?>) DiaryAdapter.this.currentDiaryDay.getCardioExerciseEntries())) || ((Strings.equals(str, Report.STRENGTH_EXERCISE) && CollectionUtils.notEmpty((Collection<?>) DiaryAdapter.this.currentDiaryDay.getStrengthExerciseEntries())) || CollectionUtils.notEmpty((Collection) DiaryAdapter.this.currentDiaryDay.getFoodEntriesByMealName().get(str)))));
        }
    }

    public class SectionHeaderViewHolder_ViewBinding implements Unbinder {
        private SectionHeaderViewHolder target;

        @UiThread
        public SectionHeaderViewHolder_ViewBinding(SectionHeaderViewHolder sectionHeaderViewHolder, View view) {
            this.target = sectionHeaderViewHolder;
            sectionHeaderViewHolder.headerContainer = Utils.findRequiredView(view, R.id.sectionHeaderRelativeLayout, "field 'headerContainer'");
            sectionHeaderViewHolder.txtSectionHeader = (TextView) Utils.findRequiredViewAsType(view, R.id.txtSectionHeader, "field 'txtSectionHeader'", TextView.class);
            sectionHeaderViewHolder.txtSectionCalories = (TextView) Utils.findRequiredViewAsType(view, R.id.txtSectionCalories, "field 'txtSectionCalories'", TextView.class);
            sectionHeaderViewHolder.checkBox = (CompoundButton) Utils.findRequiredViewAsType(view, R.id.meal_select_all, "field 'checkBox'", CompoundButton.class);
            sectionHeaderViewHolder.mealMacrosTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.meal_macros, "field 'mealMacrosTextView'", TextView.class);
            sectionHeaderViewHolder.tipContainer = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.tip_container, "field 'tipContainer'", ViewGroup.class);
            sectionHeaderViewHolder.divider = Utils.findRequiredView(view, R.id.divider, "field 'divider'");
            sectionHeaderViewHolder.waterAdContainer = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.water_ad_container, "field 'waterAdContainer'", ViewGroup.class);
        }

        @CallSuper
        public void unbind() {
            SectionHeaderViewHolder sectionHeaderViewHolder = this.target;
            if (sectionHeaderViewHolder != null) {
                this.target = null;
                sectionHeaderViewHolder.headerContainer = null;
                sectionHeaderViewHolder.txtSectionHeader = null;
                sectionHeaderViewHolder.txtSectionCalories = null;
                sectionHeaderViewHolder.checkBox = null;
                sectionHeaderViewHolder.mealMacrosTextView = null;
                sectionHeaderViewHolder.tipContainer = null;
                sectionHeaderViewHolder.divider = null;
                sectionHeaderViewHolder.waterAdContainer = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    class SponsoredWaterViewHolder extends RecyclerViewHolder<DiaryAdapterItem> {
        @BindView(2131362869)
        ImageView ivDiarySponsoredWater;

        SponsoredWaterViewHolder(ViewGroup viewGroup) {
            super(R.layout.diary_sponsored_water_entry, viewGroup);
        }

        public void setData(DiaryAdapterItem diaryAdapterItem, int i) {
            View parent = getParent();
            parent.setTag(((DiaryEntryItem) diaryAdapterItem).getDatabaseObject());
            GlideUtil.loadImage(this.context, WaterSponsorshipUtil.getAssetUrlForWaterEntry(this.context, (ConfigService) DiaryAdapter.this.configService.get(), WaterLoggingAsset.DiaryBottles), this.ivDiarySponsoredWater);
            parent.setOnClickListener(DiaryAdapter.this.onEntryClickListener);
        }
    }

    public class SponsoredWaterViewHolder_ViewBinding implements Unbinder {
        private SponsoredWaterViewHolder target;

        @UiThread
        public SponsoredWaterViewHolder_ViewBinding(SponsoredWaterViewHolder sponsoredWaterViewHolder, View view) {
            this.target = sponsoredWaterViewHolder;
            sponsoredWaterViewHolder.ivDiarySponsoredWater = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_diary_sponsored_water, "field 'ivDiarySponsoredWater'", ImageView.class);
        }

        @CallSuper
        public void unbind() {
            SponsoredWaterViewHolder sponsoredWaterViewHolder = this.target;
            if (sponsoredWaterViewHolder != null) {
                this.target = null;
                sponsoredWaterViewHolder.ivDiarySponsoredWater = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class StepsAppFooterViewHolder extends RecyclerViewHolder<DiaryAdapterItem> {
        @BindView(2131364016)
        TextView description;

        public void setData(DiaryAdapterItem diaryAdapterItem, int i) {
        }

        StepsAppFooterViewHolder(ViewGroup viewGroup) {
            super(R.layout.diary_steps_app, viewGroup);
            this.description.setText(((LocalizedStringsUtil) DiaryAdapter.this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.DIARY_STEPS_FOOTER_DESCRIPTION, DiaryAdapter.this.userEnergyService));
            this.itemView.setOnClickListener(new OnClickListener(DiaryAdapter.this) {
                public void onClick(View view) {
                    ((DiaryAnalyticsHelper) DiaryAdapter.this.diaryAnalyticsHelper.get()).reportConnectDeviceTapped();
                    ((NavigationHelper) DiaryAdapter.this.navigationHelper.get()).withContext(StepsAppFooterViewHolder.this.getContext()).withIntent(StepsSettings.newStartIntent(StepsAppFooterViewHolder.this.getContext())).startActivity();
                }
            });
        }
    }

    public class StepsAppFooterViewHolder_ViewBinding implements Unbinder {
        private StepsAppFooterViewHolder target;

        @UiThread
        public StepsAppFooterViewHolder_ViewBinding(StepsAppFooterViewHolder stepsAppFooterViewHolder, View view) {
            this.target = stepsAppFooterViewHolder;
            stepsAppFooterViewHolder.description = (TextView) Utils.findRequiredViewAsType(view, R.id.txtItemDetails, "field 'description'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            StepsAppFooterViewHolder stepsAppFooterViewHolder = this.target;
            if (stepsAppFooterViewHolder != null) {
                this.target = null;
                stepsAppFooterViewHolder.description = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public enum ViewType {
        SectionHeader,
        Entry,
        SectionFooter,
        Promo,
        LandscapeFooter,
        SponsoredWater,
        ButtonsFooter,
        StepsAppFooter
    }

    public void onAdFailedToLoad() {
    }

    public DiaryAdapter(Context context2, List<DiaryAdapterItem> list, Map<SectionHeaderItem, List<DatabaseObject>> map, DiaryDay diaryDay, List<FoodAnalyzerResponseData> list2, List<Long> list3, DiaryDelegate diaryDelegate2, boolean z, boolean z2, DiaryFragmentBase diaryFragmentBase2, int i, DiaryAdapterItemActionListener diaryAdapterItemActionListener2, Lazy<NewsFeedAnalyticsHelper> lazy) {
        MyFitnessPalApp.getInstance().component().inject(this);
        this.context = context2;
        this.layoutInflater = LayoutInflater.from(context2);
        this.adapterItems = list;
        this.headerItemToObjectsMap = map;
        this.currentDiaryDay = diaryDay;
        this.foodInsights = list2;
        this.checkedItems = list3;
        this.diaryDelegate = diaryDelegate2;
        this.inEditMode = z;
        this.isForRemoteUser = z2;
        this.diaryFragmentBase = diaryFragmentBase2;
        this.currentPageIndex = i;
        this.diaryAdapterItemActionListener = diaryAdapterItemActionListener2;
        this.isSponsoredWaterEnabled = ConfigUtils.isSponsoredWaterEnabled((ConfigService) this.configService.get());
        this.isWaterBrandingEnabled = WaterSponsorshipUtil.shouldShowWaterSponsorship((ConfigService) this.configService.get(), (Session) this.session.get(), (PremiumService) this.premiumService.get());
        this.newsFeedAnalyticsHelper = lazy;
        createPositionToDatesMap();
    }

    /* access modifiers changed from: protected */
    public Context getContext() {
        return this.context;
    }

    public DiaryDay getCurrentDiaryDay() {
        return this.currentDiaryDay;
    }

    public void resetIndexForMealHeaderTip() {
        this.mealHeaderTipIndex = -1;
    }

    public void resetParamsAndNotify(List<DiaryAdapterItem> list, Map<SectionHeaderItem, List<DatabaseObject>> map, DiaryDay diaryDay, List<FoodAnalyzerResponseData> list2, boolean z) {
        this.currentDiaryDay = diaryDay;
        this.foodInsights = list2;
        this.inEditMode = z;
        resetListAndMap(list, map);
    }

    public void resetListAndMap(List<DiaryAdapterItem> list, Map<SectionHeaderItem, List<DatabaseObject>> map) {
        this.adapterItems.clear();
        this.adapterItems.addAll(list);
        this.headerItemToObjectsMap = map;
        createPositionToDatesMap();
        notifyDataSetChanged();
    }

    private DiaryAdapterItem getItem(int i) {
        return (DiaryAdapterItem) this.adapterItems.get(i);
    }

    public int getItemViewType(int i) {
        DiaryAdapterItem item = getItem(i);
        if (item instanceof DiaryRowActionItem) {
            return ViewType.SectionFooter.ordinal();
        }
        if (item instanceof SectionHeaderItem) {
            return ViewType.SectionHeader.ordinal();
        }
        if (item instanceof DiaryLandscapeFooterItem) {
            return ViewType.LandscapeFooter.ordinal();
        }
        if (item instanceof DiaryPromoItem) {
            return ViewType.Promo.ordinal();
        }
        if (item instanceof DiaryButtonsFooterItem) {
            return ViewType.ButtonsFooter.ordinal();
        }
        if (!this.isWaterBrandingEnabled || !(item instanceof DiaryEntryItem) || !isWaterSectionAndEmpty((DiaryEntryItem) item)) {
            return item.getItemType().ordinal();
        }
        return ViewType.SponsoredWater.ordinal();
    }

    public RecyclerViewHolder<DiaryAdapterItem> onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewType viewType = ViewType.values()[i];
        switch (viewType) {
            case SectionHeader:
                return getSectionHeaderViewHolder(viewGroup);
            case SectionFooter:
                return new SectionFooterViewHolder(viewGroup);
            case Entry:
                return getEntryViewHolder(viewGroup);
            case Promo:
                return new PromoViewHolder(viewGroup);
            case LandscapeFooter:
                return getLandscapeFooterViewHolder(viewGroup);
            case SponsoredWater:
                return new SponsoredWaterViewHolder(viewGroup);
            case ButtonsFooter:
                return new ButtonsFooterViewHolder(viewGroup);
            case StepsAppFooter:
                return new StepsAppFooterViewHolder(viewGroup);
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Unknown view type: ");
                sb.append(viewType);
                throw new IllegalArgumentException(sb.toString());
        }
    }

    public void onBindViewHolder(RecyclerViewHolder<DiaryAdapterItem> recyclerViewHolder, int i) {
        recyclerViewHolder.setData(getItem(i), i);
    }

    public int getItemCount() {
        return this.adapterItems.size();
    }

    private boolean isWaterSectionAndEmpty(DiaryEntryItem diaryEntryItem) {
        boolean z = false;
        if (diaryEntryItem.getSectionType() != 4) {
            return false;
        }
        DatabaseObject databaseObject = diaryEntryItem.getDatabaseObject();
        if (!(databaseObject instanceof WaterEntry)) {
            return true;
        }
        if (((WaterEntry) databaseObject).getMilliliters() == BitmapDescriptorFactory.HUE_RED) {
            z = true;
        }
        return z;
    }

    /* access modifiers changed from: protected */
    public RecyclerViewHolder<DiaryAdapterItem> getSectionHeaderViewHolder(ViewGroup viewGroup) {
        return new SectionHeaderViewHolder(viewGroup);
    }

    /* access modifiers changed from: protected */
    public RecyclerViewHolder<DiaryAdapterItem> getEntryViewHolder(ViewGroup viewGroup) {
        return new EntryViewHolder(viewGroup);
    }

    /* access modifiers changed from: protected */
    public RecyclerViewHolder<DiaryAdapterItem> getLandscapeFooterViewHolder(ViewGroup viewGroup) {
        throw new IllegalStateException("This item type shouldn't exist for DiaryAdapter. It should only be added for DiaryLandscapeAdapter");
    }

    public int getLastItemIndexForSection(String str) {
        Iterator it = this.headerItemToObjectsMap.keySet().iterator();
        boolean z = false;
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            SectionHeaderItem sectionHeaderItem = (SectionHeaderItem) it.next();
            int size = CollectionUtils.size((Collection<?>) (List) this.headerItemToObjectsMap.get(sectionHeaderItem)) + 2;
            if (Strings.equals(str, sectionHeaderItem.getHeaderName())) {
                i += size - 2;
                z = true;
                break;
            }
            i += size;
        }
        if (z) {
            return i;
        }
        return -1;
    }

    public void selectHeaderSection(String str, boolean z) {
        for (SectionHeaderItem sectionHeaderItem : this.headerItemToObjectsMap.keySet()) {
            if (Strings.equals(str, sectionHeaderItem.getHeaderName())) {
                sectionHeaderItem.setIsSelected(z);
                return;
            }
        }
    }

    public List<DatabaseObject> getDataForHeaderType(String str) {
        for (SectionHeaderItem sectionHeaderItem : this.headerItemToObjectsMap.keySet()) {
            if (Strings.equals(str, sectionHeaderItem.getHeaderName())) {
                return (List) this.headerItemToObjectsMap.get(sectionHeaderItem);
            }
        }
        return null;
    }

    public void resume() {
        resumeDfpAdsHelper(this.dfpWaterAdsHelper);
    }

    public void pause() {
        pauseDfpAdsHelper(this.dfpWaterAdsHelper);
    }

    public void destroy() {
        destroyDfpAdsHelper(this.dfpWaterAdsHelper);
    }

    /* access modifiers changed from: 0000 */
    public boolean shouldDisplayDiaryMealMacros() {
        return ((Session) this.session.get()).getUser().shouldDisplayDiaryMealMacros();
    }

    private boolean shouldDisplayDiaryMealGoals() {
        return ((Session) this.session.get()).getUser().isMealGoalsEnabled() && ((PremiumService) this.premiumService.get()).isPremiumSubscribed();
    }

    /* access modifiers changed from: 0000 */
    public MealMacrosDisplayUnit getMealMacrosDisplayUnit() {
        return ((LocalSettingsService) this.localSettingsService.get()).getMealMacrosDisplayUnit();
    }

    /* access modifiers changed from: private */
    public boolean showMealMacrosForHeader(SectionHeaderItem sectionHeaderItem) {
        return shouldDisplayDiaryMealMacros() && isMealHeaderWithData(sectionHeaderItem);
    }

    private boolean isMealHeaderWithData(SectionHeaderItem sectionHeaderItem) {
        return isMealHeader(sectionHeaderItem) && CollectionUtils.notEmpty((Collection) this.headerItemToObjectsMap.get(sectionHeaderItem));
    }

    private boolean isMealHeader(SectionHeaderItem sectionHeaderItem) {
        return sectionHeaderItem.getDiarySection() == 2;
    }

    /* access modifiers changed from: 0000 */
    public void showMealHeaderTip(SectionHeaderItem sectionHeaderItem, ViewGroup viewGroup, View view, int i) {
        ViewUtils.setGone(viewGroup);
        view.setBackgroundColor(getContext().getResources().getColor(R.color.black_divider));
        boolean z = true;
        boolean z2 = ((LocalSettingsService) this.localSettingsService.get()).showMealGoalsTip() && shouldDisplayDiaryMealGoals();
        if (!((LocalSettingsService) this.localSettingsService.get()).showMealMacrosTip() || !shouldDisplayDiaryMealMacros()) {
            z = false;
        }
        if (z2 && isMealHeader(sectionHeaderItem)) {
            showMealHeaderTip(MealHeaderTip.Goals, viewGroup, view, i);
        } else if (z && isMealHeaderWithData(sectionHeaderItem)) {
            showMealHeaderTip(MealHeaderTip.Macros, viewGroup, view, i);
        }
    }

    /* access modifiers changed from: 0000 */
    public void setAnnotationTextForSectionHeader(TextView textView, SectionHeaderItem sectionHeaderItem) {
        String localeStringFromInt = NumberUtils.localeStringFromInt(sectionHeaderItem.getTotalCalories());
        if (sectionHeaderItem.getDiarySection() == 3) {
            textView.setText(localeStringFromInt);
        } else if (sectionHeaderItem.getDiarySection() == 2) {
            String mealGoalCalories = sectionHeaderItem.getMealGoalCalories();
            if (!sectionHeaderItem.isShowMealGoals() || !Strings.notEmpty(mealGoalCalories)) {
                textView.setText(localeStringFromInt);
                textView.setOnClickListener(null);
                return;
            }
            textView.setText(String.format(getContext().getResources().getString(R.string.total_of), new Object[]{localeStringFromInt, mealGoalCalories}));
            textView.setTag(sectionHeaderItem);
            textView.setOnClickListener(this.onMealCaloriesClickListener);
        } else {
            textView.setText("");
        }
    }

    private void createPositionToDatesMap() {
        this.timestampPositions = new HashSet();
        FoodEntry foodEntry = null;
        for (int i = 0; i < this.adapterItems.size(); i++) {
            DiaryAdapterItem diaryAdapterItem = (DiaryAdapterItem) this.adapterItems.get(i);
            if (diaryAdapterItem instanceof DiaryEntryItem) {
                DiaryEntryItem diaryEntryItem = (DiaryEntryItem) diaryAdapterItem;
                if (diaryEntryItem.getSectionType() == 2) {
                    FoodEntry foodEntry2 = (FoodEntry) diaryEntryItem.getDatabaseObject();
                    if (foodEntry == null) {
                        this.timestampPositions.add(Integer.valueOf(i));
                    } else if (foodEntry.getEntryTime() == null && foodEntry2.getEntryTime() != null) {
                        this.timestampPositions.add(Integer.valueOf(i));
                    } else if (foodEntry2.getEntryTime() != null && !foodEntry2.getEntryTime().equals(foodEntry.getEntryTime())) {
                        this.timestampPositions.add(Integer.valueOf(i));
                    }
                    foodEntry = foodEntry2;
                }
            } else {
                foodEntry = null;
            }
        }
    }

    private void resumeDfpAdsHelper(AdsHelper adsHelper) {
        if (adsHelper != null) {
            adsHelper.resume(this);
        }
    }

    private void pauseDfpAdsHelper(AdsHelper adsHelper) {
        if (adsHelper != null) {
            adsHelper.pause();
        }
    }

    private void destroyDfpAdsHelper(AdsHelper adsHelper) {
        if (adsHelper != null) {
            adsHelper.destroy();
        }
    }

    private void showMealHeaderTip(MealHeaderTip mealHeaderTip, ViewGroup viewGroup, View view, int i) {
        int i2 = this.mealHeaderTipIndex;
        if (i2 == -1 || i2 == i) {
            this.mealHeaderTipIndex = i;
            boolean z = true;
            ViewUtils.setVisible(viewGroup);
            view.setBackgroundColor(getContext().getResources().getColor(R.color.divider_light_blue));
            if (ViewUtils.findById(viewGroup, R.id.meal_macros_tip_container) == null) {
                View inflate = this.layoutInflater.inflate(R.layout.meal_macros_tip, viewGroup, false);
                viewGroup.addView(inflate);
                boolean isCalories = ((UserEnergyService) this.userEnergyService.get()).isCalories();
                if (mealHeaderTip != MealHeaderTip.Goals) {
                    z = false;
                }
                TextView textView = (TextView) ViewUtils.findById(inflate, R.id.tip_text);
                int i3 = !z ? R.string.meal_macros_tip : isCalories ? R.string.meal_goals_diary_meal_header_tip_cal : R.string.meal_goals_diary_meal_header_tip_kj;
                textView.setText(i3);
                ImageButton imageButton = (ImageButton) ViewUtils.findById(inflate, R.id.close);
                imageButton.setOnClickListener(this.onCloseMealMacrosTipClickListener);
                imageButton.setTag(mealHeaderTip);
            }
        }
    }

    public void onAdLoaded() {
        this.diaryAdapterItemActionListener.onBannerAdLoaded();
    }
}
