package com.google.cloud.audit;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface AuthorizationInfoOrBuilder extends MessageOrBuilder {
    boolean getGranted();

    String getPermission();

    ByteString getPermissionBytes();

    String getResource();

    ByteString getResourceBytes();
}
