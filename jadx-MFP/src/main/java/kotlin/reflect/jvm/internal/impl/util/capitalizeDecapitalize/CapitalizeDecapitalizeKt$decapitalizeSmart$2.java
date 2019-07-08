package kotlin.reflect.jvm.internal.impl.util.capitalizeDecapitalize;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

/* compiled from: capitalizeDecapitalize.kt */
final class CapitalizeDecapitalizeKt$decapitalizeSmart$2 extends Lambda implements Function1<String, String> {
    final /* synthetic */ boolean $asciiOnly;

    CapitalizeDecapitalizeKt$decapitalizeSmart$2(boolean z) {
        this.$asciiOnly = z;
        super(1);
    }

    @NotNull
    public final String invoke(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "string");
        if (this.$asciiOnly) {
            return CapitalizeDecapitalizeKt.toLowerCaseAsciiOnly(str);
        }
        String lowerCase = str.toLowerCase();
        Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
        return lowerCase;
    }
}
