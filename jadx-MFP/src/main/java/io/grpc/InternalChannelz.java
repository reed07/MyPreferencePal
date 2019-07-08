package io.grpc;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.security.cert.Certificate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;

@Internal
public final class InternalChannelz {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final InternalChannelz INSTANCE = new InternalChannelz();
    /* access modifiers changed from: private */
    public static final Logger log = Logger.getLogger(InternalChannelz.class.getName());
    private final ConcurrentMap<Long, InternalInstrumented<SocketStats>> otherSockets = new ConcurrentHashMap();
    private final ConcurrentMap<Long, Object> perServerSockets = new ConcurrentHashMap();
    private final ConcurrentNavigableMap<Long, InternalInstrumented<ChannelStats>> rootChannels = new ConcurrentSkipListMap();
    private final ConcurrentNavigableMap<Long, InternalInstrumented<ServerStats>> servers = new ConcurrentSkipListMap();
    private final ConcurrentMap<Long, InternalInstrumented<ChannelStats>> subchannels = new ConcurrentHashMap();

    @Immutable
    public static final class ChannelStats {

        public static final class Builder {
            private List<InternalWithLogId> sockets = Collections.emptyList();
            private List<InternalWithLogId> subchannels = Collections.emptyList();
        }
    }

    @Immutable
    public static final class ChannelTrace {

        public static final class Builder {
            private List<Event> events = Collections.emptyList();
        }

        @Immutable
        public static final class Event {
            @Nullable
            public final InternalWithLogId channelRef;
            public final String description;
            public final Severity severity;
            @Nullable
            public final InternalWithLogId subchannelRef;
            public final long timestampNanos;

            public static final class Builder {
                private InternalWithLogId channelRef;
                private String description;
                private Severity severity;
                private InternalWithLogId subchannelRef;
                private Long timestampNanos;

                public Builder setDescription(String str) {
                    this.description = str;
                    return this;
                }

                public Builder setTimestampNanos(long j) {
                    this.timestampNanos = Long.valueOf(j);
                    return this;
                }

                public Builder setSeverity(Severity severity2) {
                    this.severity = severity2;
                    return this;
                }

                public Builder setSubchannelRef(InternalWithLogId internalWithLogId) {
                    this.subchannelRef = internalWithLogId;
                    return this;
                }

                public Event build() {
                    Preconditions.checkNotNull(this.description, "description");
                    Preconditions.checkNotNull(this.severity, "severity");
                    Preconditions.checkNotNull(this.timestampNanos, "timestampNanos");
                    Preconditions.checkState(this.channelRef == null || this.subchannelRef == null, "at least one of channelRef and subchannelRef must be null");
                    Event event = new Event(this.description, this.severity, this.timestampNanos.longValue(), this.channelRef, this.subchannelRef);
                    return event;
                }
            }

            public enum Severity {
                CT_UNKNOWN,
                CT_INFO,
                CT_WARNING,
                CT_ERROR
            }

            private Event(String str, Severity severity2, long j, @Nullable InternalWithLogId internalWithLogId, @Nullable InternalWithLogId internalWithLogId2) {
                this.description = str;
                this.severity = (Severity) Preconditions.checkNotNull(severity2, "severity");
                this.timestampNanos = j;
                this.channelRef = internalWithLogId;
                this.subchannelRef = internalWithLogId2;
            }

            public int hashCode() {
                return Objects.hashCode(this.description, this.severity, Long.valueOf(this.timestampNanos), this.channelRef, this.subchannelRef);
            }

            public boolean equals(Object obj) {
                boolean z = false;
                if (!(obj instanceof Event)) {
                    return false;
                }
                Event event = (Event) obj;
                if (Objects.equal(this.description, event.description) && Objects.equal(this.severity, event.severity) && this.timestampNanos == event.timestampNanos && Objects.equal(this.channelRef, event.channelRef) && Objects.equal(this.subchannelRef, event.subchannelRef)) {
                    z = true;
                }
                return z;
            }

            public String toString() {
                return MoreObjects.toStringHelper((Object) this).add("description", (Object) this.description).add("severity", (Object) this.severity).add("timestampNanos", this.timestampNanos).add("channelRef", (Object) this.channelRef).add("subchannelRef", (Object) this.subchannelRef).toString();
            }
        }
    }

    public static final class OtherSecurity {
    }

    public static final class RootChannelList {
    }

    public static final class Security {
        @Nullable
        public final OtherSecurity other = null;
        @Nullable
        public final Tls tls;

        public Security(Tls tls2) {
            this.tls = (Tls) Preconditions.checkNotNull(tls2);
        }
    }

    public static final class ServerList {
    }

    public static final class ServerSocketsList {
    }

    @Immutable
    public static final class ServerStats {

        public static final class Builder {
            public List<InternalInstrumented<SocketStats>> listenSockets = Collections.emptyList();
        }
    }

    public static final class SocketOptions {

        public static final class Builder {
            private final Map<String, String> others = new HashMap();
        }
    }

    public static final class SocketStats {
    }

    public static final class TcpInfo {

        public static final class Builder {
        }
    }

    @Immutable
    public static final class Tls {
        public final String cipherSuiteStandardName;
        @Nullable
        public final Certificate localCert;
        @Nullable
        public final Certificate remoteCert;

        public Tls(SSLSession sSLSession) {
            String cipherSuite = sSLSession.getCipherSuite();
            Certificate[] localCertificates = sSLSession.getLocalCertificates();
            Certificate certificate = null;
            Certificate certificate2 = localCertificates != null ? localCertificates[0] : null;
            try {
                Certificate[] peerCertificates = sSLSession.getPeerCertificates();
                if (peerCertificates != null) {
                    certificate = peerCertificates[0];
                }
            } catch (SSLPeerUnverifiedException e) {
                InternalChannelz.log.log(Level.FINE, String.format("Peer cert not available for peerHost=%s", new Object[]{sSLSession.getPeerHost()}), e);
            }
            this.cipherSuiteStandardName = cipherSuite;
            this.localCert = certificate2;
            this.remoteCert = certificate;
        }
    }

    @Immutable
    public static final class TransportStats {
    }

    public static InternalChannelz instance() {
        return INSTANCE;
    }

    public void addSubchannel(InternalInstrumented<ChannelStats> internalInstrumented) {
        add(this.subchannels, internalInstrumented);
    }

    public void addRootChannel(InternalInstrumented<ChannelStats> internalInstrumented) {
        add(this.rootChannels, internalInstrumented);
    }

    public void addClientSocket(InternalInstrumented<SocketStats> internalInstrumented) {
        add(this.otherSockets, internalInstrumented);
    }

    public void removeSubchannel(InternalInstrumented<ChannelStats> internalInstrumented) {
        remove(this.subchannels, internalInstrumented);
    }

    public void removeRootChannel(InternalInstrumented<ChannelStats> internalInstrumented) {
        remove(this.rootChannels, internalInstrumented);
    }

    public void removeClientSocket(InternalInstrumented<SocketStats> internalInstrumented) {
        remove(this.otherSockets, internalInstrumented);
    }

    private static <T extends InternalInstrumented<?>> void add(Map<Long, T> map, T t) {
        InternalInstrumented internalInstrumented = (InternalInstrumented) map.put(Long.valueOf(t.getLogId().getId()), t);
    }

    private static <T extends InternalInstrumented<?>> void remove(Map<Long, T> map, T t) {
        InternalInstrumented internalInstrumented = (InternalInstrumented) map.remove(Long.valueOf(id(t)));
    }

    public static long id(InternalWithLogId internalWithLogId) {
        return internalWithLogId.getLogId().getId();
    }
}
