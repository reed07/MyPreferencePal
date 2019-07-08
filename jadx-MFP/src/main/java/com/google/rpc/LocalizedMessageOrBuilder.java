package com.google.rpc;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface LocalizedMessageOrBuilder extends MessageOrBuilder {
    String getLocale();

    ByteString getLocaleBytes();

    String getMessage();

    ByteString getMessageBytes();
}
