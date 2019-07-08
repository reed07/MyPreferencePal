package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface EndpointOrBuilder extends MessageOrBuilder {
    String getAliases(int i);

    ByteString getAliasesBytes(int i);

    int getAliasesCount();

    List<String> getAliasesList();

    boolean getAllowCors();

    String getFeatures(int i);

    ByteString getFeaturesBytes(int i);

    int getFeaturesCount();

    List<String> getFeaturesList();

    String getName();

    ByteString getNameBytes();

    String getTarget();

    ByteString getTargetBytes();
}
