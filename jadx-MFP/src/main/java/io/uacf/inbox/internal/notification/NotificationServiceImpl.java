package io.uacf.inbox.internal.notification;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.support.annotation.Nullable;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.db.table.InstalledDatasetsTable;
import com.myfitnesspal.shared.db.table.MfpDatabaseTableV2.Columns;
import com.uacf.core.database.ContentValuesMapper;
import com.uacf.core.database.CursorMapper;
import com.uacf.core.database.DatabaseUtil;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.CursorUtils;
import com.uacf.core.util.Function2;
import com.uacf.core.util.FunctionUtils;
import com.uacf.core.util.Strings;
import com.uacf.sync.engine.UacfScheduleException;
import com.uacf.sync.provider.sdk.model.SyncItem;
import io.uacf.core.api.ApiResponse;
import io.uacf.core.api.ApiResponseBase;
import io.uacf.core.api.UacfApiEnvironmentProvider;
import io.uacf.core.api.UacfApiException;
import io.uacf.core.api.UacfUserAgentProvider;
import io.uacf.core.app.UacfAppId;
import io.uacf.core.auth.UacfAuthProvider;
import io.uacf.core.interfaces.UacfClientEventsCallback;
import io.uacf.inbox.internal.database.NotificationTable;
import io.uacf.inbox.internal.model.DebugPayload;
import io.uacf.inbox.internal.model.Notification;
import io.uacf.inbox.internal.model.Notification.Builder;
import io.uacf.inbox.internal.model.NotificationList;
import io.uacf.inbox.sdk.analytics.Attributes.NotificationDeleted;
import io.uacf.inbox.sdk.analytics.Attributes.NotificationExpired;
import io.uacf.inbox.sdk.analytics.Attributes.NotificationRead;
import io.uacf.inbox.sdk.analytics.Attributes.NotificationReceived;
import io.uacf.inbox.sdk.analytics.Attributes.NotificationsOpened;
import io.uacf.net.retrofit.RetrofitBasedServiceImpl;
import io.uacf.net.retrofit.UacfApiRetrofitBuilder;
import io.uacf.net.retrofit.UacfRetrofitHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class NotificationServiceImpl extends RetrofitBasedServiceImpl implements NotificationService {
    private static final String BASE_GET_ALL_NOTIFICATIONS_CLAUSE;
    private static final List<Integer> NON_DELETED_SYNC_FLAGS = Arrays.asList(new Integer[]{Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0)});
    private static final List<Integer> SYNC_FLAGS_DELETE = Arrays.asList(new Integer[]{Integer.valueOf(3)});
    private static final List<Integer> SYNC_FLAGS_UPDATE = Arrays.asList(new Integer[]{Integer.valueOf(2)});
    private static String overrideCollapseKey;
    private final UacfAppId appId;
    private final UacfClientEventsCallback clientEventsCallback;
    private final NotificationTable notificationTable;

    private interface NotificationApiConsumer {
        @POST("debug/")
        Call<Void> createDebugNotification(@Body DebugPayload debugPayload);

        @DELETE("v1/inbox/{application}/notification/{engagement_id}")
        Call<Void> deleteNotificationWithEngagementId(@Path("application") String str, @Path("engagement_id") String str2);

        @GET("v1/inbox/{application}/notification/{engagement_id}")
        Call<ApiResponse<Notification>> fetchNotificationWithEngagementId(@Path("application") String str, @Path("engagement_id") String str2);

        @GET("v1/inbox/{application}/notification")
        Call<NotificationList> fetchNotifications(@Path("application") String str, @Query("limit") int i, @Query("after") String str2);

        @PATCH("v1/inbox/{application}/notification/state")
        Call<ApiResponseBase> updateBatch(@Path("application") String str, @Body List<NotificationBatchUpdateData> list);

        @PATCH("v1/inbox/{application}/notification/{engagement_id}")
        Call<ApiResponse<Notification>> updateNotificationWithEngagementId(@Path("application") String str, @Path("engagement_id") String str2, @Body NotificationUpdateRequest notificationUpdateRequest);
    }

    public String getSyncResourceName() {
        return "notification";
    }

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("user_id = ? AND deleted = 0 AND sync_flags IN (");
        sb.append(Strings.join(",", (Collection<T>) NON_DELETED_SYNC_FLAGS));
        sb.append(")");
        sb.append(" AND ");
        sb.append("marked_as_expired");
        sb.append(" = 0");
        BASE_GET_ALL_NOTIFICATIONS_CLAUSE = sb.toString();
    }

    public NotificationServiceImpl(Context context, UacfAppId uacfAppId, UacfUserAgentProvider uacfUserAgentProvider, UacfApiEnvironmentProvider uacfApiEnvironmentProvider, UacfAuthProvider uacfAuthProvider, UacfClientEventsCallback uacfClientEventsCallback, SQLiteDatabaseWrapper sQLiteDatabaseWrapper, @Nullable OkHttpClient okHttpClient) {
        super(context, uacfUserAgentProvider, uacfApiEnvironmentProvider, uacfAuthProvider, okHttpClient);
        this.appId = uacfAppId;
        this.clientEventsCallback = uacfClientEventsCallback;
        this.notificationTable = new NotificationTable(sQLiteDatabaseWrapper);
    }

    public List<Notification> getAllNotifications() {
        Cursor cursor;
        try {
            cursor = getAllNotificationsAsCursor();
            if (cursor != null) {
                try {
                    List<Notification> listOfNotificationsFromCursor = getListOfNotificationsFromCursor(cursor);
                    CursorUtils.close(cursor);
                    return listOfNotificationsFromCursor;
                } catch (Throwable th) {
                    th = th;
                    CursorUtils.close(cursor);
                    throw th;
                }
            } else {
                CursorUtils.close(cursor);
                return new ArrayList();
            }
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
            CursorUtils.close(cursor);
            throw th;
        }
    }

    public Cursor getAllNotificationsAsCursor() {
        return getCursorForNotifications(null);
    }

    public Notification getObjectFromCursor(Cursor cursor) {
        if (CursorUtils.isValid(cursor)) {
            return new Notification(new CursorMapper(cursor));
        }
        return null;
    }

    public int getCountForStates(String... strArr) {
        HashSet hashSet = new HashSet();
        for (String add : strArr) {
            hashSet.add(add);
        }
        Cursor cursor = null;
        try {
            cursor = getCursorForNotifications(hashSet);
            if (cursor != null) {
                return cursor.getCount();
            }
            CursorUtils.close(cursor);
            return 0;
        } finally {
            CursorUtils.close(cursor);
        }
    }

    public Notification localGetNotification(String str) {
        List localGetNotifications = localGetNotifications(Collections.singletonList(str));
        if (localGetNotifications == null) {
            return null;
        }
        return (Notification) localGetNotifications.get(0);
    }

    public List<Notification> localGetNotifications(List<String> list) {
        String uacfUserId = this.authProvider.getUacfUserId();
        Cursor cursor = null;
        if (Strings.isEmpty(uacfUserId)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("user_id = ? AND engagement_id IN (");
        sb.append(DatabaseUtil.getQuestionMarkString(CollectionUtils.size((Collection<?>) list)));
        sb.append(")");
        sb.append(" AND ");
        sb.append("deleted");
        sb.append(" = 0");
        sb.append(" AND ");
        sb.append(Columns.SYNC_FLAGS);
        sb.append(" IN (");
        sb.append(Strings.join(",", (Collection<T>) NON_DELETED_SYNC_FLAGS));
        sb.append(")");
        sb.append(" AND ");
        sb.append("marked_as_expired");
        sb.append(" = 0");
        String sb2 = sb.toString();
        try {
            ArrayList arrayList = new ArrayList();
            arrayList.add(uacfUserId);
            arrayList.addAll(list);
            Cursor queryDataOrderBy = this.notificationTable.queryDataOrderBy(false, null, sb2, "created_at DESC", arrayList.toArray(new Object[arrayList.size()]));
            try {
                List<Notification> listOfFilteredNotificationsFromCursor = getListOfFilteredNotificationsFromCursor(queryDataOrderBy);
                if (!queryDataOrderBy.moveToFirst() || listOfFilteredNotificationsFromCursor == null || listOfFilteredNotificationsFromCursor.isEmpty()) {
                    listOfFilteredNotificationsFromCursor = null;
                }
                CursorUtils.close(queryDataOrderBy);
                return listOfFilteredNotificationsFromCursor;
            } catch (Throwable th) {
                th = th;
                cursor = queryDataOrderBy;
                CursorUtils.close(cursor);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            CursorUtils.close(cursor);
            throw th;
        }
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r2v2 */
    /* JADX WARNING: type inference failed for: r2v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public io.uacf.inbox.internal.model.Notification localGetNotificationEvenIfDeleted(java.lang.String r7) {
        /*
            r6 = this;
            io.uacf.core.auth.UacfAuthProvider r0 = r6.authProvider
            java.lang.String r0 = r0.getUacfUserId()
            boolean r1 = com.uacf.core.util.Strings.isEmpty(r0)
            r2 = 0
            if (r1 == 0) goto L_0x000e
            return r2
        L_0x000e:
            io.uacf.inbox.internal.database.NotificationTable r1 = r6.notificationTable     // Catch:{ all -> 0x0036 }
            java.lang.String r3 = "user_id = ?  AND engagement_id = ?"
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x0036 }
            r5 = 0
            r4[r5] = r0     // Catch:{ all -> 0x0036 }
            r0 = 1
            r4[r0] = r7     // Catch:{ all -> 0x0036 }
            android.database.Cursor r7 = r1.queryData(r2, r3, r4)     // Catch:{ all -> 0x0036 }
            boolean r0 = r7.moveToFirst()     // Catch:{ all -> 0x0033 }
            if (r0 == 0) goto L_0x002f
            io.uacf.inbox.internal.model.Notification r2 = new io.uacf.inbox.internal.model.Notification     // Catch:{ all -> 0x0033 }
            com.uacf.core.database.CursorMapper r0 = new com.uacf.core.database.CursorMapper     // Catch:{ all -> 0x0033 }
            r0.<init>(r7)     // Catch:{ all -> 0x0033 }
            r2.<init>(r0)     // Catch:{ all -> 0x0033 }
        L_0x002f:
            com.uacf.core.util.CursorUtils.close(r7)
            return r2
        L_0x0033:
            r0 = move-exception
            r2 = r7
            goto L_0x0037
        L_0x0036:
            r0 = move-exception
        L_0x0037:
            com.uacf.core.util.CursorUtils.close(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.uacf.inbox.internal.notification.NotificationServiceImpl.localGetNotificationEvenIfDeleted(java.lang.String):io.uacf.inbox.internal.model.Notification");
    }

    public boolean localInsertOrUpdateNotification(Notification notification) {
        if (this.notificationTable.insertOrUpdateData(getContentValuesFor(notification), "user_id = ?  AND engagement_id = ?", this.authProvider.getUacfUserId(), notification.getEngagementId()) > 0) {
            return true;
        }
        return false;
    }

    public boolean localUpdateNotifications(List<String> list, String str) {
        return internalLocalUpdateNotificationsState(list, str, 2);
    }

    public boolean localUpdateNotifications(String str, String str2) {
        int i;
        int i2;
        int i3;
        String uacfUserId = this.authProvider.getUacfUserId();
        boolean z = false;
        if (Strings.isEmpty(uacfUserId)) {
            return false;
        }
        boolean z2 = Strings.equals(str, "PENDING") && Strings.equals(str2, "UNREAD");
        if (z2) {
            i3 = getCountForStates("PENDING");
            i2 = getCountForStates("UNREAD");
            i = getCountForStates("READ");
        } else {
            i3 = 0;
            i2 = 0;
            i = 0;
        }
        int updateData = this.notificationTable.updateData(new ContentValuesMapper().put(Columns.SYNC_FLAGS, Integer.valueOf(2)).put("state", Strings.toString(str2)).get(), "user_id = ? AND deleted = 0 AND state = ?", uacfUserId, str);
        if (z2) {
            this.clientEventsCallback.reportEvent("nissdk_notifications_opened", new NotificationsOpened(i3, i2, i));
        }
        if (updateData > 0) {
            z = true;
        }
        return z;
    }

    public boolean localExpireNotifications(List<String> list) {
        String uacfUserId = this.authProvider.getUacfUserId();
        boolean z = false;
        if (Strings.isEmpty(uacfUserId) || list == null || list.isEmpty()) {
            return false;
        }
        ContentValues contentValues = new ContentValuesMapper().put("expires_at", Long.valueOf(System.currentTimeMillis())).put("deleted", Boolean.valueOf(true)).put("marked_as_expired", Boolean.valueOf(true)).get();
        StringBuilder sb = new StringBuilder();
        sb.append("user_id = ?  AND engagement_id IN (");
        sb.append(DatabaseUtil.getQuestionMarkString(CollectionUtils.size((Collection<?>) list)));
        sb.append(")");
        sb.append(" AND ");
        sb.append("deleted");
        sb.append(" = 0");
        String sb2 = sb.toString();
        ArrayList arrayList = new ArrayList();
        arrayList.add(uacfUserId);
        arrayList.addAll(list);
        if (this.notificationTable.updateData(contentValues, sb2, arrayList.toArray(new Object[arrayList.size()])) > 0) {
            z = true;
        }
        return z;
    }

    public void reportNotificationRead(String str, int i, int i2) {
        Notification localGetNotification = localGetNotification(str);
        NotificationRead notificationRead = new NotificationRead(str, getAnalyticData(localGetNotification), i, i2, localGetNotification.getPriority());
        this.clientEventsCallback.reportEvent("nissdk_notification_read", notificationRead);
    }

    private void reportNotificationExpired(Notification notification) {
        this.clientEventsCallback.reportEvent("nissdk_notification_expired", new NotificationExpired(notification.getEngagementId(), getAnalyticData(notification), notification.getState(), notification.getPriority()));
    }

    private void reportNotificationReceived(Notification notification) {
        Calendar instance = Calendar.getInstance();
        long timeInMillis = instance.getTimeInMillis();
        instance.setTime(notification.getCreatedAt());
        NotificationReceived notificationReceived = new NotificationReceived(notification.getEngagementId(), getAnalyticData(notification), timeInMillis - instance.getTimeInMillis(), notification.getPriority());
        this.clientEventsCallback.reportEvent("nissdk_notification_received", notificationReceived);
    }

    @Nullable
    private Map<String, Object> getAnalyticData(Notification notification) {
        if (notification.getAnalyticData() == null) {
            return null;
        }
        return notification.getAnalyticData().getData();
    }

    public boolean localDeleteNotification(String str) {
        return localDeleteNotifications(Collections.singletonList(str));
    }

    public boolean localDeleteNotifications(List<String> list) {
        boolean localMarkNotificationsDeleted = localMarkNotificationsDeleted(list);
        localMarkCollapsedNotificationsDeleted(list);
        return localMarkNotificationsDeleted;
    }

    private boolean localMarkNotificationsDeleted(List<String> list) {
        String uacfUserId = this.authProvider.getUacfUserId();
        boolean z = false;
        if (Strings.isEmpty(uacfUserId) || CollectionUtils.isEmpty((Collection<?>) list)) {
            return false;
        }
        List<Notification> localGetNotifications = localGetNotifications(list);
        ContentValuesMapper put = new ContentValuesMapper().put("deleted", Boolean.valueOf(true)).put(Columns.SYNC_FLAGS, Integer.valueOf(3));
        StringBuilder sb = new StringBuilder();
        sb.append("user_id = ?  AND engagement_id IN (");
        sb.append(DatabaseUtil.getQuestionMarkString(CollectionUtils.size((Collection<?>) list)));
        sb.append(")");
        sb.append(" AND ");
        sb.append("deleted");
        sb.append(" = 0");
        String sb2 = sb.toString();
        ArrayList arrayList = new ArrayList();
        arrayList.add(uacfUserId);
        arrayList.addAll(list);
        if (this.notificationTable.updateData(put.get(), sb2, arrayList.toArray(new Object[arrayList.size()])) > 0) {
            z = true;
        }
        if (z) {
            for (Notification notification : localGetNotifications) {
                this.clientEventsCallback.reportEvent("nissdk_notification_deleted", new NotificationDeleted(notification.getEngagementId(), getAnalyticData(notification), notification.getState(), notification.getPriority()));
            }
        }
        return z;
    }

    private boolean localMarkCollapsedNotificationsDeleted(List<String> list) {
        int i;
        String uacfUserId = this.authProvider.getUacfUserId();
        if (Strings.isEmpty(uacfUserId) || CollectionUtils.isEmpty((Collection<?>) list)) {
            return false;
        }
        List localGetCollapsedKeys = localGetCollapsedKeys(list);
        boolean z = true;
        if (localGetCollapsedKeys == null || localGetCollapsedKeys.isEmpty()) {
            i = 0;
        } else {
            ContentValuesMapper put = new ContentValuesMapper().put("deleted", Boolean.valueOf(true));
            StringBuilder sb = new StringBuilder();
            sb.append("user_id = ?  AND collapse_key IN (");
            sb.append(DatabaseUtil.getQuestionMarkString(CollectionUtils.size((Collection<?>) localGetCollapsedKeys)));
            sb.append(")");
            sb.append(" AND ");
            sb.append("deleted");
            sb.append(" = 0");
            String sb2 = sb.toString();
            ArrayList arrayList = new ArrayList();
            arrayList.add(uacfUserId);
            arrayList.addAll(localGetCollapsedKeys);
            i = this.notificationTable.updateData(put.get(), sb2, arrayList.toArray(new Object[arrayList.size()]));
        }
        if (i <= 0) {
            z = false;
        }
        return z;
    }

    private List<String> localGetCollapsedKeys(List<String> list) {
        String uacfUserId = this.authProvider.getUacfUserId();
        Cursor cursor = null;
        if (Strings.isEmpty(uacfUserId)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("user_id = ?  AND engagement_id IN (");
        sb.append(DatabaseUtil.getQuestionMarkString(CollectionUtils.size((Collection<?>) list)));
        sb.append(")");
        String sb2 = sb.toString();
        try {
            ArrayList arrayList = new ArrayList();
            arrayList.add(uacfUserId);
            arrayList.addAll(list);
            Cursor queryData = this.notificationTable.queryData(new String[]{"collapse_key"}, sb2, arrayList.toArray(new Object[arrayList.size()]));
            try {
                List<String> listOfCollapseKeysFromCursor = getListOfCollapseKeysFromCursor(queryData);
                if (!queryData.moveToFirst() || listOfCollapseKeysFromCursor == null || listOfCollapseKeysFromCursor.isEmpty()) {
                    listOfCollapseKeysFromCursor = null;
                }
                CursorUtils.close(queryData);
                return listOfCollapseKeysFromCursor;
            } catch (Throwable th) {
                th = th;
                cursor = queryData;
                CursorUtils.close(cursor);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            CursorUtils.close(cursor);
            throw th;
        }
    }

    public void remoteUpdateNotificationsWithEngagementIds(List<NotificationBatchUpdateData> list) throws UacfApiException {
        UacfRetrofitHelper.execute(((NotificationApiConsumer) getConsumerWithUnderscoresAndBearerAuth()).updateBatch(getApiCompliantAppId(this.appId), list));
    }

    public void remoteDeleteNotificationWithEngagementId(String str) throws UacfApiException {
        UacfRetrofitHelper.execute(((NotificationApiConsumer) getConsumerWithUnderscoresAndBearerAuth()).deleteNotificationWithEngagementId(getApiCompliantAppId(this.appId), str));
    }

    public void createDebugNotification(String str) throws UacfApiException {
        createDebugNotification(false, -1, str, null);
    }

    public void createDebugNotification(boolean z, long j, String str) throws UacfApiException {
        createDebugNotification(z, j, null, str);
    }

    public void createDebugNotification(boolean z, long j, String str, String str2) throws UacfApiException {
        String uacfUserId = this.authProvider.getUacfUserId();
        if (!Strings.isEmpty(uacfUserId)) {
            NotificationApiConsumer notificationApiConsumer = (NotificationApiConsumer) getConsumerWithUnderscoresAndBearerAuth();
            DebugPayload debugPayload = new DebugPayload(uacfUserId, this.appId, overrideCollapseKey, str, z, j, str2);
            UacfRetrofitHelper.execute(notificationApiConsumer.createDebugNotification(debugPayload));
        }
    }

    /* access modifiers changed from: protected */
    public Class<?> getConsumerClass() {
        return NotificationApiConsumer.class;
    }

    /* access modifiers changed from: protected */
    public <TConsumer> UacfApiRetrofitBuilder<TConsumer> getBuilderWithUnderscoresAndBearerAuth() {
        return (UacfApiRetrofitBuilder) super.getBuilderWithUnderscoresAndBearerAuth().addHeader("UACF-User-ID", this.authProvider.getUacfUserId());
    }

    public Class<?> getSyncItemClass() {
        return Notification.class;
    }

    public void consumeSyncItems(List<SyncItem<Notification>> list) {
        if (!Strings.isEmpty(this.authProvider.getUacfUserId())) {
            for (SyncItem syncItem : list) {
                Notification notification = (Notification) syncItem.getItem();
                switch (syncItem.getAction()) {
                    case Delete:
                        markItemDeleted(syncItem.getId());
                        break;
                    case Create:
                        if (notification.isExpired() && notification.getState().equals("PENDING")) {
                            notification = new Builder(notification).withDeleted(true).withMarkedAsExpired(true).build();
                            reportNotificationExpired(notification);
                        }
                        localInsertOrUpdateNotification(notification);
                        reportNotificationReceived(notification);
                        break;
                    case Update:
                        localInsertOrUpdateNotification(notification);
                        break;
                }
            }
        }
    }

    public void publishUnsyncedItems(Function2<Integer, Integer> function2) throws UacfScheduleException {
        Cursor cursor;
        Cursor cursor2;
        Notification notification;
        String uacfUserId = this.authProvider.getUacfUserId();
        if (!Strings.isEmpty(uacfUserId)) {
            String str = "user_id = ? AND sync_flags = ?";
            try {
                cursor = this.notificationTable.queryData(null, str, this.authProvider.getUacfUserId(), SYNC_FLAGS_DELETE);
                try {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    sb.append(" AND deleted = 0");
                    String sb2 = sb.toString();
                    cursor2 = this.notificationTable.queryData(null, sb2, uacfUserId, SYNC_FLAGS_UPDATE);
                } catch (Throwable th) {
                    th = th;
                    cursor2 = null;
                    CursorUtils.close(cursor);
                    CursorUtils.close(cursor2);
                    throw th;
                }
                try {
                    int count = cursor.getCount() + cursor2.getCount();
                    CursorMapper cursorMapper = new CursorMapper(cursor);
                    int i = 0;
                    while (cursorMapper.moveToNext()) {
                        notification = new Notification(cursorMapper);
                        remoteDeleteNotificationWithEngagementId(notification.getEngagementId());
                        clearSyncFlags(notification);
                        i++;
                        FunctionUtils.invokeIfValid(function2, Integer.valueOf(i), Integer.valueOf(count));
                    }
                    HashMap hashMap = new HashMap();
                    CursorMapper cursorMapper2 = new CursorMapper(cursor2);
                    while (cursorMapper2.moveToNext()) {
                        Notification notification2 = new Notification(cursorMapper2);
                        NotificationBatchUpdateData notificationBatchUpdateData = (NotificationBatchUpdateData) hashMap.get(notification2.getState());
                        if (notificationBatchUpdateData == null) {
                            notificationBatchUpdateData = new NotificationBatchUpdateData(notification2.getState());
                            hashMap.put(notification2.getState(), notificationBatchUpdateData);
                        }
                        notificationBatchUpdateData.add(notification2.getEngagementId());
                    }
                    if (CollectionUtils.notEmpty((Map<?, ?>) hashMap)) {
                        ArrayList<NotificationBatchUpdateData> arrayList = new ArrayList<>(hashMap.values());
                        remoteUpdateNotificationsWithEngagementIds(arrayList);
                        ArrayList arrayList2 = new ArrayList();
                        for (NotificationBatchUpdateData engagementIds : arrayList) {
                            arrayList2.addAll(engagementIds.getEngagementIds());
                        }
                        internalLocalUpdateNotificationsState(arrayList2, null, 0);
                        FunctionUtils.invokeIfValid(function2, Integer.valueOf(count), Integer.valueOf(count));
                    }
                    CursorUtils.close(cursor);
                    CursorUtils.close(cursor2);
                } catch (UacfApiException e) {
                    throw new UacfScheduleException(e);
                } catch (UacfApiException e2) {
                    handleExceptionForItem(notification, e2);
                } catch (Throwable th2) {
                    th = th2;
                    CursorUtils.close(cursor);
                    CursorUtils.close(cursor2);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                cursor2 = null;
                cursor = null;
                CursorUtils.close(cursor);
                CursorUtils.close(cursor2);
                throw th;
            }
        }
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handleExceptionForItem(io.uacf.inbox.internal.model.Notification r5, io.uacf.core.api.UacfApiException r6) throws com.uacf.sync.engine.UacfScheduleException {
        /*
            r4 = this;
            int r0 = r6.getStatusCode()
            r1 = 1
            r2 = 0
            switch(r0) {
                case 403: goto L_0x003d;
                case 404: goto L_0x000a;
                default: goto L_0x0009;
            }
        L_0x0009:
            goto L_0x003c
        L_0x000a:
            java.lang.String r0 = r6.getBody()
            boolean r0 = com.uacf.core.util.Strings.notEmpty(r0)
            if (r0 == 0) goto L_0x003c
            com.uacf.core.mapping.GsonObjectMapper r0 = new com.uacf.core.mapping.GsonObjectMapper
            com.google.gson.FieldNamingPolicy r3 = com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES
            r0.<init>(r3)
            java.lang.Class<io.uacf.core.api.ApiResponseBase> r3 = io.uacf.core.api.ApiResponseBase.class
            com.uacf.core.mapping.GsonObjectMapper r0 = r0.withType(r3)
            java.lang.String r3 = r6.getBody()
            java.lang.Object r0 = r0.tryMapFrom(r3)
            io.uacf.core.api.ApiResponseBase r0 = (io.uacf.core.api.ApiResponseBase) r0
            if (r0 == 0) goto L_0x003a
            java.lang.String r0 = r0.getError()
            java.lang.String r3 = "not_found"
            boolean r0 = com.uacf.core.util.Strings.equals(r0, r3)
            if (r0 == 0) goto L_0x003a
            goto L_0x003d
        L_0x003a:
            r1 = 0
            goto L_0x003d
        L_0x003c:
            r1 = 0
        L_0x003d:
            if (r1 == 0) goto L_0x0043
            r4.markItemDeletedAndClearSyncFlags(r5)
            return
        L_0x0043:
            com.uacf.sync.engine.UacfScheduleException r5 = new com.uacf.sync.engine.UacfScheduleException
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.uacf.inbox.internal.notification.NotificationServiceImpl.handleExceptionForItem(io.uacf.inbox.internal.model.Notification, io.uacf.core.api.UacfApiException):void");
    }

    private List<String> getListOfEngagementIdsFromCursor(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        CursorMapper cursorMapper = new CursorMapper(cursor);
        while (cursorMapper.moveToNext()) {
            String string = cursorMapper.getString(Attributes.ENGAGEMENT_ID);
            if (!Strings.isEmpty(string)) {
                arrayList.add(string);
            }
        }
        return arrayList;
    }

    private List<String> getListOfCollapseKeysFromCursor(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        CursorMapper cursorMapper = new CursorMapper(cursor);
        while (cursorMapper.moveToNext()) {
            String string = cursorMapper.getString("collapse_key");
            if (!Strings.isEmpty(string)) {
                arrayList.add(string);
            }
        }
        return arrayList;
    }

    private List<Notification> getListOfFilteredNotificationsFromCursor(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        CursorMapper cursorMapper = new CursorMapper(cursor);
        while (cursorMapper.moveToNext()) {
            Notification notification = new Notification(cursorMapper);
            if (!notification.isExpired() || notification.getMarkedAsExpired()) {
                arrayList.add(notification);
            } else {
                setNotificationExpired(notification);
            }
        }
        return arrayList;
    }

    private void setNotificationsExpired(List<String> list) {
        for (String localGetNotificationEvenIfDeleted : list) {
            setNotificationExpired(localGetNotificationEvenIfDeleted(localGetNotificationEvenIfDeleted));
        }
    }

    private void setNotificationExpired(Notification notification) {
        Notification build = new Builder(notification).withDeleted(true).withMarkedAsExpired(true).build();
        localInsertOrUpdateNotification(build);
        reportNotificationExpired(build);
    }

    private List<Notification> getListOfNotificationsFromCursor(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        CursorMapper cursorMapper = new CursorMapper(cursor);
        while (cursorMapper.moveToNext()) {
            arrayList.add(new Notification(cursorMapper));
        }
        return arrayList;
    }

    private void markItemDeletedAndClearSyncFlags(Notification notification) {
        updateItem(new Builder(notification).withSyncFlags(0).withDeleted(true).build());
    }

    private void markItemDeleted(String str) {
        String uacfUserId = this.authProvider.getUacfUserId();
        if (!Strings.isEmpty(uacfUserId) && !Strings.isEmpty(str)) {
            this.notificationTable.updateData(new ContentValuesMapper().put("deleted", Boolean.valueOf(true)).get(), "user_id = ?  AND engagement_id = ?", uacfUserId, str);
        }
    }

    private void clearSyncFlags(Notification notification) {
        updateItem(new Builder(notification).withSyncFlags(0).build());
    }

    private void updateItem(Notification notification) {
        String uacfUserId = this.authProvider.getUacfUserId();
        if (!Strings.isEmpty(uacfUserId)) {
            this.notificationTable.updateData(getContentValuesFor(notification), "user_id = ?  AND engagement_id = ?", uacfUserId, notification.getEngagementId());
        }
    }

    private String getApiCompliantAppId(UacfAppId uacfAppId) {
        return uacfAppId.toString().toLowerCase(Locale.ENGLISH);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0042 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.database.Cursor getCursorForNotifications(java.util.Collection<java.lang.String> r5) {
        /*
            r4 = this;
            io.uacf.inbox.sdk.UacfNotificationInboxSettings r0 = io.uacf.inbox.sdk.UacfNotificationSdk.getSettings()
            boolean r0 = r0.getLimitPriorityToOnePerCategory()
            io.uacf.inbox.sdk.UacfNotificationInboxSettings r1 = io.uacf.inbox.sdk.UacfNotificationSdk.getSettings()
            int r1 = r1.getMaxPriorityCount()
            io.uacf.core.auth.UacfAuthProvider r2 = r4.authProvider
            java.lang.String r2 = r2.getUacfUserId()
            boolean r2 = com.uacf.core.util.Strings.isEmpty(r2)
            r3 = 0
            if (r2 == 0) goto L_0x001e
            return r3
        L_0x001e:
            r4.checkForNeedToBeDeletedNotifications()
            android.database.Cursor r0 = r4.getPriorityCursor(r5, r1, r0)     // Catch:{ SQLiteException -> 0x002c }
            android.database.Cursor r3 = r4.getRestCursor(r5, r0)     // Catch:{ SQLiteException -> 0x002a }
            goto L_0x0031
        L_0x002a:
            r5 = move-exception
            goto L_0x002e
        L_0x002c:
            r5 = move-exception
            r0 = r3
        L_0x002e:
            com.uacf.core.util.Ln.e(r5)
        L_0x0031:
            if (r0 == 0) goto L_0x0042
            android.database.MergeCursor r5 = new android.database.MergeCursor
            r1 = 2
            android.database.Cursor[] r1 = new android.database.Cursor[r1]
            r2 = 0
            r1[r2] = r0
            r0 = 1
            r1[r0] = r3
            r5.<init>(r1)
            return r5
        L_0x0042:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.uacf.inbox.internal.notification.NotificationServiceImpl.getCursorForNotifications(java.util.Collection):android.database.Cursor");
    }

    private void checkForNeedToBeDeletedNotifications() {
        String uacfUserId = this.authProvider.getUacfUserId();
        if (!Strings.isEmpty(uacfUserId)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(uacfUserId);
            Cursor cursor = null;
            try {
                StringBuilder sb = new StringBuilder();
                sb.append(BASE_GET_ALL_NOTIFICATIONS_CLAUSE);
                sb.append(" AND ");
                sb.append("expires_at");
                sb.append(" <= ");
                sb.append(System.currentTimeMillis());
                sb.append(" AND ");
                sb.append("expires_at");
                sb.append(" > 0");
                cursor = this.notificationTable.getDatabase().query("notification", null, sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]), null, null, null);
                setNotificationsExpired(getListOfEngagementIdsFromCursor(cursor));
            } finally {
                CursorUtils.close(cursor);
            }
        }
    }

    private Cursor getPriorityCursor(Collection<String> collection, int i, boolean z) {
        if (i <= -1) {
            return null;
        }
        List buildPriorityArgs = buildPriorityArgs(collection, i, z);
        return this.notificationTable.getDatabase().rawQuery(buildPriorityQueryString(collection, i, z), (String[]) buildPriorityArgs.toArray(new String[buildPriorityArgs.size()]));
    }

    private List<String> buildPriorityArgs(Collection<String> collection, int i, boolean z) {
        List<String> buildBaseArgs = buildBaseArgs(collection);
        if (i > -1 && z) {
            buildBaseArgs.addAll(buildBaseArgs(collection));
        }
        return buildBaseArgs;
    }

    private String buildBaseWhereClause(Collection<String> collection) {
        String str = BASE_GET_ALL_NOTIFICATIONS_CLAUSE;
        int size = CollectionUtils.size(collection);
        if (size <= 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" AND state IN (");
        sb.append(DatabaseUtil.getQuestionMarkString(size));
        sb.append(")");
        return sb.toString();
    }

    private List<String> buildBaseArgs(Collection<String> collection) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.authProvider.getUacfUserId());
        if (CollectionUtils.size(collection) > 0) {
            arrayList.addAll(collection);
        }
        return arrayList;
    }

    private String buildPriorityQueryString(Collection<String> collection, int i, boolean z) {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(buildBaseWhereClause(collection));
        sb.append(" AND ");
        sb.append(InstalledDatasetsTable.Columns.PRIORITY);
        sb.append(" = 1");
        String sb2 = sb.toString();
        if (z) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("( ");
            sb3.append(buildGroupByCollapseKeyQueryString(collection));
            sb3.append(" )");
            str = sb3.toString();
        } else {
            str = "notification";
        }
        return SQLiteQueryBuilder.buildQueryString(false, str, null, sb2, z ? Attributes.CATEGORY : "IFNULL(collapse_key,engagement_id)", null, "created_at DESC", String.valueOf(i));
    }

    private String buildGroupByCollapseKeyQueryString(Collection<String> collection) {
        StringBuilder sb = new StringBuilder();
        sb.append(buildBaseWhereClause(collection));
        sb.append(" AND ");
        sb.append(Attributes.CATEGORY);
        sb.append(" IS NOT NULL AND ");
        sb.append(Attributes.CATEGORY);
        sb.append(" !=\"\"");
        return SQLiteQueryBuilder.buildQueryString(false, "notification", null, sb.toString(), "IFNULL(collapse_key,engagement_id)", null, "created_at ASC", null);
    }

    private Cursor getRestCursor(Collection<String> collection, Cursor cursor) {
        List list;
        List list2 = null;
        if (cursor == null || cursor.getCount() <= 0) {
            list = null;
        } else {
            list2 = getListOfEngagementIdsFromCursor(cursor);
            list = getListOfCollapseKeysFromCursor(cursor);
        }
        List buildRestArgs = buildRestArgs(collection, list2, list);
        return this.notificationTable.getDatabase().rawQuery(buildRestQueryString(collection, list2, list), (String[]) buildRestArgs.toArray(new String[buildRestArgs.size()]));
    }

    private List<String> buildRestArgs(Collection<String> collection, List<String> list, List<String> list2) {
        List<String> buildBaseArgs = buildBaseArgs(collection);
        if (list != null && !list.isEmpty()) {
            buildBaseArgs.addAll(list);
        }
        if (list2 != null && !list2.isEmpty()) {
            buildBaseArgs.addAll(list2);
        }
        return buildBaseArgs;
    }

    private String buildRestQueryString(Collection<String> collection, List<String> list, List<String> list2) {
        String str;
        String buildBaseWhereClause = buildBaseWhereClause(collection);
        if (list != null && !list.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append(buildBaseWhereClause);
            sb.append(" AND engagement_id NOT IN (");
            sb.append(DatabaseUtil.getQuestionMarkString(list.size()));
            sb.append(")");
            buildBaseWhereClause = sb.toString();
        }
        if (list2 == null || list2.isEmpty()) {
            str = buildBaseWhereClause;
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(buildBaseWhereClause);
            sb2.append(" AND collapse_key NOT IN (");
            sb2.append(DatabaseUtil.getQuestionMarkString(list2.size()));
            sb2.append(")");
            str = sb2.toString();
        }
        return SQLiteQueryBuilder.buildQueryString(false, "notification", null, str, "IFNULL(collapse_key,engagement_id)", null, "created_at DESC", null);
    }

    private boolean internalLocalUpdateNotificationsState(List<String> list, String str, int i) {
        String uacfUserId = this.authProvider.getUacfUserId();
        boolean z = false;
        if (Strings.isEmpty(uacfUserId) || CollectionUtils.isEmpty((Collection<?>) list)) {
            return false;
        }
        ContentValuesMapper put = new ContentValuesMapper().put(Columns.SYNC_FLAGS, Integer.valueOf(i));
        if (Strings.notEmpty(str)) {
            put.put("state", str);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("user_id = ?  AND engagement_id IN (");
        sb.append(DatabaseUtil.getQuestionMarkString(CollectionUtils.size((Collection<?>) list)));
        sb.append(")");
        sb.append(" AND ");
        sb.append("deleted");
        sb.append(" = 0");
        sb.append(" AND ");
        sb.append("marked_as_expired");
        sb.append(" = 0");
        String sb2 = sb.toString();
        ArrayList arrayList = new ArrayList();
        arrayList.add(uacfUserId);
        arrayList.addAll(list);
        if (this.notificationTable.updateData(put.get(), sb2, arrayList.toArray(new Object[arrayList.size()])) > 0) {
            z = true;
        }
        return z;
    }

    private ContentValues getContentValuesFor(Notification notification) {
        return new ContentValuesMapper().exclude("_id").put("user_id", this.authProvider.getUacfUserId()).put(notification).get();
    }
}
