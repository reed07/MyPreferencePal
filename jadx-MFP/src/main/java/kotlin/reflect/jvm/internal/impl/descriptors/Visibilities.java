package kotlin.reflect.jvm.internal.impl.descriptors;

import com.google.android.gms.common.internal.ImagesContract;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;
import kotlin.collections.SetsKt;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.TypeAliasConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.SuperCallReceiverValue;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ThisClassReceiver;
import kotlin.reflect.jvm.internal.impl.types.DynamicTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.util.ModuleVisibilityHelper;
import kotlin.reflect.jvm.internal.impl.util.ModuleVisibilityHelper.EMPTY;
import kotlin.reflect.jvm.internal.impl.utils.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Visibilities {
    public static final ReceiverValue ALWAYS_SUITABLE_RECEIVER = new ReceiverValue() {
        @NotNull
        public KotlinType getType() {
            throw new IllegalStateException("This method should not be called");
        }
    };
    public static final Visibility DEFAULT_VISIBILITY = PUBLIC;
    @Deprecated
    public static final ReceiverValue FALSE_IF_PROTECTED = new ReceiverValue() {
        @NotNull
        public KotlinType getType() {
            throw new IllegalStateException("This method should not be called");
        }
    };
    @NotNull
    public static final Visibility INHERITED = new Visibility("inherited", false) {
        public boolean isVisible(@Nullable ReceiverValue receiverValue, @NotNull DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, @NotNull DeclarationDescriptor declarationDescriptor) {
            throw new IllegalStateException("Visibility is unknown yet");
        }
    };
    @NotNull
    public static final Visibility INTERNAL = new Visibility("internal", false) {
        public boolean isVisible(@Nullable ReceiverValue receiverValue, @NotNull DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, @NotNull DeclarationDescriptor declarationDescriptor) {
            if (!DescriptorUtils.getContainingModule(declarationDescriptor).shouldSeeInternalsOf(DescriptorUtils.getContainingModule(declarationDescriptorWithVisibility))) {
                return false;
            }
            return Visibilities.MODULE_VISIBILITY_HELPER.isInFriendModule(declarationDescriptorWithVisibility, declarationDescriptor);
        }
    };
    @NotNull
    public static final Visibility INVISIBLE_FAKE = new Visibility("invisible_fake", false) {
        public boolean isVisible(@Nullable ReceiverValue receiverValue, @NotNull DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, @NotNull DeclarationDescriptor declarationDescriptor) {
            return false;
        }
    };
    public static final Set<Visibility> INVISIBLE_FROM_OTHER_MODULES = Collections.unmodifiableSet(SetsKt.setOf(PRIVATE, PRIVATE_TO_THIS, INTERNAL, LOCAL));
    /* access modifiers changed from: private */
    public static final ReceiverValue IRRELEVANT_RECEIVER = new ReceiverValue() {
        @NotNull
        public KotlinType getType() {
            throw new IllegalStateException("This method should not be called");
        }
    };
    @NotNull
    public static final Visibility LOCAL = new Visibility(ImagesContract.LOCAL, false) {
        public boolean isVisible(@Nullable ReceiverValue receiverValue, @NotNull DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, @NotNull DeclarationDescriptor declarationDescriptor) {
            throw new IllegalStateException("This method shouldn't be invoked for LOCAL visibility");
        }
    };
    /* access modifiers changed from: private */
    @NotNull
    public static final ModuleVisibilityHelper MODULE_VISIBILITY_HELPER;
    private static final Map<Visibility, Integer> ORDERED_VISIBILITIES;
    @NotNull
    public static final Visibility PRIVATE = new Visibility("private", false) {
        private boolean hasContainingSourceFile(@NotNull DeclarationDescriptor declarationDescriptor) {
            return DescriptorUtils.getContainingSourceFile(declarationDescriptor) != SourceFile.NO_SOURCE_FILE;
        }

        /* JADX WARNING: Incorrect type for immutable var: ssa=kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility, code=kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor, for r5v0, types: [kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean isVisible(@org.jetbrains.annotations.Nullable kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue r4, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r5, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r6) {
            /*
                r3 = this;
                boolean r4 = kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils.isTopLevelDeclaration(r5)
                if (r4 == 0) goto L_0x0011
                boolean r4 = r3.hasContainingSourceFile(r6)
                if (r4 == 0) goto L_0x0011
                boolean r4 = kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.inSameFile(r5, r6)
                return r4
            L_0x0011:
                boolean r4 = r5 instanceof kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor
                r0 = 1
                if (r4 == 0) goto L_0x003e
                r4 = r5
                kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor r4 = (kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor) r4
                kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters r4 = r4.getContainingDeclaration()
                boolean r1 = kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils.isSealedClass(r4)
                if (r1 == 0) goto L_0x003e
                boolean r4 = kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils.isTopLevelDeclaration(r4)
                if (r4 == 0) goto L_0x003e
                boolean r4 = r6 instanceof kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor
                if (r4 == 0) goto L_0x003e
                kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r4 = r6.getContainingDeclaration()
                boolean r4 = kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils.isTopLevelDeclaration(r4)
                if (r4 == 0) goto L_0x003e
                boolean r4 = kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.inSameFile(r5, r6)
                if (r4 == 0) goto L_0x003e
                return r0
            L_0x003e:
                if (r5 == 0) goto L_0x0052
                kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r5 = r5.getContainingDeclaration()
                boolean r4 = r5 instanceof kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
                if (r4 == 0) goto L_0x004e
                boolean r4 = kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils.isCompanionObject(r5)
                if (r4 == 0) goto L_0x0052
            L_0x004e:
                boolean r4 = r5 instanceof kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor
                if (r4 == 0) goto L_0x003e
            L_0x0052:
                r4 = 0
                if (r5 != 0) goto L_0x0056
                return r4
            L_0x0056:
                if (r6 == 0) goto L_0x0084
                if (r5 != r6) goto L_0x005b
                return r0
            L_0x005b:
                boolean r1 = r6 instanceof kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor
                if (r1 == 0) goto L_0x007f
                boolean r1 = r5 instanceof kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor
                if (r1 == 0) goto L_0x007e
                r1 = r5
                kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor r1 = (kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor) r1
                kotlin.reflect.jvm.internal.impl.name.FqName r1 = r1.getFqName()
                r2 = r6
                kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor r2 = (kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor) r2
                kotlin.reflect.jvm.internal.impl.name.FqName r2 = r2.getFqName()
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x007e
                boolean r5 = kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils.areInSameModule(r6, r5)
                if (r5 == 0) goto L_0x007e
                r4 = 1
            L_0x007e:
                return r4
            L_0x007f:
                kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r6 = r6.getContainingDeclaration()
                goto L_0x0056
            L_0x0084:
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.AnonymousClass1.isVisible(kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor):boolean");
        }
    };
    @NotNull
    public static final Visibility PRIVATE_TO_THIS = new Visibility("private_to_this", false) {
        @NotNull
        public String getDisplayName() {
            return "private/*private to this*/";
        }

        public boolean isVisible(@Nullable ReceiverValue receiverValue, @NotNull DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, @NotNull DeclarationDescriptor declarationDescriptor) {
            if (Visibilities.PRIVATE.isVisible(receiverValue, declarationDescriptorWithVisibility, declarationDescriptor)) {
                if (receiverValue == Visibilities.ALWAYS_SUITABLE_RECEIVER) {
                    return true;
                }
                if (receiverValue == Visibilities.IRRELEVANT_RECEIVER) {
                    return false;
                }
                DeclarationDescriptor parentOfType = DescriptorUtils.getParentOfType(declarationDescriptorWithVisibility, ClassDescriptor.class);
                if (parentOfType != null && (receiverValue instanceof ThisClassReceiver)) {
                    return ((ThisClassReceiver) receiverValue).getClassDescriptor().getOriginal().equals(parentOfType.getOriginal());
                }
            }
            return false;
        }
    };
    @NotNull
    public static final Visibility PROTECTED = new Visibility("protected", true) {
        public boolean isVisible(@Nullable ReceiverValue receiverValue, @NotNull DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, @NotNull DeclarationDescriptor declarationDescriptor) {
            ClassDescriptor classDescriptor = (ClassDescriptor) DescriptorUtils.getParentOfType(declarationDescriptorWithVisibility, ClassDescriptor.class);
            ClassDescriptor classDescriptor2 = (ClassDescriptor) DescriptorUtils.getParentOfType(declarationDescriptor, ClassDescriptor.class, false);
            if (classDescriptor2 == null) {
                return false;
            }
            if (classDescriptor != null && DescriptorUtils.isCompanionObject(classDescriptor)) {
                ClassDescriptor classDescriptor3 = (ClassDescriptor) DescriptorUtils.getParentOfType(classDescriptor, ClassDescriptor.class);
                if (classDescriptor3 != null && DescriptorUtils.isSubclass(classDescriptor2, classDescriptor3)) {
                    return true;
                }
            }
            DeclarationDescriptorWithVisibility unwrapFakeOverrideToAnyDeclaration = DescriptorUtils.unwrapFakeOverrideToAnyDeclaration(declarationDescriptorWithVisibility);
            ClassDescriptor classDescriptor4 = (ClassDescriptor) DescriptorUtils.getParentOfType(unwrapFakeOverrideToAnyDeclaration, ClassDescriptor.class);
            if (classDescriptor4 == null) {
                return false;
            }
            if (!DescriptorUtils.isSubclass(classDescriptor2, classDescriptor4) || !doesReceiverFitForProtectedVisibility(receiverValue, unwrapFakeOverrideToAnyDeclaration, classDescriptor2)) {
                return isVisible(receiverValue, declarationDescriptorWithVisibility, classDescriptor2.getContainingDeclaration());
            }
            return true;
        }

        private boolean doesReceiverFitForProtectedVisibility(@Nullable ReceiverValue receiverValue, @NotNull DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, @NotNull ClassDescriptor classDescriptor) {
            boolean z = false;
            if (receiverValue == Visibilities.FALSE_IF_PROTECTED) {
                return false;
            }
            if (!(declarationDescriptorWithVisibility instanceof CallableMemberDescriptor) || (declarationDescriptorWithVisibility instanceof ConstructorDescriptor) || receiverValue == Visibilities.ALWAYS_SUITABLE_RECEIVER) {
                return true;
            }
            if (receiverValue == Visibilities.IRRELEVANT_RECEIVER || receiverValue == null) {
                return false;
            }
            KotlinType thisType = receiverValue instanceof SuperCallReceiverValue ? ((SuperCallReceiverValue) receiverValue).getThisType() : receiverValue.getType();
            if (DescriptorUtils.isSubtypeOfClass(thisType, classDescriptor) || DynamicTypesKt.isDynamic(thisType)) {
                z = true;
            }
            return z;
        }
    };
    @NotNull
    public static final Visibility PUBLIC = new Visibility("public", true) {
        public boolean isVisible(@Nullable ReceiverValue receiverValue, @NotNull DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, @NotNull DeclarationDescriptor declarationDescriptor) {
            return true;
        }
    };
    @NotNull
    public static final Visibility UNKNOWN = new Visibility("unknown", false) {
        public boolean isVisible(@Nullable ReceiverValue receiverValue, @NotNull DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, @NotNull DeclarationDescriptor declarationDescriptor) {
            return false;
        }
    };

    static {
        HashMap newHashMapWithExpectedSize = CollectionsKt.newHashMapWithExpectedSize(4);
        newHashMapWithExpectedSize.put(PRIVATE_TO_THIS, Integer.valueOf(0));
        newHashMapWithExpectedSize.put(PRIVATE, Integer.valueOf(0));
        newHashMapWithExpectedSize.put(INTERNAL, Integer.valueOf(1));
        newHashMapWithExpectedSize.put(PROTECTED, Integer.valueOf(1));
        newHashMapWithExpectedSize.put(PUBLIC, Integer.valueOf(2));
        ORDERED_VISIBILITIES = Collections.unmodifiableMap(newHashMapWithExpectedSize);
        Iterator it = ServiceLoader.load(ModuleVisibilityHelper.class, ModuleVisibilityHelper.class.getClassLoader()).iterator();
        MODULE_VISIBILITY_HELPER = it.hasNext() ? (ModuleVisibilityHelper) it.next() : EMPTY.INSTANCE;
    }

    public static boolean isVisibleIgnoringReceiver(@NotNull DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, @NotNull DeclarationDescriptor declarationDescriptor) {
        return findInvisibleMember(ALWAYS_SUITABLE_RECEIVER, declarationDescriptorWithVisibility, declarationDescriptor) == null;
    }

    public static boolean inSameFile(@NotNull DeclarationDescriptor declarationDescriptor, @NotNull DeclarationDescriptor declarationDescriptor2) {
        SourceFile containingSourceFile = DescriptorUtils.getContainingSourceFile(declarationDescriptor2);
        if (containingSourceFile != SourceFile.NO_SOURCE_FILE) {
            return containingSourceFile.equals(DescriptorUtils.getContainingSourceFile(declarationDescriptor));
        }
        return false;
    }

    @Nullable
    public static DeclarationDescriptorWithVisibility findInvisibleMember(@Nullable ReceiverValue receiverValue, @NotNull DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, @NotNull DeclarationDescriptor declarationDescriptor) {
        DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility2 = (DeclarationDescriptorWithVisibility) declarationDescriptorWithVisibility.getOriginal();
        while (declarationDescriptorWithVisibility2 != null && declarationDescriptorWithVisibility2.getVisibility() != LOCAL) {
            if (!declarationDescriptorWithVisibility2.getVisibility().isVisible(receiverValue, declarationDescriptorWithVisibility2, declarationDescriptor)) {
                return declarationDescriptorWithVisibility2;
            }
            declarationDescriptorWithVisibility2 = (DeclarationDescriptorWithVisibility) DescriptorUtils.getParentOfType(declarationDescriptorWithVisibility2, DeclarationDescriptorWithVisibility.class);
        }
        if (declarationDescriptorWithVisibility instanceof TypeAliasConstructorDescriptor) {
            DeclarationDescriptorWithVisibility findInvisibleMember = findInvisibleMember(receiverValue, ((TypeAliasConstructorDescriptor) declarationDescriptorWithVisibility).getUnderlyingConstructorDescriptor(), declarationDescriptor);
            if (findInvisibleMember != null) {
                return findInvisibleMember;
            }
        }
        return null;
    }

    @Nullable
    static Integer compareLocal(@NotNull Visibility visibility, @NotNull Visibility visibility2) {
        if (visibility == visibility2) {
            return Integer.valueOf(0);
        }
        Integer num = (Integer) ORDERED_VISIBILITIES.get(visibility);
        Integer num2 = (Integer) ORDERED_VISIBILITIES.get(visibility2);
        if (num == null || num2 == null || num.equals(num2)) {
            return null;
        }
        return Integer.valueOf(num.intValue() - num2.intValue());
    }

    @Nullable
    public static Integer compare(@NotNull Visibility visibility, @NotNull Visibility visibility2) {
        Integer compareTo = visibility.compareTo(visibility2);
        if (compareTo != null) {
            return compareTo;
        }
        Integer compareTo2 = visibility2.compareTo(visibility);
        if (compareTo2 != null) {
            return Integer.valueOf(-compareTo2.intValue());
        }
        return null;
    }

    public static boolean isPrivate(@NotNull Visibility visibility) {
        return visibility == PRIVATE || visibility == PRIVATE_TO_THIS;
    }
}
