package com.twitter.sdk.android.core.identity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.R;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.internal.CommonUtils;
import java.lang.ref.WeakReference;

public class TwitterLoginButton extends Button {
    final WeakReference<Activity> activityRef;
    volatile TwitterAuthClient authClient;
    Callback<TwitterSession> callback;
    OnClickListener onClickListener;

    private class LoginClickListener implements OnClickListener {
        private LoginClickListener() {
        }

        public void onClick(View view) {
            checkCallback(TwitterLoginButton.this.callback);
            checkActivity((Activity) TwitterLoginButton.this.activityRef.get());
            TwitterLoginButton.this.getTwitterAuthClient().authorize((Activity) TwitterLoginButton.this.activityRef.get(), TwitterLoginButton.this.callback);
            if (TwitterLoginButton.this.onClickListener != null) {
                TwitterLoginButton.this.onClickListener.onClick(view);
            }
        }

        private void checkCallback(Callback callback) {
            if (callback == null) {
                CommonUtils.logOrThrowIllegalStateException("Twitter", "Callback must not be null, did you call setCallback?");
            }
        }

        private void checkActivity(Activity activity) {
            if (activity == null || activity.isFinishing()) {
                CommonUtils.logOrThrowIllegalStateException("Twitter", "TwitterLoginButton requires an activity. Override getActivity to provide the activity for this button.");
            }
        }
    }

    public TwitterLoginButton(Context context) {
        this(context, null);
    }

    public TwitterLoginButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842824);
    }

    public TwitterLoginButton(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, null);
    }

    TwitterLoginButton(Context context, AttributeSet attributeSet, int i, TwitterAuthClient twitterAuthClient) {
        super(context, attributeSet, i);
        this.activityRef = new WeakReference<>(getActivity());
        this.authClient = twitterAuthClient;
        setupButton();
        checkTwitterCoreAndEnable();
    }

    @TargetApi(21)
    private void setupButton() {
        Resources resources = getResources();
        super.setCompoundDrawablesWithIntrinsicBounds(resources.getDrawable(R.drawable.tw__ic_logo_default), null, null, null);
        super.setCompoundDrawablePadding(resources.getDimensionPixelSize(R.dimen.tw__login_btn_drawable_padding));
        super.setText(R.string.tw__login_btn_txt);
        super.setTextColor(resources.getColor(R.color.tw__solid_white));
        super.setTextSize(0, (float) resources.getDimensionPixelSize(R.dimen.tw__login_btn_text_size));
        super.setTypeface(Typeface.DEFAULT_BOLD);
        super.setPadding(resources.getDimensionPixelSize(R.dimen.tw__login_btn_left_padding), 0, resources.getDimensionPixelSize(R.dimen.tw__login_btn_right_padding), 0);
        super.setBackgroundResource(R.drawable.tw__login_btn);
        super.setOnClickListener(new LoginClickListener());
        if (VERSION.SDK_INT >= 21) {
            super.setAllCaps(false);
        }
    }

    public void setCallback(Callback<TwitterSession> callback2) {
        if (callback2 != null) {
            this.callback = callback2;
            return;
        }
        throw new IllegalArgumentException("Callback cannot be null");
    }

    public Callback<TwitterSession> getCallback() {
        return this.callback;
    }

    /* access modifiers changed from: protected */
    public Activity getActivity() {
        if (getContext() instanceof Activity) {
            return (Activity) getContext();
        }
        if (isInEditMode()) {
            return null;
        }
        throw new IllegalStateException("TwitterLoginButton requires an activity. Override getActivity to provide the activity for this button.");
    }

    public void setOnClickListener(OnClickListener onClickListener2) {
        this.onClickListener = onClickListener2;
    }

    /* access modifiers changed from: 0000 */
    public TwitterAuthClient getTwitterAuthClient() {
        if (this.authClient == null) {
            synchronized (TwitterLoginButton.class) {
                if (this.authClient == null) {
                    this.authClient = new TwitterAuthClient();
                }
            }
        }
        return this.authClient;
    }

    private void checkTwitterCoreAndEnable() {
        if (!isInEditMode()) {
            try {
                TwitterCore.getInstance();
            } catch (IllegalStateException e) {
                Twitter.getLogger().e("Twitter", e.getMessage());
                setEnabled(false);
            }
        }
    }
}
