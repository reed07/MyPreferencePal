package com.google.android.gms.internal.ads;

public abstract class zzbwg {
    public abstract void zzge(String str);

    public static zzbwg zzk(Class cls) {
        if (System.getProperty("java.vm.name").equalsIgnoreCase("Dalvik")) {
            return new zzbwb(cls.getSimpleName());
        }
        return new zzbwd(cls.getSimpleName());
    }
}
