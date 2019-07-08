package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import org.jetbrains.annotations.NotNull;

/* compiled from: TypeIntersectionScope.kt */
final class TypeIntersectionScope$getContributedVariables$1 extends Lambda implements Function1<PropertyDescriptor, PropertyDescriptor> {
    public static final TypeIntersectionScope$getContributedVariables$1 INSTANCE = new TypeIntersectionScope$getContributedVariables$1();

    TypeIntersectionScope$getContributedVariables$1() {
        super(1);
    }

    @NotNull
    public final PropertyDescriptor invoke(@NotNull PropertyDescriptor propertyDescriptor) {
        Intrinsics.checkParameterIsNotNull(propertyDescriptor, "receiver$0");
        return propertyDescriptor;
    }
}
