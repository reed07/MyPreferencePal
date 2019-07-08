package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;

/* compiled from: DeserializedPackageFragmentImpl.kt */
final class DeserializedPackageFragmentImpl$initialize$1 extends Lambda implements Function0<List<? extends Name>> {
    final /* synthetic */ DeserializedPackageFragmentImpl this$0;

    DeserializedPackageFragmentImpl$initialize$1(DeserializedPackageFragmentImpl deserializedPackageFragmentImpl) {
        this.this$0 = deserializedPackageFragmentImpl;
        super(0);
    }

    @NotNull
    public final List<Name> invoke() {
        Iterable allClassIds$deserialization = this.this$0.getClassDataFinder().getAllClassIds$deserialization();
        Collection arrayList = new ArrayList();
        for (Object next : allClassIds$deserialization) {
            ClassId classId = (ClassId) next;
            if (!classId.isNestedClass() && !ClassDeserializer.Companion.getBLACK_LIST().contains(classId)) {
                arrayList.add(next);
            }
        }
        Iterable<ClassId> iterable = (List) arrayList;
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (ClassId shortClassName : iterable) {
            arrayList2.add(shortClassName.getShortClassName());
        }
        return (List) arrayList2;
    }
}
