package com.lightstep.tracer.grpc;

import com.google.api.AnnotationsProto;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import com.google.protobuf.TimestampProto;

public final class Collector {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    static final Descriptor internal_static_lightstep_collector_Auth_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(8));
    static final FieldAccessorTable internal_static_lightstep_collector_Auth_fieldAccessorTable = new FieldAccessorTable(internal_static_lightstep_collector_Auth_descriptor, new String[]{"AccessToken"});
    static final Descriptor internal_static_lightstep_collector_Command_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(10));
    static final FieldAccessorTable internal_static_lightstep_collector_Command_fieldAccessorTable = new FieldAccessorTable(internal_static_lightstep_collector_Command_descriptor, new String[]{"Disable"});
    static final Descriptor internal_static_lightstep_collector_InternalMetrics_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(7));
    static final FieldAccessorTable internal_static_lightstep_collector_InternalMetrics_fieldAccessorTable = new FieldAccessorTable(internal_static_lightstep_collector_InternalMetrics_descriptor, new String[]{"StartTimestamp", "DurationMicros", "Logs", "Counts", "Gauges"});
    static final Descriptor internal_static_lightstep_collector_KeyValue_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(1));
    static final FieldAccessorTable internal_static_lightstep_collector_KeyValue_fieldAccessorTable = new FieldAccessorTable(internal_static_lightstep_collector_KeyValue_descriptor, new String[]{"Key", "StringValue", "IntValue", "DoubleValue", "BoolValue", "JsonValue", "Value"});
    static final Descriptor internal_static_lightstep_collector_Log_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(2));
    static final FieldAccessorTable internal_static_lightstep_collector_Log_fieldAccessorTable = new FieldAccessorTable(internal_static_lightstep_collector_Log_descriptor, new String[]{"Timestamp", "Fields"});
    static final Descriptor internal_static_lightstep_collector_MetricsSample_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(6));
    static final FieldAccessorTable internal_static_lightstep_collector_MetricsSample_fieldAccessorTable = new FieldAccessorTable(internal_static_lightstep_collector_MetricsSample_descriptor, new String[]{"Name", "IntValue", "DoubleValue", "Value"});
    static final Descriptor internal_static_lightstep_collector_Reference_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(3));
    static final FieldAccessorTable internal_static_lightstep_collector_Reference_fieldAccessorTable = new FieldAccessorTable(internal_static_lightstep_collector_Reference_descriptor, new String[]{"Relationship", "SpanContext"});
    static final Descriptor internal_static_lightstep_collector_ReportRequest_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(9));
    static final FieldAccessorTable internal_static_lightstep_collector_ReportRequest_fieldAccessorTable = new FieldAccessorTable(internal_static_lightstep_collector_ReportRequest_descriptor, new String[]{"Reporter", "Auth", "Spans", "TimestampOffsetMicros", "InternalMetrics"});
    static final Descriptor internal_static_lightstep_collector_ReportResponse_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(11));
    static final FieldAccessorTable internal_static_lightstep_collector_ReportResponse_fieldAccessorTable = new FieldAccessorTable(internal_static_lightstep_collector_ReportResponse_descriptor, new String[]{"Commands", "ReceiveTimestamp", "TransmitTimestamp", "Errors", "Warnings", "Infos"});
    static final Descriptor internal_static_lightstep_collector_Reporter_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(5));
    static final FieldAccessorTable internal_static_lightstep_collector_Reporter_fieldAccessorTable = new FieldAccessorTable(internal_static_lightstep_collector_Reporter_descriptor, new String[]{"ReporterId", "Tags"});
    static final Descriptor internal_static_lightstep_collector_SpanContext_BaggageEntry_descriptor = ((Descriptor) internal_static_lightstep_collector_SpanContext_descriptor.getNestedTypes().get(0));
    static final FieldAccessorTable internal_static_lightstep_collector_SpanContext_BaggageEntry_fieldAccessorTable = new FieldAccessorTable(internal_static_lightstep_collector_SpanContext_BaggageEntry_descriptor, new String[]{"Key", "Value"});
    static final Descriptor internal_static_lightstep_collector_SpanContext_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    static final FieldAccessorTable internal_static_lightstep_collector_SpanContext_fieldAccessorTable = new FieldAccessorTable(internal_static_lightstep_collector_SpanContext_descriptor, new String[]{"TraceId", "SpanId", "Baggage"});
    static final Descriptor internal_static_lightstep_collector_Span_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(4));
    static final FieldAccessorTable internal_static_lightstep_collector_Span_fieldAccessorTable = new FieldAccessorTable(internal_static_lightstep_collector_Span_descriptor, new String[]{"SpanContext", "OperationName", "References", "StartTimestamp", "DurationMicros", "Tags", "Logs"});

    private Collector() {
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor[] fileDescriptorArr = {TimestampProto.getDescriptor(), AnnotationsProto.getDescriptor()};
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u000fcollector.proto\u0012\u0013lightstep.collector\u001a\u001fgoogle/protobuf/timestamp.proto\u001a\u001cgoogle/api/annotations.proto\" \u0001\n\u000bSpanContext\u0012\u0010\n\btrace_id\u0018\u0001 \u0001(\u0004\u0012\u000f\n\u0007span_id\u0018\u0002 \u0001(\u0004\u0012>\n\u0007baggage\u0018\u0003 \u0003(\u000b2-.lightstep.collector.SpanContext.BaggageEntry\u001a.\n\fBaggageEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012\r\n\u0005value\u0018\u0002 \u0001(\t:\u00028\u0001\"\u0001\n\bKeyValue\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012\u0016\n\fstring_value\u0018\u0002 \u0001(\tH\u0000\u0012\u0013\n\tint_value\u0018\u0003 \u0001(\u0003H\u0000\u0012\u0016\n\fdouble_value\u0018\u0004 \u0001(\u0001H\u0000\u0012\u0014\n\nbool_value\u0018\u0005 \u0001(\bH\u0000\u0012\u0014\n\njson_value\u0018\u0006 \u0001(\tH\u0000B\u0007\n\u0005value\"c\n\u0003Log\u0012-\n\ttimestamp\u0018\u0001 \u0001(\u000b2\u001a.google.protobuf.Timestamp\u0012-\n\u0006fields\u0018\u0002 \u0003(\u000b2\u001d.lightstep.collector.KeyValue\"¶\u0001\n\tReference\u0012A\n\frelationship\u0018\u0001 \u0001(\u000e2+.lightstep.collector.Reference.Relationship\u00126\n\fspan_context\u0018\u0002 \u0001(\u000b2 .lightstep.collector.SpanContext\".\n\fRelationship\u0012\f\n\bCHILD_OF\u0010\u0000\u0012\u0010\n\fFOLLOWS_FROM\u0010\u0001\"­\u0002\n\u0004Span\u00126\n\fspan_context\u0018\u0001 \u0001(\u000b2 .lightstep.collector.SpanContext\u0012\u0016\n\u000eoperation_name\u0018\u0002 \u0001(\t\u00122\n\nreferences\u0018\u0003 \u0003(\u000b2\u001e.lightstep.collector.Reference\u00123\n\u000fstart_timestamp\u0018\u0004 \u0001(\u000b2\u001a.google.protobuf.Timestamp\u0012\u0017\n\u000fduration_micros\u0018\u0005 \u0001(\u0004\u0012+\n\u0004tags\u0018\u0006 \u0003(\u000b2\u001d.lightstep.collector.KeyValue\u0012&\n\u0004logs\u0018\u0007 \u0003(\u000b2\u0018.lightstep.collector.Log\"L\n\bReporter\u0012\u0013\n\u000breporter_id\u0018\u0001 \u0001(\u0004\u0012+\n\u0004tags\u0018\u0004 \u0003(\u000b2\u001d.lightstep.collector.KeyValue\"S\n\rMetricsSample\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u0013\n\tint_value\u0018\u0002 \u0001(\u0003H\u0000\u0012\u0016\n\fdouble_value\u0018\u0003 \u0001(\u0001H\u0000B\u0007\n\u0005value\"ï\u0001\n\u000fInternalMetrics\u00123\n\u000fstart_timestamp\u0018\u0001 \u0001(\u000b2\u001a.google.protobuf.Timestamp\u0012\u0017\n\u000fduration_micros\u0018\u0002 \u0001(\u0004\u0012&\n\u0004logs\u0018\u0003 \u0003(\u000b2\u0018.lightstep.collector.Log\u00122\n\u0006counts\u0018\u0004 \u0003(\u000b2\".lightstep.collector.MetricsSample\u00122\n\u0006gauges\u0018\u0005 \u0003(\u000b2\".lightstep.collector.MetricsSample\"\u001c\n\u0004Auth\u0012\u0014\n\faccess_token\u0018\u0001 \u0001(\t\"ô\u0001\n\rReportRequest\u0012/\n\breporter\u0018\u0001 \u0001(\u000b2\u001d.lightstep.collector.Reporter\u0012'\n\u0004auth\u0018\u0002 \u0001(\u000b2\u0019.lightstep.collector.Auth\u0012(\n\u0005spans\u0018\u0003 \u0003(\u000b2\u0019.lightstep.collector.Span\u0012\u001f\n\u0017timestamp_offset_micros\u0018\u0005 \u0001(\u0003\u0012>\n\u0010internal_metrics\u0018\u0006 \u0001(\u000b2$.lightstep.collector.InternalMetrics\"\u001a\n\u0007Command\u0012\u000f\n\u0007disable\u0018\u0001 \u0001(\b\"à\u0001\n\u000eReportResponse\u0012.\n\bcommands\u0018\u0001 \u0003(\u000b2\u001c.lightstep.collector.Command\u00125\n\u0011receive_timestamp\u0018\u0002 \u0001(\u000b2\u001a.google.protobuf.Timestamp\u00126\n\u0012transmit_timestamp\u0018\u0003 \u0001(\u000b2\u001a.google.protobuf.Timestamp\u0012\u000e\n\u0006errors\u0018\u0004 \u0003(\t\u0012\u0010\n\bwarnings\u0018\u0005 \u0003(\t\u0012\r\n\u0005infos\u0018\u0006 \u0003(\t2\u0001\n\u0010CollectorService\u0012\u0001\n\u0006Report\u0012\".lightstep.collector.ReportRequest\u001a#.lightstep.collector.ReportResponse\"-Óä\u0002'\"\u000f/api/v2/reports:\u0001*Z\u0011\u0012\u000f/api/v2/reportsB1\n\u0019com.lightstep.tracer.grpcP\u0001Z\u000bcollectorpb¢\u0002\u0004LSPBb\u0006proto3"}, fileDescriptorArr, new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                Collector.descriptor = fileDescriptor;
                return null;
            }
        });
        ExtensionRegistry newInstance = ExtensionRegistry.newInstance();
        newInstance.add(AnnotationsProto.http);
        FileDescriptor.internalUpdateFileDescriptor(descriptor, newInstance);
        TimestampProto.getDescriptor();
        AnnotationsProto.getDescriptor();
    }
}
