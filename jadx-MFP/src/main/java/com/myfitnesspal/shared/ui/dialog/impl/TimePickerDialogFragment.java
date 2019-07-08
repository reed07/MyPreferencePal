package com.myfitnesspal.shared.ui.dialog.impl;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;
import com.myfitnesspal.shared.event.DialogTimePickerEvent;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import java.util.Calendar;

public class TimePickerDialogFragment extends CustomLayoutBaseDialogFragment implements OnTimeSetListener {
    private Calendar calendar = Calendar.getInstance();
    private long id;

    public static TimePickerDialogFragment newInstance() {
        return new TimePickerDialogFragment();
    }

    public TimePickerDialogFragment setTime(Calendar calendar2) {
        this.calendar = calendar2;
        return this;
    }

    public TimePickerDialogFragment setId(long j) {
        this.id = j;
        return this;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(getDialogContextThemeWrapper(), this, this.calendar.get(11), this.calendar.get(12), DateFormat.is24HourFormat(getActivity()));
        return timePickerDialog;
    }

    public void onTimeSet(TimePicker timePicker, int i, int i2) {
        Calendar calendar2 = this.calendar;
        if (calendar2 != null) {
            calendar2.set(11, i);
            this.calendar.set(12, i2);
        }
        this.messageBus.post(new DialogTimePickerEvent(this.id, this.calendar));
    }
}
