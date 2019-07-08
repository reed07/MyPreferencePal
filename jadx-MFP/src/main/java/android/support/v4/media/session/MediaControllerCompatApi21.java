package android.support.v4.media.session;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.MediaSession.QueueItem;
import android.media.session.MediaSession.Token;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.RequiresApi;
import android.view.KeyEvent;
import java.util.List;

@RequiresApi
class MediaControllerCompatApi21 {

    public interface Callback {
        void onAudioInfoChanged(int i, int i2, int i3, int i4, int i5);

        void onExtrasChanged(Bundle bundle);

        void onMetadataChanged(Object obj);

        void onPlaybackStateChanged(Object obj);

        void onQueueChanged(List<?> list);

        void onQueueTitleChanged(CharSequence charSequence);

        void onSessionDestroyed();

        void onSessionEvent(String str, Bundle bundle);
    }

    static class CallbackProxy<T extends Callback> extends android.media.session.MediaController.Callback {
        protected final T mCallback;

        public CallbackProxy(T t) {
            this.mCallback = t;
        }

        public void onSessionDestroyed() {
            this.mCallback.onSessionDestroyed();
        }

        public void onSessionEvent(String str, Bundle bundle) {
            MediaSessionCompat.ensureClassLoader(bundle);
            this.mCallback.onSessionEvent(str, bundle);
        }

        public void onPlaybackStateChanged(PlaybackState playbackState) {
            this.mCallback.onPlaybackStateChanged(playbackState);
        }

        public void onMetadataChanged(MediaMetadata mediaMetadata) {
            this.mCallback.onMetadataChanged(mediaMetadata);
        }

        public void onQueueChanged(List<QueueItem> list) {
            this.mCallback.onQueueChanged(list);
        }

        public void onQueueTitleChanged(CharSequence charSequence) {
            this.mCallback.onQueueTitleChanged(charSequence);
        }

        public void onExtrasChanged(Bundle bundle) {
            MediaSessionCompat.ensureClassLoader(bundle);
            this.mCallback.onExtrasChanged(bundle);
        }

        public void onAudioInfoChanged(android.media.session.MediaController.PlaybackInfo playbackInfo) {
            this.mCallback.onAudioInfoChanged(playbackInfo.getPlaybackType(), PlaybackInfo.getLegacyAudioStream(playbackInfo), playbackInfo.getVolumeControl(), playbackInfo.getMaxVolume(), playbackInfo.getCurrentVolume());
        }
    }

    public static class PlaybackInfo {
        public static AudioAttributes getAudioAttributes(Object obj) {
            return ((android.media.session.MediaController.PlaybackInfo) obj).getAudioAttributes();
        }

        public static int getLegacyAudioStream(Object obj) {
            return toLegacyStreamType(getAudioAttributes(obj));
        }

        private static int toLegacyStreamType(AudioAttributes audioAttributes) {
            if ((audioAttributes.getFlags() & 1) == 1) {
                return 7;
            }
            if ((audioAttributes.getFlags() & 4) == 4) {
                return 6;
            }
            switch (audioAttributes.getUsage()) {
                case 1:
                case 11:
                case 12:
                case 14:
                    return 3;
                case 2:
                    return 0;
                case 3:
                    return 8;
                case 4:
                    return 4;
                case 5:
                case 7:
                case 8:
                case 9:
                case 10:
                    return 5;
                case 6:
                    return 2;
                case 13:
                    return 1;
                default:
                    return 3;
            }
        }

        private PlaybackInfo() {
        }
    }

    public static class TransportControls {
        private TransportControls() {
        }
    }

    public static Object fromToken(Context context, Object obj) {
        return new MediaController(context, (Token) obj);
    }

    public static Object createCallback(Callback callback) {
        return new CallbackProxy(callback);
    }

    public static boolean dispatchMediaButtonEvent(Object obj, KeyEvent keyEvent) {
        return ((MediaController) obj).dispatchMediaButtonEvent(keyEvent);
    }

    public static void sendCommand(Object obj, String str, Bundle bundle, ResultReceiver resultReceiver) {
        ((MediaController) obj).sendCommand(str, bundle, resultReceiver);
    }

    private MediaControllerCompatApi21() {
    }
}
