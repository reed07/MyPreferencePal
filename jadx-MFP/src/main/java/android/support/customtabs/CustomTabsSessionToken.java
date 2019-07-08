package android.support.customtabs;

import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class CustomTabsSessionToken {
    private final CustomTabsCallback mCallback = new CustomTabsCallback() {
        public void onNavigationEvent(int i, Bundle bundle) {
            try {
                CustomTabsSessionToken.this.mCallbackBinder.onNavigationEvent(i, bundle);
            } catch (RemoteException unused) {
                Log.e("CustomTabsSessionToken", "RemoteException during ICustomTabsCallback transaction");
            }
        }

        public void extraCallback(String str, Bundle bundle) {
            try {
                CustomTabsSessionToken.this.mCallbackBinder.extraCallback(str, bundle);
            } catch (RemoteException unused) {
                Log.e("CustomTabsSessionToken", "RemoteException during ICustomTabsCallback transaction");
            }
        }

        public void onMessageChannelReady(Bundle bundle) {
            try {
                CustomTabsSessionToken.this.mCallbackBinder.onMessageChannelReady(bundle);
            } catch (RemoteException unused) {
                Log.e("CustomTabsSessionToken", "RemoteException during ICustomTabsCallback transaction");
            }
        }

        public void onPostMessage(String str, Bundle bundle) {
            try {
                CustomTabsSessionToken.this.mCallbackBinder.onPostMessage(str, bundle);
            } catch (RemoteException unused) {
                Log.e("CustomTabsSessionToken", "RemoteException during ICustomTabsCallback transaction");
            }
        }

        public void onRelationshipValidationResult(int i, Uri uri, boolean z, Bundle bundle) {
            try {
                CustomTabsSessionToken.this.mCallbackBinder.onRelationshipValidationResult(i, uri, z, bundle);
            } catch (RemoteException unused) {
                Log.e("CustomTabsSessionToken", "RemoteException during ICustomTabsCallback transaction");
            }
        }
    };
    final ICustomTabsCallback mCallbackBinder;

    CustomTabsSessionToken(ICustomTabsCallback iCustomTabsCallback) {
        this.mCallbackBinder = iCustomTabsCallback;
    }

    /* access modifiers changed from: 0000 */
    public IBinder getCallbackBinder() {
        return this.mCallbackBinder.asBinder();
    }

    public int hashCode() {
        return getCallbackBinder().hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CustomTabsSessionToken)) {
            return false;
        }
        return ((CustomTabsSessionToken) obj).getCallbackBinder().equals(this.mCallbackBinder.asBinder());
    }
}
