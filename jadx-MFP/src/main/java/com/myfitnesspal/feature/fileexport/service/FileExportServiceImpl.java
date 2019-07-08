package com.myfitnesspal.feature.fileexport.service;

import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiResponseBase;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.constants.Constants.Uri;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.inject.Provider;

public class FileExportServiceImpl implements FileExportService {
    private final Provider<MfpV2Api> apiProvider;

    public FileExportServiceImpl(Provider<MfpV2Api> provider) {
        this.apiProvider = provider;
    }

    public void postFileExportRequest(Date date, Date date2) throws ApiException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String format = simpleDateFormat.format(date);
        String format2 = simpleDateFormat.format(date2);
        ((MfpV2Api) ((MfpV2Api) this.apiProvider.get()).withOutputType(ApiResponseBase.class)).post(String.format(Uri.FILE_EXPORT, new Object[]{format, format2}), new Object[0]);
    }
}
