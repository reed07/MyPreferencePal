package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.NullabilityQualifierWithApplicability;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.utils.Jsr305State;
import kotlin.reflect.jvm.internal.impl.utils.ReportLevel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AnnotationTypeQualifierResolver.kt */
public final class AnnotationTypeQualifierResolver {
    private final boolean disabled = this.jsr305State.getDisabled();
    private final Jsr305State jsr305State;
    private final MemoizedFunctionToNullable<ClassDescriptor, AnnotationDescriptor> resolvedNicknames;

    /* compiled from: AnnotationTypeQualifierResolver.kt */
    public enum QualifierApplicabilityType {
        METHOD_RETURN_TYPE,
        VALUE_PARAMETER,
        FIELD,
        TYPE_USE
    }

    /* compiled from: AnnotationTypeQualifierResolver.kt */
    public static final class TypeQualifierWithApplicability {
        private final int applicability;
        private final AnnotationDescriptor typeQualifier;

        public TypeQualifierWithApplicability(@NotNull AnnotationDescriptor annotationDescriptor, int i) {
            Intrinsics.checkParameterIsNotNull(annotationDescriptor, "typeQualifier");
            this.typeQualifier = annotationDescriptor;
            this.applicability = i;
        }

        @NotNull
        public final AnnotationDescriptor component1() {
            return this.typeQualifier;
        }

        @NotNull
        public final List<QualifierApplicabilityType> component2() {
            QualifierApplicabilityType[] values = QualifierApplicabilityType.values();
            Collection arrayList = new ArrayList();
            for (QualifierApplicabilityType qualifierApplicabilityType : values) {
                if (isApplicableTo(qualifierApplicabilityType)) {
                    arrayList.add(qualifierApplicabilityType);
                }
            }
            return (List) arrayList;
        }

        private final boolean isApplicableTo(QualifierApplicabilityType qualifierApplicabilityType) {
            return isApplicableConsideringMask(QualifierApplicabilityType.TYPE_USE) || isApplicableConsideringMask(qualifierApplicabilityType);
        }

        private final boolean isApplicableConsideringMask(QualifierApplicabilityType qualifierApplicabilityType) {
            return ((1 << qualifierApplicabilityType.ordinal()) & this.applicability) != 0;
        }
    }

    public AnnotationTypeQualifierResolver(@NotNull StorageManager storageManager, @NotNull Jsr305State jsr305State2) {
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(jsr305State2, "jsr305State");
        this.jsr305State = jsr305State2;
        this.resolvedNicknames = storageManager.createMemoizedFunctionWithNullableValues(new AnnotationTypeQualifierResolver$resolvedNicknames$1(this));
    }

    /* access modifiers changed from: private */
    public final AnnotationDescriptor computeTypeQualifierNickname(ClassDescriptor classDescriptor) {
        AnnotationDescriptor annotationDescriptor;
        if (!classDescriptor.getAnnotations().hasAnnotation(AnnotationTypeQualifierResolverKt.TYPE_QUALIFIER_NICKNAME_FQNAME)) {
            return null;
        }
        Iterator it = classDescriptor.getAnnotations().iterator();
        while (true) {
            if (!it.hasNext()) {
                annotationDescriptor = null;
                break;
            }
            annotationDescriptor = resolveTypeQualifierAnnotation((AnnotationDescriptor) it.next());
            if (annotationDescriptor != null) {
                break;
            }
        }
        return annotationDescriptor;
    }

    private final AnnotationDescriptor resolveTypeQualifierNickname(ClassDescriptor classDescriptor) {
        if (classDescriptor.getKind() != ClassKind.ANNOTATION_CLASS) {
            return null;
        }
        return (AnnotationDescriptor) this.resolvedNicknames.invoke(classDescriptor);
    }

    @Nullable
    public final AnnotationDescriptor resolveTypeQualifierAnnotation(@NotNull AnnotationDescriptor annotationDescriptor) {
        Intrinsics.checkParameterIsNotNull(annotationDescriptor, "annotationDescriptor");
        if (this.jsr305State.getDisabled()) {
            return null;
        }
        ClassDescriptor annotationClass = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor);
        if (annotationClass == null) {
            return null;
        }
        if (AnnotationTypeQualifierResolverKt.isAnnotatedWithTypeQualifier(annotationClass)) {
            return annotationDescriptor;
        }
        return resolveTypeQualifierNickname(annotationClass);
    }

    @Nullable
    public final NullabilityQualifierWithApplicability resolveQualifierBuiltInDefaultAnnotation(@NotNull AnnotationDescriptor annotationDescriptor) {
        Intrinsics.checkParameterIsNotNull(annotationDescriptor, "annotationDescriptor");
        if (this.jsr305State.getDisabled()) {
            return null;
        }
        NullabilityQualifierWithApplicability nullabilityQualifierWithApplicability = (NullabilityQualifierWithApplicability) AnnotationTypeQualifierResolverKt.BUILT_IN_TYPE_QUALIFIER_DEFAULT_ANNOTATIONS.get(annotationDescriptor.getFqName());
        if (nullabilityQualifierWithApplicability == null) {
            return null;
        }
        NullabilityQualifierWithMigrationStatus component1 = nullabilityQualifierWithApplicability.component1();
        Collection component2 = nullabilityQualifierWithApplicability.component2();
        ReportLevel resolveJsr305AnnotationState = resolveJsr305AnnotationState(annotationDescriptor);
        if (!(resolveJsr305AnnotationState != ReportLevel.IGNORE)) {
            resolveJsr305AnnotationState = null;
        }
        if (resolveJsr305AnnotationState != null) {
            return new NullabilityQualifierWithApplicability(NullabilityQualifierWithMigrationStatus.copy$default(component1, null, resolveJsr305AnnotationState.isWarning(), 1, null), component2);
        }
        return null;
    }

    @Nullable
    public final TypeQualifierWithApplicability resolveTypeQualifierDefaultAnnotation(@NotNull AnnotationDescriptor annotationDescriptor) {
        Object obj;
        boolean z;
        List list;
        Intrinsics.checkParameterIsNotNull(annotationDescriptor, "annotationDescriptor");
        if (this.jsr305State.getDisabled()) {
            return null;
        }
        ClassDescriptor annotationClass = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor);
        if (annotationClass != null) {
            if (!annotationClass.getAnnotations().hasAnnotation(AnnotationTypeQualifierResolverKt.TYPE_QUALIFIER_DEFAULT_FQNAME)) {
                annotationClass = null;
            }
            if (annotationClass != null) {
                ClassDescriptor annotationClass2 = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor);
                if (annotationClass2 == null) {
                    Intrinsics.throwNpe();
                }
                AnnotationDescriptor findAnnotation = annotationClass2.getAnnotations().findAnnotation(AnnotationTypeQualifierResolverKt.TYPE_QUALIFIER_DEFAULT_FQNAME);
                if (findAnnotation == null) {
                    Intrinsics.throwNpe();
                }
                Map allValueArguments = findAnnotation.getAllValueArguments();
                Collection arrayList = new ArrayList();
                for (Entry entry : allValueArguments.entrySet()) {
                    Name name = (Name) entry.getKey();
                    ConstantValue constantValue = (ConstantValue) entry.getValue();
                    if (Intrinsics.areEqual((Object) name, (Object) JvmAnnotationNames.DEFAULT_ANNOTATION_MEMBER_NAME)) {
                        list = mapConstantToQualifierApplicabilityTypes(constantValue);
                    } else {
                        list = CollectionsKt.emptyList();
                    }
                    CollectionsKt.addAll(arrayList, (Iterable<? extends T>) list);
                }
                int i = 0;
                for (QualifierApplicabilityType ordinal : (List) arrayList) {
                    i |= 1 << ordinal.ordinal();
                }
                Iterator it = annotationClass.getAnnotations().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    }
                    obj = it.next();
                    if (resolveTypeQualifierAnnotation((AnnotationDescriptor) obj) != null) {
                        z = true;
                        continue;
                    } else {
                        z = false;
                        continue;
                    }
                    if (z) {
                        break;
                    }
                }
                AnnotationDescriptor annotationDescriptor2 = (AnnotationDescriptor) obj;
                if (annotationDescriptor2 != null) {
                    return new TypeQualifierWithApplicability(annotationDescriptor2, i);
                }
                return null;
            }
        }
        return null;
    }

    @NotNull
    public final ReportLevel resolveJsr305AnnotationState(@NotNull AnnotationDescriptor annotationDescriptor) {
        Intrinsics.checkParameterIsNotNull(annotationDescriptor, "annotationDescriptor");
        ReportLevel resolveJsr305CustomState = resolveJsr305CustomState(annotationDescriptor);
        if (resolveJsr305CustomState != null) {
            return resolveJsr305CustomState;
        }
        return this.jsr305State.getGlobal();
    }

    @Nullable
    public final ReportLevel resolveJsr305CustomState(@NotNull AnnotationDescriptor annotationDescriptor) {
        Intrinsics.checkParameterIsNotNull(annotationDescriptor, "annotationDescriptor");
        Map user = this.jsr305State.getUser();
        FqName fqName = annotationDescriptor.getFqName();
        ReportLevel reportLevel = null;
        ReportLevel reportLevel2 = (ReportLevel) user.get(fqName != null ? fqName.asString() : null);
        if (reportLevel2 != null) {
            return reportLevel2;
        }
        ClassDescriptor annotationClass = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor);
        if (annotationClass != null) {
            reportLevel = migrationAnnotationStatus(annotationClass);
        }
        return reportLevel;
    }

    private final ReportLevel migrationAnnotationStatus(@NotNull ClassDescriptor classDescriptor) {
        AnnotationDescriptor findAnnotation = classDescriptor.getAnnotations().findAnnotation(AnnotationTypeQualifierResolverKt.MIGRATION_ANNOTATION_FQNAME);
        ReportLevel reportLevel = null;
        ConstantValue firstArgument = findAnnotation != null ? DescriptorUtilsKt.firstArgument(findAnnotation) : null;
        if (!(firstArgument instanceof EnumValue)) {
            firstArgument = null;
        }
        EnumValue enumValue = (EnumValue) firstArgument;
        if (enumValue == null) {
            return null;
        }
        ReportLevel migration = this.jsr305State.getMigration();
        if (migration != null) {
            return migration;
        }
        String asString = enumValue.getEnumEntryName().asString();
        int hashCode = asString.hashCode();
        if (hashCode != -2137067054) {
            if (hashCode != -1838656823) {
                if (hashCode == 2656902 && asString.equals("WARN")) {
                    reportLevel = ReportLevel.WARN;
                }
            } else if (asString.equals("STRICT")) {
                reportLevel = ReportLevel.STRICT;
            }
        } else if (asString.equals("IGNORE")) {
            reportLevel = ReportLevel.IGNORE;
        }
        return reportLevel;
    }

    private final List<QualifierApplicabilityType> mapConstantToQualifierApplicabilityTypes(@NotNull ConstantValue<?> constantValue) {
        QualifierApplicabilityType qualifierApplicabilityType;
        if (constantValue instanceof ArrayValue) {
            Iterable<ConstantValue> iterable = (Iterable) ((ArrayValue) constantValue).getValue();
            Collection arrayList = new ArrayList();
            for (ConstantValue mapConstantToQualifierApplicabilityTypes : iterable) {
                CollectionsKt.addAll(arrayList, (Iterable<? extends T>) mapConstantToQualifierApplicabilityTypes(mapConstantToQualifierApplicabilityTypes));
            }
            return (List) arrayList;
        } else if (!(constantValue instanceof EnumValue)) {
            return CollectionsKt.emptyList();
        } else {
            String identifier = ((EnumValue) constantValue).getEnumEntryName().getIdentifier();
            int hashCode = identifier.hashCode();
            if (hashCode != -2024225567) {
                if (hashCode != 66889946) {
                    if (hashCode != 107598562) {
                        if (hashCode == 446088073 && identifier.equals("PARAMETER")) {
                            qualifierApplicabilityType = QualifierApplicabilityType.VALUE_PARAMETER;
                            return CollectionsKt.listOfNotNull(qualifierApplicabilityType);
                        }
                    } else if (identifier.equals("TYPE_USE")) {
                        qualifierApplicabilityType = QualifierApplicabilityType.TYPE_USE;
                        return CollectionsKt.listOfNotNull(qualifierApplicabilityType);
                    }
                } else if (identifier.equals("FIELD")) {
                    qualifierApplicabilityType = QualifierApplicabilityType.FIELD;
                    return CollectionsKt.listOfNotNull(qualifierApplicabilityType);
                }
            } else if (identifier.equals("METHOD")) {
                qualifierApplicabilityType = QualifierApplicabilityType.METHOD_RETURN_TYPE;
                return CollectionsKt.listOfNotNull(qualifierApplicabilityType);
            }
            qualifierApplicabilityType = null;
            return CollectionsKt.listOfNotNull(qualifierApplicabilityType);
        }
    }

    public final boolean getDisabled() {
        return this.disabled;
    }
}
