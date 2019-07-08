package com.google.type;

import com.google.protobuf.MessageOrBuilder;

public interface TimeOfDayOrBuilder extends MessageOrBuilder {
    int getHours();

    int getMinutes();

    int getNanos();

    int getSeconds();
}
