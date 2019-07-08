package kotlin.reflect.jvm.internal.impl.load.java.structure;

import java.util.List;
import org.jetbrains.annotations.NotNull;

/* compiled from: javaElements.kt */
public interface JavaTypeParameterListOwner extends JavaElement {
    @NotNull
    List<JavaTypeParameter> getTypeParameters();
}
