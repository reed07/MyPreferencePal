package com.amplitude.api;

import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Identify {
    protected Set<String> userProperties = new HashSet();
    protected JSONObject userPropertiesOperations = new JSONObject();

    public Identify setOnce(String str, String str2) {
        addToUserProperties("$setOnce", str, str2);
        return this;
    }

    public Identify set(String str, boolean z) {
        addToUserProperties("$set", str, Boolean.valueOf(z));
        return this;
    }

    public Identify set(String str, int i) {
        addToUserProperties("$set", str, Integer.valueOf(i));
        return this;
    }

    public Identify set(String str, String str2) {
        addToUserProperties("$set", str, str2);
        return this;
    }

    public Identify set(String str, String[] strArr) {
        addToUserProperties("$set", str, stringArrayToJSONArray(strArr));
        return this;
    }

    private void addToUserProperties(String str, String str2, Object obj) {
        if (Utils.isEmptyString(str2)) {
            AmplitudeLog.getLogger().w("com.amplitude.api.Identify", String.format("Attempting to perform operation %s with a null or empty string property, ignoring", new Object[]{str}));
        } else if (obj == null) {
            AmplitudeLog.getLogger().w("com.amplitude.api.Identify", String.format("Attempting to perform operation %s with null value for property %s, ignoring", new Object[]{str, str2}));
        } else if (this.userPropertiesOperations.has("$clearAll")) {
            AmplitudeLog.getLogger().w("com.amplitude.api.Identify", String.format("This Identify already contains a $clearAll operation, ignoring operation %s", new Object[]{str}));
        } else if (this.userProperties.contains(str2)) {
            AmplitudeLog.getLogger().w("com.amplitude.api.Identify", String.format("Already used property %s in previous operation, ignoring operation %s", new Object[]{str2, str}));
        } else {
            try {
                if (!this.userPropertiesOperations.has(str)) {
                    this.userPropertiesOperations.put(str, new JSONObject());
                }
                this.userPropertiesOperations.getJSONObject(str).put(str2, obj);
                this.userProperties.add(str2);
            } catch (JSONException e) {
                AmplitudeLog.getLogger().e("com.amplitude.api.Identify", e.toString());
            }
        }
    }

    private JSONArray stringArrayToJSONArray(String[] strArr) {
        JSONArray jSONArray = new JSONArray();
        for (String put : strArr) {
            jSONArray.put(put);
        }
        return jSONArray;
    }
}
