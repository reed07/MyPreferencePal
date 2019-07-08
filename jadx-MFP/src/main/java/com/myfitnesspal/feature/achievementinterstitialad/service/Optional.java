package com.myfitnesspal.feature.achievementinterstitialad.service;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002:\u0002\u0004\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/myfitnesspal/feature/achievementinterstitialad/service/Optional;", "T", "", "()V", "None", "Some", "Lcom/myfitnesspal/feature/achievementinterstitialad/service/Optional$Some;", "Lcom/myfitnesspal/feature/achievementinterstitialad/service/Optional$None;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: AchievementAdManager.kt */
public abstract class Optional<T> {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/myfitnesspal/feature/achievementinterstitialad/service/Optional$None;", "Lcom/myfitnesspal/feature/achievementinterstitialad/service/Optional;", "", "()V", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AchievementAdManager.kt */
    public static final class None extends Optional {
        public static final None INSTANCE = new None();

        private None() {
            super(null);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0001¢\u0006\u0002\u0010\u0004R\u0013\u0010\u0003\u001a\u00028\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lcom/myfitnesspal/feature/achievementinterstitialad/service/Optional$Some;", "T", "Lcom/myfitnesspal/feature/achievementinterstitialad/service/Optional;", "value", "(Ljava/lang/Object;)V", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AchievementAdManager.kt */
    public static final class Some<T> extends Optional<T> {
        private final T value;

        public Some(T t) {
            super(null);
            this.value = t;
        }

        public final T getValue() {
            return this.value;
        }
    }

    private Optional() {
    }

    public /* synthetic */ Optional(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
