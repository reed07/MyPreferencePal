package com.squareup.okhttp.internal;

import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.squareup.okhttp.Protocol;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.net.ssl.SSLSocket;
import okio.Buffer;

public class Platform {
    private static final Platform PLATFORM = findPlatform();

    private static class Android extends Platform {
        private final OptionalMethod<Socket> getAlpnSelectedProtocol;
        private final OptionalMethod<Socket> setAlpnProtocols;
        private final OptionalMethod<Socket> setHostname;
        private final OptionalMethod<Socket> setUseSessionTickets;
        private final Method trafficStatsTagSocket;
        private final Method trafficStatsUntagSocket;

        public Android(OptionalMethod<Socket> optionalMethod, OptionalMethod<Socket> optionalMethod2, Method method, Method method2, OptionalMethod<Socket> optionalMethod3, OptionalMethod<Socket> optionalMethod4) {
            this.setUseSessionTickets = optionalMethod;
            this.setHostname = optionalMethod2;
            this.trafficStatsTagSocket = method;
            this.trafficStatsUntagSocket = method2;
            this.getAlpnSelectedProtocol = optionalMethod3;
            this.setAlpnProtocols = optionalMethod4;
        }

        public void connectSocket(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
            try {
                socket.connect(inetSocketAddress, i);
            } catch (SecurityException e) {
                IOException iOException = new IOException("Exception in connect");
                iOException.initCause(e);
                throw iOException;
            }
        }

        public void configureTlsExtensions(SSLSocket sSLSocket, String str, List<Protocol> list) {
            if (str != null) {
                this.setUseSessionTickets.invokeOptionalWithoutCheckedException(sSLSocket, Boolean.valueOf(true));
                this.setHostname.invokeOptionalWithoutCheckedException(sSLSocket, str);
            }
            OptionalMethod<Socket> optionalMethod = this.setAlpnProtocols;
            if (optionalMethod != null && optionalMethod.isSupported(sSLSocket)) {
                this.setAlpnProtocols.invokeWithoutCheckedException(sSLSocket, concatLengthPrefixed(list));
            }
        }

        public String getSelectedProtocol(SSLSocket sSLSocket) {
            OptionalMethod<Socket> optionalMethod = this.getAlpnSelectedProtocol;
            String str = null;
            if (optionalMethod == null || !optionalMethod.isSupported(sSLSocket)) {
                return null;
            }
            byte[] bArr = (byte[]) this.getAlpnSelectedProtocol.invokeWithoutCheckedException(sSLSocket, new Object[0]);
            if (bArr != null) {
                str = new String(bArr, Util.UTF_8);
            }
            return str;
        }

        public void tagSocket(Socket socket) throws SocketException {
            Method method = this.trafficStatsTagSocket;
            if (method != null) {
                try {
                    method.invoke(null, new Object[]{socket});
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e2) {
                    throw new RuntimeException(e2.getCause());
                }
            }
        }

        public void untagSocket(Socket socket) throws SocketException {
            Method method = this.trafficStatsUntagSocket;
            if (method != null) {
                try {
                    method.invoke(null, new Object[]{socket});
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e2) {
                    throw new RuntimeException(e2.getCause());
                }
            }
        }
    }

    private static class JdkWithJettyBootPlatform extends Platform {
        private final Class<?> clientProviderClass;
        private final Method getMethod;
        private final Method putMethod;
        private final Method removeMethod;
        private final Class<?> serverProviderClass;

        public JdkWithJettyBootPlatform(Method method, Method method2, Method method3, Class<?> cls, Class<?> cls2) {
            this.putMethod = method;
            this.getMethod = method2;
            this.removeMethod = method3;
            this.clientProviderClass = cls;
            this.serverProviderClass = cls2;
        }

        public void configureTlsExtensions(SSLSocket sSLSocket, String str, List<Protocol> list) {
            ArrayList arrayList = new ArrayList(list.size());
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Protocol protocol = (Protocol) list.get(i);
                if (protocol != Protocol.HTTP_1_0) {
                    arrayList.add(protocol.toString());
                }
            }
            try {
                Object newProxyInstance = Proxy.newProxyInstance(Platform.class.getClassLoader(), new Class[]{this.clientProviderClass, this.serverProviderClass}, new JettyNegoProvider(arrayList));
                this.putMethod.invoke(null, new Object[]{sSLSocket, newProxyInstance});
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new AssertionError(e);
            }
        }

        public void afterHandshake(SSLSocket sSLSocket) {
            try {
                this.removeMethod.invoke(null, new Object[]{sSLSocket});
            } catch (IllegalAccessException | InvocationTargetException unused) {
                throw new AssertionError();
            }
        }

        public String getSelectedProtocol(SSLSocket sSLSocket) {
            try {
                Object[] objArr = {sSLSocket};
                String str = null;
                JettyNegoProvider jettyNegoProvider = (JettyNegoProvider) Proxy.getInvocationHandler(this.getMethod.invoke(null, objArr));
                if (jettyNegoProvider.unsupported || jettyNegoProvider.selected != null) {
                    if (!jettyNegoProvider.unsupported) {
                        str = jettyNegoProvider.selected;
                    }
                    return str;
                }
                Internal.logger.log(Level.INFO, "ALPN callback dropped: SPDY and HTTP/2 are disabled. Is alpn-boot on the boot class path?");
                return null;
            } catch (IllegalAccessException | InvocationTargetException unused) {
                throw new AssertionError();
            }
        }
    }

    private static class JettyNegoProvider implements InvocationHandler {
        private final List<String> protocols;
        /* access modifiers changed from: private */
        public String selected;
        /* access modifiers changed from: private */
        public boolean unsupported;

        public JettyNegoProvider(List<String> list) {
            this.protocols = list;
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            String name = method.getName();
            Class<String> returnType = method.getReturnType();
            if (objArr == null) {
                objArr = Util.EMPTY_STRING_ARRAY;
            }
            if (name.equals("supports") && Boolean.TYPE == returnType) {
                return Boolean.valueOf(true);
            }
            if (name.equals("unsupported") && Void.TYPE == returnType) {
                this.unsupported = true;
                return null;
            } else if (name.equals("protocols") && objArr.length == 0) {
                return this.protocols;
            } else {
                if ((name.equals("selectProtocol") || name.equals(Attributes.SELECT)) && String.class == returnType && objArr.length == 1 && (objArr[0] instanceof List)) {
                    List list = (List) objArr[0];
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        if (this.protocols.contains(list.get(i))) {
                            String str = (String) list.get(i);
                            this.selected = str;
                            return str;
                        }
                    }
                    String str2 = (String) this.protocols.get(0);
                    this.selected = str2;
                    return str2;
                } else if ((!name.equals("protocolSelected") && !name.equals("selected")) || objArr.length != 1) {
                    return method.invoke(this, objArr);
                } else {
                    this.selected = (String) objArr[0];
                    return null;
                }
            }
        }
    }

    public void afterHandshake(SSLSocket sSLSocket) {
    }

    public void configureTlsExtensions(SSLSocket sSLSocket, String str, List<Protocol> list) {
    }

    public String getPrefix() {
        return "OkHttp";
    }

    public String getSelectedProtocol(SSLSocket sSLSocket) {
        return null;
    }

    public void tagSocket(Socket socket) throws SocketException {
    }

    public void untagSocket(Socket socket) throws SocketException {
    }

    public static Platform get() {
        return PLATFORM;
    }

    public void logW(String str) {
        System.out.println(str);
    }

    public void connectSocket(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
        socket.connect(inetSocketAddress, i);
    }

    private static Platform findPlatform() {
        OptionalMethod optionalMethod;
        OptionalMethod optionalMethod2;
        Method method;
        Method method2;
        OptionalMethod optionalMethod3;
        Method method3;
        try {
            Class.forName("com.android.org.conscrypt.OpenSSLSocketImpl");
        } catch (ClassNotFoundException unused) {
            try {
                Class.forName("org.apache.harmony.xnet.provider.jsse.OpenSSLSocketImpl");
            } catch (ClassNotFoundException unused2) {
                String str = "org.eclipse.jetty.alpn.ALPN";
                try {
                    Class cls = Class.forName(str);
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    sb.append("$Provider");
                    Class cls2 = Class.forName(sb.toString());
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str);
                    sb2.append("$ClientProvider");
                    Class cls3 = Class.forName(sb2.toString());
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(str);
                    sb3.append("$ServerProvider");
                    Class cls4 = Class.forName(sb3.toString());
                    JdkWithJettyBootPlatform jdkWithJettyBootPlatform = new JdkWithJettyBootPlatform(cls.getMethod("put", new Class[]{SSLSocket.class, cls2}), cls.getMethod("get", new Class[]{SSLSocket.class}), cls.getMethod(ProductAction.ACTION_REMOVE, new Class[]{SSLSocket.class}), cls3, cls4);
                    return jdkWithJettyBootPlatform;
                } catch (ClassNotFoundException | NoSuchMethodException unused3) {
                    return new Platform();
                }
            }
        }
        OptionalMethod optionalMethod4 = null;
        OptionalMethod optionalMethod5 = new OptionalMethod(null, "setUseSessionTickets", Boolean.TYPE);
        OptionalMethod optionalMethod6 = new OptionalMethod(null, "setHostname", String.class);
        try {
            Class cls5 = Class.forName("android.net.TrafficStats");
            method2 = cls5.getMethod("tagSocket", new Class[]{Socket.class});
            try {
                method3 = cls5.getMethod("untagSocket", new Class[]{Socket.class});
                optionalMethod = optionalMethod4;
                optionalMethod2 = optionalMethod3;
                method = method3;
            } catch (ClassNotFoundException | NoSuchMethodException unused4) {
                method3 = null;
                optionalMethod3 = null;
                optionalMethod = null;
                optionalMethod2 = optionalMethod3;
                method = method3;
                Android android2 = new Android(optionalMethod5, optionalMethod6, method2, method, optionalMethod2, optionalMethod);
                return android2;
            }
            try {
                Class.forName("android.net.Network");
                optionalMethod3 = new OptionalMethod(byte[].class, "getAlpnSelectedProtocol", new Class[0]);
            } catch (ClassNotFoundException unused5) {
                optionalMethod3 = null;
            } catch (NoSuchMethodException unused6) {
                optionalMethod3 = null;
                optionalMethod = null;
                optionalMethod2 = optionalMethod3;
                method = method3;
                Android android22 = new Android(optionalMethod5, optionalMethod6, method2, method, optionalMethod2, optionalMethod);
                return android22;
            }
            try {
                optionalMethod4 = new OptionalMethod(null, "setAlpnProtocols", byte[].class);
            } catch (ClassNotFoundException unused7) {
            } catch (NoSuchMethodException unused8) {
                optionalMethod = null;
                optionalMethod2 = optionalMethod3;
                method = method3;
                Android android222 = new Android(optionalMethod5, optionalMethod6, method2, method, optionalMethod2, optionalMethod);
                return android222;
            }
        } catch (ClassNotFoundException | NoSuchMethodException unused9) {
            method3 = null;
            method2 = null;
            optionalMethod3 = null;
            optionalMethod = null;
            optionalMethod2 = optionalMethod3;
            method = method3;
            Android android2222 = new Android(optionalMethod5, optionalMethod6, method2, method, optionalMethod2, optionalMethod);
            return android2222;
        }
        Android android22222 = new Android(optionalMethod5, optionalMethod6, method2, method, optionalMethod2, optionalMethod);
        return android22222;
    }

    static byte[] concatLengthPrefixed(List<Protocol> list) {
        Buffer buffer = new Buffer();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Protocol protocol = (Protocol) list.get(i);
            if (protocol != Protocol.HTTP_1_0) {
                buffer.writeByte(protocol.toString().length());
                buffer.writeUtf8(protocol.toString());
            }
        }
        return buffer.readByteArray();
    }
}
