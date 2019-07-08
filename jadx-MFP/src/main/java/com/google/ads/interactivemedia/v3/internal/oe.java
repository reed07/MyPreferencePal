package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: IMASDK */
final class oe implements tv<Long> {
    private static final Pattern a = Pattern.compile("(.+?)(Z|((\\+|-|−)(\\d\\d)(:?(\\d\\d))?))");

    oe() {
    }

    private static Long a(InputStream inputStream) throws IOException {
        String readLine = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8"))).readLine();
        try {
            Matcher matcher = a.matcher(readLine);
            if (!matcher.matches()) {
                String str = "Couldn't parse timestamp: ";
                String valueOf = String.valueOf(readLine);
                throw new ca(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
            String group = matcher.group(1);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            long time = simpleDateFormat.parse(group).getTime();
            if (!"Z".equals(matcher.group(2))) {
                long j = "+".equals(matcher.group(4)) ? 1 : -1;
                long parseLong = Long.parseLong(matcher.group(5));
                String group2 = matcher.group(7);
                time -= j * ((((parseLong * 60) + (TextUtils.isEmpty(group2) ? 0 : Long.parseLong(group2))) * 60) * 1000);
            }
            return Long.valueOf(time);
        } catch (ParseException e) {
            throw new ca((Throwable) e);
        }
    }

    public final /* bridge */ /* synthetic */ Object a(Uri uri, InputStream inputStream) throws IOException {
        return a(inputStream);
    }
}
