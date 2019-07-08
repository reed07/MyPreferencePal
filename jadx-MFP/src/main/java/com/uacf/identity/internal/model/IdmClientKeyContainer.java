package com.uacf.identity.internal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class IdmClientKeyContainer {
    @SerializedName("_embedded")
    @Expose
    private IdmKeyEmbedded embedded;

    private static final class IdmKeyEmbedded {
        /* access modifiers changed from: private */
        @Expose
        public IdmKeyList clientKeys;

        private IdmKeyEmbedded() {
        }
    }

    public List<IdmKeyInfo> getKeys() {
        IdmKeyEmbedded idmKeyEmbedded = this.embedded;
        if (idmKeyEmbedded == null || idmKeyEmbedded.clientKeys == null) {
            return null;
        }
        return this.embedded.clientKeys;
    }
}
