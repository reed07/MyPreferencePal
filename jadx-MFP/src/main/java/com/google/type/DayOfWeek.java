package com.google.type;

import com.google.protobuf.Descriptors.EnumDescriptor;
import com.google.protobuf.Descriptors.EnumValueDescriptor;
import com.google.protobuf.Internal.EnumLiteMap;
import com.google.protobuf.ProtocolMessageEnum;

public enum DayOfWeek implements ProtocolMessageEnum {
    DAY_OF_WEEK_UNSPECIFIED(0),
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7),
    UNRECOGNIZED(-1);
    
    public static final int DAY_OF_WEEK_UNSPECIFIED_VALUE = 0;
    public static final int FRIDAY_VALUE = 5;
    public static final int MONDAY_VALUE = 1;
    public static final int SATURDAY_VALUE = 6;
    public static final int SUNDAY_VALUE = 7;
    public static final int THURSDAY_VALUE = 4;
    public static final int TUESDAY_VALUE = 2;
    private static final DayOfWeek[] VALUES = null;
    public static final int WEDNESDAY_VALUE = 3;
    private static final EnumLiteMap<DayOfWeek> internalValueMap = null;
    private final int value;

    static {
        internalValueMap = new EnumLiteMap<DayOfWeek>() {
            public DayOfWeek findValueByNumber(int i) {
                return DayOfWeek.forNumber(i);
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
    public static DayOfWeek valueOf(int i) {
        return forNumber(i);
    }

    public static DayOfWeek forNumber(int i) {
        switch (i) {
            case 0:
                return DAY_OF_WEEK_UNSPECIFIED;
            case 1:
                return MONDAY;
            case 2:
                return TUESDAY;
            case 3:
                return WEDNESDAY;
            case 4:
                return THURSDAY;
            case 5:
                return FRIDAY;
            case 6:
                return SATURDAY;
            case 7:
                return SUNDAY;
            default:
                return null;
        }
    }

    public static EnumLiteMap<DayOfWeek> internalGetValueMap() {
        return internalValueMap;
    }

    public final EnumValueDescriptor getValueDescriptor() {
        return (EnumValueDescriptor) getDescriptor().getValues().get(ordinal());
    }

    public final EnumDescriptor getDescriptorForType() {
        return getDescriptor();
    }

    public static final EnumDescriptor getDescriptor() {
        return (EnumDescriptor) DayOfWeekProto.getDescriptor().getEnumTypes().get(0);
    }

    public static DayOfWeek valueOf(EnumValueDescriptor enumValueDescriptor) {
        if (enumValueDescriptor.getType() != getDescriptor()) {
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        } else if (enumValueDescriptor.getIndex() == -1) {
            return UNRECOGNIZED;
        } else {
            return VALUES[enumValueDescriptor.getIndex()];
        }
    }

    private DayOfWeek(int i) {
        this.value = i;
    }
}
