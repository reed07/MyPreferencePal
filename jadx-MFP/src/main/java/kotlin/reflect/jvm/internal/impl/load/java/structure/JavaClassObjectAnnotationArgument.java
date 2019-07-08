package kotlin.reflect.jvm.internal.impl.load.java.structure;

import org.jetbrains.annotations.NotNull;

/* compiled from: annotationArguments.kt */
public interface JavaClassObjectAnnotationArgument extends JavaAnnotationArgument {
    @NotNull
    JavaType getReferencedType();
}
