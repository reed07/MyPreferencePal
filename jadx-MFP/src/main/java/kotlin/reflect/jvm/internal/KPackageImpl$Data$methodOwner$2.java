package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0012\u0012\u0002\b\u0003 \u0002*\b\u0012\u0002\b\u0003\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Ljava/lang/Class;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: KPackageImpl.kt */
final class KPackageImpl$Data$methodOwner$2 extends Lambda implements Function0<Class<?>> {
    final /* synthetic */ Data this$0;

    KPackageImpl$Data$methodOwner$2(Data data) {
        this.this$0 = data;
        super(0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x0017  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Class<?> invoke() {
        /*
            r7 = this;
            kotlin.reflect.jvm.internal.KPackageImpl$Data r0 = r7.this$0
            kotlin.reflect.jvm.internal.components.ReflectKotlinClass r0 = r0.getKotlinClass()
            if (r0 == 0) goto L_0x0013
            kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader r0 = r0.getClassHeader()
            if (r0 == 0) goto L_0x0013
            java.lang.String r0 = r0.getMultifileClassName()
            goto L_0x0014
        L_0x0013:
            r0 = 0
        L_0x0014:
            r1 = r0
            if (r1 == 0) goto L_0x0041
            r0 = r1
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            int r0 = r0.length()
            if (r0 <= 0) goto L_0x0022
            r0 = 1
            goto L_0x0023
        L_0x0022:
            r0 = 0
        L_0x0023:
            if (r0 == 0) goto L_0x0041
            kotlin.reflect.jvm.internal.KPackageImpl$Data r0 = r7.this$0
            kotlin.reflect.jvm.internal.KPackageImpl r0 = kotlin.reflect.jvm.internal.KPackageImpl.this
            java.lang.Class r0 = r0.getJClass()
            java.lang.ClassLoader r0 = r0.getClassLoader()
            r2 = 47
            r3 = 46
            r4 = 0
            r5 = 4
            r6 = 0
            java.lang.String r1 = kotlin.text.StringsKt.replace$default(r1, r2, r3, r4, r5, r6)
            java.lang.Class r0 = r0.loadClass(r1)
            goto L_0x0049
        L_0x0041:
            kotlin.reflect.jvm.internal.KPackageImpl$Data r0 = r7.this$0
            kotlin.reflect.jvm.internal.KPackageImpl r0 = kotlin.reflect.jvm.internal.KPackageImpl.this
            java.lang.Class r0 = r0.getJClass()
        L_0x0049:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.KPackageImpl$Data$methodOwner$2.invoke():java.lang.Class");
    }
}
