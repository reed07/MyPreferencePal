package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import org.jetbrains.annotations.NotNull;

/* compiled from: TypeIntersectionScope.kt */
final class TypeIntersectionScope$getContributedFunctions$1 extends Lambda implements Function1<SimpleFunctionDescriptor, SimpleFunctionDescriptor> {
    public static final TypeIntersectionScope$getContributedFunctions$1 INSTANCE = new TypeIntersectionScope$getContributedFunctions$1();

    TypeIntersectionScope$getContributedFunctions$1() {
        super(1);
    }

    @NotNull
    public final SimpleFunctionDescriptor invoke(@NotNull SimpleFunctionDescriptor simpleFunctionDescriptor) {
        Intrinsics.checkParameterIsNotNull(simpleFunctionDescriptor, "receiver$0");
        return simpleFunctionDescriptor;
    }
}
