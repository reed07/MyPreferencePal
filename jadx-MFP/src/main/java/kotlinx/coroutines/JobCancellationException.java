package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00060\u0001j\u0002`\u0002B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0002J\b\u0010\u000e\u001a\u00020\u0006H\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0004H\u0016R\u0010\u0010\u0007\u001a\u00020\b8\u0000X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lkotlinx/coroutines/JobCancellationException;", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "message", "", "cause", "", "job", "Lkotlinx/coroutines/Job;", "(Ljava/lang/String;Ljava/lang/Throwable;Lkotlinx/coroutines/Job;)V", "equals", "", "other", "", "fillInStackTrace", "hashCode", "", "toString", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: Exceptions.kt */
public final class JobCancellationException extends CancellationException {
    @NotNull
    @JvmField
    public final Job job;

    public JobCancellationException(@NotNull String str, @Nullable Throwable th, @NotNull Job job2) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        Intrinsics.checkParameterIsNotNull(job2, "job");
        super(str);
        this.job = job2;
        if (th != null) {
            initCause(th);
        }
    }

    @NotNull
    public Throwable fillInStackTrace() {
        if (!DebugKt.DEBUG) {
            return this;
        }
        Throwable fillInStackTrace = super.fillInStackTrace();
        Intrinsics.checkExpressionValueIsNotNull(fillInStackTrace, "super.fillInStackTrace()");
        return fillInStackTrace;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("; job=");
        sb.append(this.job);
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002f, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3.getCause(), (java.lang.Object) getCause()) != false) goto L_0x0034;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            r0 = r2
            kotlinx.coroutines.JobCancellationException r0 = (kotlinx.coroutines.JobCancellationException) r0
            if (r3 == r0) goto L_0x0034
            boolean r0 = r3 instanceof kotlinx.coroutines.JobCancellationException
            if (r0 == 0) goto L_0x0032
            kotlinx.coroutines.JobCancellationException r3 = (kotlinx.coroutines.JobCancellationException) r3
            java.lang.String r0 = r3.getMessage()
            java.lang.String r1 = r2.getMessage()
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0032
            kotlinx.coroutines.Job r0 = r3.job
            kotlinx.coroutines.Job r1 = r2.job
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0032
            java.lang.Throwable r3 = r3.getCause()
            java.lang.Throwable r0 = r2.getCause()
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r0)
            if (r3 == 0) goto L_0x0032
            goto L_0x0034
        L_0x0032:
            r3 = 0
            goto L_0x0035
        L_0x0034:
            r3 = 1
        L_0x0035:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobCancellationException.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        String message = getMessage();
        if (message == null) {
            Intrinsics.throwNpe();
        }
        int hashCode = ((message.hashCode() * 31) + this.job.hashCode()) * 31;
        Throwable cause = getCause();
        return hashCode + (cause != null ? cause.hashCode() : 0);
    }
}
