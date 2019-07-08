package com.google.protobuf.util;

import com.google.common.base.CaseFormat;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.primitives.Ints;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.FieldDescriptor.JavaType;
import com.google.protobuf.FieldMask;
import com.google.protobuf.FieldMask.Builder;
import com.google.protobuf.Internal;
import com.google.protobuf.Message;
import java.util.ArrayList;
import java.util.Arrays;

public class FieldMaskUtil {
    private static final String FIELD_PATH_SEPARATOR = ",";
    private static final String FIELD_PATH_SEPARATOR_REGEX = ",";
    private static final String FIELD_SEPARATOR_REGEX = "\\.";

    public static final class MergeOptions {
        private boolean replaceMessageFields = false;
        private boolean replacePrimitiveFields = false;
        private boolean replaceRepeatedFields = false;

        public boolean replaceMessageFields() {
            return this.replaceMessageFields;
        }

        public boolean replaceRepeatedFields() {
            return this.replaceRepeatedFields;
        }

        public boolean replacePrimitiveFields() {
            return this.replacePrimitiveFields;
        }

        public MergeOptions setReplaceMessageFields(boolean z) {
            this.replaceMessageFields = z;
            return this;
        }

        public MergeOptions setReplaceRepeatedFields(boolean z) {
            this.replaceRepeatedFields = z;
            return this;
        }

        public MergeOptions setReplacePrimitiveFields(boolean z) {
            this.replacePrimitiveFields = z;
            return this;
        }
    }

    private FieldMaskUtil() {
    }

    public static String toString(FieldMask fieldMask) {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String str : fieldMask.getPathsList()) {
            if (!str.isEmpty()) {
                if (z) {
                    z = false;
                } else {
                    sb.append(",");
                }
                sb.append(str);
            }
        }
        return sb.toString();
    }

    public static FieldMask fromString(String str) {
        return fromStringList(null, Arrays.asList(str.split(",")));
    }

    public static FieldMask fromString(Class<? extends Message> cls, String str) {
        return fromStringList(cls, Arrays.asList(str.split(",")));
    }

    public static FieldMask fromStringList(Class<? extends Message> cls, Iterable<String> iterable) {
        Builder newBuilder = FieldMask.newBuilder();
        for (String str : iterable) {
            if (!str.isEmpty()) {
                if (cls == null || isValid(cls, str)) {
                    newBuilder.addPaths(str);
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    sb.append(" is not a valid path for ");
                    sb.append(cls);
                    throw new IllegalArgumentException(sb.toString());
                }
            }
        }
        return newBuilder.build();
    }

    public static FieldMask fromFieldNumbers(Class<? extends Message> cls, int... iArr) {
        return fromFieldNumbers(cls, (Iterable<Integer>) Ints.asList(iArr));
    }

    public static FieldMask fromFieldNumbers(Class<? extends Message> cls, Iterable<Integer> iterable) {
        Descriptor descriptorForType = ((Message) Internal.getDefaultInstance(cls)).getDescriptorForType();
        Builder newBuilder = FieldMask.newBuilder();
        for (Integer num : iterable) {
            FieldDescriptor findFieldByNumber = descriptorForType.findFieldByNumber(num.intValue());
            Preconditions.checkArgument(findFieldByNumber != null, String.format("%s is not a valid field number for %s.", new Object[]{num, cls}));
            newBuilder.addPaths(findFieldByNumber.getName());
        }
        return newBuilder.build();
    }

    public static String toJsonString(FieldMask fieldMask) {
        ArrayList arrayList = new ArrayList(fieldMask.getPathsCount());
        for (String str : fieldMask.getPathsList()) {
            if (!str.isEmpty()) {
                arrayList.add(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, str));
            }
        }
        return Joiner.on(",").join((Iterable<?>) arrayList);
    }

    public static FieldMask fromJsonString(String str) {
        Iterable<String> split = Splitter.on(",").split(str);
        Builder newBuilder = FieldMask.newBuilder();
        for (String str2 : split) {
            if (!str2.isEmpty()) {
                newBuilder.addPaths(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, str2));
            }
        }
        return newBuilder.build();
    }

    public static boolean isValid(Class<? extends Message> cls, FieldMask fieldMask) {
        return isValid(((Message) Internal.getDefaultInstance(cls)).getDescriptorForType(), fieldMask);
    }

    public static boolean isValid(Descriptor descriptor, FieldMask fieldMask) {
        for (String isValid : fieldMask.getPathsList()) {
            if (!isValid(descriptor, isValid)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValid(Class<? extends Message> cls, String str) {
        return isValid(((Message) Internal.getDefaultInstance(cls)).getDescriptorForType(), str);
    }

    public static boolean isValid(Descriptor descriptor, String str) {
        String[] split = str.split(FIELD_SEPARATOR_REGEX);
        if (split.length == 0) {
            return false;
        }
        Descriptor descriptor2 = descriptor;
        for (String str2 : split) {
            if (descriptor2 == null) {
                return false;
            }
            FieldDescriptor findFieldByName = descriptor2.findFieldByName(str2);
            if (findFieldByName == null) {
                return false;
            }
            descriptor2 = (findFieldByName.isRepeated() || findFieldByName.getJavaType() != JavaType.MESSAGE) ? null : findFieldByName.getMessageType();
        }
        return true;
    }

    public static FieldMask normalize(FieldMask fieldMask) {
        return new FieldMaskTree(fieldMask).toFieldMask();
    }

    public static FieldMask union(FieldMask fieldMask, FieldMask fieldMask2, FieldMask... fieldMaskArr) {
        FieldMaskTree mergeFromFieldMask = new FieldMaskTree(fieldMask).mergeFromFieldMask(fieldMask2);
        for (FieldMask mergeFromFieldMask2 : fieldMaskArr) {
            mergeFromFieldMask.mergeFromFieldMask(mergeFromFieldMask2);
        }
        return mergeFromFieldMask.toFieldMask();
    }

    public static FieldMask intersection(FieldMask fieldMask, FieldMask fieldMask2) {
        FieldMaskTree fieldMaskTree = new FieldMaskTree(fieldMask);
        FieldMaskTree fieldMaskTree2 = new FieldMaskTree();
        for (String intersectFieldPath : fieldMask2.getPathsList()) {
            fieldMaskTree.intersectFieldPath(intersectFieldPath, fieldMaskTree2);
        }
        return fieldMaskTree2.toFieldMask();
    }

    public static void merge(FieldMask fieldMask, Message message, Message.Builder builder, MergeOptions mergeOptions) {
        new FieldMaskTree(fieldMask).merge(message, builder, mergeOptions);
    }

    public static void merge(FieldMask fieldMask, Message message, Message.Builder builder) {
        merge(fieldMask, message, builder, new MergeOptions());
    }
}
