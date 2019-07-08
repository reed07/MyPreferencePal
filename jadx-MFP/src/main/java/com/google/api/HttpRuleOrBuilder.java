package com.google.api;

import com.google.api.HttpRule.PatternCase;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface HttpRuleOrBuilder extends MessageOrBuilder {
    HttpRule getAdditionalBindings(int i);

    int getAdditionalBindingsCount();

    List<HttpRule> getAdditionalBindingsList();

    HttpRuleOrBuilder getAdditionalBindingsOrBuilder(int i);

    List<? extends HttpRuleOrBuilder> getAdditionalBindingsOrBuilderList();

    String getBody();

    ByteString getBodyBytes();

    CustomHttpPattern getCustom();

    CustomHttpPatternOrBuilder getCustomOrBuilder();

    String getDelete();

    ByteString getDeleteBytes();

    String getGet();

    ByteString getGetBytes();

    String getPatch();

    ByteString getPatchBytes();

    PatternCase getPatternCase();

    String getPost();

    ByteString getPostBytes();

    String getPut();

    ByteString getPutBytes();

    String getSelector();

    ByteString getSelectorBytes();

    boolean hasCustom();
}
