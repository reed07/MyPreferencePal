package com.google.api;

import com.google.protobuf.Descriptors.EnumDescriptor;
import com.google.protobuf.Descriptors.EnumValueDescriptor;
import com.google.protobuf.Internal.EnumLiteMap;
import com.google.protobuf.ProtocolMessageEnum;

public enum ChangeType implements ProtocolMessageEnum {
    CHANGE_TYPE_UNSPECIFIED(0),
    ADDED(1),
    REMOVED(2),
    MODIFIED(3),
    UNRECOGNIZED(-1);
    
    public static final int ADDED_VALUE = 1;
    public static final int CHANGE_TYPE_UNSPECIFIED_VALUE = 0;
    public static final int MODIFIED_VALUE = 3;
    public static final int REMOVED_VALUE = 2;
    private static final ChangeType[] VALUES = null;
    private static final EnumLiteMap<ChangeType> internalValueMap = null;
    private final int value;

    static {
        internalValueMap = new EnumLiteMap<ChangeType>() {
            public ChangeType findValueByNumber(int i) {
                return ChangeType.forNumber(i);
            }
        };
        VALUES = values();
    }

    public final int getNumber() {
        if (this != UNRECOGNIZED) {
            return this.value;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }

    @Deprecated
    public static ChangeType valueOf(int i) {
        return forNumber(i);
    }

    public static ChangeType forNumber(int i) {
        switch (i) {
            case 0:
                return CHANGE_TYPE_UNSPECIFIED;
            case 1:
                return ADDED;
            case 2:
                return REMOVED;
            case 3:
                return MODIFIED;
            default:
                return null;
        }
    }

    public static EnumLiteMap<ChangeType> internalGetValueMap() {
        return internalValueMap;
    }

    public final EnumValueDescriptor getValueDescriptor() {
        return (EnumValueDescriptor) getDescriptor().getValues().get(ordinal());
    }

    public final EnumDescriptor getDescriptorForType() {
        return getDescriptor();
    }

    public static final EnumDescriptor getDescriptor() {
        return (EnumDescriptor) ConfigChangeProto.getDescriptor().getEnumTypes().get(0);
    }

    public static ChangeType valueOf(EnumValueDescriptor enumValueDescriptor) {
        if (enumValueDescriptor.getType() != getDescriptor()) {
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        } else if (enumValueDescriptor.getIndex() == -1) {
            return UNRECOGNIZED;
        } else {
            return VALUES[enumValueDescriptor.getIndex()];
        }
    }

    private ChangeType(int i) {
        this.value = i;
    }
}
