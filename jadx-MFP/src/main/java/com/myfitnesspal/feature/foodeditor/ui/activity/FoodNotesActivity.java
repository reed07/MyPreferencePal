package com.myfitnesspal.feature.foodeditor.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.foodeditor.task.SaveFoodNotesTask;
import com.myfitnesspal.feature.foodeditor.task.SaveFoodNotesTask.CompletedEvent;
import com.myfitnesspal.feature.meals.ui.view.MultiLineEditTextHint;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.service.foods.FoodService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import javax.inject.Inject;

public class FoodNotesActivity extends MfpActivity {
    public static final String EXTRA_NOTES = "extra_notes";
    public static final String EXTRA_TARGET_FOOD = "extra_target_food";
    @Inject
    Lazy<FoodService> foodService;
    @BindView(2131363081)
    MultiLineEditTextHint multiLineEditHint;
    private EditText notesEdit;
    private Food targetFood;

    public static class ConfirmDiscardDialog extends DialogFragment {
        public static final String TAG = "confirm_discard_dialog";

        public static ConfirmDiscardDialog newInstance() {
            return new ConfirmDiscardDialog();
        }

        @NonNull
        public Dialog onCreateDialog(Bundle bundle) {
            AlertDialog create = new MfpAlertDialogBuilder(getActivity()).setTitle((int) R.string.meal_sharing_notes_confirm_discard_title).setMessage((int) R.string.meal_sharing_notes_confirm_discard_message).setPositiveButton((int) R.string.save, (OnClickListener) new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ((FoodNotesActivity) ConfirmDiscardDialog.this.getActivity()).save();
                    dialogInterface.dismiss();
                }
            }).setNegativeButton((int) R.string.meal_sharing_button_discard, (OnClickListener) new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ConfirmDiscardDialog.this.getActivity().finish();
                    dialogInterface.dismiss();
                }
            }).create();
            create.setCancelable(false);
            return create;
        }
    }

    public static Intent newStartIntent(Context context, String str) {
        return new Intent(context, FoodNotesActivity.class).putExtra(EXTRA_NOTES, str);
    }

    public static Intent newStartIntentForSave(Context context, String str, @NonNull Food food) {
        return newStartIntent(context, str).putExtra(EXTRA_TARGET_FOOD, food);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        setContentView((int) R.layout.food_notes_activity);
        this.multiLineEditHint.setHint(getString(R.string.meal_sharing_notes_activity_hint));
        this.notesEdit = this.multiLineEditHint.getEditText();
        getToolbar().setNavigationIcon((int) R.drawable.ic_close_white_24dp);
        Bundle extras = getIntent().getExtras();
        this.targetFood = (Food) BundleUtils.getParcelable(extras, EXTRA_TARGET_FOOD, null, Food.class.getClassLoader());
        String string = BundleUtils.getString(extras, EXTRA_NOTES);
        this.notesEdit = this.multiLineEditHint.getEditText();
        this.notesEdit.setText(string);
        if (Strings.notEmpty(string)) {
            EditText editText = this.notesEdit;
            editText.setSelection(editText.getText().length());
        }
        setResult(0);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.food_notes_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_done) {
            save();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onBackPressed() {
        confirmFinishWithoutSave();
    }

    public void finish() {
        super.finish();
        getImmHelper().hideSoftInput();
    }

    @Subscribe
    public void onSaveNoteTaskCompleted(CompletedEvent completedEvent) {
        writeIntentAndFinish();
    }

    /* access modifiers changed from: private */
    public void save() {
        if (this.targetFood != null) {
            new SaveFoodNotesTask(this.foodService, this.notesEdit.getText().toString(), this.targetFood).run(getRunner());
        } else {
            writeIntentAndFinish();
        }
    }

    private void writeIntentAndFinish() {
        setResult(-1, new Intent().putExtra(EXTRA_NOTES, this.notesEdit.getText().toString()));
        finish();
    }

    private void confirmFinishWithoutSave() {
        if (!this.notesEdit.getText().toString().equals(BundleUtils.getString(getIntent().getExtras(), EXTRA_NOTES))) {
            showDialogFragment(ConfirmDiscardDialog.newInstance(), ConfirmDiscardDialog.TAG);
        } else {
            finish();
        }
    }
}
