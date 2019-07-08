package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;

public class PropertyGetterDescriptorImpl extends PropertyAccessorDescriptorImpl implements PropertyGetterDescriptor {
    @NotNull
    private final PropertyGetterDescriptor original;
    private KotlinType returnType;

    /* JADX WARNING: type inference failed for: r1v4, types: [kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor] */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public PropertyGetterDescriptorImpl(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor r13, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r14, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.Modality r15, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.Visibility r16, boolean r17, boolean r18, boolean r19, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind r20, @org.jetbrains.annotations.Nullable kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor r21, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.SourceElement r22) {
        /*
            r12 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "<get-"
            r0.append(r1)
            kotlin.reflect.jvm.internal.impl.name.Name r1 = r13.getName()
            r0.append(r1)
            java.lang.String r1 = ">"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            kotlin.reflect.jvm.internal.impl.name.Name r6 = kotlin.reflect.jvm.internal.impl.name.Name.special(r0)
            r1 = r12
            r2 = r15
            r3 = r16
            r4 = r13
            r5 = r14
            r7 = r17
            r8 = r18
            r9 = r19
            r10 = r20
            r11 = r22
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            if (r21 == 0) goto L_0x0037
            r0 = r12
            r1 = r21
            goto L_0x0039
        L_0x0037:
            r0 = r12
            r1 = r0
        L_0x0039:
            r0.original = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyGetterDescriptorImpl.<init>(kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations, kotlin.reflect.jvm.internal.impl.descriptors.Modality, kotlin.reflect.jvm.internal.impl.descriptors.Visibility, boolean, boolean, boolean, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor$Kind, kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.SourceElement):void");
    }

    public void initialize(KotlinType kotlinType) {
        if (kotlinType == null) {
            kotlinType = getCorrespondingProperty().getType();
        }
        this.returnType = kotlinType;
    }

    @NotNull
    public Collection<? extends PropertyGetterDescriptor> getOverriddenDescriptors() {
        return super.getOverriddenDescriptors(true);
    }

    @NotNull
    public List<ValueParameterDescriptor> getValueParameters() {
        return Collections.emptyList();
    }

    public KotlinType getReturnType() {
        return this.returnType;
    }

    public <R, D> R accept(DeclarationDescriptorVisitor<R, D> declarationDescriptorVisitor, D d) {
        return declarationDescriptorVisitor.visitPropertyGetterDescriptor(this, d);
    }

    @NotNull
    public PropertyGetterDescriptor getOriginal() {
        return this.original;
    }
}
