package com.facebook.ads;

import java.io.Serializable;

public class RewardData implements Serializable {
    public static final long serialVersionUID = 1;
    private String a;
    private String b;

    public RewardData(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public String getCurrency() {
        return this.b;
    }

    public String getUserID() {
        return this.a;
    }
}
