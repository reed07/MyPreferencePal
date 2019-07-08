package android.support.v4.media.session;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

@RequiresApi
class MediaSessionCompatApi24 {

    public interface Callback extends android.support.v4.media.session.MediaSessionCompatApi23.Callback {
        void onPrepare();

        void onPrepareFromMediaId(String str, Bundle bundle);

        void onPrepareFromSearch(String str, Bundle bundle);

        void onPrepareFromUri(Uri uri, Bundle bundle);
    }

    static class CallbackProxy<T extends Callback> extends CallbackProxy<T> {
        public CallbackProxy(T t) {
            super(t);
        }

        public void onPrepare() {
            ((Callback) this.mCallback).onPrepare();
        }

        public void onPrepareFromMediaId(String str, Bundle bundle) {
            MediaSessionCompat.ensureClassLoader(bundle);
            ((Callback) this.mCallback).onPrepareFromMediaId(str, bundle);
        }

        public void onPrepareFromSearch(String str, Bundle bundle) {
            MediaSessionCompat.ensureClassLoader(bundle);
            ((Callback) this.mCallback).onPrepareFromSearch(str, bundle);
        }

        public void onPrepareFromUri(Uri uri, Bundle bundle) {
            MediaSessionCompat.ensureClassLoader(bundle);
            ((Callback) this.mCallback).onPrepareFromUri(uri, bundle);
        }
    }

    public static Object createCallback(Callback callback) {
        return new CallbackProxy(callback);
    }

    private MediaSessionCompatApi24() {
    }
}
