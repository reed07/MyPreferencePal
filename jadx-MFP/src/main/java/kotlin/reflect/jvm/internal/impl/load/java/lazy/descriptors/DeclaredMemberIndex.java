package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaField;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DeclaredMemberIndex.kt */
public interface DeclaredMemberIndex {

    /* compiled from: DeclaredMemberIndex.kt */
    public static final class Empty implements DeclaredMemberIndex {
        public static final Empty INSTANCE = new Empty();

        @Nullable
        public JavaField findFieldByName(@NotNull Name name) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            return null;
        }

        private Empty() {
        }

        @NotNull
        public List<JavaMethod> findMethodsByName(@NotNull Name name) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            return CollectionsKt.emptyList();
        }

        @NotNull
        public Set<Name> getMethodNames() {
            return SetsKt.emptySet();
        }

        @NotNull
        public Set<Name> getFieldNames() {
            return SetsKt.emptySet();
        }
    }

    @Nullable
    JavaField findFieldByName(@NotNull Name name);

    @NotNull
    Collection<JavaMethod> findMethodsByName(@NotNull Name name);

    @NotNull
    Set<Name> getFieldNames();

    @NotNull
    Set<Name> getMethodNames();
}
