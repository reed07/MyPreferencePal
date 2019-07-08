package com.myfitnesspal.feature.barcode.service;

import com.myfitnesspal.feature.barcode.service.BarcodeService.ScanResult;
import com.myfitnesspal.shared.api.ApiErrorCallback;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.v1.MfpInformationApi;
import com.myfitnesspal.shared.model.v15.FoodObject;
import com.myfitnesspal.shared.service.syncv1.packets.PacketPayloadListExtractor;
import com.myfitnesspal.shared.service.syncv1.packets.request.AssociateBarcodeWithFoodRequestPacket;
import com.myfitnesspal.shared.service.syncv1.packets.request.BarcodeSearchRequestPacket;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Function0;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import java.util.Collection;
import java.util.List;
import javax.inject.Provider;

public class BarcodeServiceImpl implements BarcodeService {
    private final Provider<MfpInformationApi> api;

    class ScanResultImpl implements ScanResult {
        private final String barcode;
        private final List<FoodObject> foods;

        public ScanResultImpl(List<FoodObject> list, String str) {
            this.foods = list;
            this.barcode = str;
        }

        public List<FoodObject> foods() {
            return this.foods;
        }

        public String barcode() {
            return this.barcode;
        }
    }

    public BarcodeServiceImpl(Provider<MfpInformationApi> provider) {
        this.api = provider;
    }

    public void associateBarcodeWithFood(long j, String str, String str2, String str3, Function0 function0, ApiErrorCallback apiErrorCallback) {
        MfpInformationApi mfpInformationApi = (MfpInformationApi) this.api.get();
        AssociateBarcodeWithFoodRequestPacket associateBarcodeWithFoodRequestPacket = new AssociateBarcodeWithFoodRequestPacket(j, str, str2, str3);
        ((MfpInformationApi) mfpInformationApi.addPacket(associateBarcodeWithFoodRequestPacket)).postAsync(function0, apiErrorCallback, new Object[0]);
    }

    public ScanResult searchBarcode(String str) throws ApiException {
        int length = Strings.length(str);
        if (length == 8 || length == 13) {
            return new ScanResultImpl((List) ((MfpInformationApi) ((MfpInformationApi) ((MfpInformationApi) this.api.get()).addPacket(new BarcodeSearchRequestPacket(str))).treatErrorCodeAsSuccess(256)).post((ReturningFunction1<TTransform, T>) new PacketPayloadListExtractor<TTransform,T>(3), new Object[0]), str);
        }
        throw new ApiException("barcodeString should be 8 or 13", 259);
    }

    public ScanResult searchBarcodeWithFallback(String str, String str2) throws ApiException {
        try {
            ScanResult searchBarcode = searchBarcode(str);
            if (CollectionUtils.notEmpty((Collection<?>) searchBarcode.foods())) {
                return searchBarcode;
            }
        } catch (ApiException e) {
            if (Strings.isEmpty(str2)) {
                throw e;
            }
        }
        return searchBarcode(str2);
    }
}
