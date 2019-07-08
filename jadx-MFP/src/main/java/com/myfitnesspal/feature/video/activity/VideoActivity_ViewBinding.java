package com.myfitnesspal.feature.video.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.brightcove.player.view.BrightcoveVideoView;
import com.myfitnesspal.android.R;

public class VideoActivity_ViewBinding implements Unbinder {
    private VideoActivity target;

    @UiThread
    public VideoActivity_ViewBinding(VideoActivity videoActivity) {
        this(videoActivity, videoActivity.getWindow().getDecorView());
    }

    @UiThread
    public VideoActivity_ViewBinding(VideoActivity videoActivity, View view) {
        this.target = videoActivity;
        videoActivity.videoView = (BrightcoveVideoView) Utils.findRequiredViewAsType(view, R.id.video, "field 'videoView'", BrightcoveVideoView.class);
        videoActivity.listContainer = Utils.findRequiredView(view, R.id.list_container, "field 'listContainer'");
        videoActivity.videosList = (RecyclerView) Utils.findOptionalViewAsType(view, R.id.videos, "field 'videosList'", RecyclerView.class);
        videoActivity.emptyListView = Utils.findRequiredView(view, R.id.empty_list_container, "field 'emptyListView'");
        videoActivity.progressBar = (ProgressBar) Utils.findOptionalViewAsType(view, R.id.loading, "field 'progressBar'", ProgressBar.class);
        videoActivity.videoContainer = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.video_container, "field 'videoContainer'", ConstraintLayout.class);
        videoActivity.imageViewContainer = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.video_image_container, "field 'imageViewContainer'", ViewGroup.class);
        videoActivity.videoImageView = (ImageView) Utils.findRequiredViewAsType(view, R.id.video_image, "field 'videoImageView'", ImageView.class);
        videoActivity.activeVideoErrorContainer = Utils.findRequiredView(view, R.id.active_video_error_container, "field 'activeVideoErrorContainer'");
        videoActivity.activeVideoErrorMsg = (TextView) Utils.findRequiredViewAsType(view, R.id.active_video_error_msg, "field 'activeVideoErrorMsg'", TextView.class);
        videoActivity.adView = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.ad_view, "field 'adView'", ViewGroup.class);
    }

    @CallSuper
    public void unbind() {
        VideoActivity videoActivity = this.target;
        if (videoActivity != null) {
            this.target = null;
            videoActivity.videoView = null;
            videoActivity.listContainer = null;
            videoActivity.videosList = null;
            videoActivity.emptyListView = null;
            videoActivity.progressBar = null;
            videoActivity.videoContainer = null;
            videoActivity.imageViewContainer = null;
            videoActivity.videoImageView = null;
            videoActivity.activeVideoErrorContainer = null;
            videoActivity.activeVideoErrorMsg = null;
            videoActivity.adView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
