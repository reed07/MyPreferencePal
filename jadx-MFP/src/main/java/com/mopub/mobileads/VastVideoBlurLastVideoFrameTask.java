package com.mopub.mobileads;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.ImageUtils;

public class VastVideoBlurLastVideoFrameTask extends AsyncTask<String, Void, Boolean> {
    @Nullable
    private Bitmap mBlurredLastVideoFrame;
    @NonNull
    private final ImageView mBlurredLastVideoFrameImageView;
    @Nullable
    private Bitmap mLastVideoFrame;
    @NonNull
    private final MediaMetadataRetriever mMediaMetadataRetriever;
    private int mVideoDuration;

    public VastVideoBlurLastVideoFrameTask(@NonNull MediaMetadataRetriever mediaMetadataRetriever, @NonNull ImageView imageView, int i) {
        this.mMediaMetadataRetriever = mediaMetadataRetriever;
        this.mBlurredLastVideoFrameImageView = imageView;
        this.mVideoDuration = i;
    }

    /* access modifiers changed from: protected */
    public Boolean doInBackground(String... strArr) {
        if (strArr == null || strArr.length == 0 || strArr[0] == null) {
            return Boolean.valueOf(false);
        }
        try {
            this.mMediaMetadataRetriever.setDataSource(strArr[0]);
            this.mLastVideoFrame = this.mMediaMetadataRetriever.getFrameAtTime((long) ((this.mVideoDuration * 1000) - 200000), 3);
            if (this.mLastVideoFrame == null) {
                return Boolean.valueOf(false);
            }
            this.mBlurredLastVideoFrame = ImageUtils.applyFastGaussianBlurToBitmap(this.mLastVideoFrame, 4);
            return Boolean.valueOf(true);
        } catch (Exception e) {
            MoPubLog.d("Failed to blur last video frame", e);
            return Boolean.valueOf(false);
        }
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Boolean bool) {
        if (isCancelled()) {
            onCancelled();
            return;
        }
        if (bool != null && bool.booleanValue()) {
            this.mBlurredLastVideoFrameImageView.setImageBitmap(this.mBlurredLastVideoFrame);
            this.mBlurredLastVideoFrameImageView.setImageAlpha(100);
        }
    }

    /* access modifiers changed from: protected */
    public void onCancelled() {
        MoPubLog.d("VastVideoBlurLastVideoFrameTask was cancelled.");
    }
}
