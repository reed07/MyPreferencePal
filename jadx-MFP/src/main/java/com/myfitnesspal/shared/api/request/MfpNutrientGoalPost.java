package com.myfitnesspal.shared.api.request;

import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.model.v2.MfpNutrientGoal;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.constants.DateTime.Format;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

public class MfpNutrientGoalPost {
    @Expose
    private List<MfpDailyGoalPost> dailyGoals;
    @Expose
    private MfpDailyGoalPost defaultGoal;
    @Expose
    private String validFrom;

    public static class API_RESPONSE_MAPPER extends ApiResponse<MfpNutrientGoalPost> {
    }

    public static class LIST_MAPPER extends ArrayList<MfpNutrientGoalPost> {
    }

    public MfpNutrientGoalPost(MfpNutrientGoal mfpNutrientGoal) {
        ArrayList arrayList = new ArrayList();
        Enumerable.forEach((Collection<T>) mfpNutrientGoal.getDailyGoals(), (Function1<T>) new Function1(arrayList) {
            private final /* synthetic */ List f$0;

            {
                this.f$0 = r1;
            }

            public final void execute(Object obj) {
                MfpNutrientGoalPost.lambda$new$0(this.f$0, (MfpDailyGoal) obj);
            }
        });
        this.dailyGoals = arrayList;
        MfpDailyGoal defaultGoal2 = mfpNutrientGoal.getDefaultGoal();
        this.defaultGoal = Strings.equals(defaultGoal2.getAssignExerciseEnergy(), "custom") ? new MfpDailyGoalsCustomPost(defaultGoal2) : new MfpDailyGoalPost(defaultGoal2);
        this.validFrom = DateTimeUtils.format(Format.newIso8601DateFormat().toPattern(), Calendar.getInstance().getTime());
    }

    static /* synthetic */ void lambda$new$0(List list, MfpDailyGoal mfpDailyGoal) throws RuntimeException {
        if (mfpDailyGoal != null) {
            list.add(Strings.equals(mfpDailyGoal.getAssignExerciseEnergy(), "custom") ? new MfpDailyGoalsCustomPost(mfpDailyGoal) : new MfpDayOfWeekDailyGoalPost(mfpDailyGoal));
        }
    }

    public void setDailyGoals(List<MfpDailyGoalPost> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.dailyGoals = list;
    }

    public void setDefaultGoal(MfpDailyGoalPost mfpDailyGoalPost) {
        this.defaultGoal = mfpDailyGoalPost;
    }

    public void setValidFrom(String str) {
        this.validFrom = str;
    }

    public List<MfpDailyGoalPost> getDailyGoals() {
        return this.dailyGoals;
    }

    public MfpDailyGoalPost getDefaultGoal() {
        return this.defaultGoal;
    }

    public String getValidFrom() {
        return this.validFrom;
    }
}
