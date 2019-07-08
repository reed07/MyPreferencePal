package com.myfitnesspal.feature.home.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.uacf.core.util.ViewUtils;

public class NotifBellHelper {
    private final String NOTIF_STRING_9PLUS = "9+";
    private final int NOTIF_THRESHOLD = 9;
    /* access modifiers changed from: private */
    public boolean hasNotifIconBeenAnimated;
    private ImageView ivNotifIcon;
    private View notifView;
    private int pendingNotifCount;
    private TextView tvNotifCount;

    public View getNotifView() {
        return this.notifView;
    }

    public void setPendingNotifCount(int i) {
        this.pendingNotifCount = i;
    }

    public void setHasNotifIconBeenAnimated(boolean z) {
        this.hasNotifIconBeenAnimated = z;
    }

    public void inflateParent(LayoutInflater layoutInflater, Toolbar toolbar) {
        this.notifView = layoutInflater.inflate(R.layout.notification_icon_with_count, toolbar, false);
        this.tvNotifCount = (TextView) this.notifView.findViewById(R.id.notif_count);
        this.ivNotifIcon = (ImageView) this.notifView.findViewById(R.id.notif_icon);
    }

    @NonNull
    private Animation getAnimation(Context context) {
        Animation loadAnimation = AnimationUtils.loadAnimation(context, R.anim.notif_bell_rotate);
        loadAnimation.setDuration(800);
        loadAnimation.setFillAfter(true);
        loadAnimation.setInterpolator(new BounceInterpolator());
        loadAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                NotifBellHelper.this.hasNotifIconBeenAnimated = true;
                NotifBellHelper.this.setupNotifText(true);
            }
        });
        return loadAnimation;
    }

    /* access modifiers changed from: private */
    public void setupNotifText(boolean z) {
        ViewUtils.setVisible(this.tvNotifCount);
        if (z) {
            this.tvNotifCount.startAnimation(getNotifCountFadeInAnimation());
        }
        this.tvNotifCount.setText(getNotifCountText());
    }

    private String getNotifCountText() {
        int i = this.pendingNotifCount;
        return i > 9 ? "9+" : String.valueOf(i);
    }

    @NonNull
    private Animation getNotifCountFadeInAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(BitmapDescriptorFactory.HUE_RED, 1.0f);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());
        alphaAnimation.setDuration(250);
        return alphaAnimation;
    }

    public void refreshNotifIcon(Context context, ConfigService configService) {
        if (this.ivNotifIcon != null) {
            setIcon();
            if (!(this.pendingNotifCount > 0)) {
                ViewUtils.setGone(this.tvNotifCount);
            } else if (!this.hasNotifIconBeenAnimated) {
                this.ivNotifIcon.startAnimation(getAnimation(context));
            } else {
                this.ivNotifIcon.setRotation(-25.0f);
                setupNotifText(false);
            }
        }
    }

    public void hideCount() {
        ViewUtils.setGone(this.tvNotifCount);
    }

    public void setIcon() {
        this.ivNotifIcon.setImageResource(R.drawable.ic_bell);
    }
}
