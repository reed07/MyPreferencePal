package com.myfitnesspal.feature.achievementinterstitialad.util;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/myfitnesspal/feature/achievementinterstitialad/util/AdErrorConverter;", "", "()V", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: AdErrorConverter.kt */
public final class AdErrorConverter {
    private static final int CODE_INTERNAL_ERROR = 0;
    private static final int CODE_INVALID_REQUEST = 1;
    private static final int CODE_NETWORK_ERROR = 2;
    private static final int CODE_NO_FILL = 3;
    public static final Companion Companion = new Companion(null);
    private static final String MESSAGE_INTERNAL_ERROR = "Something happened internally; for instance, an invalid response was received from the ad server";
    private static final String MESSAGE_INVALID_REQUEST = "The ad request was invalid; for instance, the ad unit ID was incorrect";
    private static final String MESSAGE_NETWORK_ERROR = "The ad request was unsuccessful due to network connectivity";
    private static final String MESSAGE_NO_FILL = "The ad request was successful, but no ad was returned due to lack of ad inventory";

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tXT¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/myfitnesspal/feature/achievementinterstitialad/util/AdErrorConverter$Companion;", "", "()V", "CODE_INTERNAL_ERROR", "", "CODE_INVALID_REQUEST", "CODE_NETWORK_ERROR", "CODE_NO_FILL", "MESSAGE_INTERNAL_ERROR", "", "MESSAGE_INVALID_REQUEST", "MESSAGE_NETWORK_ERROR", "MESSAGE_NO_FILL", "errorMessageByErrorCode", "errorCode", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AdErrorConverter.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final String errorMessageByErrorCode(int i) {
            switch (i) {
                case 0:
                    return AdErrorConverter.MESSAGE_INTERNAL_ERROR;
                case 1:
                    return AdErrorConverter.MESSAGE_INVALID_REQUEST;
                case 2:
                    return AdErrorConverter.MESSAGE_NETWORK_ERROR;
                case 3:
                    return AdErrorConverter.MESSAGE_NO_FILL;
                default:
                    return String.valueOf(i);
            }
        }
    }
}
