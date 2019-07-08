package com.brightcove.player.event;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.lang.Thread.State;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class BackgroundEventListener implements EventListener {
    private static final long DELAY_INTERVAL = 500;
    /* access modifiers changed from: private */
    public static final String TAG = "BackgroundEventListener";
    private Runnable backgroundCallback = new Runnable() {
        public void run() {
            Looper.prepare();
            BackgroundEventListener backgroundEventListener = BackgroundEventListener.this;
            backgroundEventListener.backgroundHandler = new BackgroundHandler(backgroundEventListener);
            Looper.loop();
        }
    };
    /* access modifiers changed from: private */
    public BackgroundHandler backgroundHandler;
    private ExecutorService executorService;

    static class BackgroundHandler extends Handler {
        public static final int MESSAGE_PROCESS_EVENT = 2;
        public static final int MESSAGE_SHUTDOWN = 1;
        private final WeakReference<BackgroundEventListener> weakReference;

        /* access modifiers changed from: private */
        public static String getMessageType(int i) {
            switch (i) {
                case 1:
                    return "MESSAGE_SHUTDOWN";
                case 2:
                    return "MESSAGE_PROCESS_EVENT";
                default:
                    return null;
            }
        }

        BackgroundHandler(BackgroundEventListener backgroundEventListener) {
            this.weakReference = new WeakReference<>(backgroundEventListener);
        }

        @TargetApi(18)
        public void handleMessage(Message message) {
            BackgroundEventListener backgroundEventListener = (BackgroundEventListener) this.weakReference.get();
            switch (message.what) {
                case 1:
                    if (VERSION.SDK_INT >= 18) {
                        getLooper().quitSafely();
                    } else {
                        getLooper().quit();
                    }
                    backgroundEventListener.stopBackgroundThread();
                    return;
                case 2:
                    backgroundEventListener.backgroundProcessEvent((Event) message.obj);
                    return;
                default:
                    return;
            }
        }

        public void sendMessageSafely(Message message) {
            Looper looper = getLooper();
            if (looper == null) {
                String access$300 = BackgroundEventListener.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("Unable to send message of type ");
                sb.append(getMessageType(message.what));
                sb.append("(");
                sb.append(message.what);
                sb.append(") safely as looper is null");
                Log.w(access$300, sb.toString());
                return;
            }
            Thread thread = looper.getThread();
            if (thread.getState() == State.RUNNABLE) {
                sendMessage(message);
            } else {
                String access$3002 = BackgroundEventListener.TAG;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Unable to send message of type ");
                sb2.append(getMessageType(message.what));
                sb2.append("(");
                sb2.append(message.what);
                sb2.append(") safely as thread's state is ");
                sb2.append(thread.getState());
                Log.w(access$3002, sb2.toString());
            }
        }
    }

    public abstract void backgroundProcessEvent(Event event);

    public BackgroundEventListener() {
        startBackgroundThread();
    }

    public final void processEvent(Event event) {
        sendMessageSafely(2, event);
    }

    public void destroyBackgroundThread() {
        sendMessageSafely(1, null);
    }

    private void sendMessageSafely(int i, Object obj) {
        startBackgroundThread();
        long currentTimeMillis = System.currentTimeMillis();
        while (this.backgroundHandler == null) {
            if (System.currentTimeMillis() - currentTimeMillis >= DELAY_INTERVAL) {
                break;
            }
        }
        BackgroundHandler backgroundHandler2 = this.backgroundHandler;
        if (backgroundHandler2 == null) {
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to send message of type ");
            sb.append(BackgroundHandler.getMessageType(i));
            sb.append("(");
            sb.append(i);
            sb.append(") because there is no background handler.");
            Log.w(str, sb.toString());
            return;
        }
        this.backgroundHandler.sendMessageSafely(backgroundHandler2.obtainMessage(i, obj));
    }

    private void startBackgroundThread() {
        ExecutorService executorService2 = this.executorService;
        if (executorService2 == null || executorService2.isShutdown()) {
            this.executorService = Executors.newSingleThreadExecutor();
            this.executorService.execute(this.backgroundCallback);
        }
    }

    /* access modifiers changed from: private */
    public void stopBackgroundThread() {
        ExecutorService executorService2 = this.executorService;
        if (executorService2 != null && !executorService2.isShutdown()) {
            this.executorService.shutdown();
        }
        this.backgroundHandler = null;
    }
}
