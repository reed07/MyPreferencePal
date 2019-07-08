package androidx.versionedparcelable;

import android.support.annotation.RestrictTo;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@RestrictTo
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
public @interface ParcelField {
}
