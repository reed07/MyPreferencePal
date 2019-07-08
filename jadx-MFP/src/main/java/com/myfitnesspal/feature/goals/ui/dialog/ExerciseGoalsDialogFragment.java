package com.myfitnesspal.feature.goals.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.goals.event.GoalPreferencesUpdatedEvent;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.uacf.core.util.NumberUtils;
import dagger.Lazy;
import javax.inject.Inject;

public class ExerciseGoalsDialogFragment extends CustomLayoutBaseDialogFragment {
    @Inject
    Lazy<NutrientGoalsUtil> nutrientGoalsUtil;

    public static ExerciseGoalsDialogFragment newInstance() {
        return new ExerciseGoalsDialogFragment();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        final ContextThemeWrapper dialogContextThemeWrapper = getDialogContextThemeWrapper();
        final View inflate = LayoutInflater.from(dialogContextThemeWrapper).inflate(R.layout.edit_profile_exercise_goals, null);
        Spinner spinner = (Spinner) inflate.findViewById(R.id.workouts);
        ArrayAdapter createFromResource = ArrayAdapter.createFromResource(dialogContextThemeWrapper, R.array.workoutsPerWeek, R.layout.alert_dialog_spinner_item);
        createFromResource.setDropDownViewResource(R.layout.alert_dialog_spinner_dropdown_item);
        spinner.setAdapter(createFromResource);
        final EditText editText = (EditText) inflate.findViewById(R.id.minutes);
        User user = getSession().getUser();
        editText.setText(((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).getMinutesPerWorkoutForDisplay());
        editText.setSelection(editText.getText().length());
        spinner.setSelection(user.getUserV1GoalPreferences().getWorkoutsPerWeek());
        final InputMethodManager inputMethodManager = (InputMethodManager) dialogContextThemeWrapper.getSystemService("input_method");
        editText.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    ((InputMethodManager) dialogContextThemeWrapper.getSystemService("input_method")).toggleSoftInput(2, 1);
                }
            }
        });
        final AnonymousClass2 r3 = new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Spinner spinner = (Spinner) inflate.findViewById(R.id.workouts);
                int tryParseInt = NumberUtils.tryParseInt(editText.getText().toString());
                ExerciseGoalsDialogFragment.this.messageBus.post(new GoalPreferencesUpdatedEvent(NumberUtils.tryParseInt(spinner.getSelectedItem().toString()), tryParseInt));
                inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                dialogInterface.cancel();
            }
        };
        return new MfpAlertDialogBuilder(dialogContextThemeWrapper).setPositiveButton((int) R.string.save, (OnClickListener) r3).setNegativeButton((int) R.string.cancel, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                dialogInterface.cancel();
            }
        }).setTitle((CharSequence) dialogContextThemeWrapper.getResources().getString(R.string.exerciseGoalsTxt)).setView(inflate).setOnKeyListener(new OnKeyListener() {
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i == 4) {
                    dialogInterface.cancel();
                } else if (i == 66 && keyEvent.getRepeatCount() == 0) {
                    r3.onClick(dialogInterface, 0);
                    dialogInterface.cancel();
                    return true;
                }
                return false;
            }
        }).create();
    }
}
