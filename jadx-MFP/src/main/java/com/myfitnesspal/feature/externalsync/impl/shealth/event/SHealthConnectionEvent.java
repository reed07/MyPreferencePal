package com.myfitnesspal.feature.externalsync.impl.shealth.event;

import com.myfitnesspal.shared.event.ConsumableEvent;

public class SHealthConnectionEvent extends ConsumableEvent {
    private final boolean connected;
    private final boolean justPaired;

    public SHealthConnectionEvent(boolean z) {
        this.connected = z;
        this.justPaired = false;
    }

    public SHealthConnectionEvent(boolean z, boolean z2) {
        this.connected = z;
        this.justPaired = z2;
    }

    public boolean isConnected() {
        return this.connected;
    }

    public boolean isJustPaired() {
        return this.justPaired;
    }
}
