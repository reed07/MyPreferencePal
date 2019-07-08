package com.myfitnesspal.feature.profile.ui.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.appgallery.ui.AppGalleryActivity;
import com.myfitnesspal.feature.profile.analytics.MeAnalytics.CardType;
import com.myfitnesspal.shared.model.v2.MfpAppImage;
import com.myfitnesspal.shared.model.v2.MfpPlatformApp;
import com.myfitnesspal.shared.ui.view.MfpImageView;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import java.util.Collection;
import java.util.List;

public class AppsCard extends MeCardBase {
    private View app1Container;
    private View app2Container;
    private View appsContainer;
    private View emptyView;
    private boolean hasData;
    private OnClickListener onEmptyStateClickListener = new OnClickListener() {
        public void onClick(View view) {
            AppsCard.this.getNavigationHelper().withIntent(AppGalleryActivity.newStartIntent(AppsCard.this.getContext())).startActivity();
        }
    };

    public String getAnalyticsType() {
        return CardType.APPS_DEVICES;
    }

    /* access modifiers changed from: protected */
    public int getButtonTextId() {
        return R.string.me_card_button_apps;
    }

    /* access modifiers changed from: protected */
    public int getContentLayoutId() {
        return R.layout.me_card_apps;
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
        return R.string.me_card_title_apps;
    }

    public AppsCard(@NonNull Context context) {
        super(context);
    }

    public AppsCard(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AppsCard(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i) {
        super(context, attributeSet, i);
    }

    public void redraw(List<MfpPlatformApp> list) {
        boolean z = false;
        if (CollectionUtils.notEmpty((Collection<?>) list)) {
            z = bindAppToView(this.app1Container, list, 0) | bindAppToView(this.app2Container, list, 1);
        }
        if (z || this.hasData) {
            this.hasData = true;
            showAsPopulated();
            return;
        }
        showAsEmpty();
    }

    private void showAsPopulated() {
        ViewUtils.setVisible(this.appsContainer);
        ViewUtils.setGone(this.emptyView);
        setOnContentViewClickListener(null);
    }

    private void showAsEmpty() {
        ViewUtils.setVisible(this.emptyView);
        ViewUtils.setGone(this.appsContainer);
        setOnContentViewClickListener(this.onEmptyStateClickListener);
    }

    /* access modifiers changed from: protected */
    public void onInflated() {
        this.emptyView = findViewById(R.id.emptyView);
        this.appsContainer = findViewById(R.id.appsContainer);
        this.app1Container = findViewById(R.id.app1);
        this.app2Container = findViewById(R.id.app2);
        AnonymousClass1 r0 = new OnClickListener() {
            public void onClick(View view) {
                MfpPlatformApp mfpPlatformApp = (MfpPlatformApp) view.getTag();
                AppsCard.this.getAnalytics().reportAppTapped(mfpPlatformApp.getName());
                AppsCard.this.getNavigationHelper().withIntent(AppGalleryActivity.newStartIntent(AppsCard.this.getContext(), mfpPlatformApp.getAppId())).startActivity();
            }
        };
        this.app1Container.setOnClickListener(r0);
        this.app2Container.setOnClickListener(r0);
    }

    private boolean bindAppToView(View view, List<MfpPlatformApp> list, int i) {
        if (list == null || list.size() <= i) {
            ViewUtils.setInvisible(view);
            return false;
        }
        MfpPlatformApp mfpPlatformApp = (MfpPlatformApp) list.get(i);
        view.setTag(mfpPlatformApp);
        ((TextView) view.findViewById(R.id.title)).setText(mfpPlatformApp.getName());
        MfpImageView mfpImageView = (MfpImageView) view.findViewById(R.id.icon);
        MfpAppImage iconImage = mfpPlatformApp.getIconImage();
        if (iconImage == null || !Strings.notEmpty(iconImage.getFilename())) {
            mfpImageView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_me_apps));
        } else {
            Glide.with(getContext()).load(iconImage.getFilename()).apply(new RequestOptions().placeholder((int) R.drawable.ic_me_apps)).into((ImageView) mfpImageView);
        }
        return true;
    }
}
