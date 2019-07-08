package com.samsung.android.sdk.accessoryfiletransfer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Process;
import android.support.v4.content.FileProvider;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.samsung.android.sdk.SsdkUnsupportedException;
import com.samsung.android.sdk.accessory.SAAgent;
import com.samsung.android.sdk.accessory.SAPeerAgent;
import com.samsung.android.sdk.accessory.SAServiceAgent;
import java.io.File;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class SAFileTransfer {
    public static final String ACTION_SAP_FILE_TRANSFER_REQUESTED = "com.samsung.accessory.ftconnection";
    public static final int ERROR_CHANNEL_IO = 1;
    public static final int ERROR_COMMAND_DROPPED = 3;
    public static final int ERROR_CONNECTION_LOST = 5;
    public static final int ERROR_FATAL = 2048;
    public static final int ERROR_FILE_IO = 2;
    public static final int ERROR_NONE = 0;
    public static final int ERROR_NOT_SUPPORTED = 12;
    public static final int ERROR_PEER_AGENT_BUSY = 8;
    public static final int ERROR_PEER_AGENT_NO_RESPONSE = 4;
    public static final int ERROR_PEER_AGENT_REJECTED = 9;
    public static final int ERROR_REQUEST_NOT_QUEUED = -1;
    public static final int ERROR_SPACE_NOT_AVAILABLE = 11;
    public static final int ERROR_TRANSACTION_NOT_FOUND = 13;
    private static Random b = new Random(System.currentTimeMillis());
    /* access modifiers changed from: private */
    public static int c;
    c a = new c(this);
    private HandlerThread d;
    private b e;
    private Object f;
    /* access modifiers changed from: private */
    public Context g;
    /* access modifiers changed from: private */
    public String h;
    /* access modifiers changed from: private */
    public EventListener i;
    private long j = 0;
    private a k;
    /* access modifiers changed from: private */
    public boolean l = false;
    /* access modifiers changed from: private */
    public ConcurrentHashMap<Integer, C0053a> m;
    private SAft n;
    private BroadcastReceiver o = new BroadcastReceiver() {
        public final void onReceive(Context context, Intent intent) {
            SAFileTransfer.a(SAFileTransfer.this, context, intent);
        }
    };

    public interface EventListener {
        void onCancelAllCompleted(int i);

        void onProgressChanged(int i, int i2);

        void onTransferCompleted(int i, String str, int i2);

        void onTransferRequested(int i, String str);
    }

    static class a implements UncaughtExceptionHandler {
        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }

        public final void uncaughtException(final Thread thread, final Throwable th) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public final void run() {
                    StringBuilder sb = new StringBuilder("Exception in SAFileTransfer Handler thread :");
                    sb.append(thread.getName());
                    Log.e("[SA_SDK]SAFileTransfer", sb.toString());
                    throw new RuntimeException(th);
                }
            });
        }
    }

    static class b extends Handler {
        public b(Looper looper) {
            super(looper);
        }
    }

    public interface c {
        private /* synthetic */ SAFileTransfer a;

        c(SAFileTransfer sAFileTransfer) {
            this.a = sAFileTransfer;
        }

        final void a(int i, int i2) {
            for (Entry entry : this.a.m.entrySet()) {
                if (((C0053a) entry.getValue()).a == i && this.a.i != null) {
                    this.a.i.onProgressChanged(((Integer) entry.getKey()).intValue(), i2);
                    return;
                }
            }
        }

        final void a(int i, String str, int i2) {
            StringBuilder sb;
            String str2;
            for (Entry entry : this.a.m.entrySet()) {
                C0053a aVar = (C0053a) entry.getValue();
                if (aVar.a == i && this.a.i != null) {
                    if (aVar.b != null && i2 != 0) {
                        StringBuilder sb2 = new StringBuilder(String.valueOf(aVar.b));
                        sb2.append("_temp_");
                        sb2.append(i);
                        File file = new File(sb2.toString());
                        if (!file.isFile() || !file.exists()) {
                            str2 = "[SA_SDK]SAFileTransfer";
                            sb = new StringBuilder("temp file could not be deleted - ");
                        } else if (file.delete()) {
                            StringBuilder sb3 = new StringBuilder("temp file deleted successfully - ");
                            sb3.append(aVar.b);
                            Log.v("[SA_SDK]SAFileTransfer", sb3.toString());
                            aVar.b = null;
                        } else {
                            str2 = "[SA_SDK]SAFileTransfer";
                            sb = new StringBuilder("temp file could not be deleted - ");
                        }
                        sb.append(aVar.b);
                        Log.e(str2, sb.toString());
                        aVar.b = null;
                    } else if (aVar.b != null && i2 == 0) {
                        StringBuilder sb4 = new StringBuilder(String.valueOf(aVar.b));
                        sb4.append("_temp_");
                        sb4.append(i);
                        boolean a2 = SAFileTransfer.a(sb4.toString(), aVar.b);
                        aVar.b = null;
                        if (!a2) {
                            i2 = 2;
                        }
                    }
                    SAFileTransfer.a(i2);
                    this.a.i.onTransferCompleted(((Integer) entry.getKey()).intValue(), str, i2);
                    this.a.m.remove(entry.getKey());
                    if (i == SAFileTransfer.c) {
                        SAFileTransfer.c = 0;
                    }
                    return;
                }
            }
            if (!this.a.l || i2 != 9) {
                this.a.l = false;
                if (i == SAFileTransfer.c) {
                    SAFileTransfer.c = 0;
                    if (!this.a.m.containsKey(new Integer(i)) && this.a.i != null) {
                        SAFileTransfer.a(i2);
                        this.a.i.onTransferCompleted(i, str, i2);
                    }
                }
                return;
            }
            Log.d("[SA_SDK]SAFileTransfer", "Ignoring onTransferCompleted because setup in progress");
        }

        final void a(int[] iArr, int i) {
            if (iArr == null) {
                SAFileTransfer.b(13);
                this.a.i.onCancelAllCompleted(13);
                return;
            }
            int[] iArr2 = new int[iArr.length];
            int i2 = 0;
            for (int i3 : iArr) {
                for (Entry entry : this.a.m.entrySet()) {
                    if (((C0053a) entry.getValue()).a == i3 && this.a.i != null) {
                        iArr2[i2] = ((Integer) entry.getKey()).intValue();
                        i2++;
                        this.a.m.remove(entry.getKey());
                    }
                }
            }
            if (this.a.i != null) {
                SAFileTransfer.b(i);
                this.a.i.onCancelAllCompleted(i);
            }
        }
    }

    public SAFileTransfer(SAAgent sAAgent, EventListener eventListener) {
        if (sAAgent == null || eventListener == null) {
            throw new IllegalArgumentException("FileEventCallback parameter cannot be null");
        }
        this.f = sAAgent;
        this.g = sAAgent.getApplicationContext();
        this.h = sAAgent.getClass().getName();
        this.i = eventListener;
        if (this.n == null) {
            this.n = new SAft();
            try {
                this.n.initialize(this.g);
            } catch (SsdkUnsupportedException e2) {
                e2.printStackTrace();
            }
        }
        if (!c()) {
            Log.d("[SA_SDK]SAFileTransfer", "Agent already registered");
            this.k = b.b(this.h);
            a aVar = this.k;
            if (aVar != null) {
                this.d = aVar.c();
                this.e = (b) this.k.d();
                this.m = this.k.e();
                this.k.a(this.i);
                this.k.a(this.a);
            }
        }
    }

    public SAFileTransfer(SAServiceAgent sAServiceAgent, EventListener eventListener) {
        if (sAServiceAgent == null || eventListener == null) {
            throw new IllegalArgumentException("FileEventCallback parameter cannot be null");
        }
        this.f = sAServiceAgent;
        this.g = sAServiceAgent.getApplicationContext();
        this.h = sAServiceAgent.getClass().getName();
        this.i = eventListener;
        if (this.n == null) {
            this.n = new SAft();
            try {
                this.n.initialize(this.g);
            } catch (SsdkUnsupportedException e2) {
                e2.printStackTrace();
            }
        }
        if (!c()) {
            Log.d("[SA_SDK]SAFileTransfer", "Agent already registered");
            this.k = b.b(this.h);
            a aVar = this.k;
            if (aVar != null) {
                this.d = aVar.c();
                this.e = (b) this.k.d();
                this.m = this.k.e();
                this.k.a(this.i);
                this.k.a(this.a);
            }
        }
    }

    static /* synthetic */ void a(int i2) {
        if (i2 == 11) {
            Log.i("[SA_SDK]SAFileTransfer", "onTransferCompleted() -> ERROR_SPACE_NOT_AVAILABLE");
        } else if (i2 != 2048) {
            switch (i2) {
                case -1:
                    Log.i("[SA_SDK]SAFileTransfer", "onTransferCompleted() -> ERROR_REQUEST_NOT_QUEUED");
                    return;
                case 0:
                    Log.i("[SA_SDK]SAFileTransfer", "onTransferCompleted() -> ERROR_NONE");
                    return;
                case 1:
                    Log.i("[SA_SDK]SAFileTransfer", "onTransferCompleted() -> ERROR_CHANNEL_IO");
                    return;
                case 2:
                    Log.i("[SA_SDK]SAFileTransfer", "onTransferCompleted() -> ERROR_FILE_IO");
                    return;
                case 3:
                    Log.i("[SA_SDK]SAFileTransfer", "onTransferCompleted() -> ERROR_COMMAND_DROPPED");
                    return;
                case 4:
                    Log.i("[SA_SDK]SAFileTransfer", "onTransferCompleted() -> ERROR_PEER_AGENT_NO_RESPONSE");
                    return;
                case 5:
                    Log.i("[SA_SDK]SAFileTransfer", "onTransferCompleted() -> ERROR_CONNECTION_LOST");
                    return;
                default:
                    switch (i2) {
                        case 8:
                            Log.i("[SA_SDK]SAFileTransfer", "onTransferCompleted() -> ERROR_PEER_AGENT_BUSY");
                            return;
                        case 9:
                            Log.i("[SA_SDK]SAFileTransfer", "onTransferCompleted() -> ERROR_PEER_AGENT_REJECTED");
                            return;
                        default:
                            StringBuilder sb = new StringBuilder("onTransferCompleted() error_code: ");
                            sb.append(i2);
                            Log.w("[SA_SDK]SAFileTransfer", sb.toString());
                            return;
                    }
            }
        } else {
            Log.i("[SA_SDK]SAFileTransfer", "onTransferCompleted() -> ERROR_FATAL");
        }
    }

    static /* synthetic */ void a(SAFileTransfer sAFileTransfer, Context context, Intent intent) {
        while (true) {
            c = intent.getIntExtra("transId", -1);
            String stringExtra = intent.getStringExtra("agentClass");
            if (stringExtra == null) {
                stringExtra = context.getSharedPreferences("AccessoryPreferences", 0).getString(intent.getStringExtra("peerId"), null);
            }
            StringBuilder sb = new StringBuilder("class now:");
            sb.append(stringExtra);
            Log.d("[SA_SDK]SAFileTransfer", sb.toString());
            if (stringExtra == null) {
                Log.e("[SA_SDK]SAFileTransfer", "Target agent was cleared. Re-registering");
                Intent intent2 = new Intent();
                intent2.setAction("com.samsung.accessory.action.REGISTER_AGENT");
                intent2.setPackage(context.getPackageName());
                context.sendBroadcast(intent2);
                return;
            } else if (sAFileTransfer.f == null) {
                Log.e("[SA_SDK]SAFileTransfer", "Calling agent was cleared");
                return;
            } else if (!stringExtra.equalsIgnoreCase(sAFileTransfer.h)) {
                StringBuilder sb2 = new StringBuilder("Class name not matched with ");
                sb2.append(sAFileTransfer.h);
                Log.e("[SA_SDK]SAFileTransfer", sb2.toString());
                return;
            } else {
                final a b2 = b.b(stringExtra);
                if (b2 == null) {
                    Log.e("[SA_SDK]SAFileTransfer", "AgentInfo is NULL! Re-Registering");
                    sAFileTransfer.c();
                } else if (b2.a() == null) {
                    StringBuilder sb3 = new StringBuilder("callback is not registered for ");
                    sb3.append(stringExtra);
                    Log.e("[SA_SDK]SAFileTransfer", sb3.toString());
                    return;
                } else {
                    final String stringExtra2 = intent.getStringExtra("filePath");
                    StringBuilder sb4 = new StringBuilder("Informing app of incoming file transfer request on registered callback-tid: ");
                    sb4.append(c);
                    Log.d("[SA_SDK]SAFileTransfer", sb4.toString());
                    sAFileTransfer.e.post(new Runnable() {
                        public final void run() {
                            try {
                                b.a(SAFileTransfer.this.g, SAFileTransfer.this.h).a(SAFileTransfer.this.a, SAFileTransfer.c);
                                SAFileTransfer.this.l = true;
                                b2.a().onTransferRequested(SAFileTransfer.c, stringExtra2);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (com.samsung.android.sdk.accessory.c e2) {
                                e2.printStackTrace();
                            }
                        }
                    });
                    return;
                }
            }
        }
    }

    private boolean a(String str) {
        StringBuilder sb = new StringBuilder("checkPathPermission calling pkg: ");
        sb.append(this.g.getPackageName());
        sb.append(" file Path:");
        sb.append(str);
        Log.d("[SA_SDK]SAFileTransfer", sb.toString());
        if (str == null) {
            return false;
        }
        return !str.startsWith("/data/data") || str.contains(this.g.getPackageName());
    }

    private boolean a(String str, int i2) {
        String str2;
        String str3;
        String str4;
        String str5;
        boolean z = true;
        if (str != null) {
            if (str.length() != 0) {
                if (!a(str)) {
                    str4 = "[SA_SDK]SAFileTransfer";
                    str5 = "checkReceiveParams return false, internal path";
                } else {
                    File file = new File(str);
                    if (file.isDirectory()) {
                        str2 = "[SA_SDK]SAFileTransfer";
                        str3 = "given path is a directory";
                    } else {
                        String substring = str.substring(str.lastIndexOf("/") + 1, str.length());
                        if (!substring.contains(".")) {
                            str4 = "[SA_SDK]SAFileTransfer";
                            str5 = "No extension provided";
                        } else if (substring.charAt(substring.length() - 1) == '.') {
                            str4 = "[SA_SDK]SAFileTransfer";
                            str5 = "extension length is 0";
                        } else {
                            File parentFile = file.getParentFile();
                            if (parentFile != null) {
                                z = parentFile.exists();
                                if (!z) {
                                    Log.d("[SA_SDK]SAFileTransfer", "Directory does not exist!");
                                }
                            } else {
                                str2 = "[SA_SDK]SAFileTransfer";
                                str3 = "getParentFile() is null ";
                            }
                        }
                    }
                    Log.d(str2, str3);
                }
                Log.d(str4, str5);
                return false;
            }
            if (z && this.m.containsKey(Integer.valueOf(i2))) {
                Log.d("[SA_SDK]SAFileTransfer", "transactionId already exist");
                z = false;
            }
            return z;
        }
        z = false;
        Log.d("[SA_SDK]SAFileTransfer", "transactionId already exist");
        z = false;
        return z;
    }

    static /* synthetic */ boolean a(String str, String str2) {
        String str3;
        StringBuilder sb;
        File file = new File(str2);
        if (file.isFile() && file.exists()) {
            String substring = str2.substring(0, str2.lastIndexOf("/") + 1);
            String substring2 = str2.substring(str.lastIndexOf("/") + 1, str2.lastIndexOf("."));
            String substring3 = str2.substring(str2.lastIndexOf("."), str2.length());
            long currentTimeMillis = System.currentTimeMillis();
            StringBuilder sb2 = new StringBuilder(String.valueOf(substring));
            sb2.append(substring2);
            sb2.append(currentTimeMillis);
            sb2.append(substring3);
            str2 = sb2.toString();
            if (new File(str).renameTo(new File(str2))) {
                str3 = "[SA_SDK]SAFileTransfer";
                sb = new StringBuilder("File successfully renamed ");
                sb.append(str2);
                Log.v(str3, sb.toString());
                return true;
            }
        } else if (new File(str).renameTo(new File(str2))) {
            str3 = "[SA_SDK]SAFileTransfer";
            sb = new StringBuilder("File successfully renamed: ");
            sb.append(str2);
            Log.v(str3, sb.toString());
            return true;
        }
        Log.e("[SA_SDK]SAFileTransfer", "File rename failed");
        return false;
    }

    static /* synthetic */ void b(int i2) {
        switch (i2) {
            case 12:
                Log.i("[SA_SDK]SAFileTransfer", "onCancelAllCompleted() -> ERROR_NOT_SUPPORTED");
                return;
            case 13:
                Log.i("[SA_SDK]SAFileTransfer", "onCancelAllCompleted() -> ERROR_TRANSACTION_NOT_FOUND");
                return;
            default:
                StringBuilder sb = new StringBuilder("onCancelAllCompleted() error_code: ");
                sb.append(i2);
                Log.w("[SA_SDK]SAFileTransfer", sb.toString());
                return;
        }
    }

    private static boolean b(String str) {
        return str.startsWith("/data/data");
    }

    private boolean c() {
        if (b.c(this.h)) {
            this.d = new HandlerThread("FileTransferHandlerThread");
            this.d.setUncaughtExceptionHandler(new a(0));
            this.d.start();
            Log.d("[SA_SDK]SAFileTransfer", "FileTransferHandlerThread started");
            Looper looper = this.d.getLooper();
            if (looper != null) {
                this.e = new b(looper);
            }
            if (this.e != null) {
                this.m = new ConcurrentHashMap<>();
                a aVar = new a(this.i, this.d, this.e, this.a, this.m);
                this.k = aVar;
                LocalBroadcastManager.getInstance(this.g).registerReceiver(this.o, new IntentFilter("com.samsung.accessory.ftconnection.internal"));
                b.a(this.h, this.k);
                this.e.post(new Runnable() {
                    public final void run() {
                        try {
                            b.a(SAFileTransfer.this.g, SAFileTransfer.this.h);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (com.samsung.android.sdk.accessory.c e2) {
                            e2.printStackTrace();
                        }
                    }
                });
                return true;
            }
        }
        return false;
    }

    private String d() {
        List<ProviderInfo> list;
        try {
            list = this.g.getPackageManager().queryContentProviders(this.g.getPackageName(), Process.myUid(), 0);
        } catch (RuntimeException e2) {
            e2.printStackTrace();
            list = null;
        }
        if (list == null) {
            return null;
        }
        for (ProviderInfo providerInfo : list) {
            if (providerInfo.name.equalsIgnoreCase("android.support.v4.content.FileProvider")) {
                StringBuilder sb = new StringBuilder("Authority:");
                sb.append(providerInfo.authority);
                Log.d("[SA_SDK]SAFileTransfer", sb.toString());
                return providerInfo.authority;
            }
        }
        return null;
    }

    private int e() {
        long currentTimeMillis;
        do {
            currentTimeMillis = System.currentTimeMillis();
        } while (currentTimeMillis == this.j);
        this.j = currentTimeMillis;
        b.setSeed(currentTimeMillis);
        return b.nextInt();
    }

    public void cancel(final int i2) {
        if (this.f == null || this.i == null) {
            Log.d("[SA_SDK]SAFileTransfer", "Using invalid instance of SAFileTransfer(). Please re-register.");
        } else if (this.m.containsKey(Integer.valueOf(i2))) {
            this.e.post(new Runnable() {
                public final void run() {
                    try {
                        C0053a aVar = (C0053a) SAFileTransfer.this.m.get(Integer.valueOf(i2));
                        if (aVar == null) {
                            Log.d("[SA_SDK]SAFileTransfer", "cancelFile aborted because service connection or transaction already closed.");
                        } else if (aVar.a == 0) {
                            aVar.a = -1;
                            StringBuilder sb = new StringBuilder("Cancel called before transaction id is genereated");
                            sb.append(i2);
                            Log.d("[SA_SDK]SAFileTransfer", sb.toString());
                        } else if (aVar.a == -1) {
                            StringBuilder sb2 = new StringBuilder("Cancel called again before transaction id is genereated");
                            sb2.append(i2);
                            Log.d("[SA_SDK]SAFileTransfer", sb2.toString());
                        } else {
                            b.a(SAFileTransfer.this.g, SAFileTransfer.this.h).a(aVar.a);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (com.samsung.android.sdk.accessory.c e2) {
                        e2.printStackTrace();
                    }
                }
            });
        } else {
            throw new IllegalArgumentException("Wrong transaction id used for cancel");
        }
    }

    public void cancelAll() {
        if (this.f == null || this.i == null) {
            Log.d("[SA_SDK]SAFileTransfer", "Using invalid instance of SAFileTransfer. Please re-register.");
            return;
        }
        final String string = this.g.getSharedPreferences("AccessoryPreferences", 0).getString(this.h, null);
        if (string == null) {
            Log.e("[SA_SDK]SAFileTransfer", "Your service was not found. Please re-register");
        } else {
            this.e.post(new Runnable() {
                public final void run() {
                    try {
                        int a2 = b.a(SAFileTransfer.this.g, SAFileTransfer.this.h).a(string);
                        if (a2 == 0) {
                            SAFileTransfer.b(12);
                            SAFileTransfer.this.i.onCancelAllCompleted(12);
                            return;
                        }
                        if (a2 == 13) {
                            SAFileTransfer.b(13);
                            SAFileTransfer.this.i.onCancelAllCompleted(13);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (com.samsung.android.sdk.accessory.c e2) {
                        e2.printStackTrace();
                    }
                }
            });
        }
    }

    public void close() {
        ConcurrentHashMap<Integer, C0053a> concurrentHashMap = this.m;
        if (concurrentHashMap != null && concurrentHashMap.size() != 0) {
            throw new RuntimeException("Cannot close as File Transfer is in progress!");
        } else if (this.f == null || this.i == null) {
            Log.d("[SA_SDK]SAFileTransfer", "Using invalid instance of SAFileTransfer(). Please re-register.");
        } else {
            StringBuilder sb = new StringBuilder("stopFileTransferService() called by : ");
            sb.append(this.h);
            Log.d("[SA_SDK]SAFileTransfer", sb.toString());
            Context context = this.g;
            if (context == null || LocalBroadcastManager.getInstance(context) == null) {
                Log.d("[SA_SDK]SAFileTransfer", "Could not unregister receiver. Calling context is null");
            } else {
                LocalBroadcastManager.getInstance(context).unregisterReceiver(this.o);
            }
            b.d(this.h);
            ConcurrentHashMap<Integer, C0053a> concurrentHashMap2 = this.m;
            if (concurrentHashMap2 != null) {
                concurrentHashMap2.clear();
            }
            this.f = null;
            this.i = null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x0110  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0118  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void receive(final int r9, final java.lang.String r10) {
        /*
            r8 = this;
            java.lang.Object r0 = r8.f
            r1 = 0
            if (r0 == 0) goto L_0x0175
            com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer$EventListener r0 = r8.i
            if (r0 != 0) goto L_0x000b
            goto L_0x0175
        L_0x000b:
            boolean r0 = r8.a(r10, r9)
            if (r0 == 0) goto L_0x0149
            int r0 = c
            if (r9 != r0) goto L_0x0149
            com.samsung.android.sdk.accessoryfiletransfer.a$a r0 = new com.samsung.android.sdk.accessoryfiletransfer.a$a
            r0.<init>()
            r0.a = r9
            r0.c = r10
            java.util.concurrent.ConcurrentHashMap<java.lang.Integer, com.samsung.android.sdk.accessoryfiletransfer.a$a> r1 = r8.m
            java.lang.Integer r2 = java.lang.Integer.valueOf(r9)
            r1.put(r2, r0)
            com.samsung.android.sdk.accessoryfiletransfer.SAft r1 = r8.n
            if (r1 != 0) goto L_0x003e
            com.samsung.android.sdk.accessoryfiletransfer.SAft r1 = new com.samsung.android.sdk.accessoryfiletransfer.SAft
            r1.<init>()
            r8.n = r1
            com.samsung.android.sdk.accessoryfiletransfer.SAft r1 = r8.n     // Catch:{ SsdkUnsupportedException -> 0x003a }
            android.content.Context r2 = r8.g     // Catch:{ SsdkUnsupportedException -> 0x003a }
            r1.initialize(r2)     // Catch:{ SsdkUnsupportedException -> 0x003a }
            goto L_0x003e
        L_0x003a:
            r1 = move-exception
            r1.printStackTrace()
        L_0x003e:
            com.samsung.android.sdk.accessoryfiletransfer.SAft r1 = r8.n
            android.content.Context r2 = r8.g
            java.lang.String r1 = r1.getFileTransferPackageName(r2)
            java.lang.String r2 = r8.d()
            com.samsung.android.sdk.accessoryfiletransfer.SAft r3 = r8.n
            boolean r3 = r3.a()
            r4 = 0
            if (r3 == 0) goto L_0x0130
            if (r1 == 0) goto L_0x0130
            if (r2 == 0) goto L_0x0130
            if (r10 != 0) goto L_0x0061
            java.lang.String r0 = "[SA_SDK]SAFileTransfer"
            java.lang.String r1 = "File path is wrong!!"
            android.util.Log.e(r0, r1)     // Catch:{ IllegalArgumentException -> 0x00ed, NullPointerException -> 0x00e3, IOException -> 0x00d9 }
            return
        L_0x0061:
            java.lang.String r3 = "[SA_SDK]SAFileTransfer"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x00ed, NullPointerException -> 0x00e3, IOException -> 0x00d9 }
            java.lang.String r6 = "File :"
            r5.<init>(r6)     // Catch:{ IllegalArgumentException -> 0x00ed, NullPointerException -> 0x00e3, IOException -> 0x00d9 }
            r5.append(r10)     // Catch:{ IllegalArgumentException -> 0x00ed, NullPointerException -> 0x00e3, IOException -> 0x00d9 }
            java.lang.String r5 = r5.toString()     // Catch:{ IllegalArgumentException -> 0x00ed, NullPointerException -> 0x00e3, IOException -> 0x00d9 }
            android.util.Log.v(r3, r5)     // Catch:{ IllegalArgumentException -> 0x00ed, NullPointerException -> 0x00e3, IOException -> 0x00d9 }
            java.io.File r3 = new java.io.File     // Catch:{ IllegalArgumentException -> 0x00ed, NullPointerException -> 0x00e3, IOException -> 0x00d9 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x00ed, NullPointerException -> 0x00e3, IOException -> 0x00d9 }
            java.lang.String r6 = java.lang.String.valueOf(r10)     // Catch:{ IllegalArgumentException -> 0x00ed, NullPointerException -> 0x00e3, IOException -> 0x00d9 }
            r5.<init>(r6)     // Catch:{ IllegalArgumentException -> 0x00ed, NullPointerException -> 0x00e3, IOException -> 0x00d9 }
            java.lang.String r6 = "_temp_"
            r5.append(r6)     // Catch:{ IllegalArgumentException -> 0x00ed, NullPointerException -> 0x00e3, IOException -> 0x00d9 }
            r5.append(r9)     // Catch:{ IllegalArgumentException -> 0x00ed, NullPointerException -> 0x00e3, IOException -> 0x00d9 }
            java.lang.String r5 = r5.toString()     // Catch:{ IllegalArgumentException -> 0x00ed, NullPointerException -> 0x00e3, IOException -> 0x00d9 }
            r3.<init>(r5)     // Catch:{ IllegalArgumentException -> 0x00ed, NullPointerException -> 0x00e3, IOException -> 0x00d9 }
            java.lang.String r5 = "[SA_SDK]SAFileTransfer"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x00d7, NullPointerException -> 0x00d5, IOException -> 0x00d3 }
            java.lang.String r7 = "Temporary File Created for content URI : "
            r6.<init>(r7)     // Catch:{ IllegalArgumentException -> 0x00d7, NullPointerException -> 0x00d5, IOException -> 0x00d3 }
            boolean r7 = r3.createNewFile()     // Catch:{ IllegalArgumentException -> 0x00d7, NullPointerException -> 0x00d5, IOException -> 0x00d3 }
            r6.append(r7)     // Catch:{ IllegalArgumentException -> 0x00d7, NullPointerException -> 0x00d5, IOException -> 0x00d3 }
            java.lang.String r6 = r6.toString()     // Catch:{ IllegalArgumentException -> 0x00d7, NullPointerException -> 0x00d5, IOException -> 0x00d3 }
            android.util.Log.e(r5, r6)     // Catch:{ IllegalArgumentException -> 0x00d7, NullPointerException -> 0x00d5, IOException -> 0x00d3 }
            android.content.Context r5 = r8.g     // Catch:{ IllegalArgumentException -> 0x00d7, NullPointerException -> 0x00d5, IOException -> 0x00d3 }
            android.net.Uri r2 = android.support.v4.content.FileProvider.getUriForFile(r5, r2, r3)     // Catch:{ IllegalArgumentException -> 0x00d7, NullPointerException -> 0x00d5, IOException -> 0x00d3 }
            if (r2 != 0) goto L_0x00ca
            java.lang.String r0 = "[SA_SDK]SAFileTransfer"
            java.lang.String r1 = "Cannot create the content URI !"
            android.util.Log.e(r0, r1)     // Catch:{ IllegalArgumentException -> 0x00d7, NullPointerException -> 0x00d5, IOException -> 0x00d3 }
            boolean r0 = r3.delete()     // Catch:{ IllegalArgumentException -> 0x00d7, NullPointerException -> 0x00d5, IOException -> 0x00d3 }
            if (r0 == 0) goto L_0x00c2
            java.lang.String r0 = "[SA_SDK]SAFileTransfer"
            java.lang.String r1 = "temp file deleted successfully "
            android.util.Log.v(r0, r1)     // Catch:{ IllegalArgumentException -> 0x00d7, NullPointerException -> 0x00d5, IOException -> 0x00d3 }
            goto L_0x00fa
        L_0x00c2:
            java.lang.String r0 = "[SA_SDK]SAFileTransfer"
            java.lang.String r1 = "temp file could not be deleted "
            android.util.Log.e(r0, r1)     // Catch:{ IllegalArgumentException -> 0x00d7, NullPointerException -> 0x00d5, IOException -> 0x00d3 }
            goto L_0x00fa
        L_0x00ca:
            r0.b = r10     // Catch:{ IllegalArgumentException -> 0x00d7, NullPointerException -> 0x00d5, IOException -> 0x00d3 }
            android.content.Context r0 = r8.g     // Catch:{ IllegalArgumentException -> 0x00d7, NullPointerException -> 0x00d5, IOException -> 0x00d3 }
            r5 = 2
            r0.grantUriPermission(r1, r2, r5)     // Catch:{ IllegalArgumentException -> 0x00d7, NullPointerException -> 0x00d5, IOException -> 0x00d3 }
            goto L_0x00fa
        L_0x00d3:
            r0 = move-exception
            goto L_0x00db
        L_0x00d5:
            r0 = move-exception
            goto L_0x00e5
        L_0x00d7:
            r0 = move-exception
            goto L_0x00ef
        L_0x00d9:
            r0 = move-exception
            r3 = r4
        L_0x00db:
            r0.printStackTrace()
            java.lang.String r0 = "[SA_SDK]SAFileTransfer"
            java.lang.String r1 = "Cannot create the File !"
            goto L_0x00f6
        L_0x00e3:
            r0 = move-exception
            r3 = r4
        L_0x00e5:
            r0.printStackTrace()
            java.lang.String r0 = "[SA_SDK]SAFileTransfer"
            java.lang.String r1 = "Cannot create the content URI !!"
            goto L_0x00f6
        L_0x00ed:
            r0 = move-exception
            r3 = r4
        L_0x00ef:
            r0.printStackTrace()
            java.lang.String r0 = "[SA_SDK]SAFileTransfer"
            java.lang.String r1 = "Cannot create the content URI !"
        L_0x00f6:
            android.util.Log.e(r0, r1)
            r2 = r4
        L_0x00fa:
            if (r2 != 0) goto L_0x011f
            if (r3 == 0) goto L_0x011f
            boolean r0 = r3.isFile()
            if (r0 == 0) goto L_0x011f
            boolean r0 = r3.exists()
            if (r0 == 0) goto L_0x011f
            boolean r0 = r3.delete()
            if (r0 == 0) goto L_0x0118
            java.lang.String r0 = "[SA_SDK]SAFileTransfer"
            java.lang.String r1 = "temp file deleted successfully "
            android.util.Log.v(r0, r1)
            goto L_0x011f
        L_0x0118:
            java.lang.String r0 = "[SA_SDK]SAFileTransfer"
            java.lang.String r1 = "temp file could not be deleted "
            android.util.Log.e(r0, r1)
        L_0x011f:
            if (r2 != 0) goto L_0x0138
            boolean r0 = b(r10)
            if (r0 != 0) goto L_0x0128
            goto L_0x0138
        L_0x0128:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.String r10 = "Content URI needs to be implemented for receiving to internal folders. Please refer to sdk documentation for more details"
            r9.<init>(r10)
            throw r9
        L_0x0130:
            java.lang.String r0 = "[SA_SDK]SAFileTransfer"
            java.lang.String r1 = "Accessory Framework doesn't support content URI !!"
            android.util.Log.v(r0, r1)
            r2 = r4
        L_0x0138:
            if (r2 == 0) goto L_0x013e
            java.lang.String r4 = r2.toString()
        L_0x013e:
            com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer$b r0 = r8.e
            com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer$3 r1 = new com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer$3
            r1.<init>(r9, r10, r4)
            r0.post(r1)
            return
        L_0x0149:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r0 = "TransactionId: Given["
            r10.<init>(r0)
            r10.append(r9)
            java.lang.String r9 = "] Current ["
            r10.append(r9)
            int r9 = c
            r10.append(r9)
            java.lang.String r9 = "]"
            r10.append(r9)
            java.lang.String r9 = r10.toString()
            java.lang.String r10 = "[SA_SDK]SAFileTransfer"
            android.util.Log.d(r10, r9)
            r8.l = r1
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.String r10 = "Wrong filepath or transaction id used"
            r9.<init>(r10)
            throw r9
        L_0x0175:
            java.lang.String r9 = "[SA_SDK]SAFileTransfer"
            java.lang.String r10 = "Using invalid instance of SAFileTransfer(). Please re-register."
            android.util.Log.d(r9, r10)
            r8.l = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.receive(int, java.lang.String):void");
    }

    public void reject(final int i2) {
        if (this.f == null || this.i == null) {
            Log.d("[SA_SDK]SAFileTransfer", "Using invalid instance of SAFileTransfer(). Please re-register.");
        } else if (!a("", i2) || c != i2) {
            throw new IllegalArgumentException("Wrong transaction id used in reject()");
        } else {
            C0053a aVar = new C0053a();
            aVar.a = i2;
            aVar.c = "";
            this.m.put(Integer.valueOf(i2), aVar);
            this.e.post(new Runnable() {
                public final void run() {
                    try {
                        b.a(SAFileTransfer.this.g, SAFileTransfer.this.h).a(null, i2, "", null, false);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (com.samsung.android.sdk.accessory.c e2) {
                        e2.printStackTrace();
                    }
                }
            });
        }
    }

    /* JADX INFO: finally extract failed */
    public int send(SAPeerAgent sAPeerAgent, String str) {
        String str2;
        Uri uri;
        String str3;
        String str4;
        String str5 = str;
        int i2 = -1;
        if (this.f == null || this.i == null) {
            Log.d("[SA_SDK]SAFileTransfer", "Using invalid instance of SAFileTransfer(). Please re-register.");
            return -1;
        } else if (sAPeerAgent == null) {
            throw new IllegalArgumentException("PeerAgent cannot be null");
        } else if (str5 == null || str.length() == 0 || !a(str5)) {
            throw new IllegalArgumentException("Wrong file path");
        } else {
            try {
                String substring = str5.substring(str5.lastIndexOf("."), str.length());
                StringBuilder sb = new StringBuilder("File has a valid extentsion: ");
                sb.append(substring);
                Log.v("[SA_SDK]SAFileTransfer", sb.toString());
                Uri parse = Uri.parse(str);
                if ("file".equalsIgnoreCase(parse.getScheme())) {
                    str2 = parse.getPath();
                    if (str2 != null) {
                        StringBuilder sb2 = new StringBuilder("URI scheme is SCHEME_FILE  File Path : ");
                        sb2.append(str2);
                        Log.v("[SA_SDK]SAFileTransfer", sb2.toString());
                    }
                } else if (Param.CONTENT.equalsIgnoreCase(parse.getScheme())) {
                    Cursor query = this.g.getContentResolver().query(parse, new String[]{"_data"}, null, null, null);
                    if (query == null || !query.moveToFirst()) {
                        str2 = str5;
                    } else {
                        try {
                            str2 = query.getString(0);
                            if (str2 != null) {
                                StringBuilder sb3 = new StringBuilder("URI ContentResolver is SCHEME_CONTENT File Path : ");
                                sb3.append(str2);
                                Log.v("[SA_SDK]SAFileTransfer", sb3.toString());
                            }
                            query.close();
                            query = null;
                        } catch (Throwable th) {
                            query.close();
                            throw th;
                        }
                    }
                    if (query != null) {
                        query.close();
                    }
                } else {
                    str2 = str5;
                }
                File file = new File(str2);
                if (!file.exists()) {
                    throw new IllegalArgumentException("File doesnot exist");
                } else if (file.isDirectory()) {
                    throw new IllegalArgumentException("File is a directory");
                } else if (file.length() != 0) {
                    Log.v("[SA_SDK]SAFileTransfer", "File is valid !!");
                    int e2 = e();
                    if (this.n == null) {
                        this.n = new SAft();
                        try {
                            this.n.initialize(this.g);
                        } catch (SsdkUnsupportedException e3) {
                            e3.printStackTrace();
                        }
                    }
                    String fileTransferPackageName = this.n.getFileTransferPackageName(this.g);
                    String d2 = d();
                    if (!this.n.a() || fileTransferPackageName == null || d2 == null) {
                        Log.v("[SA_SDK]SAFileTransfer", "FTCore version doesnot support content uri");
                        uri = null;
                    } else if (str5 == null) {
                        try {
                            Log.e("[SA_SDK]SAFileTransfer", "File path is wrong!!");
                            return -1;
                        } catch (IllegalArgumentException e4) {
                            e4.printStackTrace();
                            str4 = "[SA_SDK]SAFileTransfer";
                            str3 = "Cannot create the content URI !";
                            Log.e(str4, str3);
                            uri = null;
                            throw new IllegalArgumentException("content uri needs to be implemented for sending from internal folders.Please check file-transfer sdk documentation for more details");
                        } catch (NullPointerException e5) {
                            e5.printStackTrace();
                            str4 = "[SA_SDK]SAFileTransfer";
                            str3 = "Cannot create the content URI !! ";
                            Log.e(str4, str3);
                            uri = null;
                            throw new IllegalArgumentException("content uri needs to be implemented for sending from internal folders.Please check file-transfer sdk documentation for more details");
                        }
                    } else {
                        StringBuilder sb4 = new StringBuilder("File :");
                        sb4.append(str5);
                        Log.v("[SA_SDK]SAFileTransfer", sb4.toString());
                        uri = FileProvider.getUriForFile(this.g, d2, new File(str5));
                        if (uri == null) {
                            Log.e("[SA_SDK]SAFileTransfer", "Cannot create the content URI !");
                        } else {
                            this.g.grantUriPermission(fileTransferPackageName, uri, 1);
                        }
                        if (uri == null && b(str)) {
                            throw new IllegalArgumentException("content uri needs to be implemented for sending from internal folders.Please check file-transfer sdk documentation for more details");
                        }
                    }
                    C0053a aVar = new C0053a();
                    String uri2 = uri != null ? uri.toString() : null;
                    if (b.a()) {
                        try {
                            i2 = b.a(this.g, this.h).a(this.g, this.h, this.a, sAPeerAgent, uri2, str);
                        } catch (IllegalAccessException e6) {
                            e6.printStackTrace();
                        } catch (com.samsung.android.sdk.accessory.c e7) {
                            e7.printStackTrace();
                        }
                        StringBuilder sb5 = new StringBuilder("received tx from FTCore");
                        sb5.append(e2);
                        sb5.append(" ");
                        sb5.append(i2);
                        Log.d("[SA_SDK]SAFileTransfer", sb5.toString());
                        aVar.a = i2;
                        aVar.c = str5;
                        this.m.put(Integer.valueOf(e2), aVar);
                    } else {
                        aVar.a = 0;
                        aVar.c = str5;
                        this.m.put(Integer.valueOf(e2), aVar);
                        StringBuilder sb6 = new StringBuilder("returning temporary transaction id");
                        sb6.append(e2);
                        Log.d("[SA_SDK]SAFileTransfer", sb6.toString());
                        b bVar = this.e;
                        final SAPeerAgent sAPeerAgent2 = sAPeerAgent;
                        final String str6 = uri2;
                        final String str7 = str;
                        final int i3 = e2;
                        AnonymousClass2 r1 = new Runnable() {
                            public final void run() {
                                try {
                                    int a2 = b.a(SAFileTransfer.this.g, SAFileTransfer.this.h).a(SAFileTransfer.this.g, SAFileTransfer.this.h, SAFileTransfer.this.a, sAPeerAgent2, str6, str7);
                                    StringBuilder sb = new StringBuilder("received tx from FTCore");
                                    sb.append(i3);
                                    sb.append(" ");
                                    sb.append(a2);
                                    Log.d("[SA_SDK]SAFileTransfer", sb.toString());
                                    C0053a aVar = (C0053a) SAFileTransfer.this.m.get(Integer.valueOf(i3));
                                    if (aVar != null && aVar.a == -1) {
                                        StringBuilder sb2 = new StringBuilder("Cancel aready requested for ");
                                        sb2.append(i3);
                                        sb2.append(" ");
                                        sb2.append(a2);
                                        Log.d("[SA_SDK]SAFileTransfer", sb2.toString());
                                        aVar.a = a2;
                                        SAFileTransfer.this.cancel(i3);
                                    }
                                    C0053a aVar2 = new C0053a();
                                    aVar2.a = a2;
                                    aVar2.c = str7;
                                    SAFileTransfer.this.m.put(Integer.valueOf(i3), aVar2);
                                } catch (IllegalAccessException e2) {
                                    e2.printStackTrace();
                                } catch (com.samsung.android.sdk.accessory.c e3) {
                                    e3.printStackTrace();
                                    SAFileTransfer.a(2048);
                                    SAFileTransfer.this.i.onTransferCompleted(-1, str7, 2048);
                                }
                            }
                        };
                        bVar.post(r1);
                    }
                    return e2;
                } else {
                    throw new IllegalArgumentException("File length is 0");
                }
            } catch (StringIndexOutOfBoundsException e8) {
                e8.printStackTrace();
                throw new IllegalArgumentException("Wrong file..does not have extension");
            }
        }
    }
}
