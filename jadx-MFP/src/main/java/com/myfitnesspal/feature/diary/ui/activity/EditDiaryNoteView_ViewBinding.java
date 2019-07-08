package com.myfitnesspal.feature.diary.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class EditDiaryNoteView_ViewBinding implements Unbinder {
    private EditDiaryNoteView target;

    @UiThread
    public EditDiaryNoteView_ViewBinding(EditDiaryNoteView editDiaryNoteView) {
        this(editDiaryNoteView, editDiaryNoteView.getWindow().getDecorView());
    }

    @UiThread
    public EditDiaryNoteView_ViewBinding(EditDiaryNoteView editDiaryNoteView, View view) {
        this.target = editDiaryNoteView;
        editDiaryNoteView.noteBodyEditTxtView = (EditText) Utils.findRequiredViewAsType(view, R.id.noteBodyEditTxtView, "field 'noteBodyEditTxtView'", EditText.class);
        editDiaryNoteView.noteText = (TextView) Utils.findRequiredViewAsType(view, R.id.noteText, "field 'noteText'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        EditDiaryNoteView editDiaryNoteView = this.target;
        if (editDiaryNoteView != null) {
            this.target = null;
            editDiaryNoteView.noteBodyEditTxtView = null;
            editDiaryNoteView.noteText = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
