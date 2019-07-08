package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: TypeDeserializer.kt */
final class TypeDeserializer$typeConstructor$1 extends Lambda implements Function1<Integer, ClassDescriptor> {
    final /* synthetic */ Type $proto;
    final /* synthetic */ TypeDeserializer this$0;

    TypeDeserializer$typeConstructor$1(TypeDeserializer typeDeserializer, Type type) {
        this.this$0 = typeDeserializer;
        this.$proto = type;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).intValue());
    }

    @NotNull
    public final ClassDescriptor invoke(int i) {
        ClassId classId = NameResolverUtilKt.getClassId(this.this$0.c.getNameResolver(), i);
        List mutableList = SequencesKt.toMutableList(SequencesKt.map(SequencesKt.generateSequence((Object) this.$proto, (Function1) new TypeDeserializer$typeConstructor$1$typeParametersCount$1(this)), TypeDeserializer$typeConstructor$1$typeParametersCount$2.INSTANCE));
        int count = SequencesKt.count(SequencesKt.generateSequence((Object) classId, (Function1) TypeDeserializer$typeConstructor$1$classNestingLevel$1.INSTANCE));
        while (mutableList.size() < count) {
            mutableList.add(Integer.valueOf(0));
        }
        return this.this$0.c.getComponents().getNotFoundClasses().getClass(classId, mutableList);
    }
}
