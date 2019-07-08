package com.samsung.android.sdk.internal.healthdata;

import android.os.Looper;
import android.os.RemoteException;
import com.samsung.android.sdk.healthdata.HealthResultHolder;
import com.samsung.android.sdk.healthdata.HealthResultHolder.BaseResult;
import com.samsung.android.sdk.internal.healthdata.HealthResultReceiver.ForwardAsync;

public final class IpcUtil {
    public static final String KEY_CODE = "key";
    public static final String KEY_COUNT = "count";
    public static final String KEY_PARCEL = "parcel";
    public static final String KEY_TYPE = "type";
    public static final int TYPE_AGGREGATE = 2;
    public static final int TYPE_NONE = 0;
    public static final int TYPE_PERMISSION = 3;
    public static final int TYPE_READ = 1;

    public static <T extends BaseResult> HealthResultHolder<T> getAsyncResultHolder(final ForwardAsync forwardAsync, Looper looper) {
        AnonymousClass1 r0 = new HealthResultHolderImpl<T>(looper) {
            /* access modifiers changed from: protected */
            public final void cancelResult() throws RemoteException {
                forwardAsync.cancel();
            }
        };
        forwardAsync.registerListener(r0);
        return r0;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(14:3|4|5|(1:7)|8|9|10|(1:12)(1:13)|14|15|16|17|18|19) */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:26|(2:31|32)|33|34|35) */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0084, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0080 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x008c */
    /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0099 */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0096 A[SYNTHETIC, Splitter:B:31:0x0096] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void sendBlob(com.samsung.android.sdk.healthdata.HealthData r9, java.lang.String r10) throws android.os.RemoteException {
        /*
            java.lang.Object r0 = r9.get(r10)
            byte[] r0 = (byte[]) r0
            if (r0 != 0) goto L_0x0009
            return
        L_0x0009:
            android.net.LocalSocket r1 = new android.net.LocalSocket
            r1.<init>()
            r2 = 0
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x008c }
            r3.<init>()     // Catch:{ IOException -> 0x008c }
            java.lang.String r4 = com.samsung.android.sdk.healthdata.HealthDataStore.getPlatformPackageName()     // Catch:{ IOException -> 0x008c }
            r3.append(r4)     // Catch:{ IOException -> 0x008c }
            java.lang.String r4 = ".BlobSocketServer"
            r3.append(r4)     // Catch:{ IOException -> 0x008c }
            java.lang.String r3 = r3.toString()     // Catch:{ IOException -> 0x008c }
            long r4 = com.samsung.android.sdk.healthdata.HealthDataStore.getMyUserId()     // Catch:{ IOException -> 0x008c }
            r6 = 0
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x0046
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x008c }
            r4.<init>()     // Catch:{ IOException -> 0x008c }
            r4.append(r3)     // Catch:{ IOException -> 0x008c }
            java.lang.String r3 = "."
            r4.append(r3)     // Catch:{ IOException -> 0x008c }
            long r5 = com.samsung.android.sdk.healthdata.HealthDataStore.getMyUserId()     // Catch:{ IOException -> 0x008c }
            r4.append(r5)     // Catch:{ IOException -> 0x008c }
            java.lang.String r3 = r4.toString()     // Catch:{ IOException -> 0x008c }
        L_0x0046:
            android.net.LocalSocketAddress r4 = new android.net.LocalSocketAddress     // Catch:{ IOException -> 0x008c }
            r4.<init>(r3)     // Catch:{ IOException -> 0x008c }
            r1.connect(r4)     // Catch:{ IOException -> 0x008c }
            java.io.DataOutputStream r3 = new java.io.DataOutputStream     // Catch:{ IOException -> 0x008c }
            java.io.OutputStream r4 = r1.getOutputStream()     // Catch:{ IOException -> 0x008c }
            r3.<init>(r4)     // Catch:{ IOException -> 0x008c }
            java.lang.String r2 = com.samsung.android.sdk.healthdata.HealthDataStore.getSocketKey()     // Catch:{ IOException -> 0x0088, all -> 0x0085 }
            r3.writeUTF(r2)     // Catch:{ IOException -> 0x0088, all -> 0x0085 }
            java.lang.String r2 = new java.lang.String     // Catch:{ IOException -> 0x0088, all -> 0x0085 }
            java.lang.String r4 = "UTF-8"
            r2.<init>(r0, r4)     // Catch:{ IOException -> 0x0088, all -> 0x0085 }
            r3.writeUTF(r2)     // Catch:{ IOException -> 0x0088, all -> 0x0085 }
            byte[] r9 = r9.getBlob(r10)     // Catch:{ IOException -> 0x0088, all -> 0x0085 }
            if (r9 == 0) goto L_0x0076
            int r10 = r9.length     // Catch:{ IOException -> 0x0088, all -> 0x0085 }
            r3.writeInt(r10)     // Catch:{ IOException -> 0x0088, all -> 0x0085 }
            r3.write(r9)     // Catch:{ IOException -> 0x0088, all -> 0x0085 }
            goto L_0x007a
        L_0x0076:
            r9 = 0
            r3.writeInt(r9)     // Catch:{ IOException -> 0x0088, all -> 0x0085 }
        L_0x007a:
            r3.flush()     // Catch:{ IOException -> 0x0088, all -> 0x0085 }
            r3.close()     // Catch:{ IOException -> 0x0080 }
        L_0x0080:
            r1.close()     // Catch:{ IOException -> 0x0084 }
            return
        L_0x0084:
            return
        L_0x0085:
            r9 = move-exception
            r2 = r3
            goto L_0x0094
        L_0x0088:
            r2 = r3
            goto L_0x008c
        L_0x008a:
            r9 = move-exception
            goto L_0x0094
        L_0x008c:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException     // Catch:{ all -> 0x008a }
            java.lang.String r10 = "Blob data sending failure"
            r9.<init>(r10)     // Catch:{ all -> 0x008a }
            throw r9     // Catch:{ all -> 0x008a }
        L_0x0094:
            if (r2 == 0) goto L_0x0099
            r2.close()     // Catch:{ IOException -> 0x0099 }
        L_0x0099:
            r1.close()     // Catch:{ IOException -> 0x009c }
        L_0x009c:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.internal.healthdata.IpcUtil.sendBlob(com.samsung.android.sdk.healthdata.HealthData, java.lang.String):void");
    }
}
