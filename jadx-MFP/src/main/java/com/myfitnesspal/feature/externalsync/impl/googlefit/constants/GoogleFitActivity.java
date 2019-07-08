package com.myfitnesspal.feature.externalsync.impl.googlefit.constants;

import com.google.android.gms.fitness.FitnessActivities;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum GoogleFitActivity {
    AEROBICS(9, FitnessActivities.AEROBICS),
    BADMINTON(10, FitnessActivities.BADMINTON),
    BASEBALL(11, FitnessActivities.BASEBALL),
    BASKETBALL(12, FitnessActivities.BASKETBALL),
    BIATHLON(13, FitnessActivities.BIATHLON),
    BIKING(1, FitnessActivities.BIKING),
    BIKING_HAND(14, FitnessActivities.BIKING_HAND),
    BIKING_MOUNTAIN(15, FitnessActivities.BIKING_MOUNTAIN),
    BIKING_ROAD(16, FitnessActivities.BIKING_ROAD),
    BIKING_SPINNING(17, FitnessActivities.BIKING_SPINNING),
    BIKING_STATIONARY(18, FitnessActivities.BIKING_STATIONARY),
    BIKING_UTILITY(19, FitnessActivities.BIKING_UTILITY),
    BOXING(20, FitnessActivities.BOXING),
    CALISTHENICS(21, FitnessActivities.CALISTHENICS),
    CIRCUIT_TRAINING(22, FitnessActivities.CIRCUIT_TRAINING),
    CRICKET(23, FitnessActivities.CRICKET),
    CURLING(106, FitnessActivities.CURLING),
    DANCING(24, FitnessActivities.DANCING),
    DIVING(102, FitnessActivities.DIVING),
    ELLIPTICAL(25, FitnessActivities.ELLIPTICAL),
    ERGOMETER(103, FitnessActivities.ERGOMETER),
    FENCING(26, FitnessActivities.FENCING),
    FOOTBALL_AMERICAN(27, FitnessActivities.FOOTBALL_AMERICAN),
    FOOTBALL_AUSTRALIAN(28, FitnessActivities.FOOTBALL_AUSTRALIAN),
    FOOTBALL_SOCCER(29, FitnessActivities.FOOTBALL_SOCCER),
    FRISBEE_DISC(30, FitnessActivities.FRISBEE_DISC),
    GARDENING(31, FitnessActivities.GARDENING),
    GOLF(32, FitnessActivities.GOLF),
    GYMNASTICS(33, FitnessActivities.GYMNASTICS),
    HANDBALL(34, FitnessActivities.HANDBALL),
    HIKING(35, FitnessActivities.HIKING),
    HOCKEY(36, FitnessActivities.HOCKEY),
    HORSEBACK_RIDING(37, FitnessActivities.HORSEBACK_RIDING),
    HOUSEWORK(38, FitnessActivities.HOUSEWORK),
    ICE_SKATING(104, FitnessActivities.ICE_SKATING),
    JUMP_ROPE(39, FitnessActivities.JUMP_ROPE),
    KAYAKING(40, FitnessActivities.KAYAKING),
    KICKBOXING(42, FitnessActivities.KICKBOXING),
    MARTIAL_ARTS(44, FitnessActivities.MARTIAL_ARTS),
    MARTIAL_ARTS_MIXED(46, FitnessActivities.MIXED_MARTIAL_ARTS),
    ON_FOOT(2, FitnessActivities.ON_FOOT),
    PILATES(49, FitnessActivities.PILATES),
    POLO(50, FitnessActivities.POLO),
    RACQUETBALL(51, FitnessActivities.RACQUETBALL),
    ROCK_CLIMBING(52, FitnessActivities.ROCK_CLIMBING),
    ROWING(53, FitnessActivities.ROWING),
    ROWING_MACHINE(54, FitnessActivities.ROWING_MACHINE),
    RUGBY(55, FitnessActivities.RUGBY),
    RUNNING(8, FitnessActivities.RUNNING),
    RUNNING_JOGGING(56, FitnessActivities.RUNNING_JOGGING),
    RUNNING_SAND(57, FitnessActivities.RUNNING_SAND),
    RUNNING_TREADMILL(58, FitnessActivities.RUNNING_TREADMILL),
    SAILING(59, FitnessActivities.SAILING),
    SCUBA_DIVING(60, FitnessActivities.SCUBA_DIVING),
    SKATEBOARDING(61, FitnessActivities.SKATEBOARDING),
    SKATING(62, FitnessActivities.SKATING),
    SKATING_CROSS(63, FitnessActivities.SKATING_CROSS),
    SKATING_INDOOR(105, FitnessActivities.SKATING_INDOOR),
    SKATING_INLINE(64, FitnessActivities.SKATING_INLINE),
    SKIING(65, FitnessActivities.SKIING),
    SKIING_BACK_COUNTRY(66, FitnessActivities.SKIING_BACK_COUNTRY),
    SKIING_CROSS_COUNTRY(67, FitnessActivities.SKIING_CROSS_COUNTRY),
    SKIING_DOWNHILL(68, FitnessActivities.SKIING_DOWNHILL),
    SKIING_KITE(69, FitnessActivities.SKIING_KITE),
    SKIING_ROLLER(70, FitnessActivities.SKIING_ROLLER),
    SLEDDING(71, FitnessActivities.SLEDDING),
    SNOWBOARDING(73, FitnessActivities.SNOWBOARDING),
    SNOWMOBILE(74, FitnessActivities.SNOWMOBILE),
    SNOWSHOEING(75, FitnessActivities.SNOWSHOEING),
    SQUASH(76, FitnessActivities.SQUASH),
    STAIR_CLIMBING(77, FitnessActivities.STAIR_CLIMBING),
    STAIR_CLIMBING_MACHINE(78, FitnessActivities.STAIR_CLIMBING_MACHINE),
    STANDUP_PADDLEBOARDING(79, FitnessActivities.STANDUP_PADDLEBOARDING),
    SURFING(81, FitnessActivities.SURFING),
    SWIMMING(82, FitnessActivities.SWIMMING),
    SWIMMING_POOL(83, FitnessActivities.SWIMMING_POOL),
    SWIMMING_OPEN_POOL(84, FitnessActivities.SWIMMING_OPEN_WATER),
    TABLE_TENNIS(85, FitnessActivities.TABLE_TENNIS),
    TENNINS(87, FitnessActivities.TENNIS),
    TILTING(5, FitnessActivities.TILTING),
    TREADMILL(88, FitnessActivities.TREADMILL),
    VOLLEYBALL(89, FitnessActivities.VOLLEYBALL),
    VOLLEYBALL_BEACH(90, FitnessActivities.VOLLEYBALL_BEACH),
    VOLLEYBALL_INDOOR(91, FitnessActivities.VOLLEYBALL_INDOOR),
    WALKING_FITNESS(93, FitnessActivities.WALKING_FITNESS),
    WALKING_NORDIC(94, FitnessActivities.WALKING_NORDIC),
    WALKING_TREADMILL(95, FitnessActivities.WALKING_TREADMILL),
    WATER_POLO(96, FitnessActivities.WATER_POLO),
    WINDSURFING(99, FitnessActivities.WINDSURFING),
    YOGA(100, FitnessActivities.YOGA);
    
    private static final Map<Integer, String> idToValueMap = null;
    private final int id;
    private final String name;

    static {
        idToValueMap = new HashMap();
        Iterator it = EnumSet.allOf(GoogleFitActivity.class).iterator();
        while (it.hasNext()) {
            GoogleFitActivity googleFitActivity = (GoogleFitActivity) it.next();
            idToValueMap.put(Integer.valueOf(googleFitActivity.getId()), googleFitActivity.getName());
        }
    }

    private GoogleFitActivity(int i, String str) {
        this.id = i;
        this.name = str;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public static String forId(int i) {
        return (String) idToValueMap.get(Integer.valueOf(i));
    }

    public static boolean containsId(int i) {
        return idToValueMap.containsKey(Integer.valueOf(i));
    }
}
