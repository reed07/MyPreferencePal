package kotlin.reflect.jvm.internal.impl.descriptors;

import org.jetbrains.annotations.NotNull;

public interface SourceElement {
    public static final SourceElement NO_SOURCE = new SourceElement() {
        public String toString() {
            return "NO_SOURCE";
        }

        @NotNull
        public SourceFile getContainingFile() {
            return SourceFile.NO_SOURCE_FILE;
        }
    };

    @NotNull
    SourceFile getContainingFile();
}
