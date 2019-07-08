package com.samsung.android.sdk.accessory;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class SAService extends Service {
    private a a = new a();

    class a extends Binder {
        a() {
        }
    }

    public IBinder onBind(Intent intent) {
        return this.a;
    }

    public void onCreate() {
        super.onCreate();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x0077  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int onStartCommand(android.content.Intent r6, int r7, int r8) {
        /*
            r5 = this;
            if (r6 == 0) goto L_0x0087
            java.lang.String r7 = r6.getAction()
            if (r7 == 0) goto L_0x0087
            java.lang.String r8 = "com.samsung.accessory.action.SERVICE_CONNECTION_REQUESTED"
            boolean r7 = r8.equalsIgnoreCase(r7)
            if (r7 == 0) goto L_0x0087
            java.lang.String r7 = "[SA_SDK]SAService"
            java.lang.String r8 = "Received incoming connection indication"
            android.util.Log.d(r7, r8)
            java.lang.String r7 = "agentImplclass"
            java.lang.String r7 = r6.getStringExtra(r7)
            com.samsung.android.sdk.accessory.SAServiceAgent r8 = com.samsung.android.sdk.accessory.SAServiceAgent.getServiceAgent(r7)
            if (r8 != 0) goto L_0x0075
            java.lang.Class r0 = java.lang.Class.forName(r7)     // Catch:{ ClassNotFoundException -> 0x0071, NoSuchMethodException -> 0x006c, InstantiationException -> 0x0067, IllegalAccessException -> 0x0062, IllegalArgumentException -> 0x005d, InvocationTargetException -> 0x0058 }
            r1 = 1
            java.lang.Class[] r2 = new java.lang.Class[r1]     // Catch:{ ClassNotFoundException -> 0x0071, NoSuchMethodException -> 0x006c, InstantiationException -> 0x0067, IllegalAccessException -> 0x0062, IllegalArgumentException -> 0x005d, InvocationTargetException -> 0x0058 }
            java.lang.Class<android.content.Context> r3 = android.content.Context.class
            r4 = 0
            r2[r4] = r3     // Catch:{ ClassNotFoundException -> 0x0071, NoSuchMethodException -> 0x006c, InstantiationException -> 0x0067, IllegalAccessException -> 0x0062, IllegalArgumentException -> 0x005d, InvocationTargetException -> 0x0058 }
            java.lang.reflect.Constructor r0 = r0.getDeclaredConstructor(r2)     // Catch:{ ClassNotFoundException -> 0x0071, NoSuchMethodException -> 0x006c, InstantiationException -> 0x0067, IllegalAccessException -> 0x0062, IllegalArgumentException -> 0x005d, InvocationTargetException -> 0x0058 }
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ ClassNotFoundException -> 0x0071, NoSuchMethodException -> 0x006c, InstantiationException -> 0x0067, IllegalAccessException -> 0x0062, IllegalArgumentException -> 0x005d, InvocationTargetException -> 0x0058 }
            android.content.Context r2 = r5.getApplicationContext()     // Catch:{ ClassNotFoundException -> 0x0071, NoSuchMethodException -> 0x006c, InstantiationException -> 0x0067, IllegalAccessException -> 0x0062, IllegalArgumentException -> 0x005d, InvocationTargetException -> 0x0058 }
            r1[r4] = r2     // Catch:{ ClassNotFoundException -> 0x0071, NoSuchMethodException -> 0x006c, InstantiationException -> 0x0067, IllegalAccessException -> 0x0062, IllegalArgumentException -> 0x005d, InvocationTargetException -> 0x0058 }
            java.lang.Object r0 = r0.newInstance(r1)     // Catch:{ ClassNotFoundException -> 0x0071, NoSuchMethodException -> 0x006c, InstantiationException -> 0x0067, IllegalAccessException -> 0x0062, IllegalArgumentException -> 0x005d, InvocationTargetException -> 0x0058 }
            com.samsung.android.sdk.accessory.SAServiceAgent r0 = (com.samsung.android.sdk.accessory.SAServiceAgent) r0     // Catch:{ ClassNotFoundException -> 0x0071, NoSuchMethodException -> 0x006c, InstantiationException -> 0x0067, IllegalAccessException -> 0x0062, IllegalArgumentException -> 0x005d, InvocationTargetException -> 0x0058 }
            com.samsung.android.sdk.accessory.SAServiceAgent.putServiceAgent(r7, r0)     // Catch:{ ClassNotFoundException -> 0x0055, NoSuchMethodException -> 0x0052, InstantiationException -> 0x004f, IllegalAccessException -> 0x004c, IllegalArgumentException -> 0x0049, InvocationTargetException -> 0x0046 }
            r8 = r0
            goto L_0x0075
        L_0x0046:
            r7 = move-exception
            r8 = r0
            goto L_0x0059
        L_0x0049:
            r7 = move-exception
            r8 = r0
            goto L_0x005e
        L_0x004c:
            r7 = move-exception
            r8 = r0
            goto L_0x0063
        L_0x004f:
            r7 = move-exception
            r8 = r0
            goto L_0x0068
        L_0x0052:
            r7 = move-exception
            r8 = r0
            goto L_0x006d
        L_0x0055:
            r7 = move-exception
            r8 = r0
            goto L_0x0072
        L_0x0058:
            r7 = move-exception
        L_0x0059:
            r7.printStackTrace()
            goto L_0x0075
        L_0x005d:
            r7 = move-exception
        L_0x005e:
            r7.printStackTrace()
            goto L_0x0075
        L_0x0062:
            r7 = move-exception
        L_0x0063:
            r7.printStackTrace()
            goto L_0x0075
        L_0x0067:
            r7 = move-exception
        L_0x0068:
            r7.printStackTrace()
            goto L_0x0075
        L_0x006c:
            r7 = move-exception
        L_0x006d:
            r7.printStackTrace()
            goto L_0x0075
        L_0x0071:
            r7 = move-exception
        L_0x0072:
            r7.printStackTrace()
        L_0x0075:
            if (r8 == 0) goto L_0x0087
            com.samsung.android.sdk.accessory.SAServiceAgent$b r7 = r8.a
            android.os.Message r7 = r7.obtainMessage()
            r0 = 5
            r7.what = r0
            r7.obj = r6
            com.samsung.android.sdk.accessory.SAServiceAgent$b r6 = r8.a
            r6.sendMessage(r7)
        L_0x0087:
            r6 = 2
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.accessory.SAService.onStartCommand(android.content.Intent, int, int):int");
    }

    public boolean onUnbind(Intent intent) {
        stopSelf();
        return super.onUnbind(intent);
    }
}
