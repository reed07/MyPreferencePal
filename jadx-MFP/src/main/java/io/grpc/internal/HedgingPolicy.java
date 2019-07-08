package io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;
import io.grpc.Status.Code;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import javax.annotation.concurrent.Immutable;

@Immutable
final class HedgingPolicy {
    static final HedgingPolicy DEFAULT = new HedgingPolicy(1, 0, Collections.emptySet());
    final long hedgingDelayNanos;
    final int maxAttempts;
    final Set<Code> nonFatalStatusCodes;

    interface Provider {
    }

    HedgingPolicy(int i, long j, Set<Code> set) {
        this.maxAttempts = i;
        this.hedgingDelayNanos = j;
        this.nonFatalStatusCodes = ImmutableSet.copyOf((Collection<? extends E>) set);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        HedgingPolicy hedgingPolicy = (HedgingPolicy) obj;
        if (!(this.maxAttempts == hedgingPolicy.maxAttempts && this.hedgingDelayNanos == hedgingPolicy.hedgingDelayNanos && Objects.equal(this.nonFatalStatusCodes, hedgingPolicy.nonFatalStatusCodes))) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.maxAttempts), Long.valueOf(this.hedgingDelayNanos), this.nonFatalStatusCodes);
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("maxAttempts", this.maxAttempts).add("hedgingDelayNanos", this.hedgingDelayNanos).add("nonFatalStatusCodes", (Object) this.nonFatalStatusCodes).toString();
    }
}
