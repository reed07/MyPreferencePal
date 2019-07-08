package com.myfitnesspal.shared.service.analytics;

import java.util.List;

public interface AnalyticsServiceWithHistory extends AnalyticsService {
    void clearHistory();

    List<MfpEventHistoryItem> getEventHistory();
}
