package com.myfitnesspal.feature.profile.ui.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.addfriends.ui.activity.AddFriendsParent;
import com.myfitnesspal.feature.friends.ui.activity.FriendsActivity;
import com.myfitnesspal.feature.profile.service.ProfileAggregatorService.ProfileFriend;
import com.myfitnesspal.feature.profile.service.ProfileAggregatorService.ProfileFriends;
import com.myfitnesspal.feature.profile.ui.activity.ProfileView;
import com.myfitnesspal.shared.ui.view.MfpImageView;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import java.util.Collection;

public class FriendsCard extends MeCardBase {
    private View emptyView;
    private View friend1Container;
    private View friend2Container;
    private View friendsContainer;
    private boolean hasData;
    private View moreContainer;
    private TextView moreCount;
    private OnClickListener onAllFriendsClick;
    private OnClickListener onEmptyStateClickListener = new OnClickListener() {
        public void onClick(View view) {
            FriendsCard.this.getNavigationHelper().withIntent(AddFriendsParent.newStartIntent(FriendsCard.this.getContext())).startActivity();
        }
    };

    public String getAnalyticsType() {
        return "friends";
    }

    /* access modifiers changed from: protected */
    public int getButtonTextId() {
        return R.string.me_card_button_add_friends;
    }

    /* access modifiers changed from: protected */
    public int getContentLayoutId() {
        return R.layout.me_card_friends;
    }

    /* access modifiers changed from: protected */
    public int getLeftBadgeLayoutId() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public int getRightBadgeLayoutId() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public int getTitleTextId() {
        return R.string.me_card_title_friends;
    }

    public FriendsCard(@NonNull Context context) {
        super(context);
    }

    public FriendsCard(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FriendsCard(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i) {
        super(context, attributeSet, i);
    }

    private void drawEmpty() {
        ViewUtils.setVisible(this.emptyView);
        ViewUtils.setGone(this.friendsContainer);
        setOnContentViewClickListener(this.onEmptyStateClickListener);
    }

    private void drawPopulated(int i) {
        ViewUtils.setGone(this.emptyView);
        ViewUtils.setVisible(this.friendsContainer);
        setOnContentViewClickListener(i < 3 ? this.onAllFriendsClick : null);
    }

    public void redraw(ProfileFriends profileFriends) {
        if (profileFriends != null && !CollectionUtils.isEmpty((Collection<?>) profileFriends.getFriends())) {
            int totalFriendCount = profileFriends.getTotalFriendCount();
            drawPopulated(totalFriendCount);
            bindFriend(this.friend1Container, (ProfileFriend) profileFriends.getFriends().get(0));
            if (totalFriendCount > 1) {
                bindFriend(this.friend2Container, (ProfileFriend) profileFriends.getFriends().get(1));
            }
            int i = 4;
            this.friend2Container.setVisibility(totalFriendCount > 1 ? 0 : 4);
            View view = this.moreContainer;
            if (totalFriendCount > 2) {
                i = 0;
            }
            view.setVisibility(i);
            int i2 = totalFriendCount - 2;
            if (i2 > 999) {
                this.moreCount.setText(getContext().getString(R.string.me_card_friends_more_thousand, new Object[]{NumberUtils.localeStringFromDouble(((double) i2) / 1000.0d, 1)}));
            } else {
                this.moreCount.setText(getContext().getString(R.string.me_card_friends_more, new Object[]{Integer.valueOf(i2)}));
            }
            this.hasData = true;
        } else if (!this.hasData) {
            drawEmpty();
        }
    }

    /* access modifiers changed from: protected */
    public void onInflated() {
        this.emptyView = findViewById(R.id.emptyView);
        this.friendsContainer = findViewById(R.id.friendsContainer);
        this.friend1Container = findViewById(R.id.friend1);
        this.friend2Container = findViewById(R.id.friend2);
        this.moreContainer = findViewById(R.id.moreContainer);
        this.moreCount = (TextView) findViewById(R.id.moreCount);
        this.onAllFriendsClick = new OnClickListener() {
            public void onClick(View view) {
                FriendsCard.this.getNavigationHelper().withIntent(FriendsActivity.newStartIntent(FriendsCard.this.getContext())).startActivity();
            }
        };
        AnonymousClass2 r0 = new OnClickListener() {
            public void onClick(View view) {
                FriendsCard.this.getAnalytics().reportFriendTapped();
                FriendsCard.this.getNavigationHelper().withIntent(ProfileView.newStartIntent(FriendsCard.this.getContext(), ((ProfileFriend) view.getTag()).getUsername())).startActivity();
            }
        };
        this.moreContainer.setOnClickListener(this.onAllFriendsClick);
        this.friend1Container.setOnClickListener(r0);
        this.friend2Container.setOnClickListener(r0);
    }

    private void bindFriend(View view, ProfileFriend profileFriend) {
        MfpImageView mfpImageView = (MfpImageView) view.findViewById(R.id.icon);
        ((TextView) view.findViewById(R.id.title)).setText(profileFriend.getUsername());
        String imageUrl = profileFriend.getImageUrl();
        if (Strings.notEmpty(imageUrl)) {
            mfpImageView.setPlaceholderImageId(R.drawable.ic_profile);
            mfpImageView.setUrl(imageUrl);
        } else {
            mfpImageView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_profile));
        }
        view.setTag(profileFriend);
    }
}
