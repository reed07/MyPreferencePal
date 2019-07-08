package com.myfitnesspal.feature.nutrition.service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.home.ui.adapter.NutrientEntryAdapter;
import com.myfitnesspal.feature.nutrition.model.NutrientData;
import com.myfitnesspal.feature.nutrition.model.NutrientDetails;
import com.myfitnesspal.feature.nutrition.model.NutrientEntry;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v1.NutritionalValues;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.squareup.otto.Bus;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.TextViewUtils;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

public class NutritionDetailsDelegate {
    private final Context context;
    private final boolean isPremiumUser;
    @Inject
    Lazy<Bus> messageBus;
    @Inject
    Lazy<NutrientGoalService> nutrientGoalService;
    @Inject
    Lazy<NutrientGoalsUtil> nutritionalGoalsUtil;
    @Inject
    Lazy<UserEnergyService> userEnergyService;

    public NutritionDetailsDelegate(Context context2, boolean z) {
        MyFitnessPalApp.getInstance().component().inject(this);
        this.context = context2;
        this.isPremiumUser = z;
    }

    public List<NutrientEntry> getDailyNutrientDetails(Date date, int i) {
        NutrientDetails[] nutrientDetailsArr = new NutrientDetails[17];
        addNutritionalInfoForDiaryDay(getDiaryDayForDate(date), getMfpDailyGoalForDate(date), nutrientDetailsArr);
        return getNutrientEntriesFromNutrientDetails(nutrientDetailsArr, i);
    }

    public List<NutrientEntry> getWeeklyNutrientDetails(Date date, int i) {
        NutrientDetails[] nutrientDetailsArr = new NutrientDetails[17];
        Date offsetDate = DateTimeUtils.offsetDate(date, 6);
        int i2 = 0;
        for (int i3 = 6; i3 >= 0; i3--) {
            Date offsetDate2 = DateTimeUtils.offsetDate((Date) offsetDate.clone(), -i3);
            if (addNutritionalInfoForDiaryDay(getDiaryDayForDate(offsetDate2), getMfpDailyGoalForDate(offsetDate2), nutrientDetailsArr)) {
                i2++;
            }
        }
        for (int i4 = 0; i4 < 17; i4++) {
            NutrientDetails nutrientDetails = nutrientDetailsArr[i4];
            if (i2 > 0) {
                nutrientDetails.setNutrientsConsumed(nutrientDetails.getNutrientsConsumed() / ((float) i2));
            }
            nutrientDetails.setNutritionalGoals(nutrientDetails.getNutritionalGoals() / 7.0f);
        }
        return getNutrientEntriesFromNutrientDetails(nutrientDetailsArr, i);
    }

    public View buildNutrientLayout(List<NutrientEntry> list, boolean z, boolean z2) {
        LinearLayout linearLayout = new LinearLayout(this.context);
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LayoutParams(-1, -1));
        linearLayout.addView(buildNutrientDetailsHeader(linearLayout, z));
        if (!z2) {
            list = rearrangeNutrients(list);
        }
        NutrientEntryAdapter nutrientEntryAdapter = new NutrientEntryAdapter(this.context, list, this.messageBus, this.isPremiumUser);
        for (int i = 0; i < nutrientEntryAdapter.getCount(); i++) {
            linearLayout.addView(nutrientEntryAdapter.getView(i, null, linearLayout));
        }
        return linearLayout;
    }

    private List<NutrientEntry> rearrangeNutrients(List<NutrientEntry> list) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(list.get(12));
        arrayList.add(list.get(9));
        arrayList.add(list.get(10));
        arrayList.add(list.get(11));
        arrayList.add(list.get(1));
        arrayList.add(list.get(2));
        arrayList.add(list.get(3));
        arrayList.add(list.get(4));
        arrayList.add(list.get(5));
        arrayList.add(list.get(6));
        arrayList.add(list.get(7));
        arrayList.add(list.get(8));
        arrayList.add(list.get(13));
        arrayList.add(list.get(14));
        arrayList.add(list.get(15));
        arrayList.add(list.get(16));
        return arrayList;
    }

    private View buildNutrientDetailsHeader(ViewGroup viewGroup, boolean z) {
        LayoutInflater from = LayoutInflater.from(this.context);
        if (!this.isPremiumUser) {
            return from.inflate(R.layout.nutrients_header_blue, null);
        }
        View inflate = from.inflate(R.layout.nutrients_header_grey, viewGroup, false);
        TextViewUtils.setText((TextView) ViewUtils.findById(inflate, R.id.txtLabel), this.context.getString(z ? R.string.weekly_graph_average_token : R.string.totalTxt));
        return inflate;
    }

    private boolean addNutritionalInfoForDiaryDay(DiaryDay diaryDay, MfpDailyGoal mfpDailyGoal, NutrientDetails[] nutrientDetailsArr) {
        NutritionalValues adjustedNutrientGoals = diaryDay.getAdjustedNutrientGoals();
        boolean z = false;
        for (int i = 0; i < 17; i++) {
            NutrientDetails nutrientDetails = nutrientDetailsArr[i];
            if (nutrientDetails == null) {
                nutrientDetails = new NutrientDetails();
                nutrientDetailsArr[i] = nutrientDetails;
            }
            float amountOfNutrientConsumed = diaryDay.amountOfNutrientConsumed(i);
            if (i == 0) {
                z = !NumberUtils.isEffectivelyZero((double) amountOfNutrientConsumed);
            }
            nutrientDetails.addNutrientsConsumed(amountOfNutrientConsumed);
            nutrientDetails.setUnits(NutritionalValues.unitForNutrientIndex(this.context, i));
            if (adjustedNutrientGoals != null) {
                nutrientDetails.addNutritionalGoals(((NutrientGoalsUtil) this.nutritionalGoalsUtil.get()).getAdjustedNutritionalGoal(diaryDay, mfpDailyGoal, i));
                nutrientDetails.setIsSubordinateNutrientIndex(adjustedNutrientGoals.isSubordinateNutrientIndex(i));
                nutrientDetails.setIsPercentage(adjustedNutrientGoals.nutrientIndexIsPercentage(i));
            }
        }
        return z;
    }

    private List<NutrientEntry> getNutrientEntriesFromNutrientDetails(NutrientDetails[] nutrientDetailsArr, int i) {
        ArrayList arrayList = new ArrayList(17);
        NutrientData nutrientData = new NutrientData(nutrientDetailsArr, true);
        boolean isCalories = ((UserEnergyService) this.userEnergyService.get()).isCalories();
        while (i < 17) {
            NutrientEntry nutrientEntry = new NutrientEntry(i, nutrientData.getFormattedLabel(this.context, i, isCalories), nutrientData.getFormattedTotal(i), nutrientData.getFormattedGoalNoUnits(i), nutrientData.getFormattedRemaining(i), nutrientData.getIsSubordinateNutrient(i));
            arrayList.add(nutrientEntry);
            i++;
        }
        return arrayList;
    }

    private DiaryDay getDiaryDayForDate(Date date) {
        DiaryDay diaryDay = new DiaryDay();
        diaryDay.initFromDatabaseForDate(date);
        return diaryDay;
    }

    private MfpDailyGoal getMfpDailyGoalForDate(Date date) {
        return ((NutrientGoalService) this.nutrientGoalService.get()).getMfpDailyGoalFromDB(date);
    }
}
