package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils.ErrorScope;
import org.jetbrains.annotations.NotNull;

/* compiled from: KotlinTypeFactory.kt */
final class SimpleTypeImpl extends SimpleType {
    @NotNull
    private final List<TypeProjection> arguments;
    @NotNull
    private final TypeConstructor constructor;
    private final boolean isMarkedNullable;
    @NotNull
    private final MemberScope memberScope;

    @NotNull
    public TypeConstructor getConstructor() {
        return this.constructor;
    }

    @NotNull
    public List<TypeProjection> getArguments() {
        return this.arguments;
    }

    public boolean isMarkedNullable() {
        return this.isMarkedNullable;
    }

    @NotNull
    public MemberScope getMemberScope() {
        return this.memberScope;
    }

    public SimpleTypeImpl(@NotNull TypeConstructor typeConstructor, @NotNull List<? extends TypeProjection> list, boolean z, @NotNull MemberScope memberScope2) {
        Intrinsics.checkParameterIsNotNull(typeConstructor, "constructor");
        Intrinsics.checkParameterIsNotNull(list, "arguments");
        Intrinsics.checkParameterIsNotNull(memberScope2, "memberScope");
        this.constructor = typeConstructor;
        this.arguments = list;
        this.isMarkedNullable = z;
        this.memberScope = memberScope2;
        if (getMemberScope() instanceof ErrorScope) {
            StringBuilder sb = new StringBuilder();
            sb.append("SimpleTypeImpl should not be created for error type: ");
            sb.append(getMemberScope());
            sb.append(10);
            sb.append(getConstructor());
            throw new IllegalStateException(sb.toString());
        }
    }

    @NotNull
    public Annotations getAnnotations() {
        return Annotations.Companion.getEMPTY();
    }

    @NotNull
    public SimpleType replaceAnnotations(@NotNull Annotations annotations) {
        Intrinsics.checkParameterIsNotNull(annotations, "newAnnotations");
        if (annotations.isEmpty()) {
            return this;
        }
        return new AnnotatedSimpleType(this, annotations);
    }

    @NotNull
    public SimpleType makeNullableAsSpecified(boolean z) {
        DelegatingSimpleTypeImpl delegatingSimpleTypeImpl;
        if (z == isMarkedNullable()) {
            return this;
        }
        if (z) {
            delegatingSimpleTypeImpl = new NullableSimpleType(this);
        } else {
            delegatingSimpleTypeImpl = new NotNullSimpleType(this);
        }
        return delegatingSimpleTypeImpl;
    }
}
