package com.samsung.android.sdk.accessory;

import android.util.Log;
import com.samsung.accessory.a.a.a;

final class d {
    private int a;
    private int b;
    private int c;
    private int d;
    private a e;

    d(int i) {
        switch (i) {
            case 1:
                this.a = 1;
                this.b = 1;
                this.c = 2;
                return;
            case 2:
                this.a = 1;
                this.b = 2;
                this.c = 3;
                return;
            default:
                Log.e("[SA_SDK]", "invalid fragment mode!");
                return;
        }
    }

    /* access modifiers changed from: 0000 */
    public final int a() {
        return this.a;
    }

    /* access modifiers changed from: 0000 */
    public final void a(int i) {
        this.d = i;
    }

    /* access modifiers changed from: 0000 */
    public final void a(a aVar) {
        this.e = aVar;
    }

    /* access modifiers changed from: 0000 */
    public final int b() {
        return this.b;
    }

    /* access modifiers changed from: 0000 */
    public final int c() {
        return this.c;
    }

    /* access modifiers changed from: 0000 */
    public final byte[] d() {
        return this.e.a();
    }

    /* access modifiers changed from: 0000 */
    public final int e() {
        return this.d;
    }

    /* access modifiers changed from: 0000 */
    public final int f() {
        return this.e.d();
    }

    /* access modifiers changed from: 0000 */
    public final int g() {
        return this.e.c();
    }

    /* access modifiers changed from: 0000 */
    public final void h() {
        a aVar = this.e;
        if (aVar != null) {
            aVar.e();
        }
    }
}
