package com.samsung.android.sdk.accessory;

import android.util.Log;
import com.samsung.accessory.a.a.a;
import com.samsung.accessory.a.a.c;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class e {
    private static final String a;
    private static Map<Object, e> b = new ConcurrentHashMap();
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private byte[] h;
    private d i;
    private Object j;

    static {
        StringBuilder sb = new StringBuilder("[SA_SDK]");
        sb.append(e.class.getSimpleName());
        a = sb.toString();
    }

    e(int i2, Object obj) {
        this.j = obj;
        this.i = new d(i2);
    }

    /* access modifiers changed from: 0000 */
    public final d a() throws IOException {
        d dVar;
        int c2;
        byte[] bArr = this.h;
        if (bArr != null) {
            int i2 = this.c;
            if (i2 == bArr.length) {
                return null;
            }
            int length = bArr.length;
            int i3 = this.d;
            if (length <= i3) {
                i3 = bArr.length;
                dVar = this.i;
                c2 = 0;
            } else if (i2 == 0) {
                dVar = this.i;
                c2 = dVar.a();
            } else if (bArr.length - i2 > i3) {
                dVar = this.i;
                c2 = dVar.b();
            } else {
                i3 = bArr.length - i2;
                dVar = this.i;
                c2 = dVar.c();
            }
            dVar.a(c2);
            a b2 = a.a().b(this.e + i3 + this.g + this.f);
            b2.a(this.e);
            try {
                b2.a(this.h, this.c, i3);
                this.i.a(b2);
                this.c += i3;
                return this.i;
            } catch (c e2) {
                String str = a;
                StringBuilder sb = new StringBuilder("BufferException: ");
                sb.append(e2.getLocalizedMessage());
                Log.e(str, sb.toString());
                return null;
            }
        } else {
            throw new IOException("Send Failed! Fragmenter is already shutdown");
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(int i2, int i3, int i4, int i5, byte[] bArr) throws IOException {
        if (!b.containsKey(this.j)) {
            this.e = i2;
            this.f = i3;
            this.g = i5;
            this.d = i4 - i5;
            this.h = bArr;
            b.put(this.j, this);
            String str = a;
            StringBuilder sb = new StringBuilder("confiure: ");
            sb.append(i2);
            sb.append(" ");
            sb.append(i3);
            sb.append(" ");
            sb.append(i4);
            sb.append(" ");
            sb.append(i5);
            sb.append(" ");
            sb.append(bArr.length);
            Log.v(str, sb.toString());
            return;
        }
        StringBuilder sb2 = new StringBuilder("Concurrent write detected! Another write active: ");
        sb2.append(this.j);
        throw new IOException(sb2.toString());
    }

    /* access modifiers changed from: 0000 */
    public final d b() {
        return this.i;
    }

    /* access modifiers changed from: 0000 */
    public final int c() {
        return this.c;
    }

    /* access modifiers changed from: 0000 */
    public final void d() {
        d dVar = this.i;
        if (dVar != null) {
            dVar.h();
        }
        e eVar = (e) b.get(this.j);
        if (eVar != null && eVar.equals(this)) {
            b.remove(this.j);
        }
        this.h = null;
    }
}
