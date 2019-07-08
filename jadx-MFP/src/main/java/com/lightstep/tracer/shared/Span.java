package com.lightstep.tracer.shared;

import com.lightstep.tracer.grpc.KeyValue;
import com.lightstep.tracer.grpc.Log;
import com.lightstep.tracer.grpc.Span.Builder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Span implements io.opentracing.Span {
    private SpanContext context;
    private final Builder grpcSpan;
    private final Object mutex = new Object();
    private final long startTimestampRelativeNanos;
    private final AbstractTracer tracer;

    Span(AbstractTracer abstractTracer, SpanContext spanContext, Builder builder, long j) {
        this.context = spanContext;
        this.tracer = abstractTracer;
        this.grpcSpan = builder;
        this.startTimestampRelativeNanos = j;
    }

    public SpanContext context() {
        return this.context;
    }

    public void finish() {
        finish(nowMicros());
    }

    public void finish(long j) {
        synchronized (this.mutex) {
            this.grpcSpan.setDurationMicros(durationMicros(j));
            this.tracer.addSpan(this.grpcSpan.build());
        }
    }

    public Span setTag(String str, String str2) {
        if (str == null || str2 == null) {
            AbstractTracer abstractTracer = this.tracer;
            StringBuilder sb = new StringBuilder();
            sb.append("key (");
            sb.append(str);
            sb.append(") or value (");
            sb.append(str2);
            sb.append(") is null, ignoring");
            abstractTracer.debug(sb.toString());
            return this;
        }
        synchronized (this.mutex) {
            this.grpcSpan.addTags(KeyValue.newBuilder().setKey(str).setStringValue(str2));
        }
        return this;
    }

    public Span setTag(String str, boolean z) {
        if (str == null) {
            this.tracer.debug("key is null, ignoring");
            return this;
        }
        synchronized (this.mutex) {
            this.grpcSpan.addTags(KeyValue.newBuilder().setKey(str).setBoolValue(z));
        }
        return this;
    }

    public Span setTag(String str, Number number) {
        if (str == null || number == null) {
            AbstractTracer abstractTracer = this.tracer;
            StringBuilder sb = new StringBuilder();
            sb.append("key (");
            sb.append(str);
            sb.append(") or value (");
            sb.append(number);
            sb.append(") is null, ignoring");
            abstractTracer.debug(sb.toString());
            return this;
        }
        synchronized (this.mutex) {
            if (!(number instanceof Long)) {
                if (!(number instanceof Integer)) {
                    if (!(number instanceof Double)) {
                        if (!(number instanceof Float)) {
                            this.grpcSpan.addTags(KeyValue.newBuilder().setKey(str).setStringValue(number.toString()));
                        }
                    }
                    this.grpcSpan.addTags(KeyValue.newBuilder().setKey(str).setDoubleValue(number.doubleValue()));
                }
            }
            this.grpcSpan.addTags(KeyValue.newBuilder().setKey(str).setIntValue(number.longValue()));
        }
        return this;
    }

    public synchronized String getBaggageItem(String str) {
        return this.context.getBaggageItem(str);
    }

    public synchronized Span setBaggageItem(String str, String str2) {
        this.context = this.context.withBaggageItem(str, str2);
        return this;
    }

    public synchronized Span setOperationName(String str) {
        this.grpcSpan.setOperationName(str);
        return this;
    }

    public final Span log(Map<String, ?> map) {
        return log(nowMicros(), map);
    }

    public final Span log(long j, Map<String, ?> map) {
        Log.Builder timestamp = Log.newBuilder().setTimestamp(Util.epochTimeMicrosToProtoTime(j));
        for (Entry entry : map.entrySet()) {
            Object value = entry.getValue();
            KeyValue.Builder key = KeyValue.newBuilder().setKey((String) entry.getKey());
            if (value instanceof String) {
                key.setStringValue((String) value);
            } else if (value instanceof Number) {
                if ((value instanceof Long) || (value instanceof Integer)) {
                    key.setIntValue(((Number) value).longValue());
                } else if ((value instanceof Double) || (value instanceof Float)) {
                    key.setDoubleValue(((Number) value).doubleValue());
                } else {
                    key.setStringValue(value.toString());
                }
            } else if (value instanceof Boolean) {
                key.setBoolValue(((Boolean) value).booleanValue());
            } else {
                key.setJsonValue(stringToJSONValue(value.toString()));
            }
            timestamp.addFields(key.build());
        }
        synchronized (this.mutex) {
            this.grpcSpan.addLogs(timestamp.build());
        }
        return this;
    }

    public Span log(String str) {
        return log(nowMicros(), str, null);
    }

    public Span log(long j, String str) {
        return log(j, str, null);
    }

    private Span log(long j, String str, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("message", str);
        if (obj != null) {
            hashMap.put("payload", obj);
        }
        return log(j, (Map<String, ?>) hashMap);
    }

    private long nowMicros() {
        if (this.startTimestampRelativeNanos <= 0) {
            return System.currentTimeMillis() * 1000;
        }
        return Util.protoTimeToEpochMicros(this.grpcSpan.getStartTimestamp()) + ((System.nanoTime() - this.startTimestampRelativeNanos) / 1000);
    }

    private long durationMicros(long j) {
        return j - Util.protoTimeToEpochMicros(this.grpcSpan.getStartTimestamp());
    }

    static String stringToJSONValue(String str) {
        StringBuilder sb = new StringBuilder(str.length() + 2);
        sb.append("\"");
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt != '\"' && charAt != '/' && charAt != '\\') {
                switch (charAt) {
                    case 8:
                        sb.append("\\b");
                        break;
                    case 9:
                        sb.append("\\t");
                        break;
                    case 10:
                        sb.append("\\n");
                        break;
                    default:
                        switch (charAt) {
                            case 12:
                                sb.append("\\f");
                                break;
                            case 13:
                                sb.append("\\r");
                                break;
                            default:
                                if (charAt > 31) {
                                    sb.append(charAt);
                                    break;
                                } else {
                                    sb.append(String.format("\\u%04x", new Object[]{Integer.valueOf(charAt)}));
                                    break;
                                }
                        }
                }
            } else {
                sb.append('\\');
                sb.append(charAt);
            }
        }
        sb.append("\"");
        return sb.toString();
    }
}
