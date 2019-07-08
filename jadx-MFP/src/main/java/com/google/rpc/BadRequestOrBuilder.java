package com.google.rpc;

import com.google.protobuf.MessageOrBuilder;
import com.google.rpc.BadRequest.FieldViolation;
import com.google.rpc.BadRequest.FieldViolationOrBuilder;
import java.util.List;

public interface BadRequestOrBuilder extends MessageOrBuilder {
    FieldViolation getFieldViolations(int i);

    int getFieldViolationsCount();

    List<FieldViolation> getFieldViolationsList();

    FieldViolationOrBuilder getFieldViolationsOrBuilder(int i);

    List<? extends FieldViolationOrBuilder> getFieldViolationsOrBuilderList();
}
