package com.myfitnesspal.feature.home.service;

import com.myfitnesspal.feature.home.model.ImageReportPacket;
import com.myfitnesspal.feature.home.model.ReportImageData;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.constants.Constants.Uri;
import javax.inject.Provider;

public class ImageReportingServiceImpl implements ImageReportingService {
    private final Provider<MfpV2Api> apiProvider;

    public ImageReportingServiceImpl(Provider<MfpV2Api> provider) {
        this.apiProvider = provider;
    }

    public void reportImage(boolean z, String str, String str2) throws ApiException {
        ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) this.apiProvider.get()).withOutputType(Object.class)).withJsonBody(new ImageReportPacket(new ReportImageData(z, str, str2)))).post(Uri.REPORT_IMAGE, new Object[0]);
    }
}
