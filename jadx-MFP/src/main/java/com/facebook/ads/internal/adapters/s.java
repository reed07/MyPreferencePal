package com.facebook.ads.internal.adapters;

import com.facebook.ads.RewardData;
import com.facebook.ads.internal.protocol.AdPlacementType;

public abstract class s implements AdAdapter {
    RewardData a;
    int b;

    public abstract int a();

    public void a(int i) {
        this.b = i;
    }

    public void a(RewardData rewardData) {
        this.a = rewardData;
    }

    public abstract boolean b();

    public AdPlacementType getPlacementType() {
        return AdPlacementType.REWARDED_VIDEO;
    }
}
