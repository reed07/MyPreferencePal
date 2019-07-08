package com.myfitnesspal.shared.model.v1;

import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.constants.Units;
import com.myfitnesspal.shared.util.UnitsUtils.Energy;
import com.myfitnesspal.shared.util.UnitsUtils.Length;
import com.myfitnesspal.shared.util.UnitsUtils.Water;
import com.myfitnesspal.shared.util.UnitsUtils.Weight;
import java.util.ArrayList;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\b\n\u0002\b\u0006\b\b\u0018\u0000 (2\u00020\u0001:\u0002()B=\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001f\u001a\u00020\tHÆ\u0003JA\u0010 \u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010!\u001a\u00020\u00072\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020$HÖ\u0001J\t\u0010%\u001a\u00020\u0003HÖ\u0001J\u0010\u0010&\u001a\u00020\u00072\b\u0010'\u001a\u0004\u0018\u00010\u0003R\u001e\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u000b\"\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000bR \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\b\u001a\u00020\t8\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R \u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0010\"\u0004\b\u001a\u0010\u0012¨\u0006*"}, d2 = {"Lcom/myfitnesspal/shared/model/v1/Country;", "", "shortName", "", "longName", "zipCodeFormat", "isFacebookSupported", "", "preferredUnits", "Lcom/myfitnesspal/shared/model/v1/PreferredUnits;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLcom/myfitnesspal/shared/model/v1/PreferredUnits;)V", "()Z", "setFacebookSupported", "(Z)V", "isUnitedStates", "getLongName", "()Ljava/lang/String;", "setLongName", "(Ljava/lang/String;)V", "getPreferredUnits", "()Lcom/myfitnesspal/shared/model/v1/PreferredUnits;", "setPreferredUnits", "(Lcom/myfitnesspal/shared/model/v1/PreferredUnits;)V", "getShortName", "setShortName", "getZipCodeFormat", "setZipCodeFormat", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "validateZipCode", "test", "Companion", "LIST_MAPPER", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: Country.kt */
public final class Country {
    @NotNull
    public static final String CANADA_SHORT = "CA";
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String JAPANESE_SHORT = "JP";
    @NotNull
    public static final String KOREA_SOUTH_SHORT = "KR";
    @NotNull
    public static final String SIMPLIFIED_CHINESE_SHORT = "TW";
    @NotNull
    public static final String TRADITIONAL_CHINESE_SHORT = "CN";
    @NotNull
    public static final String UNITED_STATES_LONG = "United States";
    @NotNull
    public static final String UNITED_STATES_SHORT = "US";
    @Expose
    private boolean isFacebookSupported;
    @Nullable
    @Expose
    private String longName;
    @NotNull
    @Expose
    private PreferredUnits preferredUnits;
    @Nullable
    @Expose
    private String shortName;
    @Nullable
    @Expose
    private String zipCodeFormat;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/myfitnesspal/shared/model/v1/Country$Companion;", "", "()V", "CANADA_SHORT", "", "JAPANESE_SHORT", "KOREA_SOUTH_SHORT", "SIMPLIFIED_CHINESE_SHORT", "TRADITIONAL_CHINESE_SHORT", "UNITED_STATES_LONG", "UNITED_STATES_SHORT", "newInstanceUSA", "Lcom/myfitnesspal/shared/model/v1/Country;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: Country.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final Country newInstanceUSA() {
            PreferredUnits newInstance = PreferredUnits.newInstance(Energy.CALORIES, Weight.POUNDS, Length.FEET_INCHES, Length.MILES, Water.FL_OZ);
            Intrinsics.checkExpressionValueIsNotNull(newInstance, "PreferredUnits.newInstan…r.FL_OZ\n                )");
            Country country = new Country(Country.UNITED_STATES_SHORT, Country.UNITED_STATES_LONG, "[0-9][0-9][0-9][0-9][0-9]", true, newInstance);
            return country;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/myfitnesspal/shared/model/v1/Country$LIST_MAPPER;", "Ljava/util/ArrayList;", "Lcom/myfitnesspal/shared/model/v1/Country;", "()V", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: Country.kt */
    public static final class LIST_MAPPER extends ArrayList<Country> {
        public /* bridge */ boolean contains(Country country) {
            return super.contains(country);
        }

        public final /* bridge */ boolean contains(Object obj) {
            if (obj instanceof Country) {
                return contains((Country) obj);
            }
            return false;
        }

        public /* bridge */ int getSize() {
            return super.size();
        }

        public /* bridge */ int indexOf(Country country) {
            return super.indexOf(country);
        }

        public final /* bridge */ int indexOf(Object obj) {
            if (obj instanceof Country) {
                return indexOf((Country) obj);
            }
            return -1;
        }

        public /* bridge */ int lastIndexOf(Country country) {
            return super.lastIndexOf(country);
        }

        public final /* bridge */ int lastIndexOf(Object obj) {
            if (obj instanceof Country) {
                return lastIndexOf((Country) obj);
            }
            return -1;
        }

        public final /* bridge */ Country remove(int i) {
            return removeAt(i);
        }

        public /* bridge */ boolean remove(Country country) {
            return super.remove(country);
        }

        public final /* bridge */ boolean remove(Object obj) {
            if (obj instanceof Country) {
                return remove((Country) obj);
            }
            return false;
        }

        public /* bridge */ Country removeAt(int i) {
            return (Country) super.remove(i);
        }

        public final /* bridge */ int size() {
            return getSize();
        }
    }

    public Country() {
        this(null, null, null, false, null, 31, null);
    }

    @NotNull
    public static /* synthetic */ Country copy$default(Country country, String str, String str2, String str3, boolean z, PreferredUnits preferredUnits2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = country.shortName;
        }
        if ((i & 2) != 0) {
            str2 = country.longName;
        }
        String str4 = str2;
        if ((i & 4) != 0) {
            str3 = country.zipCodeFormat;
        }
        String str5 = str3;
        if ((i & 8) != 0) {
            z = country.isFacebookSupported;
        }
        boolean z2 = z;
        if ((i & 16) != 0) {
            preferredUnits2 = country.preferredUnits;
        }
        return country.copy(str, str4, str5, z2, preferredUnits2);
    }

    @JvmStatic
    @NotNull
    public static final Country newInstanceUSA() {
        return Companion.newInstanceUSA();
    }

    @Nullable
    public final String component1() {
        return this.shortName;
    }

    @Nullable
    public final String component2() {
        return this.longName;
    }

    @Nullable
    public final String component3() {
        return this.zipCodeFormat;
    }

    public final boolean component4() {
        return this.isFacebookSupported;
    }

    @NotNull
    public final PreferredUnits component5() {
        return this.preferredUnits;
    }

    @NotNull
    public final Country copy(@Nullable String str, @Nullable String str2, @Nullable String str3, boolean z, @NotNull PreferredUnits preferredUnits2) {
        Intrinsics.checkParameterIsNotNull(preferredUnits2, "preferredUnits");
        Country country = new Country(str, str2, str3, z, preferredUnits2);
        return country;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof Country) {
                Country country = (Country) obj;
                if (Intrinsics.areEqual((Object) this.shortName, (Object) country.shortName) && Intrinsics.areEqual((Object) this.longName, (Object) country.longName) && Intrinsics.areEqual((Object) this.zipCodeFormat, (Object) country.zipCodeFormat)) {
                    if (!(this.isFacebookSupported == country.isFacebookSupported) || !Intrinsics.areEqual((Object) this.preferredUnits, (Object) country.preferredUnits)) {
                        return false;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        String str = this.shortName;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.longName;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.zipCodeFormat;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        boolean z = this.isFacebookSupported;
        if (z) {
            z = true;
        }
        int i2 = (hashCode3 + (z ? 1 : 0)) * 31;
        PreferredUnits preferredUnits2 = this.preferredUnits;
        if (preferredUnits2 != null) {
            i = preferredUnits2.hashCode();
        }
        return i2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Country(shortName=");
        sb.append(this.shortName);
        sb.append(", longName=");
        sb.append(this.longName);
        sb.append(", zipCodeFormat=");
        sb.append(this.zipCodeFormat);
        sb.append(", isFacebookSupported=");
        sb.append(this.isFacebookSupported);
        sb.append(", preferredUnits=");
        sb.append(this.preferredUnits);
        sb.append(")");
        return sb.toString();
    }

    public Country(@Nullable String str, @Nullable String str2, @Nullable String str3, boolean z, @NotNull PreferredUnits preferredUnits2) {
        Intrinsics.checkParameterIsNotNull(preferredUnits2, "preferredUnits");
        this.shortName = str;
        this.longName = str2;
        this.zipCodeFormat = str3;
        this.isFacebookSupported = z;
        this.preferredUnits = preferredUnits2;
    }

    public /* synthetic */ Country(String str, String str2, String str3, boolean z, PreferredUnits preferredUnits2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        PreferredUnits preferredUnits3;
        if ((i & 1) != 0) {
            str = null;
        }
        String str4 = (i & 2) != 0 ? null : str2;
        String str5 = (i & 4) != 0 ? null : str3;
        boolean z2 = (i & 8) != 0 ? true : z;
        if ((i & 16) != 0) {
            PreferredUnits preferredUnits4 = PreferredUnits.PREFERRED_UNITS_DEFAULT;
            Intrinsics.checkExpressionValueIsNotNull(preferredUnits4, "PreferredUnits.PREFERRED_UNITS_DEFAULT");
            preferredUnits3 = preferredUnits4;
        } else {
            preferredUnits3 = preferredUnits2;
        }
        this(str, str4, str5, z2, preferredUnits3);
    }

    @Nullable
    public final String getShortName() {
        return this.shortName;
    }

    public final void setShortName(@Nullable String str) {
        this.shortName = str;
    }

    @Nullable
    public final String getLongName() {
        return this.longName;
    }

    public final void setLongName(@Nullable String str) {
        this.longName = str;
    }

    @Nullable
    public final String getZipCodeFormat() {
        return this.zipCodeFormat;
    }

    public final void setZipCodeFormat(@Nullable String str) {
        this.zipCodeFormat = str;
    }

    public final boolean isFacebookSupported() {
        return this.isFacebookSupported;
    }

    public final void setFacebookSupported(boolean z) {
        this.isFacebookSupported = z;
    }

    @NotNull
    public final PreferredUnits getPreferredUnits() {
        return this.preferredUnits;
    }

    public final void setPreferredUnits(@NotNull PreferredUnits preferredUnits2) {
        Intrinsics.checkParameterIsNotNull(preferredUnits2, "<set-?>");
        this.preferredUnits = preferredUnits2;
    }

    public final boolean isUnitedStates() {
        return StringsKt.equals(Units.US, this.shortName, true);
    }

    public final boolean validateZipCode(@Nullable String str) {
        CharSequence charSequence = str;
        if (charSequence == null || charSequence.length() == 0) {
            return false;
        }
        CharSequence charSequence2 = this.zipCodeFormat;
        if (!(charSequence2 == null || charSequence2.length() == 0)) {
            CharSequence charSequence3 = this.zipCodeFormat;
            if ((charSequence3 == null || charSequence3.length() == 0) || !Pattern.matches(this.zipCodeFormat, charSequence)) {
                return false;
            }
        }
        return true;
    }
}
