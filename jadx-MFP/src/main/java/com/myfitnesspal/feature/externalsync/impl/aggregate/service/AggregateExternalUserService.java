package com.myfitnesspal.feature.externalsync.impl.aggregate.service;

import com.myfitnesspal.feature.externalsync.service.ExternalSyncCooldown;
import com.myfitnesspal.feature.externalsync.service.ExternalUserService;
import com.uacf.core.util.Ln;
import dagger.Lazy;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AggregateExternalUserService implements ExternalUserService {
    private List<Lazy<? extends ExternalUserService>> services;

    public AggregateExternalUserService(Lazy<? extends ExternalUserService>... lazyArr) {
        this.services = Arrays.asList(lazyArr);
    }

    public void sync() {
        for (Lazy lazy : this.services) {
            try {
                if (((ExternalUserService) lazy.get()).enabled()) {
                    ((ExternalUserService) lazy.get()).sync();
                }
            } catch (Exception unused) {
                Ln.e("failed to sync user data for %s", lazy.getClass());
            }
        }
    }

    public boolean enabled() {
        for (Lazy lazy : this.services) {
            if (((ExternalUserService) lazy.get()).enabled()) {
                return true;
            }
        }
        return false;
    }

    public void onWeightUpdated(Date date, float f, String str) {
        ExternalSyncCooldown.clear();
        for (Lazy lazy : this.services) {
            try {
                ((ExternalUserService) lazy.get()).onWeightUpdated(date, f, str);
            } catch (Exception unused) {
                Ln.e("failed to insert user data entry for %s", ((ExternalUserService) lazy.get()).getClass());
            }
        }
    }
}
