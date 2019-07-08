package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JavaDescriptorResolver;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ModuleClassResolver.kt */
public final class SingleModuleClassResolver implements ModuleClassResolver {
    @NotNull
    public JavaDescriptorResolver resolver;

    @Nullable
    public ClassDescriptor resolveClass(@NotNull JavaClass javaClass) {
        Intrinsics.checkParameterIsNotNull(javaClass, "javaClass");
        JavaDescriptorResolver javaDescriptorResolver = this.resolver;
        if (javaDescriptorResolver == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resolver");
        }
        return javaDescriptorResolver.resolveClass(javaClass);
    }

    public final void setResolver(@NotNull JavaDescriptorResolver javaDescriptorResolver) {
        Intrinsics.checkParameterIsNotNull(javaDescriptorResolver, "<set-?>");
        this.resolver = javaDescriptorResolver;
    }
}
