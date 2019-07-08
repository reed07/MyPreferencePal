package com.mopub.mobileads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.VideoView;
import com.mopub.common.IntentActions;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;

public abstract class BaseVideoViewController {
    @NonNull
    private final BaseVideoViewControllerListener mBaseVideoViewControllerListener;
    @Nullable
    private Long mBroadcastIdentifier;
    private final Context mContext;
    private final RelativeLayout mLayout = new RelativeLayout(this.mContext);

    public interface BaseVideoViewControllerListener {
        void onFinish();

        void onSetContentView(View view);

        void onSetRequestedOrientation(int i);

        void onStartActivityForResult(Class<? extends Activity> cls, int i, Bundle bundle);
    }

    public boolean backButtonEnabled() {
        return true;
    }

    /* access modifiers changed from: protected */
    public abstract VideoView getVideoView();

    /* access modifiers changed from: 0000 */
    public void onActivityResult(int i, int i2, Intent intent) {
    }

    /* access modifiers changed from: protected */
    public abstract void onBackPressed();

    /* access modifiers changed from: protected */
    public abstract void onConfigurationChanged(Configuration configuration);

    /* access modifiers changed from: protected */
    public abstract void onDestroy();

    /* access modifiers changed from: protected */
    public abstract void onPause();

    /* access modifiers changed from: protected */
    public abstract void onResume();

    /* access modifiers changed from: protected */
    public abstract void onSaveInstanceState(@NonNull Bundle bundle);

    protected BaseVideoViewController(Context context, @Nullable Long l, @NonNull BaseVideoViewControllerListener baseVideoViewControllerListener) {
        Preconditions.checkNotNull(baseVideoViewControllerListener);
        this.mContext = context;
        this.mBroadcastIdentifier = l;
        this.mBaseVideoViewControllerListener = baseVideoViewControllerListener;
    }

    /* access modifiers changed from: protected */
    public void onCreate() {
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(13);
        this.mLayout.addView(getVideoView(), 0, layoutParams);
        this.mBaseVideoViewControllerListener.onSetContentView(this.mLayout);
    }

    /* access modifiers changed from: protected */
    @NonNull
    public BaseVideoViewControllerListener getBaseVideoViewControllerListener() {
        return this.mBaseVideoViewControllerListener;
    }

    /* access modifiers changed from: protected */
    public Context getContext() {
        return this.mContext;
    }

    public ViewGroup getLayout() {
        return this.mLayout;
    }

    /* access modifiers changed from: protected */
    public void videoError(boolean z) {
        MoPubLog.e("Video cannot be played.");
        broadcastAction(IntentActions.ACTION_INTERSTITIAL_FAIL);
        if (z) {
            this.mBaseVideoViewControllerListener.onFinish();
        }
    }

    /* access modifiers changed from: protected */
    public void videoCompleted(boolean z) {
        if (z) {
            this.mBaseVideoViewControllerListener.onFinish();
        }
    }

    /* access modifiers changed from: 0000 */
    public void broadcastAction(String str) {
        Long l = this.mBroadcastIdentifier;
        if (l != null) {
            BaseBroadcastReceiver.broadcastAction(this.mContext, l.longValue(), str);
        } else {
            MoPubLog.w("Tried to broadcast a video event without a broadcast identifier to send to.");
        }
    }
}
