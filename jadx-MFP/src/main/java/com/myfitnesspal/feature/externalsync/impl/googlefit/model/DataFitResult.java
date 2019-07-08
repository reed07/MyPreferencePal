package com.myfitnesspal.feature.externalsync.impl.googlefit.model;

public interface DataFitResult {

    public enum Type {
        FitUser,
        FitNutrition,
        FitActivity,
        FitSteps,
        FitWeight
    }

    Type getType();
}
