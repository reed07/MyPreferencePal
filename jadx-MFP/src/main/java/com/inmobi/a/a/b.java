package com.inmobi.a.a;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CellTowerInfo */
public class b {
    private static final String d = "b";
    String a;
    int b;
    int c;

    public b() {
    }

    @TargetApi(18)
    public b(CellInfo cellInfo, String str, String str2, int i) {
        if (cellInfo instanceof CellInfoGsm) {
            this.c = i;
            CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
            this.b = cellInfoGsm.getCellSignalStrength().getDbm();
            CellIdentityGsm cellIdentity = cellInfoGsm.getCellIdentity();
            this.a = a(str, str2, cellIdentity.getLac(), cellIdentity.getCid(), -1, Integer.MAX_VALUE);
        } else if (cellInfo instanceof CellInfoCdma) {
            this.c = i;
            CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
            this.b = cellInfoCdma.getCellSignalStrength().getDbm();
            CellIdentityCdma cellIdentity2 = cellInfoCdma.getCellIdentity();
            this.a = a(str, cellIdentity2.getSystemId(), cellIdentity2.getNetworkId(), cellIdentity2.getBasestationId());
        } else {
            if (VERSION.SDK_INT >= 18) {
                if (cellInfo instanceof CellInfoWcdma) {
                    this.c = i;
                    CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
                    this.b = cellInfoWcdma.getCellSignalStrength().getDbm();
                    CellIdentityWcdma cellIdentity3 = cellInfoWcdma.getCellIdentity();
                    this.a = a(str, str2, cellIdentity3.getLac(), cellIdentity3.getCid(), cellIdentity3.getPsc(), Integer.MAX_VALUE);
                }
            } else if (cellInfo instanceof CellInfoLte) {
                this.c = i;
                CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
                this.b = cellInfoLte.getCellSignalStrength().getDbm();
                CellIdentityLte cellIdentity4 = cellInfoLte.getCellIdentity();
                this.a = a(str, str2, cellIdentity4.getTac(), cellIdentity4.getCi(), -1, cellIdentity4.getPci());
            }
        }
    }

    public static String a(String str, int i, int i2, int i3) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("#");
        sb.append(i);
        sb.append("#");
        sb.append(i2);
        sb.append("#");
        sb.append(i3);
        return sb.toString();
    }

    public static String a(String str, String str2, int i, int i2, int i3, int i4) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("#");
        sb.append(str2);
        sb.append("#");
        sb.append(i);
        sb.append("#");
        sb.append(i2);
        sb.append("#");
        sb.append(i3 == -1 ? "" : Integer.valueOf(i3));
        sb.append("#");
        sb.append(i4 == Integer.MAX_VALUE ? "" : Integer.valueOf(i4));
        return sb.toString();
    }

    public final JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.a);
            if (this.b != Integer.MAX_VALUE) {
                jSONObject.put("ss", this.b);
            }
            jSONObject.put("nt", this.c);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
