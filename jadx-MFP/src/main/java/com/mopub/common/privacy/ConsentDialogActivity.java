package com.mopub.common.privacy;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.MoPub;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Intents;
import com.mopub.exceptions.IntentNotResolvableException;

public class ConsentDialogActivity extends Activity {
    @Nullable
    Handler mCloseButtonHandler;
    @Nullable
    ConsentStatus mConsentStatus;
    @Nullable
    private Runnable mEnableCloseButtonRunnable;
    @Nullable
    ConsentDialogLayout mView;

    static void start(@NonNull Context context, @NonNull String str) {
        Preconditions.checkNotNull(context);
        if (TextUtils.isEmpty(str)) {
            MoPubLog.e("ConsentDialogActivity htmlData can't be empty string.");
            return;
        }
        try {
            Intents.startActivity(context, createIntent(context, str));
        } catch (ActivityNotFoundException | IntentNotResolvableException unused) {
            MoPubLog.e("ConsentDialogActivity not found - did you declare it in AndroidManifest.xml?");
        }
    }

    @NonNull
    static Intent createIntent(@NonNull Context context, @NonNull String str) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(str);
        Bundle bundle = new Bundle();
        bundle.putString("html-page-content", str);
        return Intents.getStartActivityIntent(context, ConsentDialogActivity.class, bundle);
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        String stringExtra = getIntent().getStringExtra("html-page-content");
        if (TextUtils.isEmpty(stringExtra)) {
            MoPubLog.e("Web page for ConsentDialogActivity is empty");
            finish();
            return;
        }
        requestWindowFeature(1);
        getWindow().addFlags(1024);
        this.mView = new ConsentDialogLayout(this);
        this.mView.setConsentClickListener(new ConsentListener() {
            public void onConsentClick(ConsentStatus consentStatus) {
                ConsentDialogActivity.this.saveConsentStatus(consentStatus);
                ConsentDialogActivity.this.setCloseButtonVisibility(false);
            }

            public void onCloseClick() {
                ConsentDialogActivity.this.finish();
            }
        });
        this.mEnableCloseButtonRunnable = new Runnable() {
            public void run() {
                ConsentDialogActivity.this.setCloseButtonVisibility(true);
            }
        };
        setContentView(this.mView);
        this.mView.startLoading(stringExtra, new PageLoadListener() {
            public void onLoadProgress(int i) {
                int i2 = ConsentDialogLayout.FINISHED_LOADING;
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        this.mCloseButtonHandler = new Handler();
        this.mCloseButtonHandler.postDelayed(this.mEnableCloseButtonRunnable, 10000);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        setCloseButtonVisibility(true);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        PersonalInfoManager personalInformationManager = MoPub.getPersonalInformationManager();
        if (personalInformationManager != null) {
            ConsentStatus consentStatus = this.mConsentStatus;
            if (consentStatus != null) {
                personalInformationManager.changeConsentStateFromDialog(consentStatus);
            }
        }
        super.onDestroy();
    }

    /* access modifiers changed from: 0000 */
    public void setCloseButtonVisibility(boolean z) {
        Handler handler = this.mCloseButtonHandler;
        if (handler != null) {
            handler.removeCallbacks(this.mEnableCloseButtonRunnable);
        }
        ConsentDialogLayout consentDialogLayout = this.mView;
        if (consentDialogLayout != null) {
            consentDialogLayout.setCloseVisible(z);
        }
    }

    /* access modifiers changed from: private */
    public void saveConsentStatus(@NonNull ConsentStatus consentStatus) {
        Preconditions.checkNotNull(consentStatus);
        this.mConsentStatus = consentStatus;
    }
}
