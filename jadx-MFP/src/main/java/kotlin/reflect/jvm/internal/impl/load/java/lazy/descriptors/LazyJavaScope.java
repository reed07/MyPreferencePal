package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import com.brightcove.player.event.AbstractEvent;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.load.java.components.TypeUsage;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaMethodDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaPropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.ContextKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaAnnotationsKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeResolverKt;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaField;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindExclude.NonExtensions;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNotNull;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: LazyJavaScope.kt */
public abstract class LazyJavaScope extends MemberScopeImpl {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LazyJavaScope.class), "functionNamesLazy", "getFunctionNamesLazy()Ljava/util/Set;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LazyJavaScope.class), "propertyNamesLazy", "getPropertyNamesLazy()Ljava/util/Set;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LazyJavaScope.class), "classNamesLazy", "getClassNamesLazy()Ljava/util/Set;"))};
    private final NotNullLazyValue<Collection<DeclarationDescriptor>> allDescriptors = this.c.getStorageManager().createRecursionTolerantLazyValue(new LazyJavaScope$allDescriptors$1(this), CollectionsKt.emptyList());
    @NotNull
    private final LazyJavaResolverContext c;
    private final NotNullLazyValue classNamesLazy$delegate = this.c.getStorageManager().createLazyValue(new LazyJavaScope$classNamesLazy$2(this));
    @NotNull
    private final NotNullLazyValue<DeclaredMemberIndex> declaredMemberIndex = this.c.getStorageManager().createLazyValue(new LazyJavaScope$declaredMemberIndex$1(this));
    private final NotNullLazyValue functionNamesLazy$delegate = this.c.getStorageManager().createLazyValue(new LazyJavaScope$functionNamesLazy$2(this));
    private final MemoizedFunctionToNotNull<Name, Collection<SimpleFunctionDescriptor>> functions = this.c.getStorageManager().createMemoizedFunction(new LazyJavaScope$functions$1(this));
    private final MemoizedFunctionToNotNull<Name, List<PropertyDescriptor>> properties = this.c.getStorageManager().createMemoizedFunction(new LazyJavaScope$properties$1(this));
    private final NotNullLazyValue propertyNamesLazy$delegate = this.c.getStorageManager().createLazyValue(new LazyJavaScope$propertyNamesLazy$2(this));

    /* compiled from: LazyJavaScope.kt */
    protected static final class MethodSignatureData {
        @NotNull
        private final List<String> errors;
        private final boolean hasStableParameterNames;
        @Nullable
        private final KotlinType receiverType;
        @NotNull
        private final KotlinType returnType;
        @NotNull
        private final List<TypeParameterDescriptor> typeParameters;
        @NotNull
        private final List<ValueParameterDescriptor> valueParameters;

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (obj instanceof MethodSignatureData) {
                    MethodSignatureData methodSignatureData = (MethodSignatureData) obj;
                    if (Intrinsics.areEqual((Object) this.returnType, (Object) methodSignatureData.returnType) && Intrinsics.areEqual((Object) this.receiverType, (Object) methodSignatureData.receiverType) && Intrinsics.areEqual((Object) this.valueParameters, (Object) methodSignatureData.valueParameters) && Intrinsics.areEqual((Object) this.typeParameters, (Object) methodSignatureData.typeParameters)) {
                        if (!(this.hasStableParameterNames == methodSignatureData.hasStableParameterNames) || !Intrinsics.areEqual((Object) this.errors, (Object) methodSignatureData.errors)) {
                            return false;
                        }
                    }
                }
                return false;
            }
            return true;
        }

        public int hashCode() {
            KotlinType kotlinType = this.returnType;
            int i = 0;
            int hashCode = (kotlinType != null ? kotlinType.hashCode() : 0) * 31;
            KotlinType kotlinType2 = this.receiverType;
            int hashCode2 = (hashCode + (kotlinType2 != null ? kotlinType2.hashCode() : 0)) * 31;
            List<ValueParameterDescriptor> list = this.valueParameters;
            int hashCode3 = (hashCode2 + (list != null ? list.hashCode() : 0)) * 31;
            List<TypeParameterDescriptor> list2 = this.typeParameters;
            int hashCode4 = (hashCode3 + (list2 != null ? list2.hashCode() : 0)) * 31;
            boolean z = this.hasStableParameterNames;
            if (z) {
                z = true;
            }
            int i2 = (hashCode4 + (z ? 1 : 0)) * 31;
            List<String> list3 = this.errors;
            if (list3 != null) {
                i = list3.hashCode();
            }
            return i2 + i;
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("MethodSignatureData(returnType=");
            sb.append(this.returnType);
            sb.append(", receiverType=");
            sb.append(this.receiverType);
            sb.append(", valueParameters=");
            sb.append(this.valueParameters);
            sb.append(", typeParameters=");
            sb.append(this.typeParameters);
            sb.append(", hasStableParameterNames=");
            sb.append(this.hasStableParameterNames);
            sb.append(", errors=");
            sb.append(this.errors);
            sb.append(")");
            return sb.toString();
        }

        public MethodSignatureData(@NotNull KotlinType kotlinType, @Nullable KotlinType kotlinType2, @NotNull List<? extends ValueParameterDescriptor> list, @NotNull List<? extends TypeParameterDescriptor> list2, boolean z, @NotNull List<String> list3) {
            Intrinsics.checkParameterIsNotNull(kotlinType, "returnType");
            Intrinsics.checkParameterIsNotNull(list, "valueParameters");
            Intrinsics.checkParameterIsNotNull(list2, "typeParameters");
            Intrinsics.checkParameterIsNotNull(list3, AbstractEvent.ERRORS);
            this.returnType = kotlinType;
            this.receiverType = kotlinType2;
            this.valueParameters = list;
            this.typeParameters = list2;
            this.hasStableParameterNames = z;
            this.errors = list3;
        }

        @NotNull
        public final KotlinType getReturnType() {
            return this.returnType;
        }

        @Nullable
        public final KotlinType getReceiverType() {
            return this.receiverType;
        }

        @NotNull
        public final List<ValueParameterDescriptor> getValueParameters() {
            return this.valueParameters;
        }

        @NotNull
        public final List<TypeParameterDescriptor> getTypeParameters() {
            return this.typeParameters;
        }

        public final boolean getHasStableParameterNames() {
            return this.hasStableParameterNames;
        }

        @NotNull
        public final List<String> getErrors() {
            return this.errors;
        }
    }

    /* compiled from: LazyJavaScope.kt */
    protected static final class ResolvedValueParameters {
        @NotNull
        private final List<ValueParameterDescriptor> descriptors;
        private final boolean hasSynthesizedNames;

        public ResolvedValueParameters(@NotNull List<? extends ValueParameterDescriptor> list, boolean z) {
            Intrinsics.checkParameterIsNotNull(list, "descriptors");
            this.descriptors = list;
            this.hasSynthesizedNames = z;
        }

        @NotNull
        public final List<ValueParameterDescriptor> getDescriptors() {
            return this.descriptors;
        }

        public final boolean getHasSynthesizedNames() {
            return this.hasSynthesizedNames;
        }
    }

    private final Set<Name> getFunctionNamesLazy() {
        return (Set) StorageKt.getValue(this.functionNamesLazy$delegate, (Object) this, $$delegatedProperties[0]);
    }

    private final Set<Name> getPropertyNamesLazy() {
        return (Set) StorageKt.getValue(this.propertyNamesLazy$delegate, (Object) this, $$delegatedProperties[1]);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public abstract Set<Name> computeClassNames(@NotNull DescriptorKindFilter descriptorKindFilter, @Nullable Function1<? super Name, Boolean> function1);

    /* access modifiers changed from: protected */
    @NotNull
    public abstract Set<Name> computeFunctionNames(@NotNull DescriptorKindFilter descriptorKindFilter, @Nullable Function1<? super Name, Boolean> function1);

    /* access modifiers changed from: protected */
    @NotNull
    public abstract DeclaredMemberIndex computeMemberIndex();

    /* access modifiers changed from: protected */
    public abstract void computeNonDeclaredFunctions(@NotNull Collection<SimpleFunctionDescriptor> collection, @NotNull Name name);

    /* access modifiers changed from: protected */
    public abstract void computeNonDeclaredProperties(@NotNull Name name, @NotNull Collection<PropertyDescriptor> collection);

    /* access modifiers changed from: protected */
    @NotNull
    public abstract Set<Name> computePropertyNames(@NotNull DescriptorKindFilter descriptorKindFilter, @Nullable Function1<? super Name, Boolean> function1);

    /* access modifiers changed from: protected */
    @Nullable
    public abstract ReceiverParameterDescriptor getDispatchReceiverParameter();

    /* access modifiers changed from: protected */
    @NotNull
    public abstract DeclarationDescriptor getOwnerDescriptor();

    /* access modifiers changed from: protected */
    public boolean isVisibleAsFunction(@NotNull JavaMethodDescriptor javaMethodDescriptor) {
        Intrinsics.checkParameterIsNotNull(javaMethodDescriptor, "receiver$0");
        return true;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public abstract MethodSignatureData resolveMethodSignature(@NotNull JavaMethod javaMethod, @NotNull List<? extends TypeParameterDescriptor> list, @NotNull KotlinType kotlinType, @NotNull List<? extends ValueParameterDescriptor> list2);

    public LazyJavaScope(@NotNull LazyJavaResolverContext lazyJavaResolverContext) {
        Intrinsics.checkParameterIsNotNull(lazyJavaResolverContext, "c");
        this.c = lazyJavaResolverContext;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public final LazyJavaResolverContext getC() {
        return this.c;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public final NotNullLazyValue<DeclaredMemberIndex> getDeclaredMemberIndex() {
        return this.declaredMemberIndex;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public final JavaMethodDescriptor resolveMethodToFunctionDescriptor(@NotNull JavaMethod javaMethod) {
        Map map;
        Intrinsics.checkParameterIsNotNull(javaMethod, Param.METHOD);
        JavaMethodDescriptor createJavaMethod = JavaMethodDescriptor.createJavaMethod(getOwnerDescriptor(), LazyJavaAnnotationsKt.resolveAnnotations(this.c, javaMethod), javaMethod.getName(), this.c.getComponents().getSourceElementFactory().source(javaMethod));
        Intrinsics.checkExpressionValueIsNotNull(createJavaMethod, "JavaMethodDescriptor.cre….source(method)\n        )");
        LazyJavaResolverContext childForMethod$default = ContextKt.childForMethod$default(this.c, createJavaMethod, javaMethod, 0, 4, null);
        Iterable<JavaTypeParameter> typeParameters = javaMethod.getTypeParameters();
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameters, 10));
        for (JavaTypeParameter resolveTypeParameter : typeParameters) {
            TypeParameterDescriptor resolveTypeParameter2 = childForMethod$default.getTypeParameterResolver().resolveTypeParameter(resolveTypeParameter);
            if (resolveTypeParameter2 == null) {
                Intrinsics.throwNpe();
            }
            arrayList.add(resolveTypeParameter2);
        }
        List list = (List) arrayList;
        ResolvedValueParameters resolveValueParameters = resolveValueParameters(childForMethod$default, createJavaMethod, javaMethod.getValueParameters());
        MethodSignatureData resolveMethodSignature = resolveMethodSignature(javaMethod, list, computeMethodReturnType(javaMethod, childForMethod$default), resolveValueParameters.getDescriptors());
        KotlinType receiverType = resolveMethodSignature.getReceiverType();
        ReceiverParameterDescriptor createExtensionReceiverParameterForCallable = receiverType != null ? DescriptorFactory.createExtensionReceiverParameterForCallable(createJavaMethod, receiverType, Annotations.Companion.getEMPTY()) : null;
        ReceiverParameterDescriptor dispatchReceiverParameter = getDispatchReceiverParameter();
        List typeParameters2 = resolveMethodSignature.getTypeParameters();
        List valueParameters = resolveMethodSignature.getValueParameters();
        KotlinType returnType = resolveMethodSignature.getReturnType();
        Modality convertFromFlags = Modality.Companion.convertFromFlags(javaMethod.isAbstract(), !javaMethod.isFinal());
        Visibility visibility = javaMethod.getVisibility();
        if (resolveMethodSignature.getReceiverType() != null) {
            map = MapsKt.mapOf(TuplesKt.to(JavaMethodDescriptor.ORIGINAL_VALUE_PARAMETER_FOR_EXTENSION_RECEIVER, CollectionsKt.first(resolveValueParameters.getDescriptors())));
        } else {
            map = MapsKt.emptyMap();
        }
        createJavaMethod.initialize(createExtensionReceiverParameterForCallable, dispatchReceiverParameter, typeParameters2, valueParameters, returnType, convertFromFlags, visibility, map);
        createJavaMethod.setParameterNamesStatus(resolveMethodSignature.getHasStableParameterNames(), resolveValueParameters.getHasSynthesizedNames());
        if (!resolveMethodSignature.getErrors().isEmpty()) {
            childForMethod$default.getComponents().getSignaturePropagator().reportSignatureErrors(createJavaMethod, resolveMethodSignature.getErrors());
        }
        return createJavaMethod;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public final KotlinType computeMethodReturnType(@NotNull JavaMethod javaMethod, @NotNull LazyJavaResolverContext lazyJavaResolverContext) {
        Intrinsics.checkParameterIsNotNull(javaMethod, Param.METHOD);
        Intrinsics.checkParameterIsNotNull(lazyJavaResolverContext, "c");
        return lazyJavaResolverContext.getTypeResolver().transformJavaType(javaMethod.getReturnType(), JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, javaMethod.getContainingClass().isAnnotationType(), null, 2, null));
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00c5  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0115  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0135  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0139  */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope.ResolvedValueParameters resolveValueParameters(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext r22, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r23, @org.jetbrains.annotations.NotNull java.util.List<? extends kotlin.reflect.jvm.internal.impl.load.java.structure.JavaValueParameter> r24) {
        /*
            r21 = this;
            r0 = r22
            r1 = r23
            r2 = r24
            java.lang.String r3 = "c"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r0, r3)
            java.lang.String r3 = "function"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r1, r3)
            java.lang.String r3 = "jValueParameters"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r3)
            java.util.LinkedHashSet r3 = new java.util.LinkedHashSet
            r3.<init>()
            java.util.Set r3 = (java.util.Set) r3
            r4 = r2
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.lang.Iterable r4 = kotlin.collections.CollectionsKt.withIndex(r4)
            java.util.ArrayList r5 = new java.util.ArrayList
            r6 = 10
            int r6 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r4, r6)
            r5.<init>(r6)
            java.util.Collection r5 = (java.util.Collection) r5
            java.util.Iterator r4 = r4.iterator()
            r6 = 0
            r7 = 0
        L_0x0036:
            boolean r8 = r4.hasNext()
            if (r8 == 0) goto L_0x0180
            java.lang.Object r8 = r4.next()
            kotlin.collections.IndexedValue r8 = (kotlin.collections.IndexedValue) r8
            int r12 = r8.component1()
            java.lang.Object r8 = r8.component2()
            kotlin.reflect.jvm.internal.impl.load.java.structure.JavaValueParameter r8 = (kotlin.reflect.jvm.internal.impl.load.java.structure.JavaValueParameter) r8
            r9 = r8
            kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner r9 = (kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner) r9
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r13 = kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaAnnotationsKt.resolveAnnotations(r0, r9)
            kotlin.reflect.jvm.internal.impl.load.java.components.TypeUsage r9 = kotlin.reflect.jvm.internal.impl.load.java.components.TypeUsage.COMMON
            r10 = 3
            r11 = 0
            kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeAttributes r9 = kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeResolverKt.toAttributes$default(r9, r6, r11, r10, r11)
            kotlin.reflect.jvm.internal.impl.name.FqName r10 = kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNames.PARAMETER_NAME_FQ_NAME
            java.lang.String r14 = "JvmAnnotationNames.PARAMETER_NAME_FQ_NAME"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r10, r14)
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor r10 = r13.findAnnotation(r10)
            if (r10 == 0) goto L_0x007e
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r10 = kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.firstArgument(r10)
            if (r10 == 0) goto L_0x007e
            boolean r14 = r10 instanceof kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue
            if (r14 != 0) goto L_0x0073
            r10 = r11
        L_0x0073:
            kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue r10 = (kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue) r10
            if (r10 == 0) goto L_0x007e
            java.lang.Object r10 = r10.getValue()
            java.lang.String r10 = (java.lang.String) r10
            goto L_0x007f
        L_0x007e:
            r10 = r11
        L_0x007f:
            boolean r14 = r8.isVararg()
            r15 = 1
            if (r14 == 0) goto L_0x00c5
            kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType r14 = r8.getType()
            boolean r6 = r14 instanceof kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayType
            if (r6 != 0) goto L_0x008f
            r14 = r11
        L_0x008f:
            kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayType r14 = (kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayType) r14
            if (r14 == 0) goto L_0x00ac
            kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeResolver r6 = r22.getTypeResolver()
            kotlin.reflect.jvm.internal.impl.types.KotlinType r6 = r6.transformArrayType(r14, r9, r15)
            kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor r9 = r22.getModule()
            kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns r9 = r9.getBuiltIns()
            kotlin.reflect.jvm.internal.impl.types.KotlinType r9 = r9.getArrayElementType(r6)
            kotlin.Pair r6 = kotlin.TuplesKt.to(r6, r9)
            goto L_0x00d5
        L_0x00ac:
            java.lang.AssertionError r0 = new java.lang.AssertionError
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Vararg parameter should be an array: "
            r1.append(r2)
            r1.append(r8)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x00c5:
            kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeResolver r6 = r22.getTypeResolver()
            kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType r14 = r8.getType()
            kotlin.reflect.jvm.internal.impl.types.KotlinType r6 = r6.transformJavaType(r14, r9)
            kotlin.Pair r6 = kotlin.TuplesKt.to(r6, r11)
        L_0x00d5:
            java.lang.Object r9 = r6.component1()
            r14 = r9
            kotlin.reflect.jvm.internal.impl.types.KotlinType r14 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r14
            java.lang.Object r6 = r6.component2()
            r19 = r6
            kotlin.reflect.jvm.internal.impl.types.KotlinType r19 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r19
            kotlin.reflect.jvm.internal.impl.name.Name r6 = r23.getName()
            java.lang.String r6 = r6.asString()
            java.lang.String r9 = "equals"
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r9)
            if (r6 == 0) goto L_0x0113
            int r6 = r24.size()
            if (r6 != r15) goto L_0x0113
            kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor r6 = r22.getModule()
            kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns r6 = r6.getBuiltIns()
            kotlin.reflect.jvm.internal.impl.types.SimpleType r6 = r6.getNullableAnyType()
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r14)
            if (r6 == 0) goto L_0x0113
            java.lang.String r6 = "other"
            kotlin.reflect.jvm.internal.impl.name.Name r6 = kotlin.reflect.jvm.internal.impl.name.Name.identifier(r6)
            goto L_0x014f
        L_0x0113:
            if (r10 == 0) goto L_0x012e
            r6 = r10
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            int r6 = r6.length()
            if (r6 <= 0) goto L_0x0120
            r6 = 1
            goto L_0x0121
        L_0x0120:
            r6 = 0
        L_0x0121:
            if (r6 == 0) goto L_0x012e
            boolean r6 = r3.add(r10)
            if (r6 == 0) goto L_0x012e
            kotlin.reflect.jvm.internal.impl.name.Name r6 = kotlin.reflect.jvm.internal.impl.name.Name.identifier(r10)
            goto L_0x014f
        L_0x012e:
            kotlin.reflect.jvm.internal.impl.name.Name r6 = r8.getName()
            if (r6 != 0) goto L_0x0135
            goto L_0x0136
        L_0x0135:
            r15 = r7
        L_0x0136:
            if (r6 == 0) goto L_0x0139
            goto L_0x014e
        L_0x0139:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r7 = 112(0x70, float:1.57E-43)
            r6.append(r7)
            r6.append(r12)
            java.lang.String r6 = r6.toString()
            kotlin.reflect.jvm.internal.impl.name.Name r6 = kotlin.reflect.jvm.internal.impl.name.Name.identifier(r6)
        L_0x014e:
            r7 = r15
        L_0x014f:
            java.lang.String r9 = "if (function.name.asStri…(\"p$index\")\n            }"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r9)
            kotlin.reflect.jvm.internal.impl.descriptors.impl.ValueParameterDescriptorImpl r15 = new kotlin.reflect.jvm.internal.impl.descriptors.impl.ValueParameterDescriptorImpl
            r10 = r1
            kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor r10 = (kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor) r10
            r11 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverComponents r9 = r22.getComponents()
            kotlin.reflect.jvm.internal.impl.load.java.sources.JavaSourceElementFactory r9 = r9.getSourceElementFactory()
            kotlin.reflect.jvm.internal.impl.load.java.structure.JavaElement r8 = (kotlin.reflect.jvm.internal.impl.load.java.structure.JavaElement) r8
            kotlin.reflect.jvm.internal.impl.load.java.sources.JavaSourceElement r8 = r9.source(r8)
            r20 = r8
            kotlin.reflect.jvm.internal.impl.descriptors.SourceElement r20 = (kotlin.reflect.jvm.internal.impl.descriptors.SourceElement) r20
            r9 = r15
            r8 = r14
            r14 = r6
            r6 = r15
            r15 = r8
            r9.<init>(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            r5.add(r6)
            r6 = 0
            goto L_0x0036
        L_0x0180:
            java.util.List r5 = (java.util.List) r5
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.List r0 = kotlin.collections.CollectionsKt.toList(r5)
            kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope$ResolvedValueParameters r1 = new kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope$ResolvedValueParameters
            r1.<init>(r0, r7)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope.resolveValueParameters(kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext, kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor, java.util.List):kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope$ResolvedValueParameters");
    }

    @NotNull
    public Set<Name> getFunctionNames() {
        return getFunctionNamesLazy();
    }

    @NotNull
    public Set<Name> getVariableNames() {
        return getPropertyNamesLazy();
    }

    @NotNull
    public Collection<SimpleFunctionDescriptor> getContributedFunctions(@NotNull Name name, @NotNull LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
        if (!getFunctionNames().contains(name)) {
            return CollectionsKt.emptyList();
        }
        return (Collection) this.functions.invoke(name);
    }

    /* access modifiers changed from: private */
    public final PropertyDescriptor resolveProperty(JavaField javaField) {
        PropertyDescriptorImpl createPropertyDescriptor = createPropertyDescriptor(javaField);
        createPropertyDescriptor.initialize(null, null, null, null);
        createPropertyDescriptor.setType(getPropertyType(javaField), CollectionsKt.emptyList(), getDispatchReceiverParameter(), null);
        if (DescriptorUtils.shouldRecordInitializerForProperty(createPropertyDescriptor, createPropertyDescriptor.getType())) {
            createPropertyDescriptor.setCompileTimeInitializer(this.c.getStorageManager().createNullableLazyValue(new LazyJavaScope$resolveProperty$1(this, javaField, createPropertyDescriptor)));
        }
        PropertyDescriptor propertyDescriptor = createPropertyDescriptor;
        this.c.getComponents().getJavaResolverCache().recordField(javaField, propertyDescriptor);
        return propertyDescriptor;
    }

    private final PropertyDescriptorImpl createPropertyDescriptor(JavaField javaField) {
        boolean z = !javaField.isFinal();
        JavaPropertyDescriptor create = JavaPropertyDescriptor.create(getOwnerDescriptor(), LazyJavaAnnotationsKt.resolveAnnotations(this.c, javaField), Modality.FINAL, javaField.getVisibility(), z, javaField.getName(), this.c.getComponents().getSourceElementFactory().source(javaField), isFinalStatic(javaField));
        Intrinsics.checkExpressionValueIsNotNull(create, "JavaPropertyDescriptor.c…d.isFinalStatic\n        )");
        return create;
    }

    private final boolean isFinalStatic(@NotNull JavaField javaField) {
        return javaField.isFinal() && javaField.isStatic();
    }

    private final KotlinType getPropertyType(JavaField javaField) {
        boolean z = false;
        KotlinType transformJavaType = this.c.getTypeResolver().transformJavaType(javaField.getType(), JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, false, null, 3, null));
        if ((KotlinBuiltIns.isPrimitiveType(transformJavaType) || KotlinBuiltIns.isString(transformJavaType)) && isFinalStatic(javaField) && javaField.getHasConstantNotNullInitializer()) {
            z = true;
        }
        if (!z) {
            return transformJavaType;
        }
        KotlinType makeNotNullable = TypeUtils.makeNotNullable(transformJavaType);
        Intrinsics.checkExpressionValueIsNotNull(makeNotNullable, "TypeUtils.makeNotNullable(propertyType)");
        return makeNotNullable;
    }

    @NotNull
    public Collection<PropertyDescriptor> getContributedVariables(@NotNull Name name, @NotNull LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
        if (!getVariableNames().contains(name)) {
            return CollectionsKt.emptyList();
        }
        return (Collection) this.properties.invoke(name);
    }

    @NotNull
    public Collection<DeclarationDescriptor> getContributedDescriptors(@NotNull DescriptorKindFilter descriptorKindFilter, @NotNull Function1<? super Name, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(descriptorKindFilter, "kindFilter");
        Intrinsics.checkParameterIsNotNull(function1, "nameFilter");
        return (Collection) this.allDescriptors.invoke();
    }

    /* access modifiers changed from: protected */
    @NotNull
    public final List<DeclarationDescriptor> computeDescriptors(@NotNull DescriptorKindFilter descriptorKindFilter, @NotNull Function1<? super Name, Boolean> function1, @NotNull LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(descriptorKindFilter, "kindFilter");
        Intrinsics.checkParameterIsNotNull(function1, "nameFilter");
        Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        if (descriptorKindFilter.acceptsKinds(DescriptorKindFilter.Companion.getCLASSIFIERS_MASK())) {
            for (Name name : computeClassNames(descriptorKindFilter, function1)) {
                if (((Boolean) function1.invoke(name)).booleanValue()) {
                    kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(linkedHashSet, getContributedClassifier(name, lookupLocation));
                }
            }
        }
        if (descriptorKindFilter.acceptsKinds(DescriptorKindFilter.Companion.getFUNCTIONS_MASK()) && !descriptorKindFilter.getExcludes().contains(NonExtensions.INSTANCE)) {
            for (Name name2 : computeFunctionNames(descriptorKindFilter, function1)) {
                if (((Boolean) function1.invoke(name2)).booleanValue()) {
                    linkedHashSet.addAll(getContributedFunctions(name2, lookupLocation));
                }
            }
        }
        if (descriptorKindFilter.acceptsKinds(DescriptorKindFilter.Companion.getVARIABLES_MASK()) && !descriptorKindFilter.getExcludes().contains(NonExtensions.INSTANCE)) {
            for (Name name3 : computePropertyNames(descriptorKindFilter, function1)) {
                if (((Boolean) function1.invoke(name3)).booleanValue()) {
                    linkedHashSet.addAll(getContributedVariables(name3, lookupLocation));
                }
            }
        }
        return CollectionsKt.toList(linkedHashSet);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Lazy scope for ");
        sb.append(getOwnerDescriptor());
        return sb.toString();
    }
}
