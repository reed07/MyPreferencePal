package com.google.api;

import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import java.util.Map;

public interface MonitoredResourceMetadataOrBuilder extends MessageOrBuilder {
    boolean containsUserLabels(String str);

    Struct getSystemLabels();

    StructOrBuilder getSystemLabelsOrBuilder();

    @Deprecated
    Map<String, String> getUserLabels();

    int getUserLabelsCount();

    Map<String, String> getUserLabelsMap();

    String getUserLabelsOrDefault(String str, String str2);

    String getUserLabelsOrThrow(String str);

    boolean hasSystemLabels();
}
