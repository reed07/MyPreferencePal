package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.myfitnesspal.shared.service.syncv1.PacketTypes;
import com.myfitnesspal.shared.validation.Validator;
import javax.inject.Inject;
import javax.inject.Named;

public class EmailUniquenessCheckRequestPacket extends ApiRequestPacketImpl {
    private String emailAddress;
    @Inject
    @Named("emailValidator")
    Validator emailValidator;

    public EmailUniquenessCheckRequestPacket(String str) {
        super(PacketTypes.EmailUniquenessCheckRequest);
        MyFitnessPalApp.getInstance().component().inject(this);
        this.emailAddress = str;
    }

    /* access modifiers changed from: protected */
    public boolean validatePacketData() {
        return this.emailValidator.validate(this.emailAddress);
    }

    /* access modifiers changed from: protected */
    public void writeDataInternal(BinaryEncoder binaryEncoder) {
        binaryEncoder.writeString(this.emailAddress);
    }
}
