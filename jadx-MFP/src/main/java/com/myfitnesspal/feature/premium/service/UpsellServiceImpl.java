package com.myfitnesspal.feature.premium.service;

import android.content.Context;
import com.myfitnesspal.feature.premium.model.MfpUpsellConfig;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.uacf.core.asyncservice.SimpleAsyncServiceBase;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Ln;
import dagger.Lazy;

public class UpsellServiceImpl extends SimpleAsyncServiceBase implements UpsellService {
    private final Lazy<ConfigService> configService;
    private final Context context;
    private final Lazy<CountryService> countryService;

    /* access modifiers changed from: protected */
    public int getMaxThreads() {
        return 1;
    }

    /* access modifiers changed from: protected */
    public String getThreadName() {
        return "SimpleAsyncServiceBase";
    }

    public UpsellServiceImpl(Context context2, Lazy<ConfigService> lazy, Lazy<CountryService> lazy2) {
        this.context = context2.getApplicationContext();
        this.configService = lazy;
        this.countryService = lazy2;
    }

    public void getUpsellConfig(Function1<MfpUpsellConfig> function1, String str) {
        MfpUpsellConfig defaultConfig;
        try {
            defaultConfig = MfpUpsellConfig.parse(this.context, (CountryService) this.countryService.get(), ((ConfigService) this.configService.get()).getABTestProperties(str), (ConfigService) this.configService.get());
        } catch (Exception e) {
            Ln.e(e);
            defaultConfig = MfpUpsellConfig.getDefaultConfig(this.context, (CountryService) this.countryService.get(), (ConfigService) this.configService.get());
        } catch (Throwable th) {
            postToMainThread(function1, null);
            throw th;
        }
        postToMainThread(function1, defaultConfig);
    }
}
