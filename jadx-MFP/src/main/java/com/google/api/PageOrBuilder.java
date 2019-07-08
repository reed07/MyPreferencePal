package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface PageOrBuilder extends MessageOrBuilder {
    String getContent();

    ByteString getContentBytes();

    String getName();

    ByteString getNameBytes();

    Page getSubpages(int i);

    int getSubpagesCount();

    List<Page> getSubpagesList();

    PageOrBuilder getSubpagesOrBuilder(int i);

    List<? extends PageOrBuilder> getSubpagesOrBuilderList();
}
