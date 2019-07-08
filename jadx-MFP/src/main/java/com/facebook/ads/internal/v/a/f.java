package com.facebook.ads.internal.v.a;

import com.brightcove.player.C;
import com.google.common.net.HttpHeaders;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class f implements q {
    private final r a;

    public f() {
        this(new g());
    }

    public f(r rVar) {
        this.a = rVar;
    }

    public OutputStream a(HttpURLConnection httpURLConnection) {
        return httpURLConnection.getOutputStream();
    }

    public HttpURLConnection a(String str) {
        return (HttpURLConnection) new URL(str).openConnection();
    }

    public void a(OutputStream outputStream, byte[] bArr) {
        outputStream.write(bArr);
    }

    public void a(HttpURLConnection httpURLConnection, j jVar, String str) {
        httpURLConnection.setRequestMethod(jVar.c());
        httpURLConnection.setDoOutput(jVar.b());
        httpURLConnection.setDoInput(jVar.a());
        if (str != null) {
            httpURLConnection.setRequestProperty("Content-Type", str);
        }
        httpURLConnection.setRequestProperty(HttpHeaders.ACCEPT_CHARSET, "UTF-8");
    }

    public boolean a(m mVar) {
        n a2 = mVar.a();
        if (this.a.a()) {
            this.a.a("BasicRequestHandler.onError got");
            mVar.printStackTrace();
        }
        return a2 != null && a2.a() > 0;
    }

    public byte[] a(InputStream inputStream) {
        byte[] bArr = new byte[C.DASH_ROLE_CAPTION_FLAG];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byteArrayOutputStream.flush();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    public InputStream b(HttpURLConnection httpURLConnection) {
        return httpURLConnection.getInputStream();
    }
}
