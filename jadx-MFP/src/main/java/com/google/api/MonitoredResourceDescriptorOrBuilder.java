package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface MonitoredResourceDescriptorOrBuilder extends MessageOrBuilder {
    String getDescription();

    ByteString getDescriptionBytes();

    String getDisplayName();

    ByteString getDisplayNameBytes();

    LabelDescriptor getLabels(int i);

    int getLabelsCount();

    List<LabelDescriptor> getLabelsList();

    LabelDescriptorOrBuilder getLabelsOrBuilder(int i);

    List<? extends LabelDescriptorOrBuilder> getLabelsOrBuilderList();

    String getName();

    ByteString getNameBytes();

    String getType();

    ByteString getTypeBytes();
}
