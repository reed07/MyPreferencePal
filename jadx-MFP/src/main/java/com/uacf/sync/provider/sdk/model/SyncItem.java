package com.uacf.sync.provider.sdk.model;

public class SyncItem<T> {
    private Action action;
    private String id;
    private T item;
    private String type;

    public enum Action {
        Delete,
        Create,
        Update
    }

    public SyncItem(T t, String str, String str2, Action action2) {
        this.item = t;
        this.type = str;
        this.id = str2;
        this.action = action2;
    }

    public T getItem() {
        return this.item;
    }

    public Action getAction() {
        return this.action;
    }

    public String getId() {
        return this.id;
    }
}
