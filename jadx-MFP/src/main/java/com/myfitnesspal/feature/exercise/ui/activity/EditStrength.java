package com.myfitnesspal.feature.exercise.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.exercise.ui.fragment.StrengthCalorieAlertFragment;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.LocalizedStrings;
import com.myfitnesspal.shared.model.AdUnit;
import com.myfitnesspal.shared.model.v1.ExerciseEntry;
import com.myfitnesspal.shared.service.ExerciseStringService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.util.Database;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.MapUtil;
import dagger.Lazy;
import javax.inject.Inject;

public class EditStrength extends MfpActivity {
    private static final int SAVE_CHANGES_ACTION = 9000;
    private static final String STRENGTH_ATTRIBUTE_VALUE = "strength";
    @BindView(2131362065)
    TextView caloriesBurned;
    @BindView(2131362066)
    TextView caloriesBurnedFaq;
    @Inject
    Lazy<DiaryService> diaryService;
    @Inject
    ExerciseStringService exerciseStringService;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    @BindView(2131362412)
    EditText reps;
    @BindView(2131362419)
    EditText sets;
    private ExerciseEntry updatedExerciseEntry;
    @Inject
    Lazy<UserEnergyService> userEnergyService;
    @Inject
    UserWeightService userWeightService;
    @BindView(2131362429)
    EditText weight;

    public static Intent newStartIntent(Context context) {
        return newStartIntent(context, null);
    }

    public static Intent newStartIntent(Context context, ExerciseEntry exerciseEntry) {
        return new Intent(context, EditStrength.class).putExtra("exercise_entry", exerciseEntry);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.edit_strength_exercise);
        component().inject(this);
        this.updatedExerciseEntry = (ExerciseEntry) ExtrasUtils.getParcelable(getIntent(), "exercise_entry", ExerciseEntry.class.getClassLoader());
        loadInfo();
        addEventListeners();
    }

    private void loadInfo() {
        try {
            setTitle(this.exerciseStringService.getDescriptionName(this.updatedExerciseEntry.getExercise()));
            this.sets.setText(String.valueOf(this.updatedExerciseEntry.getSets()));
            this.sets.setSelection(this.sets.getText().length());
            this.reps.setText(String.valueOf(this.updatedExerciseEntry.getQuantity()));
            this.reps.setSelection(this.reps.getText().length());
            this.weight.setText(this.userWeightService.getDisplayableExerciseString(this.updatedExerciseEntry.getWeight()));
            this.weight.setSelection(this.weight.getText().length());
            this.caloriesBurned.setText(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.BURNED, this.userEnergyService.get()));
        } catch (Exception e) {
            Ln.e(e);
            finish();
        }
    }

    private void addEventListeners() {
        this.sets.setOnFocusChangeListener(new OnFocusChangeListener() {
            public final void onFocusChange(View view, boolean z) {
                EditStrength.lambda$addEventListeners$0(EditStrength.this, view, z);
            }
        });
        this.reps.setOnFocusChangeListener(new OnFocusChangeListener() {
            public final void onFocusChange(View view, boolean z) {
                EditStrength.lambda$addEventListeners$1(EditStrength.this, view, z);
            }
        });
        this.weight.setOnFocusChangeListener(new OnFocusChangeListener() {
            public final void onFocusChange(View view, boolean z) {
                EditStrength.lambda$addEventListeners$2(EditStrength.this, view, z);
            }
        });
        this.caloriesBurnedFaq.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                EditStrength.this.showStrengthCalorieDialog();
            }
        });
    }

    public static /* synthetic */ void lambda$addEventListeners$0(EditStrength editStrength, View view, boolean z) {
        if (z) {
            EditText editText = editStrength.sets;
            editText.setSelection(editText.getText().length());
        }
    }

    public static /* synthetic */ void lambda$addEventListeners$1(EditStrength editStrength, View view, boolean z) {
        if (z) {
            EditText editText = editStrength.reps;
            editText.setSelection(editText.getText().length());
        }
    }

    public static /* synthetic */ void lambda$addEventListeners$2(EditStrength editStrength, View view, boolean z) {
        if (z) {
            EditText editText = editStrength.weight;
            editText.setSelection(editText.getText().length());
        }
    }

    /* access modifiers changed from: private */
    public void showStrengthCalorieDialog() {
        showDialogFragment(StrengthCalorieAlertFragment.getStrengthCalorieAlertFragment(this, getNavigationHelper()), StrengthCalorieAlertFragment.STRENGTH_CALORIE_FRAGMENT);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00af A[Catch:{ Exception -> 0x00d0 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void saveClick() {
        /*
            r5 = this;
            com.myfitnesspal.shared.util.InputMethodManagerHelper r0 = r5.getImmHelper()
            r0.hideSoftInput()
            android.widget.EditText r0 = r5.sets
            android.text.Editable r0 = r0.getText()
            java.lang.String r0 = com.uacf.core.util.Strings.toString(r0)
            android.widget.EditText r1 = r5.reps
            android.text.Editable r1 = r1.getText()
            java.lang.String r1 = com.uacf.core.util.Strings.toString(r1)
            android.widget.EditText r2 = r5.weight
            android.text.Editable r2 = r2.getText()
            java.lang.String r2 = com.uacf.core.util.Strings.toString(r2)
            boolean r3 = com.uacf.core.util.Strings.isEmpty(r2)
            if (r3 == 0) goto L_0x002d
            java.lang.String r2 = "0"
        L_0x002d:
            java.lang.String r3 = r0.trim()
            java.lang.String r4 = ""
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x00fe
            java.lang.String r3 = r0.trim()
            java.lang.String r4 = "0"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0047
            goto L_0x00fe
        L_0x0047:
            java.lang.String r3 = r1.trim()
            java.lang.String r4 = ""
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x00e7
            java.lang.String r3 = r1.trim()
            java.lang.String r4 = "0"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0061
            goto L_0x00e7
        L_0x0061:
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ Exception -> 0x00d0 }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ Exception -> 0x00d0 }
            com.myfitnesspal.shared.service.userdata.UserWeightService r3 = r5.userWeightService     // Catch:{ Exception -> 0x00d0 }
            float r2 = r3.getExerciseWeightInPounds(r2)     // Catch:{ Exception -> 0x00d0 }
            com.myfitnesspal.shared.model.v1.ExerciseEntry r3 = r5.updatedExerciseEntry     // Catch:{ Exception -> 0x00d0 }
            int r3 = r3.getSets()     // Catch:{ Exception -> 0x00d0 }
            if (r0 != r3) goto L_0x008c
            com.myfitnesspal.shared.model.v1.ExerciseEntry r3 = r5.updatedExerciseEntry     // Catch:{ Exception -> 0x00d0 }
            int r3 = r3.getQuantity()     // Catch:{ Exception -> 0x00d0 }
            if (r1 != r3) goto L_0x008c
            com.myfitnesspal.shared.model.v1.ExerciseEntry r3 = r5.updatedExerciseEntry     // Catch:{ Exception -> 0x00d0 }
            float r3 = r3.getWeight()     // Catch:{ Exception -> 0x00d0 }
            int r3 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r3 == 0) goto L_0x008a
            goto L_0x008c
        L_0x008a:
            r3 = 0
            goto L_0x008d
        L_0x008c:
            r3 = 1
        L_0x008d:
            com.myfitnesspal.shared.model.v1.ExerciseEntry r4 = r5.updatedExerciseEntry     // Catch:{ Exception -> 0x00d0 }
            r4.setSets(r0)     // Catch:{ Exception -> 0x00d0 }
            com.myfitnesspal.shared.model.v1.ExerciseEntry r0 = r5.updatedExerciseEntry     // Catch:{ Exception -> 0x00d0 }
            r0.setQuantity(r1)     // Catch:{ Exception -> 0x00d0 }
            com.myfitnesspal.shared.model.v1.ExerciseEntry r0 = r5.updatedExerciseEntry     // Catch:{ Exception -> 0x00d0 }
            r0.setWeight(r2)     // Catch:{ Exception -> 0x00d0 }
            com.myfitnesspal.shared.model.v1.ExerciseEntry r0 = r5.updatedExerciseEntry     // Catch:{ Exception -> 0x00d0 }
            com.myfitnesspal.shared.service.session.Session r1 = r5.getSession()     // Catch:{ Exception -> 0x00d0 }
            com.myfitnesspal.shared.model.User r1 = r1.getUser()     // Catch:{ Exception -> 0x00d0 }
            java.util.Date r1 = r1.getActiveDate()     // Catch:{ Exception -> 0x00d0 }
            r0.setDate(r1)     // Catch:{ Exception -> 0x00d0 }
            if (r3 == 0) goto L_0x00b2
            r5.reportUserEditedExerciseEvent()     // Catch:{ Exception -> 0x00d0 }
        L_0x00b2:
            dagger.Lazy<com.myfitnesspal.feature.diary.service.DiaryService> r0 = r5.diaryService     // Catch:{ Exception -> 0x00d0 }
            java.lang.Object r0 = r0.get()     // Catch:{ Exception -> 0x00d0 }
            com.myfitnesspal.feature.diary.service.DiaryService r0 = (com.myfitnesspal.feature.diary.service.DiaryService) r0     // Catch:{ Exception -> 0x00d0 }
            com.myfitnesspal.shared.model.v1.DiaryDay r0 = r0.getDiaryDayForActiveDateSync()     // Catch:{ Exception -> 0x00d0 }
            com.myfitnesspal.shared.model.v1.ExerciseEntry r1 = r5.updatedExerciseEntry     // Catch:{ Exception -> 0x00d0 }
            r0.updateExerciseEntry(r1)     // Catch:{ Exception -> 0x00d0 }
            android.content.Intent r0 = new android.content.Intent     // Catch:{ Exception -> 0x00d0 }
            r0.<init>()     // Catch:{ Exception -> 0x00d0 }
            r1 = -1
            r5.setResult(r1, r0)     // Catch:{ Exception -> 0x00d0 }
            r5.finish()     // Catch:{ Exception -> 0x00d0 }
            goto L_0x0114
        L_0x00d0:
            r0 = move-exception
            java.lang.Class<com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin> r1 = com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin.class
            com.myfitnesspal.framework.mixin.RunnerLifecycleMixin r1 = r5.mixin(r1)
            com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin r1 = (com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin) r1
            r2 = 2131887158(0x7f120436, float:1.9408915E38)
            java.lang.String r2 = r5.getString(r2)
            r1.showAlertDialog(r2)
            com.uacf.core.util.Ln.e(r0)
            goto L_0x0114
        L_0x00e7:
            java.lang.Class<com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin> r0 = com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin.class
            com.myfitnesspal.framework.mixin.RunnerLifecycleMixin r0 = r5.mixin(r0)
            com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin r0 = (com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin) r0
            android.content.Context r1 = r5.getApplicationContext()
            r2 = 2131886332(0x7f1200fc, float:1.940724E38)
            java.lang.String r1 = r1.getString(r2)
            r0.showAlertDialog(r1)
            goto L_0x0114
        L_0x00fe:
            java.lang.Class<com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin> r0 = com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin.class
            com.myfitnesspal.framework.mixin.RunnerLifecycleMixin r0 = r5.mixin(r0)
            com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin r0 = (com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin) r0
            android.content.Context r1 = r5.getApplicationContext()
            r2 = 2131886333(0x7f1200fd, float:1.9407242E38)
            java.lang.String r1 = r1.getString(r2)
            r0.showAlertDialog(r1)
        L_0x0114:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.exercise.ui.activity.EditStrength.saveClick():void");
    }

    private void reportUserEditedExerciseEvent() {
        getAnalyticsService().reportEvent(Events.USER_EDITED_EXERCISE_ENTRY, MapUtil.createMap("exercise_type", "strength", Attributes.EXERCISE_SOURCE, this.updatedExerciseEntry.extraPropertyNamed("source"), Attributes.EXERCISE_DATE, Database.encodeDate(this.updatedExerciseEntry.getDate())));
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!super.onPrepareOptionsMenu(menu)) {
            return false;
        }
        addProminentActionItem(menu, R.string.save, new OnClickListener() {
            public final void onClick(View view) {
                EditStrength.this.saveClick();
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
