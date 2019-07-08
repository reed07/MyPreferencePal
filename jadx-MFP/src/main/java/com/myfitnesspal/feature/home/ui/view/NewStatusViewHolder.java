package com.myfitnesspal.feature.home.ui.view;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.home.listener.NewsFeedItemActionListener;
import com.myfitnesspal.feature.home.model.NewsFeedItem;
import com.myfitnesspal.feature.home.service.NewsFeedAnalyticsHelper;
import com.myfitnesspal.shared.ui.view.MfpImageView;
import com.myfitnesspal.shared.ui.view.RecyclerViewHolder;
import dagger.Lazy;

public class NewStatusViewHolder extends RecyclerViewHolder<NewsFeedItem> {
    @BindView(2131362072)
    View cameraButton;
    /* access modifiers changed from: private */
    public final NewsFeedItemActionListener listener;
    @BindView(2131363139)
    View newStatusContainer;
    /* access modifiers changed from: private */
    public final Lazy<NewsFeedAnalyticsHelper> newsFeedAnalyticsHelper;
    private OnClickListener onClickListener = new OnClickListener() {
        public void onClick(View view) {
            ((NewsFeedAnalyticsHelper) NewStatusViewHolder.this.newsFeedAnalyticsHelper.get()).reportNewsfeedAddStatusClicked();
            if (NewStatusViewHolder.this.listener != null) {
                NewStatusViewHolder.this.listener.onUpdateStatusClick();
            }
        }
    };
    private final String profileImageUrl;
    @BindView(2131363313)
    MfpImageView profileImageView;

    public NewStatusViewHolder(ViewGroup viewGroup, Lazy<NewsFeedAnalyticsHelper> lazy, final NewsFeedItemActionListener newsFeedItemActionListener, String str) {
        super(R.layout.newsfeed_post_status, viewGroup);
        this.newsFeedAnalyticsHelper = lazy;
        this.listener = newsFeedItemActionListener;
        this.profileImageUrl = str;
        this.profileImageView.setTransformCircular(true);
        this.cameraButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                NewsFeedItemActionListener newsFeedItemActionListener = newsFeedItemActionListener;
                if (newsFeedItemActionListener != null) {
                    newsFeedItemActionListener.onCameraClick();
                }
            }
        });
    }

    public void setData(NewsFeedItem newsFeedItem, int i) {
        getParent().setOnClickListener(this.onClickListener);
        this.profileImageView.setUrl(this.profileImageUrl);
    }
}
