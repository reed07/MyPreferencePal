package com.myfitnesspal.shared.service.install;

import android.content.Context;
import android.content.Intent;
import com.google.android.exoplayer2.C;
import com.myfitnesspal.feature.registration.ui.activity.LoginActivity;
import com.myfitnesspal.shared.constants.SharedConstants.LaunchActions;
import com.myfitnesspal.shared.constants.SharedConstants.LaunchParams;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;

public class InstallReferrerReceiver extends AbstractInstallReferrerReceiver {
    /* access modifiers changed from: protected */
    public void onReceivedReferral(Context context, String str, String str2, String str3, String str4, String str5) {
        if (Strings.equals("mfpconnect", str)) {
            Ln.d("Got MFPCONNECT", new Object[0]);
            handleMfpConnect(context, str2, str3, str4);
        }
    }

    private void handleMfpConnect(Context context, String str, String str2, String str3) {
        Ln.d("handleMfpConnect", new Object[0]);
        if (Strings.isEmpty(str)) {
            Ln.w("handleMfpConnect: clientId is empty", new Object[0]);
        } else if (Strings.isEmpty(str3)) {
            Ln.w("handleMfpConnect: redirectUri is empty", new Object[0]);
        } else {
            Ln.d("Starting activity for clientId = %s, suffix = %s, redirectURI = %s", str, str2, str3);
            Intent addFlags = new Intent(context, LoginActivity.class).putExtra(LaunchParams.USE_REDIRECT_URI_INSTEAD_OF_ACTIVITY_RESULT, true).putExtra("operation", "mfpconnect").putExtra("action", LaunchActions.AUTHORIZE).putExtra("client_id", str).putExtra(LaunchParams.SUFFIX, str2).putExtra("redirect_uri", str3).addFlags(C.ENCODING_PCM_MU_LAW);
            Ln.d("Install Receiver Connect intent action = %s", addFlags.getAction());
            context.startActivity(addFlags);
        }
    }
}
