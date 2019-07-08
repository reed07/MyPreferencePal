package com.google.api;

import com.google.protobuf.MessageOrBuilder;

public interface ExperimentalOrBuilder extends MessageOrBuilder {
    AuthorizationConfig getAuthorization();

    AuthorizationConfigOrBuilder getAuthorizationOrBuilder();

    boolean hasAuthorization();
}
