package com.google.api;

import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface QuotaOrBuilder extends MessageOrBuilder {
    QuotaLimit getLimits(int i);

    int getLimitsCount();

    List<QuotaLimit> getLimitsList();

    QuotaLimitOrBuilder getLimitsOrBuilder(int i);

    List<? extends QuotaLimitOrBuilder> getLimitsOrBuilderList();

    MetricRule getMetricRules(int i);

    int getMetricRulesCount();

    List<MetricRule> getMetricRulesList();

    MetricRuleOrBuilder getMetricRulesOrBuilder(int i);

    List<? extends MetricRuleOrBuilder> getMetricRulesOrBuilderList();
}
