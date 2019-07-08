package com.myfitnesspal.shared.ui.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.uacf.core.util.ViewUtils;

public abstract class GenericCardBase extends CardView {
    @BindView(2131362032)
    protected TextView button;
    @BindView(2131362033)
    protected View buttonContainer;
    @BindView(2131362209)
    protected FrameLayout contentContainer;
    @BindView(2131362732)
    protected View headerAndContentContainer;
    @BindView(2131363835)
    protected FrameLayout leftBadgeContainer;
    @BindView(2131363836)
    protected FrameLayout rightBadgeContainer;
    @BindView(2131363833)
    protected TextView title;

    public abstract String getAnalyticsType();

    /* access modifiers changed from: protected */
    public abstract int getButtonTextId();

    /* access modifiers changed from: protected */
    public abstract int getContentLayoutId();

    /* access modifiers changed from: protected */
    public abstract int getLeftBadgeLayoutId();

    /* access modifiers changed from: protected */
    public abstract int getRightBadgeLayoutId();

    /* access modifiers changed from: protected */
    public abstract int getTitleTextId();

    /* access modifiers changed from: protected */
    public abstract void onInflated();

    /* access modifiers changed from: protected */
    public abstract void reportCardTapped();

    public GenericCardBase(Context context) {
        super(context);
        init();
    }

    public GenericCardBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public GenericCardBase(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public void setOnButtonClickListener(OnClickListener onClickListener) {
        this.button.setOnClickListener(onClickListener);
    }

    /* access modifiers changed from: protected */
    public void setOnContentViewClickListener(final OnClickListener onClickListener) {
        if (onClickListener == null) {
            this.headerAndContentContainer.setBackground(null);
            this.headerAndContentContainer.setOnClickListener(null);
            return;
        }
        this.headerAndContentContainer.setBackgroundResource(R.drawable.list_item_bg_selector);
        this.headerAndContentContainer.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                GenericCardBase.this.reportCardTapped();
                onClickListener.onClick(view);
            }
        });
    }

    /* access modifiers changed from: protected */
    public final NavigationHelper getNavigationHelper() {
        return ((MfpActivity) getContext()).getNavigationHelper();
    }

    private void init() {
        LayoutInflater from = LayoutInflater.from(getContext());
        from.inflate(R.layout.generic_card_base, this, true);
        ButterKnife.bind((View) this);
        int contentLayoutId = getContentLayoutId();
        if (contentLayoutId != 0) {
            from.inflate(contentLayoutId, this.contentContainer, true);
            this.title.setText(getTitleTextId());
            int buttonTextId = getButtonTextId();
            if (buttonTextId == 0) {
                ViewUtils.setGone(this.buttonContainer);
            } else {
                this.button.setText(buttonTextId);
            }
            int leftBadgeLayoutId = getLeftBadgeLayoutId();
            if (leftBadgeLayoutId != 0) {
                from.inflate(leftBadgeLayoutId, this.leftBadgeContainer, true);
            }
            int rightBadgeLayoutId = getRightBadgeLayoutId();
            if (rightBadgeLayoutId != 0) {
                from.inflate(rightBadgeLayoutId, this.rightBadgeContainer, true);
            }
            onInflated();
            return;
        }
        throw new IllegalStateException("cannot have an empty content layout");
    }
}
