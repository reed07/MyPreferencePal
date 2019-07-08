package com.myfitnesspal.feature.profile.ui.view;

import android.app.Activity;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout.LayoutParams;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.premium.ui.activity.PremiumUpsellActivity;
import com.myfitnesspal.feature.profile.ui.activity.MeActivity;
import com.myfitnesspal.feature.profile.ui.behavior.ProfileHeaderBehavior;
import com.myfitnesspal.feature.profile.util.LocationUtil;
import com.myfitnesspal.shared.api.ApiErrorCallback;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v1.MiniUserInfo;
import com.myfitnesspal.shared.model.v15.UserSummaryObject;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserSummaryService;
import com.myfitnesspal.shared.ui.view.MfpImageView;
import com.myfitnesspal.shared.util.ActivityUtils;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;

public class MeHeaderViews {
    private MeActivity activity;
    @BindView(2131362889)
    MfpImageView appbarImage;
    @BindView(2131363858)
    TextView appbarLocation;
    @BindView(2131363859)
    TextView appbarStreak;
    @BindView(2131363862)
    TextView appbarTitle;
    private String imageUri;
    @BindView(2131363856)
    View premiumButton;
    private Session session;
    @BindView(2131362890)
    RelativeLayout toolbarContainer;
    @BindView(2131363857)
    MfpImageView toolbarImage;
    @BindView(2131363855)
    AppBarLayout toolbarLayout;
    @BindView(2131363861)
    TextView toolbarTitle;
    /* access modifiers changed from: private */
    public boolean updating;
    /* access modifiers changed from: private */
    public UserSummaryObject userSummary;
    private UserSummaryService userSummaryService;

    public MeHeaderViews(final MeActivity meActivity, UserSummaryService userSummaryService2) {
        this.activity = meActivity;
        this.userSummaryService = userSummaryService2;
        this.session = meActivity.getSession();
        this.userSummary = userSummaryService2.getCachedUserSummary(this.session.getUser().getUsername());
        ButterKnife.bind((Object) this, (Activity) meActivity);
        this.premiumButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                meActivity.getNavigationHelper().withIntent(PremiumUpsellActivity.newStartIntent(meActivity)).startActivity();
            }
        });
        refreshUserSummary();
    }

    public void resume() {
        this.imageUri = this.session.getUser().getUserV1().getImageUrl();
        update();
    }

    public void update() {
        boolean z;
        if (this.userSummary == null) {
            refreshUserSummary();
        }
        if (this.activity.isEnabled()) {
            String username = this.session.getUser().getUsername();
            this.toolbarTitle.setText(username);
            this.appbarTitle.setText(username);
            UserSummaryObject userSummaryObject = this.userSummary;
            if (userSummaryObject == null) {
                ViewUtils.setInvisible(this.appbarStreak, this.appbarLocation);
            } else {
                int loginStreak = userSummaryObject.getLoginStreak();
                String city = this.userSummary.getCity();
                String state = this.userSummary.getState();
                String countryName = this.session.getUser().getUserV1().getProfile().getCountryName();
                ViewUtils.setVisible(this.appbarStreak, this.appbarLocation);
                this.appbarStreak.setText(this.activity.getResources().getQuantityString(R.plurals.me_header_streak, loginStreak, new Object[]{Integer.valueOf(loginStreak)}));
                this.appbarLocation.setText(LocationUtil.getFormattedLocation(countryName, state, city));
            }
            if (Strings.notEmpty(this.imageUri)) {
                this.toolbarImage.setUrl(this.imageUri);
                this.appbarImage.setUrl(this.imageUri);
            }
            z = this.activity.showPremiumUpsell() != ViewUtils.isVisible(this.premiumButton);
            ViewUtils.setVisible(this.activity.showPremiumUpsell(), this.premiumButton);
        } else {
            z = false;
        }
        if (z && (this.toolbarContainer.getLayoutParams() instanceof LayoutParams)) {
            LayoutParams layoutParams = (LayoutParams) this.toolbarContainer.getLayoutParams();
            if (layoutParams.getBehavior() instanceof ProfileHeaderBehavior) {
                ((ProfileHeaderBehavior) layoutParams.getBehavior()).onLayoutChanged();
            }
        }
        if (ActivityUtils.isInMultiWindow(this.activity) && ActivityUtils.isLandscape(this.activity)) {
            this.toolbarLayout.setExpanded(false);
            this.toolbarContainer.getLayoutParams().height = 0;
        }
    }

    private void refreshUserSummary() {
        if (this.userSummary == null) {
            this.userSummary = this.userSummaryService.getCachedUserSummary(this.session.getUser().getUsername());
        }
        if (!this.updating) {
            this.updating = true;
            MiniUserInfo forCurrentUser = MiniUserInfo.forCurrentUser(this.session);
            if (forCurrentUser != null) {
                this.userSummaryService.fetchUserSummaryAsync(forCurrentUser.getUsername(), forCurrentUser.getUid(), new Function1<UserSummaryObject>() {
                    public void execute(UserSummaryObject userSummaryObject) {
                        MeHeaderViews.this.updating = false;
                        if (userSummaryObject != null) {
                            MeHeaderViews.this.userSummary = userSummaryObject;
                            MeHeaderViews.this.update();
                        }
                    }
                }, new ApiErrorCallback() {
                    public void execute(ApiException apiException) {
                        MeHeaderViews.this.updating = false;
                    }
                });
            }
        }
    }
}
