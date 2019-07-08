package com.lightstep.tracer.shared;

import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.lightstep.tracer.grpc.Auth;
import com.lightstep.tracer.grpc.Auth.Builder;
import com.lightstep.tracer.grpc.Command;
import com.lightstep.tracer.grpc.KeyValue;
import com.lightstep.tracer.grpc.ReportRequest;
import com.lightstep.tracer.grpc.ReportResponse;
import com.lightstep.tracer.grpc.Reporter;
import com.lightstep.tracer.grpc.Span;
import io.opentracing.Scope;
import io.opentracing.ScopeManager;
import io.opentracing.SpanContext;
import io.opentracing.Tracer;
import io.opentracing.Tracer.SpanBuilder;
import io.opentracing.propagation.Format;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public abstract class AbstractTracer implements Tracer {
    private final Builder auth;
    /* access modifiers changed from: private */
    public final CollectorClient client;
    private final ClientMetrics clientMetrics;
    /* access modifiers changed from: private */
    public final ClockState clockState;
    private boolean firstErrorLogged = false;
    private boolean isDisabled;
    /* access modifiers changed from: private */
    public final AtomicLong lastNewSpanMillis;
    /* access modifiers changed from: private */
    public final int maxBufferedSpans;
    protected final Object mutex = new Object();
    private final Map<Format<?>, Propagator<?>> propagators;
    private boolean reportInProgress;
    private final Reporter.Builder reporter;
    private ReportingLoop reportingLoop;
    private Thread reportingThread;
    /* access modifiers changed from: private */
    public boolean resetClient;
    private final ScopeManager scopeManager;
    /* access modifiers changed from: private */
    public ArrayList<Span> spans;
    private final int verbosity;

    protected enum InternalLogLevel {
        DEBUG,
        INFO,
        WARN,
        ERROR
    }

    private static class ReportResult {
        private final int droppedSpans;
        private final boolean success;

        private ReportResult(int i, boolean z) {
            this.droppedSpans = i;
            this.success = z;
        }

        public static ReportResult Success() {
            return new ReportResult(0, true);
        }

        public static ReportResult Error(int i) {
            return new ReportResult(i, false);
        }

        public int getDroppedSpans() {
            return this.droppedSpans;
        }

        public boolean wasSuccessful() {
            return this.success;
        }
    }

    private class ReportingLoop implements Runnable {
        private int consecutiveFailures = 0;
        private long reportingIntervalMillis = 0;
        private Random rng = new Random(System.currentTimeMillis());

        ReportingLoop(long j) {
            this.reportingIntervalMillis = j;
        }

        public void run() {
            boolean z;
            AbstractTracer.this.debug("Reporting thread started");
            long computeNextReportMillis = computeNextReportMillis();
            long currentTimeMillis = System.currentTimeMillis() + 300000;
            while (!Thread.interrupted()) {
                long currentTimeMillis2 = System.currentTimeMillis();
                if (AbstractTracer.this.resetClient && currentTimeMillis2 >= currentTimeMillis) {
                    AbstractTracer.this.client.reconnect();
                    currentTimeMillis = System.currentTimeMillis() + 300000;
                }
                boolean z2 = true;
                if (AbstractTracer.this.spans.size() >= AbstractTracer.this.maxBufferedSpans / 2 || currentTimeMillis2 >= computeNextReportMillis) {
                    try {
                        z = ((Boolean) AbstractTracer.this.flushInternal(false).get()).booleanValue();
                    } catch (InterruptedException unused) {
                        AbstractTracer.this.warn("Future timed out");
                        Thread.currentThread().interrupt();
                        z = false;
                    }
                    if (!z) {
                        this.consecutiveFailures++;
                    } else {
                        this.consecutiveFailures = 0;
                    }
                    computeNextReportMillis = computeNextReportMillis();
                }
                if (AbstractTracer.this.unreportedSpanCount() <= 0) {
                    z2 = false;
                }
                long currentTimeMillis3 = System.currentTimeMillis() - AbstractTracer.this.lastNewSpanMillis.get();
                if ((!z2 || this.consecutiveFailures >= 2) && currentTimeMillis3 > AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS) {
                    AbstractTracer.this.doStopReporting();
                } else {
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException unused2) {
                        AbstractTracer.this.warn("Exception trying to sleep in reporting thread");
                        Thread.currentThread().interrupt();
                    }
                }
            }
            AbstractTracer.this.debug("Reporting thread stopped");
        }

        /* access modifiers changed from: 0000 */
        public long computeNextReportMillis() {
            double d;
            if (!AbstractTracer.this.clockState.isReady()) {
                d = 500.0d;
            } else {
                d = (double) this.reportingIntervalMillis;
            }
            double min = d * ((double) (Math.min(7, this.consecutiveFailures) + 1)) * ((this.rng.nextDouble() * 0.2d) + 0.9d);
            long currentTimeMillis = System.currentTimeMillis() + ((long) Math.ceil(min));
            AbstractTracer.this.debug(String.format("Next report: %d (%f) [%d]", new Object[]{Long.valueOf(currentTimeMillis), Double.valueOf(min), Integer.valueOf(AbstractTracer.this.clockState.activeSampleCount())}));
            return currentTimeMillis;
        }
    }

    /* access modifiers changed from: protected */
    public abstract SimpleFuture<Boolean> flushInternal(boolean z);

    /* access modifiers changed from: protected */
    public abstract void printLogToConsole(InternalLogLevel internalLogLevel, String str, Object obj);

    public AbstractTracer(Options options) {
        boolean z = false;
        this.scopeManager = options.scopeManager;
        this.verbosity = options.verbosity;
        this.maxBufferedSpans = options.maxBufferedSpans;
        this.lastNewSpanMillis = new AtomicLong(System.currentTimeMillis());
        this.spans = new ArrayList<>(this.maxBufferedSpans);
        if (options.useClockCorrection) {
            this.clockState = new ClockState();
        } else {
            this.clockState = new NoopClockState();
        }
        this.auth = Auth.newBuilder().setAccessToken(options.accessToken);
        this.reporter = Reporter.newBuilder().setReporterId(options.getGuid());
        this.resetClient = options.resetClient;
        this.clientMetrics = new ClientMetrics();
        this.client = CollectorClientProvider.provider().forOptions(this, options);
        if (this.client == null) {
            error("Exception creating client.");
            disable();
        } else {
            z = true;
        }
        for (Entry entry : options.tags.entrySet()) {
            addTracerTag((String) entry.getKey(), entry.getValue());
        }
        if (z && !options.disableReportingLoop) {
            this.reportingLoop = new ReportingLoop(options.maxReportingIntervalMillis);
        }
        this.propagators = options.propagators;
    }

    /* access modifiers changed from: private */
    public void doStopReporting() {
        synchronized (this) {
            if (this.reportingThread != null) {
                this.reportingThread.interrupt();
                this.reportingThread = null;
            }
        }
    }

    private void maybeStartReporting() {
        if (this.reportingThread == null) {
            this.reportingThread = new Thread(this.reportingLoop);
            this.reportingThread.setDaemon(true);
            this.reportingThread.start();
        }
    }

    private void disable() {
        info("Disabling client library");
        doStopReporting();
        synchronized (this.mutex) {
            if (this.client != null) {
                this.client.shutdown();
            }
            this.isDisabled = true;
            this.spans = new ArrayList<>(0);
        }
    }

    public boolean isDisabled() {
        boolean z;
        synchronized (this.mutex) {
            z = this.isDisabled;
        }
        return z;
    }

    public ScopeManager scopeManager() {
        return this.scopeManager;
    }

    public io.opentracing.Span activeSpan() {
        Scope active = this.scopeManager.active();
        if (active == null) {
            return null;
        }
        return active.span();
    }

    public SpanBuilder buildSpan(String str) {
        return new SpanBuilder(str, this);
    }

    public <C> void inject(SpanContext spanContext, Format<C> format, C c) {
        if (!(spanContext instanceof SpanContext)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unsupported SpanContext implementation: ");
            sb.append(spanContext.getClass());
            error(sb.toString());
            return;
        }
        SpanContext spanContext2 = (SpanContext) spanContext;
        if (!this.propagators.containsKey(format)) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Unsupported carrier type: ");
            sb2.append(c.getClass());
            info(sb2.toString());
            return;
        }
        ((Propagator) this.propagators.get(format)).inject(spanContext2, c);
    }

    public <C> SpanContext extract(Format<C> format, C c) {
        if (this.propagators.containsKey(format)) {
            return ((Propagator) this.propagators.get(format)).extract(c);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unsupported carrier type: ");
        sb.append(c.getClass());
        info(sb.toString());
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean sendReport(boolean z) {
        synchronized (this.mutex) {
            if (this.reportInProgress) {
                debug("Report in progress. Skipping.");
                return true;
            } else if (this.spans.size() != 0 || !this.clockState.isReady()) {
                this.reportInProgress = true;
                try {
                    ReportResult sendReportWorker = sendReportWorker(z);
                    this.clientMetrics.addSpansDropped(sendReportWorker.getDroppedSpans());
                    boolean wasSuccessful = sendReportWorker.wasSuccessful();
                    synchronized (this.mutex) {
                        this.reportInProgress = false;
                    }
                    return wasSuccessful;
                } catch (Throwable th) {
                    synchronized (this.mutex) {
                        this.reportInProgress = false;
                        throw th;
                    }
                }
            } else {
                debug("Skipping report. No new data.");
                return true;
            }
        }
    }

    /* access modifiers changed from: private */
    public int unreportedSpanCount() {
        int size;
        synchronized (this.mutex) {
            size = this.spans.size();
        }
        return size;
    }

    private ReportResult sendReportWorker(boolean z) {
        ArrayList<Span> arrayList;
        synchronized (this.mutex) {
            if (!this.clockState.isReady()) {
                if (!z) {
                    debug("Sending empty report to prime clock state");
                    arrayList = new ArrayList<>();
                }
            }
            arrayList = this.spans;
            this.spans = new ArrayList<>(this.maxBufferedSpans);
            debug(String.format("Sending report, %d spans", new Object[]{Integer.valueOf(arrayList.size())}));
        }
        ReportRequest build = ReportRequest.newBuilder().setReporter(this.reporter).setAuth(this.auth).addAllSpans(arrayList).setTimestampOffsetMicros(this.clockState.offsetMicros()).setInternalMetrics(this.clientMetrics.toInternalMetricsAndReset()).build();
        long nowMicrosApproximate = Util.nowMicrosApproximate();
        long nanoTime = System.nanoTime();
        ReportResponse reportResponse = null;
        CollectorClient collectorClient = this.client;
        if (collectorClient != null) {
            reportResponse = collectorClient.report(build);
        }
        if (reportResponse == null) {
            return ReportResult.Error(arrayList.size());
        }
        if (!reportResponse.getErrorsList().isEmpty()) {
            for (String error : reportResponse.getErrorsList()) {
                error("Collector response contained error: ", error);
            }
            return ReportResult.Error(arrayList.size());
        }
        if (!reportResponse.hasReceiveTimestamp() || !reportResponse.hasTransmitTimestamp()) {
            warn("Collector response did not include timing info");
        } else {
            this.clockState.addSample(nowMicrosApproximate, Util.protoTimeToEpochMicros(reportResponse.getReceiveTimestamp()), Util.protoTimeToEpochMicros(reportResponse.getTransmitTimestamp()), nowMicrosApproximate + ((System.nanoTime() - nanoTime) / 1000));
        }
        if (reportResponse.getCommandsCount() != 0) {
            for (Command disable : reportResponse.getCommandsList()) {
                if (disable.getDisable()) {
                    disable();
                }
            }
        }
        debug(String.format("Report sent successfully (%d spans)", new Object[]{Integer.valueOf(arrayList.size())}));
        return ReportResult.Success();
    }

    /* access modifiers changed from: 0000 */
    public void addSpan(Span span) {
        this.lastNewSpanMillis.set(System.currentTimeMillis());
        synchronized (this.mutex) {
            if (this.spans.size() >= this.maxBufferedSpans) {
                this.clientMetrics.addSpansDropped(1);
            } else {
                this.spans.add(span);
            }
            maybeStartReporting();
        }
    }

    /* access modifiers changed from: protected */
    public void addTracerTag(String str, Object obj) {
        StringBuilder sb = new StringBuilder();
        sb.append("Adding tracer tag: ");
        sb.append(str);
        sb.append(" => ");
        sb.append(obj);
        debug(sb.toString());
        if (obj instanceof String) {
            this.reporter.addTags(KeyValue.newBuilder().setKey(str).setStringValue((String) obj));
        } else if (obj instanceof Boolean) {
            this.reporter.addTags(KeyValue.newBuilder().setKey(str).setBoolValue(((Boolean) obj).booleanValue()));
        } else if (!(obj instanceof Number)) {
            this.reporter.addTags(KeyValue.newBuilder().setKey(str).setStringValue(obj.toString()));
        } else if ((obj instanceof Long) || (obj instanceof Integer)) {
            this.reporter.addTags(KeyValue.newBuilder().setKey(str).setIntValue(((Number) obj).longValue()));
        } else if ((obj instanceof Double) || (obj instanceof Float)) {
            this.reporter.addTags(KeyValue.newBuilder().setKey(str).setDoubleValue(((Number) obj).doubleValue()));
        } else {
            this.reporter.addTags(KeyValue.newBuilder().setKey(str).setStringValue(obj.toString()));
        }
    }

    /* access modifiers changed from: protected */
    public void debug(String str) {
        debug(str, null);
    }

    /* access modifiers changed from: protected */
    public void debug(String str, Object obj) {
        if (this.verbosity >= 4) {
            printLogToConsole(InternalLogLevel.DEBUG, str, obj);
        }
    }

    /* access modifiers changed from: protected */
    public void info(String str) {
        info(str, null);
    }

    /* access modifiers changed from: protected */
    public void info(String str, Object obj) {
        if (this.verbosity >= 3) {
            printLogToConsole(InternalLogLevel.INFO, str, obj);
        }
    }

    /* access modifiers changed from: protected */
    public void warn(String str) {
        warn(str, null);
    }

    /* access modifiers changed from: protected */
    public void warn(String str, Object obj) {
        if (this.verbosity >= 3) {
            printLogToConsole(InternalLogLevel.WARN, str, obj);
        }
    }

    /* access modifiers changed from: protected */
    public void error(String str) {
        error(str, null);
    }

    /* access modifiers changed from: protected */
    public void error(String str, Object obj) {
        int i = this.verbosity;
        if (i >= 1) {
            if (i != 1 || !this.firstErrorLogged) {
                this.firstErrorLogged = true;
                printLogToConsole(InternalLogLevel.ERROR, str, obj);
            }
        }
    }
}
