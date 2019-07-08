package com.myfitnesspal.feature.meals.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.meals.service.MealService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import javax.inject.Inject;

public class DeleteMealConfirmationDialogFragment extends CustomLayoutBaseDialogFragment {
    /* access modifiers changed from: private */
    public OnDeleteMealClickListener deleteMealClickListener;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    @Inject
    Lazy<MealService> mealService;

    public interface OnDeleteMealClickListener {
        void onDeleteMealClick(boolean z);
    }

    public static DeleteMealConfirmationDialogFragment newInstance() {
        return new DeleteMealConfirmationDialogFragment();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        MfpAlertDialogBuilder mfpAlertDialogBuilder = new MfpAlertDialogBuilder(getActivity());
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.delete_entry_confirm, null);
        final CheckBox checkBox = (CheckBox) ViewUtils.findById(inflate, R.id.dont_ask);
        ((TextView) ViewUtils.findById(inflate, R.id.message)).setText(R.string.delete_meal_confirmation);
        mfpAlertDialogBuilder.setPositiveButton((int) R.string.delete, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                DeleteMealConfirmationDialogFragment.this.deleteMealClickListener.onDeleteMealClick(checkBox.isChecked());
            }
        }).setNegativeButton((int) R.string.cancel, (OnClickListener) null).setTitle((int) R.string.delete).setView(inflate);
        return mfpAlertDialogBuilder.create();
    }

    public void setDeleteMealClickListener(OnDeleteMealClickListener onDeleteMealClickListener) {
        this.deleteMealClickListener = onDeleteMealClickListener;
    }
}
