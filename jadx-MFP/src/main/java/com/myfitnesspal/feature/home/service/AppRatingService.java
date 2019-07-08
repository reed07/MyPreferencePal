package com.myfitnesspal.feature.home.service;

import android.content.Context;
import android.database.Cursor;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.table.DiaryNotesTable;
import com.myfitnesspal.shared.db.table.ExerciseEntriesTable;
import com.myfitnesspal.shared.db.table.FoodEntriesTable;
import com.myfitnesspal.shared.db.table.WaterEntriesTable;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.util.Database;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.asyncservice.SimpleAsyncServiceBase;
import com.uacf.core.constants.DateTime.Format;
import com.uacf.core.util.ApplicationUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.core.util.VersionUtils;
import dagger.Lazy;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;

public class AppRatingService extends SimpleAsyncServiceBase {
    private static final String DATE_FORMAT = Format.newIso8601DateFormat().toPattern();
    private final Context context;
    private Lazy<GlobalSettingsService> globalSettingsService;
    private Lazy<LocalSettingsService> localSettingsService;
    private Session session;

    /* access modifiers changed from: protected */
    public int getMaxThreads() {
        return 1;
    }

    @Inject
    public AppRatingService(Context context2, Session session2, Lazy<LocalSettingsService> lazy, Lazy<GlobalSettingsService> lazy2) {
        this.context = context2;
        this.session = session2;
        this.localSettingsService = lazy;
        this.globalSettingsService = lazy2;
    }

    /* access modifiers changed from: protected */
    public String getThreadName() {
        return getClass().getCanonicalName();
    }

    public boolean shouldShowDialog() {
        boolean dontAskForReview = ((LocalSettingsService) this.localSettingsService.get()).getDontAskForReview();
        boolean userHasReviewedApp = ((GlobalSettingsService) this.globalSettingsService.get()).getUserHasReviewedApp();
        Ln.d("REMIND: dontAsk = %s, userHasReviewedApp = %s", Boolean.valueOf(dontAskForReview), Boolean.valueOf(userHasReviewedApp));
        if (!dontAskForReview && !userHasReviewedApp) {
            Ln.i("user has not reviewed the app and the app just started.", new Object[0]);
            int appAgeInDays = getAppAgeInDays();
            int countDaysWithDiaryEntries = countDaysWithDiaryEntries();
            Ln.d("REMIND: age = %s, days = %s", Integer.valueOf(appAgeInDays), Integer.valueOf(countDaysWithDiaryEntries));
            if (appAgeInDays < 7 || appAgeInDays > 14) {
                if ((appAgeInDays > 14 && countDaysWithDiaryEntries == 0) || !activeForLast14Days()) {
                    ((LocalSettingsService) this.localSettingsService.get()).setAppInstallationDate(Long.valueOf(Calendar.getInstance().getTimeInMillis()));
                }
            } else if (countDaysWithDiaryEntries >= 3) {
                return true;
            }
        }
        return false;
    }

    public void runUpdateCheck() {
        if (hasAppBeenUpgraded()) {
            ((GlobalSettingsService) this.globalSettingsService.get()).setAppVersion(VersionUtils.getAppVersionName(this.context));
            if (getAppAgeInDays() > 7) {
                updateInstallationDate();
            }
        }
    }

    private int countDaysWithDiaryEntries() {
        Calendar instance = Calendar.getInstance();
        instance.setTime(installationDate());
        int appAgeInDays = getAppAgeInDays();
        String format = DateTimeUtils.format(DATE_FORMAT, instance.getTime());
        instance.add(5, appAgeInDays);
        String format2 = DateTimeUtils.format(DATE_FORMAT, instance.getTime());
        HashSet hashSet = new HashSet();
        getDiaryEntryDates(format, format2, FoodEntriesTable.TABLE_NAME, hashSet);
        getDiaryEntryDates(format, format2, ExerciseEntriesTable.TABLE_NAME, hashSet);
        getDiaryEntryDates(format, format2, DiaryNotesTable.TABLE_NAME, hashSet);
        getDiaryEntryDates(format, format2, WaterEntriesTable.TABLE_NAME, hashSet);
        return hashSet.size();
    }

    /* JADX INFO: finally extract failed */
    private void getDiaryEntryDates(String str, String str2, String str3, Set<String> set) {
        String str4 = str3;
        Cursor query = DbConnectionManager.getDb(this.context).query(true, str4, new String[]{"entry_date"}, "user_id=? AND entry_date>=? AND entry_date<=?", new String[]{String.valueOf(this.session.getUser().getUserV1().getLocalId()), str, str2}, null, null, null, null);
        while (query.moveToNext()) {
            try {
                set.add(query.getString(0));
            } catch (Throwable th) {
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        }
        if (query != null) {
            query.close();
        }
    }

    private static boolean activeForLast14Days() {
        Calendar instance = Calendar.getInstance();
        DiaryDay diaryDay = new DiaryDay();
        for (int i = 14; i > 0; i--) {
            diaryDay.initFromDatabaseForDate(instance.getTime());
            if (diaryDay.hasAnyFoodEntries() || diaryDay.hasAnyExerciseEntries() || diaryDay.hasAnyNotes() || diaryDay.hasWater()) {
                return true;
            }
            instance.add(5, -1);
        }
        return false;
    }

    private void setInstallationDate(Long l) {
        StringBuilder sb = new StringBuilder();
        sb.append("installation date set to: ");
        sb.append(Database.encodeDate(new Date(l.longValue())));
        Ln.i(sb.toString(), new Object[0]);
        ((LocalSettingsService) this.localSettingsService.get()).setAppInstallationDate(l);
    }

    private Date installationDate() {
        long longValue = ((LocalSettingsService) this.localSettingsService.get()).getAppInstallationDate().longValue();
        if (longValue == 0) {
            longValue = updateInstallationDate();
        }
        return longValue > 0 ? new Date(longValue) : new Date();
    }

    private boolean hasAppBeenUpgraded() {
        return !Strings.equals(VersionUtils.getAppVersionName(this.context), ((GlobalSettingsService) this.globalSettingsService.get()).getAppVersion());
    }

    private int getAppAgeInDays() {
        long timeInMillis = (Calendar.getInstance().getTimeInMillis() - installationDate().getTime()) / 86400000;
        long j = 0;
        if (timeInMillis < 0) {
            setInstallationDate(Long.valueOf(Calendar.getInstance().getTimeInMillis()));
        } else {
            j = timeInMillis;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("the application is ");
        sb.append(j);
        sb.append(" days old.");
        Ln.i(sb.toString(), new Object[0]);
        return (int) j;
    }

    private long updateInstallationDate() {
        long installationDate = ApplicationUtils.getInstallationDate(this.context);
        setInstallationDate(Long.valueOf(installationDate));
        return installationDate;
    }
}
