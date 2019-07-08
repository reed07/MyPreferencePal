package com.crashlytics.android.beta;

import com.myfitnesspal.shared.constants.SharedConstants.Http;
import com.myfitnesspal.shared.db.table.InstalledDatasetsTable.Columns;
import java.io.IOException;
import org.json.JSONObject;

class CheckForUpdatesResponseTransform {
    CheckForUpdatesResponseTransform() {
    }

    public CheckForUpdatesResponse fromJson(JSONObject jSONObject) throws IOException {
        if (jSONObject == null) {
            return null;
        }
        CheckForUpdatesResponse checkForUpdatesResponse = new CheckForUpdatesResponse(jSONObject.optString("url", null), jSONObject.optString("version_string", null), jSONObject.optString("display_version", null), jSONObject.optString(Http.BUILD_VERSION, null), jSONObject.optString(Columns.IDENTIFIER, null), jSONObject.optString("instance_identifier", null));
        return checkForUpdatesResponse;
    }
}
