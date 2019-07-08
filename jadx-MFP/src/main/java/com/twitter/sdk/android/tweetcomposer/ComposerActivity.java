package com.twitter.sdk.android.tweetcomposer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterSession;

public class ComposerActivity extends Activity {
    private ComposerController composerController;

    public static class Builder {
    }

    interface Finisher {
        void finish();
    }

    class FinisherImpl implements Finisher {
        FinisherImpl() {
        }

        public void finish() {
            ComposerActivity.this.finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        TwitterSession twitterSession = new TwitterSession((TwitterAuthToken) intent.getParcelableExtra("EXTRA_USER_TOKEN"), -1, "");
        Uri uri = (Uri) intent.getParcelableExtra("EXTRA_IMAGE_URI");
        String stringExtra = intent.getStringExtra("EXTRA_TEXT");
        String stringExtra2 = intent.getStringExtra("EXTRA_HASHTAGS");
        setTheme(intent.getIntExtra("EXTRA_THEME", R.style.ComposerLight));
        setContentView(R.layout.tw__activity_composer);
        ComposerController composerController2 = new ComposerController((ComposerView) findViewById(R.id.tw__composer_view), twitterSession, uri, stringExtra, stringExtra2, new FinisherImpl());
        this.composerController = composerController2;
    }

    public void onBackPressed() {
        super.onBackPressed();
        this.composerController.onClose();
    }
}
