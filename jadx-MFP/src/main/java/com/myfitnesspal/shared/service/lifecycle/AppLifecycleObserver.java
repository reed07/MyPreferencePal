package com.myfitnesspal.shared.service.lifecycle;

import android.arch.lifecycle.DefaultLifecycleObserver;
import android.arch.lifecycle.DefaultLifecycleObserver.CC;
import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.support.annotation.NonNull;
import com.myfitnesspal.shared.service.ads.AdIdConsentCompliant;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/myfitnesspal/shared/service/lifecycle/AppLifecycleObserver;", "Landroid/arch/lifecycle/DefaultLifecycleObserver;", "context", "Landroid/content/Context;", "adIdConsentCompliant", "Lcom/myfitnesspal/shared/service/ads/AdIdConsentCompliant;", "(Landroid/content/Context;Lcom/myfitnesspal/shared/service/ads/AdIdConsentCompliant;)V", "onStart", "", "owner", "Landroid/arch/lifecycle/LifecycleOwner;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: AppLifecycleObserver.kt */
public final class AppLifecycleObserver implements DefaultLifecycleObserver {
    private final AdIdConsentCompliant adIdConsentCompliant;
    private final Context context;

    public /* synthetic */ void onCreate(@NonNull LifecycleOwner lifecycleOwner) {
        CC.$default$onCreate(this, lifecycleOwner);
    }

    public /* synthetic */ void onDestroy(@NonNull LifecycleOwner lifecycleOwner) {
        CC.$default$onDestroy(this, lifecycleOwner);
    }

    public /* synthetic */ void onPause(@NonNull LifecycleOwner lifecycleOwner) {
        CC.$default$onPause(this, lifecycleOwner);
    }

    public /* synthetic */ void onResume(@NonNull LifecycleOwner lifecycleOwner) {
        CC.$default$onResume(this, lifecycleOwner);
    }

    public /* synthetic */ void onStop(@NonNull LifecycleOwner lifecycleOwner) {
        CC.$default$onStop(this, lifecycleOwner);
    }

    @Inject
    public AppLifecycleObserver(@NotNull Context context2, @NotNull AdIdConsentCompliant adIdConsentCompliant2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(adIdConsentCompliant2, "adIdConsentCompliant");
        this.context = context2;
        this.adIdConsentCompliant = adIdConsentCompliant2;
    }

    public void onStart(@NotNull LifecycleOwner lifecycleOwner) {
        Intrinsics.checkParameterIsNotNull(lifecycleOwner, "owner");
        this.adIdConsentCompliant.storeGaidAndSendAnalyticsEvent(this.context);
    }
}
