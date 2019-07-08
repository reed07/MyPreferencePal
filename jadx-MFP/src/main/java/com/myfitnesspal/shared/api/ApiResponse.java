package com.myfitnesspal.shared.api;

import com.google.gson.annotations.Expose;
import java.util.List;

public class ApiResponse<T> extends ApiResponseBase {
    @Expose
    private T item;
    @Expose
    private List<T> items;

    public static class API_RESPONSE_MAPPER extends ApiResponse {
    }

    public T getItem() {
        return this.item;
    }

    public void setItem(T t) {
        this.item = t;
    }

    public List<T> getItems() {
        return this.items;
    }

    public void setItems(List<T> list) {
        this.items = list;
    }
}
