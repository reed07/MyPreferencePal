package com.myfitnesspal.feature.foodeditor.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.foodeditor.event.ServingsEditedEvent;
import com.myfitnesspal.feature.foodeditor.task.GetSuggestedServingsTask;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpServingSize;
import com.myfitnesspal.shared.service.foods.FoodService;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.taskrunner.Task;
import dagger.Lazy;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import javax.inject.Inject;

public class EditFoodServingsDialogFragment extends EditServingsDialogFragmentBase {
    private static final String FOOD = "food";
    private static final String GET_SUGGESTED_SERVINGS = "get_suggested_servings";
    private static final String SELECTED_SERVING_SIZE = "selected_serving_size";
    @Inject
    Lazy<FoodService> foodService;
    private MfpFood mfpFood;
    private OnServingSizeSelectedListener onServingSizeSelectedListener;
    private List<MfpServingSize> servingSizes;
    private boolean shouldGetSuggestedServings;

    public interface OnServingSizeSelectedListener {
        void onServingSizeSelected(MfpServingSize mfpServingSize, float f, int i);
    }

    public static EditFoodServingsDialogFragment newInstance(MfpFood mfpFood2, MfpServingSize mfpServingSize, float f, boolean z, boolean z2) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("food", mfpFood2);
        bundle.putParcelable(SELECTED_SERVING_SIZE, mfpServingSize);
        bundle.putFloat("current_servings", f);
        bundle.putBoolean("show_keyboard", z);
        bundle.putBoolean(GET_SUGGESTED_SERVINGS, z2);
        EditFoodServingsDialogFragment editFoodServingsDialogFragment = new EditFoodServingsDialogFragment();
        editFoodServingsDialogFragment.setArguments(bundle);
        return editFoodServingsDialogFragment;
    }

    /* access modifiers changed from: protected */
    public boolean onPositiveButtonClick() {
        try {
            int selectedSpinnerItemPosition = getSelectedSpinnerItemPosition();
            float numServings = getNumServings();
            MfpServingSize mfpServingSize = (MfpServingSize) getServingSizes().get(selectedSpinnerItemPosition);
            this.messageBus.post(new ServingsEditedEvent(mfpServingSize, numServings));
            if (this.onServingSizeSelectedListener != null) {
                this.onServingSizeSelectedListener.onServingSizeSelected(mfpServingSize, numServings, selectedSpinnerItemPosition);
            }
            dismissDialogAndKeyboard();
            return true;
        } catch (ParseException e) {
            Ln.e(e);
            showInvalidServingView();
            return false;
        }
    }

    public void setOnServingSizeSelectedListener(OnServingSizeSelectedListener onServingSizeSelectedListener2) {
        this.onServingSizeSelectedListener = onServingSizeSelectedListener2;
    }

    /* access modifiers changed from: protected */
    public MfpServingSize getSelectedServingSize() {
        return (MfpServingSize) BundleUtils.getParcelable(getArguments(), SELECTED_SERVING_SIZE, MfpServingSize.class.getClassLoader());
    }

    /* access modifiers changed from: protected */
    public List<MfpServingSize> getServingSizes() {
        return this.servingSizes;
    }

    private MfpFood getMfpFood() {
        return (MfpFood) BundleUtils.getParcelable(getArguments(), "food", MfpFood.class.getClassLoader());
    }

    public Boolean shouldGetSuggestedServings() {
        return Boolean.valueOf(BundleUtils.getBoolean(getArguments(), GET_SUGGESTED_SERVINGS));
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        this.mfpFood = getMfpFood();
        this.shouldGetSuggestedServings = shouldGetSuggestedServings().booleanValue();
        this.servingSizes = new ArrayList(this.mfpFood.getServingSizes());
        Dialog onCreateDialog = super.onCreateDialog(bundle);
        setupSuggestedServings();
        return onCreateDialog;
    }

    private void setupSuggestedServings() {
        if (this.shouldGetSuggestedServings) {
            new GetSuggestedServingsTask(this.foodService, this.mfpFood).run(getRunner());
        }
    }

    public void onTaskCompleted(String str, long j, Task task, Object obj) {
        if (GetSuggestedServingsTask.matches(str)) {
            ApiResponse apiResponse = (ApiResponse) obj;
            if (apiResponse != null && CollectionUtils.notEmpty((Collection<?>) apiResponse.getItems())) {
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                linkedHashSet.addAll(getServingSizes());
                linkedHashSet.addAll(apiResponse.getItems());
                ArrayList arrayList = new ArrayList(linkedHashSet);
                this.servingSizes = arrayList;
                ArrayList arrayList2 = new ArrayList();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    arrayList2.add(((MfpServingSize) it.next()).descriptionWithAmount());
                }
                String[] strArr = (String[]) arrayList2.toArray(new String[arrayList2.size()]);
                ArrayAdapter arrayAdapter = new ArrayAdapter(getDialogContextThemeWrapper(), R.layout.alert_dialog_spinner_item, strArr);
                arrayAdapter.setDropDownViewResource(R.layout.alert_dialog_spinner_dropdown_item);
                this.servingSizeSpinner.setAdapter(arrayAdapter);
                for (int i = 0; i < strArr.length; i++) {
                    if (Strings.equalsIgnoreCase(strArr[i], this.mfpFood.getSelectedServingSize().descriptionWithAmount())) {
                        this.servingSizeSpinner.setSelection(i);
                    }
                }
            }
            this.servingSizeSpinner.setEnabled(true);
            if (this.servingSizeSpinner.getSelectedView() != null) {
                this.servingSizeSpinner.getSelectedView().setEnabled(true);
            }
        }
    }
}
