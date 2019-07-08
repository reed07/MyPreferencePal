package com.lightstep.tracer.shared;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

public abstract class CollectorClientProvider {
    private static final CollectorClientProvider provider = load();

    public static class ProviderNotFoundException extends RuntimeException {
        public ProviderNotFoundException(String str) {
            super(str);
        }
    }

    /* access modifiers changed from: 0000 */
    public abstract CollectorClient forOptions(AbstractTracer abstractTracer, Options options);

    /* access modifiers changed from: protected */
    public abstract int priority();

    public static CollectorClientProvider provider() throws ProviderNotFoundException {
        CollectorClientProvider collectorClientProvider = provider;
        if (collectorClientProvider != null) {
            return collectorClientProvider;
        }
        throw new ProviderNotFoundException("No functional collector client provider found. Try adding a dependency on the tracer-okhttp or tracer-grpc artifact");
    }

    private static CollectorClientProvider load() {
        CollectorClientProvider collectorClientProvider = null;
        for (CollectorClientProvider collectorClientProvider2 : loadCandidates()) {
            if (collectorClientProvider == null || collectorClientProvider2.priority() > collectorClientProvider.priority()) {
                collectorClientProvider = collectorClientProvider2;
            }
        }
        return collectorClientProvider;
    }

    private static ClassLoader getCorrectClassLoader() {
        if (isAndroid()) {
            return CollectorClientProvider.class.getClassLoader();
        }
        return Thread.currentThread().getContextClassLoader();
    }

    private static boolean isAndroid() {
        try {
            Class.forName("android.app.Application", false, null);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private static Iterable<CollectorClientProvider> loadCandidates() {
        if (isAndroid()) {
            return getCandidatesViaHardCoded(getCorrectClassLoader());
        }
        return getCandidatesViaServiceLoader();
    }

    private static Collection<CollectorClientProvider> getCandidatesViaHardCoded(ClassLoader classLoader) {
        ArrayList arrayList = new ArrayList(2);
        try {
            arrayList.add(create(Class.forName("com.lightstep.tracer.shared.GrpcCollectorClientProvider", true, classLoader)));
        } catch (ClassNotFoundException unused) {
        }
        try {
            arrayList.add(create(Class.forName("com.lightstep.tracer.shared.HttpCollectorClientProvider", true, classLoader)));
        } catch (ClassNotFoundException unused2) {
        }
        return arrayList;
    }

    private static Iterable<CollectorClientProvider> getCandidatesViaServiceLoader() {
        return ServiceLoader.load(CollectorClientProvider.class);
    }

    private static CollectorClientProvider create(Class<?> cls) {
        try {
            return (CollectorClientProvider) cls.asSubclass(CollectorClientProvider.class).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Throwable th) {
            StringBuilder sb = new StringBuilder();
            sb.append("Provider ");
            sb.append(cls.getName());
            sb.append(" could not be instantiated: ");
            sb.append(th);
            throw new ServiceConfigurationError(sb.toString(), th);
        }
    }
}
