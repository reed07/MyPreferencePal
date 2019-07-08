package com.myfitnesspal.feature.progress.ui.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.TextView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.event.DialogWeightEvent;
import com.myfitnesspal.shared.model.unitconv.LocalizedWeight;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.service.userdata.UserWeightService.WeightType;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.util.UnitsUtils;
import com.myfitnesspal.shared.util.UnitsUtils.Weight;
import com.squareup.otto.Bus;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.inject.Inject;

public class LegacyWeightPickerFragment extends CustomLayoutBaseDialogFragment {
    private static final double MAX_GOAL_WEIGHT = 1000.0d;
    private static final double MIN_GOAL_WEIGHT = 30.0d;
    @Inject
    Lazy<AnalyticsService> analyticsService;
    @Inject
    Lazy<ConfigService> configService;
    private boolean didReportUnderweightWarning;
    private ArrayList<String> displayStrings;
    private boolean isFromSettings;
    /* access modifiers changed from: private */
    public NumberPicker lbNumberPicker;
    private double maxSuggestedGoal;
    @Inject
    Lazy<Bus> messageBus;
    /* access modifiers changed from: private */
    public double minSuggestedGoal;
    /* access modifiers changed from: private */
    public TextView underweightWarning;
    @Inject
    Lazy<UserWeightService> userWeightService;
    /* access modifiers changed from: private */
    public NumberPicker weightNumberPicker;
    OnValueChangeListener weightPickerChangeListener;
    /* access modifiers changed from: private */
    public Weight weightUnit;

    public static LegacyWeightPickerFragment newInstance(WeightType weightType, double d, double d2, double d3, boolean z) {
        LegacyWeightPickerFragment legacyWeightPickerFragment = new LegacyWeightPickerFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Extras.WEIGHT_TYPE, weightType);
        bundle.putDouble(Extras.CURRENT_WEIGHT, d);
        bundle.putDouble(Extras.MIN_WEIGHT, d2);
        bundle.putDouble(Extras.MAX_WEIGHT, d3);
        bundle.putBoolean(Extras.IS_FROM_SETTINGS, z);
        legacyWeightPickerFragment.setArguments(bundle);
        return legacyWeightPickerFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        ((Bus) this.messageBus.get()).register(this);
        FragmentActivity activity = getActivity();
        final View inflate = LayoutInflater.from(activity).inflate(R.layout.weight_picker, null);
        this.weightNumberPicker = (NumberPicker) inflate.findViewById(R.id.weight_picker);
        this.lbNumberPicker = (NumberPicker) inflate.findViewById(R.id.lb_picker);
        this.underweightWarning = (TextView) ViewUtils.findById(inflate, R.id.underweight_recommendation);
        TextView textView = (TextView) inflate.findViewById(R.id.weight_unit);
        TextView textView2 = (TextView) inflate.findViewById(R.id.lb_unit);
        initializeWeightPickerListener();
        this.weightNumberPicker.setOnValueChangedListener(this.weightPickerChangeListener);
        this.lbNumberPicker.setOnValueChangedListener(this.weightPickerChangeListener);
        this.weightUnit = ((UserWeightService) this.userWeightService.get()).getUserCurrentWeightUnit();
        Bundle arguments = getArguments();
        double d = arguments.getDouble(Extras.CURRENT_WEIGHT);
        this.isFromSettings = arguments.getBoolean(Extras.IS_FROM_SETTINGS);
        this.minSuggestedGoal = (double) ((int) arguments.getDouble(Extras.MIN_WEIGHT));
        this.maxSuggestedGoal = (double) ((int) arguments.getDouble(Extras.MAX_WEIGHT));
        if (d < this.minSuggestedGoal) {
            showUnderweightWarning();
        }
        if (this.weightUnit == Weight.POUNDS) {
            int i = (int) MAX_GOAL_WEIGHT;
            int i2 = (int) d;
            this.weightNumberPicker.setMinValue((int) MIN_GOAL_WEIGHT);
            this.weightNumberPicker.setMaxValue(i);
            this.weightNumberPicker.setValue(i2);
            textView.setText(R.string.lb);
        } else if (this.weightUnit == Weight.KILOGRAMS) {
            double kilograms = LocalizedWeight.fromPounds(MIN_GOAL_WEIGHT).toKilograms();
            double kilograms2 = LocalizedWeight.fromPounds(MAX_GOAL_WEIGHT).toKilograms();
            double kilograms3 = LocalizedWeight.fromPounds(d).toKilograms();
            this.displayStrings = new ArrayList<>();
            int i3 = (int) ((kilograms2 - kilograms) / 0.10000000149011612d);
            double d2 = kilograms;
            double d3 = -1.0d;
            int i4 = 0;
            int i5 = 0;
            while (i4 < i3) {
                this.displayStrings.add(NumberUtils.localeStringFromDouble(d2, 1));
                double abs = Math.abs(d2 - kilograms3);
                if (d3 == -1.0d || abs < d3) {
                    i5 = i4;
                    d3 = abs;
                }
                i4++;
                d2 += 0.10000000149011612d;
            }
            this.weightNumberPicker.setMinValue(0);
            this.weightNumberPicker.setMaxValue(i3 - 1);
            this.weightNumberPicker.setValue(i5);
            NumberPicker numberPicker = this.weightNumberPicker;
            ArrayList<String> arrayList = this.displayStrings;
            numberPicker.setDisplayedValues((String[]) arrayList.toArray(new String[arrayList.size()]));
            textView.setText(R.string.kg);
        } else if (this.weightUnit == Weight.STONES_POUNDS) {
            ViewUtils.setVisible(this.lbNumberPicker);
            ViewUtils.setVisible(textView2);
            String[] stonesPoundsFromPounds = UnitsUtils.getStonesPoundsFromPounds(MIN_GOAL_WEIGHT);
            String[] stonesPoundsFromPounds2 = UnitsUtils.getStonesPoundsFromPounds(MAX_GOAL_WEIGHT);
            String[] stonesPoundsFromPounds3 = UnitsUtils.getStonesPoundsFromPounds(d);
            int tryParseInt = NumberUtils.tryParseInt(stonesPoundsFromPounds[0]);
            int tryParseInt2 = NumberUtils.tryParseInt(stonesPoundsFromPounds2[0]);
            int tryParseInt3 = NumberUtils.tryParseInt(stonesPoundsFromPounds3[0]);
            int intValue = Float.valueOf(NumberUtils.localeFloatFromString(stonesPoundsFromPounds3[1])).intValue();
            this.weightNumberPicker.setMinValue(tryParseInt);
            this.weightNumberPicker.setMaxValue(tryParseInt2);
            this.weightNumberPicker.setValue(tryParseInt3);
            this.lbNumberPicker.setMinValue(0);
            this.lbNumberPicker.setMaxValue(13);
            this.lbNumberPicker.setValue(intValue);
            textView.setText(R.string.st);
            textView2.setText(R.string.lb);
        }
        ViewUtils.setPickerCommonProperties(this.weightNumberPicker);
        ViewUtils.setPickerCommonProperties(this.lbNumberPicker);
        return new MfpAlertDialogBuilder(activity).setTitle((int) R.string.update_goal_weight).setView(inflate).setPositiveButton((int) R.string.setBtn, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                LegacyWeightPickerFragment.this.clearFocusAndHideSoftInput(inflate);
                LegacyWeightPickerFragment.this.doSave();
            }
        }).setNegativeButton((int) R.string.cancel, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                LegacyWeightPickerFragment.this.clearFocusAndHideSoftInput(inflate);
                LegacyWeightPickerFragment.this.dismiss();
            }
        }).create();
    }

    private void initializeWeightPickerListener() {
        this.weightPickerChangeListener = new OnValueChangeListener() {
            public void onValueChange(NumberPicker numberPicker, int i, int i2) {
                double d = LegacyWeightPickerFragment.this.weightUnit == Weight.POUNDS ? (double) i2 : LegacyWeightPickerFragment.this.weightUnit == Weight.KILOGRAMS ? UnitsUtils.getPoundsFromKilograms((double) NumberUtils.tryParseFloat(numberPicker.getDisplayedValues()[i2])) : LegacyWeightPickerFragment.this.weightUnit == Weight.STONES_POUNDS ? NumberUtils.tryParseDouble(UnitsUtils.getPoundsFromStonesPounds((double) LegacyWeightPickerFragment.this.weightNumberPicker.getValue(), (double) LegacyWeightPickerFragment.this.lbNumberPicker.getValue())) : 0.0d;
                if (d < LegacyWeightPickerFragment.this.minSuggestedGoal) {
                    LegacyWeightPickerFragment.this.showUnderweightWarning();
                } else {
                    ViewUtils.setVisible(false, LegacyWeightPickerFragment.this.underweightWarning);
                }
            }
        };
    }

    /* access modifiers changed from: private */
    public void showUnderweightWarning() {
        String str;
        DecimalFormat decimalFormat = new DecimalFormat("#");
        String str2 = "";
        if (this.weightNumberPicker.getDisplayedValues() != null) {
            str2 = this.weightNumberPicker.getDisplayedValues()[this.weightNumberPicker.getValue()];
        }
        String str3 = "";
        String str4 = "";
        if (this.weightUnit == Weight.POUNDS) {
            str3 = decimalFormat.format(this.minSuggestedGoal);
            if (this.isFromSettings) {
                str4 = getString(R.string.a_normal_weight_range_pounds, str3, decimalFormat.format(this.maxSuggestedGoal), str3);
            } else {
                str4 = getString(R.string.please_select_goal_weight_over_pounds, str3);
            }
        } else if (this.weightUnit == Weight.KILOGRAMS) {
            DecimalFormat decimalFormat2 = new DecimalFormat("#.#");
            str3 = decimalFormat2.format(UnitsUtils.getKilogramsFromPounds(this.minSuggestedGoal));
            if (this.isFromSettings) {
                str4 = getString(R.string.a_normal_weight_range_kilos, str3, decimalFormat2.format(UnitsUtils.getKilogramsFromPounds(this.maxSuggestedGoal)), str3);
            } else {
                str4 = getString(R.string.please_select_goal_weight_over_kilos, str3);
            }
        } else if (this.weightUnit == Weight.STONES_POUNDS) {
            if (!(this.weightNumberPicker.getDisplayedValues() == null || this.lbNumberPicker.getDisplayedValues() == null)) {
                StringBuilder sb = new StringBuilder();
                sb.append(this.weightNumberPicker.getDisplayedValues()[this.weightNumberPicker.getValue()]);
                sb.append(".");
                sb.append(this.lbNumberPicker.getDisplayedValues()[this.lbNumberPicker.getValue()]);
                str2 = sb.toString();
            }
            String[] stonesPoundsFromPounds = UnitsUtils.getStonesPoundsFromPounds(this.minSuggestedGoal);
            String[] stonesPoundsFromPounds2 = UnitsUtils.getStonesPoundsFromPounds(this.maxSuggestedGoal);
            StringBuilder sb2 = new StringBuilder();
            sb2.append(stonesPoundsFromPounds[0]);
            sb2.append(".");
            sb2.append(stonesPoundsFromPounds[1]);
            String sb3 = sb2.toString();
            if (this.isFromSettings) {
                str = getString(R.string.a_normal_weight_range_stones, stonesPoundsFromPounds[0], stonesPoundsFromPounds[1], stonesPoundsFromPounds2[0], stonesPoundsFromPounds2[1], stonesPoundsFromPounds[0], stonesPoundsFromPounds[1]);
            } else {
                str = getString(R.string.please_select_goal_weight_over_stones, stonesPoundsFromPounds[0], stonesPoundsFromPounds[1]);
            }
            str3 = sb3;
            str4 = str;
        }
        boolean checkStringsForLessThanMin = checkStringsForLessThanMin(str2, str3);
        this.underweightWarning.setText(str4);
        ViewUtils.setVisible(checkStringsForLessThanMin, this.underweightWarning);
        if (!this.didReportUnderweightWarning && checkStringsForLessThanMin) {
            ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.ED_GOALS_SCREEN_SHOW_LOW_GOAL_WEIGHT_WARNING);
            this.didReportUnderweightWarning = true;
        }
    }

    private boolean checkStringsForLessThanMin(String str, String str2) {
        return !Strings.notEmpty(str) || NumberUtils.tryParseDouble(str) <= NumberUtils.tryParseDouble(str2);
    }

    /* access modifiers changed from: private */
    public void clearFocusAndHideSoftInput(View view) {
        this.weightNumberPicker.clearFocus();
        hideSoftInputFor(view);
    }

    public void onPause() {
        ((Bus) this.messageBus.get()).unregister(this);
        super.onPause();
    }

    /* access modifiers changed from: private */
    public void doSave() {
        int value = this.weightNumberPicker.getValue();
        float f = this.weightUnit == Weight.POUNDS ? (float) value : this.weightUnit == Weight.KILOGRAMS ? (float) UnitsUtils.getPoundsFromKilograms((double) NumberUtils.localeFloatFromString((String) this.displayStrings.get(value))) : this.weightUnit == Weight.STONES_POUNDS ? (float) ((value * 14) + this.lbNumberPicker.getValue()) : BitmapDescriptorFactory.HUE_RED;
        if (ViewUtils.isVisible(this.underweightWarning)) {
            ((AnalyticsService) this.analyticsService.get()).reportEvent(this.isFromSettings ? Events.ED_GOALS_SCREEN_SET_UNDERWEIGHT_GOAL_WEIGHT : Events.ED_GOAL_WEIGHT_SET_UNDERWEIGHT_BMI);
        } else {
            ((AnalyticsService) this.analyticsService.get()).reportEvent(this.isFromSettings ? Events.ED_GOALS_SCREEN_SET_NORMAL_GOAL_WEIGHT : Events.ED_GOAL_WEIGHT_SET_NORMAL_BMI);
        }
        ((Bus) this.messageBus.get()).post(new DialogWeightEvent(f, (WeightType) getArguments().getSerializable(Extras.WEIGHT_TYPE)));
    }

    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }
}
