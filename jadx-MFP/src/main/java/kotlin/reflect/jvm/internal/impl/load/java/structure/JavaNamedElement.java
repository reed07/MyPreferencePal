package kotlin.reflect.jvm.internal.impl.load.java.structure;

import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;

/* compiled from: javaElements.kt */
public interface JavaNamedElement extends JavaElement {
    @NotNull
    Name getName();
}
