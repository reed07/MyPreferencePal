package com.myfitnesspal.feature.progress.task;

import android.content.Context;
import com.myfitnesspal.feature.images.util.ImageUploadUtil;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.model.v1.Measurement;
import com.myfitnesspal.shared.service.measurements.MeasurementsService;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.Calendar;

public class AssociateMeasurementImageTask extends EventedTaskBase<Boolean, Exception> {
    private final String imagePath;
    private final String measurementType;
    private final Lazy<MeasurementsService> measurementsService;
    private final Calendar resourceDate;
    private final long resourceId;
    private final String resourceUid;

    public static class CompletedEvent extends TaskEventBase<Boolean, Exception> {
    }

    public AssociateMeasurementImageTask(String str, String str2, long j, String str3, Calendar calendar, Lazy<MeasurementsService> lazy) {
        super(CompletedEvent.class);
        this.imagePath = str;
        this.measurementType = str2;
        this.resourceId = j;
        this.resourceUid = str3;
        this.resourceDate = calendar;
        this.measurementsService = lazy;
    }

    /* access modifiers changed from: protected */
    public Boolean exec(Context context) throws Exception {
        return Boolean.valueOf(associateMeasurement(ImageUploadUtil.copyFileToUploadDirectory(context, this.imagePath)));
    }

    private boolean associateMeasurement(String str) {
        MeasurementsService measurementsService2 = (MeasurementsService) this.measurementsService.get();
        boolean z = false;
        Ln.d("looking for measurement with uid=%s, id=%s", this.resourceUid, Long.valueOf(this.resourceId));
        Measurement measurementByUid = Strings.notEmpty(this.resourceUid) ? measurementsService2.getMeasurementByUid(this.resourceUid) : null;
        if (measurementByUid != null) {
            Ln.d("measurement found by uid=%s", this.resourceUid);
        } else {
            measurementByUid = measurementsService2.getMeasurementByLocalId(this.resourceId);
        }
        if (measurementByUid != null) {
            Ln.d("measurement found by localId=%s", Long.valueOf(this.resourceId));
        } else if (Strings.notEmpty(this.measurementType)) {
            measurementByUid = measurementsService2.getMeasurementByDate(this.resourceDate.getTime(), this.measurementType);
        }
        if (measurementByUid != null) {
            if (((MeasurementsService) this.measurementsService.get()).insertOrUpdateMeasurement(measurementByUid.getMeasurementTypeName(), measurementByUid.getEntryDate(), measurementByUid.getValue().floatValue(), str) != -1) {
                z = true;
            }
            return z;
        }
        Ln.d("measurement not found!", new Object[0]);
        return false;
    }
}
