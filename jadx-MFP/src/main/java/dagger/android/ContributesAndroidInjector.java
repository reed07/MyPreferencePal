package dagger.android;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
public @interface ContributesAndroidInjector {
    Class<?>[] modules() default {};
}
