package com.myfitnesspal.feature.dashboard.ui.view;

import android.view.View;
import android.view.ViewGroup;
import com.myfitnesspal.feature.dashboard.ui.view.NutrientDashboard.Type;
import com.myfitnesspal.feature.dashboard.ui.view.NutrientDashboardBase.DashboardUserType;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v2.MfpGoalPreferences;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.uacf.core.util.Function1;
import com.uacf.core.util.FunctionUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.inject.Provider;

public class NutrientDashboardRenderer {
    private static final int MAX_ENTRIES = 5;
    private Map<String, NutrientDashboard> dateToNutrientDashboardMap = new LinkedHashMap<String, NutrientDashboard>(5) {
        /* access modifiers changed from: protected */
        public boolean removeEldestEntry(Entry<String, NutrientDashboard> entry) {
            return size() > 5;
        }
    };
    private final Lazy<PremiumService> premiumService;
    private final Provider<RadialGraphNutrientDashboard> radialGraphNutrientDashboard;
    private final Lazy<Session> session;
    private final Provider<TextNutrientDashboard> textNutrientDashboard;

    public NutrientDashboardRenderer(Provider<TextNutrientDashboard> provider, Provider<RadialGraphNutrientDashboard> provider2, Lazy<Session> lazy, Lazy<PremiumService> lazy2) {
        this.textNutrientDashboard = provider;
        this.radialGraphNutrientDashboard = provider2;
        this.session = lazy;
        this.premiumService = lazy2;
    }

    public void render(MfpActivity mfpActivity, Type type, Calendar calendar, ViewGroup viewGroup, DiaryDay diaryDay, DashboardUserType dashboardUserType) {
        render(mfpActivity, type, calendar, viewGroup, diaryDay, dashboardUserType, null);
    }

    public void render(MfpActivity mfpActivity, Type type, Calendar calendar, ViewGroup viewGroup, Function1<NutrientDashboard> function1) {
        render(mfpActivity, type, calendar, viewGroup, null, DashboardUserType.Self, function1);
    }

    private void render(MfpActivity mfpActivity, Type type, Calendar calendar, final ViewGroup viewGroup, DiaryDay diaryDay, DashboardUserType dashboardUserType, final Function1<NutrientDashboard> function1) {
        String strings = Strings.toString(getDisplayType(type), Extras.DEFAULT_GOAL_DISPLAY);
        String format = String.format("%d-%d-%d", new Object[]{Integer.valueOf(calendar.get(5)), Integer.valueOf(calendar.get(2)), Integer.valueOf(calendar.get(1))});
        NutrientDashboard nutrientDashboard = (NutrientDashboard) this.dateToNutrientDashboardMap.get(format);
        if (nutrientDashboard == null || !Strings.equals(strings, nutrientDashboard.getCurrentDisplaySetting())) {
            nutrientDashboard = create(mfpActivity, type, calendar, strings);
            this.dateToNutrientDashboardMap.put(format, nutrientDashboard);
        }
        reparentDashboardView(nutrientDashboard, viewGroup);
        nutrientDashboard.render(new Function1<NutrientDashboard>() {
            public void execute(NutrientDashboard nutrientDashboard) throws RuntimeException {
                NutrientDashboardRenderer.this.reparentDashboardView(nutrientDashboard, viewGroup);
                FunctionUtils.invokeIfValid(function1, nutrientDashboard);
            }
        }, calendar, diaryDay, dashboardUserType);
    }

    /* access modifiers changed from: private */
    public void reparentDashboardView(NutrientDashboard nutrientDashboard, ViewGroup viewGroup) {
        if (nutrientDashboard != null) {
            View view = nutrientDashboard.getView();
            ViewGroup viewGroup2 = (ViewGroup) view.getParent();
            if (viewGroup2 != viewGroup) {
                if (viewGroup2 != null) {
                    viewGroup2.removeView(view);
                }
                if (viewGroup != null) {
                    viewGroup.removeAllViews();
                    viewGroup.addView(view);
                }
            }
        }
    }

    public void pause() {
        for (Entry value : this.dateToNutrientDashboardMap.entrySet()) {
            ((NutrientDashboard) value.getValue()).pause();
        }
    }

    public void resume() {
        for (Entry value : this.dateToNutrientDashboardMap.entrySet()) {
            ((NutrientDashboard) value.getValue()).resume();
        }
    }

    private NutrientDashboard create(MfpActivity mfpActivity, Type type, Calendar calendar, String str) {
        NutrientDashboard nutrientDashboard = (!isDashboardFeatureSubscribed() || type != Type.Home || Strings.equals(str, Extras.DEFAULT_GOAL_DISPLAY)) ? null : (NutrientDashboard) this.radialGraphNutrientDashboard.get();
        if (nutrientDashboard == null) {
            nutrientDashboard = (NutrientDashboard) this.textNutrientDashboard.get();
        }
        nutrientDashboard.initialize(mfpActivity, type, calendar, str);
        return nutrientDashboard;
    }

    private String getDisplayType(Type type) {
        if (isDashboardFeatureSubscribed()) {
            MfpGoalPreferences goalPreferences = ((Session) this.session.get()).getUser().getGoalPreferences();
            if (goalPreferences != null) {
                switch (type) {
                    case Diary:
                        return goalPreferences.getDiaryGoalDisplay();
                    case Home:
                        return goalPreferences.getHomeGoalDisplay();
                }
            }
        }
        return Extras.DEFAULT_GOAL_DISPLAY;
    }

    public void reset() {
        this.dateToNutrientDashboardMap.clear();
    }

    private boolean isDashboardFeatureSubscribed() {
        return ((PremiumService) this.premiumService.get()).isFeatureSubscribed(PremiumFeature.NutrientDashboard);
    }
}
