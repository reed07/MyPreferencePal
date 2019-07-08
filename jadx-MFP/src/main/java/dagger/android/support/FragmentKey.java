package dagger.android.support;

import android.support.v4.app.Fragment;
import dagger.MapKey;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@MapKey
@Documented
@Target({ElementType.METHOD})
public @interface FragmentKey {
    Class<? extends Fragment> value();
}
