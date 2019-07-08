package com.google.api;

import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface BackendOrBuilder extends MessageOrBuilder {
    BackendRule getRules(int i);

    int getRulesCount();

    List<BackendRule> getRulesList();

    BackendRuleOrBuilder getRulesOrBuilder(int i);

    List<? extends BackendRuleOrBuilder> getRulesOrBuilderList();
}
