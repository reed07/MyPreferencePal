package kotlin.reflect.jvm.internal;

import java.lang.reflect.Field;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0006\b\u0002\u0010\u0004 \u0001H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Ljava/lang/reflect/Field;", "D", "E", "R", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: KProperty2Impl.kt */
final class KProperty2Impl$delegateField$1 extends Lambda implements Function0<Field> {
    final /* synthetic */ KProperty2Impl this$0;

    KProperty2Impl$delegateField$1(KProperty2Impl kProperty2Impl) {
        this.this$0 = kProperty2Impl;
        super(0);
    }

    @Nullable
    public final Field invoke() {
        return this.this$0.computeDelegateField();
    }
}
