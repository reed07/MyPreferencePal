package io.uacf.core.api;

import com.google.gson.annotations.Expose;
import java.util.List;

public class ApiResponse<T> extends ApiResponseBase {
    @Expose
    T item;
    @Expose
    List<T> items;

    public T getItem() {
        return this.item;
    }

    public List<T> getItems() {
        return this.items;
    }
}
