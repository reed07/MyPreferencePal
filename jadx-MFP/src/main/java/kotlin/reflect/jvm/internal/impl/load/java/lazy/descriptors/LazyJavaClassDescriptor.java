package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.TypeCastException;
import kotlin._Assertions;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.MappingUtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses.MockClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassDescriptorBase;
import kotlin.reflect.jvm.internal.impl.load.java.JavaVisibilities;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNames;
import kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache;
import kotlin.reflect.jvm.internal.impl.load.java.components.TypeUsage;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.ContextKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaAnnotationsKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeResolverKt;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNamesUtilKt;
import kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.InnerClassesScopeWrapper;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.types.AbstractClassTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.utils.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: LazyJavaClassDescriptor.kt */
public final class LazyJavaClassDescriptor extends ClassDescriptorBase implements JavaClassDescriptor {
    public static final Companion Companion = new Companion(null);
    private static final Set<String> PUBLIC_METHOD_NAMES_IN_OBJECT = SetsKt.setOf("equals", "hashCode", "getClass", "wait", "notify", "notifyAll", "toString");
    /* access modifiers changed from: private */
    public final ClassDescriptor additionalSupertypeClassDescriptor;
    @NotNull
    private final Annotations annotations;
    /* access modifiers changed from: private */
    public final LazyJavaResolverContext c;
    private final NotNullLazyValue<List<TypeParameterDescriptor>> declaredParameters;
    private final InnerClassesScopeWrapper innerClassesScope;
    private final boolean isInner;
    /* access modifiers changed from: private */
    public final JavaClass jClass;
    private final ClassKind kind;
    private final Modality modality;
    private final LazyJavaStaticClassScope staticScope;
    private final LazyJavaClassTypeConstructor typeConstructor;
    private final LazyJavaClassMemberScope unsubstitutedMemberScope;
    private final Visibility visibility;

    /* compiled from: LazyJavaClassDescriptor.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: LazyJavaClassDescriptor.kt */
    private final class LazyJavaClassTypeConstructor extends AbstractClassTypeConstructor {
        private final NotNullLazyValue<List<TypeParameterDescriptor>> parameters;

        public boolean isDenotable() {
            return true;
        }

        public LazyJavaClassTypeConstructor() {
            super(LazyJavaClassDescriptor.this.c.getStorageManager());
            this.parameters = LazyJavaClassDescriptor.this.c.getStorageManager().createLazyValue(new LazyJavaClassDescriptor$LazyJavaClassTypeConstructor$parameters$1(this));
        }

        @NotNull
        public List<TypeParameterDescriptor> getParameters() {
            return (List) this.parameters.invoke();
        }

        /* access modifiers changed from: protected */
        @NotNull
        public Collection<KotlinType> computeSupertypes() {
            Object obj;
            Collection supertypes = LazyJavaClassDescriptor.this.jClass.getSupertypes();
            ArrayList arrayList = new ArrayList(supertypes.size());
            ArrayList arrayList2 = new ArrayList(0);
            KotlinType purelyImplementedSupertype = getPurelyImplementedSupertype();
            Iterator it = supertypes.iterator();
            while (true) {
                obj = null;
                if (!it.hasNext()) {
                    break;
                }
                JavaClassifierType javaClassifierType = (JavaClassifierType) it.next();
                KotlinType transformJavaType = LazyJavaClassDescriptor.this.c.getTypeResolver().transformJavaType(javaClassifierType, JavaTypeResolverKt.toAttributes$default(TypeUsage.SUPERTYPE, false, null, 3, null));
                if (transformJavaType.getConstructor().getDeclarationDescriptor() instanceof MockClassDescriptor) {
                    arrayList2.add(javaClassifierType);
                }
                TypeConstructor constructor = transformJavaType.getConstructor();
                if (purelyImplementedSupertype != null) {
                    obj = purelyImplementedSupertype.getConstructor();
                }
                if (!Intrinsics.areEqual((Object) constructor, obj) && !KotlinBuiltIns.isAnyOrNullableAny(transformJavaType)) {
                    arrayList.add(transformJavaType);
                }
            }
            Collection collection = arrayList;
            ClassDescriptor access$getAdditionalSupertypeClassDescriptor$p = LazyJavaClassDescriptor.this.additionalSupertypeClassDescriptor;
            if (access$getAdditionalSupertypeClassDescriptor$p != null) {
                obj = MappingUtilKt.createMappedTypeParametersSubstitution(access$getAdditionalSupertypeClassDescriptor$p, LazyJavaClassDescriptor.this).buildSubstitutor().substitute(access$getAdditionalSupertypeClassDescriptor$p.getDefaultType(), Variance.INVARIANT);
            }
            CollectionsKt.addIfNotNull(collection, obj);
            CollectionsKt.addIfNotNull(collection, purelyImplementedSupertype);
            if (!arrayList2.isEmpty()) {
                ErrorReporter errorReporter = LazyJavaClassDescriptor.this.c.getComponents().getErrorReporter();
                ClassDescriptor declarationDescriptor = getDeclarationDescriptor();
                Iterable<JavaType> iterable = arrayList2;
                Collection arrayList3 = new ArrayList(kotlin.collections.CollectionsKt.collectionSizeOrDefault(iterable, 10));
                for (JavaType javaType : iterable) {
                    if (javaType != null) {
                        arrayList3.add(((JavaClassifierType) javaType).getPresentableText());
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.load.java.structure.JavaClassifierType");
                    }
                }
                errorReporter.reportIncompleteHierarchy(declarationDescriptor, (List) arrayList3);
            }
            return collection.isEmpty() ^ true ? kotlin.collections.CollectionsKt.toList(arrayList) : kotlin.collections.CollectionsKt.listOf(LazyJavaClassDescriptor.this.c.getModule().getBuiltIns().getAnyType());
        }

        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0019, code lost:
            if ((!r0.isRoot() && r0.startsWith(kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.BUILT_INS_PACKAGE_NAME)) != false) goto L_0x001d;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private final kotlin.reflect.jvm.internal.impl.types.KotlinType getPurelyImplementedSupertype() {
            /*
                r8 = this;
                kotlin.reflect.jvm.internal.impl.name.FqName r0 = r8.getPurelyImplementsFqNameFromAnnotation()
                r1 = 1
                r2 = 0
                if (r0 == 0) goto L_0x001c
                boolean r3 = r0.isRoot()
                if (r3 != 0) goto L_0x0018
                kotlin.reflect.jvm.internal.impl.name.Name r3 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.BUILT_INS_PACKAGE_NAME
                boolean r3 = r0.startsWith(r3)
                if (r3 == 0) goto L_0x0018
                r3 = 1
                goto L_0x0019
            L_0x0018:
                r3 = 0
            L_0x0019:
                if (r3 == 0) goto L_0x001c
                goto L_0x001d
            L_0x001c:
                r0 = r2
            L_0x001d:
                if (r0 == 0) goto L_0x0021
                r3 = r0
                goto L_0x002d
            L_0x0021:
                kotlin.reflect.jvm.internal.impl.load.java.FakePureImplementationsProvider r3 = kotlin.reflect.jvm.internal.impl.load.java.FakePureImplementationsProvider.INSTANCE
                kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassDescriptor r4 = kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassDescriptor.this
                kotlin.reflect.jvm.internal.impl.name.FqName r4 = kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.getFqNameSafe(r4)
                kotlin.reflect.jvm.internal.impl.name.FqName r3 = r3.getPurelyImplementedInterface(r4)
            L_0x002d:
                if (r3 == 0) goto L_0x00f7
                kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassDescriptor r4 = kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassDescriptor.this
                kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext r4 = r4.c
                kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor r4 = r4.getModule()
                kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation r5 = kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation.FROM_JAVA_LOADER
                kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation r5 = (kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation) r5
                kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r3 = kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.resolveTopLevelClass(r4, r3, r5)
                if (r3 == 0) goto L_0x00f6
                kotlin.reflect.jvm.internal.impl.types.TypeConstructor r4 = r3.getTypeConstructor()
                java.lang.String r5 = "classDescriptor.typeConstructor"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r5)
                java.util.List r4 = r4.getParameters()
                int r4 = r4.size()
                kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassDescriptor r5 = kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassDescriptor.this
                kotlin.reflect.jvm.internal.impl.types.TypeConstructor r5 = r5.getTypeConstructor()
                java.util.List r5 = r5.getParameters()
                java.lang.String r6 = "getTypeConstructor().parameters"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r6)
                int r6 = r5.size()
                r7 = 10
                if (r6 != r4) goto L_0x00a1
                java.lang.Iterable r5 = (java.lang.Iterable) r5
                java.util.ArrayList r0 = new java.util.ArrayList
                int r1 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r5, r7)
                r0.<init>(r1)
                java.util.Collection r0 = (java.util.Collection) r0
                java.util.Iterator r1 = r5.iterator()
            L_0x007c:
                boolean r2 = r1.hasNext()
                if (r2 == 0) goto L_0x009e
                java.lang.Object r2 = r1.next()
                kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor r2 = (kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor) r2
                kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl r4 = new kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl
                kotlin.reflect.jvm.internal.impl.types.Variance r5 = kotlin.reflect.jvm.internal.impl.types.Variance.INVARIANT
                java.lang.String r6 = "parameter"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r6)
                kotlin.reflect.jvm.internal.impl.types.SimpleType r2 = r2.getDefaultType()
                kotlin.reflect.jvm.internal.impl.types.KotlinType r2 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r2
                r4.<init>(r5, r2)
                r0.add(r4)
                goto L_0x007c
            L_0x009e:
                java.util.List r0 = (java.util.List) r0
                goto L_0x00e8
            L_0x00a1:
                if (r6 != r1) goto L_0x00f5
                if (r4 <= r1) goto L_0x00f5
                if (r0 != 0) goto L_0x00f5
                kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl r0 = new kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl
                kotlin.reflect.jvm.internal.impl.types.Variance r2 = kotlin.reflect.jvm.internal.impl.types.Variance.INVARIANT
                java.lang.Object r5 = kotlin.collections.CollectionsKt.single(r5)
                java.lang.String r6 = "typeParameters.single()"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r6)
                kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor r5 = (kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor) r5
                kotlin.reflect.jvm.internal.impl.types.SimpleType r5 = r5.getDefaultType()
                kotlin.reflect.jvm.internal.impl.types.KotlinType r5 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r5
                r0.<init>(r2, r5)
                kotlin.ranges.IntRange r2 = new kotlin.ranges.IntRange
                r2.<init>(r1, r4)
                java.lang.Iterable r2 = (java.lang.Iterable) r2
                java.util.ArrayList r1 = new java.util.ArrayList
                int r4 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r2, r7)
                r1.<init>(r4)
                java.util.Collection r1 = (java.util.Collection) r1
                java.util.Iterator r2 = r2.iterator()
            L_0x00d5:
                boolean r4 = r2.hasNext()
                if (r4 == 0) goto L_0x00e5
                r4 = r2
                kotlin.collections.IntIterator r4 = (kotlin.collections.IntIterator) r4
                r4.nextInt()
                r1.add(r0)
                goto L_0x00d5
            L_0x00e5:
                r0 = r1
                java.util.List r0 = (java.util.List) r0
            L_0x00e8:
                kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations$Companion r1 = kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations.Companion
                kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r1 = r1.getEMPTY()
                kotlin.reflect.jvm.internal.impl.types.SimpleType r0 = kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory.simpleNotNullType(r1, r3, r0)
                kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r0
                return r0
            L_0x00f5:
                return r2
            L_0x00f6:
                return r2
            L_0x00f7:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassDescriptor.LazyJavaClassTypeConstructor.getPurelyImplementedSupertype():kotlin.reflect.jvm.internal.impl.types.KotlinType");
        }

        private final FqName getPurelyImplementsFqNameFromAnnotation() {
            Annotations annotations = LazyJavaClassDescriptor.this.getAnnotations();
            FqName fqName = JvmAnnotationNames.PURELY_IMPLEMENTS_ANNOTATION;
            Intrinsics.checkExpressionValueIsNotNull(fqName, "JvmAnnotationNames.PURELY_IMPLEMENTS_ANNOTATION");
            AnnotationDescriptor findAnnotation = annotations.findAnnotation(fqName);
            if (findAnnotation == null) {
                return null;
            }
            Object singleOrNull = kotlin.collections.CollectionsKt.singleOrNull((Iterable<? extends T>) findAnnotation.getAllValueArguments().values());
            if (!(singleOrNull instanceof StringValue)) {
                singleOrNull = null;
            }
            StringValue stringValue = (StringValue) singleOrNull;
            if (stringValue != null) {
                String str = (String) stringValue.getValue();
                if (str == null || !FqNamesUtilKt.isValidJavaFqName(str)) {
                    return null;
                }
                return new FqName(str);
            }
            return null;
        }

        /* access modifiers changed from: protected */
        @NotNull
        public SupertypeLoopChecker getSupertypeLoopChecker() {
            return LazyJavaClassDescriptor.this.c.getComponents().getSupertypeLoopChecker();
        }

        @NotNull
        public ClassDescriptor getDeclarationDescriptor() {
            return LazyJavaClassDescriptor.this;
        }

        @NotNull
        public String toString() {
            String asString = LazyJavaClassDescriptor.this.getName().asString();
            Intrinsics.checkExpressionValueIsNotNull(asString, "name.asString()");
            return asString;
        }
    }

    @Nullable
    public ClassDescriptor getCompanionObjectDescriptor() {
        return null;
    }

    @Nullable
    public ClassConstructorDescriptor getUnsubstitutedPrimaryConstructor() {
        return null;
    }

    public boolean isActual() {
        return false;
    }

    public boolean isCompanionObject() {
        return false;
    }

    public boolean isData() {
        return false;
    }

    public boolean isExpect() {
        return false;
    }

    public boolean isInline() {
        return false;
    }

    public /* synthetic */ LazyJavaClassDescriptor(LazyJavaResolverContext lazyJavaResolverContext, DeclarationDescriptor declarationDescriptor, JavaClass javaClass, ClassDescriptor classDescriptor, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 8) != 0) {
            classDescriptor = null;
        }
        this(lazyJavaResolverContext, declarationDescriptor, javaClass, classDescriptor);
    }

    public LazyJavaClassDescriptor(@NotNull LazyJavaResolverContext lazyJavaResolverContext, @NotNull DeclarationDescriptor declarationDescriptor, @NotNull JavaClass javaClass, @Nullable ClassDescriptor classDescriptor) {
        ClassKind classKind;
        Modality modality2;
        JavaClass javaClass2 = javaClass;
        LazyJavaResolverContext lazyJavaResolverContext2 = lazyJavaResolverContext;
        Intrinsics.checkParameterIsNotNull(lazyJavaResolverContext, "outerContext");
        DeclarationDescriptor declarationDescriptor2 = declarationDescriptor;
        Intrinsics.checkParameterIsNotNull(declarationDescriptor, "containingDeclaration");
        Intrinsics.checkParameterIsNotNull(javaClass2, "jClass");
        super(lazyJavaResolverContext.getStorageManager(), declarationDescriptor2, javaClass.getName(), lazyJavaResolverContext.getComponents().getSourceElementFactory().source(javaClass2), false);
        this.jClass = javaClass2;
        this.additionalSupertypeClassDescriptor = classDescriptor;
        this.c = ContextKt.childForClassOrPackage$default(lazyJavaResolverContext, this, this.jClass, 0, 4, null);
        ClassDescriptor classDescriptor2 = this;
        this.c.getComponents().getJavaResolverCache().recordClass(this.jClass, classDescriptor2);
        boolean z = false;
        boolean z2 = this.jClass.getLightClassOriginKind() == null;
        if (!_Assertions.ENABLED || z2) {
            if (this.jClass.isAnnotationType()) {
                classKind = ClassKind.ANNOTATION_CLASS;
            } else if (this.jClass.isInterface()) {
                classKind = ClassKind.INTERFACE;
            } else if (this.jClass.isEnum()) {
                classKind = ClassKind.ENUM_CLASS;
            } else {
                classKind = ClassKind.CLASS;
            }
            this.kind = classKind;
            if (this.jClass.isAnnotationType()) {
                modality2 = Modality.FINAL;
            } else {
                modality2 = Modality.Companion.convertFromFlags(this.jClass.isAbstract() || this.jClass.isInterface(), !this.jClass.isFinal());
            }
            this.modality = modality2;
            this.visibility = this.jClass.getVisibility();
            if (this.jClass.getOuterClass() != null && !this.jClass.isStatic()) {
                z = true;
            }
            this.isInner = z;
            this.typeConstructor = new LazyJavaClassTypeConstructor();
            this.unsubstitutedMemberScope = new LazyJavaClassMemberScope(this.c, classDescriptor2, this.jClass);
            this.innerClassesScope = new InnerClassesScopeWrapper(getUnsubstitutedMemberScope());
            this.staticScope = new LazyJavaStaticClassScope(this.c, this.jClass, this);
            this.annotations = LazyJavaAnnotationsKt.resolveAnnotations(this.c, this.jClass);
            this.declaredParameters = this.c.getStorageManager().createLazyValue(new LazyJavaClassDescriptor$declaredParameters$1(this));
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Creating LazyJavaClassDescriptor for light class ");
        sb.append(this.jClass);
        throw new AssertionError(sb.toString());
    }

    @NotNull
    public ClassKind getKind() {
        return this.kind;
    }

    @NotNull
    public Modality getModality() {
        return this.modality;
    }

    @NotNull
    public Visibility getVisibility() {
        Visibility visibility2 = (!Intrinsics.areEqual((Object) this.visibility, (Object) Visibilities.PRIVATE) || this.jClass.getOuterClass() != null) ? this.visibility : JavaVisibilities.PACKAGE_VISIBILITY;
        Intrinsics.checkExpressionValueIsNotNull(visibility2, "if (visibility == Visibi…ISIBILITY else visibility");
        return visibility2;
    }

    public boolean isInner() {
        return this.isInner;
    }

    @NotNull
    public TypeConstructor getTypeConstructor() {
        return this.typeConstructor;
    }

    @NotNull
    public LazyJavaClassMemberScope getUnsubstitutedMemberScope() {
        return this.unsubstitutedMemberScope;
    }

    @NotNull
    public MemberScope getUnsubstitutedInnerClassesScope() {
        return this.innerClassesScope;
    }

    @NotNull
    public MemberScope getStaticScope() {
        return this.staticScope;
    }

    @NotNull
    public List<ClassConstructorDescriptor> getConstructors() {
        return (List) this.unsubstitutedMemberScope.getConstructors$descriptors_jvm().invoke();
    }

    @NotNull
    public Annotations getAnnotations() {
        return this.annotations;
    }

    @NotNull
    public List<TypeParameterDescriptor> getDeclaredTypeParameters() {
        return (List) this.declaredParameters.invoke();
    }

    @NotNull
    public Collection<ClassDescriptor> getSealedSubclasses() {
        return kotlin.collections.CollectionsKt.emptyList();
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Lazy Java class ");
        sb.append(DescriptorUtilsKt.getFqNameUnsafe(this));
        return sb.toString();
    }

    @NotNull
    public final LazyJavaClassDescriptor copy$descriptors_jvm(@NotNull JavaResolverCache javaResolverCache, @Nullable ClassDescriptor classDescriptor) {
        Intrinsics.checkParameterIsNotNull(javaResolverCache, "javaResolverCache");
        LazyJavaResolverContext lazyJavaResolverContext = this.c;
        LazyJavaResolverContext replaceComponents = ContextKt.replaceComponents(lazyJavaResolverContext, lazyJavaResolverContext.getComponents().replace(javaResolverCache));
        DeclarationDescriptor containingDeclaration = getContainingDeclaration();
        Intrinsics.checkExpressionValueIsNotNull(containingDeclaration, "containingDeclaration");
        return new LazyJavaClassDescriptor(replaceComponents, containingDeclaration, this.jClass, classDescriptor);
    }
}
