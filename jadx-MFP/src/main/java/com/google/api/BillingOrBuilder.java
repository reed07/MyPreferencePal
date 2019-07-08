package com.google.api;

import com.google.api.Billing.BillingDestination;
import com.google.api.Billing.BillingDestinationOrBuilder;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface BillingOrBuilder extends MessageOrBuilder {
    BillingDestination getConsumerDestinations(int i);

    int getConsumerDestinationsCount();

    List<BillingDestination> getConsumerDestinationsList();

    BillingDestinationOrBuilder getConsumerDestinationsOrBuilder(int i);

    List<? extends BillingDestinationOrBuilder> getConsumerDestinationsOrBuilderList();
}
