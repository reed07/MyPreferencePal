package io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;
import io.grpc.Status.Code;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

@Immutable
final class RetryPolicy {
    static final RetryPolicy DEFAULT;
    final double backoffMultiplier;
    final long initialBackoffNanos;
    final int maxAttempts;
    final long maxBackoffNanos;
    final Set<Code> retryableStatusCodes;

    interface Provider {
        RetryPolicy get();
    }

    static {
        RetryPolicy retryPolicy = new RetryPolicy(1, 0, 0, 1.0d, Collections.emptySet());
        DEFAULT = retryPolicy;
    }

    RetryPolicy(int i, long j, long j2, double d, @Nonnull Set<Code> set) {
        this.maxAttempts = i;
        this.initialBackoffNanos = j;
        this.maxBackoffNanos = j2;
        this.backoffMultiplier = d;
        this.retryableStatusCodes = ImmutableSet.copyOf((Collection<? extends E>) set);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.maxAttempts), Long.valueOf(this.initialBackoffNanos), Long.valueOf(this.maxBackoffNanos), Double.valueOf(this.backoffMultiplier), this.retryableStatusCodes);
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof RetryPolicy)) {
            return false;
        }
        RetryPolicy retryPolicy = (RetryPolicy) obj;
        if (this.maxAttempts == retryPolicy.maxAttempts && this.initialBackoffNanos == retryPolicy.initialBackoffNanos && this.maxBackoffNanos == retryPolicy.maxBackoffNanos && Double.compare(this.backoffMultiplier, retryPolicy.backoffMultiplier) == 0 && Objects.equal(this.retryableStatusCodes, retryPolicy.retryableStatusCodes)) {
            z = true;
        }
        return z;
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("maxAttempts", this.maxAttempts).add("initialBackoffNanos", this.initialBackoffNanos).add("maxBackoffNanos", this.maxBackoffNanos).add("backoffMultiplier", this.backoffMultiplier).add("retryableStatusCodes", (Object) this.retryableStatusCodes).toString();
    }
}
