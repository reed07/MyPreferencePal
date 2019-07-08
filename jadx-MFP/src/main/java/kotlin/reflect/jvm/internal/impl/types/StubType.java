package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import org.jetbrains.annotations.NotNull;

/* compiled from: StubType.kt */
public final class StubType extends SimpleType {
    @NotNull
    private final TypeConstructor constructor;
    private final boolean isMarkedNullable;
    @NotNull
    private final MemberScope memberScope;
    private final TypeConstructor originalTypeVariable;

    public boolean isMarkedNullable() {
        return this.isMarkedNullable;
    }

    @NotNull
    public TypeConstructor getConstructor() {
        return this.constructor;
    }

    @NotNull
    public MemberScope getMemberScope() {
        return this.memberScope;
    }

    public StubType(@NotNull TypeConstructor typeConstructor, boolean z, @NotNull TypeConstructor typeConstructor2, @NotNull MemberScope memberScope2) {
        Intrinsics.checkParameterIsNotNull(typeConstructor, "originalTypeVariable");
        Intrinsics.checkParameterIsNotNull(typeConstructor2, "constructor");
        Intrinsics.checkParameterIsNotNull(memberScope2, "memberScope");
        this.originalTypeVariable = typeConstructor;
        this.isMarkedNullable = z;
        this.constructor = typeConstructor2;
        this.memberScope = memberScope2;
    }

    @NotNull
    public List<TypeProjection> getArguments() {
        return CollectionsKt.emptyList();
    }

    @NotNull
    public Annotations getAnnotations() {
        return Annotations.Companion.getEMPTY();
    }

    @NotNull
    public SimpleType replaceAnnotations(@NotNull Annotations annotations) {
        Intrinsics.checkParameterIsNotNull(annotations, "newAnnotations");
        throw new IllegalStateException("Shouldn't be called on non-fixed type".toString());
    }

    @NotNull
    public SimpleType makeNullableAsSpecified(boolean z) {
        if (z == isMarkedNullable()) {
            return this;
        }
        return new StubType(this.originalTypeVariable, z, getConstructor(), getMemberScope());
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NonFixed: ");
        sb.append(this.originalTypeVariable);
        return sb.toString();
    }
}
