package com.myfitnesspal.feature.progress.service;

import com.facebook.appevents.codeless.internal.Constants;
import com.myfitnesspal.shared.model.v2.MfpGoalPreferences.MacroGoalFormat;
import com.myfitnesspal.shared.util.UnitsUtils.Weight;
import java.util.Comparator;
import java.util.List;

public interface ProgressCongratsService {

    public enum GoalType {
        Lose,
        Gain,
        Maintain;

        public static GoalType fromWeeklyChange(float f) {
            int i = (((double) f) > 0.0d ? 1 : (((double) f) == 0.0d ? 0 : -1));
            if (i == 0) {
                return Maintain;
            }
            return i > 0 ? Lose : Gain;
        }
    }

    public static class Message {
        private double storedValue;
        private MessageType type;
        private double value;

        public Message(MessageType messageType, double d) {
            this(messageType, d, d);
        }

        public Message(MessageType messageType, double d, double d2) {
            this.type = messageType;
            this.value = d;
            this.storedValue = d2;
        }

        public MessageType getType() {
            return this.type;
        }

        public double getValue() {
            return this.value;
        }

        /* access modifiers changed from: 0000 */
        public double getStoredValue() {
            return this.storedValue;
        }
    }

    public enum MessageType implements Comparator<MessageType> {
        WeightPercentage(1, MacroGoalFormat.PERCENTAGE),
        WeightAbsolute(2, Constants.PATH_TYPE_ABSOLUTE);
        
        private String analyticValue;
        private int priority;

        private MessageType(int i, String str) {
            this.priority = i;
            this.analyticValue = str;
        }

        public String getAnalyticValue() {
            return this.analyticValue;
        }

        public int compare(MessageType messageType, MessageType messageType2) {
            return Integer.compare(messageType.priority, messageType2.priority);
        }
    }

    List<Message> getPendingMessages();

    double getValueIncrement(Weight weight);

    void markMessageConsumed(Message message);

    void reset();
}
