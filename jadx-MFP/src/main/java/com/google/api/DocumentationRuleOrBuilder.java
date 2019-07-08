package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface DocumentationRuleOrBuilder extends MessageOrBuilder {
    String getDeprecationDescription();

    ByteString getDeprecationDescriptionBytes();

    String getDescription();

    ByteString getDescriptionBytes();

    String getSelector();

    ByteString getSelectorBytes();
}
