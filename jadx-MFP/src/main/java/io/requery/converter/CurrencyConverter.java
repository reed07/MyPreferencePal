package io.requery.converter;

import io.requery.Converter;
import java.util.Currency;
import javax.annotation.Nullable;

public class CurrencyConverter implements Converter<Currency, String> {
    public Class<Currency> getMappedType() {
        return Currency.class;
    }

    public Class<String> getPersistedType() {
        return String.class;
    }

    @Nullable
    public Integer getPersistedSize() {
        return Integer.valueOf(3);
    }

    public String convertToPersisted(Currency currency) {
        if (currency == null) {
            return null;
        }
        return currency.getCurrencyCode();
    }

    public Currency convertToMapped(Class<? extends Currency> cls, @Nullable String str) {
        if (str == null) {
            return null;
        }
        return Currency.getInstance(str);
    }
}
