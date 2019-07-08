package kotlin.reflect.jvm.internal.impl.resolve;

import kotlin.jvm.JvmField;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor.Capability;
import org.jetbrains.annotations.NotNull;

/* compiled from: MultiTargetPlatform.kt */
public abstract class MultiTargetPlatform implements Comparable<MultiTargetPlatform> {
    @NotNull
    @JvmField
    public static final Capability<MultiTargetPlatform> CAPABILITY = new Capability<>("MULTI_TARGET_PLATFORM");
    public static final Companion Companion = new Companion(null);

    /* compiled from: MultiTargetPlatform.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private MultiTargetPlatform() {
    }
}
