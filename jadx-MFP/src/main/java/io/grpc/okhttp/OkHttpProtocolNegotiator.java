package io.grpc.okhttp;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.myfitnesspal.shared.constants.SharedConstants.Http;
import io.grpc.okhttp.internal.OptionalMethod;
import io.grpc.okhttp.internal.Platform;
import io.grpc.okhttp.internal.Platform.TlsExtensionType;
import io.grpc.okhttp.internal.Protocol;
import io.grpc.okhttp.internal.Util;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.net.ssl.SSLSocket;

class OkHttpProtocolNegotiator {
    private static final Platform DEFAULT_PLATFORM = Platform.get();
    private static OkHttpProtocolNegotiator NEGOTIATOR = createNegotiator(OkHttpProtocolNegotiator.class.getClassLoader());
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(OkHttpProtocolNegotiator.class.getName());
    protected final Platform platform;

    @VisibleForTesting
    static final class AndroidNegotiator extends OkHttpProtocolNegotiator {
        private static final OptionalMethod<Socket> GET_ALPN_SELECTED_PROTOCOL = new OptionalMethod<>(byte[].class, "getAlpnSelectedProtocol", new Class[0]);
        private static final OptionalMethod<Socket> GET_NPN_SELECTED_PROTOCOL = new OptionalMethod<>(byte[].class, "getNpnSelectedProtocol", new Class[0]);
        private static final OptionalMethod<Socket> SET_ALPN_PROTOCOLS = new OptionalMethod<>(null, "setAlpnProtocols", byte[].class);
        private static final OptionalMethod<Socket> SET_HOSTNAME = new OptionalMethod<>(null, "setHostname", String.class);
        private static final OptionalMethod<Socket> SET_NPN_PROTOCOLS = new OptionalMethod<>(null, "setNpnProtocols", byte[].class);
        private static final OptionalMethod<Socket> SET_USE_SESSION_TICKETS = new OptionalMethod<>(null, "setUseSessionTickets", Boolean.TYPE);

        AndroidNegotiator(Platform platform) {
            super(platform);
        }

        public String negotiate(SSLSocket sSLSocket, String str, List<Protocol> list) throws IOException {
            String selectedProtocol = getSelectedProtocol(sSLSocket);
            return selectedProtocol == null ? OkHttpProtocolNegotiator.super.negotiate(sSLSocket, str, list) : selectedProtocol;
        }

        /* access modifiers changed from: protected */
        public void configureTlsExtensions(SSLSocket sSLSocket, String str, List<Protocol> list) {
            if (str != null) {
                SET_USE_SESSION_TICKETS.invokeOptionalWithoutCheckedException(sSLSocket, Boolean.valueOf(true));
                SET_HOSTNAME.invokeOptionalWithoutCheckedException(sSLSocket, str);
            }
            Object[] objArr = {Platform.concatLengthPrefixed(list)};
            if (this.platform.getTlsExtensionType() == TlsExtensionType.ALPN_AND_NPN) {
                SET_ALPN_PROTOCOLS.invokeWithoutCheckedException(sSLSocket, objArr);
            }
            if (this.platform.getTlsExtensionType() != TlsExtensionType.NONE) {
                SET_NPN_PROTOCOLS.invokeWithoutCheckedException(sSLSocket, objArr);
                return;
            }
            throw new RuntimeException("We can not do TLS handshake on this Android version, please install the Google Play Services Dynamic Security Provider to use TLS");
        }

        public String getSelectedProtocol(SSLSocket sSLSocket) {
            if (this.platform.getTlsExtensionType() == TlsExtensionType.ALPN_AND_NPN) {
                try {
                    byte[] bArr = (byte[]) GET_ALPN_SELECTED_PROTOCOL.invokeWithoutCheckedException(sSLSocket, new Object[0]);
                    if (bArr != null) {
                        return new String(bArr, Util.UTF_8);
                    }
                } catch (Exception e) {
                    OkHttpProtocolNegotiator.logger.log(Level.FINE, "Failed calling getAlpnSelectedProtocol()", e);
                }
            }
            if (this.platform.getTlsExtensionType() != TlsExtensionType.NONE) {
                try {
                    byte[] bArr2 = (byte[]) GET_NPN_SELECTED_PROTOCOL.invokeWithoutCheckedException(sSLSocket, new Object[0]);
                    if (bArr2 != null) {
                        return new String(bArr2, Util.UTF_8);
                    }
                } catch (Exception e2) {
                    OkHttpProtocolNegotiator.logger.log(Level.FINE, "Failed calling getNpnSelectedProtocol()", e2);
                }
            }
            return null;
        }
    }

    @VisibleForTesting
    OkHttpProtocolNegotiator(Platform platform2) {
        this.platform = (Platform) Preconditions.checkNotNull(platform2, Http.PLATFORM);
    }

    public static OkHttpProtocolNegotiator get() {
        return NEGOTIATOR;
    }

    @VisibleForTesting
    static OkHttpProtocolNegotiator createNegotiator(ClassLoader classLoader) {
        boolean z;
        try {
            classLoader.loadClass("com.android.org.conscrypt.OpenSSLSocketImpl");
        } catch (ClassNotFoundException e) {
            logger.log(Level.FINE, "Unable to find Conscrypt. Skipping", e);
            try {
                classLoader.loadClass("org.apache.harmony.xnet.provider.jsse.OpenSSLSocketImpl");
            } catch (ClassNotFoundException e2) {
                logger.log(Level.FINE, "Unable to find any OpenSSLSocketImpl. Skipping", e2);
                z = false;
            }
        }
        z = true;
        if (z) {
            return new AndroidNegotiator(DEFAULT_PLATFORM);
        }
        return new OkHttpProtocolNegotiator(DEFAULT_PLATFORM);
    }

    public String negotiate(SSLSocket sSLSocket, String str, @Nullable List<Protocol> list) throws IOException {
        if (list != null) {
            configureTlsExtensions(sSLSocket, str, list);
        }
        try {
            sSLSocket.startHandshake();
            String selectedProtocol = getSelectedProtocol(sSLSocket);
            if (selectedProtocol != null) {
                return selectedProtocol;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("TLS ALPN negotiation failed with protocols: ");
            sb.append(list);
            throw new RuntimeException(sb.toString());
        } finally {
            this.platform.afterHandshake(sSLSocket);
        }
    }

    /* access modifiers changed from: protected */
    public void configureTlsExtensions(SSLSocket sSLSocket, String str, List<Protocol> list) {
        this.platform.configureTlsExtensions(sSLSocket, str, list);
    }

    public String getSelectedProtocol(SSLSocket sSLSocket) {
        return this.platform.getSelectedProtocol(sSLSocket);
    }
}
