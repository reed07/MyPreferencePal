package com.myfitnesspal.feature.consents.model;

import com.myfitnesspal.feature.consents.service.ConsentsService;
import io.uacf.core.consents.UacfUserConsentStatus;
import kotlin.Metadata;
import kotlin.Result.Failure;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.BooleanRef;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "com/myfitnesspal/feature/consents/model/AdConsentsViewModel$updateAdConsents$4", f = "AdConsentsViewModel.kt", i = {}, l = {93, 95}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: AdConsentsViewModel.kt */
final class AdConsentsViewModel$updateAdConsents$4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ BooleanRef $isPersonalizedAdConsentAccepted;
    final /* synthetic */ UacfUserConsentStatus $userConsentStatus;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ AdConsentsViewModel this$0;

    AdConsentsViewModel$updateAdConsents$4(AdConsentsViewModel adConsentsViewModel, UacfUserConsentStatus uacfUserConsentStatus, BooleanRef booleanRef, Continuation continuation) {
        this.this$0 = adConsentsViewModel;
        this.$userConsentStatus = uacfUserConsentStatus;
        this.$isPersonalizedAdConsentAccepted = booleanRef;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        AdConsentsViewModel$updateAdConsents$4 adConsentsViewModel$updateAdConsents$4 = new AdConsentsViewModel$updateAdConsents$4(this.this$0, this.$userConsentStatus, this.$isPersonalizedAdConsentAccepted, continuation);
        adConsentsViewModel$updateAdConsents$4.p$ = (CoroutineScope) obj;
        return adConsentsViewModel$updateAdConsents$4;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((AdConsentsViewModel$updateAdConsents$4) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                if (!(obj instanceof Failure)) {
                    CoroutineScope coroutineScope = this.p$;
                    ConsentsService access$getConsentService$p = this.this$0.consentService;
                    UacfUserConsentStatus uacfUserConsentStatus = this.$userConsentStatus;
                    this.label = 1;
                    if (access$getConsentService$p.updateAdConsentsForInterruption(uacfUserConsentStatus, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    throw ((Failure) obj).exception;
                }
                break;
            case 1:
                if (obj instanceof Failure) {
                    throw ((Failure) obj).exception;
                }
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.this$0.adConsentsAnalyticsHelper.reportInterruptionAccepted(this.this$0.getConsentsList());
        this.this$0.localSettingsService.setIsPersonalizedAdConsentAccepted(this.$isPersonalizedAdConsentAccepted.element);
        return Unit.INSTANCE;
    }
}
