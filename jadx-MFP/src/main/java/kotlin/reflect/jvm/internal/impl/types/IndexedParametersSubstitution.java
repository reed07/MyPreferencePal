package kotlin.reflect.jvm.internal.impl.types;

import com.samsung.android.sdk.internal.healthdata.IpcUtil;
import java.util.List;
import kotlin.TypeCastException;
import kotlin._Assertions;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: TypeSubstitution.kt */
public final class IndexedParametersSubstitution extends TypeSubstitution {
    private final boolean approximateCapturedTypes;
    @NotNull
    private final TypeProjection[] arguments;
    @NotNull
    private final TypeParameterDescriptor[] parameters;

    @NotNull
    public final TypeParameterDescriptor[] getParameters() {
        return this.parameters;
    }

    @NotNull
    public final TypeProjection[] getArguments() {
        return this.arguments;
    }

    public /* synthetic */ IndexedParametersSubstitution(TypeParameterDescriptor[] typeParameterDescriptorArr, TypeProjection[] typeProjectionArr, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 4) != 0) {
            z = false;
        }
        this(typeParameterDescriptorArr, typeProjectionArr, z);
    }

    public IndexedParametersSubstitution(@NotNull TypeParameterDescriptor[] typeParameterDescriptorArr, @NotNull TypeProjection[] typeProjectionArr, boolean z) {
        Intrinsics.checkParameterIsNotNull(typeParameterDescriptorArr, "parameters");
        Intrinsics.checkParameterIsNotNull(typeProjectionArr, "arguments");
        this.parameters = typeParameterDescriptorArr;
        this.arguments = typeProjectionArr;
        this.approximateCapturedTypes = z;
        boolean z2 = this.parameters.length <= this.arguments.length;
        if (_Assertions.ENABLED && !z2) {
            StringBuilder sb = new StringBuilder();
            sb.append("Number of arguments should not be less then number of parameters, but: parameters=");
            sb.append(this.parameters.length);
            sb.append(", args=");
            sb.append(this.arguments.length);
            throw new AssertionError(sb.toString());
        }
    }

    public IndexedParametersSubstitution(@NotNull List<? extends TypeParameterDescriptor> list, @NotNull List<? extends TypeProjection> list2) {
        Intrinsics.checkParameterIsNotNull(list, "parameters");
        Intrinsics.checkParameterIsNotNull(list2, "argumentsList");
        Object[] array = list.toArray(new TypeParameterDescriptor[0]);
        if (array != null) {
            TypeParameterDescriptor[] typeParameterDescriptorArr = (TypeParameterDescriptor[]) array;
            Object[] array2 = list2.toArray(new TypeProjection[0]);
            if (array2 != null) {
                this(typeParameterDescriptorArr, (TypeProjection[]) array2, false, 4, null);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public boolean isEmpty() {
        return this.arguments.length == 0;
    }

    public boolean approximateContravariantCapturedTypes() {
        return this.approximateCapturedTypes;
    }

    @Nullable
    public TypeProjection get(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, IpcUtil.KEY_CODE);
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        if (!(declarationDescriptor instanceof TypeParameterDescriptor)) {
            declarationDescriptor = null;
        }
        TypeParameterDescriptor typeParameterDescriptor = (TypeParameterDescriptor) declarationDescriptor;
        if (typeParameterDescriptor == null) {
            return null;
        }
        int index = typeParameterDescriptor.getIndex();
        TypeParameterDescriptor[] typeParameterDescriptorArr = this.parameters;
        if (index >= typeParameterDescriptorArr.length || !Intrinsics.areEqual((Object) typeParameterDescriptorArr[index].getTypeConstructor(), (Object) typeParameterDescriptor.getTypeConstructor())) {
            return null;
        }
        return this.arguments[index];
    }
}
