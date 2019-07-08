package com.myfitnesspal.shared.service.ads;

import android.content.Context;
import android.support.annotation.WorkerThread;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0017J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0017¨\u0006\t"}, d2 = {"Lcom/myfitnesspal/shared/service/ads/AndroidAdvertisementIdentifier;", "", "()V", "getID", "", "context", "Landroid/content/Context;", "isAdTrackingEnabled", "", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: AndroidAdvertisementIdentifier.kt */
public class AndroidAdvertisementIdentifier {
    @WorkerThread
    public boolean isAdTrackingEnabled(@NotNull Context context) throws Exception {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
        Intrinsics.checkExpressionValueIsNotNull(advertisingIdInfo, "AdvertisingIdClient.getAdvertisingIdInfo(context)");
        return !advertisingIdInfo.isLimitAdTrackingEnabled();
    }

    @WorkerThread
    @NotNull
    public String getID(@NotNull Context context) throws Exception {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
        Intrinsics.checkExpressionValueIsNotNull(advertisingIdInfo, "AdvertisingIdClient.getAdvertisingIdInfo(context)");
        String id = advertisingIdInfo.getId();
        Intrinsics.checkExpressionValueIsNotNull(id, "AdvertisingIdClient.getA…rtisingIdInfo(context).id");
        return id;
    }
}
