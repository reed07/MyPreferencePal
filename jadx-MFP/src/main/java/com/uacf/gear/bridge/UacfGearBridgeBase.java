package com.uacf.gear.bridge;

import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Base64;
import com.samsung.android.sdk.SsdkUnsupportedException;
import com.samsung.android.sdk.accessory.SA;
import com.samsung.android.sdk.accessory.SAAgent;
import com.samsung.android.sdk.accessory.SAAuthenticationToken;
import com.samsung.android.sdk.accessory.SAPeerAgent;
import com.samsung.android.sdk.accessory.SASocket;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class UacfGearBridgeBase extends SAAgent {
    private static final int HANDLER_MESSAGE_PROCESS_QUEUE = 1024;
    private static final int HANDLER_MESSAGE_QUIT = 1025;
    private LocalBinder mBinder = new LocalBinder();
    private QueueHandler mHandler;
    /* access modifiers changed from: private */
    public ErrorCode mLastErrorCode = null;
    /* access modifiers changed from: private */
    public Queue<MessageDescriptor> mMessageQueue = new ConcurrentLinkedQueue();
    /* access modifiers changed from: private */
    public UacfPeerSocket mPeerSocket;

    public enum ErrorCode {
        UnknownError,
        DeviceNotSupported,
        LibraryNotInstalled,
        LibraryUpdateRequired,
        ReadFailed,
        WriteFailed,
        ConnectionLost,
        InvalidMessageFormat,
        UntrustedCaller,
        NotConnected
    }

    public class LocalBinder extends Binder {
        public LocalBinder() {
        }

        public boolean isConnected() {
            boolean z;
            synchronized (UacfGearBridgeBase.this) {
                z = UacfGearBridgeBase.this.mPeerSocket != null;
            }
            return z;
        }
    }

    private static final class MessageDescriptor {
        final Direction mDirection;
        final Message mMessage;

        enum Direction {
            In,
            Out
        }

        static MessageDescriptor outbound(Message message) {
            return new MessageDescriptor(Direction.Out, message);
        }

        static MessageDescriptor inbound(Message message) {
            return new MessageDescriptor(Direction.In, message);
        }

        private MessageDescriptor(Direction direction, Message message) {
            this.mDirection = direction;
            this.mMessage = message;
        }
    }

    private final class QueueHandler extends Handler {
        public QueueHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            if (message.what == 1025) {
                getLooper().quit();
            } else if (message.what == UacfGearBridgeBase.HANDLER_MESSAGE_PROCESS_QUEUE) {
                while (UacfGearBridgeBase.this.mMessageQueue.size() > 0) {
                    MessageDescriptor messageDescriptor = (MessageDescriptor) UacfGearBridgeBase.this.mMessageQueue.remove();
                    if (messageDescriptor.mDirection == Direction.In) {
                        UacfGearBridgeBase.this.onMessageReceived(messageDescriptor.mMessage);
                    } else {
                        UacfGearBridgeBase.this.sendMessageInternal(messageDescriptor.mMessage);
                    }
                }
            } else {
                super.handleMessage(message);
            }
        }
    }

    /* access modifiers changed from: protected */
    public abstract boolean isPeerKeyValid(String str);

    /* access modifiers changed from: protected */
    public abstract void onError(ErrorCode errorCode, Exception exc);

    /* access modifiers changed from: protected */
    public abstract void onMessageReceived(Message message);

    /* access modifiers changed from: protected */
    public abstract void onMessageSendFailed(Message message);

    public UacfGearBridgeBase(String str) {
        super(str, UacfPeerSocket.class);
    }

    /* access modifiers changed from: protected */
    public synchronized void sendMessage(Message message) {
        if (this.mPeerSocket == null) {
            onError(ErrorCode.NotConnected, null);
            onMessageSendFailed(message);
        } else {
            addMessageToQueue(MessageDescriptor.outbound(message));
        }
    }

    public void onCreate() {
        super.onCreate();
        HandlerThread handlerThread = new HandlerThread(getClass().getCanonicalName());
        handlerThread.start();
        this.mHandler = new QueueHandler(handlerThread.getLooper());
        try {
            new SA().initialize(this);
        } catch (SsdkUnsupportedException e) {
            raiseDeviceNotSupportedError(e);
            stopSelf();
        } catch (Exception e2) {
            raiseError(ErrorCode.UnknownError, e2);
            stopSelf();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.mHandler.sendEmptyMessage(1025);
    }

    @Nullable
    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }

    /* access modifiers changed from: 0000 */
    public void onPeerSocketDisconnected(UacfPeerSocket uacfPeerSocket, int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("SAPeerAgent disconnected. error=");
        sb.append(UacfPeerSocket.convertErrorCodeToErrorName(i));
        raiseError(ErrorCode.ConnectionLost, new RuntimeException(sb.toString()));
        synchronized (this) {
            if (this.mPeerSocket == uacfPeerSocket) {
                this.mPeerSocket.close();
                this.mPeerSocket = null;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void onPeerSocketReceive(UacfPeerSocket uacfPeerSocket, int i, byte[] bArr) {
        Message fromBytes = Message.fromBytes(bArr);
        if (fromBytes != null) {
            addMessageToQueue(MessageDescriptor.inbound(fromBytes));
        } else {
            raiseError(ErrorCode.InvalidMessageFormat, null);
        }
    }

    /* access modifiers changed from: protected */
    public void onServiceConnectionRequested(SAPeerAgent sAPeerAgent) {
        if (sAPeerAgent != null) {
            authenticatePeerAgent(sAPeerAgent);
        }
    }

    /* access modifiers changed from: protected */
    public void onServiceConnectionResponse(SAPeerAgent sAPeerAgent, SASocket sASocket, int i) {
        if (i == 0 && sASocket != null) {
            synchronized (this) {
                if (!(this.mPeerSocket == null || this.mPeerSocket == sASocket)) {
                    this.mPeerSocket.close();
                }
                this.mPeerSocket = (UacfPeerSocket) sASocket;
                this.mPeerSocket.setUacfGearBridgeBase(this);
                this.mLastErrorCode = null;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onAuthenticationResponse(SAPeerAgent sAPeerAgent, SAAuthenticationToken sAAuthenticationToken, int i) {
        if (sAAuthenticationToken == null || sAAuthenticationToken.getAuthenticationType() != 1548 || !isPeerKeyValid(Base64.encodeToString(sAAuthenticationToken.getKey(), 2))) {
            StringBuilder sb = new StringBuilder();
            sb.append("SAPeerAgent authentication failed. error=");
            sb.append(UacfPeerSocket.convertErrorCodeToErrorName(i));
            raiseError(ErrorCode.UntrustedCaller, new RuntimeException(sb.toString()));
            if (sAPeerAgent != null) {
                rejectServiceConnectionRequest(sAPeerAgent);
            }
            return;
        }
        acceptServiceConnectionRequest(sAPeerAgent);
    }

    /* access modifiers changed from: protected */
    public void onPeerAgentsUpdated(SAPeerAgent[] sAPeerAgentArr, int i) {
        super.onPeerAgentsUpdated(sAPeerAgentArr, i);
    }

    private void raiseError(ErrorCode errorCode, Exception exc) {
        synchronized (this) {
            this.mLastErrorCode = errorCode;
        }
        onError(errorCode, exc);
    }

    private void addMessageToQueue(MessageDescriptor messageDescriptor) {
        this.mMessageQueue.add(messageDescriptor);
        if (!this.mHandler.hasMessages(HANDLER_MESSAGE_PROCESS_QUEUE)) {
            this.mHandler.sendEmptyMessage(HANDLER_MESSAGE_PROCESS_QUEUE);
        }
    }

    private void raiseDeviceNotSupportedError(SsdkUnsupportedException ssdkUnsupportedException) {
        int type = ssdkUnsupportedException.getType();
        if (type == 0 || type == 1) {
            raiseError(ErrorCode.DeviceNotSupported, ssdkUnsupportedException);
        } else if (type == 2) {
            raiseError(ErrorCode.LibraryNotInstalled, ssdkUnsupportedException);
        } else if (type == 3 || type == 4) {
            raiseError(ErrorCode.LibraryUpdateRequired, ssdkUnsupportedException);
        } else {
            raiseError(ErrorCode.DeviceNotSupported, ssdkUnsupportedException);
        }
    }

    /* access modifiers changed from: private */
    public synchronized void sendMessageInternal(Message message) {
        if (this.mPeerSocket != null) {
            for (int i = 0; i < getServiceChannelSize(); i++) {
                try {
                    this.mPeerSocket.secureSend(getServiceChannelId(i), message.getBytes());
                } catch (IOException e) {
                    raiseError(ErrorCode.WriteFailed, e);
                    onMessageSendFailed(message);
                }
            }
        }
    }
}
