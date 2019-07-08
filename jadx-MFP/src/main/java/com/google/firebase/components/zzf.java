package com.google.firebase.components;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.components.Component.AnonymousClass1;
import com.google.firebase.events.Publisher;
import com.google.firebase.events.Subscriber;
import com.google.firebase.inject.Provider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
public final class zzf extends zza {
    private final List<Component<?>> zza;
    private final Map<Class<?>, zzj<?>> zzb = new HashMap();
    private final zzh zzc;

    public final /* bridge */ /* synthetic */ Object get(Class cls) {
        return super.get(cls);
    }

    public zzf(Executor executor, Iterable<ComponentRegistrar> iterable, Component<?>... componentArr) {
        this.zzc = new zzh(executor);
        ArrayList arrayList = new ArrayList();
        arrayList.add(Component.of(this.zzc, zzh.class, Subscriber.class, Publisher.class));
        for (ComponentRegistrar components : iterable) {
            arrayList.addAll(components.getComponents());
        }
        Collections.addAll(arrayList, componentArr);
        this.zza = Collections.unmodifiableList(AnonymousClass1.zza((List<Component<?>>) arrayList));
        for (Component zza2 : this.zza) {
            zza(zza2);
        }
        zza();
    }

    public final <T> Provider<T> getProvider(Class<T> cls) {
        Preconditions.checkNotNull(cls, "Null interface requested.");
        return (Provider) this.zzb.get(cls);
    }

    public final void zza(boolean z) {
        for (Component component : this.zza) {
            if (component.zze() || (component.zzf() && z)) {
                get((Class) component.zza().iterator().next());
            }
        }
        this.zzc.zza();
    }

    private <T> void zza(Component<T> component) {
        zzj zzj = new zzj(component.zzc(), new zzl(component, this));
        for (Class put : component.zza()) {
            this.zzb.put(put, zzj);
        }
    }

    private void zza() {
        for (Component component : this.zza) {
            Iterator it = component.zzb().iterator();
            while (true) {
                if (it.hasNext()) {
                    Dependency dependency = (Dependency) it.next();
                    if (dependency.zzb() && !this.zzb.containsKey(dependency.zza())) {
                        throw new MissingDependencyException(String.format("Unsatisfied dependency for component %s: %s", new Object[]{component, dependency.zza()}));
                    }
                }
            }
        }
    }
}
