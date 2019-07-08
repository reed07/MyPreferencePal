package com.squareup.okhttp.internal;

import com.squareup.okhttp.Connection;
import com.squareup.okhttp.ConnectionPool;
import com.squareup.okhttp.ConnectionSpec;
import com.squareup.okhttp.Headers.Builder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.internal.http.HttpEngine;
import com.squareup.okhttp.internal.http.RouteException;
import com.squareup.okhttp.internal.http.Transport;
import java.io.IOException;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;

public abstract class Internal {
    public static Internal instance;
    public static final Logger logger = Logger.getLogger(OkHttpClient.class.getName());

    public abstract void addLenient(Builder builder, String str);

    public abstract void apply(ConnectionSpec connectionSpec, SSLSocket sSLSocket, boolean z);

    public abstract boolean clearOwner(Connection connection);

    public abstract void connectAndSetOwner(OkHttpClient okHttpClient, Connection connection, HttpEngine httpEngine, Request request) throws RouteException;

    public abstract InternalCache internalCache(OkHttpClient okHttpClient);

    public abstract boolean isReadable(Connection connection);

    public abstract Network network(OkHttpClient okHttpClient);

    public abstract Transport newTransport(Connection connection, HttpEngine httpEngine) throws IOException;

    public abstract void recycle(ConnectionPool connectionPool, Connection connection);

    public abstract int recycleCount(Connection connection);

    public abstract RouteDatabase routeDatabase(OkHttpClient okHttpClient);

    public abstract void setOwner(Connection connection, HttpEngine httpEngine);

    public abstract void setProtocol(Connection connection, Protocol protocol);
}
