package com.myfitnesspal.feature.settings.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class EmailSettingsListFragment_ViewBinding implements Unbinder {
    private EmailSettingsListFragment target;

    @UiThread
    public EmailSettingsListFragment_ViewBinding(EmailSettingsListFragment emailSettingsListFragment, View view) {
        this.target = emailSettingsListFragment;
        emailSettingsListFragment.emailText = (TextView) Utils.findRequiredViewAsType(view, R.id.email, "field 'emailText'", TextView.class);
        emailSettingsListFragment.emailVerifyParent = Utils.findRequiredView(view, R.id.email_verify_parent, "field 'emailVerifyParent'");
        emailSettingsListFragment.resendButton = (TextView) Utils.findRequiredViewAsType(view, R.id.resend, "field 'resendButton'", TextView.class);
        emailSettingsListFragment.updateButton = Utils.findRequiredView(view, R.id.update, "field 'updateButton'");
        emailSettingsListFragment.listView = (ListView) Utils.findRequiredViewAsType(view, R.id.list, "field 'listView'", ListView.class);
    }

    @CallSuper
    public void unbind() {
        EmailSettingsListFragment emailSettingsListFragment = this.target;
        if (emailSettingsListFragment != null) {
            this.target = null;
            emailSettingsListFragment.emailText = null;
            emailSettingsListFragment.emailVerifyParent = null;
            emailSettingsListFragment.resendButton = null;
            emailSettingsListFragment.updateButton = null;
            emailSettingsListFragment.listView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
