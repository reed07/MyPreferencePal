package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.shared.model.v15.ExerciseSearchRequestObject;

public class ExerciseSearchRequestPacket extends SearchRequestPacket {
    public ExerciseSearchRequestPacket(int i, String str, int i2) {
        super(new ExerciseSearchRequestObject(i, str, i2));
    }
}
