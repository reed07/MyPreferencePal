package kotlin.reflect.jvm.internal.impl.types;

import java.util.Collection;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface TypeConstructor {
    @NotNull
    KotlinBuiltIns getBuiltIns();

    @Nullable
    ClassifierDescriptor getDeclarationDescriptor();

    @NotNull
    List<TypeParameterDescriptor> getParameters();

    @NotNull
    Collection<KotlinType> getSupertypes();

    boolean isDenotable();
}
