package com.google.android.gms.internal.fitness;

import com.google.android.gms.fitness.FitnessActivities;

public final class zzfa {
    private static final String[] zzjb;

    public static int zzl(String str) {
        int i = 0;
        while (true) {
            String[] strArr = zzjb;
            if (i >= strArr.length) {
                return 4;
            }
            if (strArr[i].equals(str)) {
                return i;
            }
            i++;
        }
    }

    public static String getName(int i) {
        if (i >= 0) {
            String[] strArr = zzjb;
            if (i < strArr.length) {
                String str = strArr[i];
                return str == null ? "unknown" : str;
            }
        }
        return "unknown";
    }

    public static String getMimeType(String str) {
        String valueOf = String.valueOf(FitnessActivities.MIME_TYPE_PREFIX);
        String valueOf2 = String.valueOf(str);
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    static {
        String[] strArr = new String[122];
        zzjb = strArr;
        strArr[9] = FitnessActivities.AEROBICS;
        String[] strArr2 = zzjb;
        strArr2[119] = FitnessActivities.ARCHERY;
        strArr2[10] = FitnessActivities.BADMINTON;
        strArr2[11] = FitnessActivities.BASEBALL;
        strArr2[12] = FitnessActivities.BASKETBALL;
        strArr2[13] = FitnessActivities.BIATHLON;
        strArr2[1] = FitnessActivities.BIKING;
        strArr2[14] = FitnessActivities.BIKING_HAND;
        strArr2[15] = FitnessActivities.BIKING_MOUNTAIN;
        strArr2[16] = FitnessActivities.BIKING_ROAD;
        strArr2[17] = FitnessActivities.BIKING_SPINNING;
        strArr2[18] = FitnessActivities.BIKING_STATIONARY;
        strArr2[19] = FitnessActivities.BIKING_UTILITY;
        strArr2[20] = FitnessActivities.BOXING;
        strArr2[21] = FitnessActivities.CALISTHENICS;
        strArr2[22] = FitnessActivities.CIRCUIT_TRAINING;
        strArr2[23] = FitnessActivities.CRICKET;
        strArr2[113] = FitnessActivities.CROSSFIT;
        strArr2[106] = FitnessActivities.CURLING;
        strArr2[24] = FitnessActivities.DANCING;
        strArr2[102] = FitnessActivities.DIVING;
        strArr2[117] = FitnessActivities.ELEVATOR;
        strArr2[25] = FitnessActivities.ELLIPTICAL;
        strArr2[103] = FitnessActivities.ERGOMETER;
        strArr2[118] = FitnessActivities.ESCALATOR;
        strArr2[6] = "exiting_vehicle";
        strArr2[26] = FitnessActivities.FENCING;
        strArr2[121] = "flossing";
        strArr2[27] = FitnessActivities.FOOTBALL_AMERICAN;
        strArr2[28] = FitnessActivities.FOOTBALL_AUSTRALIAN;
        strArr2[29] = FitnessActivities.FOOTBALL_SOCCER;
        strArr2[30] = FitnessActivities.FRISBEE_DISC;
        strArr2[31] = FitnessActivities.GARDENING;
        strArr2[32] = FitnessActivities.GOLF;
        strArr2[33] = FitnessActivities.GYMNASTICS;
        strArr2[34] = FitnessActivities.HANDBALL;
        strArr2[114] = FitnessActivities.HIGH_INTENSITY_INTERVAL_TRAINING;
        strArr2[35] = FitnessActivities.HIKING;
        strArr2[36] = FitnessActivities.HOCKEY;
        strArr2[37] = FitnessActivities.HORSEBACK_RIDING;
        strArr2[38] = FitnessActivities.HOUSEWORK;
        strArr2[104] = FitnessActivities.ICE_SKATING;
        strArr2[0] = FitnessActivities.IN_VEHICLE;
        strArr2[115] = FitnessActivities.INTERVAL_TRAINING;
        strArr2[39] = FitnessActivities.JUMP_ROPE;
        strArr2[40] = FitnessActivities.KAYAKING;
        strArr2[41] = FitnessActivities.KETTLEBELL_TRAINING;
        strArr2[107] = FitnessActivities.KICK_SCOOTER;
        strArr2[42] = FitnessActivities.KICKBOXING;
        strArr2[43] = FitnessActivities.KITESURFING;
        strArr2[44] = FitnessActivities.MARTIAL_ARTS;
        strArr2[45] = FitnessActivities.MEDITATION;
        strArr2[46] = FitnessActivities.MIXED_MARTIAL_ARTS;
        strArr2[2] = FitnessActivities.ON_FOOT;
        strArr2[108] = "other";
        strArr2[47] = FitnessActivities.P90X;
        strArr2[48] = FitnessActivities.PARAGLIDING;
        strArr2[49] = FitnessActivities.PILATES;
        strArr2[50] = FitnessActivities.POLO;
        strArr2[51] = FitnessActivities.RACQUETBALL;
        strArr2[52] = FitnessActivities.ROCK_CLIMBING;
        strArr2[53] = FitnessActivities.ROWING;
        strArr2[54] = FitnessActivities.ROWING_MACHINE;
        strArr2[55] = FitnessActivities.RUGBY;
        strArr2[8] = FitnessActivities.RUNNING;
        strArr2[56] = FitnessActivities.RUNNING_JOGGING;
        strArr2[57] = FitnessActivities.RUNNING_SAND;
        strArr2[58] = FitnessActivities.RUNNING_TREADMILL;
        strArr2[59] = FitnessActivities.SAILING;
        strArr2[60] = FitnessActivities.SCUBA_DIVING;
        strArr2[61] = FitnessActivities.SKATEBOARDING;
        strArr2[62] = FitnessActivities.SKATING;
        strArr2[63] = FitnessActivities.SKATING_CROSS;
        strArr2[105] = FitnessActivities.SKATING_INDOOR;
        strArr2[64] = FitnessActivities.SKATING_INLINE;
        strArr2[65] = FitnessActivities.SKIING;
        strArr2[66] = FitnessActivities.SKIING_BACK_COUNTRY;
        strArr2[67] = FitnessActivities.SKIING_CROSS_COUNTRY;
        strArr2[68] = FitnessActivities.SKIING_DOWNHILL;
        strArr2[69] = FitnessActivities.SKIING_KITE;
        strArr2[70] = FitnessActivities.SKIING_ROLLER;
        strArr2[71] = FitnessActivities.SLEDDING;
        strArr2[72] = FitnessActivities.SLEEP;
        strArr2[109] = FitnessActivities.SLEEP_LIGHT;
        strArr2[110] = FitnessActivities.SLEEP_DEEP;
        strArr2[111] = FitnessActivities.SLEEP_REM;
        strArr2[112] = FitnessActivities.SLEEP_AWAKE;
        strArr2[73] = FitnessActivities.SNOWBOARDING;
        strArr2[74] = FitnessActivities.SNOWMOBILE;
        strArr2[75] = FitnessActivities.SNOWSHOEING;
        strArr2[120] = FitnessActivities.SOFTBALL;
        strArr2[76] = FitnessActivities.SQUASH;
        strArr2[77] = FitnessActivities.STAIR_CLIMBING;
        strArr2[78] = FitnessActivities.STAIR_CLIMBING_MACHINE;
        strArr2[79] = FitnessActivities.STANDUP_PADDLEBOARDING;
        strArr2[3] = FitnessActivities.STILL;
        strArr2[80] = FitnessActivities.STRENGTH_TRAINING;
        strArr2[81] = FitnessActivities.SURFING;
        strArr2[82] = FitnessActivities.SWIMMING;
        strArr2[83] = FitnessActivities.SWIMMING_POOL;
        strArr2[84] = FitnessActivities.SWIMMING_OPEN_WATER;
        strArr2[85] = FitnessActivities.TABLE_TENNIS;
        strArr2[86] = FitnessActivities.TEAM_SPORTS;
        strArr2[87] = FitnessActivities.TENNIS;
        strArr2[5] = FitnessActivities.TILTING;
        strArr2[88] = FitnessActivities.TREADMILL;
        strArr2[4] = "unknown";
        strArr2[89] = FitnessActivities.VOLLEYBALL;
        strArr2[90] = FitnessActivities.VOLLEYBALL_BEACH;
        strArr2[91] = FitnessActivities.VOLLEYBALL_INDOOR;
        strArr2[92] = FitnessActivities.WAKEBOARDING;
        strArr2[7] = FitnessActivities.WALKING;
        strArr2[93] = FitnessActivities.WALKING_FITNESS;
        strArr2[94] = FitnessActivities.WALKING_NORDIC;
        strArr2[95] = FitnessActivities.WALKING_TREADMILL;
        strArr2[116] = FitnessActivities.WALKING_STROLLER;
        strArr2[96] = FitnessActivities.WATER_POLO;
        strArr2[97] = FitnessActivities.WEIGHTLIFTING;
        strArr2[98] = FitnessActivities.WHEELCHAIR;
        strArr2[99] = FitnessActivities.WINDSURFING;
        strArr2[100] = FitnessActivities.YOGA;
        strArr2[101] = FitnessActivities.ZUMBA;
    }
}
