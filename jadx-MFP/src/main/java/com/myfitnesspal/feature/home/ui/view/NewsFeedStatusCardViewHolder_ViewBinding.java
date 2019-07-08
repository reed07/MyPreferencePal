package com.myfitnesspal.feature.home.ui.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.view.MfpImageView;

public class NewsFeedStatusCardViewHolder_ViewBinding implements Unbinder {
    private NewsFeedStatusCardViewHolder target;

    @UiThread
    public NewsFeedStatusCardViewHolder_ViewBinding(NewsFeedStatusCardViewHolder newsFeedStatusCardViewHolder, View view) {
        this.target = newsFeedStatusCardViewHolder;
        newsFeedStatusCardViewHolder.avatarImg = (MfpImageView) Utils.findRequiredViewAsType(view, R.id.user_avatar, "field 'avatarImg'", MfpImageView.class);
        newsFeedStatusCardViewHolder.cardActionBtn = (ImageButton) Utils.findRequiredViewAsType(view, R.id.cardAction, "field 'cardActionBtn'", ImageButton.class);
        newsFeedStatusCardViewHolder.likeBtn = Utils.findRequiredView(view, R.id.likeBtn, "field 'likeBtn'");
        newsFeedStatusCardViewHolder.commentBtn = Utils.findRequiredView(view, R.id.commentBtn, "field 'commentBtn'");
        newsFeedStatusCardViewHolder.userNameTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.username, "field 'userNameTxt'", TextView.class);
        newsFeedStatusCardViewHolder.dateTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.date, "field 'dateTxt'", TextView.class);
        newsFeedStatusCardViewHolder.partnerAppInfoTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.partner_app_info, "field 'partnerAppInfoTxt'", TextView.class);
        newsFeedStatusCardViewHolder.statusTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.status, "field 'statusTxt'", TextView.class);
        newsFeedStatusCardViewHolder.numberOfComments = (TextView) Utils.findRequiredViewAsType(view, R.id.numberOfCommentsTxt, "field 'numberOfComments'", TextView.class);
        newsFeedStatusCardViewHolder.numberOfLikes = (TextView) Utils.findRequiredViewAsType(view, R.id.numberOfLikesTxt, "field 'numberOfLikes'", TextView.class);
        newsFeedStatusCardViewHolder.likeTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.like_button_text, "field 'likeTextView'", TextView.class);
        newsFeedStatusCardViewHolder.commentTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.comment_btn_text, "field 'commentTextView'", TextView.class);
        newsFeedStatusCardViewHolder.statusImageViewArea = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.view_area, "field 'statusImageViewArea'", FrameLayout.class);
        newsFeedStatusCardViewHolder.statusImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.ivStatusImage, "field 'statusImage'", ImageView.class);
        newsFeedStatusCardViewHolder.viewImageFailed = Utils.findRequiredView(view, R.id.viewImageFailed, "field 'viewImageFailed'");
        newsFeedStatusCardViewHolder.progressBar = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.progress, "field 'progressBar'", ProgressBar.class);
        newsFeedStatusCardViewHolder.tvPhotoLoadFailed = (TextView) Utils.findRequiredViewAsType(view, R.id.tvPhotoLoadFailed, "field 'tvPhotoLoadFailed'", TextView.class);
        newsFeedStatusCardViewHolder.tvTryAgain = (TextView) Utils.findRequiredViewAsType(view, R.id.tvTryAgain, "field 'tvTryAgain'", TextView.class);
        newsFeedStatusCardViewHolder.llLikesComments = Utils.findRequiredView(view, R.id.likes_comments_row, "field 'llLikesComments'");
        newsFeedStatusCardViewHolder.viewMealButton = Utils.findRequiredView(view, R.id.view_meal_btn, "field 'viewMealButton'");
    }

    @CallSuper
    public void unbind() {
        NewsFeedStatusCardViewHolder newsFeedStatusCardViewHolder = this.target;
        if (newsFeedStatusCardViewHolder != null) {
            this.target = null;
            newsFeedStatusCardViewHolder.avatarImg = null;
            newsFeedStatusCardViewHolder.cardActionBtn = null;
            newsFeedStatusCardViewHolder.likeBtn = null;
            newsFeedStatusCardViewHolder.commentBtn = null;
            newsFeedStatusCardViewHolder.userNameTxt = null;
            newsFeedStatusCardViewHolder.dateTxt = null;
            newsFeedStatusCardViewHolder.partnerAppInfoTxt = null;
            newsFeedStatusCardViewHolder.statusTxt = null;
            newsFeedStatusCardViewHolder.numberOfComments = null;
            newsFeedStatusCardViewHolder.numberOfLikes = null;
            newsFeedStatusCardViewHolder.likeTextView = null;
            newsFeedStatusCardViewHolder.commentTextView = null;
            newsFeedStatusCardViewHolder.statusImageViewArea = null;
            newsFeedStatusCardViewHolder.statusImage = null;
            newsFeedStatusCardViewHolder.viewImageFailed = null;
            newsFeedStatusCardViewHolder.progressBar = null;
            newsFeedStatusCardViewHolder.tvPhotoLoadFailed = null;
            newsFeedStatusCardViewHolder.tvTryAgain = null;
            newsFeedStatusCardViewHolder.llLikesComments = null;
            newsFeedStatusCardViewHolder.viewMealButton = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
