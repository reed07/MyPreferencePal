package com.google.rpc;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface RequestInfoOrBuilder extends MessageOrBuilder {
    String getRequestId();

    ByteString getRequestIdBytes();

    String getServingData();

    ByteString getServingDataBytes();
}
