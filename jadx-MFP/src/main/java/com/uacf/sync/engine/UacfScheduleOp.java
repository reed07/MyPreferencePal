package com.uacf.sync.engine;

public interface UacfScheduleOp {

    public interface Progress<ProgressType> {
        void publish(UacfScheduleProgressInfo<ProgressType> uacfScheduleProgressInfo);
    }

    public static final class Result {
        private int delayMillis;
        private UacfScheduleException exception;
        private Status status;

        private Result(Status status2, UacfScheduleException uacfScheduleException) {
            this.status = status2;
            this.exception = uacfScheduleException;
        }

        public Result withDelayMillis(int i) {
            this.delayMillis = i;
            return this;
        }

        public int getDelayMillis() {
            return this.delayMillis;
        }

        public static Result retry(UacfScheduleException uacfScheduleException) {
            return new Result(Status.Retry, uacfScheduleException);
        }

        public static Result completed() {
            return new Result(Status.Completed, null);
        }

        public static Result pending() {
            return new Result(Status.Pending, null);
        }

        public static Result yield() {
            return new Result(Status.Yield, null);
        }

        public Status getStatus() {
            return this.status;
        }

        public UacfScheduleException getException() {
            return this.exception;
        }
    }

    public enum Status {
        Completed,
        Pending,
        Yield,
        Retry
    }

    void onRetriesExhausted();

    Result sync(UacfScheduleContext uacfScheduleContext, Progress progress) throws UacfScheduleException;
}
