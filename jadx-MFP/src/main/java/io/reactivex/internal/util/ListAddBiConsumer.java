package io.reactivex.internal.util;

import io.reactivex.functions.BiFunction;
import java.util.List;

public enum ListAddBiConsumer implements BiFunction<List, Object, List> {
    INSTANCE;

    public List apply(List list, Object obj) throws Exception {
        list.add(obj);
        return list;
    }
}
