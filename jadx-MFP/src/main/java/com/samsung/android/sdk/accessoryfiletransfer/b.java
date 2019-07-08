package com.samsung.android.sdk.accessoryfiletransfer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.samsung.accessory.safiletransfer.a.h;
import com.samsung.accessory.safiletransfer.a.i;
import com.samsung.accessory.safiletransfer.a.j;
import com.samsung.accessory.safiletransfer.a.k;
import com.samsung.accessory.safiletransfer.core.ISAFTManager;
import com.samsung.accessory.safiletransfer.core.ISAFTManager.Stub;
import com.samsung.android.sdk.accessory.SAPeerAgent;
import com.samsung.android.sdk.accessory.c;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONException;

public final class b {
    /* access modifiers changed from: private */
    public static b a;
    private static List<String> d = new CopyOnWriteArrayList();
    /* access modifiers changed from: private */
    public static ConcurrentHashMap<String, a> e = new ConcurrentHashMap<>();
    /* access modifiers changed from: private */
    public static boolean g = false;
    /* access modifiers changed from: private */
    public a b;
    /* access modifiers changed from: private */
    public Context c;
    /* access modifiers changed from: private */
    public C0054b f;
    private ServiceConnection h = new ServiceConnection() {
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (iBinder != null) {
                Log.i("[SA_SDK]SAFileTransferManager", "inside onServiceConnected mFTServiceConn");
                ISAFTManager asInterface = Stub.asInterface(iBinder);
                b bVar = b.this;
                bVar.c;
                b.this.c.getPackageName();
                bVar.b = new a(asInterface);
                HandlerThread handlerThread = new HandlerThread("FileUpdateReceiverThread");
                handlerThread.start();
                if (handlerThread.getLooper() != null) {
                    b.this.f = new C0054b(handlerThread.getLooper());
                }
                synchronized (b.a) {
                    b.g = true;
                    b.a.notifyAll();
                    Log.i("[SA_SDK]SAFileTransferManager", "onServiceConnected: File Transfer service connected");
                }
                return;
            }
            Log.e("[SA_SDK]SAFileTransferManager", "onServiceConnected: File Transfer service not created");
        }

        public final void onServiceDisconnected(ComponentName componentName) {
            Log.d("[SA_SDK]SAFileTransferManager", "onServiceDisconnected: File Transfer service disconnected");
            if (b.a != null) {
                b.a.b = null;
            }
            b.g = false;
            if (b.this.f != null) {
                b.this.f.getLooper().quit();
                b.this.f = null;
            }
            for (Entry entry : b.e.entrySet()) {
                a aVar = (a) entry.getValue();
                if (aVar.e() != null && !aVar.e().isEmpty()) {
                    ArrayList<C0053a> arrayList = new ArrayList<>();
                    for (Entry value : aVar.e().entrySet()) {
                        arrayList.add((C0053a) value.getValue());
                    }
                    for (C0053a aVar2 : arrayList) {
                        ((a) entry.getValue()).b().a(aVar2.a, aVar2.c, 2048);
                    }
                    arrayList.clear();
                }
            }
        }
    };

    static class a {
        private ISAFTManager a;

        a(ISAFTManager iSAFTManager) {
            this.a = iSAFTManager;
        }

        /* access modifiers changed from: 0000 */
        public final ISAFTManager a() {
            return this.a;
        }
    }

    /* renamed from: com.samsung.android.sdk.accessoryfiletransfer.b$b reason: collision with other inner class name */
    static class C0054b extends Handler {
        public C0054b(Looper looper) {
            super(looper);
        }
    }

    static synchronized b a(Context context, String str) throws IllegalAccessException, c {
        b bVar;
        synchronized (b.class) {
            if (a == null || a.b == null) {
                b bVar2 = new b();
                a = bVar2;
                bVar2.c = context;
                synchronized (a) {
                    Intent intent = new Intent(SAft.FILE_TRANSFER_SERVICE_INTENT);
                    String fileTransferPackageName = new SAft().getFileTransferPackageName(a.c);
                    if (fileTransferPackageName != null) {
                        intent.setPackage(fileTransferPackageName);
                        if (a.c.bindService(intent, a.h, 1)) {
                            try {
                                Log.i("[SA_SDK]SAFileTransferManager", "SAFTAdapter: About start waiting");
                                for (int i = 0; i <= 0; i++) {
                                    a.wait(10000);
                                }
                            } catch (InterruptedException e2) {
                                e2.printStackTrace();
                            }
                            if (g) {
                                Log.i("[SA_SDK]SAFileTransferManager", "getInstance: Woken up , FTService Connected");
                            } else {
                                throw new c(2048, "Timed out trying to bind to FT Service!");
                            }
                        } else {
                            Log.e("[SA_SDK]SAFileTransferManager", "getInstance: FTService Connection Failed");
                        }
                    } else {
                        throw new c(2048, "Package name is null!");
                    }
                }
            }
            if (str != null) {
                String str2 = "[SA_SDK]SAFileTransferManager";
                StringBuilder sb = new StringBuilder(String.valueOf(str));
                sb.append(" is using FTService");
                Log.d(str2, sb.toString());
                bVar = a;
            } else {
                throw new IllegalAccessException("Calling agent was cleared from record. Please re-register your service.");
            }
        }
        return bVar;
    }

    static void a(String str, a aVar) {
        e.put(str, aVar);
    }

    static boolean a() {
        return g;
    }

    static a b(String str) {
        return (a) e.get(str);
    }

    static boolean c(String str) {
        if (d.contains(str)) {
            return false;
        }
        d.add(str);
        return true;
    }

    static void d(String str) {
        if (a != null) {
            d.remove(str);
            if (d.isEmpty()) {
                b bVar = a;
                bVar.c.unbindService(bVar.h);
                a.b = null;
                g = false;
                Log.d("[SA_SDK]SAFileTransferManager", "File transfer service disconnected");
                return;
            }
            Log.e("[SA_SDK]SAFileTransferManager", "Other applications are still using this FT binding");
            return;
        }
        Log.e("[SA_SDK]SAFileTransferManager", "FT already unbound for this package. Please check whether the calling agent was registered");
    }

    /* access modifiers changed from: 0000 */
    public final int a(Context context, String str, SAFileTransfer.c cVar, SAPeerAgent sAPeerAgent, String str2, String str3) {
        com.samsung.accessory.safiletransfer.a.c cVar2;
        int i;
        boolean z = false;
        Bundle bundle = null;
        String string = context.getSharedPreferences("AccessoryPreferences", 0).getString(str, null);
        if (string == null) {
            Log.w("[SA_SDK]SAFileTransferManager", "Agent id was not found in prefs! Fetching from framework..");
            string = "";
        }
        String str4 = string;
        if (str2 != null) {
            String str5 = str3;
            File file = new File(str5);
            k kVar = new k(str5, "", sAPeerAgent.getPeerId(), str4, sAPeerAgent.getAccessoryId(), file.length(), file.getName(), str2, context.getPackageName(), str);
            try {
                cVar2 = new com.samsung.accessory.safiletransfer.a.c(4, kVar.a());
            } catch (JSONException e2) {
                e2.printStackTrace();
                cVar2 = null;
            }
        } else {
            j jVar = new j(str3, "", sAPeerAgent.getPeerId(), str4, sAPeerAgent.getAccessoryId(), context.getPackageName(), str);
            cVar2 = new com.samsung.accessory.safiletransfer.a.c(1, jVar.a());
        }
        try {
            if (this.b != null) {
                bundle = this.b.a().sendCommand(cVar2.a().toString());
            }
        } catch (JSONException e3) {
            e3.printStackTrace();
        } catch (RemoteException e4) {
            e4.printStackTrace();
        }
        if (bundle != null) {
            z = bundle.getBoolean("STATUS");
            i = bundle.getInt("ID");
        } else {
            i = 0;
        }
        if (!z || !a(cVar, i)) {
            return -1;
        }
        Log.d("[SA_SDK]SAFileTransferManager", "File Pushed and Callback registered");
        return i;
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x002d A[Catch:{ RemoteException -> 0x003c }] */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0034 A[Catch:{ RemoteException -> 0x003c }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int a(java.lang.String r3) {
        /*
            r2 = this;
            com.samsung.accessory.safiletransfer.a.a r0 = new com.samsung.accessory.safiletransfer.a.a     // Catch:{ RemoteException -> 0x003c }
            r0.<init>(r3)     // Catch:{ RemoteException -> 0x003c }
            com.samsung.accessory.safiletransfer.a.c r3 = new com.samsung.accessory.safiletransfer.a.c     // Catch:{ JSONException -> 0x0026 }
            r1 = 6
            org.json.JSONObject r0 = r0.a()     // Catch:{ JSONException -> 0x0026 }
            r3.<init>(r1, r0)     // Catch:{ JSONException -> 0x0026 }
            com.samsung.android.sdk.accessoryfiletransfer.b$a r0 = r2.b     // Catch:{ JSONException -> 0x0026 }
            if (r0 == 0) goto L_0x002a
            com.samsung.android.sdk.accessoryfiletransfer.b$a r0 = r2.b     // Catch:{ JSONException -> 0x0026 }
            com.samsung.accessory.safiletransfer.core.ISAFTManager r0 = r0.a()     // Catch:{ JSONException -> 0x0026 }
            org.json.JSONObject r3 = r3.a()     // Catch:{ JSONException -> 0x0026 }
            java.lang.String r3 = r3.toString()     // Catch:{ JSONException -> 0x0026 }
            android.os.Bundle r3 = r0.sendCommand(r3)     // Catch:{ JSONException -> 0x0026 }
            goto L_0x002b
        L_0x0026:
            r3 = move-exception
            r3.printStackTrace()     // Catch:{ RemoteException -> 0x003c }
        L_0x002a:
            r3 = 0
        L_0x002b:
            if (r3 == 0) goto L_0x0034
            java.lang.String r0 = "receiveStatus"
            int r3 = r3.getInt(r0)     // Catch:{ RemoteException -> 0x003c }
            return r3
        L_0x0034:
            java.lang.String r3 = "[SA_SDK]SAFileTransferManager"
            java.lang.String r0 = "File Transfer Daemon could not queue request"
            android.util.Log.i(r3, r0)     // Catch:{ RemoteException -> 0x003c }
            goto L_0x0040
        L_0x003c:
            r3 = move-exception
            r3.printStackTrace()
        L_0x0040:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.accessoryfiletransfer.b.a(java.lang.String):int");
    }

    /* access modifiers changed from: 0000 */
    public final void a(int i) {
        try {
            try {
                com.samsung.accessory.safiletransfer.a.c cVar = new com.samsung.accessory.safiletransfer.a.c(3, new com.samsung.accessory.safiletransfer.a.b(i).a());
                if (this.b != null) {
                    this.b.a().sendCommand(cVar.a().toString());
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        } catch (RemoteException e3) {
            e3.printStackTrace();
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(SAFileTransfer.c cVar, int i, String str, String str2, boolean z) {
        com.samsung.accessory.safiletransfer.a.c cVar2;
        if (z) {
            try {
                if (!a(cVar, i)) {
                    Log.d("[SA_SDK]SAFileTransferManager", "Could not register file event callback. Declining transfer.");
                    cVar.a(i, str, 3);
                    return;
                }
            } catch (RemoteException e2) {
                e2.printStackTrace();
                return;
            }
        }
        if (str2 != null) {
            try {
                cVar2 = new com.samsung.accessory.safiletransfer.a.c(5, new i(i, str, str2, z).a());
            } catch (JSONException e3) {
                e3.printStackTrace();
                return;
            }
        } else {
            cVar2 = new com.samsung.accessory.safiletransfer.a.c(2, new h(i, str, z).a());
        }
        Bundle bundle = null;
        if (this.b != null) {
            bundle = this.b.a().sendCommand(cVar2.a().toString());
        }
        if (bundle != null) {
            int i2 = bundle.getInt("receiveStatus");
            StringBuilder sb = new StringBuilder("receiveStatus:");
            sb.append(i2);
            Log.i("[SA_SDK]SAFileTransferManager", sb.toString());
            return;
        }
        Log.i("[SA_SDK]SAFileTransferManager", "File Transfer Daemon could not queue request");
    }

    /* access modifiers changed from: 0000 */
    public final boolean a(SAFileTransfer.c cVar, int i) {
        if (cVar == null) {
            return false;
        }
        try {
            if (this.b != null) {
                return this.b.a().registerCallbackFacilitator(i, new SAFileTransferCallbackReceiver(this.f, cVar));
            }
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
        return false;
    }
}
