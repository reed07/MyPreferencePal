package com.myfitnesspal.shared.service.measurements;

import android.database.Cursor;
import com.myfitnesspal.shared.model.v1.Measurement;
import com.myfitnesspal.shared.model.v1.MfpMeasurementValue;
import java.util.Calendar;
import java.util.Date;

public interface MeasurementsService {
    MfpMeasurementValue getInitialMeasurementOfType(String str);

    float getLeastRecentMeasurementValue(long j, long j2);

    Measurement getMeasurementByDate(Date date, String str);

    Measurement getMeasurementByLocalId(long j);

    Measurement getMeasurementByUid(String str);

    long getMeasurementTypeIdFromMeasurementTypeName(String str);

    float getMeasurementValueForDate(long j, long j2, Date date);

    Cursor getMostRecentMeasurementBeforeDate(Date date, long j, long j2);

    float getMostRecentMeasurementValue(long j, long j2);

    float getMostRecentMeasurementValueBeforeDate(Date date, long j, long j2);

    long insertOrUpdateMeasurement(Measurement measurement);

    long insertOrUpdateMeasurement(String str, Calendar calendar, float f, String str2);

    long insertOrUpdateMeasurementForToday(String str, float f);

    void insertOrUpdateThirdPartyMeasurement(String str, float f, long j, String str2);

    void localDeleteByMasterId(long j);

    void markForRemoteDeletion(long j);

    void setInitialMeasurementOfType(String str, float f);
}
