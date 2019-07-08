package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface AuthenticationRuleOrBuilder extends MessageOrBuilder {
    boolean getAllowWithoutCredential();

    OAuthRequirements getOauth();

    OAuthRequirementsOrBuilder getOauthOrBuilder();

    AuthRequirement getRequirements(int i);

    int getRequirementsCount();

    List<AuthRequirement> getRequirementsList();

    AuthRequirementOrBuilder getRequirementsOrBuilder(int i);

    List<? extends AuthRequirementOrBuilder> getRequirementsOrBuilderList();

    String getSelector();

    ByteString getSelectorBytes();

    boolean hasOauth();
}
