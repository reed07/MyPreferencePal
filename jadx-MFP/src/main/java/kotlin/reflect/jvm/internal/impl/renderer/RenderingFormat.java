package kotlin.reflect.jvm.internal.impl.renderer;

import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: DescriptorRenderer.kt */
public enum RenderingFormat {
    ;

    /* compiled from: DescriptorRenderer.kt */
    static final class HTML extends RenderingFormat {
        HTML(String str, int i) {
            super(str, i);
        }

        @NotNull
        public String escape(@NotNull String str) {
            Intrinsics.checkParameterIsNotNull(str, "string");
            return StringsKt.replace$default(StringsKt.replace$default(str, "<", "&lt;", false, 4, (Object) null), ">", "&gt;", false, 4, (Object) null);
        }
    }

    /* compiled from: DescriptorRenderer.kt */
    static final class PLAIN extends RenderingFormat {
        @NotNull
        public String escape(@NotNull String str) {
            Intrinsics.checkParameterIsNotNull(str, "string");
            return str;
        }

        PLAIN(String str, int i) {
            super(str, i);
        }
    }

    @NotNull
    public abstract String escape(@NotNull String str);
}
