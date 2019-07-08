package kotlin.reflect.jvm.internal.impl.load.java.structure;

import org.jetbrains.annotations.NotNull;

/* compiled from: javaElements.kt */
public interface JavaField extends JavaMember {
    boolean getHasConstantNotNullInitializer();

    @NotNull
    JavaType getType();

    boolean isEnumEntry();
}
