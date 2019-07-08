package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;

public interface zzdt extends IInterface {
    int id() throws RemoteException;

    void onEvent(String str, String str2, Bundle bundle, long j) throws RemoteException;
}
