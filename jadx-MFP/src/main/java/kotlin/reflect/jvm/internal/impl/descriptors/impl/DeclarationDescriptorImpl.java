package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotatedImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import org.jetbrains.annotations.NotNull;

public abstract class DeclarationDescriptorImpl extends AnnotatedImpl implements DeclarationDescriptor {
    @NotNull
    private final Name name;

    @NotNull
    public DeclarationDescriptor getOriginal() {
        return this;
    }

    public DeclarationDescriptorImpl(@NotNull Annotations annotations, @NotNull Name name2) {
        super(annotations);
        this.name = name2;
    }

    @NotNull
    public Name getName() {
        return this.name;
    }

    public String toString() {
        return toString(this);
    }

    @NotNull
    public static String toString(@NotNull DeclarationDescriptor declarationDescriptor) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(DescriptorRenderer.DEBUG_TEXT.render(declarationDescriptor));
            sb.append("[");
            sb.append(declarationDescriptor.getClass().getSimpleName());
            sb.append("@");
            sb.append(Integer.toHexString(System.identityHashCode(declarationDescriptor)));
            sb.append("]");
            return sb.toString();
        } catch (Throwable unused) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(declarationDescriptor.getClass().getSimpleName());
            sb2.append(" ");
            sb2.append(declarationDescriptor.getName());
            return sb2.toString();
        }
    }
}
