package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.components.TypeUsage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JavaTypeResolver.kt */
public final class JavaTypeAttributes {
    @NotNull
    private final JavaTypeFlexibility flexibility;
    @NotNull
    private final TypeUsage howThisTypeIsUsed;
    private final boolean isForAnnotationParameter;
    @Nullable
    private final TypeParameterDescriptor upperBoundOfTypeParameter;

    @NotNull
    public static /* synthetic */ JavaTypeAttributes copy$default(JavaTypeAttributes javaTypeAttributes, TypeUsage typeUsage, JavaTypeFlexibility javaTypeFlexibility, boolean z, TypeParameterDescriptor typeParameterDescriptor, int i, Object obj) {
        if ((i & 1) != 0) {
            typeUsage = javaTypeAttributes.howThisTypeIsUsed;
        }
        if ((i & 2) != 0) {
            javaTypeFlexibility = javaTypeAttributes.flexibility;
        }
        if ((i & 4) != 0) {
            z = javaTypeAttributes.isForAnnotationParameter;
        }
        if ((i & 8) != 0) {
            typeParameterDescriptor = javaTypeAttributes.upperBoundOfTypeParameter;
        }
        return javaTypeAttributes.copy(typeUsage, javaTypeFlexibility, z, typeParameterDescriptor);
    }

    @NotNull
    public final JavaTypeAttributes copy(@NotNull TypeUsage typeUsage, @NotNull JavaTypeFlexibility javaTypeFlexibility, boolean z, @Nullable TypeParameterDescriptor typeParameterDescriptor) {
        Intrinsics.checkParameterIsNotNull(typeUsage, "howThisTypeIsUsed");
        Intrinsics.checkParameterIsNotNull(javaTypeFlexibility, "flexibility");
        return new JavaTypeAttributes(typeUsage, javaTypeFlexibility, z, typeParameterDescriptor);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof JavaTypeAttributes) {
                JavaTypeAttributes javaTypeAttributes = (JavaTypeAttributes) obj;
                if (Intrinsics.areEqual((Object) this.howThisTypeIsUsed, (Object) javaTypeAttributes.howThisTypeIsUsed) && Intrinsics.areEqual((Object) this.flexibility, (Object) javaTypeAttributes.flexibility)) {
                    if (!(this.isForAnnotationParameter == javaTypeAttributes.isForAnnotationParameter) || !Intrinsics.areEqual((Object) this.upperBoundOfTypeParameter, (Object) javaTypeAttributes.upperBoundOfTypeParameter)) {
                        return false;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        TypeUsage typeUsage = this.howThisTypeIsUsed;
        int i = 0;
        int hashCode = (typeUsage != null ? typeUsage.hashCode() : 0) * 31;
        JavaTypeFlexibility javaTypeFlexibility = this.flexibility;
        int hashCode2 = (hashCode + (javaTypeFlexibility != null ? javaTypeFlexibility.hashCode() : 0)) * 31;
        boolean z = this.isForAnnotationParameter;
        if (z) {
            z = true;
        }
        int i2 = (hashCode2 + (z ? 1 : 0)) * 31;
        TypeParameterDescriptor typeParameterDescriptor = this.upperBoundOfTypeParameter;
        if (typeParameterDescriptor != null) {
            i = typeParameterDescriptor.hashCode();
        }
        return i2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("JavaTypeAttributes(howThisTypeIsUsed=");
        sb.append(this.howThisTypeIsUsed);
        sb.append(", flexibility=");
        sb.append(this.flexibility);
        sb.append(", isForAnnotationParameter=");
        sb.append(this.isForAnnotationParameter);
        sb.append(", upperBoundOfTypeParameter=");
        sb.append(this.upperBoundOfTypeParameter);
        sb.append(")");
        return sb.toString();
    }

    public JavaTypeAttributes(@NotNull TypeUsage typeUsage, @NotNull JavaTypeFlexibility javaTypeFlexibility, boolean z, @Nullable TypeParameterDescriptor typeParameterDescriptor) {
        Intrinsics.checkParameterIsNotNull(typeUsage, "howThisTypeIsUsed");
        Intrinsics.checkParameterIsNotNull(javaTypeFlexibility, "flexibility");
        this.howThisTypeIsUsed = typeUsage;
        this.flexibility = javaTypeFlexibility;
        this.isForAnnotationParameter = z;
        this.upperBoundOfTypeParameter = typeParameterDescriptor;
    }

    @NotNull
    public final TypeUsage getHowThisTypeIsUsed() {
        return this.howThisTypeIsUsed;
    }

    public /* synthetic */ JavaTypeAttributes(TypeUsage typeUsage, JavaTypeFlexibility javaTypeFlexibility, boolean z, TypeParameterDescriptor typeParameterDescriptor, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 2) != 0) {
            javaTypeFlexibility = JavaTypeFlexibility.INFLEXIBLE;
        }
        if ((i & 4) != 0) {
            z = false;
        }
        if ((i & 8) != 0) {
            typeParameterDescriptor = null;
        }
        this(typeUsage, javaTypeFlexibility, z, typeParameterDescriptor);
    }

    @NotNull
    public final JavaTypeFlexibility getFlexibility() {
        return this.flexibility;
    }

    public final boolean isForAnnotationParameter() {
        return this.isForAnnotationParameter;
    }

    @Nullable
    public final TypeParameterDescriptor getUpperBoundOfTypeParameter() {
        return this.upperBoundOfTypeParameter;
    }

    @NotNull
    public final JavaTypeAttributes withFlexibility(@NotNull JavaTypeFlexibility javaTypeFlexibility) {
        Intrinsics.checkParameterIsNotNull(javaTypeFlexibility, "flexibility");
        return copy$default(this, null, javaTypeFlexibility, false, null, 13, null);
    }
}
