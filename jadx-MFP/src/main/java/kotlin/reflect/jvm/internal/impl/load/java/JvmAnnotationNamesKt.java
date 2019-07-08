package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;

/* compiled from: JvmAnnotationNames.kt */
public final class JvmAnnotationNamesKt {
    @NotNull
    private static final FqName ANDROIDX_RECENTLY_NON_NULL_ANNOTATION = new FqName("androidx.annotation.RecentlyNonNull");
    @NotNull
    private static final FqName ANDROIDX_RECENTLY_NULLABLE_ANNOTATION = new FqName("androidx.annotation.RecentlyNullable");
    @NotNull
    private static final FqName COMPATQUAL_NONNULL_ANNOTATION = new FqName("org.checkerframework.checker.nullness.compatqual.NonNullDecl");
    @NotNull
    private static final FqName COMPATQUAL_NULLABLE_ANNOTATION = new FqName("org.checkerframework.checker.nullness.compatqual.NullableDecl");
    @NotNull
    private static final FqName JAVAX_CHECKFORNULL_ANNOTATION = new FqName("javax.annotation.CheckForNull");
    @NotNull
    private static final FqName JAVAX_NONNULL_ANNOTATION = new FqName("javax.annotation.Nonnull");
    @NotNull
    private static final List<FqName> MUTABLE_ANNOTATIONS = CollectionsKt.listOf(JvmAnnotationNames.JETBRAINS_MUTABLE_ANNOTATION, JvmAnnotationNames.MUTABLE_ANNOTATION);
    @NotNull
    private static final List<FqName> NOT_NULL_ANNOTATIONS = CollectionsKt.listOf(JvmAnnotationNames.JETBRAINS_NOT_NULL_ANNOTATION, new FqName("edu.umd.cs.findbugs.annotations.NonNull"), new FqName("androidx.annotation.NonNull"), new FqName("android.support.annotation.NonNull"), new FqName("com.android.annotations.NonNull"), new FqName("org.eclipse.jdt.annotation.NonNull"), new FqName("org.checkerframework.checker.nullness.qual.NonNull"), new FqName("lombok.NonNull"), new FqName("io.reactivex.annotations.NonNull"));
    @NotNull
    private static final Set<FqName> NULLABILITY_ANNOTATIONS = SetsKt.plus(SetsKt.plus(SetsKt.plus(SetsKt.plus(SetsKt.plus(SetsKt.plus(SetsKt.plus((Set<? extends T>) new LinkedHashSet<Object>(), (Iterable<? extends T>) NULLABLE_ANNOTATIONS), JAVAX_NONNULL_ANNOTATION), (Iterable<? extends T>) NOT_NULL_ANNOTATIONS), COMPATQUAL_NULLABLE_ANNOTATION), COMPATQUAL_NONNULL_ANNOTATION), ANDROIDX_RECENTLY_NULLABLE_ANNOTATION), ANDROIDX_RECENTLY_NON_NULL_ANNOTATION);
    @NotNull
    private static final List<FqName> NULLABLE_ANNOTATIONS = CollectionsKt.listOf(JvmAnnotationNames.JETBRAINS_NULLABLE_ANNOTATION, new FqName("androidx.annotation.Nullable"), new FqName("android.support.annotation.Nullable"), new FqName("com.android.annotations.Nullable"), new FqName("org.eclipse.jdt.annotation.Nullable"), new FqName("org.checkerframework.checker.nullness.qual.Nullable"), new FqName("javax.annotation.Nullable"), new FqName("javax.annotation.CheckForNull"), new FqName("edu.umd.cs.findbugs.annotations.CheckForNull"), new FqName("edu.umd.cs.findbugs.annotations.Nullable"), new FqName("edu.umd.cs.findbugs.annotations.PossiblyNull"), new FqName("io.reactivex.annotations.Nullable"));
    @NotNull
    private static final List<FqName> READ_ONLY_ANNOTATIONS = CollectionsKt.listOf(JvmAnnotationNames.JETBRAINS_READONLY_ANNOTATION, JvmAnnotationNames.READONLY_ANNOTATION);

    @NotNull
    public static final List<FqName> getNULLABLE_ANNOTATIONS() {
        return NULLABLE_ANNOTATIONS;
    }

    @NotNull
    public static final FqName getJAVAX_NONNULL_ANNOTATION() {
        return JAVAX_NONNULL_ANNOTATION;
    }

    @NotNull
    public static final FqName getJAVAX_CHECKFORNULL_ANNOTATION() {
        return JAVAX_CHECKFORNULL_ANNOTATION;
    }

    @NotNull
    public static final List<FqName> getNOT_NULL_ANNOTATIONS() {
        return NOT_NULL_ANNOTATIONS;
    }

    @NotNull
    public static final FqName getCOMPATQUAL_NULLABLE_ANNOTATION() {
        return COMPATQUAL_NULLABLE_ANNOTATION;
    }

    @NotNull
    public static final FqName getCOMPATQUAL_NONNULL_ANNOTATION() {
        return COMPATQUAL_NONNULL_ANNOTATION;
    }

    @NotNull
    public static final FqName getANDROIDX_RECENTLY_NULLABLE_ANNOTATION() {
        return ANDROIDX_RECENTLY_NULLABLE_ANNOTATION;
    }

    @NotNull
    public static final FqName getANDROIDX_RECENTLY_NON_NULL_ANNOTATION() {
        return ANDROIDX_RECENTLY_NON_NULL_ANNOTATION;
    }

    @NotNull
    public static final List<FqName> getREAD_ONLY_ANNOTATIONS() {
        return READ_ONLY_ANNOTATIONS;
    }

    @NotNull
    public static final List<FqName> getMUTABLE_ANNOTATIONS() {
        return MUTABLE_ANNOTATIONS;
    }
}
