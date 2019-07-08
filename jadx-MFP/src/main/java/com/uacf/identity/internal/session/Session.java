package com.uacf.identity.internal.session;

import com.uacf.identity.internal.model.AppSessionInfo;
import io.uacf.core.app.UacfAppId;

public interface Session {
    AppSessionInfo getSessionInformationFor(UacfAppId uacfAppId);

    void removeSessionInformationFor(UacfAppId uacfAppId);

    void saveAndNotify();

    void setSessionInformationFor(UacfAppId uacfAppId, AppSessionInfo appSessionInfo);
}
