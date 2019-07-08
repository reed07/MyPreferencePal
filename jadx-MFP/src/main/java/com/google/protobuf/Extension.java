package com.google.protobuf;

import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.MessageLite;

public abstract class Extension<ContainingType extends MessageLite, Type> extends ExtensionLite<ContainingType, Type> {

    protected enum ExtensionType {
        IMMUTABLE,
        MUTABLE,
        PROTO1
    }

    public enum MessageType {
        PROTO1,
        PROTO2
    }

    /* access modifiers changed from: protected */
    public abstract Object fromReflectionType(Object obj);

    public abstract FieldDescriptor getDescriptor();

    /* access modifiers changed from: protected */
    public abstract ExtensionType getExtensionType();

    /* access modifiers changed from: 0000 */
    public final boolean isLite() {
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract Object singularFromReflectionType(Object obj);

    /* access modifiers changed from: protected */
    public abstract Object singularToReflectionType(Object obj);

    /* access modifiers changed from: protected */
    public abstract Object toReflectionType(Object obj);

    public MessageType getMessageType() {
        return MessageType.PROTO2;
    }
}
