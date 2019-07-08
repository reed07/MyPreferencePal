package com.samsung.android.sdk.healthdata;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import android.util.Log;
import com.samsung.android.sdk.healthdata.HealthResultHolder.BaseResult;
import com.samsung.android.sdk.internal.healthdata.ErrorUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class HealthPermissionManager {
    private final HealthDataStore a;

    public static class PermissionKey {
        private final String a;
        private final PermissionType b;

        public PermissionKey(String str, PermissionType permissionType) {
            this.a = str;
            this.b = permissionType;
        }

        public String getDataType() {
            return this.a;
        }

        public PermissionType getPermissionType() {
            return this.b;
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof PermissionKey)) {
                return false;
            }
            PermissionKey permissionKey = (PermissionKey) obj;
            String str = this.a;
            if (str != null) {
                String str2 = permissionKey.a;
                if (str2 != null && str.equals(str2) && this.b.getValue() == permissionKey.b.getValue()) {
                    return true;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            String str = this.a;
            if (str == null) {
                return 0;
            }
            return (str.hashCode() / 31) + this.b.getValue();
        }
    }

    public static class PermissionResult extends BaseResult implements Parcelable {
        public static final Creator<PermissionResult> CREATOR = new Creator<PermissionResult>() {
            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new PermissionResult[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new PermissionResult(parcel, 0);
            }
        };
        private final Bundle a;

        public int describeContents() {
            return 0;
        }

        /* synthetic */ PermissionResult(Parcel parcel, byte b) {
            this(parcel);
        }

        public PermissionResult(Bundle bundle, int i) {
            super(1, i);
            this.a = bundle;
        }

        private PermissionResult(Parcel parcel) {
            super(parcel);
            this.a = parcel.readBundle();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeBundle(this.a);
        }

        public Map<PermissionKey, Boolean> getResultMap() {
            return HealthPermissionManager.b(this.a);
        }
    }

    public enum PermissionType {
        READ(0),
        WRITE(1);
        
        private final int a;

        private PermissionType(int i) {
            this.a = i;
        }

        public final int getValue() {
            return this.a;
        }

        public static PermissionType getType(int i) {
            if (i == READ.getValue()) {
                return READ;
            }
            if (i == WRITE.getValue()) {
                return WRITE;
            }
            throw new IllegalArgumentException("Unknown input value");
        }
    }

    public HealthPermissionManager(HealthDataStore healthDataStore) {
        this.a = healthDataStore;
    }

    public HealthResultHolder<PermissionResult> requestPermissions(Set<PermissionKey> set) {
        return requestPermissions(set, null);
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x003c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.samsung.android.sdk.healthdata.HealthResultHolder<com.samsung.android.sdk.healthdata.HealthPermissionManager.PermissionResult> requestPermissions(java.util.Set<com.samsung.android.sdk.healthdata.HealthPermissionManager.PermissionKey> r5, android.app.Activity r6) {
        /*
            r4 = this;
            if (r5 == 0) goto L_0x006c
            boolean r0 = r5.isEmpty()
            if (r0 != 0) goto L_0x0064
            com.samsung.android.sdk.healthdata.HealthDataStore r0 = r4.a
            com.samsung.android.sdk.healthdata.IHealth r0 = com.samsung.android.sdk.healthdata.HealthDataStore.getInterface(r0)
            android.os.Bundle r5 = a(r5)
            java.lang.String r1 = "Health.Permission"
            java.lang.String r2 = "Trying to acquire the health data permissions..."
            android.util.Log.d(r1, r2)
            com.samsung.android.sdk.internal.healthdata.HealthResultReceiver$ForwardAsync r1 = new com.samsung.android.sdk.internal.healthdata.HealthResultReceiver$ForwardAsync     // Catch:{ RemoteException -> 0x0059 }
            r1.<init>()     // Catch:{ RemoteException -> 0x0059 }
            android.os.Looper r2 = android.os.Looper.myLooper()     // Catch:{ RemoteException -> 0x0059 }
            com.samsung.android.sdk.healthdata.HealthResultHolder r2 = com.samsung.android.sdk.internal.healthdata.IpcUtil.getAsyncResultHolder(r1, r2)     // Catch:{ RemoteException -> 0x0059 }
            com.samsung.android.sdk.healthdata.HealthDataStore r3 = r4.a     // Catch:{ RemoteException -> 0x0059 }
            android.content.Context r3 = r3.a()     // Catch:{ RemoteException -> 0x0059 }
            java.lang.String r3 = r3.getPackageName()     // Catch:{ RemoteException -> 0x0059 }
            android.content.Intent r5 = r0.requestHealthDataPermissions2(r3, r1, r5)     // Catch:{ RemoteException -> 0x0059 }
            if (r5 == 0) goto L_0x0058
            if (r6 == 0) goto L_0x0046
            r6.startActivity(r5)     // Catch:{ ActivityNotFoundException -> 0x0044, Exception -> 0x003c }
            goto L_0x0058
        L_0x003c:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException     // Catch:{ RemoteException -> 0x0059 }
            java.lang.String r6 = "Invalid instance of Activity"
            r5.<init>(r6)     // Catch:{ RemoteException -> 0x0059 }
            throw r5     // Catch:{ RemoteException -> 0x0059 }
        L_0x0044:
            r5 = move-exception
            throw r5     // Catch:{ RemoteException -> 0x0059 }
        L_0x0046:
            com.samsung.android.sdk.healthdata.HealthDataStore r6 = r4.a     // Catch:{ RemoteException -> 0x0059 }
            android.content.Context r6 = r6.a()     // Catch:{ RemoteException -> 0x0059 }
            boolean r0 = r6 instanceof android.app.Activity     // Catch:{ RemoteException -> 0x0059 }
            if (r0 != 0) goto L_0x0055
            r0 = 268435456(0x10000000, float:2.5243549E-29)
            r5.addFlags(r0)     // Catch:{ RemoteException -> 0x0059 }
        L_0x0055:
            r6.startActivity(r5)     // Catch:{ RemoteException -> 0x0059 }
        L_0x0058:
            return r2
        L_0x0059:
            r5 = move-exception
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r5 = com.samsung.android.sdk.internal.healthdata.ErrorUtil.getRemoteExceptionMessage(r5)
            r6.<init>(r5)
            throw r6
        L_0x0064:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "The input argument has no items"
            r5.<init>(r6)
            throw r5
        L_0x006c:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "The input argument is null"
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.healthdata.HealthPermissionManager.requestPermissions(java.util.Set, android.app.Activity):com.samsung.android.sdk.healthdata.HealthResultHolder");
    }

    public Map<PermissionKey, Boolean> isPermissionAcquired(Set<PermissionKey> set) {
        if (set == null) {
            throw new IllegalArgumentException("The input argument is null");
        } else if (!set.isEmpty()) {
            IHealth iHealth = HealthDataStore.getInterface(this.a);
            Bundle a2 = a(set);
            try {
                Log.d("Health.Permission", "Checking the health data permissions are acquired...");
                Bundle isHealthDataPermissionAcquired2 = iHealth.isHealthDataPermissionAcquired2(this.a.a().getPackageName(), a2);
                if (isHealthDataPermissionAcquired2 != null) {
                    return b(isHealthDataPermissionAcquired2);
                }
                throw new IllegalStateException("Binder error occurs during getting the result");
            } catch (RemoteException e) {
                throw new IllegalStateException(ErrorUtil.getRemoteExceptionMessage(e));
            }
        } else {
            throw new IllegalArgumentException("The input argument has no items");
        }
    }

    private static Bundle a(Set<PermissionKey> set) {
        HashMap hashMap = new HashMap();
        for (PermissionKey permissionKey : set) {
            String dataType = permissionKey.getDataType();
            if (dataType != null) {
                ArrayList arrayList = (ArrayList) hashMap.get(dataType);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    hashMap.put(dataType, arrayList);
                }
                arrayList.add(Integer.valueOf(permissionKey.getPermissionType().getValue()));
            } else {
                throw new IllegalArgumentException("The input argument includes null as a dataType of PermissionKey");
            }
        }
        Bundle bundle = new Bundle();
        for (Entry entry : hashMap.entrySet()) {
            ArrayList arrayList2 = (ArrayList) entry.getValue();
            int[] iArr = new int[arrayList2.size()];
            int i = 0;
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                int i2 = i + 1;
                iArr[i] = ((Integer) it.next()).intValue();
                i = i2;
            }
            bundle.putIntArray((String) entry.getKey(), iArr);
        }
        return bundle;
    }

    /* access modifiers changed from: private */
    public static Map<PermissionKey, Boolean> b(Bundle bundle) {
        PermissionType[] values;
        HashMap hashMap = new HashMap();
        for (String str : bundle.keySet()) {
            int[] intArray = bundle.getIntArray(str);
            if (intArray != null) {
                for (PermissionType permissionType : PermissionType.values()) {
                    int i = intArray[permissionType.getValue()];
                    if (i == 0) {
                        hashMap.put(new PermissionKey(str, permissionType), Boolean.FALSE);
                    } else if (i == 1) {
                        hashMap.put(new PermissionKey(str, permissionType), Boolean.TRUE);
                    }
                }
            }
        }
        return hashMap;
    }
}
