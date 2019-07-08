package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: CompanionObjectMapping.kt */
public final class CompanionObjectMapping {
    public static final CompanionObjectMapping INSTANCE = new CompanionObjectMapping();
    private static final LinkedHashSet<ClassId> classIds;

    static {
        Set<PrimitiveType> set = PrimitiveType.NUMBER_TYPES;
        Intrinsics.checkExpressionValueIsNotNull(set, "PrimitiveType.NUMBER_TYPES");
        Iterable<PrimitiveType> iterable = set;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (PrimitiveType primitiveFqName : iterable) {
            arrayList.add(KotlinBuiltIns.getPrimitiveFqName(primitiveFqName));
        }
        Iterable<FqName> plus = CollectionsKt.plus((Collection<? extends T>) CollectionsKt.plus((Collection<? extends T>) CollectionsKt.plus((Collection<? extends T>) (List) arrayList, KotlinBuiltIns.FQ_NAMES.string.toSafe()), KotlinBuiltIns.FQ_NAMES._boolean.toSafe()), KotlinBuiltIns.FQ_NAMES._enum.toSafe());
        Collection linkedHashSet = new LinkedHashSet();
        for (FqName fqName : plus) {
            linkedHashSet.add(ClassId.topLevel(fqName));
        }
        classIds = (LinkedHashSet) linkedHashSet;
    }

    private CompanionObjectMapping() {
    }

    @NotNull
    public final Set<ClassId> allClassesWithIntrinsicCompanions() {
        Set<ClassId> unmodifiableSet = Collections.unmodifiableSet(classIds);
        Intrinsics.checkExpressionValueIsNotNull(unmodifiableSet, "Collections.unmodifiableSet(classIds)");
        return unmodifiableSet;
    }

    public final boolean isMappedIntrinsicCompanionObject(@NotNull ClassDescriptor classDescriptor) {
        Intrinsics.checkParameterIsNotNull(classDescriptor, "classDescriptor");
        if (DescriptorUtils.isCompanionObject(classDescriptor)) {
            Iterable iterable = classIds;
            ClassId classId = DescriptorUtilsKt.getClassId(classDescriptor);
            if (CollectionsKt.contains(iterable, classId != null ? classId.getOuterClassId() : null)) {
                return true;
            }
        }
        return false;
    }
}
