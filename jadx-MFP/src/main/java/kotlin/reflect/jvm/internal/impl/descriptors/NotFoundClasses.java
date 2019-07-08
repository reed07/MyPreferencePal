package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassDescriptorBase;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.TypeParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope.Empty;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNotNull;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.ClassTypeConstructorImpl;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: NotFoundClasses.kt */
public final class NotFoundClasses {
    private final MemoizedFunctionToNotNull<ClassRequest, ClassDescriptor> classes = this.storageManager.createMemoizedFunction(new NotFoundClasses$classes$1(this));
    /* access modifiers changed from: private */
    public final ModuleDescriptor module;
    /* access modifiers changed from: private */
    public final MemoizedFunctionToNotNull<FqName, PackageFragmentDescriptor> packageFragments = this.storageManager.createMemoizedFunction(new NotFoundClasses$packageFragments$1(this));
    /* access modifiers changed from: private */
    public final StorageManager storageManager;

    /* compiled from: NotFoundClasses.kt */
    private static final class ClassRequest {
        @NotNull
        private final ClassId classId;
        @NotNull
        private final List<Integer> typeParametersCount;

        @NotNull
        public final ClassId component1() {
            return this.classId;
        }

        @NotNull
        public final List<Integer> component2() {
            return this.typeParametersCount;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
            if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.typeParametersCount, (java.lang.Object) r3.typeParametersCount) != false) goto L_0x001f;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
            /*
                r2 = this;
                if (r2 == r3) goto L_0x001f
                boolean r0 = r3 instanceof kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses.ClassRequest
                if (r0 == 0) goto L_0x001d
                kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses$ClassRequest r3 = (kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses.ClassRequest) r3
                kotlin.reflect.jvm.internal.impl.name.ClassId r0 = r2.classId
                kotlin.reflect.jvm.internal.impl.name.ClassId r1 = r3.classId
                boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
                if (r0 == 0) goto L_0x001d
                java.util.List<java.lang.Integer> r0 = r2.typeParametersCount
                java.util.List<java.lang.Integer> r3 = r3.typeParametersCount
                boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
                if (r3 == 0) goto L_0x001d
                goto L_0x001f
            L_0x001d:
                r3 = 0
                return r3
            L_0x001f:
                r3 = 1
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses.ClassRequest.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            ClassId classId2 = this.classId;
            int i = 0;
            int hashCode = (classId2 != null ? classId2.hashCode() : 0) * 31;
            List<Integer> list = this.typeParametersCount;
            if (list != null) {
                i = list.hashCode();
            }
            return hashCode + i;
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("ClassRequest(classId=");
            sb.append(this.classId);
            sb.append(", typeParametersCount=");
            sb.append(this.typeParametersCount);
            sb.append(")");
            return sb.toString();
        }

        public ClassRequest(@NotNull ClassId classId2, @NotNull List<Integer> list) {
            Intrinsics.checkParameterIsNotNull(classId2, "classId");
            Intrinsics.checkParameterIsNotNull(list, "typeParametersCount");
            this.classId = classId2;
            this.typeParametersCount = list;
        }
    }

    /* compiled from: NotFoundClasses.kt */
    public static final class MockClassDescriptor extends ClassDescriptorBase {
        private final boolean isInner;
        private final ClassTypeConstructorImpl typeConstructor;
        private final List<TypeParameterDescriptor> typeParameters;

        @Nullable
        public ClassDescriptor getCompanionObjectDescriptor() {
            return null;
        }

        @Nullable
        public ClassConstructorDescriptor getUnsubstitutedPrimaryConstructor() {
            return null;
        }

        public boolean isActual() {
            return false;
        }

        public boolean isCompanionObject() {
            return false;
        }

        public boolean isData() {
            return false;
        }

        public boolean isExpect() {
            return false;
        }

        public boolean isExternal() {
            return false;
        }

        public boolean isInline() {
            return false;
        }

        public MockClassDescriptor(@NotNull StorageManager storageManager, @NotNull DeclarationDescriptor declarationDescriptor, @NotNull Name name, boolean z, int i) {
            Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
            Intrinsics.checkParameterIsNotNull(declarationDescriptor, "container");
            Intrinsics.checkParameterIsNotNull(name, "name");
            super(storageManager, declarationDescriptor, name, SourceElement.NO_SOURCE, false);
            this.isInner = z;
            Iterable until = RangesKt.until(0, i);
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(until, 10));
            Iterator it = until.iterator();
            while (it.hasNext()) {
                int nextInt = ((IntIterator) it).nextInt();
                DeclarationDescriptor declarationDescriptor2 = this;
                Annotations empty = Annotations.Companion.getEMPTY();
                Variance variance = Variance.INVARIANT;
                StringBuilder sb = new StringBuilder();
                sb.append('T');
                sb.append(nextInt);
                arrayList.add(TypeParameterDescriptorImpl.createWithDefaultBound(declarationDescriptor2, empty, false, variance, Name.identifier(sb.toString()), nextInt));
            }
            this.typeParameters = (List) arrayList;
            this.typeConstructor = new ClassTypeConstructorImpl(this, this.typeParameters, SetsKt.setOf(DescriptorUtilsKt.getModule(this).getBuiltIns().getAnyType()), storageManager);
        }

        @NotNull
        public ClassKind getKind() {
            return ClassKind.CLASS;
        }

        @NotNull
        public Modality getModality() {
            return Modality.FINAL;
        }

        @NotNull
        public Visibility getVisibility() {
            Visibility visibility = Visibilities.PUBLIC;
            Intrinsics.checkExpressionValueIsNotNull(visibility, "Visibilities.PUBLIC");
            return visibility;
        }

        @NotNull
        public ClassTypeConstructorImpl getTypeConstructor() {
            return this.typeConstructor;
        }

        @NotNull
        public List<TypeParameterDescriptor> getDeclaredTypeParameters() {
            return this.typeParameters;
        }

        public boolean isInner() {
            return this.isInner;
        }

        @NotNull
        public Annotations getAnnotations() {
            return Annotations.Companion.getEMPTY();
        }

        @NotNull
        public Empty getUnsubstitutedMemberScope() {
            return Empty.INSTANCE;
        }

        @NotNull
        public Empty getStaticScope() {
            return Empty.INSTANCE;
        }

        @NotNull
        public Collection<ClassConstructorDescriptor> getConstructors() {
            return SetsKt.emptySet();
        }

        @NotNull
        public Collection<ClassDescriptor> getSealedSubclasses() {
            return CollectionsKt.emptyList();
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("class ");
            sb.append(getName());
            sb.append(" (not found)");
            return sb.toString();
        }
    }

    public NotFoundClasses(@NotNull StorageManager storageManager2, @NotNull ModuleDescriptor moduleDescriptor) {
        Intrinsics.checkParameterIsNotNull(storageManager2, "storageManager");
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "module");
        this.storageManager = storageManager2;
        this.module = moduleDescriptor;
    }

    @NotNull
    public final ClassDescriptor getClass(@NotNull ClassId classId, @NotNull List<Integer> list) {
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        Intrinsics.checkParameterIsNotNull(list, "typeParametersCount");
        return (ClassDescriptor) this.classes.invoke(new ClassRequest(classId, list));
    }
}
