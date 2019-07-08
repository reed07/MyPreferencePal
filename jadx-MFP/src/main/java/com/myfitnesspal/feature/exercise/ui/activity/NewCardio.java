package com.myfitnesspal.feature.exercise.ui.activity;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.exercise.service.ExerciseAnalyticsHelper;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.LocalizedStrings;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.Calendar;
import javax.inject.Inject;

public class NewCardio extends GenericExercise {
    private static final int TIME_DIALOG_ID = 1;
    @BindView(2131362395)
    EditText calories;
    @BindView(2131362059)
    TextView caloriesLabel;
    @Inject
    Lazy<ExerciseAnalyticsHelper> exerciseAnalyticsHelper;
    private int hourOfDay;
    @BindView(2131362400)
    EditText interval;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    private int minute;
    @Inject
    Lazy<Session> session;
    private boolean showStartTimeField;
    @BindView(2131364039)
    EditText startTime;
    @BindView(2131363720)
    View startTimeLayout;
    private OnTimeSetListener timeSetListener = new OnTimeSetListener() {
        public void onTimeSet(TimePicker timePicker, int i, int i2) {
            ((EditText) NewCardio.this.findViewById(R.id.txtStartTime)).setText(DateTimeUtils.localeFormattedTime(NewCardio.this, i, i2));
        }
    };
    @Inject
    Lazy<UserEnergyService> userEnergyService;

    static /* synthetic */ boolean lambda$hookupUIEventListeners$1(View view, int i, KeyEvent keyEvent) {
        return i != 4;
    }

    public static Intent newStartIntent(Context context, boolean z) {
        return new Intent(context, NewCardio.class).putExtra(Extras.ADD_TO_DIARY_AFTER_CREATE, z);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.new_cardio_exercise);
        component().inject(this);
        this.exerciseType = 0;
    }

    public void onSetContentView() {
        super.onSetContentView();
        Calendar instance = Calendar.getInstance();
        this.hourOfDay = instance.get(11);
        this.minute = instance.get(12);
        this.timeSetListener.onTimeSet(null, this.hourOfDay, 0);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.showStartTimeField = ((Session) this.session.get()).getUser().getProfile().getIsLinkedWithFitbit();
        ViewUtils.setVisible(this.showStartTimeField, this.startTimeLayout);
        this.caloriesLabel.setText(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.BURNED, this.userEnergyService.get()));
        this.descriptionView.requestFocus();
        hookupUIEventListeners();
    }

    private void hookupUIEventListeners() {
        if (this.showStartTimeField) {
            this.startTimeLayout.setOnClickListener(new OnClickListener() {
                public final void onClick(View view) {
                    NewCardio.lambda$hookupUIEventListeners$0(NewCardio.this, view);
                }
            });
            this.startTime.setOnKeyListener($$Lambda$NewCardio$3uNhZopUG2TkPhpW7V_4HeyG8pY.INSTANCE);
            this.startTime.setOnFocusChangeListener(new OnFocusChangeListener() {
                public final void onFocusChange(View view, boolean z) {
                    NewCardio.lambda$hookupUIEventListeners$2(NewCardio.this, view, z);
                }
            });
            this.startTime.setOnClickListener(new OnClickListener() {
                public final void onClick(View view) {
                    NewCardio.lambda$hookupUIEventListeners$3(NewCardio.this, view);
                }
            });
        }
        this.calories.setOnKeyListener(this.addKeyListener);
    }

    public static /* synthetic */ void lambda$hookupUIEventListeners$0(NewCardio newCardio, View view) {
        newCardio.getImmHelper().hideSoftInput();
        newCardio.showDialog(1);
    }

    public static /* synthetic */ void lambda$hookupUIEventListeners$2(NewCardio newCardio, View view, boolean z) {
        if (z) {
            newCardio.getImmHelper().hideSoftInput();
            newCardio.showDialog(1);
        }
    }

    public static /* synthetic */ void lambda$hookupUIEventListeners$3(NewCardio newCardio, View view) {
        newCardio.getImmHelper().hideSoftInput();
        newCardio.showDialog(1);
    }

    /* access modifiers changed from: protected */
    public void onSave() {
        setStartTime();
        getImmHelper().hideSoftInput();
        String trimmed = Strings.trimmed((Object) this.descriptionView.getText());
        String trimmed2 = Strings.trimmed((Object) this.calories.getText());
        String trimmed3 = Strings.trimmed((Object) this.interval.getText());
        int minutesOfCardioExercises = (int) (1440.0f - ((DiaryService) this.diaryService.get()).getDiaryDayForActiveDateSync().minutesOfCardioExercises());
        LegacyAlertMixin legacyAlertMixin = (LegacyAlertMixin) mixin(LegacyAlertMixin.class);
        if (Strings.isEmpty(trimmed)) {
            legacyAlertMixin.showAlertDialog(getString(R.string.alert_exercise_description));
        } else if (Strings.isEmpty(trimmed3) || Strings.equals(trimmed3, "0")) {
            legacyAlertMixin.showAlertDialog(getString(R.string.alert_exercise_interval));
        } else if (Strings.isEmpty(trimmed2) || Strings.equals(trimmed2, "0")) {
            legacyAlertMixin.showAlertDialog(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.ALERT_EXERCISE, this.userEnergyService.get()));
        } else if (Integer.parseInt(trimmed3) > minutesOfCardioExercises) {
            legacyAlertMixin.showAlertDialog(getString(R.string.cardio_exercises_minutes_error, new Object[]{Integer.toString(minutesOfCardioExercises)}));
        } else if (!this.showStartTimeField || !Strings.isEmpty(this.createdExerciseEntry.startTime())) {
            this.description = trimmed;
            this.caloriesBurned = ((UserEnergyService) this.userEnergyService.get()).getCalories(trimmed2);
            this.duration = NumberUtils.tryParseInt(trimmed3, -1);
            if (this.duration > 0) {
                ((ExerciseAnalyticsHelper) this.exerciseAnalyticsHelper.get()).reportNewCardioExerciseCreated(this.caloriesBurned, this.duration);
                onSaveCompleted();
                return;
            }
            legacyAlertMixin.showAlertDialog(getResources().getString(R.string.enter_numeric_value));
        } else {
            legacyAlertMixin.showAlertDialog(getString(R.string.alert_add_exercise_start_time));
        }
    }

    /* access modifiers changed from: protected */
    public Dialog onCreateDialog(int i) {
        if (i != 1) {
            return super.onCreateDialog(i);
        }
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, this.timeSetListener, this.hourOfDay, this.minute, false);
        return timePickerDialog;
    }

    /* access modifiers changed from: protected */
    public void onPrepareDialog(int i, Dialog dialog) {
        if (i != 1) {
            super.onPrepareDialog(i, dialog);
        } else {
            ((TimePickerDialog) dialog).updateTime(this.hourOfDay, this.minute);
        }
    }

    private void setStartTime() {
        if (this.showStartTimeField) {
            EditText editText = this.startTime;
            int[] timeComponents = DateTimeUtils.getTimeComponents(editText != null ? Strings.toString(editText.getText()) : "08:00 AM");
            if (timeComponents != null) {
                this.createdExerciseEntry.setExtraPropertyNamed("start_time", DateTimeUtils.formatAsHoursAndMinutes(timeComponents[0], timeComponents[1]));
            }
        }
    }
}
