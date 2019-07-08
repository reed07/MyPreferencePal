package com.myfitnesspal.shared.event;

public class InsightQuestionAnsweredEvent extends MfpEventBase {
    private final int id;

    public InsightQuestionAnsweredEvent(int i) {
        this.id = i;
    }

    public int getId() {
        return this.id;
    }
}
