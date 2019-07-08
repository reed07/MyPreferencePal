package com.myfitnesspal.feature.settings.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class EditReminder_ViewBinding implements Unbinder {
    private EditReminder target;

    @UiThread
    public EditReminder_ViewBinding(EditReminder editReminder) {
        this(editReminder, editReminder.getWindow().getDecorView());
    }

    @UiThread
    public EditReminder_ViewBinding(EditReminder editReminder, View view) {
        this.target = editReminder;
        editReminder.reminderTimeBtn = (TextView) Utils.findRequiredViewAsType(view, R.id.reminder_time_value, "field 'reminderTimeBtn'", TextView.class);
        editReminder.reminderTypeValue = (TextView) Utils.findRequiredViewAsType(view, R.id.reminder_type_value, "field 'reminderTypeValue'", TextView.class);
        editReminder.reminderFrequencyContainer = Utils.findRequiredView(view, R.id.reminder_frequency_container, "field 'reminderFrequencyContainer'");
        editReminder.reminderFrequencyBtn = (TextView) Utils.findRequiredViewAsType(view, R.id.reminder_frequency_value, "field 'reminderFrequencyBtn'", TextView.class);
        editReminder.reminderDayOfContainer = Utils.findRequiredView(view, R.id.reminder_day_of_container, "field 'reminderDayOfContainer'");
        editReminder.reminderDayOfHeader = (TextView) Utils.findRequiredViewAsType(view, R.id.reminder_day_of_header, "field 'reminderDayOfHeader'", TextView.class);
        editReminder.reminderDayOfWeek = (TextView) Utils.findRequiredViewAsType(view, R.id.reminder_day_of_week, "field 'reminderDayOfWeek'", TextView.class);
        editReminder.reminderDayOfMonth = (TextView) Utils.findRequiredViewAsType(view, R.id.reminder_day_of_month, "field 'reminderDayOfMonth'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        EditReminder editReminder = this.target;
        if (editReminder != null) {
            this.target = null;
            editReminder.reminderTimeBtn = null;
            editReminder.reminderTypeValue = null;
            editReminder.reminderFrequencyContainer = null;
            editReminder.reminderFrequencyBtn = null;
            editReminder.reminderDayOfContainer = null;
            editReminder.reminderDayOfHeader = null;
            editReminder.reminderDayOfWeek = null;
            editReminder.reminderDayOfMonth = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
