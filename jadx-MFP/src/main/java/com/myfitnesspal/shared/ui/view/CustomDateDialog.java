package com.myfitnesspal.shared.ui.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.nutrition.ui.view.CustomDatePicker;
import com.myfitnesspal.feature.nutrition.ui.view.CustomDatePicker.OnDateChangedListener;
import com.myfitnesspal.shared.util.DateTimeUtils;
import java.util.Calendar;

public class CustomDateDialog extends AlertDialog implements OnClickListener, OnDateChangedListener {
    private static final String DAY = "day";
    private static final String MONTH = "month";
    private static final String YEAR = "year";
    private Calendar mCalendar;
    private OnDateSetListener mCallBack;
    private CustomDatePicker mDatePicker;
    private int mInitialDay;
    private int mInitialMonth;
    private int mInitialYear;

    public interface OnDateSetListener {
        void onDateSet(CustomDatePicker customDatePicker, int i, int i2, int i3);
    }

    public CustomDateDialog(Context context, OnDateSetListener onDateSetListener, int i, int i2, int i3) {
        super(context);
        init(context, onDateSetListener, i, i2, i3);
    }

    public CustomDateDialog(Context context, int i, OnDateSetListener onDateSetListener, int i2, int i3, int i4) {
        super(context, i);
        init(context, onDateSetListener, i2, i3, i4);
    }

    public void init(Context context, OnDateSetListener onDateSetListener, int i, int i2, int i3) {
        this.mCallBack = onDateSetListener;
        this.mCalendar = Calendar.getInstance();
        this.mInitialYear = i;
        this.mInitialMonth = i2;
        this.mInitialDay = i3;
        updateTitle(this.mInitialYear, this.mInitialMonth, this.mInitialDay);
        setButton(context.getResources().getString(R.string.set), this);
        setButton2(context.getText(17039360), null);
        setIcon(R.drawable.ic_dialog_time);
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.custom_date_picker_dialog, null);
        setView(inflate);
        this.mDatePicker = (CustomDatePicker) inflate.findViewById(R.id.datePicker);
        this.mDatePicker.init(this.mInitialYear, this.mInitialMonth, this.mInitialDay, this);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (this.mCallBack != null) {
            this.mDatePicker.clearFocus();
            OnDateSetListener onDateSetListener = this.mCallBack;
            CustomDatePicker customDatePicker = this.mDatePicker;
            onDateSetListener.onDateSet(customDatePicker, customDatePicker.getYear(), this.mDatePicker.getMonth(), this.mDatePicker.getDayOfMonth());
        }
    }

    public void onDateChanged(CustomDatePicker customDatePicker, int i, int i2, int i3) {
        updateTitle(i, i2, i3);
    }

    public void updateDate(int i, int i2, int i3) {
        this.mInitialYear = i;
        this.mInitialMonth = i2;
        this.mInitialDay = i3;
        this.mDatePicker.updateDate(i, i2, i3);
    }

    private void updateTitle(int i, int i2, int i3) {
        this.mCalendar.set(1, i);
        this.mCalendar.set(2, i2);
        this.mCalendar.set(5, i3);
        setTitle(DateTimeUtils.getMediumLocaleFormattedDate(getContext(), this.mCalendar.getTime()));
    }

    public Bundle onSaveInstanceState() {
        Bundle onSaveInstanceState = super.onSaveInstanceState();
        onSaveInstanceState.putInt("year", this.mDatePicker.getYear());
        onSaveInstanceState.putInt("month", this.mDatePicker.getMonth());
        onSaveInstanceState.putInt(DAY, this.mDatePicker.getDayOfMonth());
        return onSaveInstanceState;
    }

    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        int i = bundle.getInt("year");
        int i2 = bundle.getInt("month");
        int i3 = bundle.getInt(DAY);
        this.mDatePicker.init(i, i2, i3, this);
        updateTitle(i, i2, i3);
    }
}
