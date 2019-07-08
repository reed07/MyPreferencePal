package com.google.cloud.audit;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface AuthenticationInfoOrBuilder extends MessageOrBuilder {
    String getPrincipalEmail();

    ByteString getPrincipalEmailBytes();
}
