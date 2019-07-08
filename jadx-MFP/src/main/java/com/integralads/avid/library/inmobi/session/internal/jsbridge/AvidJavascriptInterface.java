package com.integralads.avid.library.inmobi.session.internal.jsbridge;

import android.os.Handler;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSessionContext;

public class AvidJavascriptInterface {
    private final InternalAvidAdSessionContext avidAdSessionContext;
    private AvidJavascriptInterfaceCallback callback;
    private final Handler handler = new Handler();

    public interface AvidJavascriptInterfaceCallback {
    }

    public AvidJavascriptInterface(InternalAvidAdSessionContext internalAvidAdSessionContext) {
        this.avidAdSessionContext = internalAvidAdSessionContext;
    }

    public void setCallback(AvidJavascriptInterfaceCallback avidJavascriptInterfaceCallback) {
        this.callback = avidJavascriptInterfaceCallback;
    }
}
