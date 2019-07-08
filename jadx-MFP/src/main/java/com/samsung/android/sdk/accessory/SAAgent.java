package com.samsung.android.sdk.accessory;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.samsung.accessory.api.ISAPeerAgentAuthCallback;
import com.samsung.accessory.api.ISAPeerAgentAuthCallback.Stub;
import com.samsung.accessory.api.ISAPeerAgentCallback;
import com.samsung.android.sdk.SsdkUnsupportedException;
import com.samsung.android.sdk.SsdkVendorCheck;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public abstract class SAAgent extends Service {
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
    private String k;
    private SAMessage l;
    private Class<? extends SASocket> m;
    private boolean n = false;
    private b o = null;
    private k p = null;

    class AuthenticationCallback extends Stub {
        private AuthenticationCallback() {
        }

        /* synthetic */ AuthenticationCallback(SAAgent sAAgent, AuthenticationCallback authenticationCallback) {
            this();
        }

        public void onPeerAgentAuthenticated(Bundle bundle) throws RemoteException {
            Log.v("[SA_SDK]SAAgent", "Received Authentication response");
            if (SAAgent.this.a != null) {
                Message obtainMessage = SAAgent.this.a.obtainMessage(10);
                obtainMessage.setData(bundle);
                SAAgent.this.a.sendMessage(obtainMessage);
                return;
            }
            Log.w("[SA_SDK]SAAgent", "onPeerAgentAuthenticated: mBackgroundWorker is null!");
        }
    }

    class PeerAgentCallback extends ISAPeerAgentCallback.Stub {
        private PeerAgentCallback() {
        }

        /* synthetic */ PeerAgentCallback(SAAgent sAAgent, PeerAgentCallback peerAgentCallback) {
            this();
        }

        public void onPeerAgentUpdated(Bundle bundle) throws RemoteException {
            Log.v("[SA_SDK]SAAgent", "Received peer agent update");
            bundle.setClassLoader(SAPeerAgent.class.getClassLoader());
            if (bundle.containsKey("peerAgents")) {
                ArrayList<SAPeerAgent> parcelableArrayList = bundle.getParcelableArrayList("peerAgents");
                int i = bundle.getInt("peerAgentStatus");
                if (parcelableArrayList == null) {
                    Log.e("[SA_SDK]SAAgent", "Peer Update - invalid peer agent list from Accessory Framework");
                } else if (i == 105 || i == 106) {
                    StringBuilder sb = new StringBuilder(String.valueOf(parcelableArrayList.size()));
                    sb.append(" Peer agent(s) updated for:");
                    sb.append(getClass().getName());
                    Log.i("[SA_SDK]SAAgent", sb.toString());
                    for (SAPeerAgent sAPeerAgent : parcelableArrayList) {
                        StringBuilder sb2 = new StringBuilder("Peer ID:");
                        sb2.append(sAPeerAgent.getPeerId());
                        sb2.append("Container Id:");
                        sb2.append(sAPeerAgent.getContainerId());
                        sb2.append(" Accessory");
                        sb2.append(sAPeerAgent.getAccessory().getAccessoryId());
                        Log.i("[SA_SDK]SAAgent", sb2.toString());
                    }
                    if (SAAgent.this.a != null) {
                        Message obtainMessage = SAAgent.this.a.obtainMessage();
                        obtainMessage.what = 4;
                        obtainMessage.arg1 = i == 105 ? 1 : 2;
                        obtainMessage.obj = parcelableArrayList.toArray(new SAPeerAgent[parcelableArrayList.size()]);
                        SAAgent.this.a.sendMessage(obtainMessage);
                        return;
                    }
                    Log.w("[SA_SDK]SAAgent", "onPeerAgentUpdated: mBackgroundWorker is null!");
                } else {
                    StringBuilder sb3 = new StringBuilder("Peer Update - invalid peer status from Accessory Framework:");
                    sb3.append(i);
                    Log.e("[SA_SDK]SAAgent", sb3.toString());
                }
            } else {
                Log.e("[SA_SDK]SAAgent", "No peer agents in PeerAgent update callback!");
            }
        }

        public void onPeerAgentsFound(Bundle bundle) throws RemoteException {
            Log.v("[SA_SDK]SAAgent", "FindPeer response received.");
            bundle.setClassLoader(SAPeerAgent.class.getClassLoader());
            if (bundle.containsKey("errorcode")) {
                int i = bundle.getInt("errorcode");
                StringBuilder sb = new StringBuilder("Peer Not Found(");
                sb.append(i);
                sb.append(") for: ");
                sb.append(getClass().getName());
                Log.e("[SA_SDK]SAAgent", sb.toString());
                if (SAAgent.this.a != null) {
                    Message obtainMessage = SAAgent.this.a.obtainMessage();
                    obtainMessage.what = 3;
                    obtainMessage.arg1 = i;
                    SAAgent.this.a.sendMessage(obtainMessage);
                    return;
                }
                Log.w("[SA_SDK]SAAgent", "onPeersAgentsFound: mBackgroundWorker is null!");
                return;
            }
            ArrayList<SAPeerAgent> parcelableArrayList = bundle.getParcelableArrayList("peerAgents");
            if (parcelableArrayList == null) {
                Log.e("[SA_SDK]SAAgent", "Find Peer - invalid response from Accessory Framework");
                return;
            }
            StringBuilder sb2 = new StringBuilder(String.valueOf(parcelableArrayList.size()));
            sb2.append(" Peer agent(s) found for:");
            sb2.append(getClass().getName());
            Log.i("[SA_SDK]SAAgent", sb2.toString());
            for (SAPeerAgent sAPeerAgent : parcelableArrayList) {
                StringBuilder sb3 = new StringBuilder("Peer ID:");
                sb3.append(sAPeerAgent.getPeerId());
                sb3.append("Container Id:");
                sb3.append(sAPeerAgent.getContainerId());
                sb3.append(" Accessory");
                sb3.append(sAPeerAgent.getAccessory().getAccessoryId());
                sb3.append(" Transport:");
                sb3.append(sAPeerAgent.getAccessory().getTransportType());
                Log.i("[SA_SDK]SAAgent", sb3.toString());
            }
            if (SAAgent.this.a != null) {
                Message obtainMessage2 = SAAgent.this.a.obtainMessage();
                obtainMessage2.what = 3;
                obtainMessage2.arg1 = 0;
                obtainMessage2.obj = parcelableArrayList.toArray(new SAPeerAgent[parcelableArrayList.size()]);
                SAAgent.this.a.sendMessage(obtainMessage2);
                return;
            }
            Log.w("[SA_SDK]SAAgent", "onPeerAgentsFound: mBackgroundWorker is null!");
        }
    }

    static class a implements b {
        private SAAgent a;

        public a(SAAgent sAAgent) {
            this.a = sAAgent;
        }

        public final void a() {
            if (this.a.a != null) {
                Message obtainMessage = this.a.a.obtainMessage(11);
                obtainMessage.arg1 = 2048;
                this.a.a.sendMessage(obtainMessage);
                return;
            }
            Log.w("[SA_SDK]SAAgent", "onFrameworkDisconnected: mBackgroundWorker is null!");
        }

        public final void b() {
            try {
                this.a.b();
            } catch (c e) {
                StringBuilder sb = new StringBuilder("onFrameworkConnected() - Failed to register agent with message! ");
                sb.append(e.getMessage());
                Log.e("[SA_SDK]SAAgent", sb.toString());
            }
        }

        public final void c() throws c {
            this.a.a.sendEmptyMessage(14);
        }
    }

    static class b extends Handler {
        SAAgent a;

        public b(SAAgent sAAgent, Looper looper) {
            super(looper);
            this.a = sAAgent;
        }

        public final void handleMessage(Message message) {
            SAPeerAgent sAPeerAgent = null;
            switch (message.what) {
                case 0:
                    try {
                        SAAgent.a(this.a);
                        return;
                    } catch (c e) {
                        Log.e("[SA_SDK]SAAgent", "Binding to Accessory Framework failed", e);
                        this.a.a(e.a(), (SAPeerAgent) null);
                        return;
                    }
                case 1:
                    this.a.c();
                    return;
                case 2:
                    SAAgent.c(this.a);
                    return;
                case 3:
                    if (message.arg1 != 0) {
                        SAAgent sAAgent = this.a;
                        if (sAAgent instanceof SAManagerAgent) {
                            new ArrayList();
                            return;
                        }
                        sAAgent.onFindPeerAgentsResponse(null, message.arg1);
                        SAAgent.c(message.arg1);
                        return;
                    } else if (this.a instanceof SAManagerAgent) {
                        Arrays.asList((SAPeerAgent[]) message.obj);
                        return;
                    } else {
                        this.a.onFindPeerAgentsResponse((SAPeerAgent[]) message.obj, 0);
                        SAAgent.c(0);
                        return;
                    }
                case 4:
                    this.a.onPeerAgentsUpdated((SAPeerAgent[]) message.obj, message.arg1);
                    SAAgent.b(message.arg1);
                    return;
                case 5:
                    SAAgent.a(this.a, (Intent) message.obj);
                    return;
                case 6:
                    SAAgent.a(this.a, (SAPeerAgent) message.obj);
                    return;
                case 7:
                    SAAgent.b(this.a, (SAPeerAgent) message.obj);
                    return;
                case 8:
                    this.a.a((SAPeerAgent) message.obj);
                    return;
                case 9:
                    SAAgent.d(this.a, (SAPeerAgent) message.obj);
                    return;
                case 10:
                    SAAgent.a(this.a, message.getData());
                    return;
                case 11:
                    if (message.obj != null && (message.obj instanceof SAPeerAgent)) {
                        sAPeerAgent = (SAPeerAgent) message.obj;
                    }
                    this.a.a(message.arg1, sAPeerAgent);
                    return;
                case 12:
                    this.a.onServiceConnectionResponse((message.obj == null || !(message.obj instanceof SAPeerAgent)) ? null : (SAPeerAgent) message.obj, null, message.arg1);
                    this.a.e(message.arg1);
                    return;
                case 13:
                    SAAgent.d(this.a);
                    return;
                case 14:
                    try {
                        this.a.a();
                        return;
                    } catch (c e2) {
                        Log.e("[SA_SDK]SAAgent", "Retrieving agent id failed", e2);
                        this.a.a(e2.a(), (SAPeerAgent) null);
                        return;
                    }
                default:
                    StringBuilder sb = new StringBuilder("Invalid msg received: ");
                    sb.append(message.what);
                    Log.w("[SA_SDK]SAAgent", sb.toString());
                    return;
            }
        }
    }

    class c implements a {
        private c() {
        }

        /* synthetic */ c(SAAgent sAAgent, byte b) {
            this();
        }

        public final void a(SAPeerAgent sAPeerAgent, int i) {
            if (i == 2048) {
                Log.w("[SA_SDK]SAAgent", "Framework disconnected during connection process!");
                SAAgent.this.a(i, sAPeerAgent);
            } else if (SAAgent.this.a != null) {
                if (i == 1034) {
                    i = 1033;
                    SAAgent.this.a.sendMessage(SAAgent.this.a.obtainMessage(1));
                }
                StringBuilder sb = new StringBuilder("Connection attempt failed wih peer:");
                sb.append(sAPeerAgent.getPeerId());
                sb.append(" reason:");
                sb.append(i);
                Log.e("[SA_SDK]SAAgent", sb.toString());
                Message obtainMessage = SAAgent.this.a.obtainMessage(12);
                obtainMessage.arg1 = i;
                obtainMessage.obj = sAPeerAgent;
                SAAgent.this.a.sendMessage(obtainMessage);
            } else {
                Log.w("[SA_SDK]SAAgent", "onConnectionFailure: mBackgroundWorker is null!");
            }
        }

        public final void a(SAPeerAgent sAPeerAgent, SASocket sASocket) {
            synchronized (SAAgent.this.h) {
                SAAgent.this.h.add(sASocket);
            }
            StringBuilder sb = new StringBuilder("Connection success with peer:");
            sb.append(sAPeerAgent.getPeerId());
            Log.i("[SA_SDK]SAAgent", sb.toString());
            SAAgent.this.onServiceConnectionResponse(sAPeerAgent, sASocket, 0);
            SAAgent.this.e(0);
        }
    }

    protected SAAgent(String str) {
        if (str == null || str.equalsIgnoreCase("")) {
            StringBuilder sb = new StringBuilder("Invalid parameter name:");
            sb.append(str);
            throw new IllegalArgumentException(sb.toString());
        }
        this.j = str;
    }

    protected SAAgent(String str, Class<? extends SASocket> cls) {
        if (str == null || str.equalsIgnoreCase("")) {
            StringBuilder sb = new StringBuilder("Invalid parameter name:");
            sb.append(str);
            throw new IllegalArgumentException(sb.toString());
        }
        a(cls);
        this.j = str;
        this.m = cls;
        StringBuilder sb2 = new StringBuilder("Thread Name:");
        sb2.append(this.j);
        sb2.append("SASocket Imple class:");
        sb2.append(cls.getName());
        Log.d("[SA_SDK]SAAgent", sb2.toString());
    }

    /* access modifiers changed from: private */
    public void a() throws c {
        String d2 = d();
        if (d2 == null) {
            a(2048, (SAPeerAgent) null);
            return;
        }
        Editor edit = getApplicationContext().getSharedPreferences("AccessoryPreferences", 0).edit();
        edit.putString(d2, getClass().getName());
        edit.putString(getClass().getName(), d2);
        edit.commit();
        this.k = d2;
        b();
    }

    private void a(Context context, int i2) {
        StringBuilder sb = new StringBuilder(String.valueOf(i2));
        sb.append("#");
        sb.append(VERSION.SDK_INT);
        sb.append("#");
        sb.append(i.e());
        sb.append("#");
        sb.append(getServiceProfileId());
        f.a(context, sb.toString());
    }

    private void a(Context context, String str) {
        StringBuilder sb = new StringBuilder(String.valueOf(context.getPackageName()));
        sb.append("#");
        sb.append(this.c.getVersionCode());
        f.a(context, sb.toString(), str);
    }

    static /* synthetic */ void a(SAAgent sAAgent) throws c {
        sAAgent.b.a();
        sAAgent.b.a((b) sAAgent.f);
        sAAgent.a();
    }

    static /* synthetic */ void a(SAAgent sAAgent, Intent intent) {
        if (intent == null) {
            Log.e("[SA_SDK]SAAgent", "Invalid service connection indication.Intent:null.Ignoring reqeuset");
            return;
        }
        long longExtra = intent.getLongExtra("transactionId", 0);
        SAPeerAgent sAPeerAgent = (SAPeerAgent) intent.getParcelableExtra("peerAgent");
        String stringExtra = intent.getStringExtra("agentId");
        if (sAPeerAgent == null) {
            StringBuilder sb = new StringBuilder("Invalid initiator peer agent:");
            sb.append(sAPeerAgent);
            sb.append(". Ignoring connection request");
            Log.e("[SA_SDK]SAAgent", sb.toString());
        } else if (stringExtra == null) {
            StringBuilder sb2 = new StringBuilder("Invalid local agent Id:");
            sb2.append(stringExtra);
            sb2.append(".Ignoring connection request");
            Log.e("[SA_SDK]SAAgent", sb2.toString());
        } else {
            sAPeerAgent.a(longExtra);
            StringBuilder sb3 = new StringBuilder("Connection initiated by peer: ");
            sb3.append(sAPeerAgent.getPeerId());
            sb3.append(" on Accessory: ");
            sb3.append(sAPeerAgent.getAccessory().getAccessoryId());
            sb3.append(" Transaction: ");
            sb3.append(longExtra);
            Log.i("[SA_SDK]SAAgent", sb3.toString());
            sAAgent.i.add(sAPeerAgent);
            sAAgent.onServiceConnectionRequested(sAPeerAgent);
        }
    }

    static /* synthetic */ void a(SAAgent sAAgent, Bundle bundle) {
        bundle.setClassLoader(SAPeerAgent.class.getClassLoader());
        byte[] byteArray = bundle.getByteArray("PEER_AGENT_KEY");
        int i2 = bundle.getInt("CERT_TYPE");
        SAPeerAgent sAPeerAgent = (SAPeerAgent) bundle.getParcelable("peerAgent");
        long j2 = bundle.getLong("transactionId");
        if (sAPeerAgent == null) {
            Log.e("[SA_SDK]SAAgent", "Invalid response from framework! No peer agent in auth response.Ignoring response");
            return;
        }
        sAPeerAgent.a(j2);
        int i3 = 0;
        if (byteArray == null) {
            i3 = 1545;
            StringBuilder sb = new StringBuilder("Authentication failed error:1545 Peer Id:");
            sb.append(sAPeerAgent.getPeerId());
            Log.e("[SA_SDK]SAAgent", sb.toString());
        } else {
            StringBuilder sb2 = new StringBuilder("Authentication success status: 0 for peer: ");
            sb2.append(sAPeerAgent.getPeerId());
            Log.i("[SA_SDK]SAAgent", sb2.toString());
        }
        sAAgent.onAuthenticationResponse(sAPeerAgent, new SAAuthenticationToken(i2, byteArray), i3);
        d(i3);
    }

    static /* synthetic */ void a(SAAgent sAAgent, SAPeerAgent sAPeerAgent) {
        String d2 = sAAgent.d();
        if (d2 == null) {
            Log.e("[SA_SDK]SAAgent", "Failed to retrieve service description.Ignoring service connection request");
            sAAgent.a(2048, sAPeerAgent);
            return;
        }
        sAAgent.e().a(d2, sAPeerAgent, sAAgent.b, (a) sAAgent.g);
    }

    /* access modifiers changed from: private */
    public void a(SAPeerAgent sAPeerAgent) {
        int a2;
        String d2 = d();
        if (d2 == null) {
            a2 = 2048;
        } else {
            try {
                this.b.a(d2, sAPeerAgent, sAPeerAgent.c());
                return;
            } catch (c e2) {
                Log.e("[SA_SDK]SAAgent", "Failed to reject Service connection!", e2);
                a2 = e2.a();
            }
        }
        a(a2, sAPeerAgent);
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
                Log.e("[SA_SDK]SAAgent", sb.toString(), e2);
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
        this.c.clearSdkConfig();
    }

    /* access modifiers changed from: private */
    public void b() throws c {
        SAMessage sAMessage = this.l;
        if (sAMessage != null) {
            String str = this.k;
            if (str != null) {
                sAMessage.a(str);
            }
        }
    }

    static /* synthetic */ void b(int i2) {
        switch (i2) {
            case 1:
                Log.i("[SA_SDK]SAAgent", "onPeerAgentUpdated() -> PEER_AGENT_AVAILABLE");
                return;
            case 2:
                Log.i("[SA_SDK]SAAgent", "onPeerAgentUpdated() -> PEER_AGENT_UNAVAILABLE");
                return;
            default:
                StringBuilder sb = new StringBuilder("onPeerAgentUpdated() error_code: ");
                sb.append(i2);
                Log.w("[SA_SDK]SAAgent", sb.toString());
                return;
        }
    }

    static /* synthetic */ void b(SAAgent sAAgent, SAPeerAgent sAPeerAgent) {
        String d2 = sAAgent.d();
        if (d2 == null) {
            sAAgent.a(2048, sAPeerAgent);
        } else {
            sAAgent.e().b(d2, sAPeerAgent, sAAgent.b, sAAgent.g);
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
                Log.w("[SA_SDK]SAAgent", "handleInvlaidPeerAction: mBackgroundWorker is null!");
            }
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        String str;
        String str2;
        h hVar = new h(getApplicationContext());
        Future a2 = hVar.a();
        hVar.b();
        try {
            a2.get();
        } catch (InterruptedException unused) {
            str = "[SA_SDK]SAAgent";
            str2 = "Regisration failed! : InterruptedException";
            Log.e(str, str2);
        } catch (ExecutionException unused2) {
            str = "[SA_SDK]SAAgent";
            str2 = "Registration failed! : ExecutionException";
            Log.e(str, str2);
        }
    }

    /* access modifiers changed from: private */
    public static void c(int i2) {
        if (i2 == 0) {
            Log.i("[SA_SDK]SAAgent", "onFindPeerAgentsResponse() -> PEER_AGENT_FOUND");
        } else if (i2 != 3085) {
            switch (i2) {
                case 1793:
                    Log.i("[SA_SDK]SAAgent", "onFindPeerAgentsResponse() -> FINDPEER_DEVICE_NOT_CONNECTED");
                    return;
                case 1794:
                    Log.i("[SA_SDK]SAAgent", "onFindPeerAgentsResponse() -> FINDPEER_SERVICE_NOT_FOUND");
                    return;
                default:
                    StringBuilder sb = new StringBuilder("onFindPeerAgentsResponse() error_code: ");
                    sb.append(i2);
                    Log.w("[SA_SDK]SAAgent", sb.toString());
                    return;
            }
        } else {
            Log.i("[SA_SDK]SAAgent", "onFindPeerAgentsResponse() -> FINDPEER_DUPLICATE_REQUEST");
        }
    }

    static /* synthetic */ void c(SAAgent sAAgent) {
        int a2;
        String d2 = sAAgent.d();
        if (d2 == null) {
            a2 = 2048;
        } else {
            try {
                int a3 = sAAgent.b.a(d2, (ISAPeerAgentCallback) sAAgent.d);
                if (a3 == 0) {
                    Log.d("[SA_SDK]SAAgent", "Find peer request successfully enqueued.");
                    return;
                }
                StringBuilder sb = new StringBuilder("Find peer request failed:");
                sb.append(a3);
                sb.append(" for service ");
                sb.append(sAAgent.getClass().getName());
                Log.w("[SA_SDK]SAAgent", sb.toString());
                sAAgent.onFindPeerAgentsResponse(null, a3);
                c(a3);
                return;
            } catch (c e2) {
                Log.e("[SA_SDK]SAAgent", "Find Peer request failed!");
                a2 = e2.a();
            }
        }
        sAAgent.a(a2, (SAPeerAgent) null);
    }

    private String d() {
        String str;
        String str2;
        try {
            String a2 = this.b.a(getClass().getName());
            StringBuilder sb = new StringBuilder("Agent ID retrieved successfully for ");
            sb.append(getClass().getName());
            sb.append(" Agent ID:");
            sb.append(a2);
            Log.i("[SA_SDK]SAAgent", sb.toString());
            return a2;
        } catch (c e2) {
            if (e2.a() != 777 || i.d() < 298) {
                str = "[SA_SDK]SAAgent";
                str2 = "Failed to retrieve service record";
            } else {
                Log.w("[SA_SDK]SAAgent", "Service record was not found in Accessory Framework.Registering service again!");
                c();
                try {
                    Log.i("[SA_SDK]SAAgent", "Trying to fetch agent ID after re-registration");
                    return this.b.a(getClass().getName());
                } catch (c unused) {
                    str = "[SA_SDK]SAAgent";
                    str2 = "Failed to retrieve service record after re-registration";
                    Log.e(str, str2, e2);
                    return null;
                }
            }
            Log.e(str, str2, e2);
            return null;
        }
    }

    private static void d(int i2) {
        if (i2 != 0) {
            switch (i2) {
                case 1545:
                    Log.i("[SA_SDK]SAAgent", "onAuthenticationResponse() -> AUTHENTICATION_FAILURE_TOKEN_NOT_GENERATED");
                    return;
                case 1546:
                    Log.i("[SA_SDK]SAAgent", "onAuthenticationResponse() -> AUTHENTICATION_FAILURE_PEER_AGENT_NOT_SUPPORTED");
                    return;
                default:
                    StringBuilder sb = new StringBuilder("onAuthenticationResponse() error_code: ");
                    sb.append(i2);
                    Log.w("[SA_SDK]SAAgent", sb.toString());
                    return;
            }
        } else {
            Log.i("[SA_SDK]SAAgent", "onAuthenticationResponse() -> AUTHENTICATION_SUCCESS");
        }
    }

    static /* synthetic */ void d(SAAgent sAAgent) {
        Log.w("[SA_SDK]SAAgent", "Performing agent cleanup");
        sAAgent.a(false);
        String d2 = sAAgent.d();
        if (d2 != null) {
            sAAgent.b.c(d2);
        }
        sAAgent.b.b((b) sAAgent.f);
        SAMessage sAMessage = sAAgent.l;
        if (sAMessage != null) {
            sAMessage.a();
        }
        b bVar = sAAgent.a;
        if (bVar != null) {
            bVar.getLooper().quit();
            bVar.a = null;
            sAAgent.a = null;
        }
    }

    static /* synthetic */ void d(SAAgent sAAgent, SAPeerAgent sAPeerAgent) {
        int a2;
        String d2 = sAAgent.d();
        if (d2 == null) {
            a2 = 2048;
        } else {
            try {
                int a3 = sAAgent.b.a(d2, sAPeerAgent, (ISAPeerAgentAuthCallback) sAAgent.e, sAPeerAgent.c());
                if (a3 == 0) {
                    StringBuilder sb = new StringBuilder("Auth. request for peer: ");
                    sb.append(sAPeerAgent.getPeerId());
                    sb.append(" done successfully");
                    Log.i("[SA_SDK]SAAgent", sb.toString());
                    return;
                }
                StringBuilder sb2 = new StringBuilder("Auth. request for peer: ");
                sb2.append(sAPeerAgent.getPeerId());
                sb2.append(" failed as reason: ");
                sb2.append(a3);
                Log.e("[SA_SDK]SAAgent", sb2.toString());
                sAAgent.onAuthenticationResponse(sAPeerAgent, null, a3);
                d(a3);
                return;
            } catch (c e2) {
                Log.e("[SA_SDK]SAAgent", "Failed to request peer authentication!", e2);
                a2 = e2.a();
            }
        }
        sAAgent.a(a2, sAPeerAgent);
    }

    private SASocket e() {
        a(this.m);
        String str = "[SA_SDK]SAAgent";
        try {
            StringBuilder sb = new StringBuilder("Instantiating SASocket: ");
            sb.append(this.m.getName());
            Log.d(str, sb.toString());
            if (this.m.getEnclosingClass() == null || !SAAgent.class.isAssignableFrom(this.m.getEnclosingClass())) {
                Constructor declaredConstructor = this.m.getDeclaredConstructor(new Class[0]);
                declaredConstructor.setAccessible(true);
                return (SASocket) declaredConstructor.newInstance(new Object[0]);
            }
            Constructor declaredConstructor2 = this.m.getDeclaredConstructor(new Class[]{this.m.getEnclosingClass()});
            declaredConstructor2.setAccessible(true);
            return (SASocket) declaredConstructor2.newInstance(new Object[]{this});
        } catch (NoSuchMethodException e2) {
            StringBuilder sb2 = new StringBuilder("Invalid implemetation of SASocket. Provide a public default constructor.");
            sb2.append(e2.getClass().getSimpleName());
            sb2.append(" ");
            sb2.append(e2.getMessage());
            Log.e("[SA_SDK]SAAgent", sb2.toString());
            throw new RuntimeException("Invalid implemetation of SASocket. Provide a public default constructor.");
        } catch (InstantiationException e3) {
            StringBuilder sb3 = new StringBuilder("Invalid implemetation of SASocket. Provide a public default constructor.");
            sb3.append(e3.getClass().getSimpleName());
            sb3.append(" ");
            sb3.append(e3.getMessage());
            Log.e("[SA_SDK]SAAgent", sb3.toString());
            throw new RuntimeException("Invalid implemetation of SASocket. Provide a public default constructor.");
        } catch (IllegalAccessException e4) {
            StringBuilder sb4 = new StringBuilder("Invalid implemetation of SASocket. Provide a public default constructor.");
            sb4.append(e4.getClass().getSimpleName());
            sb4.append(" ");
            sb4.append(e4.getMessage());
            Log.e("[SA_SDK]SAAgent", sb4.toString());
            throw new RuntimeException("Invalid implemetation of SASocket. Provide a public default constructor.");
        } catch (IllegalArgumentException e5) {
            StringBuilder sb5 = new StringBuilder("Invalid implemetation of SASocket. Provide a public default constructor.");
            sb5.append(e5.getClass().getSimpleName());
            sb5.append(" ");
            sb5.append(e5.getMessage());
            Log.e("[SA_SDK]SAAgent", sb5.toString());
            throw new RuntimeException("Invalid implemetation of SASocket. Provide a public default constructor.");
        } catch (InvocationTargetException e6) {
            StringBuilder sb6 = new StringBuilder("Invalid implemetation of SASocket. Provide a public default constructor.");
            sb6.append(e6.getClass().getSimpleName());
            sb6.append(" ");
            sb6.append(e6.getMessage());
            Log.e("[SA_SDK]SAAgent", sb6.toString());
            throw new RuntimeException("Invalid implemetation of SASocket. Provide a public default constructor.");
        }
    }

    /* access modifiers changed from: private */
    public void e(int i2) {
        a(getApplicationContext(), i2);
        if (i2 == 0) {
            Log.i("[SA_SDK]SAAgent", "onServiceConnectionResponse() -> CONNECTION_SUCCESS");
        } else if (i2 == 1033) {
            Log.i("[SA_SDK]SAAgent", "onServiceConnectionResponse() -> CONNECTION_FAILURE_INVALID_PEERAGENT");
        } else if (i2 == 1037) {
            Log.i("[SA_SDK]SAAgent", "onServiceConnectionResponse() -> CONNECTION_FAILURE_SERVICE_LIMIT_REACHED");
        } else if (i2 == 1040) {
            Log.i("[SA_SDK]SAAgent", "onServiceConnectionResponse() -> CONNECTION_DUPLICATE_REQUEST");
        } else if (i2 != 1280) {
            switch (i2) {
                case 1028:
                    Log.i("[SA_SDK]SAAgent", "onServiceConnectionResponse() -> CONNECTION_FAILURE_DEVICE_UNREACHABLE");
                    return;
                case 1029:
                    Log.i("[SA_SDK]SAAgent", "onServiceConnectionResponse() -> CONNECTION_ALREADY_EXIST");
                    return;
                case 1030:
                    Log.i("[SA_SDK]SAAgent", "onServiceConnectionResponse() -> CONNECTION_FAILURE_PEERAGENT_NO_RESPONSE");
                    return;
                case 1031:
                    Log.i("[SA_SDK]SAAgent", "onServiceConnectionResponse() -> CONNECTION_FAILURE_PEERAGENT_REJECTED");
                    return;
                default:
                    StringBuilder sb = new StringBuilder("onServiceConnectionResponse() error_code: ");
                    sb.append(i2);
                    Log.w("[SA_SDK]SAAgent", sb.toString());
                    return;
            }
        } else {
            Log.i("[SA_SDK]SAAgent", "onServiceConnectionResponse() -> CONNECTION_FAILURE_NETWORK");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0034, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void f() {
        /*
            r2 = this;
            monitor-enter(r2)
            android.content.Context r0 = r2.getApplicationContext()     // Catch:{ all -> 0x0035 }
            com.samsung.android.sdk.accessory.b r0 = com.samsung.android.sdk.accessory.b.a(r0)     // Catch:{ all -> 0x0035 }
            r2.o = r0     // Catch:{ all -> 0x0035 }
            com.samsung.android.sdk.accessory.b r0 = r2.o     // Catch:{ all -> 0x0035 }
            if (r0 == 0) goto L_0x002c
            com.samsung.android.sdk.accessory.b r0 = r2.o     // Catch:{ all -> 0x0035 }
            java.lang.Class r1 = r2.getClass()     // Catch:{ all -> 0x0035 }
            java.lang.String r1 = r1.getName()     // Catch:{ all -> 0x0035 }
            com.samsung.android.sdk.accessory.k r0 = r0.a(r1)     // Catch:{ all -> 0x0035 }
            r2.p = r0     // Catch:{ all -> 0x0035 }
            com.samsung.android.sdk.accessory.k r0 = r2.p     // Catch:{ all -> 0x0035 }
            if (r0 != 0) goto L_0x0033
            java.lang.String r0 = "[SA_SDK]SAAgent"
            java.lang.String r1 = "fetch service profile description failed !!"
            android.util.Log.e(r0, r1)     // Catch:{ all -> 0x0035 }
            monitor-exit(r2)
            return
        L_0x002c:
            java.lang.String r0 = "[SA_SDK]SAAgent"
            java.lang.String r1 = "config  util defualt instance  creation failed !!"
            android.util.Log.e(r0, r1)     // Catch:{ all -> 0x0035 }
        L_0x0033:
            monitor-exit(r2)
            return
        L_0x0035:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.accessory.SAAgent.f():void");
    }

    private void f(int i2) {
        a(getApplicationContext(), i2);
        switch (i2) {
            case 1025:
                Log.i("[SA_SDK]SAAgent", "onError() -> ERROR_CONNECTION_INVALID_PARAM");
                return;
            case 2048:
                Log.i("[SA_SDK]SAAgent", "onError() -> ERROR_FATAL");
                return;
            case 2049:
                Log.i("[SA_SDK]SAAgent", "onError() -> ERROR_SDK_NOT_INITIALIZED");
                return;
            case 2304:
                Log.i("[SA_SDK]SAAgent", "onError() -> ERROR_PERMISSION_DENIED");
                return;
            case 2305:
                Log.i("[SA_SDK]SAAgent", "onError() -> ERROR_PERMISSION_FAILED");
                return;
            default:
                StringBuilder sb = new StringBuilder("onError() error_code: ");
                sb.append(i2);
                Log.w("[SA_SDK]SAAgent", sb.toString());
                return;
        }
    }

    /* access modifiers changed from: 0000 */
    public final String a(SAMessage sAMessage) {
        this.l = sAMessage;
        return this.k;
    }

    /* access modifiers changed from: 0000 */
    public final void a(int i2, SAPeerAgent sAPeerAgent) {
        switch (i2) {
            case 1033:
                onServiceConnectionResponse(sAPeerAgent, null, 1033);
                e(1033);
                return;
            case 2048:
                a(true);
                onError(null, "Samsung Accessory Framework has died!!", i2);
                f(i2);
                return;
            case 2049:
                Log.e("[SA_SDK]SAAgent", "Samsung Accessory SDK cannot be initialized");
                onError(null, "Samsung Accessory SDK cannot be initialized. Device or Build not compatible.", i2);
                f(i2);
                return;
            case 2304:
            case 2305:
                onError(null, "Permission error!", i2);
                f(i2);
                return;
            default:
                StringBuilder sb = new StringBuilder("Unknown error: ");
                sb.append(i2);
                Log.w("[SA_SDK]SAAgent", sb.toString());
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void acceptServiceConnectionRequest(SAPeerAgent sAPeerAgent) {
        if (sAPeerAgent != null) {
            try {
                this.c.initialize(getApplicationContext());
                if (this.i.remove(sAPeerAgent)) {
                    StringBuilder sb = new StringBuilder("Trying to Accept service connection request from peer:");
                    sb.append(sAPeerAgent.getPeerId());
                    sb.append(" Transaction:");
                    sb.append(sAPeerAgent.c());
                    Log.i("[SA_SDK]SAAgent", sb.toString());
                    b bVar = this.a;
                    if (bVar != null) {
                        Message obtainMessage = bVar.obtainMessage(7);
                        obtainMessage.obj = sAPeerAgent;
                        this.a.sendMessage(obtainMessage);
                    } else {
                        Log.w("[SA_SDK]SAAgent", "acceptServiceConnection: mBackgroundWorker is null!");
                    }
                } else {
                    StringBuilder sb2 = new StringBuilder("Accepting service connection with invalid peer agent:");
                    sb2.append(sAPeerAgent.toString());
                    Log.w("[SA_SDK]SAAgent", sb2.toString());
                    b(sAPeerAgent);
                }
                if (!this.n) {
                    try {
                        if (SsdkVendorCheck.isSamsungDevice()) {
                            a(getApplicationContext(), "acceptServiceConnectionRequest");
                        }
                    } catch (SecurityException unused) {
                        Log.e("[SA_SDK]SAAgent", "SecurityException : WRITE_USE_APP_FEATURE_SURVEY permission is required.");
                    }
                    this.n = true;
                }
            } catch (SsdkUnsupportedException e2) {
                StringBuilder sb3 = new StringBuilder("exception: ");
                sb3.append(e2.getMessage());
                Log.e("[SA_SDK]SAAgent", sb3.toString());
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
                this.c.initialize(getApplicationContext());
                StringBuilder sb = new StringBuilder("Authentication requested for peer:");
                sb.append(sAPeerAgent.getPeerId());
                Log.i("[SA_SDK]SAAgent", sb.toString());
                b bVar = this.a;
                if (bVar != null) {
                    Message obtainMessage = bVar.obtainMessage(9);
                    obtainMessage.obj = sAPeerAgent;
                    this.a.sendMessage(obtainMessage);
                } else {
                    Log.w("[SA_SDK]SAAgent", "authenticatePeerAgent: mBackgroundWorker is null!");
                }
                if (!this.n) {
                    try {
                        if (SsdkVendorCheck.isSamsungDevice()) {
                            a(getApplicationContext(), "authenticatePeerAgent");
                        }
                    } catch (SecurityException unused) {
                        Log.e("[SA_SDK]SAAgent", "SecurityException : WRITE_USE_APP_FEATURE_SURVEY permission is required.");
                    }
                    this.n = true;
                }
            } catch (SsdkUnsupportedException e2) {
                StringBuilder sb2 = new StringBuilder("exception: ");
                sb2.append(e2.getMessage());
                Log.e("[SA_SDK]SAAgent", sb2.toString());
                a(2049, sAPeerAgent);
            }
        } else {
            StringBuilder sb3 = new StringBuilder("Illegal argument peerAgent:");
            sb3.append(sAPeerAgent);
            throw new IllegalArgumentException(sb3.toString());
        }
    }

    /* access modifiers changed from: protected */
    public final synchronized void findPeerAgents() {
        String str = "[SA_SDK]SAAgent";
        StringBuilder sb = new StringBuilder("findPeer request received by:");
        sb.append(getClass().getName());
        Log.d(str, sb.toString());
        try {
            this.c.initialize(getApplicationContext());
            if (this.a != null) {
                Message obtainMessage = this.a.obtainMessage();
                obtainMessage.what = 2;
                this.a.sendMessage(obtainMessage);
                return;
            }
            Log.w("[SA_SDK]SAAgent", "findPeerAgents: mBackgroundWorker is null!");
        } catch (SsdkUnsupportedException e2) {
            String str2 = "[SA_SDK]SAAgent";
            StringBuilder sb2 = new StringBuilder("exception: ");
            sb2.append(e2.getMessage());
            Log.e(str2, sb2.toString());
            a(2049, (SAPeerAgent) null);
        }
    }

    public int getServiceChannelId(int i2) {
        String str;
        String str2;
        if (this.p == null) {
            str = "[SA_SDK]SAAgent";
            str2 = "Failed because Service Profile is null";
        } else if (i2 >= 0 && i2 < getServiceChannelSize()) {
            return ((j) this.p.i().get(i2)).a();
        } else {
            str = "[SA_SDK]SAAgent";
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
        Log.e("[SA_SDK]SAAgent", "Failed because Service Profile is null");
        return -1;
    }

    public String getServiceProfileId() {
        k kVar = this.p;
        if (kVar != null) {
            return kVar.b();
        }
        Log.e("[SA_SDK]SAAgent", "Failed because Service Profile is null");
        return null;
    }

    public String getServiceProfileName() {
        k kVar = this.p;
        if (kVar != null) {
            return kVar.c();
        }
        Log.e("[SA_SDK]SAAgent", "Failed because Service Profile is null");
        return null;
    }

    /* access modifiers changed from: protected */
    public void onAuthenticationResponse(SAPeerAgent sAPeerAgent, SAAuthenticationToken sAAuthenticationToken, int i2) {
        StringBuilder sb = new StringBuilder("Peer authentication response received:");
        sb.append(i2);
        Log.d("[SA_SDK]SAAgent", sb.toString());
    }

    public void onCreate() {
        super.onCreate();
        try {
            new i(getApplicationContext());
        } catch (c e2) {
            e2.printStackTrace();
        }
        StringBuilder sb = new StringBuilder("SAAgent - onCreate:");
        sb.append(getClass().getSimpleName());
        Log.d("[SA_SDK]SAAgent", sb.toString());
        this.h = Collections.synchronizedList(new ArrayList());
        this.i = Collections.synchronizedSet(new HashSet());
        HandlerThread handlerThread = new HandlerThread(this.j);
        handlerThread.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            public final void uncaughtException(Thread thread, final Throwable th) {
                StringBuilder sb = new StringBuilder("Exception in background thread:");
                sb.append(thread.getName());
                Log.e("[SA_SDK]SAAgent", sb.toString(), th);
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
                this.c.initialize(getApplicationContext());
            } catch (SsdkUnsupportedException e3) {
                Log.e("[SA_SDK]SAAgent", "SDK initialization failed!", e3);
                Message obtainMessage = this.a.obtainMessage(11);
                obtainMessage.arg1 = 2049;
                this.a.sendMessage(obtainMessage);
            }
            this.b = SAAdapter.a(getApplicationContext());
            this.e = new AuthenticationCallback(this, null);
            this.d = new PeerAgentCallback(this, null);
            this.g = new c(this, 0);
            this.f = new a(this);
            this.a.sendEmptyMessage(0);
            f();
            return;
        }
        Log.e("[SA_SDK]SAAgent", "Unable to start Agent thread.");
        throw new RuntimeException("Unable to start Agent.Worker thread creation failed");
    }

    public void onDestroy() {
        StringBuilder sb = new StringBuilder("SAAgent - onDestroy:");
        sb.append(getClass().getSimpleName());
        Log.d("[SA_SDK]SAAgent", sb.toString());
        b bVar = this.a;
        if (bVar != null) {
            bVar.obtainMessage(13).sendToTarget();
        }
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onError(SAPeerAgent sAPeerAgent, String str, int i2) {
        if (sAPeerAgent == null) {
            StringBuilder sb = new StringBuilder("ACCEPT_STATE_ERROR: ");
            sb.append(i2);
            sb.append(": ");
            sb.append(str);
            sb.append(" PeerAgent: null");
            Log.e("[SA_SDK]SAAgent", sb.toString());
        } else {
            StringBuilder sb2 = new StringBuilder("ACCEPT_STATE_ERROR: ");
            sb2.append(i2);
            sb2.append(": ");
            sb2.append(str);
            sb2.append(" PeerAgent: ");
            sb2.append(sAPeerAgent.getPeerId());
            Log.e("[SA_SDK]SAAgent", sb2.toString());
        }
        onError(str, i2);
    }

    /* access modifiers changed from: protected */
    public void onError(String str, int i2) {
        StringBuilder sb = new StringBuilder("ACCEPT_STATE_ERROR: ");
        sb.append(i2);
        sb.append(": ");
        sb.append(str);
        Log.e("[SA_SDK]SAAgent", sb.toString());
    }

    /* access modifiers changed from: protected */
    public void onFindPeerAgentResponse(SAPeerAgent sAPeerAgent, int i2) {
        Log.e("[SA_SDK]SAAgent", "Invalid implementation of SAAgent. Either one of onFindPeerAgentsResponse(SAPeerAgent[], int) or onFindPeerAgentResponse(SAPeerAgent, int) should be overrided!");
    }

    /* access modifiers changed from: protected */
    public void onFindPeerAgentsResponse(SAPeerAgent[] sAPeerAgentArr, int i2) {
        if (sAPeerAgentArr == null) {
            onFindPeerAgentResponse(null, i2);
            return;
        }
        for (SAPeerAgent onFindPeerAgentResponse : sAPeerAgentArr) {
            onFindPeerAgentResponse(onFindPeerAgentResponse, i2);
        }
    }

    /* access modifiers changed from: protected */
    public void onPeerAgentUpdated(SAPeerAgent sAPeerAgent, int i2) {
        Log.e("[SA_SDK]SAAgent", "Invalid implementation of SAAgent. Either one of onPeerAgentUpdated(SAPeerAgent[], int) or onPeerAgentUpdated(SAPeerAgent, int) should be overrided!");
    }

    /* access modifiers changed from: protected */
    public void onPeerAgentsUpdated(SAPeerAgent[] sAPeerAgentArr, int i2) {
        if (sAPeerAgentArr == null) {
            onPeerAgentUpdated(null, i2);
            return;
        }
        for (SAPeerAgent onPeerAgentUpdated : sAPeerAgentArr) {
            onPeerAgentUpdated(onPeerAgentUpdated, i2);
        }
    }

    /* access modifiers changed from: protected */
    public void onServiceConnectionRequested(SAPeerAgent sAPeerAgent) {
        if (sAPeerAgent != null) {
            StringBuilder sb = new StringBuilder("Accepting connection request by default from Peer:");
            sb.append(sAPeerAgent.getPeerId());
            sb.append(" Transaction:");
            sb.append(sAPeerAgent.c());
            Log.v("[SA_SDK]SAAgent", sb.toString());
        }
        acceptServiceConnectionRequest(sAPeerAgent);
    }

    /* access modifiers changed from: protected */
    public void onServiceConnectionResponse(SAPeerAgent sAPeerAgent, SASocket sASocket, int i2) {
        Log.w("[SA_SDK]SAAgent", "No Implementaion for onServiceConnectionResponse(SAPeerAgent peerAgent, SASocket socket, int result)! \n Calling deprecated API");
        onServiceConnectionResponse(sASocket, i2);
    }

    /* access modifiers changed from: protected */
    public void onServiceConnectionResponse(SASocket sASocket, int i2) {
        Log.e("[SA_SDK]SAAgent", "No implementaion for method onServiceConnectionResponse(SAPeerAgent, SASocket socket, int result): Implement method to get instance for SASocket");
    }

    public int onStartCommand(Intent intent, int i2, int i3) {
        if (intent != null) {
            String action = intent.getAction();
            if (action != null) {
                if ("com.samsung.accessory.action.SERVICE_CONNECTION_REQUESTED".equalsIgnoreCase(action)) {
                    Log.d("[SA_SDK]SAAgent", "Received incoming connection request");
                    Message obtainMessage = this.a.obtainMessage();
                    obtainMessage.what = 5;
                    obtainMessage.arg1 = i3;
                    obtainMessage.obj = intent;
                    this.a.sendMessage(obtainMessage);
                } else if (SAMessage.ACTION_ACCESSORY_MESSAGE_RECEIVED.equalsIgnoreCase(action)) {
                    Log.d("[SA_SDK]SAAgent", "Received incoming message ind");
                }
            }
        }
        return 2;
    }

    public void onTrimMemory(int i2) {
        a.a().a(i2);
        super.onTrimMemory(i2);
    }

    /* access modifiers changed from: protected */
    public void rejectServiceConnectionRequest(SAPeerAgent sAPeerAgent) {
        if (sAPeerAgent != null) {
            try {
                this.c.initialize(getApplicationContext());
                if (this.i.remove(sAPeerAgent)) {
                    StringBuilder sb = new StringBuilder("Trying to reject connection request from peer:");
                    sb.append(sAPeerAgent.getPeerId());
                    sb.append(" Transaction:");
                    sb.append(sAPeerAgent.c());
                    Log.i("[SA_SDK]SAAgent", sb.toString());
                    b bVar = this.a;
                    if (bVar != null) {
                        Message obtainMessage = bVar.obtainMessage(8);
                        obtainMessage.obj = sAPeerAgent;
                        this.a.sendMessage(obtainMessage);
                    } else {
                        Log.w("[SA_SDK]SAAgent", "rejectServiceConnection: mBackgroundWorker is null!");
                    }
                } else {
                    StringBuilder sb2 = new StringBuilder("Rejecting service connection with invalid peer agent:");
                    sb2.append(sAPeerAgent.toString());
                    Log.w("[SA_SDK]SAAgent", sb2.toString());
                    b(sAPeerAgent);
                }
                if (!this.n) {
                    try {
                        if (SsdkVendorCheck.isSamsungDevice()) {
                            a(getApplicationContext(), "rejectServiceConnectionRequest");
                        }
                    } catch (SecurityException unused) {
                        Log.e("[SA_SDK]SAAgent", "SecurityException : WRITE_USE_APP_FEATURE_SURVEY permission is required.");
                    }
                    this.n = true;
                }
            } catch (SsdkUnsupportedException e2) {
                StringBuilder sb3 = new StringBuilder("exception: ");
                sb3.append(e2.getMessage());
                Log.e("[SA_SDK]SAAgent", sb3.toString());
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
                this.c.initialize(getApplicationContext());
                StringBuilder sb = new StringBuilder("Service connection requested for peer:");
                sb.append(sAPeerAgent.getPeerId());
                Log.i("[SA_SDK]SAAgent", sb.toString());
                b bVar = this.a;
                if (bVar != null) {
                    Message obtainMessage = bVar.obtainMessage(6);
                    obtainMessage.obj = sAPeerAgent;
                    this.a.sendMessage(obtainMessage);
                } else {
                    Log.w("[SA_SDK]SAAgent", "requestServiceConection: mBackgroundWorker is null!");
                }
                if (!this.n) {
                    try {
                        if (SsdkVendorCheck.isSamsungDevice()) {
                            a(getApplicationContext(), "requestServiceConnection");
                        }
                    } catch (SecurityException unused) {
                        Log.e("[SA_SDK]SAAgent", "SecurityException : WRITE_USE_APP_FEATURE_SURVEY permission is required.");
                    }
                    this.n = true;
                }
            } catch (SsdkUnsupportedException e2) {
                StringBuilder sb2 = new StringBuilder("exception: ");
                sb2.append(e2.getMessage());
                Log.e("[SA_SDK]SAAgent", sb2.toString());
                a(2049, sAPeerAgent);
            }
        } else {
            StringBuilder sb3 = new StringBuilder("Illegal argument peerAgent:");
            sb3.append(sAPeerAgent);
            throw new IllegalArgumentException(sb3.toString());
        }
    }
}
