package com.google.api;

import com.google.api.MetricDescriptor.MetricKind;
import com.google.api.MetricDescriptor.ValueType;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface MetricDescriptorOrBuilder extends MessageOrBuilder {
    String getDescription();

    ByteString getDescriptionBytes();

    String getDisplayName();

    ByteString getDisplayNameBytes();

    LabelDescriptor getLabels(int i);

    int getLabelsCount();

    List<LabelDescriptor> getLabelsList();

    LabelDescriptorOrBuilder getLabelsOrBuilder(int i);

    List<? extends LabelDescriptorOrBuilder> getLabelsOrBuilderList();

    MetricKind getMetricKind();

    int getMetricKindValue();

    String getName();

    ByteString getNameBytes();

    String getType();

    ByteString getTypeBytes();

    String getUnit();

    ByteString getUnitBytes();

    ValueType getValueType();

    int getValueTypeValue();
}
