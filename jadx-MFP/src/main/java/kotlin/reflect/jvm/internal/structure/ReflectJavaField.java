package kotlin.reflect.jvm.internal.structure;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaField;
import kotlin.reflect.jvm.internal.structure.ReflectJavaType.Factory;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0016\u0010\n\u001a\u0004\u0018\u00010\u000b8VX\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u00078VX\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\tR\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u00128VX\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lkotlin/reflect/jvm/internal/structure/ReflectJavaField;", "Lkotlin/reflect/jvm/internal/structure/ReflectJavaMember;", "Lkotlin/reflect/jvm/internal/impl/load/java/structure/JavaField;", "member", "Ljava/lang/reflect/Field;", "(Ljava/lang/reflect/Field;)V", "hasConstantNotNullInitializer", "", "getHasConstantNotNullInitializer", "()Z", "initializerValue", "", "getInitializerValue", "()Ljava/lang/Void;", "isEnumEntry", "getMember", "()Ljava/lang/reflect/Field;", "type", "Lkotlin/reflect/jvm/internal/structure/ReflectJavaType;", "getType", "()Lkotlin/reflect/jvm/internal/structure/ReflectJavaType;", "descriptors.runtime"}, k = 1, mv = {1, 1, 13})
/* compiled from: ReflectJavaField.kt */
public final class ReflectJavaField extends ReflectJavaMember implements JavaField {
    @NotNull
    private final Field member;

    public boolean getHasConstantNotNullInitializer() {
        return false;
    }

    public ReflectJavaField(@NotNull Field field) {
        Intrinsics.checkParameterIsNotNull(field, "member");
        this.member = field;
    }

    @NotNull
    public Field getMember() {
        return this.member;
    }

    public boolean isEnumEntry() {
        return getMember().isEnumConstant();
    }

    @NotNull
    public ReflectJavaType getType() {
        Factory factory = ReflectJavaType.Factory;
        Type genericType = getMember().getGenericType();
        Intrinsics.checkExpressionValueIsNotNull(genericType, "member.genericType");
        return factory.create(genericType);
    }
}
