package com.myfitnesspal.shared.service.analytics;

import android.content.SharedPreferences;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.util.SharedPreferencesObjectQueue;

public class MfpAnalyticsTaskQueue extends SharedPreferencesObjectQueue<MfpAnalyticsTask> {
    private static final int MAX_QUEUE_SIZE = 1024;

    public MfpAnalyticsTaskQueue(SharedPreferences sharedPreferences, ApiJsonMapper apiJsonMapper) {
        super(sharedPreferences, apiJsonMapper);
    }

    public void clear() {
        for (int size = size() - 1; size >= 0; size--) {
            remove();
        }
    }

    public void add(MfpAnalyticsTask mfpAnalyticsTask) {
        if (mfpAnalyticsTask != null) {
            if (size() >= MAX_QUEUE_SIZE) {
                remove();
            }
            super.add(mfpAnalyticsTask);
        }
    }
}
