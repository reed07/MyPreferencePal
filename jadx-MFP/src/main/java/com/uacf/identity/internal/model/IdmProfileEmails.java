package com.uacf.identity.internal.model;

import com.google.gson.annotations.Expose;
import java.util.List;

public class IdmProfileEmails {
    @Expose
    private List<IdmProfileEmail> emails;
    @Expose
    private boolean isEmailVerified;

    public boolean isEmailVerified() {
        return this.isEmailVerified;
    }

    public List<IdmProfileEmail> getEmails() {
        return this.emails;
    }
}
