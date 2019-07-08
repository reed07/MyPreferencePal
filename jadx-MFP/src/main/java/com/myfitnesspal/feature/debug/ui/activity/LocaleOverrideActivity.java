package com.myfitnesspal.feature.debug.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.util.Toaster;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import javax.inject.Inject;

public class LocaleOverrideActivity extends MfpActivity {
    private EditText editField;
    @Inject
    Lazy<GlobalSettingsService> settings;

    public static Intent newStartIntent(Activity activity) {
        return new Intent(activity, LocaleOverrideActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        setContentView((int) R.layout.locale_override_activity);
        this.editField = (EditText) findViewById(R.id.locale_edit);
        this.editField.setText(((GlobalSettingsService) this.settings.get()).getRequestLocaleOverride());
        findViewById(R.id.locale_button).setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                LocaleOverrideActivity.lambda$onCreate$0(LocaleOverrideActivity.this, view);
            }
        });
    }

    public static /* synthetic */ void lambda$onCreate$0(LocaleOverrideActivity localeOverrideActivity, View view) {
        String obj = localeOverrideActivity.editField.getText().toString();
        if (!Strings.notEmpty(obj) || localeOverrideActivity.isValid(obj)) {
            if (Strings.notEmpty(obj)) {
                obj = localeOverrideActivity.sanitize(obj);
            }
            ((GlobalSettingsService) localeOverrideActivity.settings.get()).setRequestLocaleOverride(obj);
            localeOverrideActivity.editField.setText(((GlobalSettingsService) localeOverrideActivity.settings.get()).getRequestLocaleOverride());
            localeOverrideActivity.showSuccess();
            return;
        }
        localeOverrideActivity.showError();
    }

    private boolean isValid(String str) {
        return Strings.notEmpty(str) && str.length() == 5 && str.charAt(2) == '_';
    }

    private String sanitize(String str) {
        String[] split = str.split("_");
        StringBuilder sb = new StringBuilder();
        sb.append(split[0].toLowerCase());
        sb.append("_");
        sb.append(split[1].toUpperCase());
        return sb.toString();
    }

    private void showError() {
        Toaster.showLong((Activity) this, "Invalid locale specified.");
    }

    private void showSuccess() {
        StringBuilder sb = new StringBuilder();
        sb.append("Locale override set to '");
        sb.append(((GlobalSettingsService) this.settings.get()).getRequestLocaleOverride());
        sb.append("'. PLEASE KILL AND RESTART THE APP!");
        Toaster.showLong((Activity) this, sb.toString());
    }
}
