package com.myfitnesspal.feature.home.ui.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.view.MfpImageView;

public class ProfileHeaderViewHolder_ViewBinding implements Unbinder {
    private ProfileHeaderViewHolder target;

    @UiThread
    public ProfileHeaderViewHolder_ViewBinding(ProfileHeaderViewHolder profileHeaderViewHolder, View view) {
        this.target = profileHeaderViewHolder;
        profileHeaderViewHolder.profileLayout = Utils.findRequiredView(view, R.id.profileLayout, "field 'profileLayout'");
        profileHeaderViewHolder.usernameTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.userName, "field 'usernameTextView'", TextView.class);
        profileHeaderViewHolder.loginDate = (TextView) Utils.findRequiredViewAsType(view, R.id.lastLoginDate, "field 'loginDate'", TextView.class);
        profileHeaderViewHolder.lostLabel = (TextView) Utils.findRequiredViewAsType(view, R.id.lost, "field 'lostLabel'", TextView.class);
        profileHeaderViewHolder.units = (TextView) Utils.findRequiredViewAsType(view, R.id.unitsTextView, "field 'units'", TextView.class);
        profileHeaderViewHolder.profileImageView = (MfpImageView) Utils.findRequiredViewAsType(view, R.id.profileImageView, "field 'profileImageView'", MfpImageView.class);
        profileHeaderViewHolder.profileActions = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.frame_actions, "field 'profileActions'", FrameLayout.class);
        profileHeaderViewHolder.premiumCtaContainer = Utils.findRequiredView(view, R.id.premium_cta_container, "field 'premiumCtaContainer'");
    }

    @CallSuper
    public void unbind() {
        ProfileHeaderViewHolder profileHeaderViewHolder = this.target;
        if (profileHeaderViewHolder != null) {
            this.target = null;
            profileHeaderViewHolder.profileLayout = null;
            profileHeaderViewHolder.usernameTextView = null;
            profileHeaderViewHolder.loginDate = null;
            profileHeaderViewHolder.lostLabel = null;
            profileHeaderViewHolder.units = null;
            profileHeaderViewHolder.profileImageView = null;
            profileHeaderViewHolder.profileActions = null;
            profileHeaderViewHolder.premiumCtaContainer = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
