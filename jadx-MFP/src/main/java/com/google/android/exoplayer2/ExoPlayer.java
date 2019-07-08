package com.google.android.exoplayer2;

import android.os.Looper;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.PlayerMessage.Target;
import com.google.android.exoplayer2.source.MediaSource;

public interface ExoPlayer extends Player {
    @Deprecated
    public static final int REPEAT_MODE_ALL = 2;
    @Deprecated
    public static final int REPEAT_MODE_OFF = 0;
    @Deprecated
    public static final int REPEAT_MODE_ONE = 1;
    @Deprecated
    public static final int STATE_BUFFERING = 2;
    @Deprecated
    public static final int STATE_ENDED = 4;
    @Deprecated
    public static final int STATE_IDLE = 1;
    @Deprecated
    public static final int STATE_READY = 3;

    @Deprecated
    public interface EventListener extends com.google.android.exoplayer2.Player.EventListener {
    }

    @Deprecated
    public interface ExoPlayerComponent extends Target {
    }

    @Deprecated
    public static final class ExoPlayerMessage {
        public final Object message;
        public final int messageType;
        public final Target target;

        @Deprecated
        public ExoPlayerMessage(Target target2, int i, Object obj) {
            this.target = target2;
            this.messageType = i;
            this.message = obj;
        }
    }

    @Deprecated
    void blockingSendMessages(ExoPlayerMessage... exoPlayerMessageArr);

    PlayerMessage createMessage(Target target);

    Looper getPlaybackLooper();

    SeekParameters getSeekParameters();

    void prepare(MediaSource mediaSource);

    void prepare(MediaSource mediaSource, boolean z, boolean z2);

    void retry();

    @Deprecated
    void sendMessages(ExoPlayerMessage... exoPlayerMessageArr);

    void setSeekParameters(@Nullable SeekParameters seekParameters);
}
