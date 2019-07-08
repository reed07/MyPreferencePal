package com.myfitnesspal.feature.externalsync.impl.googlefit.service;

import java.util.concurrent.TimeUnit;

public interface GoogleFitServiceBase {
    public static final long AWAIT_MAX_TIME_SEC = 30;
    public static final TimeUnit AWAIT_TIME_UNIT = TimeUnit.SECONDS;
}
