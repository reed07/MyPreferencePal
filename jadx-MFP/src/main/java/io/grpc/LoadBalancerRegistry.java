package io.grpc;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import io.grpc.ServiceProviders.PriorityAccessor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

@ExperimentalApi
public final class LoadBalancerRegistry {
    private static final Iterable<Class<?>> HARDCODED_CLASSES = getHardCodedClasses();
    private static LoadBalancerRegistry instance;
    private static final Logger logger = Logger.getLogger(LoadBalancerRegistry.class.getName());
    private final Map<String, LoadBalancerProvider> providers;

    private static final class LoadBalancerPriorityAccessor implements PriorityAccessor<LoadBalancerProvider> {
        LoadBalancerPriorityAccessor() {
        }

        public boolean isAvailable(LoadBalancerProvider loadBalancerProvider) {
            return loadBalancerProvider.isAvailable();
        }

        public int getPriority(LoadBalancerProvider loadBalancerProvider) {
            return loadBalancerProvider.getPriority();
        }
    }

    @VisibleForTesting
    LoadBalancerRegistry(List<LoadBalancerProvider> list) {
        LoadBalancerProvider loadBalancerProvider;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (LoadBalancerProvider loadBalancerProvider2 : list) {
            if (!loadBalancerProvider2.isAvailable()) {
                Logger logger2 = logger;
                StringBuilder sb = new StringBuilder();
                sb.append(loadBalancerProvider2);
                sb.append(" found but not available");
                logger2.fine(sb.toString());
            } else {
                String policyName = loadBalancerProvider2.getPolicyName();
                LoadBalancerProvider loadBalancerProvider3 = (LoadBalancerProvider) linkedHashMap.get(policyName);
                if (loadBalancerProvider3 == null) {
                    Logger logger3 = logger;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Found ");
                    sb2.append(loadBalancerProvider2);
                    logger3.fine(sb2.toString());
                    linkedHashMap.put(policyName, loadBalancerProvider2);
                } else if (loadBalancerProvider3.getPriority() < loadBalancerProvider2.getPriority()) {
                    Logger logger4 = logger;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(loadBalancerProvider2);
                    sb3.append(" overrides ");
                    sb3.append(loadBalancerProvider3);
                    sb3.append(" because of higher priority");
                    logger4.fine(sb3.toString());
                    linkedHashMap.put(policyName, loadBalancerProvider2);
                } else if (loadBalancerProvider3.getPriority() > loadBalancerProvider2.getPriority()) {
                    Logger logger5 = logger;
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append(loadBalancerProvider2);
                    sb4.append(" doesn't override ");
                    sb4.append(loadBalancerProvider3);
                    sb4.append(" because of lower priority");
                    logger5.fine(sb4.toString());
                } else {
                    if (loadBalancerProvider3.getClass().getName().compareTo(loadBalancerProvider2.getClass().getName()) < 0) {
                        linkedHashMap.put(policyName, loadBalancerProvider2);
                        loadBalancerProvider = loadBalancerProvider2;
                    } else {
                        loadBalancerProvider = loadBalancerProvider3;
                    }
                    Logger logger6 = logger;
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append(loadBalancerProvider2);
                    sb5.append(" and ");
                    sb5.append(loadBalancerProvider3);
                    sb5.append(" has the same priority. ");
                    sb5.append(loadBalancerProvider);
                    sb5.append(" is selected for this time. You should make them differ in either policy name or priority, or remove one of them from your classpath");
                    logger6.warning(sb5.toString());
                }
            }
        }
        this.providers = Collections.unmodifiableMap(linkedHashMap);
    }

    public static synchronized LoadBalancerRegistry getDefaultRegistry() {
        LoadBalancerRegistry loadBalancerRegistry;
        synchronized (LoadBalancerRegistry.class) {
            if (instance == null) {
                instance = new LoadBalancerRegistry(ServiceProviders.loadAll(LoadBalancerProvider.class, HARDCODED_CLASSES, LoadBalancerProvider.class.getClassLoader(), new LoadBalancerPriorityAccessor()));
            }
            loadBalancerRegistry = instance;
        }
        return loadBalancerRegistry;
    }

    @Nullable
    public LoadBalancerProvider getProvider(String str) {
        return (LoadBalancerProvider) this.providers.get(Preconditions.checkNotNull(str, "policy"));
    }

    @VisibleForTesting
    static List<Class<?>> getHardCodedClasses() {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.add(Class.forName("io.grpc.internal.PickFirstLoadBalancerProvider"));
        } catch (ClassNotFoundException e) {
            logger.log(Level.WARNING, "Unable to find pick-first LoadBalancer", e);
        }
        try {
            arrayList.add(Class.forName("io.grpc.util.SecretRoundRobinLoadBalancerProvider$Provider"));
        } catch (ClassNotFoundException e2) {
            logger.log(Level.FINE, "Unable to find round-robin LoadBalancer", e2);
        }
        return Collections.unmodifiableList(arrayList);
    }
}
