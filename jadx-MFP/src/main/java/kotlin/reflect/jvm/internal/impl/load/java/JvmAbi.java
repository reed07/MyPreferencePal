package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.reflect.jvm.internal.impl.builtins.CompanionObjectMapping;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FieldDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.util.capitalizeDecapitalize.CapitalizeDecapitalizeKt;
import org.jetbrains.annotations.NotNull;

public final class JvmAbi {
    public static final FqName JVM_FIELD_ANNOTATION_FQ_NAME = new FqName("kotlin.jvm.JvmField");
    public static final ClassId REFLECTION_FACTORY_IMPL = ClassId.topLevel(new FqName("kotlin.reflect.jvm.internal.ReflectionFactoryImpl"));

    public static boolean isGetterName(@NotNull String str) {
        return str.startsWith("get") || str.startsWith("is");
    }

    public static boolean isSetterName(@NotNull String str) {
        return str.startsWith("set");
    }

    @NotNull
    public static String getterName(@NotNull String str) {
        if (startsWithIsPrefix(str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("get");
        sb.append(CapitalizeDecapitalizeKt.capitalizeAsciiOnly(str));
        return sb.toString();
    }

    @NotNull
    public static String setterName(@NotNull String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("set");
        sb.append(startsWithIsPrefix(str) ? str.substring(2) : CapitalizeDecapitalizeKt.capitalizeAsciiOnly(str));
        return sb.toString();
    }

    public static boolean startsWithIsPrefix(String str) {
        boolean z = false;
        if (!str.startsWith("is") || str.length() == 2) {
            return false;
        }
        char charAt = str.charAt(2);
        if ('a' > charAt || charAt > 'z') {
            z = true;
        }
        return z;
    }

    public static boolean isPropertyWithBackingFieldInOuterClass(@NotNull PropertyDescriptor propertyDescriptor) {
        if (propertyDescriptor.getKind() == Kind.FAKE_OVERRIDE) {
            return false;
        }
        boolean z = true;
        if (isClassCompanionObjectWithBackingFieldsInOuter(propertyDescriptor.getContainingDeclaration())) {
            return true;
        }
        if (!DescriptorUtils.isCompanionObject(propertyDescriptor.getContainingDeclaration()) || !hasJvmFieldAnnotation(propertyDescriptor)) {
            z = false;
        }
        return z;
    }

    public static boolean isClassCompanionObjectWithBackingFieldsInOuter(@NotNull DeclarationDescriptor declarationDescriptor) {
        return DescriptorUtils.isCompanionObject(declarationDescriptor) && DescriptorUtils.isClassOrEnumClass(declarationDescriptor.getContainingDeclaration()) && !isMappedIntrinsicCompanionObject((ClassDescriptor) declarationDescriptor);
    }

    public static boolean isMappedIntrinsicCompanionObject(@NotNull ClassDescriptor classDescriptor) {
        return CompanionObjectMapping.INSTANCE.isMappedIntrinsicCompanionObject(classDescriptor);
    }

    public static boolean hasJvmFieldAnnotation(@NotNull CallableMemberDescriptor callableMemberDescriptor) {
        if (callableMemberDescriptor instanceof PropertyDescriptor) {
            FieldDescriptor backingField = ((PropertyDescriptor) callableMemberDescriptor).getBackingField();
            if (backingField != null && backingField.getAnnotations().hasAnnotation(JVM_FIELD_ANNOTATION_FQ_NAME)) {
                return true;
            }
        }
        return callableMemberDescriptor.getAnnotations().hasAnnotation(JVM_FIELD_ANNOTATION_FQ_NAME);
    }
}
