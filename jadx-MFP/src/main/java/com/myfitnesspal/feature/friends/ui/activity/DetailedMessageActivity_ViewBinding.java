package com.myfitnesspal.feature.friends.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.view.MfpImageView;

public class DetailedMessageActivity_ViewBinding implements Unbinder {
    private DetailedMessageActivity target;

    @UiThread
    public DetailedMessageActivity_ViewBinding(DetailedMessageActivity detailedMessageActivity) {
        this(detailedMessageActivity, detailedMessageActivity.getWindow().getDecorView());
    }

    @UiThread
    public DetailedMessageActivity_ViewBinding(DetailedMessageActivity detailedMessageActivity, View view) {
        this.target = detailedMessageActivity;
        detailedMessageActivity.avatarImageView = (MfpImageView) Utils.findRequiredViewAsType(view, R.id.avatar_image, "field 'avatarImageView'", MfpImageView.class);
        detailedMessageActivity.subjectTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.subject_text, "field 'subjectTextView'", TextView.class);
        detailedMessageActivity.userNameTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.user_name_text, "field 'userNameTextView'", TextView.class);
        detailedMessageActivity.dateTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.date_text, "field 'dateTextView'", TextView.class);
        detailedMessageActivity.messageTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.message_text, "field 'messageTextView'", TextView.class);
        detailedMessageActivity.replyButtonView = Utils.findRequiredView(view, R.id.reply_button, "field 'replyButtonView'");
        detailedMessageActivity.loadingView = Utils.findRequiredView(view, R.id.loading_view, "field 'loadingView'");
    }

    @CallSuper
    public void unbind() {
        DetailedMessageActivity detailedMessageActivity = this.target;
        if (detailedMessageActivity != null) {
            this.target = null;
            detailedMessageActivity.avatarImageView = null;
            detailedMessageActivity.subjectTextView = null;
            detailedMessageActivity.userNameTextView = null;
            detailedMessageActivity.dateTextView = null;
            detailedMessageActivity.messageTextView = null;
            detailedMessageActivity.replyButtonView = null;
            detailedMessageActivity.loadingView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
