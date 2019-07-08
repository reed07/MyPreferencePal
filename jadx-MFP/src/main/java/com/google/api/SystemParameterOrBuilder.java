package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface SystemParameterOrBuilder extends MessageOrBuilder {
    String getHttpHeader();

    ByteString getHttpHeaderBytes();

    String getName();

    ByteString getNameBytes();

    String getUrlQueryParameter();

    ByteString getUrlQueryParameterBytes();
}
