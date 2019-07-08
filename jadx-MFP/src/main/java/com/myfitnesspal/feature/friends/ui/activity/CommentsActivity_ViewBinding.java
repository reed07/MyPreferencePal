package com.myfitnesspal.feature.friends.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class CommentsActivity_ViewBinding implements Unbinder {
    private CommentsActivity target;

    @UiThread
    public CommentsActivity_ViewBinding(CommentsActivity commentsActivity) {
        this(commentsActivity, commentsActivity.getWindow().getDecorView());
    }

    @UiThread
    public CommentsActivity_ViewBinding(CommentsActivity commentsActivity, View view) {
        this.target = commentsActivity;
        commentsActivity.commentsRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.comments_view, "field 'commentsRecyclerView'", RecyclerView.class);
        commentsActivity.commentEditText = (EditText) Utils.findRequiredViewAsType(view, R.id.comment_input, "field 'commentEditText'", EditText.class);
    }

    @CallSuper
    public void unbind() {
        CommentsActivity commentsActivity = this.target;
        if (commentsActivity != null) {
            this.target = null;
            commentsActivity.commentsRecyclerView = null;
            commentsActivity.commentEditText = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
