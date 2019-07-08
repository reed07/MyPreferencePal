package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.Map;

public interface MetricRuleOrBuilder extends MessageOrBuilder {
    boolean containsMetricCosts(String str);

    @Deprecated
    Map<String, Long> getMetricCosts();

    int getMetricCostsCount();

    Map<String, Long> getMetricCostsMap();

    long getMetricCostsOrDefault(String str, long j);

    long getMetricCostsOrThrow(String str);

    String getSelector();

    ByteString getSelectorBytes();
}
