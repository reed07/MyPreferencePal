package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.List;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: NewCapturedType.kt */
public final class NewCapturedTypeConstructor implements TypeConstructor {
    @NotNull
    private final TypeProjection projection;
    private List<? extends UnwrappedType> supertypes;

    @Nullable
    public ClassifierDescriptor getDeclarationDescriptor() {
        return null;
    }

    public boolean isDenotable() {
        return false;
    }

    public NewCapturedTypeConstructor(@NotNull TypeProjection typeProjection, @Nullable List<? extends UnwrappedType> list) {
        Intrinsics.checkParameterIsNotNull(typeProjection, "projection");
        this.projection = typeProjection;
        this.supertypes = list;
    }

    public /* synthetic */ NewCapturedTypeConstructor(TypeProjection typeProjection, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 2) != 0) {
            list = null;
        }
        this(typeProjection, list);
    }

    public final void initializeSupertypes(@NotNull List<? extends UnwrappedType> list) {
        Intrinsics.checkParameterIsNotNull(list, "supertypes");
        boolean z = this.supertypes == null;
        if (!_Assertions.ENABLED || z) {
            this.supertypes = list;
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Already initialized! oldValue = ");
        sb.append(this.supertypes);
        sb.append(", newValue = ");
        sb.append(list);
        throw new AssertionError(sb.toString());
    }

    @NotNull
    public List<UnwrappedType> getSupertypes() {
        List<? extends UnwrappedType> list = this.supertypes;
        return list != null ? list : CollectionsKt.emptyList();
    }

    @NotNull
    public List<TypeParameterDescriptor> getParameters() {
        return CollectionsKt.emptyList();
    }

    @NotNull
    public KotlinBuiltIns getBuiltIns() {
        KotlinType type = this.projection.getType();
        Intrinsics.checkExpressionValueIsNotNull(type, "projection.type");
        return TypeUtilsKt.getBuiltIns(type);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CapturedType(");
        sb.append(this.projection);
        sb.append(')');
        return sb.toString();
    }
}
