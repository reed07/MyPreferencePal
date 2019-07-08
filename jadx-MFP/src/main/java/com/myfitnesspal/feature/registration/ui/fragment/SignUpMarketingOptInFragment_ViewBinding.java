package com.myfitnesspal.feature.registration.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class SignUpMarketingOptInFragment_ViewBinding implements Unbinder {
    private SignUpMarketingOptInFragment target;

    @UiThread
    public SignUpMarketingOptInFragment_ViewBinding(SignUpMarketingOptInFragment signUpMarketingOptInFragment, View view) {
        this.target = signUpMarketingOptInFragment;
        signUpMarketingOptInFragment.sendMeEmails = (Button) Utils.findRequiredViewAsType(view, R.id.send_me_emails, "field 'sendMeEmails'", Button.class);
        signUpMarketingOptInFragment.skip = (TextView) Utils.findRequiredViewAsType(view, R.id.skip, "field 'skip'", TextView.class);
        signUpMarketingOptInFragment.disclaimerCanada = (TextView) Utils.findRequiredViewAsType(view, R.id.disclaimer_canada, "field 'disclaimerCanada'", TextView.class);
        signUpMarketingOptInFragment.withdrawCanada = (TextView) Utils.findRequiredViewAsType(view, R.id.withdraw_canada, "field 'withdrawCanada'", TextView.class);
        signUpMarketingOptInFragment.uaAddress = (TextView) Utils.findRequiredViewAsType(view, R.id.ua_address, "field 'uaAddress'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        SignUpMarketingOptInFragment signUpMarketingOptInFragment = this.target;
        if (signUpMarketingOptInFragment != null) {
            this.target = null;
            signUpMarketingOptInFragment.sendMeEmails = null;
            signUpMarketingOptInFragment.skip = null;
            signUpMarketingOptInFragment.disclaimerCanada = null;
            signUpMarketingOptInFragment.withdrawCanada = null;
            signUpMarketingOptInFragment.uaAddress = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
