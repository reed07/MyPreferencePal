package com.myfitnesspal.shared.notification;

import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.uacf.core.util.Debouncer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0002H\u0014¨\u0006\u0006"}, d2 = {"com/myfitnesspal/shared/notification/PushNotificationManager$syncDebouncer$1", "Lcom/uacf/core/util/Debouncer;", "", "onDebounced", "", "context", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: PushNotificationManager.kt */
public final class PushNotificationManager$syncDebouncer$1 extends Debouncer<Object> {
    final /* synthetic */ PushNotificationManager this$0;

    PushNotificationManager$syncDebouncer$1(PushNotificationManager pushNotificationManager, int i) {
        this.this$0 = pushNotificationManager;
        super(i);
    }

    /* access modifiers changed from: protected */
    public void onDebounced(@NotNull Object obj) {
        Intrinsics.checkParameterIsNotNull(obj, "context");
        ((SyncService) this.this$0.syncService.get()).enqueue(SyncType.BackgroundIncremental);
    }
}
