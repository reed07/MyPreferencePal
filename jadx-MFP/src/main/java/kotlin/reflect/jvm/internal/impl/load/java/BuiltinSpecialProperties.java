package kotlin.reflect.jvm.internal.impl.load.java;

import com.brightcove.player.event.AbstractEvent;
import com.brightcove.player.model.VideoFields;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: specialBuiltinMembers.kt */
public final class BuiltinSpecialProperties {
    private static final Map<Name, List<Name>> GETTER_JVM_NAME_TO_PROPERTIES_SHORT_NAME_MAP;
    public static final BuiltinSpecialProperties INSTANCE = new BuiltinSpecialProperties();
    @NotNull
    private static final Map<FqName, Name> PROPERTY_FQ_NAME_TO_JVM_GETTER_NAME_MAP;
    private static final Set<FqName> SPECIAL_FQ_NAMES = PROPERTY_FQ_NAME_TO_JVM_GETTER_NAME_MAP.keySet();
    @NotNull
    private static final Set<Name> SPECIAL_SHORT_NAMES;

    static {
        FqNameUnsafe fqNameUnsafe = KotlinBuiltIns.FQ_NAMES._enum;
        Intrinsics.checkExpressionValueIsNotNull(fqNameUnsafe, "BUILTIN_NAMES._enum");
        FqNameUnsafe fqNameUnsafe2 = KotlinBuiltIns.FQ_NAMES._enum;
        Intrinsics.checkExpressionValueIsNotNull(fqNameUnsafe2, "BUILTIN_NAMES._enum");
        FqName fqName = KotlinBuiltIns.FQ_NAMES.collection;
        Intrinsics.checkExpressionValueIsNotNull(fqName, "BUILTIN_NAMES.collection");
        FqName fqName2 = KotlinBuiltIns.FQ_NAMES.map;
        Intrinsics.checkExpressionValueIsNotNull(fqName2, "BUILTIN_NAMES.map");
        FqNameUnsafe fqNameUnsafe3 = KotlinBuiltIns.FQ_NAMES.charSequence;
        Intrinsics.checkExpressionValueIsNotNull(fqNameUnsafe3, "BUILTIN_NAMES.charSequence");
        FqName fqName3 = KotlinBuiltIns.FQ_NAMES.map;
        Intrinsics.checkExpressionValueIsNotNull(fqName3, "BUILTIN_NAMES.map");
        FqName fqName4 = KotlinBuiltIns.FQ_NAMES.map;
        Intrinsics.checkExpressionValueIsNotNull(fqName4, "BUILTIN_NAMES.map");
        FqName fqName5 = KotlinBuiltIns.FQ_NAMES.map;
        Intrinsics.checkExpressionValueIsNotNull(fqName5, "BUILTIN_NAMES.map");
        PROPERTY_FQ_NAME_TO_JVM_GETTER_NAME_MAP = MapsKt.mapOf(TuplesKt.to(SpecialBuiltinMembers.childSafe(fqNameUnsafe, "name"), Name.identifier("name")), TuplesKt.to(SpecialBuiltinMembers.childSafe(fqNameUnsafe2, "ordinal"), Name.identifier("ordinal")), TuplesKt.to(SpecialBuiltinMembers.child(fqName, AbstractEvent.SIZE), Name.identifier(AbstractEvent.SIZE)), TuplesKt.to(SpecialBuiltinMembers.child(fqName2, AbstractEvent.SIZE), Name.identifier(AbstractEvent.SIZE)), TuplesKt.to(SpecialBuiltinMembers.childSafe(fqNameUnsafe3, VideoFields.DURATION), Name.identifier(VideoFields.DURATION)), TuplesKt.to(SpecialBuiltinMembers.child(fqName3, "keys"), Name.identifier("keySet")), TuplesKt.to(SpecialBuiltinMembers.child(fqName4, "values"), Name.identifier("values")), TuplesKt.to(SpecialBuiltinMembers.child(fqName5, "entries"), Name.identifier("entrySet")));
        Iterable<Entry> entrySet = PROPERTY_FQ_NAME_TO_JVM_GETTER_NAME_MAP.entrySet();
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(entrySet, 10));
        for (Entry entry : entrySet) {
            arrayList.add(new Pair(((FqName) entry.getKey()).shortName(), entry.getValue()));
        }
        Iterable<Pair> iterable = (List) arrayList;
        Map<Name, List<Name>> linkedHashMap = new LinkedHashMap<>();
        for (Pair pair : iterable) {
            Name name = (Name) pair.getSecond();
            Object obj = linkedHashMap.get(name);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap.put(name, obj);
            }
            ((List) obj).add((Name) pair.getFirst());
        }
        GETTER_JVM_NAME_TO_PROPERTIES_SHORT_NAME_MAP = linkedHashMap;
        Iterable<FqName> iterable2 = SPECIAL_FQ_NAMES;
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
        for (FqName shortName : iterable2) {
            arrayList2.add(shortName.shortName());
        }
        SPECIAL_SHORT_NAMES = CollectionsKt.toSet((List) arrayList2);
    }

    private BuiltinSpecialProperties() {
    }

    @NotNull
    public final Set<Name> getSPECIAL_SHORT_NAMES$descriptors_jvm() {
        return SPECIAL_SHORT_NAMES;
    }

    public final boolean hasBuiltinSpecialPropertyFqName(@NotNull CallableMemberDescriptor callableMemberDescriptor) {
        Intrinsics.checkParameterIsNotNull(callableMemberDescriptor, "callableMemberDescriptor");
        if (!SPECIAL_SHORT_NAMES.contains(callableMemberDescriptor.getName())) {
            return false;
        }
        return hasBuiltinSpecialPropertyFqNameImpl(callableMemberDescriptor);
    }

    private final boolean hasBuiltinSpecialPropertyFqNameImpl(@NotNull CallableMemberDescriptor callableMemberDescriptor) {
        DeclarationDescriptor declarationDescriptor = callableMemberDescriptor;
        if (CollectionsKt.contains(SPECIAL_FQ_NAMES, DescriptorUtilsKt.fqNameOrNull(declarationDescriptor)) && callableMemberDescriptor.getValueParameters().isEmpty()) {
            return true;
        }
        boolean isBuiltIn = KotlinBuiltIns.isBuiltIn(declarationDescriptor);
        boolean z = false;
        if (!isBuiltIn) {
            return false;
        }
        Collection overriddenDescriptors = callableMemberDescriptor.getOverriddenDescriptors();
        Intrinsics.checkExpressionValueIsNotNull(overriddenDescriptors, "overriddenDescriptors");
        Iterable iterable = overriddenDescriptors;
        if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
            Iterator it = iterable.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                CallableMemberDescriptor callableMemberDescriptor2 = (CallableMemberDescriptor) it.next();
                BuiltinSpecialProperties builtinSpecialProperties = INSTANCE;
                Intrinsics.checkExpressionValueIsNotNull(callableMemberDescriptor2, "it");
                if (builtinSpecialProperties.hasBuiltinSpecialPropertyFqName(callableMemberDescriptor2)) {
                    z = true;
                    break;
                }
            }
        }
        return z;
    }

    @NotNull
    public final List<Name> getPropertyNameCandidatesBySpecialGetterName(@NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(name, "name1");
        List<Name> list = (List) GETTER_JVM_NAME_TO_PROPERTIES_SHORT_NAME_MAP.get(name);
        return list != null ? list : CollectionsKt.emptyList();
    }

    @Nullable
    public final String getBuiltinSpecialPropertyGetterName(@NotNull CallableMemberDescriptor callableMemberDescriptor) {
        Intrinsics.checkParameterIsNotNull(callableMemberDescriptor, "receiver$0");
        boolean isBuiltIn = KotlinBuiltIns.isBuiltIn(callableMemberDescriptor);
        if (!_Assertions.ENABLED || isBuiltIn) {
            String str = null;
            CallableMemberDescriptor firstOverridden$default = DescriptorUtilsKt.firstOverridden$default(DescriptorUtilsKt.getPropertyIfAccessor(callableMemberDescriptor), false, BuiltinSpecialProperties$getBuiltinSpecialPropertyGetterName$descriptor$1.INSTANCE, 1, null);
            if (firstOverridden$default == null) {
                return null;
            }
            Name name = (Name) PROPERTY_FQ_NAME_TO_JVM_GETTER_NAME_MAP.get(DescriptorUtilsKt.getFqNameSafe(firstOverridden$default));
            if (name != null) {
                str = name.asString();
            }
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("This method is defined only for builtin members, but ");
        sb.append(callableMemberDescriptor);
        sb.append(" found");
        throw new AssertionError(sb.toString());
    }
}
