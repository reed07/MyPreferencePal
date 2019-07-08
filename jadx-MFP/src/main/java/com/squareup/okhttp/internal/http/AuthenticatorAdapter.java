package com.squareup.okhttp.internal.http;

import com.google.common.net.HttpHeaders;
import com.squareup.okhttp.Authenticator;
import com.squareup.okhttp.Challenge;
import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.net.Authenticator.RequestorType;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.util.List;

public final class AuthenticatorAdapter implements Authenticator {
    public static final Authenticator INSTANCE = new AuthenticatorAdapter();

    public Request authenticate(Proxy proxy, Response response) throws IOException {
        List challenges = response.challenges();
        Request request = response.request();
        HttpUrl httpUrl = request.httpUrl();
        int size = challenges.size();
        for (int i = 0; i < size; i++) {
            Challenge challenge = (Challenge) challenges.get(i);
            if (!"Basic".equalsIgnoreCase(challenge.getScheme())) {
                Proxy proxy2 = proxy;
            } else {
                PasswordAuthentication requestPasswordAuthentication = java.net.Authenticator.requestPasswordAuthentication(httpUrl.host(), getConnectToInetAddress(proxy, httpUrl), httpUrl.port(), httpUrl.scheme(), challenge.getRealm(), challenge.getScheme(), httpUrl.url(), RequestorType.SERVER);
                if (requestPasswordAuthentication != null) {
                    return request.newBuilder().header("Authorization", Credentials.basic(requestPasswordAuthentication.getUserName(), new String(requestPasswordAuthentication.getPassword()))).build();
                }
            }
        }
        return null;
    }

    public Request authenticateProxy(Proxy proxy, Response response) throws IOException {
        List challenges = response.challenges();
        Request request = response.request();
        HttpUrl httpUrl = request.httpUrl();
        int size = challenges.size();
        for (int i = 0; i < size; i++) {
            Challenge challenge = (Challenge) challenges.get(i);
            if ("Basic".equalsIgnoreCase(challenge.getScheme())) {
                InetSocketAddress inetSocketAddress = (InetSocketAddress) proxy.address();
                PasswordAuthentication requestPasswordAuthentication = java.net.Authenticator.requestPasswordAuthentication(inetSocketAddress.getHostName(), getConnectToInetAddress(proxy, httpUrl), inetSocketAddress.getPort(), httpUrl.scheme(), challenge.getRealm(), challenge.getScheme(), httpUrl.url(), RequestorType.PROXY);
                if (requestPasswordAuthentication != null) {
                    return request.newBuilder().header(HttpHeaders.PROXY_AUTHORIZATION, Credentials.basic(requestPasswordAuthentication.getUserName(), new String(requestPasswordAuthentication.getPassword()))).build();
                }
            }
        }
        return null;
    }

    private InetAddress getConnectToInetAddress(Proxy proxy, HttpUrl httpUrl) throws IOException {
        if (proxy == null || proxy.type() == Type.DIRECT) {
            return InetAddress.getByName(httpUrl.host());
        }
        return ((InetSocketAddress) proxy.address()).getAddress();
    }
}
