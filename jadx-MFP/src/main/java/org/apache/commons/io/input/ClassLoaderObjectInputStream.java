package org.apache.commons.io.input;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Proxy;

public class ClassLoaderObjectInputStream extends ObjectInputStream {
    private final ClassLoader classLoader;

    /* access modifiers changed from: protected */
    public Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
        Class<?> cls = Class.forName(objectStreamClass.getName(), false, this.classLoader);
        if (cls != null) {
            return cls;
        }
        return super.resolveClass(objectStreamClass);
    }

    /* access modifiers changed from: protected */
    public Class<?> resolveProxyClass(String[] strArr) throws IOException, ClassNotFoundException {
        Class[] clsArr = new Class[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            clsArr[i] = Class.forName(strArr[i], false, this.classLoader);
        }
        try {
            return Proxy.getProxyClass(this.classLoader, clsArr);
        } catch (IllegalArgumentException unused) {
            return super.resolveProxyClass(strArr);
        }
    }
}
