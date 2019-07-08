package kotlin.reflect.jvm.internal.components;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceFile;
import kotlin.reflect.jvm.internal.impl.load.java.sources.JavaSourceElement;
import kotlin.reflect.jvm.internal.impl.load.java.sources.JavaSourceElementFactory;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaElement;
import kotlin.reflect.jvm.internal.structure.ReflectJavaElement;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0007B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\b"}, d2 = {"Lkotlin/reflect/jvm/internal/components/RuntimeSourceElementFactory;", "Lkotlin/reflect/jvm/internal/impl/load/java/sources/JavaSourceElementFactory;", "()V", "source", "Lkotlin/reflect/jvm/internal/impl/load/java/sources/JavaSourceElement;", "javaElement", "Lkotlin/reflect/jvm/internal/impl/load/java/structure/JavaElement;", "RuntimeSourceElement", "descriptors.runtime"}, k = 1, mv = {1, 1, 13})
/* compiled from: RuntimeSourceElementFactory.kt */
public final class RuntimeSourceElementFactory implements JavaSourceElementFactory {
    public static final RuntimeSourceElementFactory INSTANCE = new RuntimeSourceElementFactory();

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"Lkotlin/reflect/jvm/internal/components/RuntimeSourceElementFactory$RuntimeSourceElement;", "Lkotlin/reflect/jvm/internal/impl/load/java/sources/JavaSourceElement;", "javaElement", "Lkotlin/reflect/jvm/internal/structure/ReflectJavaElement;", "(Lkotlin/reflect/jvm/internal/structure/ReflectJavaElement;)V", "getJavaElement", "()Lkotlin/reflect/jvm/internal/structure/ReflectJavaElement;", "getContainingFile", "Lkotlin/reflect/jvm/internal/impl/descriptors/SourceFile;", "toString", "", "descriptors.runtime"}, k = 1, mv = {1, 1, 13})
    /* compiled from: RuntimeSourceElementFactory.kt */
    public static final class RuntimeSourceElement implements JavaSourceElement {
        @NotNull
        private final ReflectJavaElement javaElement;

        public RuntimeSourceElement(@NotNull ReflectJavaElement reflectJavaElement) {
            Intrinsics.checkParameterIsNotNull(reflectJavaElement, "javaElement");
            this.javaElement = reflectJavaElement;
        }

        @NotNull
        public ReflectJavaElement getJavaElement() {
            return this.javaElement;
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(getClass().getName());
            sb.append(": ");
            sb.append(getJavaElement().toString());
            return sb.toString();
        }

        @NotNull
        public SourceFile getContainingFile() {
            SourceFile sourceFile = SourceFile.NO_SOURCE_FILE;
            Intrinsics.checkExpressionValueIsNotNull(sourceFile, "SourceFile.NO_SOURCE_FILE");
            return sourceFile;
        }
    }

    private RuntimeSourceElementFactory() {
    }

    @NotNull
    public JavaSourceElement source(@NotNull JavaElement javaElement) {
        Intrinsics.checkParameterIsNotNull(javaElement, "javaElement");
        return new RuntimeSourceElement((ReflectJavaElement) javaElement);
    }
}
