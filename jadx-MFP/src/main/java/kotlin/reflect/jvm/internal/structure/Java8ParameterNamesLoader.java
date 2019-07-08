package kotlin.reflect.jvm.internal.structure;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001:\u0001\u000fB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r2\u0006\u0010\n\u001a\u00020\u000bR\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0010"}, d2 = {"Lkotlin/reflect/jvm/internal/structure/Java8ParameterNamesLoader;", "", "()V", "cache", "Lkotlin/reflect/jvm/internal/structure/Java8ParameterNamesLoader$Cache;", "getCache", "()Lkotlin/reflect/jvm/internal/structure/Java8ParameterNamesLoader$Cache;", "setCache", "(Lkotlin/reflect/jvm/internal/structure/Java8ParameterNamesLoader$Cache;)V", "buildCache", "member", "Ljava/lang/reflect/Member;", "loadParameterNames", "", "", "Cache", "descriptors.runtime"}, k = 1, mv = {1, 1, 13})
/* compiled from: ReflectJavaMember.kt */
final class Java8ParameterNamesLoader {
    public static final Java8ParameterNamesLoader INSTANCE = new Java8ParameterNamesLoader();
    @Nullable
    private static Cache cache;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {"Lkotlin/reflect/jvm/internal/structure/Java8ParameterNamesLoader$Cache;", "", "getParameters", "Ljava/lang/reflect/Method;", "getName", "(Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;)V", "getGetName", "()Ljava/lang/reflect/Method;", "getGetParameters", "descriptors.runtime"}, k = 1, mv = {1, 1, 13})
    /* compiled from: ReflectJavaMember.kt */
    public static final class Cache {
        @Nullable
        private final Method getName;
        @Nullable
        private final Method getParameters;

        public Cache(@Nullable Method method, @Nullable Method method2) {
            this.getParameters = method;
            this.getName = method2;
        }

        @Nullable
        public final Method getGetName() {
            return this.getName;
        }

        @Nullable
        public final Method getGetParameters() {
            return this.getParameters;
        }
    }

    private Java8ParameterNamesLoader() {
    }

    @NotNull
    public final Cache buildCache(@NotNull Member member) {
        Intrinsics.checkParameterIsNotNull(member, "member");
        Class cls = member.getClass();
        try {
            return new Cache(cls.getMethod("getParameters", new Class[0]), ReflectClassUtilKt.getSafeClassLoader(cls).loadClass("java.lang.reflect.Parameter").getMethod("getName", new Class[0]));
        } catch (NoSuchMethodException unused) {
            return new Cache(null, null);
        }
    }

    @Nullable
    public final List<String> loadParameterNames(@NotNull Member member) {
        Intrinsics.checkParameterIsNotNull(member, "member");
        Cache cache2 = cache;
        if (cache2 == null) {
            cache2 = buildCache(member);
            cache = cache2;
        }
        Method getParameters = cache2.getGetParameters();
        if (getParameters == null) {
            return null;
        }
        Method getName = cache2.getGetName();
        if (getName == null) {
            return null;
        }
        Object invoke = getParameters.invoke(member, new Object[0]);
        if (invoke != null) {
            Object[] objArr = (Object[]) invoke;
            Collection arrayList = new ArrayList(objArr.length);
            int length = objArr.length;
            int i = 0;
            while (i < length) {
                Object invoke2 = getName.invoke(objArr[i], new Object[0]);
                if (invoke2 != null) {
                    arrayList.add((String) invoke2);
                    i++;
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                }
            }
            return (List) arrayList;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<*>");
    }
}
