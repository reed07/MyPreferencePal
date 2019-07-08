package com.uacf.gear.bridge;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.brightcove.player.event.EventType;
import com.facebook.share.internal.ShareConstants;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class Message implements Parcelable {
    public static final Creator<Message> CREATOR = new Creator<Message>() {
        public Message createFromParcel(Parcel parcel) {
            return new Message(parcel);
        }

        public Message[] newArray(int i) {
            return new Message[i];
        }
    };
    /* access modifiers changed from: private */
    public JSONObject json;

    public static class Builder {
        final Message result = new Message();

        private Builder() {
            put("body", new JSONObject());
        }

        public static Builder respondTo(Message message) {
            Builder builder = new Builder();
            builder.put("id", message.getId());
            builder.put("version", Integer.valueOf(message.getVersion()));
            builder.put("name", message.getName());
            builder.put("type", EventType.RESPONSE);
            return builder;
        }

        public static Builder broadcast(String str) {
            Builder builder = new Builder();
            builder.put("id", newId(str));
            builder.put("type", "broadcast");
            return builder;
        }

        public Builder setName(String str) {
            return put("name", str);
        }

        public Builder setBody(JSONObject jSONObject) {
            return put("body", jSONObject);
        }

        public <T> Builder addBodyValue(String str, T t) {
            try {
                this.result.json.getJSONObject("body").put(str, t);
                return this;
            } catch (JSONException unused) {
                throw new RuntimeException("fatal JSON error");
            }
        }

        public Message build() {
            if (this.result.isValid()) {
                return this.result;
            }
            throw new IllegalStateException("invalid message!");
        }

        private <T> Builder put(String str, T t) {
            try {
                this.result.json.put(str, t);
                return this;
            } catch (JSONException unused) {
                throw new RuntimeException("invalid JSON key/value??");
            }
        }

        private static String newId(String str) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("-");
            sb.append(UUID.randomUUID().toString());
            return sb.toString();
        }
    }

    public interface Type {
    }

    public int describeContents() {
        return 0;
    }

    public Message() {
        this.json = new JSONObject();
        initDefaults();
    }

    private Message(JSONObject jSONObject) {
        this.json = jSONObject;
        initDefaults();
    }

    private Message(Parcel parcel) {
        try {
            this.json = new JSONObject(parcel.readString());
        } catch (JSONException unused) {
            throw new RuntimeException("unparceling failed?!");
        }
    }

    private void initDefaults() {
        try {
            this.json.putOpt("timestamp", Long.valueOf(System.currentTimeMillis()));
            if (this.json.isNull("body")) {
                this.json.put("body", new JSONObject());
            }
        } catch (JSONException unused) {
            throw new RuntimeException("invalid JSON??");
        }
    }

    public int getVersion() {
        return this.json.optInt("version", 1);
    }

    public String getId() {
        return this.json.optString("id", "");
    }

    public String getName() {
        return this.json.optString("name", "");
    }

    public String getType() {
        return this.json.optString("type", "");
    }

    public JSONObject getBody() {
        return this.json.optJSONObject("body");
    }

    public String getStringFromBody(String str) {
        return getBody().optString(str);
    }

    /* access modifiers changed from: private */
    public boolean isValid() {
        boolean z = false;
        if (getName().length() == 0 || getId().length() == 0 || getVersion() <= 0 || this.json.isNull("body")) {
            return false;
        }
        String type = getType();
        if ("broadcast".equals(type) || ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID.equals(type) || EventType.RESPONSE.equals(type)) {
            z = true;
        }
        return z;
    }

    public String toString() {
        return this.json.toString();
    }

    public static Message fromBytes(byte[] bArr) {
        if (bArr != null && bArr.length > 0) {
            try {
                Message message = new Message(new JSONObject(new String(bArr)));
                if (message.isValid()) {
                    return message;
                }
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    public byte[] getBytes() {
        return this.json.toString().getBytes();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.json.toString());
    }
}
