package com.myfitnesspal.feature.uashop.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.myfitnesspal.shared.constants.Constants.ABTest.UAShopAndroid201511;
import com.myfitnesspal.shared.constants.Constants.Settings.App.URLs;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.util.Gender;
import dagger.Lazy;

public class UAShopHelper {
    public static void loadUAShopUrl(Context context, Lazy<Session> lazy, Lazy<ConfigService> lazy2) {
        StringBuilder sb = new StringBuilder();
        String genderString = ((Session) lazy.get()).getUser().getProfile().getGenderString();
        if (genderString == null) {
            genderString = Gender.Male.toString();
        }
        sb.append(Gender.isMale(genderString) ? URLs.UA_SHOP_MEN : URLs.UA_SHOP_WOMEN);
        sb.append(((ConfigService) lazy2.get()).getVariant(UAShopAndroid201511.NAME));
        context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(sb.toString())));
    }
}
