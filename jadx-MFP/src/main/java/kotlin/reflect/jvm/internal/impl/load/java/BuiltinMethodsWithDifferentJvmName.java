package kotlin.reflect.jvm.internal.impl.load.java;

import com.google.android.gms.analytics.ecommerce.ProductAction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: specialBuiltinMembers.kt */
public final class BuiltinMethodsWithDifferentJvmName {
    public static final BuiltinMethodsWithDifferentJvmName INSTANCE = new BuiltinMethodsWithDifferentJvmName();
    private static final Map<Name, List<Name>> JVM_SHORT_NAME_TO_BUILTIN_SHORT_NAMES_MAP;
    private static final Map<NameAndSignature, Name> NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP;
    @NotNull
    private static final List<Name> ORIGINAL_SHORT_NAMES;
    private static final NameAndSignature REMOVE_AT_NAME_AND_SIGNATURE;
    /* access modifiers changed from: private */
    public static final Map<String, Name> SIGNATURE_TO_JVM_REPRESENTATION_NAME;

    static {
        String desc = JvmPrimitiveType.INT.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc, "JvmPrimitiveType.INT.desc");
        REMOVE_AT_NAME_AND_SIGNATURE = SpecialBuiltinMembers.method("java/util/List", "removeAt", desc, "Ljava/lang/Object;");
        SignatureBuildingComponents signatureBuildingComponents = SignatureBuildingComponents.INSTANCE;
        String desc2 = JvmPrimitiveType.BYTE.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc2, "JvmPrimitiveType.BYTE.desc");
        String desc3 = JvmPrimitiveType.SHORT.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc3, "JvmPrimitiveType.SHORT.desc");
        String desc4 = JvmPrimitiveType.INT.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc4, "JvmPrimitiveType.INT.desc");
        String desc5 = JvmPrimitiveType.LONG.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc5, "JvmPrimitiveType.LONG.desc");
        String desc6 = JvmPrimitiveType.FLOAT.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc6, "JvmPrimitiveType.FLOAT.desc");
        String desc7 = JvmPrimitiveType.DOUBLE.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc7, "JvmPrimitiveType.DOUBLE.desc");
        String desc8 = JvmPrimitiveType.INT.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc8, "JvmPrimitiveType.INT.desc");
        String desc9 = JvmPrimitiveType.CHAR.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc9, "JvmPrimitiveType.CHAR.desc");
        NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP = MapsKt.mapOf(TuplesKt.to(SpecialBuiltinMembers.method(signatureBuildingComponents.javaLang("Number"), "toByte", "", desc2), Name.identifier("byteValue")), TuplesKt.to(SpecialBuiltinMembers.method(signatureBuildingComponents.javaLang("Number"), "toShort", "", desc3), Name.identifier("shortValue")), TuplesKt.to(SpecialBuiltinMembers.method(signatureBuildingComponents.javaLang("Number"), "toInt", "", desc4), Name.identifier("intValue")), TuplesKt.to(SpecialBuiltinMembers.method(signatureBuildingComponents.javaLang("Number"), "toLong", "", desc5), Name.identifier("longValue")), TuplesKt.to(SpecialBuiltinMembers.method(signatureBuildingComponents.javaLang("Number"), "toFloat", "", desc6), Name.identifier("floatValue")), TuplesKt.to(SpecialBuiltinMembers.method(signatureBuildingComponents.javaLang("Number"), "toDouble", "", desc7), Name.identifier("doubleValue")), TuplesKt.to(REMOVE_AT_NAME_AND_SIGNATURE, Name.identifier(ProductAction.ACTION_REMOVE)), TuplesKt.to(SpecialBuiltinMembers.method(signatureBuildingComponents.javaLang("CharSequence"), "get", desc8, desc9), Name.identifier("charAt")));
        Map<NameAndSignature, Name> map = NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP;
        Map<String, Name> linkedHashMap = new LinkedHashMap<>(MapsKt.mapCapacity(map.size()));
        for (Entry entry : map.entrySet()) {
            linkedHashMap.put(((NameAndSignature) entry.getKey()).getSignature(), entry.getValue());
        }
        SIGNATURE_TO_JVM_REPRESENTATION_NAME = linkedHashMap;
        Iterable<NameAndSignature> keySet = NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP.keySet();
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(keySet, 10));
        for (NameAndSignature name : keySet) {
            arrayList.add(name.getName());
        }
        ORIGINAL_SHORT_NAMES = (List) arrayList;
        Iterable<Entry> entrySet = NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP.entrySet();
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(entrySet, 10));
        for (Entry entry2 : entrySet) {
            arrayList2.add(new Pair(((NameAndSignature) entry2.getKey()).getName(), entry2.getValue()));
        }
        Iterable<Pair> iterable = (List) arrayList2;
        Map<Name, List<Name>> linkedHashMap2 = new LinkedHashMap<>();
        for (Pair pair : iterable) {
            Name name2 = (Name) pair.getSecond();
            Object obj = linkedHashMap2.get(name2);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap2.put(name2, obj);
            }
            ((List) obj).add((Name) pair.getFirst());
        }
        JVM_SHORT_NAME_TO_BUILTIN_SHORT_NAMES_MAP = linkedHashMap2;
    }

    private BuiltinMethodsWithDifferentJvmName() {
    }

    @NotNull
    public final List<Name> getORIGINAL_SHORT_NAMES() {
        return ORIGINAL_SHORT_NAMES;
    }

    public final boolean getSameAsRenamedInJvmBuiltin(@NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(name, "receiver$0");
        return ORIGINAL_SHORT_NAMES.contains(name);
    }

    @Nullable
    public final Name getJvmName(@NotNull SimpleFunctionDescriptor simpleFunctionDescriptor) {
        Intrinsics.checkParameterIsNotNull(simpleFunctionDescriptor, "functionDescriptor");
        Map<String, Name> map = SIGNATURE_TO_JVM_REPRESENTATION_NAME;
        String computeJvmSignature = MethodSignatureMappingKt.computeJvmSignature(simpleFunctionDescriptor);
        if (computeJvmSignature != null) {
            return (Name) map.get(computeJvmSignature);
        }
        return null;
    }

    public final boolean isBuiltinFunctionWithDifferentNameInJvm(@NotNull SimpleFunctionDescriptor simpleFunctionDescriptor) {
        Intrinsics.checkParameterIsNotNull(simpleFunctionDescriptor, "functionDescriptor");
        return KotlinBuiltIns.isBuiltIn(simpleFunctionDescriptor) && DescriptorUtilsKt.firstOverridden$default(simpleFunctionDescriptor, false, new BuiltinMethodsWithDifferentJvmName$isBuiltinFunctionWithDifferentNameInJvm$1(simpleFunctionDescriptor), 1, null) != null;
    }

    @NotNull
    public final List<Name> getBuiltinFunctionNamesByJvmName(@NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        List<Name> list = (List) JVM_SHORT_NAME_TO_BUILTIN_SHORT_NAMES_MAP.get(name);
        return list != null ? list : CollectionsKt.emptyList();
    }

    public final boolean isRemoveAtByIndex(@NotNull SimpleFunctionDescriptor simpleFunctionDescriptor) {
        Intrinsics.checkParameterIsNotNull(simpleFunctionDescriptor, "receiver$0");
        return Intrinsics.areEqual((Object) simpleFunctionDescriptor.getName().asString(), (Object) "removeAt") && Intrinsics.areEqual((Object) MethodSignatureMappingKt.computeJvmSignature(simpleFunctionDescriptor), (Object) REMOVE_AT_NAME_AND_SIGNATURE.getSignature());
    }
}
