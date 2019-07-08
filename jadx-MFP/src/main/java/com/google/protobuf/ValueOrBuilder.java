package com.google.protobuf;

import com.google.protobuf.Value.KindCase;

public interface ValueOrBuilder extends MessageOrBuilder {
    boolean getBoolValue();

    KindCase getKindCase();

    ListValue getListValue();

    ListValueOrBuilder getListValueOrBuilder();

    NullValue getNullValue();

    int getNullValueValue();

    double getNumberValue();

    String getStringValue();

    ByteString getStringValueBytes();

    Struct getStructValue();

    StructOrBuilder getStructValueOrBuilder();

    boolean hasListValue();

    boolean hasStructValue();
}
