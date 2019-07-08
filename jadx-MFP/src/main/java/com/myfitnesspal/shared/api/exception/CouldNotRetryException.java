package com.myfitnesspal.shared.api.exception;

import com.myfitnesspal.shared.api.ApiException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/myfitnesspal/shared/api/exception/CouldNotRetryException;", "Lcom/myfitnesspal/shared/api/ApiException;", "cause", "", "statusCode", "", "(Ljava/lang/Throwable;I)V", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: CouldNotRetryException.kt */
public final class CouldNotRetryException extends ApiException {
    public CouldNotRetryException(@NotNull Throwable th, int i) {
        Intrinsics.checkParameterIsNotNull(th, "cause");
        super(th, i);
    }
}
