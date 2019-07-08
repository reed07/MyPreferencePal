package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.jetbrains.annotations.NotNull;

/* compiled from: RawType.kt */
final class RawTypeImpl$render$3 extends Lambda implements Function2<String, String, String> {
    public static final RawTypeImpl$render$3 INSTANCE = new RawTypeImpl$render$3();

    RawTypeImpl$render$3() {
        super(2);
    }

    @NotNull
    public final String invoke(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str2, "newArgs");
        if (!StringsKt.contains$default((CharSequence) str, (char) Typography.less, false, 2, (Object) null)) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(StringsKt.substringBefore$default(str, (char) Typography.less, (String) null, 2, (Object) null));
        sb.append(Typography.less);
        sb.append(str2);
        sb.append(Typography.greater);
        sb.append(StringsKt.substringAfterLast$default(str, (char) Typography.greater, (String) null, 2, (Object) null));
        return sb.toString();
    }
}
