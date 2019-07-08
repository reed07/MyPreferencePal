package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface AuthorizationConfigOrBuilder extends MessageOrBuilder {
    String getProvider();

    ByteString getProviderBytes();
}
