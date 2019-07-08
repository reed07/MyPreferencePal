package kotlin.reflect.jvm.internal;

import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002 \u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lkotlin/reflect/jvm/internal/KTypeImpl;", "R", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: KCallableImpl.kt */
final class KCallableImpl$_returnType$1 extends Lambda implements Function0<KTypeImpl> {
    final /* synthetic */ KCallableImpl this$0;

    KCallableImpl$_returnType$1(KCallableImpl kCallableImpl) {
        this.this$0 = kCallableImpl;
        super(0);
    }

    @NotNull
    public final KTypeImpl invoke() {
        KotlinType returnType = this.this$0.getDescriptor().getReturnType();
        if (returnType == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(returnType, "descriptor.returnType!!");
        return new KTypeImpl(returnType, new Function0<Type>(this) {
            final /* synthetic */ KCallableImpl$_returnType$1 this$0;

            {
                this.this$0 = r1;
            }

            @NotNull
            public final Type invoke() {
                Type access$extractContinuationArgument = this.this$0.this$0.extractContinuationArgument();
                return access$extractContinuationArgument != null ? access$extractContinuationArgument : this.this$0.this$0.getCaller().getReturnType();
            }
        });
    }
}
