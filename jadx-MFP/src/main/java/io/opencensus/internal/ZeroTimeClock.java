package io.opencensus.internal;

import io.opencensus.common.Clock;
import io.opencensus.common.Timestamp;
import javax.annotation.concurrent.Immutable;

@Immutable
public final class ZeroTimeClock extends Clock {
    private static final ZeroTimeClock INSTANCE = new ZeroTimeClock();
    private static final Timestamp ZERO_TIMESTAMP = Timestamp.create(0, 0);

    private ZeroTimeClock() {
    }
}
