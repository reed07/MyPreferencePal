package com.myfitnesspal.shared.model.v15;

import com.myfitnesspal.shared.model.v15.BinaryApiSerializable.BinaryCreator;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;

public class UsernameSuggestionObject implements BinaryApiSerializable {
    public static final BinaryCreator<UsernameSuggestionObject> BINARY_CREATOR = new BinaryCreator<UsernameSuggestionObject>() {
        public UsernameSuggestionObject create(BinaryDecoder binaryDecoder) {
            UsernameSuggestionObject usernameSuggestionObject = new UsernameSuggestionObject();
            usernameSuggestionObject.readData(binaryDecoder);
            return usernameSuggestionObject;
        }
    };
    private String username;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public void writeData(BinaryEncoder binaryEncoder) {
        binaryEncoder.writeString(this.username);
    }

    public void readData(BinaryDecoder binaryDecoder) {
        this.username = binaryDecoder.decodeString();
    }
}
