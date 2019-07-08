package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.CompanionObjectMapping;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.functions.FunctionClassDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.FqNamesUtilKt;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.name.SpecialNames;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JavaToKotlinClassMap.kt */
public final class JavaToKotlinClassMap {
    private static final ClassId FUNCTION_N_CLASS_ID;
    @NotNull
    private static final FqName FUNCTION_N_FQ_NAME;
    public static final JavaToKotlinClassMap INSTANCE;
    private static final ClassId K_FUNCTION_CLASS_ID;
    private static final String NUMBERED_FUNCTION_PREFIX;
    private static final String NUMBERED_K_FUNCTION_PREFIX;
    private static final String NUMBERED_K_SUSPEND_FUNCTION_PREFIX;
    private static final String NUMBERED_SUSPEND_FUNCTION_PREFIX;
    private static final HashMap<FqNameUnsafe, ClassId> javaToKotlin = new HashMap<>();
    private static final HashMap<FqNameUnsafe, ClassId> kotlinToJava = new HashMap<>();
    @NotNull
    private static final List<PlatformMutabilityMapping> mutabilityMappings;
    private static final HashMap<FqNameUnsafe, FqName> mutableToReadOnly = new HashMap<>();
    private static final HashMap<FqNameUnsafe, FqName> readOnlyToMutable = new HashMap<>();

    /* compiled from: JavaToKotlinClassMap.kt */
    public static final class PlatformMutabilityMapping {
        @NotNull
        private final ClassId javaClass;
        @NotNull
        private final ClassId kotlinMutable;
        @NotNull
        private final ClassId kotlinReadOnly;

        @NotNull
        public final ClassId component1() {
            return this.javaClass;
        }

        @NotNull
        public final ClassId component2() {
            return this.kotlinReadOnly;
        }

        @NotNull
        public final ClassId component3() {
            return this.kotlinMutable;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0024, code lost:
            if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.kotlinMutable, (java.lang.Object) r3.kotlinMutable) != false) goto L_0x0029;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
            /*
                r2 = this;
                if (r2 == r3) goto L_0x0029
                boolean r0 = r3 instanceof kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap.PlatformMutabilityMapping
                if (r0 == 0) goto L_0x0027
                kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap$PlatformMutabilityMapping r3 = (kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap.PlatformMutabilityMapping) r3
                kotlin.reflect.jvm.internal.impl.name.ClassId r0 = r2.javaClass
                kotlin.reflect.jvm.internal.impl.name.ClassId r1 = r3.javaClass
                boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
                if (r0 == 0) goto L_0x0027
                kotlin.reflect.jvm.internal.impl.name.ClassId r0 = r2.kotlinReadOnly
                kotlin.reflect.jvm.internal.impl.name.ClassId r1 = r3.kotlinReadOnly
                boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
                if (r0 == 0) goto L_0x0027
                kotlin.reflect.jvm.internal.impl.name.ClassId r0 = r2.kotlinMutable
                kotlin.reflect.jvm.internal.impl.name.ClassId r3 = r3.kotlinMutable
                boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
                if (r3 == 0) goto L_0x0027
                goto L_0x0029
            L_0x0027:
                r3 = 0
                return r3
            L_0x0029:
                r3 = 1
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap.PlatformMutabilityMapping.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            ClassId classId = this.javaClass;
            int i = 0;
            int hashCode = (classId != null ? classId.hashCode() : 0) * 31;
            ClassId classId2 = this.kotlinReadOnly;
            int hashCode2 = (hashCode + (classId2 != null ? classId2.hashCode() : 0)) * 31;
            ClassId classId3 = this.kotlinMutable;
            if (classId3 != null) {
                i = classId3.hashCode();
            }
            return hashCode2 + i;
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("PlatformMutabilityMapping(javaClass=");
            sb.append(this.javaClass);
            sb.append(", kotlinReadOnly=");
            sb.append(this.kotlinReadOnly);
            sb.append(", kotlinMutable=");
            sb.append(this.kotlinMutable);
            sb.append(")");
            return sb.toString();
        }

        public PlatformMutabilityMapping(@NotNull ClassId classId, @NotNull ClassId classId2, @NotNull ClassId classId3) {
            Intrinsics.checkParameterIsNotNull(classId, "javaClass");
            Intrinsics.checkParameterIsNotNull(classId2, "kotlinReadOnly");
            Intrinsics.checkParameterIsNotNull(classId3, "kotlinMutable");
            this.javaClass = classId;
            this.kotlinReadOnly = classId2;
            this.kotlinMutable = classId3;
        }

        @NotNull
        public final ClassId getJavaClass() {
            return this.javaClass;
        }
    }

    static {
        JvmPrimitiveType[] values;
        JavaToKotlinClassMap javaToKotlinClassMap = new JavaToKotlinClassMap();
        INSTANCE = javaToKotlinClassMap;
        StringBuilder sb = new StringBuilder();
        sb.append(Kind.Function.getPackageFqName().toString());
        sb.append(".");
        sb.append(Kind.Function.getClassNamePrefix());
        NUMBERED_FUNCTION_PREFIX = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(Kind.KFunction.getPackageFqName().toString());
        sb2.append(".");
        sb2.append(Kind.KFunction.getClassNamePrefix());
        NUMBERED_K_FUNCTION_PREFIX = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(Kind.SuspendFunction.getPackageFqName().toString());
        sb3.append(".");
        sb3.append(Kind.SuspendFunction.getClassNamePrefix());
        NUMBERED_SUSPEND_FUNCTION_PREFIX = sb3.toString();
        StringBuilder sb4 = new StringBuilder();
        sb4.append(Kind.KSuspendFunction.getPackageFqName().toString());
        sb4.append(".");
        sb4.append(Kind.KSuspendFunction.getClassNamePrefix());
        NUMBERED_K_SUSPEND_FUNCTION_PREFIX = sb4.toString();
        ClassId classId = ClassId.topLevel(new FqName("kotlin.jvm.functions.FunctionN"));
        Intrinsics.checkExpressionValueIsNotNull(classId, "ClassId.topLevel(FqName(…vm.functions.FunctionN\"))");
        FUNCTION_N_CLASS_ID = classId;
        FqName asSingleFqName = FUNCTION_N_CLASS_ID.asSingleFqName();
        Intrinsics.checkExpressionValueIsNotNull(asSingleFqName, "FUNCTION_N_CLASS_ID.asSingleFqName()");
        FUNCTION_N_FQ_NAME = asSingleFqName;
        ClassId classId2 = ClassId.topLevel(new FqName("kotlin.reflect.KFunction"));
        Intrinsics.checkExpressionValueIsNotNull(classId2, "ClassId.topLevel(FqName(…tlin.reflect.KFunction\"))");
        K_FUNCTION_CLASS_ID = classId2;
        ClassId classId3 = ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.iterable);
        Intrinsics.checkExpressionValueIsNotNull(classId3, "ClassId.topLevel(FQ_NAMES.iterable)");
        FqName fqName = KotlinBuiltIns.FQ_NAMES.mutableIterable;
        Intrinsics.checkExpressionValueIsNotNull(fqName, "FQ_NAMES.mutableIterable");
        FqName packageFqName = classId3.getPackageFqName();
        FqName packageFqName2 = classId3.getPackageFqName();
        Intrinsics.checkExpressionValueIsNotNull(packageFqName2, "kotlinReadOnly.packageFqName");
        FqName tail = FqNamesUtilKt.tail(fqName, packageFqName2);
        ClassId classId4 = new ClassId(packageFqName, tail, false);
        ClassId classId5 = ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.iterator);
        Intrinsics.checkExpressionValueIsNotNull(classId5, "ClassId.topLevel(FQ_NAMES.iterator)");
        FqName fqName2 = KotlinBuiltIns.FQ_NAMES.mutableIterator;
        Intrinsics.checkExpressionValueIsNotNull(fqName2, "FQ_NAMES.mutableIterator");
        FqName packageFqName3 = classId5.getPackageFqName();
        FqName packageFqName4 = classId5.getPackageFqName();
        Intrinsics.checkExpressionValueIsNotNull(packageFqName4, "kotlinReadOnly.packageFqName");
        ClassId classId6 = new ClassId(packageFqName3, FqNamesUtilKt.tail(fqName2, packageFqName4), false);
        ClassId classId7 = ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.collection);
        Intrinsics.checkExpressionValueIsNotNull(classId7, "ClassId.topLevel(FQ_NAMES.collection)");
        FqName fqName3 = KotlinBuiltIns.FQ_NAMES.mutableCollection;
        Intrinsics.checkExpressionValueIsNotNull(fqName3, "FQ_NAMES.mutableCollection");
        FqName packageFqName5 = classId7.getPackageFqName();
        FqName packageFqName6 = classId7.getPackageFqName();
        Intrinsics.checkExpressionValueIsNotNull(packageFqName6, "kotlinReadOnly.packageFqName");
        ClassId classId8 = new ClassId(packageFqName5, FqNamesUtilKt.tail(fqName3, packageFqName6), false);
        ClassId classId9 = ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.list);
        Intrinsics.checkExpressionValueIsNotNull(classId9, "ClassId.topLevel(FQ_NAMES.list)");
        FqName fqName4 = KotlinBuiltIns.FQ_NAMES.mutableList;
        Intrinsics.checkExpressionValueIsNotNull(fqName4, "FQ_NAMES.mutableList");
        FqName packageFqName7 = classId9.getPackageFqName();
        FqName packageFqName8 = classId9.getPackageFqName();
        Intrinsics.checkExpressionValueIsNotNull(packageFqName8, "kotlinReadOnly.packageFqName");
        ClassId classId10 = new ClassId(packageFqName7, FqNamesUtilKt.tail(fqName4, packageFqName8), false);
        ClassId classId11 = ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.set);
        Intrinsics.checkExpressionValueIsNotNull(classId11, "ClassId.topLevel(FQ_NAMES.set)");
        FqName fqName5 = KotlinBuiltIns.FQ_NAMES.mutableSet;
        Intrinsics.checkExpressionValueIsNotNull(fqName5, "FQ_NAMES.mutableSet");
        FqName packageFqName9 = classId11.getPackageFqName();
        FqName packageFqName10 = classId11.getPackageFqName();
        Intrinsics.checkExpressionValueIsNotNull(packageFqName10, "kotlinReadOnly.packageFqName");
        ClassId classId12 = new ClassId(packageFqName9, FqNamesUtilKt.tail(fqName5, packageFqName10), false);
        ClassId classId13 = ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.listIterator);
        Intrinsics.checkExpressionValueIsNotNull(classId13, "ClassId.topLevel(FQ_NAMES.listIterator)");
        FqName fqName6 = KotlinBuiltIns.FQ_NAMES.mutableListIterator;
        Intrinsics.checkExpressionValueIsNotNull(fqName6, "FQ_NAMES.mutableListIterator");
        FqName packageFqName11 = classId13.getPackageFqName();
        FqName packageFqName12 = classId13.getPackageFqName();
        Intrinsics.checkExpressionValueIsNotNull(packageFqName12, "kotlinReadOnly.packageFqName");
        ClassId classId14 = new ClassId(packageFqName11, FqNamesUtilKt.tail(fqName6, packageFqName12), false);
        ClassId classId15 = ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.map);
        Intrinsics.checkExpressionValueIsNotNull(classId15, "ClassId.topLevel(FQ_NAMES.map)");
        FqName fqName7 = KotlinBuiltIns.FQ_NAMES.mutableMap;
        Intrinsics.checkExpressionValueIsNotNull(fqName7, "FQ_NAMES.mutableMap");
        FqName packageFqName13 = classId15.getPackageFqName();
        FqName packageFqName14 = classId15.getPackageFqName();
        Intrinsics.checkExpressionValueIsNotNull(packageFqName14, "kotlinReadOnly.packageFqName");
        ClassId classId16 = new ClassId(packageFqName13, FqNamesUtilKt.tail(fqName7, packageFqName14), false);
        ClassId createNestedClassId = ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.map).createNestedClassId(KotlinBuiltIns.FQ_NAMES.mapEntry.shortName());
        Intrinsics.checkExpressionValueIsNotNull(createNestedClassId, "ClassId.topLevel(FQ_NAME…MES.mapEntry.shortName())");
        FqName fqName8 = KotlinBuiltIns.FQ_NAMES.mutableMapEntry;
        Intrinsics.checkExpressionValueIsNotNull(fqName8, "FQ_NAMES.mutableMapEntry");
        FqName packageFqName15 = createNestedClassId.getPackageFqName();
        FqName packageFqName16 = createNestedClassId.getPackageFqName();
        Intrinsics.checkExpressionValueIsNotNull(packageFqName16, "kotlinReadOnly.packageFqName");
        mutabilityMappings = CollectionsKt.listOf(new PlatformMutabilityMapping(javaToKotlinClassMap.classId(Iterable.class), classId3, classId4), new PlatformMutabilityMapping(javaToKotlinClassMap.classId(Iterator.class), classId5, classId6), new PlatformMutabilityMapping(javaToKotlinClassMap.classId(Collection.class), classId7, classId8), new PlatformMutabilityMapping(javaToKotlinClassMap.classId(List.class), classId9, classId10), new PlatformMutabilityMapping(javaToKotlinClassMap.classId(Set.class), classId11, classId12), new PlatformMutabilityMapping(javaToKotlinClassMap.classId(ListIterator.class), classId13, classId14), new PlatformMutabilityMapping(javaToKotlinClassMap.classId(Map.class), classId15, classId16), new PlatformMutabilityMapping(javaToKotlinClassMap.classId(Entry.class), createNestedClassId, new ClassId(packageFqName15, FqNamesUtilKt.tail(fqName8, packageFqName16), false)));
        FqNameUnsafe fqNameUnsafe = KotlinBuiltIns.FQ_NAMES.any;
        Intrinsics.checkExpressionValueIsNotNull(fqNameUnsafe, "FQ_NAMES.any");
        javaToKotlinClassMap.addTopLevel(Object.class, fqNameUnsafe);
        FqNameUnsafe fqNameUnsafe2 = KotlinBuiltIns.FQ_NAMES.string;
        Intrinsics.checkExpressionValueIsNotNull(fqNameUnsafe2, "FQ_NAMES.string");
        javaToKotlinClassMap.addTopLevel(String.class, fqNameUnsafe2);
        FqNameUnsafe fqNameUnsafe3 = KotlinBuiltIns.FQ_NAMES.charSequence;
        Intrinsics.checkExpressionValueIsNotNull(fqNameUnsafe3, "FQ_NAMES.charSequence");
        javaToKotlinClassMap.addTopLevel(CharSequence.class, fqNameUnsafe3);
        FqName fqName9 = KotlinBuiltIns.FQ_NAMES.throwable;
        Intrinsics.checkExpressionValueIsNotNull(fqName9, "FQ_NAMES.throwable");
        javaToKotlinClassMap.addTopLevel(Throwable.class, fqName9);
        FqNameUnsafe fqNameUnsafe4 = KotlinBuiltIns.FQ_NAMES.cloneable;
        Intrinsics.checkExpressionValueIsNotNull(fqNameUnsafe4, "FQ_NAMES.cloneable");
        javaToKotlinClassMap.addTopLevel(Cloneable.class, fqNameUnsafe4);
        FqNameUnsafe fqNameUnsafe5 = KotlinBuiltIns.FQ_NAMES.number;
        Intrinsics.checkExpressionValueIsNotNull(fqNameUnsafe5, "FQ_NAMES.number");
        javaToKotlinClassMap.addTopLevel(Number.class, fqNameUnsafe5);
        FqName fqName10 = KotlinBuiltIns.FQ_NAMES.comparable;
        Intrinsics.checkExpressionValueIsNotNull(fqName10, "FQ_NAMES.comparable");
        javaToKotlinClassMap.addTopLevel(Comparable.class, fqName10);
        FqNameUnsafe fqNameUnsafe6 = KotlinBuiltIns.FQ_NAMES._enum;
        Intrinsics.checkExpressionValueIsNotNull(fqNameUnsafe6, "FQ_NAMES._enum");
        javaToKotlinClassMap.addTopLevel(Enum.class, fqNameUnsafe6);
        FqName fqName11 = KotlinBuiltIns.FQ_NAMES.annotation;
        Intrinsics.checkExpressionValueIsNotNull(fqName11, "FQ_NAMES.annotation");
        javaToKotlinClassMap.addTopLevel(Annotation.class, fqName11);
        for (PlatformMutabilityMapping addMapping : mutabilityMappings) {
            javaToKotlinClassMap.addMapping(addMapping);
        }
        for (JvmPrimitiveType jvmPrimitiveType : JvmPrimitiveType.values()) {
            ClassId classId17 = ClassId.topLevel(jvmPrimitiveType.getWrapperFqName());
            Intrinsics.checkExpressionValueIsNotNull(classId17, "ClassId.topLevel(jvmType.wrapperFqName)");
            ClassId classId18 = ClassId.topLevel(KotlinBuiltIns.getPrimitiveFqName(jvmPrimitiveType.getPrimitiveType()));
            Intrinsics.checkExpressionValueIsNotNull(classId18, "ClassId.topLevel(KotlinB…e(jvmType.primitiveType))");
            javaToKotlinClassMap.add(classId17, classId18);
        }
        for (ClassId classId19 : CompanionObjectMapping.INSTANCE.allClassesWithIntrinsicCompanions()) {
            StringBuilder sb5 = new StringBuilder();
            sb5.append("kotlin.jvm.internal.");
            sb5.append(classId19.getShortClassName().asString());
            sb5.append("CompanionObject");
            ClassId classId20 = ClassId.topLevel(new FqName(sb5.toString()));
            Intrinsics.checkExpressionValueIsNotNull(classId20, "ClassId.topLevel(FqName(…g() + \"CompanionObject\"))");
            ClassId createNestedClassId2 = classId19.createNestedClassId(SpecialNames.DEFAULT_NAME_FOR_COMPANION_OBJECT);
            Intrinsics.checkExpressionValueIsNotNull(createNestedClassId2, "classId.createNestedClas…AME_FOR_COMPANION_OBJECT)");
            javaToKotlinClassMap.add(classId20, createNestedClassId2);
        }
        for (int i = 0; i < 23; i++) {
            StringBuilder sb6 = new StringBuilder();
            sb6.append("kotlin.jvm.functions.Function");
            sb6.append(i);
            ClassId classId21 = ClassId.topLevel(new FqName(sb6.toString()));
            Intrinsics.checkExpressionValueIsNotNull(classId21, "ClassId.topLevel(FqName(…m.functions.Function$i\"))");
            ClassId functionClassId = KotlinBuiltIns.getFunctionClassId(i);
            Intrinsics.checkExpressionValueIsNotNull(functionClassId, "KotlinBuiltIns.getFunctionClassId(i)");
            javaToKotlinClassMap.add(classId21, functionClassId);
            StringBuilder sb7 = new StringBuilder();
            sb7.append(NUMBERED_K_FUNCTION_PREFIX);
            sb7.append(i);
            javaToKotlinClassMap.addKotlinToJava(new FqName(sb7.toString()), K_FUNCTION_CLASS_ID);
        }
        for (int i2 = 0; i2 < 22; i2++) {
            Kind kind = Kind.KSuspendFunction;
            StringBuilder sb8 = new StringBuilder();
            sb8.append(kind.getPackageFqName().toString());
            sb8.append(".");
            sb8.append(kind.getClassNamePrefix());
            String sb9 = sb8.toString();
            StringBuilder sb10 = new StringBuilder();
            sb10.append(sb9);
            sb10.append(i2);
            javaToKotlinClassMap.addKotlinToJava(new FqName(sb10.toString()), K_FUNCTION_CLASS_ID);
        }
        FqName safe = KotlinBuiltIns.FQ_NAMES.nothing.toSafe();
        Intrinsics.checkExpressionValueIsNotNull(safe, "FQ_NAMES.nothing.toSafe()");
        javaToKotlinClassMap.addKotlinToJava(safe, javaToKotlinClassMap.classId(Void.class));
    }

    private JavaToKotlinClassMap() {
    }

    @NotNull
    public final FqName getFUNCTION_N_FQ_NAME() {
        return FUNCTION_N_FQ_NAME;
    }

    @NotNull
    public final List<PlatformMutabilityMapping> getMutabilityMappings() {
        return mutabilityMappings;
    }

    @Nullable
    public final ClassId mapJavaToKotlin(@NotNull FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        return (ClassId) javaToKotlin.get(fqName.toUnsafe());
    }

    @Nullable
    public static /* synthetic */ ClassDescriptor mapJavaToKotlin$default(JavaToKotlinClassMap javaToKotlinClassMap, FqName fqName, KotlinBuiltIns kotlinBuiltIns, Integer num, int i, Object obj) {
        if ((i & 4) != 0) {
            num = null;
        }
        return javaToKotlinClassMap.mapJavaToKotlin(fqName, kotlinBuiltIns, num);
    }

    @Nullable
    public final ClassDescriptor mapJavaToKotlin(@NotNull FqName fqName, @NotNull KotlinBuiltIns kotlinBuiltIns, @Nullable Integer num) {
        ClassId classId;
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        Intrinsics.checkParameterIsNotNull(kotlinBuiltIns, "builtIns");
        if (num == null || !Intrinsics.areEqual((Object) fqName, (Object) FUNCTION_N_FQ_NAME)) {
            classId = mapJavaToKotlin(fqName);
        } else {
            classId = KotlinBuiltIns.getFunctionClassId(num.intValue());
        }
        if (classId != null) {
            return kotlinBuiltIns.getBuiltInClassByFqName(classId.asSingleFqName());
        }
        return null;
    }

    @Nullable
    public final ClassId mapKotlinToJava(@NotNull FqNameUnsafe fqNameUnsafe) {
        Intrinsics.checkParameterIsNotNull(fqNameUnsafe, "kotlinFqName");
        if (isKotlinFunctionWithBigArity(fqNameUnsafe, NUMBERED_FUNCTION_PREFIX)) {
            return FUNCTION_N_CLASS_ID;
        }
        if (isKotlinFunctionWithBigArity(fqNameUnsafe, NUMBERED_SUSPEND_FUNCTION_PREFIX)) {
            return FUNCTION_N_CLASS_ID;
        }
        if (isKotlinFunctionWithBigArity(fqNameUnsafe, NUMBERED_K_FUNCTION_PREFIX)) {
            return K_FUNCTION_CLASS_ID;
        }
        if (isKotlinFunctionWithBigArity(fqNameUnsafe, NUMBERED_K_SUSPEND_FUNCTION_PREFIX)) {
            return K_FUNCTION_CLASS_ID;
        }
        return (ClassId) kotlinToJava.get(fqNameUnsafe);
    }

    private final boolean isKotlinFunctionWithBigArity(FqNameUnsafe fqNameUnsafe, String str) {
        String asString = fqNameUnsafe.asString();
        Intrinsics.checkExpressionValueIsNotNull(asString, "kotlinFqName.asString()");
        String substringAfter = StringsKt.substringAfter(asString, str, "");
        CharSequence charSequence = substringAfter;
        boolean z = true;
        if (!(charSequence.length() > 0) || StringsKt.startsWith$default(charSequence, '0', false, 2, (Object) null)) {
            return false;
        }
        Integer intOrNull = StringsKt.toIntOrNull(substringAfter);
        if (intOrNull == null || intOrNull.intValue() < 23) {
            z = false;
        }
        return z;
    }

    private final void addMapping(PlatformMutabilityMapping platformMutabilityMapping) {
        ClassId component1 = platformMutabilityMapping.component1();
        ClassId component2 = platformMutabilityMapping.component2();
        ClassId component3 = platformMutabilityMapping.component3();
        add(component1, component2);
        FqName asSingleFqName = component3.asSingleFqName();
        Intrinsics.checkExpressionValueIsNotNull(asSingleFqName, "mutableClassId.asSingleFqName()");
        addKotlinToJava(asSingleFqName, component1);
        FqName asSingleFqName2 = component2.asSingleFqName();
        Intrinsics.checkExpressionValueIsNotNull(asSingleFqName2, "readOnlyClassId.asSingleFqName()");
        FqName asSingleFqName3 = component3.asSingleFqName();
        Intrinsics.checkExpressionValueIsNotNull(asSingleFqName3, "mutableClassId.asSingleFqName()");
        mutableToReadOnly.put(component3.asSingleFqName().toUnsafe(), asSingleFqName2);
        readOnlyToMutable.put(asSingleFqName2.toUnsafe(), asSingleFqName3);
    }

    private final void add(ClassId classId, ClassId classId2) {
        addJavaToKotlin(classId, classId2);
        FqName asSingleFqName = classId2.asSingleFqName();
        Intrinsics.checkExpressionValueIsNotNull(asSingleFqName, "kotlinClassId.asSingleFqName()");
        addKotlinToJava(asSingleFqName, classId);
    }

    private final void addTopLevel(Class<?> cls, FqNameUnsafe fqNameUnsafe) {
        FqName safe = fqNameUnsafe.toSafe();
        Intrinsics.checkExpressionValueIsNotNull(safe, "kotlinFqName.toSafe()");
        addTopLevel(cls, safe);
    }

    private final void addTopLevel(Class<?> cls, FqName fqName) {
        ClassId classId = classId(cls);
        ClassId classId2 = ClassId.topLevel(fqName);
        Intrinsics.checkExpressionValueIsNotNull(classId2, "ClassId.topLevel(kotlinFqName)");
        add(classId, classId2);
    }

    private final void addJavaToKotlin(ClassId classId, ClassId classId2) {
        javaToKotlin.put(classId.asSingleFqName().toUnsafe(), classId2);
    }

    private final void addKotlinToJava(FqName fqName, ClassId classId) {
        kotlinToJava.put(fqName.toUnsafe(), classId);
    }

    @NotNull
    public final Collection<ClassDescriptor> mapPlatformClass(@NotNull FqName fqName, @NotNull KotlinBuiltIns kotlinBuiltIns) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        Intrinsics.checkParameterIsNotNull(kotlinBuiltIns, "builtIns");
        ClassDescriptor mapJavaToKotlin$default = mapJavaToKotlin$default(this, fqName, kotlinBuiltIns, null, 4, null);
        if (mapJavaToKotlin$default == null) {
            return SetsKt.emptySet();
        }
        FqName fqName2 = (FqName) readOnlyToMutable.get(DescriptorUtilsKt.getFqNameUnsafe(mapJavaToKotlin$default));
        if (fqName2 == null) {
            return SetsKt.setOf(mapJavaToKotlin$default);
        }
        Intrinsics.checkExpressionValueIsNotNull(fqName2, "readOnlyToMutable[kotlin…eturn setOf(kotlinAnalog)");
        List asList = Arrays.asList(new ClassDescriptor[]{mapJavaToKotlin$default, kotlinBuiltIns.getBuiltInClassByFqName(fqName2)});
        Intrinsics.checkExpressionValueIsNotNull(asList, "Arrays.asList(kotlinAnal…tlinMutableAnalogFqName))");
        return asList;
    }

    public final boolean isMutable(@NotNull ClassDescriptor classDescriptor) {
        Intrinsics.checkParameterIsNotNull(classDescriptor, "mutable");
        return mutableToReadOnly.containsKey(DescriptorUtils.getFqName(classDescriptor));
    }

    public final boolean isMutable(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "type");
        ClassDescriptor classDescriptor = TypeUtils.getClassDescriptor(kotlinType);
        return classDescriptor != null && isMutable(classDescriptor);
    }

    public final boolean isReadOnly(@NotNull ClassDescriptor classDescriptor) {
        Intrinsics.checkParameterIsNotNull(classDescriptor, "readOnly");
        return readOnlyToMutable.containsKey(DescriptorUtils.getFqName(classDescriptor));
    }

    public final boolean isReadOnly(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "type");
        ClassDescriptor classDescriptor = TypeUtils.getClassDescriptor(kotlinType);
        return classDescriptor != null && isReadOnly(classDescriptor);
    }

    @NotNull
    public final ClassDescriptor convertMutableToReadOnly(@NotNull ClassDescriptor classDescriptor) {
        Intrinsics.checkParameterIsNotNull(classDescriptor, "mutable");
        return convertToOppositeMutability(classDescriptor, mutableToReadOnly, "mutable");
    }

    @NotNull
    public final ClassDescriptor convertReadOnlyToMutable(@NotNull ClassDescriptor classDescriptor) {
        Intrinsics.checkParameterIsNotNull(classDescriptor, "readOnly");
        return convertToOppositeMutability(classDescriptor, readOnlyToMutable, "read-only");
    }

    /* access modifiers changed from: private */
    public final ClassId classId(Class<?> cls) {
        boolean z = !cls.isPrimitive() && !cls.isArray();
        if (!_Assertions.ENABLED || z) {
            Class declaringClass = cls.getDeclaringClass();
            if (declaringClass == null) {
                ClassId classId = ClassId.topLevel(new FqName(cls.getCanonicalName()));
                Intrinsics.checkExpressionValueIsNotNull(classId, "ClassId.topLevel(FqName(clazz.canonicalName))");
                return classId;
            }
            ClassId createNestedClassId = classId(declaringClass).createNestedClassId(Name.identifier(cls.getSimpleName()));
            Intrinsics.checkExpressionValueIsNotNull(createNestedClassId, "classId(outer).createNes…tifier(clazz.simpleName))");
            return createNestedClassId;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid class: ");
        sb.append(cls);
        throw new AssertionError(sb.toString());
    }

    private final ClassDescriptor convertToOppositeMutability(ClassDescriptor classDescriptor, Map<FqNameUnsafe, FqName> map, String str) {
        DeclarationDescriptor declarationDescriptor = classDescriptor;
        FqName fqName = (FqName) map.get(DescriptorUtils.getFqName(declarationDescriptor));
        if (fqName != null) {
            ClassDescriptor builtInClassByFqName = DescriptorUtilsKt.getBuiltIns(declarationDescriptor).getBuiltInClassByFqName(fqName);
            Intrinsics.checkExpressionValueIsNotNull(builtInClassByFqName, "descriptor.builtIns.getB…Name(oppositeClassFqName)");
            return builtInClassByFqName;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Given class ");
        sb.append(classDescriptor);
        sb.append(" is not a ");
        sb.append(str);
        sb.append(" collection");
        throw new IllegalArgumentException(sb.toString());
    }
}
