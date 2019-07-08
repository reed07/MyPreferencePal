package dagger.releasablereferences;

import dagger.internal.GwtIncompatible;
import java.lang.annotation.Annotation;

@Deprecated
@GwtIncompatible
public interface ReleasableReferenceManager {
    void releaseStrongReferences();

    void restoreStrongReferences();

    Class<? extends Annotation> scope();
}
