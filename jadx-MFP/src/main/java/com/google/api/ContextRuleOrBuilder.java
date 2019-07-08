package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface ContextRuleOrBuilder extends MessageOrBuilder {
    String getProvided(int i);

    ByteString getProvidedBytes(int i);

    int getProvidedCount();

    List<String> getProvidedList();

    String getRequested(int i);

    ByteString getRequestedBytes(int i);

    int getRequestedCount();

    List<String> getRequestedList();

    String getSelector();

    ByteString getSelectorBytes();
}
