package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.components.ReflectKotlinClass;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lkotlin/reflect/jvm/internal/components/ReflectKotlinClass;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: KPackageImpl.kt */
final class KPackageImpl$Data$kotlinClass$2 extends Lambda implements Function0<ReflectKotlinClass> {
    final /* synthetic */ Data this$0;

    KPackageImpl$Data$kotlinClass$2(Data data) {
        this.this$0 = data;
        super(0);
    }

    @Nullable
    public final ReflectKotlinClass invoke() {
        return ReflectKotlinClass.Factory.create(KPackageImpl.this.getJClass());
    }
}
