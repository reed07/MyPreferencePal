package com.google.type;

import com.google.protobuf.MessageOrBuilder;

public interface DateOrBuilder extends MessageOrBuilder {
    int getDay();

    int getMonth();

    int getYear();
}
