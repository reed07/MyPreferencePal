package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.gson.annotations.Expose;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class MfpStepSource implements Parcelable {
    public static final Creator<MfpStepSource> CREATOR = new Creator<MfpStepSource>() {
        public MfpStepSource createFromParcel(Parcel parcel) {
            return new MfpStepSource(parcel);
        }

        public MfpStepSource[] newArray(int i) {
            return new MfpStepSource[i];
        }
    };
    @Expose
    private String appId;
    @Expose
    private String clientId;
    @Expose
    private String deviceId;
    @Expose
    private String deviceType;
    @Expose
    private boolean enabled;
    @Expose
    private boolean primary;
    @Expose
    private int stepGoal;
    @Expose
    private boolean stepGoalEditable;

    public static class LIST_MAPPER extends ArrayList<MfpStepSource> {
    }

    public int describeContents() {
        return 0;
    }

    public MfpStepSource() {
    }

    public MfpStepSource(MfpStepSource mfpStepSource) {
        this.clientId = mfpStepSource.getClientId();
        this.deviceId = mfpStepSource.getDeviceId();
        this.appId = mfpStepSource.appId;
        this.deviceType = mfpStepSource.getDeviceType();
        this.stepGoal = mfpStepSource.getStepGoal();
        this.primary = mfpStepSource.isPrimary();
        this.enabled = mfpStepSource.enabled;
        this.stepGoalEditable = mfpStepSource.isStepGoalEditable();
    }

    public MfpStepSource(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void setClientId(String str) {
        this.clientId = str;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public void setStepGoal(int i) {
        this.stepGoal = this.stepGoal;
    }

    public void setPrimary(boolean z) {
        this.primary = z;
    }

    public void setStepGoalEditable(boolean z) {
        this.stepGoalEditable = z;
    }

    public String getClientId() {
        return this.clientId;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getAppId() {
        return this.appId;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public int getStepGoal() {
        return this.stepGoal;
    }

    public boolean isPrimary() {
        return this.primary;
    }

    public boolean isStepGoalEditable() {
        return this.stepGoalEditable;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.clientId);
        parcel.writeString(this.deviceId);
        parcel.writeString(this.appId);
        parcel.writeString(this.deviceType);
        parcel.writeInt(this.stepGoal);
        parcel.writeByte(this.primary ? (byte) 1 : 0);
        parcel.writeByte(this.enabled ? (byte) 1 : 0);
        parcel.writeByte(this.stepGoalEditable ? (byte) 1 : 0);
    }

    private void readFromParcel(Parcel parcel) {
        this.clientId = parcel.readString();
        this.deviceId = parcel.readString();
        this.appId = parcel.readString();
        this.deviceType = parcel.readString();
        this.stepGoal = parcel.readInt();
        boolean z = true;
        this.primary = parcel.readByte() != 0;
        this.enabled = parcel.readByte() != 0;
        if (parcel.readByte() == 0) {
            z = false;
        }
        this.stepGoalEditable = z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0030, code lost:
        if (com.uacf.core.util.Strings.equals(r3.deviceType, r4.deviceType) != false) goto L_0x0034;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r4) {
        /*
            r3 = this;
            r0 = 1
            if (r3 != r4) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r4 instanceof com.myfitnesspal.shared.model.v2.MfpStepSource
            if (r1 == 0) goto L_0x0033
            java.lang.String r1 = r3.clientId
            com.myfitnesspal.shared.model.v2.MfpStepSource r4 = (com.myfitnesspal.shared.model.v2.MfpStepSource) r4
            java.lang.String r2 = r4.clientId
            boolean r1 = com.uacf.core.util.Strings.equals(r1, r2)
            if (r1 == 0) goto L_0x0033
            java.lang.String r1 = r3.deviceId
            java.lang.String r2 = r4.deviceId
            boolean r1 = com.uacf.core.util.Strings.equals(r1, r2)
            if (r1 == 0) goto L_0x0033
            java.lang.String r1 = r3.appId
            java.lang.String r2 = r4.appId
            boolean r1 = com.uacf.core.util.Strings.equals(r1, r2)
            if (r1 == 0) goto L_0x0033
            java.lang.String r1 = r3.deviceType
            java.lang.String r4 = r4.deviceType
            boolean r4 = com.uacf.core.util.Strings.equals(r1, r4)
            if (r4 == 0) goto L_0x0033
            goto L_0x0034
        L_0x0033:
            r0 = 0
        L_0x0034:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.model.v2.MfpStepSource.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        return Strings.toString(this.clientId).hashCode();
    }

    public static final boolean isStepsSourcesSame(@Nullable List<MfpStepSource> list, @Nullable List<MfpStepSource> list2) {
        boolean z;
        if (list == null && list2 == null) {
            return true;
        }
        boolean z2 = false;
        if (list == null || list2 == null || list.size() != list2.size()) {
            return false;
        }
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                z2 = true;
                break;
            }
            MfpStepSource mfpStepSource = (MfpStepSource) it.next();
            int indexOf = list2.indexOf(mfpStepSource);
            if (indexOf >= 0) {
                MfpStepSource mfpStepSource2 = (MfpStepSource) list2.get(indexOf);
                if (mfpStepSource2.equals(mfpStepSource) && mfpStepSource2.isStepGoalEditable() == mfpStepSource.isStepGoalEditable() && mfpStepSource2.isPrimary() == mfpStepSource.isPrimary() && mfpStepSource2.getStepGoal() == mfpStepSource.getStepGoal()) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
                if (!z) {
                    break;
                }
            } else {
                break;
            }
        }
        return z2;
    }

    public static final List<MfpStepSource> removeDuplicateStepSources(@Nullable List<MfpStepSource> list) {
        if (list == null) {
            return null;
        }
        HashSet hashSet = new HashSet();
        for (MfpStepSource add : list) {
            hashSet.add(add);
        }
        if (list.size() != hashSet.size()) {
            Ln.w("user has duplicate step sources!!!", new Object[0]);
        }
        return new ArrayList(hashSet);
    }
}
