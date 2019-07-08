package com.myfitnesspal.feature.video.task;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.brightcove.player.edge.Catalog;
import com.brightcove.player.edge.PlaylistListener;
import com.brightcove.player.event.EventEmitter;
import com.brightcove.player.model.Playlist;
import com.myfitnesspal.android.R;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.uacf.core.util.Strings;
import com.uacf.taskrunner.Tasks.AsyncWait;
import com.uacf.taskrunner.Tasks.AsyncWait.Completion;

public class FetchVideosTask extends AsyncWait<Playlist, ApiException> {
    public static final String NAME = FetchVideosTask.class.getCanonicalName();
    private final EventEmitter eventEmitter;
    private final String playlistId;

    public static class CompletedEvent extends TaskEventBase<Playlist, ApiException> {
    }

    public FetchVideosTask(@NonNull EventEmitter eventEmitter2, @Nullable String str) {
        this.eventEmitter = eventEmitter2;
        this.playlistId = str;
    }

    public void exec(Context context, final Completion<Playlist, ApiException> completion) throws ApiException {
        if (Strings.isEmpty(this.playlistId)) {
            completion.setResult(null);
            completion.complete();
        }
        new Catalog(this.eventEmitter, context.getString(R.string.video_account), context.getString(R.string.video_policy)).findPlaylistByID(this.playlistId, new PlaylistListener() {
            public void onPlaylist(Playlist playlist) {
                completion.setResult(playlist);
                completion.complete();
            }

            public void onError(String str) {
                completion.setResult(null);
                completion.complete();
            }
        });
    }
}
