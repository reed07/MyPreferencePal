package com.uacf.taskrunner;

import android.content.Context;
import java.util.concurrent.CountDownLatch;

public class Tasks {

    public static abstract class AsyncWait<ResultT, ErrorT extends Throwable> implements Task<ResultT, ErrorT> {
        /* access modifiers changed from: private */
        public ErrorT error;
        /* access modifiers changed from: private */
        public ResultT result;

        public interface Completion<ResultT, ErrorT> {
            void complete();

            void setResult(ResultT resultt);
        }

        public static abstract class Unchecked<ResultT> extends AsyncWait<ResultT, RuntimeException> {
        }

        public abstract void exec(Context context, Completion<ResultT, ErrorT> completion) throws Throwable;

        public ResultT get() throws Throwable {
            ResultT resultt;
            synchronized (this) {
                if (this.result != null) {
                    resultt = this.result;
                } else if (this.error == null) {
                    throw new NotCompletedException();
                } else {
                    throw this.error;
                }
            }
            return resultt;
        }

        public final ResultT run(Context context) throws Throwable {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            exec(context, new Completion<ResultT, ErrorT>() {
                public void setResult(ResultT resultt) {
                    synchronized (AsyncWait.this) {
                        AsyncWait.this.result = resultt;
                    }
                }

                public void complete() {
                    countDownLatch.countDown();
                }
            });
            try {
                countDownLatch.await();
            } catch (InterruptedException unused) {
            }
            return get();
        }
    }

    public static abstract class Blocking<ResultT, ErrorT extends Throwable> implements Task<ResultT, ErrorT> {
        private ResultT result;
        private final Object resultMonitor = new Object();

        public static abstract class Unchecked<ResultT> extends Blocking<ResultT, RuntimeException> {
        }

        /* access modifiers changed from: protected */
        public abstract ResultT exec(Context context) throws Throwable;

        public final ResultT run(Context context) throws Throwable {
            synchronized (this) {
                synchronized (this.resultMonitor) {
                    if (this.result != null) {
                        ResultT resultt = this.result;
                        return resultt;
                    }
                    ResultT exec = exec(context);
                    synchronized (this.resultMonitor) {
                        this.result = exec;
                    }
                    return this.result;
                }
            }
        }

        public final ResultT get() throws Throwable, NotCompletedException {
            ResultT resultt;
            synchronized (this.resultMonitor) {
                if (this.result != null) {
                    resultt = this.result;
                } else {
                    throw new NotCompletedException();
                }
            }
            return resultt;
        }
    }
}
