package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.brightcove.player.event.AbstractEvent;
import com.facebook.ads.MediaView;
import com.facebook.ads.internal.t.f;
import com.facebook.ads.internal.t.k;
import com.facebook.ads.internal.w.b.x;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.myfitnesspal.shared.model.v2.MfpImage.Status;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class p extends b {
    private final i c;
    private k d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;
    private View j;
    @Nullable
    private f k;
    private List<View> l;
    private a m = a.ALL;
    private b n = null;
    @Nullable
    private String o;

    public enum a {
        ALL("all"),
        NONE("none"),
        MANUAL("manual");
        
        private final String d;

        private a(String str) {
            this.d = str;
        }

        public String toString() {
            return this.d;
        }
    }

    public enum b {
        AVAILABLE(Status.AVAILABLE),
        TOO_SMALL("too_small"),
        NO_NATIVE_AD_LAYOUT("no_native_ad_layout");
        
        private final String d;

        private b(String str) {
            this.d = str;
        }

        public String toString() {
            return this.d;
        }
    }

    public p(Context context, c cVar, com.facebook.ads.internal.x.a aVar, i iVar) {
        super(context, cVar, aVar);
        this.c = iVar;
    }

    private String b(View view) {
        try {
            return c(view).toString();
        } catch (JSONException unused) {
            return "Json exception";
        }
    }

    private JSONObject c(View view) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.putOpt("id", Integer.valueOf(view.getId()));
        jSONObject.putOpt("class", view.getClass());
        boolean z = true;
        jSONObject.putOpt("origin", String.format(Locale.US, "{x:%d, y:%d}", new Object[]{Integer.valueOf(view.getTop()), Integer.valueOf(view.getLeft())}));
        jSONObject.putOpt(AbstractEvent.SIZE, String.format(Locale.US, "{h:%d, w:%d}", new Object[]{Integer.valueOf(view.getHeight()), Integer.valueOf(view.getWidth())}));
        List<View> list = this.l;
        if (list == null || !list.contains(view)) {
            z = false;
        }
        jSONObject.putOpt("clickable", Boolean.valueOf(z));
        String str = "unknown";
        if (view instanceof Button) {
            str = "button";
        } else if (view instanceof TextView) {
            str = "text";
        } else if (view instanceof ImageView) {
            str = "image";
        } else if (view instanceof MediaView) {
            str = "mediaview";
        } else if (view instanceof ViewGroup) {
            str = "viewgroup";
        }
        jSONObject.putOpt("type", str);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            JSONArray jSONArray = new JSONArray();
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                jSONArray.put(c(viewGroup.getChildAt(i2)));
            }
            jSONObject.putOpt(AbstractEvent.LIST, jSONArray);
        }
        return jSONObject;
    }

    private String d(View view) {
        if (view.getWidth() <= 0 || view.getHeight() <= 0) {
            return "";
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Config.ARGB_8888);
            createBitmap.setDensity(view.getResources().getDisplayMetrics().densityDpi);
            view.draw(new Canvas(createBitmap));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            createBitmap.compress(CompressFormat.JPEG, this.c.i(), byteArrayOutputStream);
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        } catch (Exception unused) {
            return "";
        }
    }

    public void a(View view) {
        this.j = view;
    }

    public void a(a aVar) {
        this.m = aVar;
    }

    public void a(b bVar) {
        this.n = bVar;
    }

    public void a(f fVar) {
        this.k = fVar;
    }

    public void a(k kVar) {
        this.d = kVar;
    }

    public void a(@Nullable String str) {
        this.o = str;
    }

    public void a(List<View> list) {
        this.l = list;
    }

    /* access modifiers changed from: protected */
    public void a(Map<String, String> map) {
        if (this.c != null) {
            k kVar = this.d;
            if (kVar != null) {
                map.put("nti", String.valueOf(kVar.c()));
            }
            if (this.e) {
                map.put("nhs", Boolean.TRUE.toString());
            }
            if (this.f) {
                map.put("nmv", Boolean.TRUE.toString());
            }
            if (this.g) {
                map.put("nmvap", Boolean.TRUE.toString());
            }
            if (this.j != null && this.c.d()) {
                map.put(Promotion.ACTION_VIEW, b(this.j));
            }
            if (this.j != null && this.c.h()) {
                map.put("snapshot", d(this.j));
            }
            if (this.h) {
                map.put("niv", Boolean.TRUE.toString());
            }
            a aVar = this.m;
            if (aVar != null) {
                map.put("precache_media", aVar.toString());
            }
            if (this.i) {
                map.put("ucvr", Boolean.TRUE.toString());
            }
            f fVar = this.k;
            if (fVar != null) {
                map.put("namw", String.valueOf((int) (((float) fVar.getWidth()) / x.b)));
                map.put("namh", String.valueOf((int) (((float) this.k.getHeight()) / x.b)));
            }
            b bVar = this.n;
            if (bVar != null) {
                map.put("narar", bVar.toString());
            }
            String str = this.o;
            if (str != null) {
                map.put("extra_hints", str);
            }
            this.c.a(map);
        }
    }

    public void a(boolean z) {
        this.e = z;
    }

    public void b(boolean z) {
        this.f = z;
    }

    public void c(boolean z) {
        this.g = z;
    }

    public void d(boolean z) {
        this.h = z;
    }

    public void e(boolean z) {
        this.i = z;
    }
}
