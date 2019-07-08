package com.uacf.identity.internal.model;

import com.google.gson.annotations.Expose;

public class IdmKeyInfo {
    @Expose
    private String clientId;
    @Expose
    private IdmKeyDesc key;

    public IdmKeyDesc getKey() {
        return this.key;
    }

    public String getClientId() {
        return this.clientId;
    }
}
