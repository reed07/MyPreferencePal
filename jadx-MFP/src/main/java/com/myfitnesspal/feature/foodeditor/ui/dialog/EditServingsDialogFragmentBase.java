package com.myfitnesspal.feature.foodeditor.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.appevents.AppEventsConstants;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.model.v2.MfpServingSize;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import java.text.ParseException;
import java.util.List;

public abstract class EditServingsDialogFragmentBase extends CustomLayoutBaseDialogFragment {
    protected static final String CURRENT_SERVINGS = "current_servings";
    protected static final String SHOW_KEYBOARD = "show_keyboard";
    @BindView(2131362838)
    protected View invalidServings;
    @BindView(2131363187)
    protected EditText noServingsEditText;
    @BindView(2131363606)
    protected Spinner servingSizeSpinner;
    @BindView(2131364037)
    protected TextView servingsOfTextView;

    /* access modifiers changed from: protected */
    public abstract MfpServingSize getSelectedServingSize();

    /* access modifiers changed from: protected */
    public abstract List<MfpServingSize> getServingSizes();

    /* access modifiers changed from: protected */
    public void onNegativeButtonClick() {
    }

    /* access modifiers changed from: protected */
    public abstract boolean onPositiveButtonClick();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        View inflate = LayoutInflater.from(getDialogContextThemeWrapper()).inflate(R.layout.edit_food_entry_serving, null);
        ButterKnife.bind((Object) this, inflate);
        ViewUtils.setGone(this.invalidServings);
        setupServingSizeSpinner();
        setupNoServingsEditText();
        AlertDialog create = new MfpAlertDialogBuilder(getDialogContextThemeWrapper()).setPositiveButton((int) R.string.save, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).setNegativeButton((int) R.string.cancel, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                EditServingsDialogFragmentBase.this.dismissDialogAndKeyboard();
                EditServingsDialogFragmentBase.this.onNegativeButtonClick();
            }
        }).setTitle((int) R.string.how_much).setView(inflate).setCancelable(true).setCanceledOnTouchOutside(true).create();
        this.noServingsEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    EditServingsDialogFragmentBase.this.noServingsEditText.setSelection(0, EditServingsDialogFragmentBase.this.noServingsEditText.getText().length());
                }
            }
        });
        if (BundleUtils.getBoolean(getArguments(), SHOW_KEYBOARD)) {
            create.getWindow().setSoftInputMode(5);
        }
        return create;
    }

    public void onStart() {
        super.onStart();
        final AlertDialog alertDialog = (AlertDialog) getDialog();
        if (alertDialog != null) {
            alertDialog.getButton(-1).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (EditServingsDialogFragmentBase.this.onPositiveButtonClick()) {
                        alertDialog.dismiss();
                    }
                }
            });
        }
    }

    private void setupNoServingsEditText() {
        this.noServingsEditText.setText(NumberUtils.localeStringFromFloat(getCurrentServings()));
    }

    private void setupServingSizeSpinner() {
        List servingSizes = getServingSizes();
        MfpServingSize selectedServingSize = getSelectedServingSize();
        if (servingSizes == null || selectedServingSize == null) {
            ViewUtils.setGone(this.servingSizeSpinner);
            this.servingsOfTextView.setText(R.string.serving_s);
            return;
        }
        String[] strArr = new String[servingSizes.size()];
        int i = 0;
        for (int i2 = 0; i2 < servingSizes.size(); i2++) {
            MfpServingSize mfpServingSize = (MfpServingSize) servingSizes.get(i2);
            strArr[i2] = mfpServingSize.descriptionWithAmount();
            if (Strings.equalsIgnoreCase(mfpServingSize.descriptionWithAmount(), selectedServingSize.descriptionWithAmount())) {
                this.servingSizeSpinner.setSelection(i2);
                i = i2;
            }
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(getDialogContextThemeWrapper(), R.layout.alert_dialog_spinner_item, strArr);
        arrayAdapter.setDropDownViewResource(R.layout.alert_dialog_spinner_dropdown_item);
        this.servingSizeSpinner.setAdapter(arrayAdapter);
        if (i != -1) {
            this.servingSizeSpinner.setSelection(i);
        }
    }

    private float getCurrentServings() {
        return BundleUtils.getFloat(getArguments(), CURRENT_SERVINGS);
    }

    /* access modifiers changed from: protected */
    public void dismissDialogAndKeyboard() {
        this.noServingsEditText.clearFocus();
        dismiss();
        hideSoftInputFor(this.noServingsEditText);
    }

    /* access modifiers changed from: protected */
    public int getSelectedSpinnerItemPosition() {
        return this.servingSizeSpinner.getSelectedItemPosition();
    }

    /* access modifiers changed from: protected */
    public float getNumServings() throws ParseException {
        String trimmed = Strings.trimmed((Object) this.noServingsEditText.getText());
        if (Strings.isEmpty(trimmed)) {
            trimmed = AppEventsConstants.EVENT_PARAM_VALUE_YES;
        }
        return NumberUtils.localeFloatFromString(NumberUtils.normalizeInputString(trimmed));
    }

    /* access modifiers changed from: protected */
    public void showInvalidServingView() {
        ViewUtils.setVisible(this.invalidServings);
    }
}
