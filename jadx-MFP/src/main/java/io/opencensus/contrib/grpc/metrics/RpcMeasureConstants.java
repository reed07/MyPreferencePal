package io.opencensus.contrib.grpc.metrics;

import com.facebook.appevents.AppEventsConstants;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import io.opencensus.stats.Measure.MeasureDouble;
import io.opencensus.stats.Measure.MeasureLong;
import io.opencensus.tags.TagKey;

public final class RpcMeasureConstants {
    public static final TagKey GRPC_CLIENT_METHOD = TagKey.create("grpc_client_method");
    public static final MeasureDouble GRPC_CLIENT_RECEIVED_BYTES_PER_RPC = MeasureDouble.create("grpc.io/client/received_bytes_per_rpc", "Total bytes received across all response messages per RPC", "By");
    public static final MeasureLong GRPC_CLIENT_RECEIVED_MESSAGES_PER_RPC = MeasureLong.create("grpc.io/client/received_messages_per_rpc", "Number of response messages received per RPC", AppEventsConstants.EVENT_PARAM_VALUE_YES);
    public static final MeasureDouble GRPC_CLIENT_ROUNDTRIP_LATENCY = MeasureDouble.create("grpc.io/client/roundtrip_latency", "Time between first byte of request sent to last byte of response received, or terminal error.", "ms");
    public static final MeasureDouble GRPC_CLIENT_SENT_BYTES_PER_RPC = MeasureDouble.create("grpc.io/client/sent_bytes_per_rpc", "Total bytes sent across all request messages per RPC", "By");
    public static final MeasureLong GRPC_CLIENT_SENT_MESSAGES_PER_RPC = MeasureLong.create("grpc.io/client/sent_messages_per_rpc", "Number of messages sent in the RPC", AppEventsConstants.EVENT_PARAM_VALUE_YES);
    public static final MeasureDouble GRPC_CLIENT_SERVER_LATENCY = MeasureDouble.create("grpc.io/client/server_latency", "Server latency in msecs", "ms");
    public static final MeasureLong GRPC_CLIENT_STARTED_RPCS = MeasureLong.create("grpc.io/client/started_rpcs", "Number of started client RPCs.", AppEventsConstants.EVENT_PARAM_VALUE_YES);
    public static final TagKey GRPC_CLIENT_STATUS = TagKey.create("grpc_client_status");
    public static final TagKey GRPC_SERVER_METHOD = TagKey.create("grpc_server_method");
    public static final MeasureDouble GRPC_SERVER_RECEIVED_BYTES_PER_RPC = MeasureDouble.create("grpc.io/server/received_bytes_per_rpc", "Total bytes received across all messages per RPC", "By");
    public static final MeasureLong GRPC_SERVER_RECEIVED_MESSAGES_PER_RPC = MeasureLong.create("grpc.io/server/received_messages_per_rpc", "Number of messages received in each RPC", AppEventsConstants.EVENT_PARAM_VALUE_YES);
    public static final MeasureDouble GRPC_SERVER_SENT_BYTES_PER_RPC = MeasureDouble.create("grpc.io/server/sent_bytes_per_rpc", "Total bytes sent across all response messages per RPC", "By");
    public static final MeasureLong GRPC_SERVER_SENT_MESSAGES_PER_RPC = MeasureLong.create("grpc.io/server/sent_messages_per_rpc", "Number of messages sent in each RPC", AppEventsConstants.EVENT_PARAM_VALUE_YES);
    public static final MeasureDouble GRPC_SERVER_SERVER_LATENCY = MeasureDouble.create("grpc.io/server/server_latency", "Time between first byte of request received to last byte of response sent, or terminal error.", "ms");
    public static final MeasureLong GRPC_SERVER_STARTED_RPCS = MeasureLong.create("grpc.io/server/started_rpcs", "Number of started server RPCs.", AppEventsConstants.EVENT_PARAM_VALUE_YES);
    public static final TagKey GRPC_SERVER_STATUS = TagKey.create("grpc_server_status");
    @Deprecated
    public static final MeasureLong RPC_CLIENT_ERROR_COUNT = MeasureLong.create("grpc.io/client/error_count", "RPC Errors", AppEventsConstants.EVENT_PARAM_VALUE_YES);
    @Deprecated
    public static final MeasureLong RPC_CLIENT_FINISHED_COUNT = MeasureLong.create("grpc.io/client/finished_count", "Number of client RPCs (streams) finished", AppEventsConstants.EVENT_PARAM_VALUE_YES);
    @Deprecated
    public static final MeasureDouble RPC_CLIENT_REQUEST_BYTES = MeasureDouble.create("grpc.io/client/request_bytes", "Request bytes", "By");
    @Deprecated
    public static final MeasureLong RPC_CLIENT_REQUEST_COUNT = MeasureLong.create("grpc.io/client/request_count", "Number of client RPC request messages", AppEventsConstants.EVENT_PARAM_VALUE_YES);
    @Deprecated
    public static final MeasureDouble RPC_CLIENT_RESPONSE_BYTES = MeasureDouble.create("grpc.io/client/response_bytes", "Response bytes", "By");
    @Deprecated
    public static final MeasureLong RPC_CLIENT_RESPONSE_COUNT = MeasureLong.create("grpc.io/client/response_count", "Number of client RPC response messages", AppEventsConstants.EVENT_PARAM_VALUE_YES);
    @Deprecated
    public static final MeasureDouble RPC_CLIENT_ROUNDTRIP_LATENCY = MeasureDouble.create("grpc.io/client/roundtrip_latency", "RPC roundtrip latency msec", "ms");
    @Deprecated
    public static final MeasureDouble RPC_CLIENT_SERVER_ELAPSED_TIME = MeasureDouble.create("grpc.io/client/server_elapsed_time", "Server elapsed time in msecs", "ms");
    @Deprecated
    public static final MeasureLong RPC_CLIENT_STARTED_COUNT = MeasureLong.create("grpc.io/client/started_count", "Number of client RPCs (streams) started", AppEventsConstants.EVENT_PARAM_VALUE_YES);
    @Deprecated
    public static final MeasureDouble RPC_CLIENT_UNCOMPRESSED_REQUEST_BYTES = MeasureDouble.create("grpc.io/client/uncompressed_request_bytes", "Uncompressed Request bytes", "By");
    @Deprecated
    public static final MeasureDouble RPC_CLIENT_UNCOMPRESSED_RESPONSE_BYTES = MeasureDouble.create("grpc.io/client/uncompressed_response_bytes", "Uncompressed Response bytes", "By");
    @Deprecated
    public static final TagKey RPC_METHOD = TagKey.create(Param.METHOD);
    @Deprecated
    public static final MeasureLong RPC_SERVER_ERROR_COUNT = MeasureLong.create("grpc.io/server/error_count", "RPC Errors", AppEventsConstants.EVENT_PARAM_VALUE_YES);
    @Deprecated
    public static final MeasureLong RPC_SERVER_FINISHED_COUNT = MeasureLong.create("grpc.io/server/finished_count", "Number of server RPCs (streams) finished", AppEventsConstants.EVENT_PARAM_VALUE_YES);
    @Deprecated
    public static final MeasureDouble RPC_SERVER_REQUEST_BYTES = MeasureDouble.create("grpc.io/server/request_bytes", "Request bytes", "By");
    @Deprecated
    public static final MeasureLong RPC_SERVER_REQUEST_COUNT = MeasureLong.create("grpc.io/server/request_count", "Number of server RPC request messages", AppEventsConstants.EVENT_PARAM_VALUE_YES);
    @Deprecated
    public static final MeasureDouble RPC_SERVER_RESPONSE_BYTES = MeasureDouble.create("grpc.io/server/response_bytes", "Response bytes", "By");
    @Deprecated
    public static final MeasureLong RPC_SERVER_RESPONSE_COUNT = MeasureLong.create("grpc.io/server/response_count", "Number of server RPC response messages", AppEventsConstants.EVENT_PARAM_VALUE_YES);
    @Deprecated
    public static final MeasureDouble RPC_SERVER_SERVER_ELAPSED_TIME = MeasureDouble.create("grpc.io/server/server_elapsed_time", "Server elapsed time in msecs", "ms");
    @Deprecated
    public static final MeasureDouble RPC_SERVER_SERVER_LATENCY = MeasureDouble.create("grpc.io/server/server_latency", "Latency in msecs", "ms");
    @Deprecated
    public static final MeasureLong RPC_SERVER_STARTED_COUNT = MeasureLong.create("grpc.io/server/started_count", "Number of server RPCs (streams) started", AppEventsConstants.EVENT_PARAM_VALUE_YES);
    @Deprecated
    public static final MeasureDouble RPC_SERVER_UNCOMPRESSED_REQUEST_BYTES = MeasureDouble.create("grpc.io/server/uncompressed_request_bytes", "Uncompressed Request bytes", "By");
    @Deprecated
    public static final MeasureDouble RPC_SERVER_UNCOMPRESSED_RESPONSE_BYTES = MeasureDouble.create("grpc.io/server/uncompressed_response_bytes", "Uncompressed Response bytes", "By");
    @Deprecated
    public static final TagKey RPC_STATUS = TagKey.create("canonical_status");

    private RpcMeasureConstants() {
    }
}
