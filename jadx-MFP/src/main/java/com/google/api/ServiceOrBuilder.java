package com.google.api;

import com.google.protobuf.Api;
import com.google.protobuf.ApiOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.Enum;
import com.google.protobuf.EnumOrBuilder;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Type;
import com.google.protobuf.TypeOrBuilder;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;
import java.util.List;

public interface ServiceOrBuilder extends MessageOrBuilder {
    Api getApis(int i);

    int getApisCount();

    List<Api> getApisList();

    ApiOrBuilder getApisOrBuilder(int i);

    List<? extends ApiOrBuilder> getApisOrBuilderList();

    Authentication getAuthentication();

    AuthenticationOrBuilder getAuthenticationOrBuilder();

    Backend getBackend();

    BackendOrBuilder getBackendOrBuilder();

    Billing getBilling();

    BillingOrBuilder getBillingOrBuilder();

    UInt32Value getConfigVersion();

    UInt32ValueOrBuilder getConfigVersionOrBuilder();

    Context getContext();

    ContextOrBuilder getContextOrBuilder();

    Control getControl();

    ControlOrBuilder getControlOrBuilder();

    Documentation getDocumentation();

    DocumentationOrBuilder getDocumentationOrBuilder();

    Endpoint getEndpoints(int i);

    int getEndpointsCount();

    List<Endpoint> getEndpointsList();

    EndpointOrBuilder getEndpointsOrBuilder(int i);

    List<? extends EndpointOrBuilder> getEndpointsOrBuilderList();

    Enum getEnums(int i);

    int getEnumsCount();

    List<Enum> getEnumsList();

    EnumOrBuilder getEnumsOrBuilder(int i);

    List<? extends EnumOrBuilder> getEnumsOrBuilderList();

    Experimental getExperimental();

    ExperimentalOrBuilder getExperimentalOrBuilder();

    Http getHttp();

    HttpOrBuilder getHttpOrBuilder();

    String getId();

    ByteString getIdBytes();

    Logging getLogging();

    LoggingOrBuilder getLoggingOrBuilder();

    LogDescriptor getLogs(int i);

    int getLogsCount();

    List<LogDescriptor> getLogsList();

    LogDescriptorOrBuilder getLogsOrBuilder(int i);

    List<? extends LogDescriptorOrBuilder> getLogsOrBuilderList();

    MetricDescriptor getMetrics(int i);

    int getMetricsCount();

    List<MetricDescriptor> getMetricsList();

    MetricDescriptorOrBuilder getMetricsOrBuilder(int i);

    List<? extends MetricDescriptorOrBuilder> getMetricsOrBuilderList();

    MonitoredResourceDescriptor getMonitoredResources(int i);

    int getMonitoredResourcesCount();

    List<MonitoredResourceDescriptor> getMonitoredResourcesList();

    MonitoredResourceDescriptorOrBuilder getMonitoredResourcesOrBuilder(int i);

    List<? extends MonitoredResourceDescriptorOrBuilder> getMonitoredResourcesOrBuilderList();

    Monitoring getMonitoring();

    MonitoringOrBuilder getMonitoringOrBuilder();

    String getName();

    ByteString getNameBytes();

    String getProducerProjectId();

    ByteString getProducerProjectIdBytes();

    Quota getQuota();

    QuotaOrBuilder getQuotaOrBuilder();

    SourceInfo getSourceInfo();

    SourceInfoOrBuilder getSourceInfoOrBuilder();

    SystemParameters getSystemParameters();

    SystemParametersOrBuilder getSystemParametersOrBuilder();

    String getTitle();

    ByteString getTitleBytes();

    Type getTypes(int i);

    int getTypesCount();

    List<Type> getTypesList();

    TypeOrBuilder getTypesOrBuilder(int i);

    List<? extends TypeOrBuilder> getTypesOrBuilderList();

    Usage getUsage();

    UsageOrBuilder getUsageOrBuilder();

    boolean hasAuthentication();

    boolean hasBackend();

    boolean hasBilling();

    boolean hasConfigVersion();

    boolean hasContext();

    boolean hasControl();

    boolean hasDocumentation();

    boolean hasExperimental();

    boolean hasHttp();

    boolean hasLogging();

    boolean hasMonitoring();

    boolean hasQuota();

    boolean hasSourceInfo();

    boolean hasSystemParameters();

    boolean hasUsage();
}
