package kotlin.reflect.jvm.internal.impl.builtins.functions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.BuiltInsPackageFragment;
import kotlin.reflect.jvm.internal.impl.builtins.functions.FunctionClassDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: BuiltInFictitiousFunctionClassFactory.kt */
public final class BuiltInFictitiousFunctionClassFactory implements ClassDescriptorFactory {
    public static final Companion Companion = new Companion(null);
    private final ModuleDescriptor module;
    private final StorageManager storageManager;

    /* compiled from: BuiltInFictitiousFunctionClassFactory.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        public final KindWithArity parseClassName(String str, FqName fqName) {
            Kind byClassNamePrefix = Kind.Companion.byClassNamePrefix(fqName, str);
            if (byClassNamePrefix == null) {
                return null;
            }
            Companion companion = this;
            int length = byClassNamePrefix.getClassNamePrefix().length();
            if (str != null) {
                String substring = str.substring(length);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
                Integer num = companion.toInt(substring);
                if (num != null) {
                    return new KindWithArity(byClassNamePrefix, num.intValue());
                }
                return null;
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }

        @JvmStatic
        @Nullable
        public final Kind getFunctionalClassKind(@NotNull String str, @NotNull FqName fqName) {
            Intrinsics.checkParameterIsNotNull(str, "className");
            Intrinsics.checkParameterIsNotNull(fqName, "packageFqName");
            KindWithArity parseClassName = parseClassName(str, fqName);
            if (parseClassName != null) {
                return parseClassName.getKind();
            }
            return null;
        }

        private final Integer toInt(String str) {
            if (str.length() == 0) {
                return null;
            }
            int length = str.length();
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                int charAt = str.charAt(i2) - '0';
                if (charAt < 0 || 9 < charAt) {
                    return null;
                }
                i = (i * 10) + charAt;
            }
            return Integer.valueOf(i);
        }
    }

    /* compiled from: BuiltInFictitiousFunctionClassFactory.kt */
    private static final class KindWithArity {
        private final int arity;
        @NotNull
        private final Kind kind;

        @NotNull
        public final Kind component1() {
            return this.kind;
        }

        public final int component2() {
            return this.arity;
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (obj instanceof KindWithArity) {
                    KindWithArity kindWithArity = (KindWithArity) obj;
                    if (Intrinsics.areEqual((Object) this.kind, (Object) kindWithArity.kind)) {
                        if (this.arity == kindWithArity.arity) {
                            return true;
                        }
                    }
                }
                return false;
            }
            return true;
        }

        public int hashCode() {
            Kind kind2 = this.kind;
            return ((kind2 != null ? kind2.hashCode() : 0) * 31) + this.arity;
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("KindWithArity(kind=");
            sb.append(this.kind);
            sb.append(", arity=");
            sb.append(this.arity);
            sb.append(")");
            return sb.toString();
        }

        public KindWithArity(@NotNull Kind kind2, int i) {
            Intrinsics.checkParameterIsNotNull(kind2, "kind");
            this.kind = kind2;
            this.arity = i;
        }

        @NotNull
        public final Kind getKind() {
            return this.kind;
        }
    }

    public BuiltInFictitiousFunctionClassFactory(@NotNull StorageManager storageManager2, @NotNull ModuleDescriptor moduleDescriptor) {
        Intrinsics.checkParameterIsNotNull(storageManager2, "storageManager");
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "module");
        this.storageManager = storageManager2;
        this.module = moduleDescriptor;
    }

    public boolean shouldCreateClass(@NotNull FqName fqName, @NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(fqName, "packageFqName");
        Intrinsics.checkParameterIsNotNull(name, "name");
        String asString = name.asString();
        Intrinsics.checkExpressionValueIsNotNull(asString, "name.asString()");
        if ((StringsKt.startsWith$default(asString, "Function", false, 2, null) || StringsKt.startsWith$default(asString, "KFunction", false, 2, null) || StringsKt.startsWith$default(asString, "SuspendFunction", false, 2, null) || StringsKt.startsWith$default(asString, "KSuspendFunction", false, 2, null)) && Companion.parseClassName(asString, fqName) != null) {
            return true;
        }
        return false;
    }

    @Nullable
    public ClassDescriptor createClass(@NotNull ClassId classId) {
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        if (classId.isLocal() || classId.isNestedClass()) {
            return null;
        }
        String asString = classId.getRelativeClassName().asString();
        Intrinsics.checkExpressionValueIsNotNull(asString, "classId.relativeClassName.asString()");
        if (!StringsKt.contains$default((CharSequence) asString, (CharSequence) "Function", false, 2, (Object) null)) {
            return null;
        }
        FqName packageFqName = classId.getPackageFqName();
        Intrinsics.checkExpressionValueIsNotNull(packageFqName, "classId.packageFqName");
        KindWithArity access$parseClassName = Companion.parseClassName(asString, packageFqName);
        if (access$parseClassName == null) {
            return null;
        }
        Kind component1 = access$parseClassName.component1();
        int component2 = access$parseClassName.component2();
        Iterable fragments = this.module.getPackage(packageFqName).getFragments();
        Collection arrayList = new ArrayList();
        for (Object next : fragments) {
            if (next instanceof BuiltInsPackageFragment) {
                arrayList.add(next);
            }
        }
        return new FunctionClassDescriptor(this.storageManager, (BuiltInsPackageFragment) CollectionsKt.first((List) arrayList), component1, component2);
    }

    @NotNull
    public Collection<ClassDescriptor> getAllContributedClassesIfPossible(@NotNull FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "packageFqName");
        return SetsKt.emptySet();
    }
}
