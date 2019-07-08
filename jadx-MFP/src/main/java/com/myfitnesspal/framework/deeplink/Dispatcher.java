package com.myfitnesspal.framework.deeplink;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.exoplayer2.C;
import com.myfitnesspal.feature.home.ui.activity.HomeActivity;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.UriUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Dispatcher {
    private Bundle bundle = new Bundle();
    private final Context context;
    private Intent intent;

    public Dispatcher(Context context2) {
        this.context = context2;
    }

    public Context getContext() {
        return this.context;
    }

    public Dispatcher withIntent(Intent intent2) {
        this.intent = intent2;
        return this;
    }

    public Dispatcher withExtras(Bundle bundle2) {
        if (bundle2 != null) {
            this.bundle.putAll(bundle2);
        }
        return this;
    }

    public Dispatcher withExtra(String str, String str2) {
        this.bundle.putString(str, str2);
        return this;
    }

    public Dispatcher withExtra(String str, int i) {
        this.bundle.putInt(str, i);
        return this;
    }

    public void dispatch(Uri uri) {
        dispatch(this.context, uri);
    }

    public void dispatch(Context context2, Uri uri) {
        boolean z;
        if (this.intent != null) {
            HashMap hashMap = new HashMap();
            if (Strings.notEmpty(uri.getQuery())) {
                z = false;
                for (String str : UriUtils.getQueryParameterNames(uri)) {
                    String queryParameter = uri.getQueryParameter(str);
                    if (Strings.equalsIgnoreCase("withinApp", str) || Strings.equalsIgnoreCase(Extras.DISABLE_NEW_TASK, str)) {
                        hashMap.put("withinApp", Arrays.asList(new String[]{String.valueOf(true)}));
                        z = true;
                    } else if (hashMap.containsKey(str)) {
                        ((List) hashMap.get(str)).add(queryParameter);
                    } else {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(queryParameter);
                        hashMap.put(str, arrayList);
                    }
                }
            } else {
                z = false;
            }
            for (String str2 : hashMap.keySet()) {
                List list = (List) hashMap.get(str2);
                if (CollectionUtils.size((Collection<?>) list) == 1) {
                    this.bundle.putString(str2, (String) list.get(0));
                } else {
                    this.bundle.putStringArrayList(str2, (ArrayList) list);
                }
            }
            if (this.intent.getComponent() != null) {
                this.bundle.putString(Extras.DEEP_LINK_DESTINATION, this.intent.getComponent().getClassName());
            }
            this.intent.putExtras(this.bundle);
            this.intent.setData(uri);
            boolean z2 = this.bundle.getBoolean(Extras.DISABLE_NEW_TASK, false) | z | this.bundle.getBoolean("withinApp", false);
            if (!(context2 instanceof Activity) || !z2) {
                this.intent.addFlags(C.ENCODING_PCM_MU_LAW);
            }
            startActivity(context2, this.intent, z2);
            return;
        }
        throw new IllegalStateException("Intent cannot be null.");
    }

    /* access modifiers changed from: protected */
    public void startActivity(Context context2, Intent intent2, boolean z) {
        if (!z) {
            Intent newStartIntent = HomeActivity.newStartIntent(context2);
            newStartIntent.addFlags(872415232);
            context2.startActivity(newStartIntent);
        }
        context2.startActivity(intent2);
    }
}
