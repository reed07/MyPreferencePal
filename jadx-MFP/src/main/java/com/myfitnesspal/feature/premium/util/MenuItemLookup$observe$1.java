package com.myfitnesspal.feature.premium.util;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 13})
/* compiled from: PremiumCrownUtil.kt */
final class MenuItemLookup$observe$1 implements Runnable {
    final /* synthetic */ MenuItemLookup this$0;

    MenuItemLookup$observe$1(MenuItemLookup menuItemLookup) {
        this.this$0 = menuItemLookup;
    }

    public final void run() {
        this.this$0.notifyObserver();
    }
}
