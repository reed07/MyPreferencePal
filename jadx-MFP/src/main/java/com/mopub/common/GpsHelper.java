package com.mopub.common;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import com.mopub.common.factories.MethodBuilderFactory;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.AsyncTasks;
import com.mopub.common.util.Reflection;
import java.lang.ref.WeakReference;

public class GpsHelper {
    public static final int GOOGLE_PLAY_SUCCESS_CODE = 0;
    public static final String IS_LIMIT_AD_TRACKING_ENABLED_KEY = "isLimitAdTrackingEnabled";
    /* access modifiers changed from: private */
    public static String sAdvertisingIdClientClassName = "com.google.android.gms.ads.identifier.AdvertisingIdClient";

    public static class AdvertisingInfo {
        public final String advertisingId;
        public final boolean limitAdTracking;

        public AdvertisingInfo(String str, boolean z) {
            this.advertisingId = str;
            this.limitAdTracking = z;
        }
    }

    private static class FetchAdvertisingInfoTask extends AsyncTask<Void, Void, Void> {
        private WeakReference<Context> mContextWeakReference;
        private WeakReference<GpsHelperListener> mGpsHelperListenerWeakReference;

        public FetchAdvertisingInfoTask(Context context, GpsHelperListener gpsHelperListener) {
            this.mContextWeakReference = new WeakReference<>(context);
            this.mGpsHelperListenerWeakReference = new WeakReference<>(gpsHelperListener);
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            try {
                Context context = (Context) this.mContextWeakReference.get();
                if (context == null) {
                    return null;
                }
                MethodBuilderFactory.create(null, "getAdvertisingIdInfo").setStatic(Class.forName(GpsHelper.sAdvertisingIdClientClassName)).addParam(Context.class, context).execute();
                return null;
            } catch (Exception unused) {
                MoPubLog.d("Unable to obtain Google AdvertisingIdClient.Info via reflection.");
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Void voidR) {
            GpsHelperListener gpsHelperListener = (GpsHelperListener) this.mGpsHelperListenerWeakReference.get();
            if (gpsHelperListener != null) {
                gpsHelperListener.onFetchAdInfoCompleted();
            }
        }
    }

    public interface GpsHelperListener {
        void onFetchAdInfoCompleted();
    }

    public static boolean isLimitAdTrackingEnabled(Context context) {
        return SharedPreferencesHelper.getSharedPreferences(context).getBoolean(IS_LIMIT_AD_TRACKING_ENABLED_KEY, false);
    }

    public static void fetchAdvertisingInfoAsync(Context context, GpsHelperListener gpsHelperListener) {
        internalFetchAdvertisingInfoAsync(context, gpsHelperListener);
    }

    @Nullable
    public static AdvertisingInfo fetchAdvertisingInfoSync(Context context) {
        if (context == null) {
            return null;
        }
        try {
            Object execute = MethodBuilderFactory.create(null, "getAdvertisingIdInfo").setStatic(Class.forName(sAdvertisingIdClientClassName)).addParam(Context.class, context).execute();
            return new AdvertisingInfo(reflectedGetAdvertisingId(execute, null), reflectedIsLimitAdTrackingEnabled(execute, false));
        } catch (Exception unused) {
            MoPubLog.d("Unable to obtain Google AdvertisingIdClient.Info via reflection.");
            return null;
        }
    }

    private static void internalFetchAdvertisingInfoAsync(Context context, GpsHelperListener gpsHelperListener) {
        if (!Reflection.classFound(sAdvertisingIdClientClassName)) {
            if (gpsHelperListener != null) {
                gpsHelperListener.onFetchAdInfoCompleted();
            }
            return;
        }
        try {
            AsyncTasks.safeExecuteOnExecutor(new FetchAdvertisingInfoTask(context, gpsHelperListener), new Void[0]);
        } catch (Exception e) {
            MoPubLog.d("Error executing FetchAdvertisingInfoTask", e);
            if (gpsHelperListener != null) {
                gpsHelperListener.onFetchAdInfoCompleted();
            }
        }
    }

    static String reflectedGetAdvertisingId(Object obj, String str) {
        try {
            return (String) MethodBuilderFactory.create(obj, "getId").execute();
        } catch (Exception unused) {
            return str;
        }
    }

    static boolean reflectedIsLimitAdTrackingEnabled(Object obj, boolean z) {
        try {
            Boolean bool = (Boolean) MethodBuilderFactory.create(obj, IS_LIMIT_AD_TRACKING_ENABLED_KEY).execute();
            if (bool != null) {
                z = bool.booleanValue();
            }
            return z;
        } catch (Exception unused) {
            return z;
        }
    }
}
