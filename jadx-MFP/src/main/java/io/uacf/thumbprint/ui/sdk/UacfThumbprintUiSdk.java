package io.uacf.thumbprint.ui.sdk;

import android.content.Context;
import io.uacf.thumbprint.ui.internal.email.UacfEmailVerificationActivity.Actions;
import io.uacf.thumbprint.ui.internal.email.UacfEmailVerificationActivity.ExportType;

public interface UacfThumbprintUiSdk {
    void showEmailVerificationOnAppLaunch(Context context, ClientEmailVerificationStatus clientEmailVerificationStatus, Actions actions);

    void showEmailVerificationOnFileExport(Context context, ExportType exportType, ClientEmailVerificationStatus clientEmailVerificationStatus, Actions actions);
}
