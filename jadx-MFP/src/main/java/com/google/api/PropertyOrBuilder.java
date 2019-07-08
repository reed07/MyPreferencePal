package com.google.api;

import com.google.api.Property.PropertyType;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface PropertyOrBuilder extends MessageOrBuilder {
    String getDescription();

    ByteString getDescriptionBytes();

    String getName();

    ByteString getNameBytes();

    PropertyType getType();

    int getTypeValue();
}
