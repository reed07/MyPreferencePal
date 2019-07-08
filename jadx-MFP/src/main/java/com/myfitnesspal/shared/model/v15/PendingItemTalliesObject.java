package com.myfitnesspal.shared.model.v15;

import com.myfitnesspal.shared.model.v15.BinaryApiSerializable.BinaryCreator;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.uacf.core.util.Strings;
import java.util.Map;

public class PendingItemTalliesObject implements BinaryApiSerializable {
    public static final BinaryCreator<PendingItemTalliesObject> BINARY_CREATOR = new BinaryCreator<PendingItemTalliesObject>() {
        public PendingItemTalliesObject create(BinaryDecoder binaryDecoder) {
            PendingItemTalliesObject pendingItemTalliesObject = new PendingItemTalliesObject();
            pendingItemTalliesObject.readData(binaryDecoder);
            return pendingItemTalliesObject;
        }
    };
    private Map<String, Long> values;

    public static final class ValueTypes {
        public static final String DIARY_COMMENTS_FROM_COACH = "diary_comments_from_coach";
        public static final String FRIEND_REQUESTS = "friend_requests";
        public static final String MESSAGES = "messages";
        public static final String MESSAGES_FROM_COACH = "messages_from_coach";
    }

    public long getProperty(String str) {
        if (Strings.notEmpty(str)) {
            Map<String, Long> map = this.values;
            if (map != null) {
                return ((Long) map.get(str)).longValue();
            }
        }
        return 0;
    }

    public int getTotal() {
        return getFriendRequestCount() + getMessageCount();
    }

    public int getFriendRequestCount() {
        return (int) getProperty(ValueTypes.FRIEND_REQUESTS);
    }

    public int getMessageCount() {
        return (int) getProperty("messages");
    }

    public int getMessagesFromCoachCount() {
        return (int) getProperty(ValueTypes.MESSAGES_FROM_COACH);
    }

    public int getDiaryCommentsFromCoachCount() {
        return (int) getProperty(ValueTypes.DIARY_COMMENTS_FROM_COACH);
    }

    public void writeData(BinaryEncoder binaryEncoder) {
        binaryEncoder.writeStringToLongMap(this.values);
    }

    public void readData(BinaryDecoder binaryDecoder) {
        this.values = binaryDecoder.decodeStringToLongMap();
    }
}
