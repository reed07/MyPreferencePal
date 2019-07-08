package com.myfitnesspal.feature.fileexport.task;

import android.content.Context;
import com.myfitnesspal.feature.fileexport.service.FileExportService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import dagger.Lazy;
import java.util.Date;

public class FileExportTask extends EventedTaskBase<Object, ApiException> {
    private final Lazy<FileExportService> fileExportService;
    private final Date fromDate;
    private final Date toDate;

    public static class CompletedEvent extends TaskEventBase<Object, ApiException> {
    }

    public FileExportTask(Lazy<FileExportService> lazy, Date date, Date date2) {
        super(CompletedEvent.class);
        this.fileExportService = lazy;
        this.fromDate = date;
        this.toDate = date2;
    }

    /* access modifiers changed from: protected */
    public Object exec(Context context) throws ApiException {
        ((FileExportService) this.fileExportService.get()).postFileExportRequest(this.fromDate, this.toDate);
        return new Object();
    }
}
