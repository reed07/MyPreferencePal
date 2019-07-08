package com.uacf.identity.internal.model;

import com.google.gson.annotations.Expose;

public class IdmKeyDesc {
    @Expose
    private String alg;
    @Expose
    private String e;
    @Expose
    private String k;
    @Expose
    private String kid;
    @Expose
    private String n;
    @Expose
    private String use;

    public String getE() {
        return this.e;
    }

    public String getUse() {
        return this.use;
    }

    public String getKid() {
        return this.kid;
    }

    public String getAlg() {
        return this.alg;
    }

    public String getN() {
        return this.n;
    }

    public String getK() {
        return this.k;
    }
}
