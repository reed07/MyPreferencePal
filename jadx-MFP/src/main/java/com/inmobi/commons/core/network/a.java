package com.inmobi.commons.core.network;

import com.inmobi.commons.core.network.NetworkError.ErrorCode;

/* compiled from: AsyncNetworkTask */
public final class a {
    /* access modifiers changed from: private */
    public static final String a = "a";
    /* access modifiers changed from: private */
    public c b;
    /* access modifiers changed from: private */
    public C0047a c;

    /* renamed from: com.inmobi.commons.core.network.a$a reason: collision with other inner class name */
    /* compiled from: AsyncNetworkTask */
    public interface C0047a {
        void a(d dVar);

        void b(d dVar);
    }

    public a(c cVar, C0047a aVar) {
        this.b = cVar;
        this.c = aVar;
    }

    public final void a() {
        new Thread(new Runnable() {
            public final void run() {
                try {
                    d a2 = new b(a.this.b).a();
                    if (a2.a()) {
                        a.this.c.b(a2);
                    } else {
                        a.this.c.a(a2);
                    }
                } catch (Exception e) {
                    a.a;
                    new StringBuilder("Network request failed with unexpected error: ").append(e.getMessage());
                    NetworkError networkError = new NetworkError(ErrorCode.UNKNOWN_ERROR, "Network request failed with unknown error");
                    d dVar = new d();
                    dVar.b = networkError;
                    a.this.c.b(dVar);
                }
            }
        }).start();
    }
}
