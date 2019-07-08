package com.uacf.identity.internal.model;

import com.google.gson.annotations.Expose;

public final class IdmDecodedIdToken {
    @Expose
    private String sub;

    public String getSub() {
        return this.sub;
    }
}
