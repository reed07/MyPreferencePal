package com.google.android.gms.internal.ads;

import android.os.RemoteException;

@zzark
public class zzanl {
    public static zzang zzcv(String str) throws RemoteException {
        try {
            return new zzanm((zzbit) Class.forName(str, false, zzanl.class.getClassLoader()).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
        } catch (Throwable unused) {
            throw new RemoteException();
        }
    }
}
