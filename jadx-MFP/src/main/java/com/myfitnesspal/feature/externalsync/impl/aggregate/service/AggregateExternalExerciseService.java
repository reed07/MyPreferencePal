package com.myfitnesspal.feature.externalsync.impl.aggregate.service;

import com.myfitnesspal.feature.externalsync.service.ExternalExerciseService;
import com.myfitnesspal.feature.externalsync.service.ExternalSyncCooldown;
import com.myfitnesspal.shared.model.v2.MfpExerciseEntry;
import com.uacf.core.util.Ln;
import dagger.Lazy;
import java.util.Arrays;
import java.util.List;

public class AggregateExternalExerciseService implements ExternalExerciseService {
    private List<Lazy<? extends ExternalExerciseService>> services;

    public AggregateExternalExerciseService(Lazy<? extends ExternalExerciseService>... lazyArr) {
        this.services = Arrays.asList(lazyArr);
    }

    public void sync() {
        for (Lazy lazy : this.services) {
            try {
                if (((ExternalExerciseService) lazy.get()).enabled()) {
                    ((ExternalExerciseService) lazy.get()).sync();
                }
            } catch (Exception unused) {
                Ln.e("failed to sync activity for %s", ((ExternalExerciseService) lazy.get()).getClass());
            }
        }
    }

    public void onExerciseEntryInserted(MfpExerciseEntry mfpExerciseEntry, String str) {
        ExternalSyncCooldown.clear();
        for (Lazy lazy : this.services) {
            try {
                ((ExternalExerciseService) lazy.get()).onExerciseEntryInserted(mfpExerciseEntry, str);
            } catch (Exception unused) {
                Ln.e("onExerciseEntryInserted failed for %s", ((ExternalExerciseService) lazy.get()).getClass());
            }
        }
    }

    public void onExerciseEntryUpdated(MfpExerciseEntry mfpExerciseEntry, String str) {
        ExternalSyncCooldown.clear();
        for (Lazy lazy : this.services) {
            try {
                ((ExternalExerciseService) lazy.get()).onExerciseEntryUpdated(mfpExerciseEntry, str);
            } catch (Exception unused) {
                Ln.e("onExerciseEntryUpdated failed for %s", ((ExternalExerciseService) lazy.get()).getClass());
            }
        }
    }

    public void onExerciseEntryDeleted(MfpExerciseEntry mfpExerciseEntry, String str) {
        ExternalSyncCooldown.clear();
        for (Lazy lazy : this.services) {
            try {
                ((ExternalExerciseService) lazy.get()).onExerciseEntryDeleted(mfpExerciseEntry, str);
            } catch (Exception unused) {
                Ln.e("onExerciseEntryDeleted failed for %s", ((ExternalExerciseService) lazy.get()).getClass());
            }
        }
    }

    public boolean enabled() {
        for (Lazy lazy : this.services) {
            if (((ExternalExerciseService) lazy.get()).enabled()) {
                return true;
            }
        }
        return false;
    }
}
