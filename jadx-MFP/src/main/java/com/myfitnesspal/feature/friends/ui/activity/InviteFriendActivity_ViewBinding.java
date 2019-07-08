package com.myfitnesspal.feature.friends.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class InviteFriendActivity_ViewBinding implements Unbinder {
    private InviteFriendActivity target;

    @UiThread
    public InviteFriendActivity_ViewBinding(InviteFriendActivity inviteFriendActivity) {
        this(inviteFriendActivity, inviteFriendActivity.getWindow().getDecorView());
    }

    @UiThread
    public InviteFriendActivity_ViewBinding(InviteFriendActivity inviteFriendActivity, View view) {
        this.target = inviteFriendActivity;
        inviteFriendActivity.btnGetContacts = Utils.findRequiredView(view, R.id.btnGetContacts, "field 'btnGetContacts'");
        inviteFriendActivity.editTxtUserName = (EditText) Utils.findRequiredViewAsType(view, R.id.editTxtUserName, "field 'editTxtUserName'", EditText.class);
        inviteFriendActivity.editTxtRecipients = (EditText) Utils.findRequiredViewAsType(view, R.id.editTxtRecipients, "field 'editTxtRecipients'", EditText.class);
        inviteFriendActivity.editTxtMessage = (EditText) Utils.findRequiredViewAsType(view, R.id.editTxtmessage, "field 'editTxtMessage'", EditText.class);
        inviteFriendActivity.txtFullNamePrompt = (TextView) Utils.findRequiredViewAsType(view, R.id.fullNamePrompt, "field 'txtFullNamePrompt'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        InviteFriendActivity inviteFriendActivity = this.target;
        if (inviteFriendActivity != null) {
            this.target = null;
            inviteFriendActivity.btnGetContacts = null;
            inviteFriendActivity.editTxtUserName = null;
            inviteFriendActivity.editTxtRecipients = null;
            inviteFriendActivity.editTxtMessage = null;
            inviteFriendActivity.txtFullNamePrompt = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
