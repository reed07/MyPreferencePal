package com.facebook.ads.internal.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.o.d;
import com.facebook.ads.internal.view.a.b;

public interface a {

    /* renamed from: com.facebook.ads.internal.view.a$a reason: collision with other inner class name */
    public interface C0012a {
        void a(View view);

        void a(View view, int i);

        void a(String str);

        void a(String str, d dVar);

        void a(String str, boolean z, @Nullable b bVar);
    }

    void a(Intent intent, @Nullable Bundle bundle, AudienceNetworkActivity audienceNetworkActivity);

    void a(Bundle bundle);

    void a_(boolean z);

    void b(boolean z);

    void onDestroy();

    void setListener(C0012a aVar);
}
