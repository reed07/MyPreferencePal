package com.myfitnesspal.feature.deleteaccount.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.deleteaccount.ui.activity.DeleteAccountActivity.ExportMode;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.activity.impl.FullScreenWebView;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Strings;

public class DeleteAccountPremiumActivity extends MfpActivity {
    private static final String CANCEL_PREMIUM_WEBVIEW_URL = "https://myfitnesspal.desk.com/customer/portal/articles/1940192-how-do-i-cancel-my-premium-subscription-renewal-";
    @BindView(2131362014)
    View continueButton;
    @BindView(2131363293)
    View premiumCancelInfoView;
    @BindView(2131363766)
    Switch premiumCancelSwitch;
    @BindView(2131363294)
    View premiumCanceledText;

    public static Intent newStartIntentFromManageConsents(Context context, ExportMode exportMode, String str) {
        return new Intent(context, DeleteAccountPremiumActivity.class).putExtra(DeleteAccountActivity.EXTRA_DELETE_MODE, exportMode).putExtra(DeleteAccountActivity.EXTRA_CONSENT_TYPE, str);
    }

    public static Intent newStartIntent(Context context, ExportMode exportMode, String str) {
        return new Intent(context, DeleteAccountPremiumActivity.class).putExtra(DeleteAccountActivity.EXTRA_DELETE_MODE, exportMode).putExtra(DeleteAccountActivity.EXTRA_DELETE_SOURCE, str);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.delete_account_premium);
        setupListeners();
        toggleContinueEnabled(false);
    }

    private void setupListeners() {
        this.continueButton.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                DeleteAccountPremiumActivity.lambda$setupListeners$0(DeleteAccountPremiumActivity.this, view);
            }
        });
        this.premiumCancelSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                DeleteAccountPremiumActivity.this.toggleContinueEnabled(z);
            }
        });
        this.premiumCancelInfoView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                NavigationHelper navigationHelper = DeleteAccountPremiumActivity.this.getNavigationHelper();
                DeleteAccountPremiumActivity deleteAccountPremiumActivity = DeleteAccountPremiumActivity.this;
                navigationHelper.withIntent(FullScreenWebView.newStartIntent(deleteAccountPremiumActivity, DeleteAccountPremiumActivity.CANCEL_PREMIUM_WEBVIEW_URL, deleteAccountPremiumActivity.getString(R.string.canceling_premium), false, true, false)).startActivity();
            }
        });
        this.premiumCanceledText.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                DeleteAccountPremiumActivity.this.premiumCancelSwitch.toggle();
            }
        });
    }

    public static /* synthetic */ void lambda$setupListeners$0(DeleteAccountPremiumActivity deleteAccountPremiumActivity, View view) {
        Intent intent;
        String string = BundleUtils.getString(deleteAccountPremiumActivity.getIntent().getExtras(), DeleteAccountActivity.EXTRA_CONSENT_TYPE);
        ExportMode exportMode = (ExportMode) BundleUtils.getSerializable(deleteAccountPremiumActivity.getIntent().getExtras(), DeleteAccountActivity.EXTRA_DELETE_MODE, ExportMode.class.getClassLoader());
        NavigationHelper navigationHelper = deleteAccountPremiumActivity.getNavigationHelper();
        if (Strings.isEmpty(string)) {
            intent = DeleteAccountActivity.newStartIntent(deleteAccountPremiumActivity, exportMode, deleteAccountPremiumActivity.getIntent().getStringExtra(DeleteAccountActivity.EXTRA_DELETE_SOURCE));
        } else {
            intent = DeleteAccountActivity.newStartIntentFromManageConsents(deleteAccountPremiumActivity, exportMode, string);
        }
        navigationHelper.withIntent(intent).startActivity();
    }

    /* access modifiers changed from: private */
    public void toggleContinueEnabled(boolean z) {
        this.continueButton.setEnabled(z);
        this.continueButton.setBackgroundResource(z ? R.drawable.blue_rounded_rectangle_button : R.drawable.disabled_blue_rounded_rectangle);
    }
}
