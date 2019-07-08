package com.myfitnesspal.shared.model.v2;

import java.util.List;
import java.util.Map;

public class MfpUserPatchRequest {
    private List<Map<String, String>> profiles;

    public List<Map<String, String>> getProfiles() {
        return this.profiles;
    }

    public void setProfiles(List<Map<String, String>> list) {
        this.profiles = list;
    }
}
