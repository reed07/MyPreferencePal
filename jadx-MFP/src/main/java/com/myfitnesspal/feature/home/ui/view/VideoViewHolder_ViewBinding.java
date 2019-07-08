package com.myfitnesspal.feature.home.ui.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.brightcove.player.view.BrightcoveExoPlayerVideoView;
import com.myfitnesspal.android.R;

public class VideoViewHolder_ViewBinding implements Unbinder {
    private VideoViewHolder target;

    @UiThread
    public VideoViewHolder_ViewBinding(VideoViewHolder videoViewHolder, View view) {
        this.target = videoViewHolder;
        videoViewHolder.titleTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.title, "field 'titleTextView'", TextView.class);
        videoViewHolder.blogLinkTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.blog_link, "field 'blogLinkTextView'", TextView.class);
        videoViewHolder.imageViewContainer = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.video_image_container, "field 'imageViewContainer'", ViewGroup.class);
        videoViewHolder.videoImageView = (ImageView) Utils.findRequiredViewAsType(view, R.id.video_image, "field 'videoImageView'", ImageView.class);
        videoViewHolder.videoView = (BrightcoveExoPlayerVideoView) Utils.findRequiredViewAsType(view, R.id.video, "field 'videoView'", BrightcoveExoPlayerVideoView.class);
        videoViewHolder.videoContainer = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.video_container, "field 'videoContainer'", ConstraintLayout.class);
        videoViewHolder.mute = Utils.findRequiredView(view, R.id.mute, "field 'mute'");
    }

    @CallSuper
    public void unbind() {
        VideoViewHolder videoViewHolder = this.target;
        if (videoViewHolder != null) {
            this.target = null;
            videoViewHolder.titleTextView = null;
            videoViewHolder.blogLinkTextView = null;
            videoViewHolder.imageViewContainer = null;
            videoViewHolder.videoImageView = null;
            videoViewHolder.videoView = null;
            videoViewHolder.videoContainer = null;
            videoViewHolder.mute = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
