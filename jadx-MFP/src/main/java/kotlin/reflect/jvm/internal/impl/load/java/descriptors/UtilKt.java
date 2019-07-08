package kotlin.reflect.jvm.internal.impl.load.java.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Pair;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ValueParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNames;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaStaticClassScope;
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmPackagePartSource;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: util.kt */
public final class UtilKt {
    @NotNull
    public static final List<ValueParameterDescriptor> copyValueParameters(@NotNull Collection<ValueParameterData> collection, @NotNull Collection<? extends ValueParameterDescriptor> collection2, @NotNull CallableDescriptor callableDescriptor) {
        Collection<ValueParameterData> collection3 = collection;
        Collection<? extends ValueParameterDescriptor> collection4 = collection2;
        CallableDescriptor callableDescriptor2 = callableDescriptor;
        Intrinsics.checkParameterIsNotNull(collection3, "newValueParametersTypes");
        Intrinsics.checkParameterIsNotNull(collection4, "oldValueParameters");
        Intrinsics.checkParameterIsNotNull(callableDescriptor2, "newOwner");
        boolean z = collection.size() == collection2.size();
        if (!_Assertions.ENABLED || z) {
            Iterable<Pair> zip = CollectionsKt.zip((Iterable<? extends T>) collection3, (Iterable<? extends R>) collection4);
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(zip, 10));
            for (Pair pair : zip) {
                ValueParameterData valueParameterData = (ValueParameterData) pair.component1();
                ValueParameterDescriptor valueParameterDescriptor = (ValueParameterDescriptor) pair.component2();
                int index = valueParameterDescriptor.getIndex();
                Annotations annotations = valueParameterDescriptor.getAnnotations();
                Name name = valueParameterDescriptor.getName();
                Intrinsics.checkExpressionValueIsNotNull(name, "oldParameter.name");
                KotlinType type = valueParameterData.getType();
                boolean hasDefaultValue = valueParameterData.getHasDefaultValue();
                boolean isCrossinline = valueParameterDescriptor.isCrossinline();
                boolean isNoinline = valueParameterDescriptor.isNoinline();
                KotlinType arrayElementType = valueParameterDescriptor.getVarargElementType() != null ? DescriptorUtilsKt.getModule(callableDescriptor2).getBuiltIns().getArrayElementType(valueParameterData.getType()) : null;
                SourceElement source = valueParameterDescriptor.getSource();
                Intrinsics.checkExpressionValueIsNotNull(source, "oldParameter.source");
                ValueParameterDescriptorImpl valueParameterDescriptorImpl = new ValueParameterDescriptorImpl(callableDescriptor, null, index, annotations, name, type, hasDefaultValue, isCrossinline, isNoinline, arrayElementType, source);
                arrayList.add(valueParameterDescriptorImpl);
            }
            return (List) arrayList;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Different value parameters sizes: Enhanced = ");
        sb.append(collection.size());
        sb.append(", Old = ");
        sb.append(collection2.size());
        throw new AssertionError(sb.toString());
    }

    @Nullable
    public static final LazyJavaStaticClassScope getParentJavaStaticClassScope(@NotNull ClassDescriptor classDescriptor) {
        Intrinsics.checkParameterIsNotNull(classDescriptor, "receiver$0");
        ClassDescriptor superClassNotAny = DescriptorUtilsKt.getSuperClassNotAny(classDescriptor);
        if (superClassNotAny == null) {
            return null;
        }
        MemberScope staticScope = superClassNotAny.getStaticScope();
        Intrinsics.checkExpressionValueIsNotNull(staticScope, "superClassDescriptor.staticScope");
        if (!(staticScope instanceof LazyJavaStaticClassScope)) {
            return getParentJavaStaticClassScope(superClassNotAny);
        }
        return (LazyJavaStaticClassScope) staticScope;
    }

    @Nullable
    public static final JvmClassName getImplClassNameForDeserialized(@NotNull DeserializedMemberDescriptor deserializedMemberDescriptor) {
        Intrinsics.checkParameterIsNotNull(deserializedMemberDescriptor, "receiver$0");
        DeserializedContainerSource containerSource = deserializedMemberDescriptor.getContainerSource();
        if (!(containerSource instanceof JvmPackagePartSource)) {
            containerSource = null;
        }
        JvmPackagePartSource jvmPackagePartSource = (JvmPackagePartSource) containerSource;
        if (jvmPackagePartSource != null) {
            return jvmPackagePartSource.getClassName();
        }
        return null;
    }

    @Nullable
    public static final AnnotationDefaultValue getDefaultValueFromAnnotation(@NotNull ValueParameterDescriptor valueParameterDescriptor) {
        Intrinsics.checkParameterIsNotNull(valueParameterDescriptor, "receiver$0");
        Annotations annotations = valueParameterDescriptor.getAnnotations();
        FqName fqName = JvmAnnotationNames.DEFAULT_VALUE_FQ_NAME;
        Intrinsics.checkExpressionValueIsNotNull(fqName, "JvmAnnotationNames.DEFAULT_VALUE_FQ_NAME");
        AnnotationDescriptor findAnnotation = annotations.findAnnotation(fqName);
        if (findAnnotation != null) {
            ConstantValue firstArgument = DescriptorUtilsKt.firstArgument(findAnnotation);
            if (firstArgument != null) {
                if (!(firstArgument instanceof StringValue)) {
                    firstArgument = null;
                }
                StringValue stringValue = (StringValue) firstArgument;
                if (stringValue != null) {
                    String str = (String) stringValue.getValue();
                    if (str != null) {
                        return new StringDefaultValue(str);
                    }
                }
            }
        }
        Annotations annotations2 = valueParameterDescriptor.getAnnotations();
        FqName fqName2 = JvmAnnotationNames.DEFAULT_NULL_FQ_NAME;
        Intrinsics.checkExpressionValueIsNotNull(fqName2, "JvmAnnotationNames.DEFAULT_NULL_FQ_NAME");
        if (annotations2.hasAnnotation(fqName2)) {
            return NullDefaultValue.INSTANCE;
        }
        return null;
    }
}
