package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import org.jetbrains.annotations.NotNull;

/* compiled from: methodSignatureBuilding.kt */
public final class SignatureBuildingComponents {
    public static final SignatureBuildingComponents INSTANCE = new SignatureBuildingComponents();

    private SignatureBuildingComponents() {
    }

    @NotNull
    public final String javaLang(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "name");
        StringBuilder sb = new StringBuilder();
        sb.append("java/lang/");
        sb.append(str);
        return sb.toString();
    }

    @NotNull
    public final String javaUtil(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "name");
        StringBuilder sb = new StringBuilder();
        sb.append("java/util/");
        sb.append(str);
        return sb.toString();
    }

    @NotNull
    public final String javaFunction(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "name");
        StringBuilder sb = new StringBuilder();
        sb.append("java/util/function/");
        sb.append(str);
        return sb.toString();
    }

    @NotNull
    public final LinkedHashSet<String> inJavaLang(@NotNull String str, @NotNull String... strArr) {
        Intrinsics.checkParameterIsNotNull(str, "name");
        Intrinsics.checkParameterIsNotNull(strArr, "signatures");
        return inClass(javaLang(str), (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    @NotNull
    public final LinkedHashSet<String> inJavaUtil(@NotNull String str, @NotNull String... strArr) {
        Intrinsics.checkParameterIsNotNull(str, "name");
        Intrinsics.checkParameterIsNotNull(strArr, "signatures");
        return inClass(javaUtil(str), (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    @NotNull
    public final LinkedHashSet<String> inClass(@NotNull String str, @NotNull String... strArr) {
        Intrinsics.checkParameterIsNotNull(str, "internalName");
        Intrinsics.checkParameterIsNotNull(strArr, "signatures");
        Collection linkedHashSet = new LinkedHashSet();
        for (String str2 : strArr) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(".");
            sb.append(str2);
            linkedHashSet.add(sb.toString());
        }
        return (LinkedHashSet) linkedHashSet;
    }

    @NotNull
    public final String signature(@NotNull ClassDescriptor classDescriptor, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(classDescriptor, "classDescriptor");
        Intrinsics.checkParameterIsNotNull(str, "jvmDescriptor");
        return signature(MethodSignatureMappingKt.getInternalName(classDescriptor), str);
    }

    @NotNull
    public final String signature(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "internalName");
        Intrinsics.checkParameterIsNotNull(str2, "jvmDescriptor");
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(".");
        sb.append(str2);
        return sb.toString();
    }

    @NotNull
    public final String jvmDescriptor(@NotNull String str, @NotNull List<String> list, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "name");
        Intrinsics.checkParameterIsNotNull(list, "parameters");
        Intrinsics.checkParameterIsNotNull(str2, "ret");
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append('(');
        sb.append(CollectionsKt.joinToString$default(list, "", null, null, 0, null, SignatureBuildingComponents$jvmDescriptor$1.INSTANCE, 30, null));
        sb.append(')');
        sb.append(escapeClassName(str2));
        return sb.toString();
    }

    /* access modifiers changed from: private */
    public final String escapeClassName(String str) {
        if (str.length() <= 1) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append('L');
        sb.append(str);
        sb.append(';');
        return sb.toString();
    }

    @NotNull
    public final String[] constructors(@NotNull String... strArr) {
        Intrinsics.checkParameterIsNotNull(strArr, "signatures");
        Collection arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            StringBuilder sb = new StringBuilder();
            sb.append("<init>(");
            sb.append(str);
            sb.append(")V");
            arrayList.add(sb.toString());
        }
        Object[] array = ((List) arrayList).toArray(new String[0]);
        if (array != null) {
            return (String[]) array;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }
}
