package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: MemberScope.kt */
public final class DescriptorKindFilter {
    @NotNull
    @JvmField
    public static final DescriptorKindFilter ALL = new DescriptorKindFilter(ALL_KINDS_MASK, null, 2, null);
    /* access modifiers changed from: private */
    public static final int ALL_KINDS_MASK = (Companion.nextMask() - 1);
    @NotNull
    @JvmField
    public static final DescriptorKindFilter CALLABLES = new DescriptorKindFilter(CALLABLES_MASK, null, 2, null);
    private static final int CALLABLES_MASK;
    @NotNull
    @JvmField
    public static final DescriptorKindFilter CLASSIFIERS = new DescriptorKindFilter(CLASSIFIERS_MASK, null, 2, null);
    /* access modifiers changed from: private */
    public static final int CLASSIFIERS_MASK;
    public static final Companion Companion = new Companion(null);
    private static final List<MaskToName> DEBUG_MASK_BIT_NAMES;
    private static final List<MaskToName> DEBUG_PREDEFINED_FILTERS_MASK_NAMES;
    @NotNull
    @JvmField
    public static final DescriptorKindFilter FUNCTIONS = new DescriptorKindFilter(FUNCTIONS_MASK, null, 2, null);
    /* access modifiers changed from: private */
    public static final int FUNCTIONS_MASK = Companion.nextMask();
    @NotNull
    @JvmField
    public static final DescriptorKindFilter NON_SINGLETON_CLASSIFIERS = new DescriptorKindFilter(NON_SINGLETON_CLASSIFIERS_MASK, null, 2, null);
    /* access modifiers changed from: private */
    public static final int NON_SINGLETON_CLASSIFIERS_MASK = Companion.nextMask();
    @NotNull
    @JvmField
    public static final DescriptorKindFilter PACKAGES = new DescriptorKindFilter(PACKAGES_MASK, null, 2, null);
    /* access modifiers changed from: private */
    public static final int PACKAGES_MASK = Companion.nextMask();
    @NotNull
    @JvmField
    public static final DescriptorKindFilter SINGLETON_CLASSIFIERS = new DescriptorKindFilter(SINGLETON_CLASSIFIERS_MASK, null, 2, null);
    /* access modifiers changed from: private */
    public static final int SINGLETON_CLASSIFIERS_MASK = Companion.nextMask();
    @NotNull
    @JvmField
    public static final DescriptorKindFilter TYPE_ALIASES = new DescriptorKindFilter(TYPE_ALIASES_MASK, null, 2, null);
    /* access modifiers changed from: private */
    public static final int TYPE_ALIASES_MASK = Companion.nextMask();
    @NotNull
    @JvmField
    public static final DescriptorKindFilter VALUES = new DescriptorKindFilter(VALUES_MASK, null, 2, null);
    private static final int VALUES_MASK;
    @NotNull
    @JvmField
    public static final DescriptorKindFilter VARIABLES = new DescriptorKindFilter(VARIABLES_MASK, null, 2, null);
    /* access modifiers changed from: private */
    public static final int VARIABLES_MASK = Companion.nextMask();
    /* access modifiers changed from: private */
    public static int nextMaskValue = 1;
    @NotNull
    private final List<DescriptorKindExclude> excludes;
    private final int kindMask;

    /* compiled from: MemberScope.kt */
    public static final class Companion {

        /* compiled from: MemberScope.kt */
        private static final class MaskToName {
            private final int mask;
            @NotNull
            private final String name;

            public MaskToName(int i, @NotNull String str) {
                Intrinsics.checkParameterIsNotNull(str, "name");
                this.mask = i;
                this.name = str;
            }

            public final int getMask() {
                return this.mask;
            }

            @NotNull
            public final String getName() {
                return this.name;
            }
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        public final int nextMask() {
            int access$getNextMaskValue$cp = DescriptorKindFilter.nextMaskValue;
            DescriptorKindFilter.nextMaskValue = DescriptorKindFilter.nextMaskValue << 1;
            return access$getNextMaskValue$cp;
        }

        public final int getNON_SINGLETON_CLASSIFIERS_MASK() {
            return DescriptorKindFilter.NON_SINGLETON_CLASSIFIERS_MASK;
        }

        public final int getSINGLETON_CLASSIFIERS_MASK() {
            return DescriptorKindFilter.SINGLETON_CLASSIFIERS_MASK;
        }

        public final int getTYPE_ALIASES_MASK() {
            return DescriptorKindFilter.TYPE_ALIASES_MASK;
        }

        public final int getPACKAGES_MASK() {
            return DescriptorKindFilter.PACKAGES_MASK;
        }

        public final int getFUNCTIONS_MASK() {
            return DescriptorKindFilter.FUNCTIONS_MASK;
        }

        public final int getVARIABLES_MASK() {
            return DescriptorKindFilter.VARIABLES_MASK;
        }

        public final int getALL_KINDS_MASK() {
            return DescriptorKindFilter.ALL_KINDS_MASK;
        }

        public final int getCLASSIFIERS_MASK() {
            return DescriptorKindFilter.CLASSIFIERS_MASK;
        }
    }

    public DescriptorKindFilter(int i, @NotNull List<? extends DescriptorKindExclude> list) {
        Intrinsics.checkParameterIsNotNull(list, "excludes");
        this.excludes = list;
        for (DescriptorKindExclude fullyExcludedDescriptorKinds : this.excludes) {
            i &= ~fullyExcludedDescriptorKinds.getFullyExcludedDescriptorKinds();
        }
        this.kindMask = i;
    }

    public /* synthetic */ DescriptorKindFilter(int i, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i2 & 2) != 0) {
            list = CollectionsKt.emptyList();
        }
        this(i, list);
    }

    @NotNull
    public final List<DescriptorKindExclude> getExcludes() {
        return this.excludes;
    }

    public final int getKindMask() {
        return this.kindMask;
    }

    public final boolean acceptsKinds(int i) {
        return (i & this.kindMask) != 0;
    }

    @Nullable
    public final DescriptorKindFilter restrictedToKindsOrNull(int i) {
        int i2 = i & this.kindMask;
        if (i2 == 0) {
            return null;
        }
        return new DescriptorKindFilter(i2, this.excludes);
    }

    @NotNull
    public String toString() {
        Object obj;
        boolean z;
        Iterator it = DEBUG_PREDEFINED_FILTERS_MASK_NAMES.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((MaskToName) obj).getMask() == this.kindMask) {
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
        MaskToName maskToName = (MaskToName) obj;
        String name = maskToName != null ? maskToName.getName() : null;
        if (name == null) {
            Iterable<MaskToName> iterable = DEBUG_MASK_BIT_NAMES;
            Collection arrayList = new ArrayList();
            for (MaskToName maskToName2 : iterable) {
                Object name2 = acceptsKinds(maskToName2.getMask()) ? maskToName2.getName() : null;
                if (name2 != null) {
                    arrayList.add(name2);
                }
            }
            name = CollectionsKt.joinToString$default((List) arrayList, " | ", null, null, 0, null, null, 62, null);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("DescriptorKindFilter(");
        sb.append(name);
        sb.append(", ");
        sb.append(this.excludes);
        sb.append(')');
        return sb.toString();
    }

    static {
        Object obj;
        Object obj2;
        int i = NON_SINGLETON_CLASSIFIERS_MASK;
        int i2 = SINGLETON_CLASSIFIERS_MASK;
        CLASSIFIERS_MASK = i | i2 | TYPE_ALIASES_MASK;
        int i3 = FUNCTIONS_MASK;
        int i4 = i2 | i3;
        int i5 = VARIABLES_MASK;
        VALUES_MASK = i4 | i5;
        CALLABLES_MASK = i3 | i5;
        Companion companion = Companion;
        Field[] fields = DescriptorKindFilter.class.getFields();
        Intrinsics.checkExpressionValueIsNotNull(fields, "T::class.java.fields");
        Collection arrayList = new ArrayList();
        for (Field field : fields) {
            Intrinsics.checkExpressionValueIsNotNull(field, "it");
            if (Modifier.isStatic(field.getModifiers())) {
                arrayList.add(field);
            }
        }
        Iterable<Field> iterable = (List) arrayList;
        Collection arrayList2 = new ArrayList();
        for (Field field2 : iterable) {
            Object obj3 = field2.get(null);
            if (!(obj3 instanceof DescriptorKindFilter)) {
                obj3 = null;
            }
            DescriptorKindFilter descriptorKindFilter = (DescriptorKindFilter) obj3;
            if (descriptorKindFilter != null) {
                int i6 = descriptorKindFilter.kindMask;
                Intrinsics.checkExpressionValueIsNotNull(field2, "field");
                String name = field2.getName();
                Intrinsics.checkExpressionValueIsNotNull(name, "field.name");
                obj2 = new MaskToName(i6, name);
            } else {
                obj2 = null;
            }
            if (obj2 != null) {
                arrayList2.add(obj2);
            }
        }
        DEBUG_PREDEFINED_FILTERS_MASK_NAMES = CollectionsKt.toList((List) arrayList2);
        Companion companion2 = Companion;
        Field[] fields2 = DescriptorKindFilter.class.getFields();
        Intrinsics.checkExpressionValueIsNotNull(fields2, "T::class.java.fields");
        Collection arrayList3 = new ArrayList();
        for (Field field3 : fields2) {
            Intrinsics.checkExpressionValueIsNotNull(field3, "it");
            if (Modifier.isStatic(field3.getModifiers())) {
                arrayList3.add(field3);
            }
        }
        Iterable iterable2 = (List) arrayList3;
        Collection arrayList4 = new ArrayList();
        for (Object next : iterable2) {
            Field field4 = (Field) next;
            Intrinsics.checkExpressionValueIsNotNull(field4, "it");
            if (Intrinsics.areEqual((Object) field4.getType(), (Object) Integer.TYPE)) {
                arrayList4.add(next);
            }
        }
        Iterable<Field> iterable3 = (List) arrayList4;
        Collection arrayList5 = new ArrayList();
        for (Field field5 : iterable3) {
            Object obj4 = field5.get(null);
            if (obj4 != null) {
                int intValue = ((Integer) obj4).intValue();
                if (intValue == ((-intValue) & intValue)) {
                    Intrinsics.checkExpressionValueIsNotNull(field5, "field");
                    String name2 = field5.getName();
                    Intrinsics.checkExpressionValueIsNotNull(name2, "field.name");
                    obj = new MaskToName(intValue, name2);
                } else {
                    obj = null;
                }
                if (obj != null) {
                    arrayList5.add(obj);
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
        }
        DEBUG_MASK_BIT_NAMES = CollectionsKt.toList((List) arrayList5);
    }
}
