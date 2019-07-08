package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: signatureEnhancement.kt */
public final class SignatureEnhancementKt {
    /* access modifiers changed from: private */
    public static final JavaTypeQualifiers createJavaTypeQualifiers(NullabilityQualifier nullabilityQualifier, MutabilityQualifier mutabilityQualifier, boolean z, boolean z2) {
        if (!z2 || nullabilityQualifier != NullabilityQualifier.NOT_NULL) {
            return new JavaTypeQualifiers(nullabilityQualifier, mutabilityQualifier, false, z);
        }
        return new JavaTypeQualifiers(nullabilityQualifier, mutabilityQualifier, true, z);
    }

    /* access modifiers changed from: private */
    public static final <T> T select(@NotNull Set<? extends T> set, T t, T t2, T t3, boolean z) {
        if (z) {
            T t4 = set.contains(t) ? t : set.contains(t2) ? t2 : null;
            if (Intrinsics.areEqual((Object) t4, (Object) t) && Intrinsics.areEqual((Object) t3, (Object) t2)) {
                t4 = null;
            } else if (t3 != null) {
                t4 = t3;
            }
            return t4;
        }
        if (t3 != null) {
            Set<? extends T> set2 = CollectionsKt.toSet(SetsKt.plus(set, t3));
            if (set2 != null) {
                set = set2;
            }
        }
        return CollectionsKt.singleOrNull((Iterable<? extends T>) set);
    }

    /* access modifiers changed from: private */
    public static final NullabilityQualifier select(@NotNull Set<? extends NullabilityQualifier> set, NullabilityQualifier nullabilityQualifier, boolean z) {
        if (nullabilityQualifier == NullabilityQualifier.FORCE_FLEXIBILITY) {
            return NullabilityQualifier.FORCE_FLEXIBILITY;
        }
        return (NullabilityQualifier) select(set, NullabilityQualifier.NOT_NULL, NullabilityQualifier.NULLABLE, nullabilityQualifier, z);
    }
}
