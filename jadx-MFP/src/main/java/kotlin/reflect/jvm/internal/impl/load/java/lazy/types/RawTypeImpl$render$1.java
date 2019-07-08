package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import com.brightcove.player.event.EventType;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: RawType.kt */
final class RawTypeImpl$render$1 extends Lambda implements Function2<String, String, Boolean> {
    public static final RawTypeImpl$render$1 INSTANCE = new RawTypeImpl$render$1();

    RawTypeImpl$render$1() {
        super(2);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return Boolean.valueOf(invoke((String) obj, (String) obj2));
    }

    public final boolean invoke(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "first");
        Intrinsics.checkParameterIsNotNull(str2, "second");
        return Intrinsics.areEqual((Object) str, (Object) StringsKt.removePrefix(str2, (CharSequence) "out ")) || Intrinsics.areEqual((Object) str2, (Object) EventType.ANY);
    }
}
