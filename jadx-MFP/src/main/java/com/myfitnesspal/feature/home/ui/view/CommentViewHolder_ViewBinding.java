package com.myfitnesspal.feature.home.ui.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.view.MfpImageView;

public class CommentViewHolder_ViewBinding implements Unbinder {
    private CommentViewHolder target;

    @UiThread
    public CommentViewHolder_ViewBinding(CommentViewHolder commentViewHolder, View view) {
        this.target = commentViewHolder;
        commentViewHolder.statusBody = (TextView) Utils.findRequiredViewAsType(view, R.id.status_text, "field 'statusBody'", TextView.class);
        commentViewHolder.date = (TextView) Utils.findRequiredViewAsType(view, R.id.date_text, "field 'date'", TextView.class);
        commentViewHolder.username = (TextView) Utils.findRequiredViewAsType(view, R.id.username, "field 'username'", TextView.class);
        commentViewHolder.txtDotSeparator = (TextView) Utils.findRequiredViewAsType(view, R.id.dot_separator, "field 'txtDotSeparator'", TextView.class);
        commentViewHolder.txtDotSeparator2 = (TextView) Utils.findRequiredViewAsType(view, R.id.dot_separator_2, "field 'txtDotSeparator2'", TextView.class);
        commentViewHolder.totalNumberOfLikes = (TextView) Utils.findRequiredViewAsType(view, R.id.like_count, "field 'totalNumberOfLikes'", TextView.class);
        commentViewHolder.likeCheckBox = (CheckBox) Utils.findRequiredViewAsType(view, R.id.liked, "field 'likeCheckBox'", CheckBox.class);
        commentViewHolder.image = (MfpImageView) Utils.findRequiredViewAsType(view, R.id.user_image, "field 'image'", MfpImageView.class);
        commentViewHolder.commentContainer = Utils.findRequiredView(view, R.id.comment_container, "field 'commentContainer'");
    }

    @CallSuper
    public void unbind() {
        CommentViewHolder commentViewHolder = this.target;
        if (commentViewHolder != null) {
            this.target = null;
            commentViewHolder.statusBody = null;
            commentViewHolder.date = null;
            commentViewHolder.username = null;
            commentViewHolder.txtDotSeparator = null;
            commentViewHolder.txtDotSeparator2 = null;
            commentViewHolder.totalNumberOfLikes = null;
            commentViewHolder.likeCheckBox = null;
            commentViewHolder.image = null;
            commentViewHolder.commentContainer = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
