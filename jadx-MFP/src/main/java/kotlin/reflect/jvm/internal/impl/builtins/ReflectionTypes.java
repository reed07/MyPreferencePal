package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ReflectionTypes.kt */
public final class ReflectionTypes {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReflectionTypes.class), "kotlinReflectScope", "getKotlinReflectScope()Lorg/jetbrains/kotlin/resolve/scopes/MemberScope;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReflectionTypes.class), "kClass", "getKClass()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReflectionTypes.class), "kProperty0", "getKProperty0()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReflectionTypes.class), "kProperty1", "getKProperty1()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReflectionTypes.class), "kProperty2", "getKProperty2()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReflectionTypes.class), "kMutableProperty0", "getKMutableProperty0()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReflectionTypes.class), "kMutableProperty1", "getKMutableProperty1()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReflectionTypes.class), "kMutableProperty2", "getKMutableProperty2()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;"))};
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final ClassLookup kClass$delegate = new ClassLookup(1);
    @NotNull
    private final ClassLookup kMutableProperty0$delegate = new ClassLookup(1);
    @NotNull
    private final ClassLookup kMutableProperty1$delegate = new ClassLookup(2);
    @NotNull
    private final ClassLookup kMutableProperty2$delegate = new ClassLookup(3);
    @NotNull
    private final ClassLookup kProperty0$delegate = new ClassLookup(1);
    @NotNull
    private final ClassLookup kProperty1$delegate = new ClassLookup(2);
    @NotNull
    private final ClassLookup kProperty2$delegate = new ClassLookup(3);
    private final Lazy kotlinReflectScope$delegate;
    private final NotFoundClasses notFoundClasses;

    /* compiled from: ReflectionTypes.kt */
    private static final class ClassLookup {
        private final int numberOfTypeParameters;

        public ClassLookup(int i) {
            this.numberOfTypeParameters = i;
        }

        @NotNull
        public final ClassDescriptor getValue(@NotNull ReflectionTypes reflectionTypes, @NotNull KProperty<?> kProperty) {
            Intrinsics.checkParameterIsNotNull(reflectionTypes, "types");
            Intrinsics.checkParameterIsNotNull(kProperty, "property");
            return reflectionTypes.find(StringsKt.capitalize(kProperty.getName()), this.numberOfTypeParameters);
        }
    }

    /* compiled from: ReflectionTypes.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final KotlinType createKPropertyStarType(@NotNull ModuleDescriptor moduleDescriptor) {
            Intrinsics.checkParameterIsNotNull(moduleDescriptor, "module");
            ClassId classId = KotlinBuiltIns.FQ_NAMES.kProperty;
            Intrinsics.checkExpressionValueIsNotNull(classId, "KotlinBuiltIns.FQ_NAMES.kProperty");
            ClassDescriptor findClassAcrossModuleDependencies = FindClassInModuleKt.findClassAcrossModuleDependencies(moduleDescriptor, classId);
            if (findClassAcrossModuleDependencies == null) {
                return null;
            }
            Annotations empty = Annotations.Companion.getEMPTY();
            TypeConstructor typeConstructor = findClassAcrossModuleDependencies.getTypeConstructor();
            Intrinsics.checkExpressionValueIsNotNull(typeConstructor, "kPropertyClass.typeConstructor");
            List parameters = typeConstructor.getParameters();
            Intrinsics.checkExpressionValueIsNotNull(parameters, "kPropertyClass.typeConstructor.parameters");
            Object single = CollectionsKt.single(parameters);
            Intrinsics.checkExpressionValueIsNotNull(single, "kPropertyClass.typeConstructor.parameters.single()");
            return KotlinTypeFactory.simpleNotNullType(empty, findClassAcrossModuleDependencies, CollectionsKt.listOf(new StarProjectionImpl((TypeParameterDescriptor) single)));
        }
    }

    private final MemberScope getKotlinReflectScope() {
        Lazy lazy = this.kotlinReflectScope$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (MemberScope) lazy.getValue();
    }

    @NotNull
    public final ClassDescriptor getKClass() {
        return this.kClass$delegate.getValue(this, $$delegatedProperties[1]);
    }

    public ReflectionTypes(@NotNull ModuleDescriptor moduleDescriptor, @NotNull NotFoundClasses notFoundClasses2) {
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "module");
        Intrinsics.checkParameterIsNotNull(notFoundClasses2, "notFoundClasses");
        this.notFoundClasses = notFoundClasses2;
        this.kotlinReflectScope$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0<? extends T>) new ReflectionTypes$kotlinReflectScope$2<Object>(moduleDescriptor));
    }

    /* access modifiers changed from: private */
    public final ClassDescriptor find(String str, int i) {
        Name identifier = Name.identifier(str);
        Intrinsics.checkExpressionValueIsNotNull(identifier, "Name.identifier(className)");
        ClassifierDescriptor contributedClassifier = getKotlinReflectScope().getContributedClassifier(identifier, NoLookupLocation.FROM_REFLECTION);
        if (!(contributedClassifier instanceof ClassDescriptor)) {
            contributedClassifier = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) contributedClassifier;
        return classDescriptor != null ? classDescriptor : this.notFoundClasses.getClass(new ClassId(ReflectionTypesKt.getKOTLIN_REFLECT_FQ_NAME(), identifier), CollectionsKt.listOf(Integer.valueOf(i)));
    }
}
