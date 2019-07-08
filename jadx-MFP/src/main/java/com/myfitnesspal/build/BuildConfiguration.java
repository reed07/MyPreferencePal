package com.myfitnesspal.build;

import com.uacf.core.util.Strings;

public final class BuildConfiguration implements BuildType {
    public static final String BETA_FLAVOR = "beta";
    public static final String FASTDEV_FLAVOR = "fastdev";
    public static final String GOOGLE_FLAVOR = "google";
    public static final String QA_FLAVOR = "qa";
    private static BuildConfiguration RUNTIME;
    private final BuildFlavor buildFlavor = new BuildFlavorImpl();
    private final BuildType buildType = new BuildTypeImpl();

    public static BuildConfiguration getBuildConfiguration() {
        if (RUNTIME == null) {
            RUNTIME = new BuildConfiguration();
        }
        return RUNTIME;
    }

    public boolean isDebug() {
        return this.buildType.isDebug();
    }

    public String getBuildFlavor() {
        return this.buildFlavor.getBuildFlavor();
    }

    public boolean isQABuild() {
        return Strings.equals("qa", this.buildFlavor.getBuildFlavor());
    }

    public boolean isBetaBuild() {
        return Strings.equals("beta", this.buildFlavor.getBuildFlavor());
    }
}
