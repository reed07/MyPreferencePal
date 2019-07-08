package android.support.v4.os;

import android.support.annotation.RestrictTo;

@RestrictTo
interface LocaleListInterface {
    boolean equals(Object obj);

    Object getLocaleList();

    int hashCode();

    String toString();
}
