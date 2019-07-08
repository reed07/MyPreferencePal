package com.myfitnesspal.feature.addentry.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.addentry.util.EditableServing;
import com.myfitnesspal.shared.constants.Constants.Dialogs;
import com.myfitnesspal.shared.model.v1.FoodPortion;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.view.CustomLocalizedNumberEditText;
import com.myfitnesspal.shared.util.FragmentUtil;
import com.uacf.core.util.ArrayUtil;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import java.text.ParseException;

public class EditServingsDialogFragment extends CustomLayoutBaseDialogFragment {
    private static final String SHOW_KEYBOARD = "show_keyboard";

    public static EditServingsDialogFragment newInstance() {
        return newInstance(true);
    }

    public static EditServingsDialogFragment newInstance(boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(SHOW_KEYBOARD, z);
        EditServingsDialogFragment editServingsDialogFragment = new EditServingsDialogFragment();
        editServingsDialogFragment.setArguments(bundle);
        return editServingsDialogFragment;
    }

    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        EditableServing editableServing = (EditableServing) FragmentUtil.getTargetFragmentOrParentActivity(this, EditableServing.class);
        String str = null;
        View inflate = LayoutInflater.from(getDialogContextThemeWrapper()).inflate(R.layout.edit_food_entry_serving, null);
        Spinner spinner = (Spinner) inflate.findViewById(R.id.serving_size);
        FoodPortion[] foodPortions = (editableServing == null || editableServing.getFood() == null) ? null : editableServing.getFood().getFoodPortions();
        if (!(editableServing == null || editableServing.getFoodPortion() == null)) {
            str = editableServing.getFoodPortion().descriptionWithAmount();
        }
        int size = ArrayUtil.size(foodPortions);
        String[] strArr = new String[size];
        int i = -1;
        for (int i2 = 0; i2 < size; i2++) {
            strArr[i2] = foodPortions[i2].descriptionWithAmount();
            if (Strings.notEmpty(str) && foodPortions[i2].descriptionWithAmount().equalsIgnoreCase(str)) {
                i = i2;
            }
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(getDialogContextThemeWrapper(), R.layout.alert_dialog_spinner_item, strArr);
        arrayAdapter.setDropDownViewResource(R.layout.alert_dialog_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnTouchListener(new OnTouchListener(inflate) {
            private final /* synthetic */ View f$1;

            {
                this.f$1 = r2;
            }

            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return EditServingsDialogFragment.lambda$onCreateDialog$0(EditServingsDialogFragment.this, this.f$1, view, motionEvent);
            }
        });
        if (i != -1) {
            spinner.setSelection(i);
        }
        CustomLocalizedNumberEditText customLocalizedNumberEditText = (CustomLocalizedNumberEditText) inflate.findViewById(R.id.numOfServings);
        customLocalizedNumberEditText.setText(editableServing != null ? NumberUtils.localeStringFromFloat(editableServing.getServings()) : "");
        MfpAlertDialogBuilder mfpAlertDialogBuilder = new MfpAlertDialogBuilder(getDialogContextThemeWrapper());
        $$Lambda$EditServingsDialogFragment$jdCJC5D8OWCxFXb6IlEoNwZ6Fs r0 = new OnClickListener(spinner, size, foodPortions, customLocalizedNumberEditText) {
            private final /* synthetic */ Spinner f$1;
            private final /* synthetic */ int f$2;
            private final /* synthetic */ FoodPortion[] f$3;
            private final /* synthetic */ CustomLocalizedNumberEditText f$4;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                EditServingsDialogFragment.lambda$onCreateDialog$1(EditableServing.this, this.f$1, this.f$2, this.f$3, this.f$4, dialogInterface, i);
            }
        };
        AlertDialog create = mfpAlertDialogBuilder.setPositiveButton((int) R.string.save, (OnClickListener) r0).setNegativeButton((int) R.string.cancel, (OnClickListener) new OnClickListener(editableServing) {
            private final /* synthetic */ EditableServing f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                EditServingsDialogFragment.lambda$onCreateDialog$2(CustomLocalizedNumberEditText.this, this.f$1, dialogInterface, i);
            }
        }).setOnCancelListener(new OnCancelListener() {
            public final void onCancel(DialogInterface dialogInterface) {
                CustomLocalizedNumberEditText.this.clearFocus();
            }
        }).setTitle((int) R.string.how_much).setView(inflate).create();
        customLocalizedNumberEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
            public final void onFocusChange(View view, boolean z) {
                EditServingsDialogFragment.lambda$onCreateDialog$4(CustomLocalizedNumberEditText.this, view, z);
            }
        });
        if (BundleUtils.getBoolean(getArguments(), SHOW_KEYBOARD)) {
            create.getWindow().setSoftInputMode(5);
            customLocalizedNumberEditText.requestFocus();
        }
        return create;
    }

    public static /* synthetic */ boolean lambda$onCreateDialog$0(EditServingsDialogFragment editServingsDialogFragment, View view, View view2, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            editServingsDialogFragment.hideSoftInputFor(view);
        }
        return false;
    }

    static /* synthetic */ void lambda$onCreateDialog$1(EditableServing editableServing, Spinner spinner, int i, FoodPortion[] foodPortionArr, CustomLocalizedNumberEditText customLocalizedNumberEditText, DialogInterface dialogInterface, int i2) {
        if (editableServing != null) {
            try {
                int selectedItemPosition = spinner.getSelectedItemPosition();
                if (selectedItemPosition >= 0 && selectedItemPosition < i) {
                    editableServing.setFoodPortion(foodPortionArr[selectedItemPosition]);
                }
                editableServing.initFoodData(NumberUtils.localeFloatFromString(NumberUtils.normalizeInputString(Strings.trimmed((Object) customLocalizedNumberEditText.getText()))));
                dialogInterface.cancel();
                customLocalizedNumberEditText.clearFocus();
                editableServing.hideSoftInput();
            } catch (NumberFormatException | ParseException e) {
                dialogInterface.cancel();
                editableServing.showDialogFragment(Dialogs.INVALID_INPUT);
                Ln.e(e);
            } catch (Exception e2) {
                Ln.e(e2);
            }
        }
    }

    static /* synthetic */ void lambda$onCreateDialog$2(CustomLocalizedNumberEditText customLocalizedNumberEditText, EditableServing editableServing, DialogInterface dialogInterface, int i) {
        try {
            dialogInterface.cancel();
            customLocalizedNumberEditText.clearFocus();
            if (editableServing != null) {
                editableServing.hideSoftInput();
            }
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    static /* synthetic */ void lambda$onCreateDialog$4(CustomLocalizedNumberEditText customLocalizedNumberEditText, View view, boolean z) {
        if (z) {
            customLocalizedNumberEditText.setSelection(0, customLocalizedNumberEditText.getText().length());
        }
    }
}
