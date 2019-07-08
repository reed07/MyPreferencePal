package com.integralads.avid.library.inmobi.session;

import android.view.View;
import com.integralads.avid.library.inmobi.AvidManager;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidManagedAdSession;

public abstract class AbstractAvidManagedAdSession extends AbstractAvidAdSession<View> {
    public void injectJavaScriptResource(String str) {
        InternalAvidManagedAdSession internalAvidManagedAdSession = (InternalAvidManagedAdSession) AvidManager.getInstance().findInternalAvidAdSessionById(getAvidAdSessionId());
        if (internalAvidManagedAdSession != null) {
            internalAvidManagedAdSession.getJavaScriptResourceInjector().injectJavaScriptResource(str);
        }
    }
}
