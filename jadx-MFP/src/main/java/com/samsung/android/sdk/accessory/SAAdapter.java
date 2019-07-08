package com.samsung.android.sdk.accessory;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Process;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.TransactionTooLargeException;
import android.util.Log;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.samsung.accessory.api.IDeathCallback;
import com.samsung.accessory.api.IDeathCallback.Stub;
import com.samsung.accessory.api.ISAFrameworkManagerV2;
import com.samsung.accessory.api.ISAMexCallback;
import com.samsung.accessory.api.ISAPeerAgentAuthCallback;
import com.samsung.accessory.api.ISAPeerAgentCallback;
import com.samsung.accessory.api.ISAServiceChannelCallback;
import com.samsung.accessory.api.ISAServiceConnectionCallback;
import com.samsung.accessory.api.ISAServiceConnectionIndicationCallback;
import java.util.HashSet;
import java.util.Set;

public final class SAAdapter {
    /* access modifiers changed from: private */
    public static final String a;
    /* access modifiers changed from: private */
    public static SAAdapter b;
    private Set<b> c;
    /* access modifiers changed from: private */
    public Context d;
    /* access modifiers changed from: private */
    public long e = -1;
    private int f = 0;
    /* access modifiers changed from: private */
    public ISAFrameworkManagerV2 g;
    private a h = new a(0);
    /* access modifiers changed from: private */
    public IDeathCallback i;
    /* access modifiers changed from: private */
    public ResultReceiver j;
    /* access modifiers changed from: private */
    public ServiceConnectionIndicationCallback k;

    static final class DeathCallbackStub extends Stub {
        private String mPackageName;

        public DeathCallbackStub(String str) {
            if (str != null) {
                this.mPackageName = str;
                return;
            }
            throw new IllegalArgumentException("Invalid packageName:null");
        }

        public final String getAppName() throws RemoteException {
            return this.mPackageName;
        }
    }

    final class ServiceConnectionIndicationCallback extends ISAServiceConnectionIndicationCallback.Stub {
        private ServiceConnectionIndicationCallback() {
        }

        /* synthetic */ ServiceConnectionIndicationCallback(SAAdapter sAAdapter, ServiceConnectionIndicationCallback serviceConnectionIndicationCallback) {
            this();
        }

        public final void onServiceConnectionRequested(Bundle bundle) throws RemoteException {
            String c;
            String str;
            byte[] byteArray = bundle.getByteArray("peerAgent");
            if (byteArray != null) {
                Parcel obtain = Parcel.obtain();
                if (obtain != null) {
                    obtain.unmarshall(byteArray, 0, byteArray.length);
                    obtain.setDataPosition(0);
                    SAPeerAgent sAPeerAgent = (SAPeerAgent) SAPeerAgent.CREATOR.createFromParcel(obtain);
                    obtain.recycle();
                    long j = bundle.getLong("transactionId", 0);
                    String string = bundle.getString("agentId");
                    String string2 = bundle.getString("agentImplclass");
                    if (string2 == null) {
                        c = SAAdapter.a;
                        str = "Implementation class not available in intent. Ignoring request";
                    } else {
                        Intent intent = new Intent("com.samsung.accessory.action.SERVICE_CONNECTION_REQUESTED");
                        intent.putExtra("transactionId", j);
                        intent.putExtra("agentId", string);
                        intent.putExtra("peerAgent", sAPeerAgent);
                        intent.setClassName(SAAdapter.this.d, string2);
                        try {
                            if (((VERSION.SDK_INT < 26 || SAAdapter.this.d.getPackageManager().getPackageInfo(SAAdapter.this.d.getPackageName(), 0).applicationInfo.targetSdkVersion < 26) ? SAAdapter.this.d.startService(intent) : SAAdapter.this.d.startForegroundService(intent)) == null) {
                                String c2 = SAAdapter.a;
                                StringBuilder sb = new StringBuilder("Agent ");
                                sb.append(string2);
                                sb.append(" not found in recepient application. Check your Accessory Service XML for serviceImpl attribute");
                                Log.e(c2, sb.toString());
                                return;
                            }
                        } catch (NameNotFoundException e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                } else {
                    c = SAAdapter.a;
                    str = "Failed to obtain parcel";
                }
            } else {
                c = SAAdapter.a;
                str = "marshalled accessory byte[] is null!";
            }
            Log.e(c, str);
        }
    }

    static class a implements ServiceConnection {
        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }

        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            synchronized (SAAdapter.b) {
                if (iBinder != null) {
                    Log.d(SAAdapter.a, "Accessory service connected");
                    SAAdapter.b.g = ISAFrameworkManagerV2.Stub.asInterface(iBinder);
                    try {
                        Bundle makeFrameworkConnection = SAAdapter.b.g.makeFrameworkConnection(Process.myPid(), SAAdapter.b.d.getPackageName(), SAAdapter.b.i, 9, SAAdapter.b.k);
                        if (makeFrameworkConnection == null) {
                            Log.e(SAAdapter.a, "Unable to setup client Identity.Invalid response from Framework");
                            return;
                        }
                        i.d(makeFrameworkConnection.getInt("fwk_version", 321));
                        SAAdapter.b.e = makeFrameworkConnection.getLong(Attributes.CLIENT_ID, -1);
                        if (SAAdapter.b.e == -1) {
                            SAAdapter.b.a(-1);
                            String c = SAAdapter.a;
                            StringBuilder sb = new StringBuilder("Unable to setup client Identity.Error:");
                            sb.append(makeFrameworkConnection.getInt("errorcode"));
                            Log.e(c, sb.toString());
                            return;
                        }
                        String c2 = SAAdapter.a;
                        StringBuilder sb2 = new StringBuilder("Received Client ID:");
                        sb2.append(SAAdapter.b.e);
                        Log.i(c2, sb2.toString());
                        SAAdapter.b.a(1);
                        if (makeFrameworkConnection.getInt("com.samsung.accessory.adapter.extra.PROCESS_ID") == Process.myPid() && i.d() >= 79) {
                            SAAdapter.b.j = SAAdapter.b.g.getClientCallback(SAAdapter.b.e);
                            String c3 = SAAdapter.a;
                            StringBuilder sb3 = new StringBuilder("Running in SAP process, Updated my proxy: ");
                            sb3.append(SAAdapter.b.j);
                            Log.i(c3, sb3.toString());
                        }
                        i.a(makeFrameworkConnection.getInt("com.samsung.accessory.adapter.extra.HEADER_LEN"));
                        i.c(makeFrameworkConnection.getInt("com.samsung.accessory.adapter.extra.FOOTER_LEN"));
                        i.b(makeFrameworkConnection.getInt("com.samsung.accessory.adapter.extra.MSG_HEADER_LEN"));
                    } catch (RemoteException e) {
                        Log.e(SAAdapter.a, "Unable to setup client Identity.", e);
                        SAAdapter.b.a(-1);
                        SAAdapter.b.a(e);
                    }
                }
                SAAdapter.b.notifyAll();
                SAAdapter.b.f();
            }
        }

        public final void onServiceDisconnected(ComponentName componentName) {
            synchronized (SAAdapter.b) {
                Log.w(SAAdapter.a, "Accessory service disconnected");
                SAAdapter.b.a(0);
                SAAdapter.b.a(false);
            }
        }
    }

    interface b {
        void a();

        void b();

        void c() throws c;
    }

    static {
        StringBuilder sb = new StringBuilder("[SA_SDK]");
        sb.append(SAAdapter.class.getSimpleName());
        a = sb.toString();
    }

    private SAAdapter(Context context) {
        this.d = context;
        this.i = new DeathCallbackStub(context.getPackageName());
        this.c = new HashSet();
        this.k = new ServiceConnectionIndicationCallback(this, null);
    }

    static synchronized SAAdapter a(Context context) {
        SAAdapter sAAdapter;
        synchronized (SAAdapter.class) {
            if (b == null) {
                b = new SAAdapter(context);
            }
            sAAdapter = b;
        }
        return sAAdapter;
    }

    /* access modifiers changed from: private */
    public synchronized void a(RemoteException remoteException) {
        if (remoteException instanceof TransactionTooLargeException) {
            Log.w(a, "Remote call falied, binder transaction buffer low", remoteException);
            a(true);
            return;
        }
        Log.w(a, "Remote call falied", remoteException);
    }

    /* access modifiers changed from: private */
    public synchronized void a(boolean z) {
        if (z) {
            e();
        }
        if (b.f == 1) {
            this.d.unbindService(this.h);
        }
        b.e = -1;
        a(0);
        b.g = null;
        for (b a2 : b.c) {
            a2.a();
        }
    }

    private synchronized int d() {
        return this.f;
    }

    private synchronized void e() {
        if (this.g == null) {
            Log.i(a, "Binding to framework does not exists");
            return;
        }
        try {
            this.g.tearFrameworkConnection(this.e);
            a(false);
        } catch (RemoteException e2) {
            try {
                Log.w(a, "Failed to tear framework connection", e2);
            } finally {
                a(false);
            }
        }
    }

    /* access modifiers changed from: private */
    public synchronized void f() {
        for (b b2 : b.c) {
            b2.b();
        }
    }

    /* access modifiers changed from: 0000 */
    public final int a(String str, int i2, byte[] bArr, boolean z, int i3, int i4, int i5, int i6) throws c {
        int i7 = i2;
        int i8 = i6;
        if (b.g == null) {
            a();
        }
        if (i8 == 2) {
            try {
                return this.g.sendUncompressed(this.e, str, (long) i7, bArr, z, i3, i4, i5);
            } catch (RemoteException e2) {
                String str2 = a;
                StringBuilder sb = new StringBuilder("Failed send data for connection:");
                sb.append(str);
                Log.w(str2, sb.toString(), e2);
                a(e2);
                throw new c(2048, "send: Remote call failed");
            }
        } else if (i8 == 1) {
            return this.g.sendCompressed(this.e, str, (long) i7, bArr, z, i3, i4, i5);
        } else {
            return this.g.send(this.e, str, (long) i7, bArr, z, i3, i4, i5);
        }
    }

    /* access modifiers changed from: 0000 */
    public final int a(String str, ISAPeerAgentCallback iSAPeerAgentCallback) throws c {
        if (b.g == null) {
            a();
        }
        try {
            if (b.g != null) {
                return b.g.findPeerAgents(this.e, -1, str, iSAPeerAgentCallback);
            }
            throw new c(2048, "findPeerAgents:mServiceProxy is null");
        } catch (RemoteException e2) {
            Log.w(a, "Failed to initiate peer discovery", e2);
            a(e2);
            throw new c(2048, "findPeerAgents:Remote call failed");
        }
    }

    /* access modifiers changed from: 0000 */
    public final int a(String str, SAPeerAgent sAPeerAgent, long j2) throws c {
        if (b.g == null) {
            a();
        }
        try {
            return this.g.rejectServiceConnection(this.e, str, sAPeerAgent, j2);
        } catch (RemoteException e2) {
            Log.w(a, "Failed to reject service connection", e2);
            a(e2);
            throw new c(2048, "rejectServiceConnection:Remote call failed");
        }
    }

    /* access modifiers changed from: 0000 */
    public final int a(String str, SAPeerAgent sAPeerAgent, ISAPeerAgentAuthCallback iSAPeerAgentAuthCallback, long j2) throws c {
        if (b.g == null) {
            a();
        }
        try {
            return this.g.authenticatePeerAgent(this.e, str, sAPeerAgent, iSAPeerAgentAuthCallback, j2);
        } catch (RemoteException e2) {
            Log.w(a, "Failed to request peer authentication", e2);
            a(e2);
            throw new c(2048, "authenticatePeeragent:Remote call failed");
        }
    }

    /* access modifiers changed from: 0000 */
    public final int a(String str, SAPeerAgent sAPeerAgent, ISAServiceConnectionCallback iSAServiceConnectionCallback, ISAServiceChannelCallback iSAServiceChannelCallback) throws c {
        if (b.g == null) {
            a();
        }
        try {
            return this.g.requestServiceConnection(this.e, str, sAPeerAgent, iSAServiceConnectionCallback, iSAServiceChannelCallback);
        } catch (RemoteException e2) {
            Log.w(a, "Failed to request service connection", e2);
            a(e2);
            throw new c(2048, "requestServiceConnection:Remote call failed");
        }
    }

    /* access modifiers changed from: 0000 */
    public final int a(String str, String str2, long j2, byte[] bArr, boolean z, int i2, int i3, int i4) throws c {
        if (b.g == null) {
            a();
        }
        if (!i.g()) {
            return -1797;
        }
        try {
            return b.g.sendMessage(this.e, str, str2, j2, bArr, z, i2, i3, i4);
        } catch (RemoteException e2) {
            String str3 = a;
            StringBuilder sb = new StringBuilder("Failed to send messages ");
            sb.append(e2);
            Log.w(str3, sb.toString());
            a(e2);
            throw new c(2048, "sendMessage: Remote call failed");
        }
    }

    /* access modifiers changed from: 0000 */
    public final synchronized String a(String str) throws c {
        String string;
        if (b.g == null) {
            a();
        }
        Bundle bundle = null;
        try {
            if (b.g != null) {
                bundle = b.g.getLocalAgentId(this.e, str);
            }
            if (bundle == null) {
                throw new RuntimeException("Get Local agent ID:Invalid response from accessory framework - null");
            } else if (!bundle.containsKey("errorcode")) {
                string = bundle.getString("agentId");
                if (string == null) {
                    throw new RuntimeException("Get Local agent ID:Invalid response - localAgentID:null");
                }
            } else {
                throw new c(bundle.getInt("errorcode"), "Failed to fetch localAgent ID");
            }
        } catch (RemoteException e2) {
            Log.w(a, "Failed to fetch localAgent ID", e2);
            a(e2);
            throw new c(2048, "getLocalAgentId:Remote call failed");
        }
        return string;
    }

    /* access modifiers changed from: 0000 */
    public final String a(String str, SAPeerAgent sAPeerAgent, long j2, ISAServiceConnectionCallback iSAServiceConnectionCallback, ISAServiceChannelCallback iSAServiceChannelCallback) throws c {
        if (b.g == null) {
            a();
        }
        try {
            Bundle acceptServiceConnection = this.g.acceptServiceConnection(this.e, str, sAPeerAgent, j2, iSAServiceConnectionCallback, iSAServiceChannelCallback);
            if (acceptServiceConnection == null) {
                String str2 = a;
                StringBuilder sb = new StringBuilder("acceptServiceConnection:Invalid response from Accessory Framework:");
                sb.append(acceptServiceConnection);
                Log.e(str2, sb.toString());
                StringBuilder sb2 = new StringBuilder("acceptServiceConnection:Invalid response from Accessory Framework:");
                sb2.append(acceptServiceConnection);
                throw new RuntimeException(sb2.toString());
            } else if (!acceptServiceConnection.containsKey("errorcode")) {
                String string = acceptServiceConnection.getString("connectionId");
                if (string != null) {
                    return string;
                }
                String str3 = a;
                StringBuilder sb3 = new StringBuilder("acceptServiceConnection:Invalid response from Accessory Framework- connectionId:");
                sb3.append(string);
                Log.e(str3, sb3.toString());
                StringBuilder sb4 = new StringBuilder("acceptServiceConnection:Invalid response from Accessory Framework- connectionId:");
                sb4.append(string);
                throw new RuntimeException(sb4.toString());
            } else {
                throw new c(acceptServiceConnection.getInt("errorcode"), "Failed to accept connection request!");
            }
        } catch (RemoteException e2) {
            Log.w(a, "Failed to accept service connection", e2);
            a(e2);
            throw new c(2048, "acceptServiceConnection:Remote call failed");
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:34:0x0092 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void a() throws com.samsung.android.sdk.accessory.c {
        /*
            r10 = this;
            monitor-enter(r10)
            com.samsung.android.sdk.accessory.SAAdapter r0 = b     // Catch:{ all -> 0x00bc }
            com.samsung.accessory.api.ISAFrameworkManagerV2 r0 = r0.g     // Catch:{ all -> 0x00bc }
            if (r0 != 0) goto L_0x00ba
            r0 = 0
            r10.a(r0)     // Catch:{ all -> 0x00bc }
            r0 = -1
            android.content.Intent r1 = new android.content.Intent     // Catch:{ SecurityException -> 0x0092 }
            java.lang.Class<com.samsung.accessory.api.ISAFrameworkManagerV2> r2 = com.samsung.accessory.api.ISAFrameworkManagerV2.class
            java.lang.String r2 = r2.getName()     // Catch:{ SecurityException -> 0x0092 }
            r1.<init>(r2)     // Catch:{ SecurityException -> 0x0092 }
            java.lang.String r2 = com.samsung.android.sdk.accessory.i.a     // Catch:{ SecurityException -> 0x0092 }
            r1.setPackage(r2)     // Catch:{ SecurityException -> 0x0092 }
            r2 = 1
            r3 = 1
        L_0x001e:
            com.samsung.android.sdk.accessory.SAAdapter r4 = b     // Catch:{ SecurityException -> 0x0092 }
            long r4 = r4.e     // Catch:{ SecurityException -> 0x0092 }
            r6 = -1
            r8 = 2048(0x800, float:2.87E-42)
            int r9 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r9 != 0) goto L_0x0071
            int r4 = r10.d()     // Catch:{ SecurityException -> 0x0092 }
            if (r4 != 0) goto L_0x0071
            r4 = 5
            if (r3 <= r4) goto L_0x0034
            goto L_0x0071
        L_0x0034:
            com.samsung.android.sdk.accessory.SAAdapter r4 = b     // Catch:{ SecurityException -> 0x0092 }
            android.content.Context r4 = r4.d     // Catch:{ SecurityException -> 0x0092 }
            com.samsung.android.sdk.accessory.SAAdapter r5 = b     // Catch:{ SecurityException -> 0x0092 }
            com.samsung.android.sdk.accessory.SAAdapter$a r5 = r5.h     // Catch:{ SecurityException -> 0x0092 }
            boolean r4 = r4.bindService(r1, r5, r2)     // Catch:{ SecurityException -> 0x0092 }
            if (r4 == 0) goto L_0x005f
            java.lang.String r4 = a     // Catch:{ InterruptedException -> 0x0053 }
            java.lang.String r5 = "getDefaultAdapter: About start waiting"
            android.util.Log.i(r4, r5)     // Catch:{ InterruptedException -> 0x0053 }
            com.samsung.android.sdk.accessory.SAAdapter r4 = b     // Catch:{ InterruptedException -> 0x0053 }
            r5 = 10000(0x2710, double:4.9407E-320)
            r4.wait(r5)     // Catch:{ InterruptedException -> 0x0053 }
            int r3 = r3 + 1
            goto L_0x001e
        L_0x0053:
            r1 = move-exception
            r10.a(r0)     // Catch:{ SecurityException -> 0x0092 }
            com.samsung.android.sdk.accessory.c r2 = new com.samsung.android.sdk.accessory.c     // Catch:{ SecurityException -> 0x0092 }
            java.lang.String r3 = "Failed to Bind to Accessory Framework - Action interrupted!"
            r2.<init>(r3, r1)     // Catch:{ SecurityException -> 0x0092 }
            throw r2     // Catch:{ SecurityException -> 0x0092 }
        L_0x005f:
            java.lang.String r1 = a     // Catch:{ SecurityException -> 0x0092 }
            java.lang.String r2 = "getDefaultAdapter: Binding to Accessory service failed!"
            android.util.Log.e(r1, r2)     // Catch:{ SecurityException -> 0x0092 }
            r10.a(r0)     // Catch:{ SecurityException -> 0x0092 }
            com.samsung.android.sdk.accessory.c r1 = new com.samsung.android.sdk.accessory.c     // Catch:{ SecurityException -> 0x0092 }
            java.lang.String r2 = "Is the Samsung Accessory Service Framework installed?!"
            r1.<init>(r8, r2)     // Catch:{ SecurityException -> 0x0092 }
            throw r1     // Catch:{ SecurityException -> 0x0092 }
        L_0x0071:
            com.samsung.android.sdk.accessory.SAAdapter r1 = b     // Catch:{ SecurityException -> 0x0092 }
            com.samsung.accessory.api.ISAFrameworkManagerV2 r1 = r1.g     // Catch:{ SecurityException -> 0x0092 }
            if (r1 == 0) goto L_0x0080
            java.lang.String r1 = a     // Catch:{ SecurityException -> 0x0092 }
            java.lang.String r2 = "Application is now connected to Accessory Framework!"
            android.util.Log.i(r1, r2)     // Catch:{ SecurityException -> 0x0092 }
            monitor-exit(r10)
            return
        L_0x0080:
            java.lang.String r1 = a     // Catch:{ SecurityException -> 0x0092 }
            java.lang.String r2 = "getDefaultAdapter: Service Connection proxy is null!"
            android.util.Log.e(r1, r2)     // Catch:{ SecurityException -> 0x0092 }
            r10.a(r0)     // Catch:{ SecurityException -> 0x0092 }
            com.samsung.android.sdk.accessory.c r1 = new com.samsung.android.sdk.accessory.c     // Catch:{ SecurityException -> 0x0092 }
            java.lang.String r2 = "Unable to bind to Samsung Accessory Service!"
            r1.<init>(r8, r2)     // Catch:{ SecurityException -> 0x0092 }
            throw r1     // Catch:{ SecurityException -> 0x0092 }
        L_0x0092:
            java.lang.String r1 = a     // Catch:{ all -> 0x00bc }
            java.lang.String r2 = "getDefaultAdapter: Permission denied! Binding to Accessory service failed!"
            android.util.Log.e(r1, r2)     // Catch:{ all -> 0x00bc }
            r10.a(r0)     // Catch:{ all -> 0x00bc }
            com.samsung.android.sdk.accessory.SAAdapter r0 = b     // Catch:{ all -> 0x00bc }
            android.content.Context r0 = r0.d     // Catch:{ all -> 0x00bc }
            boolean r0 = com.samsung.android.sdk.accessory.i.a(r0)     // Catch:{ all -> 0x00bc }
            if (r0 == 0) goto L_0x00b0
            com.samsung.android.sdk.accessory.c r0 = new com.samsung.android.sdk.accessory.c     // Catch:{ all -> 0x00bc }
            r1 = 2305(0x901, float:3.23E-42)
            java.lang.String r2 = "Permission validation failed to bind to Samsung Accessory Service! Please re-install the application and try again."
            r0.<init>(r1, r2)     // Catch:{ all -> 0x00bc }
            throw r0     // Catch:{ all -> 0x00bc }
        L_0x00b0:
            com.samsung.android.sdk.accessory.c r0 = new com.samsung.android.sdk.accessory.c     // Catch:{ all -> 0x00bc }
            r1 = 2304(0x900, float:3.229E-42)
            java.lang.String r2 = "Permission denied to bind to Samsung Accessory Service! Please add permission and try again."
            r0.<init>(r1, r2)     // Catch:{ all -> 0x00bc }
            throw r0     // Catch:{ all -> 0x00bc }
        L_0x00ba:
            monitor-exit(r10)
            return
        L_0x00bc:
            r0 = move-exception
            monitor-exit(r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.accessory.SAAdapter.a():void");
    }

    /* access modifiers changed from: 0000 */
    public final synchronized void a(int i2) {
        this.f = i2;
    }

    /* access modifiers changed from: 0000 */
    public final void a(long j2, int i2, int i3) throws c {
        if (b.g == null) {
            a();
        }
        try {
            if (b.g != null) {
                b.g.sendMessageDeliveryStatus(this.e, j2, i2, i3);
            }
        } catch (RemoteException e2) {
            Log.w(a, "Failed to send message delivery status", e2);
            a(e2);
            throw new c(2048, "sendMessageDeliveryStatus: Remote call failed");
        }
    }

    /* access modifiers changed from: 0000 */
    public final synchronized void a(b bVar) {
        this.c.add(bVar);
    }

    /* access modifiers changed from: 0000 */
    public final void a(String str, ISAMexCallback iSAMexCallback) throws c {
        if (b.g == null) {
            a();
        }
        try {
            if (b.g != null) {
                b.g.registerMexCallback(this.e, str, iSAMexCallback);
            }
        } catch (RemoteException e2) {
            Log.w(a, "Failed to register mex callback", e2);
            a(e2);
            throw new c(2048, "registerMexCallback: Remote call failed");
        }
    }

    /* access modifiers changed from: 0000 */
    public final synchronized void a(byte[] bArr) throws c {
        if (b.g == null) {
            a();
        }
        try {
            if (b.g != null) {
                b.g.registerComponent(this.e, bArr);
            }
            for (b c2 : this.c) {
                c2.c();
            }
        } catch (RemoteException e2) {
            Log.w(a, "Service registration call failed", e2);
            a(e2);
            throw new c(2048, "registerServices:Remote call failed");
        }
    }

    /* access modifiers changed from: 0000 */
    public final int b(String str) throws c {
        if (b.g == null) {
            a();
        }
        try {
            return this.g.closeServiceConnection(this.e, str);
        } catch (RemoteException e2) {
            Log.w(a, "Failed to close service connection", e2);
            a(e2);
            throw new c(2048, "closeServiceConnection:Remote call failed");
        }
    }

    /* access modifiers changed from: 0000 */
    public final synchronized void b(b bVar) {
        this.c.remove(bVar);
        if (this.c.isEmpty()) {
            Log.i(a, "All clients have unregistered.Disconnection from Accessory Framework.");
            a(true);
        }
    }

    /* access modifiers changed from: 0000 */
    public final synchronized void b(byte[] bArr) {
        if (b.j != null) {
            Bundle bundle = new Bundle();
            bundle.putByteArray("com.samsung.accessory.adapter.extra.READ_BYTES", bArr);
            b.j.send(0, bundle);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void c(String str) {
        if (b.g == null) {
            Log.w(a, "Binding to framework does not exists");
            return;
        }
        try {
            this.g.cleanupAgent(this.e, str);
        } catch (RemoteException e2) {
            Log.w(a, "Failed to cleanup agent details", e2);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void d(String str) throws c {
        ISAFrameworkManagerV2 iSAFrameworkManagerV2 = b.g;
        if (iSAFrameworkManagerV2 != null) {
            try {
                iSAFrameworkManagerV2.unregisterMexCallback(this.e, str);
            } catch (RemoteException e2) {
                Log.w(a, "Failed to unregister mex callback", e2);
                a(e2);
                throw new c(2048, "unregisterMexCallback: Remote call failed");
            }
        }
    }
}
