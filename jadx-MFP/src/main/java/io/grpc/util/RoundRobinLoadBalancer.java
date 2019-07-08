package io.grpc.util;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.Attributes.Builder;
import io.grpc.Attributes.Key;
import io.grpc.ChannelLogger.ChannelLogLevel;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.EquivalentAddressGroup;
import io.grpc.LoadBalancer;
import io.grpc.LoadBalancer.Helper;
import io.grpc.LoadBalancer.PickResult;
import io.grpc.LoadBalancer.PickSubchannelArgs;
import io.grpc.LoadBalancer.Subchannel;
import io.grpc.LoadBalancer.SubchannelPicker;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.internal.GrpcAttributes;
import io.grpc.internal.ServiceConfigUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

final class RoundRobinLoadBalancer extends LoadBalancer {
    private static final Status EMPTY_OK = Status.OK.withDescription("no subchannels ready");
    @VisibleForTesting
    static final Key<Ref<ConnectivityStateInfo>> STATE_INFO = Key.create("state-info");
    static final Key<Ref<Subchannel>> STICKY_REF = Key.create("sticky-ref");
    private RoundRobinPicker currentPicker = new EmptyPicker(EMPTY_OK);
    private ConnectivityState currentState;
    private final Helper helper;
    private final Random random;
    @Nullable
    private StickinessState stickinessState;
    private final Map<EquivalentAddressGroup, Subchannel> subchannels = new HashMap();

    @VisibleForTesting
    static final class EmptyPicker extends RoundRobinPicker {
        private final Status status;

        EmptyPicker(@Nonnull Status status2) {
            super();
            this.status = (Status) Preconditions.checkNotNull(status2, "status");
        }

        public PickResult pickSubchannel(PickSubchannelArgs pickSubchannelArgs) {
            return this.status.isOk() ? PickResult.withNoResult() : PickResult.withError(this.status);
        }

        /* access modifiers changed from: 0000 */
        public boolean isEquivalentTo(RoundRobinPicker roundRobinPicker) {
            if (roundRobinPicker instanceof EmptyPicker) {
                EmptyPicker emptyPicker = (EmptyPicker) roundRobinPicker;
                if (Objects.equal(this.status, emptyPicker.status) || (this.status.isOk() && emptyPicker.status.isOk())) {
                    return true;
                }
            }
            return false;
        }
    }

    @VisibleForTesting
    static final class ReadyPicker extends RoundRobinPicker {
        private static final AtomicIntegerFieldUpdater<ReadyPicker> indexUpdater = AtomicIntegerFieldUpdater.newUpdater(ReadyPicker.class, "index");
        private volatile int index;
        private final List<Subchannel> list;
        @Nullable
        private final StickinessState stickinessState;

        ReadyPicker(List<Subchannel> list2, int i, @Nullable StickinessState stickinessState2) {
            super();
            Preconditions.checkArgument(!list2.isEmpty(), "empty list");
            this.list = list2;
            this.stickinessState = stickinessState2;
            this.index = i - 1;
        }

        /* JADX WARNING: Removed duplicated region for block: B:11:0x0031  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public io.grpc.LoadBalancer.PickResult pickSubchannel(io.grpc.LoadBalancer.PickSubchannelArgs r3) {
            /*
                r2 = this;
                io.grpc.util.RoundRobinLoadBalancer$StickinessState r0 = r2.stickinessState
                if (r0 == 0) goto L_0x002d
                io.grpc.Metadata r3 = r3.getHeaders()
                io.grpc.util.RoundRobinLoadBalancer$StickinessState r0 = r2.stickinessState
                io.grpc.Metadata$Key<java.lang.String> r0 = r0.key
                java.lang.Object r3 = r3.get(r0)
                java.lang.String r3 = (java.lang.String) r3
                if (r3 == 0) goto L_0x002d
                io.grpc.util.RoundRobinLoadBalancer$StickinessState r0 = r2.stickinessState
                io.grpc.LoadBalancer$Subchannel r0 = r0.getSubchannel(r3)
                if (r0 == 0) goto L_0x0022
                boolean r1 = io.grpc.util.RoundRobinLoadBalancer.isReady(r0)
                if (r1 != 0) goto L_0x002e
            L_0x0022:
                io.grpc.util.RoundRobinLoadBalancer$StickinessState r0 = r2.stickinessState
                io.grpc.LoadBalancer$Subchannel r1 = r2.nextSubchannel()
                io.grpc.LoadBalancer$Subchannel r0 = r0.maybeRegister(r3, r1)
                goto L_0x002e
            L_0x002d:
                r0 = 0
            L_0x002e:
                if (r0 == 0) goto L_0x0031
                goto L_0x0035
            L_0x0031:
                io.grpc.LoadBalancer$Subchannel r0 = r2.nextSubchannel()
            L_0x0035:
                io.grpc.LoadBalancer$PickResult r3 = io.grpc.LoadBalancer.PickResult.withSubchannel(r0)
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.util.RoundRobinLoadBalancer.ReadyPicker.pickSubchannel(io.grpc.LoadBalancer$PickSubchannelArgs):io.grpc.LoadBalancer$PickResult");
        }

        private Subchannel nextSubchannel() {
            int i;
            int size = this.list.size();
            int incrementAndGet = indexUpdater.incrementAndGet(this);
            if (incrementAndGet >= size) {
                i = incrementAndGet % size;
                indexUpdater.compareAndSet(this, incrementAndGet, i);
            } else {
                i = incrementAndGet;
            }
            return (Subchannel) this.list.get(i);
        }

        /* access modifiers changed from: 0000 */
        public boolean isEquivalentTo(RoundRobinPicker roundRobinPicker) {
            boolean z = false;
            if (!(roundRobinPicker instanceof ReadyPicker)) {
                return false;
            }
            ReadyPicker readyPicker = (ReadyPicker) roundRobinPicker;
            if (readyPicker == this || (this.stickinessState == readyPicker.stickinessState && this.list.size() == readyPicker.list.size() && new HashSet(this.list).containsAll(readyPicker.list))) {
                z = true;
            }
            return z;
        }
    }

    @VisibleForTesting
    static final class Ref<T> {
        T value;

        Ref(T t) {
            this.value = t;
        }
    }

    private static abstract class RoundRobinPicker extends SubchannelPicker {
        /* access modifiers changed from: 0000 */
        public abstract boolean isEquivalentTo(RoundRobinPicker roundRobinPicker);

        private RoundRobinPicker() {
        }
    }

    @VisibleForTesting
    static final class StickinessState {
        final Queue<String> evictionQueue = new ConcurrentLinkedQueue();
        final Metadata.Key<String> key;
        final ConcurrentMap<String, Ref<Subchannel>> stickinessMap = new ConcurrentHashMap();

        StickinessState(@Nonnull String str) {
            this.key = Metadata.Key.of(str, Metadata.ASCII_STRING_MARSHALLER);
        }

        /* access modifiers changed from: 0000 */
        @Nonnull
        public Subchannel maybeRegister(String str, @Nonnull Subchannel subchannel) {
            Ref ref;
            Ref ref2 = (Ref) subchannel.getAttributes().get(RoundRobinLoadBalancer.STICKY_REF);
            do {
                ref = (Ref) this.stickinessMap.putIfAbsent(str, ref2);
                if (ref == null) {
                    addToEvictionQueue(str);
                    return subchannel;
                }
                Subchannel subchannel2 = (Subchannel) ref.value;
                if (subchannel2 != null && RoundRobinLoadBalancer.isReady(subchannel2)) {
                    return subchannel2;
                }
            } while (!this.stickinessMap.replace(str, ref, ref2));
            return subchannel;
        }

        private void addToEvictionQueue(String str) {
            while (this.stickinessMap.size() >= 1000) {
                String str2 = (String) this.evictionQueue.poll();
                if (str2 == null) {
                    break;
                }
                this.stickinessMap.remove(str2);
            }
            this.evictionQueue.add(str);
        }

        /* access modifiers changed from: 0000 */
        public void remove(Subchannel subchannel) {
            ((Ref) subchannel.getAttributes().get(RoundRobinLoadBalancer.STICKY_REF)).value = null;
        }

        /* access modifiers changed from: 0000 */
        @Nullable
        public Subchannel getSubchannel(String str) {
            Ref ref = (Ref) this.stickinessMap.get(str);
            if (ref != null) {
                return (Subchannel) ref.value;
            }
            return null;
        }
    }

    RoundRobinLoadBalancer(Helper helper2) {
        this.helper = (Helper) Preconditions.checkNotNull(helper2, "helper");
        this.random = new Random();
    }

    public void handleResolvedAddressGroups(List<EquivalentAddressGroup> list, Attributes attributes) {
        Set keySet = this.subchannels.keySet();
        Set stripAttrs = stripAttrs(list);
        Set<EquivalentAddressGroup> set = setsDifference(stripAttrs, keySet);
        Set<EquivalentAddressGroup> set2 = setsDifference(keySet, stripAttrs);
        Map map = (Map) attributes.get(GrpcAttributes.NAME_RESOLVER_SERVICE_CONFIG);
        if (map != null) {
            String stickinessMetadataKeyFromServiceConfig = ServiceConfigUtil.getStickinessMetadataKeyFromServiceConfig(map);
            if (stickinessMetadataKeyFromServiceConfig != null) {
                if (stickinessMetadataKeyFromServiceConfig.endsWith("-bin")) {
                    this.helper.getChannelLogger().log(ChannelLogLevel.WARNING, "Binary stickiness header is not supported. The header \"{0}\" will be ignored", stickinessMetadataKeyFromServiceConfig);
                } else {
                    StickinessState stickinessState2 = this.stickinessState;
                    if (stickinessState2 == null || !stickinessState2.key.name().equals(stickinessMetadataKeyFromServiceConfig)) {
                        this.stickinessState = new StickinessState(stickinessMetadataKeyFromServiceConfig);
                    }
                }
            }
        }
        for (EquivalentAddressGroup equivalentAddressGroup : set) {
            Builder builder = Attributes.newBuilder().set(STATE_INFO, new Ref(ConnectivityStateInfo.forNonError(ConnectivityState.IDLE)));
            Ref ref = null;
            if (this.stickinessState != null) {
                Key<Ref<Subchannel>> key = STICKY_REF;
                Ref ref2 = new Ref(null);
                builder.set(key, ref2);
                ref = ref2;
            }
            T t = (Subchannel) Preconditions.checkNotNull(this.helper.createSubchannel(equivalentAddressGroup, builder.build()), "subchannel");
            if (ref != null) {
                ref.value = t;
            }
            this.subchannels.put(equivalentAddressGroup, t);
            t.requestConnection();
        }
        for (EquivalentAddressGroup remove : set2) {
            shutdownSubchannel((Subchannel) this.subchannels.remove(remove));
        }
        updateBalancingState();
    }

    public void handleNameResolutionError(Status status) {
        ConnectivityState connectivityState = ConnectivityState.TRANSIENT_FAILURE;
        RoundRobinPicker roundRobinPicker = this.currentPicker;
        if (!(roundRobinPicker instanceof ReadyPicker)) {
            roundRobinPicker = new EmptyPicker(status);
        }
        updateBalancingState(connectivityState, roundRobinPicker);
    }

    public void handleSubchannelState(Subchannel subchannel, ConnectivityStateInfo connectivityStateInfo) {
        if (this.subchannels.get(subchannel.getAddresses()) == subchannel) {
            if (connectivityStateInfo.getState() == ConnectivityState.SHUTDOWN) {
                StickinessState stickinessState2 = this.stickinessState;
                if (stickinessState2 != null) {
                    stickinessState2.remove(subchannel);
                }
            }
            if (connectivityStateInfo.getState() == ConnectivityState.IDLE) {
                subchannel.requestConnection();
            }
            getSubchannelStateInfoRef(subchannel).value = connectivityStateInfo;
            updateBalancingState();
        }
    }

    private void shutdownSubchannel(Subchannel subchannel) {
        subchannel.shutdown();
        getSubchannelStateInfoRef(subchannel).value = ConnectivityStateInfo.forNonError(ConnectivityState.SHUTDOWN);
        StickinessState stickinessState2 = this.stickinessState;
        if (stickinessState2 != null) {
            stickinessState2.remove(subchannel);
        }
    }

    public void shutdown() {
        for (Subchannel shutdownSubchannel : getSubchannels()) {
            shutdownSubchannel(shutdownSubchannel);
        }
    }

    private void updateBalancingState() {
        List filterNonFailingSubchannels = filterNonFailingSubchannels(getSubchannels());
        if (filterNonFailingSubchannels.isEmpty()) {
            boolean z = false;
            Status status = EMPTY_OK;
            for (Subchannel subchannelStateInfoRef : getSubchannels()) {
                ConnectivityStateInfo connectivityStateInfo = (ConnectivityStateInfo) getSubchannelStateInfoRef(subchannelStateInfoRef).value;
                if (connectivityStateInfo.getState() == ConnectivityState.CONNECTING || connectivityStateInfo.getState() == ConnectivityState.IDLE) {
                    z = true;
                }
                if (status == EMPTY_OK || !status.isOk()) {
                    status = connectivityStateInfo.getStatus();
                }
            }
            updateBalancingState(z ? ConnectivityState.CONNECTING : ConnectivityState.TRANSIENT_FAILURE, new EmptyPicker(status));
            return;
        }
        updateBalancingState(ConnectivityState.READY, new ReadyPicker(filterNonFailingSubchannels, this.random.nextInt(filterNonFailingSubchannels.size()), this.stickinessState));
    }

    private void updateBalancingState(ConnectivityState connectivityState, RoundRobinPicker roundRobinPicker) {
        if (connectivityState != this.currentState || !roundRobinPicker.isEquivalentTo(this.currentPicker)) {
            this.helper.updateBalancingState(connectivityState, roundRobinPicker);
            this.currentState = connectivityState;
            this.currentPicker = roundRobinPicker;
        }
    }

    private static List<Subchannel> filterNonFailingSubchannels(Collection<Subchannel> collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        for (Subchannel subchannel : collection) {
            if (isReady(subchannel)) {
                arrayList.add(subchannel);
            }
        }
        return arrayList;
    }

    private static Set<EquivalentAddressGroup> stripAttrs(List<EquivalentAddressGroup> list) {
        HashSet hashSet = new HashSet(list.size());
        for (EquivalentAddressGroup addresses : list) {
            hashSet.add(new EquivalentAddressGroup(addresses.getAddresses()));
        }
        return hashSet;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public Collection<Subchannel> getSubchannels() {
        return this.subchannels.values();
    }

    private static Ref<ConnectivityStateInfo> getSubchannelStateInfoRef(Subchannel subchannel) {
        return (Ref) Preconditions.checkNotNull(subchannel.getAttributes().get(STATE_INFO), "STATE_INFO");
    }

    static boolean isReady(Subchannel subchannel) {
        return ((ConnectivityStateInfo) getSubchannelStateInfoRef(subchannel).value).getState() == ConnectivityState.READY;
    }

    private static <T> Set<T> setsDifference(Set<T> set, Set<T> set2) {
        HashSet hashSet = new HashSet(set);
        hashSet.removeAll(set2);
        return hashSet;
    }
}
