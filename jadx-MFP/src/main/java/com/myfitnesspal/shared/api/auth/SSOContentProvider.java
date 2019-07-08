package com.myfitnesspal.shared.api.auth;

import com.uacf.identity.sdk.content.UacfIdentityContentProvider;
import io.uacf.core.app.UacfAppId;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0014¨\u0006\u0005"}, d2 = {"Lcom/myfitnesspal/shared/api/auth/SSOContentProvider;", "Lcom/uacf/identity/sdk/content/UacfIdentityContentProvider;", "()V", "getAppId", "Lio/uacf/core/app/UacfAppId;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: SSOContentProvider.kt */
public final class SSOContentProvider extends UacfIdentityContentProvider {
    /* access modifiers changed from: protected */
    @NotNull
    public UacfAppId getAppId() {
        return UacfAppId.MYFITNESSPAL;
    }
}
