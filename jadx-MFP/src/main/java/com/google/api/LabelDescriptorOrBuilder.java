package com.google.api;

import com.google.api.LabelDescriptor.ValueType;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface LabelDescriptorOrBuilder extends MessageOrBuilder {
    String getDescription();

    ByteString getDescriptionBytes();

    String getKey();

    ByteString getKeyBytes();

    ValueType getValueType();

    int getValueTypeValue();
}
