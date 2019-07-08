package com.google.firebase.appindexing.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.appindexing.FirebaseAppIndexingException;
import com.google.firebase.appindexing.FirebaseAppIndexingInvalidArgumentException;
import com.google.firebase.appindexing.FirebaseAppIndexingTooManyArgumentsException;
import com.google.firebase.appindexing.zza;
import com.google.firebase.appindexing.zzb;
import com.google.firebase.appindexing.zzc;
import com.google.firebase.appindexing.zzd;
import com.google.firebase.appindexing.zze;
import com.google.firebase.appindexing.zzf;
import com.google.firebase.appindexing.zzg;

public final class zzae {
    public static FirebaseAppIndexingException zza(@NonNull Status status, String str) {
        Preconditions.checkNotNull(status);
        String statusMessage = status.getStatusMessage();
        if (statusMessage != null && !statusMessage.isEmpty()) {
            str = statusMessage;
        }
        int statusCode = status.getStatusCode();
        if (statusCode == 17602) {
            return new zzf(str);
        }
        switch (statusCode) {
            case 17510:
                return new FirebaseAppIndexingInvalidArgumentException(str);
            case 17511:
                return new FirebaseAppIndexingTooManyArgumentsException(str);
            default:
                switch (statusCode) {
                    case 17513:
                        return new zzb(str);
                    case 17514:
                        return new zza(str);
                    case 17515:
                        return new zzg(str);
                    case 17516:
                        return new zze(str);
                    case 17517:
                        return new zzd(str);
                    case 17518:
                        return new zzc(str);
                    default:
                        return new FirebaseAppIndexingException(str);
                }
        }
    }
}
