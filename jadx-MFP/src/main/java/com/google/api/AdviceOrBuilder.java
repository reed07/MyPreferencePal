package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface AdviceOrBuilder extends MessageOrBuilder {
    String getDescription();

    ByteString getDescriptionBytes();
}
