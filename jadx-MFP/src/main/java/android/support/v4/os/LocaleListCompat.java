package android.support.v4.os;

import android.os.Build.VERSION;
import android.os.LocaleList;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import java.util.Locale;

public final class LocaleListCompat {
    static final LocaleListInterface IMPL;
    private static final LocaleListCompat sEmptyLocaleList = new LocaleListCompat();

    @RequiresApi
    static class LocaleListCompatApi24Impl implements LocaleListInterface {
        private LocaleList mLocaleList = new LocaleList(new Locale[0]);

        LocaleListCompatApi24Impl() {
        }

        public Object getLocaleList() {
            return this.mLocaleList;
        }

        public boolean equals(Object obj) {
            return this.mLocaleList.equals(((LocaleListCompat) obj).unwrap());
        }

        public int hashCode() {
            return this.mLocaleList.hashCode();
        }

        public String toString() {
            return this.mLocaleList.toString();
        }
    }

    static class LocaleListCompatBaseImpl implements LocaleListInterface {
        private LocaleListHelper mLocaleList = new LocaleListHelper(new Locale[0]);

        LocaleListCompatBaseImpl() {
        }

        public Object getLocaleList() {
            return this.mLocaleList;
        }

        public boolean equals(Object obj) {
            return this.mLocaleList.equals(((LocaleListCompat) obj).unwrap());
        }

        public int hashCode() {
            return this.mLocaleList.hashCode();
        }

        public String toString() {
            return this.mLocaleList.toString();
        }
    }

    static {
        if (VERSION.SDK_INT >= 24) {
            IMPL = new LocaleListCompatApi24Impl();
        } else {
            IMPL = new LocaleListCompatBaseImpl();
        }
    }

    private LocaleListCompat() {
    }

    @Nullable
    public Object unwrap() {
        return IMPL.getLocaleList();
    }

    public boolean equals(Object obj) {
        return IMPL.equals(obj);
    }

    public int hashCode() {
        return IMPL.hashCode();
    }

    public String toString() {
        return IMPL.toString();
    }
}
