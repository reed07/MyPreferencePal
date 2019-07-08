package com.myfitnesspal.feature.progress.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.ui.activity.MfpActivity;

public class ImagePreviewActivity extends MfpActivity {
    @BindView(2131362788)
    ImageView imagePreview;

    public static Intent newStartIntent(Context context, Uri uri) {
        return new Intent(context, ImagePreviewActivity.class).putExtra(Extras.CONTENT_URI, uri);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_image_preview);
        Uri uri = (Uri) getIntent().getExtras().getParcelable(Extras.CONTENT_URI);
        if (uri != null) {
            this.imagePreview.setImageURI(uri);
        }
    }
}
