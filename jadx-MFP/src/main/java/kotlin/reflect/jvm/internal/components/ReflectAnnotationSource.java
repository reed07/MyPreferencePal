package kotlin.reflect.jvm.internal.components;

import java.lang.annotation.Annotation;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceFile;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lkotlin/reflect/jvm/internal/components/ReflectAnnotationSource;", "Lkotlin/reflect/jvm/internal/impl/descriptors/SourceElement;", "annotation", "", "(Ljava/lang/annotation/Annotation;)V", "getAnnotation", "()Ljava/lang/annotation/Annotation;", "getContainingFile", "Lkotlin/reflect/jvm/internal/impl/descriptors/SourceFile;", "descriptors.runtime"}, k = 1, mv = {1, 1, 13})
/* compiled from: ReflectAnnotationSource.kt */
public final class ReflectAnnotationSource implements SourceElement {
    @NotNull
    private final Annotation annotation;

    public ReflectAnnotationSource(@NotNull Annotation annotation2) {
        Intrinsics.checkParameterIsNotNull(annotation2, "annotation");
        this.annotation = annotation2;
    }

    @NotNull
    public final Annotation getAnnotation() {
        return this.annotation;
    }

    @NotNull
    public SourceFile getContainingFile() {
        SourceFile sourceFile = SourceFile.NO_SOURCE_FILE;
        Intrinsics.checkExpressionValueIsNotNull(sourceFile, "SourceFile.NO_SOURCE_FILE");
        return sourceFile;
    }
}
