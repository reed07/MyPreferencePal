package com.myfitnesspal.feature.deleteaccount.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.consents.util.ConsentsAnalyticsHelperImpl;
import com.myfitnesspal.feature.deleteaccount.service.DeleteAccountAnalyticsHelper;
import com.myfitnesspal.feature.deleteaccount.service.DeleteAccountService;
import com.myfitnesspal.feature.deleteaccount.task.DeleteAccountTask;
import com.myfitnesspal.feature.deleteaccount.task.VerifyAccountTask;
import com.myfitnesspal.feature.deleteaccount.task.VerifyAccountTask.CompletedEvent;
import com.myfitnesspal.feature.deleteaccount.task.VerifyAccountTask.VerificationType;
import com.myfitnesspal.feature.fileexport.ui.activity.FileExport;
import com.myfitnesspal.feature.fileexport.ui.activity.FileExport.ExportMode;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.feature.registration.service.SignInService;
import com.myfitnesspal.feature.registration.ui.activity.FacebookLoginActivity;
import com.myfitnesspal.feature.registration.ui.activity.ForgotPasswordActivity;
import com.myfitnesspal.feature.registration.ui.activity.LoginActivity;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.activity.impl.FullScreenWebView;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogPositiveListener;
import com.myfitnesspal.shared.ui.dialog.impl.ProgressDialogFragment;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import javax.inject.Inject;

public class DeleteAccountActivity extends MfpActivity {
    private static final String CANCEL_PREMIUM_WEBVIEW_URL = "https://myfitnesspal.desk.com/customer/portal/articles/1940192-how-do-i-cancel-my-premium-subscription-renewal-";
    private static final String CONFIRM_DELETE_ACCOUNT_TAG = "confirm_delete_account";
    private static final String DELETE_ACCOUNT_FAILED_TAG = "delete_account_failed";
    public static final String DELETE_ACCOUNT_SEE = "delete_account_see";
    public static final String EXTRA_CONSENT_TYPE = "extra_consent_type";
    public static final String EXTRA_DELETE_MODE = "extra_delete_mode";
    public static final String EXTRA_DELETE_SOURCE = "extra_delete_source";
    private static final String FIX_ACCOUNT_URL = "https://myfitnesspal.desk.com/customer/portal/emails/new";
    private static final String PROGRESS_DIALOG_TAG = "progress_dialog";
    private static final String UA_ACCOUNT_DELETE_URL = "https://account.underarmour.com/help";
    private static final String VERIFY_FACEBOOK_FAILED_TAG = "verify_facebook_failed";
    private static final String VERIFY_PASSWORD_FAILED_TAG = "verify_password_failed";
    @Inject
    Lazy<DeleteAccountAnalyticsHelper> deleteAccountAnalyticsHelper;
    @BindView(2131362015)
    View deleteAccountButton;
    private final DialogPositiveListener deleteAccountListener = new DialogPositiveListener() {
        public void onClick(Object obj) {
            new DeleteAccountTask(DeleteAccountActivity.this.deleteAccountService).run(DeleteAccountActivity.this.getRunner());
            DeleteAccountActivity.this.showProgressDialogFragment();
        }
    };
    @Inject
    Lazy<DeleteAccountService> deleteAccountService;
    @BindView(2131362292)
    TextView deleteText;
    @BindView(2131362480)
    TextView enterPasswordText;
    @BindView(2131362540)
    TextView exportInformationText;
    @BindView(2131362551)
    View fbButton;
    @BindView(2131362552)
    TextView fbButtonText;
    @BindView(2131362564)
    View fbLoginText;
    @BindView(2131362595)
    View fixAccountText;
    @BindView(2131362653)
    View forgotPasswordText;
    @BindView(2131362752)
    View howCancelPremiumText;
    private boolean isExportModeEnabled;
    @Inject
    Lazy<LoginModel> loginModel;
    @BindView(2131363249)
    EditText passwordInput;
    @Inject
    Lazy<SignInService> signInService;
    @BindView(2131364072)
    View uaAccountDeleteText;

    public enum ExportMode {
        Enabled,
        Disabled
    }

    public static Intent newStartIntentFromManageConsents(Context context, ExportMode exportMode, String str) {
        return new Intent(context, DeleteAccountActivity.class).putExtra(EXTRA_DELETE_MODE, exportMode).putExtra(EXTRA_CONSENT_TYPE, str);
    }

    public static Intent newStartIntent(Context context, ExportMode exportMode, String str) {
        return new Intent(context, DeleteAccountActivity.class).putExtra(EXTRA_DELETE_MODE, exportMode).putExtra(EXTRA_DELETE_SOURCE, str);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.delete_account);
        component().inject(this);
        this.isExportModeEnabled = BundleUtils.getSerializable(getIntent().getExtras(), EXTRA_DELETE_MODE, ExportMode.class.getClassLoader()) == ExportMode.Enabled;
        setupListeners();
        setupFacebookViewAndVisibility();
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (!Strings.equals(CONFIRM_DELETE_ACCOUNT_TAG, str)) {
            return super.onRebindDialogFragment(dialogFragment, str);
        }
        ((AlertDialogFragment) dialogFragment).setPositiveListener(this.deleteAccountListener);
        return true;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 186) {
            return;
        }
        if (i2 == -1) {
            VerifyAccountTask.createTaskForFacebookVerification(this.signInService, ((LoginModel) this.loginModel.get()).getFacebookData().getUserId(), ExtrasUtils.getString(intent, FacebookLoginActivity.EXTRA_VERIFY_ACCESS_TOKEN)).run(getRunner());
            showProgressDialogFragment();
            return;
        }
        showFacebookErrorDialog();
    }

    @Subscribe
    public void onVerifyAccountTaskCompletedEvent(CompletedEvent completedEvent) {
        dismissProgressDialogFragment();
        if (completedEvent.successful() && ((Boolean) completedEvent.getResult()).booleanValue()) {
            showDeleteConfirmationDialog();
        } else if (completedEvent.getVerificationType() == VerificationType.Facebook) {
            showFacebookErrorDialog();
        } else {
            showErrorDialog(VERIFY_PASSWORD_FAILED_TAG, R.string.incorrect_password, R.string.reenter_password);
            ((DeleteAccountAnalyticsHelper) this.deleteAccountAnalyticsHelper.get()).reportDeleteFailPassword();
        }
    }

    @Subscribe
    public void onDeleteAccountTaskCompletedEvent(DeleteAccountTask.CompletedEvent completedEvent) {
        dismissProgressDialogFragment();
        if (!completedEvent.successful() || !((Boolean) completedEvent.getResult()).booleanValue()) {
            showErrorDialog(DELETE_ACCOUNT_FAILED_TAG, R.string.upload_image_failed_dialog_title, R.string.unable_delete);
            ((DeleteAccountAnalyticsHelper) this.deleteAccountAnalyticsHelper.get()).reportDeleteFailBackend();
            return;
        }
        reportDeleteSuccessful();
        getNavigationHelper().finishActivityAfterNavigation(true).withIntent(LoginActivity.newStartIntent(this)).startActivity();
    }

    private void setupListeners() {
        AnonymousClass1 r0 = new OnClickListener() {
            public void onClick(View view) {
                String str;
                int i;
                int id = view.getId();
                if (id == R.id.fix_account_text) {
                    str = DeleteAccountActivity.FIX_ACCOUNT_URL;
                    i = R.string.contact_us;
                } else if (id == R.id.how_cancel_premium_text) {
                    str = DeleteAccountActivity.CANCEL_PREMIUM_WEBVIEW_URL;
                    i = R.string.canceling_premium;
                } else if (id == R.id.ua_account_delete_text) {
                    str = DeleteAccountActivity.UA_ACCOUNT_DELETE_URL;
                    i = R.string.ua_account;
                } else {
                    throw new IllegalStateException("Unhandled button click");
                }
                NavigationHelper navigationHelper = DeleteAccountActivity.this.getNavigationHelper();
                DeleteAccountActivity deleteAccountActivity = DeleteAccountActivity.this;
                navigationHelper.withIntent(deleteAccountActivity.getWebViewIntent(str, deleteAccountActivity.getString(i))).startActivity();
            }
        };
        this.howCancelPremiumText.setOnClickListener(r0);
        this.uaAccountDeleteText.setOnClickListener(r0);
        this.fixAccountText.setOnClickListener(r0);
        if (this.isExportModeEnabled) {
            ViewUtils.setVisible(true, this.exportInformationText);
            this.deleteText.setText(R.string.delete_account_text);
            this.exportInformationText.setOnClickListener(new OnClickListener() {
                public final void onClick(View view) {
                    DeleteAccountActivity.this.getNavigationHelper().withIntent(FileExport.createIntentForFileExport(DeleteAccountActivity.this.getActivity(), ExportMode.DeleteAccount)).startActivity();
                }
            });
        }
        this.forgotPasswordText.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                DeleteAccountActivity.this.getNavigationHelper().withIntent(ForgotPasswordActivity.newStartIntent(DeleteAccountActivity.this.getActivity())).startActivity();
            }
        });
        this.deleteAccountButton.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                DeleteAccountActivity.lambda$setupListeners$2(DeleteAccountActivity.this, view);
            }
        });
        this.fbButton.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                DeleteAccountActivity.this.getNavigationHelper().withIntent(FacebookLoginActivity.getStartIntentForVerification(DeleteAccountActivity.this.getActivity())).startActivity(RequestCodes.FACEBOOK_LOGIN);
            }
        });
    }

    public static /* synthetic */ void lambda$setupListeners$2(DeleteAccountActivity deleteAccountActivity, View view) {
        deleteAccountActivity.validatePasswordEntryAndStartDeleteTask();
        ((DeleteAccountAnalyticsHelper) deleteAccountActivity.deleteAccountAnalyticsHelper.get()).reportAccountDeleteButton();
    }

    private void validatePasswordEntryAndStartDeleteTask() {
        String strings = Strings.toString(this.passwordInput.getText());
        if (Strings.isEmpty(strings)) {
            this.enterPasswordText.setTextColor(getResources().getColor(R.color.red_light));
            return;
        }
        VerifyAccountTask.createTaskForRegularVerification(this.signInService, getSession().getUser().getEmail(), strings).run(getRunner());
        showProgressDialogFragment();
    }

    private void setupFacebookViewAndVisibility() {
        this.fbButtonText.setText(R.string.connect_with_fb);
        ViewUtils.setVisible(((LoginModel) this.loginModel.get()).getFacebookData().isValid(), this.fbButton, this.fbLoginText);
    }

    private void showDeleteConfirmationDialog() {
        AlertDialogFragment newInstance = AlertDialogFragment.newInstance();
        ((AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) newInstance.setTitle(R.string.delete_account)).setMessage(this.isExportModeEnabled ? R.string.delete_account_confirmation : R.string.confirm_delete_account)).setPositiveText(this.isExportModeEnabled ? R.string.yesBtn : R.string.delete, this.deleteAccountListener)).setNegativeText(R.string.cancel, null);
        showDialogFragment(newInstance, CONFIRM_DELETE_ACCOUNT_TAG);
    }

    private void showFacebookErrorDialog() {
        showErrorDialog(VERIFY_FACEBOOK_FAILED_TAG, R.string.upload_image_failed_dialog_title, R.string.unable_verify_fb);
    }

    private void showErrorDialog(String str, int i, int i2) {
        showDialogFragment(((AlertDialogFragment) ((AlertDialogFragment) AlertDialogFragment.newInstance().setTitle(i)).setMessage(i2)).setPositiveText(R.string.ok, null), str);
    }

    /* access modifiers changed from: private */
    public void showProgressDialogFragment() {
        DialogFragment currentProgressDialogFragment = getCurrentProgressDialogFragment();
        if (currentProgressDialogFragment == null) {
            currentProgressDialogFragment = ProgressDialogFragment.newInstance(R.string.please_wait);
        }
        if (!currentProgressDialogFragment.isAdded()) {
            currentProgressDialogFragment.setCancelable(false);
            currentProgressDialogFragment.show(getSupportFragmentManager(), "progress_dialog");
        }
    }

    private void dismissProgressDialogFragment() {
        DialogFragment currentProgressDialogFragment = getCurrentProgressDialogFragment();
        if (currentProgressDialogFragment != null) {
            currentProgressDialogFragment.dismiss();
        }
    }

    private DialogFragment getCurrentProgressDialogFragment() {
        return (DialogFragment) getSupportFragmentManager().findFragmentByTag("progress_dialog");
    }

    /* access modifiers changed from: private */
    public Intent getWebViewIntent(String str, String str2) {
        return FullScreenWebView.newStartIntent(this, str, str2, false, true, false);
    }

    private void reportDeleteSuccessful() {
        String str;
        String str2 = "";
        if (Strings.isEmpty(getIntent().getExtras().getString(EXTRA_CONSENT_TYPE))) {
            str = getIntent().getStringExtra(EXTRA_DELETE_SOURCE);
        } else {
            str = ConsentsAnalyticsHelperImpl.PRIVACY_CENTER_CONSENTS_SEE;
            str2 = getIntent().getExtras().getString(EXTRA_CONSENT_TYPE);
        }
        ((DeleteAccountAnalyticsHelper) this.deleteAccountAnalyticsHelper.get()).reportAccountDeleteSuccessful(str, str2);
    }
}
