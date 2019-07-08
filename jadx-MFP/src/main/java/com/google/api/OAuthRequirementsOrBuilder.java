package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface OAuthRequirementsOrBuilder extends MessageOrBuilder {
    String getCanonicalScopes();

    ByteString getCanonicalScopesBytes();
}
