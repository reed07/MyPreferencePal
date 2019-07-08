package com.myfitnesspal.shared.service.appindexer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.myfitnesspal.shared.constants.Constants.ABTest.AppIndexingQuickSearch201508;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;

public class AppIndexerClient {
    private static final String EXTRA_DEEP_LINK = "AppIndexerClient.EXTRA_DEEP_LINK";
    private static final String EXTRA_STATE_FLAG = "AppIndexerClient.EXTRA_STATE_FLAG";
    private static final String EXTRA_TITLE = "AppIndexerClient.EXTRA_TITLE";
    private static final String EXTRA_WEB_LINK = "AppIndexerClient.EXTRA_WEB_LINK";
    private Action action;
    private Context context;
    private Uri deepLink;
    private GoogleApiClient googleClient;
    private boolean rolloutOn;
    private int state = 0;
    private String title;
    private Uri webLink;

    private interface State {
        public static final int ENDED = 2;
        public static final int NOT_STARTED = 0;
        public static final int STARTED = 1;
    }

    public AppIndexerClient(Context context2, ConfigService configService, Intent intent, Bundle bundle) {
        this.context = context2;
        this.rolloutOn = configService.isVariantEnabled(AppIndexingQuickSearch201508.NAME);
        if (intent != null ? BundleUtils.getBoolean(intent.getExtras(), Extras.STARTED_FROM_DEEP_LINK) : false) {
            this.state = 2;
            return;
        }
        this.state = BundleUtils.getInt(bundle, EXTRA_STATE_FLAG, 0);
        this.deepLink = (Uri) BundleUtils.getParcelable(bundle, EXTRA_DEEP_LINK, Uri.class.getClassLoader());
        this.webLink = (Uri) BundleUtils.getParcelable(bundle, EXTRA_WEB_LINK, Uri.class.getClassLoader());
        this.title = BundleUtils.getString(bundle, EXTRA_TITLE);
    }

    public void saveInstanceState(Bundle bundle) {
        if (bundle != null) {
            bundle.putInt(EXTRA_STATE_FLAG, this.state);
            bundle.putParcelable(EXTRA_DEEP_LINK, this.deepLink);
            bundle.putParcelable(EXTRA_WEB_LINK, this.webLink);
            bundle.putString(EXTRA_TITLE, this.title);
        }
    }

    public void start(String str, Uri uri, Uri uri2) {
        this.action = null;
        this.title = str;
        this.deepLink = uri;
        this.webLink = uri2;
        if (this.state == 0 && this.rolloutOn) {
            checkCreateGoogleClientAndAction();
            if (this.action != null) {
                AppIndex.AppIndexApi.start(this.googleClient, this.action);
                this.state = 1;
                return;
            }
            Ln.d("start(): no action available, check input parameters", new Object[0]);
        }
    }

    public void start(String str, Uri uri) {
        start(str, uri, null);
    }

    public void end() {
        int i = this.state;
        if (i == 2) {
            Ln.d("indexing already ended! ignoring end()", new Object[0]);
        } else if (i == 1) {
            if (this.rolloutOn) {
                checkCreateGoogleClientAndAction();
                if (this.action != null) {
                    AppIndex.AppIndexApi.end(this.googleClient, this.action);
                } else {
                    Ln.d("end(): no action available, check input parameters", new Object[0]);
                }
                GoogleApiClient googleApiClient = this.googleClient;
                if (googleApiClient != null) {
                    googleApiClient.disconnect();
                }
            }
            this.googleClient = null;
            this.action = null;
            this.context = null;
            this.state = 2;
        }
    }

    private void checkCreateGoogleClientAndAction() {
        Action action2;
        if (this.googleClient == null) {
            this.googleClient = new Builder(this.context).addApi(AppIndex.API).build();
            this.googleClient.connect();
        }
        if (this.action == null && Strings.notEmpty(this.title)) {
            Uri uri = this.deepLink;
            if (uri != null) {
                Uri uri2 = this.webLink;
                if (uri2 == null) {
                    action2 = Action.newAction(Action.TYPE_VIEW, this.title, uri);
                } else {
                    action2 = Action.newAction(Action.TYPE_VIEW, this.title, uri2, uri);
                }
                this.action = action2;
            }
        }
    }
}
