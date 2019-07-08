package com.uacf.sync.provider.internal.service;

import com.uacf.core.util.Tuple3;
import com.uacf.sync.provider.internal.model.SyncRawResponseItem;
import com.uacf.sync.provider.sdk.model.SyncMode;
import io.uacf.core.api.UacfApiException;
import java.util.List;
import java.util.Map;

public interface InternalSyncService {
    Tuple3<Integer, List<SyncRawResponseItem>, Map<String, List<String>>> doImport(String str, SyncMode syncMode) throws UacfApiException;

    Tuple3<Integer, List<SyncRawResponseItem>, Map<String, List<String>>> doSync(String str) throws UacfApiException;
}
