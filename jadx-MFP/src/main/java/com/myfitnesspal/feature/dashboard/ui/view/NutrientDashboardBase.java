package com.myfitnesspal.feature.dashboard.ui.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.dashboard.service.NutrientDashboardAnalyticsHelper;
import com.myfitnesspal.feature.dashboard.ui.activity.NutrientDashboardSettingsActivity;
import com.myfitnesspal.feature.dashboard.ui.view.NutrientDashboard.Type;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.diary.ui.activity.Diary;
import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.externalsync.impl.googlefit.constants.GoogleFitConstants;
import com.myfitnesspal.feature.externalsync.impl.googlefit.util.GoogleFitStepsUtils;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthStepsService;
import com.myfitnesspal.feature.externalsync.impl.shealth.util.SHealthUtil;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.home.ui.activity.HomeActivity;
import com.myfitnesspal.feature.nutrition.model.Nutrient;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.service.PremiumService.Availability;
import com.myfitnesspal.feature.settings.ui.activity.StepsSettings;
import com.myfitnesspal.shared.api.ApiResponseBase;
import com.myfitnesspal.shared.api.v2.MfpV2ApiErrorCallback;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v2.MfpExerciseMetadataForSteps;
import com.myfitnesspal.shared.model.v2.MfpPlatformApp;
import com.myfitnesspal.shared.model.v2.MfpStepSource;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.uacf.core.util.Function1;
import com.uacf.core.util.FunctionUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.TextViewUtils;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.Calendar;

public abstract class NutrientDashboardBase implements NutrientDashboard {
    private static final int DEFAULT_RECOMMENDED_STEP_COUNT = 10000;
    protected final String MACRO_FORMAT = "%s (%s)";
    protected final Lazy<ActionTrackingService> actionTrackingService;
    protected MfpActivity activity;
    private final Lazy<AppGalleryService> appGalleryService;
    protected Context context;
    protected String currentDisplaySetting;
    protected DashboardUserType dashboardUserType = DashboardUserType.Self;
    protected Calendar date;
    private final Lazy<DiaryService> diaryService;
    @Nullable
    @BindView(2131362320)
    View diaryShortcut;
    @Nullable
    @BindView(2131362269)
    View dropdownButton;
    protected final Lazy<SharedPreferences> fitStepsSharedPreferences;
    private final Lazy<GoogleFitClient> googleFitClient;
    @Nullable
    @BindView(2131362747)
    View homeStepsContainer;
    protected final Lazy<LocalizedStringsUtil> localizedStringsUtil;
    /* access modifiers changed from: private */
    public final Lazy<NutrientDashboardAnalyticsHelper> nutrientDashboardAnalyticsHelper;
    protected final Lazy<NutrientGoalService> nutrientGoalService;
    protected final Lazy<NutrientGoalsUtil> nutritionalGoalsUtil;
    private OnClickListener onDiaryShortcutClickedListener = new OnClickListener() {
        public void onClick(View view) {
            if (NutrientDashboardBase.this.type == Type.Home) {
                ((ActionTrackingService) NutrientDashboardBase.this.actionTrackingService.get()).registerEvent("recipe_importer", "channel", Extras.REFERRER_HOME_SUMMARY);
                NutrientDashboardBase.this.activity.getAnalyticsService().reportEvent(Events.HOME_SUMMARY_MATH_CLICK);
                ((ActionTrackingService) NutrientDashboardBase.this.actionTrackingService.get()).appendToEvent("channel", "referrer", Extras.REFERRER_HOME_SUMMARY);
                ((NutrientDashboardAnalyticsHelper) NutrientDashboardBase.this.nutrientDashboardAnalyticsHelper.get()).reportNutrientDashboardClicked("home");
                NutrientDashboardBase.this.activity.getNavigationHelper().withIntent(Diary.newStartIntentWithReferrer(NutrientDashboardBase.this.activity, Extras.REFERRER_HOME_SUMMARY)).withExtra(Extras.SHOW_AS_TOP_LEVEL_ACTIVITY, true).startActivity();
            } else if (((PremiumService) NutrientDashboardBase.this.premiumService.get()).isFeatureAvailable(PremiumFeature.NutrientDashboard) && NutrientDashboardBase.this.dashboardUserType == DashboardUserType.Self) {
                ((NutrientDashboardAnalyticsHelper) NutrientDashboardBase.this.nutrientDashboardAnalyticsHelper.get()).reportNutrientDashboardClicked("diary");
                ((NutrientDashboardAnalyticsHelper) NutrientDashboardBase.this.nutrientDashboardAnalyticsHelper.get()).reportCustomNutrientDashboardScreenViewed("diary");
                NutrientDashboardBase.this.activity.getNavigationHelper().withExtra(Extras.SETTINGS_PARENT, "diary").withIntent(NutrientDashboardSettingsActivity.newStartIntent(NutrientDashboardBase.this.activity)).startActivity(RequestCodes.MACROS_SUMMARY);
            }
        }
    };
    private OnClickListener onSettingsClickedListener = new OnClickListener() {
        public void onClick(View view) {
            if (((PremiumService) NutrientDashboardBase.this.premiumService.get()).isFeatureAvailable(PremiumFeature.NutrientDashboard) && NutrientDashboardBase.this.dashboardUserType == DashboardUserType.Self) {
                Fragment findFragmentByTag = NutrientDashboardBase.this.activity.getSupportFragmentManager().findFragmentByTag(HomeActivity.HOME_FRAGMENT_TAG);
                ((NutrientDashboardAnalyticsHelper) NutrientDashboardBase.this.nutrientDashboardAnalyticsHelper.get()).reportNutrientDashboardOverflowClicked();
                ((NutrientDashboardAnalyticsHelper) NutrientDashboardBase.this.nutrientDashboardAnalyticsHelper.get()).reportCustomNutrientDashboardScreenViewed("home");
                NutrientDashboardBase.this.activity.getNavigationHelper().fromFragment(findFragmentByTag).withExtra(Extras.SETTINGS_PARENT, NutrientDashboardBase.this.getParentId()).withIntent(NutrientDashboardSettingsActivity.newStartIntent(NutrientDashboardBase.this.activity)).startActivity(RequestCodes.MACROS_SUMMARY);
            }
        }
    };
    protected final Lazy<PremiumService> premiumService;
    protected Resources resources;
    private View rootView;
    protected final Lazy<Session> session;
    protected final Lazy<StepService> stepService;
    @Nullable
    @BindView(2131363735)
    ProgressBar stepsProgressBar;
    @Nullable
    @BindView(2131363833)
    protected TextView title;
    @Nullable
    @BindView(2131362270)
    View titleRow;
    @Nullable
    @BindView(2131363970)
    TextView tvPartnerName;
    @Nullable
    @BindView(2131363974)
    TextView tvStepCount;
    @Nullable
    @BindView(2131363975)
    TextView tvStepGoal;
    protected Type type;
    protected final Lazy<UserEnergyService> userEnergyService;

    public enum DashboardUserType {
        Self,
        Friend
    }

    /* access modifiers changed from: protected */
    public abstract View createView();

    /* access modifiers changed from: protected */
    public abstract String getParentId();

    public void pause() {
    }

    public void resume() {
    }

    protected NutrientDashboardBase(Context context2, Lazy<UserEnergyService> lazy, Lazy<Session> lazy2, Lazy<LocalizedStringsUtil> lazy3, Lazy<StepService> lazy4, Lazy<ActionTrackingService> lazy5, Lazy<NutrientGoalService> lazy6, Lazy<NutrientGoalsUtil> lazy7, Lazy<PremiumService> lazy8, Lazy<SharedPreferences> lazy9, Lazy<DiaryService> lazy10, Lazy<AppGalleryService> lazy11, Lazy<GoogleFitClient> lazy12, Lazy<NutrientDashboardAnalyticsHelper> lazy13) {
        this.context = context2;
        this.userEnergyService = lazy;
        this.session = lazy2;
        this.localizedStringsUtil = lazy3;
        this.actionTrackingService = lazy5;
        this.nutrientGoalService = lazy6;
        this.nutritionalGoalsUtil = lazy7;
        this.premiumService = lazy8;
        this.fitStepsSharedPreferences = lazy9;
        this.resources = context2.getResources();
        this.stepService = lazy4;
        this.diaryService = lazy10;
        this.appGalleryService = lazy11;
        this.googleFitClient = lazy12;
        this.nutrientDashboardAnalyticsHelper = lazy13;
    }

    public void initialize(MfpActivity mfpActivity, Type type2, Calendar calendar, String str) {
        if (this.activity == null) {
            this.activity = mfpActivity;
            this.type = type2;
            this.date = calendar;
            this.rootView = createView();
            ButterKnife.bind((Object) this, this.rootView);
            setCurrentDisplaySetting(str);
            TextViewUtils.setInputType(this.tvPartnerName, 524288);
            ViewUtils.setOnClickListener(this.diaryShortcut, this.onDiaryShortcutClickedListener);
            return;
        }
        throw new RuntimeException("already initialized!");
    }

    public void render(Function1<NutrientDashboard> function1, Calendar calendar, DiaryDay diaryDay, DashboardUserType dashboardUserType2) {
        this.date = calendar;
        this.dashboardUserType = dashboardUserType2;
        renderSteps(calendar);
        render(function1, diaryDay);
    }

    /* access modifiers changed from: protected */
    public final void render() {
        render(null, null);
    }

    public final void render(final Function1<NutrientDashboard> function1, DiaryDay diaryDay) {
        if (diaryDay == null) {
            ((DiaryService) this.diaryService.get()).getDiaryDayForDate(this.date.getTime(), new Function1<DiaryDay>() {
                public void execute(DiaryDay diaryDay) {
                    NutrientDashboardBase.this.renderPremiumSettingsButton();
                    NutrientDashboardBase.this.onRender(function1, diaryDay);
                }
            });
            return;
        }
        renderPremiumSettingsButton();
        onRender(function1, diaryDay);
    }

    /* access modifiers changed from: protected */
    public void onRender(Function1<NutrientDashboard> function1, DiaryDay diaryDay) {
        FunctionUtils.invokeIfValid(function1, this);
    }

    public final View getView() {
        return this.rootView;
    }

    /* access modifiers changed from: protected */
    public void setTitleVisibility(boolean z) {
        int i = 0;
        ViewUtils.setVisible(z, this.title);
        View view = this.diaryShortcut;
        int paddingLeft = view.getPaddingLeft();
        if (z && this.type != Type.Home) {
            i = this.context.getResources().getDimensionPixelSize(R.dimen.diary_summary_padding);
        }
        view.setPadding(paddingLeft, i, this.diaryShortcut.getPaddingRight(), this.diaryShortcut.getPaddingBottom());
    }

    private void renderSteps(Calendar calendar) {
        if (stepsVisibleForDisplayType()) {
            boolean z = !(!((GoogleFitClient) this.googleFitClient.get()).isEnabled() && GoogleFitStepsUtils.isGoogleFitStepsActive((StepService) this.stepService.get())) && ((StepService) this.stepService.get()).shouldTrackSteps();
            ViewUtils.setVisible(z, this.homeStepsContainer);
            if (z) {
                renderStepsView(((StepService) this.stepService.get()).fetchStepsEntry(calendar.getTime(), ((StepService) this.stepService.get()).getPrimaryStepSource()));
                this.homeStepsContainer.setOnClickListener(new OnClickListener() {
                    public final void onClick(View view) {
                        NutrientDashboardBase.this.activity.getNavigationHelper().withIntent(StepsSettings.newStartIntent(NutrientDashboardBase.this.activity)).startActivity();
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public void renderPremiumSettingsButton() {
        if (this.dropdownButton != null && this.titleRow != null) {
            boolean z = ((PremiumService) this.premiumService.get()).getFeatureAvailability(PremiumFeature.NutrientDashboard) != Availability.Hidden;
            ViewUtils.setVisible(z, this.dropdownButton);
            this.titleRow.setOnClickListener(z ? this.onSettingsClickedListener : null);
        }
    }

    private int getStepCountForStepSource(MfpExerciseMetadataForSteps mfpExerciseMetadataForSteps, MfpStepSource mfpStepSource) {
        int steps = mfpExerciseMetadataForSteps.getSteps();
        if (!DateTimeUtils.isDateToday(this.date)) {
            return steps;
        }
        if (GoogleFitStepsUtils.isGoogleFitStepSource(mfpStepSource)) {
            return Math.max(steps, ((SharedPreferences) this.fitStepsSharedPreferences.get()).getInt(GoogleFitConstants.SharedPreferences.LAST_SYNC_TODAYS_STEP_COUNT, 0));
        }
        return SHealthUtil.isSHealthStepsSource(mfpStepSource) ? Math.max(steps, SHealthStepsService.getStepCountForToday()) : steps;
    }

    private void renderStepsView(MfpExerciseMetadataForSteps mfpExerciseMetadataForSteps) {
        if (mfpExerciseMetadataForSteps == null) {
            this.stepsProgressBar.setProgress(0);
            TextViewUtils.setText(this.tvStepGoal, "");
            TextViewUtils.setText(this.tvPartnerName, "");
            TextViewUtils.setText(this.tvStepCount, String.format(this.resources.getString(R.string.steps_from_device), new Object[]{this.resources.getString(R.string.zero)}));
            return;
        }
        final String clientId = mfpExerciseMetadataForSteps.getClientId();
        MfpStepSource stepSource = ((StepService) this.stepService.get()).getStepSource(clientId);
        boolean isGoogleFitStepSource = GoogleFitStepsUtils.isGoogleFitStepSource(stepSource);
        int stepGoal = (stepSource == null || stepSource.getStepGoal() <= 0) ? 10000 : stepSource.getStepGoal();
        int stepCountForStepSource = getStepCountForStepSource(mfpExerciseMetadataForSteps, stepSource);
        TextViewUtils.setText(this.tvStepGoal, NumberUtils.localeStringFromInt(stepGoal));
        this.stepsProgressBar.setMax(stepGoal);
        this.stepsProgressBar.setProgress(mfpExerciseMetadataForSteps.getSteps());
        TextViewUtils.setText(this.tvStepCount, NumberUtils.localeStringFromInt(stepCountForStepSource));
        if (Strings.equals(clientId, Extras.MFP_MOBILE_IOS) || isGoogleFitStepSource) {
            String string = this.resources.getString(R.string.tracker_steps_proper_casing);
            Object[] objArr = new Object[1];
            objArr[0] = this.resources.getString(isGoogleFitStepSource ? R.string.google_fit : R.string.iPhone);
            TextViewUtils.setText(this.tvPartnerName, String.format(string, objArr));
            return;
        }
        ((AppGalleryService) this.appGalleryService.get()).getAppDetailsAsync(stepSource, new Function1<MfpPlatformApp>() {
            public void execute(MfpPlatformApp mfpPlatformApp) {
                String name = mfpPlatformApp != null ? mfpPlatformApp.getName() : null;
                if (Strings.isEmpty(name)) {
                    name = Strings.notEmpty(clientId) ? Strings.toTitleCase(clientId.toLowerCase()) : "";
                }
                TextViewUtils.setText(NutrientDashboardBase.this.tvPartnerName, String.format(NutrientDashboardBase.this.resources.getString(R.string.tracker_steps_proper_casing), new Object[]{name}));
            }
        }, new MfpV2ApiErrorCallback() {
            public void execute(ApiResponseBase apiResponseBase) {
                Ln.e(apiResponseBase, new Object[0]);
                TextViewUtils.setText(NutrientDashboardBase.this.tvPartnerName, "");
            }
        });
    }

    private void setCurrentDisplaySetting(String str) {
        if (!Strings.notEmpty(str)) {
            str = Extras.DEFAULT_GOAL_DISPLAY;
        }
        this.currentDisplaySetting = str;
    }

    public String getCurrentDisplaySetting() {
        return this.currentDisplaySetting;
    }

    /* access modifiers changed from: protected */
    public String formatProgressBarLabel(Nutrient nutrient) {
        return nutrient.getNutrientNameString(this.context);
    }

    /* access modifiers changed from: protected */
    public String formatProgressBarLabelWithUnits(Nutrient nutrient) {
        return String.format("%s (%s)", new Object[]{nutrient.getNutrientNameString(this.context), nutrient.getAbbreviatedUnitString(this.context)});
    }

    /* access modifiers changed from: protected */
    public int getTextColorForValue(float f) {
        return this.resources.getColor(Math.round(f) >= 0 ? R.color.light_green : R.color.dark_orange);
    }

    /* access modifiers changed from: protected */
    public boolean stepsVisibleForDisplayType() {
        return this.type == Type.Home;
    }
}
