package com.myfitnesspal.feature.dashboard.ui.view;

import android.view.View;
import com.myfitnesspal.feature.dashboard.ui.view.NutrientDashboardBase.DashboardUserType;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.uacf.core.util.Function1;
import java.util.Calendar;

public interface NutrientDashboard {

    public enum Type {
        Home,
        Diary
    }

    String getCurrentDisplaySetting();

    View getView();

    void initialize(MfpActivity mfpActivity, Type type, Calendar calendar, String str);

    void pause();

    void render(Function1<NutrientDashboard> function1, DiaryDay diaryDay);

    void render(Function1<NutrientDashboard> function1, Calendar calendar, DiaryDay diaryDay, DashboardUserType dashboardUserType);

    void resume();
}
