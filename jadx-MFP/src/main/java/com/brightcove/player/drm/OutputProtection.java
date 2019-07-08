package com.brightcove.player.drm;

public class OutputProtection {
    private boolean analogue;
    private boolean digital;
    private boolean enforce;

    public OutputProtection(boolean z, boolean z2, boolean z3) {
        this.digital = z;
        this.analogue = z2;
        this.enforce = z3;
    }

    public boolean isDigital() {
        return this.digital;
    }

    public boolean isAnalogue() {
        return this.analogue;
    }

    public boolean isEnforce() {
        return this.enforce;
    }
}
