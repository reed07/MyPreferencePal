package com.myfitnesspal.shared.service.analytics;

import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.model.v2.MfpAnalyticsEvent;
import com.myfitnesspal.shared.model.v2.MfpAnalyticsEventsContainer;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MfpAnalyticsTask {
    private static Pattern FOODS_PATTERN = Pattern.compile("(\"foods\":)\"(\\[.*?\\])\"");
    @Expose
    private MfpAnalyticsEventsContainer eventContainer;

    public MfpAnalyticsTask() {
        this.eventContainer = new MfpAnalyticsEventsContainer();
    }

    public MfpAnalyticsTask(UUID uuid, String str, String str2) {
        this();
        this.eventContainer.setDeviceId(uuid);
        this.eventContainer.setClientId(str);
        this.eventContainer.setUserId(str2);
    }

    public MfpAnalyticsEventsContainer getEventContainer() {
        return this.eventContainer;
    }

    public MfpAnalyticsTask add(MfpAnalyticsEvent mfpAnalyticsEvent) {
        this.eventContainer.add(mfpAnalyticsEvent);
        return this;
    }

    public int getEventCount() {
        return this.eventContainer.getEvents().size();
    }

    public String toString() {
        return Strings.toString(this.eventContainer);
    }

    public String getSerializedData() {
        MfpAnalyticsEventsContainer eventContainer2 = getEventContainer();
        String reverseMap = new ApiJsonMapper().reverseMap((Object) eventContainer2);
        if (!Enumerable.any(eventContainer2.getEvents(), new ReturningFunction1<Boolean, MfpAnalyticsEvent>() {
            public Boolean execute(MfpAnalyticsEvent mfpAnalyticsEvent) {
                return Boolean.valueOf(Strings.equals(mfpAnalyticsEvent.getType(), Events.FOOD_LOGGED));
            }
        })) {
            return reverseMap;
        }
        Matcher matcher = FOODS_PATTERN.matcher(reverseMap);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            String group = matcher.group(1);
            String quoteReplacement = Matcher.quoteReplacement(matcher.group(2).replace("\\\"", "\"").replace("\\\\", "\\"));
            StringBuilder sb = new StringBuilder();
            sb.append(group);
            sb.append(quoteReplacement);
            matcher.appendReplacement(stringBuffer, sb.toString());
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }
}
