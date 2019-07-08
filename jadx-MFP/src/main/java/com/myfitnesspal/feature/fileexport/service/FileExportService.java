package com.myfitnesspal.feature.fileexport.service;

import com.myfitnesspal.shared.api.ApiException;
import java.util.Date;

public interface FileExportService {
    void postFileExportRequest(Date date, Date date2) throws ApiException;
}
