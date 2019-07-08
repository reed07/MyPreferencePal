package com.myfitnesspal.feature.progress.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.progress.event.MeasurementValueChangeEvent;
import com.myfitnesspal.feature.progress.event.MeasurementValueChangeEvent.State;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.service.userdata.UserHeightService;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.view.CustomLocalizedNumberEditText;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.myfitnesspal.shared.validation.Validator;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import javax.inject.Inject;
import javax.inject.Named;

public class MeasurementValueDialogFragment extends CustomLayoutBaseDialogFragment {
    private float currentValue;
    @Inject
    protected LocalizedStringsUtil localizedStringsUtil;
    private String measurementName;
    @Inject
    protected UserHeightService userHeightService;
    @Inject
    @Named("weightValidator")
    protected Validator validator;
    private CustomLocalizedNumberEditText valueField;

    public static MeasurementValueDialogFragment newInstance(String str, float f) {
        Bundle bundle = new Bundle();
        bundle.putString(Extras.MEASUREMENT_NAME, str);
        bundle.putFloat(Extras.CURRENT_VALUE, f);
        MeasurementValueDialogFragment measurementValueDialogFragment = new MeasurementValueDialogFragment();
        measurementValueDialogFragment.setArguments(bundle);
        return measurementValueDialogFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        hydrateFieldsFrom(getArguments());
        ContextThemeWrapper dialogContextThemeWrapper = getDialogContextThemeWrapper();
        View inflate = LayoutInflater.from(dialogContextThemeWrapper).inflate(R.layout.measurement_dialog, null);
        this.valueField = (CustomLocalizedNumberEditText) ViewUtils.findById(inflate, R.id.txtValue);
        String strings = Strings.toString(Float.valueOf(this.currentValue));
        this.valueField.setText(strings);
        this.valueField.setSelection(0, strings.length());
        final AlertDialog create = new MfpAlertDialogBuilder(dialogContextThemeWrapper).setPositiveButton((int) R.string.setBtn, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                MeasurementValueDialogFragment.this.doSave();
            }
        }).setNegativeButton((int) R.string.cancel, (OnClickListener) null).setView(inflate).setTitle((CharSequence) getDialogTitle()).create();
        this.valueField.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    create.getWindow().setSoftInputMode(5);
                }
            }
        });
        this.valueField.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 2) {
                    return false;
                }
                MeasurementValueDialogFragment.this.doSave();
                MeasurementValueDialogFragment.this.hideSoftInputFor(textView);
                create.dismiss();
                return true;
            }
        });
        return create;
    }

    private void hydrateFieldsFrom(Bundle bundle) {
        this.measurementName = BundleUtils.getString(bundle, Extras.MEASUREMENT_NAME);
        this.currentValue = BundleUtils.getFloat(bundle, Extras.CURRENT_VALUE);
    }

    /* access modifiers changed from: protected */
    public String getDialogTitle() {
        return getDialogContextThemeWrapper().getString(R.string.todays, new Object[]{this.localizedStringsUtil.getLocalizedMeasurementName(this.measurementName)});
    }

    /* access modifiers changed from: private */
    public void doSave() {
        String strings = Strings.toString(this.valueField.getText());
        Ln.d("MEASURE: %s", strings);
        if (!this.validator.validate(strings)) {
            this.messageBus.post(new MeasurementValueChangeEvent(State.Invalid, this.measurementName, Float.MAX_VALUE));
            return;
        }
        float localeFloatFromString = NumberUtils.localeFloatFromString(strings);
        if (NumberUtils.isEffectivelyZero((double) localeFloatFromString)) {
            this.messageBus.post(new MeasurementValueChangeEvent(State.Zero, this.measurementName, BitmapDescriptorFactory.HUE_RED));
        } else if (localeFloatFromString < -99999.0f || localeFloatFromString > 99999.0f) {
            this.messageBus.post(new MeasurementValueChangeEvent(State.OutOfRange, this.measurementName, localeFloatFromString));
        } else {
            this.messageBus.post(new MeasurementValueChangeEvent(State.Valid, this.measurementName, localeFloatFromString));
        }
    }
}
