package android.support.v4.media;

import android.support.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class VolumeProviderCompat {

    public static abstract class Callback {
    }

    @RestrictTo
    @Retention(RetentionPolicy.SOURCE)
    public @interface ControlType {
    }
}
