package com.samsung.android.sdk.accessory;

import android.util.Log;
import com.samsung.accessory.a.a.c;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class g {
    /* access modifiers changed from: private */
    public static final String a;
    private static Map<Object, a> b = new ConcurrentHashMap();

    static class a {
        com.samsung.accessory.a.a.a a;
        private int b;
        private int c;
        private d d;
        private Object e;

        private a(int i, int i2, Object obj) {
            this.b = i;
            this.c = i2;
            this.e = obj;
            this.d = new d(i);
        }

        /* synthetic */ a(int i, int i2, Object obj, byte b2) {
            this(i, i2, obj);
        }

        private int b(int i, byte[] bArr, int i2, int i3) {
            try {
                this.a.a(bArr, i2, i3);
                String a2 = g.a;
                StringBuilder sb = new StringBuilder(String.valueOf(String.valueOf(this.e)));
                sb.append(" : payload received [");
                sb.append(i);
                sb.append("] : ");
                sb.append(this.a.d());
                Log.d(a2, sb.toString());
                return (i == 0 || i == this.d.c()) ? 1 : 2;
            } catch (c e2) {
                String a3 = g.a;
                StringBuilder sb2 = new StringBuilder("BufferException: ");
                sb2.append(e2.getLocalizedMessage());
                Log.e(a3, sb2.toString());
                return 3;
            }
        }

        public final int a(int i, byte[] bArr, int i2, int i3) throws IOException {
            if (i == 0) {
                com.samsung.accessory.a.a.a aVar = this.a;
                if (aVar != null && aVar.d() > 0) {
                    if (this.b == 2) {
                        this.a.e();
                        this.a = null;
                        Log.w(g.a, "MsgFragment received out of order! clearing legacy buffer and accepting new...");
                    } else {
                        String a2 = g.a;
                        StringBuilder sb = new StringBuilder("Received a non-fragment in <");
                        sb.append(this.e);
                        sb.append("> while blob receive in progress...!");
                        Log.e(a2, sb.toString());
                        return 3;
                    }
                }
                this.a = a.a().b(i3);
                return b(i, bArr, i2, i3);
            } else if (i == this.d.a()) {
                if (this.b == 2) {
                    com.samsung.accessory.a.a.a aVar2 = this.a;
                    if (aVar2 != null) {
                        aVar2.e();
                        this.a = null;
                        Log.w(g.a, "MsgFragment received out of order! Clearing legacy buffer and accepting new...");
                    }
                }
                if (this.a == null) {
                    this.a = a.a().b(this.c);
                }
                return b(i, bArr, i2, i3);
            } else if (i == this.d.b() || i == this.d.c()) {
                com.samsung.accessory.a.a.a aVar3 = this.a;
                if (aVar3 != null && aVar3.d() != 0) {
                    return b(i, bArr, i2, i3);
                }
                throw new IOException("Reassembling failed, received invalid fragment!");
            } else {
                String a3 = g.a;
                StringBuilder sb2 = new StringBuilder("invalid fragment index:");
                sb2.append(i);
                sb2.append(" received in <");
                sb2.append(this.e);
                sb2.append(">! ");
                Log.e(a3, sb2.toString());
                return 3;
            }
        }
    }

    static {
        StringBuilder sb = new StringBuilder("[SA_SDK]");
        sb.append(g.class.getSimpleName());
        a = sb.toString();
    }

    g() {
    }

    private static int a(int i, Object obj, int i2, int i3, byte[] bArr, int i4, int i5) throws IOException {
        a aVar = (a) b.get(obj);
        if (aVar == null) {
            aVar = new a(i, i2, obj, 0);
            b.put(obj, aVar);
        }
        return aVar.a(i3, bArr, i4, i5);
    }

    static int a(Object obj, int i, int i2, byte[] bArr, int i3, int i4) throws IOException {
        return a(1, obj, i, i2, bArr, i3, i4);
    }

    static byte[] a(Object obj) throws IOException {
        a aVar = (a) b.get(obj);
        if (aVar == null || aVar.a == null) {
            return null;
        }
        return aVar.a.b();
    }

    static int b(Object obj, int i, int i2, byte[] bArr, int i3, int i4) throws IOException {
        return a(2, obj, i, i2, bArr, i3, i4);
    }

    static void b(Object obj) {
        a aVar = (a) b.remove(obj);
        if (aVar != null && aVar.a != null) {
            aVar.a.e();
            aVar.a = null;
        }
    }
}
