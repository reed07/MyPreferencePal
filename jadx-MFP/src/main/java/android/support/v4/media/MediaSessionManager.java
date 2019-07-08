package android.support.v4.media;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public final class MediaSessionManager {
    static final boolean DEBUG = Log.isLoggable("MediaSessionManager", 3);
    private static final Object sLock = new Object();

    public static final class RemoteUserInfo {
        RemoteUserInfoImpl mImpl;

        public RemoteUserInfo(@NonNull String str, int i, int i2) {
            if (VERSION.SDK_INT >= 28) {
                this.mImpl = new RemoteUserInfoImplApi28(str, i, i2);
            } else {
                this.mImpl = new RemoteUserInfoImplBase(str, i, i2);
            }
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof RemoteUserInfo)) {
                return false;
            }
            return this.mImpl.equals(((RemoteUserInfo) obj).mImpl);
        }

        public int hashCode() {
            return this.mImpl.hashCode();
        }
    }

    interface RemoteUserInfoImpl {
    }
}
