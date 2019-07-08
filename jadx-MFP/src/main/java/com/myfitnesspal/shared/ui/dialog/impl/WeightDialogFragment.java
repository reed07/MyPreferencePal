package com.myfitnesspal.shared.ui.dialog.impl;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.event.DialogWeightEvent;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.service.userdata.UserWeightService.WeightType;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.view.CustomLocalizedNumberEditText;
import com.myfitnesspal.shared.util.AnalyticsUtil;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.UnitsUtils;
import com.myfitnesspal.shared.util.UnitsUtils.Weight;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Ln;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import javax.inject.Inject;

public class WeightDialogFragment extends CustomLayoutBaseDialogFragment {
    /* access modifiers changed from: private */
    public static final DateFormat DATE_FORMAT = DateFormat.getDateInstance();
    @Inject
    Lazy<AnalyticsService> analyticsService;
    final Calendar calendar = Calendar.getInstance();
    private String currentStartingWeightDate;
    private float currentWeight;
    /* access modifiers changed from: private */
    public CustomLocalizedNumberEditText edValue;
    /* access modifiers changed from: private */
    public EditText etSWDate;
    private boolean isStartingWeight;
    private CustomLocalizedNumberEditText lbsPart;
    private Function0 swDateEditTextOnTouchAction;
    @Inject
    Lazy<UserWeightService> userWeightService;
    private String[] weightBasedOnType;
    private WeightType weightType;

    public static WeightDialogFragment newInstance(WeightType weightType2, float f) {
        return newInstance(weightType2, f, false, "");
    }

    public static WeightDialogFragment newInstance(WeightType weightType2, float f, boolean z, String str) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Extras.WEIGHT_TYPE, weightType2);
        bundle.putFloat(Extras.CURRENT_WEIGHT, f);
        bundle.putBoolean(Extras.IS_STARTING_WEIGHT, z);
        bundle.putString(Extras.CURRENT_SW_DATE, str);
        WeightDialogFragment weightDialogFragment = new WeightDialogFragment();
        weightDialogFragment.setArguments(bundle);
        return weightDialogFragment;
    }

    private void hydrateFieldsFrom(Bundle bundle) {
        this.weightType = (WeightType) bundle.getSerializable(Extras.WEIGHT_TYPE);
        this.currentWeight = BundleUtils.getFloat(bundle, Extras.CURRENT_WEIGHT);
        this.isStartingWeight = BundleUtils.getBoolean(bundle, Extras.IS_STARTING_WEIGHT);
        this.currentStartingWeightDate = BundleUtils.getString(bundle, Extras.CURRENT_SW_DATE);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        hydrateFieldsFrom(getArguments());
        this.messageBus.register(this);
        View inflate = LayoutInflater.from(getDialogContextThemeWrapper()).inflate(R.layout.edit_text_dialog, null);
        this.etSWDate = (EditText) inflate.findViewById(R.id.etSWDate);
        this.edValue = (CustomLocalizedNumberEditText) inflate.findViewById(R.id.txtValue);
        TextView textView = (TextView) inflate.findViewById(R.id.txtUnit);
        this.lbsPart = (CustomLocalizedNumberEditText) inflate.findViewById(R.id.weightLbs);
        TextView textView2 = (TextView) inflate.findViewById(R.id.lbs);
        Weight userCurrentWeightUnit = ((UserWeightService) this.userWeightService.get()).getUserCurrentWeightUnit();
        this.weightBasedOnType = ((UserWeightService) this.userWeightService.get()).getWeight(userCurrentWeightUnit, this.weightType, this.currentWeight);
        this.edValue.setText(this.weightBasedOnType[0]);
        textView.setText(((UserWeightService) this.userWeightService.get()).getDisplayableUnitString(this.weightBasedOnType[0], this.weightType));
        if (userCurrentWeightUnit == Weight.STONES_POUNDS) {
            this.lbsPart.setVisibility(0);
            textView2.setVisibility(this.lbsPart.getVisibility());
            String str = this.weightBasedOnType[1];
            this.lbsPart.setText(str);
            textView2.setText(((UserWeightService) this.userWeightService.get()).getLbsString(NumberUtils.localeFloatFromString(str), this.weightType));
        } else {
            this.lbsPart.setVisibility(8);
            textView2.setVisibility(this.lbsPart.getVisibility());
        }
        ViewUtils.setVisible(this.isStartingWeight, this.etSWDate);
        if (this.isStartingWeight) {
            if (Strings.notEmpty(this.currentStartingWeightDate)) {
                this.etSWDate.setText(DATE_FORMAT.format(DateTimeUtils.parse("yyyy-MM-dd", this.currentStartingWeightDate)));
            }
            final AnonymousClass1 r0 = new OnDateSetListener() {
                public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                    WeightDialogFragment.this.calendar.set(1, i);
                    WeightDialogFragment.this.calendar.set(2, i2);
                    WeightDialogFragment.this.calendar.set(5, i3);
                    WeightDialogFragment.this.etSWDate.setText(WeightDialogFragment.DATE_FORMAT.format(WeightDialogFragment.this.calendar.getTime()));
                }
            };
            this.swDateEditTextOnTouchAction = new Function0() {
                public void execute() throws RuntimeException {
                    WeightDialogFragment.this.showDatePickerForStartingWeight(r0);
                }
            };
            this.etSWDate.setOnTouchListener(ViewUtils.getOnTouchListenerToExecuteClick(this.swDateEditTextOnTouchAction));
        }
        this.edValue.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    WeightDialogFragment.this.showSoftInput();
                    WeightDialogFragment.this.edValue.setSelection(WeightDialogFragment.this.edValue.getText().length());
                    return;
                }
                WeightDialogFragment weightDialogFragment = WeightDialogFragment.this;
                weightDialogFragment.hideSoftInputFor(weightDialogFragment.edValue);
            }
        });
        int i = R.string.todays_weight;
        switch (this.weightType) {
            case CURRENT:
                i = R.string.update_current_weight;
                break;
            case GOAL:
                i = R.string.update_goal_weight;
                break;
            case STARTING:
                i = R.string.update_starting_weight;
                break;
        }
        final AlertDialog create = new MfpAlertDialogBuilder(getDialogContextThemeWrapper()).setTitle(i).setView(inflate).setPositiveButton((int) R.string.setBtn, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                WeightDialogFragment.this.doSave(dialogInterface);
            }
        }).setNegativeButton((int) R.string.cancel, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                WeightDialogFragment weightDialogFragment = WeightDialogFragment.this;
                weightDialogFragment.hideSoftInputFor(weightDialogFragment.edValue);
            }
        }).create();
        AnonymousClass6 r02 = new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 2) {
                    return false;
                }
                WeightDialogFragment.this.doSave(create);
                return true;
            }
        };
        this.edValue.setOnEditorActionListener(r02);
        this.lbsPart.setOnEditorActionListener(r02);
        create.getWindow().setSoftInputMode(4);
        return create;
    }

    /* access modifiers changed from: private */
    public void showDatePickerForStartingWeight(OnDateSetListener onDateSetListener) {
        if (Strings.notEmpty(this.currentStartingWeightDate)) {
            this.calendar.setTime(DateTimeUtils.parse("yyyy-MM-dd", this.currentStartingWeightDate));
        }
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), onDateSetListener, this.calendar.get(1), this.calendar.get(2), this.calendar.get(5));
        Calendar instance = Calendar.getInstance();
        instance.set(10, 23);
        instance.set(12, 59);
        datePickerDialog.getDatePicker().setMaxDate(instance.getTimeInMillis());
        datePickerDialog.getDatePicker().setMinDate(0);
        datePickerDialog.show();
    }

    public void onPause() {
        this.messageBus.unregister(this);
        hideSoftInputFor(this.edValue);
        super.onPause();
    }

    /* access modifiers changed from: private */
    public void doSave(DialogInterface dialogInterface) {
        ContextThemeWrapper dialogContextThemeWrapper = getDialogContextThemeWrapper();
        try {
            boolean z = this.weightType == WeightType.STARTING;
            double localeFloatFromString = (double) NumberUtils.localeFloatFromString(this.edValue.getText().toString());
            Weight userCurrentWeightUnit = ((UserWeightService) this.userWeightService.get()).getUserCurrentWeightUnit();
            if (userCurrentWeightUnit != Weight.POUNDS) {
                if (userCurrentWeightUnit == Weight.KILOGRAMS) {
                    localeFloatFromString = UnitsUtils.getPoundsFromKilograms(localeFloatFromString);
                } else if (userCurrentWeightUnit == Weight.STONES_POUNDS) {
                    localeFloatFromString = (localeFloatFromString * 14.0d) + Double.valueOf(this.lbsPart.getText().toString()).doubleValue();
                } else {
                    throw new IllegalStateException("invalid weight unit");
                }
            }
            String str = "";
            if (z) {
                try {
                    Date parse = DATE_FORMAT.parse(this.etSWDate.getText().toString());
                    String formatDb = DateTimeUtils.formatDb(parse);
                    reportStartingWeightSaveEvent(parse, (float) localeFloatFromString);
                    str = formatDb;
                } catch (ParseException unused) {
                    alert(dialogContextThemeWrapper.getResources().getString(getErrorString(this.weightType)), dialogContextThemeWrapper);
                    return;
                }
            }
            if (localeFloatFromString > 0.0d) {
                this.messageBus.post(z ? new DialogWeightEvent((float) localeFloatFromString, this.weightType, str) : new DialogWeightEvent((float) localeFloatFromString, this.weightType));
                hideSoftInputFor(this.edValue);
                dialogInterface.cancel();
                return;
            }
            hideSoftInputFor(this.edValue);
            alert(dialogContextThemeWrapper.getResources().getString(getErrorString(this.weightType)), dialogContextThemeWrapper);
        } catch (NumberFormatException e) {
            alert(dialogContextThemeWrapper.getResources().getString(R.string.enterNumber), dialogContextThemeWrapper);
            Ln.e(e.getMessage(), new Object[0]);
        }
    }

    private void reportStartingWeightSaveEvent(Date date, float f) {
        if (this.isStartingWeight) {
            long numberOfDaysBetween = DateTimeUtils.getNumberOfDaysBetween(date, DateTimeUtils.parse("yyyy-MM-dd", this.currentStartingWeightDate));
            float validateConvertWeight = ((UserWeightService) this.userWeightService.get()).validateConvertWeight(new String[]{Strings.toString(this.weightBasedOnType[0], "0"), Strings.toString(this.weightBasedOnType[1], "0")}) - f;
            ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.STARTING_WEIGHT_PICKER_SAVE, MapUtil.createMap(Attributes.DATE_CHANGED, AnalyticsUtil.getAnalyticsAttributeValueForBoolean(numberOfDaysBetween != 0), "weight_changed", AnalyticsUtil.getAnalyticsAttributeValueForBoolean(validateConvertWeight != BitmapDescriptorFactory.HUE_RED), Attributes.DAY_DIFF, Strings.toString(Long.valueOf(numberOfDaysBetween)), Attributes.STARTING_WEIGHT_DIFFERENCE, Strings.toString(Float.valueOf(validateConvertWeight))));
        }
    }

    private int getErrorString(WeightType weightType2) {
        if (weightType2 == WeightType.CURRENT) {
            return R.string.enter_current_weight;
        }
        return weightType2 == WeightType.STARTING ? R.string.enter_starting_weight : R.string.enter_goal_weight;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        if (this.isStartingWeight) {
            ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.STARTING_WEIGHT_DIALOG_DISMISS);
        }
    }

    private void alert(CharSequence charSequence, Context context) {
        new MfpAlertDialogBuilder(context).setMessage(charSequence).setCancelable(true).setPositiveButton(context.getResources().getText(R.string.dismiss), (OnClickListener) $$Lambda$WeightDialogFragment$zCPuBmNWl8wb3V0dYpC9hHl_XRA.INSTANCE).setTitle(context.getResources().getText(R.string.alert)).show();
    }
}
