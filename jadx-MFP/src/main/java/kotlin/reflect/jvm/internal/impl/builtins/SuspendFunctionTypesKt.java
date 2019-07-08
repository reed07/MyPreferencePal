package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.EmptyPackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.MutableClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.TypeParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: suspendFunctionTypes.kt */
public final class SuspendFunctionTypesKt {
    @NotNull
    private static final MutableClassDescriptor FAKE_CONTINUATION_CLASS_DESCRIPTOR_EXPERIMENTAL;
    @NotNull
    private static final MutableClassDescriptor FAKE_CONTINUATION_CLASS_DESCRIPTOR_RELEASE;

    @NotNull
    public static final MutableClassDescriptor getFAKE_CONTINUATION_CLASS_DESCRIPTOR_EXPERIMENTAL() {
        return FAKE_CONTINUATION_CLASS_DESCRIPTOR_EXPERIMENTAL;
    }

    static {
        ModuleDescriptor errorModule = ErrorUtils.getErrorModule();
        Intrinsics.checkExpressionValueIsNotNull(errorModule, "ErrorUtils.getErrorModule()");
        FqName fqName = DescriptorUtils.COROUTINES_PACKAGE_FQ_NAME_EXPERIMENTAL;
        Intrinsics.checkExpressionValueIsNotNull(fqName, "DescriptorUtils.COROUTIN…KAGE_FQ_NAME_EXPERIMENTAL");
        MutableClassDescriptor mutableClassDescriptor = new MutableClassDescriptor(new EmptyPackageFragmentDescriptor(errorModule, fqName), ClassKind.INTERFACE, false, false, DescriptorUtils.CONTINUATION_INTERFACE_FQ_NAME_EXPERIMENTAL.shortName(), SourceElement.NO_SOURCE, LockBasedStorageManager.NO_LOCKS);
        mutableClassDescriptor.setModality(Modality.ABSTRACT);
        mutableClassDescriptor.setVisibility(Visibilities.PUBLIC);
        mutableClassDescriptor.setTypeParameterDescriptors(CollectionsKt.listOf(TypeParameterDescriptorImpl.createWithDefaultBound(mutableClassDescriptor, Annotations.Companion.getEMPTY(), false, Variance.IN_VARIANCE, Name.identifier("T"), 0)));
        mutableClassDescriptor.createTypeConstructor();
        FAKE_CONTINUATION_CLASS_DESCRIPTOR_EXPERIMENTAL = mutableClassDescriptor;
        ModuleDescriptor errorModule2 = ErrorUtils.getErrorModule();
        Intrinsics.checkExpressionValueIsNotNull(errorModule2, "ErrorUtils.getErrorModule()");
        FqName fqName2 = DescriptorUtils.COROUTINES_PACKAGE_FQ_NAME_RELEASE;
        Intrinsics.checkExpressionValueIsNotNull(fqName2, "DescriptorUtils.COROUTINES_PACKAGE_FQ_NAME_RELEASE");
        MutableClassDescriptor mutableClassDescriptor2 = new MutableClassDescriptor(new EmptyPackageFragmentDescriptor(errorModule2, fqName2), ClassKind.INTERFACE, false, false, DescriptorUtils.CONTINUATION_INTERFACE_FQ_NAME_RELEASE.shortName(), SourceElement.NO_SOURCE, LockBasedStorageManager.NO_LOCKS);
        mutableClassDescriptor2.setModality(Modality.ABSTRACT);
        mutableClassDescriptor2.setVisibility(Visibilities.PUBLIC);
        mutableClassDescriptor2.setTypeParameterDescriptors(CollectionsKt.listOf(TypeParameterDescriptorImpl.createWithDefaultBound(mutableClassDescriptor2, Annotations.Companion.getEMPTY(), false, Variance.IN_VARIANCE, Name.identifier("T"), 0)));
        mutableClassDescriptor2.createTypeConstructor();
        FAKE_CONTINUATION_CLASS_DESCRIPTOR_RELEASE = mutableClassDescriptor2;
    }

    @NotNull
    public static final MutableClassDescriptor getFAKE_CONTINUATION_CLASS_DESCRIPTOR_RELEASE() {
        return FAKE_CONTINUATION_CLASS_DESCRIPTOR_RELEASE;
    }

    @NotNull
    public static final SimpleType transformSuspendFunctionToRuntimeFunctionType(@NotNull KotlinType kotlinType, boolean z) {
        TypeConstructor typeConstructor;
        Intrinsics.checkParameterIsNotNull(kotlinType, "suspendFunType");
        boolean isSuspendFunctionType = FunctionTypesKt.isSuspendFunctionType(kotlinType);
        if (!_Assertions.ENABLED || isSuspendFunctionType) {
            KotlinBuiltIns builtIns = TypeUtilsKt.getBuiltIns(kotlinType);
            Annotations annotations = kotlinType.getAnnotations();
            KotlinType receiverTypeFromFunctionType = FunctionTypesKt.getReceiverTypeFromFunctionType(kotlinType);
            Iterable<TypeProjection> valueParameterTypesFromFunctionType = FunctionTypesKt.getValueParameterTypesFromFunctionType(kotlinType);
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(valueParameterTypesFromFunctionType, 10));
            for (TypeProjection type : valueParameterTypesFromFunctionType) {
                arrayList.add(type.getType());
            }
            Collection collection = (List) arrayList;
            Annotations empty = Annotations.Companion.getEMPTY();
            if (z) {
                typeConstructor = FAKE_CONTINUATION_CLASS_DESCRIPTOR_RELEASE.getTypeConstructor();
            } else {
                typeConstructor = FAKE_CONTINUATION_CLASS_DESCRIPTOR_EXPERIMENTAL.getTypeConstructor();
            }
            Intrinsics.checkExpressionValueIsNotNull(typeConstructor, "if (isReleaseCoroutines)…ERIMENTAL.typeConstructor");
            List plus = CollectionsKt.plus(collection, KotlinTypeFactory.simpleType(empty, typeConstructor, CollectionsKt.listOf(TypeUtilsKt.asTypeProjection(FunctionTypesKt.getReturnTypeFromFunctionType(kotlinType))), false));
            SimpleType nullableAnyType = TypeUtilsKt.getBuiltIns(kotlinType).getNullableAnyType();
            Intrinsics.checkExpressionValueIsNotNull(nullableAnyType, "suspendFunType.builtIns.nullableAnyType");
            return FunctionTypesKt.createFunctionType$default(builtIns, annotations, receiverTypeFromFunctionType, plus, null, nullableAnyType, false, 64, null).makeNullableAsSpecified(kotlinType.isMarkedNullable());
        }
        StringBuilder sb = new StringBuilder();
        sb.append("This type should be suspend function type: ");
        sb.append(kotlinType);
        throw new AssertionError(sb.toString());
    }

    public static final boolean isContinuation(@Nullable FqName fqName, boolean z) {
        if (z) {
            return Intrinsics.areEqual((Object) fqName, (Object) DescriptorUtils.CONTINUATION_INTERFACE_FQ_NAME_RELEASE);
        }
        return Intrinsics.areEqual((Object) fqName, (Object) DescriptorUtils.CONTINUATION_INTERFACE_FQ_NAME_EXPERIMENTAL);
    }
}
