package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaField;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMember;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DeclaredMemberIndex.kt */
public class ClassDeclaredMemberIndex implements DeclaredMemberIndex {
    private final Map<Name, JavaField> fields;
    @NotNull
    private final JavaClass jClass;
    /* access modifiers changed from: private */
    public final Function1<JavaMember, Boolean> memberFilter;
    private final Function1<JavaMethod, Boolean> methodFilter = new ClassDeclaredMemberIndex$methodFilter$1(this);
    private final Map<Name, List<JavaMethod>> methods;

    public ClassDeclaredMemberIndex(@NotNull JavaClass javaClass, @NotNull Function1<? super JavaMember, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(javaClass, "jClass");
        Intrinsics.checkParameterIsNotNull(function1, "memberFilter");
        this.jClass = javaClass;
        this.memberFilter = function1;
        Sequence filter = SequencesKt.filter(CollectionsKt.asSequence(this.jClass.getMethods()), this.methodFilter);
        Map<Name, List<JavaMethod>> linkedHashMap = new LinkedHashMap<>();
        for (Object next : filter) {
            Name name = ((JavaMethod) next).getName();
            Object obj = linkedHashMap.get(name);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap.put(name, obj);
            }
            ((List) obj).add(next);
        }
        this.methods = linkedHashMap;
        Sequence filter2 = SequencesKt.filter(CollectionsKt.asSequence(this.jClass.getFields()), this.memberFilter);
        Map<Name, JavaField> linkedHashMap2 = new LinkedHashMap<>();
        for (Object next2 : filter2) {
            linkedHashMap2.put(((JavaField) next2).getName(), next2);
        }
        this.fields = linkedHashMap2;
    }

    @NotNull
    public Collection<JavaMethod> findMethodsByName(@NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        List list = (List) this.methods.get(name);
        if (list == null) {
            list = CollectionsKt.emptyList();
        }
        return list;
    }

    @NotNull
    public Set<Name> getMethodNames() {
        Sequence<JavaMethod> filter = SequencesKt.filter(CollectionsKt.asSequence(this.jClass.getMethods()), this.methodFilter);
        Collection linkedHashSet = new LinkedHashSet();
        for (JavaMethod name : filter) {
            linkedHashSet.add(name.getName());
        }
        return (Set) linkedHashSet;
    }

    @Nullable
    public JavaField findFieldByName(@NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return (JavaField) this.fields.get(name);
    }

    @NotNull
    public Set<Name> getFieldNames() {
        Sequence<JavaField> filter = SequencesKt.filter(CollectionsKt.asSequence(this.jClass.getFields()), this.memberFilter);
        Collection linkedHashSet = new LinkedHashSet();
        for (JavaField name : filter) {
            linkedHashSet.add(name.getName());
        }
        return (Set) linkedHashSet;
    }
}
