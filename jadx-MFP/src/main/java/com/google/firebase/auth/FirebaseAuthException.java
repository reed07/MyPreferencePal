package com.google.firebase.auth;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.FirebaseException;
import com.google.firebase.annotations.PublicApi;

@PublicApi
/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
public class FirebaseAuthException extends FirebaseException {
    private final String zza;

    @PublicApi
    public FirebaseAuthException(@NonNull String str, @NonNull String str2) {
        super(str2);
        this.zza = Preconditions.checkNotEmpty(str);
    }

    @PublicApi
    @NonNull
    public String getErrorCode() {
        return this.zza;
    }
}
