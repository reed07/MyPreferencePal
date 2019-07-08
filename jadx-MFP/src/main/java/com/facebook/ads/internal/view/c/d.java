package com.facebook.ads.internal.view.c;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.facebook.ads.internal.o.a;
import com.facebook.ads.internal.o.b;
import com.facebook.ads.internal.w.b.p;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.w.c.f;
import java.lang.ref.WeakReference;

public class d extends AsyncTask<String, Void, Bitmap[]> {
    private static final String b = "d";
    public boolean a = false;
    private final WeakReference<Context> c;
    private final int d;
    @Nullable
    private final WeakReference<ImageView> e;
    @Nullable
    private final WeakReference<b> f;
    @Nullable
    private final WeakReference<ViewGroup> g;
    private e h;
    private int i = -1;
    private int j = -1;

    public d(ViewGroup viewGroup, int i2) {
        this.c = new WeakReference<>(viewGroup.getContext());
        this.f = null;
        this.e = null;
        this.g = new WeakReference<>(viewGroup);
        this.d = i2;
    }

    public d(ImageView imageView) {
        this.c = new WeakReference<>(imageView.getContext());
        this.f = null;
        this.e = new WeakReference<>(imageView);
        this.g = null;
        this.d = 0;
    }

    public d(b bVar) {
        this.c = new WeakReference<>(bVar.getContext());
        this.f = new WeakReference<>(bVar);
        this.e = null;
        this.g = null;
        this.d = 0;
    }

    public d a() {
        this.i = -1;
        this.j = -1;
        return this;
    }

    public d a(int i2, int i3) {
        this.i = i2;
        this.j = i3;
        return this;
    }

    public d a(e eVar) {
        this.h = eVar;
        return this;
    }

    public d a(boolean z) {
        this.a = z;
        return this;
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            e eVar = this.h;
            if (eVar != null) {
                eVar.a(false);
            }
            return;
        }
        executeOnExecutor(p.a, new String[]{str});
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onPostExecute(Bitmap[] bitmapArr) {
        WeakReference<ImageView> weakReference = this.e;
        boolean z = false;
        if (weakReference != null) {
            ImageView imageView = (ImageView) weakReference.get();
            if (imageView != null) {
                imageView.setImageBitmap(bitmapArr[0]);
            }
        }
        WeakReference<b> weakReference2 = this.f;
        if (weakReference2 != null) {
            b bVar = (b) weakReference2.get();
            if (bVar != null) {
                bVar.a(bitmapArr[0], bitmapArr[1]);
            }
        }
        WeakReference<ViewGroup> weakReference3 = this.g;
        if (!(weakReference3 == null || weakReference3.get() == null || bitmapArr[1] == null)) {
            x.a((View) this.g.get(), (Drawable) new BitmapDrawable(((Context) this.c.get()).getResources(), bitmapArr[1]));
        }
        e eVar = this.h;
        if (eVar != null) {
            if (bitmapArr[0] != null) {
                z = true;
            }
            eVar.a(z);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Bitmap[] doInBackground(String... strArr) {
        Bitmap bitmap;
        String str = strArr[0];
        Context context = (Context) this.c.get();
        Bitmap bitmap2 = null;
        if (context == null) {
            return new Bitmap[]{null, null};
        }
        try {
            bitmap = com.facebook.ads.internal.h.d.a(context).a(str, this.i, this.j);
            try {
                boolean z = (this.f == null || this.f.get() == null) ? false : true;
                boolean z2 = (this.g == null || this.g.get() == null) ? false : true;
                if ((z || z2) && bitmap != null && !this.a) {
                    f fVar = new f(bitmap);
                    fVar.a(this.d != 0 ? this.d : Math.round(((float) bitmap.getWidth()) / 40.0f));
                    bitmap2 = fVar.a();
                }
            } catch (Throwable th) {
                th = th;
                String str2 = b;
                StringBuilder sb = new StringBuilder();
                sb.append("Error downloading image: ");
                sb.append(str);
                Log.e(str2, sb.toString(), th);
                b.a(a.a(th, null));
                return new Bitmap[]{bitmap, bitmap2};
            }
        } catch (Throwable th2) {
            th = th2;
            bitmap = null;
            String str22 = b;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Error downloading image: ");
            sb2.append(str);
            Log.e(str22, sb2.toString(), th);
            b.a(a.a(th, null));
            return new Bitmap[]{bitmap, bitmap2};
        }
        return new Bitmap[]{bitmap, bitmap2};
    }
}
