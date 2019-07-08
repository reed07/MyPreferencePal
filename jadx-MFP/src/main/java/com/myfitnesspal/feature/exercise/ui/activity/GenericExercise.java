package com.myfitnesspal.feature.exercise.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import butterknife.BindView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.exercise.service.ExerciseService;
import com.myfitnesspal.shared.api.exception.DuplicateResourceException;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.Premium;
import com.myfitnesspal.shared.event.AlertEvent;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.v1.Exercise;
import com.myfitnesspal.shared.model.v1.ExerciseEntry;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import javax.inject.Inject;

public abstract class GenericExercise extends MfpActivity {
    protected static final int ACTION_SAVE = 990;
    protected OnKeyListener addKeyListener = new OnKeyListener() {
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (i != 66 || keyEvent.getAction() != 0) {
                return false;
            }
            GenericExercise.this.onSave();
            return true;
        }
    };
    private boolean addToDiaryAfterCreate;
    protected float caloriesBurned;
    protected ExerciseEntry createdExerciseEntry;
    protected String description;
    @BindView(2131362297)
    protected EditText descriptionView;
    @Inject
    Lazy<DiaryService> diaryService;
    protected int duration;
    @Inject
    Lazy<ExerciseService> exerciseService;
    protected Exercise exerciseToEdit;
    protected int exerciseType;
    protected int repetitions;
    protected int sets;
    protected float weight;

    /* access modifiers changed from: protected */
    public abstract void onSave();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        this.addToDiaryAfterCreate = ExtrasUtils.getBoolean(getIntent(), Extras.ADD_TO_DIARY_AFTER_CREATE);
        this.createdExerciseEntry = new ExerciseEntry();
        this.exerciseToEdit = (Exercise) ExtrasUtils.getParcelable(getIntent(), "exercise", Exercise.class.getClassLoader());
        Exercise exercise = this.exerciseToEdit;
        if (exercise != null) {
            this.exerciseType = exercise.getExerciseType();
        }
    }

    public void onSetContentView() {
        super.onSetContentView();
        EditText editText = this.descriptionView;
        Exercise exercise = this.exerciseToEdit;
        editText.setText(exercise != null ? exercise.getDescription() : "");
        EditText editText2 = this.descriptionView;
        editText2.setSelection(Strings.length((Object) editText2.getText()));
    }

    /* access modifiers changed from: protected */
    public void buildCreatedExerciseAndEntry() {
        User user = getSession().getUser();
        buildCreatedExercise(user);
        this.createdExerciseEntry.setExercise(this.exerciseToEdit);
        this.createdExerciseEntry.setDate(user.getActiveDate());
        if (this.exerciseType == 0) {
            this.createdExerciseEntry.setQuantity(NumberUtils.clamp(this.duration, 0, (int) Premium.MACROS_MAXIMUM));
            this.createdExerciseEntry.setCalories(NumberUtils.clamp(this.caloriesBurned, (float) BitmapDescriptorFactory.HUE_RED, 9999.0f));
            this.createdExerciseEntry.setSets(0);
            this.createdExerciseEntry.setWeight(BitmapDescriptorFactory.HUE_RED);
        } else {
            this.createdExerciseEntry.setQuantity(NumberUtils.clamp(this.repetitions, 0, (int) Premium.MACROS_MAXIMUM));
            this.createdExerciseEntry.setCalories(BitmapDescriptorFactory.HUE_RED);
            this.createdExerciseEntry.setSets(NumberUtils.clamp(this.sets, 0, (int) Premium.MACROS_MAXIMUM));
            this.createdExerciseEntry.setWeight(NumberUtils.clamp(this.weight, (float) BitmapDescriptorFactory.HUE_RED, 9999.0f));
        }
        this.exerciseToEdit.setMets(this.createdExerciseEntry.calculateMetsForUser(getSession().getUser()));
    }

    private void buildCreatedExercise(User user) {
        if (this.exerciseToEdit == null) {
            this.exerciseToEdit = new Exercise();
        }
        this.exerciseToEdit.setExerciseType(this.exerciseType);
        this.exerciseToEdit.setDescription(this.description);
        this.exerciseToEdit.setOwnerUserId(user.getLocalId());
        this.exerciseToEdit.setOwnerUserMasterId(user.getMasterDatabaseId());
    }

    /* access modifiers changed from: protected */
    public void updateCustomExercise() {
        buildCreatedExerciseAndEntry();
        ((ExerciseService) this.exerciseService.get()).insertOrUpdateV1ExerciseLocallyForSync(this.exerciseToEdit);
        scheduleSync();
        setResult(-1, new Intent());
        finish();
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        MenuItemCompat.setShowAsAction(menu.add(0, ACTION_SAVE, 0, R.string.save), 2);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != ACTION_SAVE) {
            return super.onOptionsItemSelected(menuItem);
        }
        onSave();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onSaveCompleted() {
        buildCreatedExerciseAndEntry();
        Exercise exercise = this.createdExerciseEntry.getExercise();
        try {
            Exercise createNewV1ExerciseLocallyIfMissing = ((ExerciseService) this.exerciseService.get()).createNewV1ExerciseLocallyIfMissing(exercise);
            scheduleSync();
            this.createdExerciseEntry.setExercise(createNewV1ExerciseLocallyIfMissing);
            if (this.addToDiaryAfterCreate) {
                ((DiaryService) this.diaryService.get()).getDiaryDayForActiveDateSync().addExerciseEntry(this.createdExerciseEntry);
            }
            setResult(-1);
            finish();
        } catch (DuplicateResourceException e) {
            Ln.e(e);
            postEvent(new AlertEvent(getString(R.string.exercise_already_exists, new Object[]{exercise.getDescription()})));
        }
    }
}
