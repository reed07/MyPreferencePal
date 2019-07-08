package com.myfitnesspal.feature.progress.service;

import android.content.SharedPreferences.Editor;
import com.myfitnesspal.feature.progress.service.ProgressCongratsService.GoalType;
import com.myfitnesspal.feature.progress.service.ProgressCongratsService.Message;
import com.myfitnesspal.feature.progress.service.ProgressCongratsService.MessageType;
import com.myfitnesspal.shared.model.unitconv.LocalizedWeight;
import com.myfitnesspal.shared.service.userdata.UserHeightService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.util.UnitsUtils.Weight;
import com.myfitnesspal.shared.util.UpdateWeightProxy;
import com.uacf.core.preferences.KeyedSharedPreferences;
import com.uacf.core.util.NumberUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProgressCongratsServiceImpl implements ProgressCongratsService {
    private static final double DEFAULT_WEIGHT_INCREMENT = 5.0d;
    private static final String KEY_PREFIX = "last_consumed_";
    private static final double KILOGRAM_WEIGHT_INCREMENT = 2.0d;
    private static Comparator<Message> MESSAGE_COMPARATOR = new Comparator<Message>() {
        public int compare(Message message, Message message2) {
            if (message.getType() == message2.getType()) {
                return Double.compare(Math.abs(message.getValue()), Math.abs(message2.getValue()));
            }
            return message.getType().compareTo(message2.getType());
        }
    };
    private static final double PERCENTAGE_INCREMENT = 25.0d;
    private static final double PERCENTAGE_MAX = 100.0d;
    private static final double PERCENTAGE_MIN = 51.0d;
    private KeyedSharedPreferences prefs;
    private Lazy<UserHeightService> userHeightService;
    private Lazy<UserWeightService> userWeightService;

    /* renamed from: com.myfitnesspal.feature.progress.service.ProgressCongratsServiceImpl$2 reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$myfitnesspal$shared$util$UnitsUtils$Weight = new int[Weight.values().length];

        static {
            try {
                $SwitchMap$com$myfitnesspal$shared$util$UnitsUtils$Weight[Weight.KILOGRAMS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public ProgressCongratsServiceImpl(Lazy<UserWeightService> lazy, Lazy<UserHeightService> lazy2, KeyedSharedPreferences keyedSharedPreferences) {
        this.userWeightService = lazy;
        this.userHeightService = lazy2;
        this.prefs = keyedSharedPreferences;
    }

    public List<Message> getPendingMessages() {
        ArrayList arrayList = new ArrayList();
        GoalType fromWeeklyChange = GoalType.fromWeeklyChange(((UserWeightService) this.userWeightService.get()).getGoalPerWeekWeight());
        if (fromWeeklyChange == GoalType.Maintain) {
            return arrayList;
        }
        LocalizedWeight fromPounds = LocalizedWeight.fromPounds((double) ((UserWeightService) this.userWeightService.get()).getStartingWeightInPounds());
        LocalizedWeight fromPounds2 = LocalizedWeight.fromPounds((double) ((UserWeightService) this.userWeightService.get()).getCurrentWeightInPounds());
        LocalizedWeight fromPounds3 = LocalizedWeight.fromPounds(getLastConsumedValueFor(MessageType.WeightAbsolute, fromPounds.getValue(Weight.POUNDS)));
        Weight userCurrentWeightUnit = ((UserWeightService) this.userWeightService.get()).getUserCurrentWeightUnit();
        double valueIncrement = getValueIncrement(userCurrentWeightUnit);
        double value = fromPounds2.getValue(userCurrentWeightUnit);
        int round = (int) Math.round(fromPounds3.getValue(userCurrentWeightUnit));
        double d = (double) (round - (round % ((int) valueIncrement)));
        boolean z = false;
        boolean z2 = value <= d - valueIncrement;
        if (fromWeeklyChange == GoalType.Gain) {
            z2 = value >= d + valueIncrement;
        }
        double value2 = fromPounds.getValue(Weight.POUNDS);
        double value3 = fromPounds2.getValue(Weight.POUNDS);
        double goalWeightInPounds = value2 - ((double) ((UserWeightService) this.userWeightService.get()).getGoalWeightInPounds());
        int i = round;
        double d2 = goalWeightInPounds == 0.0d ? 0.0d : ((value2 - value3) / goalWeightInPounds) * 100.0d;
        double lastConsumedValueFor = getLastConsumedValueFor(MessageType.WeightPercentage, 0.0d);
        if (UpdateWeightProxy.calculateBmi(value3, ((UserHeightService) this.userHeightService.get()).getCurrentHeightInInches()) < 18.5d) {
            return arrayList;
        }
        if (z2 && lastConsumedValueFor < 100.0d) {
            Message message = new Message(MessageType.WeightAbsolute, ((double) Math.abs(i)) - Math.abs(value), fromPounds2.getValue(Weight.POUNDS));
            arrayList.add(message);
        }
        if (!NumberUtils.areEffectivelyEqual(lastConsumedValueFor, d2) && d2 >= 100.0d) {
            z = true;
        }
        if ((lastConsumedValueFor < 100.0d && d2 >= PERCENTAGE_MIN && d2 - lastConsumedValueFor >= 25.0d) || z) {
            Message message2 = new Message(MessageType.WeightPercentage, d2, z ? d2 : d2 - (d2 % 25.0d));
            arrayList.add(message2);
        }
        Collections.sort(arrayList, MESSAGE_COMPARATOR);
        return arrayList;
    }

    public void markMessageConsumed(Message message) {
        this.prefs.edit().putString(key(message.getType()), String.valueOf(message.getStoredValue())).apply();
    }

    public void reset() {
        Editor edit = this.prefs.edit();
        for (MessageType key : MessageType.values()) {
            edit.remove(key(key));
        }
        edit.apply();
    }

    public double getValueIncrement(Weight weight) {
        return AnonymousClass2.$SwitchMap$com$myfitnesspal$shared$util$UnitsUtils$Weight[weight.ordinal()] != 1 ? DEFAULT_WEIGHT_INCREMENT : KILOGRAM_WEIGHT_INCREMENT;
    }

    private double getLastConsumedValueFor(MessageType messageType, double d) {
        try {
            return Double.parseDouble(this.prefs.getString(key(messageType), String.valueOf(d)));
        } catch (Exception unused) {
            return (double) this.prefs.getFloat(key(messageType), (float) d);
        }
    }

    private static String key(MessageType messageType) {
        StringBuilder sb = new StringBuilder();
        sb.append(KEY_PREFIX);
        sb.append(messageType.toString());
        return sb.toString();
    }
}
