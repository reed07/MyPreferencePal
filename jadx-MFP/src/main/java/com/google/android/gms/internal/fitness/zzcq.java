package com.google.android.gms.internal.fitness;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

public interface zzcq extends IInterface {
    void onResult(Status status) throws RemoteException;
}
