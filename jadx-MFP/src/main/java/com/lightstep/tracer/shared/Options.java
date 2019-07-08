package com.lightstep.tracer.shared;

import com.mopub.common.Constants;
import io.opentracing.ScopeManager;
import io.opentracing.propagation.Format;
import io.opentracing.propagation.Format.Builtin;
import io.opentracing.util.ThreadLocalScopeManager;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public final class Options {
    static final Map<Format<?>, Propagator<?>> BUILTIN_PROPAGATORS = Collections.unmodifiableMap(new HashMap<Format<?>, Propagator<?>>() {
        {
            put(Builtin.TEXT_MAP, Propagator.TEXT_MAP);
            put(Builtin.HTTP_HEADERS, Propagator.HTTP_HEADERS);
            put(Builtin.BINARY, Propagator.BINARY);
        }
    });
    final String accessToken;
    final URL collectorUrl;
    final long deadlineMillis;
    final boolean disableReportingLoop;
    final int maxBufferedSpans;
    final long maxReportingIntervalMillis;
    final Map<Format<?>, Propagator<?>> propagators;
    final boolean resetClient;
    final ScopeManager scopeManager;
    final Map<String, Object> tags;
    final boolean useClockCorrection;
    final int verbosity;

    public static class OptionsBuilder {
        private String accessToken;
        private String collectorHost = "collector-grpc.lightstep.com";
        private int collectorPort = -1;
        private String collectorProtocol = Constants.HTTPS;
        private long deadlineMillis = -1;
        private boolean disableReportingLoop = false;
        private int maxBufferedSpans = -1;
        private long maxReportingIntervalMillis;
        private Map<Format<?>, Propagator<?>> propagators = new HashMap();
        private boolean resetClient = true;
        private ScopeManager scopeManager;
        private Map<String, Object> tags = new HashMap();
        private boolean useClockCorrection = true;
        private int verbosity = 1;

        public OptionsBuilder() {
        }

        public OptionsBuilder(Options options) {
            this.accessToken = options.accessToken;
            this.collectorProtocol = options.collectorUrl.getProtocol();
            this.collectorHost = options.collectorUrl.getHost();
            this.collectorPort = options.collectorUrl.getPort();
            this.maxReportingIntervalMillis = options.maxReportingIntervalMillis;
            this.maxBufferedSpans = options.maxBufferedSpans;
            this.verbosity = options.verbosity;
            this.disableReportingLoop = options.disableReportingLoop;
            this.resetClient = options.resetClient;
            this.tags = options.tags;
            this.scopeManager = options.scopeManager;
            this.useClockCorrection = options.useClockCorrection;
            this.deadlineMillis = options.deadlineMillis;
            this.propagators = options.propagators;
        }

        public OptionsBuilder withAccessToken(String str) {
            this.accessToken = str;
            return this;
        }

        public OptionsBuilder withCollectorProtocol(String str) {
            if (Constants.HTTPS.equals(str) || Constants.HTTP.equals(str)) {
                this.collectorProtocol = str;
                return this;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid protocol for collector: ");
            sb.append(str);
            throw new IllegalArgumentException(sb.toString());
        }

        public OptionsBuilder withCollectorHost(String str) {
            if (str == null || "".equals(str.trim())) {
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid collector host: ");
                sb.append(str);
                throw new IllegalArgumentException(sb.toString());
            }
            this.collectorHost = str;
            return this;
        }

        public OptionsBuilder withCollectorPort(int i) {
            if (i > 0) {
                this.collectorPort = i;
                return this;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid collector port: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }

        public OptionsBuilder withComponentName(String str) {
            return withTag("lightstep.component_name", str);
        }

        public OptionsBuilder withTag(String str, Object obj) {
            this.tags.put(str, obj);
            return this;
        }

        public OptionsBuilder withMaxReportingIntervalMillis(int i) {
            this.maxReportingIntervalMillis = (long) i;
            return this;
        }

        public OptionsBuilder withResetClient(boolean z) {
            this.resetClient = z;
            return this;
        }

        public Options build() throws MalformedURLException {
            defaultComponentName();
            defaultGuid();
            defaultMaxReportingIntervalMillis();
            defaultMaxBufferedSpans();
            defaultPropagators();
            defaultScopeManager();
            defaultDeadlineMillis();
            Options options = new Options(this.accessToken, getCollectorUrl(), this.maxReportingIntervalMillis, this.maxBufferedSpans, this.verbosity, this.disableReportingLoop, this.resetClient, this.tags, this.useClockCorrection, this.scopeManager, this.deadlineMillis, this.propagators);
            return options;
        }

        private void defaultScopeManager() {
            if (this.scopeManager == null) {
                this.scopeManager = new ThreadLocalScopeManager();
            }
        }

        private void defaultMaxReportingIntervalMillis() {
            if (this.maxReportingIntervalMillis <= 0) {
                this.maxReportingIntervalMillis = 3000;
            }
        }

        private void defaultMaxBufferedSpans() {
            if (this.maxBufferedSpans < 0) {
                this.maxBufferedSpans = 1000;
            }
        }

        private void defaultGuid() {
            if (this.tags.get("lightstep.guid") == null) {
                withTag("lightstep.guid", Long.valueOf(Util.generateRandomGUID()));
            }
        }

        private void defaultComponentName() {
            if (this.tags.get("lightstep.component_name") == null) {
                String property = System.getProperty("sun.java.command");
                if (property != null) {
                    StringTokenizer stringTokenizer = new StringTokenizer(property);
                    if (stringTokenizer.hasMoreTokens()) {
                        withComponentName(stringTokenizer.nextToken());
                    }
                }
            }
        }

        private void defaultDeadlineMillis() {
            if (this.deadlineMillis < 0) {
                this.deadlineMillis = 30000;
            }
        }

        private void defaultPropagators() {
            for (Entry entry : Options.BUILTIN_PROPAGATORS.entrySet()) {
                Format format = (Format) entry.getKey();
                if (!this.propagators.containsKey(format)) {
                    this.propagators.put(format, entry.getValue());
                }
            }
        }

        private int getPort() {
            int i = this.collectorPort;
            if (i > 0) {
                return i;
            }
            return this.collectorProtocol.equals(Constants.HTTPS) ? 443 : 80;
        }

        private URL getCollectorUrl() throws MalformedURLException {
            return new URL(this.collectorProtocol, this.collectorHost, getPort(), "/api/v2/reports");
        }
    }

    private Options(String str, URL url, long j, int i, int i2, boolean z, boolean z2, Map<String, Object> map, boolean z3, ScopeManager scopeManager2, long j2, Map<Format<?>, Propagator<?>> map2) {
        this.accessToken = str;
        this.collectorUrl = url;
        this.maxReportingIntervalMillis = j;
        this.maxBufferedSpans = i;
        this.verbosity = i2;
        this.disableReportingLoop = z;
        this.resetClient = z2;
        this.tags = map;
        this.useClockCorrection = z3;
        this.scopeManager = scopeManager2;
        this.deadlineMillis = j2;
        this.propagators = map2;
    }

    /* access modifiers changed from: 0000 */
    public long getGuid() {
        return ((Long) this.tags.get("lightstep.guid")).longValue();
    }

    public Options setDefaultReportingIntervalMillis(int i) {
        if (this.maxReportingIntervalMillis != 3000) {
            return this;
        }
        try {
            return new OptionsBuilder(this).withMaxReportingIntervalMillis(i).build();
        } catch (MalformedURLException unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unexpected error when building a new set ofoptions from a valid set of existing options. collectorUrl=");
            sb.append(this.collectorUrl);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public Options disableResetClient() {
        try {
            return new OptionsBuilder(this).withResetClient(false).build();
        } catch (MalformedURLException unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unexpected error when building a new set ofoptions from a valid set of existing options. collectorUrl=");
            sb.append(this.collectorUrl);
            throw new IllegalArgumentException(sb.toString());
        }
    }
}
