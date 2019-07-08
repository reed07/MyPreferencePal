package io.uacf.thumbprint.ui.internal.password;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.ProgressBar;
import io.uacf.thumbprint.ui.R;
import io.uacf.thumbprint.ui.internal.base.UacfBaseActivity;
import io.uacf.thumbprint.ui.sdk.uiconfig.UacfThumbprintTypefaceConfig;

public class UacfChangePasswordActivity extends UacfBaseActivity {
    /* access modifiers changed from: private */
    public AppCompatButton changePasswordButton;
    private ProgressBar loadingSpinner;
    private TextInputEditText newPassword;
    /* access modifiers changed from: private */
    public TextInputLayout newPasswordLayout;
    private TextInputEditText retypePassword;
    /* access modifiers changed from: private */
    public TextInputLayout retypePasswordLayout;
    private long startTime;
    /* access modifiers changed from: private */
    public UacfPasswordViewModel viewModel;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setActionBarTitleText(R.string.changePassword_title);
        this.viewModel = (UacfPasswordViewModel) ViewModelProviders.of(this).get(UacfPasswordViewModel.class);
        this.newPassword = (TextInputEditText) findViewById(R.id.new_password);
        this.newPasswordLayout = (TextInputLayout) findViewById(R.id.new_password_layout);
        this.retypePassword = (TextInputEditText) findViewById(R.id.retype_password);
        this.retypePasswordLayout = (TextInputLayout) findViewById(R.id.retype_password_layout);
        this.changePasswordButton = (AppCompatButton) findViewById(R.id.change_password_button);
        this.loadingSpinner = (ProgressBar) findViewById(R.id.loading_spinner);
        UacfThumbprintTypefaceConfig typefaceConfig = this.uiConfig.getTypefaceConfig();
        this.newPassword.setTypeface(typefaceConfig.getRegularTypeface());
        this.newPasswordLayout.setTypeface(typefaceConfig.getRegularTypeface());
        this.retypePassword.setTypeface(typefaceConfig.getRegularTypeface());
        this.retypePasswordLayout.setTypeface(typefaceConfig.getRegularTypeface());
        this.changePasswordButton.setTypeface(typefaceConfig.getBlackTypeface());
        this.changePasswordButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                UacfChangePasswordActivity.this.viewModel.changePasswordClicked();
            }
        });
        this.newPassword.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (!z) {
                    UacfChangePasswordActivity.this.viewModel.setNewPasswordEntered();
                }
            }
        });
        this.newPassword.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                UacfChangePasswordActivity.this.viewModel.setNewPassword(editable.toString());
            }
        });
        this.retypePassword.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                UacfChangePasswordActivity.this.viewModel.setRetypePassword(editable.toString());
            }
        });
        setUpObservables();
        showKeyboard();
        this.newPassword.requestFocus();
        this.changePasswordButton.setEnabled(false);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        this.startTime = System.currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        this.viewModel.reportPasswordChangeScreenViewedEvent(System.currentTimeMillis() - this.startTime);
        super.onStop();
    }

    public int getLayoutResourceId() {
        return R.layout.activity_change_password;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
        }
        return true;
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    private void setUpObservables() {
        this.viewModel.observeCurrentState(this, new Observer<ChangePasswordState>() {
            public void onChanged(@Nullable ChangePasswordState changePasswordState) {
                switch (changePasswordState) {
                    case LOADING:
                        UacfChangePasswordActivity.this.showLoadingState(true);
                        return;
                    case SUCCESS:
                        UacfChangePasswordActivity.this.finish();
                        return;
                    default:
                        UacfChangePasswordActivity.this.showRequestFailedDialog();
                        return;
                }
            }
        });
        this.viewModel.observePasswordLengthError(this, new Observer<Boolean>() {
            public void onChanged(@Nullable Boolean bool) {
                UacfChangePasswordActivity.this.newPasswordLayout.setError(bool.booleanValue() ? UacfChangePasswordActivity.this.getString(R.string.changePassword_lengthError) : null);
            }
        });
        this.viewModel.observePasswordsDontMatchError(this, new Observer<Boolean>() {
            public void onChanged(@Nullable Boolean bool) {
                UacfChangePasswordActivity.this.retypePasswordLayout.setError(bool.booleanValue() ? UacfChangePasswordActivity.this.getString(R.string.changePassword_matchError) : null);
            }
        });
        this.viewModel.observeChangePasswordEnabled(this, new Observer<Boolean>() {
            public void onChanged(@Nullable Boolean bool) {
                UacfChangePasswordActivity.this.changePasswordButton.setEnabled(bool.booleanValue());
            }
        });
    }

    /* access modifiers changed from: private */
    public void showLoadingState(boolean z) {
        this.changePasswordButton.setEnabled(!z);
        this.changePasswordButton.setText(z ? null : getString(R.string.changePassword_action));
        this.loadingSpinner.setVisibility(z ? 0 : 4);
    }

    /* access modifiers changed from: private */
    public void showRequestFailedDialog() {
        showLoadingState(false);
        new Builder(this).setMessage((CharSequence) getString(R.string.error_unknownErrorTryAgainLater)).setPositiveButton(R.string.action_ok, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();
    }
}
