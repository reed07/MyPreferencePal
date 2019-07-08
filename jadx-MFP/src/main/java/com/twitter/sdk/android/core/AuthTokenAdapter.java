package com.twitter.sdk.android.core;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.twitter.sdk.android.core.internal.oauth.GuestAuthToken;
import com.twitter.sdk.android.core.internal.oauth.OAuth2Token;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class AuthTokenAdapter implements JsonDeserializer<AuthToken>, JsonSerializer<AuthToken> {
    static final Map<String, Class<? extends AuthToken>> authTypeRegistry = new HashMap();
    private final Gson gson = new Gson();

    static {
        authTypeRegistry.put("oauth1a", TwitterAuthToken.class);
        authTypeRegistry.put("oauth2", OAuth2Token.class);
        authTypeRegistry.put("guest", GuestAuthToken.class);
    }

    public JsonElement serialize(AuthToken authToken, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("auth_type", getAuthTypeString(authToken.getClass()));
        jsonObject.add("auth_token", this.gson.toJsonTree(authToken));
        return jsonObject;
    }

    public AuthToken deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        String asString = asJsonObject.getAsJsonPrimitive("auth_type").getAsString();
        return (AuthToken) this.gson.fromJson(asJsonObject.get("auth_token"), (Class) authTypeRegistry.get(asString));
    }

    static String getAuthTypeString(Class<? extends AuthToken> cls) {
        for (Entry entry : authTypeRegistry.entrySet()) {
            if (((Class) entry.getValue()).equals(cls)) {
                return (String) entry.getKey();
            }
        }
        return "";
    }
}
