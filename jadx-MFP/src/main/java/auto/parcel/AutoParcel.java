package auto.parcel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface AutoParcel {

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Builder {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Validate {
    }
}
