package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import java.util.EnumMap;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver.QualifierApplicabilityType;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: context.kt */
public final class JavaTypeQualifiersByElementType {
    @NotNull
    private final EnumMap<QualifierApplicabilityType, NullabilityQualifierWithMigrationStatus> nullabilityQualifiers;

    public JavaTypeQualifiersByElementType(@NotNull EnumMap<QualifierApplicabilityType, NullabilityQualifierWithMigrationStatus> enumMap) {
        Intrinsics.checkParameterIsNotNull(enumMap, "nullabilityQualifiers");
        this.nullabilityQualifiers = enumMap;
    }

    @NotNull
    public final EnumMap<QualifierApplicabilityType, NullabilityQualifierWithMigrationStatus> getNullabilityQualifiers$descriptors_jvm() {
        return this.nullabilityQualifiers;
    }

    @Nullable
    public final JavaTypeQualifiers get(@Nullable QualifierApplicabilityType qualifierApplicabilityType) {
        NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus = (NullabilityQualifierWithMigrationStatus) this.nullabilityQualifiers.get(qualifierApplicabilityType);
        if (nullabilityQualifierWithMigrationStatus == null) {
            return null;
        }
        Intrinsics.checkExpressionValueIsNotNull(nullabilityQualifierWithMigrationStatus, "nullabilityQualifiers[ap…ilityType] ?: return null");
        return new JavaTypeQualifiers(nullabilityQualifierWithMigrationStatus.getQualifier(), null, false, nullabilityQualifierWithMigrationStatus.isForWarningOnly());
    }
}
