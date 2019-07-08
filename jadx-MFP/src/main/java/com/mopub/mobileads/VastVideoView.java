package com.mopub.mobileads;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.os.AsyncTask.Status;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.VideoView;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.AsyncTasks;

public class VastVideoView extends VideoView {
    @Nullable
    private VastVideoBlurLastVideoFrameTask mBlurLastVideoFrameTask;
    @Nullable
    private MediaMetadataRetriever mMediaMetadataRetriever = new MediaMetadataRetriever();

    public VastVideoView(@NonNull Context context) {
        super(context);
        Preconditions.checkNotNull(context, "context cannot be null");
    }

    public void prepareBlurredLastVideoFrame(@NonNull ImageView imageView, @NonNull String str) {
        MediaMetadataRetriever mediaMetadataRetriever = this.mMediaMetadataRetriever;
        if (mediaMetadataRetriever != null) {
            this.mBlurLastVideoFrameTask = new VastVideoBlurLastVideoFrameTask(mediaMetadataRetriever, imageView, getDuration());
            try {
                AsyncTasks.safeExecuteOnExecutor(this.mBlurLastVideoFrameTask, str);
            } catch (Exception e) {
                MoPubLog.d("Failed to blur last video frame", e);
            }
        }
    }

    public void onDestroy() {
        VastVideoBlurLastVideoFrameTask vastVideoBlurLastVideoFrameTask = this.mBlurLastVideoFrameTask;
        if (vastVideoBlurLastVideoFrameTask != null && vastVideoBlurLastVideoFrameTask.getStatus() != Status.FINISHED) {
            this.mBlurLastVideoFrameTask.cancel(true);
        }
    }

    /* access modifiers changed from: 0000 */
    @Deprecated
    @VisibleForTesting
    public void setMediaMetadataRetriever(@NonNull MediaMetadataRetriever mediaMetadataRetriever) {
        this.mMediaMetadataRetriever = mediaMetadataRetriever;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    @Deprecated
    @VisibleForTesting
    public VastVideoBlurLastVideoFrameTask getBlurLastVideoFrameTask() {
        return this.mBlurLastVideoFrameTask;
    }

    /* access modifiers changed from: 0000 */
    @Deprecated
    @VisibleForTesting
    public void setBlurLastVideoFrameTask(@NonNull VastVideoBlurLastVideoFrameTask vastVideoBlurLastVideoFrameTask) {
        this.mBlurLastVideoFrameTask = vastVideoBlurLastVideoFrameTask;
    }
}
