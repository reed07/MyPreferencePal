package com.brightcove.player.drm;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.brightcove.player.drm.CustomerRightsTokenConfig.Builder;
import java.util.Date;

public class CustomerRightsToken {
    private static final boolean STORE_LICENSE = true;
    public final OutputProtection outputProtection;
    public final Profile profile;
    public final Boolean storeLicense;

    static abstract class Profile {
        Profile() {
        }
    }

    public static class PurchaseLicense {
    }

    public static class PurchaseProfile extends Profile {
        public final PurchaseLicense purchase = new PurchaseLicense();
    }

    public static class RentalLicense {
        public final Date absoluteExpiration;
        public final Long playDuration;

        public RentalLicense(@NonNull Date date, @Nullable Long l) {
            this.absoluteExpiration = date;
            if (l == null) {
                l = Long.valueOf(Math.abs(date.getTime() - System.currentTimeMillis()));
            }
            this.playDuration = l;
        }
    }

    public static class RentalProfile extends Profile {
        public final RentalLicense rental;

        public RentalProfile(@NonNull Date date, @NonNull Long l) {
            this.rental = new RentalLicense(date, l);
        }
    }

    public CustomerRightsToken(Profile profile2, boolean z) {
        this(profile2, z, new Builder().build());
    }

    public CustomerRightsToken(Profile profile2, boolean z, @NonNull CustomerRightsTokenConfig customerRightsTokenConfig) {
        this.profile = profile2;
        this.storeLicense = Boolean.valueOf(z);
        this.outputProtection = customerRightsTokenConfig.getOutputProtection();
    }

    @NonNull
    public static CustomerRightsToken createRentalRightsToken(@NonNull Date date, @NonNull Long l, boolean z) {
        return new CustomerRightsToken(new RentalProfile(date, l), z);
    }

    @NonNull
    public static CustomerRightsToken createRentalRightsToken(@NonNull Date date, @NonNull Long l) {
        return createRentalRightsToken(date, l, true);
    }

    @NonNull
    public static CustomerRightsToken createRentalRightsToken(@NonNull Date date, @NonNull Long l, @NonNull CustomerRightsTokenConfig customerRightsTokenConfig) {
        return new CustomerRightsToken(new RentalProfile(date, l), true, customerRightsTokenConfig);
    }

    @NonNull
    public static CustomerRightsToken createPurchaseRightsToken() {
        return new CustomerRightsToken(new PurchaseProfile(), true);
    }

    @NonNull
    public static CustomerRightsToken createPurchaseRightsToken(@NonNull CustomerRightsTokenConfig customerRightsTokenConfig) {
        return new CustomerRightsToken(new PurchaseProfile(), true, customerRightsTokenConfig);
    }
}
