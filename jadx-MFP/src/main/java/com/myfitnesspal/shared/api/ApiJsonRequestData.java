package com.myfitnesspal.shared.api;

import com.google.gson.annotations.Expose;

public class ApiJsonRequestData {
    @Expose
    private final Object data;

    public ApiJsonRequestData(Object obj) {
        this.data = obj;
    }

    public Object getData() {
        return this.data;
    }
}
