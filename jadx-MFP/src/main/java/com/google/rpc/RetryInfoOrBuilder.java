package com.google.rpc;

import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;
import com.google.protobuf.MessageOrBuilder;

public interface RetryInfoOrBuilder extends MessageOrBuilder {
    Duration getRetryDelay();

    DurationOrBuilder getRetryDelayOrBuilder();

    boolean hasRetryDelay();
}
