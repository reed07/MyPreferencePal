package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.KClassImpl.Data;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "T", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: KClassImpl.kt */
final class KClassImpl$Data$simpleName$2 extends Lambda implements Function0<String> {
    final /* synthetic */ Data this$0;

    KClassImpl$Data$simpleName$2(Data data) {
        this.this$0 = data;
        super(0);
    }

    @Nullable
    public final String invoke() {
        String str;
        if (KClassImpl.this.getJClass().isAnonymousClass()) {
            return null;
        }
        ClassId access$getClassId$p = KClassImpl.this.getClassId();
        if (access$getClassId$p.isLocal()) {
            Data data = this.this$0;
            str = data.calculateLocalClassName(KClassImpl.this.getJClass());
        } else {
            str = access$getClassId$p.getShortClassName().asString();
            Intrinsics.checkExpressionValueIsNotNull(str, "classId.shortClassName.asString()");
        }
        return str;
    }
}
