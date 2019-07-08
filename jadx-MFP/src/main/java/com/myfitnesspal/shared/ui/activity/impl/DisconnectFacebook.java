package com.myfitnesspal.shared.ui.activity.impl;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.service.facebook.FacebookService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Function2;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import javax.inject.Inject;

public class DisconnectFacebook extends MfpActivity {
    @BindView(2131362014)
    Button btnContinue;
    @BindView(2131362194)
    EditText confirmField;
    @BindView(2131364056)
    TextView errorText;
    @Inject
    Lazy<FacebookService> facebookService;
    @BindView(2131363248)
    EditText passwordField;
    @BindView(2131363768)
    ViewSwitcher switcher;

    public static Intent newStartIntent(Context context) {
        return new Intent(context, DisconnectFacebook.class);
    }

    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.disconnect_facebook);
        setTitle(R.string.disconnect_facebook);
        this.passwordField.setTypeface(Typeface.DEFAULT);
        this.confirmField.setTypeface(Typeface.DEFAULT);
        if (getCurrentFocus() == this.passwordField) {
            getImmHelper().showSoftInput();
        }
        this.switcher.setDisplayedChild(0);
        this.btnContinue.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                String strings = Strings.toString(DisconnectFacebook.this.passwordField.getText());
                if (!Strings.equals(strings, Strings.toString(DisconnectFacebook.this.confirmField.getText()))) {
                    ((LegacyAlertMixin) DisconnectFacebook.this.mixin(LegacyAlertMixin.class)).showAlertDialog(DisconnectFacebook.this.getString(R.string.passwords_dont_match));
                } else if (Strings.isEmpty(strings)) {
                    ((LegacyAlertMixin) DisconnectFacebook.this.mixin(LegacyAlertMixin.class)).showAlertDialog(DisconnectFacebook.this.getString(R.string.please_enter_password));
                } else {
                    DisconnectFacebook.this.getImmHelper().hideSoftInput();
                    DisconnectFacebook.this.switcher.setDisplayedChild(1);
                    ((FacebookService) DisconnectFacebook.this.facebookService.get()).changePasswordAndDisconnect(strings, new Function0() {
                        public void execute() {
                            ViewUtils.setVisible(false, DisconnectFacebook.this.errorText);
                            DisconnectFacebook.this.finish();
                        }
                    }, new Function2<Integer, String>() {
                        public void execute(Integer num, String str) {
                            ViewUtils.setVisible(num.intValue() > 0, 8, DisconnectFacebook.this.errorText);
                            TextView textView = DisconnectFacebook.this.errorText;
                            if (!Strings.notEmpty(str)) {
                                str = DisconnectFacebook.this.getString(R.string.change_password_generic_error);
                            }
                            textView.setText(str);
                            DisconnectFacebook.this.switcher.setDisplayedChild(0);
                        }
                    });
                }
            }
        });
    }
}
