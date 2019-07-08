package kotlin.reflect.jvm.internal.impl.load.kotlin.header;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmBytecodeBinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: KotlinClassHeader.kt */
public final class KotlinClassHeader {
    @NotNull
    private final JvmBytecodeBinaryVersion bytecodeVersion;
    @Nullable
    private final String[] data;
    private final int extraInt;
    @Nullable
    private final String extraString;
    @Nullable
    private final String[] incompatibleData;
    @NotNull
    private final Kind kind;
    @NotNull
    private final JvmMetadataVersion metadataVersion;
    @Nullable
    private final String packageName;
    @Nullable
    private final String[] strings;

    /* compiled from: KotlinClassHeader.kt */
    public enum Kind {
        UNKNOWN(0),
        CLASS(1),
        FILE_FACADE(2),
        SYNTHETIC_CLASS(3),
        MULTIFILE_CLASS(4),
        MULTIFILE_CLASS_PART(5);
        
        public static final Companion Companion = null;
        /* access modifiers changed from: private */
        public static final Map<Integer, Kind> entryById = null;
        private final int id;

        /* compiled from: KotlinClassHeader.kt */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @JvmStatic
            @NotNull
            public final Kind getById(int i) {
                Kind kind = (Kind) Kind.entryById.get(Integer.valueOf(i));
                return kind != null ? kind : Kind.UNKNOWN;
            }
        }

        @JvmStatic
        @NotNull
        public static final Kind getById(int i) {
            return Companion.getById(i);
        }

        protected Kind(int i) {
            this.id = i;
        }

        static {
            int i;
            Companion = new Companion(null);
            Kind[] values = values();
            Map<Integer, Kind> linkedHashMap = new LinkedHashMap<>(RangesKt.coerceAtLeast(MapsKt.mapCapacity(values.length), 16));
            for (Kind kind : values) {
                linkedHashMap.put(Integer.valueOf(kind.id), kind);
            }
            entryById = linkedHashMap;
        }
    }

    public KotlinClassHeader(@NotNull Kind kind2, @NotNull JvmMetadataVersion jvmMetadataVersion, @NotNull JvmBytecodeBinaryVersion jvmBytecodeBinaryVersion, @Nullable String[] strArr, @Nullable String[] strArr2, @Nullable String[] strArr3, @Nullable String str, int i, @Nullable String str2) {
        Intrinsics.checkParameterIsNotNull(kind2, "kind");
        Intrinsics.checkParameterIsNotNull(jvmMetadataVersion, "metadataVersion");
        Intrinsics.checkParameterIsNotNull(jvmBytecodeBinaryVersion, "bytecodeVersion");
        this.kind = kind2;
        this.metadataVersion = jvmMetadataVersion;
        this.bytecodeVersion = jvmBytecodeBinaryVersion;
        this.data = strArr;
        this.incompatibleData = strArr2;
        this.strings = strArr3;
        this.extraString = str;
        this.extraInt = i;
        this.packageName = str2;
    }

    @NotNull
    public final Kind getKind() {
        return this.kind;
    }

    @NotNull
    public final JvmMetadataVersion getMetadataVersion() {
        return this.metadataVersion;
    }

    @Nullable
    public final String[] getData() {
        return this.data;
    }

    @Nullable
    public final String[] getIncompatibleData() {
        return this.incompatibleData;
    }

    @Nullable
    public final String[] getStrings() {
        return this.strings;
    }

    @Nullable
    public final String getMultifileClassName() {
        String str = this.extraString;
        if (this.kind == Kind.MULTIFILE_CLASS_PART) {
            return str;
        }
        return null;
    }

    @NotNull
    public final List<String> getMultifilePartNames() {
        String[] strArr = this.data;
        List<String> list = null;
        if (!(this.kind == Kind.MULTIFILE_CLASS)) {
            strArr = null;
        }
        if (strArr != null) {
            list = ArraysKt.asList((T[]) strArr);
        }
        return list != null ? list : CollectionsKt.emptyList();
    }

    public final boolean isPreRelease() {
        return (this.extraInt & 2) != 0;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.kind);
        sb.append(" version=");
        sb.append(this.metadataVersion);
        return sb.toString();
    }
}
