package com.brightcove.player.captioning;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;
import com.brightcove.player.R;

public class BrightcoveCaptionPropertiesActivity extends Activity {
    @TargetApi(11)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.caption_prefs_activity);
    }
}
