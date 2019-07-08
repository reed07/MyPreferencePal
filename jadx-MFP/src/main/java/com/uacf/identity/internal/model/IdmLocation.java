package com.uacf.identity.internal.model;

import com.google.gson.annotations.Expose;
import com.uacf.core.util.Strings;
import java.util.Arrays;

public class IdmLocation {
    @Expose
    private String country;
    @Expose
    private String locality;
    @Expose
    private String postalCode;
    @Expose
    private String region;

    public String getRegion() {
        return this.region;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public String getCountry() {
        return this.country;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        IdmLocation idmLocation = (IdmLocation) obj;
        if (!Strings.equalsIgnoreCase(this.locality, idmLocation.locality) || !Strings.equalsIgnoreCase(this.region, idmLocation.region) || !Strings.equalsIgnoreCase(this.postalCode, idmLocation.postalCode) || !Strings.equalsIgnoreCase(this.country, idmLocation.country)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.locality, this.region, this.postalCode, this.country});
    }
}
