package com.google.rpc;

import com.google.protobuf.MessageOrBuilder;
import com.google.rpc.QuotaFailure.Violation;
import com.google.rpc.QuotaFailure.ViolationOrBuilder;
import java.util.List;

public interface QuotaFailureOrBuilder extends MessageOrBuilder {
    Violation getViolations(int i);

    int getViolationsCount();

    List<Violation> getViolationsList();

    ViolationOrBuilder getViolationsOrBuilder(int i);

    List<? extends ViolationOrBuilder> getViolationsOrBuilderList();
}
