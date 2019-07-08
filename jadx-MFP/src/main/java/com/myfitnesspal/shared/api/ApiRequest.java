package com.myfitnesspal.shared.api;

import com.google.gson.annotations.Expose;
import java.util.List;

public class ApiRequest<T> {
    @Expose
    private T item;
    @Expose
    private List<T> items;

    public ApiRequest() {
    }

    public ApiRequest(T t) {
        this.item = t;
    }

    public ApiRequest(List<T> list) {
        this.items = list;
    }

    public T getItem() {
        return this.item;
    }

    public void setItem(T t) {
        this.item = t;
    }

    public void setItems(List<T> list) {
        this.items = list;
    }

    public List<T> getItems() {
        return this.items;
    }
}
