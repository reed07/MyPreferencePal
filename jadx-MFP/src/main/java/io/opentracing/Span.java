package io.opentracing;

import java.util.Map;

public interface Span {
    SpanContext context();

    void finish();

    void finish(long j);

    String getBaggageItem(String str);

    Span log(long j, String str);

    Span log(long j, Map<String, ?> map);

    Span log(String str);

    Span log(Map<String, ?> map);

    Span setBaggageItem(String str, String str2);

    Span setOperationName(String str);

    Span setTag(String str, Number number);

    Span setTag(String str, String str2);

    Span setTag(String str, boolean z);
}
