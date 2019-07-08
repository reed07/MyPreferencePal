package com.samsung.android.sdk.accessoryfiletransfer;

import android.os.Handler;
import android.os.HandlerThread;
import com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.EventListener;
import com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.c;
import java.util.concurrent.ConcurrentHashMap;

public final class a {
    private EventListener a;
    private c b;
    private HandlerThread c;
    private Handler d;
    private ConcurrentHashMap<Integer, C0053a> e;

    /* renamed from: com.samsung.android.sdk.accessoryfiletransfer.a$a reason: collision with other inner class name */
    static class C0053a {
        int a;
        String b;
        String c;

        C0053a() {
        }
    }

    a(EventListener eventListener, HandlerThread handlerThread, Handler handler, c cVar, ConcurrentHashMap<Integer, C0053a> concurrentHashMap) {
        this.a = eventListener;
        this.c = handlerThread;
        this.d = handler;
        this.e = concurrentHashMap;
        this.b = cVar;
    }

    /* access modifiers changed from: 0000 */
    public final EventListener a() {
        return this.a;
    }

    /* access modifiers changed from: 0000 */
    public final void a(EventListener eventListener) {
        this.a = eventListener;
    }

    /* access modifiers changed from: 0000 */
    public final void a(c cVar) {
        this.b = cVar;
    }

    /* access modifiers changed from: 0000 */
    public final c b() {
        return this.b;
    }

    /* access modifiers changed from: 0000 */
    public final HandlerThread c() {
        return this.c;
    }

    /* access modifiers changed from: 0000 */
    public final Handler d() {
        return this.d;
    }

    /* access modifiers changed from: 0000 */
    public final ConcurrentHashMap<Integer, C0053a> e() {
        return this.e;
    }
}
