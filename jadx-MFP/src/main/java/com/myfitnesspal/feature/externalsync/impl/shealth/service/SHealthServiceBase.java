package com.myfitnesspal.feature.externalsync.impl.shealth.service;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection.Permission;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection.PermissionState;
import com.myfitnesspal.feature.externalsync.service.ExternalService;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.samsung.android.sdk.healthdata.HealthDataResolver;
import com.samsung.android.sdk.healthdata.HealthDataResolver.DeleteRequest.Builder;
import com.samsung.android.sdk.healthdata.HealthDataResolver.Filter;
import com.samsung.android.sdk.healthdata.HealthDataStore;
import com.samsung.android.sdk.healthdata.HealthDeviceManager;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.Lazy;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

abstract class SHealthServiceBase implements ExternalService {
    protected static final int DAYS_BACK_TO_SYNC = 2;
    protected static final long MS_PER_DAY = 86400000;
    private static Handler handler;
    private final Lazy<AppGalleryService> appGalleryService;
    private final Lazy<ConfigService> configService;
    private SHealthConnection connection;

    /* access modifiers changed from: protected */
    public abstract Set<Permission> getReadPermissions();

    /* access modifiers changed from: protected */
    public abstract Set<Permission> getWritePermissions();

    /* access modifiers changed from: protected */
    public abstract void syncRead();

    /* access modifiers changed from: protected */
    public abstract void syncWrite();

    SHealthServiceBase(SHealthConnection sHealthConnection, Lazy<ConfigService> lazy, Lazy<AppGalleryService> lazy2) {
        this.connection = sHealthConnection;
        this.configService = lazy;
        this.appGalleryService = lazy2;
    }

    public final void sync() {
        if (enabled() && this.connection.connectAndWait()) {
            if (hasPermission(getReadPermissions())) {
                syncRead();
            }
            if (hasPermission(getWritePermissions())) {
                syncWrite();
            }
        }
    }

    public final boolean enabled() {
        return ConfigUtils.isSHealthEnabled((ConfigService) this.configService.get()) && this.connection.isPaired();
    }

    /* access modifiers changed from: protected */
    public final HealthDataStore getDataStore() {
        return this.connection.getDataStore();
    }

    /* access modifiers changed from: protected */
    public final SHealthConnection getConnection() {
        return this.connection;
    }

    /* access modifiers changed from: protected */
    public boolean isAppConnected(String str) {
        return ((AppGalleryService) this.appGalleryService.get()).isAppConnected(str);
    }

    /* access modifiers changed from: protected */
    public boolean isWithinSyncDateRange(Date date) {
        return ((int) Math.abs(DateTimeUtils.getNumberOfDaysBetween(date, new Date()))) <= 2;
    }

    /* access modifiers changed from: protected */
    public final String getDeviceUuid() {
        HealthDataStore dataStore = getDataStore();
        if (dataStore != null) {
            return new HealthDeviceManager(dataStore).getLocalDevice().getUuid();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public final String getPackageName() {
        return getContext().getPackageName();
    }

    /* access modifiers changed from: protected */
    public final SQLiteDatabaseWrapper getDatabase() {
        return DbConnectionManager.getDb(getContext());
    }

    /* access modifiers changed from: protected */
    public final synchronized Handler getHandler() {
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
        return handler;
    }

    /* access modifiers changed from: protected */
    public final Context getContext() {
        return MyFitnessPalApp.getInstance();
    }

    protected static Date getDateForDayWithOffset(int i) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        instance.add(5, i);
        return instance.getTime();
    }

    /* access modifiers changed from: protected */
    public final void removeMfpResourcesFromSHealthOnDate(String str, Date date) {
        HealthDataStore dataStore = getDataStore();
        if (dataStore != null) {
            long time = date.getTime();
            long j = MS_PER_DAY + time;
            String packageName = getPackageName();
            new HealthDataResolver(dataStore, getHandler()).delete(new Builder().setDataType(str).setFilter(Filter.and(Filter.greaterThanEquals("start_time", Long.valueOf(time)), Filter.lessThan("start_time", Long.valueOf(j)), Filter.eq("pkg_name", packageName))).build()).await();
        }
    }

    private boolean hasPermission(Set<Permission> set) {
        for (Permission permissionState : set) {
            if (this.connection.getPermissionState(permissionState) != PermissionState.Granted) {
                return false;
            }
        }
        return true;
    }
}
