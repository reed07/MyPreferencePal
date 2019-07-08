package com.myfitnesspal.shared.api;

import com.google.gson.annotations.Expose;
import java.util.List;

public class ApiPostData<T> {
    @Expose
    private T item;
    @Expose
    private List<T> items;

    public ApiPostData() {
    }

    public ApiPostData(T t) {
        setItem(t);
    }

    public ApiPostData(List<T> list) {
        setItems(list);
    }

    public T getItem() {
        return this.item;
    }

    public ApiPostData<T> setItem(T t) {
        this.item = t;
        return this;
    }

    public List<T> getItems() {
        return this.items;
    }

    public ApiPostData<T> setItems(List<T> list) {
        this.items = list;
        return this;
    }
}
