package com.google.ads.interactivemedia.v3.internal;

import android.media.AudioTrack;
import android.os.ConditionVariable;
import android.util.Log;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

/* compiled from: IMASDK */
public final class ef implements dt {
    public static boolean a = false;
    private static boolean b = false;
    private long A;
    private long B;
    private int C;
    private int D;
    private long E;
    private float F;
    private dj[] G;
    private ByteBuffer[] H;
    private ByteBuffer I;
    private ByteBuffer J;
    private byte[] K;
    private int L;
    private int M;
    private boolean N;
    private boolean O;
    private int P;
    private ec Q;
    private boolean R;
    /* access modifiers changed from: private */
    public long S;
    private final de c;
    private final ei d;
    private final boolean e;
    private final ee f;
    private final es g;
    private final dj[] h;
    private final dj[] i;
    /* access modifiers changed from: private */
    public final ConditionVariable j;
    private final ea k;
    private final ArrayDeque<ek> l;
    /* access modifiers changed from: private */
    public dw m;
    private AudioTrack n;
    private ej o;
    private ej p;
    private AudioTrack q;
    private dc r;
    private cc s;
    private cc t;
    private long u;
    private long v;
    private ByteBuffer w;
    private int x;
    private long y;
    private long z;

    public ef(de deVar, dj[] djVarArr) {
        this(deVar, djVarArr, false);
    }

    private ef(de deVar, dj[] djVarArr, boolean z2) {
        this(deVar, new ei(djVarArr), false);
    }

    private ef(de deVar, ei eiVar, boolean z2) {
        this.c = deVar;
        this.d = (ei) qi.a(eiVar);
        this.e = z2;
        this.j = new ConditionVariable(true);
        this.k = new ea(new eb(this, 0));
        this.f = new ee();
        this.g = new es();
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, new ed[]{new eo(), this.f, this.g});
        Collections.addAll(arrayList, eiVar.a());
        this.h = (dj[]) arrayList.toArray(new dj[0]);
        this.i = new dj[]{new em()};
        this.F = 1.0f;
        this.D = 0;
        this.r = dc.a;
        this.P = 0;
        this.Q = new ec(0, BitmapDescriptorFactory.HUE_RED);
        this.t = cc.a;
        this.M = -1;
        this.G = new dj[0];
        this.H = new ByteBuffer[0];
        this.l = new ArrayDeque<>();
    }

    public final void a(dw dwVar) {
        this.m = dwVar;
    }

    public final boolean a(int i2, int i3) {
        if (vf.c(i3)) {
            return i3 != 4 || vf.a >= 21;
        }
        de deVar = this.c;
        return deVar != null && deVar.a(i3) && (i2 == -1 || i2 <= this.c.a());
    }

    public final long a(boolean z2) {
        long j2;
        if (!o() || this.D == 0) {
            return Long.MIN_VALUE;
        }
        long min = Math.min(this.k.a(z2), this.p.a(q()));
        long j3 = this.E;
        ek ekVar = null;
        while (!this.l.isEmpty() && min >= ((ek) this.l.getFirst()).c) {
            ekVar = (ek) this.l.remove();
        }
        if (ekVar != null) {
            this.t = ekVar.a;
            this.v = ekVar.c;
            this.u = ekVar.b - this.E;
        }
        if (this.t.b == 1.0f) {
            j2 = (min + this.u) - this.v;
        } else if (this.l.isEmpty()) {
            j2 = this.d.a(min - this.v) + this.u;
        } else {
            j2 = vf.a(min - this.v, this.t.b) + this.u;
        }
        return j3 + j2 + this.p.a(this.d.b());
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00cf  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0124  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(int r21, int r22, int r23, int r24, int[] r25, int r26, int r27) throws com.google.ads.interactivemedia.v3.internal.du {
        /*
            r20 = this;
            r1 = r20
            r0 = r22
            int r2 = com.google.ads.interactivemedia.v3.internal.vf.a
            r3 = 8
            r4 = 6
            r5 = 0
            r6 = 21
            if (r2 >= r6) goto L_0x001c
            if (r0 != r3) goto L_0x001c
            if (r25 != 0) goto L_0x001c
            int[] r2 = new int[r4]
            r6 = 0
        L_0x0015:
            if (r6 >= r4) goto L_0x001e
            r2[r6] = r6
            int r6 = r6 + 1
            goto L_0x0015
        L_0x001c:
            r2 = r25
        L_0x001e:
            boolean r7 = com.google.ads.interactivemedia.v3.internal.vf.c(r21)
            r6 = 4
            r15 = 1
            if (r7 == 0) goto L_0x002d
            r8 = r21
            if (r8 == r6) goto L_0x002f
            r16 = 1
            goto L_0x0031
        L_0x002d:
            r8 = r21
        L_0x002f:
            r16 = 0
        L_0x0031:
            boolean r9 = r1.e
            if (r9 == 0) goto L_0x0043
            boolean r9 = r1.a(r0, r6)
            if (r9 == 0) goto L_0x0043
            boolean r9 = com.google.ads.interactivemedia.v3.internal.vf.d(r21)
            if (r9 == 0) goto L_0x0043
            r9 = 1
            goto L_0x0044
        L_0x0043:
            r9 = 0
        L_0x0044:
            if (r9 == 0) goto L_0x004a
            com.google.ads.interactivemedia.v3.internal.dj[] r10 = r1.i
            r14 = r10
            goto L_0x004d
        L_0x004a:
            com.google.ads.interactivemedia.v3.internal.dj[] r10 = r1.h
            r14 = r10
        L_0x004d:
            if (r16 == 0) goto L_0x0096
            com.google.ads.interactivemedia.v3.internal.es r10 = r1.g
            r11 = r26
            r12 = r27
            r10.a(r11, r12)
            com.google.ads.interactivemedia.v3.internal.ee r10 = r1.f
            r10.a(r2)
            int r2 = r14.length
            r13 = r23
            r11 = r0
            r3 = r8
            r10 = 0
            r12 = 0
        L_0x0064:
            if (r10 >= r2) goto L_0x008e
            r4 = r14[r10]
            boolean r18 = r4.a(r13, r11, r3)     // Catch:{ dk -> 0x0086 }
            r12 = r12 | r18
            boolean r18 = r4.a()
            if (r18 == 0) goto L_0x0082
            int r11 = r4.b()
            int r3 = r4.d()
            int r4 = r4.c()
            r13 = r3
            r3 = r4
        L_0x0082:
            int r10 = r10 + 1
            r4 = 6
            goto L_0x0064
        L_0x0086:
            r0 = move-exception
            r2 = r0
            com.google.ads.interactivemedia.v3.internal.du r0 = new com.google.ads.interactivemedia.v3.internal.du
            r0.<init>(r2)
            throw r0
        L_0x008e:
            r2 = r12
            r19 = r13
            r13 = r3
            r3 = r11
            r11 = r19
            goto L_0x009b
        L_0x0096:
            r11 = r23
            r3 = r0
            r13 = r8
            r2 = 0
        L_0x009b:
            int r4 = com.google.ads.interactivemedia.v3.internal.vf.a
            r10 = 28
            if (r4 > r10) goto L_0x00b3
            if (r7 != 0) goto L_0x00b3
            r4 = 7
            if (r3 != r4) goto L_0x00a9
            r4 = 8
            goto L_0x00b4
        L_0x00a9:
            r4 = 3
            if (r3 == r4) goto L_0x00b1
            if (r3 == r6) goto L_0x00b1
            r4 = 5
            if (r3 != r4) goto L_0x00b3
        L_0x00b1:
            r4 = 6
            goto L_0x00b4
        L_0x00b3:
            r4 = r3
        L_0x00b4:
            int r6 = com.google.ads.interactivemedia.v3.internal.vf.a
            r10 = 26
            if (r6 > r10) goto L_0x00c9
            java.lang.String r6 = "fugu"
            java.lang.String r10 = com.google.ads.interactivemedia.v3.internal.vf.b
            boolean r6 = r6.equals(r10)
            if (r6 == 0) goto L_0x00c9
            if (r7 != 0) goto L_0x00c9
            if (r4 != r15) goto L_0x00c9
            r4 = 2
        L_0x00c9:
            int r12 = com.google.ads.interactivemedia.v3.internal.vf.e(r4)
            if (r12 == 0) goto L_0x0124
            r4 = -1
            if (r7 == 0) goto L_0x00d8
            int r0 = com.google.ads.interactivemedia.v3.internal.vf.b(r21, r22)
            r8 = r0
            goto L_0x00d9
        L_0x00d8:
            r8 = -1
        L_0x00d9:
            if (r7 == 0) goto L_0x00e1
            int r0 = com.google.ads.interactivemedia.v3.internal.vf.b(r13, r3)
            r10 = r0
            goto L_0x00e2
        L_0x00e1:
            r10 = -1
        L_0x00e2:
            if (r16 == 0) goto L_0x00e8
            if (r9 != 0) goto L_0x00e8
            r0 = 1
            goto L_0x00e9
        L_0x00e8:
            r0 = 0
        L_0x00e9:
            com.google.ads.interactivemedia.v3.internal.ej r3 = new com.google.ads.interactivemedia.v3.internal.ej
            r4 = 0
            r6 = r3
            r9 = r23
            r17 = r14
            r14 = r4
            r4 = 1
            r15 = r16
            r16 = r0
            r6.<init>(r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)
            boolean r0 = r20.o()
            if (r0 == 0) goto L_0x0121
            com.google.ads.interactivemedia.v3.internal.ej r0 = r1.p
            int r6 = r0.g
            int r7 = r3.g
            if (r6 != r7) goto L_0x0115
            int r6 = r0.e
            int r7 = r3.e
            if (r6 != r7) goto L_0x0115
            int r0 = r0.f
            int r6 = r3.f
            if (r0 != r6) goto L_0x0115
            goto L_0x0116
        L_0x0115:
            r4 = 0
        L_0x0116:
            if (r4 != 0) goto L_0x011c
            r20.i()
            goto L_0x0121
        L_0x011c:
            if (r2 == 0) goto L_0x0121
            r1.o = r3
            return
        L_0x0121:
            r1.p = r3
            return
        L_0x0124:
            com.google.ads.interactivemedia.v3.internal.du r0 = new com.google.ads.interactivemedia.v3.internal.du
            r2 = 38
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r2)
            java.lang.String r2 = "Unsupported channel count: "
            r4.append(r2)
            r4.append(r3)
            java.lang.String r2 = r4.toString()
            r0.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.ef.a(int, int, int, int, int[], int, int):void");
    }

    private final void k() {
        dj[] djVarArr = this.p.k;
        ArrayList arrayList = new ArrayList();
        for (dj djVar : djVarArr) {
            if (djVar.a()) {
                arrayList.add(djVar);
            } else {
                djVar.h();
            }
        }
        int size = arrayList.size();
        this.G = (dj[]) arrayList.toArray(new dj[size]);
        this.H = new ByteBuffer[size];
        l();
    }

    private final void l() {
        int i2 = 0;
        while (true) {
            dj[] djVarArr = this.G;
            if (i2 < djVarArr.length) {
                dj djVar = djVarArr[i2];
                djVar.h();
                this.H[i2] = djVar.f();
                i2++;
            } else {
                return;
            }
        }
    }

    public final void a() {
        this.O = true;
        if (o()) {
            this.k.a();
            this.q.play();
        }
    }

    public final void b() {
        if (this.D == 1) {
            this.D = 2;
        }
    }

    public final boolean a(ByteBuffer byteBuffer, long j2) throws dv, dx {
        int i2;
        cc ccVar;
        cc ccVar2;
        ByteBuffer byteBuffer2 = byteBuffer;
        long j3 = j2;
        ByteBuffer byteBuffer3 = this.I;
        qi.b(byteBuffer3 == null || byteBuffer2 == byteBuffer3);
        if (this.o != null) {
            if (!m()) {
                return false;
            }
            this.p = this.o;
            this.o = null;
            if (this.p.j) {
                ccVar2 = this.d.a(this.t);
            } else {
                ccVar2 = cc.a;
            }
            this.t = ccVar2;
            k();
        }
        if (!o()) {
            this.j.block();
            this.q = ((ej) qi.a(this.p)).a(this.R, this.r, this.P);
            int audioSessionId = this.q.getAudioSessionId();
            if (this.P != audioSessionId) {
                this.P = audioSessionId;
                dw dwVar = this.m;
                if (dwVar != null) {
                    dwVar.a(audioSessionId);
                }
            }
            if (this.p.j) {
                ccVar = this.d.a(this.t);
            } else {
                ccVar = cc.a;
            }
            this.t = ccVar;
            k();
            this.k.a(this.q, this.p.g, this.p.d, this.p.h);
            n();
            if (this.Q.a != 0) {
                this.q.attachAuxEffect(this.Q.a);
                this.q.setAuxEffectSendLevel(this.Q.b);
            }
            if (this.O) {
                a();
            }
        }
        if (!this.k.a(q())) {
            return false;
        }
        if (this.I == null) {
            if (!byteBuffer.hasRemaining()) {
                return true;
            }
            if (!this.p.a && this.C == 0) {
                int i3 = this.p.g;
                if (i3 == 7 || i3 == 8) {
                    i2 = el.a(byteBuffer);
                } else if (i3 == 5) {
                    i2 = da.a();
                } else if (i3 == 6) {
                    i2 = da.a(byteBuffer);
                } else if (i3 == 14) {
                    int b2 = da.b(byteBuffer);
                    if (b2 == -1) {
                        i2 = 0;
                    } else {
                        i2 = da.a(byteBuffer2, b2) << 4;
                    }
                } else {
                    StringBuilder sb = new StringBuilder(38);
                    sb.append("Unexpected audio encoding: ");
                    sb.append(i3);
                    throw new IllegalStateException(sb.toString());
                }
                this.C = i2;
                if (this.C == 0) {
                    return true;
                }
            }
            if (this.s != null) {
                if (!m()) {
                    return false;
                }
                cc ccVar3 = this.s;
                this.s = null;
                cc a2 = this.d.a(ccVar3);
                ArrayDeque<ek> arrayDeque = this.l;
                ek ekVar = new ek(a2, Math.max(0, j3), this.p.a(q()), 0);
                arrayDeque.add(ekVar);
                k();
            }
            if (this.D == 0) {
                this.E = Math.max(0, j3);
                this.D = 1;
            } else {
                long p2 = this.E + (((p() - this.g.o()) * 1000000) / ((long) this.p.c));
                if (this.D == 1 && Math.abs(p2 - j3) > 200000) {
                    StringBuilder sb2 = new StringBuilder(80);
                    sb2.append("Discontinuity detected [expected ");
                    sb2.append(p2);
                    sb2.append(", got ");
                    sb2.append(j3);
                    sb2.append("]");
                    Log.e("AudioTrack", sb2.toString());
                    this.D = 2;
                }
                if (this.D == 2) {
                    long j4 = j3 - p2;
                    this.E += j4;
                    this.D = 1;
                    dw dwVar2 = this.m;
                    if (!(dwVar2 == null || j4 == 0)) {
                        dwVar2.a();
                    }
                }
            }
            if (this.p.a) {
                this.y += (long) byteBuffer.remaining();
            } else {
                this.z += (long) this.C;
            }
            this.I = byteBuffer2;
        }
        if (this.p.i) {
            a(j3);
        } else {
            b(this.I, j3);
        }
        if (!this.I.hasRemaining()) {
            this.I = null;
            return true;
        } else if (!this.k.c(q())) {
            return false;
        } else {
            Log.w("AudioTrack", "Resetting stalled audio track");
            i();
            return true;
        }
    }

    private final void a(long j2) throws dx {
        ByteBuffer byteBuffer;
        int length = this.G.length;
        int i2 = length;
        while (i2 >= 0) {
            if (i2 > 0) {
                byteBuffer = this.H[i2 - 1];
            } else {
                byteBuffer = this.I;
                if (byteBuffer == null) {
                    byteBuffer = dj.a;
                }
            }
            if (i2 == length) {
                b(byteBuffer, j2);
            } else {
                dj djVar = this.G[i2];
                djVar.a(byteBuffer);
                ByteBuffer f2 = djVar.f();
                this.H[i2] = f2;
                if (f2.hasRemaining()) {
                    i2++;
                }
            }
            if (!byteBuffer.hasRemaining()) {
                i2--;
            } else {
                return;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00cc, code lost:
        if (r11 < r10) goto L_0x00e5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void b(java.nio.ByteBuffer r9, long r10) throws com.google.ads.interactivemedia.v3.internal.dx {
        /*
            r8 = this;
            boolean r0 = r9.hasRemaining()
            if (r0 != 0) goto L_0x0007
            return
        L_0x0007:
            java.nio.ByteBuffer r0 = r8.J
            r1 = 21
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L_0x0018
            if (r0 != r9) goto L_0x0013
            r0 = 1
            goto L_0x0014
        L_0x0013:
            r0 = 0
        L_0x0014:
            com.google.ads.interactivemedia.v3.internal.qi.b(r0)
            goto L_0x003b
        L_0x0018:
            r8.J = r9
            int r0 = com.google.ads.interactivemedia.v3.internal.vf.a
            if (r0 >= r1) goto L_0x003b
            int r0 = r9.remaining()
            byte[] r4 = r8.K
            if (r4 == 0) goto L_0x0029
            int r4 = r4.length
            if (r4 >= r0) goto L_0x002d
        L_0x0029:
            byte[] r4 = new byte[r0]
            r8.K = r4
        L_0x002d:
            int r4 = r9.position()
            byte[] r5 = r8.K
            r9.get(r5, r3, r0)
            r9.position(r4)
            r8.L = r3
        L_0x003b:
            int r0 = r9.remaining()
            int r4 = com.google.ads.interactivemedia.v3.internal.vf.a
            if (r4 >= r1) goto L_0x006c
            com.google.ads.interactivemedia.v3.internal.ea r10 = r8.k
            long r1 = r8.A
            int r10 = r10.b(r1)
            if (r10 <= 0) goto L_0x00e5
            int r10 = java.lang.Math.min(r0, r10)
            android.media.AudioTrack r11 = r8.q
            byte[] r1 = r8.K
            int r2 = r8.L
            int r3 = r11.write(r1, r2, r10)
            if (r3 <= 0) goto L_0x00e5
            int r10 = r8.L
            int r10 = r10 + r3
            r8.L = r10
            int r10 = r9.position()
            int r10 = r10 + r3
            r9.position(r10)
            goto L_0x00e5
        L_0x006c:
            boolean r1 = r8.R
            if (r1 == 0) goto L_0x00df
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r1 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r1 == 0) goto L_0x007b
            r1 = 1
            goto L_0x007c
        L_0x007b:
            r1 = 0
        L_0x007c:
            com.google.ads.interactivemedia.v3.internal.qi.c(r1)
            android.media.AudioTrack r1 = r8.q
            java.nio.ByteBuffer r4 = r8.w
            if (r4 != 0) goto L_0x009c
            r4 = 16
            java.nio.ByteBuffer r4 = java.nio.ByteBuffer.allocate(r4)
            r8.w = r4
            java.nio.ByteBuffer r4 = r8.w
            java.nio.ByteOrder r5 = java.nio.ByteOrder.BIG_ENDIAN
            r4.order(r5)
            java.nio.ByteBuffer r4 = r8.w
            r5 = 1431633921(0x55550001, float:1.46372496E13)
            r4.putInt(r5)
        L_0x009c:
            int r4 = r8.x
            if (r4 != 0) goto L_0x00b8
            java.nio.ByteBuffer r4 = r8.w
            r5 = 4
            r4.putInt(r5, r0)
            java.nio.ByteBuffer r4 = r8.w
            r5 = 8
            r6 = 1000(0x3e8, double:4.94E-321)
            long r10 = r10 * r6
            r4.putLong(r5, r10)
            java.nio.ByteBuffer r10 = r8.w
            r10.position(r3)
            r8.x = r0
        L_0x00b8:
            java.nio.ByteBuffer r10 = r8.w
            int r10 = r10.remaining()
            if (r10 <= 0) goto L_0x00cf
            java.nio.ByteBuffer r11 = r8.w
            int r11 = r1.write(r11, r10, r2)
            if (r11 >= 0) goto L_0x00cc
            r8.x = r3
            r3 = r11
            goto L_0x00e5
        L_0x00cc:
            if (r11 >= r10) goto L_0x00cf
            goto L_0x00e5
        L_0x00cf:
            int r9 = r1.write(r9, r0, r2)
            if (r9 >= 0) goto L_0x00d8
            r8.x = r3
            goto L_0x00dd
        L_0x00d8:
            int r10 = r8.x
            int r10 = r10 - r9
            r8.x = r10
        L_0x00dd:
            r3 = r9
            goto L_0x00e5
        L_0x00df:
            android.media.AudioTrack r10 = r8.q
            int r3 = r10.write(r9, r0, r2)
        L_0x00e5:
            long r9 = android.os.SystemClock.elapsedRealtime()
            r8.S = r9
            if (r3 < 0) goto L_0x010d
            com.google.ads.interactivemedia.v3.internal.ej r9 = r8.p
            boolean r9 = r9.a
            if (r9 == 0) goto L_0x00f9
            long r9 = r8.A
            long r1 = (long) r3
            long r9 = r9 + r1
            r8.A = r9
        L_0x00f9:
            if (r3 != r0) goto L_0x010c
            com.google.ads.interactivemedia.v3.internal.ej r9 = r8.p
            boolean r9 = r9.a
            if (r9 != 0) goto L_0x0109
            long r9 = r8.B
            int r11 = r8.C
            long r0 = (long) r11
            long r9 = r9 + r0
            r8.B = r9
        L_0x0109:
            r9 = 0
            r8.J = r9
        L_0x010c:
            return
        L_0x010d:
            com.google.ads.interactivemedia.v3.internal.dx r9 = new com.google.ads.interactivemedia.v3.internal.dx
            r9.<init>(r3)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.ef.b(java.nio.ByteBuffer, long):void");
    }

    public final void c() throws dx {
        if (!this.N && o() && m()) {
            this.k.d(q());
            this.q.stop();
            this.x = 0;
            this.N = true;
        }
    }

    private final boolean m() throws dx {
        boolean z2;
        if (this.M == -1) {
            this.M = this.p.i ? 0 : this.G.length;
            z2 = true;
        } else {
            z2 = false;
        }
        while (true) {
            int i2 = this.M;
            dj[] djVarArr = this.G;
            if (i2 < djVarArr.length) {
                dj djVar = djVarArr[i2];
                if (z2) {
                    djVar.e();
                }
                a(-9223372036854775807L);
                if (!djVar.g()) {
                    return false;
                }
                this.M++;
                z2 = true;
            } else {
                ByteBuffer byteBuffer = this.J;
                if (byteBuffer != null) {
                    b(byteBuffer, -9223372036854775807L);
                    if (this.J != null) {
                        return false;
                    }
                }
                this.M = -1;
                return true;
            }
        }
    }

    public final boolean d() {
        return !o() || (this.N && !e());
    }

    public final boolean e() {
        return o() && this.k.e(q());
    }

    public final cc a(cc ccVar) {
        ej ejVar = this.p;
        if (ejVar == null || ejVar.j) {
            cc ccVar2 = this.s;
            if (ccVar2 == null) {
                if (!this.l.isEmpty()) {
                    ccVar2 = ((ek) this.l.getLast()).a;
                } else {
                    ccVar2 = this.t;
                }
            }
            if (!ccVar.equals(ccVar2)) {
                if (o()) {
                    this.s = ccVar;
                } else {
                    this.t = this.d.a(ccVar);
                }
            }
            return this.t;
        }
        this.t = cc.a;
        return this.t;
    }

    public final cc f() {
        return this.t;
    }

    public final void a(dc dcVar) {
        if (!this.r.equals(dcVar)) {
            this.r = dcVar;
            if (!this.R) {
                i();
                this.P = 0;
            }
        }
    }

    public final void a(ec ecVar) {
        if (!this.Q.equals(ecVar)) {
            int i2 = ecVar.a;
            float f2 = ecVar.b;
            if (this.q != null) {
                if (this.Q.a != i2) {
                    this.q.attachAuxEffect(i2);
                }
                if (i2 != 0) {
                    this.q.setAuxEffectSendLevel(f2);
                }
            }
            this.Q = ecVar;
        }
    }

    public final void a(int i2) {
        qi.c(vf.a >= 21);
        if (!this.R || this.P != i2) {
            this.R = true;
            this.P = i2;
            i();
        }
    }

    public final void g() {
        if (this.R) {
            this.R = false;
            this.P = 0;
            i();
        }
    }

    public final void a(float f2) {
        if (this.F != f2) {
            this.F = f2;
            n();
        }
    }

    private final void n() {
        if (o()) {
            if (vf.a >= 21) {
                this.q.setVolume(this.F);
                return;
            }
            AudioTrack audioTrack = this.q;
            float f2 = this.F;
            audioTrack.setStereoVolume(f2, f2);
        }
    }

    public final void h() {
        this.O = false;
        if (o() && this.k.c()) {
            this.q.pause();
        }
    }

    public final void i() {
        if (o()) {
            this.y = 0;
            this.z = 0;
            this.A = 0;
            this.B = 0;
            this.C = 0;
            cc ccVar = this.s;
            if (ccVar != null) {
                this.t = ccVar;
                this.s = null;
            } else if (!this.l.isEmpty()) {
                this.t = ((ek) this.l.getLast()).a;
            }
            this.l.clear();
            this.u = 0;
            this.v = 0;
            this.g.n();
            l();
            this.I = null;
            this.J = null;
            this.N = false;
            this.M = -1;
            this.w = null;
            this.x = 0;
            this.D = 0;
            if (this.k.b()) {
                this.q.pause();
            }
            AudioTrack audioTrack = this.q;
            this.q = null;
            ej ejVar = this.o;
            if (ejVar != null) {
                this.p = ejVar;
                this.o = null;
            }
            this.k.d();
            this.j.close();
            new eg(this, audioTrack).start();
        }
    }

    public final void j() {
        i();
        AudioTrack audioTrack = this.n;
        if (audioTrack != null) {
            this.n = null;
            new eh(audioTrack).start();
        }
        for (dj i2 : this.h) {
            i2.i();
        }
        for (dj i3 : this.i) {
            i3.i();
        }
        this.P = 0;
        this.O = false;
    }

    private final boolean o() {
        return this.q != null;
    }

    /* access modifiers changed from: private */
    public final long p() {
        if (this.p.a) {
            return this.y / ((long) this.p.b);
        }
        return this.z;
    }

    /* access modifiers changed from: private */
    public final long q() {
        if (this.p.a) {
            return this.A / ((long) this.p.d);
        }
        return this.B;
    }

    /* access modifiers changed from: private */
    public static int c(int i2) {
        if (i2 == 14) {
            return 3062500;
        }
        switch (i2) {
            case 5:
                return 80000;
            case 6:
                return 768000;
            case 7:
                return 192000;
            case 8:
                return 2250000;
            default:
                throw new IllegalArgumentException();
        }
    }
}
