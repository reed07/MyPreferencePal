package kotlin.reflect.jvm.internal.impl.load.java.structure;

import java.util.List;
import org.jetbrains.annotations.NotNull;

/* compiled from: javaElements.kt */
public interface JavaMethod extends JavaMember, JavaTypeParameterListOwner {
    boolean getHasAnnotationParameterDefaultValue();

    @NotNull
    JavaType getReturnType();

    @NotNull
    List<JavaValueParameter> getValueParameters();
}
