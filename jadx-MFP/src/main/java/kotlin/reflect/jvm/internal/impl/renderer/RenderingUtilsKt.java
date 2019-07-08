package kotlin.reflect.jvm.internal.impl.renderer;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;

/* compiled from: RenderingUtils.kt */
public final class RenderingUtilsKt {
    @NotNull
    public static final String render(@NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(name, "receiver$0");
        if (shouldBeEscaped(name)) {
            StringBuilder sb = new StringBuilder();
            String asString = name.asString();
            Intrinsics.checkExpressionValueIsNotNull(asString, "asString()");
            StringBuilder sb2 = new StringBuilder();
            sb2.append(String.valueOf('`'));
            sb2.append(asString);
            sb.append(sb2.toString());
            sb.append('`');
            return sb.toString();
        }
        String asString2 = name.asString();
        Intrinsics.checkExpressionValueIsNotNull(asString2, "asString()");
        return asString2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003c, code lost:
        if (r5 != false) goto L_0x003e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final boolean shouldBeEscaped(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.name.Name r5) {
        /*
            boolean r0 = r5.isSpecial()
            r1 = 0
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            java.lang.String r5 = r5.asString()
            java.lang.String r0 = "asString()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r0)
            java.util.Set<java.lang.String> r0 = kotlin.reflect.jvm.internal.impl.renderer.KeywordStringsGenerated.KEYWORDS
            boolean r0 = r0.contains(r5)
            r2 = 1
            if (r0 != 0) goto L_0x003e
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            r0 = 0
        L_0x001d:
            int r3 = r5.length()
            if (r0 >= r3) goto L_0x003b
            char r3 = r5.charAt(r0)
            boolean r4 = java.lang.Character.isLetterOrDigit(r3)
            if (r4 != 0) goto L_0x0033
            r4 = 95
            if (r3 == r4) goto L_0x0033
            r3 = 1
            goto L_0x0034
        L_0x0033:
            r3 = 0
        L_0x0034:
            if (r3 == 0) goto L_0x0038
            r5 = 1
            goto L_0x003c
        L_0x0038:
            int r0 = r0 + 1
            goto L_0x001d
        L_0x003b:
            r5 = 0
        L_0x003c:
            if (r5 == 0) goto L_0x003f
        L_0x003e:
            r1 = 1
        L_0x003f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.renderer.RenderingUtilsKt.shouldBeEscaped(kotlin.reflect.jvm.internal.impl.name.Name):boolean");
    }

    @NotNull
    public static final String render(@NotNull FqNameUnsafe fqNameUnsafe) {
        Intrinsics.checkParameterIsNotNull(fqNameUnsafe, "receiver$0");
        List pathSegments = fqNameUnsafe.pathSegments();
        Intrinsics.checkExpressionValueIsNotNull(pathSegments, "pathSegments()");
        return renderFqName(pathSegments);
    }

    @NotNull
    public static final String renderFqName(@NotNull List<Name> list) {
        Intrinsics.checkParameterIsNotNull(list, "pathSegments");
        StringBuilder sb = new StringBuilder();
        for (Name name : list) {
            if (sb.length() > 0) {
                sb.append(".");
            }
            sb.append(render(name));
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }
}
