package com.google.rpc;

import com.google.protobuf.MessageOrBuilder;
import com.google.rpc.PreconditionFailure.Violation;
import com.google.rpc.PreconditionFailure.ViolationOrBuilder;
import java.util.List;

public interface PreconditionFailureOrBuilder extends MessageOrBuilder {
    Violation getViolations(int i);

    int getViolationsCount();

    List<Violation> getViolationsList();

    ViolationOrBuilder getViolationsOrBuilder(int i);

    List<? extends ViolationOrBuilder> getViolationsOrBuilderList();
}
