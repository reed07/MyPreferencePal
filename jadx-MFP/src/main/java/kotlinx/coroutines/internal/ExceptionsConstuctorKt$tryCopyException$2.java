package kotlinx.coroutines.internal;

import com.facebook.ads.internal.j.e;
import java.lang.reflect.Constructor;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Result.Companion;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "E", "", "e", "invoke", "(Ljava/lang/Throwable;)Ljava/lang/Throwable;"}, k = 3, mv = {1, 1, 13})
/* compiled from: ExceptionsConstuctor.kt */
final class ExceptionsConstuctorKt$tryCopyException$2 extends Lambda implements Function1<Throwable, E> {
    final /* synthetic */ Constructor $constructor;

    ExceptionsConstuctorKt$tryCopyException$2(Constructor constructor) {
        this.$constructor = constructor;
        super(1);
    }

    @Nullable
    public final E invoke(@NotNull Throwable th) {
        E e;
        Intrinsics.checkParameterIsNotNull(th, e.a);
        try {
            Companion companion = Result.Companion;
            Object newInstance = this.$constructor.newInstance(new Object[]{th});
            if (newInstance != null) {
                e = Result.m6constructorimpl((Throwable) newInstance);
                if (Result.m12isFailureimpl(e)) {
                    e = null;
                }
                return (Throwable) e;
            }
            throw new TypeCastException("null cannot be cast to non-null type E");
        } catch (Throwable th2) {
            Companion companion2 = Result.Companion;
            e = Result.m6constructorimpl(ResultKt.createFailure(th2));
        }
    }
}
