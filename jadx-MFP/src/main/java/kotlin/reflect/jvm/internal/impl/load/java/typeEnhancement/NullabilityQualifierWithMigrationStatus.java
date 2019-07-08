package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: signatureEnhancement.kt */
public final class NullabilityQualifierWithMigrationStatus {
    private final boolean isForWarningOnly;
    @NotNull
    private final NullabilityQualifier qualifier;

    @NotNull
    public static /* synthetic */ NullabilityQualifierWithMigrationStatus copy$default(NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus, NullabilityQualifier nullabilityQualifier, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            nullabilityQualifier = nullabilityQualifierWithMigrationStatus.qualifier;
        }
        if ((i & 2) != 0) {
            z = nullabilityQualifierWithMigrationStatus.isForWarningOnly;
        }
        return nullabilityQualifierWithMigrationStatus.copy(nullabilityQualifier, z);
    }

    @NotNull
    public final NullabilityQualifierWithMigrationStatus copy(@NotNull NullabilityQualifier nullabilityQualifier, boolean z) {
        Intrinsics.checkParameterIsNotNull(nullabilityQualifier, "qualifier");
        return new NullabilityQualifierWithMigrationStatus(nullabilityQualifier, z);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof NullabilityQualifierWithMigrationStatus) {
                NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus = (NullabilityQualifierWithMigrationStatus) obj;
                if (Intrinsics.areEqual((Object) this.qualifier, (Object) nullabilityQualifierWithMigrationStatus.qualifier)) {
                    if (this.isForWarningOnly == nullabilityQualifierWithMigrationStatus.isForWarningOnly) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        NullabilityQualifier nullabilityQualifier = this.qualifier;
        int hashCode = (nullabilityQualifier != null ? nullabilityQualifier.hashCode() : 0) * 31;
        boolean z = this.isForWarningOnly;
        if (z) {
            z = true;
        }
        return hashCode + (z ? 1 : 0);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NullabilityQualifierWithMigrationStatus(qualifier=");
        sb.append(this.qualifier);
        sb.append(", isForWarningOnly=");
        sb.append(this.isForWarningOnly);
        sb.append(")");
        return sb.toString();
    }

    public NullabilityQualifierWithMigrationStatus(@NotNull NullabilityQualifier nullabilityQualifier, boolean z) {
        Intrinsics.checkParameterIsNotNull(nullabilityQualifier, "qualifier");
        this.qualifier = nullabilityQualifier;
        this.isForWarningOnly = z;
    }

    @NotNull
    public final NullabilityQualifier getQualifier() {
        return this.qualifier;
    }

    public /* synthetic */ NullabilityQualifierWithMigrationStatus(NullabilityQualifier nullabilityQualifier, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 2) != 0) {
            z = false;
        }
        this(nullabilityQualifier, z);
    }

    public final boolean isForWarningOnly() {
        return this.isForWarningOnly;
    }
}
