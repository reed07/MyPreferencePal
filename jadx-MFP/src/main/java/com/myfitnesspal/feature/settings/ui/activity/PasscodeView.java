package com.myfitnesspal.feature.settings.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.settings.ui.view.PasscodeField;
import com.myfitnesspal.feature.settings.util.OnCodeCompleteListener;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.util.PincodeHelper;
import com.uacf.core.util.Ln;
import dagger.Lazy;
import javax.inject.Inject;

public class PasscodeView extends MfpActivity {
    static int attempts;
    OnCodeCompleteListener codeCompleteListener = new OnCodeCompleteListener() {
        public void validate(String str) {
            PincodeHelper.current().setPincodeSuccess(false);
            if (str.length() != 4) {
                PasscodeView.this.incorrectPasscode.setVisibility(8);
                PasscodeView.this.enterPasscode.setVisibility(0);
            } else if (str.equals(PincodeHelper.current().getPincode(PasscodeView.this.getSession(), (DbConnectionManager) PasscodeView.this.dbConnectionManager.get()))) {
                Ln.i("Switching to home view", new Object[0]);
                PincodeHelper.current().setPincodeSuccess(true);
                PasscodeView.this.finish();
                PasscodeView.attempts = 0;
            } else {
                PasscodeView.attempts++;
                PasscodeView.this.passcodeField.clearText();
                if (PasscodeView.attempts == 1) {
                    PasscodeView.this.incorrectPasscode.setVisibility(8);
                    PasscodeView.this.enterPasscode.setVisibility(0);
                    PasscodeView.this.enterPasscode.setText(R.string.re_enter_your_passcode);
                } else if (PasscodeView.attempts > 1 && PasscodeView.attempts < 5) {
                    PasscodeView.this.incorrectPasscode.setVisibility(0);
                    PasscodeView.this.enterPasscode.setVisibility(8);
                } else if (PasscodeView.attempts >= 5) {
                    PincodeHelper.current().updatePincode(null, PasscodeView.this.getSession(), (GlobalSettingsService) PasscodeView.this.globalSettingsService.get(), (DbConnectionManager) PasscodeView.this.dbConnectionManager.get());
                    PasscodeView.this.logoutUser();
                }
            }
        }
    };
    @Inject
    Lazy<DbConnectionManager> dbConnectionManager;
    TextView enterPasscode;
    @Inject
    Lazy<GlobalSettingsService> globalSettingsService;
    TextView incorrectPasscode;
    PasscodeField passcodeField;

    public boolean shouldShowTitle() {
        return false;
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, PasscodeView.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            setContentView((int) R.layout.passcode);
            component().inject(this);
            if (!PincodeHelper.current().hasEnteredApp() || !PincodeHelper.current().isPincodeSuccess()) {
                this.enterPasscode = (TextView) findViewById(R.id.txtEnterPasscode);
                this.incorrectPasscode = (TextView) findViewById(R.id.txtIncorrectPasscode);
                this.passcodeField = (PasscodeField) findViewById(R.id.passcodeField1);
                return;
            }
            Ln.w("Should not show passcode screen, exiting...", new Object[0]);
            finish();
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    /* access modifiers changed from: 0000 */
    public void addEventListeners() {
        this.passcodeField.setOnCodeCompleteListener(this.codeCompleteListener);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.passcodeField.focus();
        int i = attempts;
        if (i == 1) {
            this.incorrectPasscode.setVisibility(8);
            this.enterPasscode.setVisibility(0);
            this.enterPasscode.setText(R.string.re_enter_your_passcode);
        } else if (i > 1) {
            this.incorrectPasscode.setVisibility(0);
            this.enterPasscode.setVisibility(8);
        }
        addEventListeners();
    }

    /* access modifiers changed from: private */
    public void logoutUser() {
        getSession().logoutAndNavigateToLoginActivity();
        attempts = 0;
    }
}
