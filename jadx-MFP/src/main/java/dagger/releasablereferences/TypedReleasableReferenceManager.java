package dagger.releasablereferences;

import dagger.internal.GwtIncompatible;
import java.lang.annotation.Annotation;

@Deprecated
@GwtIncompatible
public interface TypedReleasableReferenceManager<M extends Annotation> extends ReleasableReferenceManager {
    M metadata();
}
