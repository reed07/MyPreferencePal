package com.myfitnesspal.shared.model.v1;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.util.Gender;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FacebookFriend extends Friend {
    public static final Creator<FacebookFriend> CREATOR = new Creator<FacebookFriend>() {
        public FacebookFriend createFromParcel(Parcel parcel) {
            FacebookFriend facebookFriend = new FacebookFriend();
            facebookFriend.readFromParcel(parcel);
            return facebookFriend;
        }

        public FacebookFriend[] newArray(int i) {
            return new FacebookFriend[i];
        }
    };
    @Expose
    private DateWrapper birthday;
    @Expose
    private String email;
    @Expose
    private Gender gender = Gender.Unknown;
    @Expose
    private String name;
    @Expose
    private float timezone;
    @Expose
    private String username;

    public static class DateWrapper {
        @Expose
        private Date date;

        public static class Deserializer implements JsonDeserializer<DateWrapper> {
            /* JADX WARNING: Can't wrap try/catch for region: R(3:4|5|6) */
            /* JADX WARNING: Code restructure failed: missing block: B:6:0x0025, code lost:
                return new com.myfitnesspal.shared.model.v1.FacebookFriend.DateWrapper(r4.parse(r2));
             */
            /* JADX WARNING: Code restructure failed: missing block: B:7:0x0026, code lost:
                r2 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:8:0x0027, code lost:
                com.uacf.core.util.Ln.e(r2);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:9:0x002b, code lost:
                return null;
             */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x001c */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.myfitnesspal.shared.model.v1.FacebookFriend.DateWrapper deserialize(com.google.gson.JsonElement r2, java.lang.reflect.Type r3, com.google.gson.JsonDeserializationContext r4) throws com.google.gson.JsonParseException {
                /*
                    r1 = this;
                    java.text.SimpleDateFormat r3 = new java.text.SimpleDateFormat
                    java.lang.String r4 = "MM/dd/yyyy"
                    r3.<init>(r4)
                    java.text.SimpleDateFormat r4 = new java.text.SimpleDateFormat
                    java.lang.String r0 = "MM/dd"
                    r4.<init>(r0)
                    java.lang.String r2 = r2.getAsString()
                    com.myfitnesspal.shared.model.v1.FacebookFriend$DateWrapper r0 = new com.myfitnesspal.shared.model.v1.FacebookFriend$DateWrapper     // Catch:{ ParseException -> 0x001c }
                    java.util.Date r3 = r3.parse(r2)     // Catch:{ ParseException -> 0x001c }
                    r0.<init>(r3)     // Catch:{ ParseException -> 0x001c }
                    return r0
                L_0x001c:
                    com.myfitnesspal.shared.model.v1.FacebookFriend$DateWrapper r3 = new com.myfitnesspal.shared.model.v1.FacebookFriend$DateWrapper     // Catch:{ ParseException -> 0x0026 }
                    java.util.Date r2 = r4.parse(r2)     // Catch:{ ParseException -> 0x0026 }
                    r3.<init>(r2)     // Catch:{ ParseException -> 0x0026 }
                    return r3
                L_0x0026:
                    r2 = move-exception
                    com.uacf.core.util.Ln.e(r2)
                    r2 = 0
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.model.v1.FacebookFriend.DateWrapper.Deserializer.deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext):com.myfitnesspal.shared.model.v1.FacebookFriend$DateWrapper");
            }
        }

        public static class Serializer implements JsonSerializer<DateWrapper> {
            public JsonElement serialize(DateWrapper dateWrapper, Type type, JsonSerializationContext jsonSerializationContext) {
                return new JsonPrimitive(new SimpleDateFormat("MM/dd/yyyy").format(dateWrapper.getDate()));
            }
        }

        public DateWrapper(Date date2) {
            this.date = date2;
        }

        public Date getDate() {
            return this.date;
        }
    }

    /* access modifiers changed from: protected */
    public void readFromParcel(Parcel parcel) {
        super.readFromParcel(parcel);
        long readLong = parcel.readLong();
        this.birthday = new DateWrapper(readLong < 0 ? null : new Date(readLong));
        this.gender = Gender.fromString(parcel.readString());
        this.email = parcel.readString();
        this.username = parcel.readString();
        this.name = parcel.readString();
        this.timezone = parcel.readFloat();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        DateWrapper dateWrapper = this.birthday;
        parcel.writeLong((dateWrapper == null || dateWrapper.getDate() == null) ? -1 : this.birthday.getDate().getTime());
        parcel.writeString(Strings.toString(this.gender));
        parcel.writeString(Strings.toString(this.email));
        parcel.writeString(Strings.toString(this.username));
        parcel.writeString(Strings.toString(this.name));
        parcel.writeFloat(this.timezone);
    }

    public Date getBirthday() {
        return this.birthday.getDate();
    }

    public void setBirthday(Date date) {
        this.birthday = new DateWrapper(date);
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender2) {
        this.gender = gender2;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public float getTimezone() {
        return this.timezone;
    }

    public void setTimezone(float f) {
        this.timezone = f;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof FacebookFriend)) {
            return false;
        }
        FacebookFriend facebookFriend = (FacebookFriend) obj;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        if (Strings.equals(facebookFriend.getId(), getId()) && Strings.equals(facebookFriend.getEmail(), getEmail()) && ((facebookFriend.getBirthday() == getBirthday() || Strings.equals(simpleDateFormat.format(facebookFriend.getBirthday()), simpleDateFormat.format(getBirthday()))) && facebookFriend.getGender() == getGender())) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return NumberUtils.tryParseInt(getId());
    }

    public String toString() {
        return String.format("id = %s, email = %s, gender = %s, birthday = %s, tz = %s", new Object[]{getId(), this.email, this.gender, this.birthday, Float.valueOf(this.timezone)});
    }

    public boolean matches(String str) {
        return Strings.equals(getId(), str);
    }
}
