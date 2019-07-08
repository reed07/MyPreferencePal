package com.myfitnesspal.framework.taskrunner;

import java.lang.Throwable;

public abstract class EventedTaskBase<ResultT, ErrorT extends Throwable> extends TaskBase<ResultT, ErrorT> implements EventedTask {
    private TaskEventBase event;

    public static abstract class Unchecked<ResultT> extends EventedTaskBase<ResultT, RuntimeException> {
        public Unchecked(TaskEventBase taskEventBase) {
            super(taskEventBase);
        }

        public Unchecked(Class<? extends TaskEventBase> cls) {
            super(cls);
        }

        public Unchecked() {
        }
    }

    protected EventedTaskBase() {
    }

    protected EventedTaskBase(TaskEventBase taskEventBase) {
        this.event = taskEventBase;
    }

    protected EventedTaskBase(Class<? extends TaskEventBase> cls) {
        try {
            this.event = (TaskEventBase) cls.newInstance();
        } catch (Exception unused) {
            throw new RuntimeException("Unable to instantiate specified class type!  Please make sure it has a default constructor.");
        }
    }

    public final <T extends TaskEventBase> T getEvent() {
        return this.event;
    }
}
