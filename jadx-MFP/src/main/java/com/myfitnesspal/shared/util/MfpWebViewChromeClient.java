package com.myfitnesspal.shared.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.FileChooserParams;
import android.webkit.WebView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;

public class MfpWebViewChromeClient extends WebChromeClient {
    private static final String CONTENT_SCHEME = "content";
    private Activity activity;
    private MfpFragment fragmentTarget;
    private ValueCallback<Uri> legacyUploadFileSelectedCallback;
    private ValueCallback<Uri[]> uploadFileSelectedCallback;

    public MfpWebViewChromeClient(Activity activity2) {
        this.activity = activity2;
    }

    public void setFragmentTarget(MfpFragment mfpFragment) {
        this.fragmentTarget = mfpFragment;
    }

    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, FileChooserParams fileChooserParams) {
        this.uploadFileSelectedCallback = valueCallback;
        showFileChooser(null);
        return true;
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback) {
        openFileChooser(valueCallback, null);
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback, String str) {
        this.legacyUploadFileSelectedCallback = valueCallback;
        showFileChooser(str);
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback, String str, String str2) {
        openFileChooser(valueCallback, str);
    }

    private void showFileChooser(String str) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        if (Strings.isEmpty(str)) {
            str = "*/*";
        }
        intent.setType(str);
        startActivityForResult(Intent.createChooser(intent, this.activity.getString(R.string.upload_file_title)), RequestCodes.FILE_CHOOSER);
    }

    private void startActivityForResult(Intent intent, int i) {
        MfpFragment mfpFragment = this.fragmentTarget;
        if (mfpFragment != null) {
            mfpFragment.startActivityForResult(intent, i);
        } else {
            this.activity.startActivityForResult(intent, i);
        }
    }

    public boolean handleOnActivityResult(int i, int i2, Intent intent) {
        if (i != 145) {
            return false;
        }
        if (this.legacyUploadFileSelectedCallback != null) {
            handleLegacyFileChooserResult(intent, i2);
        } else if (this.uploadFileSelectedCallback != null) {
            handleFileChooserResult(intent);
        }
        this.legacyUploadFileSelectedCallback = null;
        this.uploadFileSelectedCallback = null;
        return true;
    }

    private void handleLegacyFileChooserResult(Intent intent, int i) {
        Object obj = null;
        if (intent != null && i == -1) {
            try {
                obj = resolveContentUri(intent.getData());
            } catch (Exception unused) {
                Ln.e("file chooser selection failed!", new Object[0]);
            }
        }
        this.legacyUploadFileSelectedCallback.onReceiveValue(obj);
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x001b A[Catch:{ Exception -> 0x0022 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handleFileChooserResult(android.content.Intent r4) {
        /*
            r3 = this;
            r0 = 0
            r1 = 0
            if (r4 == 0) goto L_0x0018
            java.lang.String r2 = r4.getDataString()     // Catch:{ Exception -> 0x0022 }
            if (r2 != 0) goto L_0x000b
            goto L_0x0018
        L_0x000b:
            java.lang.String r4 = r4.getDataString()     // Catch:{ Exception -> 0x0022 }
            android.net.Uri r4 = android.net.Uri.parse(r4)     // Catch:{ Exception -> 0x0022 }
            android.net.Uri r4 = r3.resolveContentUri(r4)     // Catch:{ Exception -> 0x0022 }
            goto L_0x0019
        L_0x0018:
            r4 = r1
        L_0x0019:
            if (r4 == 0) goto L_0x0029
            r2 = 1
            android.net.Uri[] r2 = new android.net.Uri[r2]     // Catch:{ Exception -> 0x0022 }
            r2[r0] = r4     // Catch:{ Exception -> 0x0022 }
            r1 = r2
            goto L_0x0029
        L_0x0022:
            java.lang.String r4 = "file chooser selection failed!"
            java.lang.Object[] r0 = new java.lang.Object[r0]
            com.uacf.core.util.Ln.e(r4, r0)
        L_0x0029:
            android.webkit.ValueCallback<android.net.Uri[]> r4 = r3.uploadFileSelectedCallback
            r4.onReceiveValue(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.util.MfpWebViewChromeClient.handleFileChooserResult(android.content.Intent):void");
    }

    private Uri resolveContentUri(Uri uri) {
        return (uri == null || !"content".equals(uri.getScheme())) ? uri : ImageContentUriUtils.getImageFilename(this.activity, uri);
    }
}
