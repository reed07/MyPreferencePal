package com.samsung.android.sdk.internal.healthdata.query;

import android.os.Parcel;
import com.samsung.android.sdk.healthdata.HealthDataResolver.Filter;
import com.samsung.android.sdk.healthdata.HealthDataResolver.Filter.ParcelType;

public class NumberArrayFilter extends Filter {
    private String a;
    private Number[] b;

    public NumberArrayFilter(String str, Number[] numberArr) {
        this.mType = ParcelType.NUMBER_ARRAY;
        this.a = str;
        this.b = numberArr;
    }

    public NumberArrayFilter(Parcel parcel) {
        readFromParcel(parcel);
    }

    /* access modifiers changed from: protected */
    public void readFromParcel(Parcel parcel) {
        super.readFromParcel(parcel);
        this.a = parcel.readString();
        this.b = (Number[]) parcel.readSerializable();
    }

    /* JADX WARNING: type inference failed for: r2v2, types: [java.lang.Number[], java.io.Serializable] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v2, types: [java.lang.Number[], java.io.Serializable]
  assigns: [java.lang.Number[]]
  uses: [java.io.Serializable]
  mth insns count: 6
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeToParcel(android.os.Parcel r1, int r2) {
        /*
            r0 = this;
            super.writeToParcel(r1, r2)
            java.lang.String r2 = r0.a
            r1.writeString(r2)
            java.lang.Number[] r2 = r0.b
            r1.writeSerializable(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.internal.healthdata.query.NumberArrayFilter.writeToParcel(android.os.Parcel, int):void");
    }

    public String getField() {
        return this.a;
    }

    public Number[] getList() {
        return this.b;
    }
}
