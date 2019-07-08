package com.myfitnesspal.feature.progress.service;

import android.content.Context;
import android.database.Cursor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.progress.constants.ArtifactType;
import com.myfitnesspal.feature.progress.ui.viewmodel.ArtifactViewModel;
import com.myfitnesspal.feature.progress.ui.viewmodel.GalleryImageViewModel;
import com.myfitnesspal.feature.progress.ui.viewmodel.GalleryImageViewModel.Fields;
import com.myfitnesspal.shared.constants.Constants.Measurement;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.table.ExerciseEntriesTable;
import com.myfitnesspal.shared.db.table.ImageAssociationsTable;
import com.myfitnesspal.shared.db.table.ImagesTable;
import com.myfitnesspal.shared.db.table.ImagesTable.Columns;
import com.myfitnesspal.shared.db.table.MeasurementTypesTable;
import com.myfitnesspal.shared.db.table.MeasurementsTable;
import com.myfitnesspal.shared.db.table.MfpDatabaseTableV2;
import com.myfitnesspal.shared.db.table.UsersTableV1;
import com.myfitnesspal.shared.model.ProgressEntryViewModel;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.unitconv.LocalizedWeight;
import com.myfitnesspal.shared.model.v15.UserSummaryObject;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.measurements.MeasurementsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserSummaryService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.Measurements;
import com.myfitnesspal.shared.util.UnitsUtils.Weight;
import com.uacf.core.constants.DateTime.Format;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.util.CursorUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

public class ProgressServiceImpl implements ProgressService {
    private static final String DATE_FORMAT = Format.newIso8601DateFormat().toPattern();
    private static final String MAX_DATE = "9999-99-99";
    private static final String MIN_DATE = "0000-00-00";
    private final Lazy<ConfigService> configService;
    private final Context context;
    private final SQLiteDatabaseWrapper db;
    private final ExerciseEntriesTable exerciseEntriesTable = new ExerciseEntriesTable(this.db);
    private final Lazy<MeasurementsService> measurementsService;
    private final Lazy<Session> session;
    private final Lazy<UserSummaryService> userSummaryService;
    private final Lazy<UserWeightService> userWeightService;

    /* renamed from: com.myfitnesspal.feature.progress.service.ProgressServiceImpl$1 reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$myfitnesspal$shared$util$UnitsUtils$Weight = new int[Weight.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                com.myfitnesspal.shared.util.UnitsUtils$Weight[] r0 = com.myfitnesspal.shared.util.UnitsUtils.Weight.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$myfitnesspal$shared$util$UnitsUtils$Weight = r0
                int[] r0 = $SwitchMap$com$myfitnesspal$shared$util$UnitsUtils$Weight     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.myfitnesspal.shared.util.UnitsUtils$Weight r1 = com.myfitnesspal.shared.util.UnitsUtils.Weight.POUNDS     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$myfitnesspal$shared$util$UnitsUtils$Weight     // Catch:{ NoSuchFieldError -> 0x001f }
                com.myfitnesspal.shared.util.UnitsUtils$Weight r1 = com.myfitnesspal.shared.util.UnitsUtils.Weight.KILOGRAMS     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$com$myfitnesspal$shared$util$UnitsUtils$Weight     // Catch:{ NoSuchFieldError -> 0x002a }
                com.myfitnesspal.shared.util.UnitsUtils$Weight r1 = com.myfitnesspal.shared.util.UnitsUtils.Weight.STONES     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.progress.service.ProgressServiceImpl.AnonymousClass1.<clinit>():void");
        }
    }

    @Inject
    public ProgressServiceImpl(Context context2, Lazy<Session> lazy, Lazy<UserSummaryService> lazy2, Lazy<UserWeightService> lazy3, Lazy<MeasurementsService> lazy4, Lazy<ConfigService> lazy5) {
        this.context = context2.getApplicationContext();
        this.db = DbConnectionManager.getDb(context2);
        this.session = lazy;
        this.userSummaryService = lazy2;
        this.userWeightService = lazy3;
        this.measurementsService = lazy4;
        this.configService = lazy5;
    }

    public List<GalleryImageViewModel> getGalleryImagesForImportMode(String str) {
        long localId = ((Session) this.session.get()).getUser().getLocalId();
        long measurementTypeIdFromMeasurementTypeName = ((MeasurementsService) this.measurementsService.get()).getMeasurementTypeIdFromMeasurementTypeName(str);
        String format = String.format("coalesce(%s.%s, -1) AS %s", new Object[]{ImageAssociationsTable.TABLE_NAME, "id", Fields.IMAGE_ASSOCIATION_LOCAL_ID});
        long j = measurementTypeIdFromMeasurementTypeName;
        String join = Strings.join(",", (T[]) new String[]{String.format("%s.%s AS %s", new Object[]{ImageAssociationsTable.TABLE_NAME, "image_uid", "image_uid"}), format, String.format("%s.%s AS %s", new Object[]{ImagesTable.TABLE_NAME, Columns.LOCAL_FILEPATH, Fields.IMAGE_LOCAL_FILEPATH}), String.format("%s.%s AS %s", new Object[]{ImagesTable.TABLE_NAME, "width", Fields.IMAGE_WIDTH}), String.format("%s.%s AS %s", new Object[]{ImagesTable.TABLE_NAME, "height", Fields.IMAGE_HEIGHT}), String.format("%s.%s AS %s", new Object[]{ImagesTable.TABLE_NAME, MfpDatabaseTableV2.Columns.SYNC_FLAGS, Fields.IMAGE_SYNC_FLAGS}), String.format("%s.%s AS %s", new Object[]{MeasurementsTable.TABLE_NAME, "entry_date", Fields.DISPLAY_DATE}), String.format("%s.%s AS %s", new Object[]{MeasurementsTable.TABLE_NAME, "value", Fields.RESOURCE_VALUE}), String.format("\"%s\" AS %s", new Object[]{"measurement", "resource_type"}), String.format("%s.%s AS %s", new Object[]{MeasurementsTable.TABLE_NAME, "id", "resource_id"}), String.format("%s.%s AS %s", new Object[]{MeasurementsTable.TABLE_NAME, "uid", "resource_uid"})});
        String join2 = Strings.join(" ", (T[]) new String[]{String.format("LEFT OUTER JOIN %s ON (%s AND %s)", new Object[]{ImageAssociationsTable.TABLE_NAME, String.format("(%s.%s=%s.%s OR %s.%s=%s.%s)", new Object[]{ImageAssociationsTable.TABLE_NAME, "resource_id", MeasurementsTable.TABLE_NAME, "id", ImageAssociationsTable.TABLE_NAME, "resource_uid", MeasurementsTable.TABLE_NAME, "uid"}), String.format("(%s.%s IS NULL OR %s.%s NOT IN (%s, %s))", new Object[]{ImageAssociationsTable.TABLE_NAME, MfpDatabaseTableV2.Columns.SYNC_FLAGS, ImageAssociationsTable.TABLE_NAME, MfpDatabaseTableV2.Columns.SYNC_FLAGS, Integer.valueOf(3), Integer.valueOf(5)})}), String.format("LEFT OUTER JOIN %s ON (%s.%s=%s.%s OR %s.%s=%s.%s)", new Object[]{ImagesTable.TABLE_NAME, ImageAssociationsTable.TABLE_NAME, "image_id", ImagesTable.TABLE_NAME, "id", ImageAssociationsTable.TABLE_NAME, "image_uid", ImagesTable.TABLE_NAME, "uid"})});
        String join3 = Strings.join(" AND ", (T[]) new String[]{"measurements.user_id=?", "measurement_type_id=?"});
        String[] strArr = {String.valueOf(localId), String.valueOf(j)};
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT ");
        sb.append(join);
        sb.append(" FROM ");
        sb.append(MeasurementsTable.TABLE_NAME);
        sb.append(" ");
        sb.append(join2);
        sb.append(" WHERE ");
        sb.append(join3);
        sb.append(" ORDER BY ");
        sb.append("entry_date ASC");
        sb.append(";");
        return GalleryImageViewModel.parseList(this.db.rawQuery(sb.toString(), strArr));
    }

    public int getImageCountForMeasurementType(String str) {
        return CursorUtils.readIntAndClose(queryGalleryImagesForViewMode("COUNT(*)", str), 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x02bf  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x02c4  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x02db  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x02e0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.myfitnesspal.shared.model.ProgressEntryViewModel> getProgressReport(java.lang.String r23, int r24, com.myfitnesspal.shared.util.UnitsUtils.Weight r25, com.myfitnesspal.shared.util.UnitsUtils.Length r26) {
        /*
            r22 = this;
            r7 = r22
            r0 = r24
            dagger.Lazy<com.myfitnesspal.shared.service.session.Session> r1 = r7.session     // Catch:{ all -> 0x02d6 }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x02d6 }
            com.myfitnesspal.shared.service.session.Session r1 = (com.myfitnesspal.shared.service.session.Session) r1     // Catch:{ all -> 0x02d6 }
            com.myfitnesspal.shared.model.User r1 = r1.getUser()     // Catch:{ all -> 0x02d6 }
            long r9 = r1.getLocalId()     // Catch:{ all -> 0x02d6 }
            dagger.Lazy<com.myfitnesspal.shared.service.measurements.MeasurementsService> r1 = r7.measurementsService     // Catch:{ all -> 0x02d6 }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x02d6 }
            com.myfitnesspal.shared.service.measurements.MeasurementsService r1 = (com.myfitnesspal.shared.service.measurements.MeasurementsService) r1     // Catch:{ all -> 0x02d6 }
            r11 = r23
            long r12 = r1.getMeasurementTypeIdFromMeasurementTypeName(r11)     // Catch:{ all -> 0x02d6 }
            com.myfitnesspal.feature.progress.constants.GraphPeriod r1 = com.myfitnesspal.feature.progress.constants.GraphPeriod.AllTime     // Catch:{ all -> 0x02d6 }
            dagger.Lazy<com.myfitnesspal.shared.service.session.Session> r2 = r7.session     // Catch:{ all -> 0x02d6 }
            java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x02d6 }
            com.myfitnesspal.shared.service.session.Session r2 = (com.myfitnesspal.shared.service.session.Session) r2     // Catch:{ all -> 0x02d6 }
            int r1 = r1.getDaysBack(r2)     // Catch:{ all -> 0x02d6 }
            r2 = 5
            if (r0 != r1) goto L_0x003b
            java.util.Date r0 = new java.util.Date     // Catch:{ all -> 0x02d6 }
            r3 = 0
            r0.<init>(r3)     // Catch:{ all -> 0x02d6 }
            goto L_0x0047
        L_0x003b:
            java.util.Calendar r1 = java.util.Calendar.getInstance()     // Catch:{ all -> 0x02d6 }
            int r0 = -r0
            r1.add(r2, r0)     // Catch:{ all -> 0x02d6 }
            java.util.Date r0 = r1.getTime()     // Catch:{ all -> 0x02d6 }
        L_0x0047:
            java.util.Calendar r1 = java.util.Calendar.getInstance()     // Catch:{ all -> 0x02d6 }
            java.text.SimpleDateFormat r14 = com.uacf.core.constants.DateTime.Format.newIso8601DateFormat()     // Catch:{ all -> 0x02d6 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x02d6 }
            r3.<init>()     // Catch:{ all -> 0x02d6 }
            java.lang.String r4 = "Reports from = "
            r3.append(r4)     // Catch:{ all -> 0x02d6 }
            java.lang.String r4 = r0.toString()     // Catch:{ all -> 0x02d6 }
            r3.append(r4)     // Catch:{ all -> 0x02d6 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x02d6 }
            r15 = 0
            java.lang.Object[] r4 = new java.lang.Object[r15]     // Catch:{ all -> 0x02d6 }
            com.uacf.core.util.Ln.v(r3, r4)     // Catch:{ all -> 0x02d6 }
            java.lang.String r3 = "coalesce(%s.%s, -1) AS %s"
            r6 = 3
            java.lang.Object[] r4 = new java.lang.Object[r6]     // Catch:{ all -> 0x02d6 }
            java.lang.String r5 = "image_associations"
            r4[r15] = r5     // Catch:{ all -> 0x02d6 }
            java.lang.String r5 = "id"
            r16 = 1
            r4[r16] = r5     // Catch:{ all -> 0x02d6 }
            java.lang.String r5 = "image_association_local_id"
            r8 = 2
            r4[r8] = r5     // Catch:{ all -> 0x02d6 }
            java.lang.String r3 = java.lang.String.format(r3, r4)     // Catch:{ all -> 0x02d6 }
            java.lang.String r4 = "%s.%s"
            java.lang.Object[] r5 = new java.lang.Object[r8]     // Catch:{ all -> 0x02d6 }
            java.lang.String r17 = "image_associations"
            r5[r15] = r17     // Catch:{ all -> 0x02d6 }
            java.lang.String r17 = "image_uid"
            r5[r16] = r17     // Catch:{ all -> 0x02d6 }
            java.lang.String r4 = java.lang.String.format(r4, r5)     // Catch:{ all -> 0x02d6 }
            java.lang.String r5 = "%s.%s"
            java.lang.Object[] r2 = new java.lang.Object[r8]     // Catch:{ all -> 0x02d6 }
            java.lang.String r18 = "images"
            r2[r15] = r18     // Catch:{ all -> 0x02d6 }
            java.lang.String r18 = "local_filepath"
            r2[r16] = r18     // Catch:{ all -> 0x02d6 }
            java.lang.String r2 = java.lang.String.format(r5, r2)     // Catch:{ all -> 0x02d6 }
            java.lang.String r5 = "%s.%s"
            java.lang.Object[] r6 = new java.lang.Object[r8]     // Catch:{ all -> 0x02d6 }
            java.lang.String r18 = "measurements"
            r6[r15] = r18     // Catch:{ all -> 0x02d6 }
            java.lang.String r18 = "id"
            r6[r16] = r18     // Catch:{ all -> 0x02d6 }
            java.lang.String r5 = java.lang.String.format(r5, r6)     // Catch:{ all -> 0x02d6 }
            java.lang.String r6 = "%s.%s"
            java.lang.Object[] r15 = new java.lang.Object[r8]     // Catch:{ all -> 0x02d6 }
            java.lang.String r19 = "measurements"
            r18 = 0
            r15[r18] = r19     // Catch:{ all -> 0x02d6 }
            java.lang.String r19 = "uid"
            r15[r16] = r19     // Catch:{ all -> 0x02d6 }
            java.lang.String r6 = java.lang.String.format(r6, r15)     // Catch:{ all -> 0x02d6 }
            java.lang.String r15 = ","
            r8 = 7
            java.lang.String[] r11 = new java.lang.String[r8]     // Catch:{ all -> 0x02d6 }
            r18 = 0
            r11[r18] = r5     // Catch:{ all -> 0x02d6 }
            r11[r16] = r6     // Catch:{ all -> 0x02d6 }
            java.lang.String r5 = "value"
            r6 = 2
            r11[r6] = r5     // Catch:{ all -> 0x02d6 }
            java.lang.String r5 = "entry_date"
            r6 = 3
            r11[r6] = r5     // Catch:{ all -> 0x02d6 }
            r5 = 4
            r11[r5] = r3     // Catch:{ all -> 0x02d6 }
            r3 = 5
            r11[r3] = r4     // Catch:{ all -> 0x02d6 }
            r3 = 6
            r11[r3] = r2     // Catch:{ all -> 0x02d6 }
            java.lang.String r11 = com.uacf.core.util.Strings.join(r15, (T[]) r11)     // Catch:{ all -> 0x02d6 }
            java.lang.String r2 = "(%s.%s=%s.%s OR %s.%s=%s.%s)"
            r4 = 8
            java.lang.Object[] r6 = new java.lang.Object[r4]     // Catch:{ all -> 0x02d6 }
            java.lang.String r15 = "image_associations"
            r18 = 0
            r6[r18] = r15     // Catch:{ all -> 0x02d6 }
            java.lang.String r15 = "resource_id"
            r6[r16] = r15     // Catch:{ all -> 0x02d6 }
            java.lang.String r15 = "measurements"
            r19 = 2
            r6[r19] = r15     // Catch:{ all -> 0x02d6 }
            java.lang.String r15 = "id"
            r20 = 3
            r6[r20] = r15     // Catch:{ all -> 0x02d6 }
            java.lang.String r15 = "image_associations"
            r6[r5] = r15     // Catch:{ all -> 0x02d6 }
            java.lang.String r15 = "resource_uid"
            r17 = 5
            r6[r17] = r15     // Catch:{ all -> 0x02d6 }
            java.lang.String r15 = "measurements"
            r6[r3] = r15     // Catch:{ all -> 0x02d6 }
            java.lang.String r15 = "uid"
            r6[r8] = r15     // Catch:{ all -> 0x02d6 }
            java.lang.String r2 = java.lang.String.format(r2, r6)     // Catch:{ all -> 0x02d6 }
            java.lang.String r6 = "(%s.%s IS NULL OR %s.%s NOT IN(%s, %s))"
            java.lang.Object[] r15 = new java.lang.Object[r3]     // Catch:{ all -> 0x02d6 }
            java.lang.String r20 = "image_associations"
            r18 = 0
            r15[r18] = r20     // Catch:{ all -> 0x02d6 }
            java.lang.String r20 = "sync_flags"
            r15[r16] = r20     // Catch:{ all -> 0x02d6 }
            java.lang.String r20 = "image_associations"
            r19 = 2
            r15[r19] = r20     // Catch:{ all -> 0x02d6 }
            java.lang.String r20 = "sync_flags"
            r21 = 3
            r15[r21] = r20     // Catch:{ all -> 0x02d6 }
            java.lang.Integer r20 = java.lang.Integer.valueOf(r21)     // Catch:{ all -> 0x02d6 }
            r15[r5] = r20     // Catch:{ all -> 0x02d6 }
            r17 = 5
            java.lang.Integer r20 = java.lang.Integer.valueOf(r17)     // Catch:{ all -> 0x02d6 }
            r15[r17] = r20     // Catch:{ all -> 0x02d6 }
            java.lang.String r6 = java.lang.String.format(r6, r15)     // Catch:{ all -> 0x02d6 }
            java.lang.String r15 = "LEFT OUTER JOIN %s ON (%s AND %s)"
            r4 = 3
            java.lang.Object[] r8 = new java.lang.Object[r4]     // Catch:{ all -> 0x02d6 }
            java.lang.String r4 = "image_associations"
            r18 = 0
            r8[r18] = r4     // Catch:{ all -> 0x02d6 }
            r8[r16] = r2     // Catch:{ all -> 0x02d6 }
            r2 = 2
            r8[r2] = r6     // Catch:{ all -> 0x02d6 }
            java.lang.String r2 = java.lang.String.format(r15, r8)     // Catch:{ all -> 0x02d6 }
            java.lang.String r4 = "LEFT OUTER JOIN %s ON (%s.%s=%s.%s OR %s.%s=%s.%s)"
            r6 = 9
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ all -> 0x02d6 }
            java.lang.String r8 = "images"
            r15 = 0
            r6[r15] = r8     // Catch:{ all -> 0x02d6 }
            java.lang.String r8 = "image_associations"
            r6[r16] = r8     // Catch:{ all -> 0x02d6 }
            java.lang.String r8 = "image_id"
            r15 = 2
            r6[r15] = r8     // Catch:{ all -> 0x02d6 }
            java.lang.String r8 = "images"
            r15 = 3
            r6[r15] = r8     // Catch:{ all -> 0x02d6 }
            java.lang.String r8 = "id"
            r6[r5] = r8     // Catch:{ all -> 0x02d6 }
            java.lang.String r8 = "image_associations"
            r15 = 5
            r6[r15] = r8     // Catch:{ all -> 0x02d6 }
            java.lang.String r8 = "image_uid"
            r6[r3] = r8     // Catch:{ all -> 0x02d6 }
            java.lang.String r3 = "images"
            r8 = 7
            r6[r8] = r3     // Catch:{ all -> 0x02d6 }
            java.lang.String r3 = "uid"
            r8 = 8
            r6[r8] = r3     // Catch:{ all -> 0x02d6 }
            java.lang.String r3 = java.lang.String.format(r4, r6)     // Catch:{ all -> 0x02d6 }
            java.lang.String r4 = " "
            r6 = 2
            java.lang.String[] r8 = new java.lang.String[r6]     // Catch:{ all -> 0x02d6 }
            r6 = 0
            r8[r6] = r2     // Catch:{ all -> 0x02d6 }
            r8[r16] = r3     // Catch:{ all -> 0x02d6 }
            java.lang.String r8 = com.uacf.core.util.Strings.join(r4, (T[]) r8)     // Catch:{ all -> 0x02d6 }
            java.lang.String r2 = " AND "
            java.lang.String r3 = "measurements.user_id=?"
            java.lang.String r4 = "measurement_type_id=?"
            java.lang.String r6 = "entry_date>=?"
            java.lang.String r15 = "entry_date<=?"
            java.lang.String[] r3 = new java.lang.String[]{r3, r4, r6, r15}     // Catch:{ all -> 0x02d6 }
            java.lang.String r2 = com.uacf.core.util.Strings.join(r2, (T[]) r3)     // Catch:{ all -> 0x02d6 }
            java.lang.String[] r3 = new java.lang.String[r5]     // Catch:{ all -> 0x02d6 }
            java.lang.String r4 = java.lang.String.valueOf(r9)     // Catch:{ all -> 0x02d6 }
            r5 = 0
            r3[r5] = r4     // Catch:{ all -> 0x02d6 }
            java.lang.String r4 = java.lang.String.valueOf(r12)     // Catch:{ all -> 0x02d6 }
            r3[r16] = r4     // Catch:{ all -> 0x02d6 }
            java.lang.String r4 = r14.format(r0)     // Catch:{ all -> 0x02d6 }
            r5 = 2
            r3[r5] = r4     // Catch:{ all -> 0x02d6 }
            java.util.Date r1 = r1.getTime()     // Catch:{ all -> 0x02d6 }
            java.lang.String r1 = r14.format(r1)     // Catch:{ all -> 0x02d6 }
            r6 = 3
            r3[r6] = r1     // Catch:{ all -> 0x02d6 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x02d6 }
            r1.<init>()     // Catch:{ all -> 0x02d6 }
            java.lang.String r4 = "SELECT DISTINCT "
            r1.append(r4)     // Catch:{ all -> 0x02d6 }
            r1.append(r11)     // Catch:{ all -> 0x02d6 }
            java.lang.String r4 = " FROM "
            r1.append(r4)     // Catch:{ all -> 0x02d6 }
            java.lang.String r4 = "measurements"
            r1.append(r4)     // Catch:{ all -> 0x02d6 }
            java.lang.String r4 = " "
            r1.append(r4)     // Catch:{ all -> 0x02d6 }
            r1.append(r8)     // Catch:{ all -> 0x02d6 }
            java.lang.String r4 = " WHERE "
            r1.append(r4)     // Catch:{ all -> 0x02d6 }
            r1.append(r2)     // Catch:{ all -> 0x02d6 }
            java.lang.String r2 = " ORDER BY "
            r1.append(r2)     // Catch:{ all -> 0x02d6 }
            java.lang.String r2 = "entry_date ASC"
            r1.append(r2)     // Catch:{ all -> 0x02d6 }
            java.lang.String r2 = ";"
            r1.append(r2)     // Catch:{ all -> 0x02d6 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x02d6 }
            com.uacf.core.database.SQLiteDatabaseWrapper r2 = r7.db     // Catch:{ all -> 0x02d6 }
            android.database.Cursor r15 = r2.rawQuery(r1, r3)     // Catch:{ all -> 0x02d6 }
            java.util.ArrayList r17 = new java.util.ArrayList     // Catch:{ all -> 0x02cf }
            r17.<init>()     // Catch:{ all -> 0x02cf }
            r20 = 0
            r1 = r22
            r2 = r15
            r3 = r17
            r4 = r23
            r5 = r25
            r24 = r15
            r15 = 3
            r6 = r20
            r1.addWeightEntriesFromCursor(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x02cd }
            java.lang.Object r1 = com.uacf.core.util.Enumerable.firstOrDefault(r17)     // Catch:{ all -> 0x02cd }
            com.myfitnesspal.shared.model.ProgressEntryViewModel r1 = (com.myfitnesspal.shared.model.ProgressEntryViewModel) r1     // Catch:{ all -> 0x02cd }
            if (r1 == 0) goto L_0x0240
            java.util.Date r1 = r1.getDate()     // Catch:{ all -> 0x02cd }
            boolean r1 = com.myfitnesspal.shared.util.DateUtil.areDatesEqualIgnoreTime(r0, r1)     // Catch:{ all -> 0x02cd }
            if (r1 != 0) goto L_0x023d
            goto L_0x0240
        L_0x023d:
            r8 = 0
            goto L_0x02bd
        L_0x0240:
            java.lang.String r1 = " AND "
            java.lang.String r2 = "measurements.user_id=?"
            java.lang.String r3 = "measurement_type_id=?"
            java.lang.String r4 = "entry_date<?"
            java.lang.String[] r2 = new java.lang.String[]{r2, r3, r4}     // Catch:{ all -> 0x02cd }
            java.lang.String r1 = com.uacf.core.util.Strings.join(r1, (T[]) r2)     // Catch:{ all -> 0x02cd }
            java.lang.String[] r2 = new java.lang.String[r15]     // Catch:{ all -> 0x02cd }
            java.lang.String r3 = java.lang.String.valueOf(r9)     // Catch:{ all -> 0x02cd }
            r4 = 0
            r2[r4] = r3     // Catch:{ all -> 0x02cd }
            java.lang.String r3 = java.lang.String.valueOf(r12)     // Catch:{ all -> 0x02cd }
            r2[r16] = r3     // Catch:{ all -> 0x02cd }
            java.lang.String r0 = r14.format(r0)     // Catch:{ all -> 0x02cd }
            r3 = 2
            r2[r3] = r0     // Catch:{ all -> 0x02cd }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x02cd }
            r0.<init>()     // Catch:{ all -> 0x02cd }
            java.lang.String r3 = "SELECT DISTINCT "
            r0.append(r3)     // Catch:{ all -> 0x02cd }
            r0.append(r11)     // Catch:{ all -> 0x02cd }
            java.lang.String r3 = " FROM "
            r0.append(r3)     // Catch:{ all -> 0x02cd }
            java.lang.String r3 = "measurements"
            r0.append(r3)     // Catch:{ all -> 0x02cd }
            java.lang.String r3 = " "
            r0.append(r3)     // Catch:{ all -> 0x02cd }
            r0.append(r8)     // Catch:{ all -> 0x02cd }
            java.lang.String r3 = " WHERE "
            r0.append(r3)     // Catch:{ all -> 0x02cd }
            r0.append(r1)     // Catch:{ all -> 0x02cd }
            java.lang.String r1 = " ORDER BY "
            r0.append(r1)     // Catch:{ all -> 0x02cd }
            java.lang.String r1 = "entry_date DESC"
            r0.append(r1)     // Catch:{ all -> 0x02cd }
            java.lang.String r1 = " LIMIT "
            r0.append(r1)     // Catch:{ all -> 0x02cd }
            java.lang.String r1 = "1"
            r0.append(r1)     // Catch:{ all -> 0x02cd }
            java.lang.String r1 = ";"
            r0.append(r1)     // Catch:{ all -> 0x02cd }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x02cd }
            com.uacf.core.database.SQLiteDatabaseWrapper r1 = r7.db     // Catch:{ all -> 0x02cd }
            android.database.Cursor r8 = r1.rawQuery(r0, r2)     // Catch:{ all -> 0x02cd }
            r6 = 1
            r1 = r22
            r2 = r8
            r3 = r17
            r4 = r23
            r5 = r25
            r1.addWeightEntriesFromCursor(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x02c8 }
        L_0x02bd:
            if (r24 == 0) goto L_0x02c2
            r24.close()
        L_0x02c2:
            if (r8 == 0) goto L_0x02c7
            r8.close()
        L_0x02c7:
            return r17
        L_0x02c8:
            r0 = move-exception
            r1 = r8
            r8 = r24
            goto L_0x02d9
        L_0x02cd:
            r0 = move-exception
            goto L_0x02d2
        L_0x02cf:
            r0 = move-exception
            r24 = r15
        L_0x02d2:
            r8 = r24
            r1 = 0
            goto L_0x02d9
        L_0x02d6:
            r0 = move-exception
            r1 = 0
            r8 = 0
        L_0x02d9:
            if (r8 == 0) goto L_0x02de
            r8.close()
        L_0x02de:
            if (r1 == 0) goto L_0x02e3
            r1.close()
        L_0x02e3:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.progress.service.ProgressServiceImpl.getProgressReport(java.lang.String, int, com.myfitnesspal.shared.util.UnitsUtils$Weight, com.myfitnesspal.shared.util.UnitsUtils$Length):java.util.List");
    }

    private Cursor queryGalleryImagesForViewMode(String str, String str2) {
        String join = Strings.join(",", (T[]) new String[]{MeasurementsTable.TABLE_NAME, MeasurementTypesTable.TABLE_NAME, "user", UsersTableV1.TABLE_NAME, ImagesTable.TABLE_NAME, ImageAssociationsTable.TABLE_NAME});
        String join2 = Strings.join(" AND ", (T[]) new String[]{"LOWER(user.username)=?", "user.id=images.user_id", "LOWER(user.username)=LOWER(users.username)", "image_associations.sync_flags!=?", "image_associations.sync_flags!=?", "measurements.user_id=users.id", "measurements.measurement_type_id=measurement_types.id", "(images.id=image_associations.image_id OR images.uid=image_associations.image_uid)", "(measurements.id=image_associations.resource_id OR measurements.uid=image_associations.resource_uid)", "measurement_types.description=?"});
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT ");
        sb.append(str);
        sb.append(" FROM ");
        sb.append(join);
        sb.append(" WHERE ");
        sb.append(join2);
        sb.append(" ORDER BY ");
        sb.append("measurements.entry_date ASC, image_associations.ID ASC");
        sb.append(";");
        String sb2 = sb.toString();
        return this.db.rawQuery(sb2, new String[]{((Session) this.session.get()).getUser().getUsername().toLowerCase(), String.valueOf(3), String.valueOf(5), str2});
    }

    public List<GalleryImageViewModel> getGalleryImagesForViewMode(String str) {
        return GalleryImageViewModel.parseList(queryGalleryImagesForViewMode(Strings.join(",", (T[]) new String[]{"images.height AS image_height", "images.width AS image_width", "images.sync_flags AS image_sync_flags", "images.local_filepath AS image_local_filepath", "image_associations.image_uid AS image_uid", "image_associations.resource_type AS resource_type", "image_associations.id AS image_association_local_id", "measurements.entry_date AS display_date", "measurements.value AS resource_value", "measurements.id AS resource_id", "measurements.uid AS resource_uid"}), str));
    }

    public List<ArtifactViewModel> getShareProgressArtifactData(String str, String str2, boolean z) {
        String str3;
        String str4;
        UserSummaryObject userSummaryObject;
        int i;
        float f;
        long j;
        float f2;
        UserSummaryObject userSummaryObject2;
        boolean z2;
        float f3;
        float f4;
        String str5;
        ArrayList arrayList = new ArrayList();
        boolean isProgressPhotosNewsfeedOn = ConfigUtils.isProgressPhotosNewsfeedOn((ConfigService) this.configService.get());
        long measurementTypeIdFromMeasurementTypeName = ((MeasurementsService) this.measurementsService.get()).getMeasurementTypeIdFromMeasurementTypeName(Measurement.WEIGHT);
        long localId = ((Session) this.session.get()).getUser().getLocalId();
        String str6 = Strings.isEmpty(str) ? MIN_DATE : str;
        String str7 = Strings.isEmpty(str2) ? MAX_DATE : str2;
        if (str6.compareTo(str7) >= 1) {
            str3 = str6;
            str4 = str7;
        } else {
            str4 = str6;
            str3 = str7;
        }
        User user = ((Session) this.session.get()).getUser();
        UserSummaryObject fetchUserSummaryWithCachedFallback = ((UserSummaryService) this.userSummaryService.get()).fetchUserSummaryWithCachedFallback(user.getUsername(), user.getUid());
        LocalizedWeight localizedWeight = null;
        if (fetchUserSummaryWithCachedFallback != null) {
            if (MIN_DATE.equals(str4)) {
                f3 = ((MeasurementsService) this.measurementsService.get()).getLeastRecentMeasurementValue(localId, measurementTypeIdFromMeasurementTypeName);
                userSummaryObject = fetchUserSummaryWithCachedFallback;
            } else {
                userSummaryObject = fetchUserSummaryWithCachedFallback;
                f3 = ((MeasurementsService) this.measurementsService.get()).getMeasurementValueForDate(localId, measurementTypeIdFromMeasurementTypeName, DateTimeUtils.parse(DATE_FORMAT, str4));
            }
            if (MAX_DATE.equals(str3)) {
                f4 = ((MeasurementsService) this.measurementsService.get()).getMostRecentMeasurementValue(localId, measurementTypeIdFromMeasurementTypeName);
            } else {
                f4 = ((MeasurementsService) this.measurementsService.get()).getMeasurementValueForDate(localId, measurementTypeIdFromMeasurementTypeName, DateTimeUtils.parse(DATE_FORMAT, str3));
            }
            f = f3 - f4;
            localizedWeight = LocalizedWeight.fromPounds((double) Math.abs(f));
            int i2 = isProgressPhotosNewsfeedOn ? f < BitmapDescriptorFactory.HUE_RED ? R.string.weight_artifact_gained_v2 : R.string.weight_artifact_lost_v2 : f < BitmapDescriptorFactory.HUE_RED ? R.string.progress_share_weight_gained : R.string.progress_share_weight_loss;
            if (isProgressPhotosNewsfeedOn) {
                str5 = LocalizedWeight.getLongDisplayString(this.context, localizedWeight, ((UserWeightService) this.userWeightService.get()).getUserCurrentWeightUnit());
            } else {
                str5 = LocalizedWeight.getDisplayString(this.context, localizedWeight, ((UserWeightService) this.userWeightService.get()).getUserCurrentWeightUnit());
            }
            i = 0;
            arrayList.add(new ArtifactViewModel(ArtifactType.WeightChange, Strings.toString(Float.valueOf(f)), this.context.getString(i2, new Object[]{str5})));
        } else {
            userSummaryObject = fetchUserSummaryWithCachedFallback;
            i = 0;
            f = BitmapDescriptorFactory.HUE_RED;
        }
        ArtifactViewModel artifactViewModel = new ArtifactViewModel(ArtifactType.Blank, "", "");
        if (z) {
            arrayList.add(i, artifactViewModel);
        } else {
            arrayList.add(artifactViewModel);
        }
        long totalStepCount = getTotalStepCount(str4, str3);
        int i3 = (totalStepCount > 0 ? 1 : (totalStepCount == 0 ? 0 : -1));
        if (i3 > 0) {
            ArtifactViewModel artifactViewModel2 = new ArtifactViewModel(ArtifactType.Steps, Strings.toString(Long.valueOf(totalStepCount)), this.context.getString(R.string.progress_share_steps, new Object[]{NumberUtils.localeStringFromLongWithSeparators(totalStepCount)}));
            if (z) {
                arrayList.add(1, artifactViewModel2);
            } else {
                arrayList.add(artifactViewModel2);
            }
        }
        long totalExerciseCount = getTotalExerciseCount(str4, str3);
        if (totalExerciseCount > 0) {
            f2 = f;
            j = totalStepCount;
            arrayList.add(new ArtifactViewModel(ArtifactType.Exercises, Strings.toString(Long.valueOf(totalExerciseCount)), this.context.getString(R.string.progress_share_exercises, new Object[]{NumberUtils.localeStringFromLongWithSeparators(totalExerciseCount)})));
            userSummaryObject2 = userSummaryObject;
        } else {
            f2 = f;
            j = totalStepCount;
            userSummaryObject2 = userSummaryObject;
        }
        if (userSummaryObject2 != null && userSummaryObject2.getLoginStreak() > 0) {
            arrayList.add(new ArtifactViewModel(ArtifactType.LoginStreak, Strings.toString(Integer.valueOf(userSummaryObject2.getLoginStreak())), this.context.getString(R.string.progress_share_streak, new Object[]{NumberUtils.localeStringFromIntWithSeparators(userSummaryObject2.getLoginStreak())})));
        }
        long totalMealsLogged = getTotalMealsLogged(str4, str3);
        if (totalMealsLogged > 0) {
            z2 = true;
            arrayList.add(new ArtifactViewModel(ArtifactType.Meals, Strings.toString(Long.valueOf(totalMealsLogged)), this.context.getString(R.string.progress_share_meals, new Object[]{NumberUtils.localeStringFromLongWithSeparators(totalMealsLogged)})));
        } else {
            z2 = true;
        }
        long numberOfDaysToShow = getNumberOfDaysToShow(str4, str3, userSummaryObject2);
        if (isProgressPhotosNewsfeedOn && userSummaryObject2 != null && i3 > 0 && numberOfDaysToShow > 0) {
            Weight userCurrentWeightUnit = ((UserWeightService) this.userWeightService.get()).getUserCurrentWeightUnit();
            if (userCurrentWeightUnit == Weight.STONES_POUNDS) {
                userCurrentWeightUnit = Weight.STONES;
            }
            ArtifactType artifactType = ArtifactType.Summary;
            String str8 = "";
            String displayStringWithoutUnit = LocalizedWeight.getDisplayStringWithoutUnit(this.context, localizedWeight, userCurrentWeightUnit);
            String formatWithSuffix = NumberUtils.formatWithSuffix(j);
            String localeStringFromLongWithSeparators = NumberUtils.localeStringFromLongWithSeparators(numberOfDaysToShow);
            if (f2 >= BitmapDescriptorFactory.HUE_RED) {
                z2 = false;
            }
            ArtifactViewModel artifactViewModel3 = new ArtifactViewModel(artifactType, str8, displayStringWithoutUnit, formatWithSuffix, localeStringFromLongWithSeparators, getWeightStringId(userCurrentWeightUnit, z2));
            arrayList.add(2, artifactViewModel3);
        }
        return arrayList;
    }

    private long getNumberOfDaysToShow(String str, String str2, UserSummaryObject userSummaryObject) {
        if (!Strings.equals(str, MIN_DATE) && !Strings.equals(str2, MAX_DATE)) {
            return DateTimeUtils.getNumberOfDaysBetween("yyyy-MM-dd", str, str2);
        }
        if (userSummaryObject == null || userSummaryObject.getLoginStreak() <= 0) {
            return 0;
        }
        return (long) userSummaryObject.getLoginStreak();
    }

    private int getWeightStringId(Weight weight, boolean z) {
        int i = AnonymousClass1.$SwitchMap$com$myfitnesspal$shared$util$UnitsUtils$Weight[weight.ordinal()];
        int i2 = R.string.lbs_gained;
        switch (i) {
            case 1:
                if (!z) {
                    i2 = R.string.lbs_lost;
                }
                return i2;
            case 2:
                return z ? R.string.kgs_gained : R.string.kgs_lost;
            case 3:
                return z ? R.string.stones_gained : R.string.stones_lost;
            default:
                if (!z) {
                    i2 = R.string.lbs_lost;
                }
                return i2;
        }
    }

    private long getTotalStepCount(String str, String str2) {
        String[] strArr = {String.format("SUM(%s)", new Object[]{"steps"})};
        String join = Strings.join(" AND ", (T[]) new String[]{"steps>0", "user_id=?", "entry_date BETWEEN ? AND ? "});
        return CursorUtils.readLongAndClose(this.exerciseEntriesTable.queryData(strArr, join, Long.valueOf(((Session) this.session.get()).getUser().getLocalId()), str, str2), 0);
    }

    private long getTotalExerciseCount(String str, String str2) {
        String[] strArr = {"COUNT(*)"};
        String join = Strings.join(" AND ", (T[]) new String[]{"user_id=?", "entry_date BETWEEN ? AND ? "});
        return CursorUtils.readLongAndClose(this.exerciseEntriesTable.queryData(strArr, join, Long.valueOf(((Session) this.session.get()).getUser().getLocalId()), str, str2), 0);
    }

    private long getTotalMealsLogged(String str, String str2) {
        return CursorUtils.readLongAndClose(this.db.rawQuery("SELECT COUNT(*) FROM (     SELECT id     FROM food_entries     WHERE user_id=? AND entry_date BETWEEN ? AND ?     GROUP BY entry_date, meal_id);", new String[]{String.valueOf(((Session) this.session.get()).getUser().getLocalId()), str, str2}), 0);
    }

    private void addWeightEntriesFromCursor(Cursor cursor, List<ProgressEntryViewModel> list, String str, Weight weight, boolean z) {
        int i;
        int i2;
        int i3;
        int i4;
        Cursor cursor2 = cursor;
        List<ProgressEntryViewModel> list2 = list;
        int columnIndex = cursor2.getColumnIndex("id");
        int columnIndex2 = cursor2.getColumnIndex("uid");
        int columnIndex3 = cursor2.getColumnIndex("value");
        int columnIndex4 = cursor2.getColumnIndex("entry_date");
        int columnIndex5 = cursor2.getColumnIndex("image_uid");
        int columnIndex6 = cursor2.getColumnIndex(Columns.LOCAL_FILEPATH);
        int columnIndex7 = cursor2.getColumnIndex(Fields.IMAGE_ASSOCIATION_LOCAL_ID);
        while (cursor.moveToNext()) {
            try {
                Date parse = Format.newIso8601DateFormat().parse(cursor2.getString(columnIndex4));
                float f = cursor2.getFloat(columnIndex3);
                try {
                    float actualValue = getActualValue(str, f, weight);
                    long j = cursor2.getLong(columnIndex);
                    i4 = columnIndex;
                    i3 = columnIndex2;
                    double d = (double) f;
                    i2 = columnIndex3;
                    i = columnIndex4;
                    try {
                        ProgressEntryViewModel progressEntryViewModel = r11;
                        ProgressEntryViewModel progressEntryViewModel2 = new ProgressEntryViewModel(j, cursor2.getString(columnIndex2), parse, d, (double) actualValue, cursor2.getString(columnIndex5), cursor2.getString(columnIndex6), cursor2.getLong(columnIndex7), !z);
                        if (z) {
                            list2.add(0, progressEntryViewModel);
                        } else {
                            list2.add(progressEntryViewModel);
                        }
                    } catch (ParseException e) {
                        e = e;
                        Ln.e(e);
                        columnIndex = i4;
                        columnIndex2 = i3;
                        columnIndex3 = i2;
                        columnIndex4 = i;
                    }
                } catch (ParseException e2) {
                    e = e2;
                    i4 = columnIndex;
                    i3 = columnIndex2;
                    i2 = columnIndex3;
                    i = columnIndex4;
                    Ln.e(e);
                    columnIndex = i4;
                    columnIndex2 = i3;
                    columnIndex3 = i2;
                    columnIndex4 = i;
                }
            } catch (ParseException e3) {
                e = e3;
                i4 = columnIndex;
                i3 = columnIndex2;
                i2 = columnIndex3;
                i = columnIndex4;
                Ln.e(e);
                columnIndex = i4;
                columnIndex2 = i3;
                columnIndex3 = i2;
                columnIndex4 = i;
            }
            columnIndex = i4;
            columnIndex2 = i3;
            columnIndex3 = i2;
            columnIndex4 = i;
        }
    }

    private float getActualValue(String str, float f, Weight weight) {
        return (!Measurements.validateType(str) || !Measurements.isWeight(str)) ? f : (float) LocalizedWeight.fromPounds((double) f).getValue(weight);
    }
}
