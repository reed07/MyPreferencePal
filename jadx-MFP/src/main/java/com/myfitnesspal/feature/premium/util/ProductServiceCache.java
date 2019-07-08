package com.myfitnesspal.feature.premium.util;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.myfitnesspal.feature.payments.model.MfpProduct;
import com.myfitnesspal.feature.payments.model.MfpProduct.LIST_MAPPER;
import com.myfitnesspal.feature.payments.model.MfpProductFeature;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.uacf.core.asyncservice.ReadWriteServiceBase;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Strings;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceCache {
    private static final long CACHE_EXPIRY = 86400000;
    /* access modifiers changed from: private */
    public static final ApiJsonMapper MAPPER = new ApiJsonMapper();
    private static final String PRODUCT_CATALOG_LOCALE_PREFS_KEY = "product_catalog_locale";
    private static final String PRODUCT_CATALOG_PREFS_KEY = "product_catalog_cache";
    private long cacheExpiry = 0;
    private Map<String, MfpProductFeature> cachedFeatures;
    private String cachedLocale;
    private List<MfpProduct> cachedProducts;
    /* access modifiers changed from: private */
    public SharedPreferences prefs;
    private InternalReadWriteService readWriteService = new InternalReadWriteService();

    private class InternalReadWriteService extends ReadWriteServiceBase {
        /* access modifiers changed from: protected */
        public int getMaxThreads() {
            return 2;
        }

        /* access modifiers changed from: protected */
        public String getThreadName() {
            return "ProductServiceCache";
        }

        private InternalReadWriteService() {
        }

        public <T> void post(Function1<T> function1, T t) {
            postToMainThread(function1, t);
        }
    }

    public ProductServiceCache(SharedPreferences sharedPreferences) {
        this.prefs = sharedPreferences;
    }

    public synchronized boolean expired() {
        return System.currentTimeMillis() > this.cacheExpiry;
    }

    public synchronized Map<String, MfpProductFeature> getFeatures(String str) {
        if (!Strings.equals(str, this.cachedLocale)) {
            return null;
        }
        return this.cachedFeatures;
    }

    public synchronized List<MfpProduct> getProducts(String str) {
        if (!Strings.equals(str, this.cachedLocale)) {
            return null;
        }
        return this.cachedProducts;
    }

    public synchronized void update(List<MfpProduct> list, String str) {
        if (list != null) {
            this.cachedProducts = list;
            this.cachedFeatures = new HashMap();
            this.cachedLocale = str;
            for (MfpProduct productFeatures : list) {
                for (MfpProductFeature mfpProductFeature : productFeatures.getProductFeatures()) {
                    this.cachedFeatures.put(mfpProductFeature.getFeatureId(), mfpProductFeature);
                }
            }
            this.cacheExpiry = System.currentTimeMillis() + CACHE_EXPIRY;
            writeCacheToDisk(list, str);
        }
    }

    public List<MfpProduct> readCacheFromDisk(String str) {
        List<MfpProduct> list = null;
        if (Strings.equals(str, this.prefs.getString(PRODUCT_CATALOG_LOCALE_PREFS_KEY, ""))) {
            String string = this.prefs.getString(PRODUCT_CATALOG_PREFS_KEY, null);
            if (Strings.notEmpty(string)) {
                list = (List) MAPPER.tryMapFrom(string, LIST_MAPPER.class);
                if (CollectionUtils.notEmpty((Collection<?>) list)) {
                    update(list, str);
                }
            }
        }
        return list;
    }

    private void writeCacheToDisk(final List<MfpProduct> list, final String str) {
        this.readWriteService.write(new Runnable() {
            public void run() {
                synchronized (ProductServiceCache.this) {
                    String reverseMap = ProductServiceCache.MAPPER.reverseMap((Object) list);
                    Editor edit = ProductServiceCache.this.prefs.edit();
                    edit.putString(ProductServiceCache.PRODUCT_CATALOG_PREFS_KEY, reverseMap);
                    edit.putString(ProductServiceCache.PRODUCT_CATALOG_LOCALE_PREFS_KEY, str);
                    edit.apply();
                }
            }
        });
    }
}
