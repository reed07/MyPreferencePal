package com.myfitnesspal.feature.video.task;

import com.amazon.device.ads.AdError;
import com.amazon.device.ads.DTBAdCallback;
import com.amazon.device.ads.DTBAdResponse;
import com.brightcove.player.event.EventType;
import com.uacf.core.util.Ln;
import com.uacf.taskrunner.Tasks.AsyncWait.Completion;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"com/myfitnesspal/feature/video/task/AmazonAdTask$exec$1", "Lcom/amazon/device/ads/DTBAdCallback;", "onFailure", "", "adError", "Lcom/amazon/device/ads/AdError;", "onSuccess", "dtbAdResponse", "Lcom/amazon/device/ads/DTBAdResponse;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: AmazonAdTask.kt */
public final class AmazonAdTask$exec$1 implements DTBAdCallback {
    final /* synthetic */ Completion $completion;
    final /* synthetic */ AmazonAdTask this$0;

    AmazonAdTask$exec$1(AmazonAdTask amazonAdTask, Completion completion) {
        this.this$0 = amazonAdTask;
        this.$completion = completion;
    }

    public void onFailure(@NotNull AdError adError) {
        Intrinsics.checkParameterIsNotNull(adError, EventType.AD_ERROR);
        Ln.e("%s %s %s", AmazonAdTask.TAG, "Failed to get the video ad from Amazon:", adError.getMessage());
        this.$completion.setResult(null);
        this.$completion.complete();
    }

    public void onSuccess(@NotNull DTBAdResponse dTBAdResponse) {
        Intrinsics.checkParameterIsNotNull(dTBAdResponse, "dtbAdResponse");
        this.$completion.setResult(dTBAdResponse);
        this.$completion.complete();
        Ln.i("%s %s %s %s %s", AmazonAdTask.TAG, "onSuccess for ", this.this$0.slotUuid, "app id =", this.this$0.amazonAppId);
    }
}
