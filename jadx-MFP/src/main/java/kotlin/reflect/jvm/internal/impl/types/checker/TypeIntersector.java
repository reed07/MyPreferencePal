package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import org.jetbrains.annotations.NotNull;

/* compiled from: IntersectionType.kt */
public final class TypeIntersector {
    public static final TypeIntersector INSTANCE = new TypeIntersector();

    /* compiled from: IntersectionType.kt */
    private enum ResultNullability {
        ;

        /* compiled from: IntersectionType.kt */
        static final class ACCEPT_NULL extends ResultNullability {
            ACCEPT_NULL(String str, int i) {
                super(str, i);
            }

            @NotNull
            public ResultNullability combine(@NotNull UnwrappedType unwrappedType) {
                Intrinsics.checkParameterIsNotNull(unwrappedType, "nextType");
                return getResultNullability(unwrappedType);
            }
        }

        /* compiled from: IntersectionType.kt */
        static final class NOT_NULL extends ResultNullability {
            @NotNull
            public NOT_NULL combine(@NotNull UnwrappedType unwrappedType) {
                Intrinsics.checkParameterIsNotNull(unwrappedType, "nextType");
                return this;
            }

            NOT_NULL(String str, int i) {
                super(str, i);
            }
        }

        /* compiled from: IntersectionType.kt */
        static final class START extends ResultNullability {
            START(String str, int i) {
                super(str, i);
            }

            @NotNull
            public ResultNullability combine(@NotNull UnwrappedType unwrappedType) {
                Intrinsics.checkParameterIsNotNull(unwrappedType, "nextType");
                return getResultNullability(unwrappedType);
            }
        }

        /* compiled from: IntersectionType.kt */
        static final class UNKNOWN extends ResultNullability {
            UNKNOWN(String str, int i) {
                super(str, i);
            }

            @NotNull
            public ResultNullability combine(@NotNull UnwrappedType unwrappedType) {
                Intrinsics.checkParameterIsNotNull(unwrappedType, "nextType");
                ResultNullability resultNullability = getResultNullability(unwrappedType);
                return resultNullability == ResultNullability.ACCEPT_NULL ? this : resultNullability;
            }
        }

        @NotNull
        public abstract ResultNullability combine(@NotNull UnwrappedType unwrappedType);

        /* access modifiers changed from: protected */
        @NotNull
        public final ResultNullability getResultNullability(@NotNull UnwrappedType unwrappedType) {
            Intrinsics.checkParameterIsNotNull(unwrappedType, "receiver$0");
            if (unwrappedType.isMarkedNullable()) {
                return ACCEPT_NULL;
            }
            if (NullabilityChecker.INSTANCE.isSubtypeOfAny(unwrappedType)) {
                return NOT_NULL;
            }
            return UNKNOWN;
        }
    }

    private TypeIntersector() {
    }

    @NotNull
    public final SimpleType intersectTypes$descriptors(@NotNull List<? extends SimpleType> list) {
        Intrinsics.checkParameterIsNotNull(list, "types");
        boolean z = list.size() > 1;
        if (!_Assertions.ENABLED || z) {
            ArrayList arrayList = new ArrayList();
            for (SimpleType simpleType : list) {
                if (simpleType.getConstructor() instanceof IntersectionTypeConstructor) {
                    Collection supertypes = simpleType.getConstructor().getSupertypes();
                    Intrinsics.checkExpressionValueIsNotNull(supertypes, "type.constructor.supertypes");
                    Iterable<KotlinType> iterable = supertypes;
                    Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                    for (KotlinType kotlinType : iterable) {
                        Intrinsics.checkExpressionValueIsNotNull(kotlinType, "it");
                        SimpleType upperIfFlexible = FlexibleTypesKt.upperIfFlexible(kotlinType);
                        if (simpleType.isMarkedNullable()) {
                            upperIfFlexible = upperIfFlexible.makeNullableAsSpecified(true);
                        }
                        arrayList2.add(upperIfFlexible);
                    }
                    arrayList.addAll((List) arrayList2);
                } else {
                    arrayList.add(simpleType);
                }
            }
            Iterable<UnwrappedType> iterable2 = arrayList;
            ResultNullability resultNullability = ResultNullability.START;
            for (UnwrappedType combine : iterable2) {
                resultNullability = resultNullability.combine(combine);
            }
            Collection linkedHashSet = new LinkedHashSet();
            Iterator it = iterable2.iterator();
            while (it.hasNext()) {
                SimpleType simpleType2 = (SimpleType) it.next();
                if (resultNullability == ResultNullability.NOT_NULL) {
                    simpleType2 = SpecialTypesKt.makeSimpleTypeDefinitelyNotNullOrNotNull(simpleType2);
                }
                linkedHashSet.add(simpleType2);
            }
            return intersectTypesWithoutIntersectionType((LinkedHashSet) linkedHashSet);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Size should be at least 2, but it is ");
        sb.append(list.size());
        throw new AssertionError(sb.toString());
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x0075 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final kotlin.reflect.jvm.internal.impl.types.SimpleType intersectTypesWithoutIntersectionType(java.util.Set<? extends kotlin.reflect.jvm.internal.impl.types.SimpleType> r12) {
        /*
            r11 = this;
            int r0 = r12.size()
            r1 = 1
            if (r0 != r1) goto L_0x0010
            java.lang.Iterable r12 = (java.lang.Iterable) r12
            java.lang.Object r12 = kotlin.collections.CollectionsKt.single(r12)
            kotlin.reflect.jvm.internal.impl.types.SimpleType r12 = (kotlin.reflect.jvm.internal.impl.types.SimpleType) r12
            return r12
        L_0x0010:
            java.util.ArrayList r0 = new java.util.ArrayList
            r2 = r12
            java.util.Collection r2 = (java.util.Collection) r2
            r0.<init>(r2)
            java.util.Iterator r3 = r0.iterator()
            java.lang.String r4 = "filteredSuperAndEqualTypes.iterator()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4)
        L_0x0021:
            boolean r4 = r3.hasNext()
            r5 = 0
            if (r4 == 0) goto L_0x007c
            java.lang.Object r4 = r3.next()
            kotlin.reflect.jvm.internal.impl.types.SimpleType r4 = (kotlin.reflect.jvm.internal.impl.types.SimpleType) r4
            r6 = r0
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            boolean r7 = r6 instanceof java.util.Collection
            if (r7 == 0) goto L_0x003f
            r7 = r6
            java.util.Collection r7 = (java.util.Collection) r7
            boolean r7 = r7.isEmpty()
            if (r7 == 0) goto L_0x003f
            goto L_0x0076
        L_0x003f:
            java.util.Iterator r6 = r6.iterator()
        L_0x0043:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x0076
            java.lang.Object r7 = r6.next()
            kotlin.reflect.jvm.internal.impl.types.SimpleType r7 = (kotlin.reflect.jvm.internal.impl.types.SimpleType) r7
            if (r7 == r4) goto L_0x0072
            kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector r8 = INSTANCE
            java.lang.String r9 = "lower"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r9)
            kotlin.reflect.jvm.internal.impl.types.KotlinType r7 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r7
            java.lang.String r9 = "upper"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r9)
            r9 = r4
            kotlin.reflect.jvm.internal.impl.types.KotlinType r9 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r9
            boolean r8 = r8.isStrictSupertype(r7, r9)
            if (r8 != 0) goto L_0x0070
            kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeChecker r8 = kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeChecker.INSTANCE
            boolean r7 = r8.equalTypes(r7, r9)
            if (r7 == 0) goto L_0x0072
        L_0x0070:
            r7 = 1
            goto L_0x0073
        L_0x0072:
            r7 = 0
        L_0x0073:
            if (r7 == 0) goto L_0x0043
            r5 = 1
        L_0x0076:
            if (r5 == 0) goto L_0x0021
            r3.remove()
            goto L_0x0021
        L_0x007c:
            r3 = r0
            java.util.Collection r3 = (java.util.Collection) r3
            boolean r3 = r3.isEmpty()
            r1 = r1 ^ r3
            boolean r3 = kotlin._Assertions.ENABLED
            if (r3 == 0) goto L_0x00b4
            if (r1 == 0) goto L_0x008b
            goto L_0x00b4
        L_0x008b:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "This collections cannot be empty! input types: "
            r0.append(r1)
            r2 = r12
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 63
            r10 = 0
            java.lang.String r12 = kotlin.collections.CollectionsKt.joinToString$default(r2, r3, r4, r5, r6, r7, r8, r9, r10)
            r0.append(r12)
            java.lang.String r12 = r0.toString()
            java.lang.AssertionError r0 = new java.lang.AssertionError
            r0.<init>(r12)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x00b4:
            int r12 = r0.size()
            r1 = 2
            if (r12 >= r1) goto L_0x00c9
            java.util.List r0 = (java.util.List) r0
            java.lang.Object r12 = kotlin.collections.CollectionsKt.single(r0)
            java.lang.String r0 = "filteredSuperAndEqualTypes.single()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r12, r0)
            kotlin.reflect.jvm.internal.impl.types.SimpleType r12 = (kotlin.reflect.jvm.internal.impl.types.SimpleType) r12
            return r12
        L_0x00c9:
            kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor r12 = new kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor
            r12.<init>(r2)
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations$Companion r0 = kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations.Companion
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r0 = r0.getEMPTY()
            r1 = r12
            kotlin.reflect.jvm.internal.impl.types.TypeConstructor r1 = (kotlin.reflect.jvm.internal.impl.types.TypeConstructor) r1
            java.util.List r2 = kotlin.collections.CollectionsKt.emptyList()
            kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope r12 = r12.createScopeForKotlinType()
            java.lang.String r3 = "constructor.createScopeForKotlinType()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r12, r3)
            kotlin.reflect.jvm.internal.impl.types.SimpleType r12 = kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(r0, r1, r2, r5, r12)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector.intersectTypesWithoutIntersectionType(java.util.Set):kotlin.reflect.jvm.internal.impl.types.SimpleType");
    }

    private final boolean isStrictSupertype(KotlinType kotlinType, KotlinType kotlinType2) {
        NewKotlinTypeChecker newKotlinTypeChecker = NewKotlinTypeChecker.INSTANCE;
        return newKotlinTypeChecker.isSubtypeOf(kotlinType, kotlinType2) && !newKotlinTypeChecker.isSubtypeOf(kotlinType2, kotlinType);
    }
}
