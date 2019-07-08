package kotlin.reflect.jvm.internal.impl.util.capitalizeDecapitalize;

import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: capitalizeDecapitalize.kt */
public final class CapitalizeDecapitalizeKt {
    @NotNull
    public static final String decapitalizeSmart(@NotNull String str, boolean z) {
        Object obj;
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        CapitalizeDecapitalizeKt$decapitalizeSmart$1 capitalizeDecapitalizeKt$decapitalizeSmart$1 = new CapitalizeDecapitalizeKt$decapitalizeSmart$1(str, z);
        CharSequence charSequence = str;
        if ((charSequence.length() == 0) || !capitalizeDecapitalizeKt$decapitalizeSmart$1.invoke(0)) {
            return str;
        }
        if (str.length() == 1 || !capitalizeDecapitalizeKt$decapitalizeSmart$1.invoke(1)) {
            return z ? decapitalizeAsciiOnly(str) : StringsKt.decapitalize(str);
        }
        CapitalizeDecapitalizeKt$decapitalizeSmart$2 capitalizeDecapitalizeKt$decapitalizeSmart$2 = new CapitalizeDecapitalizeKt$decapitalizeSmart$2(z);
        Iterator it = StringsKt.getIndices(charSequence).iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (!capitalizeDecapitalizeKt$decapitalizeSmart$1.invoke(((Number) obj).intValue())) {
                break;
            }
        }
        Integer num = (Integer) obj;
        if (num == null) {
            return capitalizeDecapitalizeKt$decapitalizeSmart$2.invoke(str);
        }
        int intValue = num.intValue() - 1;
        StringBuilder sb = new StringBuilder();
        String substring = str.substring(0, intValue);
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        sb.append(capitalizeDecapitalizeKt$decapitalizeSmart$2.invoke(substring));
        String substring2 = str.substring(intValue);
        Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.String).substring(startIndex)");
        sb.append(substring2);
        return sb.toString();
    }

    @NotNull
    public static final String capitalizeAsciiOnly(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        if (str.length() == 0) {
            return str;
        }
        char charAt = str.charAt(0);
        if ('a' <= charAt && 'z' >= charAt) {
            char upperCase = Character.toUpperCase(charAt);
            String substring = str.substring(1);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
            StringBuilder sb = new StringBuilder();
            sb.append(String.valueOf(upperCase));
            sb.append(substring);
            str = sb.toString();
        }
        return str;
    }

    @NotNull
    public static final String decapitalizeAsciiOnly(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        if (str.length() == 0) {
            return str;
        }
        char charAt = str.charAt(0);
        if ('A' <= charAt && 'Z' >= charAt) {
            char lowerCase = Character.toLowerCase(charAt);
            String substring = str.substring(1);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
            StringBuilder sb = new StringBuilder();
            sb.append(String.valueOf(lowerCase));
            sb.append(substring);
            str = sb.toString();
        }
        return str;
    }

    @NotNull
    public static final String toLowerCaseAsciiOnly(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        StringBuilder sb = new StringBuilder(str.length());
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if ('A' <= charAt && 'Z' >= charAt) {
                charAt = Character.toLowerCase(charAt);
            }
            sb.append(charAt);
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "builder.toString()");
        return sb2;
    }
}
