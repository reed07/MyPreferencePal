package android.support.v4.media;

import android.media.session.MediaSessionManager.RemoteUserInfo;
import android.support.annotation.RequiresApi;
import android.support.v4.util.ObjectsCompat;

@RequiresApi
class MediaSessionManagerImplApi28 extends MediaSessionManagerImplApi21 {

    static final class RemoteUserInfoImplApi28 implements RemoteUserInfoImpl {
        final RemoteUserInfo mObject;

        RemoteUserInfoImplApi28(String str, int i, int i2) {
            this.mObject = new RemoteUserInfo(str, i, i2);
        }

        public int hashCode() {
            return ObjectsCompat.hash(this.mObject);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof RemoteUserInfoImplApi28)) {
                return false;
            }
            return this.mObject.equals(((RemoteUserInfoImplApi28) obj).mObject);
        }
    }
}
