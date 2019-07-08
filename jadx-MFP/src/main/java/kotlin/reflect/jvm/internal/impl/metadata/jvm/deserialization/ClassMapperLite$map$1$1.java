package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

/* compiled from: ClassMapperLite.kt */
final class ClassMapperLite$map$1$1 extends Lambda implements Function2<String, String, Unit> {
    final /* synthetic */ Map $this_apply;

    ClassMapperLite$map$1$1(Map map) {
        this.$this_apply = map;
        super(2);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((String) obj, (String) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "kotlinSimpleName");
        Intrinsics.checkParameterIsNotNull(str2, "javaInternalName");
        Map map = this.$this_apply;
        StringBuilder sb = new StringBuilder();
        sb.append("kotlin/");
        sb.append(str);
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append('L');
        sb3.append(str2);
        sb3.append(';');
        map.put(sb2, sb3.toString());
    }
}
