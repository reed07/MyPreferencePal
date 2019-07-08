package kotlin.reflect.jvm.internal.impl.load.java.structure;

import java.util.Collection;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: javaElements.kt */
public interface JavaAnnotation extends JavaElement {
    @NotNull
    Collection<JavaAnnotationArgument> getArguments();

    @Nullable
    ClassId getClassId();

    @Nullable
    JavaClass resolve();
}
