package com.myfitnesspal.feature.settings.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.settings.event.DayOfWeekEvent;
import com.myfitnesspal.feature.settings.event.FrequencyEvent;
import com.myfitnesspal.feature.settings.task.DeleteRemindersTask;
import com.myfitnesspal.feature.settings.task.InsertReminderTask;
import com.myfitnesspal.feature.settings.task.UpdateReminderTask;
import com.myfitnesspal.feature.settings.task.UpdateReminderTask.CompletedEvent;
import com.myfitnesspal.feature.settings.ui.dialog.DayOfWeekDialogFragment;
import com.myfitnesspal.feature.settings.ui.dialog.ReminderFrequencyDialogFragment;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.constants.Constants.DateTime;
import com.myfitnesspal.shared.constants.Constants.Dialogs.Fragments;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.Reminder;
import com.myfitnesspal.shared.event.DialogTimePickerEvent;
import com.myfitnesspal.shared.event.NumberSelectedEvent;
import com.myfitnesspal.shared.model.MealNames;
import com.myfitnesspal.shared.model.v15.ReminderObject;
import com.myfitnesspal.shared.service.reminders.RemindersService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.impl.NumberPickerDialogFragment;
import com.myfitnesspal.shared.ui.dialog.impl.TimePickerDialogFragment;
import com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.inject.Inject;

public class EditReminder extends MfpActivity {
    protected static final int DELETE_ITEM_ID = 102;
    public static final String EXTRA_NEW_ID = "extra_new_id";
    public static final String EXTRA_ORIGINAL_ID = "extra_original_id";
    private static final int MAX_DAYS_IN_MONTH = 32;
    private static final int MIN_DAYS_IN_MONTH = 1;
    public static final int RESULT_DELETED = 3;
    public static final int RESULT_INSERTED = 4;
    public static final int RESULT_UPDATED = 2;
    protected static final int SAVE_ITEM_ID = 101;
    private static final int THIRTY_ONE_DAY = 31;
    private static final String TIME_PICKER = "reminder_time_picker";
    private Calendar calendar = Calendar.getInstance();
    private ReminderObject editedReminder;
    private boolean isForEdit;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    private ReminderObject originalReminder;
    @BindView(2131363435)
    View reminderDayOfContainer;
    @BindView(2131363436)
    TextView reminderDayOfHeader;
    @BindView(2131363437)
    TextView reminderDayOfMonth;
    @BindView(2131363438)
    TextView reminderDayOfWeek;
    @BindView(2131363441)
    TextView reminderFrequencyBtn;
    @BindView(2131363439)
    View reminderFrequencyContainer;
    @BindView(2131363444)
    TextView reminderTimeBtn;
    @BindView(2131363447)
    TextView reminderTypeValue;
    @Inject
    Lazy<RemindersService> remindersService;

    public static Intent newStartIntentForAdd(Context context) {
        return new Intent(context, EditReminder.class).putExtra(Extras.IS_FOR_EDIT, false);
    }

    public static Intent newStartIntentForEdit(Context context, ReminderObject reminderObject) {
        return new Intent(context, EditReminder.class).putExtra("reminder", reminderObject).putExtra(Extras.IS_FOR_EDIT, true);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.add_reminder);
        component().inject(this);
        this.isForEdit = ExtrasUtils.getBoolean(getIntent(), Extras.IS_FOR_EDIT);
        if (!this.isForEdit) {
            setReminder(((RemindersService) this.remindersService.get()).defaultNewReminderForUser(ExtrasUtils.getString(getIntent(), "reminder_type")));
            setTitle(getString(R.string.new_reminder));
        } else {
            this.originalReminder = (ReminderObject) ExtrasUtils.getParcelable(getIntent(), "reminder", ReminderObject.class.getClassLoader());
            setReminder(this.originalReminder);
            setTitle(getString(R.string.edit_reminder));
            supportInvalidateOptionsMenu();
        }
        this.reminderTypeValue.setText(this.editedReminder.description(this, this.localizedStringsUtil, getMealNames()));
        this.reminderTimeBtn.setText(DateTimeUtils.localeFormattedTime((Context) this, this.editedReminder.getWallClockTime()));
        toggleChoiceOptions();
        hookUpUIEventListeners();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        getTimeComponents();
    }

    private MealNames getMealNames() {
        return getSession().getUser().getMealNames();
    }

    private void hookUpUIEventListeners() {
        this.reminderTypeValue.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                EditReminder.this.getNavigationHelper().withFlags(1073741824, 0).withExtra(Extras.REMINDER_MEAL_NAME, ExtrasUtils.getString(EditReminder.this.getIntent(), Extras.REMINDER_MEAL_NAME)).withExtra(Extras.REMINDER_DESCRIPTION, Strings.toString(EditReminder.this.reminderTypeValue.getText())).withIntent(SelectReminder.newStartIntent(EditReminder.this)).startActivity(56);
            }
        });
        this.reminderFrequencyBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                EditReminder.this.showFrequencyPickerDialog();
            }
        });
        this.reminderDayOfWeek.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                EditReminder.this.showDayOfWeekPickerDialog();
            }
        });
        this.reminderTimeBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                EditReminder.this.showTimePickerDialog();
            }
        });
        this.reminderDayOfMonth.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                EditReminder.this.showDayOfMonthPickerDialog();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 56 && i2 == -1) {
            ReminderObject reminderObject = (ReminderObject) ExtrasUtils.getParcelable(intent, "reminder", ReminderObject.class.getClassLoader());
            this.editedReminder.setReminderType(reminderObject.getReminderType());
            this.editedReminder.setMealName(reminderObject.getMealName());
            this.editedReminder.setMealId(reminderObject.getMealId());
            this.editedReminder.setIntervalInDays(reminderObject.getIntervalInDays());
            this.editedReminder.setFrequency(reminderObject.getFrequency());
            this.editedReminder.setDayOfMonth(reminderObject.getDayOfMonth());
            this.editedReminder.setDayOfWeek(reminderObject.getDayOfWeek());
            this.reminderTypeValue.setText(this.editedReminder.description(this, this.localizedStringsUtil, getMealNames()));
            toggleChoiceOptions();
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!super.onPrepareOptionsMenu(menu)) {
            return false;
        }
        menu.clear();
        MenuItemCompat.setShowAsAction(menu.add(0, 101, 0, R.string.save).setIcon(R.drawable.ic_check_white_24dp), 2);
        if (this.isForEdit) {
            MenuItemCompat.setShowAsAction(menu.add(0, 102, 0, R.string.delete).setIcon(R.drawable.ic_delete_white_24dp), 2);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 101:
                onSave();
                return true;
            case 102:
                deleteReminder(this.originalReminder);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    private void getTimeComponents() {
        this.calendar.setTime(DateTimeUtils.parseTime(this, Strings.toString(this.reminderTimeBtn.getText(), DateFormat.getTimeInstance(3).format(new Date()))));
    }

    private void setReminder(ReminderObject reminderObject) {
        this.originalReminder = reminderObject;
        this.editedReminder = new ReminderObject(reminderObject);
    }

    private void deleteReminder(ReminderObject reminderObject) {
        setBusy(true);
        String eventNameForReminder = ((RemindersService) this.remindersService.get()).getEventNameForReminder(reminderObject);
        if (Strings.notEmpty(eventNameForReminder)) {
            getAnalyticsService().reportEvent(eventNameForReminder, MapUtil.createMap(Attributes.CHANGED_STATUS, "deleted"));
        }
        new DeleteRemindersTask(this.remindersService, reminderObject).run(getRunner());
    }

    private boolean hasChangesToSave() {
        ReminderObject reminderObject = this.originalReminder;
        return reminderObject == null || !reminderObject.hasLocalId() || !this.originalReminder.hasSameValuesAs(this.editedReminder);
    }

    /* access modifiers changed from: protected */
    public void onSave() {
        if (this.editedReminder.getReminderType() == 4) {
            this.editedReminder.setIntervalInDays(0);
            this.editedReminder.setMealName("");
            this.editedReminder.setMealId("");
        } else {
            this.editedReminder.setFrequency("");
            this.editedReminder.setDayOfMonth(0);
            this.editedReminder.setDayOfWeek("");
        }
        if (!this.isForEdit) {
            setBusy(true);
            new InsertReminderTask(this.remindersService, new ReminderObject(this.editedReminder)).run(getRunner());
            getAnalyticsService().reportEvent(Events.NEWREMINDER_SAVEBTN_CLICK);
        } else if (hasChangesToSave()) {
            setBusy(true);
            new UpdateReminderTask(this.remindersService, new ReminderObject(this.editedReminder)).run(getRunner());
        } else {
            setResult(-1);
            finish();
        }
    }

    /* access modifiers changed from: private */
    public void showTimePickerDialog() {
        TimePickerDialogFragment.newInstance().setTime(this.calendar).show(getSupportFragmentManager(), TIME_PICKER);
    }

    /* access modifiers changed from: private */
    public void showFrequencyPickerDialog() {
        ReminderFrequencyDialogFragment.newInstance().show(getSupportFragmentManager(), Fragments.REMINDER_FREQUENCY_DIALOG);
    }

    /* access modifiers changed from: private */
    public void showDayOfWeekPickerDialog() {
        DayOfWeekDialogFragment.newInstance().show(getSupportFragmentManager(), Fragments.DAY_OF_WEEK_DIALOG);
    }

    /* access modifiers changed from: private */
    public void showDayOfMonthPickerDialog() {
        NumberPickerDialogFragment.newInstance(R.string.select_day, 1, 32, this.editedReminder.getDayOfMonth()).show(getSupportFragmentManager(), Fragments.NUMBER_PICKER_DIALOG);
    }

    private void toggleChoiceOptions() {
        if (this.editedReminder.getReminderType() == 4) {
            if (Strings.isEmpty(this.editedReminder.getFrequency())) {
                this.editedReminder.setFrequency(Reminder.WEEKLY_FREQUENCY);
                this.editedReminder.setWallClockTime(Reminder.SEVEN_THIRTY_OCLOCK);
                this.reminderTimeBtn.setText(DateTimeUtils.localeFormattedTime((Context) this, this.editedReminder.getWallClockTime()));
            }
            this.reminderFrequencyBtn.setText(((RemindersService) this.remindersService.get()).getLocalizedFrequencyString(this.editedReminder.getFrequency()));
            ViewUtils.setVisible(true, this.reminderFrequencyContainer);
            ViewUtils.setVisible(this.reminderDayOfContainer);
            if (Strings.equalsIgnoreCase(this.editedReminder.getFrequency(), Reminder.DAILY_FREQUENCY)) {
                ViewUtils.setGone(this.reminderDayOfContainer);
            } else if (Strings.equalsIgnoreCase(this.editedReminder.getFrequency(), Reminder.MONTHLY_FREQUENCY)) {
                this.reminderDayOfHeader.setText(R.string.day_of_the_month);
                ViewUtils.setVisible(true, this.reminderDayOfHeader);
                ViewUtils.setGone(this.reminderDayOfWeek);
                ViewUtils.setVisible(true, this.reminderDayOfMonth);
                if (this.editedReminder.getDayOfMonth() <= 0) {
                    this.editedReminder.setDayOfMonth(1);
                }
                setReminderDayOfMonth(this.editedReminder.getDayOfMonth());
            } else {
                this.reminderDayOfHeader.setText(R.string.day_of_the_week);
                ViewUtils.setVisible(true, this.reminderDayOfHeader);
                if (Strings.isEmpty(this.editedReminder.getDayOfWeek())) {
                    this.editedReminder.setDayOfWeek(DateTime.MONDAY);
                }
                this.reminderDayOfWeek.setText(DateTimeUtils.getLongDayOfWeek(this.editedReminder.getDayOfWeek()));
                ViewUtils.setVisible(true, this.reminderDayOfWeek);
                ViewUtils.setGone(this.reminderDayOfMonth);
            }
        } else {
            ViewUtils.setGone(this.reminderFrequencyContainer);
            ViewUtils.setGone(this.reminderDayOfContainer);
        }
    }

    public String getAnalyticsScreenTag() {
        if (!this.isForEdit) {
            return Screens.NEW_REMINDER;
        }
        return null;
    }

    private void setReminderDayOfMonth(int i) {
        this.reminderDayOfMonth.setText(i == 32 ? getString(R.string.last_day) : NumberUtils.localeStringFromInt(i));
    }

    private void finish(int i) {
        setBusy(false);
        setResult(i);
        finish();
    }

    private void finish(long j, long j2) {
        setBusy(false);
        Intent intent = new Intent();
        intent.putExtra(EXTRA_ORIGINAL_ID, j);
        intent.putExtra(EXTRA_NEW_ID, j2);
        setResult(2, intent);
        finish();
    }

    @Subscribe
    public void onTimePicked(DialogTimePickerEvent dialogTimePickerEvent) {
        int i = dialogTimePickerEvent.getCalendar().get(11);
        int i2 = dialogTimePickerEvent.getCalendar().get(12);
        this.reminderTimeBtn.setText(DateTimeUtils.localeFormattedTime(this, i, i2));
        ReminderObject reminderObject = this.editedReminder;
        if (reminderObject != null) {
            reminderObject.setLocalTimeOfDay(i, i2, 0);
        }
    }

    @Subscribe
    public void onFrequencyEvent(FrequencyEvent frequencyEvent) {
        String frequency = frequencyEvent.getFrequency();
        if (Strings.notEmpty(frequency)) {
            this.reminderFrequencyBtn.setText(((RemindersService) this.remindersService.get()).getLocalizedFrequencyString(frequency));
            ReminderObject reminderObject = this.editedReminder;
            if (reminderObject != null) {
                reminderObject.setFrequency(frequency);
                toggleChoiceOptions();
            }
        }
    }

    @Subscribe
    public void onDayOfWeekEventEvent(DayOfWeekEvent dayOfWeekEvent) {
        String dayOfWeek = dayOfWeekEvent.getDayOfWeek();
        if (Strings.notEmpty(dayOfWeek)) {
            this.reminderDayOfWeek.setText(DateTimeUtils.getLongDayOfWeek(dayOfWeek));
            ReminderObject reminderObject = this.editedReminder;
            if (reminderObject != null) {
                reminderObject.setDayOfWeek(dayOfWeek);
            }
        }
    }

    @Subscribe
    public void onNumberSelectedEventEvent(NumberSelectedEvent numberSelectedEvent) {
        int day = numberSelectedEvent.getDay();
        setReminderDayOfMonth(day);
        ReminderObject reminderObject = this.editedReminder;
        if (reminderObject != null) {
            reminderObject.setDayOfMonth(day);
        }
        if (day == 31) {
            ((LegacyAlertMixin) mixin(LegacyAlertMixin.class)).showAlertDialogWithTitle(getString(R.string.alert), getString(R.string.receive_only_on_months_with_31_days), getString(R.string.dismiss));
        }
    }

    @Subscribe
    public void onUpdateReminderTask(CompletedEvent completedEvent) {
        if (completedEvent.isFrom(getRunner())) {
            finish(completedEvent.getOriginalId(), completedEvent.getNewId());
        }
    }

    @Subscribe
    public void onInsertReminderTask(InsertReminderTask.CompletedEvent completedEvent) {
        if (completedEvent.isFrom(getRunner())) {
            finish(4);
        }
    }

    @Subscribe
    public void onDeleteRemindersTask(DeleteRemindersTask.CompletedEvent completedEvent) {
        if (completedEvent.isFrom(getRunner())) {
            finish(3);
        }
    }
}
