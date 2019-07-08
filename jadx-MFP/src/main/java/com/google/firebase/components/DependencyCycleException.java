package com.google.firebase.components;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.Arrays;
import java.util.List;

@KeepForSdk
/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
public class DependencyCycleException extends DependencyException {
    private final List<Component<?>> zza;

    @KeepForSdk
    public DependencyCycleException(List<Component<?>> list) {
        StringBuilder sb = new StringBuilder("Dependency cycle detected: ");
        sb.append(Arrays.toString(list.toArray()));
        super(sb.toString());
        this.zza = list;
    }

    @KeepForSdk
    public List<Component<?>> getComponentsInCycle() {
        return this.zza;
    }
}
