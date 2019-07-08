package com.google.cloud.audit;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface RequestMetadataOrBuilder extends MessageOrBuilder {
    String getCallerIp();

    ByteString getCallerIpBytes();

    String getCallerSuppliedUserAgent();

    ByteString getCallerSuppliedUserAgentBytes();
}
