package com.myfitnesspal.feature.registration.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.api.auth.SSO;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.SharedConstants.Http;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogPositiveListener;
import com.myfitnesspal.shared.ui.dialog.impl.ProgressDialogFragment;
import com.myfitnesspal.shared.ui.navigation.SharedIntents;
import com.myfitnesspal.shared.util.ApiUtil;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import com.uacf.core.util.VersionUtils;
import com.uacf.taskrunner.Runner.CacheMode;
import com.uacf.taskrunner.Runner.DedupeMode;
import dagger.Lazy;
import io.uacf.core.api.UacfApiException;
import javax.inject.Inject;

public class ForgotPasswordActivity extends MfpActivity {
    private static final String ALERT_DIALOG_FRAGMENT = "alert_dialog_fragment";
    private static final String EVENT_EMAIL_NOT_RECOGNIZED = "email_not_recognized_faq_click";
    private static final String EXTRA_DIALOG_CLOSE_ON_OK = "close_on_ok";
    private static final String KEY_RESULT = "result";
    private static final String PROGRESS_DIALOG_FRAGMENT = "progress_dialog_fragment";
    private static final boolean SSO_PASSWORD_RESET_AVAILABLE = false;
    private static final String SUCCESSFUL_RESPONSE = "ok";
    private static final String VALUE_FIELD_EMPTY_OR_INVALID = "field_empty_or_invalid";
    private static final String VALUE_SUCCESSFUL = "successful";
    private static final String VALUE_UNKNOWN_EMAIL = "unknown_email_error";
    @Inject
    Lazy<ApiUrlProvider> apiUrlProvider;
    @Inject
    Lazy<AppSettings> appSettings;
    private Button done;
    private OnKeyListener goNextKeyListener = new OnKeyListener() {
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (i != 66 || keyEvent.getAction() != 0) {
                return false;
            }
            ForgotPasswordActivity.this.submitForgotPasswordRequest();
            return true;
        }
    };
    private EditText name;
    private final DialogPositiveListener onCloseOnOkClickListener = new DialogPositiveListener() {
        public void onClick(Object obj) {
            ForgotPasswordActivity.this.setResult(-1);
            ForgotPasswordActivity.this.finish();
        }
    };

    private static class SendPasswordResetTask extends EventedTaskBase<String, ApiException> {
        private ApiUrlProvider urlProvider;
        private String username;

        public static class CompletedEvent extends TaskEventBase<String, ApiException> {
        }

        public SendPasswordResetTask(String str, ApiUrlProvider apiUrlProvider) {
            super((TaskEventBase) new CompletedEvent());
            this.username = str;
            this.urlProvider = apiUrlProvider;
        }

        /* access modifiers changed from: protected */
        public String exec(Context context) throws ApiException {
            return resetLegacy();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0067, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0072, code lost:
            throw new com.myfitnesspal.shared.api.ApiException("", 999);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x007b, code lost:
            r0.body().close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0082, code lost:
            throw r1;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0069 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private java.lang.String resetLegacy() throws com.myfitnesspal.shared.api.ApiException {
            /*
                r5 = this;
                r0 = 0
                com.myfitnesspal.shared.api.ApiUrlProvider r1 = r5.urlProvider     // Catch:{ IOException -> 0x0069 }
                java.lang.String r2 = r5.username     // Catch:{ IOException -> 0x0069 }
                java.lang.String r1 = r1.getForgotPasswordUrl(r2)     // Catch:{ IOException -> 0x0069 }
                java.lang.String r2 = "%40"
                java.lang.String r3 = "@"
                java.lang.String r1 = r1.replace(r2, r3)     // Catch:{ IOException -> 0x0069 }
                okhttp3.Request$Builder r2 = new okhttp3.Request$Builder     // Catch:{ IOException -> 0x0069 }
                r2.<init>()     // Catch:{ IOException -> 0x0069 }
                java.lang.String r3 = "POST"
                java.lang.String r4 = ""
                okhttp3.RequestBody r4 = okhttp3.RequestBody.create(r0, r4)     // Catch:{ IOException -> 0x0069 }
                okhttp3.Request$Builder r2 = r2.method(r3, r4)     // Catch:{ IOException -> 0x0069 }
                okhttp3.Request$Builder r1 = r2.url(r1)     // Catch:{ IOException -> 0x0069 }
                okhttp3.Request r1 = r1.build()     // Catch:{ IOException -> 0x0069 }
                okhttp3.OkHttpClient$Builder r2 = new okhttp3.OkHttpClient$Builder     // Catch:{ IOException -> 0x0069 }
                r2.<init>()     // Catch:{ IOException -> 0x0069 }
                okhttp3.OkHttpClient r2 = r2.build()     // Catch:{ IOException -> 0x0069 }
                okhttp3.Call r1 = r2.newCall(r1)     // Catch:{ IOException -> 0x0069 }
                okhttp3.Response r0 = r1.execute()     // Catch:{ IOException -> 0x0069 }
                boolean r1 = r0.isSuccessful()     // Catch:{ IOException -> 0x0069 }
                if (r1 == 0) goto L_0x0059
                okhttp3.ResponseBody r1 = r0.body()     // Catch:{ IOException -> 0x0069 }
                java.lang.String r1 = r1.string()     // Catch:{ IOException -> 0x0069 }
                if (r0 == 0) goto L_0x0058
                okhttp3.ResponseBody r2 = r0.body()
                if (r2 == 0) goto L_0x0058
                okhttp3.ResponseBody r0 = r0.body()
                r0.close()
            L_0x0058:
                return r1
            L_0x0059:
                com.myfitnesspal.shared.api.ApiException r1 = new com.myfitnesspal.shared.api.ApiException     // Catch:{ IOException -> 0x0069 }
                java.lang.String r2 = r0.message()     // Catch:{ IOException -> 0x0069 }
                int r3 = r0.code()     // Catch:{ IOException -> 0x0069 }
                r1.<init>(r2, r3)     // Catch:{ IOException -> 0x0069 }
                throw r1     // Catch:{ IOException -> 0x0069 }
            L_0x0067:
                r1 = move-exception
                goto L_0x0073
            L_0x0069:
                com.myfitnesspal.shared.api.ApiException r1 = new com.myfitnesspal.shared.api.ApiException     // Catch:{ all -> 0x0067 }
                java.lang.String r2 = ""
                r3 = 999(0x3e7, float:1.4E-42)
                r1.<init>(r2, r3)     // Catch:{ all -> 0x0067 }
                throw r1     // Catch:{ all -> 0x0067 }
            L_0x0073:
                if (r0 == 0) goto L_0x0082
                okhttp3.ResponseBody r2 = r0.body()
                if (r2 == 0) goto L_0x0082
                okhttp3.ResponseBody r0 = r0.body()
                r0.close()
            L_0x0082:
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.registration.ui.activity.ForgotPasswordActivity.SendPasswordResetTask.resetLegacy():java.lang.String");
        }

        private String resetSso() throws ApiException {
            try {
                SSO.getSdk().forgotPassword(this.username, null);
                return ForgotPasswordActivity.SUCCESSFUL_RESPONSE;
            } catch (UacfApiException e) {
                throw new ApiException(e);
            }
        }
    }

    public boolean onSearchRequested() {
        return false;
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, ForgotPasswordActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        setContentView((int) R.layout.forgotten_password_material);
        this.name = (EditText) findById(R.id.txtEmail);
        this.done = (Button) findById(R.id.btnDone);
        initUi();
        initEventListeners();
        if (bundle == null) {
            getAnalyticsService().reportEvent(Events.FORGOT_PASSWORD_SCREEN_SEE);
        }
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (ALERT_DIALOG_FRAGMENT.equals(str)) {
            AlertDialogFragment alertDialogFragment = (AlertDialogFragment) dialogFragment;
            if (alertDialogFragment.getArguments().getBoolean(EXTRA_DIALOG_CLOSE_ON_OK)) {
                alertDialogFragment.setPositiveListener(this.onCloseOnOkClickListener);
            }
        }
        return super.onRebindDialogFragment(dialogFragment, str);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        getImmHelper().showSoftInput();
    }

    @Subscribe
    public void onSendPasswordResetCompleted(CompletedEvent completedEvent) {
        hideProgressDialog();
        if (!completedEvent.successful() || !SUCCESSFUL_RESPONSE.equals(completedEvent.getResult())) {
            reportResult(VALUE_UNKNOWN_EMAIL);
            showAlertDialog(R.string.auth_forgot_password_unknown_email_title, R.string.auth_forgot_password_unknown_email_message);
            return;
        }
        reportResult(VALUE_SUCCESSFUL);
        showAlertDialog(R.string.info_sent, R.string.check_email_for_instructions);
    }

    private void initUi() {
        this.name.setHint(R.string.auth_forgot_password_enter_email);
    }

    private void initEventListeners() {
        this.name.setOnKeyListener(this.goNextKeyListener);
        this.done.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ForgotPasswordActivity.this.getAnalyticsService().reportEvent(Events.FORGOT_PASSWORD_SUBMIT_CLICK);
                ForgotPasswordActivity.this.submitForgotPasswordRequest();
            }
        });
        findById(R.id.btn_reset_password_help).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ForgotPasswordActivity.this.getAnalyticsService().reportEvent(ForgotPasswordActivity.EVENT_EMAIL_NOT_RECOGNIZED);
                ForgotPasswordActivity.this.getNavigationHelper().withIntent(SharedIntents.newUriIntent(Uri.parse(ForgotPasswordActivity.this.getString(R.string.reset_password_help_url)).buildUpon().appendQueryParameter(Http.LANG, ForgotPasswordActivity.this.getCountryService().getCurrentLocaleIdentifier()).appendQueryParameter(Http.BINARY_API_VERSION, Strings.toString(Integer.valueOf(ApiUtil.getBinaryApiVersion((AppSettings) ForgotPasswordActivity.this.appSettings.get())))).appendQueryParameter(Http.PLATFORM, "android").appendQueryParameter(Http.CLIENT_BUILD, Strings.toString(Integer.valueOf(VersionUtils.getAppVersionCode(ForgotPasswordActivity.this)))).toString())).startActivity();
            }
        });
    }

    private void showAlertDialog(int i, int i2) {
        boolean z = i == R.string.info_sent;
        AlertDialogFragment newInstance = AlertDialogFragment.newInstance();
        newInstance.getArguments().putBoolean(EXTRA_DIALOG_CLOSE_ON_OK, z);
        ((AlertDialogFragment) ((AlertDialogFragment) newInstance.setTitle(i)).setMessage(i2)).setPositiveText(R.string.ok, z ? this.onCloseOnOkClickListener : null);
        newInstance.setCancelable(false);
        showDialogFragment(newInstance, ALERT_DIALOG_FRAGMENT);
    }

    private void showProgressDialog() {
        if (getSupportFragmentManager().findFragmentByTag(PROGRESS_DIALOG_FRAGMENT) == null) {
            showDialogFragment(ProgressDialogFragment.newInstance(R.string.sending_request), PROGRESS_DIALOG_FRAGMENT);
        }
    }

    private void hideProgressDialog() {
        Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag(PROGRESS_DIALOG_FRAGMENT);
        if (findFragmentByTag != null) {
            ((ProgressDialogFragment) findFragmentByTag).dismiss();
        }
    }

    /* access modifiers changed from: private */
    public void submitForgotPasswordRequest() {
        getImmHelper().hideSoftInput((View) this.name);
        String trimmed = Strings.trimmed((Object) this.name.getText());
        if (trimmed.length() == 0) {
            reportResult(VALUE_FIELD_EMPTY_OR_INVALID);
            showAlertDialog(R.string.error, R.string.auth_forgot_password_enter_email_message);
        } else if (trimmed.contains(" ")) {
            reportResult(VALUE_FIELD_EMPTY_OR_INVALID);
            showAlertDialog(R.string.error, R.string.auth_forgot_password_enter_email_message);
        } else {
            showProgressDialog();
            new SendPasswordResetTask(trimmed, (ApiUrlProvider) this.apiUrlProvider.get()).setCacheMode(CacheMode.None).setDedupeMode(DedupeMode.CancelExisting).run(getRunner());
        }
    }

    private void reportResult(String str) {
        getAnalyticsService().reportEvent(Events.FORGOT_PASSWORD_SUBMIT_CLICK_RESULT, MapUtil.createMap(KEY_RESULT, str));
    }
}
