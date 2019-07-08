package com.myfitnesspal.feature.diary.ui.dialog;

import android.os.Bundle;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.dialog.impl.MealNamesDialogFragment;
import com.uacf.core.util.BundleUtils;
import java.util.Date;

public class MealNamesForCopyingItemsDialogFragment extends MealNamesDialogFragment {
    private static final String EXTRA_COPY_FROM_MEAL_NAME = "copy_from_meal_name";
    private static final String EXTRA_COPY_TO_DATE = "copy_to_date";
    private OnMealSelectedListener onMealSelectedListener;

    public interface OnMealSelectedListener {
        void onMealSelected(String str, String str2, Date date);
    }

    /* access modifiers changed from: protected */
    public int getTitleStringResId() {
        return R.string.copy_to_meal_colon;
    }

    public static MealNamesForCopyingItemsDialogFragment newInstance(String str, Date date) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_COPY_FROM_MEAL_NAME, str);
        bundle.putSerializable(EXTRA_COPY_TO_DATE, date);
        MealNamesForCopyingItemsDialogFragment mealNamesForCopyingItemsDialogFragment = new MealNamesForCopyingItemsDialogFragment();
        mealNamesForCopyingItemsDialogFragment.setArguments(bundle);
        return mealNamesForCopyingItemsDialogFragment;
    }

    public void setOnMealSelectedListener(OnMealSelectedListener onMealSelectedListener2) {
        this.onMealSelectedListener = onMealSelectedListener2;
    }

    /* access modifiers changed from: protected */
    public void mealSelected(String str) {
        Bundle arguments = getArguments();
        this.onMealSelectedListener.onMealSelected(str, BundleUtils.getString(arguments, EXTRA_COPY_FROM_MEAL_NAME), (Date) BundleUtils.getSerializable(arguments, EXTRA_COPY_TO_DATE, Date.class.getClassLoader()));
    }
}
