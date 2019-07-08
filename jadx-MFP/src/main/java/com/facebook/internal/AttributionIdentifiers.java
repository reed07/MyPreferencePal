package com.facebook.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.mopub.common.GpsHelper;
import java.lang.reflect.Method;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class AttributionIdentifiers {
    private static final String ANDROID_ID_COLUMN_NAME = "androidid";
    private static final String ATTRIBUTION_ID_COLUMN_NAME = "aid";
    private static final String ATTRIBUTION_ID_CONTENT_PROVIDER = "com.facebook.katana.provider.AttributionIdProvider";
    private static final String ATTRIBUTION_ID_CONTENT_PROVIDER_WAKIZASHI = "com.facebook.wakizashi.provider.AttributionIdProvider";
    private static final int CONNECTION_RESULT_SUCCESS = 0;
    private static final long IDENTIFIER_REFRESH_INTERVAL_MILLIS = 3600000;
    private static final String LIMIT_TRACKING_COLUMN_NAME = "limit_tracking";
    private static final String TAG = AttributionIdentifiers.class.getCanonicalName();
    private static AttributionIdentifiers recentlyFetchedIdentifiers;
    private String androidAdvertiserId;
    private String androidInstallerPackage;
    private String attributionId;
    private long fetchTime;
    private boolean limitTracking;

    private static final class GoogleAdInfo implements IInterface {
        private static final int FIRST_TRANSACTION_CODE = 1;
        private static final int SECOND_TRANSACTION_CODE = 2;
        private IBinder binder;

        GoogleAdInfo(IBinder iBinder) {
            this.binder = iBinder;
        }

        public IBinder asBinder() {
            return this.binder;
        }

        public String getAdvertiserId() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.binder.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public boolean isTrackingLimited() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                boolean z = true;
                obtain.writeInt(1);
                this.binder.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() == 0) {
                    z = false;
                }
                return z;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }

    private static final class GoogleAdServiceConnection implements ServiceConnection {
        private AtomicBoolean consumed;
        private final BlockingQueue<IBinder> queue;

        public void onServiceDisconnected(ComponentName componentName) {
        }

        private GoogleAdServiceConnection() {
            this.consumed = new AtomicBoolean(false);
            this.queue = new LinkedBlockingDeque();
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (iBinder != null) {
                try {
                    this.queue.put(iBinder);
                } catch (InterruptedException unused) {
                }
            }
        }

        public IBinder getBinder() throws InterruptedException {
            if (!this.consumed.compareAndSet(true, true)) {
                return (IBinder) this.queue.take();
            }
            throw new IllegalStateException("Binder already consumed");
        }
    }

    private static AttributionIdentifiers getAndroidId(Context context) {
        AttributionIdentifiers androidIdViaReflection = getAndroidIdViaReflection(context);
        if (androidIdViaReflection != null) {
            return androidIdViaReflection;
        }
        AttributionIdentifiers androidIdViaService = getAndroidIdViaService(context);
        return androidIdViaService == null ? new AttributionIdentifiers() : androidIdViaService;
    }

    private static AttributionIdentifiers getAndroidIdViaReflection(Context context) {
        try {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                Method methodQuietly = Utility.getMethodQuietly("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", (Class<?>[]) new Class[]{Context.class});
                if (methodQuietly == null) {
                    return null;
                }
                Object invokeMethodQuietly = Utility.invokeMethodQuietly(null, methodQuietly, context);
                if (invokeMethodQuietly instanceof Integer) {
                    if (((Integer) invokeMethodQuietly).intValue() == 0) {
                        Method methodQuietly2 = Utility.getMethodQuietly("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", (Class<?>[]) new Class[]{Context.class});
                        if (methodQuietly2 == null) {
                            return null;
                        }
                        Object invokeMethodQuietly2 = Utility.invokeMethodQuietly(null, methodQuietly2, context);
                        if (invokeMethodQuietly2 == null) {
                            return null;
                        }
                        Method methodQuietly3 = Utility.getMethodQuietly(invokeMethodQuietly2.getClass(), "getId", (Class<?>[]) new Class[0]);
                        Method methodQuietly4 = Utility.getMethodQuietly(invokeMethodQuietly2.getClass(), GpsHelper.IS_LIMIT_AD_TRACKING_ENABLED_KEY, (Class<?>[]) new Class[0]);
                        if (methodQuietly3 != null) {
                            if (methodQuietly4 != null) {
                                AttributionIdentifiers attributionIdentifiers = new AttributionIdentifiers();
                                attributionIdentifiers.androidAdvertiserId = (String) Utility.invokeMethodQuietly(invokeMethodQuietly2, methodQuietly3, new Object[0]);
                                attributionIdentifiers.limitTracking = ((Boolean) Utility.invokeMethodQuietly(invokeMethodQuietly2, methodQuietly4, new Object[0])).booleanValue();
                                return attributionIdentifiers;
                            }
                        }
                        return null;
                    }
                }
                return null;
            }
            throw new FacebookException("getAndroidId cannot be called on the main thread.");
        } catch (Exception e) {
            Utility.logd("android_id", e);
            return null;
        }
    }

    private static AttributionIdentifiers getAndroidIdViaService(Context context) {
        GoogleAdServiceConnection googleAdServiceConnection = new GoogleAdServiceConnection();
        Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        if (context.bindService(intent, googleAdServiceConnection, 1)) {
            try {
                GoogleAdInfo googleAdInfo = new GoogleAdInfo(googleAdServiceConnection.getBinder());
                AttributionIdentifiers attributionIdentifiers = new AttributionIdentifiers();
                attributionIdentifiers.androidAdvertiserId = googleAdInfo.getAdvertiserId();
                attributionIdentifiers.limitTracking = googleAdInfo.isTrackingLimited();
                return attributionIdentifiers;
            } catch (Exception e) {
                Utility.logd("android_id", e);
            } finally {
                context.unbindService(googleAdServiceConnection);
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:54:0x00f1  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00f9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.facebook.internal.AttributionIdentifiers getAttributionIdentifiers(android.content.Context r12) {
        /*
            android.os.Looper r0 = android.os.Looper.myLooper()
            android.os.Looper r1 = android.os.Looper.getMainLooper()
            if (r0 != r1) goto L_0x0011
            java.lang.String r0 = TAG
            java.lang.String r1 = "getAttributionIdentifiers should not be called from the main thread"
            android.util.Log.e(r0, r1)
        L_0x0011:
            com.facebook.internal.AttributionIdentifiers r0 = recentlyFetchedIdentifiers
            if (r0 == 0) goto L_0x0026
            long r0 = java.lang.System.currentTimeMillis()
            com.facebook.internal.AttributionIdentifiers r2 = recentlyFetchedIdentifiers
            long r3 = r2.fetchTime
            long r0 = r0 - r3
            r3 = 3600000(0x36ee80, double:1.7786363E-317)
            int r5 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r5 >= 0) goto L_0x0026
            return r2
        L_0x0026:
            com.facebook.internal.AttributionIdentifiers r0 = getAndroidId(r12)
            r1 = 0
            java.lang.String r2 = "aid"
            java.lang.String r3 = "androidid"
            java.lang.String r4 = "limit_tracking"
            java.lang.String[] r7 = new java.lang.String[]{r2, r3, r4}     // Catch:{ Exception -> 0x00d3, all -> 0x00d1 }
            android.content.pm.PackageManager r2 = r12.getPackageManager()     // Catch:{ Exception -> 0x00d3, all -> 0x00d1 }
            java.lang.String r3 = "com.facebook.katana.provider.AttributionIdProvider"
            r4 = 0
            android.content.pm.ProviderInfo r2 = r2.resolveContentProvider(r3, r4)     // Catch:{ Exception -> 0x00d3, all -> 0x00d1 }
            if (r2 == 0) goto L_0x004a
            java.lang.String r2 = "content://com.facebook.katana.provider.AttributionIdProvider"
            android.net.Uri r2 = android.net.Uri.parse(r2)     // Catch:{ Exception -> 0x00d3, all -> 0x00d1 }
            r6 = r2
            goto L_0x005f
        L_0x004a:
            android.content.pm.PackageManager r2 = r12.getPackageManager()     // Catch:{ Exception -> 0x00d3, all -> 0x00d1 }
            java.lang.String r3 = "com.facebook.wakizashi.provider.AttributionIdProvider"
            android.content.pm.ProviderInfo r2 = r2.resolveContentProvider(r3, r4)     // Catch:{ Exception -> 0x00d3, all -> 0x00d1 }
            if (r2 == 0) goto L_0x005e
            java.lang.String r2 = "content://com.facebook.wakizashi.provider.AttributionIdProvider"
            android.net.Uri r2 = android.net.Uri.parse(r2)     // Catch:{ Exception -> 0x00d3, all -> 0x00d1 }
            r6 = r2
            goto L_0x005f
        L_0x005e:
            r6 = r1
        L_0x005f:
            java.lang.String r2 = getInstallerPackageName(r12)     // Catch:{ Exception -> 0x00d3, all -> 0x00d1 }
            if (r2 == 0) goto L_0x0067
            r0.androidInstallerPackage = r2     // Catch:{ Exception -> 0x00d3, all -> 0x00d1 }
        L_0x0067:
            if (r6 != 0) goto L_0x006e
            com.facebook.internal.AttributionIdentifiers r12 = cacheAndReturnIdentifiers(r0)     // Catch:{ Exception -> 0x00d3, all -> 0x00d1 }
            return r12
        L_0x006e:
            android.content.ContentResolver r5 = r12.getContentResolver()     // Catch:{ Exception -> 0x00d3, all -> 0x00d1 }
            r8 = 0
            r9 = 0
            r10 = 0
            android.database.Cursor r12 = r5.query(r6, r7, r8, r9, r10)     // Catch:{ Exception -> 0x00d3, all -> 0x00d1 }
            if (r12 == 0) goto L_0x00be
            boolean r2 = r12.moveToFirst()     // Catch:{ Exception -> 0x00cc, all -> 0x00c8 }
            if (r2 != 0) goto L_0x0082
            goto L_0x00be
        L_0x0082:
            java.lang.String r2 = "aid"
            int r2 = r12.getColumnIndex(r2)     // Catch:{ Exception -> 0x00cc, all -> 0x00c8 }
            java.lang.String r3 = "androidid"
            int r3 = r12.getColumnIndex(r3)     // Catch:{ Exception -> 0x00cc, all -> 0x00c8 }
            java.lang.String r4 = "limit_tracking"
            int r4 = r12.getColumnIndex(r4)     // Catch:{ Exception -> 0x00cc, all -> 0x00c8 }
            java.lang.String r2 = r12.getString(r2)     // Catch:{ Exception -> 0x00cc, all -> 0x00c8 }
            r0.attributionId = r2     // Catch:{ Exception -> 0x00cc, all -> 0x00c8 }
            if (r3 <= 0) goto L_0x00b4
            if (r4 <= 0) goto L_0x00b4
            java.lang.String r2 = r0.getAndroidAdvertiserId()     // Catch:{ Exception -> 0x00cc, all -> 0x00c8 }
            if (r2 != 0) goto L_0x00b4
            java.lang.String r2 = r12.getString(r3)     // Catch:{ Exception -> 0x00cc, all -> 0x00c8 }
            r0.androidAdvertiserId = r2     // Catch:{ Exception -> 0x00cc, all -> 0x00c8 }
            java.lang.String r2 = r12.getString(r4)     // Catch:{ Exception -> 0x00cc, all -> 0x00c8 }
            boolean r2 = java.lang.Boolean.parseBoolean(r2)     // Catch:{ Exception -> 0x00cc, all -> 0x00c8 }
            r0.limitTracking = r2     // Catch:{ Exception -> 0x00cc, all -> 0x00c8 }
        L_0x00b4:
            if (r12 == 0) goto L_0x00b9
            r12.close()
        L_0x00b9:
            com.facebook.internal.AttributionIdentifiers r12 = cacheAndReturnIdentifiers(r0)
            return r12
        L_0x00be:
            com.facebook.internal.AttributionIdentifiers r0 = cacheAndReturnIdentifiers(r0)     // Catch:{ Exception -> 0x00cc, all -> 0x00c8 }
            if (r12 == 0) goto L_0x00c7
            r12.close()
        L_0x00c7:
            return r0
        L_0x00c8:
            r0 = move-exception
            r1 = r12
            r12 = r0
            goto L_0x00f7
        L_0x00cc:
            r0 = move-exception
            r11 = r0
            r0 = r12
            r12 = r11
            goto L_0x00d5
        L_0x00d1:
            r12 = move-exception
            goto L_0x00f7
        L_0x00d3:
            r12 = move-exception
            r0 = r1
        L_0x00d5:
            java.lang.String r2 = TAG     // Catch:{ all -> 0x00f5 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f5 }
            r3.<init>()     // Catch:{ all -> 0x00f5 }
            java.lang.String r4 = "Caught unexpected exception in getAttributionId(): "
            r3.append(r4)     // Catch:{ all -> 0x00f5 }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x00f5 }
            r3.append(r12)     // Catch:{ all -> 0x00f5 }
            java.lang.String r12 = r3.toString()     // Catch:{ all -> 0x00f5 }
            com.facebook.internal.Utility.logd(r2, r12)     // Catch:{ all -> 0x00f5 }
            if (r0 == 0) goto L_0x00f4
            r0.close()
        L_0x00f4:
            return r1
        L_0x00f5:
            r12 = move-exception
            r1 = r0
        L_0x00f7:
            if (r1 == 0) goto L_0x00fc
            r1.close()
        L_0x00fc:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.AttributionIdentifiers.getAttributionIdentifiers(android.content.Context):com.facebook.internal.AttributionIdentifiers");
    }

    public static AttributionIdentifiers getCachedIdentifiers() {
        return recentlyFetchedIdentifiers;
    }

    private static AttributionIdentifiers cacheAndReturnIdentifiers(AttributionIdentifiers attributionIdentifiers) {
        attributionIdentifiers.fetchTime = System.currentTimeMillis();
        recentlyFetchedIdentifiers = attributionIdentifiers;
        return attributionIdentifiers;
    }

    public String getAttributionId() {
        return this.attributionId;
    }

    public String getAndroidAdvertiserId() {
        if (!FacebookSdk.isInitialized() || !FacebookSdk.getAdvertiserIDCollectionEnabled()) {
            return null;
        }
        return this.androidAdvertiserId;
    }

    public String getAndroidInstallerPackage() {
        return this.androidInstallerPackage;
    }

    public boolean isTrackingLimited() {
        return this.limitTracking;
    }

    @Nullable
    private static String getInstallerPackageName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            return packageManager.getInstallerPackageName(context.getPackageName());
        }
        return null;
    }
}
