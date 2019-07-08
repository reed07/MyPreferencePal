package com.google.type;

import com.google.protobuf.FloatValue;
import com.google.protobuf.FloatValueOrBuilder;
import com.google.protobuf.MessageOrBuilder;

public interface ColorOrBuilder extends MessageOrBuilder {
    FloatValue getAlpha();

    FloatValueOrBuilder getAlphaOrBuilder();

    float getBlue();

    float getGreen();

    float getRed();

    boolean hasAlpha();
}
