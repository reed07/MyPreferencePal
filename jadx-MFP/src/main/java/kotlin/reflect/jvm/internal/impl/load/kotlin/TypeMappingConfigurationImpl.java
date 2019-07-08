package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.Collection;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: methodSignatureMapping.kt */
public final class TypeMappingConfigurationImpl implements TypeMappingConfiguration<JvmType> {
    public static final TypeMappingConfigurationImpl INSTANCE = new TypeMappingConfigurationImpl();

    @Nullable
    public String getPredefinedInternalNameForClass(@NotNull ClassDescriptor classDescriptor) {
        Intrinsics.checkParameterIsNotNull(classDescriptor, "classDescriptor");
        return null;
    }

    @Nullable
    public Void getPredefinedTypeForClass(@NotNull ClassDescriptor classDescriptor) {
        Intrinsics.checkParameterIsNotNull(classDescriptor, "classDescriptor");
        return null;
    }

    public void processErrorType(@NotNull KotlinType kotlinType, @NotNull ClassDescriptor classDescriptor) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "kotlinType");
        Intrinsics.checkParameterIsNotNull(classDescriptor, "descriptor");
    }

    public boolean releaseCoroutines() {
        return false;
    }

    private TypeMappingConfigurationImpl() {
    }

    @NotNull
    public KotlinType commonSupertype(@NotNull Collection<? extends KotlinType> collection) {
        Intrinsics.checkParameterIsNotNull(collection, "types");
        StringBuilder sb = new StringBuilder();
        sb.append("There should be no intersection type in existing descriptors, but found: ");
        sb.append(CollectionsKt.joinToString$default(collection, null, null, null, 0, null, null, 63, null));
        throw new AssertionError(sb.toString());
    }
}
