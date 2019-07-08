package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: predefinedEnhancementInfo.kt */
public final class TypeEnhancementInfo {
    @NotNull
    private final Map<Integer, JavaTypeQualifiers> map;

    public TypeEnhancementInfo(@NotNull Map<Integer, JavaTypeQualifiers> map2) {
        Intrinsics.checkParameterIsNotNull(map2, "map");
        this.map = map2;
    }

    @NotNull
    public final Map<Integer, JavaTypeQualifiers> getMap() {
        return this.map;
    }
}
