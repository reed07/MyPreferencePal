package com.myfitnesspal.feature.foodeditor.ui.dialog;

import android.os.Bundle;
import com.myfitnesspal.feature.foodeditor.event.ServingsEditedEvent;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpServingSize;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Ln;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class EditFoodGroupServingsDialogFragment extends EditServingsDialogFragmentBase {
    private static final String FOOD_GROUP_FOODS = "food_group_foods";
    private static final String SELECTED_FOOD_INDEX = "selected_food_index";
    private OnServingSizeSelectedListener onServingSizeSelectedListener;

    public interface OnServingSizeSelectedListener {
        void onServingSizeSelected(float f, int i);
    }

    public static EditFoodGroupServingsDialogFragment newInstance(ArrayList<MfpFood> arrayList, int i, float f, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(FOOD_GROUP_FOODS, arrayList);
        bundle.putInt(SELECTED_FOOD_INDEX, i);
        bundle.putFloat("current_servings", f);
        bundle.putBoolean("show_keyboard", z);
        EditFoodGroupServingsDialogFragment editFoodGroupServingsDialogFragment = new EditFoodGroupServingsDialogFragment();
        editFoodGroupServingsDialogFragment.setArguments(bundle);
        return editFoodGroupServingsDialogFragment;
    }

    /* access modifiers changed from: protected */
    public boolean onPositiveButtonClick() {
        try {
            int selectedSpinnerItemPosition = getSelectedSpinnerItemPosition();
            float numServings = getNumServings();
            this.messageBus.post(new ServingsEditedEvent(numServings, selectedSpinnerItemPosition));
            if (this.onServingSizeSelectedListener != null) {
                this.onServingSizeSelectedListener.onServingSizeSelected(numServings, selectedSpinnerItemPosition);
            }
            dismissDialogAndKeyboard();
            return true;
        } catch (ParseException e) {
            Ln.e(e);
            showInvalidServingView();
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public MfpServingSize getSelectedServingSize() {
        return (MfpServingSize) getServingSizes().get(getSelectedFoodIndex());
    }

    /* access modifiers changed from: protected */
    public List<MfpServingSize> getServingSizes() {
        ArrayList<MfpFood> parcelableArrayList = BundleUtils.getParcelableArrayList(getArguments(), FOOD_GROUP_FOODS, MfpFood.class.getClassLoader());
        ArrayList arrayList = new ArrayList(parcelableArrayList.size());
        for (MfpFood servingSizes : parcelableArrayList) {
            arrayList.add(servingSizes.getServingSizes().get(0));
        }
        return arrayList;
    }

    public void setOnServingSizeSelectedListener(OnServingSizeSelectedListener onServingSizeSelectedListener2) {
        this.onServingSizeSelectedListener = onServingSizeSelectedListener2;
    }

    private int getSelectedFoodIndex() {
        return BundleUtils.getInt(getArguments(), SELECTED_FOOD_INDEX);
    }
}
