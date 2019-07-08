package com.myfitnesspal.framework.taskrunner;

public interface EventedTask {
    <T extends TaskEventBase> T getEvent();
}
