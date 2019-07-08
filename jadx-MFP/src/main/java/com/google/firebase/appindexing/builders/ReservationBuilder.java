package com.google.firebase.appindexing.builders;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Date;

public final class ReservationBuilder extends IndexableBuilder<ReservationBuilder> {
    ReservationBuilder() {
        super("Reservation");
    }

    public final ReservationBuilder setStartDate(@NonNull Date date) {
        Preconditions.checkNotNull(date);
        return (ReservationBuilder) put("startDate", date.getTime());
    }

    public final ReservationBuilder setPartySize(@NonNull long j) {
        return (ReservationBuilder) put("partySize", j);
    }

    public final ReservationBuilder setReservationFor(@NonNull LocalBusinessBuilder localBusinessBuilder) {
        return (ReservationBuilder) put("reservationFor", (S[]) new LocalBusinessBuilder[]{localBusinessBuilder});
    }
}
