package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import org.jetbrains.annotations.NotNull;

/* compiled from: SpecialTypes.kt */
public abstract class DelegatingSimpleType extends SimpleType {
    /* access modifiers changed from: protected */
    @NotNull
    public abstract SimpleType getDelegate();

    @NotNull
    public Annotations getAnnotations() {
        return getDelegate().getAnnotations();
    }

    @NotNull
    public TypeConstructor getConstructor() {
        return getDelegate().getConstructor();
    }

    @NotNull
    public List<TypeProjection> getArguments() {
        return getDelegate().getArguments();
    }

    public boolean isMarkedNullable() {
        return getDelegate().isMarkedNullable();
    }

    @NotNull
    public MemberScope getMemberScope() {
        return getDelegate().getMemberScope();
    }
}
