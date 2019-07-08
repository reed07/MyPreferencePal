package com.google.longrunning;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface CancelOperationRequestOrBuilder extends MessageOrBuilder {
    String getName();

    ByteString getNameBytes();
}
