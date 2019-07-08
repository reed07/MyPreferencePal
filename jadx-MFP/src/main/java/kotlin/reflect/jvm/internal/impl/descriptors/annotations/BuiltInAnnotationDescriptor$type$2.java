package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.NotNull;

/* compiled from: BuiltInAnnotationDescriptor.kt */
final class BuiltInAnnotationDescriptor$type$2 extends Lambda implements Function0<SimpleType> {
    final /* synthetic */ BuiltInAnnotationDescriptor this$0;

    BuiltInAnnotationDescriptor$type$2(BuiltInAnnotationDescriptor builtInAnnotationDescriptor) {
        this.this$0 = builtInAnnotationDescriptor;
        super(0);
    }

    @NotNull
    public final SimpleType invoke() {
        ClassDescriptor builtInClassByFqName = this.this$0.builtIns.getBuiltInClassByFqName(this.this$0.getFqName());
        Intrinsics.checkExpressionValueIsNotNull(builtInClassByFqName, "builtIns.getBuiltInClassByFqName(fqName)");
        return builtInClassByFqName.getDefaultType();
    }
}
