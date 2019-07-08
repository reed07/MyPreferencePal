package kotlin.reflect.full;

import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KClassifier;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0001\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: KClassifiers.kt */
final class KClassifiers$createType$1 extends Lambda implements Function0 {
    final /* synthetic */ KClassifier $this_createType;

    KClassifiers$createType$1(KClassifier kClassifier) {
        this.$this_createType = kClassifier;
        super(0);
    }

    @NotNull
    public final Void invoke() {
        StringBuilder sb = new StringBuilder();
        sb.append("Java type is not yet supported for types created with createType (classifier = ");
        sb.append(this.$this_createType);
        sb.append(')');
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append("An operation is not implemented: ");
        sb3.append(sb2);
        throw new NotImplementedError(sb3.toString());
    }
}
