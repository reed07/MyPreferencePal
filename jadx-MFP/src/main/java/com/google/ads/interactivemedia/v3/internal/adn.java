package com.google.ads.interactivemedia.v3.internal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import java.io.IOException;
import java.net.URL;

/* compiled from: IMASDK */
final class adn extends AsyncTask<Void, Void, Bitmap> {
    private Exception a = null;
    private final /* synthetic */ adm b;

    adn(adm adm) {
        this.b = adm;
    }

    private final Bitmap a() {
        try {
            return BitmapFactory.decodeStream(new URL(this.b.a.src()).openConnection().getInputStream());
        } catch (IOException e) {
            this.a = e;
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void onPostExecute(Object obj) {
        Bitmap bitmap = (Bitmap) obj;
        if (bitmap == null) {
            String src = this.b.a.src();
            String valueOf = String.valueOf(this.a);
            StringBuilder sb = new StringBuilder(String.valueOf(src).length() + 33 + String.valueOf(valueOf).length());
            sb.append("Loading image companion ");
            sb.append(src);
            sb.append(" failed: ");
            sb.append(valueOf);
            Log.e("IMASDK", sb.toString());
            return;
        }
        this.b.a();
        this.b.setImageBitmap(bitmap);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object doInBackground(Object[] objArr) {
        return a();
    }
}
