package com.uacf.core.tasks;

import com.uacf.core.tasks.Task.Status;

public final class Result<T> {
    private T data;
    private Exception exception;
    private Status status;

    static <T> Result<T> success(T t) {
        return new Result<>(Status.SUCCESS, t, null);
    }

    static Result running() {
        return new Result(Status.RUNNING);
    }

    static Result cancelled() {
        return new Result(Status.CANCELLED);
    }

    static Result error(Exception exc) {
        return new Result(Status.ERROR, null, exc);
    }

    private Result(Status status2) {
        this(status2, null, null);
    }

    private Result(Status status2, T t, Exception exc) {
        this.status = status2;
        this.data = t;
        this.exception = exc;
    }

    public Status getStatus() {
        return this.status;
    }

    public T getData() {
        return this.data;
    }

    public Exception getException() {
        return this.exception;
    }
}
