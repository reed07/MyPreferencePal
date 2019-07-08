package com.myfitnesspal.feature.permissions;

import io.reactivex.functions.BiConsumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0018\u0010\u0002\u001a\u0014 \u0005*\n\u0018\u00010\u0003j\u0004\u0018\u0001`\u00040\u0003j\u0002`\u00042\u000e\u0010\u0006\u001a\n \u0005*\u0004\u0018\u00010\u00070\u0007H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "s", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "kotlin.jvm.PlatformType", "s2", "", "accept"}, k = 3, mv = {1, 1, 13})
/* compiled from: Permission.kt */
final class Permission$Companion$combineName$2<T1, T2> implements BiConsumer<U, T> {
    public static final Permission$Companion$combineName$2 INSTANCE = new Permission$Companion$combineName$2();

    Permission$Companion$combineName$2() {
    }

    public final void accept(StringBuilder sb, String str) {
        Intrinsics.checkExpressionValueIsNotNull(sb, "s");
        if (sb.length() == 0) {
            sb.append(str);
            return;
        }
        sb.append(", ");
        sb.append(str);
    }
}
