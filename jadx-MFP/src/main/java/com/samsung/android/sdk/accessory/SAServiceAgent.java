package com.samsung.android.sdk.accessory;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.samsung.accessory.api.ISAPeerAgentAuthCallback;
import com.samsung.accessory.api.ISAPeerAgentAuthCallback.Stub;
import com.samsung.accessory.api.ISAPeerAgentCallback;
import com.samsung.android.sdk.SsdkUnsupportedException;
import com.samsung.android.sdk.SsdkVendorCheck;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public abstract class SAServiceAgent {
    public static final String ACTION_REGISTRATION_REQUIRED = "com.samsung.accessory.action.REGISTER_AGENT";
    public static final String ACTION_SERVICE_CONNECTION_REQUESTED = "com.samsung.accessory.action.SERVICE_CONNECTION_REQUESTED";
    public static final int AUTHENTICATION_FAILURE_PEER_AGENT_NOT_SUPPORTED = 1546;
    public static final int AUTHENTICATION_FAILURE_TOKEN_NOT_GENERATED = 1545;
    public static final int AUTHENTICATION_SUCCESS = 0;
    public static final int CONNECTION_ALREADY_EXIST = 1029;
    public static final int CONNECTION_DUPLICATE_REQUEST = 1040;
    public static final int CONNECTION_FAILURE_DEVICE_UNREACHABLE = 1028;
    public static final int CONNECTION_FAILURE_INVALID_PEERAGENT = 1033;
    public static final int CONNECTION_FAILURE_NETWORK = 1280;
    public static final int CONNECTION_FAILURE_PEERAGENT_NO_RESPONSE = 1030;
    public static final int CONNECTION_FAILURE_PEERAGENT_REJECTED = 1031;
    public static final int CONNECTION_FAILURE_SERVICE_LIMIT_REACHED = 1037;
    public static final int CONNECTION_SUCCESS = 0;
    public static final int ERROR_CONNECTION_INVALID_PARAM = 1025;
    public static final int ERROR_FATAL = 2048;
    public static final int ERROR_PERMISSION_DENIED = 2304;
    public static final int ERROR_PERMISSION_FAILED = 2305;
    public static final int ERROR_SDK_NOT_INITIALIZED = 2049;
    public static final int FINDPEER_DEVICE_NOT_CONNECTED = 1793;
    public static final int FINDPEER_DUPLICATE_REQUEST = 3085;
    public static final int FINDPEER_SERVICE_NOT_FOUND = 1794;
    public static final int PEER_AGENT_AVAILABLE = 1;
    public static final int PEER_AGENT_FOUND = 0;
    public static final int PEER_AGENT_UNAVAILABLE = 2;
    private static Map<String, SAServiceAgent> m = new HashMap();
    b a;
    private SAAdapter b;
    private SA c;
    private PeerAgentCallback d;
    private AuthenticationCallback e;
    private a f;
    private c g;
    /* access modifiers changed from: private */
    public List<SASocket> h;
    private Set<SAPeerAgent> i;
    private String j;
    private Context k;
    private Class<? extends SASocket> l;
    private boolean n = false;
    private b o = null;
    private k p = null;

    class AuthenticationCallback extends Stub {
        private AuthenticationCallback() {
        }

        /* synthetic */ AuthenticationCallback(SAServiceAgent sAServiceAgent, AuthenticationCallback authenticationCallback) {
            this();
        }

        public void onPeerAgentAuthenticated(Bundle bundle) throws RemoteException {
            Log.v("[SA_SDK]SAServiceAgent", "Received Authentication response");
            if (SAServiceAgent.this.a != null) {
                Message obtainMessage = SAServiceAgent.this.a.obtainMessage(10);
                obtainMessage.setData(bundle);
                SAServiceAgent.this.a.sendMessage(obtainMessage);
                return;
            }
            Log.w("[SA_SDK]SAServiceAgent", "onPeerAgentAuthenticated: mBackgroundWorker is null!");
        }
    }

    class PeerAgentCallback extends ISAPeerAgentCallback.Stub {
        private PeerAgentCallback() {
        }

        /* synthetic */ PeerAgentCallback(SAServiceAgent sAServiceAgent, PeerAgentCallback peerAgentCallback) {
            this();
        }

        public void onPeerAgentUpdated(Bundle bundle) throws RemoteException {
            Log.v("[SA_SDK]SAServiceAgent", "Received peer agent update");
            bundle.setClassLoader(SAPeerAgent.class.getClassLoader());
            if (bundle.containsKey("peerAgents")) {
                ArrayList<SAPeerAgent> parcelableArrayList = bundle.getParcelableArrayList("peerAgents");
                int i = bundle.getInt("peerAgentStatus");
                if (parcelableArrayList == null) {
                    Log.e("[SA_SDK]SAServiceAgent", "Peer Update - invalid peer agent list from Accessory Framework");
                } else if (i == 105 || i == 106) {
                    StringBuilder sb = new StringBuilder(String.valueOf(parcelableArrayList.size()));
                    sb.append(" Peer agent(s) updated for:");
                    sb.append(getClass().getName());
                    Log.i("[SA_SDK]SAServiceAgent", sb.toString());
                    for (SAPeerAgent sAPeerAgent : parcelableArrayList) {
                        StringBuilder sb2 = new StringBuilder("Peer ID:");
                        sb2.append(sAPeerAgent.getPeerId());
                        sb2.append("Container Id:");
                        sb2.append(sAPeerAgent.getContainerId());
                        sb2.append(" Accessory");
                        sb2.append(sAPeerAgent.getAccessory().getAccessoryId());
                        Log.i("[SA_SDK]SAServiceAgent", sb2.toString());
                    }
                    if (SAServiceAgent.this.a != null) {
                        Message obtainMessage = SAServiceAgent.this.a.obtainMessage();
                        obtainMessage.what = 4;
                        obtainMessage.arg1 = i == 105 ? 1 : 2;
                        obtainMessage.obj = parcelableArrayList.toArray(new SAPeerAgent[parcelableArrayList.size()]);
                        SAServiceAgent.this.a.sendMessage(obtainMessage);
                        return;
                    }
                    Log.w("[SA_SDK]SAServiceAgent", "onPeerAgentUpdated: mBackgroundWorker is null!");
                } else {
                    StringBuilder sb3 = new StringBuilder("Peer Update - invalid peer status from Accessory Framework:");
                    sb3.append(i);
                    Log.e("[SA_SDK]SAServiceAgent", sb3.toString());
                }
            } else {
                Log.e("[SA_SDK]SAServiceAgent", "No peer agents in PeerAgent update callback!");
            }
        }

        public void onPeerAgentsFound(Bundle bundle) throws RemoteException {
            Log.v("[SA_SDK]SAServiceAgent", "FindPeer response received.");
            bundle.setClassLoader(SAPeerAgent.class.getClassLoader());
            if (bundle.containsKey("errorcode")) {
                int i = bundle.getInt("errorcode");
                StringBuilder sb = new StringBuilder("Peer Not Found(");
                sb.append(i);
                sb.append(") for: ");
                sb.append(getClass().getName());
                Log.e("[SA_SDK]SAServiceAgent", sb.toString());
                if (SAServiceAgent.this.a != null) {
                    Message obtainMessage = SAServiceAgent.this.a.obtainMessage();
                    obtainMessage.what = 3;
                    obtainMessage.arg1 = i;
                    SAServiceAgent.this.a.sendMessage(obtainMessage);
                    return;
                }
                Log.w("[SA_SDK]SAServiceAgent", "onPeersAgentsFound: mBackgroundWorker is null!");
                return;
            }
            ArrayList<SAPeerAgent> parcelableArrayList = bundle.getParcelableArrayList("peerAgents");
            if (parcelableArrayList == null) {
                Log.e("[SA_SDK]SAServiceAgent", "Find Peer - invalid response from Accessory Framework");
                return;
            }
            StringBuilder sb2 = new StringBuilder(String.valueOf(parcelableArrayList.size()));
            sb2.append(" Peer agent(s) found for:");
            sb2.append(getClass().getName());
            Log.i("[SA_SDK]SAServiceAgent", sb2.toString());
            for (SAPeerAgent sAPeerAgent : parcelableArrayList) {
                StringBuilder sb3 = new StringBuilder("Peer ID:");
                sb3.append(sAPeerAgent.getPeerId());
                sb3.append("Container Id:");
                sb3.append(sAPeerAgent.getContainerId());
                sb3.append(" Accessory");
                sb3.append(sAPeerAgent.getAccessory().getAccessoryId());
                sb3.append(" Transport:");
                sb3.append(sAPeerAgent.getAccessory().getTransportType());
                Log.i("[SA_SDK]SAServiceAgent", sb3.toString());
            }
            if (SAServiceAgent.this.a != null) {
                Message obtainMessage2 = SAServiceAgent.this.a.obtainMessage();
                obtainMessage2.what = 3;
                obtainMessage2.arg1 = 0;
                obtainMessage2.obj = parcelableArrayList.toArray(new SAPeerAgent[parcelableArrayList.size()]);
                SAServiceAgent.this.a.sendMessage(obtainMessage2);
                return;
            }
            Log.w("[SA_SDK]SAServiceAgent", "onPeerAgentsFound: mBackgroundWorker is null!");
        }
    }

    static class a implements b {
        private SAServiceAgent a;

        public a(SAServiceAgent sAServiceAgent) {
            this.a = sAServiceAgent;
        }

        public final void a() {
            if (this.a.a != null) {
                Message obtainMessage = this.a.a.obtainMessage(11);
                obtainMessage.arg1 = 2048;
                this.a.a.sendMessage(obtainMessage);
                return;
            }
            Log.w("[SA_SDK]SAServiceAgent", "onFrameworkDisconnected: mBackgroundWorker is null!");
        }

        public final void b() {
        }

        public final void c() throws c {
            this.a.a();
        }
    }

    static class b extends Handler {
        SAServiceAgent a;

        public b(SAServiceAgent sAServiceAgent, Looper looper) {
            super(looper);
            this.a = sAServiceAgent;
        }

        public final void handleMessage(Message message) {
            SAPeerAgent sAPeerAgent = null;
            switch (message.what) {
                case 0:
                    try {
                        SAServiceAgent.a(this.a);
                        return;
                    } catch (c e) {
                        Log.e("[SA_SDK]SAServiceAgent", "Binding to Accessory Framework failed", e);
                        this.a.a(e.a(), (SAPeerAgent) null);
                        return;
                    }
                case 1:
                    this.a.b();
                    return;
                case 2:
                    SAServiceAgent.c(this.a);
                    return;
                case 3:
                    if (message.arg1 == 0) {
                        this.a.onFindPeerAgentsResponse((SAPeerAgent[]) message.obj, 0);
                        return;
                    } else {
                        this.a.onFindPeerAgentsResponse(null, message.arg1);
                        return;
                    }
                case 4:
                    this.a.onPeerAgentsUpdated((SAPeerAgent[]) message.obj, message.arg1);
                    return;
                case 5:
                    SAServiceAgent.a(this.a, (Intent) message.obj);
                    return;
                case 6:
                    SAServiceAgent.a(this.a, (SAPeerAgent) message.obj);
                    return;
                case 7:
                    SAServiceAgent.b(this.a, (SAPeerAgent) message.obj);
                    return;
                case 8:
                    this.a.a((SAPeerAgent) message.obj);
                    return;
                case 9:
                    SAServiceAgent.d(this.a, (SAPeerAgent) message.obj);
                    return;
                case 10:
                    SAServiceAgent.a(this.a, message.getData());
                    return;
                case 11:
                    if (message.obj != null && (message.obj instanceof SAPeerAgent)) {
                        sAPeerAgent = (SAPeerAgent) message.obj;
                    }
                    this.a.a(message.arg1, sAPeerAgent);
                    return;
                case 12:
                    this.a.onServiceConnectionResponse((message.obj == null || !(message.obj instanceof SAPeerAgent)) ? null : (SAPeerAgent) message.obj, null, message.arg1);
                    return;
                case 13:
                    SAServiceAgent.d(this.a);
                    return;
                default:
                    StringBuilder sb = new StringBuilder("Invalid msg received: ");
                    sb.append(message.what);
                    Log.w("[SA_SDK]SAServiceAgent", sb.toString());
                    return;
            }
        }
    }

    class c implements a {
        private c() {
        }

        /* synthetic */ c(SAServiceAgent sAServiceAgent, byte b) {
            this();
        }

        public final void a(SAPeerAgent sAPeerAgent, int i) {
            if (i == 2048) {
                Log.w("[SA_SDK]SAServiceAgent", "Framework disconnected during connection process!");
                SAServiceAgent.this.a(i, sAPeerAgent);
            } else if (SAServiceAgent.this.a != null) {
                if (i == 1034) {
                    i = 1033;
                    SAServiceAgent.this.a.sendMessage(SAServiceAgent.this.a.obtainMessage(1));
                }
                StringBuilder sb = new StringBuilder("Connection attempt failed wih peer:");
                sb.append(sAPeerAgent.getPeerId());
                sb.append(" reason:");
                sb.append(i);
                Log.e("[SA_SDK]SAServiceAgent", sb.toString());
                Message obtainMessage = SAServiceAgent.this.a.obtainMessage(12);
                obtainMessage.arg1 = i;
                obtainMessage.obj = sAPeerAgent;
                SAServiceAgent.this.a.sendMessage(obtainMessage);
            } else {
                Log.w("[SA_SDK]SAServiceAgent", "onConnectionFailure: mBackgroundWorker is null!");
            }
        }

        public final void a(SAPeerAgent sAPeerAgent, SASocket sASocket) {
            synchronized (SAServiceAgent.this.h) {
                SAServiceAgent.this.h.add(sASocket);
            }
            StringBuilder sb = new StringBuilder("Connection success with peer:");
            sb.append(sAPeerAgent.getPeerId());
            Log.i("[SA_SDK]SAServiceAgent", sb.toString());
            SAServiceAgent.this.onServiceConnectionResponse(sAPeerAgent, sASocket, 0);
        }
    }

    public SAServiceAgent(String str, Context context, Class<? extends SASocket> cls) {
        if (str == null || str.equalsIgnoreCase("")) {
            StringBuilder sb = new StringBuilder("Invalid parameter name:");
            sb.append(str);
            throw new IllegalArgumentException(sb.toString());
        } else if (!m.containsKey(getClass().getName())) {
            a(cls);
            this.j = str;
            this.l = cls;
            this.k = context;
            StringBuilder sb2 = new StringBuilder("Thread Name:");
            sb2.append(this.j);
            sb2.append("SASocket Imple class:");
            sb2.append(cls.getName());
            Log.d("[SA_SDK]SAServiceAgent", sb2.toString());
            try {
                new i(this.k);
            } catch (c e2) {
                e2.printStackTrace();
            }
            StringBuilder sb3 = new StringBuilder("SAServiceAgent - onCreate:");
            sb3.append(getClass().getSimpleName());
            Log.d("[SA_SDK]SAServiceAgent", sb3.toString());
            this.h = Collections.synchronizedList(new ArrayList());
            this.i = Collections.synchronizedSet(new HashSet());
            HandlerThread handlerThread = new HandlerThread(this.j);
            handlerThread.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
                public final void uncaughtException(Thread thread, final Throwable th) {
                    StringBuilder sb = new StringBuilder("Exception in background thread:");
                    sb.append(thread.getName());
                    Log.e("[SA_SDK]SAServiceAgent", sb.toString(), th);
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public final void run() {
                            throw new RuntimeException(th);
                        }
                    });
                }
            });
            handlerThread.start();
            Looper looper = handlerThread.getLooper();
            if (looper != null) {
                this.a = new b(this, looper);
                this.c = new SA();
                try {
                    this.c.initialize(this.k);
                } catch (SsdkUnsupportedException e3) {
                    Log.e("[SA_SDK]SAServiceAgent", "SDK initialization failed!", e3);
                    Message obtainMessage = this.a.obtainMessage(11);
                    obtainMessage.arg1 = 2049;
                    this.a.sendMessage(obtainMessage);
                }
                putServiceAgent(getClass().getName(), this);
                this.b = SAAdapter.a(this.k);
                this.e = new AuthenticationCallback(this, null);
                this.d = new PeerAgentCallback(this, null);
                this.g = new c(this, 0);
                this.f = new a(this);
                this.a.sendEmptyMessage(0);
                e();
                return;
            }
            Log.e("[SA_SDK]SAServiceAgent", "Unable to start Agent thread.");
            throw new RuntimeException("Unable to start Agent.Worker thread creation failed");
        } else {
            StringBuilder sb4 = new StringBuilder("Object already exist for the requested class");
            sb4.append(str);
            throw new IllegalArgumentException(sb4.toString());
        }
    }

    /* access modifiers changed from: private */
    public void a() throws c {
        String c2 = c();
        if (c2 == null) {
            a(2048, (SAPeerAgent) null);
            return;
        }
        Editor edit = this.k.getSharedPreferences("AccessoryPreferences", 0).edit();
        edit.putString(c2, getClass().getName());
        edit.putString(getClass().getName(), c2);
        edit.commit();
    }

    private void a(Context context, String str) {
        if (!f()) {
            return;
        }
        if (context.checkCallingOrSelfPermission("com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY") == 0) {
            ContentValues contentValues = new ContentValues();
            String name = getClass().getPackage().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(context.getPackageName()));
            sb.append("#");
            sb.append(this.c.getVersionCode());
            String sb2 = sb.toString();
            contentValues.put("app_id", name);
            contentValues.put(Attributes.FEATURE, sb2);
            contentValues.put("extra", str);
            Intent intent = new Intent();
            intent.setAction("com.samsung.android.providers.context.log.action.USE_APP_FEATURE_SURVEY");
            intent.putExtra("data", contentValues);
            intent.setPackage("com.samsung.android.providers.context");
            context.sendBroadcast(intent);
            return;
        }
        throw new SecurityException();
    }

    /* access modifiers changed from: private */
    public void a(SAPeerAgent sAPeerAgent) {
        int a2;
        String c2 = c();
        if (c2 == null) {
            a2 = 2048;
        } else {
            try {
                this.b.a(c2, sAPeerAgent, sAPeerAgent.c());
                return;
            } catch (c e2) {
                Log.e("[SA_SDK]SAServiceAgent", "Failed to reject Service connection!", e2);
                a2 = e2.a();
            }
        }
        a(a2, sAPeerAgent);
    }

    static /* synthetic */ void a(SAServiceAgent sAServiceAgent) throws c {
        sAServiceAgent.b.a();
        sAServiceAgent.b.a((b) sAServiceAgent.f);
        sAServiceAgent.a();
    }

    static /* synthetic */ void a(SAServiceAgent sAServiceAgent, Intent intent) {
        if (intent == null) {
            Log.e("[SA_SDK]SAServiceAgent", "Invalid service connection indication.Intent:null.Ignoring reqeuset");
            return;
        }
        long longExtra = intent.getLongExtra("transactionId", 0);
        SAPeerAgent sAPeerAgent = (SAPeerAgent) intent.getParcelableExtra("peerAgent");
        String stringExtra = intent.getStringExtra("agentId");
        if (sAPeerAgent == null) {
            StringBuilder sb = new StringBuilder("Invalid initiator peer agent:");
            sb.append(sAPeerAgent);
            sb.append(". Ignoring connection request");
            Log.e("[SA_SDK]SAServiceAgent", sb.toString());
        } else if (stringExtra == null) {
            StringBuilder sb2 = new StringBuilder("Invalid local agent Id:");
            sb2.append(stringExtra);
            sb2.append(".Ignoring connection request");
            Log.e("[SA_SDK]SAServiceAgent", sb2.toString());
        } else {
            sAPeerAgent.a(longExtra);
            StringBuilder sb3 = new StringBuilder("Connection initiated by peer: ");
            sb3.append(sAPeerAgent.getPeerId());
            sb3.append(" on Accessory: ");
            sb3.append(sAPeerAgent.getAccessory().getAccessoryId());
            sb3.append(" Transaction: ");
            sb3.append(longExtra);
            Log.i("[SA_SDK]SAServiceAgent", sb3.toString());
            sAServiceAgent.i.add(sAPeerAgent);
            sAServiceAgent.onServiceConnectionRequested(sAPeerAgent);
        }
    }

    static /* synthetic */ void a(SAServiceAgent sAServiceAgent, Bundle bundle) {
        bundle.setClassLoader(SAPeerAgent.class.getClassLoader());
        byte[] byteArray = bundle.getByteArray("PEER_AGENT_KEY");
        int i2 = bundle.getInt("CERT_TYPE");
        SAPeerAgent sAPeerAgent = (SAPeerAgent) bundle.getParcelable("peerAgent");
        long j2 = bundle.getLong("transactionId");
        if (sAPeerAgent == null) {
            Log.e("[SA_SDK]SAServiceAgent", "Invalid response from framework! No peer agent in auth response.Ignoring response");
            return;
        }
        sAPeerAgent.a(j2);
        int i3 = 0;
        if (byteArray == null) {
            i3 = 1545;
            StringBuilder sb = new StringBuilder("Authentication failed error:1545 Peer Id:");
            sb.append(sAPeerAgent.getPeerId());
            Log.e("[SA_SDK]SAServiceAgent", sb.toString());
        } else {
            StringBuilder sb2 = new StringBuilder("Authentication success status: 0 for peer: ");
            sb2.append(sAPeerAgent.getPeerId());
            Log.i("[SA_SDK]SAServiceAgent", sb2.toString());
        }
        sAServiceAgent.onAuthenticationResponse(sAPeerAgent, new SAAuthenticationToken(i2, byteArray), i3);
    }

    static /* synthetic */ void a(SAServiceAgent sAServiceAgent, SAPeerAgent sAPeerAgent) {
        String c2 = sAServiceAgent.c();
        if (c2 == null) {
            Log.e("[SA_SDK]SAServiceAgent", "Failed to retrieve service description.Ignoring service connection request");
            sAServiceAgent.a(2048, sAPeerAgent);
            return;
        }
        sAServiceAgent.d().a(c2, sAPeerAgent, sAServiceAgent.b, (a) sAServiceAgent.g);
    }

    private static void a(Class<? extends SASocket> cls) {
        if (cls != null) {
            try {
                if (cls.getEnclosingClass() != null) {
                    cls.getDeclaredConstructor(new Class[]{cls.getEnclosingClass()});
                    return;
                }
                cls.getDeclaredConstructor(new Class[0]);
            } catch (NoSuchMethodException e2) {
                StringBuilder sb = new StringBuilder("exception: ");
                sb.append(e2.getMessage());
                Log.e("[SA_SDK]SAServiceAgent", sb.toString(), e2);
                throw new RuntimeException("Invalid implemetation of SASocket. Provider a public default constructor in the implementation class.");
            }
        } else {
            StringBuilder sb2 = new StringBuilder("Invalid socketClass param:");
            sb2.append(cls);
            throw new IllegalArgumentException(sb2.toString());
        }
    }

    private void a(boolean z) {
        for (SASocket sASocket : this.h) {
            if (z) {
                sASocket.a();
            } else {
                sASocket.close();
            }
        }
        this.h.clear();
    }

    /* access modifiers changed from: private */
    public void b() {
        String str;
        String str2;
        h hVar = new h(this.k);
        Future a2 = hVar.a();
        hVar.b();
        try {
            a2.get();
        } catch (InterruptedException unused) {
            str = "[SA_SDK]SAServiceAgent";
            str2 = "Regisration failed! : InterruptedException";
            Log.e(str, str2);
        } catch (ExecutionException unused2) {
            str = "[SA_SDK]SAServiceAgent";
            str2 = "Registration failed! : ExecutionException";
            Log.e(str, str2);
        }
    }

    private void b(SAPeerAgent sAPeerAgent) {
        synchronized (this.i) {
            for (SAPeerAgent a2 : this.i) {
                a(a2);
            }
            if (this.a != null) {
                Message obtainMessage = this.a.obtainMessage(11);
                obtainMessage.arg1 = 1033;
                obtainMessage.obj = sAPeerAgent;
                this.a.sendMessage(obtainMessage);
            } else {
                Log.w("[SA_SDK]SAServiceAgent", "handleInvlaidPeerAction: mBackgroundWorker is null!");
            }
        }
    }

    static /* synthetic */ void b(SAServiceAgent sAServiceAgent, SAPeerAgent sAPeerAgent) {
        String c2 = sAServiceAgent.c();
        if (c2 == null) {
            sAServiceAgent.a(2048, sAPeerAgent);
        } else {
            sAServiceAgent.d().b(c2, sAPeerAgent, sAServiceAgent.b, sAServiceAgent.g);
        }
    }

    private String c() {
        String str;
        String str2;
        try {
            String a2 = this.b.a(getClass().getName());
            StringBuilder sb = new StringBuilder("Agent ID retrieved successfully for ");
            sb.append(getClass().getName());
            sb.append(" Agent ID:");
            sb.append(a2);
            Log.i("[SA_SDK]SAServiceAgent", sb.toString());
            return a2;
        } catch (c e2) {
            if (e2.a() != 777 || i.d() < 298) {
                str = "[SA_SDK]SAServiceAgent";
                str2 = "Failed to retrieve service record";
            } else {
                Log.w("[SA_SDK]SAServiceAgent", "Service record was not found in Accessory Framework.Registering service again!");
                b();
                try {
                    Log.i("[SA_SDK]SAServiceAgent", "Trying to fetch agent ID after re-registration");
                    return this.b.a(getClass().getName());
                } catch (c unused) {
                    str = "[SA_SDK]SAServiceAgent";
                    str2 = "Failed to retrieve service record after re-registration";
                    Log.e(str, str2, e2);
                    return null;
                }
            }
            Log.e(str, str2, e2);
            return null;
        }
    }

    static /* synthetic */ void c(SAServiceAgent sAServiceAgent) {
        int a2;
        String c2 = sAServiceAgent.c();
        if (c2 == null) {
            a2 = 2048;
        } else {
            try {
                int a3 = sAServiceAgent.b.a(c2, (ISAPeerAgentCallback) sAServiceAgent.d);
                if (a3 == 0) {
                    Log.d("[SA_SDK]SAServiceAgent", "Find peer request successfully enqueued.");
                    return;
                }
                StringBuilder sb = new StringBuilder("Find peer request failed:");
                sb.append(a3);
                sb.append(" for service ");
                sb.append(sAServiceAgent.getClass().getName());
                Log.w("[SA_SDK]SAServiceAgent", sb.toString());
                sAServiceAgent.onFindPeerAgentsResponse(null, a3);
                return;
            } catch (c e2) {
                Log.e("[SA_SDK]SAServiceAgent", "Find Peer request failed!");
                a2 = e2.a();
            }
        }
        sAServiceAgent.a(a2, (SAPeerAgent) null);
    }

    private SASocket d() {
        a(this.l);
        String str = "[SA_SDK]SAServiceAgent";
        try {
            StringBuilder sb = new StringBuilder("Instantiating SASocket: ");
            sb.append(this.l.getName());
            Log.d(str, sb.toString());
            if (this.l.getEnclosingClass() == null || !SAServiceAgent.class.isAssignableFrom(this.l.getEnclosingClass())) {
                Constructor declaredConstructor = this.l.getDeclaredConstructor(new Class[0]);
                declaredConstructor.setAccessible(true);
                return (SASocket) declaredConstructor.newInstance(new Object[0]);
            }
            Constructor declaredConstructor2 = this.l.getDeclaredConstructor(new Class[]{this.l.getEnclosingClass()});
            declaredConstructor2.setAccessible(true);
            return (SASocket) declaredConstructor2.newInstance(new Object[]{this});
        } catch (NoSuchMethodException e2) {
            StringBuilder sb2 = new StringBuilder("Invalid implemetation of SASocket. Provider a public default constructor.");
            sb2.append(e2.getMessage());
            Log.e("[SA_SDK]SAServiceAgent", sb2.toString());
            throw new RuntimeException("Invalid implemetation of SASocket. Provider a public default constructor.");
        } catch (InstantiationException e3) {
            StringBuilder sb3 = new StringBuilder("Invalid implemetation of SASocket. Provider a public default constructor.");
            sb3.append(e3.getMessage());
            Log.e("[SA_SDK]SAServiceAgent", sb3.toString());
            throw new RuntimeException("Invalid implemetation of SASocket. Provider a public default constructor.");
        } catch (IllegalAccessException e4) {
            StringBuilder sb4 = new StringBuilder("Invalid implemetation of SASocket. Provider a public default constructor.");
            sb4.append(e4.getMessage());
            Log.e("[SA_SDK]SAServiceAgent", sb4.toString());
            throw new RuntimeException("Invalid implemetation of SASocket. Provider a public default constructor.");
        } catch (IllegalArgumentException e5) {
            StringBuilder sb5 = new StringBuilder("Invalid implemetation of SASocket. Provider a public default constructor.");
            sb5.append(e5.getMessage());
            Log.e("[SA_SDK]SAServiceAgent", sb5.toString());
            throw new RuntimeException("Invalid implemetation of SASocket. Provider a public default constructor.");
        } catch (InvocationTargetException e6) {
            StringBuilder sb6 = new StringBuilder("Invalid implemetation of SASocket. Provider a public default constructor.");
            sb6.append(e6.getMessage());
            Log.e("[SA_SDK]SAServiceAgent", sb6.toString());
            throw new RuntimeException("Invalid implemetation of SASocket. Provider a public default constructor.");
        }
    }

    static /* synthetic */ void d(SAServiceAgent sAServiceAgent) {
        Log.w("[SA_SDK]SAServiceAgent", "Performing agent cleanup");
        sAServiceAgent.a(false);
        String c2 = sAServiceAgent.c();
        if (c2 != null) {
            sAServiceAgent.b.c(c2);
        }
        sAServiceAgent.b.b((b) sAServiceAgent.f);
        b bVar = sAServiceAgent.a;
        if (bVar != null) {
            bVar.getLooper().quit();
            bVar.a = null;
            sAServiceAgent.a = null;
        }
    }

    static /* synthetic */ void d(SAServiceAgent sAServiceAgent, SAPeerAgent sAPeerAgent) {
        int a2;
        String c2 = sAServiceAgent.c();
        if (c2 == null) {
            a2 = 2048;
        } else {
            try {
                int a3 = sAServiceAgent.b.a(c2, sAPeerAgent, (ISAPeerAgentAuthCallback) sAServiceAgent.e, sAPeerAgent.c());
                if (a3 == 0) {
                    StringBuilder sb = new StringBuilder("Auth. request for peer: ");
                    sb.append(sAPeerAgent.getPeerId());
                    sb.append(" done successfully");
                    Log.i("[SA_SDK]SAServiceAgent", sb.toString());
                    return;
                }
                StringBuilder sb2 = new StringBuilder("Auth. request for peer: ");
                sb2.append(sAPeerAgent.getPeerId());
                sb2.append(" failed as reason: ");
                sb2.append(a3);
                Log.e("[SA_SDK]SAServiceAgent", sb2.toString());
                sAServiceAgent.onAuthenticationResponse(sAPeerAgent, null, a3);
                return;
            } catch (c e2) {
                Log.e("[SA_SDK]SAServiceAgent", "Failed to request peer authentication!", e2);
                a2 = e2.a();
            }
        }
        sAServiceAgent.a(a2, sAPeerAgent);
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0032  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void e() {
        /*
            r6 = this;
            monitor-enter(r6)
            android.content.Context r0 = r6.k     // Catch:{ all -> 0x00f0 }
            com.samsung.android.sdk.accessory.b r0 = com.samsung.android.sdk.accessory.b.a(r0)     // Catch:{ all -> 0x00f0 }
            r6.o = r0     // Catch:{ all -> 0x00f0 }
            com.samsung.android.sdk.accessory.b r0 = r6.o     // Catch:{ all -> 0x00f0 }
            if (r0 == 0) goto L_0x0029
            com.samsung.android.sdk.accessory.b r0 = r6.o     // Catch:{ all -> 0x00f0 }
            java.lang.Class r1 = r6.getClass()     // Catch:{ all -> 0x00f0 }
            java.lang.String r1 = r1.getName()     // Catch:{ all -> 0x00f0 }
            com.samsung.android.sdk.accessory.k r0 = r0.a(r1)     // Catch:{ all -> 0x00f0 }
            r6.p = r0     // Catch:{ all -> 0x00f0 }
            com.samsung.android.sdk.accessory.k r0 = r6.p     // Catch:{ all -> 0x00f0 }
            if (r0 != 0) goto L_0x002e
            java.lang.String r0 = "[SA_SDK]SAServiceAgent"
            java.lang.String r1 = "fetch service profile description failed !!"
        L_0x0025:
            android.util.Log.e(r0, r1)     // Catch:{ all -> 0x00f0 }
            goto L_0x002e
        L_0x0029:
            java.lang.String r0 = "[SA_SDK]SAServiceAgent"
            java.lang.String r1 = "config  util defualt instance  creation failed !!"
            goto L_0x0025
        L_0x002e:
            com.samsung.android.sdk.accessory.k r0 = r6.p     // Catch:{ all -> 0x00f0 }
            if (r0 == 0) goto L_0x00ee
            java.lang.String r0 = "[SA_SDK]SAServiceAgent"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f0 }
            java.lang.String r2 = "=======service profile========\nApplication name : "
            r1.<init>(r2)     // Catch:{ all -> 0x00f0 }
            com.samsung.android.sdk.accessory.k r2 = r6.p     // Catch:{ all -> 0x00f0 }
            java.lang.String r2 = r2.a()     // Catch:{ all -> 0x00f0 }
            r1.append(r2)     // Catch:{ all -> 0x00f0 }
            java.lang.String r2 = "\n - profile id   : "
            r1.append(r2)     // Catch:{ all -> 0x00f0 }
            com.samsung.android.sdk.accessory.k r2 = r6.p     // Catch:{ all -> 0x00f0 }
            java.lang.String r2 = r2.b()     // Catch:{ all -> 0x00f0 }
            r1.append(r2)     // Catch:{ all -> 0x00f0 }
            java.lang.String r2 = "\n - service name : "
            r1.append(r2)     // Catch:{ all -> 0x00f0 }
            com.samsung.android.sdk.accessory.k r2 = r6.p     // Catch:{ all -> 0x00f0 }
            java.lang.String r2 = r2.c()     // Catch:{ all -> 0x00f0 }
            r1.append(r2)     // Catch:{ all -> 0x00f0 }
            java.lang.String r2 = "\n - role : "
            r1.append(r2)     // Catch:{ all -> 0x00f0 }
            com.samsung.android.sdk.accessory.k r2 = r6.p     // Catch:{ all -> 0x00f0 }
            java.lang.String r2 = r2.d()     // Catch:{ all -> 0x00f0 }
            r1.append(r2)     // Catch:{ all -> 0x00f0 }
            java.lang.String r2 = "\n - service impl : "
            r1.append(r2)     // Catch:{ all -> 0x00f0 }
            com.samsung.android.sdk.accessory.k r2 = r6.p     // Catch:{ all -> 0x00f0 }
            java.lang.String r2 = r2.e()     // Catch:{ all -> 0x00f0 }
            r1.append(r2)     // Catch:{ all -> 0x00f0 }
            java.lang.String r2 = "\n - version : "
            r1.append(r2)     // Catch:{ all -> 0x00f0 }
            com.samsung.android.sdk.accessory.k r2 = r6.p     // Catch:{ all -> 0x00f0 }
            java.lang.String r2 = r2.f()     // Catch:{ all -> 0x00f0 }
            r1.append(r2)     // Catch:{ all -> 0x00f0 }
            java.lang.String r2 = "\n - service limit : "
            r1.append(r2)     // Catch:{ all -> 0x00f0 }
            com.samsung.android.sdk.accessory.k r2 = r6.p     // Catch:{ all -> 0x00f0 }
            int r2 = r2.g()     // Catch:{ all -> 0x00f0 }
            r1.append(r2)     // Catch:{ all -> 0x00f0 }
            java.lang.String r2 = "\n - transport type : "
            r1.append(r2)     // Catch:{ all -> 0x00f0 }
            com.samsung.android.sdk.accessory.k r2 = r6.p     // Catch:{ all -> 0x00f0 }
            int r2 = r2.h()     // Catch:{ all -> 0x00f0 }
            r1.append(r2)     // Catch:{ all -> 0x00f0 }
            java.lang.String r2 = "\n"
            r1.append(r2)     // Catch:{ all -> 0x00f0 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00f0 }
            android.util.Log.d(r0, r1)     // Catch:{ all -> 0x00f0 }
            com.samsung.android.sdk.accessory.k r0 = r6.p     // Catch:{ all -> 0x00f0 }
            java.util.List r0 = r0.i()     // Catch:{ all -> 0x00f0 }
            r1 = 0
        L_0x00b9:
            int r2 = r0.size()     // Catch:{ all -> 0x00f0 }
            if (r1 < r2) goto L_0x00c0
            goto L_0x00ee
        L_0x00c0:
            java.lang.String r2 = "[SA_SDK]SAServiceAgent"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f0 }
            java.lang.String r4 = " -- service channel ("
            r3.<init>(r4)     // Catch:{ all -> 0x00f0 }
            int r4 = r1 + 1
            r3.append(r4)     // Catch:{ all -> 0x00f0 }
            java.lang.String r5 = ") : "
            r3.append(r5)     // Catch:{ all -> 0x00f0 }
            java.lang.Object r1 = r0.get(r1)     // Catch:{ all -> 0x00f0 }
            com.samsung.android.sdk.accessory.j r1 = (com.samsung.android.sdk.accessory.j) r1     // Catch:{ all -> 0x00f0 }
            int r1 = r1.a()     // Catch:{ all -> 0x00f0 }
            r3.append(r1)     // Catch:{ all -> 0x00f0 }
            java.lang.String r1 = "\n"
            r3.append(r1)     // Catch:{ all -> 0x00f0 }
            java.lang.String r1 = r3.toString()     // Catch:{ all -> 0x00f0 }
            android.util.Log.d(r2, r1)     // Catch:{ all -> 0x00f0 }
            r1 = r4
            goto L_0x00b9
        L_0x00ee:
            monitor-exit(r6)
            return
        L_0x00f0:
            r0 = move-exception
            monitor-exit(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.accessory.SAServiceAgent.e():void");
    }

    private static boolean f() {
        try {
            Class cls = Class.forName("com.samsung.android.feature.FloatingFeature");
            Object invoke = cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
            return ((Boolean) cls.getMethod("getEnableStatus", new Class[]{String.class}).invoke(invoke, new Object[]{"SEC_FLOATING_FEATURE_CONTEXTSERVICE_ENABLE_SURVEY_MODE"})).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    public static SAServiceAgent getServiceAgent(String str) {
        if (m.containsKey(str)) {
            return (SAServiceAgent) m.get(str);
        }
        return null;
    }

    public static void putServiceAgent(String str, SAServiceAgent sAServiceAgent) {
        if (!m.containsKey(str)) {
            m.put(str, sAServiceAgent);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(int i2, SAPeerAgent sAPeerAgent) {
        switch (i2) {
            case 1033:
                onServiceConnectionResponse(sAPeerAgent, null, 1033);
                return;
            case 2048:
                a(true);
                onError(null, "Samsung Accessory Framework has died!!", i2);
                return;
            case 2049:
                Log.e("[SA_SDK]SAServiceAgent", "Samsung Accessory SDK cannot be initialized");
                onError(null, "Samsung Accessory SDK cannot be initialized. Device or Build not compatible.", i2);
                return;
            case 2304:
            case 2305:
                onError(null, "Permission error!", i2);
                return;
            default:
                StringBuilder sb = new StringBuilder("Unknown error: ");
                sb.append(i2);
                Log.w("[SA_SDK]SAServiceAgent", sb.toString());
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void acceptServiceConnectionRequest(SAPeerAgent sAPeerAgent) {
        if (sAPeerAgent != null) {
            try {
                this.c.initialize(this.k);
                if (this.i.remove(sAPeerAgent)) {
                    StringBuilder sb = new StringBuilder("Trying to Accept service connection request from peer:");
                    sb.append(sAPeerAgent.getPeerId());
                    sb.append(" Transaction:");
                    sb.append(sAPeerAgent.c());
                    Log.i("[SA_SDK]SAServiceAgent", sb.toString());
                    b bVar = this.a;
                    if (bVar != null) {
                        Message obtainMessage = bVar.obtainMessage(7);
                        obtainMessage.obj = sAPeerAgent;
                        this.a.sendMessage(obtainMessage);
                    } else {
                        Log.w("[SA_SDK]SAServiceAgent", "acceptServiceConnection: mBackgroundWorker is null!");
                    }
                } else {
                    StringBuilder sb2 = new StringBuilder("Accepting service connection with invalid peer agent:");
                    sb2.append(sAPeerAgent.toString());
                    Log.w("[SA_SDK]SAServiceAgent", sb2.toString());
                    b(sAPeerAgent);
                }
                if (!this.n) {
                    try {
                        if (SsdkVendorCheck.isSamsungDevice()) {
                            a(this.k, "acceptServiceConnectionRequest");
                        }
                        this.n = true;
                    } catch (SecurityException unused) {
                        throw new SecurityException("com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY permission is required.");
                    }
                }
            } catch (SsdkUnsupportedException e2) {
                StringBuilder sb3 = new StringBuilder("exception: ");
                sb3.append(e2.getMessage());
                Log.e("[SA_SDK]SAServiceAgent", sb3.toString());
                a(2049, sAPeerAgent);
            }
        } else {
            StringBuilder sb4 = new StringBuilder("Illegal argument peerAgent:");
            sb4.append(sAPeerAgent);
            throw new IllegalArgumentException(sb4.toString());
        }
    }

    /* access modifiers changed from: protected */
    public void authenticatePeerAgent(SAPeerAgent sAPeerAgent) {
        if (sAPeerAgent != null) {
            try {
                this.c.initialize(this.k);
                StringBuilder sb = new StringBuilder("Authentication requested for peer:");
                sb.append(sAPeerAgent.getPeerId());
                Log.i("[SA_SDK]SAServiceAgent", sb.toString());
                b bVar = this.a;
                if (bVar != null) {
                    Message obtainMessage = bVar.obtainMessage(9);
                    obtainMessage.obj = sAPeerAgent;
                    this.a.sendMessage(obtainMessage);
                } else {
                    Log.w("[SA_SDK]SAServiceAgent", "authenticatePeerAgent: mBackgroundWorker is null!");
                }
                if (!this.n) {
                    try {
                        if (SsdkVendorCheck.isSamsungDevice()) {
                            a(this.k, "authenticatePeerAgent");
                        }
                        this.n = true;
                    } catch (SecurityException unused) {
                        throw new SecurityException("com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY permission is required.");
                    }
                }
            } catch (SsdkUnsupportedException e2) {
                StringBuilder sb2 = new StringBuilder("exception: ");
                sb2.append(e2.getMessage());
                Log.e("[SA_SDK]SAServiceAgent", sb2.toString());
                a(2049, sAPeerAgent);
            }
        } else {
            StringBuilder sb3 = new StringBuilder("Illegal argument peerAgent:");
            sb3.append(sAPeerAgent);
            throw new IllegalArgumentException(sb3.toString());
        }
    }

    public void destroyServiceAgent() {
        StringBuilder sb = new StringBuilder("SAServiceAgent - onDestroy:");
        sb.append(getClass().getSimpleName());
        Log.d("[SA_SDK]SAServiceAgent", sb.toString());
        b bVar = this.a;
        if (bVar != null) {
            bVar.obtainMessage(13).sendToTarget();
        }
        m.remove(getClass().getName());
    }

    /* access modifiers changed from: protected */
    public final synchronized void findPeerAgents() {
        String str = "[SA_SDK]SAServiceAgent";
        StringBuilder sb = new StringBuilder("findPeer request received by:");
        sb.append(getClass().getName());
        Log.d(str, sb.toString());
        try {
            this.c.initialize(this.k);
            if (this.a != null) {
                Message obtainMessage = this.a.obtainMessage();
                obtainMessage.what = 2;
                this.a.sendMessage(obtainMessage);
                return;
            }
            Log.w("[SA_SDK]SAServiceAgent", "findPeerAgents: mBackgroundWorker is null!");
        } catch (SsdkUnsupportedException e2) {
            String str2 = "[SA_SDK]SAServiceAgent";
            StringBuilder sb2 = new StringBuilder("exception: ");
            sb2.append(e2.getMessage());
            Log.e(str2, sb2.toString());
            a(2049, (SAPeerAgent) null);
        }
    }

    public Context getApplicationContext() {
        return this.k;
    }

    public String getProfileId() {
        k kVar = this.p;
        if (kVar != null) {
            return kVar.b();
        }
        Log.e("[SA_SDK]SAServiceAgent", "Failed because Service Profile is null");
        return null;
    }

    public String getProfileName() {
        k kVar = this.p;
        if (kVar != null) {
            return kVar.c();
        }
        Log.e("[SA_SDK]SAServiceAgent", "Failed because Service Profile is null");
        return null;
    }

    public int getServiceChannelId(int i2) {
        String str;
        String str2;
        if (this.p == null) {
            str = "[SA_SDK]SAServiceAgent";
            str2 = "Failed because Service Profile is null";
        } else if (i2 >= 0 && i2 < getServiceChannelSize()) {
            return ((j) this.p.i().get(i2)).a();
        } else {
            str = "[SA_SDK]SAServiceAgent";
            str2 = "Failed because of wrong index";
        }
        Log.e(str, str2);
        return -1;
    }

    public int getServiceChannelSize() {
        k kVar = this.p;
        if (kVar != null) {
            return kVar.i().size();
        }
        Log.e("[SA_SDK]SAServiceAgent", "Failed because Service Profile is null");
        return -1;
    }

    /* access modifiers changed from: protected */
    public void onAuthenticationResponse(SAPeerAgent sAPeerAgent, SAAuthenticationToken sAAuthenticationToken, int i2) {
    }

    /* access modifiers changed from: protected */
    public void onError(SAPeerAgent sAPeerAgent, String str, int i2) {
        if (sAPeerAgent == null) {
            StringBuilder sb = new StringBuilder("ACCEPT_STATE_ERROR: ");
            sb.append(i2);
            sb.append(": ");
            sb.append(str);
            sb.append(" PeerAgent: null");
            Log.e("[SA_SDK]SAServiceAgent", sb.toString());
            return;
        }
        StringBuilder sb2 = new StringBuilder("ACCEPT_STATE_ERROR: ");
        sb2.append(i2);
        sb2.append(": ");
        sb2.append(str);
        sb2.append(" PeerAgent: ");
        sb2.append(sAPeerAgent.getPeerId());
        Log.e("[SA_SDK]SAServiceAgent", sb2.toString());
    }

    /* access modifiers changed from: protected */
    public void onFindPeerAgentsResponse(SAPeerAgent[] sAPeerAgentArr, int i2) {
    }

    /* access modifiers changed from: protected */
    public void onPeerAgentsUpdated(SAPeerAgent[] sAPeerAgentArr, int i2) {
    }

    /* access modifiers changed from: protected */
    public void onServiceConnectionRequested(SAPeerAgent sAPeerAgent) {
        if (sAPeerAgent != null) {
            StringBuilder sb = new StringBuilder("Accepting connection request by default from Peer:");
            sb.append(sAPeerAgent.getPeerId());
            sb.append(" Transaction:");
            sb.append(sAPeerAgent.c());
            Log.v("[SA_SDK]SAServiceAgent", sb.toString());
        }
        acceptServiceConnectionRequest(sAPeerAgent);
    }

    /* access modifiers changed from: protected */
    public void onServiceConnectionResponse(SAPeerAgent sAPeerAgent, SASocket sASocket, int i2) {
    }

    /* access modifiers changed from: protected */
    public void rejectServiceConnectionRequest(SAPeerAgent sAPeerAgent) {
        if (sAPeerAgent != null) {
            try {
                this.c.initialize(this.k);
                if (this.i.remove(sAPeerAgent)) {
                    StringBuilder sb = new StringBuilder("Trying to reject connection request from peer:");
                    sb.append(sAPeerAgent.getPeerId());
                    sb.append(" Transaction:");
                    sb.append(sAPeerAgent.c());
                    Log.i("[SA_SDK]SAServiceAgent", sb.toString());
                    b bVar = this.a;
                    if (bVar != null) {
                        Message obtainMessage = bVar.obtainMessage(8);
                        obtainMessage.obj = sAPeerAgent;
                        this.a.sendMessage(obtainMessage);
                    } else {
                        Log.w("[SA_SDK]SAServiceAgent", "rejectServiceConnection: mBackgroundWorker is null!");
                    }
                } else {
                    StringBuilder sb2 = new StringBuilder("Rejecting service connection with invalid peer agent:");
                    sb2.append(sAPeerAgent.toString());
                    Log.w("[SA_SDK]SAServiceAgent", sb2.toString());
                    b(sAPeerAgent);
                }
                if (!this.n) {
                    try {
                        if (SsdkVendorCheck.isSamsungDevice()) {
                            a(this.k, "rejectServiceConnectionRequest");
                        }
                        this.n = true;
                    } catch (SecurityException unused) {
                        throw new SecurityException("com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY permission is required.");
                    }
                }
            } catch (SsdkUnsupportedException e2) {
                StringBuilder sb3 = new StringBuilder("exception: ");
                sb3.append(e2.getMessage());
                Log.e("[SA_SDK]SAServiceAgent", sb3.toString());
                a(2049, sAPeerAgent);
            }
        } else {
            StringBuilder sb4 = new StringBuilder("Illegal argument peerAgent:");
            sb4.append(sAPeerAgent);
            throw new IllegalArgumentException(sb4.toString());
        }
    }

    /* access modifiers changed from: protected */
    public final void requestServiceConnection(SAPeerAgent sAPeerAgent) {
        if (sAPeerAgent != null) {
            try {
                this.c.initialize(this.k);
                StringBuilder sb = new StringBuilder("Service connection requested for peer:");
                sb.append(sAPeerAgent.getPeerId());
                Log.i("[SA_SDK]SAServiceAgent", sb.toString());
                b bVar = this.a;
                if (bVar != null) {
                    Message obtainMessage = bVar.obtainMessage(6);
                    obtainMessage.obj = sAPeerAgent;
                    this.a.sendMessage(obtainMessage);
                } else {
                    Log.w("[SA_SDK]SAServiceAgent", "requestServiceConection: mBackgroundWorker is null!");
                }
                if (!this.n) {
                    try {
                        if (SsdkVendorCheck.isSamsungDevice()) {
                            a(this.k, "requestServiceConnection");
                        }
                        this.n = true;
                    } catch (SecurityException unused) {
                        throw new SecurityException("com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY permission is required.");
                    }
                }
            } catch (SsdkUnsupportedException e2) {
                StringBuilder sb2 = new StringBuilder("exception: ");
                sb2.append(e2.getMessage());
                Log.e("[SA_SDK]SAServiceAgent", sb2.toString());
                a(2049, sAPeerAgent);
            }
        } else {
            StringBuilder sb3 = new StringBuilder("Illegal argument peerAgent:");
            sb3.append(sAPeerAgent);
            throw new IllegalArgumentException(sb3.toString());
        }
    }
}
