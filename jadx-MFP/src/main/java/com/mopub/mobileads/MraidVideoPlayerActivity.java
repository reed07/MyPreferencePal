package com.mopub.mobileads;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.internal.AnalyticsEvents;
import com.mopub.common.AdType;
import com.mopub.common.DataKeys;
import com.mopub.common.FullAdType;
import com.mopub.common.IntentActions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Intents;
import com.mopub.common.util.Reflection;
import com.mopub.mobileads.BaseVideoViewController.BaseVideoViewControllerListener;
import com.mopub.mraid.MraidVideoViewController;

public class MraidVideoPlayerActivity extends BaseVideoPlayerActivity implements BaseVideoViewControllerListener {
    @Nullable
    private BaseVideoViewController mBaseVideoController;
    private long mBroadcastIdentifier;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().addFlags(1024);
        this.mBroadcastIdentifier = getBroadcastIdentifierFromIntent(getIntent());
        try {
            this.mBaseVideoController = createVideoViewController(bundle);
            this.mBaseVideoController.onCreate();
        } catch (IllegalStateException unused) {
            BaseBroadcastReceiver.broadcastAction(this, this.mBroadcastIdentifier, IntentActions.ACTION_INTERSTITIAL_FAIL);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        BaseVideoViewController baseVideoViewController = this.mBaseVideoController;
        if (baseVideoViewController != null) {
            baseVideoViewController.onPause();
        }
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        BaseVideoViewController baseVideoViewController = this.mBaseVideoController;
        if (baseVideoViewController != null) {
            baseVideoViewController.onResume();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        BaseVideoViewController baseVideoViewController = this.mBaseVideoController;
        if (baseVideoViewController != null) {
            baseVideoViewController.onDestroy();
        }
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        BaseVideoViewController baseVideoViewController = this.mBaseVideoController;
        if (baseVideoViewController != null) {
            baseVideoViewController.onSaveInstanceState(bundle);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        BaseVideoViewController baseVideoViewController = this.mBaseVideoController;
        if (baseVideoViewController != null) {
            baseVideoViewController.onConfigurationChanged(configuration);
        }
    }

    public void onBackPressed() {
        BaseVideoViewController baseVideoViewController = this.mBaseVideoController;
        if (baseVideoViewController != null && baseVideoViewController.backButtonEnabled()) {
            super.onBackPressed();
            this.mBaseVideoController.onBackPressed();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        BaseVideoViewController baseVideoViewController = this.mBaseVideoController;
        if (baseVideoViewController != null) {
            baseVideoViewController.onActivityResult(i, i2, intent);
        }
    }

    private BaseVideoViewController createVideoViewController(Bundle bundle) throws IllegalStateException {
        String stringExtra = getIntent().getStringExtra(BaseVideoPlayerActivity.VIDEO_CLASS_EXTRAS_KEY);
        if (FullAdType.VAST.equals(stringExtra)) {
            VastVideoViewController vastVideoViewController = new VastVideoViewController(this, getIntent().getExtras(), bundle, this.mBroadcastIdentifier, this);
            return vastVideoViewController;
        } else if (AdType.MRAID.equals(stringExtra)) {
            return new MraidVideoViewController(this, getIntent().getExtras(), bundle, this);
        } else {
            if (AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE.equals(stringExtra)) {
                Class[] clsArr = {Context.class, Bundle.class, Bundle.class, BaseVideoViewControllerListener.class};
                Object[] objArr = {this, getIntent().getExtras(), bundle, this};
                if (Reflection.classFound("com.mopub.nativeads.NativeVideoViewController")) {
                    try {
                        return (BaseVideoViewController) Reflection.instantiateClassWithConstructor("com.mopub.nativeads.NativeVideoViewController", BaseVideoViewController.class, clsArr, objArr);
                    } catch (Exception unused) {
                        throw new IllegalStateException("Missing native video module");
                    }
                } else {
                    throw new IllegalStateException("Missing native video module");
                }
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Unsupported video type: ");
                sb.append(stringExtra);
                throw new IllegalStateException(sb.toString());
            }
        }
    }

    public void onSetContentView(View view) {
        setContentView(view);
    }

    public void onSetRequestedOrientation(int i) {
        setRequestedOrientation(i);
    }

    public void onFinish() {
        finish();
    }

    public void onStartActivityForResult(Class<? extends Activity> cls, int i, Bundle bundle) {
        if (cls != null) {
            try {
                startActivityForResult(Intents.getStartActivityIntent(this, cls, bundle), i);
            } catch (ActivityNotFoundException unused) {
                StringBuilder sb = new StringBuilder();
                sb.append("Activity ");
                sb.append(cls.getName());
                sb.append(" not found. Did you declare it in your AndroidManifest.xml?");
                MoPubLog.d(sb.toString());
            }
        }
    }

    protected static long getBroadcastIdentifierFromIntent(Intent intent) {
        return intent.getLongExtra(DataKeys.BROADCAST_IDENTIFIER_KEY, -1);
    }
}
