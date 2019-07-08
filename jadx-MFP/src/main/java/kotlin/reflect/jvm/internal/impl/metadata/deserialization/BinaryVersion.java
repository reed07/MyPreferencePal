package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: BinaryVersion.kt */
public abstract class BinaryVersion {
    public static final Companion Companion = new Companion(null);
    private final int major;
    private final int minor;
    private final int[] numbers;
    private final int patch;
    @NotNull
    private final List<Integer> rest;

    /* compiled from: BinaryVersion.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BinaryVersion(@NotNull int... iArr) {
        Intrinsics.checkParameterIsNotNull(iArr, "numbers");
        this.numbers = iArr;
        Integer orNull = ArraysKt.getOrNull(this.numbers, 0);
        int i = -1;
        this.major = orNull != null ? orNull.intValue() : -1;
        Integer orNull2 = ArraysKt.getOrNull(this.numbers, 1);
        this.minor = orNull2 != null ? orNull2.intValue() : -1;
        Integer orNull3 = ArraysKt.getOrNull(this.numbers, 2);
        if (orNull3 != null) {
            i = orNull3.intValue();
        }
        this.patch = i;
        int[] iArr2 = this.numbers;
        this.rest = iArr2.length > 3 ? CollectionsKt.toList(ArraysKt.asList(iArr2).subList(3, this.numbers.length)) : CollectionsKt.emptyList();
    }

    public final int getMajor() {
        return this.major;
    }

    public final int getMinor() {
        return this.minor;
    }

    @NotNull
    public final int[] toArray() {
        return this.numbers;
    }

    /* access modifiers changed from: protected */
    public final boolean isCompatibleTo(@NotNull BinaryVersion binaryVersion) {
        Intrinsics.checkParameterIsNotNull(binaryVersion, "ourVersion");
        int i = this.major;
        if (i == 0) {
            return binaryVersion.major == 0 && this.minor == binaryVersion.minor;
        }
        if (i != binaryVersion.major || this.minor > binaryVersion.minor) {
            return false;
        }
        return true;
    }

    public final boolean isAtLeast(@NotNull BinaryVersion binaryVersion) {
        Intrinsics.checkParameterIsNotNull(binaryVersion, "version");
        return isAtLeast(binaryVersion.major, binaryVersion.minor, binaryVersion.patch);
    }

    public final boolean isAtLeast(int i, int i2, int i3) {
        int i4 = this.major;
        boolean z = true;
        if (i4 > i) {
            return true;
        }
        if (i4 < i) {
            return false;
        }
        int i5 = this.minor;
        if (i5 > i2) {
            return true;
        }
        if (i5 < i2) {
            return false;
        }
        if (this.patch < i3) {
            z = false;
        }
        return z;
    }

    @NotNull
    public String toString() {
        int[] array = toArray();
        ArrayList arrayList = new ArrayList();
        int length = array.length;
        for (int i = 0; i < length; i++) {
            int i2 = array[i];
            if (!(i2 != -1)) {
                break;
            }
            arrayList.add(Integer.valueOf(i2));
        }
        List list = arrayList;
        return list.isEmpty() ? "unknown" : CollectionsKt.joinToString$default(list, ".", null, null, 0, null, null, 62, null);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj != null && Intrinsics.areEqual((Object) getClass(), (Object) obj.getClass())) {
            BinaryVersion binaryVersion = (BinaryVersion) obj;
            if (this.major == binaryVersion.major && this.minor == binaryVersion.minor && this.patch == binaryVersion.patch && Intrinsics.areEqual((Object) this.rest, (Object) binaryVersion.rest)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = this.major;
        int i2 = i + (i * 31) + this.minor;
        int i3 = i2 + (i2 * 31) + this.patch;
        return i3 + (i3 * 31) + this.rest.hashCode();
    }
}
