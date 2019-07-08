package com.myfitnesspal.feature.video.task;

import android.content.Context;
import android.support.annotation.NonNull;
import com.brightcove.player.edge.Catalog;
import com.brightcove.player.edge.VideoListener;
import com.brightcove.player.event.EventEmitter;
import com.brightcove.player.model.Video;
import com.myfitnesspal.android.R;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.uacf.taskrunner.Tasks.AsyncWait;
import com.uacf.taskrunner.Tasks.AsyncWait.Completion;

public class FetchVideoTask extends AsyncWait<Video, ApiException> {
    private final EventEmitter eventEmitter;
    private final String videoId;

    public static class CompletedEvent extends TaskEventBase<Video, ApiException> {
    }

    public FetchVideoTask(@NonNull EventEmitter eventEmitter2, @NonNull String str) {
        this.eventEmitter = eventEmitter2;
        this.videoId = str;
    }

    public void exec(Context context, final Completion<Video, ApiException> completion) throws ApiException {
        new Catalog(this.eventEmitter, context.getString(R.string.video_account), context.getString(R.string.video_policy)).findVideoByID(this.videoId, new VideoListener() {
            public void onVideo(Video video) {
                completion.setResult(video);
                completion.complete();
            }

            public void onError(String str) {
                completion.setResult(null);
                completion.complete();
            }
        });
    }
}
