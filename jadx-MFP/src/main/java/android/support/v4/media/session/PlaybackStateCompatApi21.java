package android.support.v4.media.session;

import android.media.session.PlaybackState;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import java.util.List;

@RequiresApi
class PlaybackStateCompatApi21 {

    static final class CustomAction {
        public static String getAction(Object obj) {
            return ((android.media.session.PlaybackState.CustomAction) obj).getAction();
        }

        public static CharSequence getName(Object obj) {
            return ((android.media.session.PlaybackState.CustomAction) obj).getName();
        }

        public static int getIcon(Object obj) {
            return ((android.media.session.PlaybackState.CustomAction) obj).getIcon();
        }

        public static Bundle getExtras(Object obj) {
            return ((android.media.session.PlaybackState.CustomAction) obj).getExtras();
        }

        private CustomAction() {
        }
    }

    public static int getState(Object obj) {
        return ((PlaybackState) obj).getState();
    }

    public static long getPosition(Object obj) {
        return ((PlaybackState) obj).getPosition();
    }

    public static long getBufferedPosition(Object obj) {
        return ((PlaybackState) obj).getBufferedPosition();
    }

    public static float getPlaybackSpeed(Object obj) {
        return ((PlaybackState) obj).getPlaybackSpeed();
    }

    public static long getActions(Object obj) {
        return ((PlaybackState) obj).getActions();
    }

    public static CharSequence getErrorMessage(Object obj) {
        return ((PlaybackState) obj).getErrorMessage();
    }

    public static long getLastPositionUpdateTime(Object obj) {
        return ((PlaybackState) obj).getLastPositionUpdateTime();
    }

    public static List<Object> getCustomActions(Object obj) {
        return ((PlaybackState) obj).getCustomActions();
    }

    public static long getActiveQueueItemId(Object obj) {
        return ((PlaybackState) obj).getActiveQueueItemId();
    }

    private PlaybackStateCompatApi21() {
    }
}
