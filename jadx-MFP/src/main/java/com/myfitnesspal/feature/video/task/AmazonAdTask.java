package com.myfitnesspal.feature.video.task;

import android.content.Context;
import com.amazon.device.ads.DTBAdRequest;
import com.amazon.device.ads.DTBAdResponse;
import com.amazon.device.ads.DTBAdSize.DTBVideo;
import com.uacf.core.util.Ln;
import com.uacf.taskrunner.Tasks.AsyncWait;
import com.uacf.taskrunner.Tasks.AsyncWait.Completion;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00122\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003j\u0002`\u00040\u0001:\u0001\u0012B)\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000bJ(\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0016\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003j\u0002`\u00040\u0011H\u0016R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/myfitnesspal/feature/video/task/AmazonAdTask;", "Lcom/uacf/taskrunner/Tasks$AsyncWait;", "Lcom/amazon/device/ads/DTBAdResponse;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "videoWidth", "", "videoHeight", "amazonAppId", "", "slotUuid", "(IILjava/lang/String;Ljava/lang/String;)V", "exec", "", "context", "Landroid/content/Context;", "completion", "Lcom/uacf/taskrunner/Tasks$AsyncWait$Completion;", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: AmazonAdTask.kt */
public final class AmazonAdTask extends AsyncWait<DTBAdResponse, Exception> {
    public static final Companion Companion = new Companion(null);
    public static final int DEFAULT_AD_HEIGHT = 480;
    public static final int DEFAULT_AD_WIDTH = 640;
    @NotNull
    public static final String NAME = "AmazonAdTask";
    @NotNull
    public static final String TAG = "VIDEO_AD";
    /* access modifiers changed from: private */
    public final String amazonAppId;
    /* access modifiers changed from: private */
    public final String slotUuid;
    private final int videoHeight;
    private final int videoWidth;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/myfitnesspal/feature/video/task/AmazonAdTask$Companion;", "", "()V", "DEFAULT_AD_HEIGHT", "", "DEFAULT_AD_WIDTH", "NAME", "", "TAG", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AmazonAdTask.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public /* synthetic */ AmazonAdTask(int i, int i2, String str, String str2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i3 & 1) != 0) {
            i = DEFAULT_AD_WIDTH;
        }
        if ((i3 & 2) != 0) {
            i2 = DEFAULT_AD_HEIGHT;
        }
        this(i, i2, str, str2);
    }

    public AmazonAdTask(int i, int i2, @NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "amazonAppId");
        Intrinsics.checkParameterIsNotNull(str2, "slotUuid");
        this.videoWidth = i;
        this.videoHeight = i2;
        this.amazonAppId = str;
        this.slotUuid = str2;
    }

    public void exec(@NotNull Context context, @NotNull Completion<DTBAdResponse, Exception> completion) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        Ln.w("%s %s %s %s %s", TAG, "Loading video ad:", this.slotUuid, "app id =", this.amazonAppId);
        DTBAdRequest dTBAdRequest = new DTBAdRequest();
        dTBAdRequest.setSizes(new DTBVideo(this.videoWidth, this.videoHeight, this.slotUuid));
        dTBAdRequest.loadAd(new AmazonAdTask$exec$1(this, completion));
    }
}
