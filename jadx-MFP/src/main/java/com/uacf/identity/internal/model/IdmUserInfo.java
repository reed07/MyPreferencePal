package com.uacf.identity.internal.model;

import com.google.gson.annotations.Expose;
import com.uacf.core.mapping.GsonMappableYmdDate;

public class IdmUserInfo {
    @Expose
    private GsonMappableYmdDate birthdate;
    @Expose
    private String displayName;
    @Expose
    private String firstName;
    @Expose
    private String fullName;
    @Expose
    private String gender;
    @Expose
    private Double height;
    @Expose
    private String lastName;
    @Expose
    private String locale;
    @Expose
    private IdmLocation location;
    @Expose
    private String profilePictureUri;
    @Expose
    private Long userId;
    @Expose
    private Double weight;

    private IdmUserInfo() {
    }

    public Long getUserId() {
        return this.userId;
    }

    public GsonMappableYmdDate getBirthdate() {
        return this.birthdate;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getProfilePictureUri() {
        return this.profilePictureUri;
    }

    public String getGender() {
        return this.gender;
    }

    public Double getWeight() {
        return this.weight;
    }

    public Double getHeight() {
        return this.height;
    }

    public String getLocale() {
        return this.locale;
    }

    public IdmLocation getLocation() {
        return this.location;
    }
}
