package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

/* compiled from: signatureEnhancement.kt */
final class SignatureEnhancement$SignatureParts$computeIndexedQualifiersForOverride$1 extends Lambda implements Function1<Integer, JavaTypeQualifiers> {
    final /* synthetic */ JavaTypeQualifiers[] $computedResult;

    SignatureEnhancement$SignatureParts$computeIndexedQualifiersForOverride$1(JavaTypeQualifiers[] javaTypeQualifiersArr) {
        this.$computedResult = javaTypeQualifiersArr;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).intValue());
    }

    @NotNull
    public final JavaTypeQualifiers invoke(int i) {
        JavaTypeQualifiers[] javaTypeQualifiersArr = this.$computedResult;
        return (i < 0 || i > ArraysKt.getLastIndex((T[]) javaTypeQualifiersArr)) ? JavaTypeQualifiers.Companion.getNONE() : javaTypeQualifiersArr[i];
    }
}
