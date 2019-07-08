package com.myfitnesspal.feature.nutrition.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View.BaseSavedState;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.NumberPicker.Formatter;
import android.widget.NumberPicker.OnValueChangeListener;
import com.myfitnesspal.android.R;
import java.text.DateFormatSymbols;
import java.util.Calendar;

public class CustomDatePicker extends FrameLayout {
    private static final char DATE = 'd';
    private static final int DEFAULT_END_YEAR = 2100;
    private static final int DEFAULT_START_YEAR = 1900;
    private static final char MONTH = 'M';
    private static final Formatter twoDigitFormatter = new Formatter() {
        public String format(int i) {
            return String.format("%02d", new Object[]{Integer.valueOf(i)});
        }
    };
    /* access modifiers changed from: private */
    public int mDay;
    private final NumberPicker mDayPicker;
    /* access modifiers changed from: private */
    public int mMonth;
    private final NumberPicker mMonthPicker;
    /* access modifiers changed from: private */
    public OnDateChangedListener mOnDateChangedListener;
    /* access modifiers changed from: private */
    public int mYear;
    private final NumberPicker mYearPicker;

    public interface OnDateChangedListener {
        void onDateChanged(CustomDatePicker customDatePicker, int i, int i2, int i3);
    }

    private static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        private final int mDay;
        private final int mMonth;
        private final int mYear;

        private SavedState(Parcelable parcelable, int i, int i2, int i3) {
            super(parcelable);
            this.mYear = i;
            this.mMonth = i2;
            this.mDay = i3;
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.mYear = parcel.readInt();
            this.mMonth = parcel.readInt();
            this.mDay = parcel.readInt();
        }

        public int getYear() {
            return this.mYear;
        }

        public int getMonth() {
            return this.mMonth;
        }

        public int getDay() {
            return this.mDay;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.mYear);
            parcel.writeInt(this.mMonth);
            parcel.writeInt(this.mDay);
        }
    }

    public CustomDatePicker(Context context) {
        this(context, null);
    }

    public CustomDatePicker(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CustomDatePicker(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.date_picker, this, true);
        this.mDayPicker = (NumberPicker) findViewById(R.id.day);
        this.mDayPicker.setFormatter(twoDigitFormatter);
        this.mDayPicker.setOnValueChangedListener(new OnValueChangeListener() {
            public void onValueChange(NumberPicker numberPicker, int i, int i2) {
                CustomDatePicker.this.mDay = i2;
                if (CustomDatePicker.this.mOnDateChangedListener != null) {
                    OnDateChangedListener access$100 = CustomDatePicker.this.mOnDateChangedListener;
                    CustomDatePicker customDatePicker = CustomDatePicker.this;
                    access$100.onDateChanged(customDatePicker, customDatePicker.mYear, CustomDatePicker.this.mMonth, CustomDatePicker.this.mDay);
                }
            }
        });
        this.mMonthPicker = (NumberPicker) findViewById(R.id.month);
        this.mMonthPicker.setFormatter(new Formatter() {
            public String format(int i) {
                return String.format("%02d", new Object[]{Integer.valueOf(i)});
            }
        });
        this.mMonthPicker.setDisplayedValues(new DateFormatSymbols().getShortMonths());
        this.mMonthPicker.setOnValueChangedListener(new OnValueChangeListener() {
            public void onValueChange(NumberPicker numberPicker, int i, int i2) {
                CustomDatePicker.this.mMonth = i2 - 1;
                if (CustomDatePicker.this.mOnDateChangedListener != null) {
                    OnDateChangedListener access$100 = CustomDatePicker.this.mOnDateChangedListener;
                    CustomDatePicker customDatePicker = CustomDatePicker.this;
                    access$100.onDateChanged(customDatePicker, customDatePicker.mYear, CustomDatePicker.this.mMonth, CustomDatePicker.this.mDay);
                }
                CustomDatePicker.this.updateDaySpinner();
            }
        });
        this.mYearPicker = (NumberPicker) findViewById(R.id.year);
        this.mYearPicker.setOnValueChangedListener(new OnValueChangeListener() {
            public void onValueChange(NumberPicker numberPicker, int i, int i2) {
                CustomDatePicker.this.mYear = i2;
                if (CustomDatePicker.this.mOnDateChangedListener != null) {
                    OnDateChangedListener access$100 = CustomDatePicker.this.mOnDateChangedListener;
                    CustomDatePicker customDatePicker = CustomDatePicker.this;
                    access$100.onDateChanged(customDatePicker, customDatePicker.mYear, CustomDatePicker.this.mMonth, CustomDatePicker.this.mDay);
                }
            }
        });
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CustomDatePicker);
        int i2 = obtainStyledAttributes.getInt(1, DEFAULT_START_YEAR);
        int i3 = obtainStyledAttributes.getInt(0, 2100);
        this.mYearPicker.setMinValue(i2);
        this.mYearPicker.setMaxValue(i3);
        obtainStyledAttributes.recycle();
        Calendar instance = Calendar.getInstance();
        init(instance.get(1), instance.get(2), instance.get(5), null);
        reorderPickers();
        if (!isEnabled()) {
            setEnabled(false);
        }
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.mDayPicker.setEnabled(z);
        this.mMonthPicker.setEnabled(z);
        this.mYearPicker.setEnabled(z);
    }

    private void reorderPickers() {
        char[] dateFormatOrder = DateFormat.getDateFormatOrder(getContext());
        if (dateFormatOrder[0] != 'M' || dateFormatOrder[1] != 'd') {
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.parent);
            linearLayout.removeAllViews();
            for (char c : dateFormatOrder) {
                if (c == 'd') {
                    linearLayout.addView(this.mDayPicker);
                } else if (c == 'M') {
                    linearLayout.addView(this.mMonthPicker);
                } else {
                    linearLayout.addView(this.mYearPicker);
                }
            }
        }
    }

    public void updateDate(int i, int i2, int i3) {
        this.mYear = i;
        this.mMonth = i2;
        this.mDay = i3;
        updateSpinners();
    }

    /* access modifiers changed from: protected */
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState(), this.mYear, this.mMonth, this.mDay);
        return savedState;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mYear = savedState.getYear();
        this.mMonth = savedState.getMonth();
        this.mDay = savedState.getDay();
    }

    public void init(int i, int i2, int i3, OnDateChangedListener onDateChangedListener) {
        this.mYear = i;
        this.mMonth = i2;
        this.mDay = i3;
        this.mOnDateChangedListener = onDateChangedListener;
        updateSpinners();
    }

    private void updateSpinners() {
        updateDaySpinner();
        this.mYearPicker.setValue(this.mYear);
        this.mMonthPicker.setValue(this.mMonth + 1);
    }

    /* access modifiers changed from: private */
    public void updateDaySpinner() {
        Calendar instance = Calendar.getInstance();
        instance.set(this.mYear, this.mMonth, this.mDay);
        int actualMaximum = instance.getActualMaximum(5);
        this.mYearPicker.setMinValue(1);
        this.mYearPicker.setMaxValue(actualMaximum);
        this.mDayPicker.setValue(this.mDay);
    }

    public int getYear() {
        return this.mYear;
    }

    public int getMonth() {
        return this.mMonth;
    }

    public int getDayOfMonth() {
        return this.mDay;
    }
}
