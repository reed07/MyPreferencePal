package com.myfitnesspal.shared.ui.fragment.impl;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.DatePicker;
import com.myfitnesspal.feature.nutrition.ui.view.CustomDatePicker;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.event.DialogCalendarEvent;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.view.CustomDateDialog;
import com.uacf.core.util.BundleUtils;
import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends CustomLayoutBaseDialogFragment implements OnDateSetListener, CustomDateDialog.OnDateSetListener {
    private Calendar calendar;
    private Context context;
    private DatePickerDialog dateDialog;
    private boolean eventFired;
    private OnDateSelectedListener listener;

    public interface OnDateSelectedListener {
        void onDateSelected(Calendar calendar);
    }

    public static DatePickerFragment newInstance(Calendar calendar2) {
        return newInstance(calendar2.get(1), calendar2.get(2), calendar2.get(5));
    }

    public static DatePickerFragment newInstance(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return newInstance(instance);
    }

    public static DatePickerFragment newInstance(int i, int i2, int i3) {
        Bundle bundle = new Bundle();
        bundle.putInt("year", i);
        bundle.putInt(Extras.MONTH, i2);
        bundle.putInt("day_of_month", i3);
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setArguments(bundle);
        return datePickerFragment;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        setDateFrom(getArguments());
        this.eventFired = false;
        DatePickerDialog datePickerDialog = new DatePickerDialog(this.context, this, this.calendar.get(1), this.calendar.get(2), this.calendar.get(5));
        this.dateDialog = datePickerDialog;
        return this.dateDialog;
    }

    public void setListener(OnDateSelectedListener onDateSelectedListener) {
        this.listener = onDateSelectedListener;
    }

    private void setDateFrom(Bundle bundle) {
        setDate(BundleUtils.getInt(bundle, "year", -1), BundleUtils.getInt(bundle, Extras.MONTH, -1), BundleUtils.getInt(bundle, "day_of_month", -1));
    }

    public Calendar getSelectedDate() {
        Calendar instance = Calendar.getInstance();
        DatePicker datePicker = this.dateDialog.getDatePicker();
        instance.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
        return instance;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context = activity;
    }

    public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
        handleDateSet(i, i2, i3);
    }

    public void onDateSet(CustomDatePicker customDatePicker, int i, int i2, int i3) {
        handleDateSet(i, i2, i3);
    }

    private void handleDateSet(int i, int i2, int i3) {
        if (!this.eventFired) {
            this.eventFired = true;
            this.calendar = Calendar.getInstance();
            setCalendarValues(i, i2, i3);
            OnDateSelectedListener onDateSelectedListener = this.listener;
            if (onDateSelectedListener != null) {
                onDateSelectedListener.onDateSelected(this.calendar);
            }
            this.messageBus.post(new DialogCalendarEvent(this.calendar));
        }
    }

    private void setCalendarValues(int i, int i2, int i3) {
        this.calendar.set(1, i);
        this.calendar.set(2, i2);
        this.calendar.set(5, i3);
    }

    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }

    public DatePickerFragment setDate(Calendar calendar2) {
        this.calendar = calendar2;
        return this;
    }

    public DatePickerFragment setDate(int i, int i2, int i3) {
        this.calendar = Calendar.getInstance();
        if (i >= 0 && i2 >= 0 && i3 >= 0) {
            setCalendarValues(i, i2, i3);
        }
        return this;
    }
}
