package com.inmobi.ads;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.inmobi.ads.c.l;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/* compiled from: NativeAdTracker */
public class ai {
    static final Map<Context, y> a = new WeakHashMap();
    private static final String b = "ai";
    private static final Map<Context, ce> c = new WeakHashMap();
    /* access modifiers changed from: private */
    public static final Map<View, a> d = new HashMap();
    private static final a e = new a() {
        public final void a(View view, Object obj) {
            ((ah) obj).a(view);
        }
    };
    private static final a f = new a() {
        private final Rect a = new Rect();

        public final boolean a(@Nullable View view, @Nullable View view2, int i, @NonNull Object obj) {
            if (!(obj instanceof ah) || ((ah) obj).l) {
                return false;
            }
            if (view2 instanceof NativeVideoView) {
                av mediaPlayer = ((NativeVideoView) view2).getMediaPlayer();
                if (!(mediaPlayer == null || 3 == mediaPlayer.a)) {
                    return false;
                }
            }
            if (view2 == null || view == null || !view2.isShown() || view.getParent() == null || !view2.getGlobalVisibleRect(this.a)) {
                return false;
            }
            long height = ((long) this.a.height()) * ((long) this.a.width());
            long width = ((long) view.getWidth()) * ((long) view.getHeight());
            if (width <= 0 || height * 100 < ((long) i) * width) {
                return false;
            }
            return true;
        }
    };
    private boolean g;
    private int h;

    /* compiled from: NativeAdTracker */
    public interface a {
        void a(View view, boolean z);
    }

    ai(int i) {
        this.h = i;
    }

    /* access modifiers changed from: 0000 */
    public final void a(@NonNull Context context, @NonNull ah ahVar) {
        y yVar = (y) a.get(context);
        if (yVar != null) {
            yVar.a((Object) ahVar);
            if (!(!yVar.a.isEmpty())) {
                a(context);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(@NonNull Context context) {
        y yVar = (y) a.remove(context);
        if (yVar != null) {
            yVar.c();
        }
        if ((context instanceof Activity) && VERSION.SDK_INT >= 15 && a.isEmpty() && this.g) {
            this.g = false;
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(@NonNull Context context, View view, @NonNull ah ahVar) {
        ce ceVar = (ce) c.get(context);
        if (ceVar != null) {
            ceVar.a((Object) ahVar);
            if (!(!ceVar.b.isEmpty())) {
                ce ceVar2 = (ce) c.remove(context);
                if (ceVar2 != null) {
                    ceVar2.e();
                }
                if ((context instanceof Activity) && VERSION.SDK_INT >= 15 && c.isEmpty() && this.g) {
                    this.g = false;
                }
            }
        }
        d.remove(view);
    }

    static void b(Context context) {
        y yVar = (y) a.get(context);
        if (yVar != null) {
            yVar.b();
        }
    }

    static void c(Context context) {
        y yVar = (y) a.get(context);
        if (yVar != null) {
            yVar.a();
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(@NonNull Context context, @NonNull View view, @NonNull ah ahVar, @NonNull l lVar) {
        y yVar = (y) a.get(context);
        if (yVar == null) {
            if (context instanceof Activity) {
                yVar = new y(lVar, new s(f, (Activity) context), e);
                if (VERSION.SDK_INT >= 15 && !this.g) {
                    this.g = true;
                }
            } else {
                yVar = new y(lVar, new bk(f, lVar), e);
            }
            a.put(context, yVar);
        }
        if (this.h != 0) {
            yVar.a(view, ahVar, lVar.a, lVar.b);
        } else {
            yVar.a(view, ahVar, lVar.f, lVar.g);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(@NonNull Context context, @NonNull View view, @NonNull ah ahVar, @NonNull a aVar, @NonNull l lVar) {
        ce ceVar;
        ce ceVar2 = (ce) c.get(context);
        if (ceVar2 == null) {
            boolean z = context instanceof Activity;
            if (z) {
                ceVar = new s(f, (Activity) context);
            } else {
                ceVar = new bk(f, lVar);
            }
            ceVar.c = new c() {
                public final void a(List<View> list, List<View> list2) {
                    for (View view : list) {
                        a aVar = (a) ai.d.get(view);
                        if (aVar != null) {
                            aVar.a(view, true);
                        }
                    }
                    for (View view2 : list2) {
                        a aVar2 = (a) ai.d.get(view2);
                        if (aVar2 != null) {
                            aVar2.a(view2, false);
                        }
                    }
                }
            };
            c.put(context, ceVar);
            if (z && VERSION.SDK_INT >= 15 && !this.g) {
                this.g = true;
            }
            ceVar2 = ceVar;
        }
        d.put(view, aVar);
        if (this.h != 0) {
            ceVar2.a(view, ahVar, lVar.e);
        } else {
            ceVar2.a(view, ahVar, lVar.h);
        }
    }
}
