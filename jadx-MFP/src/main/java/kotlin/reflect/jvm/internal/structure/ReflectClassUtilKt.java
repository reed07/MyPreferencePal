package kotlin.reflect.jvm.internal.structure;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Function;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function10;
import kotlin.jvm.functions.Function11;
import kotlin.jvm.functions.Function12;
import kotlin.jvm.functions.Function13;
import kotlin.jvm.functions.Function14;
import kotlin.jvm.functions.Function15;
import kotlin.jvm.functions.Function16;
import kotlin.jvm.functions.Function17;
import kotlin.jvm.functions.Function18;
import kotlin.jvm.functions.Function19;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function20;
import kotlin.jvm.functions.Function21;
import kotlin.jvm.functions.Function22;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.functions.Function9;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\u001a\u0012\u0010#\u001a\u0006\u0012\u0002\b\u00030\u0002*\u0006\u0012\u0002\b\u00030\u0002\u001a\u000e\u0010$\u001a\u00020%*\u0006\u0012\u0002\b\u00030\u0002\"&\u0010\u0000\u001a\u001a\u0012\u0010\u0012\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00030\u0002\u0012\u0004\u0012\u00020\u00040\u0001X\u0004¢\u0006\u0002\n\u0000\"\u001c\u0010\u0005\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\b0\u00070\u0006X\u0004¢\u0006\u0002\n\u0000\",\u0010\t\u001a \u0012\u000e\u0012\f\u0012\u0006\b\u0001\u0012\u00020\b\u0018\u00010\u0002\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\b0\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\",\u0010\n\u001a \u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0002\u0012\u000e\u0012\f\u0012\u0006\b\u0001\u0012\u00020\b\u0018\u00010\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u0019\u0010\u000b\u001a\u00020\f*\u0006\u0012\u0002\b\u00030\u00028F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e\"\u0019\u0010\u000f\u001a\u00020\u0010*\u0006\u0012\u0002\b\u00030\u00028F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012\"\u001b\u0010\u0013\u001a\u0004\u0018\u00010\u0004*\u0006\u0012\u0002\b\u00030\u00028F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015\"\u001b\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0006*\u00020\u00178F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019\"\u001f\u0010\u001a\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0002*\u0006\u0012\u0002\b\u00030\u00028F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c\"\u0019\u0010\u001d\u001a\u00020\u001e*\u0006\u0012\u0002\b\u00030\u00028F¢\u0006\u0006\u001a\u0004\b\u001f\u0010 \"\u001f\u0010!\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0002*\u0006\u0012\u0002\b\u00030\u00028F¢\u0006\u0006\u001a\u0004\b\"\u0010\u001c¨\u0006&"}, d2 = {"FUNCTION_CLASSES", "", "Ljava/lang/Class;", "Lkotlin/Function;", "", "PRIMITIVE_CLASSES", "", "Lkotlin/reflect/KClass;", "", "PRIMITIVE_TO_WRAPPER", "WRAPPER_TO_PRIMITIVE", "classId", "Lkotlin/reflect/jvm/internal/impl/name/ClassId;", "getClassId", "(Ljava/lang/Class;)Lorg/jetbrains/kotlin/name/ClassId;", "desc", "", "getDesc", "(Ljava/lang/Class;)Ljava/lang/String;", "functionClassArity", "getFunctionClassArity", "(Ljava/lang/Class;)Ljava/lang/Integer;", "parameterizedTypeArguments", "Ljava/lang/reflect/Type;", "getParameterizedTypeArguments", "(Ljava/lang/reflect/Type;)Ljava/util/List;", "primitiveByWrapper", "getPrimitiveByWrapper", "(Ljava/lang/Class;)Ljava/lang/Class;", "safeClassLoader", "Ljava/lang/ClassLoader;", "getSafeClassLoader", "(Ljava/lang/Class;)Ljava/lang/ClassLoader;", "wrapperByPrimitive", "getWrapperByPrimitive", "createArrayType", "isEnumClassOrSpecializedEnumEntryClass", "", "descriptors.runtime"}, k = 2, mv = {1, 1, 13})
/* compiled from: reflectClassUtil.kt */
public final class ReflectClassUtilKt {
    private static final Map<Class<? extends Function<?>>, Integer> FUNCTION_CLASSES;
    private static final List<KClass<? extends Object>> PRIMITIVE_CLASSES = CollectionsKt.listOf(Reflection.getOrCreateKotlinClass(Boolean.TYPE), Reflection.getOrCreateKotlinClass(Byte.TYPE), Reflection.getOrCreateKotlinClass(Character.TYPE), Reflection.getOrCreateKotlinClass(Double.TYPE), Reflection.getOrCreateKotlinClass(Float.TYPE), Reflection.getOrCreateKotlinClass(Integer.TYPE), Reflection.getOrCreateKotlinClass(Long.TYPE), Reflection.getOrCreateKotlinClass(Short.TYPE));
    private static final Map<Class<? extends Object>, Class<? extends Object>> PRIMITIVE_TO_WRAPPER;
    private static final Map<Class<? extends Object>, Class<? extends Object>> WRAPPER_TO_PRIMITIVE;

    @NotNull
    public static final ClassLoader getSafeClassLoader(@NotNull Class<?> cls) {
        Intrinsics.checkParameterIsNotNull(cls, "receiver$0");
        ClassLoader classLoader = cls.getClassLoader();
        if (classLoader != null) {
            return classLoader;
        }
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        Intrinsics.checkExpressionValueIsNotNull(systemClassLoader, "ClassLoader.getSystemClassLoader()");
        return systemClassLoader;
    }

    public static final boolean isEnumClassOrSpecializedEnumEntryClass(@NotNull Class<?> cls) {
        Intrinsics.checkParameterIsNotNull(cls, "receiver$0");
        return Enum.class.isAssignableFrom(cls);
    }

    static {
        int i = 0;
        Iterable<KClass> iterable = PRIMITIVE_CLASSES;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (KClass kClass : iterable) {
            arrayList.add(TuplesKt.to(JvmClassMappingKt.getJavaObjectType(kClass), JvmClassMappingKt.getJavaPrimitiveType(kClass)));
        }
        WRAPPER_TO_PRIMITIVE = MapsKt.toMap((Iterable<? extends Pair<? extends K, ? extends V>>) (List) arrayList);
        Iterable<KClass> iterable2 = PRIMITIVE_CLASSES;
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
        for (KClass kClass2 : iterable2) {
            arrayList2.add(TuplesKt.to(JvmClassMappingKt.getJavaPrimitiveType(kClass2), JvmClassMappingKt.getJavaObjectType(kClass2)));
        }
        PRIMITIVE_TO_WRAPPER = MapsKt.toMap((Iterable<? extends Pair<? extends K, ? extends V>>) (List) arrayList2);
        Iterable listOf = CollectionsKt.listOf(Function0.class, Function1.class, Function2.class, Function3.class, Function4.class, Function5.class, Function6.class, Function7.class, Function8.class, Function9.class, Function10.class, Function11.class, Function12.class, Function13.class, Function14.class, Function15.class, Function16.class, Function17.class, Function18.class, Function19.class, Function20.class, Function21.class, Function22.class);
        Collection arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listOf, 10));
        for (Object next : listOf) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            arrayList3.add(TuplesKt.to((Class) next, Integer.valueOf(i)));
            i = i2;
        }
        FUNCTION_CLASSES = MapsKt.toMap((Iterable<? extends Pair<? extends K, ? extends V>>) (List) arrayList3);
    }

    @Nullable
    public static final Class<?> getPrimitiveByWrapper(@NotNull Class<?> cls) {
        Intrinsics.checkParameterIsNotNull(cls, "receiver$0");
        return (Class) WRAPPER_TO_PRIMITIVE.get(cls);
    }

    @Nullable
    public static final Class<?> getWrapperByPrimitive(@NotNull Class<?> cls) {
        Intrinsics.checkParameterIsNotNull(cls, "receiver$0");
        return (Class) PRIMITIVE_TO_WRAPPER.get(cls);
    }

    @Nullable
    public static final Integer getFunctionClassArity(@NotNull Class<?> cls) {
        Intrinsics.checkParameterIsNotNull(cls, "receiver$0");
        return (Integer) FUNCTION_CLASSES.get(cls);
    }

    @NotNull
    public static final ClassId getClassId(@NotNull Class<?> cls) {
        Intrinsics.checkParameterIsNotNull(cls, "receiver$0");
        if (cls.isPrimitive()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Can't compute ClassId for primitive type: ");
            sb.append(cls);
            throw new IllegalArgumentException(sb.toString());
        } else if (!cls.isArray()) {
            if (cls.getEnclosingMethod() == null && cls.getEnclosingConstructor() == null) {
                String simpleName = cls.getSimpleName();
                Intrinsics.checkExpressionValueIsNotNull(simpleName, "simpleName");
                if (!(simpleName.length() == 0)) {
                    Class declaringClass = cls.getDeclaringClass();
                    if (declaringClass != null) {
                        ClassId classId = getClassId(declaringClass);
                        if (classId != null) {
                            ClassId createNestedClassId = classId.createNestedClassId(Name.identifier(cls.getSimpleName()));
                            if (createNestedClassId != null) {
                                return createNestedClassId;
                            }
                        }
                    }
                    ClassId classId2 = ClassId.topLevel(new FqName(cls.getName()));
                    Intrinsics.checkExpressionValueIsNotNull(classId2, "ClassId.topLevel(FqName(name))");
                    return classId2;
                }
            }
            FqName fqName = new FqName(cls.getName());
            return new ClassId(fqName.parent(), FqName.topLevel(fqName.shortName()), true);
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Can't compute ClassId for array type: ");
            sb2.append(cls);
            throw new IllegalArgumentException(sb2.toString());
        }
    }

    @NotNull
    public static final String getDesc(@NotNull Class<?> cls) {
        Intrinsics.checkParameterIsNotNull(cls, "receiver$0");
        if (Intrinsics.areEqual((Object) cls, (Object) Void.TYPE)) {
            return "V";
        }
        String name = createArrayType(cls).getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "createArrayType().name");
        if (name != null) {
            String substring = name.substring(1);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
            return StringsKt.replace$default(substring, '.', '/', false, 4, (Object) null);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @NotNull
    public static final Class<?> createArrayType(@NotNull Class<?> cls) {
        Intrinsics.checkParameterIsNotNull(cls, "receiver$0");
        return Array.newInstance(cls, 0).getClass();
    }

    @NotNull
    public static final List<Type> getParameterizedTypeArguments(@NotNull Type type) {
        Intrinsics.checkParameterIsNotNull(type, "receiver$0");
        if (!(type instanceof ParameterizedType)) {
            return CollectionsKt.emptyList();
        }
        ParameterizedType parameterizedType = (ParameterizedType) type;
        if (parameterizedType.getOwnerType() != null) {
            return SequencesKt.toList(SequencesKt.flatMap(SequencesKt.generateSequence((Object) type, (Function1) ReflectClassUtilKt$parameterizedTypeArguments$1.INSTANCE), ReflectClassUtilKt$parameterizedTypeArguments$2.INSTANCE));
        }
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        Intrinsics.checkExpressionValueIsNotNull(actualTypeArguments, "actualTypeArguments");
        return ArraysKt.toList((T[]) actualTypeArguments);
    }
}
