package com.myfitnesspal.shared.service.ads;

import android.content.Context;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.uacf.core.util.Ln;
import kotlin.Metadata;
import kotlin.Result.Failure;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "com/myfitnesspal/shared/service/ads/AdIdConsentCompliant$storeGaidAndSendAnalyticsEvent$1", f = "AdIdConsentCompliant.kt", i = {}, l = {15}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: AdIdConsentCompliant.kt */
final class AdIdConsentCompliant$storeGaidAndSendAnalyticsEvent$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ AdIdConsentCompliant this$0;

    AdIdConsentCompliant$storeGaidAndSendAnalyticsEvent$1(AdIdConsentCompliant adIdConsentCompliant, Context context, Continuation continuation) {
        this.this$0 = adIdConsentCompliant;
        this.$context = context;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        AdIdConsentCompliant$storeGaidAndSendAnalyticsEvent$1 adIdConsentCompliant$storeGaidAndSendAnalyticsEvent$1 = new AdIdConsentCompliant$storeGaidAndSendAnalyticsEvent$1(this.this$0, this.$context, continuation);
        adIdConsentCompliant$storeGaidAndSendAnalyticsEvent$1.p$ = (CoroutineScope) obj;
        return adIdConsentCompliant$storeGaidAndSendAnalyticsEvent$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((AdIdConsentCompliant$storeGaidAndSendAnalyticsEvent$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else if (!(obj instanceof Failure)) {
            CoroutineScope coroutineScope = this.p$;
            try {
                boolean isAdTrackingEnabled = this.this$0.androidAdvertisementIdentifier.isAdTrackingEnabled(this.$context);
                String id = this.this$0.androidAdvertisementIdentifier.getID(this.$context);
                Object obj2 = this.this$0.localSettingsService.get();
                Intrinsics.checkExpressionValueIsNotNull(obj2, "localSettingsService.get()");
                LocalSettingsService localSettingsService = (LocalSettingsService) obj2;
                if (!isAdTrackingEnabled) {
                    id = "";
                }
                localSettingsService.setGAID(id);
                if (isAdTrackingEnabled) {
                    ((LocalSettingsService) this.this$0.localSettingsService.get()).enableAdTracking();
                } else {
                    ((LocalSettingsService) this.this$0.localSettingsService.get()).disableAdTracking();
                }
                ((AdsAnalyticsHelper) this.this$0.adAnalyticsHelper.get()).reportAdLimitingEvent(!isAdTrackingEnabled);
            } catch (Exception e) {
                Object obj3 = this.this$0.localSettingsService.get();
                Intrinsics.checkExpressionValueIsNotNull(obj3, "localSettingsService.get()");
                ((LocalSettingsService) obj3).setGAID("");
                Ln.e("AdIdConsentCompliant.storeGaidAndSendAnalyticsEvent exception", e.getClass().getName());
            }
            return Unit.INSTANCE;
        } else {
            throw ((Failure) obj).exception;
        }
    }
}
