package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: UnsignedType.kt */
public final class UnsignedTypes {
    public static final UnsignedTypes INSTANCE = new UnsignedTypes();
    private static final HashMap<ClassId, ClassId> arrayClassIdToUnsignedClassId = new HashMap<>();
    private static final Set<Name> arrayClassesShortNames;
    private static final HashMap<ClassId, ClassId> unsignedClassIdToArrayClassId = new HashMap<>();
    private static final Set<Name> unsignedTypeNames;

    static {
        UnsignedType[] values;
        UnsignedType[] values2 = UnsignedType.values();
        Collection arrayList = new ArrayList(values2.length);
        for (UnsignedType typeName : values2) {
            arrayList.add(typeName.getTypeName());
        }
        unsignedTypeNames = CollectionsKt.toSet((List) arrayList);
        UnsignedType[] values3 = UnsignedType.values();
        Collection linkedHashSet = new LinkedHashSet();
        for (UnsignedType arrayClassId : values3) {
            linkedHashSet.add(arrayClassId.getArrayClassId().getShortClassName());
        }
        arrayClassesShortNames = (Set) linkedHashSet;
        for (UnsignedType unsignedType : UnsignedType.values()) {
            arrayClassIdToUnsignedClassId.put(unsignedType.getArrayClassId(), unsignedType.getClassId());
            unsignedClassIdToArrayClassId.put(unsignedType.getClassId(), unsignedType.getArrayClassId());
        }
    }

    private UnsignedTypes() {
    }

    public final boolean isShortNameOfUnsignedArray(@NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return arrayClassesShortNames.contains(name);
    }

    @Nullable
    public final ClassId getUnsignedClassIdByArrayClassId(@NotNull ClassId classId) {
        Intrinsics.checkParameterIsNotNull(classId, "arrayClassId");
        return (ClassId) arrayClassIdToUnsignedClassId.get(classId);
    }

    @Nullable
    public final ClassId getUnsignedArrayClassIdByUnsignedClassId(@NotNull ClassId classId) {
        Intrinsics.checkParameterIsNotNull(classId, "arrayClassId");
        return (ClassId) unsignedClassIdToArrayClassId.get(classId);
    }

    public final boolean isUnsignedType(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "type");
        if (TypeUtils.noExpectedType(kotlinType)) {
            return false;
        }
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        if (declarationDescriptor == null) {
            return false;
        }
        Intrinsics.checkExpressionValueIsNotNull(declarationDescriptor, "type.constructor.declara…escriptor ?: return false");
        return isUnsignedClass(declarationDescriptor);
    }

    public final boolean isUnsignedClass(@NotNull DeclarationDescriptor declarationDescriptor) {
        Intrinsics.checkParameterIsNotNull(declarationDescriptor, "descriptor");
        DeclarationDescriptor containingDeclaration = declarationDescriptor.getContainingDeclaration();
        return (containingDeclaration instanceof PackageFragmentDescriptor) && Intrinsics.areEqual((Object) ((PackageFragmentDescriptor) containingDeclaration).getFqName(), (Object) KotlinBuiltIns.BUILT_INS_PACKAGE_FQ_NAME) && unsignedTypeNames.contains(declarationDescriptor.getName());
    }
}
