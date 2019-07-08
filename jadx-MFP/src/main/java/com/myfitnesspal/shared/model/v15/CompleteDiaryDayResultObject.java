package com.myfitnesspal.shared.model.v15;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.shared.model.v15.BinaryApiSerializable.BinaryCreator;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.uacf.core.exception.UacfNotImplementedException;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import java.util.Locale;
import java.util.Map;

public class CompleteDiaryDayResultObject implements BinaryApiSerializable {
    public static final BinaryCreator<CompleteDiaryDayResultObject> BINARY_CREATOR = new BinaryCreator<CompleteDiaryDayResultObject>() {
        public CompleteDiaryDayResultObject create(BinaryDecoder binaryDecoder) {
            CompleteDiaryDayResultObject completeDiaryDayResultObject = new CompleteDiaryDayResultObject();
            completeDiaryDayResultObject.readData(binaryDecoder);
            return completeDiaryDayResultObject;
        }
    };
    private Map<String, String> properties;

    private static final class PropertyNames {
        public static final String PROJECTED_WEIGHT_IN_POUNDS = "projected_weight_in_pounds";
        public static final String RECOMMENDATION_TEXT = "recommendation_text";

        private PropertyNames() {
        }
    }

    public float getProjectedWeightInPounds() {
        return NumberUtils.tryParseFloat(getProperty(PropertyNames.PROJECTED_WEIGHT_IN_POUNDS), BitmapDescriptorFactory.HUE_RED, Locale.US);
    }

    public String getRecommendationText() {
        return getProperty(PropertyNames.RECOMMENDATION_TEXT);
    }

    private String getProperty(String str) {
        if (Strings.notEmpty(str)) {
            Map<String, String> map = this.properties;
            if (map != null) {
                return (String) map.get(str);
            }
        }
        return null;
    }

    public void writeData(BinaryEncoder binaryEncoder) {
        throw new RuntimeException(new UacfNotImplementedException());
    }

    public void readData(BinaryDecoder binaryDecoder) {
        this.properties = binaryDecoder.decodeStringToStringMap();
    }
}
