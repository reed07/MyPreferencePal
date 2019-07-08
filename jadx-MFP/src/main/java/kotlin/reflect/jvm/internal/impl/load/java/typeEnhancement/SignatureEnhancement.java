package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver.QualifierApplicabilityType;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNamesKt;
import kotlin.reflect.jvm.internal.impl.load.java.UtilsKt;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.AnnotationDefaultValue;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.NullDefaultValue;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.StringDefaultValue;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.UtilKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.ContextKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.utils.Jsr305State;
import kotlin.reflect.jvm.internal.impl.utils.ReportLevel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: signatureEnhancement.kt */
public final class SignatureEnhancement {
    private final AnnotationTypeQualifierResolver annotationTypeQualifierResolver;
    private final Jsr305State jsr305State;

    /* compiled from: signatureEnhancement.kt */
    private static class PartEnhancementResult {
        private final boolean containsFunctionN;
        @NotNull
        private final KotlinType type;
        private final boolean wereChanges;

        public PartEnhancementResult(@NotNull KotlinType kotlinType, boolean z, boolean z2) {
            Intrinsics.checkParameterIsNotNull(kotlinType, "type");
            this.type = kotlinType;
            this.wereChanges = z;
            this.containsFunctionN = z2;
        }

        @NotNull
        public final KotlinType getType() {
            return this.type;
        }

        public final boolean getWereChanges() {
            return this.wereChanges;
        }

        public final boolean getContainsFunctionN() {
            return this.containsFunctionN;
        }
    }

    /* compiled from: signatureEnhancement.kt */
    private final class SignatureParts {
        private final QualifierApplicabilityType containerApplicabilityType;
        private final LazyJavaResolverContext containerContext;
        private final Collection<KotlinType> fromOverridden;
        private final KotlinType fromOverride;
        private final boolean isCovariant;
        final /* synthetic */ SignatureEnhancement this$0;
        private final Annotated typeContainer;

        public SignatureParts(SignatureEnhancement signatureEnhancement, @Nullable Annotated annotated, @NotNull KotlinType kotlinType, @NotNull Collection<? extends KotlinType> collection, boolean z, @NotNull LazyJavaResolverContext lazyJavaResolverContext, @NotNull QualifierApplicabilityType qualifierApplicabilityType) {
            Intrinsics.checkParameterIsNotNull(kotlinType, "fromOverride");
            Intrinsics.checkParameterIsNotNull(collection, "fromOverridden");
            Intrinsics.checkParameterIsNotNull(lazyJavaResolverContext, "containerContext");
            Intrinsics.checkParameterIsNotNull(qualifierApplicabilityType, "containerApplicabilityType");
            this.this$0 = signatureEnhancement;
            this.typeContainer = annotated;
            this.fromOverride = kotlinType;
            this.fromOverridden = collection;
            this.isCovariant = z;
            this.containerContext = lazyJavaResolverContext;
            this.containerApplicabilityType = qualifierApplicabilityType;
        }

        private final boolean isForVarargParameter() {
            Annotated annotated = this.typeContainer;
            KotlinType kotlinType = null;
            if (!(annotated instanceof ValueParameterDescriptor)) {
                annotated = null;
            }
            ValueParameterDescriptor valueParameterDescriptor = (ValueParameterDescriptor) annotated;
            if (valueParameterDescriptor != null) {
                kotlinType = valueParameterDescriptor.getVarargElementType();
            }
            return kotlinType != null;
        }

        @NotNull
        public static /* synthetic */ PartEnhancementResult enhance$default(SignatureParts signatureParts, TypeEnhancementInfo typeEnhancementInfo, int i, Object obj) {
            if ((i & 1) != 0) {
                typeEnhancementInfo = null;
            }
            return signatureParts.enhance(typeEnhancementInfo);
        }

        @NotNull
        public final PartEnhancementResult enhance(@Nullable TypeEnhancementInfo typeEnhancementInfo) {
            Function1 computeIndexedQualifiersForOverride = computeIndexedQualifiersForOverride();
            Function1 signatureEnhancement$SignatureParts$enhance$$inlined$let$lambda$1 = typeEnhancementInfo != null ? new SignatureEnhancement$SignatureParts$enhance$$inlined$let$lambda$1(typeEnhancementInfo, computeIndexedQualifiersForOverride) : null;
            boolean contains = TypeUtils.contains(this.fromOverride, SignatureEnhancement$SignatureParts$enhance$containsFunctionN$1.INSTANCE);
            KotlinType kotlinType = this.fromOverride;
            if (signatureEnhancement$SignatureParts$enhance$$inlined$let$lambda$1 == null) {
                signatureEnhancement$SignatureParts$enhance$$inlined$let$lambda$1 = computeIndexedQualifiersForOverride;
            }
            KotlinType enhance = TypeEnhancementKt.enhance(kotlinType, signatureEnhancement$SignatureParts$enhance$$inlined$let$lambda$1);
            if (enhance != null) {
                return new PartEnhancementResult(enhance, true, contains);
            }
            return new PartEnhancementResult(this.fromOverride, false, contains);
        }

        private final JavaTypeQualifiers extractQualifiers(@NotNull KotlinType kotlinType) {
            Pair pair;
            if (FlexibleTypesKt.isFlexible(kotlinType)) {
                FlexibleType asFlexibleType = FlexibleTypesKt.asFlexibleType(kotlinType);
                pair = new Pair(asFlexibleType.getLowerBound(), asFlexibleType.getUpperBound());
            } else {
                pair = new Pair(kotlinType, kotlinType);
            }
            KotlinType kotlinType2 = (KotlinType) pair.component1();
            KotlinType kotlinType3 = (KotlinType) pair.component2();
            JavaToKotlinClassMap javaToKotlinClassMap = JavaToKotlinClassMap.INSTANCE;
            NullabilityQualifier nullabilityQualifier = kotlinType2.isMarkedNullable() ? NullabilityQualifier.NULLABLE : !kotlinType3.isMarkedNullable() ? NullabilityQualifier.NOT_NULL : null;
            MutabilityQualifier mutabilityQualifier = javaToKotlinClassMap.isReadOnly(kotlinType2) ? MutabilityQualifier.READ_ONLY : javaToKotlinClassMap.isMutable(kotlinType3) ? MutabilityQualifier.MUTABLE : null;
            JavaTypeQualifiers javaTypeQualifiers = new JavaTypeQualifiers(nullabilityQualifier, mutabilityQualifier, kotlinType.unwrap() instanceof NotNullTypeParameter, false, 8, null);
            return javaTypeQualifiers;
        }

        /* JADX WARNING: Removed duplicated region for block: B:13:0x0038  */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x0053  */
        /* JADX WARNING: Removed duplicated region for block: B:21:0x0058  */
        /* JADX WARNING: Removed duplicated region for block: B:24:0x0075  */
        /* JADX WARNING: Removed duplicated region for block: B:7:0x0021  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private final kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers extractQualifiersFromAnnotations(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.types.KotlinType r8, boolean r9, kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers r10) {
            /*
                r7 = this;
                if (r9 == 0) goto L_0x0013
                kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated r0 = r7.typeContainer
                if (r0 == 0) goto L_0x0013
                kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r0 = r0.getAnnotations()
                kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r1 = r8.getAnnotations()
                kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r0 = kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationsKt.composeAnnotations(r0, r1)
                goto L_0x0017
            L_0x0013:
                kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r0 = r8.getAnnotations()
            L_0x0017:
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$SignatureParts$extractQualifiersFromAnnotations$1 r1 = new kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$SignatureParts$extractQualifiersFromAnnotations$1
                r1.<init>(r0)
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$SignatureParts$extractQualifiersFromAnnotations$2 r2 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$SignatureParts$extractQualifiersFromAnnotations$2.INSTANCE
                r3 = 0
                if (r9 == 0) goto L_0x0031
                kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext r9 = r7.containerContext
                kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaTypeQualifiersByElementType r9 = r9.getDefaultTypeQualifiers()
                if (r9 == 0) goto L_0x0030
                kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver$QualifierApplicabilityType r10 = r7.containerApplicabilityType
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers r10 = r9.get(r10)
                goto L_0x0031
            L_0x0030:
                r10 = r3
            L_0x0031:
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus r9 = r7.extractNullability(r0)
                if (r9 == 0) goto L_0x0038
                goto L_0x004f
            L_0x0038:
                if (r10 == 0) goto L_0x004e
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier r9 = r10.getNullability()
                if (r9 == 0) goto L_0x004e
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus r9 = new kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier r0 = r10.getNullability()
                boolean r10 = r10.isNullabilityQualifierForWarning$descriptors_jvm()
                r9.<init>(r0, r10)
                goto L_0x004f
            L_0x004e:
                r9 = r3
            L_0x004f:
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers r10 = new kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers
                if (r9 == 0) goto L_0x0058
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier r0 = r9.getQualifier()
                goto L_0x0059
            L_0x0058:
                r0 = r3
            L_0x0059:
                java.util.List r4 = kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNamesKt.getREAD_ONLY_ANNOTATIONS()
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.MutabilityQualifier r5 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.MutabilityQualifier.READ_ONLY
                java.lang.Object r4 = r1.invoke(r4, r5)
                java.util.List r5 = kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNamesKt.getMUTABLE_ANNOTATIONS()
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.MutabilityQualifier r6 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.MutabilityQualifier.MUTABLE
                java.lang.Object r1 = r1.invoke(r5, r6)
                java.lang.Object r1 = r2.invoke(r4, r1)
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.MutabilityQualifier r1 = (kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.MutabilityQualifier) r1
                if (r9 == 0) goto L_0x0079
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier r3 = r9.getQualifier()
            L_0x0079:
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier r2 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier.NOT_NULL
                r4 = 0
                r5 = 1
                if (r3 != r2) goto L_0x0087
                boolean r8 = kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.isTypeParameter(r8)
                if (r8 == 0) goto L_0x0087
                r8 = 1
                goto L_0x0088
            L_0x0087:
                r8 = 0
            L_0x0088:
                if (r9 == 0) goto L_0x0091
                boolean r9 = r9.isForWarningOnly()
                if (r9 != r5) goto L_0x0091
                r4 = 1
            L_0x0091:
                r10.<init>(r0, r1, r8, r4)
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.SignatureParts.extractQualifiersFromAnnotations(kotlin.reflect.jvm.internal.impl.types.KotlinType, boolean, kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers):kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers");
        }

        private final NullabilityQualifierWithMigrationStatus extractNullability(@NotNull Annotations annotations) {
            Iterable<AnnotationDescriptor> iterable = annotations;
            SignatureEnhancement signatureEnhancement = this.this$0;
            for (AnnotationDescriptor extractNullability : iterable) {
                NullabilityQualifierWithMigrationStatus extractNullability2 = signatureEnhancement.extractNullability(extractNullability);
                if (extractNullability2 != null) {
                    return extractNullability2;
                }
            }
            return null;
        }

        /* JADX WARNING: Removed duplicated region for block: B:22:0x006f  */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x0071  */
        /* JADX WARNING: Removed duplicated region for block: B:26:0x007b  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers> computeIndexedQualifiersForOverride() {
            /*
                r14 = this;
                java.util.Collection<kotlin.reflect.jvm.internal.impl.types.KotlinType> r0 = r14.fromOverridden
                java.lang.Iterable r0 = (java.lang.Iterable) r0
                java.util.ArrayList r1 = new java.util.ArrayList
                r2 = 10
                int r2 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r0, r2)
                r1.<init>(r2)
                java.util.Collection r1 = (java.util.Collection) r1
                java.util.Iterator r0 = r0.iterator()
            L_0x0015:
                boolean r2 = r0.hasNext()
                if (r2 == 0) goto L_0x0029
                java.lang.Object r2 = r0.next()
                kotlin.reflect.jvm.internal.impl.types.KotlinType r2 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r2
                java.util.List r2 = r14.toIndexed(r2)
                r1.add(r2)
                goto L_0x0015
            L_0x0029:
                java.util.List r1 = (java.util.List) r1
                kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = r14.fromOverride
                java.util.List r0 = r14.toIndexed(r0)
                boolean r2 = r14.isCovariant
                r3 = 0
                r4 = 1
                if (r2 == 0) goto L_0x006c
                java.util.Collection<kotlin.reflect.jvm.internal.impl.types.KotlinType> r2 = r14.fromOverridden
                java.lang.Iterable r2 = (java.lang.Iterable) r2
                boolean r5 = r2 instanceof java.util.Collection
                if (r5 == 0) goto L_0x004a
                r5 = r2
                java.util.Collection r5 = (java.util.Collection) r5
                boolean r5 = r5.isEmpty()
                if (r5 == 0) goto L_0x004a
                r2 = 0
                goto L_0x0068
            L_0x004a:
                java.util.Iterator r2 = r2.iterator()
            L_0x004e:
                boolean r5 = r2.hasNext()
                if (r5 == 0) goto L_0x0067
                java.lang.Object r5 = r2.next()
                kotlin.reflect.jvm.internal.impl.types.KotlinType r5 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r5
                kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker r6 = kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker.DEFAULT
                kotlin.reflect.jvm.internal.impl.types.KotlinType r7 = r14.fromOverride
                boolean r5 = r6.equalTypes(r5, r7)
                r5 = r5 ^ r4
                if (r5 == 0) goto L_0x004e
                r2 = 1
                goto L_0x0068
            L_0x0067:
                r2 = 0
            L_0x0068:
                if (r2 == 0) goto L_0x006c
                r2 = 1
                goto L_0x006d
            L_0x006c:
                r2 = 0
            L_0x006d:
                if (r2 == 0) goto L_0x0071
                r5 = 1
                goto L_0x0075
            L_0x0071:
                int r5 = r0.size()
            L_0x0075:
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers[] r5 = new kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers[r5]
                int r6 = r5.length
                r7 = 0
            L_0x0079:
                if (r7 >= r6) goto L_0x00e2
                if (r7 != 0) goto L_0x007f
                r8 = 1
                goto L_0x0080
            L_0x007f:
                r8 = 0
            L_0x0080:
                if (r8 != 0) goto L_0x0087
                if (r2 != 0) goto L_0x0085
                goto L_0x0087
            L_0x0085:
                r9 = 0
                goto L_0x0088
            L_0x0087:
                r9 = 1
            L_0x0088:
                boolean r10 = kotlin._Assertions.ENABLED
                if (r10 == 0) goto L_0x0099
                if (r9 == 0) goto L_0x008f
                goto L_0x0099
            L_0x008f:
                java.lang.AssertionError r0 = new java.lang.AssertionError
                java.lang.String r1 = "Only head type constructors should be computed"
                r0.<init>(r1)
                java.lang.Throwable r0 = (java.lang.Throwable) r0
                throw r0
            L_0x0099:
                java.lang.Object r9 = r0.get(r7)
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.TypeAndDefaultQualifiers r9 = (kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.TypeAndDefaultQualifiers) r9
                kotlin.reflect.jvm.internal.impl.types.KotlinType r10 = r9.component1()
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers r9 = r9.component2()
                r11 = r1
                java.lang.Iterable r11 = (java.lang.Iterable) r11
                java.util.ArrayList r12 = new java.util.ArrayList
                r12.<init>()
                java.util.Collection r12 = (java.util.Collection) r12
                java.util.Iterator r11 = r11.iterator()
            L_0x00b5:
                boolean r13 = r11.hasNext()
                if (r13 == 0) goto L_0x00d5
                java.lang.Object r13 = r11.next()
                java.util.List r13 = (java.util.List) r13
                java.lang.Object r13 = kotlin.collections.CollectionsKt.getOrNull(r13, r7)
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.TypeAndDefaultQualifiers r13 = (kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.TypeAndDefaultQualifiers) r13
                if (r13 == 0) goto L_0x00ce
                kotlin.reflect.jvm.internal.impl.types.KotlinType r13 = r13.getType()
                goto L_0x00cf
            L_0x00ce:
                r13 = 0
            L_0x00cf:
                if (r13 == 0) goto L_0x00b5
                r12.add(r13)
                goto L_0x00b5
            L_0x00d5:
                java.util.List r12 = (java.util.List) r12
                java.util.Collection r12 = (java.util.Collection) r12
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers r8 = r14.computeQualifiersForOverride(r10, r12, r9, r8)
                r5[r7] = r8
                int r7 = r7 + 1
                goto L_0x0079
            L_0x00e2:
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$SignatureParts$computeIndexedQualifiersForOverride$1 r0 = new kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$SignatureParts$computeIndexedQualifiersForOverride$1
                r0.<init>(r5)
                kotlin.jvm.functions.Function1 r0 = (kotlin.jvm.functions.Function1) r0
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.SignatureParts.computeIndexedQualifiersForOverride():kotlin.jvm.functions.Function1");
        }

        private final List<TypeAndDefaultQualifiers> toIndexed(@NotNull KotlinType kotlinType) {
            ArrayList arrayList = new ArrayList(1);
            new SignatureEnhancement$SignatureParts$toIndexed$1(arrayList).invoke(kotlinType, this.containerContext);
            return arrayList;
        }

        /* JADX WARNING: Removed duplicated region for block: B:73:0x014b  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private final kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers computeQualifiersForOverride(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.types.KotlinType r11, java.util.Collection<? extends kotlin.reflect.jvm.internal.impl.types.KotlinType> r12, kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers r13, boolean r14) {
            /*
                r10 = this;
                java.lang.Iterable r12 = (java.lang.Iterable) r12
                java.util.ArrayList r0 = new java.util.ArrayList
                r1 = 10
                int r1 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r12, r1)
                r0.<init>(r1)
                java.util.Collection r0 = (java.util.Collection) r0
                java.util.Iterator r1 = r12.iterator()
            L_0x0013:
                boolean r2 = r1.hasNext()
                if (r2 == 0) goto L_0x0027
                java.lang.Object r2 = r1.next()
                kotlin.reflect.jvm.internal.impl.types.KotlinType r2 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r2
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers r2 = r10.extractQualifiers(r2)
                r0.add(r2)
                goto L_0x0013
            L_0x0027:
                java.util.List r0 = (java.util.List) r0
                java.lang.Iterable r0 = (java.lang.Iterable) r0
                java.util.ArrayList r1 = new java.util.ArrayList
                r1.<init>()
                java.util.Collection r1 = (java.util.Collection) r1
                java.util.Iterator r2 = r0.iterator()
            L_0x0036:
                boolean r3 = r2.hasNext()
                if (r3 == 0) goto L_0x004c
                java.lang.Object r3 = r2.next()
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers r3 = (kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers) r3
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.MutabilityQualifier r3 = r3.getMutability()
                if (r3 == 0) goto L_0x0036
                r1.add(r3)
                goto L_0x0036
            L_0x004c:
                java.util.List r1 = (java.util.List) r1
                java.lang.Iterable r1 = (java.lang.Iterable) r1
                java.util.Set r1 = kotlin.collections.CollectionsKt.toSet(r1)
                java.util.ArrayList r2 = new java.util.ArrayList
                r2.<init>()
                java.util.Collection r2 = (java.util.Collection) r2
                java.util.Iterator r3 = r0.iterator()
            L_0x005f:
                boolean r4 = r3.hasNext()
                if (r4 == 0) goto L_0x0075
                java.lang.Object r4 = r3.next()
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers r4 = (kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers) r4
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier r4 = r4.getNullability()
                if (r4 == 0) goto L_0x005f
                r2.add(r4)
                goto L_0x005f
            L_0x0075:
                java.util.List r2 = (java.util.List) r2
                java.lang.Iterable r2 = (java.lang.Iterable) r2
                java.util.Set r2 = kotlin.collections.CollectionsKt.toSet(r2)
                java.util.ArrayList r3 = new java.util.ArrayList
                r3.<init>()
                java.util.Collection r3 = (java.util.Collection) r3
                java.util.Iterator r12 = r12.iterator()
            L_0x0088:
                boolean r4 = r12.hasNext()
                if (r4 == 0) goto L_0x00a6
                java.lang.Object r4 = r12.next()
                kotlin.reflect.jvm.internal.impl.types.KotlinType r4 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r4
                kotlin.reflect.jvm.internal.impl.types.KotlinType r4 = kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt.unwrapEnhancement(r4)
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers r4 = r10.extractQualifiers(r4)
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier r4 = r4.getNullability()
                if (r4 == 0) goto L_0x0088
                r3.add(r4)
                goto L_0x0088
            L_0x00a6:
                java.util.List r3 = (java.util.List) r3
                java.lang.Iterable r3 = (java.lang.Iterable) r3
                java.util.Set r12 = kotlin.collections.CollectionsKt.toSet(r3)
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers r11 = r10.extractQualifiersFromAnnotations(r11, r14, r13)
                boolean r13 = r11.isNullabilityQualifierForWarning$descriptors_jvm()
                r3 = 1
                r13 = r13 ^ r3
                r4 = 0
                if (r13 == 0) goto L_0x00bd
                r13 = r11
                goto L_0x00be
            L_0x00bd:
                r13 = r4
            L_0x00be:
                if (r13 == 0) goto L_0x00c5
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier r13 = r13.getNullability()
                goto L_0x00c6
            L_0x00c5:
                r13 = r4
            L_0x00c6:
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier r5 = r11.getNullability()
                boolean r6 = r10.isCovariant
                r7 = 0
                if (r6 == 0) goto L_0x00d3
                if (r14 == 0) goto L_0x00d3
                r6 = 1
                goto L_0x00d4
            L_0x00d3:
                r6 = 0
            L_0x00d4:
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier r8 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancementKt.select(r2, r13, r6)
                if (r8 == 0) goto L_0x00ec
                boolean r9 = r10.isForVarargParameter()
                if (r9 == 0) goto L_0x00e8
                if (r14 == 0) goto L_0x00e8
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier r14 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier.NULLABLE
                if (r8 != r14) goto L_0x00e8
                r14 = 1
                goto L_0x00e9
            L_0x00e8:
                r14 = 0
            L_0x00e9:
                if (r14 != 0) goto L_0x00ec
                r4 = r8
            L_0x00ec:
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.MutabilityQualifier r14 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.MutabilityQualifier.MUTABLE
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.MutabilityQualifier r8 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.MutabilityQualifier.READ_ONLY
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.MutabilityQualifier r9 = r11.getMutability()
                java.lang.Object r14 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancementKt.select(r1, r14, r8, r9, r6)
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.MutabilityQualifier r14 = (kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.MutabilityQualifier) r14
                if (r5 != r13) goto L_0x0106
                boolean r13 = kotlin.jvm.internal.Intrinsics.areEqual(r12, r2)
                r13 = r13 ^ r3
                if (r13 == 0) goto L_0x0104
                goto L_0x0106
            L_0x0104:
                r13 = 0
                goto L_0x0107
            L_0x0106:
                r13 = 1
            L_0x0107:
                boolean r11 = r11.isNotNullTypeParameter$descriptors_jvm()
                if (r11 != 0) goto L_0x013a
                boolean r11 = r0 instanceof java.util.Collection
                if (r11 == 0) goto L_0x011c
                r11 = r0
                java.util.Collection r11 = (java.util.Collection) r11
                boolean r11 = r11.isEmpty()
                if (r11 == 0) goto L_0x011c
                r11 = 0
                goto L_0x0135
            L_0x011c:
                java.util.Iterator r11 = r0.iterator()
            L_0x0120:
                boolean r0 = r11.hasNext()
                if (r0 == 0) goto L_0x0134
                java.lang.Object r0 = r11.next()
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers r0 = (kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers) r0
                boolean r0 = r0.isNotNullTypeParameter$descriptors_jvm()
                if (r0 == 0) goto L_0x0120
                r11 = 1
                goto L_0x0135
            L_0x0134:
                r11 = 0
            L_0x0135:
                if (r11 == 0) goto L_0x0138
                goto L_0x013a
            L_0x0138:
                r11 = 0
                goto L_0x013b
            L_0x013a:
                r11 = 1
            L_0x013b:
                if (r4 != 0) goto L_0x0148
                if (r13 == 0) goto L_0x0148
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier r12 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancementKt.select(r12, r5, r6)
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers r11 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancementKt.createJavaTypeQualifiers(r12, r14, r3, r11)
                return r11
            L_0x0148:
                if (r4 != 0) goto L_0x014b
                goto L_0x014c
            L_0x014b:
                r3 = 0
            L_0x014c:
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers r11 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancementKt.createJavaTypeQualifiers(r4, r14, r3, r11)
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.SignatureParts.computeQualifiersForOverride(kotlin.reflect.jvm.internal.impl.types.KotlinType, java.util.Collection, kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers, boolean):kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers");
        }
    }

    /* compiled from: signatureEnhancement.kt */
    private static final class ValueParameterEnhancementResult extends PartEnhancementResult {
        private final boolean hasDefaultValue;

        public final boolean getHasDefaultValue() {
            return this.hasDefaultValue;
        }

        public ValueParameterEnhancementResult(@NotNull KotlinType kotlinType, boolean z, boolean z2, boolean z3) {
            Intrinsics.checkParameterIsNotNull(kotlinType, "type");
            super(kotlinType, z2, z3);
            this.hasDefaultValue = z;
        }
    }

    public SignatureEnhancement(@NotNull AnnotationTypeQualifierResolver annotationTypeQualifierResolver2, @NotNull Jsr305State jsr305State2) {
        Intrinsics.checkParameterIsNotNull(annotationTypeQualifierResolver2, "annotationTypeQualifierResolver");
        Intrinsics.checkParameterIsNotNull(jsr305State2, "jsr305State");
        this.annotationTypeQualifierResolver = annotationTypeQualifierResolver2;
        this.jsr305State = jsr305State2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0057, code lost:
        if (r6.equals("NEVER") != false) goto L_0x0062;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0060, code lost:
        if (r6.equals("MAYBE") != false) goto L_0x0062;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus extractNullabilityTypeFromArgument(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor r6) {
        /*
            r5 = this;
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r6 = kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.firstArgument(r6)
            boolean r0 = r6 instanceof kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue
            r1 = 0
            if (r0 != 0) goto L_0x000a
            r6 = r1
        L_0x000a:
            kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue r6 = (kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue) r6
            r0 = 2
            r2 = 0
            if (r6 == 0) goto L_0x006c
            kotlin.reflect.jvm.internal.impl.name.Name r6 = r6.getEnumEntryName()
            java.lang.String r6 = r6.asString()
            int r3 = r6.hashCode()
            r4 = 73135176(0x45bf448, float:2.5855495E-36)
            if (r3 == r4) goto L_0x005a
            r4 = 74175084(0x46bd26c, float:2.7720738E-36)
            if (r3 == r4) goto L_0x0051
            r4 = 433141802(0x19d1382a, float:2.1632778E-23)
            if (r3 == r4) goto L_0x0041
            r4 = 1933739535(0x7342860f, float:1.5411753E31)
            if (r3 == r4) goto L_0x0031
            goto L_0x006a
        L_0x0031:
            java.lang.String r3 = "ALWAYS"
            boolean r6 = r6.equals(r3)
            if (r6 == 0) goto L_0x006a
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus r6 = new kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier r3 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier.NOT_NULL
            r6.<init>(r3, r2, r0, r1)
            goto L_0x006b
        L_0x0041:
            java.lang.String r3 = "UNKNOWN"
            boolean r6 = r6.equals(r3)
            if (r6 == 0) goto L_0x006a
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus r6 = new kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier r3 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier.FORCE_FLEXIBILITY
            r6.<init>(r3, r2, r0, r1)
            goto L_0x006b
        L_0x0051:
            java.lang.String r3 = "NEVER"
            boolean r6 = r6.equals(r3)
            if (r6 == 0) goto L_0x006a
            goto L_0x0062
        L_0x005a:
            java.lang.String r3 = "MAYBE"
            boolean r6 = r6.equals(r3)
            if (r6 == 0) goto L_0x006a
        L_0x0062:
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus r6 = new kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier r3 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier.NULLABLE
            r6.<init>(r3, r2, r0, r1)
            goto L_0x006b
        L_0x006a:
            r6 = r1
        L_0x006b:
            return r6
        L_0x006c:
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus r6 = new kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier r3 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier.NOT_NULL
            r6.<init>(r3, r2, r0, r1)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.extractNullabilityTypeFromArgument(kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor):kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus");
    }

    @Nullable
    public final NullabilityQualifierWithMigrationStatus extractNullability(@NotNull AnnotationDescriptor annotationDescriptor) {
        Intrinsics.checkParameterIsNotNull(annotationDescriptor, "annotationDescriptor");
        NullabilityQualifierWithMigrationStatus extractNullabilityFromKnownAnnotations = extractNullabilityFromKnownAnnotations(annotationDescriptor);
        if (extractNullabilityFromKnownAnnotations != null) {
            return extractNullabilityFromKnownAnnotations;
        }
        AnnotationDescriptor resolveTypeQualifierAnnotation = this.annotationTypeQualifierResolver.resolveTypeQualifierAnnotation(annotationDescriptor);
        NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus = null;
        if (resolveTypeQualifierAnnotation == null) {
            return null;
        }
        ReportLevel resolveJsr305AnnotationState = this.annotationTypeQualifierResolver.resolveJsr305AnnotationState(annotationDescriptor);
        if (resolveJsr305AnnotationState.isIgnore()) {
            return null;
        }
        NullabilityQualifierWithMigrationStatus extractNullabilityFromKnownAnnotations2 = extractNullabilityFromKnownAnnotations(resolveTypeQualifierAnnotation);
        if (extractNullabilityFromKnownAnnotations2 != null) {
            nullabilityQualifierWithMigrationStatus = NullabilityQualifierWithMigrationStatus.copy$default(extractNullabilityFromKnownAnnotations2, null, resolveJsr305AnnotationState.isWarning(), 1, null);
        }
        return nullabilityQualifierWithMigrationStatus;
    }

    private final NullabilityQualifierWithMigrationStatus extractNullabilityFromKnownAnnotations(AnnotationDescriptor annotationDescriptor) {
        FqName fqName = annotationDescriptor.getFqName();
        if (fqName == null) {
            return null;
        }
        NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus = JvmAnnotationNamesKt.getNULLABLE_ANNOTATIONS().contains(fqName) ? new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NULLABLE, false, 2, null) : JvmAnnotationNamesKt.getNOT_NULL_ANNOTATIONS().contains(fqName) ? new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, false, 2, null) : Intrinsics.areEqual((Object) fqName, (Object) JvmAnnotationNamesKt.getJAVAX_NONNULL_ANNOTATION()) ? extractNullabilityTypeFromArgument(annotationDescriptor) : (!Intrinsics.areEqual((Object) fqName, (Object) JvmAnnotationNamesKt.getCOMPATQUAL_NULLABLE_ANNOTATION()) || !this.jsr305State.getEnableCompatqualCheckerFrameworkAnnotations()) ? (!Intrinsics.areEqual((Object) fqName, (Object) JvmAnnotationNamesKt.getCOMPATQUAL_NONNULL_ANNOTATION()) || !this.jsr305State.getEnableCompatqualCheckerFrameworkAnnotations()) ? Intrinsics.areEqual((Object) fqName, (Object) JvmAnnotationNamesKt.getANDROIDX_RECENTLY_NON_NULL_ANNOTATION()) ? new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, true) : Intrinsics.areEqual((Object) fqName, (Object) JvmAnnotationNamesKt.getANDROIDX_RECENTLY_NULLABLE_ANNOTATION()) ? new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NULLABLE, true) : null : new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, false, 2, null) : new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NULLABLE, false, 2, null);
        return nullabilityQualifierWithMigrationStatus;
    }

    @NotNull
    public final <D extends CallableMemberDescriptor> Collection<D> enhanceSignatures(@NotNull LazyJavaResolverContext lazyJavaResolverContext, @NotNull Collection<? extends D> collection) {
        Intrinsics.checkParameterIsNotNull(lazyJavaResolverContext, "c");
        Intrinsics.checkParameterIsNotNull(collection, "platformSignatures");
        Iterable<CallableMemberDescriptor> iterable = collection;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (CallableMemberDescriptor enhanceSignature : iterable) {
            arrayList.add(enhanceSignature(enhanceSignature, lazyJavaResolverContext));
        }
        return (List) arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:103:0x020a  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x021c  */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x0234  */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x024a  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x0218 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x0259 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0139  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0169  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x016e  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x01b3  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01b5  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x01ba  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x01d6  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x01db  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x01f5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final <D extends kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor> D enhanceSignature(@org.jetbrains.annotations.NotNull D r18, kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext r19) {
        /*
            r17 = this;
            r7 = r17
            r8 = r18
            boolean r0 = r8 instanceof kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaCallableMemberDescriptor
            if (r0 != 0) goto L_0x0009
            return r8
        L_0x0009:
            r9 = r8
            kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaCallableMemberDescriptor r9 = (kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaCallableMemberDescriptor) r9
            kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor$Kind r0 = r9.getKind()
            kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor$Kind r1 = kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind.FAKE_OVERRIDE
            r10 = 1
            if (r0 != r1) goto L_0x0029
            kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor r0 = r9.getOriginal()
            java.lang.String r1 = "original"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            java.util.Collection r0 = r0.getOverriddenDescriptors()
            int r0 = r0.size()
            if (r0 != r10) goto L_0x0029
            return r8
        L_0x0029:
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r0 = r18.getAnnotations()
            r1 = r19
            kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext r4 = kotlin.reflect.jvm.internal.impl.load.java.lazy.ContextKt.copyWithNewDefaultTypeQualifiers(r1, r0)
            boolean r0 = r8 instanceof kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaPropertyDescriptor
            if (r0 == 0) goto L_0x0057
            r0 = r8
            kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaPropertyDescriptor r0 = (kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaPropertyDescriptor) r0
            kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyGetterDescriptorImpl r1 = r0.getGetter()
            if (r1 == 0) goto L_0x0057
            boolean r1 = r1.isDefault()
            if (r1 != 0) goto L_0x0057
            kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyGetterDescriptorImpl r0 = r0.getGetter()
            if (r0 != 0) goto L_0x004f
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x004f:
            java.lang.String r1 = "getter!!"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor) r0
            goto L_0x0058
        L_0x0057:
            r0 = r8
        L_0x0058:
            kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor r1 = r9.getExtensionReceiverParameter()
            r11 = 0
            if (r1 == 0) goto L_0x0082
            boolean r1 = r0 instanceof kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
            if (r1 != 0) goto L_0x0065
            r1 = r11
            goto L_0x0066
        L_0x0065:
            r1 = r0
        L_0x0066:
            kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r1 = (kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor) r1
            if (r1 == 0) goto L_0x0073
            kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor$UserDataKey<kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor> r2 = kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaMethodDescriptor.ORIGINAL_VALUE_PARAMETER_FOR_EXTENSION_RECEIVER
            java.lang.Object r1 = r1.getUserData(r2)
            kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor r1 = (kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor) r1
            goto L_0x0074
        L_0x0073:
            r1 = r11
        L_0x0074:
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$enhanceSignature$receiverTypeEnhancement$1 r2 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$enhanceSignature$receiverTypeEnhancement$1.INSTANCE
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$SignatureParts r1 = r7.partsForValueParameter(r8, r1, r4, r2)
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$PartEnhancementResult r1 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.SignatureParts.enhance$default(r1, r11, r10, r11)
            r12 = r1
            goto L_0x0083
        L_0x0082:
            r12 = r11
        L_0x0083:
            boolean r1 = r8 instanceof kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaMethodDescriptor
            if (r1 != 0) goto L_0x0089
            r1 = r11
            goto L_0x008a
        L_0x0089:
            r1 = r8
        L_0x008a:
            kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaMethodDescriptor r1 = (kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaMethodDescriptor) r1
            r13 = 0
            if (r1 == 0) goto L_0x00ba
            kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents r2 = kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents.INSTANCE
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r3 = r1.getContainingDeclaration()
            if (r3 == 0) goto L_0x00b2
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r3 = (kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor) r3
            kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r1 = (kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor) r1
            r5 = 3
            java.lang.String r1 = kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt.computeJvmDescriptor$default(r1, r13, r13, r5, r11)
            java.lang.String r1 = r2.signature(r3, r1)
            if (r1 == 0) goto L_0x00ba
            java.util.Map r2 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt.getPREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE()
            java.lang.Object r1 = r2.get(r1)
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedFunctionEnhancementInfo r1 = (kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedFunctionEnhancementInfo) r1
            r14 = r1
            goto L_0x00bb
        L_0x00b2:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException
            java.lang.String r1 = "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor"
            r0.<init>(r1)
            throw r0
        L_0x00ba:
            r14 = r11
        L_0x00bb:
            if (r14 == 0) goto L_0x0117
            java.util.List r1 = r14.getParametersInfo()
            int r1 = r1.size()
            java.util.List r2 = r9.getValueParameters()
            int r2 = r2.size()
            if (r1 != r2) goto L_0x00d1
            r1 = 1
            goto L_0x00d2
        L_0x00d1:
            r1 = 0
        L_0x00d2:
            boolean r2 = kotlin._Assertions.ENABLED
            if (r2 == 0) goto L_0x0117
            if (r1 == 0) goto L_0x00d9
            goto L_0x0117
        L_0x00d9:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Predefined enhancement info for "
            r0.append(r1)
            r0.append(r8)
            java.lang.String r1 = " has "
            r0.append(r1)
            java.util.List r1 = r14.getParametersInfo()
            int r1 = r1.size()
            r0.append(r1)
            java.lang.String r1 = ", but "
            r0.append(r1)
            java.util.List r1 = r9.getValueParameters()
            int r1 = r1.size()
            r0.append(r1)
            java.lang.String r1 = " expected"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.AssertionError r1 = new java.lang.AssertionError
            r1.<init>(r0)
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            throw r1
        L_0x0117:
            java.util.List r1 = r0.getValueParameters()
            java.lang.String r2 = "annotationOwnerForMember.valueParameters"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.ArrayList r2 = new java.util.ArrayList
            r15 = 10
            int r3 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r1, r15)
            r2.<init>(r3)
            java.util.Collection r2 = (java.util.Collection) r2
            java.util.Iterator r1 = r1.iterator()
        L_0x0133:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x01a8
            java.lang.Object r3 = r1.next()
            kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor r3 = (kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor) r3
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$enhanceSignature$valueParameterEnhancements$1$enhancementResult$1 r5 = new kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$enhanceSignature$valueParameterEnhancements$1$enhancementResult$1
            r5.<init>(r3)
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$SignatureParts r5 = r7.partsForValueParameter(r8, r3, r4, r5)
            if (r14 == 0) goto L_0x015e
            java.util.List r6 = r14.getParametersInfo()
            if (r6 == 0) goto L_0x015e
            int r11 = r3.getIndex()
            java.lang.Object r6 = kotlin.collections.CollectionsKt.getOrNull(r6, r11)
            r11 = r6
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.TypeEnhancementInfo r11 = (kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.TypeEnhancementInfo) r11
            goto L_0x015f
        L_0x015e:
            r11 = 0
        L_0x015f:
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$PartEnhancementResult r5 = r5.enhance(r11)
            boolean r6 = r5.getWereChanges()
            if (r6 == 0) goto L_0x016e
            kotlin.reflect.jvm.internal.impl.types.KotlinType r6 = r5.getType()
            goto L_0x017c
        L_0x016e:
            java.lang.String r6 = "p"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r6)
            kotlin.reflect.jvm.internal.impl.types.KotlinType r6 = r3.getType()
            java.lang.String r11 = "p.type"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r11)
        L_0x017c:
            java.lang.String r11 = "p"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r11)
            boolean r6 = r7.hasDefaultValueInAnnotation(r3, r6)
            boolean r11 = r5.getWereChanges()
            if (r11 != 0) goto L_0x0194
            boolean r3 = r3.declaresDefaultValue()
            if (r6 == r3) goto L_0x0192
            goto L_0x0194
        L_0x0192:
            r3 = 0
            goto L_0x0195
        L_0x0194:
            r3 = 1
        L_0x0195:
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$ValueParameterEnhancementResult r11 = new kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$ValueParameterEnhancementResult
            kotlin.reflect.jvm.internal.impl.types.KotlinType r13 = r5.getType()
            boolean r5 = r5.getContainsFunctionN()
            r11.<init>(r13, r6, r3, r5)
            r2.add(r11)
            r11 = 0
            r13 = 0
            goto L_0x0133
        L_0x01a8:
            r11 = r2
            java.util.List r11 = (java.util.List) r11
            r2 = r0
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated r2 = (kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated) r2
            r3 = 1
            boolean r0 = r8 instanceof kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor
            if (r0 != 0) goto L_0x01b5
            r0 = 0
            goto L_0x01b6
        L_0x01b5:
            r0 = r8
        L_0x01b6:
            kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor) r0
            if (r0 == 0) goto L_0x01c4
            boolean r0 = kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.JavaDescriptorUtilKt.isJavaField(r0)
            if (r0 != r10) goto L_0x01c4
            kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver$QualifierApplicabilityType r0 = kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver.QualifierApplicabilityType.FIELD
            r5 = r0
            goto L_0x01c7
        L_0x01c4:
            kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver$QualifierApplicabilityType r0 = kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver.QualifierApplicabilityType.METHOD_RETURN_TYPE
            r5 = r0
        L_0x01c7:
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$enhanceSignature$returnTypeEnhancement$1 r0 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$enhanceSignature$returnTypeEnhancement$1.INSTANCE
            r6 = r0
            kotlin.jvm.functions.Function1 r6 = (kotlin.jvm.functions.Function1) r6
            r0 = r17
            r1 = r18
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$SignatureParts r0 = r0.parts(r1, r2, r3, r4, r5, r6)
            if (r14 == 0) goto L_0x01db
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.TypeEnhancementInfo r1 = r14.getReturnTypeInfo()
            goto L_0x01dc
        L_0x01db:
            r1 = 0
        L_0x01dc:
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$PartEnhancementResult r0 = r0.enhance(r1)
            if (r12 == 0) goto L_0x01e8
            boolean r1 = r12.getContainsFunctionN()
            if (r1 == r10) goto L_0x021e
        L_0x01e8:
            boolean r1 = r0.getContainsFunctionN()
            if (r1 != 0) goto L_0x021e
            r1 = r11
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            boolean r2 = r1 instanceof java.util.Collection
            if (r2 == 0) goto L_0x0200
            r2 = r1
            java.util.Collection r2 = (java.util.Collection) r2
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L_0x0200
            r1 = 0
            goto L_0x0219
        L_0x0200:
            java.util.Iterator r1 = r1.iterator()
        L_0x0204:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0218
            java.lang.Object r2 = r1.next()
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$ValueParameterEnhancementResult r2 = (kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.ValueParameterEnhancementResult) r2
            boolean r2 = r2.getContainsFunctionN()
            if (r2 == 0) goto L_0x0204
            r1 = 1
            goto L_0x0219
        L_0x0218:
            r1 = 0
        L_0x0219:
            if (r1 == 0) goto L_0x021c
            goto L_0x021e
        L_0x021c:
            r1 = 0
            goto L_0x021f
        L_0x021e:
            r1 = 1
        L_0x021f:
            if (r12 == 0) goto L_0x0227
            boolean r2 = r12.getWereChanges()
            if (r2 == r10) goto L_0x0261
        L_0x0227:
            boolean r2 = r0.getWereChanges()
            if (r2 != 0) goto L_0x0261
            r2 = r11
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            boolean r3 = r2 instanceof java.util.Collection
            if (r3 == 0) goto L_0x0240
            r3 = r2
            java.util.Collection r3 = (java.util.Collection) r3
            boolean r3 = r3.isEmpty()
            if (r3 == 0) goto L_0x0240
            r16 = 0
            goto L_0x025b
        L_0x0240:
            java.util.Iterator r2 = r2.iterator()
        L_0x0244:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0259
            java.lang.Object r3 = r2.next()
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$ValueParameterEnhancementResult r3 = (kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.ValueParameterEnhancementResult) r3
            boolean r3 = r3.getWereChanges()
            if (r3 == 0) goto L_0x0244
            r16 = 1
            goto L_0x025b
        L_0x0259:
            r16 = 0
        L_0x025b:
            if (r16 != 0) goto L_0x0261
            if (r1 == 0) goto L_0x0260
            goto L_0x0261
        L_0x0260:
            return r8
        L_0x0261:
            if (r1 == 0) goto L_0x0274
            kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor$UserDataKey r1 = kotlin.reflect.jvm.internal.impl.resolve.DeprecationKt.getDEPRECATED_FUNCTION_KEY()
            kotlin.reflect.jvm.internal.impl.load.java.DeprecationCausedByFunctionN r2 = new kotlin.reflect.jvm.internal.impl.load.java.DeprecationCausedByFunctionN
            r3 = r8
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r3 = (kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor) r3
            r2.<init>(r3)
            kotlin.Pair r1 = kotlin.TuplesKt.to(r1, r2)
            goto L_0x0275
        L_0x0274:
            r1 = 0
        L_0x0275:
            if (r12 == 0) goto L_0x027c
            kotlin.reflect.jvm.internal.impl.types.KotlinType r2 = r12.getType()
            goto L_0x027d
        L_0x027c:
            r2 = 0
        L_0x027d:
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            java.util.ArrayList r3 = new java.util.ArrayList
            int r4 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r11, r15)
            r3.<init>(r4)
            java.util.Collection r3 = (java.util.Collection) r3
            java.util.Iterator r4 = r11.iterator()
        L_0x028e:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x02ab
            java.lang.Object r5 = r4.next()
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$ValueParameterEnhancementResult r5 = (kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.ValueParameterEnhancementResult) r5
            kotlin.reflect.jvm.internal.impl.load.java.descriptors.ValueParameterData r6 = new kotlin.reflect.jvm.internal.impl.load.java.descriptors.ValueParameterData
            kotlin.reflect.jvm.internal.impl.types.KotlinType r8 = r5.getType()
            boolean r5 = r5.getHasDefaultValue()
            r6.<init>(r8, r5)
            r3.add(r6)
            goto L_0x028e
        L_0x02ab:
            java.util.List r3 = (java.util.List) r3
            kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = r0.getType()
            kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaCallableMemberDescriptor r0 = r9.enhance(r2, r3, r0, r1)
            if (r0 == 0) goto L_0x02ba
            kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor) r0
            return r0
        L_0x02ba:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException
            java.lang.String r1 = "null cannot be cast to non-null type D"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.enhanceSignature(kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor, kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext):kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor");
    }

    private final boolean hasDefaultValueInAnnotation(@NotNull ValueParameterDescriptor valueParameterDescriptor, KotlinType kotlinType) {
        boolean z;
        AnnotationDefaultValue defaultValueFromAnnotation = UtilKt.getDefaultValueFromAnnotation(valueParameterDescriptor);
        if (defaultValueFromAnnotation instanceof StringDefaultValue) {
            z = UtilsKt.lexicalCastFrom(kotlinType, ((StringDefaultValue) defaultValueFromAnnotation).getValue()) != null;
        } else if (Intrinsics.areEqual((Object) defaultValueFromAnnotation, (Object) NullDefaultValue.INSTANCE)) {
            z = TypeUtils.acceptsNullable(kotlinType);
        } else if (defaultValueFromAnnotation == null) {
            z = valueParameterDescriptor.declaresDefaultValue();
        } else {
            throw new NoWhenBranchMatchedException();
        }
        if (!z || !valueParameterDescriptor.getOverriddenDescriptors().isEmpty()) {
            return false;
        }
        return true;
    }

    private final SignatureParts partsForValueParameter(@NotNull CallableMemberDescriptor callableMemberDescriptor, ValueParameterDescriptor valueParameterDescriptor, LazyJavaResolverContext lazyJavaResolverContext, Function1<? super CallableMemberDescriptor, ? extends KotlinType> function1) {
        LazyJavaResolverContext lazyJavaResolverContext2;
        Annotated annotated = valueParameterDescriptor;
        if (valueParameterDescriptor != null) {
            LazyJavaResolverContext copyWithNewDefaultTypeQualifiers = ContextKt.copyWithNewDefaultTypeQualifiers(lazyJavaResolverContext, valueParameterDescriptor.getAnnotations());
            if (copyWithNewDefaultTypeQualifiers != null) {
                lazyJavaResolverContext2 = copyWithNewDefaultTypeQualifiers;
                return parts(callableMemberDescriptor, annotated, false, lazyJavaResolverContext2, QualifierApplicabilityType.VALUE_PARAMETER, function1);
            }
        }
        lazyJavaResolverContext2 = lazyJavaResolverContext;
        return parts(callableMemberDescriptor, annotated, false, lazyJavaResolverContext2, QualifierApplicabilityType.VALUE_PARAMETER, function1);
    }

    private final SignatureParts parts(@NotNull CallableMemberDescriptor callableMemberDescriptor, Annotated annotated, boolean z, LazyJavaResolverContext lazyJavaResolverContext, QualifierApplicabilityType qualifierApplicabilityType, Function1<? super CallableMemberDescriptor, ? extends KotlinType> function1) {
        KotlinType kotlinType = (KotlinType) function1.invoke(callableMemberDescriptor);
        Collection overriddenDescriptors = callableMemberDescriptor.getOverriddenDescriptors();
        Intrinsics.checkExpressionValueIsNotNull(overriddenDescriptors, "this.overriddenDescriptors");
        Iterable<CallableMemberDescriptor> iterable = overriddenDescriptors;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (CallableMemberDescriptor callableMemberDescriptor2 : iterable) {
            Intrinsics.checkExpressionValueIsNotNull(callableMemberDescriptor2, "it");
            arrayList.add((KotlinType) function1.invoke(callableMemberDescriptor2));
        }
        SignatureParts signatureParts = new SignatureParts(this, annotated, kotlinType, (List) arrayList, z, ContextKt.copyWithNewDefaultTypeQualifiers(lazyJavaResolverContext, ((KotlinType) function1.invoke(callableMemberDescriptor)).getAnnotations()), qualifierApplicabilityType);
        return signatureParts;
    }
}
