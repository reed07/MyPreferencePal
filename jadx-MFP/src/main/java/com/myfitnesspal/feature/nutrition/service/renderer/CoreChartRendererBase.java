package com.myfitnesspal.feature.nutrition.service.renderer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.nutrition.service.CoreChartRenderer;
import com.myfitnesspal.feature.nutrition.service.CoreRendererBase;
import com.myfitnesspal.feature.nutrition.service.NutritionGraphService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public abstract class CoreChartRendererBase extends CoreRendererBase implements CoreChartRenderer {
    private final Lazy<CoreChartRendererBaseConstructorArgs> coreChartRendererBaseConstructorArgs;
    private int graphSubType = 1;

    /* access modifiers changed from: protected */
    public abstract void constructDailyChart(ViewGroup viewGroup, Date date, String str, int i, int i2);

    /* access modifiers changed from: protected */
    public abstract void constructWeeklyChart(ViewGroup viewGroup, Date date, int i, int i2);

    /* access modifiers changed from: protected */
    public abstract int getSpinnerContainerId();

    protected CoreChartRendererBase(Context context, Lazy<CoreChartRendererBaseConstructorArgs> lazy) {
        super(context);
        this.coreChartRendererBaseConstructorArgs = lazy;
    }

    public void addDailyChart(ViewGroup viewGroup, Date date, String str, int i, int i2) {
        toggleSpinnerContainerVisibility(viewGroup, true);
        constructDailyChart(viewGroup, date, str, i, i2);
    }

    public void addWeeklyChart(ViewGroup viewGroup, Date date, String str, int i, int i2) {
        toggleSpinnerContainerVisibility(viewGroup, true);
        constructWeeklyChart(viewGroup, getFirstDayOfWeek(date), i, i2);
    }

    /* access modifiers changed from: protected */
    public Date getFirstDayOfWeek(Date date) {
        return DateTimeUtils.startDayOnOrPriorTo(date, getLocalSettingsService().getWeeklyStartDay());
    }

    /* access modifiers changed from: protected */
    public String[] getLabelsForDays(Date date) {
        String[] shortWeekdays = new DateFormatSymbols(Locale.getDefault()).getShortWeekdays();
        int[] weekIndices = getWeekIndices(date);
        String[] strArr = new String[7];
        for (int i = 0; i < 7; i++) {
            String str = shortWeekdays[weekIndices[i]];
            StringBuilder sb = new StringBuilder();
            sb.append(str.substring(0, 1).toUpperCase());
            sb.append(str.substring(1));
            strArr[i] = sb.toString();
        }
        return strArr;
    }

    private int[] getWeekIndices(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        int i = instance.get(7);
        int[] iArr = new int[7];
        for (int i2 = 0; i2 < 7; i2++) {
            int i3 = i + i2;
            if (i3 > 7) {
                i3 %= 7;
            }
            iArr[i2] = i3;
        }
        return iArr;
    }

    /* access modifiers changed from: protected */
    public boolean isCalories() {
        return getUserEnergyService().isCalories();
    }

    /* access modifiers changed from: protected */
    public double getYAxisIncrement(float f) {
        return Math.pow(10.0d, (double) (Integer.toString(Math.round(f)).length() - 1));
    }

    /* access modifiers changed from: protected */
    public double getYAxisIncrement(float f, int i) {
        if (f == BitmapDescriptorFactory.HUE_RED) {
            return 0.0d;
        }
        int i2 = ((int) f) / i;
        if (i2 % 100 != 0) {
            i2 = ((i2 + 99) / 100) * 100;
        }
        return (double) i2;
    }

    /* access modifiers changed from: protected */
    public void hideSpinnerContainer(View view) {
        toggleSpinnerContainerVisibility(view, false);
    }

    private void toggleSpinnerContainerVisibility(View view, boolean z) {
        ViewUtils.setVisible(z, getSpinnerContainer(view));
    }

    /* access modifiers changed from: protected */
    public void removeSpinnerContainerBackground(View view) {
        getSpinnerContainer(view).setBackgroundDrawable(null);
    }

    private View getSpinnerContainer(View view) {
        return ViewUtils.findById(view, getSpinnerContainerId());
    }

    /* access modifiers changed from: protected */
    public int getColor(int i) {
        return this.context.getResources().getColor(i);
    }

    public void setGraphSubType(int i) {
        this.graphSubType = i;
    }

    public int getGraphSubType() {
        return this.graphSubType;
    }

    private CoreChartRendererBaseConstructorArgs getCoreChartRendererBaseConstructorArgs() {
        return (CoreChartRendererBaseConstructorArgs) this.coreChartRendererBaseConstructorArgs.get();
    }

    /* access modifiers changed from: protected */
    public LocalSettingsService getLocalSettingsService() {
        return (LocalSettingsService) getCoreChartRendererBaseConstructorArgs().getLocalSettingsService().get();
    }

    /* access modifiers changed from: protected */
    public Session getSession() {
        return (Session) getCoreChartRendererBaseConstructorArgs().getSession().get();
    }

    /* access modifiers changed from: protected */
    public UserEnergyService getUserEnergyService() {
        return (UserEnergyService) getCoreChartRendererBaseConstructorArgs().getUserEnergyService().get();
    }

    /* access modifiers changed from: protected */
    public NutrientGoalService getNutrientGoalService() {
        return (NutrientGoalService) getCoreChartRendererBaseConstructorArgs().getNutrientGoalService().get();
    }

    /* access modifiers changed from: protected */
    public NutrientGoalsUtil getNutritionalGoalsUtil() {
        return (NutrientGoalsUtil) getCoreChartRendererBaseConstructorArgs().getNutritionalGoalsUtil().get();
    }

    /* access modifiers changed from: protected */
    public NutritionGraphService getNutritionGraphService() {
        return (NutritionGraphService) getCoreChartRendererBaseConstructorArgs().getNutritionGraphService().get();
    }
}
