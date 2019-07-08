package com.myfitnesspal.feature.progress.event;

import com.myfitnesspal.feature.progress.constants.GraphPeriod;

public class GraphPeriodChangeEvent {
    private GraphPeriod graphPeriod;

    public GraphPeriodChangeEvent(GraphPeriod graphPeriod2) {
        this.graphPeriod = graphPeriod2;
    }

    public GraphPeriod getGraphPeriod() {
        return this.graphPeriod;
    }
}
