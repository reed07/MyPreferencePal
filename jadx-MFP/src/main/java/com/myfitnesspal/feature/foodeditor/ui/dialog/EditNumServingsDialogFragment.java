package com.myfitnesspal.feature.foodeditor.ui.dialog;

import android.os.Bundle;
import com.myfitnesspal.feature.foodeditor.event.ServingsEditedEvent;
import com.myfitnesspal.shared.model.v2.MfpServingSize;
import com.uacf.core.util.Ln;
import java.text.ParseException;
import java.util.List;

public class EditNumServingsDialogFragment extends EditServingsDialogFragmentBase {
    private OnServingSizeSelectedListener onServingSizeSelectedListener;

    public interface OnServingSizeSelectedListener {
        void onServingSizeSelected(float f);
    }

    /* access modifiers changed from: protected */
    public MfpServingSize getSelectedServingSize() {
        return null;
    }

    /* access modifiers changed from: protected */
    public List<MfpServingSize> getServingSizes() {
        return null;
    }

    public static EditNumServingsDialogFragment newInstance(float f, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putFloat("current_servings", f);
        bundle.putBoolean("show_keyboard", z);
        EditNumServingsDialogFragment editNumServingsDialogFragment = new EditNumServingsDialogFragment();
        editNumServingsDialogFragment.setArguments(bundle);
        return editNumServingsDialogFragment;
    }

    /* access modifiers changed from: protected */
    public boolean onPositiveButtonClick() {
        try {
            float numServings = getNumServings();
            this.messageBus.post(new ServingsEditedEvent(numServings));
            if (this.onServingSizeSelectedListener != null) {
                this.onServingSizeSelectedListener.onServingSizeSelected(numServings);
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
}
