package com.myfitnesspal.feature.nutrition.event;

public class GraphSubTypeChanged {
    private int graphSubType;

    public GraphSubTypeChanged(int i) {
        this.graphSubType = i;
    }

    public int getGraphSubType() {
        return this.graphSubType;
    }
}
