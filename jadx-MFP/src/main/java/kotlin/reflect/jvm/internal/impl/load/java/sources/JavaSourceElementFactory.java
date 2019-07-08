package kotlin.reflect.jvm.internal.impl.load.java.sources;

import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaElement;
import org.jetbrains.annotations.NotNull;

/* compiled from: JavaSourceElementFactory.kt */
public interface JavaSourceElementFactory {
    @NotNull
    JavaSourceElement source(@NotNull JavaElement javaElement);
}
