package com.samsung.android.sdk.accessory;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.samsung.accessory.api.ISAServiceChannelCallback;
import com.samsung.accessory.api.ISAServiceChannelCallback.Stub;
import com.samsung.accessory.api.ISAServiceConnectionCallback;
import java.io.IOException;
import java.lang.Thread.UncaughtExceptionHandler;

public abstract class SASocket {
    public static final int CONNECTION_LOST_DEVICE_DETACHED = 521;
    public static final int CONNECTION_LOST_PEER_DISCONNECTED = 513;
    public static final int CONNECTION_LOST_RECEIVE_LIMIT_VIOLATED = 523;
    public static final int CONNECTION_LOST_RETRANSMISSION_FAILED = 522;
    public static final int CONNECTION_LOST_UNKNOWN_REASON = 512;
    public static final int ERROR_FATAL = 2048;
    /* access modifiers changed from: private */
    public static final String a;
    private String b;
    private SAPeerAgent c;
    /* access modifiers changed from: private */
    public b d;
    /* access modifiers changed from: private */
    public a e;
    /* access modifiers changed from: private */
    public SAAdapter f;
    private int g = 2;

    final class ServiceChannelCallback extends Stub {
        private ServiceChannelCallback() {
        }

        /* synthetic */ ServiceChannelCallback(SASocket sASocket, ServiceChannelCallback serviceChannelCallback) {
            this();
        }

        public final void onError(Bundle bundle) throws RemoteException {
            if (bundle.containsKey("errorcode")) {
                Message obtainMessage = SASocket.this.d.obtainMessage(3);
                obtainMessage.arg1 = bundle.getInt("errorcode");
                SASocket.this.d.sendMessage(obtainMessage);
                return;
            }
            Log.w(SASocket.a, "onChannelError with no error code!");
        }

        public final void onRead(Bundle bundle) throws RemoteException {
            long j = bundle.getLong("channelId");
            int i = bundle.getInt("fragmentIndex");
            Message obtainMessage = SASocket.this.d.obtainMessage(2);
            obtainMessage.arg1 = (int) j;
            obtainMessage.arg2 = i;
            obtainMessage.obj = bundle;
            SASocket.this.d.sendMessage(obtainMessage);
        }
    }

    final class ServiceConnectionCallback extends ISAServiceConnectionCallback.Stub {
        private ServiceConnectionCallback() {
        }

        /* synthetic */ ServiceConnectionCallback(SASocket sASocket, ServiceConnectionCallback serviceConnectionCallback) {
            this();
        }

        public final void onConnectionLost(Bundle bundle) throws RemoteException {
            if (bundle.containsKey("errorcode")) {
                Message obtainMessage = SASocket.this.d.obtainMessage(1);
                obtainMessage.arg1 = bundle.getInt("errorcode");
                SASocket.this.d.sendMessage(obtainMessage);
                return;
            }
            Log.e(SASocket.a, "onConnectionLost with no error code!");
        }

        public final void onConnectionResponse(Bundle bundle) throws RemoteException {
            Message obtainMessage = SASocket.this.d.obtainMessage(4);
            obtainMessage.arg1 = bundle.getInt("errorcode", 1280);
            obtainMessage.obj = bundle.getString("connectionId", null);
            SASocket.this.d.sendMessage(obtainMessage);
        }
    }

    interface a {
        void a(SAPeerAgent sAPeerAgent, int i);

        void a(SAPeerAgent sAPeerAgent, SASocket sASocket);
    }

    static final class b extends Handler {
        private SASocket a;

        public b(SASocket sASocket, Looper looper) {
            super(looper);
            this.a = sASocket;
        }

        public final synchronized void a() {
            super.getLooper().quit();
            this.a = null;
        }

        public final synchronized void handleMessage(Message message) {
            int i = message.what;
            if (i != 4) {
                switch (i) {
                    case 1:
                        SASocket.a(this.a, message.arg1);
                        return;
                    case 2:
                        SASocket.a(this.a, message.arg1, message.arg2, (Bundle) message.obj);
                        return;
                    default:
                        String b = SASocket.a;
                        StringBuilder sb = new StringBuilder("Invalid message: ");
                        sb.append(message.what);
                        Log.e(b, sb.toString());
                        return;
                }
            }
            SASocket.a(this.a, (String) message.obj, message.arg1);
        }
    }

    static {
        StringBuilder sb = new StringBuilder("[SA_SDK]");
        sb.append(SASocket.class.getSimpleName());
        a = sb.toString();
    }

    protected SASocket(String str) {
    }

    private static String a(String str, int i) {
        StringBuilder sb = new StringBuilder(30);
        sb.append(str);
        sb.append("_");
        sb.append(i);
        return sb.toString();
    }

    private void a(int i, e eVar, boolean z, int i2) throws IOException {
        int i3 = i;
        d b2 = eVar.b();
        try {
            if (this.g == 1) {
                int a2 = this.f.a(this.b, i, b2.d(), z, b2.e(), b2.f(), b2.g(), i2);
                if (a2 == 0) {
                    String str = a;
                    StringBuilder sb = new StringBuilder("Data sent [");
                    sb.append(b2.e());
                    sb.append("] : ");
                    sb.append(b2.f());
                    sb.append(" : ");
                    sb.append(eVar.c());
                    Log.d(str, sb.toString());
                    b2.h();
                } else if (a2 == 2561) {
                    this.g = 2;
                    Log.e(a, "Write failed: Connection closed");
                    throw new IOException("Write failed:Connection already closed");
                } else if (a2 == 2566) {
                    String str2 = a;
                    StringBuilder sb2 = new StringBuilder("Write failed. Attempt to write on invalid channel:");
                    sb2.append(i);
                    Log.e(str2, sb2.toString());
                    StringBuilder sb3 = new StringBuilder("Write failed. Attempt to write on invalid channel:");
                    sb3.append(i);
                    throw new IllegalArgumentException(sb3.toString());
                } else if (a2 != 2567) {
                    b2.h();
                } else {
                    Log.e(a, "Write failed: Timed out!");
                    close();
                    throw new IOException("Write failed: Timed out!");
                }
            } else {
                Log.w(a, "Data send failed, connection closed!");
                throw new IOException("Failed to send, connection closed!");
            }
        } catch (c e2) {
            Log.e(a, "Send failed!", e2);
            throw new IOException("Send Failed", e2);
        } catch (Throwable th) {
            b2.h();
            throw th;
        }
    }

    private synchronized void a(int i, byte[] bArr, int i2) throws IOException {
        if (i < 0) {
            Log.e(a, "Send Failed : there is no service channel at the index");
        } else if (this.g != 1) {
            throw new IOException("Send failed. Socket already closed");
        } else if (bArr == null) {
            Log.e(a, "sendData: data is null");
            throw new IllegalArgumentException("Invalid data to send:NULL");
        } else if (bArr.length == 0) {
            Log.e(a, "sendData: data length is 0");
            throw new IllegalArgumentException("Invalaid data length 0");
        } else if (bArr.length <= this.c.getMaxAllowedDataSize()) {
            String str = a;
            StringBuilder sb = new StringBuilder("Sending data: ");
            sb.append(bArr.length);
            sb.append(" bytes");
            Log.d(str, sb.toString());
            e eVar = new e(1, a(this.b, i));
            try {
                eVar.a(i.a(), i.c(), getConnectedPeerAgent().b(), 0, bArr);
                while (eVar.a() != null) {
                    a(i, eVar, false, i2);
                }
                eVar.d();
            } catch (IOException e2) {
                Log.e(a, "Send Blob failed", e2);
                throw e2;
            } catch (Throwable th) {
                eVar.d();
                throw th;
            }
        } else {
            String str2 = a;
            StringBuilder sb2 = new StringBuilder("Data too long:");
            sb2.append(bArr.length);
            Log.e(str2, sb2.toString());
            StringBuilder sb3 = new StringBuilder("Data Too long! size:");
            sb3.append(bArr.length);
            sb3.append(" Max allowed Size:");
            sb3.append(this.c.getMaxAllowedDataSize());
            sb3.append(". check SAPeerAgent.getMaxAllowedDataSize()");
            throw new IllegalArgumentException(sb3.toString());
        }
    }

    static /* synthetic */ void a(SASocket sASocket, int i) {
        String str;
        String str2;
        sASocket.g = i == 2048 ? 3 : 2;
        sASocket.onServiceConnectionLost(i);
        switch (i) {
            case 512:
                str = a;
                str2 = "onServiceConnectionLost() -> CONNECTION_LOST_UNKNOWN_REASON";
                break;
            case CONNECTION_LOST_PEER_DISCONNECTED /*513*/:
                str = a;
                str2 = "onServiceConnectionLost() -> CONNECTION_LOST_PEER_DISCONNECTED";
                break;
            case CONNECTION_LOST_DEVICE_DETACHED /*521*/:
                str = a;
                str2 = "onServiceConnectionLost() -> CONNECTION_LOST_DEVICE_DETACHED";
                break;
            case CONNECTION_LOST_RETRANSMISSION_FAILED /*522*/:
                str = a;
                str2 = "onServiceConnectionLost() -> CONNECTION_LOST_RETRANSMISSION_FAILED";
                break;
            case 2048:
                str = a;
                str2 = "onServiceConnectionLost() -> ERROR_FATAL";
                break;
            default:
                String str3 = a;
                StringBuilder sb = new StringBuilder("onServiceConnectionLost() error_code: ");
                sb.append(i);
                Log.w(str3, sb.toString());
                break;
        }
        Log.i(str, str2);
        sASocket.c();
    }

    static /* synthetic */ void a(SASocket sASocket, int i, int i2, Bundle bundle) {
        if (sASocket.g != 1) {
            Log.w(a, "Ignoring data, socket is not yet established");
            return;
        }
        byte[] byteArray = bundle.getByteArray("com.samsung.accessory.adapter.extra.READ_BYTES");
        if (byteArray == null) {
            Log.e(a, "Failed to reassemble! - null data received!");
            return;
        }
        int i3 = bundle.getInt("com.samsung.accessory.adapter.extra.READ_LENGHT");
        int i4 = bundle.getInt("com.samsung.accessory.adapter.extra.READ_OFFSET");
        String a2 = a(sASocket.b, i);
        String str = a;
        StringBuilder sb = new StringBuilder("handleIncomingData: ");
        sb.append(byteArray.length);
        sb.append(" [");
        sb.append(i4);
        sb.append(", ");
        sb.append(i3);
        sb.append("]");
        Log.d(str, sb.toString());
        try {
            int a3 = g.a(a2, sASocket.c.getAccessory().a(), i2, byteArray, i4, i3);
            if (a3 == 1) {
                sASocket.onReceive(i, g.a(a2));
            } else if (a3 == 3) {
                Log.e(a, "Failed to reassemble!: closing down the connection");
                sASocket.close();
            }
            sASocket.f.b(byteArray);
            if (a3 != 2) {
                g.b(a2);
            }
        } catch (IOException e2) {
            Log.e(a, "Error in onRead!", e2);
            sASocket.f.b(byteArray);
            if (-1 != 2) {
                g.b(a2);
            }
        } catch (Throwable th) {
            sASocket.f.b(byteArray);
            if (-1 != 2) {
                g.b(a2);
            }
            throw th;
        }
    }

    static /* synthetic */ void a(SASocket sASocket, String str, int i) {
        a aVar = sASocket.e;
        if (aVar == null) {
            Log.w(a, "Connection status callback not found! Ignoring response");
        } else if (str == null) {
            Log.w(a, "connectionId is null so cleaning up");
            sASocket.e.a(sASocket.c, i);
            sASocket.c();
        } else {
            sASocket.b = str;
            sASocket.g = 1;
            aVar.a(sASocket.c, sASocket);
        }
    }

    private boolean a(String str, String str2) {
        StringBuilder sb = new StringBuilder("Socket:");
        sb.append(str);
        sb.append("_");
        sb.append(str2);
        HandlerThread handlerThread = new HandlerThread(sb.toString());
        handlerThread.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            public final void uncaughtException(Thread thread, final Throwable th) {
                String b = SASocket.a;
                StringBuilder sb = new StringBuilder("Exception in Socket background thread:");
                sb.append(thread.getName());
                sb.append("exception: ");
                sb.append(th.getMessage());
                Log.e(b, sb.toString());
                thread.interrupt();
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public final void run() {
                        throw new RuntimeException(th);
                    }
                });
            }
        });
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        if (looper == null) {
            String str3 = a;
            StringBuilder sb2 = new StringBuilder("Failed get Looper for Socket: initiator:");
            sb2.append(str);
            sb2.append(" Peer Id:");
            sb2.append(str2);
            Log.e(str3, sb2.toString());
            return false;
        }
        this.d = new b(this, looper);
        return true;
    }

    /* access modifiers changed from: private */
    public void c() {
        this.d.removeCallbacksAndMessages(null);
        this.d.a();
    }

    /* access modifiers changed from: 0000 */
    public final void a() {
        if (this.g == 1) {
            this.g = 3;
            Message obtainMessage = this.d.obtainMessage(1);
            obtainMessage.arg1 = 2048;
            this.d.sendMessage(obtainMessage);
            String str = a;
            StringBuilder sb = new StringBuilder("Socket:");
            sb.append(this.b);
            sb.append(" has been force closed!");
            Log.i(str, sb.toString());
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(final String str, final SAPeerAgent sAPeerAgent, SAAdapter sAAdapter, a aVar) {
        this.c = sAPeerAgent;
        this.e = aVar;
        this.f = sAAdapter;
        a(str, sAPeerAgent.getPeerId());
        this.d.post(new Runnable() {
            public final void run() {
                int i;
                try {
                    i = SASocket.this.f.a(str, sAPeerAgent, (ISAServiceConnectionCallback) new ServiceConnectionCallback(SASocket.this, null), (ISAServiceChannelCallback) new ServiceChannelCallback(SASocket.this, null));
                } catch (c e) {
                    Log.e(SASocket.a, "Failed to initiate connection!", e);
                    i = e.a();
                }
                if (i == 0) {
                    String b2 = SASocket.a;
                    StringBuilder sb = new StringBuilder("Connection request enqued successfully for peer:");
                    sb.append(sAPeerAgent.getPeerId());
                    Log.i(b2, sb.toString());
                    return;
                }
                String b3 = SASocket.a;
                StringBuilder sb2 = new StringBuilder("Connection request failed for peer:");
                sb2.append(sAPeerAgent.getPeerId());
                sb2.append(" Reason:");
                sb2.append(i);
                sb2.append(" Cleaning up now");
                Log.i(b3, sb2.toString());
                if (SASocket.this.e != null) {
                    SASocket.this.e.a(sAPeerAgent, i);
                }
                SASocket.this.c();
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public final void b(final String str, final SAPeerAgent sAPeerAgent, final SAAdapter sAAdapter, a aVar) {
        this.c = sAPeerAgent;
        this.f = sAAdapter;
        this.e = aVar;
        a(str, sAPeerAgent.getPeerId());
        this.d.post(new Runnable() {
            public final void run() {
                try {
                    String a2 = sAAdapter.a(str, sAPeerAgent, sAPeerAgent.c(), new ServiceConnectionCallback(SASocket.this, null), new ServiceChannelCallback(SASocket.this, null));
                    String b2 = SASocket.a;
                    StringBuilder sb = new StringBuilder("Connection accepted successfully. connection Id:");
                    sb.append(a2);
                    Log.d(b2, sb.toString());
                    SASocket.a(SASocket.this, a2, 0);
                } catch (c e) {
                    String b3 = SASocket.a;
                    StringBuilder sb2 = new StringBuilder("Failed to accept service connection: ");
                    sb2.append(e.getMessage());
                    Log.e(b3, sb2.toString());
                    SASocket.a(SASocket.this, (String) null, e.a());
                }
            }
        });
    }

    public void close() {
        if (this.g == 1) {
            this.g = 2;
            String str = a;
            StringBuilder sb = new StringBuilder("Application requested to close socket for Peer:");
            sb.append(this.c.getPeerId());
            Log.i(str, sb.toString());
            try {
                int b2 = this.f.b(this.b);
                if (b2 == 2561) {
                    Log.i(a, "Connection is already closed");
                    return;
                }
                if (b2 == 0) {
                    String str2 = a;
                    StringBuilder sb2 = new StringBuilder("Connection ");
                    sb2.append(this.b);
                    sb2.append(" close requested successfully");
                    Log.i(str2, sb2.toString());
                }
            } catch (c e2) {
                Log.e(a, "Failed to close connection!", e2);
            }
        } else {
            Log.i(a, "Connection is already closed");
        }
    }

    public SAPeerAgent getConnectedPeerAgent() {
        return this.c;
    }

    public boolean isConnected() {
        return this.g == 1;
    }

    public abstract void onError(int i, String str, int i2);

    public abstract void onReceive(int i, byte[] bArr);

    /* access modifiers changed from: protected */
    public abstract void onServiceConnectionLost(int i);

    public synchronized void secureSend(int i, byte[] bArr) throws IOException {
        if (i < 0) {
            Log.e(a, "Send Failed : there is no service channel at the index");
        } else if (this.g != 1) {
            throw new IOException("Secure Send failed. Socket already closed");
        } else if (bArr == null) {
            Log.e(a, "secureSend: data is null");
            throw new IllegalArgumentException("Invalid data to send:NULL");
        } else if (bArr.length == 0) {
            Log.e(a, "SecureSend: data length is 0");
            throw new IllegalArgumentException("Invalaid data length 0");
        } else if (bArr.length <= this.c.getMaxAllowedDataSize()) {
            String str = a;
            StringBuilder sb = new StringBuilder("Sending data:");
            sb.append(bArr.length);
            sb.append(" bytes");
            Log.d(str, sb.toString());
            e eVar = new e(1, a(this.b, i));
            try {
                eVar.a(i.a(), i.c(), getConnectedPeerAgent().b(), getConnectedPeerAgent().getAccessory().d(), bArr);
                while (eVar.a() != null) {
                    a(i, eVar, true, 3);
                }
                eVar.d();
            } catch (IOException e2) {
                Log.e(a, "Send Blob failed", e2);
                throw e2;
            } catch (Throwable th) {
                eVar.d();
                throw th;
            }
        } else {
            String str2 = a;
            StringBuilder sb2 = new StringBuilder("SecureSend:Data too long:");
            sb2.append(bArr.length);
            Log.e(str2, sb2.toString());
            StringBuilder sb3 = new StringBuilder("Secure send:Data Too long! size:");
            sb3.append(bArr.length);
            sb3.append(" Max allowed Size:");
            sb3.append(this.c.getMaxAllowedDataSize());
            sb3.append(". check SAPeerAgent.getMaxAllowedDataSize()");
            throw new IllegalArgumentException(sb3.toString());
        }
    }

    public void send(int i, byte[] bArr) throws IOException {
        a(i, bArr, 3);
    }

    public void sendCompressed(int i, byte[] bArr) throws IOException {
        a(i, bArr, 1);
    }

    public void sendUncompressed(int i, byte[] bArr) throws IOException {
        a(i, bArr, 2);
    }
}
