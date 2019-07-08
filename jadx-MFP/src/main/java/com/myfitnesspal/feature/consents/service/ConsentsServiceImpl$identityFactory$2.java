package com.myfitnesspal.feature.consents.service;

import com.uacf.identity.sdk.UacfIdentitySdkFactory;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcom/uacf/identity/sdk/UacfIdentitySdkFactory;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: ConsentsServiceImpl.kt */
final class ConsentsServiceImpl$identityFactory$2 extends Lambda implements Function0<UacfIdentitySdkFactory> {
    public static final ConsentsServiceImpl$identityFactory$2 INSTANCE = new ConsentsServiceImpl$identityFactory$2();

    ConsentsServiceImpl$identityFactory$2() {
        super(0);
    }

    @NotNull
    public final UacfIdentitySdkFactory invoke() {
        return new UacfIdentitySdkFactory();
    }
}
