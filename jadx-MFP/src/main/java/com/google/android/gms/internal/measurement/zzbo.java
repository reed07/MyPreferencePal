package com.google.android.gms.internal.measurement;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public final class zzbo extends zzau {
    private volatile String zzup;
    private Future<String> zzyd;

    protected zzbo(zzaw zzaw) {
        super(zzaw);
    }

    /* access modifiers changed from: protected */
    public final void zzag() {
    }

    public final String zzdr() {
        String str;
        zzcl();
        synchronized (this) {
            if (this.zzup == null) {
                this.zzyd = zzca().zza((Callable<V>) new zzbp<V>(this));
            }
            if (this.zzyd != null) {
                try {
                    this.zzup = (String) this.zzyd.get();
                } catch (InterruptedException e) {
                    zzd("ClientId loading or generation was interrupted", e);
                    this.zzup = "0";
                } catch (ExecutionException e2) {
                    zze("Failed to load or generate client id", e2);
                    this.zzup = "0";
                }
                if (this.zzup == null) {
                    this.zzup = "0";
                }
                zza("Loaded clientId", this.zzup);
                this.zzyd = null;
            }
            str = this.zzup;
        }
        return str;
    }

    /* access modifiers changed from: 0000 */
    public final String zzds() {
        synchronized (this) {
            this.zzup = null;
            this.zzyd = zzca().zza((Callable<V>) new zzbq<V>(this));
        }
        return zzdr();
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final String zzdt() {
        String zzd = zzd(zzca().getContext());
        return zzd == null ? zzdu() : zzd;
    }

    /* access modifiers changed from: private */
    @VisibleForTesting
    public final String zzdu() {
        String lowerCase = UUID.randomUUID().toString().toLowerCase(Locale.US);
        try {
            return !zzb(zzca().getContext(), lowerCase) ? "0" : lowerCase;
        } catch (Exception e) {
            zze("Error saving clientId file", e);
            return "0";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x0080 A[SYNTHETIC, Splitter:B:42:0x0080] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x008e A[SYNTHETIC, Splitter:B:49:0x008e] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x009c A[SYNTHETIC, Splitter:B:58:0x009c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.String zzd(android.content.Context r7) {
        /*
            r6 = this;
            java.lang.String r0 = "ClientId should be loaded from worker thread"
            com.google.android.gms.common.internal.Preconditions.checkNotMainThread(r0)
            r0 = 0
            java.lang.String r1 = "gaClientId"
            java.io.FileInputStream r1 = r7.openFileInput(r1)     // Catch:{ FileNotFoundException -> 0x0099, IOException -> 0x0072, all -> 0x006f }
            r2 = 36
            byte[] r3 = new byte[r2]     // Catch:{ FileNotFoundException -> 0x009a, IOException -> 0x006d }
            r4 = 0
            int r2 = r1.read(r3, r4, r2)     // Catch:{ FileNotFoundException -> 0x009a, IOException -> 0x006d }
            int r5 = r1.available()     // Catch:{ FileNotFoundException -> 0x009a, IOException -> 0x006d }
            if (r5 <= 0) goto L_0x0035
            java.lang.String r2 = "clientId file seems corrupted, deleting it."
            r6.zzt(r2)     // Catch:{ FileNotFoundException -> 0x009a, IOException -> 0x006d }
            r1.close()     // Catch:{ FileNotFoundException -> 0x009a, IOException -> 0x006d }
            java.lang.String r2 = "gaClientId"
            r7.deleteFile(r2)     // Catch:{ FileNotFoundException -> 0x009a, IOException -> 0x006d }
            if (r1 == 0) goto L_0x0034
            r1.close()     // Catch:{ IOException -> 0x002e }
            goto L_0x0034
        L_0x002e:
            r7 = move-exception
            java.lang.String r1 = "Failed to close client id reading stream"
            r6.zze(r1, r7)
        L_0x0034:
            return r0
        L_0x0035:
            r5 = 14
            if (r2 >= r5) goto L_0x0053
            java.lang.String r2 = "clientId file is empty, deleting it."
            r6.zzt(r2)     // Catch:{ FileNotFoundException -> 0x009a, IOException -> 0x006d }
            r1.close()     // Catch:{ FileNotFoundException -> 0x009a, IOException -> 0x006d }
            java.lang.String r2 = "gaClientId"
            r7.deleteFile(r2)     // Catch:{ FileNotFoundException -> 0x009a, IOException -> 0x006d }
            if (r1 == 0) goto L_0x0052
            r1.close()     // Catch:{ IOException -> 0x004c }
            goto L_0x0052
        L_0x004c:
            r7 = move-exception
            java.lang.String r1 = "Failed to close client id reading stream"
            r6.zze(r1, r7)
        L_0x0052:
            return r0
        L_0x0053:
            r1.close()     // Catch:{ FileNotFoundException -> 0x009a, IOException -> 0x006d }
            java.lang.String r5 = new java.lang.String     // Catch:{ FileNotFoundException -> 0x009a, IOException -> 0x006d }
            r5.<init>(r3, r4, r2)     // Catch:{ FileNotFoundException -> 0x009a, IOException -> 0x006d }
            java.lang.String r2 = "Read client id from disk"
            r6.zza(r2, r5)     // Catch:{ FileNotFoundException -> 0x009a, IOException -> 0x006d }
            if (r1 == 0) goto L_0x006c
            r1.close()     // Catch:{ IOException -> 0x0066 }
            goto L_0x006c
        L_0x0066:
            r7 = move-exception
            java.lang.String r0 = "Failed to close client id reading stream"
            r6.zze(r0, r7)
        L_0x006c:
            return r5
        L_0x006d:
            r2 = move-exception
            goto L_0x0074
        L_0x006f:
            r7 = move-exception
            r1 = r0
            goto L_0x008c
        L_0x0072:
            r2 = move-exception
            r1 = r0
        L_0x0074:
            java.lang.String r3 = "Error reading client id file, deleting it"
            r6.zze(r3, r2)     // Catch:{ all -> 0x008b }
            java.lang.String r2 = "gaClientId"
            r7.deleteFile(r2)     // Catch:{ all -> 0x008b }
            if (r1 == 0) goto L_0x008a
            r1.close()     // Catch:{ IOException -> 0x0084 }
            goto L_0x008a
        L_0x0084:
            r7 = move-exception
            java.lang.String r1 = "Failed to close client id reading stream"
            r6.zze(r1, r7)
        L_0x008a:
            return r0
        L_0x008b:
            r7 = move-exception
        L_0x008c:
            if (r1 == 0) goto L_0x0098
            r1.close()     // Catch:{ IOException -> 0x0092 }
            goto L_0x0098
        L_0x0092:
            r0 = move-exception
            java.lang.String r1 = "Failed to close client id reading stream"
            r6.zze(r1, r0)
        L_0x0098:
            throw r7
        L_0x0099:
            r1 = r0
        L_0x009a:
            if (r1 == 0) goto L_0x00a6
            r1.close()     // Catch:{ IOException -> 0x00a0 }
            goto L_0x00a6
        L_0x00a0:
            r7 = move-exception
            java.lang.String r1 = "Failed to close client id reading stream"
            r6.zze(r1, r7)
        L_0x00a6:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbo.zzd(android.content.Context):java.lang.String");
    }

    private final boolean zzb(Context context, String str) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotMainThread("ClientId should be saved from worker thread");
        FileOutputStream fileOutputStream = null;
        try {
            zza("Storing clientId", str);
            fileOutputStream = context.openFileOutput("gaClientId", 0);
            fileOutputStream.write(str.getBytes());
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    zze("Failed to close clientId writing stream", e);
                }
            }
            return true;
        } catch (FileNotFoundException e2) {
            zze("Error creating clientId file", e2);
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e3) {
                    zze("Failed to close clientId writing stream", e3);
                }
            }
            return false;
        } catch (IOException e4) {
            zze("Error writing to clientId file", e4);
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e5) {
                    zze("Failed to close clientId writing stream", e5);
                }
            }
            return false;
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e6) {
                    zze("Failed to close clientId writing stream", e6);
                }
            }
        }
    }
}
