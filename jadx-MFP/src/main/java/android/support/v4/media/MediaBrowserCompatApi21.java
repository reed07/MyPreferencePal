package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import java.util.List;

@RequiresApi
class MediaBrowserCompatApi21 {

    interface ConnectionCallback {
        void onConnected();

        void onConnectionFailed();

        void onConnectionSuspended();
    }

    static class ConnectionCallbackProxy<T extends ConnectionCallback> extends android.media.browse.MediaBrowser.ConnectionCallback {
        protected final T mConnectionCallback;

        public ConnectionCallbackProxy(T t) {
            this.mConnectionCallback = t;
        }

        public void onConnected() {
            this.mConnectionCallback.onConnected();
        }

        public void onConnectionSuspended() {
            this.mConnectionCallback.onConnectionSuspended();
        }

        public void onConnectionFailed() {
            this.mConnectionCallback.onConnectionFailed();
        }
    }

    static class MediaItem {
        public static int getFlags(Object obj) {
            return ((android.media.browse.MediaBrowser.MediaItem) obj).getFlags();
        }

        public static Object getDescription(Object obj) {
            return ((android.media.browse.MediaBrowser.MediaItem) obj).getDescription();
        }

        private MediaItem() {
        }
    }

    interface SubscriptionCallback {
        void onChildrenLoaded(@NonNull String str, List<?> list);

        void onError(@NonNull String str);
    }

    static class SubscriptionCallbackProxy<T extends SubscriptionCallback> extends android.media.browse.MediaBrowser.SubscriptionCallback {
        protected final T mSubscriptionCallback;

        public SubscriptionCallbackProxy(T t) {
            this.mSubscriptionCallback = t;
        }

        public void onChildrenLoaded(@NonNull String str, List<android.media.browse.MediaBrowser.MediaItem> list) {
            this.mSubscriptionCallback.onChildrenLoaded(str, list);
        }

        public void onError(@NonNull String str) {
            this.mSubscriptionCallback.onError(str);
        }
    }

    public static Object createConnectionCallback(ConnectionCallback connectionCallback) {
        return new ConnectionCallbackProxy(connectionCallback);
    }

    public static Object createBrowser(Context context, ComponentName componentName, Object obj, Bundle bundle) {
        return new MediaBrowser(context, componentName, (android.media.browse.MediaBrowser.ConnectionCallback) obj, bundle);
    }

    public static void connect(Object obj) {
        ((MediaBrowser) obj).connect();
    }

    public static void disconnect(Object obj) {
        ((MediaBrowser) obj).disconnect();
    }

    public static Bundle getExtras(Object obj) {
        return ((MediaBrowser) obj).getExtras();
    }

    public static Object getSessionToken(Object obj) {
        return ((MediaBrowser) obj).getSessionToken();
    }

    public static Object createSubscriptionCallback(SubscriptionCallback subscriptionCallback) {
        return new SubscriptionCallbackProxy(subscriptionCallback);
    }

    private MediaBrowserCompatApi21() {
    }
}
