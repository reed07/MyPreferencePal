package com.samsung.android.sdk.accessoryfiletransfer;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;
import com.samsung.accessory.safiletransfer.a.d;
import com.samsung.accessory.safiletransfer.a.e;
import com.samsung.accessory.safiletransfer.a.f;
import com.samsung.accessory.safiletransfer.a.g;
import com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.c;
import org.json.JSONException;

class SAFileTransferCallbackReceiver extends ResultReceiver {
    private int a;
    private int[] b;
    private String c;
    private String d;
    private c e;

    public SAFileTransferCallbackReceiver(Handler handler, c cVar) {
        super(handler);
        this.e = cVar;
    }

    /* access modifiers changed from: protected */
    public void onReceiveResult(int i, Bundle bundle) {
        c cVar;
        int i2;
        String str;
        String string = bundle.getString("CallBackJson");
        if (string != null) {
            switch (i) {
                case 100:
                    g gVar = new g();
                    try {
                        gVar.a(string);
                        this.a = gVar.a();
                        this.e.a(this.a, (int) gVar.b());
                        return;
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                        return;
                    }
                case 101:
                    f fVar = new f();
                    try {
                        fVar.a(string);
                        Log.i("[SA_SDK]SAFileTransferCallbackReceiver", "Transfer Complete");
                        this.a = fVar.a();
                        this.c = fVar.b();
                        this.d = fVar.c();
                        if (this.d.length() == 0) {
                            cVar = this.e;
                            i2 = this.a;
                            str = this.c;
                        } else {
                            cVar = this.e;
                            i2 = this.a;
                            str = this.d;
                        }
                        cVar.a(i2, str, 0);
                        this.a = -1;
                        return;
                    } catch (JSONException e3) {
                        e3.printStackTrace();
                        return;
                    }
                case 102:
                    Log.e("[SA_SDK]SAFileTransferCallbackReceiver", "FT Error");
                    d dVar = new d();
                    try {
                        dVar.a(string);
                        this.a = dVar.a();
                        this.e.a(this.a, null, dVar.b());
                    } catch (JSONException e4) {
                        e4.printStackTrace();
                    }
                    this.a = -1;
                    this.c = null;
                    this.d = null;
                    return;
                case 103:
                    Log.e("[SA_SDK]SAFileTransferCallbackReceiver", "FT Error");
                    e eVar = new e();
                    try {
                        eVar.a(string);
                        this.b = eVar.a();
                        this.e.a(this.b, eVar.b());
                    } catch (JSONException e5) {
                        e5.printStackTrace();
                    }
                    this.a = -1;
                    this.c = null;
                    this.d = null;
                    return;
                default:
                    Log.e("[SA_SDK]SAFileTransferCallbackReceiver", "Wrong resultCode");
                    return;
            }
        }
    }
}
