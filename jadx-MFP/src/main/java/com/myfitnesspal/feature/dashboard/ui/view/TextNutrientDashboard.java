package com.myfitnesspal.feature.dashboard.ui.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.dashboard.service.NutrientDashboardAnalyticsHelper;
import com.myfitnesspal.feature.dashboard.ui.view.NutrientDashboard.Type;
import com.myfitnesspal.feature.dashboard.ui.view.NutrientDashboardBase.DashboardUserType;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.nutrition.model.Nutrient;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Function1;
import com.uacf.core.util.FunctionUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.TextViewUtils;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.Collection;
import java.util.List;

public class TextNutrientDashboard extends NutrientDashboardBase {
    private static final String DEFAULT_VALUE = "-";
    private static final String MINUS = "–";
    private static final String PLUS = "+";
    @Nullable
    @BindView(2131363893)
    View equalSign;
    @BindView(2131362501)
    TextView exercise;
    @Nullable
    @BindView(2131362504)
    View exercisePlusMinus;
    @Nullable
    @BindView(2131362505)
    View exerciseTextViews;
    @BindView(2131362618)
    TextView food;
    @Nullable
    @BindView(2131363892)
    View foodPlusMinus;
    @BindView(2131362710)
    TextView goal;
    private boolean hasRenderedOnce;
    @Nullable
    @BindView(2131363915)
    TextView label1;
    @Nullable
    @BindView(2131363916)
    TextView label2;
    @Nullable
    @BindView(2131363917)
    TextView label3;
    @Nullable
    @BindView(2131363108)

    /* renamed from: net reason: collision with root package name */
    TextView f31net;
    @BindView(2131363431)
    TextView remaining;
    @Nullable
    @BindView(2131363432)
    TextView remainingDiary;
    @BindView(2131363433)
    TextView remainingLabel;

    private static class EnergyData {
        boolean assignExerciseEnergyEnabled;
        float burnedByExercise;
        float consumed;
        float goal;

        /* renamed from: net reason: collision with root package name */
        float f32net;
        float remaining;
        float total;

        public static EnergyData fromV1(float f, DiaryDay diaryDay) {
            return new EnergyData(f, diaryDay, true);
        }

        public static EnergyData fromV2(float f, DiaryDay diaryDay, MfpDailyGoal mfpDailyGoal) {
            return new EnergyData(f, diaryDay, mfpDailyGoal != null && mfpDailyGoal.isAssignExerciseEnergyOn());
        }

        public static EnergyData fromFriendDiaryDay(UserEnergyService userEnergyService, DiaryDay diaryDay) {
            return new EnergyData((float) userEnergyService.getRoundedCurrentEnergy((double) diaryDay.goalCalories()), diaryDay, diaryDay.isFriendExerciseCaloriesOn());
        }

        private EnergyData(float f, DiaryDay diaryDay, boolean z) {
            this.assignExerciseEnergyEnabled = z;
            this.consumed = diaryDay.caloriesConsumed(true);
            this.burnedByExercise = diaryDay.caloriesBurnedByExercise(true);
            this.goal = f;
            float f2 = BitmapDescriptorFactory.HUE_RED;
            this.total = f + (z ? this.burnedByExercise : BitmapDescriptorFactory.HUE_RED);
            float f3 = this.total;
            float f4 = this.consumed;
            this.remaining = f3 - f4;
            if (z) {
                f2 = this.burnedByExercise;
            }
            this.f32net = f4 - f2;
        }
    }

    public TextNutrientDashboard(Context context, Lazy<UserEnergyService> lazy, Lazy<Session> lazy2, Lazy<LocalizedStringsUtil> lazy3, Lazy<StepService> lazy4, Lazy<ActionTrackingService> lazy5, Lazy<NutrientGoalService> lazy6, Lazy<NutrientGoalsUtil> lazy7, Lazy<PremiumService> lazy8, Lazy<SharedPreferences> lazy9, Lazy<DiaryService> lazy10, Lazy<AppGalleryService> lazy11, Lazy<GoogleFitClient> lazy12, Lazy<NutrientDashboardAnalyticsHelper> lazy13) {
        super(context, lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7, lazy8, lazy9, lazy10, lazy11, lazy12, lazy13);
    }

    /* access modifiers changed from: protected */
    public View createView() {
        return LayoutInflater.from(this.context).inflate(this.type == Type.Home ? R.layout.nutrient_dashboard_home : R.layout.nutrient_dashboard_diary, null);
    }

    /* access modifiers changed from: protected */
    public String getParentId() {
        return this.type == Type.Home ? "home" : "diary";
    }

    public void onRender(final Function1<NutrientDashboard> function1, final DiaryDay diaryDay) {
        if (this.dashboardUserType == DashboardUserType.Self) {
            final float round = (float) Math.round(MfpDailyGoal.getLocalizedEnergy(((NutrientGoalService) this.nutrientGoalService.get()).getCachedDefaultGoal(), (UserEnergyService) this.userEnergyService.get()));
            final boolean equals = Strings.equals(this.currentDisplaySetting, Extras.DEFAULT_GOAL_DISPLAY);
            if (!this.hasRenderedOnce) {
                if (equals) {
                    renderAsLegacy(null);
                } else {
                    renderAsModern(null, null, null);
                }
                this.hasRenderedOnce = true;
            }
            ((NutrientGoalService) this.nutrientGoalService.get()).getDailyGoalForDate(new Function1<MfpDailyGoal>() {
                public void execute(MfpDailyGoal mfpDailyGoal) {
                    EnergyData fromV2 = EnergyData.fromV2(((UserEnergyService) TextNutrientDashboard.this.userEnergyService.get()).getCurrentEnergy(mfpDailyGoal.getEnergy()), diaryDay, mfpDailyGoal);
                    if (equals) {
                        TextNutrientDashboard.this.renderAsLegacy(fromV2);
                    } else {
                        TextNutrientDashboard.this.renderAsModern(fromV2, mfpDailyGoal, diaryDay);
                    }
                    FunctionUtils.invokeIfValid(function1, TextNutrientDashboard.this);
                }
            }, new Function1<List<Exception>>() {
                public void execute(List<Exception> list) {
                    TextNutrientDashboard.this.renderAsLegacy(EnergyData.fromV1(round, diaryDay));
                    FunctionUtils.invokeIfValid(function1, TextNutrientDashboard.this);
                    Ln.e(list, new Object[0]);
                }
            }, this.date.getTime());
            return;
        }
        renderAsLegacy(EnergyData.fromFriendDiaryDay((UserEnergyService) this.userEnergyService.get(), diaryDay));
        FunctionUtils.invokeIfValid(function1, this);
    }

    private void renderHeader() {
        TextViewUtils.setText(this.title, ((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString("summary_remaining", this.userEnergyService));
        setTitleVisibility(this.type == Type.Home);
    }

    private void renderEnergyViews(EnergyData energyData) {
        String str;
        String str2;
        setRemainingText(energyData);
        if (energyData != null) {
            str2 = NumberUtils.localeStringFromAbsDoubleNoDecimal((double) energyData.goal);
            str = Strings.signNumber(NumberUtils.localeStringFromAbsDoubleNoDecimal((double) energyData.f32net), energyData.f32net);
        } else {
            str2 = DEFAULT_VALUE;
            str = DEFAULT_VALUE;
        }
        this.goal.setText(str2);
        TextViewUtils.setText(this.f31net, str);
    }

    private void setRemainingText(EnergyData energyData) {
        int i;
        String str;
        if (energyData != null) {
            float f = energyData.remaining;
            str = NumberUtils.localeStringFromDoubleNoDecimal((double) f);
            i = getTextColorForValue(f);
        } else {
            str = DEFAULT_VALUE;
            i = R.color.light_green;
        }
        boolean z = this.type == Type.Diary;
        setRemainingTextViewData(this.remaining, i, str, !z);
        setRemainingTextViewData(this.remainingDiary, i, str, z);
    }

    private void setRemainingTextViewData(TextView textView, int i, String str, boolean z) {
        if (textView != null) {
            textView.setTextColor(i);
            textView.setText(str);
            ViewUtils.setVisible(z, textView);
        }
    }

    /* access modifiers changed from: private */
    public void renderAsLegacy(EnergyData energyData) {
        boolean z;
        String str;
        String str2;
        String str3;
        renderHeader();
        renderEnergyViews(energyData);
        if (energyData != null) {
            z = energyData.assignExerciseEnergyEnabled;
            ViewUtils.setVisible(energyData.assignExerciseEnergyEnabled, this.exerciseTextViews);
            ViewUtils.setVisible(energyData.assignExerciseEnergyEnabled, this.exercisePlusMinus);
        } else {
            z = ViewUtils.isVisible(this.exerciseTextViews);
        }
        ViewUtils.setVisible(z, this.exerciseTextViews);
        ViewUtils.setVisible(z, this.exercisePlusMinus);
        if (this.type == Type.Home) {
            String titleCase = Strings.toTitleCase(this.context.getString(R.string.remainingDiaryTxt));
            String localeStringFromAbsDoubleNoDecimal = energyData != null ? NumberUtils.localeStringFromAbsDoubleNoDecimal((double) energyData.burnedByExercise) : DEFAULT_VALUE;
            String localeStringFromAbsDoubleNoDecimal2 = energyData != null ? NumberUtils.localeStringFromAbsDoubleNoDecimal((double) energyData.consumed) : DEFAULT_VALUE;
            this.remainingLabel.setText(titleCase);
            this.food.setText(localeStringFromAbsDoubleNoDecimal2);
            this.exercise.setText(localeStringFromAbsDoubleNoDecimal);
        } else {
            if (energyData != null) {
                str3 = NumberUtils.localeStringFromAbsDoubleNoDecimal((double) energyData.goal);
                str2 = NumberUtils.localeStringFromAbsDoubleNoDecimal((double) energyData.consumed);
                str = NumberUtils.localeStringFromAbsDoubleNoDecimal((double) energyData.burnedByExercise);
            } else {
                str3 = DEFAULT_VALUE;
                str2 = DEFAULT_VALUE;
                str = DEFAULT_VALUE;
            }
            this.goal.setText(str3);
            this.food.setText(str2);
            this.exercise.setText(str);
            this.remainingLabel.setText(Strings.toTitleCase(this.context.getString(R.string.remainingDiaryTxt)));
        }
        if (energyData != null) {
            TextViewUtils.setText((TextView) ViewUtils.findById(getView(), R.id.exercisePlusMinus), energyData.burnedByExercise >= BitmapDescriptorFactory.HUE_RED ? PLUS : MINUS);
        }
        ViewUtils.setVisible(true, getView());
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00a1  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00a9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void renderAsModern(com.myfitnesspal.feature.dashboard.ui.view.TextNutrientDashboard.EnergyData r6, com.myfitnesspal.shared.model.v2.MfpDailyGoal r7, com.myfitnesspal.shared.model.v1.DiaryDay r8) {
        /*
            r5 = this;
            r5.renderHeader()
            r5.renderEnergyViews(r6)
            com.myfitnesspal.feature.dashboard.ui.view.NutrientDashboard$Type r6 = r5.type
            com.myfitnesspal.feature.dashboard.ui.view.NutrientDashboard$Type r0 = com.myfitnesspal.feature.dashboard.ui.view.NutrientDashboard.Type.Home
            if (r6 == r0) goto L_0x0027
            android.widget.TextView r6 = r5.remainingLabel
            android.content.Context r0 = r5.context
            dagger.Lazy r1 = r5.userEnergyService
            java.lang.Object r1 = r1.get()
            com.myfitnesspal.shared.service.userdata.UserEnergyService r1 = (com.myfitnesspal.shared.service.userdata.UserEnergyService) r1
            int r1 = r1.getCurrentEnergyStringId()
            java.lang.String r0 = r0.getString(r1)
            java.lang.String r0 = com.uacf.core.util.Strings.toTitleCase(r0)
            r6.setText(r0)
        L_0x0027:
            android.widget.TextView r6 = r5.title
            android.content.Context r0 = r5.context
            r1 = 2131889284(0x7f120c84, float:1.9413227E38)
            java.lang.String r0 = r0.getString(r1)
            com.uacf.core.util.TextViewUtils.setText(r6, r0)
            r6 = 1
            r5.setTitleVisibility(r6)
            android.view.View[] r0 = new android.view.View[r6]
            android.view.View r1 = r5.foodPlusMinus
            r2 = 0
            r0[r2] = r1
            com.uacf.core.util.ViewUtils.setVisible(r2, r0)
            android.view.View[] r0 = new android.view.View[r6]
            android.view.View r1 = r5.equalSign
            r0[r2] = r1
            com.uacf.core.util.ViewUtils.setVisible(r2, r0)
            android.view.View[] r0 = new android.view.View[r6]
            android.view.View r1 = r5.exercisePlusMinus
            r0[r2] = r1
            com.uacf.core.util.ViewUtils.setVisible(r2, r0)
            java.lang.String r0 = r5.currentDisplaySetting
            r1 = -1
            int r3 = r0.hashCode()
            r4 = -1170652706(0xffffffffba3941de, float:-7.067005E-4)
            if (r3 == r4) goto L_0x008f
            r4 = -834279909(0xffffffffce45e61b, float:-8.3004794E8)
            if (r3 == r4) goto L_0x0085
            r4 = 446259792(0x1a996250, float:6.343815E-23)
            if (r3 == r4) goto L_0x007b
            r4 = 716388772(0x2ab339a4, float:3.1836784E-13)
            if (r3 == r4) goto L_0x0071
            goto L_0x0099
        L_0x0071:
            java.lang.String r3 = "custom_goal_display"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0099
            r0 = 2
            goto L_0x009a
        L_0x007b:
            java.lang.String r3 = "low_carb_remaining"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0099
            r0 = 1
            goto L_0x009a
        L_0x0085:
            java.lang.String r3 = "heart_healthy_remaining"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0099
            r0 = 0
            goto L_0x009a
        L_0x008f:
            java.lang.String r3 = "macros_remaining"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0099
            r0 = 3
            goto L_0x009a
        L_0x0099:
            r0 = -1
        L_0x009a:
            switch(r0) {
                case 0: goto L_0x00a9;
                case 1: goto L_0x00a5;
                case 2: goto L_0x00a1;
                default: goto L_0x009d;
            }
        L_0x009d:
            r5.renderMacrosRemaining(r7, r8)
            goto L_0x00ac
        L_0x00a1:
            r5.renderCustomGoals(r7, r8)
            goto L_0x00ac
        L_0x00a5:
            r5.renderLowCarb(r7, r8)
            goto L_0x00ac
        L_0x00a9:
            r5.renderHearthHealthy(r7, r8)
        L_0x00ac:
            android.view.View[] r7 = new android.view.View[r6]
            android.view.View r8 = r5.getView()
            r7[r2] = r8
            com.uacf.core.util.ViewUtils.setVisible(r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.dashboard.ui.view.TextNutrientDashboard.renderAsModern(com.myfitnesspal.feature.dashboard.ui.view.TextNutrientDashboard$EnergyData, com.myfitnesspal.shared.model.v2.MfpDailyGoal, com.myfitnesspal.shared.model.v1.DiaryDay):void");
    }

    private void renderMacrosRemaining(MfpDailyGoal mfpDailyGoal, DiaryDay diaryDay) {
        setNetRemainingValuesForMacros(mfpDailyGoal, diaryDay, Nutrient.Carbohydrates, Nutrient.Fat, Nutrient.Protein);
    }

    private void renderHearthHealthy(MfpDailyGoal mfpDailyGoal, DiaryDay diaryDay) {
        setNetRemainingValuesForMacros(mfpDailyGoal, diaryDay, Nutrient.Fat, Nutrient.Sodium, Nutrient.Cholesterol);
    }

    private void renderLowCarb(MfpDailyGoal mfpDailyGoal, DiaryDay diaryDay) {
        setNetRemainingValuesForMacros(mfpDailyGoal, diaryDay, Nutrient.Carbohydrates, Nutrient.Sugar, Nutrient.Fiber);
    }

    private void renderCustomGoals(MfpDailyGoal mfpDailyGoal, DiaryDay diaryDay) {
        List<Nutrient> filterNutrientsForDisplay = NutrientDashboardUtil.filterNutrientsForDisplay(((Session) this.session.get()).getUser().getCustomDisplayGoal());
        if (CollectionUtils.size((Collection<?>) filterNutrientsForDisplay) != 3) {
            filterNutrientsForDisplay = NutrientDashboardUtil.DEFAULT_CUSTOM_GOALS;
        }
        setNetRemainingValuesForMacros(mfpDailyGoal, diaryDay, (Nutrient) filterNutrientsForDisplay.get(0), (Nutrient) filterNutrientsForDisplay.get(1), (Nutrient) filterNutrientsForDisplay.get(2));
    }

    private void setNetRemainingValuesForMacros(MfpDailyGoal mfpDailyGoal, DiaryDay diaryDay, Nutrient... nutrientArr) {
        MfpDailyGoal mfpDailyGoal2 = mfpDailyGoal;
        DiaryDay diaryDay2 = diaryDay;
        setNetRemainingValuesForMacro(this.goal, this.label1, mfpDailyGoal2, diaryDay2, nutrientArr[0]);
        setNetRemainingValuesForMacro(this.food, this.label2, mfpDailyGoal, diaryDay, nutrientArr[1]);
        setNetRemainingValuesForMacro(this.exercise, this.label3, mfpDailyGoal2, diaryDay2, nutrientArr[2]);
    }

    private void setNetRemainingValuesForMacro(TextView textView, TextView textView2, MfpDailyGoal mfpDailyGoal, DiaryDay diaryDay, Nutrient nutrient) {
        String str;
        if (mfpDailyGoal == null || diaryDay == null) {
            str = DEFAULT_VALUE;
        } else {
            int nutrientIndex = nutrient.getNutrientIndex();
            str = Strings.toString(Integer.valueOf(Math.round(((NutrientGoalsUtil) this.nutritionalGoalsUtil.get()).getAdjustedNutritionalGoal(diaryDay, mfpDailyGoal, nutrientIndex) - diaryDay.amountOfNutrientConsumed(nutrientIndex))));
        }
        textView.setText(str);
        TextViewUtils.setText(textView2, formatProgressBarLabelWithUnits(nutrient));
    }
}
