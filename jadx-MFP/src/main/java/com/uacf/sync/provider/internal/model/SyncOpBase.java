package com.uacf.sync.provider.internal.model;

import android.content.SharedPreferences.Editor;
import com.google.common.net.HttpHeaders;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.uacf.core.mapping.UacfGsonFactory;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Function2;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.core.util.Tuple3;
import com.uacf.core.util.UriUtils;
import com.uacf.sync.engine.UacfScheduleContext;
import com.uacf.sync.engine.UacfScheduleException;
import com.uacf.sync.engine.UacfScheduleOp.Progress;
import com.uacf.sync.engine.UacfScheduleOp.Result;
import com.uacf.sync.engine.UacfScheduleOp.Status;
import com.uacf.sync.engine.UacfScheduleOpBase;
import com.uacf.sync.engine.UacfScheduleProgressInfo;
import com.uacf.sync.provider.internal.service.InternalSyncService;
import com.uacf.sync.provider.sdk.UacfSyncFactory;
import com.uacf.sync.provider.sdk.model.SyncItem;
import com.uacf.sync.provider.sdk.model.SyncItem.Action;
import com.uacf.sync.provider.sdk.model.SyncItemHandler;
import com.uacf.sync.provider.sdk.model.SyncMode;
import io.uacf.core.api.UacfApiException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SyncOpBase extends UacfScheduleOpBase {
    private SyncMode currentMode;
    private final SyncOpDelegate delegate;
    private int numSubsequentRequestsWithSameSyncToken = 0;

    protected static class SyncTokenExpiredException extends Exception {
        private final int statusCode;

        public SyncTokenExpiredException(int i) {
            this.statusCode = i;
        }
    }

    public SyncOpBase(SyncOpDelegate syncOpDelegate) {
        this.delegate = syncOpDelegate;
    }

    public Result sync(UacfScheduleContext uacfScheduleContext, Progress progress) throws UacfScheduleException {
        this.delegate.onPrepareToSync();
        setCurrentMode(getNextMode());
        Result completed = Result.completed();
        if (!getCurrentMode().isImport()) {
            this.delegate.onBeforePublish();
            completed = performUploadPortion(progress);
        }
        if (completed.getStatus() != Status.Completed) {
            return completed;
        }
        this.delegate.onBeforeFetch();
        return performFetchPortion(progress);
    }

    /* access modifiers changed from: protected */
    public SyncMode getCurrentMode() {
        return this.currentMode;
    }

    private void setCurrentMode(SyncMode syncMode) {
        this.currentMode = syncMode;
    }

    private Result performUploadPortion(final Progress<UacfScheduleProgressInfo> progress) throws UacfScheduleException {
        for (String handlerForResource : this.delegate.getSupportedResourceNames()) {
            SyncItemHandler handlerForResource2 = this.delegate.getHandlerForResource(handlerForResource);
            if (handlerForResource2 != null) {
                try {
                    handlerForResource2.publishUnsyncedItems(new Function2<Integer, Integer>() {
                        public void execute(Integer num, Integer num2) {
                            progress.publish(new UacfScheduleProgressInfo(num.intValue(), num2.intValue()));
                        }
                    });
                } catch (UacfScheduleException e) {
                    Ln.e(e);
                    return Result.retry(e);
                }
            }
        }
        return Result.completed();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x006b, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x006c, code lost:
        r7 = r2;
        r2 = r9;
        r9 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00b4, code lost:
        com.uacf.core.util.Ln.e("sync token expired! switching back to import", new java.lang.Object[0]);
        resetModeState();
        setCurrentMode(getNextMode());
        r8.delegate.onSyncTokenExpired();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00ce, code lost:
        return com.uacf.sync.engine.UacfScheduleOp.Result.pending();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:42:? A[ExcHandler: SyncTokenExpiredException (unused com.uacf.sync.provider.internal.model.SyncOpBase$SyncTokenExpiredException), SYNTHETIC, Splitter:B:1:0x0006] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.uacf.sync.engine.UacfScheduleOp.Result performFetchPortion(com.uacf.sync.engine.UacfScheduleOp.Progress r9) throws com.uacf.sync.engine.UacfScheduleException {
        /*
            r8 = this;
            com.uacf.sync.provider.sdk.model.SyncMode r9 = r8.getCurrentMode()
            r0 = 1
            r1 = 0
            com.uacf.core.util.Tuple3 r2 = r8.fetchNextPage(r9)     // Catch:{ SyncTokenExpiredException -> 0x00b4, UacfScheduleException -> 0x00a1 }
            java.lang.Object r3 = r2.getItem2()     // Catch:{ SyncTokenExpiredException -> 0x00b4, UacfScheduleException -> 0x00a1 }
            java.util.List r3 = (java.util.List) r3     // Catch:{ SyncTokenExpiredException -> 0x00b4, UacfScheduleException -> 0x00a1 }
            java.lang.Object r2 = r2.getItem3()     // Catch:{ SyncTokenExpiredException -> 0x00b4, UacfScheduleException -> 0x00a1 }
            java.util.Map r2 = (java.util.Map) r2     // Catch:{ SyncTokenExpiredException -> 0x00b4, UacfScheduleException -> 0x00a1 }
            java.lang.String r4 = "SYNCV2: performFetchPortion fetch complete, got %s items"
            java.lang.Object[] r5 = new java.lang.Object[r0]     // Catch:{ SyncTokenExpiredException -> 0x00b4, UacfScheduleException -> 0x00a1 }
            int r6 = com.uacf.core.util.CollectionUtils.size(r3)     // Catch:{ SyncTokenExpiredException -> 0x00b4, UacfScheduleException -> 0x00a1 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ SyncTokenExpiredException -> 0x00b4, UacfScheduleException -> 0x00a1 }
            r5[r1] = r6     // Catch:{ SyncTokenExpiredException -> 0x00b4, UacfScheduleException -> 0x00a1 }
            com.uacf.core.util.Ln.d(r4, r5)     // Catch:{ SyncTokenExpiredException -> 0x00b4, UacfScheduleException -> 0x00a1 }
            if (r3 == 0) goto L_0x009b
            if (r2 != 0) goto L_0x002d
            goto L_0x009b
        L_0x002d:
            r8.dispatchToHandlers(r3)     // Catch:{ SyncTokenExpiredException -> 0x00b4, UacfScheduleException -> 0x00a1 }
            java.lang.String r3 = r8.getTokenForMode(r9)     // Catch:{ SyncTokenExpiredException -> 0x00b4, UacfScheduleException -> 0x00a1 }
            r8.updateModeState(r9, r2)     // Catch:{ SyncTokenExpiredException -> 0x00b4, UacfScheduleException -> 0x00a1 }
            java.lang.String r4 = "Link"
            java.lang.String r2 = com.uacf.core.util.UriUtils.getHeaderValue(r2, r4)     // Catch:{ SyncTokenExpiredException -> 0x00b4, UacfScheduleException -> 0x00a1 }
            boolean r2 = com.uacf.core.util.Strings.isEmpty(r2)     // Catch:{ SyncTokenExpiredException -> 0x00b4, UacfScheduleException -> 0x00a1 }
            if (r2 == 0) goto L_0x0070
            com.uacf.sync.provider.sdk.model.SyncMode r2 = r8.getNextMode()     // Catch:{ SyncTokenExpiredException -> 0x00b4, UacfScheduleException -> 0x00a1 }
            boolean r3 = r9.isImport()     // Catch:{ SyncTokenExpiredException -> 0x00b4, UacfScheduleException -> 0x00a1 }
            boolean r9 = r2.isImport()     // Catch:{ SyncTokenExpiredException -> 0x00b4, UacfScheduleException -> 0x00a1 }
            if (r3 == 0) goto L_0x005d
            if (r9 != 0) goto L_0x0058
            com.uacf.sync.provider.internal.model.SyncOpDelegate r9 = r8.delegate     // Catch:{ SyncTokenExpiredException -> 0x00b4, UacfScheduleException -> 0x006b }
            r9.onImportComplete()     // Catch:{ SyncTokenExpiredException -> 0x00b4, UacfScheduleException -> 0x006b }
        L_0x0058:
            com.uacf.sync.engine.UacfScheduleOp$Result r9 = com.uacf.sync.engine.UacfScheduleOp.Result.pending()     // Catch:{ SyncTokenExpiredException -> 0x00b4, UacfScheduleException -> 0x006b }
            return r9
        L_0x005d:
            if (r9 == 0) goto L_0x0064
            com.uacf.sync.engine.UacfScheduleOp$Result r9 = com.uacf.sync.engine.UacfScheduleOp.Result.pending()     // Catch:{ SyncTokenExpiredException -> 0x00b4, UacfScheduleException -> 0x006b }
            return r9
        L_0x0064:
            r8.numSubsequentRequestsWithSameSyncToken = r1     // Catch:{ SyncTokenExpiredException -> 0x00b4, UacfScheduleException -> 0x006b }
            com.uacf.sync.engine.UacfScheduleOp$Result r9 = com.uacf.sync.engine.UacfScheduleOp.Result.completed()     // Catch:{ SyncTokenExpiredException -> 0x00b4, UacfScheduleException -> 0x006b }
            return r9
        L_0x006b:
            r9 = move-exception
            r7 = r2
            r2 = r9
            r9 = r7
            goto L_0x00a2
        L_0x0070:
            boolean r2 = r9.isImport()     // Catch:{ SyncTokenExpiredException -> 0x00b4, UacfScheduleException -> 0x00a1 }
            if (r2 == 0) goto L_0x007b
            com.uacf.sync.engine.UacfScheduleOp$Result r9 = com.uacf.sync.engine.UacfScheduleOp.Result.pending()     // Catch:{ SyncTokenExpiredException -> 0x00b4, UacfScheduleException -> 0x00a1 }
            return r9
        L_0x007b:
            java.lang.String r2 = r8.getTokenForMode(r9)     // Catch:{ SyncTokenExpiredException -> 0x00b4, UacfScheduleException -> 0x00a1 }
            boolean r2 = com.uacf.core.util.Strings.equals(r3, r2)     // Catch:{ SyncTokenExpiredException -> 0x00b4, UacfScheduleException -> 0x00a1 }
            if (r2 == 0) goto L_0x0094
            int r2 = r8.numSubsequentRequestsWithSameSyncToken     // Catch:{ SyncTokenExpiredException -> 0x00b4, UacfScheduleException -> 0x00a1 }
            int r2 = r2 + r0
            r8.numSubsequentRequestsWithSameSyncToken = r2     // Catch:{ SyncTokenExpiredException -> 0x00b4, UacfScheduleException -> 0x00a1 }
            r3 = 4
            if (r2 < r3) goto L_0x0096
            r8.numSubsequentRequestsWithSameSyncToken = r1     // Catch:{ SyncTokenExpiredException -> 0x00b4, UacfScheduleException -> 0x00a1 }
            com.uacf.sync.engine.UacfScheduleOp$Result r9 = com.uacf.sync.engine.UacfScheduleOp.Result.completed()     // Catch:{ SyncTokenExpiredException -> 0x00b4, UacfScheduleException -> 0x00a1 }
            return r9
        L_0x0094:
            r8.numSubsequentRequestsWithSameSyncToken = r1     // Catch:{ SyncTokenExpiredException -> 0x00b4, UacfScheduleException -> 0x00a1 }
        L_0x0096:
            com.uacf.sync.engine.UacfScheduleOp$Result r9 = com.uacf.sync.engine.UacfScheduleOp.Result.pending()     // Catch:{ SyncTokenExpiredException -> 0x00b4, UacfScheduleException -> 0x00a1 }
            return r9
        L_0x009b:
            r2 = 0
            com.uacf.sync.engine.UacfScheduleOp$Result r9 = com.uacf.sync.engine.UacfScheduleOp.Result.retry(r2)     // Catch:{ SyncTokenExpiredException -> 0x00b4, UacfScheduleException -> 0x00a1 }
            return r9
        L_0x00a1:
            r2 = move-exception
        L_0x00a2:
            java.lang.String r3 = "exception while making '%s' call"
            java.lang.Object[] r0 = new java.lang.Object[r0]
            java.lang.String r9 = r9.getTokenPrefsKey()
            r0[r1] = r9
            com.uacf.core.util.Ln.e(r2, r3, r0)
            com.uacf.sync.engine.UacfScheduleOp$Result r9 = com.uacf.sync.engine.UacfScheduleOp.Result.retry(r2)
            return r9
        L_0x00b4:
            java.lang.String r9 = "sync token expired! switching back to import"
            java.lang.Object[] r0 = new java.lang.Object[r1]
            com.uacf.core.util.Ln.e(r9, r0)
            r8.resetModeState()
            com.uacf.sync.provider.sdk.model.SyncMode r9 = r8.getNextMode()
            r8.setCurrentMode(r9)
            com.uacf.sync.provider.internal.model.SyncOpDelegate r9 = r8.delegate
            r9.onSyncTokenExpired()
            com.uacf.sync.engine.UacfScheduleOp$Result r9 = com.uacf.sync.engine.UacfScheduleOp.Result.pending()
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.sync.provider.internal.model.SyncOpBase.performFetchPortion(com.uacf.sync.engine.UacfScheduleOp$Progress):com.uacf.sync.engine.UacfScheduleOp$Result");
    }

    private void dispatchToHandlers(List<SyncRawResponseItem> list) {
        if (CollectionUtils.isEmpty((Collection<?>) list)) {
            Ln.d("SYNCV2: dispatchToHandlers, empty list", new Object[0]);
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (SyncRawResponseItem syncRawResponseItem : list) {
            String type = syncRawResponseItem.getType();
            if (!Strings.isEmpty(type) && this.delegate.getSupportedResourceNames().contains(type)) {
                SyncItemHandler handlerForResource = this.delegate.getHandlerForResource(type);
                if (handlerForResource != null) {
                    SyncItem createSyncV2ItemFromRawItem = createSyncV2ItemFromRawItem(syncRawResponseItem, handlerForResource.getSyncItemClass());
                    Ln.d("SYNCV2: dispatchToHandlers, created item %s", createSyncV2ItemFromRawItem);
                    if (createSyncV2ItemFromRawItem != null) {
                        List list2 = (List) linkedHashMap.get(type);
                        if (list2 == null) {
                            list2 = new ArrayList();
                            linkedHashMap.put(type, list2);
                        }
                        list2.add(createSyncV2ItemFromRawItem);
                    }
                }
            }
        }
        for (Entry entry : linkedHashMap.entrySet()) {
            SyncItemHandler handlerForResource2 = this.delegate.getHandlerForResource((String) entry.getKey());
            if (handlerForResource2 != null) {
                Ln.d("SYNCV2: dispatchToHandlers dispatching %s items to handler %s", Integer.valueOf(CollectionUtils.size((Collection) entry.getValue())), entry.getKey());
                handlerForResource2.consumeSyncItems((List) entry.getValue());
            }
        }
    }

    private void resetModeState() {
        Editor edit = this.delegate.getPrefs().edit();
        ArrayList<SyncMode> arrayList = new ArrayList<>(this.delegate.getOrderedListOfImportModes());
        arrayList.add(new SyncModeSync());
        for (SyncMode syncMode : arrayList) {
            if (Strings.notEmpty(syncMode.getTokenPrefsKey())) {
                edit.remove(syncMode.getTokenPrefsKey());
            }
            if (Strings.notEmpty(syncMode.getFinishedPrefsKey())) {
                edit.remove(syncMode.getFinishedPrefsKey());
            }
        }
        edit.apply();
    }

    private void updateModeState(SyncMode syncMode, Map<String, List<String>> map) {
        Editor edit = this.delegate.getPrefs().edit();
        String headerValue = UriUtils.getHeaderValue(map, "next-sync-token");
        edit.putString("sync-token", headerValue);
        Ln.d("SYNCV2: updateModeState, mode = %s, next sync token = %s", syncMode, headerValue);
        if (syncMode.isImport()) {
            String headerValue2 = UriUtils.getHeaderValue(map, HttpHeaders.LINK);
            String headerValue3 = UriUtils.getHeaderValue(map, "next-import-token");
            boolean isEmpty = Strings.isEmpty(headerValue2);
            Ln.d("SYNCV2: updateModeState mode is import: finished = %s, link = %s, token = %s", Boolean.valueOf(isEmpty), headerValue2, headerValue3);
            edit.putBoolean(syncMode.getFinishedPrefsKey(), isEmpty);
            edit.putString(syncMode.getTokenPrefsKey(), headerValue3);
        } else {
            for (SyncMode syncMode2 : this.delegate.getOrderedListOfImportModes()) {
                edit.putBoolean(syncMode2.getFinishedPrefsKey(), true);
                edit.putString(syncMode2.getTokenPrefsKey(), null);
            }
        }
        edit.apply();
    }

    /* access modifiers changed from: protected */
    public SyncMode getNextMode() {
        Ln.d("SYNCV2: getNextMode", new Object[0]);
        for (SyncMode syncMode : this.delegate.getOrderedListOfImportModes()) {
            boolean z = this.delegate.getPrefs().getBoolean(syncMode.getFinishedPrefsKey(), false);
            Ln.d("SYNCV2: getNextMode check %s --> finished = %s", syncMode, Boolean.valueOf(z));
            if (!z) {
                Ln.d("SYNCV2: getNextMode not finished, return %s", syncMode);
                return syncMode;
            }
        }
        Ln.d("SYNCV2: getNextMode all imports finished, return SYNC", new Object[0]);
        return new SyncModeSync();
    }

    /* access modifiers changed from: protected */
    public Tuple3<Integer, List<SyncRawResponseItem>, Map<String, List<String>>> fetchNextPage(SyncMode syncMode) throws UacfScheduleException, SyncTokenExpiredException {
        boolean isImport = syncMode.isImport();
        try {
            String tokenForMode = getTokenForMode(syncMode);
            InternalSyncService newSyncServiceInstance = new UacfSyncFactory().newSyncServiceInstance();
            if (isImport) {
                return newSyncServiceInstance.doImport(tokenForMode, syncMode);
            }
            Tuple3<Integer, List<SyncRawResponseItem>, Map<String, List<String>>> doSync = newSyncServiceInstance.doSync(tokenForMode);
            handleSyncTokenExpiredOrMissing(((Integer) doSync.getItem1()).intValue());
            return doSync;
        } catch (UacfApiException e) {
            Ln.e(e, "SYNCV2", new Object[0]);
            handleSyncTokenExpiredOrMissing(e.getStatusCode());
            throw new UacfScheduleException(e);
        }
    }

    private void handleSyncTokenExpiredOrMissing(int i) throws SyncTokenExpiredException {
        if (i == 302 || i == 422) {
            throw new SyncTokenExpiredException(i);
        }
    }

    private String getTokenForMode(SyncMode syncMode) {
        return this.delegate.getPrefs().getString(syncMode.getTokenPrefsKey(), null);
    }

    private <TResourceType> SyncItem createSyncV2ItemFromRawItem(SyncRawResponseItem syncRawResponseItem, Class<? extends TResourceType> cls) {
        Action action = syncRawResponseItem.getAction();
        Map data = syncRawResponseItem.getData();
        if (action == null) {
            action = CollectionUtils.isEmpty(data) ? Action.Delete : Action.Update;
        }
        Gson newInstance = UacfGsonFactory.newInstance(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return new SyncItem(newInstance.fromJson(newInstance.toJson((Object) data), cls), syncRawResponseItem.getType(), syncRawResponseItem.getId(), action);
    }
}
