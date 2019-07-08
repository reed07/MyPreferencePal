package com.myfitnesspal.feature.exercise.ui.activity;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.LocalizedStrings;
import com.myfitnesspal.shared.model.AdUnit;
import com.myfitnesspal.shared.model.v1.ExerciseEntry;
import com.myfitnesspal.shared.service.ExerciseStringService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin;
import com.myfitnesspal.shared.util.Database;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.Calendar;
import javax.inject.Inject;

public class EditCardio extends MfpActivity {
    private static final String CARDIO_ATTRIBUTE_VALUE = "cardio";
    private static final int SAVE_CHANGES_ACTION = 9000;
    final int TIME_DIALOG_ID = 1;
    @BindView(2131362395)
    EditText calories;
    @BindView(2131362059)
    TextView caloriesLabel;
    @Inject
    Lazy<DiaryService> diaryService;
    @Inject
    Lazy<ExerciseStringService> exerciseStringService;
    int hourOfDay;
    @BindView(2131362400)
    EditText interval;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    int minute;
    @Inject
    Lazy<Session> session;
    boolean showStartTimeField;
    @BindView(2131364039)
    EditText startTime;
    @BindView(2131363720)
    LinearLayout startTimeLayout;
    private OnTimeSetListener timeSetListener = new OnTimeSetListener() {
        public void onTimeSet(TimePicker timePicker, int i, int i2) {
            try {
                if (EditCardio.this.showStartTimeField) {
                    EditCardio.this.updatedExerciseEntry.setExtraPropertyNamed("start_time", DateTimeUtils.formatAsHoursAndMinutes(i, i2));
                }
                EditText editText = (EditText) EditCardio.this.findViewById(R.id.txtStartTime);
                editText.setText(DateTimeUtils.localeFormattedTime(EditCardio.this, i, i2));
                editText.setSelection(editText.getText().length());
            } catch (Exception e) {
                Ln.e(e);
            }
        }
    };
    ExerciseEntry updatedExerciseEntry;
    @Inject
    Lazy<UserEnergyService> userEnergyService;

    public static Intent newStartIntent(Context context) {
        return newStartIntent(context, null);
    }

    public static Intent newStartIntent(Context context, ExerciseEntry exerciseEntry) {
        return new Intent(context, EditCardio.class).putExtra("exercise_entry", exerciseEntry);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.edit_cardio_exercise);
        component().inject(this);
        Calendar instance = Calendar.getInstance();
        this.hourOfDay = instance.get(11);
        this.minute = instance.get(12);
        this.updatedExerciseEntry = (ExerciseEntry) ExtrasUtils.getParcelable(getIntent(), "exercise_entry", ExerciseEntry.class.getClassLoader());
        this.showStartTimeField = ((Session) this.session.get()).getUser().getProfile().getIsLinkedWithFitbit();
        if (this.showStartTimeField) {
            ViewUtils.setVisible(true, this.startTimeLayout);
            String localeFormattedTime = DateTimeUtils.localeFormattedTime((Context) this, this.updatedExerciseEntry.startTime());
            EditText editText = this.startTime;
            if (!Strings.notEmpty(localeFormattedTime)) {
                localeFormattedTime = DateTimeUtils.localeFormattedTime(this, this.hourOfDay, this.minute);
            }
            editText.setText(localeFormattedTime);
        } else {
            ViewUtils.setVisible(false, this.startTimeLayout);
        }
        loadInfo();
        addEventListeners();
    }

    private void loadInfo() {
        try {
            setTitle(((ExerciseStringService) this.exerciseStringService.get()).getDescriptionName(this.updatedExerciseEntry.getExercise()));
            this.interval.setText(String.valueOf(this.updatedExerciseEntry.getQuantity()));
            this.interval.setSelection(this.interval.getText().length());
            this.caloriesLabel.setText(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.BURNED, this.userEnergyService.get()));
            this.calories.setText(((UserEnergyService) this.userEnergyService.get()).getDisplayableEnergy(this.updatedExerciseEntry != null ? (double) this.updatedExerciseEntry.getCalories() : 0.0d));
            this.calories.setSelection(this.calories.getText().length());
        } catch (Exception e) {
            Ln.e(e);
            finish();
            getImmHelper().hideSoftInput((View) getTheFocusedControl());
        }
    }

    private void addEventListeners() {
        if (this.showStartTimeField) {
            this.startTimeLayout.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    try {
                        EditCardio.this.getImmHelper().hideSoftInput();
                        EditCardio.this.showDialog(1);
                    } catch (Exception e) {
                        Ln.e(e);
                    }
                }
            });
            this.startTime.setOnKeyListener(new OnKeyListener() {
                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    return i != 4;
                }
            });
            this.startTime.setOnFocusChangeListener(new OnFocusChangeListener() {
                public void onFocusChange(View view, boolean z) {
                    if (z) {
                        try {
                            EditCardio.this.getImmHelper().hideSoftInput();
                            EditCardio.this.showDialog(1);
                            EditCardio.this.startTime.setSelection(EditCardio.this.startTime.getText().length());
                        } catch (Exception e) {
                            Ln.e(e);
                        }
                    }
                }
            });
            this.startTime.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    try {
                        EditCardio.this.getImmHelper().hideSoftInput();
                        EditCardio.this.showDialog(1);
                    } catch (Exception e) {
                        Ln.e(e);
                    }
                }
            });
        }
        this.interval.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                EditCardio.this.updateCalories(NumberUtils.tryParseInt(editable.toString()));
            }
        });
        this.interval.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    EditCardio.this.interval.setSelection(EditCardio.this.interval.getText().length());
                }
            }
        });
        this.calories.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    EditCardio.this.calories.setSelection(EditCardio.this.calories.getText().length());
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void saveClick() {
        try {
            String trimmed = Strings.trimmed((Object) this.calories.getText());
            String trimmed2 = Strings.trimmed((Object) this.interval.getText());
            int minutesOfCardioExercises = (int) (1440.0f - (((DiaryService) this.diaryService.get()).getDiaryDayForActiveDateSync().minutesOfCardioExercises() - ((float) this.updatedExerciseEntry.getQuantity())));
            LegacyAlertMixin legacyAlertMixin = (LegacyAlertMixin) mixin(LegacyAlertMixin.class);
            if (!Strings.isEmpty(trimmed2)) {
                if (!Strings.equals(trimmed2, "0")) {
                    if (!Strings.isEmpty(trimmed)) {
                        if (!Strings.equals(trimmed, "0")) {
                            boolean z = false;
                            if (Integer.parseInt(trimmed2) > minutesOfCardioExercises) {
                                legacyAlertMixin.showAlertDialog(getString(R.string.cardio_exercises_minutes_error, new Object[]{Integer.toString(minutesOfCardioExercises)}));
                                return;
                            } else if (!this.showStartTimeField || !Strings.isEmpty(this.updatedExerciseEntry.startTime())) {
                                try {
                                    float calories2 = ((UserEnergyService) this.userEnergyService.get()).getCalories(trimmed);
                                    int parseInt = Integer.parseInt(trimmed2);
                                    if (!(calories2 == this.updatedExerciseEntry.getCalories() && parseInt == this.updatedExerciseEntry.getQuantity())) {
                                        z = true;
                                    }
                                    this.updatedExerciseEntry.setCalories(calories2);
                                    this.updatedExerciseEntry.setQuantity(parseInt);
                                    this.updatedExerciseEntry.setDate(((Session) this.session.get()).getUser().getActiveDate());
                                    ((DiaryService) this.diaryService.get()).getDiaryDayForActiveDateSync().updateExerciseEntry(this.updatedExerciseEntry);
                                    if (z) {
                                        reportUserEditedExerciseEvent();
                                    }
                                    setResult(-1, new Intent());
                                    getImmHelper().hideSoftInput((View) getTheFocusedControl());
                                    finish();
                                    return;
                                } catch (NumberFormatException e) {
                                    legacyAlertMixin.showAlertDialog(getResources().getString(R.string.enter_numeric_value));
                                    Ln.e(e);
                                    return;
                                }
                            } else {
                                legacyAlertMixin.showAlertDialog(getString(R.string.alert_add_exercise_start_time));
                                return;
                            }
                        }
                    }
                    legacyAlertMixin.showAlertDialog(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.ALERT_EXERCISE, this.userEnergyService.get()));
                    return;
                }
            }
            legacyAlertMixin.showAlertDialog(getString(R.string.alert_exercise_interval));
        } catch (Exception e2) {
            Ln.e(e2);
        }
    }

    private void reportUserEditedExerciseEvent() {
        getAnalyticsService().reportEvent(Events.USER_EDITED_EXERCISE_ENTRY, MapUtil.createMap("exercise_type", "cardio", Attributes.EXERCISE_SOURCE, this.updatedExerciseEntry.extraPropertyNamed("source"), Attributes.EXERCISE_DATE, Database.encodeDate(this.updatedExerciseEntry.getDate())));
    }

    /* access modifiers changed from: protected */
    public Dialog onCreateDialog(int i) {
        if (i != 1) {
            try {
                return super.onCreateDialog(i);
            } catch (Exception e) {
                Ln.e(e);
                return super.onCreateDialog(i);
            }
        } else {
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, this.timeSetListener, this.hourOfDay, this.minute, DateFormat.is24HourFormat(this));
            return timePickerDialog;
        }
    }

    /* access modifiers changed from: protected */
    public void onPrepareDialog(int i, Dialog dialog) {
        if (i != 1) {
            try {
                super.onPrepareDialog(i, dialog);
            } catch (Exception e) {
                Ln.e(e);
            }
        } else {
            ((TimePickerDialog) dialog).updateTime(this.hourOfDay, this.minute);
        }
    }

    /* access modifiers changed from: private */
    public void updateCalories(int i) {
        this.calories.setText(((UserEnergyService) this.userEnergyService.get()).getDisplayableEnergy((double) this.updatedExerciseEntry.getExercise().cardioCaloriesBurnedForHours((float) (((double) ((float) i)) / 60.0d), (Session) this.session.get())));
    }

    private EditText getTheFocusedControl() {
        return this.interval.isFocused() ? this.interval : this.calories;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!super.onPrepareOptionsMenu(menu)) {
            return false;
        }
        addProminentActionItem(menu, R.string.save, new OnClickListener() {
            public void onClick(View view) {
                EditCardio.this.saveClick();
            }
        });
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != SAVE_CHANGES_ACTION) {
            return super.onOptionsItemSelected(menuItem);
        }
        saveClick();
        return true;
    }

    public AdUnit getAdUnit() {
        return getAdUnitService().getAddOrEditExerciseEntryScreenAdUnitValue();
    }
}
