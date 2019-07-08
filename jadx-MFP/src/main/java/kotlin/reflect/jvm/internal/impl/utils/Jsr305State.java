package kotlin.reflect.jvm.internal.impl.utils;

import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Jsr305State.kt */
public final class Jsr305State {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Jsr305State.class), "description", "getDescription()[Ljava/lang/String;"))};
    public static final Companion Companion = new Companion(null);
    @NotNull
    @JvmField
    public static final Jsr305State DEFAULT;
    @NotNull
    @JvmField
    public static final Jsr305State DISABLED;
    @NotNull
    @JvmField
    public static final Jsr305State STRICT;
    @NotNull
    private final Lazy description$delegate;
    private final boolean enableCompatqualCheckerFrameworkAnnotations;
    @NotNull
    private final ReportLevel global;
    @Nullable
    private final ReportLevel migration;
    @NotNull
    private final Map<String, ReportLevel> user;

    /* compiled from: Jsr305State.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof Jsr305State) {
                Jsr305State jsr305State = (Jsr305State) obj;
                if (Intrinsics.areEqual((Object) this.global, (Object) jsr305State.global) && Intrinsics.areEqual((Object) this.migration, (Object) jsr305State.migration) && Intrinsics.areEqual((Object) this.user, (Object) jsr305State.user)) {
                    if (this.enableCompatqualCheckerFrameworkAnnotations == jsr305State.enableCompatqualCheckerFrameworkAnnotations) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        ReportLevel reportLevel = this.global;
        int i = 0;
        int hashCode = (reportLevel != null ? reportLevel.hashCode() : 0) * 31;
        ReportLevel reportLevel2 = this.migration;
        int hashCode2 = (hashCode + (reportLevel2 != null ? reportLevel2.hashCode() : 0)) * 31;
        Map<String, ReportLevel> map = this.user;
        if (map != null) {
            i = map.hashCode();
        }
        int i2 = (hashCode2 + i) * 31;
        boolean z = this.enableCompatqualCheckerFrameworkAnnotations;
        if (z) {
            z = true;
        }
        return i2 + (z ? 1 : 0);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Jsr305State(global=");
        sb.append(this.global);
        sb.append(", migration=");
        sb.append(this.migration);
        sb.append(", user=");
        sb.append(this.user);
        sb.append(", enableCompatqualCheckerFrameworkAnnotations=");
        sb.append(this.enableCompatqualCheckerFrameworkAnnotations);
        sb.append(")");
        return sb.toString();
    }

    public Jsr305State(@NotNull ReportLevel reportLevel, @Nullable ReportLevel reportLevel2, @NotNull Map<String, ? extends ReportLevel> map, boolean z) {
        Intrinsics.checkParameterIsNotNull(reportLevel, "global");
        Intrinsics.checkParameterIsNotNull(map, "user");
        this.global = reportLevel;
        this.migration = reportLevel2;
        this.user = map;
        this.enableCompatqualCheckerFrameworkAnnotations = z;
        this.description$delegate = LazyKt.lazy(new Jsr305State$description$2(this));
    }

    @NotNull
    public final ReportLevel getGlobal() {
        return this.global;
    }

    @Nullable
    public final ReportLevel getMigration() {
        return this.migration;
    }

    @NotNull
    public final Map<String, ReportLevel> getUser() {
        return this.user;
    }

    public /* synthetic */ Jsr305State(ReportLevel reportLevel, ReportLevel reportLevel2, Map map, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 8) != 0) {
            z = true;
        }
        this(reportLevel, reportLevel2, map, z);
    }

    public final boolean getEnableCompatqualCheckerFrameworkAnnotations() {
        return this.enableCompatqualCheckerFrameworkAnnotations;
    }

    public final boolean getDisabled() {
        return this == DISABLED;
    }

    static {
        Jsr305State jsr305State = new Jsr305State(ReportLevel.WARN, null, MapsKt.emptyMap(), false, 8, null);
        DEFAULT = jsr305State;
        Jsr305State jsr305State2 = new Jsr305State(ReportLevel.IGNORE, ReportLevel.IGNORE, MapsKt.emptyMap(), false, 8, null);
        DISABLED = jsr305State2;
        Jsr305State jsr305State3 = new Jsr305State(ReportLevel.STRICT, ReportLevel.STRICT, MapsKt.emptyMap(), false, 8, null);
        STRICT = jsr305State3;
    }
}
