package kotlin.reflect.jvm.internal.impl.utils;

import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: numbers.kt */
public final class NumbersKt {
    @NotNull
    public static final NumberWithRadix extractRadix(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        if (StringsKt.startsWith$default(str, "0x", false, 2, null) || StringsKt.startsWith$default(str, "0X", false, 2, null)) {
            String substring = str.substring(2);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
            return new NumberWithRadix(substring, 16);
        } else if (!StringsKt.startsWith$default(str, "0b", false, 2, null) && !StringsKt.startsWith$default(str, "0B", false, 2, null)) {
            return new NumberWithRadix(str, 10);
        } else {
            String substring2 = str.substring(2);
            Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.String).substring(startIndex)");
            return new NumberWithRadix(substring2, 2);
        }
    }
}
