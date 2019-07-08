package com.twitter.sdk.android.core.models;

import java.util.Collections;
import java.util.List;

public final class ModelUtils {
    private ModelUtils() {
    }

    public static <T> List<T> getSafeList(List<T> list) {
        if (list == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(list);
    }
}
