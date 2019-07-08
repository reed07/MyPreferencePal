package com.myfitnesspal.feature.explore.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.community.service.CommunityService;
import com.myfitnesspal.feature.community.ui.activity.CommunityActivity;
import com.myfitnesspal.feature.explore.analytics.ExploreAnalytics.CommunityType;
import com.myfitnesspal.shared.ui.navigation.SharedIntents;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;

public class CommunityCard extends ExploreCardBase {
    private static final String FACEBOOK_URL = "https://www.facebook.com/myfitnesspal";
    private static final String INSTAGRAM_URL = "https://www.instagram.com/myfitnesspal/";
    private static final String PINTEREST_URL = "https://www.pinterest.com/myfitnesspal";
    private OnClickListener onForumsClicked;

    private class ButtonTag {
        public String type;
        public String url;

        public ButtonTag(String str, String str2) {
            this.url = str;
            this.type = str2;
        }
    }

    public String getAnalyticsType() {
        return "community";
    }

    /* access modifiers changed from: protected */
    public int getButtonTextId() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public int getContentLayoutId() {
        return R.layout.explore_card_community;
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
        return R.string.explore_card_title_community;
    }

    public CommunityCard(Context context) {
        super(context);
    }

    public CommunityCard(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CommunityCard(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void render(Lazy<CommunityService> lazy) {
        boolean isCommunityEnabled = ((CommunityService) lazy.get()).isCommunityEnabled();
        ViewUtils.setVisible(isCommunityEnabled, findViewById(R.id.forumsAvailableContainer));
        setOnContentViewClickListener(isCommunityEnabled ? this.onForumsClicked : null);
    }

    /* access modifiers changed from: protected */
    public void onInflated() {
        AnonymousClass1 r0 = new OnClickListener() {
            public void onClick(View view) {
                try {
                    ButtonTag buttonTag = (ButtonTag) view.getTag();
                    CommunityCard.this.getAnalytics().reportCommunityTapped(buttonTag.type);
                    CommunityCard.this.getNavigationHelper().withIntent(SharedIntents.newUriIntent(buttonTag.url)).startActivity();
                } catch (Exception unused) {
                }
            }
        };
        this.onForumsClicked = new OnClickListener() {
            public void onClick(View view) {
                CommunityCard.this.getAnalytics().reportCommunityTapped(CommunityType.FORUM);
                CommunityCard.this.getNavigationHelper().withIntent(CommunityActivity.newStartIntent(CommunityCard.this.getContext())).startActivity();
            }
        };
        findViewById(R.id.facebookButton).setTag(new ButtonTag(FACEBOOK_URL, "fb"));
        findViewById(R.id.facebookButton).setOnClickListener(r0);
        findViewById(R.id.instagramButton).setTag(new ButtonTag(INSTAGRAM_URL, CommunityType.INSTAGRAM));
        findViewById(R.id.instagramButton).setOnClickListener(r0);
        findViewById(R.id.pinterestButton).setTag(new ButtonTag(PINTEREST_URL, CommunityType.PINTEREST));
        findViewById(R.id.pinterestButton).setOnClickListener(r0);
        findViewById(R.id.forumsButton).setOnClickListener(this.onForumsClicked);
    }
}
