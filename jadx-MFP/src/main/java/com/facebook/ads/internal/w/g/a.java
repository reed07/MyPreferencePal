package com.facebook.ads.internal.w.g;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;
import com.facebook.ads.internal.g.b;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.v.a.p;
import com.facebook.ads.internal.w.b.k;
import com.facebook.ads.internal.w.e.d;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

public class a {
    @Nullable
    private static a a;
    private final b b;
    private final C0021a c;

    /* renamed from: com.facebook.ads.internal.w.g.a$a reason: collision with other inner class name */
    private static class C0021a implements com.facebook.ads.internal.w.g.b.a {
        /* access modifiers changed from: private */
        public final com.facebook.ads.internal.v.a.a a;
        /* access modifiers changed from: private */
        public final ThreadPoolExecutor b;
        private final c c;

        public C0021a(ThreadPoolExecutor threadPoolExecutor, c cVar, Context context) {
            this.a = d.b(context);
            this.b = threadPoolExecutor;
            this.c = cVar;
        }

        static /* synthetic */ Map a(C0021a aVar, String str) {
            p pVar = new p();
            p pVar2 = new p();
            p pVar3 = new p();
            pVar.put("user_identifier", b.b);
            pVar.put("config_id", "297035420885434");
            pVar.put("category_id", "277149136230712");
            pVar.put("access_token", "693953940997901|9bf29a1f2745746a6c60d707f5bc23c2");
            StringBuilder sb = new StringBuilder();
            sb.append(System.currentTimeMillis() / 1000);
            sb.append("");
            pVar.put("client_time", sb.toString());
            String f = aVar.c.f();
            if (f != null) {
                pVar3.put("client_token", f);
            }
            pVar2.put("description", str);
            pVar2.put("misc_info", k.a(pVar3));
            pVar.b(TtmlNode.TAG_METADATA, k.a(pVar2));
            return pVar;
        }

        public void a() {
            Activity a2 = com.facebook.ads.internal.w.a.b.a();
            if (a2 != null) {
                Builder builder = new Builder(a2);
                builder.setTitle("What Happened?");
                final EditText editText = new EditText(a2);
                editText.setSingleLine(false);
                editText.setImeOptions(1073741824);
                editText.setHint("May others login as you to debug? How do you reproduce the issue?");
                editText.setMaxLines(2);
                editText.setMinLines(2);
                builder.setView(editText);
                builder.setNegativeButton("Cancel", new OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.setPositiveButton("Send Report", new OnClickListener() {
                    public void onClick(final DialogInterface dialogInterface, int i) {
                        C0021a.this.b.execute(new Runnable() {
                            public void run() {
                                String str;
                                com.facebook.ads.internal.v.a.a a2 = C0021a.this.a;
                                String urlPrefix = AdInternalSettings.getUrlPrefix();
                                if (TextUtils.isEmpty(urlPrefix)) {
                                    str = "https://graph.facebook.com/693953940997901/bugs";
                                } else {
                                    str = String.format(Locale.US, "https://graph.%s.facebook.com/693953940997901/bugs", new Object[]{urlPrefix});
                                }
                                a2.b(str, C0021a.this.a.a().a(C0021a.a(C0021a.this, editText.getText().toString())));
                                dialogInterface.cancel();
                            }
                        });
                    }
                });
                builder.create().show();
            }
        }
    }

    public a(Context context, ThreadPoolExecutor threadPoolExecutor, c cVar) {
        this.b = new b(context);
        this.c = new C0021a(threadPoolExecutor, cVar, context);
    }

    public static void a(Context context, ThreadPoolExecutor threadPoolExecutor, c cVar) {
        if (com.facebook.ads.internal.r.a.c(context) && a == null) {
            a = new a(context, threadPoolExecutor, cVar);
            a aVar = a;
            aVar.b.a(aVar.c);
        }
    }
}
