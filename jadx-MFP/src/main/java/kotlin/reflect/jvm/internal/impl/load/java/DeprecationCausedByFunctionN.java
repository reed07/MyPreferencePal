package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import org.jetbrains.annotations.NotNull;

/* compiled from: utils.kt */
public final class DeprecationCausedByFunctionN {
    @NotNull
    private final DeclarationDescriptor target;

    public DeprecationCausedByFunctionN(@NotNull DeclarationDescriptor declarationDescriptor) {
        Intrinsics.checkParameterIsNotNull(declarationDescriptor, "target");
        this.target = declarationDescriptor;
    }
}
