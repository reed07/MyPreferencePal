package com.mopub.mraid;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.StateListDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.VideoView;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Drawables;
import com.mopub.mobileads.BaseVideoPlayerActivity;
import com.mopub.mobileads.BaseVideoViewController;
import com.mopub.mobileads.BaseVideoViewController.BaseVideoViewControllerListener;

public class MraidVideoViewController extends BaseVideoViewController {
    private int mButtonPadding;
    private int mButtonSize;
    /* access modifiers changed from: private */
    public ImageButton mCloseButton;
    private final VideoView mVideoView;

    /* access modifiers changed from: protected */
    public void onBackPressed() {
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
    }

    /* access modifiers changed from: protected */
    public void onPause() {
    }

    /* access modifiers changed from: protected */
    public void onResume() {
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(@NonNull Bundle bundle) {
    }

    public MraidVideoViewController(Context context, Bundle bundle, Bundle bundle2, BaseVideoViewControllerListener baseVideoViewControllerListener) {
        super(context, null, baseVideoViewControllerListener);
        this.mVideoView = new VideoView(context);
        this.mVideoView.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                MraidVideoViewController.this.mCloseButton.setVisibility(0);
                MraidVideoViewController.this.videoCompleted(true);
            }
        });
        this.mVideoView.setOnErrorListener(new OnErrorListener() {
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                MraidVideoViewController.this.mCloseButton.setVisibility(0);
                MraidVideoViewController.this.videoError(false);
                return false;
            }
        });
        this.mVideoView.setVideoPath(bundle.getString(BaseVideoPlayerActivity.VIDEO_URL));
    }

    /* access modifiers changed from: protected */
    public void onCreate() {
        super.onCreate();
        this.mButtonSize = Dips.asIntPixels(50.0f, getContext());
        this.mButtonPadding = Dips.asIntPixels(8.0f, getContext());
        createInterstitialCloseButton();
        this.mCloseButton.setVisibility(8);
        this.mVideoView.start();
    }

    /* access modifiers changed from: protected */
    public VideoView getVideoView() {
        return this.mVideoView;
    }

    private void createInterstitialCloseButton() {
        this.mCloseButton = new ImageButton(getContext());
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{-16842919}, Drawables.INTERSTITIAL_CLOSE_BUTTON_NORMAL.createDrawable(getContext()));
        stateListDrawable.addState(new int[]{16842919}, Drawables.INTERSTITIAL_CLOSE_BUTTON_PRESSED.createDrawable(getContext()));
        this.mCloseButton.setImageDrawable(stateListDrawable);
        this.mCloseButton.setBackgroundDrawable(null);
        this.mCloseButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MraidVideoViewController.this.getBaseVideoViewControllerListener().onFinish();
            }
        });
        int i = this.mButtonSize;
        LayoutParams layoutParams = new LayoutParams(i, i);
        layoutParams.addRule(11);
        int i2 = this.mButtonPadding;
        layoutParams.setMargins(i2, 0, i2, 0);
        getLayout().addView(this.mCloseButton, layoutParams);
    }
}
