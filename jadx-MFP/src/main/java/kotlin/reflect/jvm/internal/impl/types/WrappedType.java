package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.TypeCastException;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import org.jetbrains.annotations.NotNull;

/* compiled from: KotlinType.kt */
public abstract class WrappedType extends KotlinType {
    /* access modifiers changed from: protected */
    @NotNull
    public abstract KotlinType getDelegate();

    public boolean isComputed() {
        return true;
    }

    public WrappedType() {
        super(null);
    }

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

    @NotNull
    public final UnwrappedType unwrap() {
        KotlinType delegate = getDelegate();
        while (delegate instanceof WrappedType) {
            delegate = ((WrappedType) delegate).getDelegate();
        }
        if (delegate != null) {
            return (UnwrappedType) delegate;
        }
        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.types.UnwrappedType");
    }

    @NotNull
    public String toString() {
        return isComputed() ? getDelegate().toString() : "<Not computed yet>";
    }
}
