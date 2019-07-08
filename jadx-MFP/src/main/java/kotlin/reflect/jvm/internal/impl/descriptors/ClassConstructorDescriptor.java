package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ClassConstructorDescriptor.kt */
public interface ClassConstructorDescriptor extends ConstructorDescriptor {
    @NotNull
    ClassConstructorDescriptor copy(@NotNull DeclarationDescriptor declarationDescriptor, @NotNull Modality modality, @NotNull Visibility visibility, @NotNull Kind kind, boolean z);

    @NotNull
    ClassConstructorDescriptor getOriginal();

    @Nullable
    ClassConstructorDescriptor substitute(@NotNull TypeSubstitutor typeSubstitutor);
}
