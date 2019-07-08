package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface ControlOrBuilder extends MessageOrBuilder {
    String getEnvironment();

    ByteString getEnvironmentBytes();
}
