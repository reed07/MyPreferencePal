package com.myfitnesspal.feature.friends.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class ComposeMessageActivity_ViewBinding implements Unbinder {
    private ComposeMessageActivity target;

    @UiThread
    public ComposeMessageActivity_ViewBinding(ComposeMessageActivity composeMessageActivity) {
        this(composeMessageActivity, composeMessageActivity.getWindow().getDecorView());
    }

    @UiThread
    public ComposeMessageActivity_ViewBinding(ComposeMessageActivity composeMessageActivity, View view) {
        this.target = composeMessageActivity;
        composeMessageActivity.userNameEditText = (EditText) Utils.findRequiredViewAsType(view, R.id.user_name_input, "field 'userNameEditText'", EditText.class);
        composeMessageActivity.subjectEditText = (EditText) Utils.findRequiredViewAsType(view, R.id.subject_input, "field 'subjectEditText'", EditText.class);
        composeMessageActivity.messageEditText = (EditText) Utils.findRequiredViewAsType(view, R.id.message_input, "field 'messageEditText'", EditText.class);
        composeMessageActivity.previousMessageUserNameTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.previous_message_user_name_text, "field 'previousMessageUserNameTextView'", TextView.class);
        composeMessageActivity.previousMessageTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.previous_message_text, "field 'previousMessageTextView'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        ComposeMessageActivity composeMessageActivity = this.target;
        if (composeMessageActivity != null) {
            this.target = null;
            composeMessageActivity.userNameEditText = null;
            composeMessageActivity.subjectEditText = null;
            composeMessageActivity.messageEditText = null;
            composeMessageActivity.previousMessageUserNameTextView = null;
            composeMessageActivity.previousMessageTextView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
