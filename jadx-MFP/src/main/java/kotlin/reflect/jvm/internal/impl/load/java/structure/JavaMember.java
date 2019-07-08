package kotlin.reflect.jvm.internal.impl.load.java.structure;

import org.jetbrains.annotations.NotNull;

/* compiled from: javaElements.kt */
public interface JavaMember extends JavaAnnotationOwner, JavaModifierListOwner, JavaNamedElement {
    @NotNull
    JavaClass getContainingClass();
}
