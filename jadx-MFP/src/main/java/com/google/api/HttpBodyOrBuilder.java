package com.google.api;

import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface HttpBodyOrBuilder extends MessageOrBuilder {
    String getContentType();

    ByteString getContentTypeBytes();

    ByteString getData();

    Any getExtensions(int i);

    int getExtensionsCount();

    List<Any> getExtensionsList();

    AnyOrBuilder getExtensionsOrBuilder(int i);

    List<? extends AnyOrBuilder> getExtensionsOrBuilderList();
}
