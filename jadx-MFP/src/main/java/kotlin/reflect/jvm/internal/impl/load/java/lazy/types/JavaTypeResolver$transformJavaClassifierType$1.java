package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.NotNull;

/* compiled from: JavaTypeResolver.kt */
final class JavaTypeResolver$transformJavaClassifierType$1 extends Lambda implements Function0<SimpleType> {
    final /* synthetic */ JavaClassifierType $javaType;

    JavaTypeResolver$transformJavaClassifierType$1(JavaClassifierType javaClassifierType) {
        this.$javaType = javaClassifierType;
        super(0);
    }

    @NotNull
    public final SimpleType invoke() {
        StringBuilder sb = new StringBuilder();
        sb.append("Unresolved java class ");
        sb.append(this.$javaType.getPresentableText());
        SimpleType createErrorType = ErrorUtils.createErrorType(sb.toString());
        Intrinsics.checkExpressionValueIsNotNull(createErrorType, "ErrorUtils.createErrorTy…vaType.presentableText}\")");
        return createErrorType;
    }
}
