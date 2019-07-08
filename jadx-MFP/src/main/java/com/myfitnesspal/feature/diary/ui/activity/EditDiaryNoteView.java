package com.myfitnesspal.feature.diary.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.AdUnit;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v1.DiaryNote;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.sync.engine.UacfScheduler;
import dagger.Lazy;
import javax.inject.Inject;

public class EditDiaryNoteView extends MfpActivity {
    private static final int SAVE_MENU_ITEM = 1001;
    public static DiaryNote diaryNote;
    @Inject
    Lazy<DbConnectionManager> dbConnectionManager;
    @Inject
    Lazy<DiaryService> diaryService;
    private boolean editable = true;
    @BindView(2131363167)
    EditText noteBodyEditTxtView;
    @BindView(2131363169)
    TextView noteText;
    @Inject
    Lazy<UacfScheduler<SyncType>> syncScheduler;

    public static Intent newStartIntent(Context context) {
        return new Intent(context, EditDiaryNoteView.class);
    }

    public static void setDiaryNote(DiaryNote diaryNote2) {
        diaryNote = diaryNote2;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            setContentView((int) R.layout.edit_diary_note);
            component().inject(this);
            Intent intent = getIntent();
            setTitle(ExtrasUtils.getString(intent, Extras.TITLE_FOR_NOTE, ""));
            int i = 1;
            this.editable = ExtrasUtils.getBoolean(intent, Extras.EDITABLE, true);
            String strings = Strings.toString(diaryNote.getBody());
            this.noteText.setText(strings);
            this.noteBodyEditTxtView.setText(strings);
            this.noteBodyEditTxtView.setSelection(this.noteBodyEditTxtView.length());
            ViewSwitcher viewSwitcher = (ViewSwitcher) findById(R.id.switcher);
            if (this.editable) {
                i = 0;
            }
            viewSwitcher.setDisplayedChild(i);
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        getImmHelper().hideSoftInput();
    }

    private void saveNote() {
        try {
            String strings = Strings.toString(this.noteBodyEditTxtView.getText());
            if (Strings.isEmpty(strings.trim())) {
                ((LegacyAlertMixin) mixin(LegacyAlertMixin.class)).showAlertDialog(getString(R.string.empty_note_alert));
                return;
            }
            diaryNote.setMasterDatabaseId(0);
            diaryNote.setBody(strings.trim());
            ((DbConnectionManager) this.dbConnectionManager.get()).diaryNoteDbAdapter().insertOrUpdateDiaryNote(diaryNote);
            DiaryDay diaryDayForActiveDateSync = ((DiaryService) this.diaryService.get()).getDiaryDayForActiveDateSync();
            diaryDayForActiveDateSync.loadNotes();
            switch (diaryNote.getNoteType()) {
                case 0:
                    diaryDayForActiveDateSync.setJustAddedFoodNote(true);
                    break;
                case 1:
                    diaryDayForActiveDateSync.setJustAddedExerciseNote(true);
                    break;
            }
            getImmHelper().hideSoftInput();
            ((UacfScheduler) this.syncScheduler.get()).schedulePeriodicSyncIfNecessary();
            setResult(-1);
            finish();
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!super.onPrepareOptionsMenu(menu)) {
            return false;
        }
        if (this.editable) {
            MenuItemCompat.setShowAsAction(menu.add(0, 1001, 0, R.string.save).setIcon(R.drawable.ic_check_white_24dp), 2);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 1001) {
            return super.onOptionsItemSelected(menuItem);
        }
        saveNote();
        return true;
    }

    public AdUnit getAdUnit() {
        return getAdUnitService().getDiaryNoteScreenAdUnitValue();
    }
}
