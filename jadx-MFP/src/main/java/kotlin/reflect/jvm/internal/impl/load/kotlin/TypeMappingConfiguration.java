package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.Collection;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: typeSignatureMapping.kt */
public interface TypeMappingConfiguration<T> {
    @NotNull
    KotlinType commonSupertype(@NotNull Collection<KotlinType> collection);

    @Nullable
    String getPredefinedInternalNameForClass(@NotNull ClassDescriptor classDescriptor);

    @Nullable
    T getPredefinedTypeForClass(@NotNull ClassDescriptor classDescriptor);

    void processErrorType(@NotNull KotlinType kotlinType, @NotNull ClassDescriptor classDescriptor);

    boolean releaseCoroutines();
}
