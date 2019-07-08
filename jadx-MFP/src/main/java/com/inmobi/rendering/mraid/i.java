package com.inmobi.rendering.mraid;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Vibrator;
import com.google.android.gms.plus.PlusShare;
import com.inmobi.rendering.RenderView;
import com.myfitnesspal.shared.constants.Constants.Reminder;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: SystemTasksProcessor */
public class i {
    /* access modifiers changed from: private */
    public static final String b = "i";
    public RenderView a;
    private a c;

    /* compiled from: SystemTasksProcessor */
    static final class a extends Handler {
        private static final String a = "i$a";
        private WeakReference<RenderView> b;

        public a(Looper looper, RenderView renderView) {
            super(looper);
            this.b = new WeakReference<>(renderView);
        }

        public final void handleMessage(Message message) {
            if (message.what == 1) {
                String str = (String) message.obj;
                RenderView renderView = (RenderView) this.b.get();
                if (renderView != null) {
                    renderView.a(str, "broadcastEvent('vibrateComplete');");
                }
            }
        }
    }

    public i(RenderView renderView) {
        this.a = renderView;
        HandlerThread handlerThread = new HandlerThread("SystemTasksHandlerThread");
        handlerThread.start();
        this.c = new a(handlerThread.getLooper(), renderView);
    }

    public static boolean a() {
        try {
            PlusShare.class.getName();
            return true;
        } catch (NoClassDefFoundError unused) {
            return false;
        }
    }

    public static void a(Context context, int i, String str, String str2, String str3) {
        String str4;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" ");
        sb.append(str2);
        sb.append(" ");
        sb.append(str3);
        String sb2 = sb.toString();
        switch (i) {
            case 1:
                StringBuilder sb3 = new StringBuilder("https://www.facebook.com/dialog/feed?app_id=181821551957328&link=");
                sb3.append(URLEncoder.encode(str2, "UTF-8"));
                sb3.append("&picture=");
                sb3.append(URLEncoder.encode(str3, "UTF-8"));
                sb3.append("&name=&description=");
                sb3.append(URLEncoder.encode(str, "UTF-8"));
                sb3.append("&redirect_uri=");
                sb3.append(URLEncoder.encode(str2, "UTF-8"));
                str4 = sb3.toString();
                break;
            case 2:
                StringBuilder sb4 = new StringBuilder("https://m.google.com/app/plus/x/?v=compose&content=");
                sb4.append(URLEncoder.encode(sb2, "UTF-8"));
                str4 = sb4.toString();
                break;
            case 3:
                try {
                    StringBuilder sb5 = new StringBuilder("http://twitter.com/home?status=");
                    sb5.append(URLEncoder.encode(sb2, "UTF-8"));
                    str4 = sb5.toString();
                    break;
                } catch (UnsupportedEncodingException unused) {
                    return;
                }
            default:
                str4 = null;
                break;
        }
        if (str4 != null) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str4));
            try {
                com.inmobi.commons.a.a.a(context, intent);
            } catch (ActivityNotFoundException e) {
                e.getMessage();
            }
        } else {
            Intent intent2 = new Intent();
            intent2.setType("text/plain");
            intent2.putExtra("android.intent.extra.TEXT", sb2);
            try {
                com.inmobi.commons.a.a.a(context, intent2);
            } catch (ActivityNotFoundException e2) {
                e2.getMessage();
            }
        }
    }

    public final void a(Context context) {
        a aVar = this.c;
        if (aVar != null && aVar.hasMessages(1)) {
            this.c.removeMessages(1);
            ((Vibrator) context.getSystemService("vibrator")).cancel();
        }
    }

    public static String a(String str) {
        StringBuilder sb = new StringBuilder();
        if (str.length() == 0) {
            return "";
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("frequency");
            if (optString == null || "".equals(optString)) {
                return "";
            }
            if (!Reminder.DAILY_FREQUENCY.equals(optString) && !Reminder.WEEKLY_FREQUENCY.equals(optString) && !Reminder.MONTHLY_FREQUENCY.equals(optString)) {
                if (!"yearly".equals(optString)) {
                    return "";
                }
            }
            sb.append("freq=");
            sb.append(optString);
            sb.append(";");
            String optString2 = jSONObject.optString("interval");
            if (optString2 != null && !"".equals(optString2)) {
                sb.append("interval=");
                sb.append(Integer.parseInt(optString2));
                sb.append(";");
            }
            String a2 = a.a(jSONObject.optString("expires"));
            if (a2 != null) {
                sb.append("until=");
                sb.append(a2.replace("+", "Z+").replace("-", "Z-"));
                sb.append(";");
            }
            if (optString.equals(Reminder.WEEKLY_FREQUENCY)) {
                String a3 = a.a(jSONObject.optJSONArray("daysInWeek"));
                if (a3 != null) {
                    sb.append("byday=");
                    sb.append(a3);
                    sb.append(";");
                }
            }
            if (optString.equals(Reminder.MONTHLY_FREQUENCY)) {
                String a4 = a.a(jSONObject.optJSONArray("daysInMonth"), -31, 31);
                if (a4 != null) {
                    sb.append("bymonthday=");
                    sb.append(a4);
                    sb.append(";");
                }
            }
            if (optString.equals("yearly")) {
                String a5 = a.a(jSONObject.optJSONArray("daysInYear"), -366, 366);
                if (a5 != null) {
                    sb.append("byyearday=");
                    sb.append(a5);
                    sb.append(";");
                }
            }
            if (optString.equals(Reminder.MONTHLY_FREQUENCY)) {
                String a6 = a.a(jSONObject.optJSONArray("weeksInMonth"), -4, 4);
                if (a6 != null) {
                    sb.append("byweekno=");
                    sb.append(a6);
                    sb.append(";");
                }
            }
            if (optString.equals("yearly")) {
                String a7 = a.a(jSONObject.optJSONArray("monthsInYear"), 1, 12);
                if (a7 != null) {
                    sb.append("bymonth=");
                    sb.append(a7);
                    sb.append(";");
                }
            }
            return sb.toString();
        } catch (JSONException e) {
            new StringBuilder("Error Parsing recurrence string").append(e.toString());
            return "";
        }
    }
}
