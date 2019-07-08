package com.myfitnesspal.shared.api.request;

import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.model.v2.MfpServingSize;
import java.util.List;

public class FoodPatchRequest {
    @Expose
    List<MfpServingSize> servingSizes;

    public FoodPatchRequest(List<MfpServingSize> list) {
        this.servingSizes = list;
    }

    public List<MfpServingSize> getServingSizes() {
        return this.servingSizes;
    }
}
