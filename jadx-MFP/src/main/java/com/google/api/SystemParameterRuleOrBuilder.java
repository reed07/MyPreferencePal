package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface SystemParameterRuleOrBuilder extends MessageOrBuilder {
    SystemParameter getParameters(int i);

    int getParametersCount();

    List<SystemParameter> getParametersList();

    SystemParameterOrBuilder getParametersOrBuilder(int i);

    List<? extends SystemParameterOrBuilder> getParametersOrBuilderList();

    String getSelector();

    ByteString getSelectorBytes();
}
