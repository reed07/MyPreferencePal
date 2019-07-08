package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.TypeCastException;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ModuleMapping.kt */
public final class PackageParts {
    @NotNull
    private final Set<String> metadataParts = new LinkedHashSet();
    @NotNull
    private final String packageFqName;
    private final LinkedHashMap<String, String> packageParts = new LinkedHashMap<>();

    public PackageParts(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "packageFqName");
        this.packageFqName = str;
    }

    @NotNull
    public final Set<String> getParts() {
        Set<String> keySet = this.packageParts.keySet();
        Intrinsics.checkExpressionValueIsNotNull(keySet, "packageParts.keys");
        return keySet;
    }

    public final void addPart(@NotNull String str, @Nullable String str2) {
        Intrinsics.checkParameterIsNotNull(str, "partInternalName");
        this.packageParts.put(str, str2);
    }

    public final void addMetadataPart(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "shortName");
        Set<String> set = this.metadataParts;
        if (set != null) {
            TypeIntrinsics.asMutableSet(set).add(str);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableSet<kotlin.String>");
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof PackageParts) {
            PackageParts packageParts2 = (PackageParts) obj;
            if (Intrinsics.areEqual((Object) packageParts2.packageFqName, (Object) this.packageFqName) && Intrinsics.areEqual((Object) packageParts2.packageParts, (Object) this.packageParts) && Intrinsics.areEqual((Object) packageParts2.metadataParts, (Object) this.metadataParts)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (((this.packageFqName.hashCode() * 31) + this.packageParts.hashCode()) * 31) + this.metadataParts.hashCode();
    }

    @NotNull
    public String toString() {
        return SetsKt.plus(getParts(), (Iterable<? extends T>) this.metadataParts).toString();
    }
}
