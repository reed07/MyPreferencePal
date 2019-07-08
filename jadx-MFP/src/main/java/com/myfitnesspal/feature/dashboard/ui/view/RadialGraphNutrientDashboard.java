package com.myfitnesspal.feature.dashboard.ui.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.dashboard.service.NutrientDashboardAnalyticsHelper;
import com.myfitnesspal.feature.dashboard.ui.view.NutrientDashboard.Type;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.nutrition.model.Nutrient;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.shared.constants.Constants;
import com.myfitnesspal.shared.constants.Constants.LocalizedStrings;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.animation.ProgressBarAnimation;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Function1;
import com.uacf.core.util.FunctionUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.TextViewUtils;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RadialGraphNutrientDashboard extends NutrientDashboardBase {
    private static final int PROGRESS_BAR_ANIMATION_DELAY = 250;
    private static final int PROGRESS_BAR_ANIMATION_MILLIS = 500;
    private static final int PROGRESS_BAR_ROTATION = -90;
    /* access modifiers changed from: private */
    public Map<String, GraphState> graphStateMap = new HashMap();
    private Handler handler = new Handler();
    @BindView(2131363315)
    View progress1;
    @BindView(2131363316)
    View progress2;
    @BindView(2131363317)
    View progress3;
    @BindView(2131363318)
    View progress4;

    private static class GraphState {
        int consumed;
        int total;

        GraphState(int i, int i2) {
            this.total = i;
            this.consumed = i2;
        }
    }

    public RadialGraphNutrientDashboard(Context context, Lazy<UserEnergyService> lazy, Lazy<Session> lazy2, Lazy<LocalizedStringsUtil> lazy3, Lazy<StepService> lazy4, Lazy<ActionTrackingService> lazy5, Lazy<NutrientGoalService> lazy6, Lazy<NutrientGoalsUtil> lazy7, Lazy<PremiumService> lazy8, Lazy<SharedPreferences> lazy9, Lazy<DiaryService> lazy10, Lazy<AppGalleryService> lazy11, Lazy<GoogleFitClient> lazy12, Lazy<NutrientDashboardAnalyticsHelper> lazy13) {
        super(context, lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7, lazy8, lazy9, lazy10, lazy11, lazy12, lazy13);
    }

    /* access modifiers changed from: protected */
    public View createView() {
        return LayoutInflater.from(this.context).inflate(R.layout.premium_summary, null);
    }

    /* access modifiers changed from: protected */
    public String getParentId() {
        return this.type == Type.Home ? "home" : "diary";
    }

    public void initialize(MfpActivity mfpActivity, Type type, Calendar calendar, String str) {
        super.initialize(mfpActivity, type, calendar, str);
        TextViewUtils.setText(this.title, this.context.getString(R.string.summary_nutrients_remaining));
        setTitleVisibility(true);
        rebindAllProgressBarsToUnknownState();
    }

    /* access modifiers changed from: protected */
    public void onRender(final Function1<NutrientDashboard> function1, final DiaryDay diaryDay) {
        Ln.d("rendering for day of week=%s now=%s", this.date.getTime(), new Date());
        ((NutrientGoalService) this.nutrientGoalService.get()).getDailyGoalForDayOfWeek(new Function1<MfpDailyGoal>() {
            public void execute(MfpDailyGoal mfpDailyGoal) {
                RadialGraphNutrientDashboard.this.rebindAllProgressBars(mfpDailyGoal, diaryDay);
                FunctionUtils.invokeIfValid(function1, RadialGraphNutrientDashboard.this);
            }
        }, new Function1<List<Exception>>() {
            public void execute(List<Exception> list) {
                Ln.e("RadialGraphNutrientDashboard failed to get day of week! date=%s now = %s", RadialGraphNutrientDashboard.this.date.getTime(), new Date());
                Ln.e(list, new Object[0]);
            }
        }, this.date.getTime());
    }

    public void pause() {
        super.pause();
        rebindAllProgressBarsToPausedState();
        this.graphStateMap.clear();
    }

    public void resume() {
        render();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0045, code lost:
        if (r0.equals(com.myfitnesspal.shared.constants.Constants.Extras.HEART_HEALTHY_GOAL_DISPLAY) != false) goto L_0x0053;
     */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0086  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void rebindAllProgressBars(com.myfitnesspal.shared.model.v2.MfpDailyGoal r6, com.myfitnesspal.shared.model.v1.DiaryDay r7) {
        /*
            r5 = this;
            java.lang.String r0 = "rebindAllProgressBars called for displaySetting=%s"
            r1 = 1
            java.lang.Object[] r2 = new java.lang.Object[r1]
            java.lang.String r3 = r5.currentDisplaySetting
            r4 = 0
            r2[r4] = r3
            com.uacf.core.util.Ln.d(r0, r2)
            r5.updateEnergyProgressBar(r6, r7)
            java.lang.String r0 = r5.currentDisplaySetting
            int r2 = r0.hashCode()
            r3 = -1170652706(0xffffffffba3941de, float:-7.067005E-4)
            if (r2 == r3) goto L_0x0048
            r3 = -834279909(0xffffffffce45e61b, float:-8.3004794E8)
            if (r2 == r3) goto L_0x003f
            r1 = 446259792(0x1a996250, float:6.343815E-23)
            if (r2 == r1) goto L_0x0035
            r1 = 716388772(0x2ab339a4, float:3.1836784E-13)
            if (r2 == r1) goto L_0x002b
            goto L_0x0052
        L_0x002b:
            java.lang.String r1 = "custom_goal_display"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0052
            r1 = 3
            goto L_0x0053
        L_0x0035:
            java.lang.String r1 = "low_carb_remaining"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0052
            r1 = 2
            goto L_0x0053
        L_0x003f:
            java.lang.String r2 = "heart_healthy_remaining"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0052
            goto L_0x0053
        L_0x0048:
            java.lang.String r1 = "macros_remaining"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0052
            r1 = 0
            goto L_0x0053
        L_0x0052:
            r1 = -1
        L_0x0053:
            switch(r1) {
                case 0: goto L_0x0086;
                case 1: goto L_0x0070;
                case 2: goto L_0x005a;
                default: goto L_0x0056;
            }
        L_0x0056:
            r5.setCustomGoalDisplay(r6, r7)
            goto L_0x009b
        L_0x005a:
            android.view.View r0 = r5.progress1
            com.myfitnesspal.feature.nutrition.model.Nutrient r1 = com.myfitnesspal.feature.nutrition.model.Nutrient.Carbohydrates
            r5.updateProgressBar(r0, r6, r7, r1)
            android.view.View r0 = r5.progress2
            com.myfitnesspal.feature.nutrition.model.Nutrient r1 = com.myfitnesspal.feature.nutrition.model.Nutrient.Sugar
            r5.updateProgressBar(r0, r6, r7, r1)
            android.view.View r0 = r5.progress3
            com.myfitnesspal.feature.nutrition.model.Nutrient r1 = com.myfitnesspal.feature.nutrition.model.Nutrient.Fiber
            r5.updateProgressBar(r0, r6, r7, r1)
            goto L_0x009b
        L_0x0070:
            android.view.View r0 = r5.progress1
            com.myfitnesspal.feature.nutrition.model.Nutrient r1 = com.myfitnesspal.feature.nutrition.model.Nutrient.Fat
            r5.updateProgressBar(r0, r6, r7, r1)
            android.view.View r0 = r5.progress2
            com.myfitnesspal.feature.nutrition.model.Nutrient r1 = com.myfitnesspal.feature.nutrition.model.Nutrient.Sodium
            r5.updateProgressBar(r0, r6, r7, r1)
            android.view.View r0 = r5.progress3
            com.myfitnesspal.feature.nutrition.model.Nutrient r1 = com.myfitnesspal.feature.nutrition.model.Nutrient.Cholesterol
            r5.updateProgressBar(r0, r6, r7, r1)
            goto L_0x009b
        L_0x0086:
            android.view.View r0 = r5.progress1
            com.myfitnesspal.feature.nutrition.model.Nutrient r1 = com.myfitnesspal.feature.nutrition.model.Nutrient.Carbohydrates
            r5.updateProgressBar(r0, r6, r7, r1)
            android.view.View r0 = r5.progress2
            com.myfitnesspal.feature.nutrition.model.Nutrient r1 = com.myfitnesspal.feature.nutrition.model.Nutrient.Fat
            r5.updateProgressBar(r0, r6, r7, r1)
            android.view.View r0 = r5.progress3
            com.myfitnesspal.feature.nutrition.model.Nutrient r1 = com.myfitnesspal.feature.nutrition.model.Nutrient.Protein
            r5.updateProgressBar(r0, r6, r7, r1)
        L_0x009b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.dashboard.ui.view.RadialGraphNutrientDashboard.rebindAllProgressBars(com.myfitnesspal.shared.model.v2.MfpDailyGoal, com.myfitnesspal.shared.model.v1.DiaryDay):void");
    }

    private void rebindAllProgressBarsToPausedState() {
        updateProgressBarToPausedState(this.progress1);
        updateProgressBarToPausedState(this.progress2);
        updateProgressBarToPausedState(this.progress3);
        updateProgressBarToPausedState(this.progress4);
    }

    private void rebindAllProgressBarsToUnknownState() {
        updateProgressBarToUnknownState(this.progress1);
        updateProgressBarToUnknownState(this.progress2);
        updateProgressBarToUnknownState(this.progress3);
        updateProgressBarToUnknownState(this.progress4);
    }

    private void setCustomGoalDisplay(MfpDailyGoal mfpDailyGoal, DiaryDay diaryDay) {
        List<Nutrient> filterNutrientsForDisplay = NutrientDashboardUtil.filterNutrientsForDisplay(((Session) this.session.get()).getUser().getCustomDisplayGoal());
        if (CollectionUtils.size((Collection<?>) filterNutrientsForDisplay) != 3) {
            filterNutrientsForDisplay = NutrientDashboardUtil.DEFAULT_CUSTOM_GOALS;
        }
        updateProgressBar(this.progress1, mfpDailyGoal, diaryDay, (Nutrient) filterNutrientsForDisplay.get(0));
        updateProgressBar(this.progress2, mfpDailyGoal, diaryDay, (Nutrient) filterNutrientsForDisplay.get(1));
        updateProgressBar(this.progress3, mfpDailyGoal, diaryDay, (Nutrient) filterNutrientsForDisplay.get(2));
    }

    private void updateEnergyProgressBar(MfpDailyGoal mfpDailyGoal, DiaryDay diaryDay) {
        boolean isCalories = ((UserEnergyService) this.userEnergyService.get()).isCalories();
        float caloriesConsumed = diaryDay.caloriesConsumed(true);
        float caloriesBurnedByExercise = mfpDailyGoal.isAssignExerciseEnergyOn() ? diaryDay.caloriesBurnedByExercise(true) : BitmapDescriptorFactory.HUE_RED;
        float currentEnergy = ((UserEnergyService) this.userEnergyService.get()).getCurrentEnergy(mfpDailyGoal.getEnergy());
        String string = this.context.getString(isCalories ? R.string.calories : R.string.kilojoules);
        String localizedString = ((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.MACRO_ENERGY_ABBREVIATION, this.userEnergyService.get());
        int round = Math.round(caloriesConsumed);
        int round2 = Math.round(currentEnergy + caloriesBurnedByExercise);
        updateProgressBar(this.progress4, round, round2, string, localizedString);
        ((TextView) this.progress4.findViewById(R.id.tvMacroValue)).setTextColor(getTextColorForValue((float) (round2 - round)));
        this.progress4.findViewById(R.id.donutProgressBar).setBackgroundResource(R.drawable.donut_progress_base_calories);
    }

    private void updateProgressBar(View view, MfpDailyGoal mfpDailyGoal, DiaryDay diaryDay, Nutrient nutrient) {
        updateProgressBar(view, mfpDailyGoal, diaryDay, nutrient.getNutrientIndex(), formatProgressBarLabel(nutrient), nutrient.getAbbreviatedUnitString(this.context));
    }

    private void updateProgressBar(View view, MfpDailyGoal mfpDailyGoal, DiaryDay diaryDay, int i, String str, String str2) {
        View view2 = view;
        updateProgressBar(view2, Math.round(diaryDay.amountOfNutrientConsumed(i)), Math.round(((NutrientGoalsUtil) this.nutritionalGoalsUtil.get()).getAdjustedNutritionalGoal(diaryDay, mfpDailyGoal, i)), str, str2);
    }

    private void updateProgressBar(View view, int i, int i2, String str, String str2) {
        View view2 = view;
        int i3 = i2 - i;
        final ProgressBar progressBar = (ProgressBar) view2.findViewById(R.id.donutProgressBar);
        TextView textView = (TextView) view2.findViewById(R.id.tvMacroName);
        TextView textView2 = (TextView) view2.findViewById(R.id.tvMacroValue);
        TextView textView3 = (TextView) view2.findViewById(R.id.tvMacroUnit);
        ViewUtils.setVisible(true, (FrameLayout) view2.findViewById(R.id.donutProgressBarView));
        Handler handler2 = this.handler;
        final String str3 = str;
        final int i4 = i2;
        final int i5 = i;
        AnonymousClass3 r0 = new Runnable() {
            public void run() {
                GraphState graphState = (GraphState) RadialGraphNutrientDashboard.this.graphStateMap.get(str3);
                int i = 0;
                boolean z = true;
                if (graphState != null) {
                    int i2 = graphState.consumed;
                    if (graphState.total == i4 && graphState.consumed == i5) {
                        z = false;
                    }
                    i = i2;
                }
                progressBar.setProgress(i5);
                progressBar.setMax(i4);
                progressBar.setRotation(-90.0f);
                if (z) {
                    ProgressBar progressBar = progressBar;
                    progressBar.startAnimation(new ProgressBarAnimation(progressBar, (float) i, (float) i5, 500));
                }
                RadialGraphNutrientDashboard.this.updateGraphStateForNutrient(str3, i4, i5);
            }
        };
        handler2.postDelayed(r0, 250);
        textView.setText(str);
        ViewUtils.setVisible(true, textView3);
        textView3.setText(str2);
        textView2.setText(NumberUtils.localeStringFromInt(i3));
    }

    /* access modifiers changed from: private */
    public GraphState updateGraphStateForNutrient(String str, int i, int i2) {
        GraphState graphState = (GraphState) this.graphStateMap.get(str);
        if (graphState != null) {
            graphState.total = i;
            graphState.consumed = i2;
            return graphState;
        }
        GraphState graphState2 = new GraphState(i, i2);
        this.graphStateMap.put(str, graphState2);
        return graphState2;
    }

    private void updateProgressBarToPausedState(View view) {
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.donutProgressBar);
        progressBar.setMax(1);
        progressBar.setProgress(0);
    }

    private void updateProgressBarToUnknownState(View view) {
        FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.donutProgressBarView);
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.donutProgressBar);
        TextView textView = (TextView) view.findViewById(R.id.tvMacroName);
        TextView textView2 = (TextView) view.findViewById(R.id.tvMacroValue);
        TextView textView3 = (TextView) view.findViewById(R.id.tvMacroUnit);
        ViewUtils.setVisible(true, frameLayout);
        progressBar.setProgress(0);
        ViewUtils.setVisible(false, textView3);
        textView.setText(Constants.TWO_HYPHENS);
        textView2.setText(Constants.TWO_HYPHENS);
    }
}
