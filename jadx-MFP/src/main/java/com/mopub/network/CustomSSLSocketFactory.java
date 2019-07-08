package com.mopub.network;

import android.net.SSLCertificateSocketFactory;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Reflection.MethodBuilder;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class CustomSSLSocketFactory extends SSLSocketFactory {
    @Nullable
    private SSLSocketFactory mCertificateSocketFactory;

    private CustomSSLSocketFactory() {
    }

    @NonNull
    public static CustomSSLSocketFactory getDefault(int i) {
        CustomSSLSocketFactory customSSLSocketFactory = new CustomSSLSocketFactory();
        customSSLSocketFactory.mCertificateSocketFactory = SSLCertificateSocketFactory.getDefault(i, null);
        return customSSLSocketFactory;
    }

    public Socket createSocket() throws IOException {
        SSLSocketFactory sSLSocketFactory = this.mCertificateSocketFactory;
        if (sSLSocketFactory != null) {
            Socket createSocket = sSLSocketFactory.createSocket();
            enableTlsIfAvailable(createSocket);
            return createSocket;
        }
        throw new SocketException("SSLSocketFactory was null. Unable to create socket.");
    }

    public Socket createSocket(String str, int i) throws IOException, UnknownHostException {
        SSLSocketFactory sSLSocketFactory = this.mCertificateSocketFactory;
        if (sSLSocketFactory != null) {
            Socket createSocket = sSLSocketFactory.createSocket(str, i);
            enableTlsIfAvailable(createSocket);
            return createSocket;
        }
        throw new SocketException("SSLSocketFactory was null. Unable to create socket.");
    }

    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException, UnknownHostException {
        SSLSocketFactory sSLSocketFactory = this.mCertificateSocketFactory;
        if (sSLSocketFactory != null) {
            Socket createSocket = sSLSocketFactory.createSocket(str, i, inetAddress, i2);
            enableTlsIfAvailable(createSocket);
            return createSocket;
        }
        throw new SocketException("SSLSocketFactory was null. Unable to create socket.");
    }

    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        SSLSocketFactory sSLSocketFactory = this.mCertificateSocketFactory;
        if (sSLSocketFactory != null) {
            Socket createSocket = sSLSocketFactory.createSocket(inetAddress, i);
            enableTlsIfAvailable(createSocket);
            return createSocket;
        }
        throw new SocketException("SSLSocketFactory was null. Unable to create socket.");
    }

    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        SSLSocketFactory sSLSocketFactory = this.mCertificateSocketFactory;
        if (sSLSocketFactory != null) {
            Socket createSocket = sSLSocketFactory.createSocket(inetAddress, i, inetAddress2, i2);
            enableTlsIfAvailable(createSocket);
            return createSocket;
        }
        throw new SocketException("SSLSocketFactory was null. Unable to create socket.");
    }

    public String[] getDefaultCipherSuites() {
        SSLSocketFactory sSLSocketFactory = this.mCertificateSocketFactory;
        if (sSLSocketFactory == null) {
            return new String[0];
        }
        return sSLSocketFactory.getDefaultCipherSuites();
    }

    public String[] getSupportedCipherSuites() {
        SSLSocketFactory sSLSocketFactory = this.mCertificateSocketFactory;
        if (sSLSocketFactory == null) {
            return new String[0];
        }
        return sSLSocketFactory.getSupportedCipherSuites();
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        if (this.mCertificateSocketFactory == null) {
            throw new SocketException("SSLSocketFactory was null. Unable to create socket.");
        } else if (VERSION.SDK_INT < 23) {
            if (z && socket != null) {
                socket.close();
            }
            Socket createSocket = this.mCertificateSocketFactory.createSocket(InetAddressUtils.getInetAddressByName(str), i);
            enableTlsIfAvailable(createSocket);
            doManualServerNameIdentification(createSocket, str);
            return createSocket;
        } else {
            Socket createSocket2 = this.mCertificateSocketFactory.createSocket(socket, str, i, z);
            enableTlsIfAvailable(createSocket2);
            return createSocket2;
        }
    }

    private void doManualServerNameIdentification(@NonNull Socket socket, @Nullable String str) throws IOException {
        Preconditions.checkNotNull(socket);
        SSLSocketFactory sSLSocketFactory = this.mCertificateSocketFactory;
        if (sSLSocketFactory == null) {
            throw new SocketException("SSLSocketFactory was null. Unable to create socket.");
        } else if (socket instanceof SSLSocket) {
            SSLSocket sSLSocket = (SSLSocket) socket;
            setHostnameOnSocket((SSLCertificateSocketFactory) sSLSocketFactory, sSLSocket, str);
            verifyServerName(sSLSocket, str);
        }
    }

    @VisibleForTesting
    static void setHostnameOnSocket(@NonNull SSLCertificateSocketFactory sSLCertificateSocketFactory, @NonNull SSLSocket sSLSocket, @Nullable String str) {
        Preconditions.checkNotNull(sSLCertificateSocketFactory);
        Preconditions.checkNotNull(sSLSocket);
        if (VERSION.SDK_INT >= 17) {
            sSLCertificateSocketFactory.setHostname(sSLSocket, str);
            return;
        }
        try {
            new MethodBuilder(sSLSocket, "setHostname").addParam(String.class, str).execute();
        } catch (Exception unused) {
            MoPubLog.d("Unable to call setHostname() on the socket");
        }
    }

    @VisibleForTesting
    static void verifyServerName(@NonNull SSLSocket sSLSocket, @Nullable String str) throws IOException {
        Preconditions.checkNotNull(sSLSocket);
        sSLSocket.startHandshake();
        if (!HttpsURLConnection.getDefaultHostnameVerifier().verify(str, sSLSocket.getSession())) {
            throw new SSLHandshakeException("Server Name Identification failed.");
        }
    }

    private void enableTlsIfAvailable(@Nullable Socket socket) {
        if (socket instanceof SSLSocket) {
            SSLSocket sSLSocket = (SSLSocket) socket;
            sSLSocket.setEnabledProtocols(sSLSocket.getSupportedProtocols());
        }
    }
}
