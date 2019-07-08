package com.google.android.gms.tagmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public class PreviewActivity extends Activity {
    public void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            zzdi.zzdm("Preview activity");
            Uri data = getIntent().getData();
            if (!TagManager.getInstance(this).zzb(data)) {
                String valueOf = String.valueOf(data);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 73);
                sb.append("Cannot preview the app with the uri: ");
                sb.append(valueOf);
                sb.append(". Launching current version instead.");
                String sb2 = sb.toString();
                zzdi.zzab(sb2);
                AlertDialog create = new Builder(this).create();
                create.setTitle("Preview failure");
                create.setMessage(sb2);
                create.setButton(-1, "Continue", new zzeg(this));
                create.show();
            }
            Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
            if (launchIntentForPackage != null) {
                String str = "Invoke the launch activity for package name: ";
                String valueOf2 = String.valueOf(getPackageName());
                zzdi.zzdm(valueOf2.length() != 0 ? str.concat(valueOf2) : new String(str));
                startActivity(launchIntentForPackage);
                return;
            }
            String str2 = "No launch activity found for package name: ";
            String valueOf3 = String.valueOf(getPackageName());
            zzdi.zzdm(valueOf3.length() != 0 ? str2.concat(valueOf3) : new String(str2));
        } catch (Exception e) {
            String str3 = "Calling preview threw an exception: ";
            String valueOf4 = String.valueOf(e.getMessage());
            zzdi.e(valueOf4.length() != 0 ? str3.concat(valueOf4) : new String(str3));
        }
    }
}
