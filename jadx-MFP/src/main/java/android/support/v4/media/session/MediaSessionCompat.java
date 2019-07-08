package android.support.v4.media.session;

import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaSessionManager.RemoteUserInfo;
import android.support.v4.media.RatingCompat;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MediaSessionCompat {

    public static abstract class Callback {
        private CallbackHandler mCallbackHandler = null;
        final Object mCallbackObj;
        private boolean mMediaPlayPauseKeyPending;
        WeakReference<MediaSessionImpl> mSessionImpl;

        private class CallbackHandler extends Handler {
            final /* synthetic */ Callback this$0;

            public void handleMessage(Message message) {
                if (message.what == 1) {
                    this.this$0.handleMediaPlayPauseKeySingleTapIfPending((RemoteUserInfo) message.obj);
                }
            }
        }

        @RequiresApi
        private class StubApi21 implements Callback {
            StubApi21() {
            }

            /* JADX WARNING: type inference failed for: r1v0 */
            /* JADX WARNING: type inference failed for: r1v1, types: [android.support.v4.media.session.MediaSessionCompat$QueueItem] */
            /* JADX WARNING: type inference failed for: r1v3, types: [android.support.v4.media.session.MediaSessionCompat$QueueItem] */
            /* JADX WARNING: type inference failed for: r1v4, types: [android.os.IBinder] */
            /* JADX WARNING: type inference failed for: r1v5, types: [android.os.IBinder] */
            /* JADX WARNING: type inference failed for: r1v6 */
            /* JADX WARNING: type inference failed for: r1v7 */
            /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v0
  assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], android.os.IBinder, android.support.v4.media.session.MediaSessionCompat$QueueItem]
  uses: [?[int, boolean, OBJECT, ARRAY, byte, short, char], android.support.v4.media.session.MediaSessionCompat$QueueItem, android.os.IBinder]
  mth insns count: 94
            	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
            	at java.base/java.util.ArrayList.forEach(Unknown Source)
            	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
            	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
            	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
            	at java.base/java.util.ArrayList.forEach(Unknown Source)
            	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
            	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
            	at java.base/java.util.ArrayList.forEach(Unknown Source)
            	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
            	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
            	at java.base/java.util.ArrayList.forEach(Unknown Source)
            	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
            	at jadx.core.ProcessClass.process(ProcessClass.java:30)
            	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
            	at java.base/java.util.ArrayList.forEach(Unknown Source)
            	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
            	at jadx.core.ProcessClass.process(ProcessClass.java:35)
            	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
            	at jadx.api.JavaClass.decompile(JavaClass.java:62)
            	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
             */
            /* JADX WARNING: Unknown variable types count: 3 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onCommand(java.lang.String r4, android.os.Bundle r5, android.os.ResultReceiver r6) {
                /*
                    r3 = this;
                    java.lang.String r0 = "android.support.v4.media.session.command.GET_EXTRA_BINDER"
                    boolean r0 = r4.equals(r0)     // Catch:{ BadParcelableException -> 0x00ca }
                    r1 = 0
                    if (r0 == 0) goto L_0x003d
                    android.support.v4.media.session.MediaSessionCompat$Callback r4 = android.support.v4.media.session.MediaSessionCompat.Callback.this     // Catch:{ BadParcelableException -> 0x00ca }
                    java.lang.ref.WeakReference<android.support.v4.media.session.MediaSessionCompat$MediaSessionImpl> r4 = r4.mSessionImpl     // Catch:{ BadParcelableException -> 0x00ca }
                    java.lang.Object r4 = r4.get()     // Catch:{ BadParcelableException -> 0x00ca }
                    android.support.v4.media.session.MediaSessionCompat$MediaSessionImplApi21 r4 = (android.support.v4.media.session.MediaSessionCompat.MediaSessionImplApi21) r4     // Catch:{ BadParcelableException -> 0x00ca }
                    if (r4 == 0) goto L_0x00d1
                    android.os.Bundle r5 = new android.os.Bundle     // Catch:{ BadParcelableException -> 0x00ca }
                    r5.<init>()     // Catch:{ BadParcelableException -> 0x00ca }
                    android.support.v4.media.session.MediaSessionCompat$Token r4 = r4.getSessionToken()     // Catch:{ BadParcelableException -> 0x00ca }
                    android.support.v4.media.session.IMediaSession r0 = r4.getExtraBinder()     // Catch:{ BadParcelableException -> 0x00ca }
                    java.lang.String r2 = "android.support.v4.media.session.EXTRA_BINDER"
                    if (r0 != 0) goto L_0x0027
                    goto L_0x002b
                L_0x0027:
                    android.os.IBinder r1 = r0.asBinder()     // Catch:{ BadParcelableException -> 0x00ca }
                L_0x002b:
                    android.support.v4.app.BundleCompat.putBinder(r5, r2, r1)     // Catch:{ BadParcelableException -> 0x00ca }
                    android.os.Bundle r4 = r4.getSessionToken2Bundle()     // Catch:{ BadParcelableException -> 0x00ca }
                    java.lang.String r0 = "android.support.v4.media.session.SESSION_TOKEN2_BUNDLE"
                    r5.putBundle(r0, r4)     // Catch:{ BadParcelableException -> 0x00ca }
                    r4 = 0
                    r6.send(r4, r5)     // Catch:{ BadParcelableException -> 0x00ca }
                    goto L_0x00d1
                L_0x003d:
                    java.lang.String r0 = "android.support.v4.media.session.command.ADD_QUEUE_ITEM"
                    boolean r0 = r4.equals(r0)     // Catch:{ BadParcelableException -> 0x00ca }
                    if (r0 == 0) goto L_0x0054
                    android.support.v4.media.session.MediaSessionCompat$Callback r4 = android.support.v4.media.session.MediaSessionCompat.Callback.this     // Catch:{ BadParcelableException -> 0x00ca }
                    java.lang.String r6 = "android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"
                    android.os.Parcelable r5 = r5.getParcelable(r6)     // Catch:{ BadParcelableException -> 0x00ca }
                    android.support.v4.media.MediaDescriptionCompat r5 = (android.support.v4.media.MediaDescriptionCompat) r5     // Catch:{ BadParcelableException -> 0x00ca }
                    r4.onAddQueueItem(r5)     // Catch:{ BadParcelableException -> 0x00ca }
                    goto L_0x00d1
                L_0x0054:
                    java.lang.String r0 = "android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT"
                    boolean r0 = r4.equals(r0)     // Catch:{ BadParcelableException -> 0x00ca }
                    if (r0 == 0) goto L_0x0070
                    android.support.v4.media.session.MediaSessionCompat$Callback r4 = android.support.v4.media.session.MediaSessionCompat.Callback.this     // Catch:{ BadParcelableException -> 0x00ca }
                    java.lang.String r6 = "android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"
                    android.os.Parcelable r6 = r5.getParcelable(r6)     // Catch:{ BadParcelableException -> 0x00ca }
                    android.support.v4.media.MediaDescriptionCompat r6 = (android.support.v4.media.MediaDescriptionCompat) r6     // Catch:{ BadParcelableException -> 0x00ca }
                    java.lang.String r0 = "android.support.v4.media.session.command.ARGUMENT_INDEX"
                    int r5 = r5.getInt(r0)     // Catch:{ BadParcelableException -> 0x00ca }
                    r4.onAddQueueItem(r6, r5)     // Catch:{ BadParcelableException -> 0x00ca }
                    goto L_0x00d1
                L_0x0070:
                    java.lang.String r0 = "android.support.v4.media.session.command.REMOVE_QUEUE_ITEM"
                    boolean r0 = r4.equals(r0)     // Catch:{ BadParcelableException -> 0x00ca }
                    if (r0 == 0) goto L_0x0086
                    android.support.v4.media.session.MediaSessionCompat$Callback r4 = android.support.v4.media.session.MediaSessionCompat.Callback.this     // Catch:{ BadParcelableException -> 0x00ca }
                    java.lang.String r6 = "android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"
                    android.os.Parcelable r5 = r5.getParcelable(r6)     // Catch:{ BadParcelableException -> 0x00ca }
                    android.support.v4.media.MediaDescriptionCompat r5 = (android.support.v4.media.MediaDescriptionCompat) r5     // Catch:{ BadParcelableException -> 0x00ca }
                    r4.onRemoveQueueItem(r5)     // Catch:{ BadParcelableException -> 0x00ca }
                    goto L_0x00d1
                L_0x0086:
                    java.lang.String r0 = "android.support.v4.media.session.command.REMOVE_QUEUE_ITEM_AT"
                    boolean r0 = r4.equals(r0)     // Catch:{ BadParcelableException -> 0x00ca }
                    if (r0 == 0) goto L_0x00c4
                    android.support.v4.media.session.MediaSessionCompat$Callback r4 = android.support.v4.media.session.MediaSessionCompat.Callback.this     // Catch:{ BadParcelableException -> 0x00ca }
                    java.lang.ref.WeakReference<android.support.v4.media.session.MediaSessionCompat$MediaSessionImpl> r4 = r4.mSessionImpl     // Catch:{ BadParcelableException -> 0x00ca }
                    java.lang.Object r4 = r4.get()     // Catch:{ BadParcelableException -> 0x00ca }
                    android.support.v4.media.session.MediaSessionCompat$MediaSessionImplApi21 r4 = (android.support.v4.media.session.MediaSessionCompat.MediaSessionImplApi21) r4     // Catch:{ BadParcelableException -> 0x00ca }
                    if (r4 == 0) goto L_0x00d1
                    java.util.List<android.support.v4.media.session.MediaSessionCompat$QueueItem> r6 = r4.mQueue     // Catch:{ BadParcelableException -> 0x00ca }
                    if (r6 == 0) goto L_0x00d1
                    java.lang.String r6 = "android.support.v4.media.session.command.ARGUMENT_INDEX"
                    r0 = -1
                    int r5 = r5.getInt(r6, r0)     // Catch:{ BadParcelableException -> 0x00ca }
                    if (r5 < 0) goto L_0x00b8
                    java.util.List<android.support.v4.media.session.MediaSessionCompat$QueueItem> r6 = r4.mQueue     // Catch:{ BadParcelableException -> 0x00ca }
                    int r6 = r6.size()     // Catch:{ BadParcelableException -> 0x00ca }
                    if (r5 >= r6) goto L_0x00b8
                    java.util.List<android.support.v4.media.session.MediaSessionCompat$QueueItem> r4 = r4.mQueue     // Catch:{ BadParcelableException -> 0x00ca }
                    java.lang.Object r4 = r4.get(r5)     // Catch:{ BadParcelableException -> 0x00ca }
                    r1 = r4
                    android.support.v4.media.session.MediaSessionCompat$QueueItem r1 = (android.support.v4.media.session.MediaSessionCompat.QueueItem) r1     // Catch:{ BadParcelableException -> 0x00ca }
                L_0x00b8:
                    if (r1 == 0) goto L_0x00d1
                    android.support.v4.media.session.MediaSessionCompat$Callback r4 = android.support.v4.media.session.MediaSessionCompat.Callback.this     // Catch:{ BadParcelableException -> 0x00ca }
                    android.support.v4.media.MediaDescriptionCompat r5 = r1.getDescription()     // Catch:{ BadParcelableException -> 0x00ca }
                    r4.onRemoveQueueItem(r5)     // Catch:{ BadParcelableException -> 0x00ca }
                    goto L_0x00d1
                L_0x00c4:
                    android.support.v4.media.session.MediaSessionCompat$Callback r0 = android.support.v4.media.session.MediaSessionCompat.Callback.this     // Catch:{ BadParcelableException -> 0x00ca }
                    r0.onCommand(r4, r5, r6)     // Catch:{ BadParcelableException -> 0x00ca }
                    goto L_0x00d1
                L_0x00ca:
                    java.lang.String r4 = "MediaSessionCompat"
                    java.lang.String r5 = "Could not unparcel the extra data."
                    android.util.Log.e(r4, r5)
                L_0x00d1:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.session.MediaSessionCompat.Callback.StubApi21.onCommand(java.lang.String, android.os.Bundle, android.os.ResultReceiver):void");
            }

            public boolean onMediaButtonEvent(Intent intent) {
                return Callback.this.onMediaButtonEvent(intent);
            }

            public void onPlay() {
                Callback.this.onPlay();
            }

            public void onPlayFromMediaId(String str, Bundle bundle) {
                Callback.this.onPlayFromMediaId(str, bundle);
            }

            public void onPlayFromSearch(String str, Bundle bundle) {
                Callback.this.onPlayFromSearch(str, bundle);
            }

            public void onSkipToQueueItem(long j) {
                Callback.this.onSkipToQueueItem(j);
            }

            public void onPause() {
                Callback.this.onPause();
            }

            public void onSkipToNext() {
                Callback.this.onSkipToNext();
            }

            public void onSkipToPrevious() {
                Callback.this.onSkipToPrevious();
            }

            public void onFastForward() {
                Callback.this.onFastForward();
            }

            public void onRewind() {
                Callback.this.onRewind();
            }

            public void onStop() {
                Callback.this.onStop();
            }

            public void onSeekTo(long j) {
                Callback.this.onSeekTo(j);
            }

            public void onSetRating(Object obj) {
                Callback.this.onSetRating(RatingCompat.fromRating(obj));
            }

            public void onCustomAction(String str, Bundle bundle) {
                Bundle bundle2 = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                MediaSessionCompat.ensureClassLoader(bundle2);
                if (str.equals("android.support.v4.media.session.action.PLAY_FROM_URI")) {
                    Callback.this.onPlayFromUri((Uri) bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI"), bundle2);
                } else if (str.equals("android.support.v4.media.session.action.PREPARE")) {
                    Callback.this.onPrepare();
                } else if (str.equals("android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID")) {
                    Callback.this.onPrepareFromMediaId(bundle.getString("android.support.v4.media.session.action.ARGUMENT_MEDIA_ID"), bundle2);
                } else if (str.equals("android.support.v4.media.session.action.PREPARE_FROM_SEARCH")) {
                    Callback.this.onPrepareFromSearch(bundle.getString("android.support.v4.media.session.action.ARGUMENT_QUERY"), bundle2);
                } else if (str.equals("android.support.v4.media.session.action.PREPARE_FROM_URI")) {
                    Callback.this.onPrepareFromUri((Uri) bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI"), bundle2);
                } else if (str.equals("android.support.v4.media.session.action.SET_CAPTIONING_ENABLED")) {
                    Callback.this.onSetCaptioningEnabled(bundle.getBoolean("android.support.v4.media.session.action.ARGUMENT_CAPTIONING_ENABLED"));
                } else if (str.equals("android.support.v4.media.session.action.SET_REPEAT_MODE")) {
                    Callback.this.onSetRepeatMode(bundle.getInt("android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE"));
                } else if (str.equals("android.support.v4.media.session.action.SET_SHUFFLE_MODE")) {
                    Callback.this.onSetShuffleMode(bundle.getInt("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE"));
                } else if (str.equals("android.support.v4.media.session.action.SET_RATING")) {
                    Callback.this.onSetRating((RatingCompat) bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_RATING"), bundle2);
                } else {
                    Callback.this.onCustomAction(str, bundle);
                }
            }
        }

        @RequiresApi
        private class StubApi23 extends StubApi21 implements android.support.v4.media.session.MediaSessionCompatApi23.Callback {
            StubApi23() {
                super();
            }

            public void onPlayFromUri(Uri uri, Bundle bundle) {
                Callback.this.onPlayFromUri(uri, bundle);
            }
        }

        @RequiresApi
        private class StubApi24 extends StubApi23 implements android.support.v4.media.session.MediaSessionCompatApi24.Callback {
            StubApi24() {
                super();
            }

            public void onPrepare() {
                Callback.this.onPrepare();
            }

            public void onPrepareFromMediaId(String str, Bundle bundle) {
                Callback.this.onPrepareFromMediaId(str, bundle);
            }

            public void onPrepareFromSearch(String str, Bundle bundle) {
                Callback.this.onPrepareFromSearch(str, bundle);
            }

            public void onPrepareFromUri(Uri uri, Bundle bundle) {
                Callback.this.onPrepareFromUri(uri, bundle);
            }
        }

        public void onAddQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
        }

        public void onAddQueueItem(MediaDescriptionCompat mediaDescriptionCompat, int i) {
        }

        public void onCommand(String str, Bundle bundle, ResultReceiver resultReceiver) {
        }

        public void onCustomAction(String str, Bundle bundle) {
        }

        public void onFastForward() {
        }

        public void onPause() {
        }

        public void onPlay() {
        }

        public void onPlayFromMediaId(String str, Bundle bundle) {
        }

        public void onPlayFromSearch(String str, Bundle bundle) {
        }

        public void onPlayFromUri(Uri uri, Bundle bundle) {
        }

        public void onPrepare() {
        }

        public void onPrepareFromMediaId(String str, Bundle bundle) {
        }

        public void onPrepareFromSearch(String str, Bundle bundle) {
        }

        public void onPrepareFromUri(Uri uri, Bundle bundle) {
        }

        public void onRemoveQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
        }

        public void onRewind() {
        }

        public void onSeekTo(long j) {
        }

        public void onSetCaptioningEnabled(boolean z) {
        }

        public void onSetRating(RatingCompat ratingCompat) {
        }

        public void onSetRating(RatingCompat ratingCompat, Bundle bundle) {
        }

        public void onSetRepeatMode(int i) {
        }

        public void onSetShuffleMode(int i) {
        }

        public void onSkipToNext() {
        }

        public void onSkipToPrevious() {
        }

        public void onSkipToQueueItem(long j) {
        }

        public void onStop() {
        }

        public Callback() {
            if (VERSION.SDK_INT >= 24) {
                this.mCallbackObj = MediaSessionCompatApi24.createCallback(new StubApi24());
            } else if (VERSION.SDK_INT >= 23) {
                this.mCallbackObj = MediaSessionCompatApi23.createCallback(new StubApi23());
            } else if (VERSION.SDK_INT >= 21) {
                this.mCallbackObj = MediaSessionCompatApi21.createCallback(new StubApi21());
            } else {
                this.mCallbackObj = null;
            }
        }

        public boolean onMediaButtonEvent(Intent intent) {
            long j;
            if (VERSION.SDK_INT >= 27) {
                return false;
            }
            MediaSessionImpl mediaSessionImpl = (MediaSessionImpl) this.mSessionImpl.get();
            if (mediaSessionImpl == null || this.mCallbackHandler == null) {
                return false;
            }
            KeyEvent keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
            if (keyEvent == null || keyEvent.getAction() != 0) {
                return false;
            }
            RemoteUserInfo currentControllerInfo = mediaSessionImpl.getCurrentControllerInfo();
            int keyCode = keyEvent.getKeyCode();
            if (keyCode == 79 || keyCode == 85) {
                if (keyEvent.getRepeatCount() > 0) {
                    handleMediaPlayPauseKeySingleTapIfPending(currentControllerInfo);
                } else if (this.mMediaPlayPauseKeyPending) {
                    this.mCallbackHandler.removeMessages(1);
                    this.mMediaPlayPauseKeyPending = false;
                    PlaybackStateCompat playbackState = mediaSessionImpl.getPlaybackState();
                    if (playbackState == null) {
                        j = 0;
                    } else {
                        j = playbackState.getActions();
                    }
                    if ((j & 32) != 0) {
                        onSkipToNext();
                    }
                } else {
                    this.mMediaPlayPauseKeyPending = true;
                    CallbackHandler callbackHandler = this.mCallbackHandler;
                    callbackHandler.sendMessageDelayed(callbackHandler.obtainMessage(1, currentControllerInfo), (long) ViewConfiguration.getDoubleTapTimeout());
                }
                return true;
            }
            handleMediaPlayPauseKeySingleTapIfPending(currentControllerInfo);
            return false;
        }

        /* access modifiers changed from: 0000 */
        public void handleMediaPlayPauseKeySingleTapIfPending(RemoteUserInfo remoteUserInfo) {
            long j;
            if (this.mMediaPlayPauseKeyPending) {
                boolean z = false;
                this.mMediaPlayPauseKeyPending = false;
                this.mCallbackHandler.removeMessages(1);
                MediaSessionImpl mediaSessionImpl = (MediaSessionImpl) this.mSessionImpl.get();
                if (mediaSessionImpl != null) {
                    PlaybackStateCompat playbackState = mediaSessionImpl.getPlaybackState();
                    if (playbackState == null) {
                        j = 0;
                    } else {
                        j = playbackState.getActions();
                    }
                    boolean z2 = playbackState != null && playbackState.getState() == 3;
                    boolean z3 = (516 & j) != 0;
                    if ((j & 514) != 0) {
                        z = true;
                    }
                    mediaSessionImpl.setCurrentControllerInfo(remoteUserInfo);
                    if (z2 && z) {
                        onPause();
                    } else if (!z2 && z3) {
                        onPlay();
                    }
                    mediaSessionImpl.setCurrentControllerInfo(null);
                }
            }
        }
    }

    interface MediaSessionImpl {
        RemoteUserInfo getCurrentControllerInfo();

        PlaybackStateCompat getPlaybackState();

        void setCurrentControllerInfo(RemoteUserInfo remoteUserInfo);
    }

    @RequiresApi
    static class MediaSessionImplApi21 implements MediaSessionImpl {
        PlaybackStateCompat mPlaybackState;
        List<QueueItem> mQueue;
        final Token mToken;

        public RemoteUserInfo getCurrentControllerInfo() {
            return null;
        }

        public void setCurrentControllerInfo(RemoteUserInfo remoteUserInfo) {
        }

        public Token getSessionToken() {
            return this.mToken;
        }

        public PlaybackStateCompat getPlaybackState() {
            return this.mPlaybackState;
        }
    }

    public interface OnActiveChangeListener {
    }

    public static final class QueueItem implements Parcelable {
        public static final Creator<QueueItem> CREATOR = new Creator<QueueItem>() {
            public QueueItem createFromParcel(Parcel parcel) {
                return new QueueItem(parcel);
            }

            public QueueItem[] newArray(int i) {
                return new QueueItem[i];
            }
        };
        private final MediaDescriptionCompat mDescription;
        private final long mId;
        private Object mItem;

        public int describeContents() {
            return 0;
        }

        private QueueItem(Object obj, MediaDescriptionCompat mediaDescriptionCompat, long j) {
            if (mediaDescriptionCompat == null) {
                throw new IllegalArgumentException("Description cannot be null.");
            } else if (j != -1) {
                this.mDescription = mediaDescriptionCompat;
                this.mId = j;
                this.mItem = obj;
            } else {
                throw new IllegalArgumentException("Id cannot be QueueItem.UNKNOWN_ID");
            }
        }

        QueueItem(Parcel parcel) {
            this.mDescription = (MediaDescriptionCompat) MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
            this.mId = parcel.readLong();
        }

        public MediaDescriptionCompat getDescription() {
            return this.mDescription;
        }

        public void writeToParcel(Parcel parcel, int i) {
            this.mDescription.writeToParcel(parcel, i);
            parcel.writeLong(this.mId);
        }

        public static QueueItem fromQueueItem(Object obj) {
            if (obj == null || VERSION.SDK_INT < 21) {
                return null;
            }
            return new QueueItem(obj, MediaDescriptionCompat.fromMediaDescription(QueueItem.getDescription(obj)), QueueItem.getQueueId(obj));
        }

        public static List<QueueItem> fromQueueItemList(List<?> list) {
            if (list == null || VERSION.SDK_INT < 21) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (Object fromQueueItem : list) {
                arrayList.add(fromQueueItem(fromQueueItem));
            }
            return arrayList;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("MediaSession.QueueItem {Description=");
            sb.append(this.mDescription);
            sb.append(", Id=");
            sb.append(this.mId);
            sb.append(" }");
            return sb.toString();
        }
    }

    @RestrictTo
    public static final class ResultReceiverWrapper implements Parcelable {
        public static final Creator<ResultReceiverWrapper> CREATOR = new Creator<ResultReceiverWrapper>() {
            public ResultReceiverWrapper createFromParcel(Parcel parcel) {
                return new ResultReceiverWrapper(parcel);
            }

            public ResultReceiverWrapper[] newArray(int i) {
                return new ResultReceiverWrapper[i];
            }
        };
        ResultReceiver mResultReceiver;

        public int describeContents() {
            return 0;
        }

        ResultReceiverWrapper(Parcel parcel) {
            this.mResultReceiver = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(parcel);
        }

        public void writeToParcel(Parcel parcel, int i) {
            this.mResultReceiver.writeToParcel(parcel, i);
        }
    }

    @RestrictTo
    @Retention(RetentionPolicy.SOURCE)
    public @interface SessionFlags {
    }

    public static final class Token implements Parcelable {
        public static final Creator<Token> CREATOR = new Creator<Token>() {
            public Token createFromParcel(Parcel parcel) {
                Object obj;
                if (VERSION.SDK_INT >= 21) {
                    obj = parcel.readParcelable(null);
                } else {
                    obj = parcel.readStrongBinder();
                }
                return new Token(obj);
            }

            public Token[] newArray(int i) {
                return new Token[i];
            }
        };
        private IMediaSession mExtraBinder;
        private final Object mInner;
        private Bundle mSessionToken2Bundle;

        public int describeContents() {
            return 0;
        }

        Token(Object obj) {
            this(obj, null, null);
        }

        Token(Object obj, IMediaSession iMediaSession) {
            this(obj, iMediaSession, null);
        }

        Token(Object obj, IMediaSession iMediaSession, Bundle bundle) {
            this.mInner = obj;
            this.mExtraBinder = iMediaSession;
            this.mSessionToken2Bundle = bundle;
        }

        public static Token fromToken(Object obj) {
            return fromToken(obj, null);
        }

        @RestrictTo
        public static Token fromToken(Object obj, IMediaSession iMediaSession) {
            if (obj == null || VERSION.SDK_INT < 21) {
                return null;
            }
            return new Token(MediaSessionCompatApi21.verifyToken(obj), iMediaSession);
        }

        public void writeToParcel(Parcel parcel, int i) {
            if (VERSION.SDK_INT >= 21) {
                parcel.writeParcelable((Parcelable) this.mInner, i);
            } else {
                parcel.writeStrongBinder((IBinder) this.mInner);
            }
        }

        public int hashCode() {
            Object obj = this.mInner;
            if (obj == null) {
                return 0;
            }
            return obj.hashCode();
        }

        public boolean equals(Object obj) {
            boolean z = true;
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Token)) {
                return false;
            }
            Token token = (Token) obj;
            Object obj2 = this.mInner;
            if (obj2 == null) {
                if (token.mInner != null) {
                    z = false;
                }
                return z;
            }
            Object obj3 = token.mInner;
            if (obj3 == null) {
                return false;
            }
            return obj2.equals(obj3);
        }

        public Object getToken() {
            return this.mInner;
        }

        @RestrictTo
        public IMediaSession getExtraBinder() {
            return this.mExtraBinder;
        }

        @RestrictTo
        public void setExtraBinder(IMediaSession iMediaSession) {
            this.mExtraBinder = iMediaSession;
        }

        @RestrictTo
        public Bundle getSessionToken2Bundle() {
            return this.mSessionToken2Bundle;
        }

        @RestrictTo
        public void setSessionToken2Bundle(Bundle bundle) {
            this.mSessionToken2Bundle = bundle;
        }
    }

    @RestrictTo
    public static void ensureClassLoader(@Nullable Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(MediaSessionCompat.class.getClassLoader());
        }
    }
}
