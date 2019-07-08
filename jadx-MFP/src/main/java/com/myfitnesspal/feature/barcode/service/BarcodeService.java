package com.myfitnesspal.feature.barcode.service;

import com.myfitnesspal.shared.api.ApiErrorCallback;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v15.FoodObject;
import com.uacf.core.util.Function0;
import java.util.List;

public interface BarcodeService {

    public interface ScanResult {
        String barcode();

        List<FoodObject> foods();
    }

    void associateBarcodeWithFood(long j, String str, String str2, String str3, Function0 function0, ApiErrorCallback apiErrorCallback);

    ScanResult searchBarcode(String str) throws ApiException;

    ScanResult searchBarcodeWithFallback(String str, String str2) throws ApiException;
}
