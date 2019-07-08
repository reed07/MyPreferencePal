package kotlin.reflect.jvm.internal;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.KClassImpl.Data;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "Lkotlin/reflect/jvm/internal/KCallableImpl;", "T", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: KClassImpl.kt */
final class KClassImpl$Data$declaredStaticMembers$2 extends Lambda implements Function0<Collection<? extends KCallableImpl<?>>> {
    final /* synthetic */ Data this$0;

    KClassImpl$Data$declaredStaticMembers$2(Data data) {
        this.this$0 = data;
        super(0);
    }

    @NotNull
    public final Collection<KCallableImpl<?>> invoke() {
        return KClassImpl.this.getMembers(KClassImpl.this.getStaticScope$kotlin_reflect_api(), MemberBelonginess.DECLARED);
    }
}
