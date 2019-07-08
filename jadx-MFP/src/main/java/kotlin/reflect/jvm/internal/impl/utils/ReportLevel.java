package kotlin.reflect.jvm.internal.impl.utils;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: Jsr305State.kt */
public enum ReportLevel {
    IGNORE("ignore"),
    WARN("warn"),
    STRICT("strict");
    
    public static final Companion Companion = null;
    @NotNull
    private final String description;

    /* compiled from: Jsr305State.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    protected ReportLevel(String str) {
        Intrinsics.checkParameterIsNotNull(str, "description");
        this.description = str;
    }

    @NotNull
    public final String getDescription() {
        return this.description;
    }

    static {
        Companion = new Companion(null);
    }

    public final boolean isWarning() {
        return this == WARN;
    }

    public final boolean isIgnore() {
        return this == IGNORE;
    }
}
