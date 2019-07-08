package kotlin.reflect.full;

import java.lang.annotation.Annotation;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KAnnotatedElement;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\b\u0002\n\u0002\u0010\u001b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a \u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u0003H\b¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"findAnnotation", "T", "", "Lkotlin/reflect/KAnnotatedElement;", "(Lkotlin/reflect/KAnnotatedElement;)Ljava/lang/annotation/Annotation;", "kotlin-reflect-api"}, k = 2, mv = {1, 1, 13})
@JvmName(name = "KAnnotatedElements")
/* compiled from: KAnnotatedElements.kt */
public final class KAnnotatedElements {
    @SinceKotlin(version = "1.1")
    private static final <T extends Annotation> T findAnnotation(@NotNull KAnnotatedElement kAnnotatedElement) {
        T t;
        Iterator it = kAnnotatedElement.getAnnotations().iterator();
        while (true) {
            if (!it.hasNext()) {
                t = null;
                break;
            }
            t = it.next();
            Annotation annotation = (Annotation) t;
            Intrinsics.reifiedOperationMarker(3, "T");
            if (annotation instanceof Annotation) {
                break;
            }
        }
        Intrinsics.reifiedOperationMarker(1, "T?");
        return (Annotation) t;
    }
}
