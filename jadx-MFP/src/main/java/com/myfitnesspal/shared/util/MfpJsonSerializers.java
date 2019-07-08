package com.myfitnesspal.shared.util;

import com.myfitnesspal.shared.model.unitconv.LocalizedEnergy;
import com.myfitnesspal.shared.model.unitconv.LocalizedFluid;
import com.myfitnesspal.shared.model.unitconv.LocalizedLength;
import com.myfitnesspal.shared.model.unitconv.LocalizedWeight;
import com.myfitnesspal.shared.model.v1.FacebookFriend.DateWrapper;
import com.myfitnesspal.shared.model.v1.FacebookFriend.DateWrapper.Deserializer;
import com.myfitnesspal.shared.model.v1.FacebookFriend.DateWrapper.Serializer;

public final class MfpJsonSerializers {
    private static boolean registered;

    public static synchronized void register() {
        synchronized (MfpJsonSerializers.class) {
            if (!registered) {
                JsonSerializers.addTypeAdapter(DateWrapper.class, new Serializer());
                JsonSerializers.addTypeAdapter(DateWrapper.class, new Deserializer());
                JsonSerializers.addTypeAdapter(LocalizedWeight.class, new LocalizedWeight.Serializer());
                JsonSerializers.addTypeAdapter(LocalizedWeight.class, new LocalizedWeight.Deserializer());
                JsonSerializers.addTypeAdapter(LocalizedLength.class, new LocalizedLength.Serializer());
                JsonSerializers.addTypeAdapter(LocalizedLength.class, new LocalizedLength.Deserializer());
                JsonSerializers.addTypeAdapter(LocalizedEnergy.class, new LocalizedEnergy.Serializer());
                JsonSerializers.addTypeAdapter(LocalizedEnergy.class, new LocalizedEnergy.Deserializer());
                JsonSerializers.addTypeAdapter(LocalizedFluid.class, new LocalizedFluid.Serializer());
                JsonSerializers.addTypeAdapter(LocalizedFluid.class, new LocalizedFluid.Deserializer());
                registered = true;
            }
        }
    }
}
