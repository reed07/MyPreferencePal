package kotlin.reflect.jvm.internal.impl.resolve.constants;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: IntegerValueTypeConstructor.kt */
public final class IntegerValueTypeConstructor implements TypeConstructor {
    private final ModuleDescriptor module;
    private final ArrayList<KotlinType> supertypes;
    private final long value;

    @Nullable
    public Void getDeclarationDescriptor() {
        return null;
    }

    public boolean isDenotable() {
        return false;
    }

    @NotNull
    public Collection<KotlinType> getSupertypes() {
        return this.supertypes;
    }

    @NotNull
    public List<TypeParameterDescriptor> getParameters() {
        return CollectionsKt.emptyList();
    }

    @NotNull
    public KotlinBuiltIns getBuiltIns() {
        return this.module.getBuiltIns();
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("IntegerValueType(");
        sb.append(this.value);
        sb.append(')');
        return sb.toString();
    }
}
