package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

/* compiled from: signatureEnhancement.kt */
final class SignatureEnhancement$SignatureParts$enhance$$inlined$let$lambda$1 extends Lambda implements Function1<Integer, JavaTypeQualifiers> {
    final /* synthetic */ TypeEnhancementInfo $predefined$inlined;
    final /* synthetic */ Function1 $qualifiers$inlined;

    SignatureEnhancement$SignatureParts$enhance$$inlined$let$lambda$1(TypeEnhancementInfo typeEnhancementInfo, Function1 function1) {
        this.$predefined$inlined = typeEnhancementInfo;
        this.$qualifiers$inlined = function1;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).intValue());
    }

    @NotNull
    public final JavaTypeQualifiers invoke(int i) {
        JavaTypeQualifiers javaTypeQualifiers = (JavaTypeQualifiers) this.$predefined$inlined.getMap().get(Integer.valueOf(i));
        return javaTypeQualifiers != null ? javaTypeQualifiers : (JavaTypeQualifiers) this.$qualifiers$inlined.invoke(Integer.valueOf(i));
    }
}
