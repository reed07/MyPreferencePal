package com.google.firebase.iid;

final /* synthetic */ class zzag implements Runnable {
    private final zzad zzcc;

    zzag(zzad zzad) {
        this.zzcc = zzad;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0040, code lost:
        if (android.util.Log.isLoggable("MessengerIpcClient", 3) == false) goto L_0x0066;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0042, code lost:
        r4 = java.lang.String.valueOf(r1);
        r6 = new java.lang.StringBuilder(java.lang.String.valueOf(r4).length() + 8);
        r6.append("Sending ");
        r6.append(r4);
        android.util.Log.d("MessengerIpcClient", r6.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0066, code lost:
        r3 = r0.zzcb.zzx;
        r4 = r0.zzbx;
        r5 = android.os.Message.obtain();
        r5.what = r1.what;
        r5.arg1 = r1.zzcf;
        r5.replyTo = r4;
        r4 = new android.os.Bundle();
        r4.putBoolean("oneWay", r1.zzab());
        r4.putString("pkg", r3.getPackageName());
        r4.putBundle("data", r1.zzch);
        r5.setData(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r0.zzby.send(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00a4, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00a5, code lost:
        r0.zza(2, r1.getMessage());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r8 = this;
            com.google.firebase.iid.zzad r0 = r8.zzcc
        L_0x0002:
            monitor-enter(r0)
            int r1 = r0.state     // Catch:{ all -> 0x00ae }
            r2 = 2
            if (r1 == r2) goto L_0x000a
            monitor-exit(r0)     // Catch:{ all -> 0x00ae }
            return
        L_0x000a:
            java.util.Queue<com.google.firebase.iid.zzak<?>> r1 = r0.zzbz     // Catch:{ all -> 0x00ae }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x00ae }
            if (r1 == 0) goto L_0x0017
            r0.zzz()     // Catch:{ all -> 0x00ae }
            monitor-exit(r0)     // Catch:{ all -> 0x00ae }
            return
        L_0x0017:
            java.util.Queue<com.google.firebase.iid.zzak<?>> r1 = r0.zzbz     // Catch:{ all -> 0x00ae }
            java.lang.Object r1 = r1.poll()     // Catch:{ all -> 0x00ae }
            com.google.firebase.iid.zzak r1 = (com.google.firebase.iid.zzak) r1     // Catch:{ all -> 0x00ae }
            android.util.SparseArray<com.google.firebase.iid.zzak<?>> r3 = r0.zzca     // Catch:{ all -> 0x00ae }
            int r4 = r1.zzcf     // Catch:{ all -> 0x00ae }
            r3.put(r4, r1)     // Catch:{ all -> 0x00ae }
            com.google.firebase.iid.zzab r3 = r0.zzcb     // Catch:{ all -> 0x00ae }
            java.util.concurrent.ScheduledExecutorService r3 = r3.zzbu     // Catch:{ all -> 0x00ae }
            com.google.firebase.iid.zzah r4 = new com.google.firebase.iid.zzah     // Catch:{ all -> 0x00ae }
            r4.<init>(r0, r1)     // Catch:{ all -> 0x00ae }
            r5 = 30
            java.util.concurrent.TimeUnit r7 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ all -> 0x00ae }
            r3.schedule(r4, r5, r7)     // Catch:{ all -> 0x00ae }
            monitor-exit(r0)     // Catch:{ all -> 0x00ae }
            java.lang.String r3 = "MessengerIpcClient"
            r4 = 3
            boolean r3 = android.util.Log.isLoggable(r3, r4)
            if (r3 == 0) goto L_0x0066
            java.lang.String r3 = "MessengerIpcClient"
            java.lang.String r4 = java.lang.String.valueOf(r1)
            java.lang.String r5 = java.lang.String.valueOf(r4)
            int r5 = r5.length()
            int r5 = r5 + 8
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>(r5)
            java.lang.String r5 = "Sending "
            r6.append(r5)
            r6.append(r4)
            java.lang.String r4 = r6.toString()
            android.util.Log.d(r3, r4)
        L_0x0066:
            com.google.firebase.iid.zzab r3 = r0.zzcb
            android.content.Context r3 = r3.zzx
            android.os.Messenger r4 = r0.zzbx
            android.os.Message r5 = android.os.Message.obtain()
            int r6 = r1.what
            r5.what = r6
            int r6 = r1.zzcf
            r5.arg1 = r6
            r5.replyTo = r4
            android.os.Bundle r4 = new android.os.Bundle
            r4.<init>()
            java.lang.String r6 = "oneWay"
            boolean r7 = r1.zzab()
            r4.putBoolean(r6, r7)
            java.lang.String r6 = "pkg"
            java.lang.String r3 = r3.getPackageName()
            r4.putString(r6, r3)
            java.lang.String r3 = "data"
            android.os.Bundle r1 = r1.zzch
            r4.putBundle(r3, r1)
            r5.setData(r4)
            com.google.firebase.iid.zzai r1 = r0.zzby     // Catch:{ RemoteException -> 0x00a4 }
            r1.send(r5)     // Catch:{ RemoteException -> 0x00a4 }
            goto L_0x0002
        L_0x00a4:
            r1 = move-exception
            java.lang.String r1 = r1.getMessage()
            r0.zza(r2, r1)
            goto L_0x0002
        L_0x00ae:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00ae }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzag.run():void");
    }
}
