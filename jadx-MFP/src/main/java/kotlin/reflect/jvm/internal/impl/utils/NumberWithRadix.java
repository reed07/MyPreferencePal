package kotlin.reflect.jvm.internal.impl.utils;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: numbers.kt */
public final class NumberWithRadix {
    @NotNull
    private final String number;
    private final int radix;

    @NotNull
    public final String component1() {
        return this.number;
    }

    public final int component2() {
        return this.radix;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof NumberWithRadix) {
                NumberWithRadix numberWithRadix = (NumberWithRadix) obj;
                if (Intrinsics.areEqual((Object) this.number, (Object) numberWithRadix.number)) {
                    if (this.radix == numberWithRadix.radix) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        String str = this.number;
        return ((str != null ? str.hashCode() : 0) * 31) + this.radix;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NumberWithRadix(number=");
        sb.append(this.number);
        sb.append(", radix=");
        sb.append(this.radix);
        sb.append(")");
        return sb.toString();
    }

    public NumberWithRadix(@NotNull String str, int i) {
        Intrinsics.checkParameterIsNotNull(str, "number");
        this.number = str;
        this.radix = i;
    }
}
