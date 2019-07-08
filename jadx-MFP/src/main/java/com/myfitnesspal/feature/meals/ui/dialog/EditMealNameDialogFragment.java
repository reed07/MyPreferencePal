package com.myfitnesspal.feature.meals.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;

public class EditMealNameDialogFragment extends CustomLayoutBaseDialogFragment {
    private static final String EXTRA_MEAL_NAME = "meal_name";
    /* access modifiers changed from: private */
    public EditText mealFoodNameInput;
    /* access modifiers changed from: private */
    public TextView mealNameError;
    /* access modifiers changed from: private */
    public OnNewMealNameSetListener onNewMealNameSetListener;

    public interface OnNewMealNameSetListener {
        void onCancelled();

        void onNewMealNameSet(String str);
    }

    public static EditMealNameDialogFragment newInstance(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("meal_name", str);
        EditMealNameDialogFragment editMealNameDialogFragment = new EditMealNameDialogFragment();
        editMealNameDialogFragment.setArguments(bundle);
        return editMealNameDialogFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.edit_meal_name_dialog, null);
        this.mealFoodNameInput = (EditText) ViewUtils.findById(inflate, R.id.meal_name_input);
        this.mealNameError = (TextView) ViewUtils.findById(inflate, R.id.meal_name_error);
        ViewUtils.setInvisible(this.mealNameError);
        this.mealFoodNameInput.setText(getMealName());
        this.mealFoodNameInput.selectAll();
        return new MfpAlertDialogBuilder(getActivity()).setTitle((int) R.string.duplicate_meal_name_title).setView(inflate).setPositiveButton((int) R.string.ok, (OnClickListener) null).setNegativeButton((int) R.string.cancel, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                EditMealNameDialogFragment.this.onNewMealNameSetListener.onCancelled();
            }
        }).create();
    }

    public void onStart() {
        super.onStart();
        ((AlertDialog) getDialog()).getButton(-1).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String strings = Strings.toString(EditMealNameDialogFragment.this.mealFoodNameInput.getText());
                if (Strings.isEmpty(strings) || Strings.equals(strings, EditMealNameDialogFragment.this.getMealName())) {
                    ViewUtils.setVisible(EditMealNameDialogFragment.this.mealNameError);
                    EditMealNameDialogFragment.this.mealNameError.setText(Strings.isEmpty(strings) ? R.string.meal_not_blank : R.string.meal_not_duplicate);
                    return;
                }
                EditMealNameDialogFragment.this.onNewMealNameSetListener.onNewMealNameSet(strings);
                EditMealNameDialogFragment.this.dismiss();
            }
        });
    }

    public void setOnNewMealNameSetListener(OnNewMealNameSetListener onNewMealNameSetListener2) {
        this.onNewMealNameSetListener = onNewMealNameSetListener2;
    }

    /* access modifiers changed from: private */
    public String getMealName() {
        return BundleUtils.getString(getArguments(), "meal_name");
    }
}
