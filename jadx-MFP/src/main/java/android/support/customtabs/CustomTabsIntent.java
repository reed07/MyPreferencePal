package android.support.customtabs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.BundleCompat;
import android.support.v4.content.ContextCompat;
import java.util.ArrayList;

public final class CustomTabsIntent {
    @NonNull
    public final Intent intent;
    @Nullable
    public final Bundle startAnimationBundle;

    public static final class Builder {
        private ArrayList<Bundle> mActionButtons;
        private boolean mInstantAppsEnabled;
        private final Intent mIntent;
        private ArrayList<Bundle> mMenuItems;
        private Bundle mStartAnimationBundle;

        public Builder() {
            this(null);
        }

        public Builder(@Nullable CustomTabsSession customTabsSession) {
            this.mIntent = new Intent("android.intent.action.VIEW");
            IBinder iBinder = null;
            this.mMenuItems = null;
            this.mStartAnimationBundle = null;
            this.mActionButtons = null;
            this.mInstantAppsEnabled = true;
            if (customTabsSession != null) {
                this.mIntent.setPackage(customTabsSession.getComponentName().getPackageName());
            }
            Bundle bundle = new Bundle();
            String str = "android.support.customtabs.extra.SESSION";
            if (customTabsSession != null) {
                iBinder = customTabsSession.getBinder();
            }
            BundleCompat.putBinder(bundle, str, iBinder);
            this.mIntent.putExtras(bundle);
        }

        public CustomTabsIntent build() {
            ArrayList<Bundle> arrayList = this.mMenuItems;
            if (arrayList != null) {
                this.mIntent.putParcelableArrayListExtra("android.support.customtabs.extra.MENU_ITEMS", arrayList);
            }
            ArrayList<Bundle> arrayList2 = this.mActionButtons;
            if (arrayList2 != null) {
                this.mIntent.putParcelableArrayListExtra("android.support.customtabs.extra.TOOLBAR_ITEMS", arrayList2);
            }
            this.mIntent.putExtra("android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS", this.mInstantAppsEnabled);
            return new CustomTabsIntent(this.mIntent, this.mStartAnimationBundle);
        }
    }

    public void launchUrl(Context context, Uri uri) {
        this.intent.setData(uri);
        ContextCompat.startActivity(context, this.intent, this.startAnimationBundle);
    }

    CustomTabsIntent(Intent intent2, Bundle bundle) {
        this.intent = intent2;
        this.startAnimationBundle = bundle;
    }
}
