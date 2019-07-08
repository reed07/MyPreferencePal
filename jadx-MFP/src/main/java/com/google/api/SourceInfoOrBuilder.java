package com.google.api;

import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface SourceInfoOrBuilder extends MessageOrBuilder {
    Any getSourceFiles(int i);

    int getSourceFilesCount();

    List<Any> getSourceFilesList();

    AnyOrBuilder getSourceFilesOrBuilder(int i);

    List<? extends AnyOrBuilder> getSourceFilesOrBuilderList();
}
