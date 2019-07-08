package com.google.android.gms.internal.fitness;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.request.zzax;
import com.google.android.gms.fitness.request.zzaz;
import com.google.android.gms.fitness.request.zzbb;
import com.google.android.gms.fitness.request.zzbd;

public interface zzcf extends IInterface {
    void zza(SessionInsertRequest sessionInsertRequest) throws RemoteException;

    void zza(SessionReadRequest sessionReadRequest) throws RemoteException;

    void zza(zzax zzax) throws RemoteException;

    void zza(zzaz zzaz) throws RemoteException;

    void zza(zzbb zzbb) throws RemoteException;

    void zza(zzbd zzbd) throws RemoteException;
}
