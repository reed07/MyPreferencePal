package kotlin.reflect.jvm.internal.impl.descriptors;

import org.jetbrains.annotations.NotNull;

/* compiled from: Modality.kt */
public enum Modality {
    FINAL,
    SEALED,
    OPEN,
    ABSTRACT;
    
    public static final Companion Companion = null;

    /* compiled from: Modality.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Modality convertFromFlags(boolean z, boolean z2) {
            if (z) {
                return Modality.ABSTRACT;
            }
            if (z2) {
                return Modality.OPEN;
            }
            return Modality.FINAL;
        }
    }

    static {
        Companion = new Companion(null);
    }
}
