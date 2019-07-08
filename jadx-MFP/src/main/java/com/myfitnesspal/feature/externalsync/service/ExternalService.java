package com.myfitnesspal.feature.externalsync.service;

public interface ExternalService {
    boolean enabled();

    void sync();
}
