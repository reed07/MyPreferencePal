package com.amazon.device.ads;

import java.util.Arrays;
import java.util.List;

class DtbConstants {
    public static String AAX_HOSTNAME = "aax-us.amazon-adsystem.com";
    public static String AAX_ROUTE53_ENABLED_CNAME = "aax.amazon-adsystem.com";
    public static final List<String> BLACK_LIST = Arrays.asList(new String[]{"status", "errorCode", "instrPixelURL"});
    public static String SIS_END_POINT = "s.amazon-adsystem.com";

    DtbConstants() {
    }
}
