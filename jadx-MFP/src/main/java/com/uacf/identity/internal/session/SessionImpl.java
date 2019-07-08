package com.uacf.identity.internal.session;

import android.content.Context;
import com.uacf.core.caching.Cache;
import com.uacf.core.util.Strings;
import com.uacf.core.util.Tuple2;
import com.uacf.identity.internal.model.AppSessionInfo;
import com.uacf.identity.internal.model.AppUserInfo;
import com.uacf.identity.internal.util.AppIdMigrationUtil;
import com.uacf.identity.sdk.content.UacfIdentityContentProvider;
import io.uacf.core.app.UacfAppId;
import java.util.Map.Entry;

public class SessionImpl implements Session {
    private final Context context;
    private final UacfAppId currentAppId;
    final Cache<AppSessionInfo> sessionInfoCache;

    public SessionImpl(Context context2, UacfAppId uacfAppId, Cache<AppSessionInfo> cache) {
        this.context = context2;
        this.currentAppId = uacfAppId;
        this.sessionInfoCache = cache;
    }

    public synchronized AppSessionInfo getSessionInformationFor(UacfAppId uacfAppId) {
        return migrateSessionIfNecessary(AppIdMigrationUtil.getAppIdMigrationTuple(uacfAppId));
    }

    public synchronized void setSessionInformationFor(UacfAppId uacfAppId, AppSessionInfo appSessionInfo) {
        validateAppId(uacfAppId);
        if (appSessionInfo != null) {
            Tuple2 appIdMigrationTuple = AppIdMigrationUtil.getAppIdMigrationTuple(uacfAppId);
            AppSessionInfo migrateSession = migrateSession(uacfAppId, appSessionInfo, (UacfAppId) appIdMigrationTuple.getItem2());
            removeId((UacfAppId) appIdMigrationTuple.getItem1());
            this.sessionInfoCache.put(((UacfAppId) appIdMigrationTuple.getItem2()).name(), migrateSession);
        } else {
            throw new IllegalArgumentException("appSessionInfo may not be null");
        }
    }

    public synchronized void removeSessionInformationFor(UacfAppId uacfAppId) {
        Tuple2 appIdMigrationTuple = AppIdMigrationUtil.getAppIdMigrationTuple(uacfAppId);
        removeId((UacfAppId) appIdMigrationTuple.getItem1());
        removeId((UacfAppId) appIdMigrationTuple.getItem2());
    }

    public void saveWithoutNotifying() {
        this.sessionInfoCache.flush();
    }

    public void saveAndNotify() {
        saveWithoutNotifying();
        UacfIdentityContentProvider.notifyChanges(this.context, this.currentAppId, UacfIdentityContentProvider.USERS);
    }

    private synchronized void removeId(UacfAppId uacfAppId) {
        String name = uacfAppId.name();
        if (this.sessionInfoCache.contains(name)) {
            this.sessionInfoCache.remove(name);
        }
    }

    private void validateAppId(UacfAppId uacfAppId) {
        if (uacfAppId == null) {
            throw new IllegalArgumentException("appId may not be null");
        } else if (Strings.isEmpty(uacfAppId.name())) {
            throw new IllegalArgumentException("appId.name may not be null or empty");
        }
    }

    private synchronized AppSessionInfo migrateSessionIfNecessary(Tuple2<UacfAppId, UacfAppId> tuple2) {
        AppSessionInfo appSessionInfo;
        appSessionInfo = null;
        if (tuple2 != null) {
            if (tuple2.getItem1() != null) {
                appSessionInfo = (AppSessionInfo) this.sessionInfoCache.get(((UacfAppId) tuple2.getItem1()).name());
                if (tuple2.getItem2() != null) {
                    appSessionInfo = appSessionInfo != null ? migrateSession((UacfAppId) tuple2.getItem1(), appSessionInfo, (UacfAppId) tuple2.getItem2()) : (AppSessionInfo) this.sessionInfoCache.get(((UacfAppId) tuple2.getItem2()).name());
                }
            }
        }
        return appSessionInfo;
    }

    private synchronized AppSessionInfo migrateSession(UacfAppId uacfAppId, AppSessionInfo appSessionInfo, UacfAppId uacfAppId2) {
        if (!(uacfAppId == null || uacfAppId2 == null || uacfAppId2 == uacfAppId || appSessionInfo == null)) {
            AppSessionInfo clientTokenInfo = new AppSessionInfo(uacfAppId2).setCurrentUserId(appSessionInfo.getCurrentUserId()).setServerKeyInfo(appSessionInfo.getServerKeyInfo()).setClientKeyInfo(appSessionInfo.getClientKeyInfo()).setClientTokenInfo(appSessionInfo.getClientTokenInfo());
            if (appSessionInfo.getUserInfoMap() != null) {
                for (Entry entry : appSessionInfo.getUserInfoMap().entrySet()) {
                    clientTokenInfo.setUserInfo((String) entry.getKey(), (AppUserInfo) entry.getValue());
                }
            }
            removeId(uacfAppId);
            this.sessionInfoCache.put(uacfAppId2.name(), clientTokenInfo);
            this.sessionInfoCache.flush();
            appSessionInfo = clientTokenInfo;
        }
        return appSessionInfo;
    }
}
