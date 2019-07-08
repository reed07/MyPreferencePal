package com.google.longrunning;

import com.google.longrunning.Operation.ResultCase;
import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.rpc.Status;
import com.google.rpc.StatusOrBuilder;

public interface OperationOrBuilder extends MessageOrBuilder {
    boolean getDone();

    Status getError();

    StatusOrBuilder getErrorOrBuilder();

    Any getMetadata();

    AnyOrBuilder getMetadataOrBuilder();

    String getName();

    ByteString getNameBytes();

    Any getResponse();

    AnyOrBuilder getResponseOrBuilder();

    ResultCase getResultCase();

    boolean hasError();

    boolean hasMetadata();

    boolean hasResponse();
}
