package com.myfitnesspal.feature.search.event;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lcom/myfitnesspal/feature/search/event/NavigateToQuickAddEvent;", "", "source", "", "(Ljava/lang/String;)V", "getSource", "()Ljava/lang/String;", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: NavigateToQuickAddEvent.kt */
public final class NavigateToQuickAddEvent {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String SOURCE_FIXED_FOOTER = "fixed_footer";
    @NotNull
    public static final String SOURCE_OVERFLOW_MENU = "overflow_menu";
    @NotNull
    private final String source;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/myfitnesspal/feature/search/event/NavigateToQuickAddEvent$Companion;", "", "()V", "SOURCE_FIXED_FOOTER", "", "SOURCE_OVERFLOW_MENU", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: NavigateToQuickAddEvent.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public NavigateToQuickAddEvent(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "source");
        this.source = str;
    }

    @NotNull
    public final String getSource() {
        return this.source;
    }
}
