package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.PackageFragment;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ProtoBasedClassDataFinder.kt */
public final class ProtoBasedClassDataFinder implements ClassDataFinder {
    private final Map<ClassId, Class> classIdToProto;
    private final Function1<ClassId, SourceElement> classSource;
    private final BinaryVersion metadataVersion;
    private final NameResolver nameResolver;

    public ProtoBasedClassDataFinder(@NotNull PackageFragment packageFragment, @NotNull NameResolver nameResolver2, @NotNull BinaryVersion binaryVersion, @NotNull Function1<? super ClassId, ? extends SourceElement> function1) {
        Intrinsics.checkParameterIsNotNull(packageFragment, "proto");
        Intrinsics.checkParameterIsNotNull(nameResolver2, "nameResolver");
        Intrinsics.checkParameterIsNotNull(binaryVersion, "metadataVersion");
        Intrinsics.checkParameterIsNotNull(function1, "classSource");
        this.nameResolver = nameResolver2;
        this.metadataVersion = binaryVersion;
        this.classSource = function1;
        List class_List = packageFragment.getClass_List();
        Intrinsics.checkExpressionValueIsNotNull(class_List, "proto.class_List");
        Iterable iterable = class_List;
        Map<ClassId, Class> linkedHashMap = new LinkedHashMap<>(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(iterable, 10)), 16));
        for (Object next : iterable) {
            Class classR = (Class) next;
            NameResolver nameResolver3 = this.nameResolver;
            Intrinsics.checkExpressionValueIsNotNull(classR, "klass");
            linkedHashMap.put(NameResolverUtilKt.getClassId(nameResolver3, classR.getFqName()), next);
        }
        this.classIdToProto = linkedHashMap;
    }

    @NotNull
    public final Collection<ClassId> getAllClassIds$deserialization() {
        return this.classIdToProto.keySet();
    }

    @Nullable
    public ClassData findClassData(@NotNull ClassId classId) {
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        Class classR = (Class) this.classIdToProto.get(classId);
        if (classR != null) {
            return new ClassData(this.nameResolver, classR, this.metadataVersion, (SourceElement) this.classSource.invoke(classId));
        }
        return null;
    }
}
