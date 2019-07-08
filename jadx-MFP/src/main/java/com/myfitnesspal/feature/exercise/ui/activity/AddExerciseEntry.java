package com.myfitnesspal.feature.exercise.ui.activity;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
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
import android.widget.Toast;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.logging.type.LogSeverity;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.appgallery.api.ExerciseTrackingAppRecommendation;
import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.appgallery.service.ExerciseRecommendationTask;
import com.myfitnesspal.feature.appgallery.service.ExerciseRecommendationTask.CompletedEvent;
import com.myfitnesspal.feature.appgallery.ui.XPromoInterstitialActivity;
import com.myfitnesspal.feature.appgallery.util.AppStateUtil;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.exercise.ui.fragment.StrengthCalorieAlertFragment;
import com.myfitnesspal.feature.settings.model.XPromoSettings;
import com.myfitnesspal.shared.api.ApiResponseBase;
import com.myfitnesspal.shared.api.v2.MfpV2ApiErrorCallback;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.LocalizedStrings;
import com.myfitnesspal.shared.constants.Constants.Report;
import com.myfitnesspal.shared.db.DatasetManager;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.AdUnit;
import com.myfitnesspal.shared.model.v1.UserProfile;
import com.myfitnesspal.shared.model.v2.MfpCorrelationIdDetails;
import com.myfitnesspal.shared.model.v2.MfpExercise;
import com.myfitnesspal.shared.model.v2.MfpExercise.ExerciseTypes;
import com.myfitnesspal.shared.model.v2.MfpExerciseEntry;
import com.myfitnesspal.shared.model.v2.MfpMeasuredValue;
import com.myfitnesspal.shared.model.v2.MfpMeasuredValue.Unit;
import com.myfitnesspal.shared.model.v2.MfpPlatformApp;
import com.myfitnesspal.shared.service.ExerciseStringService;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.appindexer.AppIndexerClient;
import com.myfitnesspal.shared.service.appindexer.AppIndexerUriUtil;
import com.myfitnesspal.shared.service.appindexer.AppIndexerUriUtil.Source;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.id.IdService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.task.QueryStockExerciseByMasterIdTask;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogPositiveListener;
import com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.myfitnesspal.shared.util.MultiAddExerciseSelection;
import com.myfitnesspal.shared.util.UnitsUtils.Energy;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Ln;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;

public class AddExerciseEntry extends MfpActivity {
    private static final String ERROR_DIALOG_TAG = "error_dialog";
    private static final int SAVE_ACTION_ITEM = 9000;
    private static final int TIME_DIALOG_ID = 1;
    @Inject
    Lazy<ActionTrackingService> actionTrackingService;
    @Inject
    Lazy<AppGalleryService> appGalleryService;
    private AppIndexerClient appIndexerClient;
    private TextView caloriesBurnedFaq;
    /* access modifiers changed from: private */
    public EditText cals;
    @Inject
    Lazy<ConfigService> configService;
    @Inject
    Lazy<DbConnectionManager> dbConnectionManager;
    private String description;
    @Inject
    Lazy<DiaryService> diaryService;
    /* access modifiers changed from: private */
    public MfpExerciseEntry exerciseEntry;
    @Inject
    Lazy<ExerciseStringService> exerciseStringService;
    private int hourOfDay;
    @Inject
    Lazy<IdService> idService;
    /* access modifiers changed from: private */
    public EditText interval;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    private int minute;
    /* access modifiers changed from: private */
    public EditText minutes;
    private OnKeyListener onCardioKeyListener = new OnKeyListener() {
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (i != 66 || keyEvent.getAction() != 0) {
                return false;
            }
            AddExerciseEntry.this.addCardio();
            return true;
        }
    };
    private OnKeyListener onStrengthKeyListener = new OnKeyListener() {
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (i != 66 || keyEvent.getAction() != 0) {
                return false;
            }
            AddExerciseEntry.this.addStrength();
            return true;
        }
    };
    /* access modifiers changed from: private */
    public EditText reps;
    /* access modifiers changed from: private */
    public EditText sets;
    /* access modifiers changed from: private */
    public boolean showStartTimeField;
    private int sourceExerciseType;
    /* access modifiers changed from: private */
    public EditText startTime;
    private LinearLayout startTimeLayout;
    private OnTimeSetListener timeSetListener = new OnTimeSetListener() {
        public void onTimeSet(TimePicker timePicker, int i, int i2) {
            if (AddExerciseEntry.this.showStartTimeField) {
                AddExerciseEntry.this.exerciseEntry.setStartTimeString(DateTimeUtils.formatAsHoursAndMinutes(i, i2));
            }
            EditText editText = (EditText) AddExerciseEntry.this.findViewById(R.id.txtStartTime);
            editText.setText(DateTimeUtils.localeFormattedTime(AddExerciseEntry.this, i, i2));
            editText.setSelection(editText.getText().length());
        }
    };
    @Inject
    Lazy<UserEnergyService> userEnergyService;
    @Inject
    Lazy<UserWeightService> userWeightService;
    /* access modifiers changed from: private */
    public EditText weight;
    @Inject
    Lazy<XPromoSettings> xpromoSettings;

    public String getAnalyticsScreenTag() {
        return Screens.ADD_EXERCISE;
    }

    public static Intent newStartIntent(Context context, int i) {
        return new Intent(context, AddExerciseEntry.class).putExtra(Extras.EXERCISE_TYPE, i);
    }

    public static Intent newStartIntent(Context context, MfpExerciseEntry mfpExerciseEntry) {
        int value = ExerciseTypes.toValue(mfpExerciseEntry.getExercise().getType());
        return new Intent(context, AddExerciseEntry.class).putExtra(Extras.EXERCISE_TYPE, value).putExtra("source", value).putExtra("exercise_entry", mfpExerciseEntry);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        this.appIndexerClient = new AppIndexerClient(this, (ConfigService) this.configService.get(), getIntent(), bundle);
        this.exerciseEntry = (MfpExerciseEntry) BundleUtils.getParcelable(getIntent().getExtras(), "exercise_entry", MfpExerciseEntry.class.getClassLoader());
        MfpExerciseEntry mfpExerciseEntry = this.exerciseEntry;
        if (mfpExerciseEntry == null) {
            showErrorDialog();
            return;
        }
        mfpExerciseEntry.setLocalId(0);
        this.exerciseEntry.setMasterId(0);
        this.exerciseEntry.setId(null);
        this.exerciseEntry.setSource(null);
        this.exerciseEntry.setAppId(null);
        this.sourceExerciseType = ExtrasUtils.getInt(getIntent(), Extras.EXERCISE_TYPE, 0);
        int i = this.sourceExerciseType;
        if (i == 0) {
            initUiAsCardio();
        } else if (i == 1) {
            initUiAsStrength();
        }
        this.description = ((ExerciseStringService) this.exerciseStringService.get()).getDescriptionName(this.exerciseEntry.getExercise());
        setTitle(this.description);
        checkIsExerciseIndexable();
    }

    private void initUiAsCardio() {
        int i = 0;
        Ln.v(Report.CARDIO_EXERCISE, new Object[0]);
        Calendar instance = Calendar.getInstance();
        this.hourOfDay = instance.get(11);
        this.minute = instance.get(12);
        setContentView((int) R.layout.edit_cardio_exercise);
        UserProfile profile = getSession().getUser().getProfile();
        this.showStartTimeField = profile != null && profile.getIsLinkedWithFitbit();
        this.startTimeLayout = (LinearLayout) findViewById(R.id.startTimeLinearLayout);
        this.startTime = (EditText) findViewById(R.id.txtStartTime);
        MfpExerciseEntry mfpExerciseEntry = this.exerciseEntry;
        String startTimeString = mfpExerciseEntry != null ? mfpExerciseEntry.getStartTimeString() : "";
        String localeFormattedTime = Strings.notEmpty(startTimeString) ? DateTimeUtils.localeFormattedTime((Context) this, startTimeString) : "";
        if (this.showStartTimeField) {
            this.startTimeLayout.setVisibility(0);
            if (!MultiAddExerciseSelection.isActive() || Strings.isEmpty(localeFormattedTime)) {
                this.timeSetListener.onTimeSet(null, this.hourOfDay, 0);
            } else {
                this.startTime.setText(localeFormattedTime);
            }
        } else {
            this.startTimeLayout.setVisibility(8);
        }
        this.interval = (EditText) findViewById(R.id.editTxtExerciseInterval);
        this.interval.setHint(getString(R.string.hint_30));
        MfpExerciseEntry mfpExerciseEntry2 = this.exerciseEntry;
        if (mfpExerciseEntry2 != null) {
            i = mfpExerciseEntry2.getDuration() / 60;
        }
        this.interval.setText(i > 0 ? Strings.toString(Integer.valueOf(i)) : "");
        this.interval.requestFocus();
        this.cals = (EditText) findViewById(R.id.editTxtCaloriesBurned);
        EditText editText = this.cals;
        MfpExerciseEntry mfpExerciseEntry3 = this.exerciseEntry;
        editText.setText((mfpExerciseEntry3 == null || mfpExerciseEntry3.getEnergy() == null) ? "" : String.valueOf(((UserEnergyService) this.userEnergyService.get()).getEnergy(Energy.CALORIES, this.exerciseEntry.getEnergy())));
        EditText editText2 = this.cals;
        editText2.setSelection(editText2.getText().length());
        Energy userCurrentEnergyUnit = ((UserEnergyService) this.userEnergyService.get()).getUserCurrentEnergyUnit();
        ((TextView) findViewById(R.id.caloriesBurned)).setText(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.BURNED, this.userEnergyService.get()));
        this.cals = (EditText) findViewById(R.id.editTxtCaloriesBurned);
        MfpExerciseEntry mfpExerciseEntry4 = this.exerciseEntry;
        float energy = (mfpExerciseEntry4 == null || mfpExerciseEntry4.getEnergy() == null) ? BitmapDescriptorFactory.HUE_RED : ((UserEnergyService) this.userEnergyService.get()).getEnergy(Energy.CALORIES, this.exerciseEntry.getEnergy());
        this.cals.setText(energy > BitmapDescriptorFactory.HUE_RED ? ((UserEnergyService) this.userEnergyService.get()).getDisplayableEnergy(userCurrentEnergyUnit, (double) energy) : "");
        EditText editText3 = this.cals;
        editText3.setSelection(editText3.getText().length());
        initCardioEventListeners();
    }

    private void initUiAsStrength() {
        Ln.v(Report.STRENGTH_EXERCISE, new Object[0]);
        setContentView((int) R.layout.edit_strength_exercise);
        this.sets = (EditText) findViewById(R.id.editTxtSetCount);
        this.reps = (EditText) findViewById(R.id.editTxtRepetitionsPerSet);
        this.weight = (EditText) findViewById(R.id.editTxtWeightPerRepetition);
        TextView textView = (TextView) findViewById(R.id.calories_burned);
        this.caloriesBurnedFaq = (TextView) findViewById(R.id.calories_burned_faq);
        MfpExerciseEntry mfpExerciseEntry = this.exerciseEntry;
        if (mfpExerciseEntry != null) {
            this.sets.setText(mfpExerciseEntry.getSets() > 0 ? Strings.toString(Integer.valueOf(this.exerciseEntry.getSets())) : "");
            EditText editText = this.sets;
            editText.setSelection(editText.getText().length());
            this.reps.setText(this.exerciseEntry.getRepsPerSet() > 0 ? Strings.toString(Integer.valueOf(this.exerciseEntry.getRepsPerSet())) : "");
            EditText editText2 = this.reps;
            editText2.setSelection(editText2.getText().length());
            this.weight.setText((this.exerciseEntry.getWeightPerSet() == null || this.exerciseEntry.getWeightPerSet().getValue() <= BitmapDescriptorFactory.HUE_RED) ? "" : ((UserWeightService) this.userWeightService.get()).getDisplayableExerciseString(this.exerciseEntry.getWeightPerSet().getValue()));
            EditText editText3 = this.weight;
            editText3.setSelection(editText3.getText().length());
            this.sets.requestFocus();
        }
        textView.setText(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.BURNED, this.userEnergyService.get()));
        initStrengthEventListeners();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.appIndexerClient.saveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        this.appIndexerClient.end();
    }

    private void showErrorDialog() {
        AlertDialogFragment alertDialogFragment = (AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) new AlertDialogFragment().setTitle(R.string.app_name)).setMessage((int) R.string.failed_to_load_app_data)).setPositiveText(R.string.ok, new DialogPositiveListener<Object>() {
            public void onClick(Object obj) {
                AddExerciseEntry.this.finish();
            }
        });
        alertDialogFragment.setCancelable(false);
        showDialogFragment(alertDialogFragment, ERROR_DIALOG_TAG);
    }

    /* access modifiers changed from: protected */
    public Dialog onCreateDialog(int i) {
        if (i != 1) {
            return super.onCreateDialog(i);
        }
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, this.timeSetListener, this.hourOfDay, this.minute, DateFormat.is24HourFormat(this));
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

    private void initCardioEventListeners() {
        try {
            if (this.showStartTimeField) {
                this.startTimeLayout.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        try {
                            AddExerciseEntry.this.getImmHelper().hideSoftInput();
                            AddExerciseEntry.this.showDialog(1);
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
                                AddExerciseEntry.this.getImmHelper().hideSoftInput();
                                AddExerciseEntry.this.showDialog(1);
                                AddExerciseEntry.this.startTime.setSelection(AddExerciseEntry.this.startTime.getText().length());
                            } catch (Exception e) {
                                Ln.e(e);
                            }
                        }
                    }
                });
                this.startTime.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        try {
                            AddExerciseEntry.this.getImmHelper().hideSoftInput();
                            AddExerciseEntry.this.showDialog(1);
                            AddExerciseEntry.this.startTime.setSelection(AddExerciseEntry.this.startTime.getText().length());
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
                    AddExerciseEntry.this.updateCalories(NumberUtils.tryParseInt(editable.toString()));
                }
            });
            this.interval.setOnFocusChangeListener(new OnFocusChangeListener() {
                public void onFocusChange(View view, boolean z) {
                    if (z) {
                        AddExerciseEntry.this.interval.setSelection(AddExerciseEntry.this.interval.getText().length());
                    }
                }
            });
            this.minutes = (EditText) findViewById(R.id.editTxtExerciseInterval);
            this.minutes.setOnFocusChangeListener(new OnFocusChangeListener() {
                public void onFocusChange(View view, boolean z) {
                    if (z) {
                        AddExerciseEntry.this.minutes.setSelection(AddExerciseEntry.this.minutes.getText().length());
                    }
                }
            });
            this.cals.setOnFocusChangeListener(new OnFocusChangeListener() {
                public void onFocusChange(View view, boolean z) {
                    if (z) {
                        AddExerciseEntry.this.cals.setSelection(AddExerciseEntry.this.cals.getText().length());
                    }
                }
            });
            this.cals.setOnKeyListener(this.onCardioKeyListener);
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    /* access modifiers changed from: private */
    public void addCardio() {
        String trimmed = Strings.trimmed((Object) this.cals.getText());
        String trimmed2 = Strings.trimmed((Object) this.interval.getText());
        int minutesOfCardioExercises = (int) (1440.0f - (((DiaryService) this.diaryService.get()).getDiaryDayForActiveDateSync().minutesOfCardioExercises() + ((float) (MultiAddExerciseSelection.current() != null ? MultiAddExerciseSelection.current().getMinutesPerformedForSelectedExercises() : 0))));
        LegacyAlertMixin legacyAlertMixin = (LegacyAlertMixin) mixin(LegacyAlertMixin.class);
        if (Strings.isEmpty(trimmed2) || Strings.equalsIgnoreCase(trimmed2, "0")) {
            legacyAlertMixin.showAlertDialog(getString(R.string.alert_exercise_interval));
        } else if (Strings.isEmpty(trimmed) || Strings.equalsIgnoreCase(trimmed, "0")) {
            legacyAlertMixin.showAlertDialog(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.ALERT_EXERCISE, this.userEnergyService.get()));
        } else {
            int tryParseInt = NumberUtils.tryParseInt(trimmed2);
            if (tryParseInt > minutesOfCardioExercises) {
                legacyAlertMixin.showAlertDialog(String.format(getString(R.string.cardio_exercises_minutes_error), new Object[]{Integer.toString(minutesOfCardioExercises)}));
            } else if (!this.showStartTimeField || !Strings.isEmpty(this.exerciseEntry.getStartTimeString())) {
                try {
                    getImmHelper().hideSoftInput();
                    this.exerciseEntry.setEnergy(new MfpMeasuredValue("calories", ((UserEnergyService) this.userEnergyService.get()).getCalories(trimmed)));
                    this.exerciseEntry.setDuration(tryParseInt * 60);
                    this.exerciseEntry.setDate(getSession().getUser().getActiveDate());
                    if (MultiAddExerciseSelection.isActive()) {
                        MultiAddExerciseSelection.current().selectOrUpdateExerciseEntry(this.exerciseEntry);
                        setResult(0);
                        finish();
                        return;
                    }
                    ((DiaryService) this.diaryService.get()).endExerciseLoggingFlow("cardio", this.exerciseEntry.getDuration());
                    ((DiaryService) this.diaryService.get()).getDiaryDayForActiveDateSync().addExerciseEntry(this.exerciseEntry);
                    setResult(-1);
                    requestRecommendedApp();
                } catch (NumberFormatException e) {
                    showToast(getResources().getString(R.string.enter_numeric_value));
                    Ln.e(e);
                }
            } else {
                legacyAlertMixin.showAlertDialog(getString(R.string.alert_add_exercise_start_time));
            }
        }
    }

    private void initStrengthEventListeners() {
        this.weight.setOnKeyListener(this.onStrengthKeyListener);
        this.sets.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    AddExerciseEntry.this.sets.setSelection(AddExerciseEntry.this.sets.getText().length());
                }
            }
        });
        this.reps.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    AddExerciseEntry.this.reps.setSelection(AddExerciseEntry.this.reps.getText().length());
                }
            }
        });
        this.weight.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    AddExerciseEntry.this.weight.setSelection(AddExerciseEntry.this.weight.getText().length());
                }
            }
        });
        this.caloriesBurnedFaq.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                AddExerciseEntry addExerciseEntry = AddExerciseEntry.this;
                addExerciseEntry.showDialogFragment(StrengthCalorieAlertFragment.getStrengthCalorieAlertFragment(addExerciseEntry, addExerciseEntry.getNavigationHelper()), StrengthCalorieAlertFragment.STRENGTH_CALORIE_FRAGMENT);
            }
        });
    }

    /* access modifiers changed from: private */
    public void addStrength() {
        LegacyAlertMixin legacyAlertMixin = (LegacyAlertMixin) mixin(LegacyAlertMixin.class);
        String strings = Strings.toString(this.reps.getText(), "0");
        String strings2 = Strings.toString(this.sets.getText(), "0");
        String strings3 = Strings.toString(this.weight.getText(), "0");
        if (Strings.equals(strings2, "0") || Strings.isEmpty(strings2)) {
            legacyAlertMixin.showAlertDialog(getString(R.string.alert_add_exercise_sets));
        } else if (Strings.equals(strings, "0") || Strings.isEmpty(strings)) {
            legacyAlertMixin.showAlertDialog(getString(R.string.alert_add_exercise_repetitions));
        } else {
            try {
                int tryParseInt = NumberUtils.tryParseInt(strings);
                int tryParseInt2 = NumberUtils.tryParseInt(strings2);
                this.exerciseEntry.setDuration(LogSeverity.CRITICAL_VALUE);
                this.exerciseEntry.setRepsPerSet(tryParseInt);
                this.exerciseEntry.setEnergy(new MfpMeasuredValue("calories", BitmapDescriptorFactory.HUE_RED));
                this.exerciseEntry.setSets(tryParseInt2);
                this.exerciseEntry.setWeightPerSet(new MfpMeasuredValue(Unit.POUNDS, ((UserWeightService) this.userWeightService.get()).getExerciseWeightInPounds(strings3)));
                this.exerciseEntry.setDate(getSession().getUser().getActiveDate());
                getImmHelper().hideSoftInput();
                if (MultiAddExerciseSelection.isActive()) {
                    MultiAddExerciseSelection.current().selectOrUpdateExerciseEntry(this.exerciseEntry);
                    setResult(0);
                    finish();
                    return;
                }
                ((DiaryService) this.diaryService.get()).endExerciseLoggingFlow("weight", this.exerciseEntry.getDuration());
                ((DiaryService) this.diaryService.get()).getDiaryDayForActiveDateSync().addExerciseEntry(this.exerciseEntry);
                setResult(-1);
                requestRecommendedApp();
            } catch (NumberFormatException e) {
                showToast(getResources().getString(R.string.enter_numeric_value));
                Ln.e(e);
            } catch (Exception e2) {
                Ln.e(e2);
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateCalories(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("minutes: ");
        sb.append(i);
        Ln.v(sb.toString(), new Object[0]);
        if (this.exerciseEntry != null) {
            this.cals.setText(((UserEnergyService) this.userEnergyService.get()).getDisplayableEnergy((double) MfpExercise.cardioCaloriesBurnedForHours(this.exerciseEntry.getExercise(), getSession().getUser().getProfile().getCurrentWeight().getKilograms(), i)));
        }
    }

    private void showToast(String str) {
        Toast makeText = Toast.makeText(getApplicationContext(), str, 1);
        makeText.setGravity(16, 0, 0);
        makeText.show();
    }

    private void checkIsExerciseIndexable() {
        new QueryStockExerciseByMasterIdTask(this.exerciseEntry.getExercise().getMasterId(), (DbConnectionManager) this.dbConnectionManager.get(), DatasetManager.current((DbConnectionManager) this.dbConnectionManager.get())).run(getRunner());
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!super.onPrepareOptionsMenu(menu)) {
            return false;
        }
        MenuItemCompat.setShowAsAction(menu.add(0, SAVE_ACTION_ITEM, 0, getSaveButtonTextId()).setIcon(R.drawable.ic_check_white_24dp), 2);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != SAVE_ACTION_ITEM) {
            return super.onOptionsItemSelected(menuItem);
        }
        onSaveClick();
        return true;
    }

    private void onSaveClick() {
        if (this.sourceExerciseType == 0) {
            addCardio();
        } else {
            addStrength();
        }
        reportSaveClicked();
    }

    private void requestRecommendedApp() {
        if (ConfigUtils.isXPromoInterstitialEnabled((ConfigService) this.configService.get()) && !((XPromoSettings) this.xpromoSettings.get()).hasDontShowBeenSet()) {
            MfpExerciseEntry mfpExerciseEntry = this.exerciseEntry;
            if (mfpExerciseEntry != null) {
                MfpExercise exercise = mfpExerciseEntry.getExercise();
                if (exercise != null && exercise.getId() == null && exercise.getVersion() == null) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(new MfpCorrelationIdDetails(Strings.toString(Long.valueOf(exercise.getMasterId())), "exercise"));
                    ((IdService) this.idService.get()).getV2IdsFromV1IdsAsync(arrayList, new Function1<List<MfpCorrelationIdDetails>>() {
                        public void execute(List<MfpCorrelationIdDetails> list) {
                            AddExerciseEntry.this.setBusy(false);
                            if (CollectionUtils.size((Collection<?>) list) <= 0 || list.get(0) == null) {
                                AddExerciseEntry.this.finish();
                            } else {
                                new ExerciseRecommendationTask(Strings.toString(((MfpCorrelationIdDetails) list.get(0)).getId()), AddExerciseEntry.this.appGalleryService).run(AddExerciseEntry.this.getRunner());
                            }
                        }
                    }, new MfpV2ApiErrorCallback() {
                        public void execute(ApiResponseBase apiResponseBase) {
                            Ln.e("NEW OBFUSCATED_ID: error = %s", apiResponseBase.getErrorDescription());
                            AddExerciseEntry.this.setBusy(false);
                            AddExerciseEntry.this.finish();
                        }
                    });
                    return;
                }
                new ExerciseRecommendationTask(Strings.toString(this.exerciseEntry.getExercise().getId()), this.appGalleryService).run(getRunner());
                return;
            }
        }
        setBusy(false);
        finish();
    }

    private void reportSaveClicked() {
        if (Strings.toBoolean(((ActionTrackingService) this.actionTrackingService.get()).getTrackingDataForEvent("is_last_pressed_search", "is_last_pressed_search"))) {
            String uuid = UUID.randomUUID().toString();
            ((ActionTrackingService) this.actionTrackingService.get()).appendToEvent(Events.ONLINE_SEARCH_SUMMARY, MapUtil.createMap(Attributes.LOGGED, "yes", "flow_id", uuid));
        }
    }

    private int getSaveButtonTextId() {
        return MultiAddExerciseSelection.isActive() ? R.string.add_to_checked : R.string.add_nowBtn;
    }

    public AdUnit getAdUnit() {
        return getAdUnitService().getAddOrEditExerciseEntryScreenAdUnitValue();
    }

    private void checkShowXPromoInterstitial(ExerciseTrackingAppRecommendation exerciseTrackingAppRecommendation, MfpPlatformApp mfpPlatformApp) {
        if (!AppStateUtil.isConnected(mfpPlatformApp) && AppStateUtil.isConnectable(mfpPlatformApp)) {
            if (AppStateUtil.isInstallable(mfpPlatformApp) || AppStateUtil.isInstalled(this, mfpPlatformApp)) {
                getNavigationHelper().withIntent(XPromoInterstitialActivity.newStartIntent(this, exerciseTrackingAppRecommendation)).startActivity();
            }
        }
    }

    @Subscribe
    public void onExerciseTrackingAppRecommendationApiResponseEvent(CompletedEvent completedEvent) {
        if (CollectionUtils.notEmpty((Collection) completedEvent.getResult())) {
            ExerciseTrackingAppRecommendation exerciseTrackingAppRecommendation = (ExerciseTrackingAppRecommendation) ((List) completedEvent.getResult()).get(0);
            if (CollectionUtils.notEmpty((Collection<?>) exerciseTrackingAppRecommendation.getApps())) {
                checkShowXPromoInterstitial(exerciseTrackingAppRecommendation, (MfpPlatformApp) exerciseTrackingAppRecommendation.getApps().get(0));
            }
        }
        finish();
    }

    @Subscribe
    public void onCheckExerciseIsAppIndexable(QueryStockExerciseByMasterIdTask.CompletedEvent completedEvent) {
        if (completedEvent.successful() && completedEvent.getResult() != null) {
            this.appIndexerClient.start(AppIndexerUriUtil.getExerciseTitle(this.description, (LocalizedStringsUtil) this.localizedStringsUtil.get(), (UserEnergyService) this.userEnergyService.get()), AppIndexerUriUtil.exerciseToUri(this.exerciseEntry.getExercise(), Source.AutoComplete));
        }
    }
}
