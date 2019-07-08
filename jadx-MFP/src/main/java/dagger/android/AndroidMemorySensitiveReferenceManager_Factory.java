package dagger.android;

import dagger.internal.Factory;
import dagger.internal.GwtIncompatible;
import dagger.releasablereferences.TypedReleasableReferenceManager;
import java.util.Set;
import javax.inject.Provider;

@GwtIncompatible
public final class AndroidMemorySensitiveReferenceManager_Factory implements Factory<AndroidMemorySensitiveReferenceManager> {
    private final Provider<Set<TypedReleasableReferenceManager<ReleaseReferencesAt>>> managersProvider;

    public AndroidMemorySensitiveReferenceManager_Factory(Provider<Set<TypedReleasableReferenceManager<ReleaseReferencesAt>>> provider) {
        this.managersProvider = provider;
    }

    public AndroidMemorySensitiveReferenceManager get() {
        return provideInstance(this.managersProvider);
    }

    public static AndroidMemorySensitiveReferenceManager provideInstance(Provider<Set<TypedReleasableReferenceManager<ReleaseReferencesAt>>> provider) {
        return new AndroidMemorySensitiveReferenceManager((Set) provider.get());
    }

    public static AndroidMemorySensitiveReferenceManager_Factory create(Provider<Set<TypedReleasableReferenceManager<ReleaseReferencesAt>>> provider) {
        return new AndroidMemorySensitiveReferenceManager_Factory(provider);
    }

    public static AndroidMemorySensitiveReferenceManager newAndroidMemorySensitiveReferenceManager(Set<TypedReleasableReferenceManager<ReleaseReferencesAt>> set) {
        return new AndroidMemorySensitiveReferenceManager(set);
    }
}
