package android.support.v4.media.session;

import android.content.Intent;
import android.media.Rating;
import android.media.session.MediaSession.Token;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.RequiresApi;

@RequiresApi
class MediaSessionCompatApi21 {

    interface Callback {
        void onCommand(String str, Bundle bundle, ResultReceiver resultReceiver);

        void onCustomAction(String str, Bundle bundle);

        void onFastForward();

        boolean onMediaButtonEvent(Intent intent);

        void onPause();

        void onPlay();

        void onPlayFromMediaId(String str, Bundle bundle);

        void onPlayFromSearch(String str, Bundle bundle);

        void onRewind();

        void onSeekTo(long j);

        void onSetRating(Object obj);

        void onSkipToNext();

        void onSkipToPrevious();

        void onSkipToQueueItem(long j);

        void onStop();
    }

    static class CallbackProxy<T extends Callback> extends android.media.session.MediaSession.Callback {
        protected final T mCallback;

        public CallbackProxy(T t) {
            this.mCallback = t;
        }

        public void onCommand(String str, Bundle bundle, ResultReceiver resultReceiver) {
            MediaSessionCompat.ensureClassLoader(bundle);
            this.mCallback.onCommand(str, bundle, resultReceiver);
        }

        public boolean onMediaButtonEvent(Intent intent) {
            return this.mCallback.onMediaButtonEvent(intent) || super.onMediaButtonEvent(intent);
        }

        public void onPlay() {
            this.mCallback.onPlay();
        }

        public void onPlayFromMediaId(String str, Bundle bundle) {
            MediaSessionCompat.ensureClassLoader(bundle);
            this.mCallback.onPlayFromMediaId(str, bundle);
        }

        public void onPlayFromSearch(String str, Bundle bundle) {
            MediaSessionCompat.ensureClassLoader(bundle);
            this.mCallback.onPlayFromSearch(str, bundle);
        }

        public void onSkipToQueueItem(long j) {
            this.mCallback.onSkipToQueueItem(j);
        }

        public void onPause() {
            this.mCallback.onPause();
        }

        public void onSkipToNext() {
            this.mCallback.onSkipToNext();
        }

        public void onSkipToPrevious() {
            this.mCallback.onSkipToPrevious();
        }

        public void onFastForward() {
            this.mCallback.onFastForward();
        }

        public void onRewind() {
            this.mCallback.onRewind();
        }

        public void onStop() {
            this.mCallback.onStop();
        }

        public void onSeekTo(long j) {
            this.mCallback.onSeekTo(j);
        }

        public void onSetRating(Rating rating) {
            this.mCallback.onSetRating(rating);
        }

        public void onCustomAction(String str, Bundle bundle) {
            MediaSessionCompat.ensureClassLoader(bundle);
            this.mCallback.onCustomAction(str, bundle);
        }
    }

    static class QueueItem {
        public static Object getDescription(Object obj) {
            return ((android.media.session.MediaSession.QueueItem) obj).getDescription();
        }

        public static long getQueueId(Object obj) {
            return ((android.media.session.MediaSession.QueueItem) obj).getQueueId();
        }

        private QueueItem() {
        }
    }

    public static Object verifyToken(Object obj) {
        if (obj instanceof Token) {
            return obj;
        }
        throw new IllegalArgumentException("token is not a valid MediaSession.Token object");
    }

    public static Object createCallback(Callback callback) {
        return new CallbackProxy(callback);
    }

    private MediaSessionCompatApi21() {
    }
}
