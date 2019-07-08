package kotlin.reflect.jvm.internal.structure;

import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "method", "Ljava/lang/reflect/Method;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: ReflectJavaClass.kt */
final class ReflectJavaClass$methods$1 extends Lambda implements Function1<Method, Boolean> {
    final /* synthetic */ ReflectJavaClass this$0;

    ReflectJavaClass$methods$1(ReflectJavaClass reflectJavaClass) {
        this.this$0 = reflectJavaClass;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return Boolean.valueOf(invoke((Method) obj));
    }

    public final boolean invoke(Method method) {
        Intrinsics.checkExpressionValueIsNotNull(method, Param.METHOD);
        if (method.isSynthetic()) {
            return false;
        }
        if (!this.this$0.isEnum() || !this.this$0.isEnumValuesOrValueOf(method)) {
            return true;
        }
        return false;
    }
}
