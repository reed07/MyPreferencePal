package io.grpc.internal;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Throwables;
import com.google.common.base.Verify;
import com.myfitnesspal.shared.model.v2.MfpGoalPreferences.MacroGoalFormat;
import io.grpc.Attributes;
import io.grpc.Attributes.Builder;
import io.grpc.EquivalentAddressGroup;
import io.grpc.NameResolver;
import io.grpc.NameResolver.Factory;
import io.grpc.NameResolver.Listener;
import io.grpc.Status;
import io.grpc.internal.SharedResourceHolder.Resource;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

final class DnsNameResolver extends NameResolver {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String JNDI_LOCALHOST_PROPERTY = System.getProperty("io.grpc.internal.DnsNameResolverProvider.enable_jndi_localhost", "false");
    private static final String JNDI_PROPERTY = System.getProperty("io.grpc.internal.DnsNameResolverProvider.enable_jndi", "true");
    private static final String JNDI_SRV_PROPERTY = System.getProperty("io.grpc.internal.DnsNameResolverProvider.enable_grpclb", "false");
    private static final String JNDI_TXT_PROPERTY = System.getProperty("io.grpc.internal.DnsNameResolverProvider.enable_service_config", "false");
    private static final Set<String> SERVICE_CONFIG_CHOICE_KEYS = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{"clientLanguage", MacroGoalFormat.PERCENTAGE, "clientHostname", "serviceConfig"})));
    @VisibleForTesting
    static boolean enableJndi = Boolean.parseBoolean(JNDI_PROPERTY);
    @VisibleForTesting
    static boolean enableJndiLocalhost = Boolean.parseBoolean(JNDI_LOCALHOST_PROPERTY);
    @VisibleForTesting
    static boolean enableSrv = Boolean.parseBoolean(JNDI_SRV_PROPERTY);
    @VisibleForTesting
    static boolean enableTxt = Boolean.parseBoolean(JNDI_TXT_PROPERTY);
    private static String localHostname;
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(DnsNameResolver.class.getName());
    private static final ResourceResolverFactory resourceResolverFactory = getResourceResolverFactory(DnsNameResolver.class.getClassLoader());
    /* access modifiers changed from: private */
    public volatile AddressResolver addressResolver = JdkAddressResolver.INSTANCE;
    private final String authority;
    @GuardedBy("this")
    private Executor executor;
    private final Resource<Executor> executorResource;
    /* access modifiers changed from: private */
    public final String host;
    /* access modifiers changed from: private */
    @GuardedBy("this")
    public Listener listener;
    /* access modifiers changed from: private */
    public final int port;
    @VisibleForTesting
    final ProxyDetector proxyDetector;
    /* access modifiers changed from: private */
    public final Random random = new Random();
    private final Runnable resolveRunnable;
    /* access modifiers changed from: private */
    @GuardedBy("this")
    public boolean resolving;
    private final AtomicReference<ResourceResolver> resourceResolver = new AtomicReference<>();
    /* access modifiers changed from: private */
    @GuardedBy("this")
    public boolean shutdown;

    interface AddressResolver {
        List<InetAddress> resolveAddress(String str) throws Exception;
    }

    private enum JdkAddressResolver implements AddressResolver {
        INSTANCE;

        public List<InetAddress> resolveAddress(String str) throws UnknownHostException {
            return Collections.unmodifiableList(Arrays.asList(InetAddress.getAllByName(str)));
        }
    }

    @VisibleForTesting
    static final class ResolutionResults {
        final List<? extends InetAddress> addresses;
        final List<EquivalentAddressGroup> balancerAddresses;
        final List<String> txtRecords;

        ResolutionResults(List<? extends InetAddress> list, List<String> list2, List<EquivalentAddressGroup> list3) {
            this.addresses = Collections.unmodifiableList((List) Preconditions.checkNotNull(list, "addresses"));
            this.txtRecords = Collections.unmodifiableList((List) Preconditions.checkNotNull(list2, "txtRecords"));
            this.balancerAddresses = Collections.unmodifiableList((List) Preconditions.checkNotNull(list3, "balancerAddresses"));
        }

        public String toString() {
            return MoreObjects.toStringHelper((Object) this).add("addresses", (Object) this.addresses).add("txtRecords", (Object) this.txtRecords).add("balancerAddresses", (Object) this.balancerAddresses).toString();
        }
    }

    @VisibleForTesting
    static final class Resolve implements Runnable {
        private final long cacheTtlNanos;
        private ResolutionResults cachedResolutionResults = null;
        private final DnsNameResolver resolver;
        private final Stopwatch stopwatch;

        Resolve(DnsNameResolver dnsNameResolver, Stopwatch stopwatch2, long j) {
            this.resolver = dnsNameResolver;
            this.stopwatch = (Stopwatch) Preconditions.checkNotNull(stopwatch2, "stopwatch");
            this.cacheTtlNanos = j;
        }

        public void run() {
            if (DnsNameResolver.logger.isLoggable(Level.FINER)) {
                Logger access$000 = DnsNameResolver.logger;
                StringBuilder sb = new StringBuilder();
                sb.append("Attempting DNS resolution of ");
                sb.append(this.resolver.host);
                access$000.finer(sb.toString());
            }
            synchronized (this.resolver) {
                if (!this.resolver.shutdown) {
                    if (cacheRefreshRequired()) {
                        Listener access$300 = this.resolver.listener;
                        this.resolver.resolving = true;
                        try {
                            resolveInternal(access$300);
                            synchronized (this.resolver) {
                                this.resolver.resolving = false;
                            }
                        } catch (Throwable th) {
                            synchronized (this.resolver) {
                                this.resolver.resolving = false;
                                throw th;
                            }
                        }
                    }
                }
            }
        }

        private boolean cacheRefreshRequired() {
            if (this.cachedResolutionResults != null) {
                long j = this.cacheTtlNanos;
                if (j != 0 && (j <= 0 || this.stopwatch.elapsed(TimeUnit.NANOSECONDS) <= this.cacheTtlNanos)) {
                    return false;
                }
            }
            return true;
        }

        /* access modifiers changed from: 0000 */
        @VisibleForTesting
        public void resolveInternal(Listener listener) {
            InetSocketAddress createUnresolved = InetSocketAddress.createUnresolved(this.resolver.host, this.resolver.port);
            try {
                ProxyParameters proxyFor = this.resolver.proxyDetector.proxyFor(createUnresolved);
                if (proxyFor != null) {
                    if (DnsNameResolver.logger.isLoggable(Level.FINER)) {
                        Logger access$000 = DnsNameResolver.logger;
                        StringBuilder sb = new StringBuilder();
                        sb.append("Using proxy ");
                        sb.append(proxyFor.proxyAddress);
                        sb.append(" for ");
                        sb.append(this.resolver.host);
                        access$000.finer(sb.toString());
                    }
                    listener.onAddresses(Collections.singletonList(new EquivalentAddressGroup((SocketAddress) new ProxySocketAddress(createUnresolved, proxyFor))), Attributes.EMPTY);
                    return;
                }
                try {
                    Map map = null;
                    ResolutionResults resolveAll = DnsNameResolver.resolveAll(this.resolver.addressResolver, DnsNameResolver.shouldUseJndi(DnsNameResolver.enableJndi, DnsNameResolver.enableJndiLocalhost, this.resolver.host) ? this.resolver.getResourceResolver() : null, DnsNameResolver.enableSrv, DnsNameResolver.enableTxt, this.resolver.host);
                    this.cachedResolutionResults = resolveAll;
                    if (this.cacheTtlNanos > 0) {
                        this.stopwatch.reset().start();
                    }
                    if (DnsNameResolver.logger.isLoggable(Level.FINER)) {
                        Logger access$0002 = DnsNameResolver.logger;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Found DNS results ");
                        sb2.append(resolveAll);
                        sb2.append(" for ");
                        sb2.append(this.resolver.host);
                        access$0002.finer(sb2.toString());
                    }
                    ArrayList arrayList = new ArrayList();
                    for (InetAddress inetSocketAddress : resolveAll.addresses) {
                        arrayList.add(new EquivalentAddressGroup((SocketAddress) new InetSocketAddress(inetSocketAddress, this.resolver.port)));
                    }
                    arrayList.addAll(resolveAll.balancerAddresses);
                    if (arrayList.isEmpty()) {
                        Status status = Status.UNAVAILABLE;
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("No DNS backend or balancer addresses found for ");
                        sb3.append(this.resolver.host);
                        listener.onError(status.withDescription(sb3.toString()));
                        return;
                    }
                    Builder newBuilder = Attributes.newBuilder();
                    if (!resolveAll.txtRecords.isEmpty()) {
                        try {
                            for (Map map2 : DnsNameResolver.parseTxtResults(resolveAll.txtRecords)) {
                                try {
                                    map = DnsNameResolver.maybeChooseServiceConfig(map2, this.resolver.random, DnsNameResolver.getLocalHostname());
                                    continue;
                                } catch (RuntimeException e) {
                                    Logger access$0003 = DnsNameResolver.logger;
                                    Level level = Level.WARNING;
                                    StringBuilder sb4 = new StringBuilder();
                                    sb4.append("Bad service config choice ");
                                    sb4.append(map2);
                                    access$0003.log(level, sb4.toString(), e);
                                    continue;
                                }
                                if (map != null) {
                                    break;
                                }
                            }
                        } catch (RuntimeException e2) {
                            DnsNameResolver.logger.log(Level.WARNING, "Can't parse service Configs", e2);
                        }
                        if (map != null) {
                            newBuilder.set(GrpcAttributes.NAME_RESOLVER_SERVICE_CONFIG, map);
                        }
                    } else {
                        DnsNameResolver.logger.log(Level.FINE, "No TXT records found for {0}", new Object[]{this.resolver.host});
                    }
                    listener.onAddresses(arrayList, newBuilder.build());
                } catch (Exception e3) {
                    Status status2 = Status.UNAVAILABLE;
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append("Unable to resolve host ");
                    sb5.append(this.resolver.host);
                    listener.onError(status2.withDescription(sb5.toString()).withCause(e3));
                }
            } catch (IOException e4) {
                Status status3 = Status.UNAVAILABLE;
                StringBuilder sb6 = new StringBuilder();
                sb6.append("Unable to resolve host ");
                sb6.append(this.resolver.host);
                listener.onError(status3.withDescription(sb6.toString()).withCause(e4));
            }
        }
    }

    interface ResourceResolver {
        List<EquivalentAddressGroup> resolveSrv(AddressResolver addressResolver, String str) throws Exception;

        List<String> resolveTxt(String str) throws Exception;
    }

    interface ResourceResolverFactory {
        @Nullable
        ResourceResolver newResourceResolver();

        @Nullable
        Throwable unavailabilityCause();
    }

    DnsNameResolver(@Nullable String str, String str2, Attributes attributes, Resource<Executor> resource, ProxyDetector proxyDetector2, Stopwatch stopwatch, boolean z) {
        this.executorResource = resource;
        StringBuilder sb = new StringBuilder();
        sb.append("//");
        sb.append((String) Preconditions.checkNotNull(str2, "name"));
        URI create = URI.create(sb.toString());
        Preconditions.checkArgument(create.getHost() != null, "Invalid DNS name: %s", (Object) str2);
        this.authority = (String) Preconditions.checkNotNull(create.getAuthority(), "nameUri (%s) doesn't have an authority", (Object) create);
        this.host = create.getHost();
        if (create.getPort() == -1) {
            Integer num = (Integer) attributes.get(Factory.PARAMS_DEFAULT_PORT);
            if (num != null) {
                this.port = num.intValue();
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("name '");
                sb2.append(str2);
                sb2.append("' doesn't contain a port, and default port is not set in params");
                throw new IllegalArgumentException(sb2.toString());
            }
        } else {
            this.port = create.getPort();
        }
        this.proxyDetector = proxyDetector2;
        this.resolveRunnable = new Resolve(this, stopwatch, getNetworkAddressCacheTtlNanos(z));
    }

    public final String getServiceAuthority() {
        return this.authority;
    }

    public final synchronized void start(Listener listener2) {
        Preconditions.checkState(this.listener == null, "already started");
        this.executor = (Executor) SharedResourceHolder.get(this.executorResource);
        this.listener = (Listener) Preconditions.checkNotNull(listener2, CastExtraArgs.LISTENER);
        resolve();
    }

    public final synchronized void refresh() {
        Preconditions.checkState(this.listener != null, "not started");
        resolve();
    }

    @GuardedBy("this")
    private void resolve() {
        if (!this.resolving && !this.shutdown) {
            this.executor.execute(this.resolveRunnable);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void shutdown() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.shutdown     // Catch:{ all -> 0x001c }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r2)
            return
        L_0x0007:
            r0 = 1
            r2.shutdown = r0     // Catch:{ all -> 0x001c }
            java.util.concurrent.Executor r0 = r2.executor     // Catch:{ all -> 0x001c }
            if (r0 == 0) goto L_0x001a
            io.grpc.internal.SharedResourceHolder$Resource<java.util.concurrent.Executor> r0 = r2.executorResource     // Catch:{ all -> 0x001c }
            java.util.concurrent.Executor r1 = r2.executor     // Catch:{ all -> 0x001c }
            java.lang.Object r0 = io.grpc.internal.SharedResourceHolder.release(r0, r1)     // Catch:{ all -> 0x001c }
            java.util.concurrent.Executor r0 = (java.util.concurrent.Executor) r0     // Catch:{ all -> 0x001c }
            r2.executor = r0     // Catch:{ all -> 0x001c }
        L_0x001a:
            monitor-exit(r2)
            return
        L_0x001c:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.DnsNameResolver.shutdown():void");
    }

    @VisibleForTesting
    static ResolutionResults resolveAll(AddressResolver addressResolver2, @Nullable ResourceResolver resourceResolver2, boolean z, boolean z2, String str) {
        Throwable e;
        List emptyList = Collections.emptyList();
        List emptyList2 = Collections.emptyList();
        List emptyList3 = Collections.emptyList();
        Throwable th = null;
        try {
            emptyList = addressResolver2.resolveAddress(str);
            e = null;
        } catch (Exception e2) {
            e = e2;
        }
        if (resourceResolver2 != null) {
            if (z) {
                try {
                    StringBuilder sb = new StringBuilder();
                    sb.append("_grpclb._tcp.");
                    sb.append(str);
                    emptyList2 = resourceResolver2.resolveSrv(addressResolver2, sb.toString());
                    e = null;
                } catch (Exception e3) {
                    e = e3;
                }
            } else {
                e = null;
            }
            if (z2) {
                boolean z3 = false;
                boolean z4 = !z || e != null;
                if (e != null && z4) {
                    z3 = true;
                }
                if (!z3) {
                    try {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("_grpc_config.");
                        sb2.append(str);
                        emptyList3 = resourceResolver2.resolveTxt(sb2.toString());
                    } catch (Exception e4) {
                        th = e4;
                    }
                }
            }
        } else {
            e = null;
        }
        if (e != null) {
            if (e == null) {
                try {
                    if (!emptyList2.isEmpty()) {
                    }
                } catch (Throwable th2) {
                    if (e != null) {
                        logger.log(Level.FINE, "Address resolution failure", e);
                    }
                    if (e != null) {
                        logger.log(Level.FINE, "Balancer resolution failure", e);
                    }
                    if (th != null) {
                        logger.log(Level.FINE, "ServiceConfig resolution failure", th);
                    }
                    throw th2;
                }
            }
            Throwables.throwIfUnchecked(e);
            throw new RuntimeException(e);
        }
        if (e != null) {
            logger.log(Level.FINE, "Address resolution failure", e);
        }
        if (e != null) {
            logger.log(Level.FINE, "Balancer resolution failure", e);
        }
        if (th != null) {
            logger.log(Level.FINE, "ServiceConfig resolution failure", th);
        }
        return new ResolutionResults(emptyList, emptyList3, emptyList2);
    }

    @VisibleForTesting
    static List<Map<String, Object>> parseTxtResults(List<String> list) {
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (str.startsWith("_grpc_config=")) {
                try {
                    Object parse = JsonParser.parse(str.substring(13));
                    if (parse instanceof List) {
                        List<Object> list2 = (List) parse;
                        for (Object obj : list2) {
                            if (!(obj instanceof Map)) {
                                StringBuilder sb = new StringBuilder();
                                sb.append("wrong element type ");
                                sb.append(parse);
                                throw new IOException(sb.toString());
                            }
                        }
                        arrayList.addAll(list2);
                    } else {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("wrong type ");
                        sb2.append(parse);
                        throw new IOException(sb2.toString());
                    }
                } catch (IOException e) {
                    Logger logger2 = logger;
                    Level level = Level.WARNING;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("Bad service config: ");
                    sb3.append(str);
                    logger2.log(level, sb3.toString(), e);
                }
            } else {
                logger.log(Level.FINE, "Ignoring non service config {0}", new Object[]{str});
            }
        }
        return arrayList;
    }

    @Nullable
    private static final Double getPercentageFromChoice(Map<String, Object> map) {
        if (!map.containsKey(MacroGoalFormat.PERCENTAGE)) {
            return null;
        }
        return ServiceConfigUtil.getDouble(map, MacroGoalFormat.PERCENTAGE);
    }

    @Nullable
    private static final List<String> getClientLanguagesFromChoice(Map<String, Object> map) {
        if (!map.containsKey("clientLanguage")) {
            return null;
        }
        return ServiceConfigUtil.checkStringList(ServiceConfigUtil.getList(map, "clientLanguage"));
    }

    @Nullable
    private static final List<String> getHostnamesFromChoice(Map<String, Object> map) {
        if (!map.containsKey("clientHostname")) {
            return null;
        }
        return ServiceConfigUtil.checkStringList(ServiceConfigUtil.getList(map, "clientHostname"));
    }

    private static long getNetworkAddressCacheTtlNanos(boolean z) {
        if (z) {
            return 0;
        }
        String property = System.getProperty("networkaddress.cache.ttl");
        long j = 30;
        if (property != null) {
            try {
                j = Long.parseLong(property);
            } catch (NumberFormatException unused) {
                logger.log(Level.WARNING, "Property({0}) valid is not valid number format({1}), fall back to default({2})", new Object[]{"networkaddress.cache.ttl", property, Long.valueOf(30)});
            }
        }
        if (j > 0) {
            j = TimeUnit.SECONDS.toNanos(j);
        }
        return j;
    }

    @Nullable
    @VisibleForTesting
    static Map<String, Object> maybeChooseServiceConfig(Map<String, Object> map, Random random2, String str) {
        boolean z;
        for (Entry entry : map.entrySet()) {
            Verify.verify(SERVICE_CONFIG_CHOICE_KEYS.contains(entry.getKey()), "Bad key: %s", (Object) entry);
        }
        List clientLanguagesFromChoice = getClientLanguagesFromChoice(map);
        boolean z2 = true;
        if (clientLanguagesFromChoice != null && !clientLanguagesFromChoice.isEmpty()) {
            Iterator it = clientLanguagesFromChoice.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                if ("java".equalsIgnoreCase((String) it.next())) {
                    z = true;
                    break;
                }
            }
            if (!z) {
                return null;
            }
        }
        Double percentageFromChoice = getPercentageFromChoice(map);
        if (percentageFromChoice != null) {
            int intValue = percentageFromChoice.intValue();
            Verify.verify(intValue >= 0 && intValue <= 100, "Bad percentage: %s", (Object) percentageFromChoice);
            if (random2.nextInt(100) >= intValue) {
                return null;
            }
        }
        List hostnamesFromChoice = getHostnamesFromChoice(map);
        if (hostnamesFromChoice != null && !hostnamesFromChoice.isEmpty()) {
            Iterator it2 = hostnamesFromChoice.iterator();
            while (true) {
                if (it2.hasNext()) {
                    if (((String) it2.next()).equals(str)) {
                        break;
                    }
                } else {
                    z2 = false;
                    break;
                }
            }
            if (!z2) {
                return null;
            }
        }
        return ServiceConfigUtil.getObject(map, "serviceConfig");
    }

    /* access modifiers changed from: private */
    @Nullable
    public ResourceResolver getResourceResolver() {
        ResourceResolver resourceResolver2 = (ResourceResolver) this.resourceResolver.get();
        if (resourceResolver2 != null) {
            return resourceResolver2;
        }
        ResourceResolverFactory resourceResolverFactory2 = resourceResolverFactory;
        return resourceResolverFactory2 != null ? resourceResolverFactory2.newResourceResolver() : resourceResolver2;
    }

    @Nullable
    @VisibleForTesting
    static ResourceResolverFactory getResourceResolverFactory(ClassLoader classLoader) {
        try {
            try {
                try {
                    ResourceResolverFactory resourceResolverFactory2 = (ResourceResolverFactory) Class.forName("io.grpc.internal.JndiResourceResolverFactory", true, classLoader).asSubclass(ResourceResolverFactory.class).getConstructor(new Class[0]).newInstance(new Object[0]);
                    if (resourceResolverFactory2.unavailabilityCause() != null) {
                        logger.log(Level.FINE, "JndiResourceResolverFactory not available, skipping.", resourceResolverFactory2.unavailabilityCause());
                    }
                    return resourceResolverFactory2;
                } catch (Exception e) {
                    logger.log(Level.FINE, "Can't construct JndiResourceResolverFactory, skipping.", e);
                    return null;
                }
            } catch (Exception e2) {
                logger.log(Level.FINE, "Can't find JndiResourceResolverFactory ctor, skipping.", e2);
                return null;
            }
        } catch (ClassNotFoundException e3) {
            logger.log(Level.FINE, "Unable to find JndiResourceResolverFactory, skipping.", e3);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static String getLocalHostname() {
        if (localHostname == null) {
            try {
                localHostname = InetAddress.getLocalHost().getHostName();
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }
        }
        return localHostname;
    }

    @VisibleForTesting
    static boolean shouldUseJndi(boolean z, boolean z2, String str) {
        if (!z) {
            return false;
        }
        if ("localhost".equalsIgnoreCase(str)) {
            return z2;
        }
        if (str.contains(":")) {
            return false;
        }
        boolean z3 = true;
        boolean z4 = true;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt != '.') {
                z4 &= charAt >= '0' && charAt <= '9';
            }
        }
        if (z4) {
            z3 = false;
        }
        return z3;
    }
}
