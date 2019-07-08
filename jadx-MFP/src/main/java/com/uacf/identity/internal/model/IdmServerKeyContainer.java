package com.uacf.identity.internal.model;

import com.google.gson.annotations.Expose;
import java.util.List;

public class IdmServerKeyContainer {
    @Expose
    private List<IdmKeyDesc> keys;

    public List<IdmKeyDesc> getKeys() {
        return this.keys;
    }
}
