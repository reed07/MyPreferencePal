package kotlin.reflect.jvm.internal.impl.types;

import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import org.jetbrains.annotations.NotNull;

/* compiled from: KotlinType.kt */
public abstract class UnwrappedType extends KotlinType {
    @NotNull
    public abstract UnwrappedType makeNullableAsSpecified(boolean z);

    @NotNull
    public abstract UnwrappedType replaceAnnotations(@NotNull Annotations annotations);

    @NotNull
    public final UnwrappedType unwrap() {
        return this;
    }

    private UnwrappedType() {
        super(null);
    }

    public /* synthetic */ UnwrappedType(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
