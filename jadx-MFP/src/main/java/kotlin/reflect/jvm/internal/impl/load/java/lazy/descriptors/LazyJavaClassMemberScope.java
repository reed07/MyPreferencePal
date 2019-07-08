package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassConstructorDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassDescriptorBase;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyGetterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ValueParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.incremental.UtilsKt;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.load.java.BuiltinMethodsWithDifferentJvmName;
import kotlin.reflect.jvm.internal.impl.load.java.BuiltinMethodsWithSpecialGenericSignature;
import kotlin.reflect.jvm.internal.impl.load.java.BuiltinSpecialProperties;
import kotlin.reflect.jvm.internal.impl.load.java.JavaIncompatibilityRulesOverridabilityCondition;
import kotlin.reflect.jvm.internal.impl.load.java.JavaVisibilities;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAbi;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNames;
import kotlin.reflect.jvm.internal.impl.load.java.PropertiesConventionUtilKt;
import kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers;
import kotlin.reflect.jvm.internal.impl.load.java.components.DescriptorResolverUtils;
import kotlin.reflect.jvm.internal.impl.load.java.components.SignaturePropagator.PropagatedSignature;
import kotlin.reflect.jvm.internal.impl.load.java.components.TypeUsage;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaMethodDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaPropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.UtilKt;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.ValueParameterData;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.ContextKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaAnnotationsKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeAttributes;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeResolverKt;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaConstructor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaElement;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaField;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.OverrideCompatibilityInfo;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.OverrideCompatibilityInfo.Result;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: LazyJavaClassMemberScope.kt */
public final class LazyJavaClassMemberScope extends LazyJavaScope {
    @NotNull
    private final NotNullLazyValue<List<ClassConstructorDescriptor>> constructors;
    /* access modifiers changed from: private */
    public final NotNullLazyValue<Map<Name, JavaField>> enumEntryIndex;
    /* access modifiers changed from: private */
    public final JavaClass jClass;
    /* access modifiers changed from: private */
    public final NotNullLazyValue<Set<Name>> nestedClassIndex;
    private final MemoizedFunctionToNullable<Name, ClassDescriptorBase> nestedClasses;
    @NotNull
    private final ClassDescriptor ownerDescriptor;

    /* access modifiers changed from: protected */
    @NotNull
    public ClassDescriptor getOwnerDescriptor() {
        return this.ownerDescriptor;
    }

    public LazyJavaClassMemberScope(@NotNull LazyJavaResolverContext lazyJavaResolverContext, @NotNull ClassDescriptor classDescriptor, @NotNull JavaClass javaClass) {
        Intrinsics.checkParameterIsNotNull(lazyJavaResolverContext, "c");
        Intrinsics.checkParameterIsNotNull(classDescriptor, "ownerDescriptor");
        Intrinsics.checkParameterIsNotNull(javaClass, "jClass");
        super(lazyJavaResolverContext);
        this.ownerDescriptor = classDescriptor;
        this.jClass = javaClass;
        this.constructors = lazyJavaResolverContext.getStorageManager().createLazyValue(new LazyJavaClassMemberScope$constructors$1(this, lazyJavaResolverContext));
        this.nestedClassIndex = lazyJavaResolverContext.getStorageManager().createLazyValue(new LazyJavaClassMemberScope$nestedClassIndex$1(this));
        this.enumEntryIndex = lazyJavaResolverContext.getStorageManager().createLazyValue(new LazyJavaClassMemberScope$enumEntryIndex$1(this));
        this.nestedClasses = lazyJavaResolverContext.getStorageManager().createMemoizedFunctionWithNullableValues(new LazyJavaClassMemberScope$nestedClasses$1(this, lazyJavaResolverContext));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public ClassDeclaredMemberIndex computeMemberIndex() {
        return new ClassDeclaredMemberIndex(this.jClass, LazyJavaClassMemberScope$computeMemberIndex$1.INSTANCE);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashSet<Name> computeFunctionNames(@NotNull DescriptorKindFilter descriptorKindFilter, @Nullable Function1<? super Name, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(descriptorKindFilter, "kindFilter");
        TypeConstructor typeConstructor = getOwnerDescriptor().getTypeConstructor();
        Intrinsics.checkExpressionValueIsNotNull(typeConstructor, "ownerDescriptor.typeConstructor");
        Collection supertypes = typeConstructor.getSupertypes();
        Intrinsics.checkExpressionValueIsNotNull(supertypes, "ownerDescriptor.typeConstructor.supertypes");
        Iterable<KotlinType> iterable = supertypes;
        Collection hashSet = new HashSet();
        for (KotlinType memberScope : iterable) {
            CollectionsKt.addAll(hashSet, (Iterable<? extends T>) memberScope.getMemberScope().getFunctionNames());
        }
        HashSet<Name> hashSet2 = (HashSet) hashSet;
        hashSet2.addAll(((DeclaredMemberIndex) getDeclaredMemberIndex().invoke()).getMethodNames());
        hashSet2.addAll(computeClassNames(descriptorKindFilter, function1));
        return hashSet2;
    }

    @NotNull
    public final NotNullLazyValue<List<ClassConstructorDescriptor>> getConstructors$descriptors_jvm() {
        return this.constructors;
    }

    /* access modifiers changed from: protected */
    public boolean isVisibleAsFunction(@NotNull JavaMethodDescriptor javaMethodDescriptor) {
        Intrinsics.checkParameterIsNotNull(javaMethodDescriptor, "receiver$0");
        if (this.jClass.isAnnotationType()) {
            return false;
        }
        return isVisibleAsFunctionInCurrentClass(javaMethodDescriptor);
    }

    private final boolean isVisibleAsFunctionInCurrentClass(SimpleFunctionDescriptor simpleFunctionDescriptor) {
        boolean z;
        boolean z2;
        boolean z3;
        Name name = simpleFunctionDescriptor.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "function.name");
        Iterable propertyNamesCandidatesByAccessorName = PropertiesConventionUtilKt.getPropertyNamesCandidatesByAccessorName(name);
        boolean z4 = true;
        if (!(propertyNamesCandidatesByAccessorName instanceof Collection) || !((Collection) propertyNamesCandidatesByAccessorName).isEmpty()) {
            Iterator it = propertyNamesCandidatesByAccessorName.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                Iterable propertiesFromSupertypes = getPropertiesFromSupertypes((Name) it.next());
                if (!(propertiesFromSupertypes instanceof Collection) || !((Collection) propertiesFromSupertypes).isEmpty()) {
                    Iterator it2 = propertiesFromSupertypes.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            z2 = false;
                            continue;
                            break;
                        }
                        PropertyDescriptor propertyDescriptor = (PropertyDescriptor) it2.next();
                        if (!doesClassOverridesProperty(propertyDescriptor, new LazyJavaClassMemberScope$isVisibleAsFunctionInCurrentClass$$inlined$any$lambda$1(this, simpleFunctionDescriptor)) || (!propertyDescriptor.isVar() && JvmAbi.isSetterName(simpleFunctionDescriptor.getName().asString()))) {
                            z3 = false;
                            continue;
                        } else {
                            z3 = true;
                            continue;
                        }
                        if (z3) {
                            z2 = true;
                            continue;
                            break;
                        }
                    }
                } else {
                    z2 = false;
                    continue;
                }
                if (z2) {
                    z = true;
                    break;
                }
            }
        } else {
            z = false;
        }
        if (z) {
            return false;
        }
        if (doesOverrideRenamedBuiltins(simpleFunctionDescriptor) || shouldBeVisibleAsOverrideOfBuiltInWithErasedValueParameters(simpleFunctionDescriptor) || doesOverrideSuspendFunction(simpleFunctionDescriptor)) {
            z4 = false;
        }
        return z4;
    }

    private final boolean shouldBeVisibleAsOverrideOfBuiltInWithErasedValueParameters(@NotNull SimpleFunctionDescriptor simpleFunctionDescriptor) {
        BuiltinMethodsWithSpecialGenericSignature builtinMethodsWithSpecialGenericSignature = BuiltinMethodsWithSpecialGenericSignature.INSTANCE;
        Name name = simpleFunctionDescriptor.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "name");
        boolean sameAsBuiltinMethodWithErasedValueParameters = builtinMethodsWithSpecialGenericSignature.getSameAsBuiltinMethodWithErasedValueParameters(name);
        boolean z = false;
        if (!sameAsBuiltinMethodWithErasedValueParameters) {
            return false;
        }
        Name name2 = simpleFunctionDescriptor.getName();
        Intrinsics.checkExpressionValueIsNotNull(name2, "name");
        Iterable<SimpleFunctionDescriptor> functionsFromSupertypes = getFunctionsFromSupertypes(name2);
        Collection arrayList = new ArrayList();
        for (SimpleFunctionDescriptor overriddenBuiltinFunctionWithErasedValueParametersInJava : functionsFromSupertypes) {
            FunctionDescriptor overriddenBuiltinFunctionWithErasedValueParametersInJava2 = BuiltinMethodsWithSpecialGenericSignature.getOverriddenBuiltinFunctionWithErasedValueParametersInJava(overriddenBuiltinFunctionWithErasedValueParametersInJava);
            if (overriddenBuiltinFunctionWithErasedValueParametersInJava2 != null) {
                arrayList.add(overriddenBuiltinFunctionWithErasedValueParametersInJava2);
            }
        }
        Iterable iterable = (List) arrayList;
        if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
            Iterator it = iterable.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (hasSameJvmDescriptorButDoesNotOverride(simpleFunctionDescriptor, (FunctionDescriptor) it.next())) {
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return z;
    }

    /* access modifiers changed from: private */
    public final Collection<SimpleFunctionDescriptor> searchMethodsByNameWithoutBuiltinMagic(Name name) {
        Iterable<JavaMethod> findMethodsByName = ((DeclaredMemberIndex) getDeclaredMemberIndex().invoke()).findMethodsByName(name);
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(findMethodsByName, 10));
        for (JavaMethod resolveMethodToFunctionDescriptor : findMethodsByName) {
            arrayList.add(resolveMethodToFunctionDescriptor(resolveMethodToFunctionDescriptor));
        }
        return (List) arrayList;
    }

    /* access modifiers changed from: private */
    public final Collection<SimpleFunctionDescriptor> searchMethodsInSupertypesWithoutBuiltinMagic(Name name) {
        Iterable functionsFromSupertypes = getFunctionsFromSupertypes(name);
        Collection arrayList = new ArrayList();
        for (Object next : functionsFromSupertypes) {
            SimpleFunctionDescriptor simpleFunctionDescriptor = (SimpleFunctionDescriptor) next;
            if (!(SpecialBuiltinMembers.doesOverrideBuiltinWithDifferentJvmName(simpleFunctionDescriptor) || BuiltinMethodsWithSpecialGenericSignature.getOverriddenBuiltinFunctionWithErasedValueParametersInJava(simpleFunctionDescriptor) != null)) {
                arrayList.add(next);
            }
        }
        return (List) arrayList;
    }

    private final boolean doesOverrideRenamedBuiltins(@NotNull SimpleFunctionDescriptor simpleFunctionDescriptor) {
        boolean z;
        BuiltinMethodsWithDifferentJvmName builtinMethodsWithDifferentJvmName = BuiltinMethodsWithDifferentJvmName.INSTANCE;
        Name name = simpleFunctionDescriptor.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "name");
        Iterable<Name> builtinFunctionNamesByJvmName = builtinMethodsWithDifferentJvmName.getBuiltinFunctionNamesByJvmName(name);
        if ((builtinFunctionNamesByJvmName instanceof Collection) && ((Collection) builtinFunctionNamesByJvmName).isEmpty()) {
            return false;
        }
        for (Name name2 : builtinFunctionNamesByJvmName) {
            Iterable functionsFromSupertypes = getFunctionsFromSupertypes(name2);
            Collection arrayList = new ArrayList();
            for (Object next : functionsFromSupertypes) {
                if (SpecialBuiltinMembers.doesOverrideBuiltinWithDifferentJvmName((SimpleFunctionDescriptor) next)) {
                    arrayList.add(next);
                }
            }
            List list = (List) arrayList;
            if (list.isEmpty()) {
                z = false;
                continue;
            } else {
                SimpleFunctionDescriptor createRenamedCopy = createRenamedCopy(simpleFunctionDescriptor, name2);
                Iterable iterable = list;
                if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                    Iterator it = iterable.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (doesOverrideRenamedDescriptor((SimpleFunctionDescriptor) it.next(), createRenamedCopy)) {
                                z = true;
                                continue;
                                break;
                            }
                        } else {
                            z = false;
                            continue;
                            break;
                        }
                    }
                } else {
                    z = false;
                    continue;
                }
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    private final boolean doesOverrideSuspendFunction(@NotNull SimpleFunctionDescriptor simpleFunctionDescriptor) {
        boolean z;
        SimpleFunctionDescriptor createSuspendView = createSuspendView(simpleFunctionDescriptor);
        boolean z2 = false;
        if (createSuspendView == null) {
            return false;
        }
        Name name = simpleFunctionDescriptor.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "name");
        Iterable functionsFromSupertypes = getFunctionsFromSupertypes(name);
        if (!(functionsFromSupertypes instanceof Collection) || !((Collection) functionsFromSupertypes).isEmpty()) {
            Iterator it = functionsFromSupertypes.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                SimpleFunctionDescriptor simpleFunctionDescriptor2 = (SimpleFunctionDescriptor) it.next();
                if (!simpleFunctionDescriptor2.isSuspend() || !doesOverride(createSuspendView, simpleFunctionDescriptor2)) {
                    z = false;
                    continue;
                } else {
                    z = true;
                    continue;
                }
                if (z) {
                    z2 = true;
                    break;
                }
            }
        }
        return z2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0052  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor createSuspendView(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor r5) {
        /*
            r4 = this;
            java.util.List r0 = r5.getValueParameters()
            java.lang.String r1 = "valueParameters"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            java.lang.Object r0 = kotlin.collections.CollectionsKt.lastOrNull(r0)
            kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor) r0
            r1 = 0
            if (r0 == 0) goto L_0x008e
            kotlin.reflect.jvm.internal.impl.types.KotlinType r2 = r0.getType()
            kotlin.reflect.jvm.internal.impl.types.TypeConstructor r2 = r2.getConstructor()
            kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor r2 = r2.getDeclarationDescriptor()
            if (r2 == 0) goto L_0x0037
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r2 = (kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor) r2
            kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe r2 = kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.getFqNameUnsafe(r2)
            if (r2 == 0) goto L_0x0037
            boolean r3 = r2.isSafe()
            if (r3 == 0) goto L_0x002f
            goto L_0x0030
        L_0x002f:
            r2 = r1
        L_0x0030:
            if (r2 == 0) goto L_0x0037
            kotlin.reflect.jvm.internal.impl.name.FqName r2 = r2.toSafe()
            goto L_0x0038
        L_0x0037:
            r2 = r1
        L_0x0038:
            kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext r3 = r4.getC()
            kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverComponents r3 = r3.getComponents()
            kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverSettings r3 = r3.getSettings()
            boolean r3 = r3.isReleaseCoroutines()
            boolean r2 = kotlin.reflect.jvm.internal.impl.builtins.SuspendFunctionTypesKt.isContinuation(r2, r3)
            if (r2 == 0) goto L_0x004f
            goto L_0x0050
        L_0x004f:
            r0 = r1
        L_0x0050:
            if (r0 == 0) goto L_0x008e
            kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder r1 = r5.newCopyBuilder()
            java.util.List r5 = r5.getValueParameters()
            java.lang.String r2 = "valueParameters"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r2)
            r2 = 1
            java.util.List r5 = kotlin.collections.CollectionsKt.dropLast(r5, r2)
            kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder r5 = r1.setValueParameters(r5)
            kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = r0.getType()
            java.util.List r0 = r0.getArguments()
            r1 = 0
            java.lang.Object r0 = r0.get(r1)
            kotlin.reflect.jvm.internal.impl.types.TypeProjection r0 = (kotlin.reflect.jvm.internal.impl.types.TypeProjection) r0
            kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = r0.getType()
            kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder r5 = r5.setReturnType(r0)
            kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r5 = r5.build()
            kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor r5 = (kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor) r5
            r0 = r5
            kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl r0 = (kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl) r0
            if (r0 == 0) goto L_0x008d
            r0.setSuspend(r2)
        L_0x008d:
            return r5
        L_0x008e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope.createSuspendView(kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor):kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor");
    }

    private final SimpleFunctionDescriptor createRenamedCopy(@NotNull SimpleFunctionDescriptor simpleFunctionDescriptor, Name name) {
        CopyBuilder newCopyBuilder = simpleFunctionDescriptor.newCopyBuilder();
        newCopyBuilder.setName(name);
        newCopyBuilder.setSignatureChange();
        newCopyBuilder.setPreserveSourceElement();
        FunctionDescriptor build = newCopyBuilder.build();
        if (build == null) {
            Intrinsics.throwNpe();
        }
        return (SimpleFunctionDescriptor) build;
    }

    private final boolean doesOverrideRenamedDescriptor(SimpleFunctionDescriptor simpleFunctionDescriptor, FunctionDescriptor functionDescriptor) {
        if (BuiltinMethodsWithDifferentJvmName.INSTANCE.isRemoveAtByIndex(simpleFunctionDescriptor)) {
            functionDescriptor = functionDescriptor.getOriginal();
        }
        Intrinsics.checkExpressionValueIsNotNull(functionDescriptor, "if (superDescriptor.isRe…iginal else subDescriptor");
        return doesOverride(functionDescriptor, simpleFunctionDescriptor);
    }

    private final boolean doesOverride(@NotNull CallableDescriptor callableDescriptor, CallableDescriptor callableDescriptor2) {
        OverrideCompatibilityInfo isOverridableByWithoutExternalConditions = OverridingUtil.DEFAULT.isOverridableByWithoutExternalConditions(callableDescriptor2, callableDescriptor, true);
        Intrinsics.checkExpressionValueIsNotNull(isOverridableByWithoutExternalConditions, "OverridingUtil.DEFAULT.i…erDescriptor, this, true)");
        Result result = isOverridableByWithoutExternalConditions.getResult();
        Intrinsics.checkExpressionValueIsNotNull(result, "OverridingUtil.DEFAULT.i…iptor, this, true).result");
        if (result != Result.OVERRIDABLE || JavaIncompatibilityRulesOverridabilityCondition.Companion.doesJavaOverrideHaveIncompatibleValueParameterKinds(callableDescriptor2, callableDescriptor)) {
            return false;
        }
        return true;
    }

    private final SimpleFunctionDescriptor findGetterOverride(@NotNull PropertyDescriptor propertyDescriptor, Function1<? super Name, ? extends Collection<? extends SimpleFunctionDescriptor>> function1) {
        PropertyGetterDescriptor getter = propertyDescriptor.getGetter();
        String str = null;
        CallableDescriptor callableDescriptor = getter != null ? (PropertyGetterDescriptor) SpecialBuiltinMembers.getOverriddenBuiltinWithDifferentJvmName(getter) : null;
        if (callableDescriptor != null) {
            str = BuiltinSpecialProperties.INSTANCE.getBuiltinSpecialPropertyGetterName((CallableMemberDescriptor) callableDescriptor);
        }
        if (str != null && !SpecialBuiltinMembers.hasRealKotlinSuperClassWithOverrideOf(getOwnerDescriptor(), callableDescriptor)) {
            return findGetterByName(propertyDescriptor, str, function1);
        }
        String str2 = JvmAbi.getterName(propertyDescriptor.getName().asString());
        Intrinsics.checkExpressionValueIsNotNull(str2, "JvmAbi.getterName(name.asString())");
        return findGetterByName(propertyDescriptor, str2, function1);
    }

    private final SimpleFunctionDescriptor findGetterByName(@NotNull PropertyDescriptor propertyDescriptor, String str, Function1<? super Name, ? extends Collection<? extends SimpleFunctionDescriptor>> function1) {
        SimpleFunctionDescriptor simpleFunctionDescriptor;
        Name identifier = Name.identifier(str);
        Intrinsics.checkExpressionValueIsNotNull(identifier, "Name.identifier(getterName)");
        Iterator it = ((Iterable) function1.invoke(identifier)).iterator();
        do {
            simpleFunctionDescriptor = null;
            if (!it.hasNext()) {
                break;
            }
            SimpleFunctionDescriptor simpleFunctionDescriptor2 = (SimpleFunctionDescriptor) it.next();
            if (simpleFunctionDescriptor2.getValueParameters().size() == 0) {
                KotlinTypeChecker kotlinTypeChecker = KotlinTypeChecker.DEFAULT;
                KotlinType returnType = simpleFunctionDescriptor2.getReturnType();
                if (returnType != null ? kotlinTypeChecker.isSubtypeOf(returnType, propertyDescriptor.getType()) : false) {
                    simpleFunctionDescriptor = simpleFunctionDescriptor2;
                    continue;
                } else {
                    continue;
                }
            }
        } while (simpleFunctionDescriptor == null);
        return simpleFunctionDescriptor;
    }

    private final SimpleFunctionDescriptor findSetterOverride(@NotNull PropertyDescriptor propertyDescriptor, Function1<? super Name, ? extends Collection<? extends SimpleFunctionDescriptor>> function1) {
        SimpleFunctionDescriptor simpleFunctionDescriptor;
        Name identifier = Name.identifier(JvmAbi.setterName(propertyDescriptor.getName().asString()));
        Intrinsics.checkExpressionValueIsNotNull(identifier, "Name.identifier(JvmAbi.s…terName(name.asString()))");
        Iterator it = ((Iterable) function1.invoke(identifier)).iterator();
        do {
            simpleFunctionDescriptor = null;
            if (!it.hasNext()) {
                break;
            }
            SimpleFunctionDescriptor simpleFunctionDescriptor2 = (SimpleFunctionDescriptor) it.next();
            if (simpleFunctionDescriptor2.getValueParameters().size() == 1) {
                KotlinType returnType = simpleFunctionDescriptor2.getReturnType();
                if (returnType != null && KotlinBuiltIns.isUnit(returnType)) {
                    KotlinTypeChecker kotlinTypeChecker = KotlinTypeChecker.DEFAULT;
                    List valueParameters = simpleFunctionDescriptor2.getValueParameters();
                    Intrinsics.checkExpressionValueIsNotNull(valueParameters, "descriptor.valueParameters");
                    Object single = CollectionsKt.single(valueParameters);
                    Intrinsics.checkExpressionValueIsNotNull(single, "descriptor.valueParameters.single()");
                    if (kotlinTypeChecker.equalTypes(((ValueParameterDescriptor) single).getType(), propertyDescriptor.getType())) {
                        simpleFunctionDescriptor = simpleFunctionDescriptor2;
                        continue;
                    } else {
                        continue;
                    }
                }
            }
        } while (simpleFunctionDescriptor == null);
        return simpleFunctionDescriptor;
    }

    private final boolean doesClassOverridesProperty(PropertyDescriptor propertyDescriptor, Function1<? super Name, ? extends Collection<? extends SimpleFunctionDescriptor>> function1) {
        boolean z = false;
        if (JavaDescriptorUtilKt.isJavaField(propertyDescriptor)) {
            return false;
        }
        SimpleFunctionDescriptor findGetterOverride = findGetterOverride(propertyDescriptor, function1);
        SimpleFunctionDescriptor findSetterOverride = findSetterOverride(propertyDescriptor, function1);
        if (findGetterOverride == null) {
            return false;
        }
        if (!propertyDescriptor.isVar()) {
            return true;
        }
        if (findSetterOverride != null && findSetterOverride.getModality() == findGetterOverride.getModality()) {
            z = true;
        }
        return z;
    }

    /* access modifiers changed from: protected */
    public void computeNonDeclaredFunctions(@NotNull Collection<SimpleFunctionDescriptor> collection, @NotNull Name name) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(collection, "result");
        Intrinsics.checkParameterIsNotNull(name, "name");
        Set functionsFromSupertypes = getFunctionsFromSupertypes(name);
        if (!BuiltinMethodsWithDifferentJvmName.INSTANCE.getSameAsRenamedInJvmBuiltin(name) && !BuiltinMethodsWithSpecialGenericSignature.INSTANCE.getSameAsBuiltinMethodWithErasedValueParameters(name)) {
            Iterable iterable = functionsFromSupertypes;
            if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                Iterator it = iterable.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (((FunctionDescriptor) it.next()).isSuspend()) {
                            z = false;
                            break;
                        }
                    } else {
                        z = true;
                        break;
                    }
                }
            } else {
                z = true;
            }
            if (z) {
                Collection arrayList = new ArrayList();
                for (Object next : iterable) {
                    if (isVisibleAsFunctionInCurrentClass((SimpleFunctionDescriptor) next)) {
                        arrayList.add(next);
                    }
                }
                addFunctionFromSupertypes(collection, name, (List) arrayList, false);
                return;
            }
        }
        SmartSet create = SmartSet.Companion.create();
        Collection resolveOverridesForNonStaticMembers = DescriptorResolverUtils.resolveOverridesForNonStaticMembers(name, functionsFromSupertypes, CollectionsKt.emptyList(), getOwnerDescriptor(), ErrorReporter.DO_NOTHING);
        Intrinsics.checkExpressionValueIsNotNull(resolveOverridesForNonStaticMembers, "resolveOverridesForNonSt…rter.DO_NOTHING\n        )");
        LazyJavaClassMemberScope lazyJavaClassMemberScope = this;
        Name name2 = name;
        Collection<SimpleFunctionDescriptor> collection2 = collection;
        Collection collection3 = resolveOverridesForNonStaticMembers;
        addOverriddenSpecialMethods(name2, collection2, collection3, collection, new LazyJavaClassMemberScope$computeNonDeclaredFunctions$3(lazyJavaClassMemberScope));
        addOverriddenSpecialMethods(name2, collection2, collection3, create, new LazyJavaClassMemberScope$computeNonDeclaredFunctions$4(lazyJavaClassMemberScope));
        Iterable iterable2 = functionsFromSupertypes;
        Collection arrayList2 = new ArrayList();
        for (Object next2 : iterable2) {
            if (isVisibleAsFunctionInCurrentClass((SimpleFunctionDescriptor) next2)) {
                arrayList2.add(next2);
            }
        }
        addFunctionFromSupertypes(collection, name, CollectionsKt.plus((Collection<? extends T>) (List) arrayList2, (Iterable<? extends T>) create), true);
    }

    private final void addFunctionFromSupertypes(Collection<SimpleFunctionDescriptor> collection, Name name, Collection<? extends SimpleFunctionDescriptor> collection2, boolean z) {
        Collection resolveOverridesForNonStaticMembers = DescriptorResolverUtils.resolveOverridesForNonStaticMembers(name, collection2, collection, getOwnerDescriptor(), getC().getComponents().getErrorReporter());
        Intrinsics.checkExpressionValueIsNotNull(resolveOverridesForNonStaticMembers, "resolveOverridesForNonSt…s.errorReporter\n        )");
        if (!z) {
            collection.addAll(resolveOverridesForNonStaticMembers);
            return;
        }
        Iterable<SimpleFunctionDescriptor> iterable = resolveOverridesForNonStaticMembers;
        List plus = CollectionsKt.plus(collection, iterable);
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (SimpleFunctionDescriptor simpleFunctionDescriptor : iterable) {
            SimpleFunctionDescriptor simpleFunctionDescriptor2 = (SimpleFunctionDescriptor) SpecialBuiltinMembers.getOverriddenSpecialBuiltin(simpleFunctionDescriptor);
            if (simpleFunctionDescriptor2 != null) {
                Intrinsics.checkExpressionValueIsNotNull(simpleFunctionDescriptor, "resolvedOverride");
                simpleFunctionDescriptor = createHiddenCopyIfBuiltinAlreadyAccidentallyOverridden(simpleFunctionDescriptor, simpleFunctionDescriptor2, plus);
            }
            arrayList.add(simpleFunctionDescriptor);
        }
        collection.addAll((List) arrayList);
    }

    private final void addOverriddenSpecialMethods(Name name, Collection<? extends SimpleFunctionDescriptor> collection, Collection<? extends SimpleFunctionDescriptor> collection2, Collection<SimpleFunctionDescriptor> collection3, Function1<? super Name, ? extends Collection<? extends SimpleFunctionDescriptor>> function1) {
        for (SimpleFunctionDescriptor simpleFunctionDescriptor : collection2) {
            kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(collection3, obtainOverrideForBuiltinWithDifferentJvmName(simpleFunctionDescriptor, function1, name, collection));
            kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(collection3, obtainOverrideForBuiltInWithErasedValueParametersInJava(simpleFunctionDescriptor, function1, collection));
            kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(collection3, obtainOverrideForSuspend(simpleFunctionDescriptor, function1));
        }
    }

    private final SimpleFunctionDescriptor obtainOverrideForBuiltInWithErasedValueParametersInJava(SimpleFunctionDescriptor simpleFunctionDescriptor, Function1<? super Name, ? extends Collection<? extends SimpleFunctionDescriptor>> function1, Collection<? extends SimpleFunctionDescriptor> collection) {
        FunctionDescriptor overriddenBuiltinFunctionWithErasedValueParametersInJava = BuiltinMethodsWithSpecialGenericSignature.getOverriddenBuiltinFunctionWithErasedValueParametersInJava(simpleFunctionDescriptor);
        SimpleFunctionDescriptor simpleFunctionDescriptor2 = null;
        if (overriddenBuiltinFunctionWithErasedValueParametersInJava == null) {
            return null;
        }
        SimpleFunctionDescriptor createOverrideForBuiltinFunctionWithErasedParameterIfNeeded = createOverrideForBuiltinFunctionWithErasedParameterIfNeeded(overriddenBuiltinFunctionWithErasedValueParametersInJava, function1);
        if (createOverrideForBuiltinFunctionWithErasedParameterIfNeeded != null) {
            if (!isVisibleAsFunctionInCurrentClass(createOverrideForBuiltinFunctionWithErasedParameterIfNeeded)) {
                createOverrideForBuiltinFunctionWithErasedParameterIfNeeded = null;
            }
            if (createOverrideForBuiltinFunctionWithErasedParameterIfNeeded != null) {
                simpleFunctionDescriptor2 = createHiddenCopyIfBuiltinAlreadyAccidentallyOverridden(createOverrideForBuiltinFunctionWithErasedParameterIfNeeded, overriddenBuiltinFunctionWithErasedValueParametersInJava, collection);
            }
        }
        return simpleFunctionDescriptor2;
    }

    private final SimpleFunctionDescriptor obtainOverrideForBuiltinWithDifferentJvmName(SimpleFunctionDescriptor simpleFunctionDescriptor, Function1<? super Name, ? extends Collection<? extends SimpleFunctionDescriptor>> function1, Name name, Collection<? extends SimpleFunctionDescriptor> collection) {
        SimpleFunctionDescriptor simpleFunctionDescriptor2 = (SimpleFunctionDescriptor) SpecialBuiltinMembers.getOverriddenBuiltinWithDifferentJvmName(simpleFunctionDescriptor);
        if (simpleFunctionDescriptor2 == null) {
            return null;
        }
        String jvmMethodNameIfSpecial = SpecialBuiltinMembers.getJvmMethodNameIfSpecial(simpleFunctionDescriptor2);
        if (jvmMethodNameIfSpecial == null) {
            Intrinsics.throwNpe();
        }
        Name identifier = Name.identifier(jvmMethodNameIfSpecial);
        Intrinsics.checkExpressionValueIsNotNull(identifier, "Name.identifier(nameInJava)");
        for (SimpleFunctionDescriptor createRenamedCopy : (Collection) function1.invoke(identifier)) {
            SimpleFunctionDescriptor createRenamedCopy2 = createRenamedCopy(createRenamedCopy, name);
            if (doesOverrideRenamedDescriptor(simpleFunctionDescriptor2, createRenamedCopy2)) {
                return createHiddenCopyIfBuiltinAlreadyAccidentallyOverridden(createRenamedCopy2, simpleFunctionDescriptor2, collection);
            }
        }
        return null;
    }

    private final SimpleFunctionDescriptor obtainOverrideForSuspend(SimpleFunctionDescriptor simpleFunctionDescriptor, Function1<? super Name, ? extends Collection<? extends SimpleFunctionDescriptor>> function1) {
        SimpleFunctionDescriptor simpleFunctionDescriptor2;
        if (!simpleFunctionDescriptor.isSuspend()) {
            return null;
        }
        Name name = simpleFunctionDescriptor.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "descriptor.name");
        Iterator it = ((Iterable) function1.invoke(name)).iterator();
        while (true) {
            if (!it.hasNext()) {
                simpleFunctionDescriptor2 = null;
                break;
            }
            simpleFunctionDescriptor2 = createSuspendView((SimpleFunctionDescriptor) it.next());
            if (simpleFunctionDescriptor2 == null || !doesOverride(simpleFunctionDescriptor2, simpleFunctionDescriptor)) {
                simpleFunctionDescriptor2 = null;
                continue;
            }
            if (simpleFunctionDescriptor2 != null) {
                break;
            }
        }
        return simpleFunctionDescriptor2;
    }

    private final SimpleFunctionDescriptor createHiddenCopyIfBuiltinAlreadyAccidentallyOverridden(@NotNull SimpleFunctionDescriptor simpleFunctionDescriptor, CallableDescriptor callableDescriptor, Collection<? extends SimpleFunctionDescriptor> collection) {
        boolean z;
        Iterable iterable = collection;
        boolean z2 = true;
        if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
            Iterator it = iterable.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                SimpleFunctionDescriptor simpleFunctionDescriptor2 = (SimpleFunctionDescriptor) it.next();
                if (!(!Intrinsics.areEqual((Object) simpleFunctionDescriptor, (Object) simpleFunctionDescriptor2)) || simpleFunctionDescriptor2.getInitialSignatureDescriptor() != null || !doesOverride(simpleFunctionDescriptor2, callableDescriptor)) {
                    z = false;
                    continue;
                } else {
                    z = true;
                    continue;
                }
                if (z) {
                    z2 = false;
                    break;
                }
            }
        }
        if (z2) {
            return simpleFunctionDescriptor;
        }
        FunctionDescriptor build = simpleFunctionDescriptor.newCopyBuilder().setHiddenToOvercomeSignatureClash().build();
        if (build == null) {
            Intrinsics.throwNpe();
        }
        return (SimpleFunctionDescriptor) build;
    }

    private final SimpleFunctionDescriptor createOverrideForBuiltinFunctionWithErasedParameterIfNeeded(FunctionDescriptor functionDescriptor, Function1<? super Name, ? extends Collection<? extends SimpleFunctionDescriptor>> function1) {
        Object obj;
        Name name = functionDescriptor.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "overridden.name");
        Iterator it = ((Iterable) function1.invoke(name)).iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (hasSameJvmDescriptorButDoesNotOverride((SimpleFunctionDescriptor) obj, functionDescriptor)) {
                break;
            }
        }
        SimpleFunctionDescriptor simpleFunctionDescriptor = (SimpleFunctionDescriptor) obj;
        if (simpleFunctionDescriptor == null) {
            return null;
        }
        CopyBuilder newCopyBuilder = simpleFunctionDescriptor.newCopyBuilder();
        List valueParameters = functionDescriptor.getValueParameters();
        Intrinsics.checkExpressionValueIsNotNull(valueParameters, "overridden.valueParameters");
        Iterable<ValueParameterDescriptor> iterable = valueParameters;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (ValueParameterDescriptor valueParameterDescriptor : iterable) {
            Intrinsics.checkExpressionValueIsNotNull(valueParameterDescriptor, "it");
            KotlinType type = valueParameterDescriptor.getType();
            Intrinsics.checkExpressionValueIsNotNull(type, "it.type");
            arrayList.add(new ValueParameterData(type, valueParameterDescriptor.declaresDefaultValue()));
        }
        Collection collection = (List) arrayList;
        List valueParameters2 = simpleFunctionDescriptor.getValueParameters();
        Intrinsics.checkExpressionValueIsNotNull(valueParameters2, "override.valueParameters");
        newCopyBuilder.setValueParameters(UtilKt.copyValueParameters(collection, valueParameters2, functionDescriptor));
        newCopyBuilder.setSignatureChange();
        newCopyBuilder.setPreserveSourceElement();
        return (SimpleFunctionDescriptor) newCopyBuilder.build();
    }

    private final Set<SimpleFunctionDescriptor> getFunctionsFromSupertypes(Name name) {
        TypeConstructor typeConstructor = getOwnerDescriptor().getTypeConstructor();
        Intrinsics.checkExpressionValueIsNotNull(typeConstructor, "ownerDescriptor.typeConstructor");
        Collection supertypes = typeConstructor.getSupertypes();
        Intrinsics.checkExpressionValueIsNotNull(supertypes, "ownerDescriptor.typeConstructor.supertypes");
        Iterable<KotlinType> iterable = supertypes;
        Collection linkedHashSet = new LinkedHashSet();
        for (KotlinType memberScope : iterable) {
            CollectionsKt.addAll(linkedHashSet, (Iterable<? extends T>) memberScope.getMemberScope().getContributedFunctions(name, NoLookupLocation.WHEN_GET_SUPER_MEMBERS));
        }
        return (Set) linkedHashSet;
    }

    /* access modifiers changed from: protected */
    public void computeNonDeclaredProperties(@NotNull Name name, @NotNull Collection<PropertyDescriptor> collection) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(collection, "result");
        if (this.jClass.isAnnotationType()) {
            computeAnnotationProperties(name, collection);
        }
        Set propertiesFromSupertypes = getPropertiesFromSupertypes(name);
        if (!propertiesFromSupertypes.isEmpty()) {
            SmartSet create = SmartSet.Companion.create();
            addPropertyOverrideByMethod(propertiesFromSupertypes, collection, new LazyJavaClassMemberScope$computeNonDeclaredProperties$1(this));
            addPropertyOverrideByMethod(propertiesFromSupertypes, create, new LazyJavaClassMemberScope$computeNonDeclaredProperties$2(this));
            Collection resolveOverridesForNonStaticMembers = DescriptorResolverUtils.resolveOverridesForNonStaticMembers(name, SetsKt.plus(propertiesFromSupertypes, (Iterable<? extends T>) create), collection, getOwnerDescriptor(), getC().getComponents().getErrorReporter());
            Intrinsics.checkExpressionValueIsNotNull(resolveOverridesForNonStaticMembers, "resolveOverridesForNonSt…rorReporter\n            )");
            collection.addAll(resolveOverridesForNonStaticMembers);
        }
    }

    private final void addPropertyOverrideByMethod(Set<? extends PropertyDescriptor> set, Collection<PropertyDescriptor> collection, Function1<? super Name, ? extends Collection<? extends SimpleFunctionDescriptor>> function1) {
        for (PropertyDescriptor createPropertyDescriptorByMethods : set) {
            JavaPropertyDescriptor createPropertyDescriptorByMethods2 = createPropertyDescriptorByMethods(createPropertyDescriptorByMethods, function1);
            if (createPropertyDescriptorByMethods2 != null) {
                collection.add(createPropertyDescriptorByMethods2);
                return;
            }
        }
    }

    private final void computeAnnotationProperties(Name name, Collection<PropertyDescriptor> collection) {
        JavaMethod javaMethod = (JavaMethod) CollectionsKt.singleOrNull((Iterable<? extends T>) ((DeclaredMemberIndex) getDeclaredMemberIndex().invoke()).findMethodsByName(name));
        if (javaMethod != null) {
            collection.add(createPropertyDescriptorWithDefaultGetter$default(this, javaMethod, null, Modality.FINAL, 2, null));
        }
    }

    static /* synthetic */ JavaPropertyDescriptor createPropertyDescriptorWithDefaultGetter$default(LazyJavaClassMemberScope lazyJavaClassMemberScope, JavaMethod javaMethod, KotlinType kotlinType, Modality modality, int i, Object obj) {
        if ((i & 2) != 0) {
            kotlinType = null;
        }
        return lazyJavaClassMemberScope.createPropertyDescriptorWithDefaultGetter(javaMethod, kotlinType, modality);
    }

    private final JavaPropertyDescriptor createPropertyDescriptorWithDefaultGetter(JavaMethod javaMethod, KotlinType kotlinType, Modality modality) {
        JavaPropertyDescriptor create = JavaPropertyDescriptor.create(getOwnerDescriptor(), LazyJavaAnnotationsKt.resolveAnnotations(getC(), javaMethod), modality, javaMethod.getVisibility(), false, javaMethod.getName(), getC().getComponents().getSourceElementFactory().source(javaMethod), false);
        Intrinsics.checkExpressionValueIsNotNull(create, "JavaPropertyDescriptor.c…inal = */ false\n        )");
        PropertyGetterDescriptorImpl createDefaultGetter = DescriptorFactory.createDefaultGetter(create, Annotations.Companion.getEMPTY());
        Intrinsics.checkExpressionValueIsNotNull(createDefaultGetter, "DescriptorFactory.create…iptor, Annotations.EMPTY)");
        create.initialize(createDefaultGetter, null);
        if (kotlinType == null) {
            kotlinType = computeMethodReturnType(javaMethod, ContextKt.childForMethod$default(getC(), create, javaMethod, 0, 4, null));
        }
        create.setType(kotlinType, CollectionsKt.emptyList(), getDispatchReceiverParameter(), null);
        createDefaultGetter.initialize(kotlinType);
        return create;
    }

    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v1 */
    /* JADX WARNING: type inference failed for: r1v5, types: [kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertySetterDescriptorImpl] */
    /* JADX WARNING: type inference failed for: r1v6, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r1v7, types: [kotlin.reflect.jvm.internal.impl.descriptors.Modality] */
    /* JADX WARNING: type inference failed for: r1v8 */
    /* JADX WARNING: type inference failed for: r1v9 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v0
  assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], kotlin.reflect.jvm.internal.impl.descriptors.Modality, kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertySetterDescriptorImpl]
  uses: [?[OBJECT, ARRAY], java.lang.Object, kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertySetterDescriptorImpl]
  mth insns count: 104
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaPropertyDescriptor createPropertyDescriptorByMethods(kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor r14, kotlin.jvm.functions.Function1<? super kotlin.reflect.jvm.internal.impl.name.Name, ? extends java.util.Collection<? extends kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor>> r15) {
        /*
            r13 = this;
            boolean r0 = r13.doesClassOverridesProperty(r14, r15)
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor r0 = r13.findGetterOverride(r14, r15)
            if (r0 != 0) goto L_0x0011
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0011:
            boolean r2 = r14.isVar()
            if (r2 == 0) goto L_0x0021
            kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor r15 = r13.findSetterOverride(r14, r15)
            if (r15 != 0) goto L_0x0022
            kotlin.jvm.internal.Intrinsics.throwNpe()
            goto L_0x0022
        L_0x0021:
            r15 = r1
        L_0x0022:
            r2 = 0
            r3 = 1
            if (r15 == 0) goto L_0x0033
            kotlin.reflect.jvm.internal.impl.descriptors.Modality r4 = r15.getModality()
            kotlin.reflect.jvm.internal.impl.descriptors.Modality r5 = r0.getModality()
            if (r4 != r5) goto L_0x0031
            goto L_0x0033
        L_0x0031:
            r4 = 0
            goto L_0x0034
        L_0x0033:
            r4 = 1
        L_0x0034:
            boolean r5 = kotlin._Assertions.ENABLED
            if (r5 == 0) goto L_0x0079
            if (r4 != 0) goto L_0x0079
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Different accessors modalities when creating overrides for "
            r2.append(r3)
            r2.append(r14)
            java.lang.String r14 = " in "
            r2.append(r14)
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r14 = r13.getOwnerDescriptor()
            r2.append(r14)
            java.lang.String r14 = "for getter is "
            r2.append(r14)
            kotlin.reflect.jvm.internal.impl.descriptors.Modality r14 = r0.getModality()
            r2.append(r14)
            java.lang.String r14 = ", but for setter is "
            r2.append(r14)
            if (r15 == 0) goto L_0x006a
            kotlin.reflect.jvm.internal.impl.descriptors.Modality r1 = r15.getModality()
        L_0x006a:
            r2.append(r1)
            java.lang.String r14 = r2.toString()
            java.lang.AssertionError r15 = new java.lang.AssertionError
            r15.<init>(r14)
            java.lang.Throwable r15 = (java.lang.Throwable) r15
            throw r15
        L_0x0079:
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r4 = r13.getOwnerDescriptor()
            r5 = r4
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r5 = (kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor) r5
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations$Companion r4 = kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations.Companion
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r6 = r4.getEMPTY()
            kotlin.reflect.jvm.internal.impl.descriptors.Modality r7 = r0.getModality()
            kotlin.reflect.jvm.internal.impl.descriptors.Visibility r8 = r0.getVisibility()
            if (r15 == 0) goto L_0x0092
            r9 = 1
            goto L_0x0093
        L_0x0092:
            r9 = 0
        L_0x0093:
            kotlin.reflect.jvm.internal.impl.name.Name r10 = r14.getName()
            kotlin.reflect.jvm.internal.impl.descriptors.SourceElement r11 = r0.getSource()
            r12 = 0
            kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaPropertyDescriptor r14 = kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaPropertyDescriptor.create(r5, r6, r7, r8, r9, r10, r11, r12)
            java.lang.String r2 = "JavaPropertyDescriptor.c…inal = */ false\n        )"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r14, r2)
            kotlin.reflect.jvm.internal.impl.types.KotlinType r2 = r0.getReturnType()
            if (r2 != 0) goto L_0x00ae
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x00ae:
            java.util.List r3 = kotlin.collections.CollectionsKt.emptyList()
            kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor r4 = r13.getDispatchReceiverParameter()
            r14.setType(r2, r3, r4, r1)
            r2 = r14
            kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor r2 = (kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor) r2
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r6 = r0.getAnnotations()
            r7 = 0
            r8 = 0
            r9 = 0
            kotlin.reflect.jvm.internal.impl.descriptors.SourceElement r10 = r0.getSource()
            r5 = r2
            kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyGetterDescriptorImpl r3 = kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory.createGetter(r5, r6, r7, r8, r9, r10)
            kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor) r0
            r3.setInitialSignatureDescriptor(r0)
            kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = r14.getType()
            r3.initialize(r0)
            java.lang.String r0 = "DescriptorFactory.create…escriptor.type)\n        }"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r0)
            if (r15 == 0) goto L_0x0127
            java.util.List r0 = r15.getValueParameters()
            java.lang.String r1 = "setterMethod.valueParameters"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            java.lang.Object r0 = kotlin.collections.CollectionsKt.firstOrNull(r0)
            kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor) r0
            if (r0 == 0) goto L_0x010e
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r6 = r15.getAnnotations()
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r7 = r0.getAnnotations()
            r8 = 0
            r9 = 0
            r10 = 0
            kotlin.reflect.jvm.internal.impl.descriptors.Visibility r11 = r15.getVisibility()
            kotlin.reflect.jvm.internal.impl.descriptors.SourceElement r12 = r15.getSource()
            r5 = r2
            kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertySetterDescriptorImpl r1 = kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory.createSetter(r5, r6, r7, r8, r9, r10, r11, r12)
            kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r15 = (kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor) r15
            r1.setInitialSignatureDescriptor(r15)
            goto L_0x0127
        L_0x010e:
            java.lang.AssertionError r14 = new java.lang.AssertionError
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "No parameter found for "
            r0.append(r1)
            r0.append(r15)
            java.lang.String r15 = r0.toString()
            r14.<init>(r15)
            java.lang.Throwable r14 = (java.lang.Throwable) r14
            throw r14
        L_0x0127:
            kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor r1 = (kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor) r1
            r14.initialize(r3, r1)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope.createPropertyDescriptorByMethods(kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor, kotlin.jvm.functions.Function1):kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaPropertyDescriptor");
    }

    private final Set<PropertyDescriptor> getPropertiesFromSupertypes(Name name) {
        TypeConstructor typeConstructor = getOwnerDescriptor().getTypeConstructor();
        Intrinsics.checkExpressionValueIsNotNull(typeConstructor, "ownerDescriptor.typeConstructor");
        Collection supertypes = typeConstructor.getSupertypes();
        Intrinsics.checkExpressionValueIsNotNull(supertypes, "ownerDescriptor.typeConstructor.supertypes");
        Iterable<KotlinType> iterable = supertypes;
        Collection arrayList = new ArrayList();
        for (KotlinType memberScope : iterable) {
            Iterable<PropertyDescriptor> contributedVariables = memberScope.getMemberScope().getContributedVariables(name, NoLookupLocation.WHEN_GET_SUPER_MEMBERS);
            Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(contributedVariables, 10));
            for (PropertyDescriptor add : contributedVariables) {
                arrayList2.add(add);
            }
            CollectionsKt.addAll(arrayList, (Iterable<? extends T>) (List) arrayList2);
        }
        return CollectionsKt.toSet((List) arrayList);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public MethodSignatureData resolveMethodSignature(@NotNull JavaMethod javaMethod, @NotNull List<? extends TypeParameterDescriptor> list, @NotNull KotlinType kotlinType, @NotNull List<? extends ValueParameterDescriptor> list2) {
        Intrinsics.checkParameterIsNotNull(javaMethod, Param.METHOD);
        Intrinsics.checkParameterIsNotNull(list, "methodTypeParameters");
        Intrinsics.checkParameterIsNotNull(kotlinType, "returnType");
        Intrinsics.checkParameterIsNotNull(list2, "valueParameters");
        PropagatedSignature resolvePropagatedSignature = getC().getComponents().getSignaturePropagator().resolvePropagatedSignature(javaMethod, getOwnerDescriptor(), kotlinType, null, list2, list);
        Intrinsics.checkExpressionValueIsNotNull(resolvePropagatedSignature, "c.components.signaturePr…dTypeParameters\n        )");
        KotlinType returnType = resolvePropagatedSignature.getReturnType();
        Intrinsics.checkExpressionValueIsNotNull(returnType, "propagated.returnType");
        KotlinType receiverType = resolvePropagatedSignature.getReceiverType();
        List valueParameters = resolvePropagatedSignature.getValueParameters();
        Intrinsics.checkExpressionValueIsNotNull(valueParameters, "propagated.valueParameters");
        List typeParameters = resolvePropagatedSignature.getTypeParameters();
        Intrinsics.checkExpressionValueIsNotNull(typeParameters, "propagated.typeParameters");
        boolean hasStableParameterNames = resolvePropagatedSignature.hasStableParameterNames();
        List errors = resolvePropagatedSignature.getErrors();
        Intrinsics.checkExpressionValueIsNotNull(errors, "propagated.errors");
        MethodSignatureData methodSignatureData = new MethodSignatureData(returnType, receiverType, valueParameters, typeParameters, hasStableParameterNames, errors);
        return methodSignatureData;
    }

    private final boolean hasSameJvmDescriptorButDoesNotOverride(@NotNull SimpleFunctionDescriptor simpleFunctionDescriptor, FunctionDescriptor functionDescriptor) {
        String computeJvmDescriptor$default = MethodSignatureMappingKt.computeJvmDescriptor$default(simpleFunctionDescriptor, false, false, 2, null);
        FunctionDescriptor original = functionDescriptor.getOriginal();
        Intrinsics.checkExpressionValueIsNotNull(original, "builtinWithErasedParameters.original");
        return Intrinsics.areEqual((Object) computeJvmDescriptor$default, (Object) MethodSignatureMappingKt.computeJvmDescriptor$default(original, false, false, 2, null)) && !doesOverride(simpleFunctionDescriptor, functionDescriptor);
    }

    /* access modifiers changed from: private */
    public final JavaClassConstructorDescriptor resolveConstructor(JavaConstructor javaConstructor) {
        ClassDescriptor ownerDescriptor2 = getOwnerDescriptor();
        JavaElement javaElement = javaConstructor;
        JavaClassConstructorDescriptor createJavaConstructor = JavaClassConstructorDescriptor.createJavaConstructor(ownerDescriptor2, LazyJavaAnnotationsKt.resolveAnnotations(getC(), javaConstructor), false, getC().getComponents().getSourceElementFactory().source(javaElement));
        Intrinsics.checkExpressionValueIsNotNull(createJavaConstructor, "JavaClassConstructorDesc…ce(constructor)\n        )");
        LazyJavaResolverContext childForMethod = ContextKt.childForMethod(getC(), createJavaConstructor, javaConstructor, ownerDescriptor2.getDeclaredTypeParameters().size());
        ResolvedValueParameters resolveValueParameters = resolveValueParameters(childForMethod, createJavaConstructor, javaConstructor.getValueParameters());
        List declaredTypeParameters = ownerDescriptor2.getDeclaredTypeParameters();
        Intrinsics.checkExpressionValueIsNotNull(declaredTypeParameters, "classDescriptor.declaredTypeParameters");
        Collection collection = declaredTypeParameters;
        Iterable<JavaTypeParameter> typeParameters = javaConstructor.getTypeParameters();
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameters, 10));
        for (JavaTypeParameter resolveTypeParameter : typeParameters) {
            TypeParameterDescriptor resolveTypeParameter2 = childForMethod.getTypeParameterResolver().resolveTypeParameter(resolveTypeParameter);
            if (resolveTypeParameter2 == null) {
                Intrinsics.throwNpe();
            }
            arrayList.add(resolveTypeParameter2);
        }
        createJavaConstructor.initialize(resolveValueParameters.getDescriptors(), javaConstructor.getVisibility(), CollectionsKt.plus(collection, (Iterable<? extends T>) (List) arrayList));
        createJavaConstructor.setHasStableParameterNames(false);
        createJavaConstructor.setHasSynthesizedParameterNames(resolveValueParameters.getHasSynthesizedNames());
        createJavaConstructor.setReturnType(ownerDescriptor2.getDefaultType());
        childForMethod.getComponents().getJavaResolverCache().recordConstructor(javaElement, createJavaConstructor);
        return createJavaConstructor;
    }

    /* access modifiers changed from: private */
    public final ClassConstructorDescriptor createDefaultConstructor() {
        List list;
        boolean isAnnotationType = this.jClass.isAnnotationType();
        if (this.jClass.isInterface() && !isAnnotationType) {
            return null;
        }
        ClassDescriptor ownerDescriptor2 = getOwnerDescriptor();
        JavaClassConstructorDescriptor createJavaConstructor = JavaClassConstructorDescriptor.createJavaConstructor(ownerDescriptor2, Annotations.Companion.getEMPTY(), true, getC().getComponents().getSourceElementFactory().source(this.jClass));
        Intrinsics.checkExpressionValueIsNotNull(createJavaConstructor, "JavaClassConstructorDesc….source(jClass)\n        )");
        if (isAnnotationType) {
            list = createAnnotationConstructorParameters(createJavaConstructor);
        } else {
            list = Collections.emptyList();
        }
        createJavaConstructor.setHasSynthesizedParameterNames(false);
        createJavaConstructor.initialize(list, getConstructorVisibility(ownerDescriptor2));
        createJavaConstructor.setHasStableParameterNames(true);
        createJavaConstructor.setReturnType(ownerDescriptor2.getDefaultType());
        getC().getComponents().getJavaResolverCache().recordConstructor(this.jClass, createJavaConstructor);
        return createJavaConstructor;
    }

    private final Visibility getConstructorVisibility(ClassDescriptor classDescriptor) {
        Visibility visibility = classDescriptor.getVisibility();
        Intrinsics.checkExpressionValueIsNotNull(visibility, "classDescriptor.visibility");
        if (!Intrinsics.areEqual((Object) visibility, (Object) JavaVisibilities.PROTECTED_STATIC_VISIBILITY)) {
            return visibility;
        }
        Visibility visibility2 = JavaVisibilities.PROTECTED_AND_PACKAGE;
        Intrinsics.checkExpressionValueIsNotNull(visibility2, "JavaVisibilities.PROTECTED_AND_PACKAGE");
        return visibility2;
    }

    private final List<ValueParameterDescriptor> createAnnotationConstructorParameters(ClassConstructorDescriptorImpl classConstructorDescriptorImpl) {
        Pair pair;
        Collection methods = this.jClass.getMethods();
        ArrayList arrayList = new ArrayList(methods.size());
        JavaTypeAttributes attributes$default = JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, true, null, 2, null);
        Iterable iterable = methods;
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (Object next : iterable) {
            if (Intrinsics.areEqual((Object) ((JavaMethod) next).getName(), (Object) JvmAnnotationNames.DEFAULT_ANNOTATION_MEMBER_NAME)) {
                arrayList2.add(next);
            } else {
                arrayList3.add(next);
            }
        }
        Pair pair2 = new Pair(arrayList2, arrayList3);
        List list = (List) pair2.component1();
        List<JavaMethod> list2 = (List) pair2.component2();
        int i = 0;
        boolean z = list.size() <= 1;
        if (!_Assertions.ENABLED || z) {
            JavaMethod javaMethod = (JavaMethod) CollectionsKt.firstOrNull(list);
            if (javaMethod != null) {
                JavaType returnType = javaMethod.getReturnType();
                if (returnType instanceof JavaArrayType) {
                    JavaArrayType javaArrayType = (JavaArrayType) returnType;
                    pair = new Pair(getC().getTypeResolver().transformArrayType(javaArrayType, attributes$default, true), getC().getTypeResolver().transformJavaType(javaArrayType.getComponentType(), attributes$default));
                } else {
                    pair = new Pair(getC().getTypeResolver().transformJavaType(returnType, attributes$default), null);
                }
                addAnnotationValueParameter(arrayList, classConstructorDescriptorImpl, 0, javaMethod, (KotlinType) pair.component1(), (KotlinType) pair.component2());
            }
            int i2 = javaMethod != null ? 1 : 0;
            for (JavaMethod javaMethod2 : list2) {
                addAnnotationValueParameter(arrayList, classConstructorDescriptorImpl, i + i2, javaMethod2, getC().getTypeResolver().transformJavaType(javaMethod2.getReturnType(), attributes$default), null);
                i++;
            }
            return arrayList;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("There can't be more than one method named 'value' in annotation class: ");
        sb.append(this.jClass);
        throw new AssertionError(sb.toString());
    }

    private final void addAnnotationValueParameter(@NotNull List<ValueParameterDescriptor> list, ConstructorDescriptor constructorDescriptor, int i, JavaMethod javaMethod, KotlinType kotlinType, KotlinType kotlinType2) {
        CallableDescriptor callableDescriptor = constructorDescriptor;
        Annotations empty = Annotations.Companion.getEMPTY();
        Name name = javaMethod.getName();
        KotlinType makeNotNullable = TypeUtils.makeNotNullable(kotlinType);
        Intrinsics.checkExpressionValueIsNotNull(makeNotNullable, "TypeUtils.makeNotNullable(returnType)");
        ValueParameterDescriptorImpl valueParameterDescriptorImpl = new ValueParameterDescriptorImpl(callableDescriptor, null, i, empty, name, makeNotNullable, javaMethod.getHasAnnotationParameterDefaultValue(), false, false, kotlinType2 != null ? TypeUtils.makeNotNullable(kotlinType2) : null, getC().getComponents().getSourceElementFactory().source(javaMethod));
        List<ValueParameterDescriptor> list2 = list;
        list.add(valueParameterDescriptorImpl);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public ReceiverParameterDescriptor getDispatchReceiverParameter() {
        return DescriptorUtils.getDispatchReceiverParameterIfNeeded(getOwnerDescriptor());
    }

    @Nullable
    public ClassifierDescriptor getContributedClassifier(@NotNull Name name, @NotNull LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
        recordLookup(name, lookupLocation);
        return (ClassifierDescriptor) this.nestedClasses.invoke(name);
    }

    @NotNull
    public Collection<SimpleFunctionDescriptor> getContributedFunctions(@NotNull Name name, @NotNull LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
        recordLookup(name, lookupLocation);
        return super.getContributedFunctions(name, lookupLocation);
    }

    @NotNull
    public Collection<PropertyDescriptor> getContributedVariables(@NotNull Name name, @NotNull LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
        recordLookup(name, lookupLocation);
        return super.getContributedVariables(name, lookupLocation);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public Set<Name> computeClassNames(@NotNull DescriptorKindFilter descriptorKindFilter, @Nullable Function1<? super Name, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(descriptorKindFilter, "kindFilter");
        return SetsKt.plus((Set) this.nestedClassIndex.invoke(), (Iterable<? extends T>) ((Map) this.enumEntryIndex.invoke()).keySet());
    }

    /* access modifiers changed from: protected */
    @NotNull
    public Set<Name> computePropertyNames(@NotNull DescriptorKindFilter descriptorKindFilter, @Nullable Function1<? super Name, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(descriptorKindFilter, "kindFilter");
        if (this.jClass.isAnnotationType()) {
            return getFunctionNames();
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(((DeclaredMemberIndex) getDeclaredMemberIndex().invoke()).getFieldNames());
        TypeConstructor typeConstructor = getOwnerDescriptor().getTypeConstructor();
        Intrinsics.checkExpressionValueIsNotNull(typeConstructor, "ownerDescriptor.typeConstructor");
        Collection<KotlinType> supertypes = typeConstructor.getSupertypes();
        Intrinsics.checkExpressionValueIsNotNull(supertypes, "ownerDescriptor.typeConstructor.supertypes");
        for (KotlinType memberScope : supertypes) {
            CollectionsKt.addAll((Collection<? super T>) linkedHashSet, (Iterable<? extends T>) memberScope.getMemberScope().getVariableNames());
        }
        return linkedHashSet;
    }

    public void recordLookup(@NotNull Name name, @NotNull LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
        UtilsKt.record(getC().getComponents().getLookupTracker(), lookupLocation, getOwnerDescriptor(), name);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Lazy Java member scope for ");
        sb.append(this.jClass.getFqName());
        return sb.toString();
    }
}
