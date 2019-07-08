package com.myfitnesspal.shared.model.v15;

import com.myfitnesspal.shared.model.v15.BinaryApiSerializable.BinaryCreator;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.uacf.core.util.Strings;
import java.util.List;

public class UsernameValidationObject implements BinaryApiSerializable {
    public static final BinaryCreator<UsernameValidationObject> BINARY_CREATOR = new BinaryCreator<UsernameValidationObject>() {
        public UsernameValidationObject create(BinaryDecoder binaryDecoder) {
            UsernameValidationObject usernameValidationObject = new UsernameValidationObject();
            usernameValidationObject.readData(binaryDecoder);
            return usernameValidationObject;
        }
    };
    private String errorMessage;
    private boolean isValid;
    private List<String> suggestedUsernames;

    public String toString() {
        return String.format("{ isValid=%s, error='%s', suggested=[%s] }", new Object[]{Boolean.valueOf(this.isValid), Strings.toString(this.errorMessage), Strings.toString(this.suggestedUsernames)});
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public boolean isValid() {
        return this.isValid;
    }

    public List<String> getSuggestedUsernames() {
        return this.suggestedUsernames;
    }

    public void writeData(BinaryEncoder binaryEncoder) {
        binaryEncoder.writeBoolean(this.isValid);
        binaryEncoder.writeString(this.errorMessage);
        binaryEncoder.writeStringList(this.suggestedUsernames);
    }

    public void readData(BinaryDecoder binaryDecoder) {
        this.isValid = binaryDecoder.decodeBoolean();
        this.errorMessage = binaryDecoder.decodeString();
        this.suggestedUsernames = binaryDecoder.decodeStringList();
    }
}
