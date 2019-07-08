package kotlin.reflect.jvm.internal.calls;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"equals", "", "T", "", "other", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: AnnotationConstructorCaller.kt */
final class AnnotationConstructorCallerKt$createAnnotationInstance$2 extends Lambda implements Function1<Object, Boolean> {
    final /* synthetic */ Class $annotationClass;
    final /* synthetic */ List $methods;
    final /* synthetic */ Map $values;

    AnnotationConstructorCallerKt$createAnnotationInstance$2(Class cls, List list, Map map) {
        this.$annotationClass = cls;
        this.$methods = list;
        this.$values = map;
        super(1);
    }

    public final boolean invoke(@Nullable Object obj) {
        boolean z;
        boolean z2;
        Class cls = null;
        Annotation annotation = (Annotation) (!(obj instanceof Annotation) ? null : obj);
        if (annotation != null) {
            KClass annotationClass = JvmClassMappingKt.getAnnotationClass(annotation);
            if (annotationClass != null) {
                cls = JvmClassMappingKt.getJavaClass(annotationClass);
            }
        }
        if (Intrinsics.areEqual((Object) cls, (Object) this.$annotationClass)) {
            Iterable iterable = this.$methods;
            if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                Iterator it = iterable.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = true;
                        break;
                    }
                    Method method = (Method) it.next();
                    Object obj2 = this.$values.get(method.getName());
                    Object invoke = method.invoke(obj, new Object[0]);
                    if (obj2 instanceof boolean[]) {
                        boolean[] zArr = (boolean[]) obj2;
                        if (invoke != null) {
                            z2 = Arrays.equals(zArr, (boolean[]) invoke);
                            continue;
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.BooleanArray");
                        }
                    } else if (obj2 instanceof char[]) {
                        char[] cArr = (char[]) obj2;
                        if (invoke != null) {
                            z2 = Arrays.equals(cArr, (char[]) invoke);
                            continue;
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharArray");
                        }
                    } else if (obj2 instanceof byte[]) {
                        byte[] bArr = (byte[]) obj2;
                        if (invoke != null) {
                            z2 = Arrays.equals(bArr, (byte[]) invoke);
                            continue;
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.ByteArray");
                        }
                    } else if (obj2 instanceof short[]) {
                        short[] sArr = (short[]) obj2;
                        if (invoke != null) {
                            z2 = Arrays.equals(sArr, (short[]) invoke);
                            continue;
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.ShortArray");
                        }
                    } else if (obj2 instanceof int[]) {
                        int[] iArr = (int[]) obj2;
                        if (invoke != null) {
                            z2 = Arrays.equals(iArr, (int[]) invoke);
                            continue;
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.IntArray");
                        }
                    } else if (obj2 instanceof float[]) {
                        float[] fArr = (float[]) obj2;
                        if (invoke != null) {
                            z2 = Arrays.equals(fArr, (float[]) invoke);
                            continue;
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.FloatArray");
                        }
                    } else if (obj2 instanceof long[]) {
                        long[] jArr = (long[]) obj2;
                        if (invoke != null) {
                            z2 = Arrays.equals(jArr, (long[]) invoke);
                            continue;
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.LongArray");
                        }
                    } else if (obj2 instanceof double[]) {
                        double[] dArr = (double[]) obj2;
                        if (invoke != null) {
                            z2 = Arrays.equals(dArr, (double[]) invoke);
                            continue;
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.DoubleArray");
                        }
                    } else if (obj2 instanceof Object[]) {
                        Object[] objArr = (Object[]) obj2;
                        if (invoke != null) {
                            z2 = Arrays.equals(objArr, (Object[]) invoke);
                            continue;
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<*>");
                        }
                    } else {
                        z2 = Intrinsics.areEqual(obj2, invoke);
                        continue;
                    }
                    if (!z2) {
                        z = false;
                        break;
                    }
                }
            } else {
                z = true;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }
}
