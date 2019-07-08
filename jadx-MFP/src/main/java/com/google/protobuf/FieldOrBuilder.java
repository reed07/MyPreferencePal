package com.google.protobuf;

import com.google.protobuf.Field.Cardinality;
import com.google.protobuf.Field.Kind;
import java.util.List;

public interface FieldOrBuilder extends MessageOrBuilder {
    Cardinality getCardinality();

    int getCardinalityValue();

    String getDefaultValue();

    ByteString getDefaultValueBytes();

    String getJsonName();

    ByteString getJsonNameBytes();

    Kind getKind();

    int getKindValue();

    String getName();

    ByteString getNameBytes();

    int getNumber();

    int getOneofIndex();

    Option getOptions(int i);

    int getOptionsCount();

    List<Option> getOptionsList();

    OptionOrBuilder getOptionsOrBuilder(int i);

    List<? extends OptionOrBuilder> getOptionsOrBuilderList();

    boolean getPacked();

    String getTypeUrl();

    ByteString getTypeUrlBytes();
}
