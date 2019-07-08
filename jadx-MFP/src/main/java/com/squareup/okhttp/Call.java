package com.squareup.okhttp;

import com.google.common.net.HttpHeaders;
import com.squareup.okhttp.Interceptor.Chain;
import com.squareup.okhttp.Request.Builder;
import com.squareup.okhttp.internal.http.HttpEngine;
import com.squareup.okhttp.internal.http.RequestException;
import com.squareup.okhttp.internal.http.RouteException;
import java.io.IOException;
import java.net.ProtocolException;

public class Call {
    volatile boolean canceled;
    /* access modifiers changed from: private */
    public final OkHttpClient client;
    HttpEngine engine;
    private boolean executed;
    Request originalRequest;

    class ApplicationInterceptorChain implements Chain {
        private final boolean forWebSocket;
        private final int index;
        private final Request request;

        ApplicationInterceptorChain(int i, Request request2, boolean z) {
            this.index = i;
            this.request = request2;
            this.forWebSocket = z;
        }

        public Response proceed(Request request2) throws IOException {
            if (this.index >= Call.this.client.interceptors().size()) {
                return Call.this.getResponse(request2, this.forWebSocket);
            }
            return ((Interceptor) Call.this.client.interceptors().get(this.index)).intercept(new ApplicationInterceptorChain(this.index + 1, request2, this.forWebSocket));
        }
    }

    protected Call(OkHttpClient okHttpClient, Request request) {
        this.client = okHttpClient.copyWithDefaults();
        this.originalRequest = request;
    }

    public Response execute() throws IOException {
        synchronized (this) {
            if (!this.executed) {
                this.executed = true;
            } else {
                throw new IllegalStateException("Already Executed");
            }
        }
        try {
            this.client.getDispatcher().executed(this);
            Response responseWithInterceptorChain = getResponseWithInterceptorChain(false);
            if (responseWithInterceptorChain != null) {
                return responseWithInterceptorChain;
            }
            throw new IOException("Canceled");
        } finally {
            this.client.getDispatcher().finished(this);
        }
    }

    private Response getResponseWithInterceptorChain(boolean z) throws IOException {
        return new ApplicationInterceptorChain(0, this.originalRequest, z).proceed(this.originalRequest);
    }

    /* access modifiers changed from: 0000 */
    public Response getResponse(Request request, boolean z) throws IOException {
        Request request2;
        RequestBody body = request.body();
        if (body != null) {
            Builder newBuilder = request.newBuilder();
            MediaType contentType = body.contentType();
            if (contentType != null) {
                newBuilder.header("Content-Type", contentType.toString());
            }
            long contentLength = body.contentLength();
            if (contentLength != -1) {
                newBuilder.header("Content-Length", Long.toString(contentLength));
                newBuilder.removeHeader(HttpHeaders.TRANSFER_ENCODING);
            } else {
                newBuilder.header(HttpHeaders.TRANSFER_ENCODING, "chunked");
                newBuilder.removeHeader("Content-Length");
            }
            request2 = newBuilder.build();
        } else {
            request2 = request;
        }
        HttpEngine httpEngine = new HttpEngine(this.client, request2, false, false, z, null, null, null, null);
        this.engine = httpEngine;
        int i = 0;
        while (!this.canceled) {
            try {
                this.engine.sendRequest();
                this.engine.readResponse();
                Response response = this.engine.getResponse();
                Request followUpRequest = this.engine.followUpRequest();
                if (followUpRequest == null) {
                    if (!z) {
                        this.engine.releaseConnection();
                    }
                    return response;
                }
                i++;
                if (i <= 20) {
                    if (!this.engine.sameConnection(followUpRequest.httpUrl())) {
                        this.engine.releaseConnection();
                    }
                    HttpEngine httpEngine2 = new HttpEngine(this.client, followUpRequest, false, false, z, this.engine.close(), null, null, response);
                    this.engine = httpEngine2;
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Too many follow-up requests: ");
                    sb.append(i);
                    throw new ProtocolException(sb.toString());
                }
            } catch (RequestException e) {
                throw e.getCause();
            } catch (RouteException e2) {
                HttpEngine recover = this.engine.recover(e2);
                if (recover != null) {
                    this.engine = recover;
                } else {
                    throw e2.getLastConnectException();
                }
            } catch (IOException e3) {
                HttpEngine recover2 = this.engine.recover(e3, null);
                if (recover2 != null) {
                    this.engine = recover2;
                } else {
                    throw e3;
                }
            }
        }
        this.engine.releaseConnection();
        throw new IOException("Canceled");
    }
}
