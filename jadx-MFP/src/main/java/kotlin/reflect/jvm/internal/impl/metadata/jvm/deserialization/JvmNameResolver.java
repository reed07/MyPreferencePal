package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Record;
import org.jetbrains.annotations.NotNull;

/* compiled from: JvmNameResolver.kt */
public final class JvmNameResolver implements NameResolver {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final List<String> PREDEFINED_STRINGS = CollectionsKt.listOf("kotlin/Any", "kotlin/Nothing", "kotlin/Unit", "kotlin/Throwable", "kotlin/Number", "kotlin/Byte", "kotlin/Double", "kotlin/Float", "kotlin/Int", "kotlin/Long", "kotlin/Short", "kotlin/Boolean", "kotlin/Char", "kotlin/CharSequence", "kotlin/String", "kotlin/Comparable", "kotlin/Enum", "kotlin/Array", "kotlin/ByteArray", "kotlin/DoubleArray", "kotlin/FloatArray", "kotlin/IntArray", "kotlin/LongArray", "kotlin/ShortArray", "kotlin/BooleanArray", "kotlin/CharArray", "kotlin/Cloneable", "kotlin/Annotation", "kotlin/collections/Iterable", "kotlin/collections/MutableIterable", "kotlin/collections/Collection", "kotlin/collections/MutableCollection", "kotlin/collections/List", "kotlin/collections/MutableList", "kotlin/collections/Set", "kotlin/collections/MutableSet", "kotlin/collections/Map", "kotlin/collections/MutableMap", "kotlin/collections/Map.Entry", "kotlin/collections/MutableMap.MutableEntry", "kotlin/collections/Iterator", "kotlin/collections/MutableIterator", "kotlin/collections/ListIterator", "kotlin/collections/MutableListIterator");
    private static final Map<String, Integer> PREDEFINED_STRINGS_MAP;
    private final Set<Integer> localNameIndices;
    @NotNull
    private final List<Record> records;
    @NotNull
    private final String[] strings;
    @NotNull
    private final StringTableTypes types;

    /* compiled from: JvmNameResolver.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public JvmNameResolver(@NotNull StringTableTypes stringTableTypes, @NotNull String[] strArr) {
        Intrinsics.checkParameterIsNotNull(stringTableTypes, "types");
        Intrinsics.checkParameterIsNotNull(strArr, "strings");
        this.types = stringTableTypes;
        this.strings = strArr;
        List localNameList = this.types.getLocalNameList();
        this.localNameIndices = localNameList.isEmpty() ? SetsKt.emptySet() : CollectionsKt.toSet(localNameList);
        ArrayList arrayList = new ArrayList();
        List<Record> recordList = this.types.getRecordList();
        arrayList.ensureCapacity(recordList.size());
        for (Record record : recordList) {
            Intrinsics.checkExpressionValueIsNotNull(record, "record");
            int range = record.getRange();
            for (int i = 0; i < range; i++) {
                arrayList.add(record);
            }
        }
        arrayList.trimToSize();
        this.records = arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00d5  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00dd  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0122  */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getString(int r13) {
        /*
            r12 = this;
            java.util.List<kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf$StringTableTypes$Record> r0 = r12.records
            java.lang.Object r0 = r0.get(r13)
            kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf$StringTableTypes$Record r0 = (kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Record) r0
            boolean r1 = r0.hasString()
            if (r1 == 0) goto L_0x0013
            java.lang.String r13 = r0.getString()
            goto L_0x003b
        L_0x0013:
            boolean r1 = r0.hasPredefinedIndex()
            if (r1 == 0) goto L_0x0037
            java.util.List<java.lang.String> r1 = PREDEFINED_STRINGS
            java.util.Collection r1 = (java.util.Collection) r1
            int r1 = r1.size()
            int r2 = r0.getPredefinedIndex()
            if (r2 >= 0) goto L_0x0028
            goto L_0x0037
        L_0x0028:
            if (r1 <= r2) goto L_0x0037
            java.util.List<java.lang.String> r13 = PREDEFINED_STRINGS
            int r1 = r0.getPredefinedIndex()
            java.lang.Object r13 = r13.get(r1)
            java.lang.String r13 = (java.lang.String) r13
            goto L_0x003b
        L_0x0037:
            java.lang.String[] r1 = r12.strings
            r13 = r1[r13]
        L_0x003b:
            int r1 = r0.getSubstringIndexCount()
            r2 = 0
            r3 = 2
            r4 = 1
            if (r1 < r3) goto L_0x00a6
            java.util.List r1 = r0.getSubstringIndexList()
            java.lang.Object r5 = r1.get(r2)
            java.lang.Integer r5 = (java.lang.Integer) r5
            java.lang.Object r1 = r1.get(r4)
            java.lang.Integer r1 = (java.lang.Integer) r1
            java.lang.String r6 = "begin"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r6)
            int r6 = r5.intValue()
            int r6 = kotlin.jvm.internal.Intrinsics.compare(r2, r6)
            if (r6 > 0) goto L_0x00a6
            int r6 = r5.intValue()
            java.lang.String r7 = "end"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r7)
            int r7 = r1.intValue()
            int r6 = kotlin.jvm.internal.Intrinsics.compare(r6, r7)
            if (r6 > 0) goto L_0x00a6
            int r6 = r1.intValue()
            int r7 = r13.length()
            int r6 = kotlin.jvm.internal.Intrinsics.compare(r6, r7)
            if (r6 > 0) goto L_0x00a6
            java.lang.String r6 = "string"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r13, r6)
            int r5 = r5.intValue()
            int r1 = r1.intValue()
            if (r13 == 0) goto L_0x009e
            java.lang.String r13 = r13.substring(r5, r1)
            java.lang.String r1 = "(this as java.lang.Strin…ing(startIndex, endIndex)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r13, r1)
            r5 = r13
            goto L_0x00a7
        L_0x009e:
            kotlin.TypeCastException r13 = new kotlin.TypeCastException
            java.lang.String r0 = "null cannot be cast to non-null type java.lang.String"
            r13.<init>(r0)
            throw r13
        L_0x00a6:
            r5 = r13
        L_0x00a7:
            int r13 = r0.getReplaceCharCount()
            if (r13 < r3) goto L_0x00d5
            java.util.List r13 = r0.getReplaceCharList()
            java.lang.Object r1 = r13.get(r2)
            java.lang.Integer r1 = (java.lang.Integer) r1
            java.lang.Object r13 = r13.get(r4)
            java.lang.Integer r13 = (java.lang.Integer) r13
            java.lang.String r2 = "string"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r2)
            int r1 = r1.intValue()
            char r6 = (char) r1
            int r13 = r13.intValue()
            char r7 = (char) r13
            r8 = 0
            r9 = 4
            r10 = 0
            java.lang.String r5 = kotlin.text.StringsKt.replace$default(r5, r6, r7, r8, r9, r10)
            r6 = r5
            goto L_0x00d6
        L_0x00d5:
            r6 = r5
        L_0x00d6:
            kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf$StringTableTypes$Record$Operation r13 = r0.getOperation()
            if (r13 == 0) goto L_0x00dd
            goto L_0x00df
        L_0x00dd:
            kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf$StringTableTypes$Record$Operation r13 = kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Record.Operation.NONE
        L_0x00df:
            int[] r0 = kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmNameResolver.WhenMappings.$EnumSwitchMapping$0
            int r13 = r13.ordinal()
            r13 = r0[r13]
            switch(r13) {
                case 1: goto L_0x0132;
                case 2: goto L_0x0122;
                case 3: goto L_0x00eb;
                default: goto L_0x00ea;
            }
        L_0x00ea:
            goto L_0x0132
        L_0x00eb:
            int r13 = r6.length()
            if (r13 < r3) goto L_0x0110
            java.lang.String r13 = "string"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r13)
            int r13 = r6.length()
            int r13 = r13 - r4
            if (r6 == 0) goto L_0x0108
            java.lang.String r6 = r6.substring(r4, r13)
            java.lang.String r13 = "(this as java.lang.Strin…ing(startIndex, endIndex)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r13)
            r1 = r6
            goto L_0x0111
        L_0x0108:
            kotlin.TypeCastException r13 = new kotlin.TypeCastException
            java.lang.String r0 = "null cannot be cast to non-null type java.lang.String"
            r13.<init>(r0)
            throw r13
        L_0x0110:
            r1 = r6
        L_0x0111:
            java.lang.String r13 = "string"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r13)
            r2 = 36
            r3 = 46
            r4 = 0
            r5 = 4
            r6 = 0
            java.lang.String r6 = kotlin.text.StringsKt.replace$default(r1, r2, r3, r4, r5, r6)
            goto L_0x0132
        L_0x0122:
            java.lang.String r13 = "string"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r13)
            r7 = 36
            r8 = 46
            r9 = 0
            r10 = 4
            r11 = 0
            java.lang.String r6 = kotlin.text.StringsKt.replace$default(r6, r7, r8, r9, r10, r11)
        L_0x0132:
            java.lang.String r13 = "string"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r13)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmNameResolver.getString(int):java.lang.String");
    }

    @NotNull
    public String getQualifiedClassName(int i) {
        return getString(i);
    }

    public boolean isLocalClassName(int i) {
        return this.localNameIndices.contains(Integer.valueOf(i));
    }

    static {
        Iterable<IndexedValue> withIndex = CollectionsKt.withIndex(PREDEFINED_STRINGS);
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(withIndex, 10)), 16));
        for (IndexedValue indexedValue : withIndex) {
            linkedHashMap.put((String) indexedValue.getValue(), Integer.valueOf(indexedValue.getIndex()));
        }
        PREDEFINED_STRINGS_MAP = linkedHashMap;
    }
}
