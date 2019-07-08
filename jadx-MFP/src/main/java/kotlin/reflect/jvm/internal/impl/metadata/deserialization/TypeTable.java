package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type;
import org.jetbrains.annotations.NotNull;

/* compiled from: TypeTable.kt */
public final class TypeTable {
    @NotNull
    private final List<Type> types;

    public TypeTable(@NotNull kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        TypeTable typeTable2 = this;
        List<Type> typeList = typeTable.getTypeList();
        if (typeTable.hasFirstNullable()) {
            int firstNullable = typeTable.getFirstNullable();
            List typeList2 = typeTable.getTypeList();
            Intrinsics.checkExpressionValueIsNotNull(typeList2, "typeTable.typeList");
            Iterable iterable = typeList2;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            int i = 0;
            for (Object next : iterable) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                Type type = (Type) next;
                if (i >= firstNullable) {
                    type = type.toBuilder().setNullable(true).build();
                }
                arrayList.add(type);
                i = i2;
            }
            typeList = (List) arrayList;
        } else {
            Intrinsics.checkExpressionValueIsNotNull(typeList, "originalTypes");
        }
        this.types = typeList;
    }

    @NotNull
    public final Type get(int i) {
        return (Type) this.types.get(i);
    }
}
