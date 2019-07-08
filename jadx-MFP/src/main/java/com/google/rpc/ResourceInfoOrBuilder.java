package com.google.rpc;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface ResourceInfoOrBuilder extends MessageOrBuilder {
    String getDescription();

    ByteString getDescriptionBytes();

    String getOwner();

    ByteString getOwnerBytes();

    String getResourceName();

    ByteString getResourceNameBytes();

    String getResourceType();

    ByteString getResourceTypeBytes();
}
