package com.google.api;

import com.google.api.Logging.LoggingDestination;
import com.google.api.Logging.LoggingDestinationOrBuilder;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface LoggingOrBuilder extends MessageOrBuilder {
    LoggingDestination getConsumerDestinations(int i);

    int getConsumerDestinationsCount();

    List<LoggingDestination> getConsumerDestinationsList();

    LoggingDestinationOrBuilder getConsumerDestinationsOrBuilder(int i);

    List<? extends LoggingDestinationOrBuilder> getConsumerDestinationsOrBuilderList();

    LoggingDestination getProducerDestinations(int i);

    int getProducerDestinationsCount();

    List<LoggingDestination> getProducerDestinationsList();

    LoggingDestinationOrBuilder getProducerDestinationsOrBuilder(int i);

    List<? extends LoggingDestinationOrBuilder> getProducerDestinationsOrBuilderList();
}
