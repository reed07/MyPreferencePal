package com.google.firebase.components;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.firebase.inject.Provider;

@KeepForSdk
/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
public interface ComponentContainer {
    @KeepForSdk
    <T> T get(Class<T> cls);

    @KeepForSdk
    <T> Provider<T> getProvider(Class<T> cls);
}
