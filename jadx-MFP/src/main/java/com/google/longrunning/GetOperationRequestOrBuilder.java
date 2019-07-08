package com.google.longrunning;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface GetOperationRequestOrBuilder extends MessageOrBuilder {
    String getName();

    ByteString getNameBytes();
}
