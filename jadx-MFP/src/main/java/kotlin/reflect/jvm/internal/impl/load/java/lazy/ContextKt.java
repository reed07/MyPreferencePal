package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassOrPackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver.TypeQualifierWithApplicability;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameterListOwner;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus;
import kotlin.reflect.jvm.internal.impl.utils.ReportLevel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: context.kt */
public final class ContextKt {
    @NotNull
    public static final LazyJavaResolverContext child(@NotNull LazyJavaResolverContext lazyJavaResolverContext, @NotNull TypeParameterResolver typeParameterResolver) {
        Intrinsics.checkParameterIsNotNull(lazyJavaResolverContext, "receiver$0");
        Intrinsics.checkParameterIsNotNull(typeParameterResolver, "typeParameterResolver");
        return new LazyJavaResolverContext(lazyJavaResolverContext.getComponents(), typeParameterResolver, lazyJavaResolverContext.getDelegateForDefaultTypeQualifiers$descriptors_jvm());
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x009e  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaTypeQualifiersByElementType computeNewDefaultTypeQualifiers(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext r5, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r6) {
        /*
            java.lang.String r0 = "receiver$0"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0)
            java.lang.String r0 = "additionalAnnotations"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r0)
            kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverComponents r0 = r5.getComponents()
            kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver r0 = r0.getAnnotationTypeQualifierResolver()
            boolean r0 = r0.getDisabled()
            if (r0 == 0) goto L_0x001d
            kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaTypeQualifiersByElementType r5 = r5.getDefaultTypeQualifiers()
            return r5
        L_0x001d:
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Collection r0 = (java.util.Collection) r0
            java.util.Iterator r6 = r6.iterator()
        L_0x002a:
            boolean r1 = r6.hasNext()
            if (r1 == 0) goto L_0x0040
            java.lang.Object r1 = r6.next()
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor r1 = (kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor) r1
            kotlin.reflect.jvm.internal.impl.load.java.lazy.NullabilityQualifierWithApplicability r1 = extractDefaultNullabilityQualifier(r5, r1)
            if (r1 == 0) goto L_0x002a
            r0.add(r1)
            goto L_0x002a
        L_0x0040:
            java.util.List r0 = (java.util.List) r0
            boolean r6 = r0.isEmpty()
            if (r6 == 0) goto L_0x004d
            kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaTypeQualifiersByElementType r5 = r5.getDefaultTypeQualifiers()
            return r5
        L_0x004d:
            kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaTypeQualifiersByElementType r6 = r5.getDefaultTypeQualifiers()
            if (r6 == 0) goto L_0x005f
            java.util.EnumMap r6 = r6.getNullabilityQualifiers$descriptors_jvm()
            if (r6 == 0) goto L_0x005f
            java.util.EnumMap r1 = new java.util.EnumMap
            r1.<init>(r6)
            goto L_0x0066
        L_0x005f:
            java.util.EnumMap r1 = new java.util.EnumMap
            java.lang.Class<kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver$QualifierApplicabilityType> r6 = kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver.QualifierApplicabilityType.class
            r1.<init>(r6)
        L_0x0066:
            r6 = 0
            java.util.Iterator r0 = r0.iterator()
        L_0x006b:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0097
            java.lang.Object r2 = r0.next()
            kotlin.reflect.jvm.internal.impl.load.java.lazy.NullabilityQualifierWithApplicability r2 = (kotlin.reflect.jvm.internal.impl.load.java.lazy.NullabilityQualifierWithApplicability) r2
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus r3 = r2.component1()
            java.util.Collection r2 = r2.component2()
            java.util.Iterator r2 = r2.iterator()
        L_0x0083:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x006b
            java.lang.Object r6 = r2.next()
            kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver$QualifierApplicabilityType r6 = (kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver.QualifierApplicabilityType) r6
            r4 = r1
            java.util.Map r4 = (java.util.Map) r4
            r4.put(r6, r3)
            r6 = 1
            goto L_0x0083
        L_0x0097:
            if (r6 != 0) goto L_0x009e
            kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaTypeQualifiersByElementType r5 = r5.getDefaultTypeQualifiers()
            goto L_0x00a3
        L_0x009e:
            kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaTypeQualifiersByElementType r5 = new kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaTypeQualifiersByElementType
            r5.<init>(r1)
        L_0x00a3:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.lazy.ContextKt.computeNewDefaultTypeQualifiers(kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext, kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations):kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaTypeQualifiersByElementType");
    }

    private static final NullabilityQualifierWithApplicability extractDefaultNullabilityQualifier(@NotNull LazyJavaResolverContext lazyJavaResolverContext, AnnotationDescriptor annotationDescriptor) {
        AnnotationTypeQualifierResolver annotationTypeQualifierResolver = lazyJavaResolverContext.getComponents().getAnnotationTypeQualifierResolver();
        NullabilityQualifierWithApplicability resolveQualifierBuiltInDefaultAnnotation = annotationTypeQualifierResolver.resolveQualifierBuiltInDefaultAnnotation(annotationDescriptor);
        if (resolveQualifierBuiltInDefaultAnnotation != null) {
            return resolveQualifierBuiltInDefaultAnnotation;
        }
        TypeQualifierWithApplicability resolveTypeQualifierDefaultAnnotation = annotationTypeQualifierResolver.resolveTypeQualifierDefaultAnnotation(annotationDescriptor);
        if (resolveTypeQualifierDefaultAnnotation == null) {
            return null;
        }
        AnnotationDescriptor component1 = resolveTypeQualifierDefaultAnnotation.component1();
        List component2 = resolveTypeQualifierDefaultAnnotation.component2();
        ReportLevel resolveJsr305CustomState = annotationTypeQualifierResolver.resolveJsr305CustomState(annotationDescriptor);
        if (resolveJsr305CustomState == null) {
            resolveJsr305CustomState = annotationTypeQualifierResolver.resolveJsr305AnnotationState(component1);
        }
        if (resolveJsr305CustomState.isIgnore()) {
            return null;
        }
        NullabilityQualifierWithMigrationStatus extractNullability = lazyJavaResolverContext.getComponents().getSignatureEnhancement().extractNullability(component1);
        if (extractNullability != null) {
            NullabilityQualifierWithMigrationStatus copy$default = NullabilityQualifierWithMigrationStatus.copy$default(extractNullability, null, resolveJsr305CustomState.isWarning(), 1, null);
            if (copy$default != null) {
                return new NullabilityQualifierWithApplicability(copy$default, component2);
            }
        }
        return null;
    }

    @NotNull
    public static final LazyJavaResolverContext replaceComponents(@NotNull LazyJavaResolverContext lazyJavaResolverContext, @NotNull JavaResolverComponents javaResolverComponents) {
        Intrinsics.checkParameterIsNotNull(lazyJavaResolverContext, "receiver$0");
        Intrinsics.checkParameterIsNotNull(javaResolverComponents, "components");
        return new LazyJavaResolverContext(javaResolverComponents, lazyJavaResolverContext.getTypeParameterResolver(), lazyJavaResolverContext.getDelegateForDefaultTypeQualifiers$descriptors_jvm());
    }

    private static final LazyJavaResolverContext child(@NotNull LazyJavaResolverContext lazyJavaResolverContext, DeclarationDescriptor declarationDescriptor, JavaTypeParameterListOwner javaTypeParameterListOwner, int i, Lazy<JavaTypeQualifiersByElementType> lazy) {
        TypeParameterResolver typeParameterResolver;
        JavaResolverComponents components = lazyJavaResolverContext.getComponents();
        if (javaTypeParameterListOwner != null) {
            typeParameterResolver = new LazyJavaTypeParameterResolver(lazyJavaResolverContext, declarationDescriptor, javaTypeParameterListOwner, i);
        } else {
            typeParameterResolver = lazyJavaResolverContext.getTypeParameterResolver();
        }
        return new LazyJavaResolverContext(components, typeParameterResolver, lazy);
    }

    @NotNull
    public static /* synthetic */ LazyJavaResolverContext childForMethod$default(LazyJavaResolverContext lazyJavaResolverContext, DeclarationDescriptor declarationDescriptor, JavaTypeParameterListOwner javaTypeParameterListOwner, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return childForMethod(lazyJavaResolverContext, declarationDescriptor, javaTypeParameterListOwner, i);
    }

    @NotNull
    public static final LazyJavaResolverContext childForMethod(@NotNull LazyJavaResolverContext lazyJavaResolverContext, @NotNull DeclarationDescriptor declarationDescriptor, @NotNull JavaTypeParameterListOwner javaTypeParameterListOwner, int i) {
        Intrinsics.checkParameterIsNotNull(lazyJavaResolverContext, "receiver$0");
        Intrinsics.checkParameterIsNotNull(declarationDescriptor, "containingDeclaration");
        Intrinsics.checkParameterIsNotNull(javaTypeParameterListOwner, "typeParameterOwner");
        return child(lazyJavaResolverContext, declarationDescriptor, javaTypeParameterListOwner, i, lazyJavaResolverContext.getDelegateForDefaultTypeQualifiers$descriptors_jvm());
    }

    @NotNull
    public static /* synthetic */ LazyJavaResolverContext childForClassOrPackage$default(LazyJavaResolverContext lazyJavaResolverContext, ClassOrPackageFragmentDescriptor classOrPackageFragmentDescriptor, JavaTypeParameterListOwner javaTypeParameterListOwner, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            javaTypeParameterListOwner = null;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return childForClassOrPackage(lazyJavaResolverContext, classOrPackageFragmentDescriptor, javaTypeParameterListOwner, i);
    }

    @NotNull
    public static final LazyJavaResolverContext childForClassOrPackage(@NotNull LazyJavaResolverContext lazyJavaResolverContext, @NotNull ClassOrPackageFragmentDescriptor classOrPackageFragmentDescriptor, @Nullable JavaTypeParameterListOwner javaTypeParameterListOwner, int i) {
        Intrinsics.checkParameterIsNotNull(lazyJavaResolverContext, "receiver$0");
        Intrinsics.checkParameterIsNotNull(classOrPackageFragmentDescriptor, "containingDeclaration");
        return child(lazyJavaResolverContext, classOrPackageFragmentDescriptor, javaTypeParameterListOwner, i, LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0<? extends T>) new ContextKt$childForClassOrPackage$1<Object>(lazyJavaResolverContext, classOrPackageFragmentDescriptor)));
    }

    @NotNull
    public static final LazyJavaResolverContext copyWithNewDefaultTypeQualifiers(@NotNull LazyJavaResolverContext lazyJavaResolverContext, @NotNull Annotations annotations) {
        Intrinsics.checkParameterIsNotNull(lazyJavaResolverContext, "receiver$0");
        Intrinsics.checkParameterIsNotNull(annotations, "additionalAnnotations");
        return annotations.isEmpty() ? lazyJavaResolverContext : new LazyJavaResolverContext(lazyJavaResolverContext.getComponents(), lazyJavaResolverContext.getTypeParameterResolver(), LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0<? extends T>) new ContextKt$copyWithNewDefaultTypeQualifiers$1<Object>(lazyJavaResolverContext, annotations)));
    }
}
