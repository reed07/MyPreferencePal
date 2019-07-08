package com.brightcove.player.util.functional;

import android.support.annotation.NonNull;

public interface Function2<I1, I2, O> {
    @NonNull
    O apply(@NonNull I1 i1, @NonNull I2 i2) throws Exception;
}
