package com.inmobi.a;

import com.inmobi.commons.core.network.c;
import com.inmobi.commons.core.utilities.uid.d;
import com.myfitnesspal.shared.constants.HttpConstants;

/* compiled from: CarbGetListNetworkRequest */
public final class b extends c {
    private int a;
    private int b;

    b(String str, int i, int i2, d dVar) {
        super(HttpConstants.METHOD_POST, str, true, dVar);
        this.a = i;
        this.b = i2;
    }
}
