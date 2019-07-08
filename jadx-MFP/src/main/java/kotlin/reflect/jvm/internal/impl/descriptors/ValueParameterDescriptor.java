package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.Collection;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ValueParameterDescriptor.kt */
public interface ValueParameterDescriptor extends ParameterDescriptor, VariableDescriptor {

    /* compiled from: ValueParameterDescriptor.kt */
    public static final class DefaultImpls {
        public static boolean isLateInit(ValueParameterDescriptor valueParameterDescriptor) {
            return false;
        }
    }

    @NotNull
    ValueParameterDescriptor copy(@NotNull CallableDescriptor callableDescriptor, @NotNull Name name, int i);

    boolean declaresDefaultValue();

    @NotNull
    CallableDescriptor getContainingDeclaration();

    int getIndex();

    @NotNull
    ValueParameterDescriptor getOriginal();

    @NotNull
    Collection<ValueParameterDescriptor> getOverriddenDescriptors();

    @Nullable
    KotlinType getVarargElementType();

    boolean isCrossinline();

    boolean isNoinline();
}
