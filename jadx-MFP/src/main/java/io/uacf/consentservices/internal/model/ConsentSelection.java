package io.uacf.consentservices.internal.model;

import com.google.gson.annotations.Expose;
import java.util.List;

public class ConsentSelection {
    @Expose
    private List<String> optional;
    @Expose
    private List<String> required;
    @Expose
    private List<String> tos;

    public List<String> getTos() {
        return this.tos;
    }

    public List<String> getRequired() {
        return this.required;
    }

    public List<String> getOptional() {
        return this.optional;
    }
}
