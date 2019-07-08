package com.myfitnesspal.shared.model.v15;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.Reminder;
import com.myfitnesspal.shared.db.table.RemindersTable.StatusFlag;
import com.myfitnesspal.shared.model.MealNames;
import com.myfitnesspal.shared.model.v15.BinaryApiSerializable.BinaryCreator;
import com.myfitnesspal.shared.service.reminders.RemindersService;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.uacf.core.util.Ln;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.HashMap;
import java.util.Map;

public class ReminderObject extends BaseObject implements Parcelable {
    public static final BinaryCreator<ReminderObject> BINARY_CREATOR = new BinaryCreator<ReminderObject>() {
        public ReminderObject create(BinaryDecoder binaryDecoder) {
            ReminderObject reminderObject = new ReminderObject();
            reminderObject.readData(binaryDecoder);
            return reminderObject;
        }
    };
    public static final Creator<ReminderObject> CREATOR = new Creator<ReminderObject>() {
        public ReminderObject createFromParcel(Parcel parcel) {
            return new ReminderObject(parcel);
        }

        public ReminderObject[] newArray(int i) {
            return new ReminderObject[i];
        }
    };
    private static final int LAST_DAY = 32;
    @Expose
    private int flags;
    private String mealId;
    @Expose
    private Map<String, String> properties = new HashMap();
    private StatusFlag statusFlag = StatusFlag.DEFAULT;

    private static final class Flags {
        public static final int ACTIVE = 1;
        public static final int AUTO_CREATED = 2;

        private Flags() {
        }
    }

    private static final class PropertyNames {
        public static final String DAY_OF_MONTH = "day_of_month";
        public static final String DAY_OF_WEEK = "day_of_week";
        public static final String FREQUENCY = "frequency";
        public static final String INTERVAL_IN_DAYS = "interval_in_days";
        public static final String MEAL_NAME = "meal_name";
        public static final String REMINDER_TYPE = "reminder_type";
        public static final String WALL_CLOCK_TIME = "wall_clock_time";

        private PropertyNames() {
        }
    }

    private static final class ReminderTypeKeys {
        private static final String EXERCISE = "exercise";
        private static final String FOOD = "food";
        private static final String FOOD_OR_EXERCISE = "food_or_exercise";
        private static final String WEIGHT = "log_weight";

        private ReminderTypeKeys() {
        }
    }

    public static final class ReminderTypes {
        public static final int EXERCISE = 2;
        public static final int FOOD = 1;
        public static final int FOOD_OR_EXERCISE = 3;
        public static final int WEIGHT = 4;
    }

    public int describeContents() {
        return 0;
    }

    public String reminderTypeNameForCode(int i) {
        switch (i) {
            case 1:
                return "food";
            case 2:
                return "exercise";
            case 3:
                return Reminder.FOOD_OR_EXERCISE;
            case 4:
                return "log_weight";
            default:
                return "unknown";
        }
    }

    public ReminderObject() {
    }

    public ReminderObject(Parcel parcel) {
        readFromParcel(parcel);
    }

    public ReminderObject(ReminderObject reminderObject) {
        setReminderType(reminderObject.getReminderType());
        setMealName(reminderObject.getMealName());
        setMealId(reminderObject.getMealId());
        setIntervalInDays(reminderObject.getIntervalInDays());
        setWallClockTime(reminderObject.getWallClockTime());
        setFrequency(reminderObject.getFrequency());
        setDayOfMonth(reminderObject.getDayOfMonth());
        setDayOfWeek(reminderObject.getDayOfWeek());
        setFlags(reminderObject.flags);
        setLocalId(reminderObject.getLocalId());
        setMasterId(reminderObject.getMasterId());
        setUid(reminderObject.getUid());
        setStatusFlag(reminderObject.statusFlag);
    }

    public Map<String, String> getProperties() {
        return this.properties;
    }

    public void setProperties(Map<String, String> map) {
        if (map == null) {
            this.properties = new HashMap();
        } else {
            this.properties = map;
        }
    }

    public int getIntervalInDays() {
        return getIntProperty("interval_in_days", 1);
    }

    public void setIntervalInDays(int i) {
        setProperty("interval_in_days", Strings.toString(Integer.valueOf(i)));
    }

    public String getMealName() {
        return getProperty("meal_name", null);
    }

    public void setMealName(String str) {
        setProperty("meal_name", Strings.toString(str));
    }

    public String getMealId() {
        return this.mealId;
    }

    public void setMealId(String str) {
        this.mealId = str;
    }

    public int getReminderType() {
        String property = getProperty("reminder_type", null);
        if (Strings.equalsIgnoreCase(property, "food")) {
            return 1;
        }
        if (Strings.equalsIgnoreCase(property, "exercise")) {
            return 2;
        }
        return Strings.equalsIgnoreCase(property, "log_weight") ? 4 : 3;
    }

    public void setReminderType(int i) {
        String str = Reminder.FOOD_OR_EXERCISE;
        if (i != 4) {
            switch (i) {
                case 1:
                    str = "food";
                    break;
                case 2:
                    str = "exercise";
                    break;
            }
        } else {
            str = "log_weight";
        }
        setProperty("reminder_type", str);
    }

    public String getWallClockTime() {
        return getProperty("wall_clock_time", null);
    }

    public void setWallClockTime(String str) {
        setProperty("wall_clock_time", str);
    }

    public void setFrequency(String str) {
        setProperty("frequency", Strings.toString(str));
    }

    public String getFrequency() {
        return getProperty("frequency", null);
    }

    public void setDayOfMonth(int i) {
        setProperty("day_of_month", Strings.toString(Integer.valueOf(i)));
    }

    public int getDayOfMonth() {
        return getIntProperty("day_of_month", 0);
    }

    public void setDayOfWeek(String str) {
        setProperty("day_of_week", Strings.toString(str));
    }

    public String getDayOfWeek() {
        return getProperty("day_of_week", null);
    }

    public void setLocalTimeOfDay(int i, int i2, int i3) {
        setWallClockTime(String.format("%02d:%02d:%02d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)}));
    }

    public StatusFlag getStatusFlag() {
        return this.statusFlag;
    }

    public void setStatusFlag(StatusFlag statusFlag2) {
        this.statusFlag = statusFlag2;
    }

    public String description(Context context, Lazy<LocalizedStringsUtil> lazy, MealNames mealNames) {
        String str;
        String mealId2 = getMealId();
        int reminderType = getReminderType();
        if (Strings.notEmpty(mealId2) && (reminderType == 3 || reminderType == 1)) {
            return ((LocalizedStringsUtil) lazy.get()).getMealNameString(mealNames.nameForId(Long.valueOf(mealId2).longValue()), null);
        }
        int intervalInDays = getIntervalInDays();
        int reminderType2 = getReminderType();
        if (reminderType2 == 4) {
            return context.getString(R.string.weight);
        }
        switch (reminderType2) {
            case 1:
                str = context.getString(R.string.food);
                break;
            case 2:
                str = context.getString(R.string.exercise);
                break;
            default:
                str = context.getString(R.string.item);
                break;
        }
        String string = context.getString(R.string.reminder_desc);
        Object[] objArr = new Object[3];
        objArr[0] = str;
        objArr[1] = Integer.valueOf(intervalInDays);
        objArr[2] = context.getString(intervalInDays == 1 ? R.string.day : R.string.days);
        return String.format(string, objArr);
    }

    public String getDisplayDescription(Context context, Lazy<LocalizedStringsUtil> lazy, Lazy<RemindersService> lazy2, MealNames mealNames) {
        String str;
        if (getReminderType() != 4) {
            return description(context, lazy, mealNames);
        }
        if (Strings.equals(getFrequency(), Reminder.DAILY_FREQUENCY)) {
            return ((RemindersService) lazy2.get()).getLocalizedFrequencyString(getFrequency());
        }
        if (Strings.equals(getFrequency(), Reminder.WEEKLY_FREQUENCY)) {
            return context.getString(DateTimeUtils.getWeeklyOnDayOfWeek(getDayOfWeek()));
        }
        if (getDayOfMonth() == 32) {
            str = context.getString(R.string.last_day);
        } else {
            str = String.format(context.getString(R.string.monthly_weight_reminder_desc), new Object[]{NumberUtils.localeStringFromInt(getDayOfMonth())});
        }
        return str;
    }

    public String getReminderFrequencyOrTimeInterval(Context context) {
        return DateTimeUtils.localeFormattedTime(context, getWallClockTime());
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0044 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0045 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int reminderTypeCodeForName(java.lang.String r5) {
        /*
            r4 = this;
            java.lang.String r5 = com.uacf.core.util.Strings.toString(r5)
            java.lang.String r5 = r5.toLowerCase()
            int r0 = r5.hashCode()
            r1 = -1769855021(0xffffffff968227d3, float:-2.1027765E-25)
            r2 = 2
            r3 = 1
            if (r0 == r1) goto L_0x0032
            r1 = 3148894(0x300c5e, float:4.41254E-39)
            if (r0 == r1) goto L_0x0028
            r1 = 2056323544(0x7a9101d8, float:3.7646022E35)
            if (r0 == r1) goto L_0x001e
            goto L_0x003c
        L_0x001e:
            java.lang.String r0 = "exercise"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x003c
            r5 = 1
            goto L_0x003d
        L_0x0028:
            java.lang.String r0 = "food"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x003c
            r5 = 0
            goto L_0x003d
        L_0x0032:
            java.lang.String r0 = "log_weight"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x003c
            r5 = 2
            goto L_0x003d
        L_0x003c:
            r5 = -1
        L_0x003d:
            switch(r5) {
                case 0: goto L_0x0045;
                case 1: goto L_0x0044;
                case 2: goto L_0x0042;
                default: goto L_0x0040;
            }
        L_0x0040:
            r5 = 3
            return r5
        L_0x0042:
            r5 = 4
            return r5
        L_0x0044:
            return r2
        L_0x0045:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.model.v15.ReminderObject.reminderTypeCodeForName(java.lang.String):int");
    }

    public boolean hasSameValuesAs(ReminderObject reminderObject) {
        return isOfSameKindAs(reminderObject) && Strings.equalsIgnoreCase(getWallClockTime(), reminderObject.getWallClockTime());
    }

    public boolean isOfSameKindAs(ReminderObject reminderObject) {
        return getReminderType() == reminderObject.getReminderType() && getIntervalInDays() == reminderObject.getIntervalInDays() && reminderObject.getFlags() == getFlags() && Strings.equalsIgnoreCase(reminderObject.getFrequency(), getFrequency()) && reminderObject.getDayOfMonth() == getDayOfMonth() && Strings.equalsIgnoreCase(reminderObject.getDayOfWeek(), getDayOfWeek()) && Strings.equalsIgnoreCase(reminderObject.getMealId(), getMealId());
    }

    public void writeData(BinaryEncoder binaryEncoder) {
        binaryEncoder.write8ByteInt(getLocalId());
        binaryEncoder.write8ByteInt(getMasterId());
        binaryEncoder.writeString(getUid());
        binaryEncoder.writeStringToStringMap(this.properties);
        binaryEncoder.write2ByteInt(this.flags);
    }

    public void readData(BinaryDecoder binaryDecoder) {
        setLocalId(binaryDecoder.decode8ByteInt());
        setMasterId(binaryDecoder.decode8ByteInt());
        setUid(binaryDecoder.decodeString());
        this.properties = binaryDecoder.decodeStringToStringMap();
        this.flags = binaryDecoder.decode2ByteInt();
    }

    private String getProperty(String str, String str2) {
        Map<String, String> map = this.properties;
        return (map == null || !map.containsKey(str)) ? str2 : (String) this.properties.get(str);
    }

    private int getIntProperty(String str, int i) {
        String property = getProperty(str, null);
        if (!Strings.notEmpty(property)) {
            return i;
        }
        try {
            return Integer.valueOf(property).intValue();
        } catch (NumberFormatException e) {
            Ln.e(e);
            return i;
        }
    }

    private void setProperty(String str, String str2) {
        if (this.properties == null) {
            this.properties = new HashMap();
        }
        this.properties.put(str, str2);
    }

    public boolean isActive() {
        return (this.flags & 1) == 1;
    }

    public void setIsActive(boolean z) {
        if (z) {
            addFlags(1);
        } else {
            removeFlags(1);
        }
    }

    public boolean isAutoCreated() {
        return (this.flags & 2) == 2;
    }

    public void setIsAutoCreated(boolean z) {
        if (z) {
            addFlags(2);
        } else {
            removeFlags(2);
        }
    }

    public int getFlags() {
        return this.flags;
    }

    public void setFlags(int i) {
        this.flags = i;
    }

    public void addFlags(int i) {
        setFlags(i | getFlags());
    }

    public void removeFlags(int i) {
        setFlags((~i) & getFlags());
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, this.flags);
        parcel.writeMap(this.properties);
        parcel.writeInt(this.flags);
        parcel.writeInt(this.statusFlag.getValue());
        parcel.writeString(this.mealId);
    }

    public void readFromParcel(Parcel parcel) {
        super.readFromParcel(parcel);
        this.properties.clear();
        parcel.readMap(this.properties, String.class.getClassLoader());
        this.flags = parcel.readInt();
        this.statusFlag = StatusFlag.getStatusFlag(parcel.readInt());
        this.mealId = parcel.readString();
    }
}
