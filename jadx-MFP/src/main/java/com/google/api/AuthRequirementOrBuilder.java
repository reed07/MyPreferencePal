package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface AuthRequirementOrBuilder extends MessageOrBuilder {
    String getAudiences();

    ByteString getAudiencesBytes();

    String getProviderId();

    ByteString getProviderIdBytes();
}
