package com.myfitnesspal.feature.externalsync.service;

import java.util.Date;

public interface ExternalUserService extends ExternalService {
    void onWeightUpdated(Date date, float f, String str);
}
