package com.myfitnesspal.shared.task;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.premium.util.PremiumApiErrorUtil;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.constants.Constants.LocalizedStrings;
import com.myfitnesspal.shared.model.v1.UserV1;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.uacf.core.util.Function0;
import com.uacf.core.util.FunctionUtils;
import dagger.Lazy;

public class RecalculateNutrientGoalsTask extends EventedTaskBase<Boolean, Exception> {
    private static final float UNSPECIFIED_CALORIE_GOAL = Float.MIN_VALUE;
    private final Lazy<DiaryService> diaryService;
    private final Lazy<NutrientGoalService> goalsService;
    private final Lazy<NutrientGoalsUtil> goalsUtil;
    private final float newCalorieGoal;
    private final Lazy<Session> session;

    public static class CompletedEvent extends TaskEventBase<Boolean, Exception> {
    }

    public RecalculateNutrientGoalsTask(Lazy<NutrientGoalsUtil> lazy, Lazy<DiaryService> lazy2, Lazy<NutrientGoalService> lazy3, Lazy<Session> lazy4) {
        this(Float.MIN_VALUE, lazy, lazy2, lazy3, lazy4);
    }

    private RecalculateNutrientGoalsTask(float f, Lazy<NutrientGoalsUtil> lazy, Lazy<DiaryService> lazy2, Lazy<NutrientGoalService> lazy3, Lazy<Session> lazy4) {
        super(CompletedEvent.class);
        this.newCalorieGoal = f;
        this.goalsUtil = lazy;
        this.diaryService = lazy2;
        this.goalsService = lazy3;
        this.session = lazy4;
    }

    /* access modifiers changed from: protected */
    public Boolean exec(Context context) throws Exception {
        float f = this.newCalorieGoal;
        if (f == Float.MIN_VALUE) {
            UserV1 userV1 = ((Session) this.session.get()).getUser().getUserV1();
            f = userV1.getGoalPreferences().calculateCalorieGoalForUser(userV1);
        }
        ((NutrientGoalService) this.goalsService.get()).setNutrientGoal(((NutrientGoalsUtil) this.goalsUtil.get()).recalculateGoals(f, ((Session) this.session.get()).getUser().getMealNames()));
        ((DiaryService) this.diaryService.get()).markDiaryDayCacheStale();
        return Boolean.valueOf(true);
    }

    public static void confirmRecalculation(Activity activity, LocalizedStringsUtil localizedStringsUtil, UserEnergyService userEnergyService, Function0 function0) {
        confirmRecalculation(activity, localizedStringsUtil, userEnergyService, function0, null);
    }

    public static void confirmRecalculation(Activity activity, LocalizedStringsUtil localizedStringsUtil, UserEnergyService userEnergyService, final Function0 function0, final Function0 function02) {
        new MfpAlertDialogBuilder(activity).setTitle((int) R.string.are_you_sure).setCancelable(false).setMessage((CharSequence) localizedStringsUtil.getLocalizedString(LocalizedStrings.MIGHT_OVERWRITE_EXISTING_GOALS, userEnergyService)).setPositiveButton((int) R.string.yesBtn, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                FunctionUtils.invokeIfValid(function0);
            }
        }).setNegativeButton((int) R.string.noBtn, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                FunctionUtils.invokeIfValid(function02);
            }
        }).show();
    }

    public static void showErrorDialogIfFailed(MfpFragment mfpFragment, CompletedEvent completedEvent) {
        showErrorDialogIfFailed((MfpActivity) mfpFragment.getActivity(), completedEvent);
    }

    public static void showErrorDialogIfFailed(MfpActivity mfpActivity, CompletedEvent completedEvent) {
        if (mfpActivity != null && !completedEvent.successful()) {
            new PremiumApiErrorUtil(mfpActivity, mfpActivity.getMessageBus()).showAlertForApiError((Exception) completedEvent.getFailure(), mfpActivity.getString(R.string.error_could_not_set_goals));
        }
    }
}
