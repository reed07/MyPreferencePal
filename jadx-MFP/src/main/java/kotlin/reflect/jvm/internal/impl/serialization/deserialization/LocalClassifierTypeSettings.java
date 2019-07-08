package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.Nullable;

/* compiled from: LocalClassifierTypeSettings.kt */
public interface LocalClassifierTypeSettings {

    /* compiled from: LocalClassifierTypeSettings.kt */
    public static final class Default implements LocalClassifierTypeSettings {
        public static final Default INSTANCE = new Default();

        @Nullable
        public SimpleType getReplacementTypeForLocalClassifiers() {
            return null;
        }

        private Default() {
        }
    }

    @Nullable
    SimpleType getReplacementTypeForLocalClassifiers();
}
