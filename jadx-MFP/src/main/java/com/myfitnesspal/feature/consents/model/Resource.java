package com.myfitnesspal.feature.consents.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002:\u0003\u0004\u0005\u0006B\u0007\b\u0002¢\u0006\u0002\u0010\u0003\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lcom/myfitnesspal/feature/consents/model/Resource;", "T", "", "()V", "Error", "Loading", "Success", "Lcom/myfitnesspal/feature/consents/model/Resource$Success;", "Lcom/myfitnesspal/feature/consents/model/Resource$Loading;", "Lcom/myfitnesspal/feature/consents/model/Resource$Error;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: Response.kt */
public abstract class Resource<T> {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/myfitnesspal/feature/consents/model/Resource$Error;", "T", "Lcom/myfitnesspal/feature/consents/model/Resource;", "throwable", "", "(Ljava/lang/Throwable;)V", "getThrowable", "()Ljava/lang/Throwable;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: Response.kt */
    public static final class Error<T> extends Resource<T> {
        @NotNull
        private final Throwable throwable;

        public Error(@NotNull Throwable th) {
            Intrinsics.checkParameterIsNotNull(th, "throwable");
            super(null);
            this.throwable = th;
        }

        @NotNull
        public final Throwable getThrowable() {
            return this.throwable;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/myfitnesspal/feature/consents/model/Resource$Loading;", "T", "Lcom/myfitnesspal/feature/consents/model/Resource;", "()V", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: Response.kt */
    public static final class Loading<T> extends Resource<T> {
        public Loading() {
            super(null);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00018\u0001¢\u0006\u0002\u0010\u0004R\u0015\u0010\u0003\u001a\u0004\u0018\u00018\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lcom/myfitnesspal/feature/consents/model/Resource$Success;", "T", "Lcom/myfitnesspal/feature/consents/model/Resource;", "data", "(Ljava/lang/Object;)V", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: Response.kt */
    public static final class Success<T> extends Resource<T> {
        @Nullable
        private final T data;

        public Success(@Nullable T t) {
            super(null);
            this.data = t;
        }

        @Nullable
        public final T getData() {
            return this.data;
        }
    }

    private Resource() {
    }

    public /* synthetic */ Resource(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
