package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzub;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

abstract class zzum<T extends zzub> {
    private static final Logger logger = Logger.getLogger(zztv.class.getName());
    private static String zzbyd = "com.google.protobuf.BlazeGeneratedExtensionRegistryLiteLoader";

    zzum() {
    }

    /* access modifiers changed from: protected */
    public abstract T zzwd();

    static <T extends zzub> T zzd(Class<T> cls) {
        String str;
        ClassLoader classLoader = zzum.class.getClassLoader();
        if (cls.equals(zzub.class)) {
            str = zzbyd;
        } else if (cls.getPackage().equals(zzum.class.getPackage())) {
            str = String.format("%s.BlazeGenerated%sLoader", new Object[]{cls.getPackage().getName(), cls.getSimpleName()});
        } else {
            throw new IllegalArgumentException(cls.getName());
        }
        try {
            return (zzub) cls.cast(((zzum) Class.forName(str, true, classLoader).getConstructor(new Class[0]).newInstance(new Object[0])).zzwd());
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException(e);
        } catch (InstantiationException e2) {
            throw new IllegalStateException(e2);
        } catch (IllegalAccessException e3) {
            throw new IllegalStateException(e3);
        } catch (InvocationTargetException e4) {
            throw new IllegalStateException(e4);
        } catch (ClassNotFoundException unused) {
            Iterator it = ServiceLoader.load(zzum.class, classLoader).iterator();
            ArrayList arrayList = new ArrayList();
            while (it.hasNext()) {
                try {
                    arrayList.add((zzub) cls.cast(((zzum) it.next()).zzwd()));
                } catch (ServiceConfigurationError e5) {
                    ServiceConfigurationError serviceConfigurationError = e5;
                    Logger logger2 = logger;
                    Level level = Level.SEVERE;
                    String str2 = "com.google.protobuf.GeneratedExtensionRegistryLoader";
                    String str3 = "load";
                    String str4 = "Unable to load ";
                    String valueOf = String.valueOf(cls.getSimpleName());
                    logger2.logp(level, str2, str3, valueOf.length() != 0 ? str4.concat(valueOf) : new String(str4), serviceConfigurationError);
                }
            }
            if (arrayList.size() == 1) {
                return (zzub) arrayList.get(0);
            }
            if (arrayList.size() == 0) {
                return null;
            }
            try {
                return (zzub) cls.getMethod("combine", new Class[]{Collection.class}).invoke(null, new Object[]{arrayList});
            } catch (NoSuchMethodException e6) {
                throw new IllegalStateException(e6);
            } catch (IllegalAccessException e7) {
                throw new IllegalStateException(e7);
            } catch (InvocationTargetException e8) {
                throw new IllegalStateException(e8);
            }
        }
    }
}
