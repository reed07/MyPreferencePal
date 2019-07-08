package android.support.v4.media;

import android.service.media.MediaBrowserService.Result;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.lang.reflect.Field;

@RequiresApi
class MediaBrowserServiceCompatApi26 {
    static Field sResultFlags;

    public interface ServiceCompatProxy extends android.support.v4.media.MediaBrowserServiceCompatApi23.ServiceCompatProxy {
    }

    static {
        try {
            sResultFlags = Result.class.getDeclaredField("mFlags");
            sResultFlags.setAccessible(true);
        } catch (NoSuchFieldException e) {
            Log.w("MBSCompatApi26", e);
        }
    }

    private MediaBrowserServiceCompatApi26() {
    }
}
