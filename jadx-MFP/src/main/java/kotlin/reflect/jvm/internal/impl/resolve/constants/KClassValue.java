package kotlin.reflect.jvm.internal.impl.resolve.constants;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import org.jetbrains.annotations.NotNull;

/* compiled from: constantValues.kt */
public final class KClassValue extends ConstantValue<KotlinType> {
    private final KotlinType type;

    public KClassValue(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "type");
        super(kotlinType);
        this.type = kotlinType;
    }

    @NotNull
    public KotlinType getType(@NotNull ModuleDescriptor moduleDescriptor) {
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "module");
        return this.type;
    }

    @NotNull
    public KotlinType getValue() {
        KotlinType type2 = ((TypeProjection) CollectionsKt.single(this.type.getArguments())).getType();
        Intrinsics.checkExpressionValueIsNotNull(type2, "type.arguments.single().type");
        return type2;
    }
}
