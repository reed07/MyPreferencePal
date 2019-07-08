package android.arch.lifecycle;

import android.support.annotation.RestrictTo;
import java.util.HashMap;
import java.util.Map;

@RestrictTo
public class MethodCallsLogger {
    private Map<String, Integer> mCalledMethods = new HashMap();
}
