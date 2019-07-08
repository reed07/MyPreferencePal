package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: VersionRequirement.kt */
public final class VersionRequirementTable {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    @NotNull
    public static final VersionRequirementTable EMPTY = new VersionRequirementTable(CollectionsKt.emptyList());
    private final List<VersionRequirement> infos;

    /* compiled from: VersionRequirement.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final VersionRequirementTable getEMPTY() {
            return VersionRequirementTable.EMPTY;
        }

        @NotNull
        public final VersionRequirementTable create(@NotNull kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirementTable versionRequirementTable) {
            Intrinsics.checkParameterIsNotNull(versionRequirementTable, "table");
            if (versionRequirementTable.getRequirementCount() == 0) {
                return getEMPTY();
            }
            List requirementList = versionRequirementTable.getRequirementList();
            Intrinsics.checkExpressionValueIsNotNull(requirementList, "table.requirementList");
            return new VersionRequirementTable(requirementList, null);
        }
    }

    private VersionRequirementTable(List<VersionRequirement> list) {
        this.infos = list;
    }

    public /* synthetic */ VersionRequirementTable(@NotNull List list, DefaultConstructorMarker defaultConstructorMarker) {
        this(list);
    }

    @Nullable
    public final VersionRequirement get(int i) {
        return (VersionRequirement) CollectionsKt.getOrNull(this.infos, i);
    }
}
