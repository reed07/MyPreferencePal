package com.myfitnesspal.feature.registration.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.uacf.core.util.ViewUtils;

public class LoginSignInUpButtonsFragment extends MfpFragment {
    /* access modifiers changed from: private */
    public EventListener eventListener;

    public interface EventListener {
        void onSignInClicked();

        void onSignUpClicked();
    }

    public static LoginSignInUpButtonsFragment newInstance() {
        return new LoginSignInUpButtonsFragment();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.login_sign_in_up_buttons, viewGroup, false);
        Button button = (Button) ViewUtils.findById(inflate, R.id.btnNewAccount);
        ((Button) ViewUtils.findById(inflate, R.id.btnSignIn)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (LoginSignInUpButtonsFragment.this.eventListener != null) {
                    LoginSignInUpButtonsFragment.this.eventListener.onSignInClicked();
                }
            }
        });
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (LoginSignInUpButtonsFragment.this.eventListener != null) {
                    LoginSignInUpButtonsFragment.this.eventListener.onSignUpClicked();
                }
            }
        });
        return inflate;
    }

    public void setEventListener(EventListener eventListener2) {
        this.eventListener = eventListener2;
    }
}
