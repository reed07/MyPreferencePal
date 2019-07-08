package com.google.firebase.internal.api;

import android.support.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.firebase.FirebaseException;

@KeepForSdk
/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
public class FirebaseNoSignedInUserException extends FirebaseException {
    @KeepForSdk
    public FirebaseNoSignedInUserException(@NonNull String str) {
        super(str);
    }
}
