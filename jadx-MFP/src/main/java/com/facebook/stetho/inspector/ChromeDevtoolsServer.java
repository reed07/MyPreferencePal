package com.facebook.stetho.inspector;

import com.facebook.stetho.common.LogRedirector;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcException;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.jsonrpc.PendingRequest;
import com.facebook.stetho.inspector.jsonrpc.protocol.JsonRpcError;
import com.facebook.stetho.inspector.jsonrpc.protocol.JsonRpcError.ErrorCode;
import com.facebook.stetho.inspector.jsonrpc.protocol.JsonRpcRequest;
import com.facebook.stetho.inspector.jsonrpc.protocol.JsonRpcResponse;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import com.facebook.stetho.json.ObjectMapper;
import com.facebook.stetho.websocket.CloseCodes;
import com.facebook.stetho.websocket.SimpleEndpoint;
import com.facebook.stetho.websocket.SimpleSession;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class ChromeDevtoolsServer implements SimpleEndpoint {
    public static final String PATH = "/inspector";
    private static final String TAG = "ChromeDevtoolsServer";
    private final MethodDispatcher mMethodDispatcher;
    private final ObjectMapper mObjectMapper = new ObjectMapper();
    private final Map<SimpleSession, JsonRpcPeer> mPeers = Collections.synchronizedMap(new HashMap());

    /* renamed from: com.facebook.stetho.inspector.ChromeDevtoolsServer$1 reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$stetho$inspector$jsonrpc$protocol$JsonRpcError$ErrorCode = new int[ErrorCode.values().length];

        static {
            try {
                $SwitchMap$com$facebook$stetho$inspector$jsonrpc$protocol$JsonRpcError$ErrorCode[ErrorCode.METHOD_NOT_FOUND.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public ChromeDevtoolsServer(Iterable<ChromeDevtoolsDomain> iterable) {
        this.mMethodDispatcher = new MethodDispatcher(this.mObjectMapper, iterable);
    }

    public void onOpen(SimpleSession simpleSession) {
        LogRedirector.d(TAG, "onOpen");
        this.mPeers.put(simpleSession, new JsonRpcPeer(this.mObjectMapper, simpleSession));
    }

    public void onClose(SimpleSession simpleSession, int i, String str) {
        String str2 = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("onClose: reason=");
        sb.append(i);
        sb.append(" ");
        sb.append(str);
        LogRedirector.d(str2, sb.toString());
        JsonRpcPeer jsonRpcPeer = (JsonRpcPeer) this.mPeers.remove(simpleSession);
        if (jsonRpcPeer != null) {
            jsonRpcPeer.invokeDisconnectReceivers();
        }
    }

    public void onMessage(SimpleSession simpleSession, byte[] bArr, int i) {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("Ignoring binary message of length ");
        sb.append(i);
        LogRedirector.d(str, sb.toString());
    }

    public void onMessage(SimpleSession simpleSession, String str) {
        if (LogRedirector.isLoggable(TAG, 2)) {
            String str2 = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onMessage: message=");
            sb.append(str);
            LogRedirector.v(str2, sb.toString());
        }
        try {
            JsonRpcPeer jsonRpcPeer = (JsonRpcPeer) this.mPeers.get(simpleSession);
            Util.throwIfNull(jsonRpcPeer);
            handleRemoteMessage(jsonRpcPeer, str);
        } catch (IOException e) {
            if (LogRedirector.isLoggable(TAG, 2)) {
                String str3 = TAG;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Unexpected I/O exception processing message: ");
                sb2.append(e);
                LogRedirector.v(str3, sb2.toString());
            }
            closeSafely(simpleSession, CloseCodes.UNEXPECTED_CONDITION, e.getClass().getSimpleName());
        } catch (MessageHandlingException e2) {
            String str4 = TAG;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Message could not be processed by implementation: ");
            sb3.append(e2);
            LogRedirector.i(str4, sb3.toString());
            closeSafely(simpleSession, CloseCodes.UNEXPECTED_CONDITION, e2.getClass().getSimpleName());
        } catch (JSONException e3) {
            LogRedirector.v(TAG, "Unexpected JSON exception processing message", e3);
            closeSafely(simpleSession, CloseCodes.UNEXPECTED_CONDITION, e3.getClass().getSimpleName());
        }
    }

    private void closeSafely(SimpleSession simpleSession, int i, String str) {
        simpleSession.close(i, str);
    }

    private void handleRemoteMessage(JsonRpcPeer jsonRpcPeer, String str) throws IOException, MessageHandlingException, JSONException {
        JSONObject jSONObject = new JSONObject(str);
        if (jSONObject.has(Param.METHOD)) {
            handleRemoteRequest(jsonRpcPeer, jSONObject);
        } else if (jSONObject.has("result")) {
            handleRemoteResponse(jsonRpcPeer, jSONObject);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Improper JSON-RPC message: ");
            sb.append(str);
            throw new MessageHandlingException(sb.toString());
        }
    }

    private void handleRemoteRequest(JsonRpcPeer jsonRpcPeer, JSONObject jSONObject) throws MessageHandlingException {
        JSONObject jSONObject2;
        JSONObject jSONObject3;
        String str;
        JsonRpcRequest jsonRpcRequest = (JsonRpcRequest) this.mObjectMapper.convertValue(jSONObject, JsonRpcRequest.class);
        try {
            jSONObject3 = this.mMethodDispatcher.dispatch(jsonRpcPeer, jsonRpcRequest.method, jsonRpcRequest.params);
            jSONObject2 = null;
        } catch (JsonRpcException e) {
            logDispatchException(e);
            jSONObject2 = (JSONObject) this.mObjectMapper.convertValue(e.getErrorMessage(), JSONObject.class);
            jSONObject3 = null;
        }
        if (jsonRpcRequest.id != null) {
            JsonRpcResponse jsonRpcResponse = new JsonRpcResponse();
            jsonRpcResponse.id = jsonRpcRequest.id.longValue();
            jsonRpcResponse.result = jSONObject3;
            jsonRpcResponse.error = jSONObject2;
            try {
                str = ((JSONObject) this.mObjectMapper.convertValue(jsonRpcResponse, JSONObject.class)).toString();
            } catch (OutOfMemoryError e2) {
                jsonRpcResponse.result = null;
                jsonRpcResponse.error = (JSONObject) this.mObjectMapper.convertValue(e2.getMessage(), JSONObject.class);
                str = ((JSONObject) this.mObjectMapper.convertValue(jsonRpcResponse, JSONObject.class)).toString();
            }
            jsonRpcPeer.getWebSocket().sendText(str);
        }
    }

    private static void logDispatchException(JsonRpcException jsonRpcException) {
        JsonRpcError errorMessage = jsonRpcException.getErrorMessage();
        if (AnonymousClass1.$SwitchMap$com$facebook$stetho$inspector$jsonrpc$protocol$JsonRpcError$ErrorCode[errorMessage.code.ordinal()] != 1) {
            LogRedirector.w(TAG, "Error processing remote message", jsonRpcException);
            return;
        }
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("Method not implemented: ");
        sb.append(errorMessage.message);
        LogRedirector.d(str, sb.toString());
    }

    private void handleRemoteResponse(JsonRpcPeer jsonRpcPeer, JSONObject jSONObject) throws MismatchedResponseException {
        JsonRpcResponse jsonRpcResponse = (JsonRpcResponse) this.mObjectMapper.convertValue(jSONObject, JsonRpcResponse.class);
        PendingRequest andRemovePendingRequest = jsonRpcPeer.getAndRemovePendingRequest(jsonRpcResponse.id);
        if (andRemovePendingRequest == null) {
            throw new MismatchedResponseException(jsonRpcResponse.id);
        } else if (andRemovePendingRequest.callback != null) {
            andRemovePendingRequest.callback.onResponse(jsonRpcPeer, jsonRpcResponse);
        }
    }

    public void onError(SimpleSession simpleSession, Throwable th) {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("onError: ex=");
        sb.append(th.toString());
        LogRedirector.e(str, sb.toString());
    }
}
