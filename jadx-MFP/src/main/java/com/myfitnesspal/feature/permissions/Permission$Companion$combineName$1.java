package com.myfitnesspal.feature.permissions;

import io.reactivex.functions.Function;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "permission", "Lcom/myfitnesspal/feature/permissions/Permission;", "apply"}, k = 3, mv = {1, 1, 13})
/* compiled from: Permission.kt */
final class Permission$Companion$combineName$1<T, R> implements Function<T, R> {
    public static final Permission$Companion$combineName$1 INSTANCE = new Permission$Companion$combineName$1();

    Permission$Companion$combineName$1() {
    }

    @NotNull
    public final String apply(@Nullable Permission permission) {
        if (permission != null) {
            String name = permission.getName();
            if (name != null) {
                return name;
            }
        }
        return "";
    }
}
