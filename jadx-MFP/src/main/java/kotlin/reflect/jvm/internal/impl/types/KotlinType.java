package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.checker.StrictEqualityTypeChecker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: KotlinType.kt */
public abstract class KotlinType implements Annotated {
    @NotNull
    public abstract List<TypeProjection> getArguments();

    @NotNull
    public abstract TypeConstructor getConstructor();

    @NotNull
    public abstract MemberScope getMemberScope();

    public abstract boolean isMarkedNullable();

    @NotNull
    public abstract UnwrappedType unwrap();

    private KotlinType() {
    }

    public /* synthetic */ KotlinType(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final int hashCode() {
        if (KotlinTypeKt.isError(this)) {
            return super.hashCode();
        }
        return (((getConstructor().hashCode() * 31) + getArguments().hashCode()) * 31) + (isMarkedNullable() ? 1 : 0);
    }

    public final boolean equals(@Nullable Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof KotlinType)) {
            return false;
        }
        KotlinType kotlinType = (KotlinType) obj;
        if (isMarkedNullable() != kotlinType.isMarkedNullable() || !StrictEqualityTypeChecker.INSTANCE.strictEqualTypes(unwrap(), kotlinType.unwrap())) {
            z = false;
        }
        return z;
    }
}
