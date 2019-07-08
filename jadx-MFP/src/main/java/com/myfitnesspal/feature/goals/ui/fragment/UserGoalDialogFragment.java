package com.myfitnesspal.feature.goals.ui.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.goals.event.GoalUpdatedEvent;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.view.CustomLocalizedNumberEditText;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;

public class UserGoalDialogFragment extends CustomLayoutBaseDialogFragment {
    private static final String CURRENT_VALUE = "current_value";
    private static final String GOAL_ID = "goal_id";
    private static final String TITLE_RESOURCE_ID = "title_resource_id";
    private static final String UNIT_ABBREVIATION = "unit_abbreviation";
    private float currentValue;
    private String goalId;
    private String title;
    private String unitAbbreviation;
    /* access modifiers changed from: private */
    public CustomLocalizedNumberEditText value;

    public static UserGoalDialogFragment newInstance(String str, String str2, String str3, float f) {
        UserGoalDialogFragment userGoalDialogFragment = new UserGoalDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE_RESOURCE_ID, str);
        bundle.putString(UNIT_ABBREVIATION, str2);
        bundle.putString(GOAL_ID, str3);
        bundle.putFloat("current_value", f);
        userGoalDialogFragment.setArguments(bundle);
        return userGoalDialogFragment;
    }

    private void hydrateFieldsFrom(Bundle bundle) {
        if (bundle == null) {
            Ln.w("Arguments are null.", new Object[0]);
        }
        this.title = BundleUtils.getString(bundle, TITLE_RESOURCE_ID);
        this.unitAbbreviation = BundleUtils.getString(bundle, UNIT_ABBREVIATION);
        this.goalId = BundleUtils.getString(bundle, GOAL_ID);
        this.currentValue = BundleUtils.getFloat(bundle, "current_value");
    }

    public Dialog onCreateDialog(Bundle bundle) {
        hydrateFieldsFrom(getArguments());
        ContextThemeWrapper dialogContextThemeWrapper = getDialogContextThemeWrapper();
        View inflate = LayoutInflater.from(dialogContextThemeWrapper).inflate(R.layout.edit_text_dialog, null);
        this.value = (CustomLocalizedNumberEditText) ViewUtils.findById(inflate, R.id.txtValue);
        ((TextView) ViewUtils.findById(inflate, R.id.txtUnit)).setText(this.unitAbbreviation);
        this.value.setText(Strings.toString(Integer.valueOf((int) this.currentValue)));
        CustomLocalizedNumberEditText customLocalizedNumberEditText = this.value;
        customLocalizedNumberEditText.setSelection(customLocalizedNumberEditText.getText().length());
        final AlertDialog create = new MfpAlertDialogBuilder(dialogContextThemeWrapper).setTitle((CharSequence) this.title).setView(inflate).setPositiveButton((int) R.string.setBtn, (OnClickListener) null).setNegativeButton((int) R.string.cancel, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                UserGoalDialogFragment userGoalDialogFragment = UserGoalDialogFragment.this;
                userGoalDialogFragment.hideSoftInputFor(userGoalDialogFragment.value);
            }
        }).create();
        this.value.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 2) {
                    return false;
                }
                UserGoalDialogFragment.this.setValue(create);
                return true;
            }
        });
        return create;
    }

    public void onStart() {
        super.onStart();
        final AlertDialog alertDialog = (AlertDialog) getDialog();
        if (alertDialog != null) {
            alertDialog.getButton(-1).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    UserGoalDialogFragment.this.setValue(alertDialog);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void setValue(DialogInterface dialogInterface) {
        int tryParseInt = NumberUtils.tryParseInt(Strings.toString(this.value.getText()), -1);
        if (tryParseInt < 0) {
            this.value.setError(getString(R.string.enter_numeric_value));
            return;
        }
        this.messageBus.post(new GoalUpdatedEvent(this.goalId, tryParseInt));
        dialogInterface.dismiss();
    }
}
