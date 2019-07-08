package android.support.transition;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.util.AttributeSet;
import java.lang.reflect.Constructor;

public class TransitionInflater {
    private static final ArrayMap<String, Constructor> CONSTRUCTORS = new ArrayMap<>();
    private static final Class<?>[] CONSTRUCTOR_SIGNATURE = {Context.class, AttributeSet.class};
}
