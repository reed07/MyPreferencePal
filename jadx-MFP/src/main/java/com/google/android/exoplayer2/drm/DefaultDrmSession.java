package com.google.android.exoplayer2.drm;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.NotProvisionedException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Pair;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.drm.DrmInitData.SchemeData;
import com.google.android.exoplayer2.drm.DrmSession.DrmSessionException;
import com.google.android.exoplayer2.drm.ExoMediaCrypto;
import com.google.android.exoplayer2.drm.ExoMediaDrm.KeyRequest;
import com.google.android.exoplayer2.drm.ExoMediaDrm.ProvisionRequest;
import com.google.android.exoplayer2.util.EventDispatcher;
import com.google.android.exoplayer2.util.EventDispatcher.Event;
import com.google.android.exoplayer2.util.Log;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@TargetApi(18)
class DefaultDrmSession<T extends ExoMediaCrypto> implements DrmSession<T> {
    private static final int MAX_LICENSE_DURATION_TO_RENEW = 60;
    private static final int MSG_KEYS = 1;
    private static final int MSG_PROVISION = 0;
    private static final String TAG = "DefaultDrmSession";
    final MediaDrmCallback callback;
    private KeyRequest currentKeyRequest;
    private ProvisionRequest currentProvisionRequest;
    private final EventDispatcher<DefaultDrmSessionEventListener> eventDispatcher;
    /* access modifiers changed from: private */
    public final int initialDrmRequestRetryCount;
    private DrmSessionException lastException;
    private T mediaCrypto;
    private final ExoMediaDrm<T> mediaDrm;
    private final int mode;
    @Nullable
    private byte[] offlineLicenseKeySetId;
    private int openCount;
    private final HashMap<String, String> optionalKeyRequestParameters;
    private PostRequestHandler postRequestHandler;
    final PostResponseHandler postResponseHandler;
    private final ProvisioningManager<T> provisioningManager;
    private HandlerThread requestHandlerThread;
    @Nullable
    public final List<SchemeData> schemeDatas;
    private byte[] sessionId;
    private int state;
    final UUID uuid;

    @SuppressLint({"HandlerLeak"})
    private class PostRequestHandler extends Handler {
        public PostRequestHandler(Looper looper) {
            super(looper);
        }

        /* access modifiers changed from: 0000 */
        public void post(int i, Object obj, boolean z) {
            obtainMessage(i, z ? 1 : 0, 0, obj).sendToTarget();
        }

        public void handleMessage(Message message) {
            Object obj = message.obj;
            try {
                switch (message.what) {
                    case 0:
                        e = DefaultDrmSession.this.callback.executeProvisionRequest(DefaultDrmSession.this.uuid, (ProvisionRequest) obj);
                        break;
                    case 1:
                        e = DefaultDrmSession.this.callback.executeKeyRequest(DefaultDrmSession.this.uuid, (KeyRequest) obj);
                        break;
                    default:
                        throw new RuntimeException();
                }
            } catch (Exception e) {
                e = e;
                if (maybeRetryRequest(message)) {
                    return;
                }
            }
            DefaultDrmSession.this.postResponseHandler.obtainMessage(message.what, Pair.create(obj, e)).sendToTarget();
        }

        private boolean maybeRetryRequest(Message message) {
            if (!(message.arg1 == 1)) {
                return false;
            }
            int i = message.arg2 + 1;
            if (i > DefaultDrmSession.this.initialDrmRequestRetryCount) {
                return false;
            }
            Message obtain = Message.obtain(message);
            obtain.arg2 = i;
            sendMessageDelayed(obtain, getRetryDelayMillis(i));
            return true;
        }

        private long getRetryDelayMillis(int i) {
            return (long) Math.min((i - 1) * 1000, 5000);
        }
    }

    @SuppressLint({"HandlerLeak"})
    private class PostResponseHandler extends Handler {
        public PostResponseHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            Pair pair = (Pair) message.obj;
            Object obj = pair.first;
            Object obj2 = pair.second;
            switch (message.what) {
                case 0:
                    DefaultDrmSession.this.onProvisionResponse(obj, obj2);
                    return;
                case 1:
                    DefaultDrmSession.this.onKeyResponse(obj, obj2);
                    return;
                default:
                    return;
            }
        }
    }

    public interface ProvisioningManager<T extends ExoMediaCrypto> {
        void onProvisionCompleted();

        void onProvisionError(Exception exc);

        void provisionRequired(DefaultDrmSession<T> defaultDrmSession);
    }

    public DefaultDrmSession(UUID uuid2, ExoMediaDrm<T> exoMediaDrm, ProvisioningManager<T> provisioningManager2, @Nullable List<SchemeData> list, int i, @Nullable byte[] bArr, HashMap<String, String> hashMap, MediaDrmCallback mediaDrmCallback, Looper looper, EventDispatcher<DefaultDrmSessionEventListener> eventDispatcher2, int i2) {
        this.uuid = uuid2;
        this.provisioningManager = provisioningManager2;
        this.mediaDrm = exoMediaDrm;
        this.mode = i;
        this.offlineLicenseKeySetId = bArr;
        this.schemeDatas = bArr == null ? Collections.unmodifiableList(list) : null;
        this.optionalKeyRequestParameters = hashMap;
        this.callback = mediaDrmCallback;
        this.initialDrmRequestRetryCount = i2;
        this.eventDispatcher = eventDispatcher2;
        this.state = 2;
        this.postResponseHandler = new PostResponseHandler<>(looper);
        this.requestHandlerThread = new HandlerThread("DrmRequestHandler");
        this.requestHandlerThread.start();
        this.postRequestHandler = new PostRequestHandler<>(this.requestHandlerThread.getLooper());
    }

    public void acquire() {
        int i = this.openCount + 1;
        this.openCount = i;
        if (i == 1 && this.state != 1 && openInternal(true)) {
            doLicense(true);
        }
    }

    public boolean release() {
        int i = this.openCount - 1;
        this.openCount = i;
        if (i != 0) {
            return false;
        }
        this.state = 0;
        this.postResponseHandler.removeCallbacksAndMessages(null);
        this.postRequestHandler.removeCallbacksAndMessages(null);
        this.postRequestHandler = null;
        this.requestHandlerThread.quit();
        this.requestHandlerThread = null;
        this.mediaCrypto = null;
        this.lastException = null;
        this.currentKeyRequest = null;
        this.currentProvisionRequest = null;
        byte[] bArr = this.sessionId;
        if (bArr != null) {
            this.mediaDrm.closeSession(bArr);
            this.sessionId = null;
            this.eventDispatcher.dispatch($$Lambda$1U2yJBSMBm8ESUSz9LUzNXtoVus.INSTANCE);
        }
        return true;
    }

    public boolean hasSessionId(byte[] bArr) {
        return Arrays.equals(this.sessionId, bArr);
    }

    public void onMediaDrmEvent(int i) {
        if (isOpen()) {
            switch (i) {
                case 1:
                    this.state = 3;
                    this.provisioningManager.provisionRequired(this);
                    break;
                case 2:
                    doLicense(false);
                    break;
                case 3:
                    onKeysExpired();
                    break;
            }
        }
    }

    public void provision() {
        this.currentProvisionRequest = this.mediaDrm.getProvisionRequest();
        this.postRequestHandler.post(0, this.currentProvisionRequest, true);
    }

    public void onProvisionCompleted() {
        if (openInternal(false)) {
            doLicense(true);
        }
    }

    public void onProvisionError(Exception exc) {
        onError(exc);
    }

    public final int getState() {
        return this.state;
    }

    public final DrmSessionException getError() {
        if (this.state == 1) {
            return this.lastException;
        }
        return null;
    }

    public final T getMediaCrypto() {
        return this.mediaCrypto;
    }

    public Map<String, String> queryKeyStatus() {
        byte[] bArr = this.sessionId;
        if (bArr == null) {
            return null;
        }
        return this.mediaDrm.queryKeyStatus(bArr);
    }

    public byte[] getOfflineLicenseKeySetId() {
        return this.offlineLicenseKeySetId;
    }

    private boolean openInternal(boolean z) {
        if (isOpen()) {
            return true;
        }
        try {
            this.sessionId = this.mediaDrm.openSession();
            this.eventDispatcher.dispatch($$Lambda$jFcVU4qXZB2nhSZWHXCB9S7MtRI.INSTANCE);
            this.mediaCrypto = this.mediaDrm.createMediaCrypto(this.sessionId);
            this.state = 3;
            return true;
        } catch (NotProvisionedException e) {
            if (z) {
                this.provisioningManager.provisionRequired(this);
            } else {
                onError(e);
            }
            return false;
        } catch (Exception e2) {
            onError(e2);
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void onProvisionResponse(Object obj, Object obj2) {
        if (obj == this.currentProvisionRequest && (this.state == 2 || isOpen())) {
            this.currentProvisionRequest = null;
            if (obj2 instanceof Exception) {
                this.provisioningManager.onProvisionError((Exception) obj2);
                return;
            }
            try {
                this.mediaDrm.provideProvisionResponse((byte[]) obj2);
                this.provisioningManager.onProvisionCompleted();
            } catch (Exception e) {
                this.provisioningManager.onProvisionError(e);
            }
        }
    }

    private void doLicense(boolean z) {
        switch (this.mode) {
            case 0:
            case 1:
                if (this.offlineLicenseKeySetId == null) {
                    postKeyRequest(1, z);
                    return;
                } else if (this.state == 4 || restoreKeys()) {
                    long licenseDurationRemainingSec = getLicenseDurationRemainingSec();
                    if (this.mode == 0 && licenseDurationRemainingSec <= 60) {
                        String str = TAG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("Offline license has expired or will expire soon. Remaining seconds: ");
                        sb.append(licenseDurationRemainingSec);
                        Log.d(str, sb.toString());
                        postKeyRequest(2, z);
                        return;
                    } else if (licenseDurationRemainingSec <= 0) {
                        onError(new KeysExpiredException());
                        return;
                    } else {
                        this.state = 4;
                        this.eventDispatcher.dispatch($$Lambda$tzysvANfjWo6mXRxYD2fQMdks_4.INSTANCE);
                        return;
                    }
                } else {
                    return;
                }
            case 2:
                if (this.offlineLicenseKeySetId == null) {
                    postKeyRequest(2, z);
                    return;
                } else if (restoreKeys()) {
                    postKeyRequest(2, z);
                    return;
                } else {
                    return;
                }
            case 3:
                if (restoreKeys()) {
                    postKeyRequest(3, z);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private boolean restoreKeys() {
        try {
            this.mediaDrm.restoreKeys(this.sessionId, this.offlineLicenseKeySetId);
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error trying to restore Widevine keys.", e);
            onError(e);
            return false;
        }
    }

    private long getLicenseDurationRemainingSec() {
        if (!C.WIDEVINE_UUID.equals(this.uuid)) {
            return Long.MAX_VALUE;
        }
        Pair licenseDurationRemainingSec = WidevineUtil.getLicenseDurationRemainingSec(this);
        return Math.min(((Long) licenseDurationRemainingSec.first).longValue(), ((Long) licenseDurationRemainingSec.second).longValue());
    }

    private void postKeyRequest(int i, boolean z) {
        try {
            this.currentKeyRequest = this.mediaDrm.getKeyRequest(i == 3 ? this.offlineLicenseKeySetId : this.sessionId, this.schemeDatas, i, this.optionalKeyRequestParameters);
            this.postRequestHandler.post(1, this.currentKeyRequest, z);
        } catch (Exception e) {
            onKeysError(e);
        }
    }

    /* access modifiers changed from: private */
    public void onKeyResponse(Object obj, Object obj2) {
        if (obj == this.currentKeyRequest && isOpen()) {
            this.currentKeyRequest = null;
            if (obj2 instanceof Exception) {
                onKeysError((Exception) obj2);
                return;
            }
            try {
                byte[] bArr = (byte[]) obj2;
                if (this.mode == 3) {
                    this.mediaDrm.provideKeyResponse(this.offlineLicenseKeySetId, bArr);
                    this.eventDispatcher.dispatch($$Lambda$tzysvANfjWo6mXRxYD2fQMdks_4.INSTANCE);
                } else {
                    byte[] provideKeyResponse = this.mediaDrm.provideKeyResponse(this.sessionId, bArr);
                    if (!((this.mode != 2 && (this.mode != 0 || this.offlineLicenseKeySetId == null)) || provideKeyResponse == null || provideKeyResponse.length == 0)) {
                        this.offlineLicenseKeySetId = provideKeyResponse;
                    }
                    this.state = 4;
                    this.eventDispatcher.dispatch($$Lambda$wyKVEWJALn1OyjwryLo2GUxlQ2M.INSTANCE);
                }
            } catch (Exception e) {
                onKeysError(e);
            }
        }
    }

    private void onKeysExpired() {
        if (this.state == 4) {
            this.state = 3;
            onError(new KeysExpiredException());
        }
    }

    private void onKeysError(Exception exc) {
        if (exc instanceof NotProvisionedException) {
            this.provisioningManager.provisionRequired(this);
        } else {
            onError(exc);
        }
    }

    private void onError(Exception exc) {
        this.lastException = new DrmSessionException(exc);
        this.eventDispatcher.dispatch(new Event(exc) {
            private final /* synthetic */ Exception f$0;

            {
                this.f$0 = r1;
            }

            public final void sendTo(Object obj) {
                ((DefaultDrmSessionEventListener) obj).onDrmSessionManagerError(this.f$0);
            }
        });
        if (this.state != 4) {
            this.state = 1;
        }
    }

    private boolean isOpen() {
        int i = this.state;
        return i == 3 || i == 4;
    }
}
