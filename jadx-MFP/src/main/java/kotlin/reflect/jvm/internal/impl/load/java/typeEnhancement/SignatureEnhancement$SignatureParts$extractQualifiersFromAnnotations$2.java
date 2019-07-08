package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;

/* compiled from: signatureEnhancement.kt */
final class SignatureEnhancement$SignatureParts$extractQualifiersFromAnnotations$2 extends Lambda implements Function2<T, T, T> {
    public static final SignatureEnhancement$SignatureParts$extractQualifiersFromAnnotations$2 INSTANCE = new SignatureEnhancement$SignatureParts$extractQualifiersFromAnnotations$2();

    SignatureEnhancement$SignatureParts$extractQualifiersFromAnnotations$2() {
        super(2);
    }

    @Nullable
    public final <T> T invoke(@Nullable T t, @Nullable T t2) {
        if (t == null || t2 == null || Intrinsics.areEqual((Object) t, (Object) t2)) {
            return t != null ? t : t2;
        }
        return null;
    }
}
