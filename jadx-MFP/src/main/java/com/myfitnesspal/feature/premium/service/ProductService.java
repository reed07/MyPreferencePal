package com.myfitnesspal.feature.premium.service;

import com.myfitnesspal.feature.payments.model.MfpProduct;
import com.myfitnesspal.shared.api.ApiException;
import com.uacf.core.util.Function1;
import java.util.List;

public interface ProductService {
    boolean areFreeTrialsEnabled();

    void getProducts(Function1<List<MfpProduct>> function1);

    String getUpsellWebViewCacheFilename();

    boolean isFeatureInCatalog(String str);

    void preCacheUpsellWebViewHtml(List<MfpProduct> list);

    List<MfpProduct> refreshProductsIfCacheExpired() throws ApiException;

    void setFreeTrialFlagAndCacheWebView(boolean z, List<MfpProduct> list);
}
