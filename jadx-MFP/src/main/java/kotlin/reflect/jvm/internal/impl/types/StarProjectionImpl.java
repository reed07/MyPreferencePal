package kotlin.reflect.jvm.internal.impl.types;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import org.jetbrains.annotations.NotNull;

/* compiled from: StarProjectionImpl.kt */
public final class StarProjectionImpl extends TypeProjectionBase {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(StarProjectionImpl.class), "_type", "get_type()Lorg/jetbrains/kotlin/types/KotlinType;"))};
    private final Lazy _type$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0<? extends T>) new StarProjectionImpl$_type$2<Object>(this));
    /* access modifiers changed from: private */
    public final TypeParameterDescriptor typeParameter;

    private final KotlinType get_type() {
        Lazy lazy = this._type$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (KotlinType) lazy.getValue();
    }

    public boolean isStarProjection() {
        return true;
    }

    public StarProjectionImpl(@NotNull TypeParameterDescriptor typeParameterDescriptor) {
        Intrinsics.checkParameterIsNotNull(typeParameterDescriptor, "typeParameter");
        this.typeParameter = typeParameterDescriptor;
    }

    @NotNull
    public Variance getProjectionKind() {
        return Variance.OUT_VARIANCE;
    }

    @NotNull
    public KotlinType getType() {
        return get_type();
    }
}
