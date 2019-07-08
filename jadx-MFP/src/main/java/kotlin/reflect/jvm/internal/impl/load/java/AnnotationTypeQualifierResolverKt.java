package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.Map;
import java.util.Set;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver.QualifierApplicabilityType;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.NullabilityQualifierWithApplicability;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: AnnotationTypeQualifierResolver.kt */
public final class AnnotationTypeQualifierResolverKt {
    /* access modifiers changed from: private */
    public static final Map<FqName, NullabilityQualifierWithApplicability> BUILT_IN_TYPE_QUALIFIER_DEFAULT_ANNOTATIONS = MapsKt.mapOf(TuplesKt.to(new FqName("javax.annotation.ParametersAreNullableByDefault"), new NullabilityQualifierWithApplicability(new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NULLABLE, false, 2, null), CollectionsKt.listOf(QualifierApplicabilityType.VALUE_PARAMETER))), TuplesKt.to(new FqName("javax.annotation.ParametersAreNonnullByDefault"), new NullabilityQualifierWithApplicability(new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, false, 2, null), CollectionsKt.listOf(QualifierApplicabilityType.VALUE_PARAMETER))));
    @NotNull
    private static final Set<FqName> BUILT_IN_TYPE_QUALIFIER_FQ_NAMES = SetsKt.setOf(JvmAnnotationNamesKt.getJAVAX_NONNULL_ANNOTATION(), JvmAnnotationNamesKt.getJAVAX_CHECKFORNULL_ANNOTATION());
    /* access modifiers changed from: private */
    public static final FqName MIGRATION_ANNOTATION_FQNAME = new FqName("kotlin.annotations.jvm.UnderMigration");
    /* access modifiers changed from: private */
    public static final FqName TYPE_QUALIFIER_DEFAULT_FQNAME = new FqName("javax.annotation.meta.TypeQualifierDefault");
    private static final FqName TYPE_QUALIFIER_FQNAME = new FqName("javax.annotation.meta.TypeQualifier");
    /* access modifiers changed from: private */
    public static final FqName TYPE_QUALIFIER_NICKNAME_FQNAME = new FqName("javax.annotation.meta.TypeQualifierNickname");

    /* access modifiers changed from: private */
    public static final boolean isAnnotatedWithTypeQualifier(@NotNull ClassDescriptor classDescriptor) {
        return BUILT_IN_TYPE_QUALIFIER_FQ_NAMES.contains(DescriptorUtilsKt.getFqNameSafe(classDescriptor)) || classDescriptor.getAnnotations().hasAnnotation(TYPE_QUALIFIER_FQNAME);
    }
}
