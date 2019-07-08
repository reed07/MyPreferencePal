package com.google.api;

import com.google.api.Monitoring.MonitoringDestination;
import com.google.api.Monitoring.MonitoringDestinationOrBuilder;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface MonitoringOrBuilder extends MessageOrBuilder {
    MonitoringDestination getConsumerDestinations(int i);

    int getConsumerDestinationsCount();

    List<MonitoringDestination> getConsumerDestinationsList();

    MonitoringDestinationOrBuilder getConsumerDestinationsOrBuilder(int i);

    List<? extends MonitoringDestinationOrBuilder> getConsumerDestinationsOrBuilderList();

    MonitoringDestination getProducerDestinations(int i);

    int getProducerDestinationsCount();

    List<MonitoringDestination> getProducerDestinationsList();

    MonitoringDestinationOrBuilder getProducerDestinationsOrBuilder(int i);

    List<? extends MonitoringDestinationOrBuilder> getProducerDestinationsOrBuilderList();
}
