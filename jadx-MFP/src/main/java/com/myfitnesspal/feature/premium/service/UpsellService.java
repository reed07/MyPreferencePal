package com.myfitnesspal.feature.premium.service;

import com.myfitnesspal.feature.premium.model.MfpUpsellConfig;
import com.uacf.core.util.Function1;

public interface UpsellService {
    void getUpsellConfig(Function1<MfpUpsellConfig> function1, String str);
}
