package com.myfitnesspal.shared.service.location;

import android.location.Location;
import com.google.android.gms.tasks.OnSuccessListener;
import com.myfitnesspal.shared.model.UserLocation;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "location", "Landroid/location/Location;", "onSuccess", "com/myfitnesspal/shared/service/location/LocationServiceImpl$updateUserLocation$1$1"}, k = 3, mv = {1, 1, 13})
/* compiled from: LocationServiceImpl.kt */
final class LocationServiceImpl$updateUserLocation$$inlined$with$lambda$1<TResult> implements OnSuccessListener<Location> {
    final /* synthetic */ LocationServiceImpl this$0;

    LocationServiceImpl$updateUserLocation$$inlined$with$lambda$1(LocationServiceImpl locationServiceImpl) {
        this.this$0 = locationServiceImpl;
    }

    public final void onSuccess(@Nullable Location location) {
        if (location != null) {
            Object obj = this.this$0.getLocalSettingsService().get();
            Intrinsics.checkExpressionValueIsNotNull(obj, "localSettingsService.get()");
            LocalSettingsService localSettingsService = (LocalSettingsService) obj;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Object[] objArr = {Double.valueOf(location.getLatitude())};
            String format = String.format("%.2f", Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            Object[] objArr2 = {Double.valueOf(location.getLongitude())};
            String format2 = String.format("%.2f", Arrays.copyOf(objArr2, objArr2.length));
            Intrinsics.checkExpressionValueIsNotNull(format2, "java.lang.String.format(format, *args)");
            localSettingsService.setUserLocation(new UserLocation(format, format2));
        }
    }
}
