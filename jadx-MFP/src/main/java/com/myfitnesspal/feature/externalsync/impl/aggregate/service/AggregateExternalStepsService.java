package com.myfitnesspal.feature.externalsync.impl.aggregate.service;

import com.myfitnesspal.feature.externalsync.service.ExternalStepsService;
import com.uacf.core.util.Ln;
import dagger.Lazy;
import java.util.Arrays;
import java.util.List;

public class AggregateExternalStepsService implements ExternalStepsService {
    private List<Lazy<? extends ExternalStepsService>> services;

    public AggregateExternalStepsService(Lazy<? extends ExternalStepsService>... lazyArr) {
        this.services = Arrays.asList(lazyArr);
    }

    public void sync() {
        for (Lazy lazy : this.services) {
            try {
                if (((ExternalStepsService) lazy.get()).enabled()) {
                    ((ExternalStepsService) lazy.get()).sync();
                }
            } catch (Exception unused) {
                Ln.e("failed to sync steps for %s", lazy.getClass());
            }
        }
    }

    public boolean enabled() {
        for (Lazy lazy : this.services) {
            if (((ExternalStepsService) lazy.get()).enabled()) {
                return true;
            }
        }
        return false;
    }
}
