package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.GivenFunctionsMemberScope;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;

/* compiled from: CloneableClassScope.kt */
public final class CloneableClassScope extends GivenFunctionsMemberScope {
    /* access modifiers changed from: private */
    @NotNull
    public static final Name CLONE_NAME;
    public static final Companion Companion = new Companion(null);

    /* compiled from: CloneableClassScope.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Name getCLONE_NAME$descriptors_jvm() {
            return CloneableClassScope.CLONE_NAME;
        }
    }

    public CloneableClassScope(@NotNull StorageManager storageManager, @NotNull ClassDescriptor classDescriptor) {
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(classDescriptor, "containingClass");
        super(storageManager, classDescriptor);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public List<FunctionDescriptor> computeDeclaredFunctions() {
        SimpleFunctionDescriptorImpl create = SimpleFunctionDescriptorImpl.create(getContainingClass(), Annotations.Companion.getEMPTY(), CLONE_NAME, Kind.DECLARATION, SourceElement.NO_SOURCE);
        create.initialize((ReceiverParameterDescriptor) null, getContainingClass().getThisAsReceiverParameter(), CollectionsKt.emptyList(), CollectionsKt.emptyList(), (KotlinType) DescriptorUtilsKt.getBuiltIns(getContainingClass()).getAnyType(), Modality.OPEN, Visibilities.PROTECTED);
        return CollectionsKt.listOf(create);
    }

    static {
        Name identifier = Name.identifier("clone");
        Intrinsics.checkExpressionValueIsNotNull(identifier, "Name.identifier(\"clone\")");
        CLONE_NAME = identifier;
    }
}
