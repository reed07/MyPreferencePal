package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\u001a\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0000\u001a\u0018\u0010\u0004\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0007H\u0000\u001a,\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\u00012\b\b\u0002\u0010\n\u001a\u00020\u0001H\u0000\u001a,\u0010\u0004\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u000b2\b\b\u0002\u0010\t\u001a\u00020\u000b2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0000\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\f"}, d2 = {"AVAILABLE_PROCESSORS", "", "getAVAILABLE_PROCESSORS", "()I", "systemProp", "", "propertyName", "", "defaultValue", "minValue", "maxValue", "", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 13})
/* compiled from: SystemProps.kt */
public final class SystemPropsKt {
    private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    public static final int getAVAILABLE_PROCESSORS() {
        return AVAILABLE_PROCESSORS;
    }

    @Nullable
    public static final String systemProp(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "propertyName");
        try {
            return System.getProperty(str);
        } catch (SecurityException unused) {
            return null;
        }
    }

    public static final boolean systemProp(@NotNull String str, boolean z) {
        Intrinsics.checkParameterIsNotNull(str, "propertyName");
        try {
            String property = System.getProperty(str);
            return property != null ? Boolean.parseBoolean(property) : z;
        } catch (SecurityException unused) {
            return z;
        }
    }

    public static /* synthetic */ int systemProp$default(String str, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i2 = 1;
        }
        if ((i4 & 8) != 0) {
            i3 = Integer.MAX_VALUE;
        }
        return systemProp(str, i, i2, i3);
    }

    public static final int systemProp(@NotNull String str, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(str, "propertyName");
        return (int) systemProp(str, (long) i, (long) i2, (long) i3);
    }

    public static /* synthetic */ long systemProp$default(String str, long j, long j2, long j3, int i, Object obj) {
        return systemProp(str, j, (i & 4) != 0 ? 1 : j2, (i & 8) != 0 ? Long.MAX_VALUE : j3);
    }

    public static final long systemProp(@NotNull String str, long j, long j2, long j3) {
        Intrinsics.checkParameterIsNotNull(str, "propertyName");
        String systemProp = systemProp(str);
        if (systemProp == null) {
            return j;
        }
        Long longOrNull = StringsKt.toLongOrNull(systemProp);
        if (longOrNull != null) {
            long longValue = longOrNull.longValue();
            if (j2 <= longValue && j3 >= longValue) {
                return longValue;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("System property '");
            sb.append(str);
            sb.append("' should be in range ");
            sb.append(j2);
            sb.append("..");
            sb.append(j3);
            sb.append(", but is '");
            sb.append(longValue);
            sb.append('\'');
            throw new IllegalStateException(sb.toString().toString());
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("System property '");
        sb2.append(str);
        sb2.append("' has unrecognized value '");
        sb2.append(systemProp);
        sb2.append('\'');
        throw new IllegalStateException(sb2.toString().toString());
    }
}
