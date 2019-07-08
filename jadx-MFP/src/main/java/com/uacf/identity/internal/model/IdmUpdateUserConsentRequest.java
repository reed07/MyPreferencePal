package com.uacf.identity.internal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class IdmUpdateUserConsentRequest {
    @SerializedName("account_links")
    @Expose
    private List<IdmAccountLink> accountLinks;
    @Expose
    private User user;

    private static final class User {
        @Expose
        private final String region;

        private User(String str) {
            this.region = str;
        }
    }

    public IdmUpdateUserConsentRequest(String str, List<IdmAccountLink> list) {
        this.user = new User(str);
        this.accountLinks = list;
    }
}
