package com.myfitnesspal.feature.progress.ui.viewmodel;

import com.myfitnesspal.feature.progress.constants.ArtifactType;

public class ArtifactViewModel {
    private final ArtifactType artifactType;
    private final String description;
    private final String summarySteps;
    private final String summaryStreak;
    private int summaryWeightStringId;
    private final String value;

    public ArtifactViewModel(ArtifactType artifactType2, String str, String str2) {
        this(artifactType2, str, str2, null, null, -1);
    }

    public ArtifactViewModel(ArtifactType artifactType2, String str, String str2, String str3, String str4, int i) {
        this.artifactType = artifactType2;
        this.description = str2;
        this.value = str;
        this.summarySteps = str3;
        this.summaryStreak = str4;
        this.summaryWeightStringId = i;
    }

    public ArtifactType getArtifactType() {
        return this.artifactType;
    }

    public String getDescription() {
        return this.description;
    }

    public String getSummarySteps() {
        return this.summarySteps;
    }

    public String getSummaryStreak() {
        return this.summaryStreak;
    }

    public int getSummaryWeightStringId() {
        return this.summaryWeightStringId;
    }

    public String getValue() {
        return this.value;
    }
}
