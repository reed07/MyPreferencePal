package com.google.android.gms.measurement.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

final class zzfz extends SSLSocket {
    private final SSLSocket zzauv;

    zzfz(zzfy zzfy, SSLSocket sSLSocket) {
        this.zzauv = sSLSocket;
    }

    public final void setEnabledProtocols(String[] strArr) {
        if (strArr != null && Arrays.asList(strArr).contains("SSLv3")) {
            ArrayList arrayList = new ArrayList(Arrays.asList(this.zzauv.getEnabledProtocols()));
            if (arrayList.size() > 1) {
                arrayList.remove("SSLv3");
            }
            strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
        }
        this.zzauv.setEnabledProtocols(strArr);
    }

    public final String[] getSupportedCipherSuites() {
        return this.zzauv.getSupportedCipherSuites();
    }

    public final String[] getEnabledCipherSuites() {
        return this.zzauv.getEnabledCipherSuites();
    }

    public final void setEnabledCipherSuites(String[] strArr) {
        this.zzauv.setEnabledCipherSuites(strArr);
    }

    public final String[] getSupportedProtocols() {
        return this.zzauv.getSupportedProtocols();
    }

    public final String[] getEnabledProtocols() {
        return this.zzauv.getEnabledProtocols();
    }

    public final SSLSession getSession() {
        return this.zzauv.getSession();
    }

    public final void addHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
        this.zzauv.addHandshakeCompletedListener(handshakeCompletedListener);
    }

    public final void removeHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
        this.zzauv.removeHandshakeCompletedListener(handshakeCompletedListener);
    }

    public final void startHandshake() throws IOException {
        this.zzauv.startHandshake();
    }

    public final void setUseClientMode(boolean z) {
        this.zzauv.setUseClientMode(z);
    }

    public final boolean getUseClientMode() {
        return this.zzauv.getUseClientMode();
    }

    public final void setNeedClientAuth(boolean z) {
        this.zzauv.setNeedClientAuth(z);
    }

    public final void setWantClientAuth(boolean z) {
        this.zzauv.setWantClientAuth(z);
    }

    public final boolean getNeedClientAuth() {
        return this.zzauv.getNeedClientAuth();
    }

    public final boolean getWantClientAuth() {
        return this.zzauv.getWantClientAuth();
    }

    public final void setEnableSessionCreation(boolean z) {
        this.zzauv.setEnableSessionCreation(z);
    }

    public final boolean getEnableSessionCreation() {
        return this.zzauv.getEnableSessionCreation();
    }

    public final void bind(SocketAddress socketAddress) throws IOException {
        this.zzauv.bind(socketAddress);
    }

    public final synchronized void close() throws IOException {
        this.zzauv.close();
    }

    public final void connect(SocketAddress socketAddress) throws IOException {
        this.zzauv.connect(socketAddress);
    }

    public final void connect(SocketAddress socketAddress, int i) throws IOException {
        this.zzauv.connect(socketAddress, i);
    }

    public final SocketChannel getChannel() {
        return this.zzauv.getChannel();
    }

    public final InetAddress getInetAddress() {
        return this.zzauv.getInetAddress();
    }

    public final InputStream getInputStream() throws IOException {
        return this.zzauv.getInputStream();
    }

    public final boolean getKeepAlive() throws SocketException {
        return this.zzauv.getKeepAlive();
    }

    public final InetAddress getLocalAddress() {
        return this.zzauv.getLocalAddress();
    }

    public final int getLocalPort() {
        return this.zzauv.getLocalPort();
    }

    public final SocketAddress getLocalSocketAddress() {
        return this.zzauv.getLocalSocketAddress();
    }

    public final boolean getOOBInline() throws SocketException {
        return this.zzauv.getOOBInline();
    }

    public final OutputStream getOutputStream() throws IOException {
        return this.zzauv.getOutputStream();
    }

    public final int getPort() {
        return this.zzauv.getPort();
    }

    public final synchronized int getReceiveBufferSize() throws SocketException {
        return this.zzauv.getReceiveBufferSize();
    }

    public final SocketAddress getRemoteSocketAddress() {
        return this.zzauv.getRemoteSocketAddress();
    }

    public final boolean getReuseAddress() throws SocketException {
        return this.zzauv.getReuseAddress();
    }

    public final synchronized int getSendBufferSize() throws SocketException {
        return this.zzauv.getSendBufferSize();
    }

    public final int getSoLinger() throws SocketException {
        return this.zzauv.getSoLinger();
    }

    public final synchronized int getSoTimeout() throws SocketException {
        return this.zzauv.getSoTimeout();
    }

    public final boolean getTcpNoDelay() throws SocketException {
        return this.zzauv.getTcpNoDelay();
    }

    public final int getTrafficClass() throws SocketException {
        return this.zzauv.getTrafficClass();
    }

    public final boolean isBound() {
        return this.zzauv.isBound();
    }

    public final boolean isClosed() {
        return this.zzauv.isClosed();
    }

    public final boolean isConnected() {
        return this.zzauv.isConnected();
    }

    public final boolean isInputShutdown() {
        return this.zzauv.isInputShutdown();
    }

    public final boolean isOutputShutdown() {
        return this.zzauv.isOutputShutdown();
    }

    public final void sendUrgentData(int i) throws IOException {
        this.zzauv.sendUrgentData(i);
    }

    public final void setKeepAlive(boolean z) throws SocketException {
        this.zzauv.setKeepAlive(z);
    }

    public final void setOOBInline(boolean z) throws SocketException {
        this.zzauv.setOOBInline(z);
    }

    public final void setPerformancePreferences(int i, int i2, int i3) {
        this.zzauv.setPerformancePreferences(i, i2, i3);
    }

    public final synchronized void setReceiveBufferSize(int i) throws SocketException {
        this.zzauv.setReceiveBufferSize(i);
    }

    public final void setReuseAddress(boolean z) throws SocketException {
        this.zzauv.setReuseAddress(z);
    }

    public final synchronized void setSendBufferSize(int i) throws SocketException {
        this.zzauv.setSendBufferSize(i);
    }

    public final void setSoLinger(boolean z, int i) throws SocketException {
        this.zzauv.setSoLinger(z, i);
    }

    public final synchronized void setSoTimeout(int i) throws SocketException {
        this.zzauv.setSoTimeout(i);
    }

    public final void setTcpNoDelay(boolean z) throws SocketException {
        this.zzauv.setTcpNoDelay(z);
    }

    public final void setTrafficClass(int i) throws SocketException {
        this.zzauv.setTrafficClass(i);
    }

    public final void shutdownInput() throws IOException {
        this.zzauv.shutdownInput();
    }

    public final void shutdownOutput() throws IOException {
        this.zzauv.shutdownOutput();
    }

    public final String toString() {
        return this.zzauv.toString();
    }

    public final boolean equals(Object obj) {
        return this.zzauv.equals(obj);
    }
}
