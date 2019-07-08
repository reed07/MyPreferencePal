package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface BackendRuleOrBuilder extends MessageOrBuilder {
    String getAddress();

    ByteString getAddressBytes();

    double getDeadline();

    double getMinDeadline();

    String getSelector();

    ByteString getSelectorBytes();
}
