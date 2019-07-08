package com.myfitnesspal.shared.service.syncv2;

public enum SyncType {
    SignUp,
    SignIn,
    User,
    UacfUser,
    Incremental,
    Config(false),
    BackgroundIncremental,
    External,
    NoOp(false),
    FindAndCorrectFoodsWithMissingInfo;
    
    private final boolean requiresThatUserIsLoggedIn;

    private SyncType(boolean z) {
        this.requiresThatUserIsLoggedIn = z;
    }

    public boolean requiresThatUserIsLoggedIn() {
        return this.requiresThatUserIsLoggedIn;
    }
}
