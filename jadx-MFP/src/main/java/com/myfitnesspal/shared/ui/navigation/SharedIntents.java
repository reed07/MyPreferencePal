package com.myfitnesspal.shared.ui.navigation;

import android.content.Intent;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.provider.MediaStore.Images.Media;
import com.google.android.exoplayer2.C;
import com.myfitnesspal.shared.constants.Constants.Extras;

public final class SharedIntents {
    public static Intent newImageCaptureIntent(Uri uri) {
        return new Intent("android.media.action.IMAGE_CAPTURE").putExtra(Extras.RETURN_DATA, true).addFlags(64).putExtra("output", uri);
    }

    public static Intent newImageChooserIntent(String str, CompressFormat compressFormat) {
        return Intent.createChooser(new Intent("android.intent.action.PICK", Media.EXTERNAL_CONTENT_URI).setType("image/*"), str);
    }

    public static Intent newUriIntent(String str) {
        return newUriIntent(str, true);
    }

    public static Intent newUriIntent(String str, boolean z) {
        Intent action = new Intent().setData(Uri.parse(str)).setAction("android.intent.action.VIEW");
        if (z) {
            action.addFlags(C.ENCODING_PCM_MU_LAW);
        }
        return action;
    }

    public static Intent newLocationSettingsIntent() {
        return new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
    }
}
