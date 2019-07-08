package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface CustomHttpPatternOrBuilder extends MessageOrBuilder {
    String getKind();

    ByteString getKindBytes();

    String getPath();

    ByteString getPathBytes();
}
