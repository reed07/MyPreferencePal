package com.myfitnesspal.shared.service.syncv2.ops;

import com.myfitnesspal.shared.geolocation.GeoLocationService;
import com.uacf.sync.engine.UacfScheduleContext;
import com.uacf.sync.engine.UacfScheduleException;
import com.uacf.sync.engine.UacfScheduleOp.Progress;
import com.uacf.sync.engine.UacfScheduleOp.Result;
import com.uacf.sync.engine.UacfScheduleOpBase;
import dagger.Lazy;

public class GeoLocationOp extends UacfScheduleOpBase {
    private Lazy<GeoLocationService> geoLocationService;

    public GeoLocationOp(Lazy<GeoLocationService> lazy) {
        this.geoLocationService = lazy;
    }

    public Result sync(UacfScheduleContext uacfScheduleContext, Progress progress) throws UacfScheduleException {
        ((GeoLocationService) this.geoLocationService.get()).refreshSync();
        return Result.completed();
    }
}
