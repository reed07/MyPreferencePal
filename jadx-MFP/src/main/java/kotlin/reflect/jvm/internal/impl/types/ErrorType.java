package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import org.jetbrains.annotations.NotNull;

/* compiled from: ErrorType.kt */
public class ErrorType extends SimpleType {
    @NotNull
    private final List<TypeProjection> arguments;
    @NotNull
    private final TypeConstructor constructor;
    private final boolean isMarkedNullable;
    @NotNull
    private final MemberScope memberScope;

    @JvmOverloads
    public ErrorType(@NotNull TypeConstructor typeConstructor, @NotNull MemberScope memberScope2) {
        this(typeConstructor, memberScope2, null, false, 12, null);
    }

    @NotNull
    public TypeConstructor getConstructor() {
        return this.constructor;
    }

    @NotNull
    public MemberScope getMemberScope() {
        return this.memberScope;
    }

    @JvmOverloads
    public /* synthetic */ ErrorType(TypeConstructor typeConstructor, MemberScope memberScope2, List list, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 4) != 0) {
            list = CollectionsKt.emptyList();
        }
        if ((i & 8) != 0) {
            z = false;
        }
        this(typeConstructor, memberScope2, list, z);
    }

    @NotNull
    public List<TypeProjection> getArguments() {
        return this.arguments;
    }

    public boolean isMarkedNullable() {
        return this.isMarkedNullable;
    }

    @JvmOverloads
    public ErrorType(@NotNull TypeConstructor typeConstructor, @NotNull MemberScope memberScope2, @NotNull List<? extends TypeProjection> list, boolean z) {
        Intrinsics.checkParameterIsNotNull(typeConstructor, "constructor");
        Intrinsics.checkParameterIsNotNull(memberScope2, "memberScope");
        Intrinsics.checkParameterIsNotNull(list, "arguments");
        this.constructor = typeConstructor;
        this.memberScope = memberScope2;
        this.arguments = list;
        this.isMarkedNullable = z;
    }

    @NotNull
    public Annotations getAnnotations() {
        return Annotations.Companion.getEMPTY();
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getConstructor().toString());
        sb.append(getArguments().isEmpty() ? "" : CollectionsKt.joinToString(getArguments(), ", ", "<", ">", -1, "...", null));
        return sb.toString();
    }

    @NotNull
    public SimpleType replaceAnnotations(@NotNull Annotations annotations) {
        Intrinsics.checkParameterIsNotNull(annotations, "newAnnotations");
        return this;
    }

    @NotNull
    public SimpleType makeNullableAsSpecified(boolean z) {
        return new ErrorType(getConstructor(), getMemberScope(), getArguments(), z);
    }
}
