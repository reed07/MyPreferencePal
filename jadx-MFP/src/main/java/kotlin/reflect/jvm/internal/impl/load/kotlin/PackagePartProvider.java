package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.List;
import org.jetbrains.annotations.NotNull;

/* compiled from: PackagePartProvider.kt */
public interface PackagePartProvider {
    @NotNull
    List<String> findPackageParts(@NotNull String str);
}
