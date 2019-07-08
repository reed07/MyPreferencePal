package com.squareup.okhttp.internal;

import java.net.InetAddress;
import java.net.UnknownHostException;

public interface Network {
    public static final Network DEFAULT = new Network() {
        public InetAddress[] resolveInetAddresses(String str) throws UnknownHostException {
            if (str != null) {
                return InetAddress.getAllByName(str);
            }
            throw new UnknownHostException("host == null");
        }
    };

    InetAddress[] resolveInetAddresses(String str) throws UnknownHostException;
}
