package com.myfitnesspal.shared.api.exception;

import com.myfitnesspal.shared.api.ApiException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/myfitnesspal/shared/api/exception/DuplicateResourceException;", "Lcom/myfitnesspal/shared/api/ApiException;", "detailMessage", "", "(Ljava/lang/String;)V", "message", "body", "(Ljava/lang/String;Ljava/lang/String;)V", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: DuplicateResourceException.kt */
public final class DuplicateResourceException extends ApiException {
    public DuplicateResourceException(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "detailMessage");
        super(str, 409);
    }

    public DuplicateResourceException(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        Intrinsics.checkParameterIsNotNull(str2, "body");
        super(str, 409, str2);
    }
}
