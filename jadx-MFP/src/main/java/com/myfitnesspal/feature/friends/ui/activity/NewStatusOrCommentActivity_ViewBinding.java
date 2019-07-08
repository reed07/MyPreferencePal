package com.myfitnesspal.feature.friends.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class NewStatusOrCommentActivity_ViewBinding implements Unbinder {
    private NewStatusOrCommentActivity target;

    @UiThread
    public NewStatusOrCommentActivity_ViewBinding(NewStatusOrCommentActivity newStatusOrCommentActivity) {
        this(newStatusOrCommentActivity, newStatusOrCommentActivity.getWindow().getDecorView());
    }

    @UiThread
    public NewStatusOrCommentActivity_ViewBinding(NewStatusOrCommentActivity newStatusOrCommentActivity, View view) {
        this.target = newStatusOrCommentActivity;
        newStatusOrCommentActivity.status = (EditText) Utils.findRequiredViewAsType(view, R.id.editTextStatus, "field 'status'", EditText.class);
        newStatusOrCommentActivity.separator = Utils.findRequiredView(view, R.id.vSep, "field 'separator'");
        newStatusOrCommentActivity.tvAddProgressPic = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_add_pic, "field 'tvAddProgressPic'", TextView.class);
        newStatusOrCommentActivity.ivStatusPic = (ImageView) Utils.findOptionalViewAsType(view, R.id.iv_status_image, "field 'ivStatusPic'", ImageView.class);
    }

    @CallSuper
    public void unbind() {
        NewStatusOrCommentActivity newStatusOrCommentActivity = this.target;
        if (newStatusOrCommentActivity != null) {
            this.target = null;
            newStatusOrCommentActivity.status = null;
            newStatusOrCommentActivity.separator = null;
            newStatusOrCommentActivity.tvAddProgressPic = null;
            newStatusOrCommentActivity.ivStatusPic = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
